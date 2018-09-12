package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.utils.FechaUtils;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadesglosediferencias1.ConsultaDesgloseDiferencias1Servicio;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadesglosediferencias1.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadesglosediferencias1.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadesglosediferencias1.ResponseBansefi;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadesglosediferencias1.ResponseBansefi.OTRDESGLSSMLSTTRNO.TRDESGLSSMLSTEVTZ.SMSOLCTDESGLSE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDesgloseDiferencias1Backend extends BackEndBean{
	
	private static final long serialVersionUID = -4617873198312860248L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	FechaUtils fechaUtils;
	
	public List<ExistenciaDenominacionBean> ejecutaTRN(String centroOrigen, int fechaSolicitud, 
			String codigoMoneda, String codigoDistribucion, int numeroUrgente, 
			String centroDestino) 
					throws ParseException{
		
		List<ExistenciaDenominacionBean> listaExistencias = new ArrayList<ExistenciaDenominacionBean>();
		
		Ejecutar.ITRDESGLSSMLSTTRNI contexto = initPeticion(centroOrigen, fechaSolicitud, codigoMoneda, codigoDistribucion, numeroUrgente, centroDestino);
		EjecutarResult resultado = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(resultado);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_OK) {
				throw ce;
			}
		}
		
		listaExistencias = obtenerDesglose(resultado.getResponseBansefi());
		
		return listaExistencias;
	}
	
	private Ejecutar.ITRDESGLSSMLSTTRNI initPeticion(String centroOrigen, int fechaSolicitud, String codigoMoneda, 
			String codigoDistribucion, int numeroUrgente, String centroDestino) 
					throws ParseException {
		
		Ejecutar.ITRDESGLSSMLSTTRNI contexto = new Ejecutar.ITRDESGLSSMLSTTRNI();
		Ejecutar.ITRDESGLSSMLSTTRNI.TRDESGLSSMLSTEVTY nivel1_1 = new Ejecutar.ITRDESGLSSMLSTTRNI.TRDESGLSSMLSTEVTY();
		Ejecutar.ITRDESGLSSMLSTTRNI.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRDESGLSSMLSTTRNI.STDTRNIPARMV();
		Ejecutar.ITRDESGLSSMLSTTRNI.STDAPPLPARMV nivel1_3 = new Ejecutar.ITRDESGLSSMLSTTRNI.STDAPPLPARMV();
		
		Ejecutar.ITRDESGLSSMLSTTRNI.TRDESGLSSMLSTEVTY.SMSOLCTDESGLSP nivel2_1 = new Ejecutar.ITRDESGLSSMLSTTRNI.TRDESGLSSMLSTEVTY.SMSOLCTDESGLSP();
		
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);
		
		nivel2_1.setCODNRBEEN(super.getEntidad());
		nivel2_1.setCODINTERNOUO(centroOrigen);
		nivel2_1.setFECHASOLCTSM(fechaSolicitud);
		nivel2_1.setCODPPL(codigoMoneda);
		nivel2_1.setCODDISTRIB(codigoDistribucion);
		nivel2_1.setINDURG(numeroUrgente);
		nivel2_1.setCODINTERNOUO1(centroDestino);
		nivel2_1.setCODDSTN("");
		nivel2_1.setCODVALORFACIAL("");
		nivel1_1.setSMSOLCTDESGLSP(nivel2_1);
		contexto.setTRDESGLSSMLSTEVTY(nivel1_1);
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setIDEMPLAUT("");
		nivel1_2.setNUMSEC(0);
		nivel1_2.setCODTX("VCM18COU");
		nivel1_2.setCODTXDI("CO28");
		contexto.setSTDTRNIPARMV(nivel1_2);
		
		nivel1_3.setCODNRBEEN(super.getEntidad());
		nivel1_3.setCODNRBEENFSC(super.getEntidad());
		nivel1_3.setCODINTERNOUO(super.getSucursal());
		nivel1_3.setCODINTERNOUOFSC(super.getSucursal());
		nivel1_3.setCODCSBOF(super.getSucursal());
		nivel1_3.setIDINTERNOEMPLEP(super.getUsuarioId());
		nivel1_3.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_3.setIDEMPLAUT("");
		nivel1_3.setNUMSEC(0);
		nivel1_3.setCODTX("VCM18COU");
		nivel1_3.setCODTXDI("CO28");
		nivel1_3.setFECHAOPRCN(0);
		nivel1_3.setHORAOPRCN(0);
		nivel1_3.setFECHACTBLE(super.getFechaSistemaInteger());
		nivel1_3.setCODIDIOMA("");
		nivel1_3.setCODISO("MXN");
		nivel1_3.setCODISOPAISAG("");
		nivel1_3.setBUFFER("");
		contexto.setSTDAPPLPARMV(nivel1_3);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRDESGLSSMLSTTRNI contexto){
		EjecutarResult resultado = null;
		
		try{
			resultado = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaDesgloseDiferencias1Servicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("No se pudo invocar el servicio de"
					+ " consulta de desglose de diferencias.", nce);
		}
		
		return resultado;
	}
	
	private List<ExistenciaDenominacionBean> obtenerDesglose(ResponseBansefi response){
		List<ExistenciaDenominacionBean> listaExistencias = new ArrayList<ExistenciaDenominacionBean>();
		for(SMSOLCTDESGLSE desglose : response.getOTRDESGLSSMLSTTRNO().getTRDESGLSSMLSTEVTZ().getSMSOLCTDESGLSE()){
			if(desglose.getIMPRECBDO() != null && desglose.getIMPRECBDO().compareTo(BigDecimal.ZERO) > 0){
				
				ExistenciaDenominacionBean existenciaDenominacionBean = new ExistenciaDenominacionBean();
				
				existenciaDenominacionBean.setOrigen(desglose.getCODDSTN());
				existenciaDenominacionBean.setValorFacial(desglose.getCODVALORFACIAL());
				existenciaDenominacionBean.setImporteAutorizado(desglose.getIMPAUT());
				existenciaDenominacionBean.setImportePedido(desglose.getIMPPEDIDO());
				existenciaDenominacionBean.setImporteRecibido(desglose.getIMPRECBDO());
				
				listaExistencias.add(existenciaDenominacionBean);
			}
		}
		
		return listaExistencias;
	}

}
 