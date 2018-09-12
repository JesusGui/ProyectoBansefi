package mx.babel.bansefi.banksystem.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author gerard.chavez
 *
 */
public class MarshallObjetoUtils {
    private static final Logger LOGGER = LogManager.getLogger(MarshallObjetoUtils.class.getName());
	/**
	 * @param clase Clase de tipo respuesta que regresa un servicio web
	 * @param respuesta instancia concreta del objeto anterior
	 * @return
	 */
	public static Element marshallObjeto(Class clase, Object respuesta){
		DOMResult resultado = new DOMResult();
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(clase);
			 Marshaller m = jc.createMarshaller();
			 m.marshal(respuesta, resultado);
		} catch (JAXBException e) {
		    LOGGER.error("ERROR JAXB:"+e.getMessage());
		}
		return ((Document)resultado.getNode()).getDocumentElement();
	}
	
	/**
	 * @param clase Clase de tipo respuesta que regresa un servicio web
	 * @param peticion peticion a convertir a objeto
	 * @return
	 */
	public static Object unMarshallObjeto(Class<?> clase, Element peticion){
		JAXBContext jc;
		Object obj = null;
		try {
			jc = JAXBContext.newInstance(clase);
			 Unmarshaller unm = jc.createUnmarshaller();
			 obj = unm.unmarshal(peticion);
		} catch (JAXBException e) {
		    LOGGER.error("ERROR JAXB:"+e.getMessage());
		}
		return obj;
	}
	
	/**
	 * @param peticion
	 * @return
	 * 
	 * Metodo que imprime y retorna el xml en String
	 */
	private String imprimeXML(Element peticion){
		javax.xml.transform.TransformerFactory tfactory = TransformerFactory.newInstance();
		javax.xml.transform.Transformer xform;
		
		
		javax.xml.transform.Source src= new DOMSource(peticion);
		java.io.StringWriter writer = new StringWriter();
		Result resultado = new javax.xml.transform.stream.StreamResult(writer);
		try {
			xform = tfactory.newTransformer();
			xform.transform(src, resultado);
		} catch (TransformerException e) {
		    LOGGER.error("ERROR JAXB al imprimir XML:"+e.getMessage());
		}
		StringReader sr = new StringReader(writer.toString());
		System.out.println(writer.toString());
		
		return writer.toString();
	}

}
