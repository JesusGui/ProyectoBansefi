package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.MercadoOrganizadoBean;
import mx.babel.bansefi.banksystem.personas.webservices.altamercadosorganizados.AltaMercadosOrganizadosServicio;
import mx.babel.bansefi.banksystem.personas.webservices.altamercadosorganizados.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.altamercadosorganizados.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.wrappers.ActividadEmpresarialWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para ejecutar el servicio de alta de Mercados Organizados.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class AltaMercadosOrganizadosBackEnd extends BackEndBean {

	private static final long serialVersionUID = -8588672852835304902L;
	
	@Autowired
	private ActividadEmpresarialWrapper wrapperActividad;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar la TRN de alta de mercados organizados.
	 * 
	 * @param mercadoOrganizado
	 * @return int Código de retorno del servicio
	 */
	public int ejecutarTRN(MercadoOrganizadoBean mercadoOrganizado){
		Ejecutar.ITRPEALTAMERCADOSORG contextoEntrada = new Ejecutar.ITRPEALTAMERCADOSORG();
		Ejecutar.ITRPEALTAMERCADOSORG.TRPEALTAMERCADOSORGE contextoEntradaCampos = null;

		mercadoOrganizado.setEntidad(super.getEntidad());
		contextoEntradaCampos = wrapperActividad
				.wrapperAltaMercadosOrganizados(mercadoOrganizado);

		Ejecutar.ITRPEALTAMERCADOSORG.STDTRNIPARMV contextoComun = new Ejecutar.ITRPEALTAMERCADOSORG.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX(CodTxConstants.COD_TX_TR_PE_ALTA_MERCADOS_ORG_TRN);
		contextoComun.setCODTXDI("");

		contextoEntrada.setSTDTRNIPARMV(contextoComun);
		contextoEntrada.setTRPEALTAMERCADOSORGE(contextoEntradaCampos);
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				AltaMercadosOrganizadosServicio.class, contextoEntrada);

		super.verificaRespuesta(respuesta);
		
		return respuesta.getResponseBansefi().getOTRPEALTAMERCADOSORG().getRTRNCD();

	}

}
