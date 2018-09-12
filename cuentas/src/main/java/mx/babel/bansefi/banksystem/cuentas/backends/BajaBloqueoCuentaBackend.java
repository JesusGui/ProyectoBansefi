package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajabloqueocuenta.BajaBloqueoCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajabloqueocuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajabloqueocuenta.Ejecutar.ITRBAJABLOQUEOPRTCNTR.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajabloqueocuenta.Ejecutar.ITRBAJABLOQUEOPRTCNTR.TRBAJABLOQUEOPRTCNEVT.BPBLOQUEOPRTCNP;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajabloqueocuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajabloqueocuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ModificaCondicionComisionWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class BajaBloqueoCuentaBackend extends BackEndBean{

	private static final long serialVersionUID = 5979675960742465600L;

	@Autowired
    ModificaCondicionComisionWrapper modificaCondicionComisionWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public boolean ejecutarTRN(final Long numAcuerdo, final MovimientoBean bloqueoBean){
		final Ejecutar.ITRBAJABLOQUEOPRTCNTR itrbajabloqueoprtcntr = initPeticion(numAcuerdo, bloqueoBean);

		final EjecutarResult respuesta = ejecutarWS(itrbajabloqueoprtcntr);
		
		super.verificaRespuesta(respuesta);
		
		return verificaRespuestaDocumento(respuesta.getResponseBansefi());
	}

	private Ejecutar.ITRBAJABLOQUEOPRTCNTR initPeticion(final Long numAcuerdo, final MovimientoBean bloqueoBean){
		final Ejecutar.ITRBAJABLOQUEOPRTCNTR itrbajabloqueoprtcntr = new Ejecutar.ITRBAJABLOQUEOPRTCNTR();


		super.initialize(itrbajabloqueoprtcntr);
		if(StringUtils.isNotBlank(bloqueoBean.getMotivoCancelacion())){
		    itrbajabloqueoprtcntr.getTRBAJABLOQUEOPRTCNEVT().setMOTIVOCANCANTIC(bloqueoBean.getMotivoCancelacion());
		}
		final BPBLOQUEOPRTCNP trbajabloqueoprtcnevt = itrbajabloqueoprtcntr.getTRBAJABLOQUEOPRTCNEVT().getBPBLOQUEOPRTCNP();
		trbajabloqueoprtcnevt.setCODNRBEEN(this.getEntidad());
		trbajabloqueoprtcnevt.setNUMSECAC(numAcuerdo);
		trbajabloqueoprtcnevt.setCODBLOQUEO(bloqueoBean.getSubTipo());
		trbajabloqueoprtcnevt.setNUMBLQPRTCN(bloqueoBean.getNumeroBloqueo());


        final STDTRNIPARMV stdtrniparmv = itrbajabloqueoprtcntr.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_BAJA_BLOQUEO_PRTCN_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return itrbajabloqueoprtcntr;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRBAJABLOQUEOPRTCNTR contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					BajaBloqueoCuentaServicio .class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de baja "
					+ "de bloqueo de cuenta.", e);
		}
		return respuesta;
	}
	
    private Boolean verificaRespuestaDocumento(final ResponseBansefi response){
        Boolean noNulo = false;
        if(response != null && response.getOTRBAJABLOQUEOPRTCNTR() != null &&
                response.getOTRBAJABLOQUEOPRTCNTR().getSTDTRNOPARMV() != null){
            noNulo = true;
        }
        return noNulo;
    }
}
