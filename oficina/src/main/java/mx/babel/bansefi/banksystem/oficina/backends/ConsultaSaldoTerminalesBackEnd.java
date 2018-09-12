package mx.babel.bansefi.banksystem.oficina.backends;

import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.oficina.beans.ConsultaSaldosBean;
import mx.babel.bansefi.banksystem.oficina.beans.SaldoTerminalBean;
import mx.babel.bansefi.banksystem.oficina.webservices.consultasaldos.ConsultaSaldosServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.consultasaldos.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.consultasaldos.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.consultasaldos.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.webservices.consultasaldos.ResponseBansefi.OUOTERMCNS2MLISTTRN.UOTERMCNS2MLISTEVTZ.PCTERMINALT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para consultar los saldos netos en una sucursal
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaSaldoTerminalesBackEnd extends BackEndBean{

	private static final long serialVersionUID = 1128887250700122502L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
    /**
	 * Método para obetener los saldos en un centro a partir de un ws
	 * @param centro Número de centro a consultar
	 * @return lista de saldos por terminal
	 */
	public Boolean ejecutarTRN(ConsultaSaldosBean centro){
		
		Ejecutar.IUOTERMCNS2MLISTTRN contexto = initPeticion(centro);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return false;
			}
		}
		
		if(verificaResponse(respuesta)){
			return getSaldos(centro, respuesta.getResponseBansefi());
		}
		
		return false;
	}
	
	/**
	 * Método para construir una lista de saldos a partir de la respuesta del ws
	 * @param respuesta Objeto de respuesta del ws
	 * @return lista de saldos
	 */
	private Boolean getSaldos(ConsultaSaldosBean centro, ResponseBansefi respuesta){
		Boolean exito = false;		
		if(respuesta.getOUOTERMCNS2MLISTTRN() != null && respuesta.getOUOTERMCNS2MLISTTRN().getUOTERMCNS2MLISTEVTZ() != null
				&& respuesta.getOUOTERMCNS2MLISTTRN().getUOTERMCNS2MLISTEVTZ().getPCTERMINALT() != null){
			centro.setMasDatos(respuesta.getOUOTERMCNS2MLISTTRN().getMOREDATAIN() == 1);
			centro.adicionaNumeroDatos(respuesta.getOUOTERMCNS2MLISTTRN().getNUMBEROFRECORDS());
			List<PCTERMINALT> saldos = respuesta.getOUOTERMCNS2MLISTTRN().getUOTERMCNS2MLISTEVTZ().getPCTERMINALT();
			centro.setUltimoDatoConsultaAnterior(saldos.get(respuesta.getOUOTERMCNS2MLISTTRN().getNUMBEROFRECORDS()-1).getIDINTERNOTERMTN());
			for(int i = 0; i < respuesta.getOUOTERMCNS2MLISTTRN().getNUMBEROFRECORDS(); i++){
				SaldoTerminalBean saldoTerminalBean = new SaldoTerminalBean();
				saldoTerminalBean.setPuesto(saldos.get(i).getNUMPUESTO());
				saldoTerminalBean.setTerminal(saldos.get(i).getIDINTERNOTERMTN());
				saldoTerminalBean.setSaldoNeto(saldos.get(i).getIMPINI());
				centro.getSaldos().add(saldoTerminalBean);
			}
			exito = true;
		}
		return exito;
	}
	
	/**
	 * Método para inicializar el objeto de petición al ws
	 * @param puesto Número de centro a consultar
	 * @return objeto de petición al ws
	 */
	private Ejecutar.IUOTERMCNS2MLISTTRN initPeticion(ConsultaSaldosBean centro){
		Ejecutar.IUOTERMCNS2MLISTTRN contexto = new Ejecutar.IUOTERMCNS2MLISTTRN();
		Ejecutar.IUOTERMCNS2MLISTTRN.STDTRNIPARMV cuerpoContexto =
				new Ejecutar.IUOTERMCNS2MLISTTRN.STDTRNIPARMV();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		super.initialize(contexto);
		
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		
		if(centro.getUltimoDatoConsultaAnterior() != null){
			contexto.getUOTERMCNS2MLISTEVTY().getPCTERMINALP().setIDINTERNOTERMTN((String)centro.getUltimoDatoConsultaAnterior());
		}
		
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_UO_TERM_CNS_2M_LIST_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		
		return contexto;
	}
	
	/**
	 * Método para ejecutar el ws y obtener su respuesta
	 * @param contexto objeto de petición al ws
	 * @return objeto de respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IUOTERMCNS2MLISTTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaSaldosServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de saldos de centro.", e);
		}
		return respuesta;
	}
	
	/**
	 * Método para verificar la respuesta del ws
	 * @param respuesta del ws
	 * @return <code>true</code> si la respuesta no esta vacía
	 */
	private Boolean verificaResponse(EjecutarResult respuesta){
		if(respuesta.getResponseBansefi() != null){
			return true;
		}
		return false;
	}
}
