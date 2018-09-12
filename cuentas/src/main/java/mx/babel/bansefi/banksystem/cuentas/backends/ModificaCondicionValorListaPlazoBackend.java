package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionValorListaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlistaplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlistaplazo.Ejecutar.ITRMODIFICARKSVLSTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlistaplazo.Ejecutar.ITRMODIFICARKSVLSTRN.TRMODIFICARKSVLSEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlistaplazo.Ejecutar.ITRMODIFICARKSVLSTRN.TRMODIFICARKSVLSEVTY.KSCDSBE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlistaplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionvalorlistaplazo.ModificaCondicionValorListaPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ModificaCondicionValorListaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionValorListaPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 8052643287690917321L;

	@Autowired
    ModificaCondicionValorListaWrapper modificaCondicionValorListaWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta, final int numSubAc, final String idPds, final CondicionValorListaBean condicionBean){
		final Ejecutar.ITRMODIFICARKSVLSTRN trmodificarksvlstrni = initPeticion(numCuenta, numSubAc, idPds, condicionBean);

		final EjecutarResult respuesta = ejecutarWS(trmodificarksvlstrni);

		super.verificaRespuesta(respuesta);

	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRMODIFICARKSVLSTRN initPeticion(final Long numCuenta, final int numSubAc, final String idPds,
	        final CondicionValorListaBean condicionBean){
		final Ejecutar.ITRMODIFICARKSVLSTRN trmodificarksvlstrni = new Ejecutar.ITRMODIFICARKSVLSTRN();
        condicionBean.setFechaEstadoActual(new Date());
		final TRMODIFICARKSVLSEVTY trmodificarksvlsevty = modificaCondicionValorListaWrapper
		        .wrappCondicionValorListaPlazo(condicionBean);
		trmodificarksvlstrni.setTRMODIFICARKSVLSEVTY(trmodificarksvlsevty);

		super.initialize(trmodificarksvlstrni);

        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KSCDSBE kscdsbe = trmodificarksvlstrni.getTRMODIFICARKSVLSEVTY().getKSCDSBE();
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


        trmodificarksvlstrni.getTRMODIFICARKSVLSEVTY().getIDCCPS1V().setIDCCPS(condicionBean.getIdCcps());

        trmodificarksvlstrni.getTRMODIFICARKSVLSEVTY().getKSESTRCTVLISTAV().setCODDOMPARMCD(condicionBean.getCodDomParmcd());

        //TODO solucionar esto
        if(null!=condicionBean.getEstado()){
            trmodificarksvlstrni.getTRMODIFICARKSVLSEVTY().getCODECVACV().setCODECVAC(condicionBean.getEstado());
        }else{
            trmodificarksvlstrni.getTRMODIFICARKSVLSEVTY().getCODECVACV().setCODECVAC("1");
        }
        final STDTRNIPARMV stdtrniparmv = trmodificarksvlstrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KS);
        stdtrniparmv.setNUMSEC(0);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
        return trmodificarksvlstrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKSVLSTRN contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ModificaCondicionValorListaPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modifica "
					+ "de condicion tipo Valor-Lista Plazo.", e);
		}
		return respuesta;
	}


}
