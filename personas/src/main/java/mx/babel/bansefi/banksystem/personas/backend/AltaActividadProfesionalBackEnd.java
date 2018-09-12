package mx.babel.bansefi.banksystem.personas.backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.personas.beans.ActividadProfesionalBean;
import mx.babel.bansefi.banksystem.personas.webservices.altadatosprofesionales.AltaDatosProfesionalesServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altadatosprofesionales.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altadatosprofesionales.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.wrappers.DatosEconomicosWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Alta de Actividad
 * Profesional.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class AltaActividadProfesionalBackEnd extends BackEndBean {

	private static final long serialVersionUID = 3055233569220504151L;

	@Autowired
	private DatosEconomicosWrapper datosEconomicosWrapper;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar la TRN de alta de Actividad Profesional.
	 * 
	 * @param actividadProfesionalBean Bean con los datos a almacenar.
	 * @return int Código de retorno del servicio.
	 */
	public int ejecutarTRN(ActividadProfesionalBean actividadProfesionalBean){
		
		Ejecutar.ITRPEALTACOMPLPROFTR contextoEntrada = new Ejecutar.ITRPEALTACOMPLPROFTR();
		Ejecutar.ITRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT contextoEntradaCampos = null;

		actividadProfesionalBean.setEntidad(super.getEntidad());
		contextoEntradaCampos = datosEconomicosWrapper.wrapperAltaDatosProfesional(actividadProfesionalBean);
		
		Ejecutar.ITRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT.STDFECANTGLABORALV stdfecantglaboralv = new Ejecutar.ITRPEALTACOMPLPROFTR.TRPEALTACOMPLPROFEVT.STDFECANTGLABORALV();
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		if(actividadProfesionalBean.getFechaAntLaboral() != null){
			stdfecantglaboralv.setSTDFECHA(Integer.parseInt(formatter.format(actividadProfesionalBean.getFechaAntLaboral())));
		} else {
			stdfecantglaboralv.setSTDFECHA(0);
		}
		contextoEntradaCampos.setSTDFECANTGLABORALV(stdfecantglaboralv);
		
		Ejecutar.ITRPEALTACOMPLPROFTR.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEALTACOMPLPROFTR.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT22MON");
		contextoComun.setCODTXDI("");

		contextoEntrada.setSTDTRNIPARMV(contextoComun);
		contextoEntrada.setTRPEALTACOMPLPROFEVT(contextoEntradaCampos);

		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				AltaDatosProfesionalesServicio.class, contextoEntrada);

		super.verificaRespuesta(respuesta);
		actividadProfesionalBean.setEstado(EstadoListadosEnum.CONSULTADO);
		actividadProfesionalBean.setNumActProfIn(respuesta.getResponseBansefi().getOTRPEALTACOMPLPROFTR()
				.getTRPEALTACOMPLPROFEVT().getPEACTPROFINDVP().getNUMACTPROFIN());
		return respuesta.getResponseBansefi().getOTRPEALTACOMPLPROFTR().getRTRNCD();

	}
	
}
