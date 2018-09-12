package mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros;

import java.io.Serializable;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.beans.centro.AltaCentroBean;
import mx.babel.bansefi.banksystem.administracion.webservices.altacentro.AltaCentroServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.altacentro.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.altacentro.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.wrappers.VentanaCentroWrapper;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase Backend para el Alta de Centros.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class AltaCentroBackend extends BackEndBean implements Serializable {

	private static final long serialVersionUID = 4339240640772517782L;

	@Autowired
	private VentanaCentroWrapper wrapperBeanService;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	/**
	 * Metodo para ejecutar el servicio de alta de centros.
	 * 
	 * @param centroBean
	 *            Bean con los datos a enviar al servicio.
	 * @return int con valor de codigo de mensaje OK.
	 */
	public int ejecutarTRN(AltaCentroBean centroBean){
		Ejecutar.ITRALTACENTROTRNI contextoEntrada = new Ejecutar.ITRALTACENTROTRNI();

		Ejecutar.ITRALTACENTROTRNI.STDTRNIPARMV contextoComun = new Ejecutar.ITRALTACENTROTRNI.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("GCS04MON");
		contextoComun.setCODTXDI("");

		Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY contextoEntradaCampos = null;
		Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV contextoEntradaCampos2 = null;
		contextoEntradaCampos = wrapperBeanService.wrappBeanAltaCentro1(centroBean);

		contextoEntradaCampos.setINDCENTFICTICUO("N");
		contextoEntradaCampos.setCODECVCENT("1");
		contextoEntradaCampos.setNIVJERARUO("");
		contextoEntradaCampos.setINDDEPENCTBLEUO("N");
		contextoEntradaCampos.setINDAPERTURAUO("S");
		contextoEntradaCampos.setINDCSBUO("N");

		contextoEntradaCampos2 = wrapperBeanService.wrappBeanAltaCentroDomicilio(centroBean.getDomicilio());

		contextoEntradaCampos2.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos2.setCODDIR("1");
		contextoEntradaCampos2.setNOMBCORREO("");
		contextoEntradaCampos2.setPREFTLFNODOMIC("");
		contextoEntradaCampos2.setINDMASDOMIC("S");
		contextoEntradaCampos2.setCODPAISAG(centroBean.getDomicilio().getCodigoPostalCatGeo().getCodigoPais());
		contextoEntradaCampos2.setCODPROVINCIAAG(centroBean.getDomicilio().getCodigoPostalCatGeo().getCodigoProvincia());
		contextoEntradaCampos2.setNOMBPROVINCIAAG(centroBean.getDomicilio().getCodigoPostalCatGeo().getNombreProvincia());
		contextoEntradaCampos2.setCODCOMAUTNMAAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getCodigoComunidadAutonoma());

		Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV codDireccion = new Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV();
		codDireccion.setCODPERSRLDIR("02");
		contextoEntradaCampos2.getCODPERSRLDIRV().add(codDireccion);
		Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV codDireccion1 = new Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV();
		codDireccion1.setCODPERSRLDIR("03");
		contextoEntradaCampos2.getCODPERSRLDIRV().add(codDireccion1);
		Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV codDireccion2 = new Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV();
		codDireccion2.setCODPERSRLDIR("");
		contextoEntradaCampos2.getCODPERSRLDIRV().add(codDireccion2);
		Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV codDireccion3 = new Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV();
		codDireccion3.setCODPERSRLDIR("");
		contextoEntradaCampos2.getCODPERSRLDIRV().add(codDireccion3);

		Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.CODARGEODOMICILIOV codArGeo = new Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.CODARGEODOMICILIOV();
		codArGeo.setCODARGEO("07");
		contextoEntradaCampos2.setCODARGEODOMICILIOV(codArGeo);

		Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.NUMARGEODOMICILIOV numArGeo = new Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.NUMARGEODOMICILIOV();
		numArGeo.setNUMARGEO(Integer.parseInt(centroBean.getDomicilio().getCodigoPostalCatGeo().getNumArGeo()));
		contextoEntradaCampos2.setNUMARGEODOMICILIOV(numArGeo);

		Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.DRELCTRV drelctrv = new Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.DRELCTRV();
		drelctrv.setCODARGEO("");
		drelctrv.setNUMARGEO(0);
		contextoEntradaCampos2.setDRELCTRV(drelctrv);
		contextoEntradaCampos.setDRDMDPOBJTRDV(contextoEntradaCampos2);

		Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.PECOMPARTIRDOMICOBJTR contextoVacioCampos = new Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.PECOMPARTIRDOMICOBJTR();
		contextoVacioCampos.setCODNRBEEN("");
		contextoVacioCampos.setIDINTERNOPE(0);
		contextoVacioCampos.setIDINTERNOBI(0);
		contextoVacioCampos.setNUMDIR(0);
		contextoVacioCampos.setCODDIR("");
		contextoVacioCampos.setVALORELCTRDR("");
		contextoVacioCampos.setVALORELCTRDRLEN(0);

		Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.PECOMPARTIRDOMICOBJTR.CODPERSRLDIRV campoVacioCodPers = new Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.PECOMPARTIRDOMICOBJTR.CODPERSRLDIRV();
		campoVacioCodPers.setCODPERSRLDIR("");
		contextoVacioCampos.getCODPERSRLDIRV().add(campoVacioCodPers);
		campoVacioCodPers.setCODPERSRLDIR("");
		contextoVacioCampos.getCODPERSRLDIRV().add(campoVacioCodPers);
		campoVacioCodPers.setCODPERSRLDIR("");
		contextoVacioCampos.getCODPERSRLDIRV().add(campoVacioCodPers);
		campoVacioCodPers.setCODPERSRLDIR("");
		contextoVacioCampos.getCODPERSRLDIRV().add(campoVacioCodPers);
		contextoEntradaCampos.setPECOMPARTIRDOMICOBJTR(contextoVacioCampos);

		Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.UOCENTROV contextoVacioUOCentro = new Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.UOCENTROV();
		contextoVacioUOCentro.setCODENTCOLECAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getCodigoEntidadColectiva());
		contextoVacioUOCentro.setCODENTSINGAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getCodigoProvincia());
		contextoVacioUOCentro.setCODMUNICIPIOAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getCodigoMunicipio());
		contextoVacioUOCentro.setCODPROVINCIAAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getCodigoProvincia());
		contextoEntradaCampos.setUOCENTROV(contextoVacioUOCentro);

		contextoEntrada.setSTDTRNIPARMV(contextoComun);

		contextoEntrada.setTRALTACENTROEVTY(contextoEntradaCampos);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaCentroServicio.class, contextoEntrada);

		super.verificaRespuesta(respuesta);
		
		centroBean.setIdInterno(respuesta.getResponseBansefi().getOTRALTACENTROTRNO().getTRALTACENTROEVTZ().getPEPERSP().getIDINTERNOPE());
	
		return respuesta.getResponseBansefi().getOTRALTACENTROTRNO().getRTRNCD();
	}
}
