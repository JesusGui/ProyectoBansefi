package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorRangoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrango.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrango.Ejecutar.ITRMODIFICARKAVRGTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrango.Ejecutar.ITRMODIFICARKAVRGTRN.TRMODIFICARKAVRGEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrango.Ejecutar.ITRMODIFICARKAVRGTRN.TRMODIFICARKAVRGEVTY.KACDACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrango.Ejecutar.ITRMODIFICARKAVRGTRN.TRMODIFICARKAVRGEVTY.KAESTRCTVRNGV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrango.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorrango.ModificaCondicionValorRangoServicio;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ModificaCondicionValorRangoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionValorRangoBackend extends BackEndBean{

	private static final long serialVersionUID = -6309746381056846332L;

	@Autowired
    ModificaCondicionValorRangoWrapper modificaCondicionValorRangoWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta, final String idPds, final CondicionValorRangoBean condicionBean){
		final Ejecutar.ITRMODIFICARKAVRGTRN trmodificarkavrgtrni = initPeticion(numCuenta, idPds, condicionBean);

		final EjecutarResult respuesta = ejecutarWS(trmodificarkavrgtrni);

		super.verificaRespuesta(respuesta);
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * @param numCuenta
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRMODIFICARKAVRGTRN initPeticion(final Long numCuenta, final String idPds, final CondicionValorRangoBean condicionBean){
		final Ejecutar.ITRMODIFICARKAVRGTRN trmodificarkavrgtrni = new Ejecutar.ITRMODIFICARKAVRGTRN();
        condicionBean.setFechaEstadoActual(new Date());
		final TRMODIFICARKAVRGEVTY trmodificarkavrgevty = modificaCondicionValorRangoWrapper
            .wrappCondicionValorRango(condicionBean);
        trmodificarkavrgtrni.setTRMODIFICARKAVRGEVTY(trmodificarkavrgevty);

		super.initialize(trmodificarkavrgtrni);

		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KACDACE kacdace = trmodificarkavrgtrni.getTRMODIFICARKAVRGEVTY().getKACDACE();
		kacdace.setCODNRBEEN(this.getEntidad());

		kacdace.setNUMSECAC(numCuenta);

		kacdace.setIDPDS(idPds);

		kacdace.setIDPARMCD(condicionBean.getClave());

		kacdace.setFECHAINICVAL(itdConverter.convertFrom(condicionBean.getFechaInicioValidez()));
		if(null!=condicionBean.getFechaFinValidez()){
            kacdace.setFECHACRREVAL(itdConverter.convertFrom(condicionBean.getFechaFinValidez()));
		}
        trmodificarkavrgtrni.getTRMODIFICARKAVRGEVTY().getIDCCPS1V().setIDCCPS(condicionBean.getIdCcps());

        final KAESTRCTVRNGV kaestrctvrngv = trmodificarkavrgtrni.getTRMODIFICARKAVRGEVTY().getKAESTRCTVRNGV();
        kaestrctvrngv.setRNGVALOR(condicionBean.getValor());
        kaestrctvrngv.setCODUM(condicionBean.getUnidades());

        trmodificarkavrgtrni.getTRMODIFICARKAVRGEVTY().getCODECVACV().setCODECVAC(condicionBean.getEstado());

        final STDTRNIPARMV stdtrniparmv = trmodificarkavrgtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KA);
        stdtrniparmv.setNUMSEC(0);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

        return trmodificarkavrgtrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKAVRGTRN contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificaCondicionValorRangoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modifica "
					+ "de condicion tipo Valor-Rango.", e);
		}
		return respuesta;
	}
}
