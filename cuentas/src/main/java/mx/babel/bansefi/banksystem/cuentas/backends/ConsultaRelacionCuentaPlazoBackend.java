package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaRelacionPlazoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentaplazo.ConsultaRelacionesCuentaPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentaplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentaplazo.Ejecutar.ITRRELIMPSCNACTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentaplazo.Ejecutar.ITRRELIMPSCNACTRNI.TRRELIMPSCNACEVTY.IPIMPSCNP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionescuentaplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaRelacionCuentaPlazoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaRelacionCuentaPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

    @Autowired
    ConsultaRelacionCuentaPlazoWrapper consultaRelacionCuentaPlazoWrapper;

	public List<CuentaRelacionPlazoBean> ejecutarTRN(final Long numAcuerdo, final Integer numSubAc){
	    List<CuentaRelacionPlazoBean> resultado = new ArrayList<CuentaRelacionPlazoBean>();
		final Ejecutar.ITRRELIMPSCNACTRNI request = initPeticion(numAcuerdo, numSubAc);

		final EjecutarResult respuesta = ejecutarWS(request);

		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return resultado;
            }
        }		
		
		return consultaRelacionCuentaPlazoWrapper.wrappRelacionCuentaPlazo(
	            respuesta.getResponseBansefi().getOTRRELIMPSCNACTRNO().getTRRELIMPSCNACEVTZ().getTRRELIMPSCNACEVTV());
	}


	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRRELIMPSCNACTRNI initPeticion(final Long numAcuerdo, final Integer numSubAc){
		final Ejecutar.ITRRELIMPSCNACTRNI peticion = new Ejecutar.ITRRELIMPSCNACTRNI();

		super.initialize(peticion);
		peticion.setSCROLLABLEOCCURS(50);

		final IPIMPSCNP ipimpscnp = peticion.getTRRELIMPSCNACEVTY().getIPIMPSCNP();
		ipimpscnp.setCODNRBEEN(this.getEntidad());

		ipimpscnp.setNUMSECAC(numAcuerdo);

		ipimpscnp.setNUMSUBAC(numSubAc.intValue());

		peticion.getTRRELIMPSCNACEVTY().setFECHAOPRCN(this.getFechaSistemaInteger());

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_REL_IMPSCN_AC_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRRELIMPSCNACTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaRelacionesCuentaPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de relacion cuenta plazo.", e);
		}
		return respuesta;
	}

}
