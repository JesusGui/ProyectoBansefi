package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.CuotaIPFBean;
import mx.babel.bansefi.banksystem.cuentas.beans.RespuestaCuotaIPFBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.pagocuota.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.pagocuota.Ejecutar.ITRCOBRARCUOTAAHORROT.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.pagocuota.Ejecutar.ITRCOBRARCUOTAAHORROT.TRCOBRARCUOTAAHORROEV.ACACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.pagocuota.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.pagocuota.PagoCuotaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.pagocuota.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.pagocuota.ResponseBansefi.OTRCOBRARCUOTAAHORROT.TRCOBRARCUOTAAHORROEV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class PagoCuotaBackend extends BackEndBean{



	/**
     *
     */
    private static final long serialVersionUID = 7621057730875763483L;

    @Autowired
	ServicioWebUtils servicioWebUtils;

	public RespuestaCuotaIPFBean ejecutarTRN(final CuotaIPFBean datosCuota)
			throws ControlableException, NoControlableException{
	    RespuestaCuotaIPFBean result = null;
		final Ejecutar.ITRCOBRARCUOTAAHORROT itrcobrarcuotaahorrot = initPeticion(datosCuota);

		final EjecutarResult respuesta = ejecutarWS(itrcobrarcuotaahorrot);

		if(verificaResponseBansefi(respuesta)){
		    result = getResponse(respuesta);
        }

		return result;
	}

	private RespuestaCuotaIPFBean getResponse(final EjecutarResult response)
            throws NoControlableException, ControlableException{
	    RespuestaCuotaIPFBean respuesta = null;
        try{
            super.verificaRespuesta(response);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return respuesta;
            }
        }
        if(verificaRespuesta(response.getResponseBansefi())){
            respuesta = new RespuestaCuotaIPFBean();
            final TRCOBRARCUOTAAHORROEV datos = response.getResponseBansefi().getOTRCOBRARCUOTAAHORROT().getTRCOBRARCUOTAAHORROEV();
            respuesta.setCuentaDeposito(datos.getACACE().getNUMSECAC());
            respuesta.setDeposito(datos.getIMPCUOTAV().getIMPAPNTE());
            respuesta.setDigito(datos.getACACE().getCODDIGCRUO());
            respuesta.setFechaOperacion(Integer.toString(response.getResponseBansefi().getOTRCOBRARCUOTAAHORROT().getSTDTRNOPARMV().getFECHAOPRCN()));
            respuesta.setHoraOperacion(Integer.toString(response.getResponseBansefi().getOTRCOBRARCUOTAAHORROT().getSTDTRNOPARMV().getHORAOPRCN()));
            respuesta.setImposicion(datos.getIPFV().getNUMSUBAC());
            respuesta.setTotal(datos.getDATOSCUOTAIMPRV().getCUOTASV().getSTDDEC15Y2());
            respuesta.setInteres(datos.getDATOSCUOTAIMPRV().getREINTERESMESV().getSTDDEC15Y2());
            respuesta.setIsr(datos.getDATOSCUOTAIMPRV().getREISRV().getSTDDEC15Y2());
            respuesta.setMensualidad(datos.getDATOSCUOTAIMPRV().getCUOTAMENSUALV().getSTDDEC15Y2());
            respuesta.setMetaAhorro(datos.getDATOSCUOTAIMPRV().getCUOTAMENSUALV().getSTDDEC15Y2());
            respuesta.setImporte(datos.getAFAPNTEE().getIMPAPNTE());
            respuesta.setNeto(datos.getDATOSCUOTAIMPRV().getREINTERESNETOV().getSTDDEC15Y2());
            respuesta.setNumCuotas(datos.getDATOSCUOTAIMPRV().getCUOTASPAGADASV().getSTDSMALLINT());
            respuesta.setPlaza(datos.getACACE().getCODPLZBANCARIA());
            respuesta.setSaldo(datos.getDATOSCUOTAIMPRV().getSDOACTUALV().getSTDDEC15Y2());
            //TODO corregir
            respuesta.setSucursal(this.getSucursal());
            respuesta.setTitular(datos.getDATOSCUOTAIMPRV().getNOMBCLIENTEV().getNOMB50());
        }
        return respuesta;
    }

	/**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos
     * del alta de realción.
     *
     * @param response Respuesta Bansefi con datos del alta
     * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
     */
    private Boolean verificaRespuesta(final ResponseBansefi response){
        Boolean noNulo = false;
        if(response != null && response.getOTRCOBRARCUOTAAHORROT() != null &&
            response.getOTRCOBRARCUOTAAHORROT().getTRCOBRARCUOTAAHORROEV()!= null &&
            response.getOTRCOBRARCUOTAAHORROT().getTRCOBRARCUOTAAHORROEV().getDATOSCUOTAIMPRV() != null &&
            response.getOTRCOBRARCUOTAAHORROT().getTRCOBRARCUOTAAHORROEV().getDATOSCUOTAIMPRV() != null ){
            noNulo = true;
        }
        return noNulo;
    }


    private Boolean verificaResponseBansefi(final EjecutarResult respuesta){
        return (respuesta != null && respuesta.getResponseBansefi() != null);
    }

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCOBRARCUOTAAHORROT initPeticion(final CuotaIPFBean cuotaIPF){
		final Ejecutar.ITRCOBRARCUOTAAHORROT itrcobrarcuotaahorrot = new Ejecutar.ITRCOBRARCUOTAAHORROT();

		super.initialize(itrcobrarcuotaahorrot);
        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();

		final ACACP acacp = itrcobrarcuotaahorrot.getTRCOBRARCUOTAAHORROEV().getACACP();
		acacp.setCODNRBEEN(this.getEntidad());

		acacp.setNUMSECAC(cuotaIPF.getNumAcuerdo());

		itrcobrarcuotaahorrot.getTRCOBRARCUOTAAHORROEV().getIMPCUOTAV().setIMPAPNTE(cuotaIPF.getImporteDeposito());

        itrcobrarcuotaahorrot.getTRCOBRARCUOTAAHORROEV().getIPFV().setNUMSUBAC(cuotaIPF.getNumSubAc());

        itrcobrarcuotaahorrot.getTRCOBRARCUOTAAHORROEV().getINDCAJAV().setCONTRIDA(cuotaIPF.getTipoCargo().getTipo());

        final Ejecutar.ITRCOBRARCUOTAAHORROT.STDAUTORIZAV.ARTRNMSJPARMV artrnmsjparmv =
                new Ejecutar.ITRCOBRARCUOTAAHORROT.STDAUTORIZAV.ARTRNMSJPARMV();
        super.initialize(artrnmsjparmv);
        for(int i = 0; i<10;i++){
            itrcobrarcuotaahorrot.getSTDAUTORIZAV().getARTRNMSJPARMV().add(artrnmsjparmv);
        }

        final Ejecutar.ITRCOBRARCUOTAAHORROT.STDAUTORIZAV.STDTARGETTERMINALV stdtargetterminalv =
                new Ejecutar.ITRCOBRARCUOTAAHORROT.STDAUTORIZAV.STDTARGETTERMINALV();
        super.initialize(stdtargetterminalv);
        for(int i = 0; i<50;i++){
            itrcobrarcuotaahorrot.getSTDAUTORIZAV().getSTDTARGETTERMINALV().add(stdtargetterminalv);
        }

        final STDTRNIPARMV stdtrniparmv = itrcobrarcuotaahorrot.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_COBRAR_CUOTA_AHORRO_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return itrcobrarcuotaahorrot;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRCOBRARCUOTAAHORROT contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        PagoCuotaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de pago "
					+ "de cuota para ipf.", e);
		}
		return respuesta;
	}

}
