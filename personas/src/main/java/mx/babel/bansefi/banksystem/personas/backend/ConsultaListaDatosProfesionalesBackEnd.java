package mx.babel.bansefi.banksystem.personas.backend;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.personas.beans.ActividadProfesionalBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultalistadatosprofesionales.ConsultaListaDatosProfesionalesServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultalistadatosprofesionales.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultalistadatosprofesionales.Ejecutar.ITRPEPROFLSCNSTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.consultalistadatosprofesionales.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.consultalistadatosprofesionales.ResponseBansefi.OTRPEPROFLSCNSTRNO.TRPEPROFLSCNSEVTZ.TRPEPROFLSCNSEVTV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para ejecutar el servicio de consulta listado de Datos
 * Profesionales.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class ConsultaListaDatosProfesionalesBackEnd extends BackEndBean {

	private static final long serialVersionUID = 2347647929535751574L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar la TRN de consulta listado de Datos Profesionales.
	 * 
	 * @param idInterno IdInterno a consultar.
	 * @return Lista de Datos Profesionales.
	 */
	public List<ActividadProfesionalBean> ejecutarTRN(int idInterno){
		Ejecutar.ITRPEPROFLSCNSTRNI contexto = new Ejecutar.ITRPEPROFLSCNSTRNI();
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);

		Ejecutar.ITRPEPROFLSCNSTRNI.TRPEPROFLSCNSEVTY contextoEntrada = new Ejecutar.ITRPEPROFLSCNSTRNI.TRPEPROFLSCNSEVTY();

		Ejecutar.ITRPEPROFLSCNSTRNI.TRPEPROFLSCNSEVTY.PEACTPROFINDVP contextoEntradaCampos = new Ejecutar.ITRPEPROFLSCNSTRNI.TRPEPROFLSCNSEVTY.PEACTPROFINDVP();
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setIDINTERNOPE(idInterno);
		contextoEntradaCampos.setNUMACTPROFIN(0);
		contextoEntrada.setPEACTPROFINDVP(contextoEntradaCampos);

		Ejecutar.ITRPEPROFLSCNSTRNI.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEPROFLSCNSTRNI.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT21CON");
		contextoComun.setCODTXDI("");

		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRPEPROFLSCNSEVTY(contextoEntrada);

		return this.obtenerRespuestaServicio(contexto);

	}

	/**
	 * Método para obtener resultados del servicio de consulta listado de datos
	 * profesionales.
	 * 
	 * @param contextoEntrada Entrada con los datos a consultar
	 * @return Lista de resultados del servicio
	 */
	private List<ActividadProfesionalBean> obtenerRespuestaServicio(ITRPEPROFLSCNSTRNI contextoEntrada){
		List<ActividadProfesionalBean> listaDirecciones = new ArrayList<>();
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ConsultaListaDatosProfesionalesServicio.class, contextoEntrada);

		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return listaDirecciones;
			}
		}
		
		for (TRPEPROFLSCNSEVTV resultado : respuesta.getResponseBansefi()
				.getOTRPEPROFLSCNSTRNO().getTRPEPROFLSCNSEVTZ()
				.getTRPEPROFLSCNSEVTV()) {
			if(resultado.getNUMACTPROFIN() != 0 && resultado != null){
				ActividadProfesionalBean actividadProf = new ActividadProfesionalBean();
				actividadProf.setNumActProfIn(resultado.getNUMACTPROFIN());
				actividadProf.setIdEmpresa(resultado.getCIFEMPRIN().trim().toUpperCase());
				actividadProf.setNombreEmpresa(resultado.getNOMB50().trim().toUpperCase());
				actividadProf.setCodActividadProf(resultado.getCODCNOINDV().trim());
				listaDirecciones.add(actividadProf);
			}
		}
		return listaDirecciones;
	}

}
