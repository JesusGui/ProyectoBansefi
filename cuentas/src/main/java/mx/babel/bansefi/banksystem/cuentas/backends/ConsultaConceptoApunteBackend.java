package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.ConceptoApunteBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaconceptopk.ConsultaConceptoApunteServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaconceptopk.Ejecutar.ITRNOTIFCONSPKTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaconceptopk.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de consulta concepto de un apunte.
 * 
 * @author omar.marquez
 */
@Component
public class ConsultaConceptoApunteBackend extends BackEndBean {

	private static final long serialVersionUID = 2235280689523627356L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que devuelve el concepto a partir de su clave.
	 * 
	 * @param claveConcepto
	 * @return concepto del apunte
	 */
	public ConceptoApunteBean ejecutarTRN(final String claveConcepto) {

		final ITRNOTIFCONSPKTRNI itrnotifconspktrni = initPeticion(claveConcepto);

		final EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaConceptoApunteServicio.class,
						itrnotifconspktrni);

		super.verificaRespuesta(resultado);

		if (resultado.getResponseBansefi().getOTRNOTIFCONSPKTRNO()
				.getTRNOTIFCONSPKEVTZ() != null
				&& resultado.getResponseBansefi().getOTRNOTIFCONSPKTRNO()
						.getTRNOTIFCONSPKEVTZ().getNOMBPARMCD() != null) {
			final ConceptoApunteBean caBean = new ConceptoApunteBean();
			caBean.setNombre(resultado.getResponseBansefi()
					.getOTRNOTIFCONSPKTRNO().getTRNOTIFCONSPKEVTZ()
					.getNOMBPARMCD().trim());
			caBean.setUnidades(resultado.getResponseBansefi()
					.getOTRNOTIFCONSPKTRNO().getTRNOTIFCONSPKEVTZ()
					.getCODCATUM().trim());
			caBean.setCodEstrctCd(resultado.getResponseBansefi()
					.getOTRNOTIFCONSPKTRNO().getTRNOTIFCONSPKEVTZ()
					.getCODESTRCTPARMCD().trim());

			return caBean;
		}
		return new ConceptoApunteBean();
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param claveConcepto
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRNOTIFCONSPKTRNI
	 */
	private ITRNOTIFCONSPKTRNI initPeticion(final String claveConcepto) {
		final ITRNOTIFCONSPKTRNI itrnotifconspktrni = new ITRNOTIFCONSPKTRNI();

		super.initialize(itrnotifconspktrni);

		itrnotifconspktrni.setELEVATORPOSITION(0);
		itrnotifconspktrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrnotifconspktrni.getTRNOTIFCONSPKEVTY().getPKPARMCDP()
				.setIDPARMCD(claveConcepto);

		itrnotifconspktrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrnotifconspktrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_NOTIF_CONS_PK_TRN);

		return itrnotifconspktrni;
	}

}