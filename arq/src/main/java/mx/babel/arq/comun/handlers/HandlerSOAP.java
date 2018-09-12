package mx.babel.arq.comun.handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.sesion.contexto.services.IBSContextoService;
import mx.babel.arq.sesion.utils.ContextoUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.NodeList;

/**
 * @author gerard.chavez
 * Clase utilizada para manejar y loggear los mensajes de entrada y salida a llamadas SOAP
 *
 */
public class HandlerSOAP implements SOAPHandler<SOAPMessageContext>{
	private static final Logger LOGGER = LogManager.getLogger(HandlerSOAP.class.getName());
	
	@Override
	public void close(MessageContext arg0) {
		//Método blanco por que no se realiza ninguna
	    //acción al cerrar
	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
	    LOGGER.info("---Error en ejecución de Handler...");
	    Boolean outboundProperty = (Boolean)
	            arg0.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);
            
            if (outboundProperty.booleanValue()) {
                LOGGER.info("---Mensaje de salida:");
            } else {
                LOGGER.info("---Mensaje de entrada:");
            }
            SOAPMessage message = arg0.getMessage();
            dumpSOAPMessage(message);
		return true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext smc) throws NoControlableException{
		Boolean outboundProperty = (Boolean)
	            smc.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);
	        
	        if (outboundProperty.booleanValue()) {
	            LOGGER.info("---Mensaje de salida:");
	        } else {
	            LOGGER.info("---Mensaje de entrada:");
	        }

			SOAPMessage message = smc.getMessage();
			dumpSOAPMessage(message);
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		return new HashSet<>();
	}
	
	/**
	 * Log de mensaje SOAP
	 * 
	 * @param msg
	 */
	private void dumpSOAPMessage(SOAPMessage msg) throws NoControlableException{
		if (msg == null) {
		    LOGGER.info("SOAP Message is null");
			return;
		}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				String contrasena = "";
				
				//Validar si se debe encriptar la contraseña en los logs
				if("true".equals(ProveedorMensajeSpringUtils.getValorConfiguracion("pwd.codifica"))){
					SOAPBody body = msg.getSOAPBody();
					NodeList list = body.getElementsByTagName("PASSHEADER");
					
					for (int i = 0; i < list.getLength(); i++) {
						LOGGER.debug(list.item(i).getNodeName() +" - "+list.item(i).getFirstChild());					
						contrasena = list.item(i).getFirstChild().getNodeValue();
					}
				}
				
				msg.writeTo(baos);
				if(!"".equals(contrasena)){
					LOGGER.info(baos.toString(getMessageEncoding(msg)).replace(contrasena, getMD5(contrasena)));
				}else{
					LOGGER.info(baos.toString(getMessageEncoding(msg)));
				}
                
				if (baos.toString().indexOf("IP HEADER-LOGON INCORRECTA")!=-1) 
				{
					
					NodeList list = msg.getSOAPBody().getElementsByTagName("MENSAJE");
					
					throw new NoControlableException("", list.item(0).getTextContent());
				}

                // show included values
                String values = msg.getSOAPBody().getTextContent();
                LOGGER.info("Valores incluidos:" + values);
            } catch (SOAPException e) {
                throw new NoControlableException("Error SOAP al registrar mensaje." , e);
            } catch (UnsupportedEncodingException e) {
                throw new NoControlableException("Error al registar mensaje SOAP, encoding no soportado" , e);
            } catch (IOException e) {
                throw new NoControlableException("Error de I/O" , e);
            }
			

	}
	
	/**
	 * Returns the message encoding (e.g. utf-8)
	 * 
	 * @param msg
	 * @return
	 * @throws javax.xml.soap.SOAPException
	 */
	private String getMessageEncoding(SOAPMessage msg) throws SOAPException {
		String encoding = "utf-8";
		if (msg.getProperty(SOAPMessage.CHARACTER_SET_ENCODING) != null) {
			encoding = msg.getProperty(SOAPMessage.CHARACTER_SET_ENCODING)
					.toString();
		}
		return encoding;
	}

	/**
	 * Method that is used to hash the logged password
	 * 
	 * @param input string with the password to be hashed
	 * @return String with the hashed password
	 */
	private String getMD5(String input){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input.getBytes());
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			LOGGER.debug("Error al obtener instancia de has MD5");
			return input;
		}
	}

}
