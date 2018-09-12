package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.condiciones.CondicionRangoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionrangoplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionrangoplazo.Ejecutar.ITRMODIFICARKSRNGTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionrangoplazo.Ejecutar.ITRMODIFICARKSRNGTRN.TRMODIFICARKSRNGEVTY.KSCDSBE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionrangoplazo.Ejecutar.ITRMODIFICARKSRNGTRN.TRMODIFICARKSRNGEVTY.KSESTRCTRNGV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionrangoplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificacondicionrangoplazo.ModificaCondicionRangoPlazoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaCondicionRangoPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 5979675960742465600L;


    @Autowired
    ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final Long numCuenta, final int numSubAc,final String idPds, final CondicionRangoBean condicionBean){
		final Ejecutar.ITRMODIFICARKSRNGTRN itrmodificarksrngtrn = initPeticion(numCuenta,numSubAc,idPds,condicionBean);

		final EjecutarResult respuesta = ejecutarWS(itrmodificarksrngtrn);

		super.verificaRespuesta(respuesta);
	}

	private Ejecutar.ITRMODIFICARKSRNGTRN initPeticion(final Long numCuenta, final int numSubAc, final String idPds,
	        final CondicionRangoBean condicionBean){
		final Ejecutar.ITRMODIFICARKSRNGTRN itrmodificarksrngtrn = new Ejecutar.ITRMODIFICARKSRNGTRN();

		super.initialize(itrmodificarksrngtrn);

		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();


		final KSCDSBE kscdsbe = itrmodificarksrngtrn.getTRMODIFICARKSRNGEVTY().getKSCDSBE();
        kscdsbe.setCODNRBEEN(this.getEntidad());

		kscdsbe.setNUMSECAC(numCuenta);
        kscdsbe.setNUMSUBAC(numSubAc);
		kscdsbe.setIDPDS(idPds);
		kscdsbe.setIDPARMCD(condicionBean.getClave());

		kscdsbe.setFECHAINICVAL(itdConverter.convertFrom(condicionBean.getFechaInicioValidez()));
		if(null!=condicionBean.getFechaFinValidez()){
            kscdsbe.setFECHACRREVAL(itdConverter.convertFrom(condicionBean.getFechaFinValidez()));
		}

        itrmodificarksrngtrn.getTRMODIFICARKSRNGEVTY().getIDCCPS1V().setIDCCPS(condicionBean.getIdCcps());
        itrmodificarksrngtrn.getTRMODIFICARKSRNGEVTY().getCODECVACV().setCODECVAC(condicionBean.getEstado());

        final KSESTRCTRNGV ksestrctrngv = itrmodificarksrngtrn.getTRMODIFICARKSRNGEVTY().getKSESTRCTRNGV();

        ksestrctrngv.setRNGPREF(condicionBean.getPreferente());
        ksestrctrngv.setRNGMIN(condicionBean.getMinimo());
        ksestrctrngv.setRNGMAX(condicionBean.getMaximo());
        ksestrctrngv.setRNGINCREM(condicionBean.getIncremento());
        ksestrctrngv.setCODUM(condicionBean.getUnidades());

        final STDTRNIPARMV stdtrniparmv = itrmodificarksrngtrn.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODIFICAR_KS);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return itrmodificarksrngtrn;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRMODIFICARKSRNGTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ModificaCondicionRangoPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de modificacion "
					+ "de condicion tipo rango plazo.", e);
		}
		return respuesta;
	}

}
