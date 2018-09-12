package mx.babel.bansefi.banksystem.base.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.TipoDomicilioUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultadomiciliospersona.ConsultaDomiciliosPersonaServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadomiciliospersona.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadomiciliospersona.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadomiciliospersona.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultadomiciliospersona.ResponseBansefi.OTRDRDMLSCNSTRNO.TRDRDMLSCNSEVTZ.TRDRDMLSCNSEVTV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para la consulta de domicilios asociados a una persona
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaDomiciliosPersonaBackEnd extends BackEndBean{

	private static final long serialVersionUID = 5925957347443685272L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	TipoDomicilioUtils tipoDomicilioUtils;
	
	/**
	 * Método para obtener los domicilios asociados a una persona por medio de un ws.
	 * @param idInterno Id de la persona a consultar
	 * @return lista de domicilios asociados a la persona
	 */
	public List<DomicilioTipoBean> ejecutarTRN(Integer idInterno, PaginacionBean paginacionBean){
		if(paginacionBean == null){
			paginacionBean = new PaginacionBean();
		}
		
		Ejecutar.ITRDRDMLSCNSTRNI contexto = initPeticion(idInterno, paginacionBean);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
			
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return new ArrayList<DomicilioTipoBean>();
			}
		}
		
		return getRespuesta(respuesta.getResponseBansefi(), paginacionBean,idInterno);
	}
	
	/**
	 * Método para construit una lista de domicilios a partir de la respuesta del ws
	 * @param respuesta Objeto respuesta del ws
	 * @return lista de domicilios asociados a una persona
	 */
	private List<DomicilioTipoBean>getRespuesta(ResponseBansefi respuesta, PaginacionBean paginacionBean,Integer idInterno){
		List<DomicilioTipoBean> domicilios = new ArrayList<DomicilioTipoBean>();
		
		paginacionBean.adicionaNumeroDatos(respuesta.getOTRDRDMLSCNSTRNO().getNUMBEROFRECORDS());
		paginacionBean.setMasDatos(respuesta.getOTRDRDMLSCNSTRNO().getMOREDATAIN() == 1);
		List<TRDRDMLSCNSEVTV> domiciliosRepsuesta = respuesta.getOTRDRDMLSCNSTRNO().getTRDRDMLSCNSEVTZ().getTRDRDMLSCNSEVTV();
		paginacionBean.setUltimoDatoConsultaAnterior(domiciliosRepsuesta.get(paginacionBean.getNumeroDatos()-1).getNUMDIR());
		if(respuesta.getOTRDRDMLSCNSTRNO() != null){
			for(int i = 0; i < respuesta.getOTRDRDMLSCNSTRNO().getNUMBEROFRECORDS(); i++){
				TRDRDMLSCNSEVTV domicilio = respuesta.getOTRDRDMLSCNSTRNO().getTRDRDMLSCNSEVTZ().getTRDRDMLSCNSEVTV().get(i);
				DomicilioTipoBean domicilioRef = null;
				int j = 0;
				while(domicilioRef == null && j < domicilios.size()){
					if(domicilios.get(j).getNumeroDireccion() == domicilio.getNUMDIR()){
						domicilioRef = domicilios.get(j);
					}
					j++;
				}
				if(domicilioRef != null){
					domicilioRef.getGrouping().add(tipoDomicilioUtils.getTipoDomicilio(domicilio.getCODPERSRLDIR().trim()));
				}else{
					DomicilioTipoBean domicilioTipoBean = new DomicilioTipoBean();
					domicilioTipoBean.setNumeroDireccion(domicilio.getNUMDIR());
					domicilioTipoBean.getGrouping().add(tipoDomicilioUtils.getTipoDomicilio(domicilio.getCODPERSRLDIR().trim()));
					domicilioTipoBean.setNombreCalle(domicilio.getNOMBREVIA());
					CatalogoGeoBean localidad = new CatalogoGeoBean();
					localidad.setNombreLocalidad(domicilio.getNOMBLOCALIDADAG());
					domicilioTipoBean.setCodigoPostalCatGeo(localidad);
					domicilios.add(domicilioTipoBean);
				}
			}
		}
		return domicilios;
	}
	
	/**
	 * Método para inicializar el objeto petición para el ws
	 * @param idInterno id de la persona a consultar
	 * @return objeto de petición al ws
	 */
	private Ejecutar.ITRDRDMLSCNSTRNI initPeticion(Integer idInterno, PaginacionBean paginacionBean){
		Ejecutar.ITRDRDMLSCNSTRNI contexto = new Ejecutar.ITRDRDMLSCNSTRNI();
		Ejecutar.ITRDRDMLSCNSTRNI.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRDRDMLSCNSTRNI.STDTRNIPARMV();
		Ejecutar.ITRDRDMLSCNSTRNI.TRDRDMLSCNSEVTY detalleConsulta = 
				new Ejecutar.ITRDRDMLSCNSTRNI.TRDRDMLSCNSEVTY();
		Ejecutar.ITRDRDMLSCNSTRNI.TRDRDMLSCNSEVTY.PEPERSRLDIRP persona =
				new Ejecutar.ITRDRDMLSCNSTRNI.TRDRDMLSCNSEVTY.PEPERSRLDIRP();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRDRDMLSCNSEVTY(detalleConsulta);
		detalleConsulta.setPEPERSRLDIRP(persona);
		super.initialize(contexto);
	
		contexto.setSCROLLABLEOCCURS(50);
		contexto.setELEVATORPOSITION(1);
		
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_DR_DM_LS_CNS_TRN);
		
		persona.setCODNRBEEN(super.getEntidad());
		persona.setIDINTERNOPE(idInterno);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRDRDMLSCNSTRNI peticion){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaDomiciliosPersonaServicio.class, peticion);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "domicilios de persona.", e);
		}
		return respuesta;
	}	
}
