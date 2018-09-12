package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.personas.beans.ActividadProfesionalBean;
import mx.babel.bansefi.banksystem.personas.webservices.bajadatoprofesional.BajaDatoProfesionalServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajadatoprofesional.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.bajadatoprofesional.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.wrappers.DatosEconomicosWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para realizar la ejecución del servicio de Baja de un Dato
 * Profesional.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class BajaActividadProfesionalBackEnd extends BackEndBean {

	private static final long serialVersionUID = 2948267737836360291L;

	@Autowired
	private DatosEconomicosWrapper datosEconomicosWrapper;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar la TRN de baja de Datos Profesionales.
	 * 
	 * @param actividadProfesionalBean Bean con los datos a eliminar.
	 * @return int Código de retorno del servicio
	 */
	public int ejecutarTRN(ActividadProfesionalBean actividadProfesionalBean) throws ControlableException, NoControlableException{
		Ejecutar.ITRPEBAJACOMPLPROFTR contexto = new Ejecutar.ITRPEBAJACOMPLPROFTR();
		Ejecutar.ITRPEBAJACOMPLPROFTR.TRPEBAJACOMPLPROFEVT contextoEntrada = new Ejecutar.ITRPEBAJACOMPLPROFTR.TRPEBAJACOMPLPROFEVT();

		Ejecutar.ITRPEBAJACOMPLPROFTR.TRPEBAJACOMPLPROFEVT.PEACTPROFINDVP contextoEntradaCampos = null;

		actividadProfesionalBean.setEntidad(super.getEntidad());
		contextoEntradaCampos = datosEconomicosWrapper
				.wrapperBajaDatosProfesional(actividadProfesionalBean);
		contextoEntrada.setPEACTPROFINDVP(contextoEntradaCampos);

		Ejecutar.ITRPEBAJACOMPLPROFTR.TRPEBAJACOMPLPROFEVT.PEACTPROFINDVV contextoEntradaCamposNulos = new Ejecutar.ITRPEBAJACOMPLPROFTR.TRPEBAJACOMPLPROFEVT.PEACTPROFINDVV();
		contextoEntradaCamposNulos.setIDINTERNOPE2(0);
		contextoEntrada.setPEACTPROFINDVV(contextoEntradaCamposNulos);

		Ejecutar.ITRPEBAJACOMPLPROFTR.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEBAJACOMPLPROFTR.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT23MON");
		contextoComun.setCODTXDI("");

		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRPEBAJACOMPLPROFEVT(contextoEntrada);

		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				BajaDatoProfesionalServicio.class, contexto);

		super.verificaRespuesta(respuesta);

		return respuesta.getResponseBansefi().getOTRPEBAJACOMPLPROFTR().getRTRNCD();

	}

}
