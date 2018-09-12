package mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros;

import java.io.Serializable;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.beans.centro.AltaCentroBean;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacioncentro.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacioncentro.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacioncentro.ModificacionCentroServicio;
import mx.babel.bansefi.banksystem.administracion.wrappers.VentanaCentroWrapper;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que ejecuta el servicio para Modificación de Centros.
 * @author alejandro.pineda
 *
 */
@Component
public class ModificacionCentroBackend extends BackEndBean implements Serializable{

	private static final long serialVersionUID = 4663351131469384275L;

	@Autowired
	private VentanaCentroWrapper wrapperBeanService;
	
	@Autowired
	private ServicioWebUtils servicioWebUtils;
	
	/**
	 * Metodo para ejecutar el servicio de modificación de centros.
	 * 
	 * @param centroBean
	 *            Bean con los datos a enviar al servicio.
	 * @return int con valor de codigo de mensaje OK.
	 */
	public int ejecutarTRN(AltaCentroBean centroBean){
		
		Ejecutar.ITRMODICENTROTRNI contextoEntrada = new Ejecutar.ITRMODICENTROTRNI();
		
		Ejecutar.ITRMODICENTROTRNI.STDTRNIPARMV contextoComun = new Ejecutar.ITRMODICENTROTRNI.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("GCS36MON");
		contextoComun.setCODTXDI("");
		
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY contextoEntradaCampos = null;
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV contextoEntradaCampos2 = null;
		contextoEntradaCampos = wrapperBeanService
				.wrappBeanAltaModificacion1(centroBean);
		
		contextoEntradaCampos.setINDCENTFICTICUO("N");
		contextoEntradaCampos.setNIVJERARUO("");
		contextoEntradaCampos.setINDDEPENCTBLEUO("N");
		contextoEntradaCampos.setINDCSBUO("N");
		
		contextoEntradaCampos2 = wrapperBeanService
				.wrappBeanModificacionCentroDomicilio(centroBean.getDomicilio());

		contextoEntradaCampos2.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos2.setIDINTERNOPE(centroBean.getIdInterno());
		contextoEntradaCampos2.setNUMDIR(centroBean.getCodDomicilio());
		contextoEntradaCampos2.setCODDIR("1");
		contextoEntradaCampos2.setNOMBCORREO("");
		contextoEntradaCampos2.setPREFTLFNODOMIC("");
		contextoEntradaCampos2.setINDMASDOMIC("S");
		contextoEntradaCampos2.setCODPAISAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getCodigoPais());
		contextoEntradaCampos2.setCODPROVINCIAAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getCodigoProvincia());
		contextoEntradaCampos2.setNOMBPROVINCIAAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getNombreProvincia());
		contextoEntradaCampos2.setCODCOMAUTNMAAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getCodigoComunidadAutonoma());
		
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV codDireccion = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV();
		codDireccion.setCODPERSRLDIR("02");
		contextoEntradaCampos2.getCODPERSRLDIRV().add(codDireccion);
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV codDireccion1 = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV();
		codDireccion1.setCODPERSRLDIR("03");
		contextoEntradaCampos2.getCODPERSRLDIRV().add(codDireccion1);
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV codDireccion2 = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV();
		codDireccion2.setCODPERSRLDIR("");
		contextoEntradaCampos2.getCODPERSRLDIRV().add(codDireccion2);
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV codDireccion3 = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.CODPERSRLDIRV();
		codDireccion3.setCODPERSRLDIR("");
		contextoEntradaCampos2.getCODPERSRLDIRV().add(codDireccion3);
		
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.CODARGEODOMICILIOV codArGeo = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.CODARGEODOMICILIOV();
		codArGeo.setCODARGEO("07");
		contextoEntradaCampos2.setCODARGEODOMICILIOV(codArGeo);

		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.NUMARGEODOMICILIOV numArGeo = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.NUMARGEODOMICILIOV();
		numArGeo.setNUMARGEO(Integer.parseInt(centroBean.getDomicilio().getCodigoPostalCatGeo().getNumArGeo()));
		contextoEntradaCampos2.setNUMARGEODOMICILIOV(numArGeo);
		
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.DRELCTRV drelctrv = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.DRELCTRV();
		drelctrv.setCODARGEO("");
		drelctrv.setNUMARGEO(0);
		contextoEntradaCampos2.setDRELCTRV(drelctrv);
		contextoEntradaCampos.setDRDMDPOBJTRDV(contextoEntradaCampos2);
		
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.INDMENSAJEMODIFDOMICV indmensajemodifdomicv = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.INDMENSAJEMODIFDOMICV();
		indmensajemodifdomicv.setSTDCHAR01("S");
		contextoEntradaCampos.setINDMENSAJEMODIFDOMICV(indmensajemodifdomicv);
		
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.INDMODIFDOMICV indmodifdomicv = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.INDMODIFDOMICV();
		indmodifdomicv.setSTDCHAR01("S");
		contextoEntradaCampos.setINDMODIFDOMICV(indmodifdomicv);
		
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.INDMODIFCENTROV indmodifcentrov = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.INDMODIFCENTROV();
		indmodifcentrov.setSTDCHAR01("S");
		contextoEntradaCampos.setINDMODIFCENTROV(indmodifcentrov);
		
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.INDALTADOMICV indaltadomicv = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.INDALTADOMICV();
		indaltadomicv.setSTDCHAR01("N");
		contextoEntradaCampos.setINDALTADOMICV(indaltadomicv);
		
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.UOCENTROP uocentrop = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.UOCENTROP();
		uocentrop.setCODINTERNOUO(centroBean.getNumero());
		uocentrop.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setUOCENTROP(uocentrop);
		
		
		Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.UOCENTROV contextoVacioUOCentro = new Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.UOCENTROV();
		contextoVacioUOCentro.setCODENTCOLECAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getCodigoEntidadColectiva());
		contextoVacioUOCentro.setCODENTSINGAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getCodigoProvincia());
		contextoVacioUOCentro.setCODMUNICIPIOAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getCodigoMunicipio());
		contextoVacioUOCentro.setCODPROVINCIAAG(centroBean.getDomicilio().getDatosFinalesCatGeo().getCodigoProvincia());
		contextoEntradaCampos.setUOCENTROV(contextoVacioUOCentro);
		
		
		contextoEntrada.setSTDTRNIPARMV(contextoComun);

		contextoEntrada.setTRMODICENTROEVTY(contextoEntradaCampos);

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ModificacionCentroServicio.class, contextoEntrada);
		
		super.verificaRespuesta(respuesta);

		return respuesta.getResponseBansefi().getOTRMODICENTROTRNO().getRTRNCD();
	}
	
}
