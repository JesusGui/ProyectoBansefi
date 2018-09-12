package mx.babel.bansefi.banksystem.base.backends.busquedas;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.GrupoBusquedaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupoporidexterna.ConsultaGrupoPorIdExternaServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupoporidexterna.Ejecutar.ITRGRLSTTRNI;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupoporidexterna.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupoporidexterna.ResponseBansefi.OTRGRLSTTRNO.TRGRLSTEVTZ.TRGRLSTEVTV;
import mx.babel.bansefi.banksystem.base.wrappers.WrapperBusquedasUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para ejecutar la TRN de búsqueda de grupos por identificación o
 * nombre.
 * 
 * @author omar.marquez
 * 
 */
@Component
public class BusquedaGrupoBackEnd extends BackEndBean {

	private static final long serialVersionUID = -8657964284924159972L;

	/**
	 * Constante utilizada por el HOST para efectuar la consulta. Sin esta
	 * variable la TRN no devuelve datos.
	 */
	private static final String STD_SELECT_BUSCAR_GR_V_ID = "IDENTIFICACION";

	private static final String STD_SELECT_BUSCAR_GR_V_NOMBRE = "NOMBRE";

	@Autowired
	private WrapperBusquedasUtils wrapperBusquedasUtils;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public BusquedaGrupoBackEnd() {
		super();
	}

	/**
	 * Método que ejecuta la TRN de búsqueda de grupos a partir de una
	 * identificación.
	 * 
	 * @param codigoTipoGrupo
	 * @param identificacion
	 * @return nombre del grupo
	 * @throws IndexOutOfBoundsException
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public String ejecutarTRN(String codigoTipoGrupo, String identificacion)
			throws IndexOutOfBoundsException, NullPointerException,
			ControlableException, NoControlableException {
		ITRGRLSTTRNI itrgrlsttrni = initPeticion(codigoTipoGrupo,
				identificacion);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(BusquedaGrupoBackEnd.class, itrgrlsttrni);

		super.verificaRespuesta(resultado);

		return resultado.getResponseBansefi().getOTRGRLSTTRNO()
				.getTRGRLSTEVTZ().getTRGRLSTEVTV().get(0).getNOMBGR().trim();
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param codigoTipoGrupo
	 * @param identificacion
	 * @return parametros de entrada encapsulados en un objeto ITRGRLSTTRNI
	 * @throws NullPointerException
	 */
	private ITRGRLSTTRNI initPeticion(String codigoTipoGrupo,
			String identificacion) throws NullPointerException {
		ITRGRLSTTRNI itrgrlsttrni = new ITRGRLSTTRNI();

		super.initialize(itrgrlsttrni);

		itrgrlsttrni.setELEVATORPOSITION(0);
		itrgrlsttrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrgrlsttrni.getTRGRLSTEVTY().getGRGRPP()
				.setCODNRBEEN(super.getEntidad());
		itrgrlsttrni.getTRGRLSTEVTY().getGRGRPP().setCODGRP(codigoTipoGrupo);
		itrgrlsttrni.getTRGRLSTEVTY().getGRGRPP().setIDEXTGR(identificacion);

		itrgrlsttrni.getTRGRLSTEVTY().getGRLACB().getSTDSELECTBUSCARGRV()
				.setSTDCHAR14(STD_SELECT_BUSCAR_GR_V_ID);

		itrgrlsttrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		itrgrlsttrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_GR_LST_TRN);

		return itrgrlsttrni;
	}

	/**
	 * @author alejandro.pineda
	 */
	public List<Object> ejecutarTRN(Object obj) throws ControlableException,
			NoControlableException {
		List<Object> resultados = new ArrayList<>();
		GrupoBusquedaBean grupo = (GrupoBusquedaBean) obj;
		ITRGRLSTTRNI itrgrlsttrni = initPeticion(grupo.getNombre(),
				grupo.getTipoGrupo(), grupo.getIdInterna());

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaGrupoPorIdExternaServicio.class,
						itrgrlsttrni);

		super.verificaRespuesta(resultado);

		for (TRGRLSTEVTV datoResultado : resultado.getResponseBansefi()
				.getOTRGRLSTTRNO().getTRGRLSTEVTZ().getTRGRLSTEVTV()) {
			if (datoResultado.getIDEXTGR() != null
					&& !("").equals(datoResultado.getIDEXTGR().trim())) {
				resultados.add(wrapperBusquedasUtils
						.wrappBeanResultadosGrupo(datoResultado));
			}
		}

		return resultados;
	}

	/**
	 * @author alejandro.pineda
	 */
	private ITRGRLSTTRNI initPeticion(String nombre, String codigoTipoGrupo,
			String idExternoGrupo) {
		ITRGRLSTTRNI itrgrlsttrni = new ITRGRLSTTRNI();

		super.initialize(itrgrlsttrni);

		itrgrlsttrni.setELEVATORPOSITION(0);
		itrgrlsttrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrgrlsttrni.getTRGRLSTEVTY().getGRGRPP()
				.setCODNRBEEN(super.getEntidad());
		itrgrlsttrni.getTRGRLSTEVTY().getGRGRPP().setCODGRP(codigoTipoGrupo);
		itrgrlsttrni.getTRGRLSTEVTY().getGRGRPP().setIDEXTGR(idExternoGrupo);
		
		itrgrlsttrni.getTRGRLSTEVTY().getGRLACB().setCODGRP(codigoTipoGrupo);
		
		itrgrlsttrni.getTRGRLSTEVTY().getGRLACB().setNOMBGR(nombre);
		
		itrgrlsttrni.getTRGRLSTEVTY().getGRLACB().setIDEXTGR(idExternoGrupo);
		
		
		itrgrlsttrni.getTRGRLSTEVTY().getNOMBGRCB().setSTDCHAR60(nombre);

		if (nombre == null) {
			itrgrlsttrni.getTRGRLSTEVTY().getGRLACB().getSTDSELECTBUSCARGRV()
					.setSTDCHAR14(STD_SELECT_BUSCAR_GR_V_ID);
		} else {
			itrgrlsttrni.getTRGRLSTEVTY().getGRLACB().getSTDSELECTBUSCARGRV()
					.setSTDCHAR14(STD_SELECT_BUSCAR_GR_V_NOMBRE);
		}
		itrgrlsttrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		itrgrlsttrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_GR_LST_TRN);

		return itrgrlsttrni;
	}

}