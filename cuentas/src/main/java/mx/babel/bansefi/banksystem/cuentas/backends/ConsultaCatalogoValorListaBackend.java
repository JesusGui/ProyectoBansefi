package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.PreferenceItemBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacatalogovalorlista.ConsultaCatalogoValorListaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacatalogovalorlista.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacatalogovalorlista.Ejecutar.ITRKPCNSLSTSMPLTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacatalogovalorlista.Ejecutar.ITRKPCNSLSTSMPLTRNI.TRKPCNSLSTSMPLEVTY.KPCODCLAVEV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacatalogovalorlista.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacatalogovalorlista.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacatalogovalorlista.ResponseBansefi.OTRKPCNSLSTSMPLTRNO.TRKPCNSLSTSMPLEVTZ.KPLISDOMSMPLV.KPLISTASMPLV;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author joseluis.pena
 *
 */
@Component
public class ConsultaCatalogoValorListaBackend extends BackEndBean{

	private static final long serialVersionUID = 6564921993348101257L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

	public List<PreferenceItemBean> ejecutarTRN(final String idPds, final String idCcps, final String idParmCd){
		
		final Ejecutar.ITRKPCNSLSTSMPLTRNI request = initPeticion(idPds, idCcps, idParmCd);

		final EjecutarResult respuesta = ejecutarWS(request);

		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return new ArrayList<PreferenceItemBean>();
            }
        }
		
		return getCatalogo(respuesta.getResponseBansefi());
	}


	private List<PreferenceItemBean> getCatalogo(final ResponseBansefi response){
	    final List<PreferenceItemBean> respuesta = new ArrayList<>();		
		if(verificaRespuesta(response)){
		    for(final KPLISTASMPLV kplistasmplv : response.getOTRKPCNSLSTSMPLTRNO().getTRKPCNSLSTSMPLEVTZ().getKPLISDOMSMPLV().getKPLISTASMPLV()){
		        if(StringUtils.isNoneBlank(kplistasmplv.getCODDOMPARMCD(), kplistasmplv.getIDPARMCD1())){
		            final PreferenceItemBean pib = new PreferenceItemBean();
		            pib.setId(kplistasmplv.getCODDOMPARMCD().trim());
		            pib.setDesc(kplistasmplv.getIDPARMCD1().trim());
		            pib.setPreferente(StringUtils.equals("1", kplistasmplv.getDOMINDPREF()));
		            respuesta.add(pib);
		        }
		    }
		}
		return respuesta;
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 *
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRKPCNSLSTSMPLTRNI initPeticion(final String idPds, final String idCcps, final String idParmCd){
		final Ejecutar.ITRKPCNSLSTSMPLTRNI peticion = new Ejecutar.ITRKPCNSLSTSMPLTRNI();

		super.initialize(peticion);
		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();


		final KPCODCLAVEV kpcodclavev = peticion.getTRKPCNSLSTSMPLEVTY().getKPCODCLAVEV();
		kpcodclavev.setCODNRBEEN(this.getEntidad());

		kpcodclavev.setIDPDS(idPds);

		kpcodclavev.setIDCCPS(idCcps);

		kpcodclavev.setIDPARMCD(idParmCd);

		kpcodclavev.setCODESTRCTPARMCD(ConstantesFuncionales.CATALOGO_TIPO_CONDICION.LISTA.getId());

		//TODO esta fecha es correcta?
		peticion.getTRKPCNSLSTSMPLEVTY().getKPFECHAV().setFECHAENT(itdConverter.convertFrom(this.getFechaSistema()));

        final STDTRNIPARMV stdtrniparmv = peticion.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_KP_CNS_LST_SMPL_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
		return peticion;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 *
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(final Ejecutar.ITRKPCNSLSTSMPLTRNI contexto)
			throws NoControlableException{
		EjecutarResult respuesta = null;

		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCatalogoValorListaServicio.class, contexto);
		}catch(final NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de catalogo Valor-Lista.", e);
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
		if(response != null && response.getOTRKPCNSLSTSMPLTRNO() != null &&
			response.getOTRKPCNSLSTSMPLTRNO().getTRKPCNSLSTSMPLEVTZ()!= null &&
			response.getOTRKPCNSLSTSMPLTRNO().getTRKPCNSLSTSMPLEVTZ().getKPLISDOMSMPLV() != null &&
			response.getOTRKPCNSLSTSMPLTRNO().getTRKPCNSLSTSMPLEVTZ().getKPLISDOMSMPLV().getKPLISTASMPLV()!= null &&
			!response.getOTRKPCNSLSTSMPLTRNO().getTRKPCNSLSTSMPLEVTZ().getKPLISDOMSMPLV().getKPLISTASMPLV().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}

}
