package mx.babel.bansefi.banksystem.base.backends;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.DepositoIPFBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.consultadatospeticionipf.ConsultaDatosPeticionIPFServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadatospeticionipf.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadatospeticionipf.Ejecutar.ITRDATOSPETIMPSCNPAG.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.base.webservices.consultadatospeticionipf.Ejecutar.ITRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE;
import mx.babel.bansefi.banksystem.base.webservices.consultadatospeticionipf.Ejecutar.ITRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.ACACP;
import mx.babel.bansefi.banksystem.base.webservices.consultadatospeticionipf.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadatospeticionipf.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.wrappers.ConsultaDatosPeticionIPFWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaDatosPeticionIPFBackend extends BackEndBean{

    /**
     *
     */
    private static final long serialVersionUID = 5096423337662981106L;

    @Autowired
    ConsultaDatosPeticionIPFWrapper consultaDatosPeticionIPFWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public DepositoIPFBean ejecutarTRN(final Long numCuenta, final BigDecimal importeApunte)
			throws ControlableException, NoControlableException{
	    DepositoIPFBean resultado = null;
		final Ejecutar.ITRDATOSPETIMPSCNPAG request = initPeticion(numCuenta, importeApunte);

		final EjecutarResult respuesta = ejecutarWS(request);
		
		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return null;
            }
        }
		
		if(verificaResponseBansefi(respuesta)){
	        resultado = getResponse(respuesta.getResponseBansefi());
		}else{
		    throw new NoControlableException("Ha ocurrido un error durante la consulta de datos de la peticion IPF."
		            , "La respuesta de la consulta de datos de la peticion IPF es nula.");
		}
		return resultado;
	}


	private DepositoIPFBean getResponse(final ResponseBansefi response){
	    DepositoIPFBean respuesta = null;		
		if(verificaRespuesta(response)){
		    respuesta = consultaDatosPeticionIPFWrapper.wrappDepositoIPF(response.getOTRDATOSPETIMPSCNPAG().getTRDATOSPETIMPSCNPAGE());
		}
		return respuesta;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * @param ultimoTramo
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRDATOSPETIMPSCNPAG initPeticion(final Long numCuenta, final BigDecimal importeApunte){
		final Ejecutar.ITRDATOSPETIMPSCNPAG peticion = new Ejecutar.ITRDATOSPETIMPSCNPAG();

		super.initialize(peticion);
		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();


		final ACACP acacp = peticion.getTRDATOSPETIMPSCNPAGE().getACACP();
		acacp.setCODNRBEEN(this.getEntidad());
		acacp.setNUMSECAC(numCuenta);

		final TRDATOSPETIMPSCNPAGE trdatospetimpscnpage = peticion.getTRDATOSPETIMPSCNPAGE();

		trdatospetimpscnpage.setIMPAPNTE(importeApunte);
		//TODO cambiar por dato real
		trdatospetimpscnpage.setIMPNOMINAL(new BigDecimal(0));
		trdatospetimpscnpage.setFECHAVALOR(itdConverter.convertFrom(super.getFechaSistema()));

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_DATOS_PET_IMPSCN_PAG_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRDATOSPETIMPSCNPAG contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        ConsultaDatosPeticionIPFServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de la consulta "
					+ "de datos de la peticion IPF.", e);
		}
		return respuesta;
	}

	/**
	 * Función que valida que la respuesta del servidor no este vacía.
	 *
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(final EjecutarResult respuesta){
		return (respuesta != null && respuesta.getResponseBansefi() != null);
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
		if(response != null && response.getOTRDATOSPETIMPSCNPAG() != null &&
			response.getOTRDATOSPETIMPSCNPAG().getTRDATOSPETIMPSCNPAGE()!= null &&
			response.getOTRDATOSPETIMPSCNPAG().getSTDANAVMSJV() != null ){
			noNulo = true;
		}
		return noNulo;
	}

}
