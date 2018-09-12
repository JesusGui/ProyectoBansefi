package mx.babel.bansefi.banksystem.personas.backend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.personas.beans.BalanceBean;
import mx.babel.bansefi.banksystem.personas.beans.RegistroBalanceBean;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionbalance.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionbalance.Ejecutar.ITRMODIBLNCETRNI.TRMODIBLNCEEVTY.DCBLLINEAV;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionbalance.Ejecutar.ITRMODIBLNCETRNI.TRMODIBLNCEEVTY.DCDATOSBLNCEDOCV;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionbalance.Ejecutar.ITRMODIBLNCETRNI.TRMODIBLNCEEVTY.DCDOCP;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionbalance.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionbalance.ModificacionBalanceServicio;
import mx.babel.bansefi.banksystem.personas.webservices.modificacionbalance.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.BalanceWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la modificación de un balance
 * 
 * @author babel
 *
 */
@Component
public class ModificacionBalanceBackEnd extends BackEndBean{
	
    private static final long serialVersionUID = 6155428038323533853L;
    
    @Autowired
    private ServicioWebUtils servicioWebUtils;
    
    @Autowired
	BalanceWrapper balanceWrapper;
	
	/**
	 * Función encargada de la modificación de un balance de un cliente
	 * 
	 * @param balance balance a dar de alta 
	 * @return lista de balances asociados a un cliente
	 */
	public Integer ejecutarTRN(List<RegistroBalanceBean> registros, Integer idCliente, BalanceBean balance){
		Ejecutar.ITRMODIBLNCETRNI contexto = initPeticion(registros, idCliente, balance);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){			
			return modificacionbalance(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que devuelve si la respuesta de la modificación del balance ha funcionado o no.
	 * 
	 * @param response
	 * @return
	 */
	private Integer modificacionbalance(ResponseBansefi response){
		Integer resultado = null;
		if(verificaRespuestaCliente(response)){
			return response.getOTRMODIBLNCETRNO().getRTRNCD();
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param balance balance a modificar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRMODIBLNCETRNI initPeticion(List<RegistroBalanceBean> registros, Integer idCliente, BalanceBean balance){
		
		Ejecutar.ITRMODIBLNCETRNI contexto = new Ejecutar.ITRMODIBLNCETRNI();
		Ejecutar.ITRMODIBLNCETRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRMODIBLNCETRNI.STDTRNIPARMV();
		
		DCBLLINEAV bal = null;
		
		super.initialize(contexto);
		
		// TODO: REVISAR WRAPPER Y LOGICA DEL SERVICIO
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_MODI_BLNCE_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		DCDOCP docBalance = new DCDOCP();
		docBalance.setCODNRBEEN(super.getEntidad());
		docBalance.setIDINTERNODC(balance.getCodDocumento()!=null ? Integer.parseInt(balance.getCodDocumento()):0);
		contexto.getTRMODIBLNCEEVTY().setDCDOCP(docBalance);
		
		List<DCBLLINEAV> request = new ArrayList<DCBLLINEAV>();
		for (RegistroBalanceBean reg : registros) {
			
			bal = balanceWrapper.wrappBeanModifBalance(reg);
			request.add(bal);
		}
		
		contexto.getTRMODIBLNCEEVTY().setDCBLLINEAV(request);
		
		DCDATOSBLNCEDOCV datos = new DCDATOSBLNCEDOCV();
		datos.setCODDOC(ConstantesFuncionales.COD_DOC_BALANCE);
		datos.setCODNRBEEN(super.getEntidad());
		datos.setFECHACDCDADDC(Integer.parseInt(
				ConstantesFuncionales.MAX_FECHA_FIN.substring(6,10)+
				ConstantesFuncionales.MAX_FECHA_FIN.substring(3,5)+
				ConstantesFuncionales.MAX_FECHA_FIN.substring(0,2)
				));
		datos.setIDINTERNOPE(idCliente);
		datos.setTXTDC(balance.getDescripcion());
		/*datos.setFECHAREVDC(Integer.parseInt(balance.getAnyo()+
				("0"+Calendar.getInstance().get(Calendar.MONTH)).substring(0,2)+""+
				("0"+Calendar.getInstance().get(Calendar.DATE)).substring(0,2)));*/

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fechaFormateada = sdf.format(new Date());
		
		datos.setFECHAREVDC(Integer.parseInt(fechaFormateada));
		datos.setINDVIGENDC(ConstantesFuncionales.IND_SI);
		datos.setINDVIGENDC(ConstantesFuncionales.IND_SI);
		
		contexto.getTRMODIBLNCEEVTY().setDCDATOSBLNCEDOCV(datos);

		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRMODIBLNCETRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					ModificacionBalanceServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion de balance", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta del servidor no este vacía. 
	 *  
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos del balance
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRMODIBLNCETRNO() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
