package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacioncuentaplazo.BajaRelacionCuentaPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacioncuentaplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacioncuentaplazo.Ejecutar.ITRBAJARLIMPSCNPAGTR.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacioncuentaplazo.Ejecutar.ITRBAJARLIMPSCNPAGTR.TRBAJARLIMPSCNPAGEVT.IPIMPSCNP;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacioncuentaplazo.Ejecutar.ITRBAJARLIMPSCNPAGTR.TRBAJARLIMPSCNPAGEVT.TRBAJARLIMPSCNPAGEVT1;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacioncuentaplazo.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class BajaRelacionCuentaPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numAcuerdo, final Integer numSubAc, final Long numAcRel,
	        final String codAcRel, final BigDecimal porcentaje){
		final Ejecutar.ITRBAJARLIMPSCNPAGTR request = initPeticion(numAcuerdo, numSubAc, numAcRel, codAcRel, porcentaje);

		final EjecutarResult respuesta = ejecutarWS(request);

		super.verificaRespuesta(respuesta);
		
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRBAJARLIMPSCNPAGTR initPeticion(final Long numAcuerdo, final Integer numSubAc, final Long numAcRel,
            final String codAcRel, final BigDecimal porcentaje){
		final Ejecutar.ITRBAJARLIMPSCNPAGTR peticion = new Ejecutar.ITRBAJARLIMPSCNPAGTR();

		super.initialize(peticion);
		final IPIMPSCNP ipimpscnp = peticion.getTRBAJARLIMPSCNPAGEVT().getIPIMPSCNP();
        ipimpscnp.setCODNRBEEN(this.getEntidad());
		ipimpscnp.setNUMSECAC(numAcuerdo);
		ipimpscnp.setNUMSUBAC(numSubAc);


        final TRBAJARLIMPSCNPAGEVT1 traltarlimpscnpagevt1 = peticion.getTRBAJARLIMPSCNPAGEVT().getTRBAJARLIMPSCNPAGEVT();
        traltarlimpscnpagevt1.setCODRLACSUBAC(codAcRel);
        traltarlimpscnpagevt1.setPCT(porcentaje);
        traltarlimpscnpagevt1.getACACP().setNUMSECAC(numAcRel);
        traltarlimpscnpagevt1.getACACP().setCODNRBEEN(this.getEntidad());
        traltarlimpscnpagevt1.setFECHAINIC(this.getFechaSistemaInteger());

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();

        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_BAJA_RL_IMPSCN_PAG_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRBAJARLIMPSCNPAGTR contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					BajaRelacionCuentaPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de baja "
					+ "de relacion cuenta plazo.", e);
		}
		return respuesta;
	}
}
