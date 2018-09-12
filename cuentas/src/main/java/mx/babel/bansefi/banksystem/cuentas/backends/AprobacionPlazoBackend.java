package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.cuentas.webservices.aprobacionplazo.AprobacionPlazoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.aprobacionplazo.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.aprobacionplazo.Ejecutar.ITRAPROBSOLIMPSCNPAG.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.aprobacionplazo.Ejecutar.ITRAPROBSOLIMPSCNPAG.TRAPROBSOLIMPSCNPAGE.IPIMPSCNP;
import mx.babel.bansefi.banksystem.cuentas.webservices.aprobacionplazo.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.aprobacionplazo.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class AprobacionPlazoBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;
	private final static String ESTADO_SOLICITADO = "S";
    private final static String ESTADO_APROBADO = "B";

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public String ejecutarTRN(final long numAcuerdo, final int numSubacuerdo){
	    
		final Ejecutar.ITRAPROBSOLIMPSCNPAG request = initPeticion(numAcuerdo, numSubacuerdo);

		final EjecutarResult respuesta = ejecutarWS(request);
		
		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return ESTADO_SOLICITADO;
            }
        }
		
		return getResult(respuesta.getResponseBansefi());
	}


	private String getResult(final ResponseBansefi response)
			throws NoControlableException, ControlableException{
	    String respuesta = ESTADO_SOLICITADO;		
		if(verificaRespuesta(response)){
//		    for(final KPLISTASMPLV kplistasmplv : response.getOTRKPCNSLSTSMPLTRNO().getTRKPCNSLSTSMPLEVTZ().getKPLISDOMSMPLV().getKPLISTASMPLV()){
//		        if(StringUtils.isNoneBlank(kplistasmplv.getCODDOMPARMCD(), kplistasmplv.getIDPARMCD1())){
//		            final PreferenceItemBean pib = new PreferenceItemBean();
//		            pib.setId(kplistasmplv.getCODDOMPARMCD().trim());
//		            pib.setDesc(kplistasmplv.getIDPARMCD1().trim());
//		            pib.setPreferente(StringUtils.equals("1", kplistasmplv.getDOMINDPREF()));
//		            respuesta.add(pib);
//		        }
//		    }
			respuesta = ESTADO_APROBADO;
		}
		return respuesta;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRAPROBSOLIMPSCNPAG initPeticion(final long numAcuerdo, final int numSubacuerdo){
		final Ejecutar.ITRAPROBSOLIMPSCNPAG peticion = new Ejecutar.ITRAPROBSOLIMPSCNPAG();

		super.initialize(peticion);


		final IPIMPSCNP ipimpscnp = peticion.getTRAPROBSOLIMPSCNPAGE().getIPIMPSCNP();
		ipimpscnp.setCODNRBEEN(this.getEntidad());
		ipimpscnp.setNUMSECAC(numAcuerdo);
		ipimpscnp.setNUMSUBAC(numSubacuerdo);

        //TODO esto esta correcto?
		peticion.getTRAPROBSOLIMPSCNPAGE().setCODINTERNOUO(this.getSucursal());
        //TODO esto esta correcto?
		peticion.getTRAPROBSOLIMPSCNPAGE().setFECHAVALOR(this.getFechaSistemaInteger());
        //TODO esto esta correcto?
        peticion.getTRAPROBSOLIMPSCNPAGE().setFECHAVTO(ConstantesFuncionales.MAX_FECHA_FIN_INTEGER);

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_APROB_SOL_IMPSCN_PAG_TRN);
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
	private EjecutarResult ejecutarWS(final Ejecutar.ITRAPROBSOLIMPSCNPAG contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
			        AprobacionPlazoServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de aprobacion "
					+ "de plazo.", e);
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
		if(response != null && response.getOTRAPROBSOLIMPSCNPAG() != null &&
			response.getOTRAPROBSOLIMPSCNPAG().getSTDAUTORIZAV()!= null){
			noNulo = true;
		}
		return noNulo;
	}

}
