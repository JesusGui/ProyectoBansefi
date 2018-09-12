package mx.babel.bansefi.banksystem.base.backends.login;

import java.util.Calendar;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.arq.serviciosAppwhere.clients.remesasNacionales.EntidadesClient;
import mx.babel.arq.serviciosAppwhere.dto.remesasNacionales.ResCredencialesEntidadDto;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.AperturaPuestoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.aperturapuesto.AperturaPuestoServicio;
import mx.babel.bansefi.banksystem.base.webservices.aperturapuesto.Ejecutar.ITRAPERTURAPUESTOSTRN;
import mx.babel.bansefi.banksystem.base.webservices.aperturapuesto.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.aperturapuesto.ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de apertura de puesto.
 * 
 * @author omar.marquez
 */
@Component
public class AperturaPuestoBackend extends BackEndBean {

	private static final long serialVersionUID = -851627785294494437L;

	private IntegerToDateConverter integerToDateConverter;
	private EntidadesClient entidadesClient;

	@Autowired
	public void setEntidadesClient(EntidadesClient entidadesClient) {
		this.entidadesClient = entidadesClient;
	}

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public AperturaPuestoBackend() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de apertura de puesto y construye un objeto
	 * tipo AperturaPuestoBean con los datos devueltos.
	 * 
	 * @return aperturaPuestoBean
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public AperturaPuestoBean ejecutarTRN() throws NullPointerException,
			ControlableException, NoControlableException {
		ITRAPERTURAPUESTOSTRN itraperturapuestostrn = initPeticion();
//		verifyEntityCredentialsAndReplace(itraperturapuestostrn);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(AperturaPuestoServicio.class, itraperturapuestostrn);

		super.verificaRespuesta(resultado);

		return obtenerAperturaPuestoBean(resultado.getResponseBansefi()
				.getOTRAPERTURAPUESTOSTRN().getTRAPERTURAPUESTOSEVTZ());
	}

	/**
	 * Metodo para realizar verificacion de credenciales para otras entidades diferentes a bansefi
	 *
	 * @param itraperturapuestostrn
	 */
	private void verifyEntityCredentialsAndReplace(ITRAPERTURAPUESTOSTRN itraperturapuestostrn) {
		if(!super.getEntidad().equals("0166")) {
			ResCredencialesEntidadDto resCredencialesEntidadDto =
					entidadesClient.consultaDatosCentro(super.getEntidad());
			if (resCredencialesEntidadDto.getErrorCode() == null) {
				itraperturapuestostrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(resCredencialesEntidadDto.getTerminal());
				itraperturapuestostrn.getTRAPERTURAPUESTOSEVTY().setIDINTERNOTERMTN(resCredencialesEntidadDto.getTerminal());
				itraperturapuestostrn.getTRAPERTURAPUESTOSEVTY().setIDINTERNOEMPLEP(resCredencialesEntidadDto.getUser());
				itraperturapuestostrn.getTRAPERTURAPUESTOSEVTY().setCODNRBEENFSC(resCredencialesEntidadDto.getOrganizationCode());
				itraperturapuestostrn.getTRAPERTURAPUESTOSEVTY().setCODNRBEEN(resCredencialesEntidadDto.getOrganizationCode());
				itraperturapuestostrn.getTRAPERTURAPUESTOSEVTY().setCODINTERNOUO(resCredencialesEntidadDto.getBranchOffice());
			} else {
				throw new NoControlableException("Servicio no disponible","Fall\u00F3 consulta de credenciales en remesas nacionales.");
			}
		}
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRAPERTURAPUESTOSTRN
	 * @throws NullPointerException
	 */
	private ITRAPERTURAPUESTOSTRN initPeticion() throws NullPointerException {
		ITRAPERTURAPUESTOSTRN itraperturapuestostrn = new ITRAPERTURAPUESTOSTRN();

		super.initialize(itraperturapuestostrn);

		itraperturapuestostrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itraperturapuestostrn.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_APERTURA_PUESTOS_TRN);

		itraperturapuestostrn.getTRAPERTURAPUESTOSEVTY().setFECHAPERFIL(
				integerToDateConverter.convertFrom(super.getFechaSistema()));
		itraperturapuestostrn.getTRAPERTURAPUESTOSEVTY().setFECHAPC(
				integerToDateConverter.convertFrom(Calendar.getInstance()
						.getTime()));
		itraperturapuestostrn.getTRAPERTURAPUESTOSEVTY().setIDINTERNOTERMTN(
				super.getTerminal());
		itraperturapuestostrn.getTRAPERTURAPUESTOSEVTY().setIDINTERNOEMPLEP(
				super.getUsuarioId());
		itraperturapuestostrn.getTRAPERTURAPUESTOSEVTY().setCODNRBEENFSC(
				super.getEntidad());
		itraperturapuestostrn.getTRAPERTURAPUESTOSEVTY().setCODNRBEEN(
				super.getEntidad());
		itraperturapuestostrn.getTRAPERTURAPUESTOSEVTY().setCODINTERNOUO(
				super.getSucursal());

		return itraperturapuestostrn;
	}

	/**
	 * Método que devuelve un objeto tipo AperturaPuestoBean.
	 * 
	 * @param resultado
	 * @return aperturaPuestoBean
	 * @throws NullPointerException
	 */
	private AperturaPuestoBean obtenerAperturaPuestoBean(
			TRAPERTURAPUESTOSEVTZ resultado) throws NullPointerException {
		AperturaPuestoBean aperturaPuestoBean = new AperturaPuestoBean();
		aperturaPuestoBean.setFechaContable(integerToDateConverter
				.convertTo(resultado.getFECHACTBLE()));
		aperturaPuestoBean.setImporteInicialAnt(resultado.getIMPINI());
		aperturaPuestoBean.setImporteInicialNuevo(resultado.getIMPINI());
		aperturaPuestoBean.setSaldoContable(resultado.getIMPACSDOPNTAL());
		aperturaPuestoBean.setImporteTotal(resultado.getIMPTOTAL());
		aperturaPuestoBean.setIndPuestoPrincipal(resultado.getPUESTOPRINCIPAL()
				.trim());
		return aperturaPuestoBean;
	}

}