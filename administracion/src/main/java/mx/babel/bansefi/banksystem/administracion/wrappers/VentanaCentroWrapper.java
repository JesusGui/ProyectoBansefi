package mx.babel.bansefi.banksystem.administracion.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.administracion.beans.centro.AltaCentroBean;
import mx.babel.bansefi.banksystem.administracion.beans.centro.CentroControladorBean;
import mx.babel.bansefi.banksystem.administracion.webservices.altacentro.Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY;
import mx.babel.bansefi.banksystem.administracion.webservices.altacentro.Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV;
import mx.babel.bansefi.banksystem.administracion.webservices.consultacentroscontroladores.ResponseBansefi.OTRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTZ.UOCENTCTRLE;
import mx.babel.bansefi.banksystem.administracion.webservices.detallecentro.ResponseBansefi.OTRCONSCENTROTRNO.TRCONSCENTROEVTZ;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacioncentro.Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioBean;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Utilería para realizar wrapper de campos en la ventana de Mantenimiento de
 * Centros.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class VentanaCentroWrapper implements Serializable {

	private static final long serialVersionUID = 2912755701206830747L;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	public VentanaCentroWrapper() {

	}

	/**
	 * Método para mapear datos del centro al realizar un alta.
	 * 
	 * @param altaCentro
	 *            Bean que contiene los datos a mapear
	 * @return TRALTACENTROEVTY Bean con los datos mapeados.
	 */
	public TRALTACENTROEVTY wrappBeanAltaCentro1(AltaCentroBean altaCentro) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(altaCentro, TRALTACENTROEVTY.class, "altaCentro1");
	}

	/**
	 * Método para mapear datos del domicilio del centro al realizar un alta.
	 * 
	 * @param domicilio
	 *            Bean que contiene los datos a mapear
	 * @return DRDMDPOBJTRDV Bean con los datos mapeados.
	 */
	public DRDMDPOBJTRDV wrappBeanAltaCentroDomicilio(DomicilioBean domicilio) {
		Mapper mapper = dozerBeanMapper;
		DRDMDPOBJTRDV drdmdpobjtrdv = mapper
				.map(domicilio, DRDMDPOBJTRDV.class, "altaDomicilioCentro");
		if(drdmdpobjtrdv.getCOMPDOMICV().size() < 14){
			while(drdmdpobjtrdv.getCOMPDOMICV().size() < 14){
				mx.babel.bansefi.banksystem.administracion.webservices.altacentro.Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.COMPDOMICV compdomicv = 
						new mx.babel.bansefi.banksystem.administracion.webservices.altacentro.Ejecutar.ITRALTACENTROTRNI.TRALTACENTROEVTY.DRDMDPOBJTRDV.COMPDOMICV();
				compdomicv.setCODCOMPDOMIC("");
				compdomicv.setVALCOMPDOMICDRLEN(0);
				compdomicv.setVALCOMPDOMICDR("");
				drdmdpobjtrdv.getCOMPDOMICV().add(compdomicv);
			}
		}	
		return drdmdpobjtrdv;
	}

	/**
	 * Método para mapear datos del centro al realizar una modificación.
	 * 
	 * @param altaCentro
	 *            Bean que contiene los datos a mapear
	 * @return TRMODICENTROEVTY Bean con los datos mapeados.
	 */
	public TRMODICENTROEVTY wrappBeanAltaModificacion1(AltaCentroBean altaCentro) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(altaCentro, TRMODICENTROEVTY.class,
				"modificacionCentro1");
	}

	/**
	 * Método para mapear datos del domicilio del centro al realizar una
	 * modificacion.
	 * 
	 * @param domicilio
	 *            Bean que contiene los datos a mapear
	 * @return DRDMDPOBJTRDV Bean con los datos mapeados.
	 */
	public mx.babel.bansefi.banksystem.administracion.webservices.modificacioncentro.Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV wrappBeanModificacionCentroDomicilio(
			DomicilioBean domicilio) {
		Mapper mapper = dozerBeanMapper;
		mx.babel.bansefi.banksystem.administracion.webservices.modificacioncentro.Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV drdmdpobjtrdv = 
				mapper
				.map(domicilio,
						mx.babel.bansefi.banksystem.administracion.webservices.modificacioncentro.Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.class,
						"modificacionDomicilioCentro");
		if(drdmdpobjtrdv.getCOMPDOMICV().size() < 14){
			while(drdmdpobjtrdv.getCOMPDOMICV().size() < 14){
				mx.babel.bansefi.banksystem.administracion.webservices.modificacioncentro.Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.COMPDOMICV compdomicv = 
						new mx.babel.bansefi.banksystem.administracion.webservices.modificacioncentro.Ejecutar.ITRMODICENTROTRNI.TRMODICENTROEVTY.DRDMDPOBJTRDV.COMPDOMICV();
				compdomicv.setCODCOMPDOMIC("");
				compdomicv.setVALCOMPDOMICDRLEN(0);
				compdomicv.setVALCOMPDOMICDR("");
				drdmdpobjtrdv.getCOMPDOMICV().add(compdomicv);
			}	
		}
		return drdmdpobjtrdv;
	}

	/**
	 * Método para mapear los datos que se obtienen del WS.
	 * 
	 * @param resultado
	 *            Salida de la TRN a mapear
	 * @return AltaCentroBean Bean mapeado para mostrar en la ventana
	 */
	public AltaCentroBean wrappDetalleCentro(TRCONSCENTROEVTZ resultado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, AltaCentroBean.class, "detalleCentro");
	}

	/**
	 * Método para mapear los datos que se obtienen del WS de Centros
	 * Controladores.
	 * 
	 * @param resultado
	 *            Salida de la TRN a mapear
	 * @return AltaCentroBean Bean mapeado para mostrar en la ventana
	 */
	public CentroControladorBean wrappConsultaCentroCtrl(UOCENTCTRLE resultado) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(resultado, CentroControladorBean.class,
				"consultaCentroContrl");
	}
}
