package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorListaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlista.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlista.Ejecutar.ITRMODIFICARKAVLSTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlista.Ejecutar.ITRMODIFICARKAVLSTRN.TRMODIFICARKAVLSEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlista.Ejecutar.ITRMODIFICARKAVLSTRN.TRMODIFICARKAVLSEVTY.KACDACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlista.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlista.ModificaCondicionValorListaServicio;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ModificaCondicionValorListaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionValorListaBackend extends BackEndBean{

	private static final long serialVersionUID = 8052643287690917321L;

	@Autowired
    ModificaCondicionValorListaWrapper modificaCondicionValorListaWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta, final String idPds, final CondicionValorListaBean condicionBean){
		final Ejecutar.ITRMODIFICARKAVLSTRN trmodificarkavlstrni = initPeticion(numCuenta, idPds, condicionBean);

		final EjecutarResult respuesta = ejecutarWS(trmodificarkavlstrni);

		super.verificaRespuesta(respuesta);
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRMODIFICARKAVLSTRN initPeticion(final Long numCuenta, final String idPds, final CondicionValorListaBean condicionBean){
		final Ejecutar.ITRMODIFICARKAVLSTRN trmodificarkavlstrni = new Ejecutar.ITRMODIFICARKAVLSTRN();
        condicionBean.setFechaEstadoActual(new Date());
		final TRMODIFICARKAVLSEVTY trmodificarkavlsevty = modificaCondicionValorListaWrapper
		        .wrappCondicionValorLista(condicionBean);
		trmodificarkavlstrni.setTRMODIFICARKAVLSEVTY(trmodificarkavlsevty);

		super.initialize(trmodificarkavlstrni);

        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KACDACE kacdace = trmodificarkavlstrni.getTRMODIFICARKAVLSEVTY().getKACDACE();
		kacdace.setCODNRBEEN(this.getEntidad());

		kacdace.setNUMSECAC(numCuenta);
		kacdace.setIDPDS(idPds);
		kacdace.setIDPARMCD(condicionBean.getClave());

        kacdace.setFECHAINICVAL(itdConverter.convertFrom(condicionBean.getFechaInicioValidez()));
        if(null!=condicionBean.getFechaFinValidez()){
            kacdace.setFECHACRREVAL(itdConverter.convertFrom(condicionBean.getFechaFinValidez()));
        }


        trmodificarkavlstrni.getTRMODIFICARKAVLSEVTY().getIDCCPS1V().setIDCCPS(condicionBean.getIdCcps());

        trmodificarkavlstrni.getTRMODIFICARKAVLSEVTY().getKAESTRCTVLISTAV().setCODDOMPARMCD(condicionBean.getCodDomParmcd());

        trmodificarkavlstrni.getTRMODIFICARKAVLSEVTY().getCODECVACV().setCODECVAC(condicionBean.getEstado());
        final STDTRNIPARMV stdtrniparmv = trmodificarkavlstrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KA);
        stdtrniparmv.setNUMSEC(0);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
        return trmodificarkavlstrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKAVLSTRN contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificaCondicionValorListaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modifica "
					+ "de condicion tipo Valor-Lista.", e);
		}
		return respuesta;
	}
}
