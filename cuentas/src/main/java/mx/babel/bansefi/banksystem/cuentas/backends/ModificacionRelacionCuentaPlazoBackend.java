package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacionrelacioncuentaplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacionrelacioncuentaplazo.Ejecutar.ITRMDFRLIMPSCNPAGTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacionrelacioncuentaplazo.Ejecutar.ITRMDFRLIMPSCNPAGTRN.TRMDFRLIMPSCNPAGEVT.IPIMPSCNP;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacionrelacioncuentaplazo.Ejecutar.ITRMDFRLIMPSCNPAGTRN.TRMDFRLIMPSCNPAGEVT.TRMDFRLIMPSCNPAGEVT1;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacionrelacioncuentaplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacionrelacioncuentaplazo.ModificacionRelacionCuentaPlazoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ModificacionRelacionCuentaPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numAcuerdo, final Integer numSubAc, final Long numAcRel,
	        final String codAcRel, final BigDecimal porcentaje){
		final Ejecutar.ITRMDFRLIMPSCNPAGTRN request = initPeticion(numAcuerdo, numSubAc, numAcRel, codAcRel, porcentaje);

		final EjecutarResult respuesta = ejecutarWS(request);
		
		super.verificaRespuesta(respuesta);		
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRMDFRLIMPSCNPAGTRN initPeticion(final Long numAcuerdo, final Integer numSubAc, final Long numAcRel,
            final String codAcRel, final BigDecimal porcentaje){
		final Ejecutar.ITRMDFRLIMPSCNPAGTRN peticion = new Ejecutar.ITRMDFRLIMPSCNPAGTRN();

		super.initialize(peticion);
		final IPIMPSCNP ipimpscnp = peticion.getTRMDFRLIMPSCNPAGEVT().getIPIMPSCNP();
        ipimpscnp.setCODNRBEEN(this.getEntidad());
		ipimpscnp.setNUMSECAC(numAcuerdo);
		ipimpscnp.setNUMSUBAC(numSubAc);

		final TRMDFRLIMPSCNPAGEVT1 trmdfrlimpscnpagevt1 = peticion.getTRMDFRLIMPSCNPAGEVT().getTRMDFRLIMPSCNPAGEVT();
		trmdfrlimpscnpagevt1.getACACP().setCODNRBEEN(this.getEntidad());
		trmdfrlimpscnpagevt1.getACACP().setNUMSECAC(numAcRel);
		trmdfrlimpscnpagevt1.setCODRLACSUBAC(codAcRel);
        trmdfrlimpscnpagevt1.setPCT(porcentaje);
        trmdfrlimpscnpagevt1.setFECHAINIC(this.getFechaSistemaInteger());
        trmdfrlimpscnpagevt1.setFECHACRRE(ConstantesFuncionales.MAX_FECHA_FIN_INTEGER);

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();

        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MDF_RL_IMPSCN_PAG_TRN );

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMDFRLIMPSCNPAGTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificacionRelacionCuentaPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion "
					+ "de relacion cuenta plazo.", e);
		}
		return respuesta;
	}

}
