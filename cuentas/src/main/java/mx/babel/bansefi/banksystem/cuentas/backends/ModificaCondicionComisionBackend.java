package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionComisionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomision.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomision.Ejecutar.ITRMODIFICARKACMSTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomision.Ejecutar.ITRMODIFICARKACMSTRN.TRMODIFICARKACMSEVTY.KACDACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomision.Ejecutar.ITRMODIFICARKACMSTRN.TRMODIFICARKACMSEVTY.KACMSNPRCTLV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomision.Ejecutar.ITRMODIFICARKACMSTRN.TRMODIFICARKACMSEVTY.KAESTRCTCMSN1V;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomision.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioncomision.ModificaCondicionComisionServicio;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ModificaCondicionComisionWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionComisionBackend extends BackEndBean{

	private static final long serialVersionUID = 5979675960742465600L;

	@Autowired
    ModificaCondicionComisionWrapper modificaCondicionComisionWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta,final String idPds, final CondicionComisionBean condicionBean){
		final Ejecutar.ITRMODIFICARKACMSTRN trmodificarkacmstrni = initPeticion(numCuenta,idPds,condicionBean);

		final EjecutarResult respuesta = ejecutarWS(trmodificarkacmstrni);

		super.verificaRespuesta(respuesta);
	}

	private Ejecutar.ITRMODIFICARKACMSTRN initPeticion(final Long numCuenta,final String idPds, final CondicionComisionBean condicionBean){
		final Ejecutar.ITRMODIFICARKACMSTRN trmodificarkacmstrni = new Ejecutar.ITRMODIFICARKACMSTRN();

		super.initialize(trmodificarkacmstrni);

		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KACDACE kacdace = trmodificarkacmstrni.getTRMODIFICARKACMSEVTY().getKACDACE();
        kacdace.setCODNRBEEN(this.getEntidad());

		kacdace.setNUMSECAC(numCuenta);
		kacdace.setIDPDS(idPds);
		kacdace.setIDPARMCD(condicionBean.getClave());

		kacdace.setFECHAINICVAL(itdConverter.convertFrom(condicionBean.getFechaInicioValidez()));
		if(null!=condicionBean.getFechaFinValidez()){
		    kacdace.setFECHACRREVAL(itdConverter.convertFrom(condicionBean.getFechaFinValidez()));
		}
        final KAESTRCTCMSN1V kaestrctcmsn1v = trmodificarkacmstrni.getTRMODIFICARKACMSEVTY().getKAESTRCTCMSN1V();

        kaestrctcmsn1v.setCODCMSN(condicionBean.getTipo());
        //TODO hardcodeado
        kaestrctcmsn1v.setCODNUMRCOMONEDA("MXN");

        trmodificarkacmstrni.getTRMODIFICARKACMSEVTY().getKACMSNFIJV().setIMPCMSNFIJ(condicionBean.getImporteComFija());
        trmodificarkacmstrni.getTRMODIFICARKACMSEVTY().getKACMSNFIJV().setFORCALPERPCAL(condicionBean.getFormaCalculoComFija());

        final KACMSNPRCTLV kacmsnprctlv = trmodificarkacmstrni.getTRMODIFICARKACMSEVTY().getKACMSNPRCTLV();
        kacmsnprctlv.setCODCOMSNPRCTL(condicionBean.getBaseCalculoComVar());
        kacmsnprctlv.setCMSNPCT(condicionBean.getPorcentajeComisionComVar());
        kacmsnprctlv.setCMSNMIN(condicionBean.getImporteMinComVar());
        kacmsnprctlv.setIMPFRANQ(condicionBean.getImporteFranquiciaComVar());

        trmodificarkacmstrni.getTRMODIFICARKACMSEVTY().getIDCCPS1V().setIDCCPS(condicionBean.getIdCcps());

        trmodificarkacmstrni.getTRMODIFICARKACMSEVTY().getCODECVACV().setCODECVAC(condicionBean.getEstado());

        final STDTRNIPARMV stdtrniparmv = trmodificarkacmstrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KA);
        stdtrniparmv.setNUMSEC(0);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return trmodificarkacmstrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKACMSTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificaCondicionComisionServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion "
					+ "de condicion tipo Comision.", e);
		}
		return respuesta;
	}

}
