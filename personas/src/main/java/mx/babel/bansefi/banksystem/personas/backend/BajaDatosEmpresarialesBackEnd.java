package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.personas.webservices.bajadatosempresarialespersona.BajaDatosEmpresarialesPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajadatosempresarialespersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.bajadatosempresarialespersona.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para la baja de datos empresariales
 *
 */
@Component
public class BajaDatosEmpresarialesBackEnd extends BackEndBean {
	
	private static final long serialVersionUID = -932865668307392310L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	/**
	 * Método para ejecutar la TRN de baja de datos empresariales.
	 * @param idInterno
	 * @return int Código de retorno del servicio
	 */
	public int ejecutarTRN(int idInterno) {
		Ejecutar.ITRPEBAJACOMPLEMPRTR contexto = new Ejecutar.ITRPEBAJACOMPLEMPRTR();
		Ejecutar.ITRPEBAJACOMPLEMPRTR.TRPEBAJACOMPLEMPREVT contextoEntrada = new Ejecutar.ITRPEBAJACOMPLEMPRTR.TRPEBAJACOMPLEMPREVT();
		
		Ejecutar.ITRPEBAJACOMPLEMPRTR.TRPEBAJACOMPLEMPREVT.PEEMPRESAP contextoEntradaCampos = new Ejecutar.ITRPEBAJACOMPLEMPRTR.TRPEBAJACOMPLEMPREVT.PEEMPRESAP();
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setIDINTERNOPE(idInterno);
		contextoEntradaCampos.setNUMORDEMPPE("2");
		contextoEntrada.setPEEMPRESAP(contextoEntradaCampos);

		Ejecutar.ITRPEBAJACOMPLEMPRTR.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEBAJACOMPLEMPRTR.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT17MON");
		contextoComun.setCODTXDI("");
		
		// TODO: CODTX en constantes
		
		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRPEBAJACOMPLEMPREVT(contextoEntrada);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(BajaDatosEmpresarialesPersonaServicio.class, contexto);

		super.verificaRespuesta(respuesta);
		
		return respuesta.getResponseBansefi().getOTRPEBAJACOMPLEMPRTR().getRTRNCD();

	}

}
