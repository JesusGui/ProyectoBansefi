package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.PreferenceItemBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionListaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionlistaplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionlistaplazo.Ejecutar.ITRMODIFICARKSLSTTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionlistaplazo.Ejecutar.ITRMODIFICARKSLSTTRN.TRMODIFICARKSLSTEVTY.KSCDSBE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionlistaplazo.Ejecutar.ITRMODIFICARKSLSTTRN.TRMODIFICARKSLSTEVTY.KSKSRLPKDOMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionlistaplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionlistaplazo.ModificaCondicionListaPlazoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionListaPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 5979675960742465600L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta, final int numSubAc,final String idPds, final CondicionListaBean condicionBean){
		final Ejecutar.ITRMODIFICARKSLSTTRN itrmodificarkslsttrn = initPeticion(numCuenta, numSubAc,idPds,condicionBean);

		final EjecutarResult respuesta = ejecutarWS(itrmodificarkslsttrn);

		super.verificaRespuesta(respuesta);
	}

	private Ejecutar.ITRMODIFICARKSLSTTRN initPeticion(final Long numCuenta, final int numSubAc,final String idPds,
	        final CondicionListaBean condicionBean){
		final Ejecutar.ITRMODIFICARKSLSTTRN itrmodificarkslsttrn = new Ejecutar.ITRMODIFICARKSLSTTRN();

		super.initialize(itrmodificarkslsttrn);

		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KSCDSBE kscdsbe = itrmodificarkslsttrn.getTRMODIFICARKSLSTEVTY().getKSCDSBE();
		kscdsbe.setCODNRBEEN(this.getEntidad());

		kscdsbe.setNUMSECAC(numCuenta);
        kscdsbe.setNUMSUBAC(numSubAc);
		kscdsbe.setIDPDS(idPds);
		kscdsbe.setIDPARMCD(condicionBean.getClave());

		kscdsbe.setFECHAINICVAL(itdConverter.convertFrom(condicionBean.getFechaInicioValidez()));
		if(null!=condicionBean.getFechaFinValidez()){
            kscdsbe.setFECHACRREVAL(itdConverter.convertFrom(condicionBean.getFechaFinValidez()));
		}

        itrmodificarkslsttrn.getTRMODIFICARKSLSTEVTY().getIDCCPS1V().setIDCCPS(condicionBean.getIdCcps());
        itrmodificarkslsttrn.getTRMODIFICARKSLSTEVTY().getCODECVACV().setCODECVAC(condicionBean.getEstado());

        final List<KSKSRLPKDOMV>  listKaksrlpkdomv = itrmodificarkslsttrn.getTRMODIFICARKSLSTEVTY().getKSKSRLPKDOMV();
        if(condicionBean.getLista()!=null){
            for(final PreferenceItemBean item: condicionBean.getLista()){
                //TODO setear el preferente
                final KSKSRLPKDOMV newItem = new KSKSRLPKDOMV();
                newItem.setCODDOMPARMCD(item.getId());
                if(item.isPreferente()){
                    newItem.setDOMINDPREF("1");
                }
                listKaksrlpkdomv.add(newItem);
            }
        }

        final STDTRNIPARMV stdtrniparmv = itrmodificarkslsttrn.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KS);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return itrmodificarkslsttrn;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKSLSTTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ModificaCondicionListaPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion "
					+ "de condicion tipo Lista Plazo.", e);
		}
		return respuesta;
	}

}
