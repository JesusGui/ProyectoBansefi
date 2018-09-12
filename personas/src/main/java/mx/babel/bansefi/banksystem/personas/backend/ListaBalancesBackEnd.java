package mx.babel.bansefi.banksystem.personas.backend;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.personas.beans.BalanceBean;
import mx.babel.bansefi.banksystem.personas.webservices.listabalances.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.listabalances.Ejecutar.ITRDCBL1LSCNSTRNI.TRDCBL1LSCNSEVTY;
import mx.babel.bansefi.banksystem.personas.webservices.listabalances.Ejecutar.ITRDCBL1LSCNSTRNI.TRDCBL1LSCNSEVTY.PEPERSRLDOCP;
import mx.babel.bansefi.banksystem.personas.webservices.listabalances.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.listabalances.ListaBalancesServicio;
import mx.babel.bansefi.banksystem.personas.webservices.listabalances.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.webservices.listabalances.ResponseBansefi.OTRDCBL1LSCNSTRNO.TRDCBL1LSCNSEVTZ.TRDCBL1LSCNSEVTV;
import mx.babel.bansefi.banksystem.personas.wrappers.BalanceWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consultas de clientes de tipo balance.
 * 
 * @author babel
 *
 */
@Component
public class ListaBalancesBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -3273686134931993989L;
	
	@Autowired
	private ServicioWebUtils servicioWebUtils;
	
    @Autowired
	BalanceWrapper balanceWrapper;
	
	/**
	 * Función para obtener los balances de un cliente
	 * 
	 * @param idInterno Id interno del cliente
	 * @return Listado de balances del cliente
	 */
	public List<BalanceBean> ejecutarTRN(Integer idInterno){
		Ejecutar.ITRDCBL1LSCNSTRNI contexto = initPeticion(idInterno);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		List<BalanceBean> balances = null;
		if(verificaResponseBansefi(respuesta)){			
			balances = getBalances(respuesta.getResponseBansefi());
		}
		
		return balances;
	}
	
	/**
	 * Funciòn encargada de crear un objeto de tipo balance a través de la 
	 * respuesta obetenida del servicio web.
	 * 
	 * @param response Respuesta del servicio web
	 * @return Objeto de tipo balance
	 */
	private List<BalanceBean> getBalances(ResponseBansefi response){
		
		BalanceBean balBean  = null;
		List<BalanceBean> result = new ArrayList<BalanceBean>();
			
		List<TRDCBL1LSCNSEVTV> listaBalances = 
				response.getOTRDCBL1LSCNSTRNO().getTRDCBL1LSCNSEVTZ().getTRDCBL1LSCNSEVTV();
		
		for (TRDCBL1LSCNSEVTV bal : listaBalances) {
			if (bal.getIDINTERNODC() != 0) {
				balBean = balanceWrapper.wrappBeanListaBalance(bal);
				result.add(balBean);
			}
		}

		return result;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idExterno Id del cliente de tipo balance
	 * @return Objeto de petición al servicio web
	 */
	private Ejecutar.ITRDCBL1LSCNSTRNI initPeticion(Integer id){
		
		Ejecutar.ITRDCBL1LSCNSTRNI contexto = new Ejecutar.ITRDCBL1LSCNSTRNI();
		
		Ejecutar.ITRDCBL1LSCNSTRNI.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRDCBL1LSCNSTRNI.STDTRNIPARMV();
				
		// TODO: poner los atributos correctos
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_DC_BL_1_LS_CNS_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setIDEMPLAUT("");
		contexto.setSCROLLABLEOCCURS(BackEndBean.SCROLLABLE_OCCURS);
		contexto.setELEVATORPOSITION(0);
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		PEPERSRLDOCP peRequest = new PEPERSRLDOCP();
		peRequest.setIDINTERNOPE(id);
		peRequest.setCODNRBEEN(super.getEntidad());
		peRequest.setCODDOC(ConstantesFuncionales.COD_DOC_BALANCE);
		
		TRDCBL1LSCNSEVTY request = new TRDCBL1LSCNSEVTY();
		request.setPEPERSRLDOCP(peRequest);
		
		contexto.setTRDCBL1LSCNSEVTY(request);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRDCBL1LSCNSTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					ListaBalancesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "balances.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respues del servidor no este vacía. 
	 *  
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}

}
