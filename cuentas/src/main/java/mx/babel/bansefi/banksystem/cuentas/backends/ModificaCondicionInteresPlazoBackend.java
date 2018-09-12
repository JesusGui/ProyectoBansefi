package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionInteresBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioninteresplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioninteresplazo.Ejecutar.ITRMODIFICARKSINTTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioninteresplazo.Ejecutar.ITRMODIFICARKSINTTRN.TRMODIFICARKSINTEVTY.KSCDSBE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioninteresplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicioninteresplazo.ModificaCondicionInteresPlazoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionInteresPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 5979675960742465600L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta, final int numSubAc,final String idPds, final CondicionInteresBean condicionBean){
		final Ejecutar.ITRMODIFICARKSINTTRN contexto = initPeticion(numCuenta, numSubAc,idPds,condicionBean);

		final EjecutarResult respuesta = ejecutarWS(contexto);

		super.verificaRespuesta(respuesta);
	}

	private Ejecutar.ITRMODIFICARKSINTTRN initPeticion(final Long numCuenta, final int numSubAc,final String idPds,
	        final CondicionInteresBean condicionBean){
		final Ejecutar.ITRMODIFICARKSINTTRN itrmodificarksinttrn = new Ejecutar.ITRMODIFICARKSINTTRN();

		super.initialize(itrmodificarksinttrn);

		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KSCDSBE kscdsbe = itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getKSCDSBE();
        kscdsbe.setCODNRBEEN(this.getEntidad());

		kscdsbe.setNUMSECAC(numCuenta);
        kscdsbe.setNUMSUBAC(numSubAc);
		kscdsbe.setIDPDS(idPds);
		kscdsbe.setIDPARMCD(condicionBean.getClave());

		kscdsbe.setFECHAINICVAL(itdConverter.convertFrom(condicionBean.getFechaInicioValidez()));
		if(null!=condicionBean.getFechaFinValidez()){
            kscdsbe.setFECHACRREVAL(itdConverter.convertFrom(condicionBean.getFechaFinValidez()));
		}
        //TODO soluciona esto
        if(null!=condicionBean.getIndCdAc()){
            kscdsbe.setINDCDAC(condicionBean.getIndCdAc());
        }else{
            kscdsbe.setINDCDAC("N");
        }

        itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getIDCCPS1V().setIDCCPS(condicionBean.getIdCcps());
      //TODO solucionar esto
        if(null!=condicionBean.getEstado()){
            itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getCODECVACV().setCODECVAC(condicionBean.getEstado());
        }else{
            itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getCODECVACV().setCODECVAC("1");
        }

        itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getKSESTRCTINT1V().setCODINT(condicionBean.getTipo());
        itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getKSESTRCTINTFIJV().setINTVALOR(condicionBean.getValorIntFijo());
        itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getKSESTRCTINTVARV().setINTINCREM(condicionBean.getDiferencialIntVar());
        itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getKSESTRCTINTVARV().setINTMIN(condicionBean.getInteresMinIntVar());
        itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getKSESTRCTINTVARV().setINTMAX(condicionBean.getInteresMaxIntVar());
        itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getKSESTRCTINTVARV().setINTFRECREV(condicionBean.getFrecuenciaRevIntVar());
        itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getKSESTRCTINTVARV().setFRACCIONREV(condicionBean.getFraccionRevIntVar());
        itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getKSESTRCTINTVARV().setPCTMINREV(condicionBean.getPorcentajeMinRevIntVar());
        itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getKSESTRCTINTVARV().setCODREFINT(condicionBean.getCodReferenciaIntVar());
        itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getKSESTRCTINTVARV().setCODCRITREV(condicionBean.getCodCriterioRevIntVar());
        itrmodificarksinttrn.getTRMODIFICARKSINTEVTY().getKSESTRCTINTVARV().setFORMAREV(condicionBean.getFormaRevIntVar());


        final STDTRNIPARMV stdtrniparmv = itrmodificarksinttrn.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KS);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return itrmodificarksinttrn;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKSINTTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ModificaCondicionInteresPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion "
					+ "de condicion tipo interes plazo.", e);
		}
		return respuesta;
	}

}
