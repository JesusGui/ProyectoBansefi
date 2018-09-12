package mx.babel.bansefi.banksystem.personas.backend;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.OtroValorBean;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotrotelfaxpersona.BajaOtroTelFaxPersonaServicio;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotrotelfaxpersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotrotelfaxpersona.Ejecutar.ITRDRBAJADRELCTRTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotrotelfaxpersona.Ejecutar.ITRDRBAJADRELCTRTRN.TRDRBAJADRELCTREVTY.DRELCTRP;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotrotelfaxpersona.Ejecutar.ITRDRBAJADRELCTRTRN.TRDRBAJADRELCTREVTY.PEPERSP;
import mx.babel.bansefi.banksystem.personas.webservices.bajaotrotelfaxpersona.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Baja
 * de Otro Telefono/Fax de Personas.
 * 
 */
@Component
public class BajaOtroTelFaxPersonaBackend extends BackEndBean{

	private static final long serialVersionUID = 6524815288581129761L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public void ejecutarTRN(final int idPersona, final OtroValorBean otroValorBean){
		
		final Ejecutar.ITRDRBAJADRELCTRTRN trdrbajadrelctrtrni = initPeticion(idPersona, otroValorBean);
		final EjecutarResult respuesta = ejecutarWS(trdrbajadrelctrtrni);
        super.verificaRespuesta(respuesta);

	}


	private Ejecutar.ITRDRBAJADRELCTRTRN initPeticion(final int idPersona, final OtroValorBean otroValorBean){
		final Ejecutar.ITRDRBAJADRELCTRTRN trdrbajadrelctrtrni = new Ejecutar.ITRDRBAJADRELCTRTRN();

		super.initialize(trdrbajadrelctrtrni);

		final PEPERSP pepersp = trdrbajadrelctrtrni.getTRDRBAJADRELCTREVTY().getPEPERSP();
		pepersp.setCODNRBEEN(this.getEntidad());
		pepersp.setIDINTERNOPE(idPersona);

		final DRELCTRP drelctrp = trdrbajadrelctrtrni.getTRDRBAJADRELCTREVTY().getDRELCTRP();
		drelctrp.setCODNRBEEN(this.getEntidad());
		//TODO WTF como relleno esto
		drelctrp.setNUMDIR(otroValorBean.getIdInterno());

        //TODO WTF como relleno esto

        final STDTRNIPARMV stdtrniparmv = trdrbajadrelctrtrni.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_DR_BAJA_DR_ELCTR_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

		return trdrbajadrelctrtrni;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRDRBAJADRELCTRTRN contexto){
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(BajaOtroTelFaxPersonaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de baja "
					+ "de otro telefono/fax de persona.", e);
		}
		return respuesta;
	}
}
