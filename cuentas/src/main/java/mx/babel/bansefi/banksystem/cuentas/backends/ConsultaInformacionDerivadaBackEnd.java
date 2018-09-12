package mx.babel.bansefi.banksystem.cuentas.backends;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.InformacionDerivadaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainformacionderivada.ConsultaInformacionDerivadaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainformacionderivada.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainformacionderivada.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainformacionderivada.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainformacionderivada.ResponseBansefi.OTRCONSUDERIVADASACTR.TRCONSUDERIVADASACEVT.ACINFDRVDE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainformacionderivada.ResponseBansefi.OTRCONSUDERIVADASACTR.TRCONSUDERIVADASACEVT.LIQTAEAPERV.LIQTAEAPERLSTV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainformacionderivada.ResponseBansefi.OTRCONSUDERIVADASACTR.TRCONSUDERIVADASACEVT.LIQTAEAPERV.LIQTAEAPERLSTV.LIQTRAMOINTV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainformacionderivada.ResponseBansefi.OTRCONSUDERIVADASACTR.TRCONSUDERIVADASACEVT.LIQTAEAPERV.LIQTAEAPERLSTV.LIQTRAMOINTV.LIQTRAMOINTLSTV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultainformacionderivada.ResponseBansefi.OTRCONSUDERIVADASACTR.TRCONSUDERIVADASACEVT.LIQTAEAPERV.LIQTAECOMISV;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaInformacionDerivadaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BackEnd para la consulta de información derivada de la cuenta
 * @author alejandro.villegas
 *
 */
@Component
public class ConsultaInformacionDerivadaBackEnd extends BackEndBean{	
	
	private static final long serialVersionUID = 4812895778631018910L;
	
	@Autowired
	ConsultaInformacionDerivadaWrapper consultaInformacionDerivadaWrapper;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	ContextoUtils contextoUtils;
	
	@Autowired
	CatalogoUtils catalogoUtils;
	
