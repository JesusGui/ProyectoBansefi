package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.FinalidadBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarlistafinalidad.ConsultarListaFinalidad;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarlistafinalidad.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarlistafinalidad.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarlistafinalidad.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarlistafinalidad.ResponseBansefi.OTRLISTARLTVFINTRNO.TRLISTARLTVFINEVTZ.TRLISTARLTVFINEVTV;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultarListaFinalidadWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaFinalidadListaBackEnd extends BackEndBean {

	private static final long serialVersionUID = 4043242942917760788L;
	
	@Autowired
	ConsultarListaFinalidadWrapper consultarListaFinalidadWrapper;
		
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	private final int SCROLLABLE_OCCURS = 50;
	
	public List<FinalidadBean> ejecutarTRN(String linea, String grupo, String productoVendible, String tarifaVendible){

		Ejecutar.ITRLISTARLTVFINTRNI contexto = this.initPeticion(linea, grupo, productoVendible, tarifaVendible);
		EjecutarResult respuesta = this.ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch(ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return new ArrayList<FinalidadBean>();
			}
			
		}		
		
		return getRespuesta(respuesta.getResponseBansefi());
	}
	
	private List<FinalidadBean> getRespuesta(ResponseBansefi response){
		List<FinalidadBean> respuesta = new ArrayList<FinalidadBean>();		
		if(verificaRespuesta(response)){
			for(TRLISTARLTVFINEVTV trlistarltvfinevtv : response.getOTRLISTARLTVFINTRNO().getTRLISTARLTVFINEVTZ().getTRLISTARLTVFINEVTV()){
				if(StringUtils.isNoneBlank(trlistarltvfinevtv.getCODFNDADAC())){
					respuesta.add(consultarListaFinalidadWrapper.wrappListaFinalidad(trlistarltvfinevtv));
				}
			}
		}
		return respuesta;
	}
	
	private Ejecutar.ITRLISTARLTVFINTRNI initPeticion(String linea, String grupo, String productoVendible, String tarifaVendible){
		Ejecutar.ITRLISTARLTVFINTRNI contexto = new Ejecutar.ITRLISTARLTVFINTRNI();
		Ejecutar.ITRLISTARLTVFINTRNI.TRLISTARLTVFINEVTY trlistarltvfinevty = new Ejecutar.ITRLISTARLTVFINTRNI.TRLISTARLTVFINEVTY();
		Ejecutar.ITRLISTARLTVFINTRNI.TRLISTARLTVFINEVTY.TVTIPOENTRADAV tvtipoentradav= new Ejecutar.ITRLISTARLTVFINTRNI.TRLISTARLTVFINEVTY.TVTIPOENTRADAV();
		Ejecutar.ITRLISTARLTVFINTRNI.TRLISTARLTVFINEVTY.TVTVRLFNDADP tvtvrlfndadp = new Ejecutar.ITRLISTARLTVFINTRNI.TRLISTARLTVFINEVTY.TVTVRLFNDADP();
		Ejecutar.ITRLISTARLTVFINTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRLISTARLTVFINTRNI.STDTRNIPARMV();
		
		//tvtvrlfndadp.setCODFNDADAC(value);
		tvtvrlfndadp.setCODLINEA(linea);
		tvtvrlfndadp.setCODNRBEEN(getEntidad());
		tvtvrlfndadp.setIDGRPPD(grupo);
		tvtvrlfndadp.setIDPDV(productoVendible);
		tvtvrlfndadp.setIDTRFAPDV(tarifaVendible);
		
		//tvtipoentradav.setACTNCD(null);
		//tvtipoentradav.setNOMBPDV(null);
		//tvtipoentradav.setNOMBTRFAPDV(null);
		
		stdtrniparmv.setCODTX(CodTxConstants.COD_TR_LISTA_RL_TV_FIN_TRN);
		//stdtrniparmv.setCODTXDI("");
		//stdtrniparmv.setIDEMPLAUT("");
		stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
		//stdtrniparmv.setNUMSEC(0);
		
		trlistarltvfinevty.setTVTIPOENTRADAV(tvtipoentradav);
		trlistarltvfinevty.setTVTVRLFNDADP(tvtvrlfndadp);
				
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		contexto.setTRLISTARLTVFINEVTY(trlistarltvfinevty);
		contexto.setSTDTRNIPARMV(stdtrniparmv);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRLISTARLTVFINTRNI contexto) throws NoControlableException{
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultarListaFinalidad.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
                    + "entidades.", e);			
		}
		return respuesta;
	}	
	
	private Boolean verificaRespuesta(final ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRLISTARLTVFINTRNO() != null &&
				response.getOTRLISTARLTVFINTRNO().getTRLISTARLTVFINEVTZ() != null){
			noNulo = true;
			
		}
		return noNulo;
	}

}
