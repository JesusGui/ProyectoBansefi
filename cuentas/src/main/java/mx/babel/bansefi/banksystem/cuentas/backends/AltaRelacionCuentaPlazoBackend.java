package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentaplazo.AltaRelacionCuentaPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentaplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentaplazo.Ejecutar.ITRALTARLIMPSCNPAGTR.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentaplazo.Ejecutar.ITRALTARLIMPSCNPAGTR.TRALTARLIMPSCNPAGEVT.IPIMPSCNP;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentaplazo.Ejecutar.ITRALTARLIMPSCNPAGTR.TRALTARLIMPSCNPAGEVT.TRALTARLIMPSCNPAGEVT1;
import mx.babel.bansefi.banksystem.cuentas.webservices.altarelacioncuentaplazo.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class AltaRelacionCuentaPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numAcuerdo, final Integer numSubAc, final Long numAcRel,
	        final String codAcRel, final BigDecimal porcentaje){
		final Ejecutar.ITRALTARLIMPSCNPAGTR request = initPeticion(numAcuerdo, numSubAc, numAcRel, codAcRel, porcentaje);

		final EjecutarResult respuesta = ejecutarWS(request);

		super.verificaRespuesta(respuesta);
		
	}



	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRALTARLIMPSCNPAGTR initPeticion(final Long numAcuerdo, final Integer numSubAc, final Long numAcRel,
            final String codAcRel, final BigDecimal porcentaje){
		final Ejecutar.ITRALTARLIMPSCNPAGTR peticion = new Ejecutar.ITRALTARLIMPSCNPAGTR();

		super.initialize(peticion);
		final IPIMPSCNP ipimpscnp = peticion.getTRALTARLIMPSCNPAGEVT().getIPIMPSCNP();
        ipimpscnp.setCODNRBEEN(this.getEntidad());
		ipimpscnp.setNUMSECAC(numAcuerdo);
		ipimpscnp.setNUMSUBAC(numSubAc);


        final TRALTARLIMPSCNPAGEVT1 traltarlimpscnpagevt1 = peticion.getTRALTARLIMPSCNPAGEVT().getTRALTARLIMPSCNPAGEVT();
        traltarlimpscnpagevt1.setCODRLACSUBAC(codAcRel);
        traltarlimpscnpagevt1.setPCT(porcentaje);
        traltarlimpscnpagevt1.getACACP().setNUMSECAC(numAcRel);
        traltarlimpscnpagevt1.getACACP().setCODNRBEEN(this.getEntidad());
        traltarlimpscnpagevt1.setFECHAINIC(this.getFechaSistemaInteger());

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();

        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_ALTA_RL_IMPSCN_PAG_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRALTARLIMPSCNPAGTR contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaRelacionCuentaPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta "
					+ "de relacion cuenta plazo.", e);
		}
		return respuesta;
	}

}
