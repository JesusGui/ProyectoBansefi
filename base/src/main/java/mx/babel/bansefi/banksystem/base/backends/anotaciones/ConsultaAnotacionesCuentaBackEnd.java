package mx.babel.bansefi.banksystem.base.backends.anotaciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.anotacionesCuenta.ConsultaAnotacionesCuentaServicio;
import mx.babel.bansefi.banksystem.base.webservices.anotacionesCuenta.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.anotacionesCuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.anotacionesCuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.anotacionesCuenta.ResponseBansefi.OTRINFORAVISOSANTRNO.STDANAVMSJV.STDANAVMSJLS;
import mx.babel.bansefi.banksystem.base.wrappers.AnotacionWrapper;

/**
 * BackEnd para la consulta de anotaciones de una cuenta
 * @author alejandro.villegas
 *
 */
@Component
public class ConsultaAnotacionesCuentaBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 8429227450003921548L;
	private static final Integer EVENT_CD = 1;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	@Autowired
	ContextoUtils contextoUtils;
	@Autowired
	AnotacionWrapper anotacionWrapper;
	
	public List<AnotacionBean> ejecutarTRN(Long numeroCuenta)
			throws ControlableException, NoControlableException{
		
		List<AnotacionBean> listaAnotaciones = new ArrayList<AnotacionBean>();
		
		Ejecutar.ITRINFORAVISOSANTRNI contexto = this.initPeticion(numeroCuenta);
		EjecutarResult respuesta = this.getResponse(contexto);
		
		super.verificaRespuesta(respuesta);
		
		listaAnotaciones = getAnotaciones(respuesta.getResponseBansefi());
		
		return listaAnotaciones;
	}
	
	/**
	 * Función que inicializa el contexto para ejecutar la TRN
	 * @param cuentaBean
	 * @return contexto
	 */
	private Ejecutar.ITRINFORAVISOSANTRNI initPeticion(Long numeroCuenta){
		Ejecutar.ITRINFORAVISOSANTRNI contexto = new Ejecutar.ITRINFORAVISOSANTRNI();
		Ejecutar.ITRINFORAVISOSANTRNI.ANANTCNRLACP anantcnrlacp = new Ejecutar.ITRINFORAVISOSANTRNI.ANANTCNRLACP();
		Ejecutar.ITRINFORAVISOSANTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRINFORAVISOSANTRNI.STDTRNIPARMV();
		
		anantcnrlacp.setCODNRBEEN(super.getEntidad());
		anantcnrlacp.setNUMSECAC(numeroCuenta);
		
		stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_INFOR_AVISOS_AN_TRN);
		
		contexto.setANANTCNRLACP(anantcnrlacp);
		contexto.setSTDTRNIPARMV(stdtrniparmv);
		contexto.setEVENTCD(EVENT_CD);
		
		return contexto;
	}
	
	/**
	 * Funcion para invocar  a la TRN
	 * @param contexto a peticion de la TRN
	 * @return respuesta obtenida de la TRN
	 */
	private EjecutarResult getResponse(Ejecutar.ITRINFORAVISOSANTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = new EjecutarResult();
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
												ConsultaAnotacionesCuentaServicio.class, contexto);
		} catch(NoControlableException nce) {
			throw new NoControlableException("Error al invocar servicio web de consulta de anotaciones de cuenta.", nce);
		}
		
		return respuesta;
	}
	
	private List<AnotacionBean> getAnotaciones(ResponseBansefi response){
		List<AnotacionBean> listaAnotaciones = new ArrayList<AnotacionBean>();
		
		List<STDANAVMSJLS> anotaciones = response.getOTRINFORAVISOSANTRNO().getSTDANAVMSJV().getSTDANAVMSJLS();
		
		for (STDANAVMSJLS elemento : anotaciones) {
			if(elemento.getNUMEROANTCN() != 0L){
				listaAnotaciones.add(anotacionWrapper.wrappBean(elemento));
			}
		}
		
		return listaAnotaciones;
	}

}
