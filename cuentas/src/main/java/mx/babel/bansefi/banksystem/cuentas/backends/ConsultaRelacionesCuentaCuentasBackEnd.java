package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentacuentas.ConsultaRelacionesCuentaCuentasServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentacuentas.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentacuentas.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentacuentas.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentacuentas.ResponseBansefi.OTRCONSULTARXPANTTRN.RXDATOSAC1V;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentacuentas.ResponseBansefi.OTRCONSULTARXPANTTRN.TRCONSULTARXPANTEVTZ.TRCONSULTARXAC1V.RXACRLACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentacuentas.ResponseBansefi.OTRCONSULTARXPANTTRN.TRCONSULTARXPANTEVTZ.TRCONSULTARXAC1V.RXACRLACV;
import mx.babel.bansefi.banksystem.cuentas.wrappers.RelacionesCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaRelacionesCuentaCuentasBackEnd extends BackEndBean{

	private static final long serialVersionUID = -1951634735873354922L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	RelacionesCuentaWrapper relacionesCuentaWrapper;
	
	private static final String IND_TP_CONSULTA_RX_V = "2";
	
	public List<CuentaRelacionadaBean> ejecutarTRN(Long numeroCuenta){
		return ejecutarTRN(numeroCuenta, false);
	}

	public List<CuentaRelacionadaBean> ejecutarTRN(Long numeroCuenta, Boolean activas){
		List<CuentaRelacionadaBean> cuentas = new ArrayList<CuentaRelacionadaBean>();
		Ejecutar.ITRCONSULTARXPANTTRN contexto = initPeticion(numeroCuenta, activas);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return cuentas;
			}
		}
		return getRelaciones(respuesta.getResponseBansefi());
	}
		
	private List<CuentaRelacionadaBean> getRelaciones(ResponseBansefi respuesta){
		List<CuentaRelacionadaBean> cuentas = new ArrayList<CuentaRelacionadaBean>();		
		if(verificaRespuestaCliente(respuesta)){
			for(int i = 0; i < respuesta.getOTRCONSULTARXPANTTRN().getNUMBEROFRECORDS(); i++){
				RXACRLACE relacion = respuesta.getOTRCONSULTARXPANTTRN().getTRCONSULTARXPANTEVTZ().getTRCONSULTARXAC1V().getRXACRLACE().get(i);
				RXACRLACV detalles = respuesta.getOTRCONSULTARXPANTTRN().getTRCONSULTARXPANTEVTZ().getTRCONSULTARXAC1V().getRXACRLACV().get(i);
				RXDATOSAC1V nombreProducto = respuesta.getOTRCONSULTARXPANTTRN().getRXDATOSAC1V().get(i);
				CuentaRelacionadaBean cuentaRelacionadaBean = relacionesCuentaWrapper.wrappBean(relacion, detalles);
				cuentaRelacionadaBean.getCuenta().setTipoCuenta(nombreProducto.getNOMBPDV().trim());
				cuentaRelacionadaBean.getCuenta().setEstado(nombreProducto.getCODECVAC().trim());
				if(ConstantesFuncionales.COD_ECV_AC_AC_INACTIVA.equals(cuentaRelacionadaBean.getEstadoRelacion())){
					cuentaRelacionadaBean.setEstadoListado(EstadoListadosEnum.INACTIVO);
				}else{
					cuentaRelacionadaBean.setEstadoListado(EstadoListadosEnum.CONSULTADO);
				}
				if(cuentaRelacionadaBean.getCuenta() != null && cuentaRelacionadaBean.getCuenta().getNumeroCuenta() != 0){
					cuentas.add(cuentaRelacionadaBean);
				}
			}
		}
		return cuentas;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSULTARXPANTTRN initPeticion(Long numeroCuenta, Boolean activas){
		Ejecutar.ITRCONSULTARXPANTTRN contexto = new Ejecutar.ITRCONSULTARXPANTTRN();
		
		Ejecutar.ITRCONSULTARXPANTTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONSULTARXPANTTRN.STDTRNIPARMV();
		
		Ejecutar.ITRCONSULTARXPANTTRN.TRCONSULTARXPANTEVTY cuerpoContexto =
				new Ejecutar.ITRCONSULTARXPANTTRN.TRCONSULTARXPANTEVTY();
		contexto.setTRCONSULTARXPANTEVTY(cuerpoContexto);
		contexto.setSTDTRNIPARMV(contextoEntrada);	
		super.initialize(contexto);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSULTA_RX_PANT_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());	
		
		cuerpoContexto.getRXACRLACE().setCODNRBEEN(super.getEntidad());
		cuerpoContexto.getRXACRLACE().setNUMSECAC1(numeroCuenta);
		
		cuerpoContexto.getINDTPCONSULTARXV().setSTDCHAR01(ConsultaRelacionesCuentaCuentasBackEnd.IND_TP_CONSULTA_RX_V);
		
		contexto.setSCROLLABLEOCCURS(BackEndBean.SCROLLABLE_OCCURS);
		
		if(activas){
			cuerpoContexto.getRXCAMPOOPCV().setCODECVACAC(ConstantesFuncionales.COD_ECV_AC_AC_ACTIVA);
		}
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSULTARXPANTTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaRelacionesCuentaCuentasServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "anotaciones.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRCONSULTARXPANTTRN() != null &&
				response.getOTRCONSULTARXPANTTRN().getTRCONSULTARXPANTEVTZ() != null){
			noNulo = true;
		}
		return noNulo;
	}
}
