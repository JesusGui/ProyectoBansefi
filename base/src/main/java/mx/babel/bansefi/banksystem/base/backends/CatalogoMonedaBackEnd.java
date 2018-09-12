package mx.babel.bansefi.banksystem.base.backends;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import mx.babel.arq.cache.annotation.Cache;
import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.webservices.catalogomoneda.CatalogoMonedaServicio;
import mx.babel.bansefi.banksystem.base.webservices.catalogomoneda.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.catalogomoneda.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.catalogomoneda.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.catalogomoneda.ResponseBansefi.OTRXMLSTMONEDATRNO.TRXMLSTMONEDAEVTZ.XMMONEDAE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CatalogoMonedaBackEnd extends BackEndBean {

	private static final long serialVersionUID = 5096372207755732919L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired 
	CatalogoUtils catalogoUtils;	

	@Cache
	public List<CatalogoBean> ejecutarTRN(String ultimaMoneda){
		Ejecutar.ITRXMLSTMONEDATRNI contexto = initPeticion(ultimaMoneda);
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		if (verificaResponseBansefi(respuesta)) {
			return catalogoMoneda(respuesta.getResponseBansefi());
		}
		return null;
	}

	private List<CatalogoBean> catalogoMoneda(ResponseBansefi response) {
		List<CatalogoBean> resultado = new CopyOnWriteArrayList<CatalogoBean>();
		if (verificaRespuestaCliente(response)) {
			 List<XMMONEDAE> monedas = response.getOTRXMLSTMONEDATRNO().getTRXMLSTMONEDAEVTZ().getXMMONEDAE();
			 for (XMMONEDAE moneda : monedas) {
				 CatalogoBean nuevaMoneda = new CatalogoBean();
				 nuevaMoneda.setClaveFila(moneda.getCODNUMRCOMONEDA().trim());
				 nuevaMoneda.setDescripcionC(moneda.getDESCRMONEDA().trim());
				 nuevaMoneda.setDescripcionL(moneda.getCODNUMRCOMONEDA().trim() + " - " + moneda.getDESCRMONEDA().trim());				 
				 resultado.add(nuevaMoneda);
			 }
			 if (!"".equals(resultado.get(resultado.size() - 1).getClaveFila().trim())) {
				 resultado.addAll(this.ejecutarTRN(resultado.get(resultado.size() - 1).getClaveFila()));
			 }
		}
		return resultado;
	}

	private boolean verificaRespuestaCliente(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null && response.getOTRXMLSTMONEDATRNO() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	private boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	private EjecutarResult ejecutarWS(Ejecutar.ITRXMLSTMONEDATRNI contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(CatalogoMonedaServicio.class, contexto);
		} catch (NoControlableException e) {
			throw new NoControlableException("Error al invocar servicio web catalogo de moneda", e);
		}
		return respuesta;
	}

	private Ejecutar.ITRXMLSTMONEDATRNI initPeticion(String ultimaMoneda) {
		Ejecutar.ITRXMLSTMONEDATRNI contexto = new Ejecutar.ITRXMLSTMONEDATRNI();
		Ejecutar.ITRXMLSTMONEDATRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRXMLSTMONEDATRNI.STDTRNIPARMV();
		Ejecutar.ITRXMLSTMONEDATRNI.TRXMLSTMONEDAEVTY.XMMONEDADESCY moneda = new Ejecutar.ITRXMLSTMONEDATRNI.TRXMLSTMONEDAEVTY.XMMONEDADESCY();
		
		super.initialize(contexto);
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CNS_INF_FINANC_TRNN);
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setNUMSEC(0);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		moneda.setCODNUMRCOMONEDA(ultimaMoneda);
		
		contexto.getTRXMLSTMONEDAEVTY().setXMMONEDADESCY(moneda);
		contexto.setSTDTRNIPARMV(contextoEntrada);

		
		return contexto;
	}
}
