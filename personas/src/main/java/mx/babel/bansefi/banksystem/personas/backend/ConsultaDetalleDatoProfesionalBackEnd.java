package mx.babel.bansefi.banksystem.personas.backend;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.personas.beans.ActividadProfesionalBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledatoprofesional.ConsultaDetalleDatoProfesionalServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledatoprofesional.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledatoprofesional.Ejecutar.ITRPECONSCOMPLPROFTR;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledatoprofesional.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.wrappers.DatosEconomicosWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para ejecutar el servicio de detalle de un Dato Profesional.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class ConsultaDetalleDatoProfesionalBackEnd extends BackEndBean {

	private static final long serialVersionUID = 5333623697345552231L;

	@Autowired
	private DatosEconomicosWrapper datosEconomicosWrapper;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar la TRN de consulta Detalle de un Dato Profesiona.
	 * 
	 * @param datoProfesional Bean de dato profesional a consultar.
	 * @return ActividadProfesionalBean
	 */
	public ActividadProfesionalBean ejecutarTRN(ActividadProfesionalBean datoProfesional){
		Ejecutar.ITRPECONSCOMPLPROFTR contexto = new Ejecutar.ITRPECONSCOMPLPROFTR();

		Ejecutar.ITRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT contextoEntrada = new Ejecutar.ITRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT();

		Ejecutar.ITRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT.PEACTPROFINDVP contextoEntradaCampos = new Ejecutar.ITRPECONSCOMPLPROFTR.TRPECONSCOMPLPROFEVT.PEACTPROFINDVP();
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setIDINTERNOPE(datoProfesional.getIdInterno());
		contextoEntradaCampos.setNUMACTPROFIN(datoProfesional.getNumActProfIn());
		contextoEntrada.setPEACTPROFINDVP(contextoEntradaCampos);

		Ejecutar.ITRPECONSCOMPLPROFTR.STDTRNIPARMV contextoComun = new Ejecutar.ITRPECONSCOMPLPROFTR.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT24CON");
		contextoComun.setCODTXDI("");

		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRPECONSCOMPLPROFEVT(contextoEntrada);

		return this.obtenerRespuestaServicio(contexto, datoProfesional);

	}

	/**
	 * Método para obtener el resultado del servicio de consulta detalle de Dato
	 * Profesional.
	 * 
	 * @param contextoEntrada
	 *            Entrada con los datos a consultar
	 * @param datoProfesional
	 *            Bean de dato profesional a consultar.
	 * @return ActividadProfesionalBean
	 */
	private ActividadProfesionalBean obtenerRespuestaServicio(
			ITRPECONSCOMPLPROFTR contextoEntrada, ActividadProfesionalBean datoProfesional){
		int fechaInvalidaInt1 = 99991231;
		int fechaInvalidaInt2 = 19500101;
		int fechaInvalidaInt3 = 11111111;
		IntegerToDateConverter converterITD = new IntegerToDateConverter();
		Date fechaInvalida1 = converterITD.convertTo(99991231);
		Date fechaInvalida2 = converterITD.convertTo(19500101);
		Date fechaInvalida3 = converterITD.convertTo(11111111);
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ConsultaDetalleDatoProfesionalServicio.class, contextoEntrada);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return datoProfesional;
			}
		}		
		
		datosEconomicosWrapper.wrapperConsultaDetalleDatoProf(respuesta
				.getResponseBansefi().getOTRPECONSCOMPLPROFTR()
				.getTRPECONSCOMPLPROFEVT(), datoProfesional);
		if (null != datoProfesional.getFechaAntPuesto()) {
			if ((!datoProfesional.getFechaAntPuesto().before(fechaInvalida1) && !fechaInvalida1
					.before(datoProfesional.getFechaAntPuesto()))
					|| (!datoProfesional.getFechaAntPuesto().before(
							fechaInvalida2) && !fechaInvalida2
							.before(datoProfesional.getFechaAntPuesto()))
							|| (!datoProfesional.getFechaAntPuesto().before(
									fechaInvalida3) && !fechaInvalida3
									.before(datoProfesional.getFechaAntPuesto()))) {
				datoProfesional.setFechaAntPuesto(null);
			}
		}
		try {
			Integer fechaAnt = respuesta.getResponseBansefi()
					.getOTRPECONSCOMPLPROFTR().getTRPECONSCOMPLPROFEVT()
					.getSTDFECANTGLABORALV().getSTDFECHA();
			if(fechaAnt.intValue() != 0 && fechaAnt != null){
				if((fechaAnt.intValue() != fechaInvalidaInt1 && fechaAnt.intValue() != fechaInvalidaInt2) && fechaAnt.intValue() != -1){
					datoProfesional.setFechaAntLaboral(formatter.parse(String
							.format("%08d", fechaAnt)));
				}else{
					datoProfesional.setFechaAntLaboral(null);
				}
			}
		} catch (ParseException e) {
			datoProfesional.setFechaAntLaboral(null);
		}
		if("".equals(datoProfesional.getDuracion()) || datoProfesional.getDuracion() == null){
			datoProfesional.setDuracion("1");
		}
		if("".equals(datoProfesional.getContinuidad()) || datoProfesional.getContinuidad() == null){
			datoProfesional.setContinuidad("1");
		}
		if("".equals(datoProfesional.getJornada()) || datoProfesional.getJornada() == null){
			datoProfesional.setJornada("1");
		}
		if("".equals(datoProfesional.getDedicacion()) || datoProfesional.getDedicacion() == null){
			datoProfesional.setDedicacion("1");
		}
		
		return datoProfesional;
	}

}
