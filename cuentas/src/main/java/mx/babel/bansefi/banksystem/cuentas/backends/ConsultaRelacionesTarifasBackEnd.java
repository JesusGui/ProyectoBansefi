package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.cache.annotation.Cache;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cuentas.beans.ProductoBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaRelacionBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionestarifas.ConsultaRelacionesTarifasServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionestarifas.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionestarifas.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionestarifas.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultarelacionestarifas.ResponseBansefi.OTRYFRLACACCNSTRNO.TRYFRLACACCNSEVTZ.YFRLACACLGE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaRelacionesTarifasBackEnd extends BackEndBean{

	private static final long serialVersionUID = -983394128539332277L;
	
	private static final String IND_OBLIGATORIA = "S";
	
	private static final int EVENT_CD = 3;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	CatalogoUtils catalogoUtils;
	
	@Cache
	public List<TarifaRelacionBean> ejecutarTRN(TarifaBean tarifaBean){
		List<TarifaRelacionBean> relaciones = new ArrayList<TarifaRelacionBean>();
		relaciones = ejecutarTRN(tarifaBean.getLinea(), tarifaBean.getGrupo());
		return relaciones;
	}
	
	public List<TarifaRelacionBean> ejecutarTRN(String linea, String grupo){
		List<TarifaRelacionBean> relacionTarifa = new ArrayList<TarifaRelacionBean>();
		
		Ejecutar.ITRYFRLACACCNSTRNI contexto = initPeticion(linea, grupo);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return relacionTarifa;
			}
		}
		
		return getTarifa(respuesta.getResponseBansefi(), linea, grupo);
	}
	
	private List<TarifaRelacionBean> getTarifa(ResponseBansefi respuesta, String linea, String grupo){
		List<TarifaRelacionBean> relacionTarifa = new ArrayList<TarifaRelacionBean>();		
		Map<String, TarifaRelacionBean> mapaCodigos = new HashMap<String, TarifaRelacionBean>();
		if(respuesta.getOTRYFRLACACCNSTRNO() != null && respuesta.getOTRYFRLACACCNSTRNO().getTRYFRLACACCNSEVTZ() != null
				&& respuesta.getOTRYFRLACACCNSTRNO().getTRYFRLACACCNSEVTZ().getYFRLACACLGE() != null){
			for(int i = 0; i < respuesta.getOTRYFRLACACCNSTRNO().getNUMBEROFRECORDS(); i++){
				YFRLACACLGE tarifa = respuesta.getOTRYFRLACACCNSTRNO().getTRYFRLACACCNSEVTZ().getYFRLACACLGE().get(i);
				
				if(!mapaCodigos.containsKey(tarifa.getCODRLACAC())){
					TarifaRelacionBean tarifaRelacionBean = new TarifaRelacionBean();
					tarifaRelacionBean.setCodigoRelacion(tarifa.getCODRLACAC());
					tarifaRelacionBean.setRequerido(IND_OBLIGATORIA.equals(tarifa.getINDOBLIG()));
					tarifaRelacionBean.setNombreRelacion(
							catalogoUtils.getCatalogoBean(CatalogoEnum.TP_RL_AC_AC, tarifa.getCODRLACAC())
							.getDescripcionL());
					mapaCodigos.put(tarifa.getCODRLACAC(), tarifaRelacionBean);
				}
				
				ProductoBean productoBean = new ProductoBean();
				productoBean.setLinea(tarifa.getCODLINEA1());
				productoBean.setGrupo(tarifa.getIDGRPPD1());
				
				mapaCodigos.get(tarifa.getCODRLACAC()).getProductos().add(productoBean);
			}
		}
		for (Map.Entry<String, TarifaRelacionBean> entry : mapaCodigos.entrySet()){
			relacionTarifa.add(entry.getValue());
		}
		return relacionTarifa;
	}
	
	private Ejecutar.ITRYFRLACACCNSTRNI initPeticion(String linea, String grupo){
		Ejecutar.ITRYFRLACACCNSTRNI contexto = new Ejecutar.ITRYFRLACACCNSTRNI();
		Ejecutar.ITRYFRLACACCNSTRNI.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRYFRLACACCNSTRNI.STDTRNIPARMV();
		Ejecutar.ITRYFRLACACCNSTRNI.TRYFRLACACCNSEVTY detalle =
				new Ejecutar.ITRYFRLACACCNSTRNI.TRYFRLACACCNSEVTY();
		Ejecutar.ITRYFRLACACCNSTRNI.TRYFRLACACCNSEVTY.YFRLACACLGP tarifa =
				new Ejecutar.ITRYFRLACACCNSTRNI.TRYFRLACACCNSEVTY.YFRLACACLGP();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRYFRLACACCNSEVTY(detalle);
		detalle.setYFRLACACLGP(tarifa);
		super.initialize(contexto);
		
		contexto.setSCROLLABLEOCCURS(BackEndBean.SCROLLABLE_OCCURS);
		contexto.setEVENTCD(EVENT_CD);
		
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_YF_RL_AC_AC_CNS_TRN);
		
		tarifa.setCODNRBEEN(ConstantesFuncionales.ENTIDAD_ADAN);
		tarifa.setCODLINEA(linea);
		tarifa.setIDGRPPD(grupo);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRYFRLACACCNSTRNI contexto) 
			throws NoControlableException{
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaRelacionesTarifasServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "relaciones tarifa-tarifa.", e);
		}
		return respuesta;
	}
	
}
