package mx.babel.bansefi.banksystem.personas.backend;

import java.util.Date;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.personas.beans.ActividadEmpresarialBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultadatosempresarialespersona.ConsultaDatosEmpresarialesPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultadatosempresarialespersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultadatosempresarialespersona.Ejecutar.ITRPECONSCOMPLEMPRTR;
import mx.babel.bansefi.banksystem.personas.webservices.consultadatosempresarialespersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.wrappers.ActividadEmpresarialWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BackEnd para Consulta de Datos Empresariales.
 * @author alejandro.pineda
 *
 */
@Component
public class ConsultaDatosEmpresarialesBackEnd extends BackEndBean {

	private static final long serialVersionUID = -6107441691657877934L;
	
	@Autowired
	private ActividadEmpresarialWrapper actividadEmpresarialWrapper;
		
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public ActividadEmpresarialBean ejecutarTRN(int idInterno){
		Ejecutar.ITRPECONSCOMPLEMPRTR contexto = new Ejecutar.ITRPECONSCOMPLEMPRTR();
		Ejecutar.ITRPECONSCOMPLEMPRTR.TRPECONSCOMPLEMPREVT contextoEntrada = new Ejecutar.ITRPECONSCOMPLEMPRTR.TRPECONSCOMPLEMPREVT();
		Ejecutar.ITRPECONSCOMPLEMPRTR.TRPECONSCOMPLEMPREVT.PEEMPRESAP contextoEntradaCampos = new Ejecutar.ITRPECONSCOMPLEMPRTR.TRPECONSCOMPLEMPREVT.PEEMPRESAP();
		contextoEntradaCampos.setIDINTERNOPE(idInterno);
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setNUMORDEMPPE("1");
		contextoEntrada.setPEEMPRESAP(contextoEntradaCampos);

		Ejecutar.ITRPECONSCOMPLEMPRTR.STDTRNIPARMV contextoComun = new Ejecutar.ITRPECONSCOMPLEMPRTR.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT19CON");
		contextoComun.setCODTXDI("");

		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRPECONSCOMPLEMPREVT(contextoEntrada);

		return this.obtenerRespuestaServicio(contexto, idInterno);

	}

	public ActividadEmpresarialBean obtenerRespuestaServicio(ITRPECONSCOMPLEMPRTR contexto, int idInterno){
		int fechaInvalidaInt1 = 99991231;
		int fechaInvalidaInt2 = 19500101;
		int fechaInvalidaInt3 = 11111111;
		
		IntegerToDateConverter converter = new IntegerToDateConverter();
		
		Date fechaInvalida1 = converter.convertTo(fechaInvalidaInt1);
		Date fechaInvalida2 = converter.convertTo(fechaInvalidaInt2);
		Date fechaInvalida3 = converter.convertTo(fechaInvalidaInt3);
		
		ActividadEmpresarialBean actividadEmpresarial = null;
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaDatosEmpresarialesPersonaServicio.class, contexto);

		super.verificaRespuesta(respuesta);
		
		actividadEmpresarial = actividadEmpresarialWrapper
				.wrapperConsultaDatosEmpresariales(respuesta.getResponseBansefi().getOTRPECONSCOMPLEMPRTR().getTRPECONSCOMPLEMPREVT());
		if(actividadEmpresarial.getFechaCalificacion() != null){
			if((!actividadEmpresarial.getFechaCalificacion().before(fechaInvalida1) && !fechaInvalida1.before(actividadEmpresarial.getFechaCalificacion())) || 
					(!actividadEmpresarial.getFechaCalificacion().before(fechaInvalida2) && !fechaInvalida2.before(actividadEmpresarial.getFechaCalificacion())) ||
						(!actividadEmpresarial.getFechaCalificacion().before(fechaInvalida3) && !fechaInvalida3.before(actividadEmpresarial.getFechaCalificacion()))){
				actividadEmpresarial.setFechaCalificacion(null);
			}
		}
			
