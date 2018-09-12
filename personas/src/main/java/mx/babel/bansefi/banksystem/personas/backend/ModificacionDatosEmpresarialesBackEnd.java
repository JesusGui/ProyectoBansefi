package mx.babel.bansefi.banksystem.personas.backend;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.ActividadEmpresarialBean;
import mx.babel.bansefi.banksystem.personas.webservices.modificaciondatosempresarialespersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.modificaciondatosempresarialespersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.modificaciondatosempresarialespersona.ModificacionDatosEmpresarialesPersonaServicio;
import mx.babel.bansefi.banksystem.personas.wrappers.ActividadEmpresarialWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Modificacion
 * de Datos Empresariales.
 * 
 */
@Component
public class ModificacionDatosEmpresarialesBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 196851877407505013L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	private ActividadEmpresarialWrapper actividadEmpresarialWrapper;
	
	/**
	 * Método para ejecutar la TRN de modificación de datos empresariales.
	 * @param actividadEmpresarial
	 * @return int Código de retorno del servicio,
	 */
	public int ejecutarTRN(ActividadEmpresarialBean actividadEmpresarial){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		
		Ejecutar.ITRPEMODIFCOMPLEMPRT contextoEntrada = new Ejecutar.ITRPEMODIFCOMPLEMPRT();
		Ejecutar.ITRPEMODIFCOMPLEMPRT.TRPEMODIFCOMPLEMPREV contextoEntradaCampos = null;
		if(!"".equals(actividadEmpresarial.getDiaCierreEjercicio()) && actividadEmpresarial.getDiaCierreEjercicio() != null 
				&& !"".equals(actividadEmpresarial.getMesCierreEjercicio()) && actividadEmpresarial.getMesCierreEjercicio() != null){
			actividadEmpresarial.setFechaCierreEjercicio(actividadEmpresarial.getDiaCierreEjercicio() + "/"
					+ actividadEmpresarial.getMesCierreEjercicio() + "/"
					+ dateFormat.format(Calendar.getInstance()
							.getTime()));
		}else{
			actividadEmpresarial.setFechaCierreEjercicio("");
		}
		contextoEntradaCampos = actividadEmpresarialWrapper.wrapperModificacionDatosEmpresariales(actividadEmpresarial);
		
		Ejecutar.ITRPEMODIFCOMPLEMPRT.TRPEMODIFCOMPLEMPREV.PEEMPRESAP contextoEntradaCampos2 = new Ejecutar.ITRPEMODIFCOMPLEMPRT.TRPEMODIFCOMPLEMPREV.PEEMPRESAP();
		contextoEntradaCampos2.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos2.setIDINTERNOPE(actividadEmpresarial.getIdInterno());
		contextoEntradaCampos2.setNUMORDEMPPE("1");
		
		contextoEntradaCampos.setPEEMPRESAP(contextoEntradaCampos2);

		Ejecutar.ITRPEMODIFCOMPLEMPRT.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEMODIFCOMPLEMPRT.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(21478);
		contextoComun.setCODTX(CodTxConstants.COD_TX_TR_PE_MODIF_COMPL_EMPR_TRN);
		contextoComun.setCODTXDI("");
		
		
		contextoEntrada.setSTDTRNIPARMV(contextoComun);
		contextoEntrada.setTRPEMODIFCOMPLEMPREV(contextoEntradaCampos);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificacionDatosEmpresarialesPersonaServicio.class, contextoEntrada);
		
		super.verificaRespuesta(respuesta);
	
		return respuesta.getResponseBansefi().getOTRPEMODIFCOMPLEMPRT().getRTRNCD();

	}
	
}
