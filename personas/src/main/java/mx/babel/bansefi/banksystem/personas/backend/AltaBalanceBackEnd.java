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
import mx.babel.bansefi.banksystem.personas.beans.RegistroBalanceBean;
import mx.babel.bansefi.banksystem.personas.webservices.altabalance.AltaBalanceServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altabalance.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altabalance.Ejecutar.ITRALTABLNCETRNI.TRALTABLNCEEVTY.DCBLLINEAV;
import mx.babel.bansefi.banksystem.personas.webservices.altabalance.Ejecutar.ITRALTABLNCETRNI.TRALTABLNCEEVTY.DCDATOSBLNCEDOCV;
import mx.babel.bansefi.banksystem.personas.webservices.altabalance.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.altabalance.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.wrappers.BalanceWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para el alta de un balance
 * 
 * @author babel
 *
 */
@Component
public class AltaBalanceBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 6911322837306883506L;
    
	@Autowired
	private ServicioWebUtils servicioWebUtils;
	
    @Autowired
	BalanceWrapper balanceWrapper;
	
	/**
	 * Función encargada del alta de un balance
	 * 
	 * @param balance balance a dar de alta
	 * @return Integer Resultado de la operación
	 */
	public Integer ejecutarTRN(List<RegistroBalanceBean> balance, Integer idPersona, String desc, String anyo){
		Ejecutar.ITRALTABLNCETRNI contexto = initPeticion(balance,idPersona,desc,anyo);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if(verificaResponseBansefi(respuesta)){
			return altabalance(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que realiza el alta del balance y controla la respuesta
	 * 
	 * @param response
	 * @return
	 */
	private Integer altabalance(ResponseBansefi response){
		Integer resultado = null;
		if(verificaRespuestaCliente(response)){
			return response.getOTRALTABLNCETRNO().getRTRNCD();
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param persona balance a dar de alta
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRALTABLNCETRNI initPeticion(List<RegistroBalanceBean> balance, Integer idPersona, String desc, String anyo){
		
		Ejecutar.ITRALTABLNCETRNI contexto = new Ejecutar.ITRALTABLNCETRNI();
		Ejecutar.ITRALTABLNCETRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRALTABLNCETRNI.STDTRNIPARMV();
		
		DCBLLINEAV bal = null;
		
		super.initialize(contexto);
		
		// TODO: REVISAR WRAPPER Y LOGICA DEL SERVICIO
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_ALTA_BLNCE_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		List<DCBLLINEAV> request = new ArrayList<DCBLLINEAV>();
		for (RegistroBalanceBean reg : balance) {
			bal = balanceWrapper.wrappBeanAltaBalance(reg);
			request.add(bal);
		}
		
		contexto.getTRALTABLNCEEVTY().setDCBLLINEAV(request);
		
		DCDATOSBLNCEDOCV datos = new DCDATOSBLNCEDOCV();
		datos.setCODDOC(ConstantesFuncionales.COD_DOC_BALANCE);
		datos.setCODNRBEEN(super.getEntidad());
		datos.setFECHACDCDADDC(Integer.parseInt(
				ConstantesFuncionales.MAX_FECHA_FIN.substring(6,10)+
				ConstantesFuncionales.MAX_FECHA_FIN.substring(3,5)+
				ConstantesFuncionales.MAX_FECHA_FIN.substring(0,2)
				));
		datos.setIDINTERNOPE(idPersona);
		datos.setTXTDC(desc);
		
		if (anyo==null) {
			anyo = ""+Calendar.getInstance().get(Calendar.YEAR);
		}
		
		/*datos.setFECHAREVDC(Integer.parseInt(anyo+
				("0"+Calendar.getInstance().get(Calendar.MONTH)).substring(0,2)+""+
				("0"+Calendar.getInstance().get(Calendar.DATE)).substring(0,2)));*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fechaFormateada = sdf.format(new Date());
		
		datos.setFECHAREVDC(Integer.parseInt(fechaFormateada));
		datos.setINDVIGENDC(ConstantesFuncionales.IND_SI);
		
		contexto.getTRALTABLNCEEVTY().setDCDATOSBLNCEDOCV(datos);
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRALTABLNCETRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					AltaBalanceServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta de balance", e);
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
		if(response!= null && response.getOTRALTABLNCETRNO() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
