package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadesglosediferencias2.Ejecutar;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadesglosediferencias2.EjecutarResult;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadesglosediferencias2.ConsultaDesgloseDiferencias2Servicio;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadesglosediferencias2.ResponseBansefi;
import mx.babel.bansefi.banksystem.cajas.webservice.consultadesglosediferencias2.ResponseBansefi.OTRDESGLSPETCNTRNO.TRDESGLSPETCNEVTZ.SMDESGLSPETCNLS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDesgloseDiferencias2Backend extends BackEndBean{
	
	private static final long serialVersionUID = 8450626546423308467L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	public List<ExistenciaDenominacionBean> ejecutarTRN(List<ExistenciaDenominacionBean> listaExistencias, String centroOrigen, String centroDestino, String codigoMoneda, String codigoDistribucion, int fecha, int numeroUrgencia){
		List<ExistenciaDenominacionBean> listaExistenciasFinal = new ArrayList<ExistenciaDenominacionBean>();
		
		Ejecutar.ITRDESGLSPETCNTRNI contexto = initPeticion(listaExistencias, centroOrigen, centroDestino, codigoMoneda, codigoDistribucion, fecha, numeroUrgencia);
		EjecutarResult resultado = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(resultado);
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_OK){
				throw ce;
			}
		}
		
		listaExistenciasFinal = obtenerParrilla(resultado.getResponseBansefi());
		
		return listaExistenciasFinal;
	}
	
	private Ejecutar.ITRDESGLSPETCNTRNI initPeticion(List<ExistenciaDenominacionBean> listaExistencias, String centroOrigen, String centroDestino, String codigoMoneda, String codigoDistribucion, int fecha, int numeroUrgencia){
		
		Ejecutar.ITRDESGLSPETCNTRNI contexto = new Ejecutar.ITRDESGLSPETCNTRNI();
		
		contexto.setEVENTCD(1);
		contexto.setELEVATORPOSITION(0);
		contexto.setNUMBEROFRECORDS(50);
		
		Ejecutar.ITRDESGLSPETCNTRNI.TRDESGLSPETCNEVTY nivel1_1 = new Ejecutar.ITRDESGLSPETCNTRNI.TRDESGLSPETCNEVTY();
		Ejecutar.ITRDESGLSPETCNTRNI.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRDESGLSPETCNTRNI.STDTRNIPARMV();
		Ejecutar.ITRDESGLSPETCNTRNI.STDLSTPARMV nivel1_3 = new Ejecutar.ITRDESGLSPETCNTRNI.STDLSTPARMV();
		
		Ejecutar.ITRDESGLSPETCNTRNI.TRDESGLSPETCNEVTY.SMSOLCTDESGLSE nivel2_1;
		
		for (ExistenciaDenominacionBean existenciaDenominacionBean : listaExistencias) {
			
			nivel2_1 = new Ejecutar.ITRDESGLSPETCNTRNI.TRDESGLSPETCNEVTY.SMSOLCTDESGLSE();
			
			nivel2_1.setCODNRBEEN(super.getEntidad());
			nivel2_1.setCODINTERNOUO(centroOrigen);
			nivel2_1.setFECHASOLCTSM(fecha);
			nivel2_1.setCODPPL(codigoMoneda);
			nivel2_1.setCODDISTRIB(codigoDistribucion);
			nivel2_1.setINDURG(numeroUrgencia);
			nivel2_1.setCODINTERNOUO1(centroDestino);
			nivel2_1.setCODDSTN(existenciaDenominacionBean.getOrigen());
			nivel2_1.setCODVALORFACIAL(existenciaDenominacionBean.getValorFacial());
			nivel2_1.setIMPAUT(existenciaDenominacionBean.getImporteAutorizado());
			nivel2_1.setIMPPEDIDO(existenciaDenominacionBean.getImportePedido());
			nivel2_1.setIMPRECBDO(existenciaDenominacionBean.getImporteRecibido());
			nivel2_1.setNUMPRECINTO("");
			
			nivel1_1.getSMSOLCTDESGLSE().add(nivel2_1);
		}
		
		contexto.setTRDESGLSPETCNEVTY(nivel1_1);
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setIDEMPLAUT("");
		nivel1_2.setNUMSEC(0);
		nivel1_2.setCODTX("VCM18COU");
		nivel1_2.setCODTXDI("");
		contexto.setSTDTRNIPARMV(nivel1_2);
		
		nivel1_3.setCOUNTREQIN(0);
		nivel1_3.setFIRSTREADIN(0);
		nivel1_3.setOCCURSNR(0);
		nivel1_3.setOPERACIONCONTINUA(0);
		nivel1_3.setSOLOCOUNTREQIN(0);
		nivel1_3.setSTARTRECNR(0);
		contexto.setSTDLSTPARMV(nivel1_3);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRDESGLSPETCNTRNI contexto){
		EjecutarResult resultado = null;
		
		try{
			resultado = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaDesgloseDiferencias2Servicio.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("No se pudo ejecutar el servicio de"
					+ "consulta desglose diferencias 2.", nce);
		}
		
		return resultado;
	}
	
	private List<ExistenciaDenominacionBean> obtenerParrilla(ResponseBansefi response){
		
		List<ExistenciaDenominacionBean> listaExistencias = new ArrayList<ExistenciaDenominacionBean>();
		
		for(SMDESGLSPETCNLS desglose : response.getOTRDESGLSPETCNTRNO().getTRDESGLSPETCNEVTZ().getSMDESGLSPETCNLS()){
			if(desglose.getCODDSTN() != null && !desglose.getCODDSTN().equals("")
					&& desglose.getIMPRECBDO() != null && desglose.getIMPRECBDO().compareTo(BigDecimal.ZERO) > 0){
				
				ExistenciaDenominacionBean existencia = new ExistenciaDenominacionBean();
				
				existencia.setValor(desglose.getVALORMONEDA());
				existencia.setMoneda(desglose.getSOPORTE().equals("M"));
				existencia.setFormato(desglose.getFORMATO());
				existencia.setOrigen(desglose.getCODDSTN());
				existencia.setImporteAutorizado(desglose.getIMPAUT());
				existencia.setImportePedido(desglose.getIMPPEDIDO());
				existencia.setImporteRecibido(desglose.getIMPRECBDO());
				existencia.setValorFacial(desglose.getCODVALORFACIAL());
				
				listaExistencias.add(existencia);
			}
		}
		
		return listaExistencias;
	}

}
