package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.peticionactivacionipf.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.peticionactivacionipf.Ejecutar.ITRPETCNIMPSCNPAGTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.peticionactivacionipf.Ejecutar.ITRPETCNIMPSCNPAGTRN.TRPETCNIMPSCNPAGEVTY.IPIMPSCNP;
import mx.babel.bansefi.banksystem.cuentas.webservices.peticionactivacionipf.Ejecutar.ITRPETCNIMPSCNPAGTRN.TRPETCNIMPSCNPAGEVTY.TRPETCNIMPSCNPAGEVTV;
import mx.babel.bansefi.banksystem.cuentas.webservices.peticionactivacionipf.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.peticionactivacionipf.PeticionActivacionIPFServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.peticionactivacionipf.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class PeticionActivacionIPFBackend extends BackEndBean{

    private static final long serialVersionUID = 5096423337662981106L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public Integer ejecutarTRN(final Long numCuenta, final BigDecimal importeApunte){
		final Ejecutar.ITRPETCNIMPSCNPAGTRN request = initPeticion(numCuenta, importeApunte);

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
		
		return getResponse(respuesta.getResponseBansefi());
	}


	private Integer getResponse(final ResponseBansefi response){
	    Integer respuesta = null;
		if(verificaRespuesta(response)){

		    respuesta = response.getOTRPETCNIMPSCNPAGTRN().getTRPETCNIMPSCNPAGEVTZ().getIPNUMSUBACV().getNUMSUBAC();
		}
		return respuesta;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * @param ultimoTramo
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRPETCNIMPSCNPAGTRN initPeticion(final Long numCuenta, final BigDecimal importeApunte){
		final Ejecutar.ITRPETCNIMPSCNPAGTRN peticion = new Ejecutar.ITRPETCNIMPSCNPAGTRN();

		super.initialize(peticion);
		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();


		final IPIMPSCNP ipimpscnp = peticion.getTRPETCNIMPSCNPAGEVTY().getIPIMPSCNP();
		ipimpscnp.setCODNRBEEN(this.getEntidad());
		ipimpscnp.setNUMSECAC(numCuenta);

		final TRPETCNIMPSCNPAGEVTV trpetcnimpscnpagevtv = peticion.getTRPETCNIMPSCNPAGEVTY().getTRPETCNIMPSCNPAGEVTV();
		trpetcnimpscnpagevtv.setIMPAPNTE(importeApunte);
		//TODO cambiar por dato real
		trpetcnimpscnpagevtv.setCODECVIMPSCN("S");
		trpetcnimpscnpagevtv.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		trpetcnimpscnpagevtv.setFECHAVALOR(itdConverter.convertFrom(this.getFechaSistema()));

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PETCN_IMPSCN_PAG_TRN);
        stdtrniparmv.setIDEMPLAUT(this.getUsuarioId());
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRPETCNIMPSCNPAGTRN contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					PeticionActivacionIPFServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de peticion "
					+ "de activacion de IPF.", e);
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
		if(response != null && response.getOTRPETCNIMPSCNPAGTRN() != null &&
			response.getOTRPETCNIMPSCNPAGTRN().getTRPETCNIMPSCNPAGEVTZ()!= null &&
			response.getOTRPETCNIMPSCNPAGTRN().getTRPETCNIMPSCNPAGEVTZ().getIPNUMSUBACV() != null ){
			noNulo = true;
		}
		return noNulo;
	}

}
