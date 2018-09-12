package mx.babel.bansefi.banksystem.personas.backend;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.personas.beans.DireccionRegistralBean;
import mx.babel.bansefi.banksystem.personas.webservices.listadireccionesregistralespersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.listadireccionesregistralespersona.Ejecutar.ITRDRREGLSCNSTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.listadireccionesregistralespersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.listadireccionesregistralespersona.ListaDireccionesRegistralesPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.listadireccionesregistralespersona.ResponseBansefi.OTRDRREGLSCNSTRNO.TRDRREGLSCNSEVTZ.TRDRREGLSCNSEVTV;
import mx.babel.bansefi.banksystem.personas.wrappers.ActividadEmpresarialWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para ejecutar el servicio de Consulta de Direcciones
 * Registrales.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class ConsultaDireccionesRegistralesBackEnd extends BackEndBean {
	
	private static final long serialVersionUID = 418085943577432862L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	private ActividadEmpresarialWrapper actividadEmpresarialWrapper;
		
	/**
	 * Método para ejecutar la TRN de consulta de Direcciones registrales.
	 * 
	 * @param idInterno IdInterno a consultar.
	 * @return Lista de Direcciones Registrales.
	 */
	public List<DireccionRegistralBean> ejecutarTRN(int idInterno){
		Ejecutar.ITRDRREGLSCNSTRNI contexto = new Ejecutar.ITRDRREGLSCNSTRNI();
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);

		Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY contextoEntrada = new Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY();

		Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.PEPERSRLDIRP contextoEntradaCampos = new Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.PEPERSRLDIRP();
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setIDINTERNOPE(idInterno);
		contextoEntradaCampos.setCODPERSRLDIR("");
		contextoEntradaCampos.setCODDIR("");
		contextoEntradaCampos.setNUMDIR(0);
		contextoEntradaCampos.setFECHAINICRL(0);
		contextoEntradaCampos.setHORAOPRCN(0);
		contextoEntrada.setPEPERSRLDIRP(contextoEntradaCampos);

		Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.BIBIENRLDIRP contextoEntradaCamposVacios = new Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.BIBIENRLDIRP();
		contextoEntradaCamposVacios.setCODNRBEEN(super.getEntidad());
		contextoEntradaCamposVacios.setIDINTERNOBI(0);
		contextoEntradaCamposVacios.setCODNRBEEN("");
		contextoEntradaCamposVacios.setCODDIR("");
		contextoEntradaCamposVacios.setNUMDIR(0);
		contextoEntradaCamposVacios.setFECHAINICRL(0);
		contextoEntradaCamposVacios.setHORAOPRCN(0);
		contextoEntrada.setBIBIENRLDIRP(contextoEntradaCamposVacios);

		Ejecutar.ITRDRREGLSCNSTRNI.STDTRNIPARMV contextoComun = new Ejecutar.ITRDRREGLSCNSTRNI.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT05CON");
		contextoComun.setCODTXDI("");
		
		// TODO : Constante en ConstantesFuncionales y Initialize si procede
		
		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRDRREGLSCNSEVTY(contextoEntrada);

		return this.obtenerRespuestaServicio(contexto);

	}

	/**
	 * Método para obtener resultados del servicio de consulta de direcciones
	 * registrales.
	 * 
	 * @param contexto Entrada con los datos a consultar
	 * @return Lista de resultados del servicio
	 */
	private List<DireccionRegistralBean> obtenerRespuestaServicio(ITRDRREGLSCNSTRNI contexto){
		List<DireccionRegistralBean> listaDirecciones = new ArrayList<>();
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ListaDireccionesRegistralesPersonaServicio.class, contexto);

		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return listaDirecciones;
			}
		}
		
		for (TRDRREGLSCNSEVTV resultado : respuesta.getResponseBansefi()
				.getOTRDRREGLSCNSTRNO()
				.getTRDRREGLSCNSEVTZ()
				.getTRDRREGLSCNSEVTV()) {
			
			DireccionRegistralBean direccion = actividadEmpresarialWrapper
					.wrapperConsultaDireccionRegistral(resultado);
			if(!("").equals(direccion.getCodDatoResgistral().trim())){
				listaDirecciones.add(direccion);
			}
		}
		return listaDirecciones;
	}
}
