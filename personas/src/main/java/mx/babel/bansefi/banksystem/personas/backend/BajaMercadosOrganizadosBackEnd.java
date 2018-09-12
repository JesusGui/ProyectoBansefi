package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.MercadoOrganizadoBean;
import mx.babel.bansefi.banksystem.personas.webservices.bajamercadosorganizados.BajaMercadosOrganizadosServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajamercadosorganizados.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.bajamercadosorganizados.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.wrappers.ActividadEmpresarialWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Baja de
 * Mercados Organizados.
 * 
 */
@Component
public class BajaMercadosOrganizadosBackEnd extends BackEndBean {
	
	private static final long serialVersionUID = -8032142194931358016L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	private ActividadEmpresarialWrapper wrapperActividad;
	
	/**
	 * Método para ejecutar la TRN de baja de mercados organizados.
	 * 
	 * @param mercadoOrganizado
	 * @return int Código de retorno del servicio
	 */
	public int ejecutarTRN(MercadoOrganizadoBean mercadoOrganizado){
		
		Ejecutar.ITRPEBAJAMERCADOSORG contexto = new Ejecutar.ITRPEBAJAMERCADOSORG();
		Ejecutar.ITRPEBAJAMERCADOSORG.TRPEBAJAMERCADOSORGE contextoEntradaCampos = null;
		mercadoOrganizado.setEntidad(super.getEntidad());
		contextoEntradaCampos = wrapperActividad.wrapperBajaMercadosOrganizados(mercadoOrganizado);

		Ejecutar.ITRPEBAJAMERCADOSORG.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEBAJAMERCADOSORG.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX(CodTxConstants.COD_TX_TR_PE_BAJA_MERCADOS_ORG_TRN);
		contextoComun.setCODTXDI("");
				
		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRPEBAJAMERCADOSORGE(contextoEntradaCampos);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(BajaMercadosOrganizadosServicio.class, contexto);
		
		super.verificaRespuesta(respuesta);
		
		return respuesta.getResponseBansefi().getOTRPEBAJAMERCADOSORG().getRTRNCD();

	}	
}
