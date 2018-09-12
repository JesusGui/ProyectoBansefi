package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionInteresBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioninteres.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioninteres.Ejecutar.ITRMODIFICARKAINTTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioninteres.Ejecutar.ITRMODIFICARKAINTTRN.TRMODIFICARKAINTEVTY.KACDACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioninteres.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioninteres.ModificaCondicionInteresServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionInteresBackend extends BackEndBean{

	private static final long serialVersionUID = 5979675960742465600L;


    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta,final String idPds, final CondicionInteresBean condicionBean){
		final Ejecutar.ITRMODIFICARKAINTTRN contexto = initPeticion(numCuenta,idPds,condicionBean);

		final EjecutarResult respuesta = ejecutarWS(contexto);

		super.verificaRespuesta(respuesta);
	}

	private Ejecutar.ITRMODIFICARKAINTTRN initPeticion(final Long numCuenta,final String idPds, final CondicionInteresBean condicionBean){
		final Ejecutar.ITRMODIFICARKAINTTRN itrmodificarkainttrn = new Ejecutar.ITRMODIFICARKAINTTRN();

		super.initialize(itrmodificarkainttrn);

		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KACDACE kacdace = itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getKACDACE();
        kacdace.setCODNRBEEN(this.getEntidad());

		kacdace.setNUMSECAC(numCuenta);
		kacdace.setIDPDS(idPds);
		kacdace.setIDPARMCD(condicionBean.getClave());

		kacdace.setFECHAINICVAL(itdConverter.convertFrom(condicionBean.getFechaInicioValidez()));
		if(null!=condicionBean.getFechaFinValidez()){
            kacdace.setFECHACRREVAL(itdConverter.convertFrom(condicionBean.getFechaFinValidez()));
		}

        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getIDCCPS1V().setIDCCPS(condicionBean.getIdCcps());
        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getCODECVACV().setCODECVAC(condicionBean.getEstado());

        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getKAESTRCTINT1V().setCODINT(condicionBean.getTipo());
        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getKAESTRCTINTFIJV().setINTVALOR(condicionBean.getValorIntFijo());
        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getKAESTRCTINTVARV().setINTINCREM(condicionBean.getDiferencialIntVar());
        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getKAESTRCTINTVARV().setINTMIN(condicionBean.getInteresMinIntVar());
        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getKAESTRCTINTVARV().setINTMAX(condicionBean.getInteresMaxIntVar());
        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getKAESTRCTINTVARV().setINTFRECREV(condicionBean.getFrecuenciaRevIntVar());
        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getKAESTRCTINTVARV().setFRACCIONREV(condicionBean.getFraccionRevIntVar());
        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getKAESTRCTINTVARV().setPCTMINREV(condicionBean.getPorcentajeMinRevIntVar());
        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getKAESTRCTINTVARV().setCODREFINT(condicionBean.getCodReferenciaIntVar());
        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getKAESTRCTINTVARV().setCODCRITREV(condicionBean.getCodCriterioRevIntVar());
        itrmodificarkainttrn.getTRMODIFICARKAINTEVTY().getKAESTRCTINTVARV().setFORMAREV(condicionBean.getFormaRevIntVar());


        final STDTRNIPARMV stdtrniparmv = itrmodificarkainttrn.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KA);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return itrmodificarkainttrn;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKAINTTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ModificaCondicionInteresServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion "
					+ "de condicion tipo interes.", e);
		}
		return respuesta;
	}

}
