package mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.beans.centro.CentroControladorBean;
import mx.babel.bansefi.banksystem.administracion.webservices.consultacentroscontroladores.ConsultaCentrosControladoresServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.consultacentroscontroladores.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.consultacentroscontroladores.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.webservices.consultacentroscontroladores.ResponseBansefi.OTRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTZ.UOCENTCTRLE;
import mx.babel.bansefi.banksystem.administracion.wrappers.VentanaCentroWrapper;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de Consulta de Centros
 * Controladores.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class ConsultaCentrosControladoresBackEnd extends BackEndBean {

	private static final long serialVersionUID = -9213173965752723093L;

	@Autowired
	private VentanaCentroWrapper ventanaCentroWrapper;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	/**
	 * Metodo que ejecuta el servicio de consulta de centros controladores.
	 * 
	 * @param codigoCentro
	 *            Codigo del centro.
	 * @return Lista con los codigos de las aplicaciones.
	 */
	public List<CentroControladorBean> ejecutarTRN(String codigoCentro){
		Ejecutar.ITRUOCONCENTCTRLTRN contexto = new Ejecutar.ITRUOCONCENTCTRLTRN();
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);

		Ejecutar.ITRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTY contextoEntrada = new Ejecutar.ITRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTY();

		Ejecutar.ITRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTY.UOCENTCTRLP contextoEntradaCampos = new Ejecutar.ITRUOCONCENTCTRLTRN.TRUOCONCENTCTRLEVTY.UOCENTCTRLP();
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setCODINTERNOUO(codigoCentro);
		contextoEntradaCampos.setCODCENTCTRL("");
		contextoEntrada.setUOCENTCTRLP(contextoEntradaCampos);

		Ejecutar.ITRUOCONCENTCTRLTRN.STDTRNIPARMV contextoComun = new Ejecutar.ITRUOCONCENTCTRLTRN.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("GCA57CON");
		contextoComun.setCODTXDI("");

		contexto.setTRUOCONCENTCTRLEVTY(contextoEntrada);
		contexto.setSTDTRNIPARMV(contextoComun);	

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ConsultaCentrosControladoresServicio.class, contexto);

		super.verificaRespuesta(respuesta);

		List<CentroControladorBean> aplicaciones = new ArrayList<>();
		
		for (UOCENTCTRLE dato : respuesta.getResponseBansefi()
				.getOTRUOCONCENTCTRLTRN().getTRUOCONCENTCTRLEVTZ()
				.getUOCENTCTRLE()) {
			if (!("").equals(dato.getCODINTERNOUO().trim())) {
				CentroControladorBean centroCtrl = ventanaCentroWrapper.wrappConsultaCentroCtrl(dato);
				aplicaciones.add(centroCtrl);
			}
		}

		return aplicaciones;		
	}

}
