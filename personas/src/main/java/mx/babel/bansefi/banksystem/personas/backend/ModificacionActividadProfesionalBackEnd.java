package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.personas.beans.ActividadProfesionalBean;
import mx.babel.bansefi.banksystem.personas.webservices.modificaciondatoprofesional.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.modificaciondatoprofesional.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificaciondatoprofesional.ModificacionDatoProfesionalServicio;
import mx.babel.bansefi.banksystem.personas.wrappers.DatosEconomicosWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de la Modificación de Datos
 * Profesionales.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class ModificacionActividadProfesionalBackEnd extends BackEndBean {

	private static final long serialVersionUID = 2948267737836360291L;

	@Autowired
	private DatosEconomicosWrapper datosEconomicosWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public ModificacionActividadProfesionalBackEnd() {

	}

	/**
	 * Método para ejecutar la TRN de modificación de Actividad Profesional.
	 * 
	 * @param actividadProfesionalBean
	 *            Bean con los datos a almacenar.
	 * @return int Código de retorno del servicio.
	 */
	public int ejecutarTRN(ActividadProfesionalBean actividadProfesionalBean){
		Ejecutar.ITRPEMODIFCOMPLPROFT contextoEntrada = new Ejecutar.ITRPEMODIFCOMPLPROFT();
		Ejecutar.ITRPEMODIFCOMPLPROFT.TRPEMODIFCOMPLPROFEV contextoEntradaCampos = null;

		actividadProfesionalBean.setEntidad(super.getEntidad());
		contextoEntradaCampos = datosEconomicosWrapper
				.wrapperModificacionDatosProfesional(actividadProfesionalBean);

		Ejecutar.ITRPEMODIFCOMPLPROFT.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEMODIFCOMPLPROFT.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT25MON");
		contextoComun.setCODTXDI("");

		contextoEntrada.setSTDTRNIPARMV(contextoComun);
		contextoEntrada.setTRPEMODIFCOMPLPROFEV(contextoEntradaCampos);
		
		// Se ejecuta el WebService correspondiente
		
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ModificacionDatoProfesionalServicio.class, contextoEntrada);

		super.verificaRespuesta(respuesta);
		actividadProfesionalBean.setEstado(EstadoListadosEnum.CONSULTADO);
		return respuesta.getResponseBansefi().getOTRPEMODIFCOMPLPROFT().getRTRNCD();

	}

}
