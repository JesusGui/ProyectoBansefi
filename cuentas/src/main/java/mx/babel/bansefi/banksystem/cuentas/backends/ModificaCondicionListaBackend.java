package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.PreferenceItemBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionListaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionlista.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionlista.Ejecutar.ITRMODIFICARKALSTTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionlista.Ejecutar.ITRMODIFICARKALSTTRN.TRMODIFICARKALSTEVTY.KACDACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionlista.Ejecutar.ITRMODIFICARKALSTTRN.TRMODIFICARKALSTEVTY.KAKARLPKDOMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionlista.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionlista.ModificaCondicionListaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionListaBackend extends BackEndBean{

	private static final long serialVersionUID = 5979675960742465600L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta,final String idPds, final CondicionListaBean condicionBean){
		final Ejecutar.ITRMODIFICARKALSTTRN itrmodificarkalsttrn = initPeticion(numCuenta,idPds,condicionBean);

		final EjecutarResult respuesta = ejecutarWS(itrmodificarkalsttrn);

		super.verificaRespuesta(respuesta);
	}

	private Ejecutar.ITRMODIFICARKALSTTRN initPeticion(final Long numCuenta,final String idPds, final CondicionListaBean condicionBean){
		final Ejecutar.ITRMODIFICARKALSTTRN itrmodificarkalsttrn = new Ejecutar.ITRMODIFICARKALSTTRN();

		super.initialize(itrmodificarkalsttrn);

		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KACDACE kacdace = itrmodificarkalsttrn.getTRMODIFICARKALSTEVTY().getKACDACE();
        kacdace.setCODNRBEEN(this.getEntidad());

		kacdace.setNUMSECAC(numCuenta);
		kacdace.setIDPDS(idPds);
		kacdace.setIDPARMCD(condicionBean.getClave());

		kacdace.setFECHAINICVAL(itdConverter.convertFrom(condicionBean.getFechaInicioValidez()));
		if(null!=condicionBean.getFechaFinValidez()){
            kacdace.setFECHACRREVAL(itdConverter.convertFrom(condicionBean.getFechaFinValidez()));
		}
        itrmodificarkalsttrn.getTRMODIFICARKALSTEVTY().getIDCCPS1V().setIDCCPS(condicionBean.getIdCcps());
        itrmodificarkalsttrn.getTRMODIFICARKALSTEVTY().getCODECVACV().setCODECVAC(condicionBean.getEstado());

        final List<KAKARLPKDOMV>  listKakarlpkdomv = itrmodificarkalsttrn.getTRMODIFICARKALSTEVTY().getKAKARLPKDOMV();
        if(condicionBean.getLista()!=null){
            for(final PreferenceItemBean item: condicionBean.getLista()){
                //TODO setear el preferente
                final KAKARLPKDOMV newItem = new KAKARLPKDOMV();
                newItem.setCODDOMPARMCD(item.getId());
                if(item.isPreferente()){
                    newItem.setDOMINDPREF("1");
                }
                listKakarlpkdomv.add(newItem);
            }
        }

        final STDTRNIPARMV stdtrniparmv = itrmodificarkalsttrn.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KA);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return itrmodificarkalsttrn;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKALSTTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ModificaCondicionListaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion "
					+ "de condicion tipo Lista.", e);
		}
		return respuesta;
	}

}
