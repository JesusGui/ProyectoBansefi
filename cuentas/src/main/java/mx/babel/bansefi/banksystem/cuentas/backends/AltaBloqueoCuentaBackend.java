package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.EstadoEnum;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.altabloqueocuenta.AltaBloqueoCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.altabloqueocuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.altabloqueocuenta.Ejecutar.ITRALTABLOQUEOPRTCNTR.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.altabloqueocuenta.Ejecutar.ITRALTABLOQUEOPRTCNTR.TRALTABLOQUEOPRTCNEVT;
import mx.babel.bansefi.banksystem.cuentas.webservices.altabloqueocuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.altabloqueocuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ModificaCondicionComisionWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class AltaBloqueoCuentaBackend extends BackEndBean{

	private static final long serialVersionUID = 5979675960742465600L;

	@Autowired
    ModificaCondicionComisionWrapper modificaCondicionComisionWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public String ejecutarTRN(final Long numAcuerdo, final MovimientoBean bloqueoBean)
			throws ControlableException, NoControlableException{
		final Ejecutar.ITRALTABLOQUEOPRTCNTR itraltabloqueoprtcntr = initPeticion(numAcuerdo, bloqueoBean);

		final EjecutarResult respuesta = ejecutarWS(itraltabloqueoprtcntr);
		
		super.verificaRespuestaWS(respuesta);
		if(respuesta ==null || !verificaResponseBansefi(respuesta)){
		    throw new NoControlableException("Ha ocurrido un error al dar de alta un bloqueo de cuenta."
		    , "La respuesta del alta de un bloqueo de cuenta es nula.");
		}
        return getRespuesta(respuesta.getResponseBansefi());
	}

	private Ejecutar.ITRALTABLOQUEOPRTCNTR initPeticion(final Long numAcuerdo, final MovimientoBean bloqueoBean){
		final Ejecutar.ITRALTABLOQUEOPRTCNTR itraltabloqueoprtcntr = new Ejecutar.ITRALTABLOQUEOPRTCNTR();


		super.initialize(itraltabloqueoprtcntr);

		final TRALTABLOQUEOPRTCNEVT traltabloqueoprtcnevt = itraltabloqueoprtcntr.getTRALTABLOQUEOPRTCNEVT();
		traltabloqueoprtcnevt.setCODNRBEEN(this.getEntidad());
		traltabloqueoprtcnevt.setNUMSECAC(numAcuerdo);
		traltabloqueoprtcnevt.setCODBLOQUEO(bloqueoBean.getSubTipo());
		traltabloqueoprtcnevt.setMOTIVOBLQPRTCN(bloqueoBean.getConcepto());
		traltabloqueoprtcnevt.setCODINTERNOUO(this.getSucursal());
        traltabloqueoprtcnevt.setIDINTERNOEMPLEP(this.getUsuarioId());

        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();
        traltabloqueoprtcnevt.getFECHAINICIOV().setFECHAOPRCN(
                itdConverter.convertFrom(bloqueoBean.getFechaAlta()));
        if(null != bloqueoBean.getFechaVencimiento()){
            traltabloqueoprtcnevt.getFECHAFINV().setFECHAOPRCN(
                    itdConverter.convertFrom(bloqueoBean.getFechaVencimiento()));
        }

        final STDTRNIPARMV stdtrniparmv = itraltabloqueoprtcntr.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_ALTA_BLOQUEO_PRTCN_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return itraltabloqueoprtcntr;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRALTABLOQUEOPRTCNTR contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaBloqueoCuentaServicio .class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de alta "
					+ "de bloqueo de cuenta.", e);
		}
		return respuesta;
	}

	private Boolean verificaResponseBansefi(final EjecutarResult respuesta){
        Boolean noNulo = false;
        if(respuesta != null && respuesta.getResponseBansefi() != null){
            noNulo = true;
        }
        return noNulo;
    }

	public String getRespuesta(final ResponseBansefi respuesta){
	    final int resultado = verificaResultado(respuesta);
        if(resultado == 1 || resultado == 8630 || resultado == 12){
            if(verificaRespuestaDocumento(respuesta)){
                if(resultado == 8630){
                    return "Bloqueo/Protección no manual";
                }
                if(resultado == 12){
                	return "12";
                }
            }
        }
        //Logica un poco rara, si el resultado fuera diferente
        //a 1 o 8630 saltaria una exepcion en verificaResultado
        //y no llegariamos aqui
        return null;
   }

   private Integer verificaResultado(final ResponseBansefi response)
            throws NoControlableException, ControlableException{
        int codigo = response.getOTRALTABLOQUEOPRTCNTR().getRTRNCD();
        if(codigo != 1 && codigo != 8630 && codigo != 12 ){
            for (final ResponseBansefi.OTRALTABLOQUEOPRTCNTR.STDTRNMSJPARMV mensaje :
                response.getOTRALTABLOQUEOPRTCNTR().getSTDTRNMSJPARMV()) {
                codigo = mensaje.getTEXTCODE();
            }
            EstadoEnum.mensajesError("trn", codigo);
        }
        return codigo;
    }

    private Boolean verificaRespuestaDocumento(final ResponseBansefi response){
        Boolean noNulo = false;
        if(response != null && response.getOTRALTABLOQUEOPRTCNTR() != null &&
                response.getOTRALTABLOQUEOPRTCNTR().getSTDTRNOPARMV() != null &&
                response.getOTRALTABLOQUEOPRTCNTR().getTRALTABLOQUEOPRTCNEVT() != null &&
                response.getOTRALTABLOQUEOPRTCNTR().getTRALTABLOQUEOPRTCNEVT().getBPBLOQUEOPRTCNP() != null ){
            noNulo = true;
        }
        return noNulo;
    }

}
