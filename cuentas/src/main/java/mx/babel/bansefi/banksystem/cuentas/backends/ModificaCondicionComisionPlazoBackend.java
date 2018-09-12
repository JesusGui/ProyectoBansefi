package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionComisionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomisionplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomisionplazo.Ejecutar.ITRMODIFICARKSCMSTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomisionplazo.Ejecutar.ITRMODIFICARKSCMSTRN.TRMODIFICARKSCMSEVTY.KSCDSBE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomisionplazo.Ejecutar.ITRMODIFICARKSCMSTRN.TRMODIFICARKSCMSEVTY.KSCMSNPRCTLV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomisionplazo.Ejecutar.ITRMODIFICARKSCMSTRN.TRMODIFICARKSCMSEVTY.KSESTRCTCMSN1V;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomisionplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomisionplazo.ModificaCondicionComisionPlazoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionComisionPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 5979675960742465600L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta, final int numSubAc, final String idPds, final CondicionComisionBean condicionBean){
		final Ejecutar.ITRMODIFICARKSCMSTRN trmodificarkscmstrni = initPeticion(numCuenta,numSubAc,idPds,condicionBean);

		final EjecutarResult respuesta = ejecutarWS(trmodificarkscmstrni);

		super.verificaRespuesta(respuesta);
	}

	private Ejecutar.ITRMODIFICARKSCMSTRN initPeticion(final Long numCuenta,final int numSubAc, final String idPds,
	        final CondicionComisionBean condicionBean){
		final Ejecutar.ITRMODIFICARKSCMSTRN trmodificarkscmstrni = new Ejecutar.ITRMODIFICARKSCMSTRN();

		super.initialize(trmodificarkscmstrni);

		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KSCDSBE kscdsbe = trmodificarkscmstrni.getTRMODIFICARKSCMSEVTY().getKSCDSBE();
        kscdsbe.setCODNRBEEN(this.getEntidad());

		kscdsbe.setNUMSECAC(numCuenta);
        kscdsbe.setNUMSUBAC(numSubAc);
		kscdsbe.setIDPDS(idPds);
		kscdsbe.setIDPARMCD(condicionBean.getClave());

		kscdsbe.setFECHAINICVAL(itdConverter.convertFrom(condicionBean.getFechaInicioValidez()));
		if(null!=condicionBean.getFechaFinValidez()){
            kscdsbe.setFECHACRREVAL(itdConverter.convertFrom(condicionBean.getFechaFinValidez()));
		}
        final KSESTRCTCMSN1V ksestrctcmsn1v = trmodificarkscmstrni.getTRMODIFICARKSCMSEVTY().getKSESTRCTCMSN1V();

        ksestrctcmsn1v.setCODCMSN(condicionBean.getTipo());
        //TODO hardcodeado
        ksestrctcmsn1v.setCODNUMRCOMONEDA("MXN");

        trmodificarkscmstrni.getTRMODIFICARKSCMSEVTY().getKSCMSNFIJV().setIMPCMSNFIJ(condicionBean.getImporteComFija());
        trmodificarkscmstrni.getTRMODIFICARKSCMSEVTY().getKSCMSNFIJV().setFORCALPERPCAL(condicionBean.getFormaCalculoComFija());

        final KSCMSNPRCTLV kscmsnprctlv = trmodificarkscmstrni.getTRMODIFICARKSCMSEVTY().getKSCMSNPRCTLV();
        kscmsnprctlv.setCODCOMSNPRCTL(condicionBean.getBaseCalculoComVar());
        kscmsnprctlv.setCMSNPCT(condicionBean.getPorcentajeComisionComVar());
        kscmsnprctlv.setCMSNMIN(condicionBean.getImporteMinComVar());
        kscmsnprctlv.setIMPFRANQ(condicionBean.getImporteFranquiciaComVar());

        trmodificarkscmstrni.getTRMODIFICARKSCMSEVTY().getIDCCPS1V().setIDCCPS(condicionBean.getIdCcps());

        trmodificarkscmstrni.getTRMODIFICARKSCMSEVTY().getCODECVACV().setCODECVAC(condicionBean.getEstado());

        final STDTRNIPARMV stdtrniparmv = trmodificarkscmstrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KS);
        stdtrniparmv.setNUMSEC(0);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return trmodificarkscmstrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKSCMSTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificaCondicionComisionPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion "
					+ "de condicion tipo Comision Plazo.", e);
		}
		return respuesta;
	}

}
