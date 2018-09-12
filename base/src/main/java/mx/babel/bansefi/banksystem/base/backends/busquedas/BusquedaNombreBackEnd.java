package mx.babel.bansefi.banksystem.base.backends.busquedas;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaGestorBusquedaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.busquedanombre.BusquedaNombrePersonaServicio;
import mx.babel.bansefi.banksystem.base.webservices.busquedanombre.Ejecutar.ITRPECBNOMBRECNSTRN;
import mx.babel.bansefi.banksystem.base.webservices.busquedanombre.EjecutarResult;
import mx.babel.bansefi.banksystem.base.wrappers.WrapperBusquedasUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio de busqueda por nombre de un
 * Gestor.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class BusquedaNombreBackEnd extends BackEndBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1019680047604946710L;

	@Autowired
	private WrapperBusquedasUtils wrapperBusquedasUtils;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	private static final String STD_SELEC_BUSCAR_PE_V = "APELLIDO";

	public BusquedaNombreBackEnd() {
		super();
	}

	/**
	 * Método que ejecutar el servicio de busqueda por nombre.
	 * 
	 * @return Lista de resultados
	 * @throws ControlableException
	 *             Excepción controlable del servicio.
	 * @throws NoControlableException
	 *             Excepción no controlable del servicio.
	 */
	public List<Object> ejecutarTRN(Object obj) throws ControlableException,
			NoControlableException {
		PersonaGestorBusquedaBean personaBusqueda = (PersonaGestorBusquedaBean) obj;

		ITRPECBNOMBRECNSTRN itrpecbnombrecnstrn = initPeticion(personaBusqueda);
		List<Object> resultados = new ArrayList<>();

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(BusquedaNombrePersonaServicio.class,
						itrpecbnombrecnstrn);

		super.verificaRespuesta(resultado);

		personaBusqueda.setMasDatos(resultado.getResponseBansefi()
				.getOTRPECBNOMBRECNSTRN().getMOREDATAIN() == 1);

		resultados = wrapperBusquedasUtils.wrappResultadosBusquedaNombre(
				resultado.getResponseBansefi().getOTRPECBNOMBRECNSTRN()
						.getTRPECBNOMBRECNSEVTZ()).getDatos();

		return resultados;
	}

	public ITRPECBNOMBRECNSTRN initPeticion(PersonaGestorBusquedaBean obj) {

		ITRPECBNOMBRECNSTRN itrpecbnombrecnstrn = new ITRPECBNOMBRECNSTRN();

		super.initialize(itrpecbnombrecnstrn);

		itrpecbnombrecnstrn.setELEVATORPOSITION(0);
		itrpecbnombrecnstrn.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);

		itrpecbnombrecnstrn.getTRPECBNOMBRECNSEVTY().setPEINDVCBNOMBREV(
				wrapperBusquedasUtils.wrappBusquedaNombre(obj));
		itrpecbnombrecnstrn.getTRPECBNOMBRECNSEVTY().getPEINDVCBNOMBREV()
				.setCODNRBEEN(super.getEntidad());

		itrpecbnombrecnstrn.getTRPECBNOMBRECNSEVTY().getSTDSELECBUSCARPEV()
				.setSTDCHAR14(STD_SELEC_BUSCAR_PE_V);

		itrpecbnombrecnstrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrpecbnombrecnstrn.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_PE_CB_NOMBRE_CNS_TRN);

		return itrpecbnombrecnstrn;

	}

}
