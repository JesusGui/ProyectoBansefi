package mx.babel.bansefi.banksystem.base.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.TipoRelacionPersonaCuenta;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascliente.ConsultaCuentasClienteServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascliente.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascliente.Ejecutar.ITRCONSULTARPPANT5TR;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascliente.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascliente.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ClienteWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consulta de cuentas asociadas a un cliente en especifico
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaCuentasClienteBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 6414886644170893995L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	ClienteWrapper personasWrapper;
	
	/**
	 * Función encargada de entrgar una lista con las cuentas que estan 
	 * asociadas a un cliente por medio de servicios web.
	 * 
	 * @param idInterno id del cliente al cual están asignadas las cuentas
	 * @return lista de cuentas asociadas a un cliente
	 */
	public List<CuentaClienteBean> ejecutarTRN(Integer idInterno){
		return ejecutarTRN(idInterno, false);
	}
	
	/**
	 * Función encargada de entrgar una lista con las cuentas que estan 
	 * asociadas a un cliente por medio de servicios web.
	 * 
	 * @param idInterno id del cliente al cual están asignadas las cuentas
	 * @return lista de cuentas asociadas a un cliente
	 */
	public List<CuentaClienteBean> ejecutarTRN(Integer idInterno, Boolean cuentasActivas){
		ITRCONSULTARPPANT5TR contexto = initPeticion(idInterno, cuentasActivas);
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return null;
			}
		}
		
		if(verificaResponseBansefi(respuesta)){
			return getCuentas(respuesta.getResponseBansefi(), cuentasActivas);
		}
		return null;
	}
	
	/**
	 * Función que construye la lista de cuentas a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private List<CuentaClienteBean> getCuentas(ResponseBansefi response, Boolean cuentasActivas){
		List<CuentaClienteBean> resultado = null;
		if(verificaRespuestaListaClientes(response)){
			List<ResponseBansefi.OTRCONSULTARPPANT5TR.TRCONSULTARPPANT5EVT.RPPERSRLACE> cuentas = 
					response.getOTRCONSULTARPPANT5TR().getTRCONSULTARPPANT5EVT().getRPPERSRLACE();
			List<ResponseBansefi.OTRCONSULTARPPANT5TR.TRCONSULTARPPANT5EVT.RPPERSRLACV> detalleProducto = 
					response.getOTRCONSULTARPPANT5TR().getTRCONSULTARPPANT5EVT().getRPPERSRLACV();
			List<ResponseBansefi.OTRCONSULTARPPANT5TR.TRCONSULTARPPANT5EVT.RPACV> cuentasDetalles = 
					response.getOTRCONSULTARPPANT5TR().getTRCONSULTARPPANT5EVT().getRPACV();
			List<ResponseBansefi.OTRCONSULTARPPANT5TR.TRCONSULTARPPANT5EVT.ECVACV> cuentasEstados = 
					response.getOTRCONSULTARPPANT5TR().getTRCONSULTARPPANT5EVT().getECVACV();
			resultado = new ArrayList<CuentaClienteBean>();
			for (int i =0; i < response.getOTRCONSULTARPPANT5TR().getNUMBEROFRECORDS(); i++){
				CuentaClienteBean cuentaClienteBean = personasWrapper.wrappbean(cuentas.get(i));
				TipoRelacionPersonaCuenta relacion = TipoRelacionPersonaCuenta.codigoDe(cuentaClienteBean.getRelacion());
				if(relacion != null){
					cuentaClienteBean.setRelacion(relacion.getNombre());
				}
				cuentaClienteBean.getCuenta().setTipoCuenta(cuentasDetalles.get(i).getNOMBPDV());
				cuentaClienteBean.getCuenta().setEstado(cuentasEstados.get(i).getCODECVAC());
				cuentaClienteBean.getCuenta().setCodLinea(detalleProducto.get(i).getCODLINEA());
				cuentaClienteBean.getCuenta().setIdGrupoProducto(detalleProducto.get(i).getIDGRPPD());
				cuentaClienteBean.getCuenta().setIdProducto(detalleProducto.get(i).getIDPDV());
				if((cuentasActivas && cuentasEstados.get(i).getCODECVAC().equals(ConstantesFuncionales.ESTADO_CUENTA_ACTIVO) &&
					cuentas.get(i).getCODRLPERSAC().equals(TipoRelacionPersonaCuenta.TITULAR.getCodigo())) 
					|| !cuentasActivas){
					resultado.add(cuentaClienteBean);
				}
			}
		}
		return resultado;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del cliente de tipo persona física
	 * @return Objeto de petición al web service
	 */
	private ITRCONSULTARPPANT5TR initPeticion(Integer idInterno, Boolean cuentasActivas){
		Ejecutar.ITRCONSULTARPPANT5TR contexto = new Ejecutar.ITRCONSULTARPPANT5TR();
		Ejecutar.ITRCONSULTARPPANT5TR.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONSULTARPPANT5TR.STDTRNIPARMV();
		Ejecutar.ITRCONSULTARPPANT5TR.TRCONSULTARPPANT5EVT cuerpoContexto =
				new Ejecutar.ITRCONSULTARPPANT5TR.TRCONSULTARPPANT5EVT();
		Ejecutar.ITRCONSULTARPPANT5TR.TRCONSULTARPPANT5EVT.RPPERSRLACP cliente = 
				new Ejecutar.ITRCONSULTARPPANT5TR.TRCONSULTARPPANT5EVT.RPPERSRLACP();
		contexto.setTRCONSULTARPPANT5EVT(cuerpoContexto);
		cuerpoContexto.setRPPERSRLACP(cliente);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		super.initialize(contexto);
		
		cliente.setCODNRBEEN(super.getEntidad());
		if(idInterno != null){
			cliente.setIDINTERNOPE(idInterno);
		}
		
		if(cuentasActivas){
			cuerpoContexto.getECVACV().setCODECVAC(ConstantesFuncionales.ESTADO_CUENTA_ACTIVO);
			Ejecutar.ITRCONSULTARPPANT5TR.TRCONSULTARPPANT5EVT.TIPRELV relacion = 
					new Ejecutar.ITRCONSULTARPPANT5TR.TRCONSULTARPPANT5EVT.TIPRELV();
			super.initialize(relacion);
			relacion.setCODRLPERSAC(TipoRelacionPersonaCuenta.TITULAR.getCodigo());
			cuerpoContexto.getTIPRELV().add(relacion);
		}
		
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSULTA_RP_PANT_5_TRN);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSULTARPPANT5TR contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaCuentasClienteServicio.class, contexto);			
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "cuentas de cliente.", e);
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
		if(respuesta != null && respuesta.getResponseBansefi() != null &&  
				respuesta.getResponseBansefi().getOTRCONSULTARPPANT5TR() !=null){
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene un cliente de tipo persona moral
	 */
	private Boolean verificaRespuestaListaClientes(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRCONSULTARPPANT5TR() != null &&
				response.getOTRCONSULTARPPANT5TR().getTRCONSULTARPPANT5EVT() != null &&
				response.getOTRCONSULTARPPANT5TR().getTRCONSULTARPPANT5EVT().getRPPERSRLACE() != null){
			noNulo = true;
		}
		return noNulo;
	}	
}
