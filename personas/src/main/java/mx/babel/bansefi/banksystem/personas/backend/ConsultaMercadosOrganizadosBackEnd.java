package mx.babel.bansefi.banksystem.personas.backend;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.personas.beans.MercadoOrganizadoBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultamercadosorganizados.ConsultaMercadosOrganizadosServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultamercadosorganizados.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultamercadosorganizados.Ejecutar.ITRPECONSMERCADOSORG;
import mx.babel.bansefi.banksystem.personas.webservices.consultamercadosorganizados.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.consultamercadosorganizados.ResponseBansefi.OTRPECONSMERCADOSORG.TRPECONSMERCADOSORGE.TRPECONSMERCADOSORGE1;
import mx.babel.bansefi.banksystem.personas.wrappers.ActividadEmpresarialWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BackEnd para Consulta de Mercados organizados.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class ConsultaMercadosOrganizadosBackEnd extends BackEndBean {

	private static final long serialVersionUID = 2948267737836360291L;
	
	@Autowired
	private ActividadEmpresarialWrapper actividadEmpresarialWrapper;
	
	@Autowired	
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar la TRN de consulta de mercados organizados.
	 * 
	 * @param idInterno
	 *            Id Interno de la persona a consultar.
	 * @return Lista con los resultados del servicio.
	 */
	public List<MercadoOrganizadoBean> ejecutarTRN(int idInterno){
		
		Ejecutar.ITRPECONSMERCADOSORG contexto = new Ejecutar.ITRPECONSMERCADOSORG();
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);
		Ejecutar.ITRPECONSMERCADOSORG.TRPECONSMERCADOSORGE contextoEntrada = new Ejecutar.ITRPECONSMERCADOSORG.TRPECONSMERCADOSORGE();

		Ejecutar.ITRPECONSMERCADOSORG.TRPECONSMERCADOSORGE.PEMERCADOSORGP contextoEntradaCampos = new Ejecutar.ITRPECONSMERCADOSORG.TRPECONSMERCADOSORGE.PEMERCADOSORGP();
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setIDINTERNOPE(idInterno);
		contextoEntradaCampos.setCODMERCADOSORG("");
		contextoEntradaCampos.setFECHAINICCRT(0);
		contextoEntradaCampos.setHORAOPRCN(0);
		contextoEntrada.setPEMERCADOSORGP(contextoEntradaCampos);

		Ejecutar.ITRPECONSMERCADOSORG.STDTRNIPARMV contextoComun = new Ejecutar.ITRPECONSMERCADOSORG.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT35CON");
		contextoComun.setCODTXDI("");

		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRPECONSMERCADOSORGE(contextoEntrada);

		return this.obtenerRespuestaServicio(contexto);

	}

	/**
	 * Método para obtener los resultados del servicio de consulta de mercados
	 * organizados.
	 * 
	 * @param contextoEntrada
	 * @return Lista con los resultados del servicio.
	 */
	public List<MercadoOrganizadoBean> obtenerRespuestaServicio(ITRPECONSMERCADOSORG contextoEntrada){
		List<MercadoOrganizadoBean> listaMercados = new ArrayList<>(0);
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ConsultaMercadosOrganizadosServicio.class, contextoEntrada);

		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return listaMercados;
			}
		}
		
		for (TRPECONSMERCADOSORGE1 resultado : respuesta.getResponseBansefi()
				.getOTRPECONSMERCADOSORG()
				.getTRPECONSMERCADOSORGE()
				.getTRPECONSMERCADOSORGE()) {
			MercadoOrganizadoBean mercado = actividadEmpresarialWrapper
					.wrapperConsultaMercadosOrganizados(resultado);
			if(mercado.getIdInterno() != 0 && !("").equals(mercado.getCodigoMercado().trim())){
				listaMercados.add(mercado);
			}
			
		}
		return listaMercados;
	}

}