		if(actividadEmpresarial.getFechaPresentacion() != null){
			if((!actividadEmpresarial.getFechaPresentacion().before(fechaInvalida1) && !fechaInvalida1.before(actividadEmpresarial.getFechaPresentacion())) ||
					(!actividadEmpresarial.getFechaPresentacion().before(fechaInvalida2) && !fechaInvalida2.before(actividadEmpresarial.getFechaPresentacion())) ||
						(!actividadEmpresarial.getFechaPresentacion().before(fechaInvalida3) && !fechaInvalida3.before(actividadEmpresarial.getFechaPresentacion()))){
				actividadEmpresarial.setFechaPresentacion(null);
			}
		}
			
		if(actividadEmpresarial.getFechaTransformacion() != null){
			if((!actividadEmpresarial.getFechaTransformacion().before(fechaInvalida1) && !fechaInvalida1.before(actividadEmpresarial.getFechaTransformacion())) ||
					(!actividadEmpresarial.getFechaTransformacion().before(fechaInvalida2) && !fechaInvalida2.before(actividadEmpresarial.getFechaTransformacion())) ||
						(!actividadEmpresarial.getFechaTransformacion().before(fechaInvalida3) && !fechaInvalida3.before(actividadEmpresarial.getFechaTransformacion()))){
				actividadEmpresarial.setFechaTransformacion(null);
			}
		}
			
		if(actividadEmpresarial.getFinSociedad() != null){
			if((!actividadEmpresarial.getFinSociedad().before(fechaInvalida1) && !fechaInvalida1.before(actividadEmpresarial.getFinSociedad())) ||
					(!actividadEmpresarial.getFinSociedad().before(fechaInvalida2) && !fechaInvalida2.before(actividadEmpresarial.getFinSociedad())) ||
						(!actividadEmpresarial.getFinSociedad().before(fechaInvalida3) && !fechaInvalida3.before(actividadEmpresarial.getFinSociedad()))){
				actividadEmpresarial.setFinSociedad(null);
			}
		}
			
		if(actividadEmpresarial.getFinVacacional() != null){
			if((!actividadEmpresarial.getFinVacacional().before(fechaInvalida1) && !fechaInvalida1.before(actividadEmpresarial.getFinVacacional())) ||
					(!actividadEmpresarial.getFinVacacional().before(fechaInvalida2) && !fechaInvalida2.before(actividadEmpresarial.getFinVacacional())) ||
						(!actividadEmpresarial.getFinVacacional().before(fechaInvalida3) && !fechaInvalida3.before(actividadEmpresarial.getFinVacacional()))){
				actividadEmpresarial.setFinVacacional(null);
			}
		}
			
		if(actividadEmpresarial.getInicioSociedad() != null){
			if((!actividadEmpresarial.getInicioSociedad().before(fechaInvalida1) && !fechaInvalida1.before(actividadEmpresarial.getInicioSociedad())) ||
					(!actividadEmpresarial.getInicioSociedad().before(fechaInvalida2) && !fechaInvalida2.before(actividadEmpresarial.getInicioSociedad())) ||
						(!actividadEmpresarial.getInicioSociedad().before(fechaInvalida3) && !fechaInvalida3.before(actividadEmpresarial.getInicioSociedad()))){
				actividadEmpresarial.setInicioSociedad(null);
			}
		}
			
		if(actividadEmpresarial.getInicioVacacional() != null){
			if((!actividadEmpresarial.getInicioVacacional().before(fechaInvalida1) && !fechaInvalida1.before(actividadEmpresarial.getInicioVacacional())) ||
					(!actividadEmpresarial.getInicioVacacional().before(fechaInvalida2) && !fechaInvalida2.before(actividadEmpresarial.getInicioVacacional())) ||
						(!actividadEmpresarial.getInicioVacacional().before(fechaInvalida3) && !fechaInvalida3.before(actividadEmpresarial.getInicioVacacional()))){
				actividadEmpresarial.setInicioVacacional(null);
			}
		}
		
		actividadEmpresarial.setIdInterno(idInterno);

		return actividadEmpresarial;
	}

}