	public List<InformacionDerivadaBean> ejecutarTRN(CuentaBean cuentaBean){
				
		Ejecutar.ITRCONSUDERIVADASACTR contexto = this.initPeticion(cuentaBean);
		
		EjecutarResult respuesta = getResponse(contexto);
		
		super.verificaRespuesta(respuesta);
		
		return getInformacionDerivada(respuesta.getResponseBansefi());
	}
	
	
	/**
	 * Función para iniciar los atributos del objeto a peticion del WS
	 * @return Objeto a enviar al WS
	 */
	private Ejecutar.ITRCONSUDERIVADASACTR initPeticion(CuentaBean cuentaBean){
		Ejecutar.ITRCONSUDERIVADASACTR contexto = new Ejecutar.ITRCONSUDERIVADASACTR();
		Ejecutar.ITRCONSUDERIVADASACTR.TRCONSUDERIVADASACEVT TRCONSUDERIVADASACEVT = new Ejecutar.ITRCONSUDERIVADASACTR.TRCONSUDERIVADASACEVT();
		Ejecutar.ITRCONSUDERIVADASACTR.TRCONSUDERIVADASACEVT.ACACP acacp = new Ejecutar.ITRCONSUDERIVADASACTR.TRCONSUDERIVADASACEVT.ACACP();
		Ejecutar.ITRCONSUDERIVADASACTR.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRCONSUDERIVADASACTR.STDTRNIPARMV();
		
		stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_CONSU_DERIVADAS_AC_TRN);
		stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());			
			
		acacp.setCODNRBEEN(contextoUtils.getEntidad());
		acacp.setNUMSECAC(cuentaBean.getNumeroCuenta());
			
		TRCONSUDERIVADASACEVT.setACACP(acacp);
			
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
			
		contexto.setSTDTRNIPARMV(stdtrniparmv);
		contexto.setTRCONSUDERIVADASACEVT(TRCONSUDERIVADASACEVT);
			
		super.initialize(contexto);
			
		return contexto;
	}
	
	/**
	 * Función para innvocar al WS	
	 * @param contexto. Objeto que se envia a peticion del web service
	 * @return Respuesta del WS
	 */
	private EjecutarResult getResponse(Ejecutar.ITRCONSUDERIVADASACTR contexto) 
			throws NoControlableException{
		EjecutarResult respuesta = null;
		
		try{
			
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaInformacionDerivadaServicio.class, contexto);
					
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de informacion derivada.", e);
		}		
		
		return respuesta;
	}
	
	
	
	/**
	 * Funcón que añade la información derivada a una lista
	 * @return Lista con la información derivada de la cuenta
	 * @throws NoControlableException
	 * @throws ControlableException
	 */
	private List<InformacionDerivadaBean> getInformacionDerivada(ResponseBansefi response) 
			throws NoControlableException, ControlableException{
		
		List <InformacionDerivadaBean> informacionDerivada = new ArrayList<InformacionDerivadaBean>();
		String tae;
		
		InformacionDerivadaBean informacionBean;
		
		LIQTAECOMISV liqtaecomisv = response.getOTRCONSUDERIVADASACTR().getTRCONSUDERIVADASACEVT().getLIQTAEAPERV().getLIQTAECOMISV();
		if(liqtaecomisv.getTAE().compareTo(new BigDecimal("0")) > 0){
			informacionBean = new InformacionDerivadaBean();
			
			informacionBean.setDescripcionInformacion("TAE DE COMISION");
			informacionBean.setValor(String.valueOf(liqtaecomisv.getTAE()));
			informacionBean.setTipo("liqtaecomisv");
			
			informacionDerivada.add(informacionBean);
		}
		
		List<LIQTAEAPERLSTV> nivelUno = response.getOTRCONSUDERIVADASACTR().getTRCONSUDERIVADASACEVT().getLIQTAEAPERV().getLIQTAEAPERLSTV();
		for (LIQTAEAPERLSTV liqtaeaperlstv : nivelUno) {
			if(!liqtaeaperlstv.getCODFORAPLCTR().equals("")){
				List<LIQTRAMOINTV> nivelDos = liqtaeaperlstv.getLIQTRAMOINTV();
				for (LIQTRAMOINTV liqtramointv : nivelDos) {
					if(liqtramointv.getOCCURSNR() > 0){
						List<LIQTRAMOINTLSTV>nivelTres = liqtramointv.getLIQTRAMOINTLSTV();
						for (int i = 0; i < liqtramointv.getOCCURSNR(); i++){
							if(nivelTres.get(i).getNUMTRAMO() > 0){
								informacionBean = new InformacionDerivadaBean();

								if(nivelTres.get(i).getTAE().compareTo(new BigDecimal("0")) == 0){
									tae = "";
								} else {
									tae = String.valueOf((nivelTres.get(i).getTAE()));
								}
								
								informacionBean.setInformacionDerivada(liqtaeaperlstv.getIDPARMCD());
								informacionBean.setValor(tae);
								informacionBean.setTipo("liqtramointlstv");
								
								informacionDerivada.add(informacionBean);
							} else {
								informacionBean = new InformacionDerivadaBean();
								
								if(nivelTres.get(i).getTAE().compareTo(new BigDecimal("0")) == 0){
									tae = "";
								} else {
									tae = String.valueOf((nivelTres.get(i).getTAE()));
								}
									
								informacionBean.setInformacionDerivada(liqtaeaperlstv.getIDPARMCD());
								informacionBean.setValor(tae);
								informacionBean.setTipo("liqtramointlstv");
									
								informacionDerivada.add(informacionBean);
							}
						}//FIN FOR nivelTres
					}
				}//FIN FOR nivelDos
			}
		}//FIN FOR nivelUno
		
		List<ACINFDRVDE> informacion = response.getOTRCONSUDERIVADASACTR().getTRCONSUDERIVADASACEVT().getACINFDRVDE();
		for (ACINFDRVDE acinfdrvde : informacion) {
			if(!acinfdrvde.getCODNRBEEN().trim().equals("")){
				informacionDerivada.add(consultaInformacionDerivadaWrapper.wrappInformacionDerivada(acinfdrvde));
			}
		}
		
		return informacionDerivada;
	}
	
}
