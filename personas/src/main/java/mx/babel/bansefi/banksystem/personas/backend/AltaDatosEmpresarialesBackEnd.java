package mx.babel.bansefi.banksystem.personas.backend;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.ActividadEmpresarialBean;
import mx.babel.bansefi.banksystem.personas.webservices.altadatosempresarialespersona.AltaDatosEmpresarialesPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altadatosempresarialespersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altadatosempresarialespersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.wrappers.ActividadEmpresarialWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para ejecutar el servicio de alta de Datos Empresariales.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class AltaDatosEmpresarialesBackEnd extends BackEndBean {

	private static final long serialVersionUID = 2918185801229698295L;

	@Autowired
	private ActividadEmpresarialWrapper wrapperActividad;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar la TRN de alta de datos empresariales.
	 * 
	 * @param actividadEmpresarial
	 *            Bean de la ventana de Actividad Empresarial.
	 * @return int Código de retorno del servicio
	 */
	public int ejecutarTRN(ActividadEmpresarialBean actividadEmpresarial){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Ejecutar.ITRPEALTACOMPLEMPRTR contextoEntrada = new Ejecutar.ITRPEALTACOMPLEMPRTR();
		Ejecutar.ITRPEALTACOMPLEMPRTR.TRPEALTACOMPLEMPREVT contextoEntradaCampos = null;
		if(actividadEmpresarial.getMesCierreEjercicio() != null && actividadEmpresarial.getDiaCierreEjercicio() != null){
			actividadEmpresarial.setFechaCierreEjercicio(actividadEmpresarial.getDiaCierreEjercicio() + "/"
					+ actividadEmpresarial.getMesCierreEjercicio() + "/"
					+ dateFormat.format(Calendar.getInstance()
							.getTime()));
		}
		contextoEntradaCampos = wrapperActividad
				.wrapperAltaDatosEmpresariales(actividadEmpresarial);
		contextoEntradaCampos.setNUMORDEMPPE("1");
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());

		Ejecutar.ITRPEALTACOMPLEMPRTR.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEALTACOMPLEMPRTR.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_COMPL_EMPR_TRN);
		contextoComun.setCODTXDI("");

		contextoEntrada.setSTDTRNIPARMV(contextoComun);
		contextoEntrada.setTRPEALTACOMPLEMPREVT(contextoEntradaCampos);

		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaDatosEmpresarialesPersonaServicio.class, contextoEntrada);

		super.verificaRespuesta(respuesta);
		
		return respuesta.getResponseBansefi().getOTRPEALTACOMPLEMPRTR().getRTRNCD();

	}

}
