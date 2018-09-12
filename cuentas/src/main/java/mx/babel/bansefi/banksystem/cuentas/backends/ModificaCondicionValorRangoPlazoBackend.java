package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorRangoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrangoplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrangoplazo.Ejecutar.ITRMODIFICARKSVRGTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrangoplazo.Ejecutar.ITRMODIFICARKSVRGTRN.TRMODIFICARKSVRGEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrangoplazo.Ejecutar.ITRMODIFICARKSVRGTRN.TRMODIFICARKSVRGEVTY.KSCDSBE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrangoplazo.Ejecutar.ITRMODIFICARKSVRGTRN.TRMODIFICARKSVRGEVTY.KSESTRCTVRNGV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrangoplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrangoplazo.ModificaCondicionValorRangoPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ModificaCondicionValorRangoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionValorRangoPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = -6309746381056846332L;

	@Autowired
    ModificaCondicionValorRangoWrapper modificaCondicionValorRangoWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta, final int numSubAc, final String idPds, final CondicionValorRangoBean condicionBean){
		final Ejecutar.ITRMODIFICARKSVRGTRN trmodificarksvrgtrni = initPeticion(numCuenta, numSubAc, idPds, condicionBean);

		final EjecutarResult respuesta = ejecutarWS(trmodificarksvrgtrni);

		super.verificaRespuesta(respuesta);
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * @param numCuenta
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRMODIFICARKSVRGTRN initPeticion(final Long numCuenta, final int numSubAc, final String idPds,
	        final CondicionValorRangoBean condicionBean){
		final Ejecutar.ITRMODIFICARKSVRGTRN trmodificarksvrgtrni = new Ejecutar.ITRMODIFICARKSVRGTRN();
        condicionBean.setFechaEstadoActual(new Date());
		final TRMODIFICARKSVRGEVTY trmodificarksvrgevty = modificaCondicionValorRangoWrapper
            .wrappCondicionValorRangoPlazo(condicionBean);
        trmodificarksvrgtrni.setTRMODIFICARKSVRGEVTY(trmodificarksvrgevty);

		super.initialize(trmodificarksvrgtrni);

		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KSCDSBE kscdsbe = trmodificarksvrgtrni.getTRMODIFICARKSVRGEVTY().getKSCDSBE();
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

        trmodificarksvrgtrni.getTRMODIFICARKSVRGEVTY().getIDCCPS1V().setIDCCPS(condicionBean.getIdCcps());

        final KSESTRCTVRNGV ksestrctvrngv = trmodificarksvrgtrni.getTRMODIFICARKSVRGEVTY().getKSESTRCTVRNGV();
        ksestrctvrngv.setRNGVALOR(condicionBean.getValor());
        ksestrctvrngv.setCODUM(condicionBean.getUnidades());

        //TODO solucionar esto
        if(null!=condicionBean.getEstado()){
            trmodificarksvrgtrni.getTRMODIFICARKSVRGEVTY().getCODECVACV().setCODECVAC(condicionBean.getEstado());
        }else{
            trmodificarksvrgtrni.getTRMODIFICARKSVRGEVTY().getCODECVACV().setCODECVAC("1");
        }
        final STDTRNIPARMV stdtrniparmv = trmodificarksvrgtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KS);
        stdtrniparmv.setNUMSEC(0);
        //TODO cambiar por terminal real
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

        return trmodificarksvrgtrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKSVRGTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificaCondicionValorRangoPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modifica "
					+ "de condicion tipo Valor-Rango Plazo.", e);
		}
		return respuesta;
	}

}
