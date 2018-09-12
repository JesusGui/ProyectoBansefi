package mx.babel.bansefi.banksystem.base.backends.anotaciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.AnotacionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.consultarespuestasanotaciones.ConsultaRespuestaAnotacionesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultarespuestasanotaciones.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultarespuestasanotaciones.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultarespuestasanotaciones.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la consulta de respuestas a una anotacion
 * 
 * @author javier.martinnino
 *
 */
@Component
public class ConsultaRespuestasAnotacionBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 8758478567528294078L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Función encargada de consultar las respuestas a una anotacion por medio de servicio web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @return List<AnotacionBean> lista de respuestas de anotaciones
	 */
	public List<AnotacionBean> ejecutarTRN(AnotacionBean anotacion){
		Ejecutar.ITRCONSULTAANMANTRNI contexto = initPeticion(anotacion);
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
			return consultaRespuestasAnotacion(respuesta.getResponseBansefi());
		}
		return null;
	}
	
	/**
	 * Función que construye el detalle de la anotacion a partir de la respuesta al servidor.
	 * 
	 * @param response
	 * @return
	 */
	private List<AnotacionBean> consultaRespuestasAnotacion(ResponseBansefi response){
		List<AnotacionBean> resultado = null;		
		if(verificaRespuestaCliente(response)){
			resultado = new ArrayList<>();
			IntegerToDateConverter converter = new IntegerToDateConverter();
			for (int i=0;i<response.getOTRCONSULTAANMANTRNO().getNUMBEROFRECORDS();i++){
				AnotacionBean anot = new AnotacionBean();
				anot.setTipo(response.getOTRCONSULTAANMANTRNO().getTRCONSULTAANMANEVTZ().getANCONTESMANV().get(i).getCODANTCN1().trim());
				anot.setSubcodigo(response.getOTRCONSULTAANMANTRNO().getTRCONSULTAANMANEVTZ().getANCONTESMANV().get(i).getSUBCDANTCN1().trim());
				// Incluimos la logica de front para montar el valor de catalogo y poder obtener el tipo correcto.
				if (anot.getTipo()!=null && anot.getSubcodigo()!=null){
					anot.setSubcodigo(anot.getTipo()+anot.getSubcodigo());
				}
				anot.setNumero(response.getOTRCONSULTAANMANTRNO().getTRCONSULTAANMANEVTZ().getANCONTESMANV().get(i).getNUMEROANTCN1());
				anot.setFechaInicio(converter.convertTo(response.getOTRCONSULTAANMANTRNO().getTRCONSULTAANMANEVTZ().getANCONTESMANV().get(i).getFECHAINIC(),null));
				anot.setIdEmpleado(response.getOTRCONSULTAANMANTRNO().getTRCONSULTAANMANEVTZ().getANCONTESMANV().get(i).getANEMPLV().get(0).getIDINTERNOEMPLEP().trim());
				anot.setNombreEmpleado(response.getOTRCONSULTAANMANTRNO().getTRCONSULTAANMANEVTZ().getANCONTESMANV().get(i).getANNOMBEMPV().get(0).getNOMB50().trim());
				resultado.add(anot);
			}
			Collections.sort(resultado, new Comparator<AnotacionBean>() {
				@Override
				public int compare(AnotacionBean o1, AnotacionBean o2) {
					Long key1 = o1.getNumero();
					Long key2 = o2.getNumero();
					return key1.compareTo(key2);
				}
			});
		}
		return resultado;
	}
	
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param anotacion anotacion a consultar
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSULTAANMANTRNI initPeticion(AnotacionBean anotacion){
		Ejecutar.ITRCONSULTAANMANTRNI contexto = new Ejecutar.ITRCONSULTAANMANTRNI();
		
		Ejecutar.ITRCONSULTAANMANTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONSULTAANMANTRNI.STDTRNIPARMV();
		
		Ejecutar.ITRCONSULTAANMANTRNI.TRCONSULTAANMANEVTY cuerpoContexto =
				new Ejecutar.ITRCONSULTAANMANTRNI.TRCONSULTAANMANEVTY();
		
		Ejecutar.ITRCONSULTAANMANTRNI.TRCONSULTAANMANEVTY.ANANTCNRLANTCNP anot =
				new Ejecutar.ITRCONSULTAANMANTRNI.TRCONSULTAANMANEVTY.ANANTCNRLANTCNP();
				
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSULTA_AN_MAN_TRN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDEMPLAUT("");
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());		
		
		anot.setCODNRBEEN(super.getEntidad());
		anot.setCODANTCN(anotacion.getTipo());
		anot.setSUBCDANTCN(anotacion.getSubcodigo());
		// Incluimos la logica de front para hacer viajar el valor correcto esperado en back 
		// quitando el primer elemento de la cadena que viaja
		if (anotacion.getSubcodigo() !=null && anotacion.getSubcodigo().length()>1){
			anot.setSUBCDANTCN(anotacion.getSubcodigo().substring(1));
		}
		anot.setNUMEROANTCN(anotacion.getNumero());
		
		for(int i=1;i<5;i++){
			Ejecutar.ITRCONSULTAANMANTRNI.TRCONSULTAANMANEVTY.ANANTCNRLOPCV tiposConsulta =
					new Ejecutar.ITRCONSULTAANMANTRNI.TRCONSULTAANMANEVTY.ANANTCNRLOPCV();
			tiposConsulta.setCODANTCN(String.valueOf(i));
			cuerpoContexto.getANANTCNRLOPCV().add(tiposConsulta);
		}		
		
		cuerpoContexto.setANANTCNRLANTCNP(anot);		
		contexto.setTRCONSULTAANMANEVTY(cuerpoContexto);		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSULTAANMANTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaRespuestaAnotacionesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "respuesta de anotaciones.", e);
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
	 * Función que valida que la respuesta Bansefi.
	 * 
	 * @param response Respuesta Bansefi 
	 * @return <code>true</code> si la respuesta Bansefi contiene elemento no nulos
	 */
	private Boolean verificaRespuestaCliente(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRCONSULTAANMANTRNO() != null &&
				response.getOTRCONSULTAANMANTRNO().getTRCONSULTAANMANEVTZ() != null &&
				response.getOTRCONSULTAANMANTRNO().getTRCONSULTAANMANEVTZ().getANCONTESMANV() != null){
			noNulo = true;
		}
		return noNulo;
	}	
	
}
