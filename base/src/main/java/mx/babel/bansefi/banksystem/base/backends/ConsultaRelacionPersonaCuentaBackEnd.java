package mx.babel.bansefi.banksystem.base.backends;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.TipoRelacionPersonaCuenta;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.ConsultaRelacionPersonasCuentaServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.ResponseBansefi.OTRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTZ.RPPERSRLACE;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.ResponseBansefi.OTRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTZ.RPPERSV;
import mx.babel.bansefi.banksystem.base.wrappers.ConsultaRelacionesCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para consulta de relaciones de persona-cuenta.
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaRelacionPersonaCuentaBackEnd extends BackEndBean {

	private static final long serialVersionUID = 8652345623605998295L;
	
	private static final String STDCHAR_02 = "4";

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaRelacionesCuentaWrapper relacionesCuentaWrapper;

	/**
	 * Método para obtener las relaciones existentes de personas para una cuenta.
	 * 
	 * @param numeroCuenta El número de la cuenta que se va a consultar
	 * @return Lista de personas relacionadas a la cuenta
	 */
	public List<RelacionadoBean> ejecutarTRN(Long numeroCuenta, Boolean detallesBeneficiarios){
		Ejecutar.ITRCONSULTARPPANTTRN contexto = initPeticion(numeroCuenta);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return new ArrayList<RelacionadoBean>();
			}
		}
		
		return getRelacionados(respuesta.getResponseBansefi(), numeroCuenta, detallesBeneficiarios);
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param numeroCuenta Numero de la cuenta del cual se quieren obtener las relaciones
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRCONSULTARPPANTTRN initPeticion(Long numeroCuenta) {
		Ejecutar.ITRCONSULTARPPANTTRN contexto = new Ejecutar.ITRCONSULTARPPANTTRN();
		Ejecutar.ITRCONSULTARPPANTTRN.CONSULTAV stdchar = new Ejecutar.ITRCONSULTARPPANTTRN.CONSULTAV();
		Ejecutar.ITRCONSULTARPPANTTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONSULTARPPANTTRN.STDTRNIPARMV();
		Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY cuerpoContexto =
				new Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY();
		Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY.RPPERSRLACP relacion = 
				new Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY.RPPERSRLACP();
		cuerpoContexto.setRPPERSRLACP(relacion);	
		contexto.setCONSULTAV(stdchar);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRCONSULTARPPANTEVTY(cuerpoContexto);
		super.initialize(contexto);
		
		relacion.setCODNRBEEN(super.getEntidad());
		relacion.setNUMSECAC(numeroCuenta);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONSULTA_RP_PANT_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		stdchar.setSTDCHAR02(ConsultaRelacionPersonaCuentaBackEnd.STDCHAR_02);
		return contexto;
	}
	
	/**
	 * Función encargada de construir una lista de relaciones a través de la
	 * respuesta del servicio web.
	 * 
	 * @param response respuesta del servicio web
	 * @return lista con los detalles de relaciones
	 */
	private List<RelacionadoBean> getRelacionados(ResponseBansefi response, Long numeroCuenta, Boolean detallesBeneficiarios)
			throws NoControlableException, ControlableException{
		List<RelacionadoBean> relacionados = new ArrayList<RelacionadoBean>();
		if(verificaRespuestaRelaciones(response)){
			int numBeneficiarios = 0;
			for (int i = 0;i< response.getOTRCONSULTARPPANTTRN().getNUMBEROFRECORDS(); i++) {
				RPPERSRLACE relacionado = response.getOTRCONSULTARPPANTTRN().getTRCONSULTARPPANTEVTZ().getRPPERSRLACE().get(i);
				RPPERSV persona = response.getOTRCONSULTARPPANTTRN().getTRCONSULTARPPANTEVTZ().getRPPERSV().get(i);
				RelacionadoBean relacionadoBean = relacionesCuentaWrapper.wrappBean(relacionado, persona);
				TipoRelacionPersonaCuenta relacion = TipoRelacionPersonaCuenta.codigoDe(relacionado.getCODRLPERSAC());
				relacionadoBean.setTipo(relacion);
				relacionadoBean.setGarantia(response.getOTRCONSULTARPPANTTRN().getTRCONSULTARPPANTEVTZ().getRPPERSRLACV().get(i).getIMPGRTZAI());
				if(detallesBeneficiarios && TipoRelacionPersonaCuenta.BENEFICIARIO.equals(relacion)){
					numBeneficiarios++;
					Ejecutar.ITRCONSULTARPPANTTRN contexto = initPeticion(numeroCuenta);
					contexto.getTRCONSULTARPPANTEVTY().getRPCAMPOOPCV().setNUMRLORDEN(numBeneficiarios);
					contexto.getTRCONSULTARPPANTEVTY().getRPPERSRLACP().setCODRLPERSAC(relacionado.getCODRLPERSAC());	
					EjecutarResult respuesta = ejecutarWS(contexto);
					if(verificaResponseBansefi(respuesta)){
						relacionadoBean.setPorcentaje(respuesta.getResponseBansefi().getOTRCONSULTARPPANTTRN().getTRCONSULTARPPANTEVTZ().getPCTV().getPCTCOEF());
					}						
				}
				if(ConstantesFuncionales.COD_ECV_PERS_AC_INACTIVA.equals(relacionadoBean.getEstado())){
					if(verificaFechaInicio(relacionadoBean.getFechaInicio())){
						relacionadoBean.setEstadoListado(EstadoListadosEnum.POR_REACTIVAR);
					}else{
						relacionadoBean.setEstadoListado(EstadoListadosEnum.INACTIVO);
					}
				}else{
					relacionadoBean.setEstadoListado(EstadoListadosEnum.CONSULTADO);
				}
				relacionadoBean.setCodPe(response.getOTRCONSULTARPPANTTRN().getTRCONSULTARPPANTEVTZ().getRPPERSV().get(i).getCODPE());
				relacionados.add(relacionadoBean);
			}
		}
		return relacionados;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSULTARPPANTTRN contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaRelacionPersonasCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "relaciones persona-cuenta.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respues del servidor no este vacía. 
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
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene un cliente de tipo persona moral
	 */
	private Boolean verificaRespuestaRelaciones(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRCONSULTARPPANTTRN() != null &&
				response.getOTRCONSULTARPPANTTRN().getTRCONSULTARPPANTEVTZ() != null && 
				response.getOTRCONSULTARPPANTTRN().getTRCONSULTARPPANTEVTZ().getRPPERSRLACE() != null){
			noNulo = true;
		}
		return noNulo;
	}
		
	/**
	 * Función para validar si un relacionado está en espera de reactivación.
	 * @param fechaInicio fecha en que se reactivara la relación
	 * @return <code>true</code> si la fecha de inicio es posterior a la fecha actual
	 */
	private Boolean verificaFechaInicio(Date fechaInicio){
		Calendar fechaActual = Calendar.getInstance(); 
		fechaActual.setTime(super.getFechaSistema());
		Calendar inicio = Calendar.getInstance();
		inicio.setTime(fechaInicio);
		return fechaActual.before(inicio);
	}
}
