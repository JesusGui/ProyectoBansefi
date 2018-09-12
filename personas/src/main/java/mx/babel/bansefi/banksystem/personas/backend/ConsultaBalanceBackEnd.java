package mx.babel.bansefi.banksystem.personas.backend;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.personas.beans.RegistroBalanceBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultabalance.ConsultaBalanceServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultabalance.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultabalance.Ejecutar.ITRCONSBLNCETRNI.TRCONSBLNCEEVTY;
import mx.babel.bansefi.banksystem.personas.webservices.consultabalance.Ejecutar.ITRCONSBLNCETRNI.TRCONSBLNCEEVTY.PEPERSRLDOCP;
import mx.babel.bansefi.banksystem.personas.webservices.consultabalance.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.consultabalance.ResponseBansefi;
import mx.babel.bansefi.banksystem.personas.webservices.consultabalance.ResponseBansefi.OTRCONSBLNCETRNO.TRCONSBLNCEEVTZ.DCBLLINEAV;
import mx.babel.bansefi.banksystem.personas.wrappers.BalanceWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consultas del detalle de los registros de un balance
 * 
 * @author babel
 *
 */
@Component
public class ConsultaBalanceBackEnd extends BackEndBean{
	
	private static final int SCROLLABLE_OCCURS = 160;
	
    private static final long serialVersionUID = -5784844653419859563L;
    
    @Autowired
    private ServicioWebUtils servicioWebUtils;
    
    @Autowired
	BalanceWrapper balanceWrapper;
	
	/**
	 * Función para obtener los datos de un cliente de tipo balance invocando un 
	 * servicio web.
	 * 
	 * @param idInterno Id interno del cliente
	 * @param codDocumento Codigo documento Balance
	 * @return Registro de balances del cliente de uno de sus balances
	 */
	public List<RegistroBalanceBean> ejecutarTRN(Integer idInterno, String codDocumento, Integer idInternoDoc){
		
		Ejecutar.ITRCONSBLNCETRNI contexto = initPeticion(idInterno, codDocumento, idInternoDoc);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		List<RegistroBalanceBean> balance = null;
		
		super.verificaRespuesta(respuesta);
		
		if(verificaResponseBansefi(respuesta)){
			balance = getBalance(respuesta.getResponseBansefi());
		}
		
		return balance;
	}
	
	/**
	 * Funciòn encargada de crear un objeto de tipo balance a través de la 
	 * respuesta obetenida del servicio web.
	 * 
	 * @param response Respuesta del servicio web
	 * @return Objeto de tipo balance
	 */
	private List<RegistroBalanceBean> getBalance(ResponseBansefi response)
			throws NoControlableException, ControlableException{
		
		RegistroBalanceBean balBean  = null;
		List<RegistroBalanceBean> result = new ArrayList<RegistroBalanceBean>();
		
		List<DCBLLINEAV> listaBalances = 
				response.getOTRCONSBLNCETRNO().getTRCONSBLNCEEVTZ().getDCBLLINEAV();
		
		for (DCBLLINEAV bal : listaBalances) {
			
			if (bal.getCODLINBLNCEORG()!=null && bal.getCODLINBLNCEORG().trim().length()>0) {
				balBean = new RegistroBalanceBean();
				balBean.setId(bal.getCODLINBLNCEORG().trim());
				balBean.setTexto(bal.getDESCRLINBLNCEOR()!=null ? bal.getDESCRLINBLNCEOR().trim():"");
				balBean.setPorcentaje(bal.getPCTLINBLNCEDC());
				balBean.setValor(bal.getIMPLINBLNCEDC());
				result.add(balBean);
			}
		}

		return result;
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idExterno Id del cliente de tipo balance
	 * @param codDoc ID del documento donde se almancena el balance
	 * @return Objeto de petición al servicio web
	 */
	private Ejecutar.ITRCONSBLNCETRNI initPeticion(Integer id, String codDoc, Integer idInternoDoc){
		
		Ejecutar.ITRCONSBLNCETRNI contexto = new Ejecutar.ITRCONSBLNCETRNI();
		
		Ejecutar.ITRCONSBLNCETRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.ITRCONSBLNCETRNI.STDTRNIPARMV();
		
		// TODO: poner los atributos correctos
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_CONS_BLNCE_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		contextoEntrada.setCODTXDI("");
		contextoEntrada.setIDEMPLAUT("");
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		contexto.setELEVATORPOSITION(0);
		
		contexto.setSTDTRNIPARMV(contextoEntrada);
		
		PEPERSRLDOCP peRequest = new PEPERSRLDOCP();
		peRequest.setIDINTERNOPE(id);
		peRequest.setCODDOC(codDoc);
		peRequest.setCODNRBEEN(super.getEntidad());
		peRequest.setIDINTERNODC(idInternoDoc);
		
		TRCONSBLNCEEVTY request = new TRCONSBLNCEEVTY();
		request. setPEPERSRLDOCP(peRequest);
		
		contexto.setTRCONSBLNCEEVTY(request);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSBLNCETRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					ConsultaBalanceServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta del "
					+ "balance.", e);
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
