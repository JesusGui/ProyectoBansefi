package mx.babel.bansefi.banksystem.base.backends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.GestorBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosgestor.ConsultaDatosGestorServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosgestor.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosgestor.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultadatosgestor.ResponseBansefi;

/**
 * Back End para el ws de consulta de datos de gestor.
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaDatosGestorBackEnd extends BackEndBean{

	private static final long serialVersionUID = -2876651346271278558L;
	
	private static final String INDICADOR_CIERTO = "S";

	@Autowired
    ServicioWebUtils servicioWebUtils;
    
	/**
	 * Función para obtener los datos de un gestor invocando un 
	 * servicio web.
	 * 
	 * @param idInterno Id interno del gestor.
	 * @return Objeto con atributos del gestor.
	 * @throws NoControlableException Excepción controlada de errores en front end
	 * @throws ControlableException Excepción no controlada de errores en host
	 */
    public GestorBean ejecutarTRN(Integer idInterno)throws NoControlableException, ControlableException{
		Ejecutar.ITRGTCONSGTGESTORTRN contexto = initPeticion(idInterno);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return null;
			}
		}
		
		return getGestor(respuesta.getResponseBansefi());
    }
    
	/**
	 * Funciòn encargada de crear un objeto de tipo grupo a través de la 
	 * respuesta obetenida del servicio web.
	 * 
	 * @param response Respuesta del servicio web
	 * @return Objeto de tipo grupo
	 */
	private GestorBean getGestor(ResponseBansefi response){
		GestorBean gestorBean  = null;		
		gestorBean = new GestorBean();
		IntegerToDateConverter itdc = new IntegerToDateConverter();
		if(verificaRespuestaGrupo(response)){
			ResponseBansefi.OTRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT gestor = 
				response.getOTRGTCONSGTGESTORTRN().getTRGTCONSGTGESTOREVT();
			gestorBean.setNombreGestor(gestor.getNOMBGT());
			gestorBean.setEstado(ConstantesFuncionales.estadosGestor().get(gestor.getCODECVGT()));
			gestorBean.setFechaAlta(itdc.convertTo(gestor.getFECHAALTAGT()));
			gestorBean.setGestorComercial(INDICADOR_CIERTO.equals(gestor.getINDGTCOMRCLGT()));
			gestorBean.setGestorContacto(INDICADOR_CIERTO.equals(gestor.getINDGTCONTCTGT()));
			gestorBean.setGestorSituacionEspecial(INDICADOR_CIERTO.equals(gestor.getINDGTSITESPCLGT()));
			gestorBean.setIndicadorExterno(gestor.getINDEXTRNINTRN());
		}
		return gestorBean;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idExterno Id del cliente de tipo grupo
	 * @return Objeto de petición al servicio web
	 */
	private Ejecutar.ITRGTCONSGTGESTORTRN initPeticion(Integer idInterno){
		Ejecutar.ITRGTCONSGTGESTORTRN contexto = new Ejecutar.ITRGTCONSGTGESTORTRN();
		Ejecutar.ITRGTCONSGTGESTORTRN.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRGTCONSGTGESTORTRN.STDTRNIPARMV();
		Ejecutar.ITRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT cuerpoContexto =
				new Ejecutar.ITRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT();
		Ejecutar.ITRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT.GTGESTORP gestor = 
				new Ejecutar.ITRGTCONSGTGESTORTRN.TRGTCONSGTGESTOREVT.GTGESTORP();
		
		gestor.setCODNRBEEN(super.getEntidad());
		gestor.setIDINTERNOPE(idInterno);
		
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_GT_CONS_GT_GESTOR_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRGTCONSGTGESTOREVT(cuerpoContexto);
		cuerpoContexto.setGTGESTORP(gestor);
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRGTCONSGTGESTORTRN contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaDatosGestorServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "datos de gestor.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * del grupo.
	 * 
	 * @param response Respuesta Bansefi con datos del grupo
	 * @return <code>true</code> si la respuesta Bansefi contiene un objeto de tipo grupo
	 */
	private Boolean verificaRespuestaGrupo(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRGTCONSGTGESTORTRN() != null &&
				response.getOTRGTCONSGTGESTORTRN().getTRGTCONSGTGESTOREVT() != null){
			noNulo = true;
		}
		return noNulo;
	}
}
