package mx.babel.bansefi.banksystem.oficina.backends;

import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.oficina.beans.ConsultaTraspasoBean;
import mx.babel.bansefi.banksystem.oficina.beans.TraspasoBean;
import mx.babel.bansefi.banksystem.oficina.webservices.consultatraspasos.ConsultaTraspasosServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.consultatraspasos.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.consultatraspasos.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.consultatraspasos.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.webservices.consultatraspasos.ResponseBansefi.OVVVTRASPASOSLISTMTRN.VVVTRASPASOSLISTMEVTZ.VVVTRASPASOST;
import mx.babel.bansefi.banksystem.oficina.wrappers.ArqueoPuestoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para consultar los traspasos relacionados a un puesto
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaTraspasosBackEnd extends BackEndBean{

	private static final long serialVersionUID = -3566532708023171514L;

	 @Autowired
	 ServicioWebUtils servicioWebUtils;
	 
	 @Autowired
	 ArqueoPuestoWrapper arqueoPuestoWrapper;
	    
    /**
	 * Método para obtener los traspasos relacionados a un puesto haciendo uso de un ws
	 * @param puesto Puesto del cual se obtendran los traspasos relacionados
	 * @return lista de traspasos relacionados al puesto
	 */
	public Boolean ejecutarTRN(ConsultaTraspasoBean traspasoBean){
		return ejecutarTRN(traspasoBean, false);
	}
	
	public Boolean ejecutarTRN(ConsultaTraspasoBean traspasoBean, Boolean neto){
		Ejecutar.IVVVTRASPASOSLISTMTRN contexto = initPeticion(traspasoBean);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				if(!neto){
					return false;
				}
			}
		}
		
		if(verificaResponse(respuesta)){
			 return getTraspasos(traspasoBean, respuesta.getResponseBansefi());
		}
		
		return false;
	}
	
	/**
	 * Método para construir lista de traspasos a partir de un objeto de respuesta del ws
	 * @param respuesta Objeto de respuesta del ws
	 * @return lista de traspasos
	 */
	private Boolean getTraspasos(ConsultaTraspasoBean traspasosBean, ResponseBansefi respuesta){
		if(respuesta.getOVVVTRASPASOSLISTMTRN() != null 
				&& respuesta.getOVVVTRASPASOSLISTMTRN().getVVVTRASPASOSLISTMEVTZ() != null
				&& respuesta.getOVVVTRASPASOSLISTMTRN().getVVVTRASPASOSLISTMEVTZ().getPCTERMINALT() != null){
			traspasosBean.setSaldoInicial(respuesta.getOVVVTRASPASOSLISTMTRN().getVVVTRASPASOSLISTMEVTZ().getPCTERMINALT().getIMPINI());
			traspasosBean.setNetoTraspaso(respuesta.getOVVVTRASPASOSLISTMTRN().getVVVTRASPASOSLISTMEVTZ().getPCTERMINALT().getIMPINI());
		}
		
		if(respuesta.getOVVVTRASPASOSLISTMTRN() != null && respuesta.getOVVVTRASPASOSLISTMTRN().getVVVTRASPASOSLISTMEVTZ() != null &&
				respuesta.getOVVVTRASPASOSLISTMTRN().getVVVTRASPASOSLISTMEVTZ().getVVVTRASPASOST() != null){
			traspasosBean.setMasDatos(respuesta.getOVVVTRASPASOSLISTMTRN().getMOREDATAIN() == 1);
			//TODO remover cuando la paginación de HOST funcione
			traspasosBean.setMasDatos(false);
			traspasosBean.adicionaNumeroDatos(respuesta.getOVVVTRASPASOSLISTMTRN().getNUMBEROFRECORDS());
			List<VVVTRASPASOST> traspasos = respuesta.getOVVVTRASPASOSLISTMTRN().getVVVTRASPASOSLISTMEVTZ().getVVVTRASPASOST();
			if(respuesta.getOVVVTRASPASOSLISTMTRN().getNUMBEROFRECORDS() > 0){
				traspasosBean.setUltimoDatoConsultaAnterior(traspasos.get(respuesta.getOVVVTRASPASOSLISTMTRN().getNUMBEROFRECORDS()-1));
			}
			for(int i = 0; i < respuesta.getOVVVTRASPASOSLISTMTRN().getNUMBEROFRECORDS();i++){
				TraspasoBean traspasoBean = arqueoPuestoWrapper.wrappBean(traspasos.get(i));
				traspasoBean.setUsuarioOrigen(traspasos.get(i).getIDINTERNOEMPLEP());
				traspasosBean.getTraspasos().add(traspasoBean);
			}
		}
		return true;
	}
	
	/**
	 * Método para inicializar el objeto de petición al ws
	 * @param puesto Número de puesto a consultar
	 * @return objeto de petición al ws
	 */
	private Ejecutar.IVVVTRASPASOSLISTMTRN initPeticion(ConsultaTraspasoBean traspaso){
		Ejecutar.IVVVTRASPASOSLISTMTRN contexto = new Ejecutar.IVVVTRASPASOSLISTMTRN();
		Ejecutar.IVVVTRASPASOSLISTMTRN.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.IVVVTRASPASOSLISTMTRN.STDTRNIPARMV();
		Ejecutar.IVVVTRASPASOSLISTMTRN.VVVTRASPASOSLISTMEVTY puesto = 
				new Ejecutar.IVVVTRASPASOSLISTMTRN.VVVTRASPASOSLISTMEVTY();
		Ejecutar.IVVVTRASPASOSLISTMTRN.VVVTRASPASOSLISTMEVTY.PCTERMINALP detallePuesto = 
				new Ejecutar.IVVVTRASPASOSLISTMTRN.VVVTRASPASOSLISTMEVTY.PCTERMINALP();
		Ejecutar.IVVVTRASPASOSLISTMTRN.VVVTRASPASOSLISTMEVTY.VVVTRASPASOSLSCB filtros =
				new Ejecutar.IVVVTRASPASOSLISTMTRN.VVVTRASPASOSLISTMEVTY.VVVTRASPASOSLSCB();
		Ejecutar.IVVVTRASPASOSLISTMTRN.VVVTRASPASOSLISTMEVTY.VVVTRASPASOSP plazaPuesto =
				new Ejecutar.IVVVTRASPASOSLISTMTRN.VVVTRASPASOSLISTMEVTY.VVVTRASPASOSP();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setVVVTRASPASOSLISTMEVTY(puesto);
		puesto.setPCTERMINALP(detallePuesto);
		puesto.setVVVTRASPASOSLSCB(filtros);
		puesto.setVVVTRASPASOSP(plazaPuesto);
		super.initialize(contexto);
		
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		
		plazaPuesto.setCODNRBEENFSC(super.getEntidad());
		plazaPuesto.setCODINTERNOUOFSC(super.getSucursal());
		plazaPuesto.setNUMPUESTO(traspaso.getPuesto());
		
		IntegerToDateConverter itdc = new IntegerToDateConverter();
		filtros.setFECHADESDE(itdc.convertFrom(traspaso.getDesde()));
		filtros.setFECHAHASTA(itdc.convertFrom(traspaso.getHasta()));
		
		detallePuesto.setCODINTERNOUOFSC(super.getSucursal());
		detallePuesto.setCODNRBEENFSC(super.getEntidad());
		detallePuesto.setIDINTERNOTERMTN(super.getTerminal());
		
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_VVV_TRASPASOS_LISTM_TRN);
		
		return contexto;
	}
	
	/**
	 * Método para ejecutar el ws y recibir su respuesta
	 * @param contexto Objeto de petición al ws
	 * @return Objeto de respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.IVVVTRASPASOSLISTMTRN contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaTraspasosServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de traspasos.", e);
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
