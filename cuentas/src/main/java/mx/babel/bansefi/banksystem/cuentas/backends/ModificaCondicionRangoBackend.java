package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionrango.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionrango.Ejecutar.ITRMODIFICARKARNGTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionrango.Ejecutar.ITRMODIFICARKARNGTRN.TRMODIFICARKARNGEVTY.KACDACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionrango.Ejecutar.ITRMODIFICARKARNGTRN.TRMODIFICARKARNGEVTY.KAESTRCTRNGV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionrango.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionrango.ModificaCondicionRangoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionRangoBackend extends BackEndBean{

	private static final long serialVersionUID = 5979675960742465600L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta,final String idPds, final CondicionRangoBean condicionBean){
		final Ejecutar.ITRMODIFICARKARNGTRN itrmodificarkarngtrn = initPeticion(numCuenta,idPds,condicionBean);

		final EjecutarResult respuesta = ejecutarWS(itrmodificarkarngtrn);

		super.verificaRespuesta(respuesta);
	}

	private Ejecutar.ITRMODIFICARKARNGTRN initPeticion(final Long numCuenta,final String idPds, final CondicionRangoBean condicionBean){
		final Ejecutar.ITRMODIFICARKARNGTRN itrmodificarkarngtrn = new Ejecutar.ITRMODIFICARKARNGTRN();

		super.initialize(itrmodificarkarngtrn);

		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final KACDACE kacdace = itrmodificarkarngtrn.getTRMODIFICARKARNGEVTY().getKACDACE();
        kacdace.setCODNRBEEN(this.getEntidad());

		kacdace.setNUMSECAC(numCuenta);
		kacdace.setIDPDS(idPds);
		kacdace.setIDPARMCD(condicionBean.getClave());

		kacdace.setFECHAINICVAL(itdConverter.convertFrom(condicionBean.getFechaInicioValidez()));
		if(null!=condicionBean.getFechaFinValidez()){
            kacdace.setFECHACRREVAL(itdConverter.convertFrom(condicionBean.getFechaFinValidez()));
		}

        itrmodificarkarngtrn.getTRMODIFICARKARNGEVTY().getIDCCPS1V().setIDCCPS(condicionBean.getIdCcps());
        itrmodificarkarngtrn.getTRMODIFICARKARNGEVTY().getCODECVACV().setCODECVAC(condicionBean.getEstado());

        final KAESTRCTRNGV kaestrctrngv = itrmodificarkarngtrn.getTRMODIFICARKARNGEVTY().getKAESTRCTRNGV();

        kaestrctrngv.setRNGPREF(condicionBean.getPreferente());
        kaestrctrngv.setRNGMIN(condicionBean.getMinimo());
        kaestrctrngv.setRNGMAX(condicionBean.getMaximo());
        kaestrctrngv.setRNGINCREM(condicionBean.getIncremento());
        kaestrctrngv.setCODUM(condicionBean.getUnidades());

        final STDTRNIPARMV stdtrniparmv = itrmodificarkarngtrn.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KA);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return itrmodificarkarngtrn;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKARNGTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ModificaCondicionRangoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion "
					+ "de condicion tipo rango.", e);
		}
		return respuesta;
	}

}
