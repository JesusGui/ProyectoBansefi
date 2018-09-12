package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cajas.beans.DiferenciaBean;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadiferencias.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadiferencias.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadiferencias.ConsultaDiferenciasServicio;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadiferencias.ResponseBansefi;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadiferencias.ResponseBansefi.OTRDIFOFSMLSTTRNO.TRDIFOFSMLSTEVTZ.SMSOLCTMONEDAE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDiferenciasBackend extends BackEndBean{

	private static final long serialVersionUID = 8711424375049309162L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	@Autowired
	CatalogoUtils catalogoUtils;
	
	public List<DiferenciaBean> ejecutarTRN(int fechaDesde, int fechaHasta){
		
		List<DiferenciaBean> listaDiferencias = new ArrayList<DiferenciaBean>();
		
		Ejecutar.ITRDIFOFSMLSTTRNI contexto = initPeticion(fechaDesde, fechaHasta);
		EjecutarResult resultado = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(resultado);
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		listaDiferencias = obtenerResultado(resultado.getResponseBansefi());
		
		return listaDiferencias;
	}
	
	private Ejecutar.ITRDIFOFSMLSTTRNI initPeticion(int fechaDesde, int fechaHasta){
		
		Ejecutar.ITRDIFOFSMLSTTRNI contexto = new Ejecutar.ITRDIFOFSMLSTTRNI();
		
		Ejecutar.ITRDIFOFSMLSTTRNI.TRDIFOFSMLSTEVTY nivel1_1 = new Ejecutar.ITRDIFOFSMLSTTRNI.TRDIFOFSMLSTEVTY();
		Ejecutar.ITRDIFOFSMLSTTRNI.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRDIFOFSMLSTTRNI.STDTRNIPARMV();
		
		Ejecutar.ITRDIFOFSMLSTTRNI.TRDIFOFSMLSTEVTY.SMFECHADESDEV nivel2_1 = new Ejecutar.ITRDIFOFSMLSTTRNI.TRDIFOFSMLSTEVTY.SMFECHADESDEV();
		Ejecutar.ITRDIFOFSMLSTTRNI.TRDIFOFSMLSTEVTY.SMFECHAHASTAV nivel2_2 = new Ejecutar.ITRDIFOFSMLSTTRNI.TRDIFOFSMLSTEVTY.SMFECHAHASTAV();
		Ejecutar.ITRDIFOFSMLSTTRNI.TRDIFOFSMLSTEVTY.SMSOLCTMONEDAP nivel2_3 = new Ejecutar.ITRDIFOFSMLSTTRNI.TRDIFOFSMLSTEVTY.SMSOLCTMONEDAP();
		
		nivel2_1.setSTDFECHA(fechaDesde);
		nivel1_1.setSMFECHADESDEV(nivel2_1);
		
		nivel2_2.setSTDFECHA(fechaHasta);
		nivel1_1.setSMFECHAHASTAV(nivel2_2);
		
		nivel2_3.setCODNRBEEN(super.getEntidad());
		nivel2_3.setCODINTERNOUO("");
		nivel2_3.setFECHASOLCTSM(0);
		nivel2_3.setCODPPL("");
		nivel2_3.setCODDISTRIB("");
		nivel2_3.setINDURG(0);
		nivel2_3.setCODINTERNOUO1("");
		nivel1_1.setSMSOLCTMONEDAP(nivel2_3);
		contexto.setTRDIFOFSMLSTEVTY(nivel1_1);
		
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(50);
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setIDEMPLAUT("");
		nivel1_2.setNUMSEC(0);
		nivel1_2.setCODTX("VCO31COU");
		nivel1_2.setCODTXDI("CO31");
		contexto.setSTDTRNIPARMV(nivel1_2);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRDIFOFSMLSTTRNI contexto){
		
		EjecutarResult resultado = null;
		
		try{
			resultado = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaDiferenciasServicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("No se pudo invocar el servicio de"
					+ " consulta diferencias.", nce);
		}
		return resultado;
	}
	
	private List<DiferenciaBean> obtenerResultado(ResponseBansefi responseBansefi){
		
		List<DiferenciaBean> listaDiferencias = new ArrayList<DiferenciaBean>();
		
		for (SMSOLCTMONEDAE desglose : responseBansefi.getOTRDIFOFSMLSTTRNO().getTRDIFOFSMLSTEVTZ().getSMSOLCTMONEDAE()){
			if(desglose.getIMPAUTV() != null && desglose.getIMPAUTV().compareTo(BigDecimal.ZERO) == 1){
				DiferenciaBean diferenciaBean = new DiferenciaBean();
				
				diferenciaBean.setEntidad(desglose.getCODNRBEEN());
				diferenciaBean.setCentrOrigen(desglose.getCODINTERNOUO());
				diferenciaBean.setFechaPeticion(desglose.getFECHASOLCTSM());
				diferenciaBean.setCodigoMoneda(desglose.getCODPPL());
				diferenciaBean.setCodigoDistribucion(catalogoUtils.getCatalogoBean(CatalogoEnum.CODIGO_DISTRIBUCION, desglose.getCODDISTRIB()));
				diferenciaBean.setNumeroUrgente(desglose.getINDURG());
				diferenciaBean.setCentroDesino(desglose.getCODINTERNOUO1());
				diferenciaBean.setCentroControlador(desglose.getCODINTERNOUO2());
				diferenciaBean.setEstatus(catalogoUtils.getCatalogoBean(CatalogoEnum.ESTATUS_CAJA_CENT, desglose.getCODECVSM()));
				diferenciaBean.setTotalAutorizado(desglose.getIMPAUTV());
				diferenciaBean.setTotalPedido(desglose.getIMPPEDIDOV());
				diferenciaBean.setTotalRecibido(desglose.getIMPRECBDOV());
				diferenciaBean.setTotalCertificado(desglose.getIMPCERTFDOV());
				diferenciaBean.setIdInternoEmpleado(desglose.getIDINTERNOEMPLEP());
				diferenciaBean.setObservaciones(desglose.getVALOROBSERSM());
				
				listaDiferencias.add(diferenciaBean);
			}
		}
		
		return listaDiferencias;
	}

}
