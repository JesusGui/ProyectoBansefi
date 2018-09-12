package mx.babel.bansefi.banksystem.oficina.backends;

import java.math.BigDecimal;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.oficina.webservices.traspasoentrepuestos.Ejecutar.ITRTRASPASOACTUSALDOS;
import mx.babel.bansefi.banksystem.oficina.webservices.traspasoentrepuestos.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.traspasoentrepuestos.TraspasoEntrePuestosServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para ejecutar la TRN de traspasos entre puestos.
 * 
 * @author omar.marquez
 */
@Component
public class TraspasoEntrePuestosBackend extends BackEndBean {

	private static final long serialVersionUID = 347537576639397929L;

	private IntegerToDateConverter integerToDateConverter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public TraspasoEntrePuestosBackend() {
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN de traspasos entre puestos.
	 * 
	 * @param puestoDestino
	 * @param importeTraspaso
	 * @return código de retorno de la operación
	 */
	public int ejecutarTRN(String puestoDestino, BigDecimal importeTraspaso){
		ITRTRASPASOACTUSALDOS itrtraspasoactusaldos = initPeticion(puestoDestino, importeTraspaso);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(TraspasoEntrePuestosServicio.class,itrtraspasoactusaldos);
		
		// Se omite validación de la salida para tratarlo desde el controller.
		return resultado.getResponseBansefi().getOTRTRASPASOACTUSALDOS().getRTRNCD();
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param puestoDestino
	 * @param importeTraspaso
	 * @return parametros de entrada encapsulados en un objeto
	 *         ITRTRASPASOACTUSALDOS
	 */
	private ITRTRASPASOACTUSALDOS initPeticion(String puestoDestino, BigDecimal importeTraspaso){
		ITRTRASPASOACTUSALDOS itrtraspasoactusaldos = new ITRTRASPASOACTUSALDOS();

		super.initialize(itrtraspasoactusaldos);

		itrtraspasoactusaldos.getVVVTRASPASOTNTNDP().getTNORIGEN()
				.setIDINTERNOTERMTN(super.getTerminal());
		itrtraspasoactusaldos.getVVVTRASPASOTNTNDP().getTNORIGEN()
				.setNUMPUESTO(super.getPuesto());

		// A partir de la terminal de origen formamos la terminal de destino.
		String terminalDestino = super.getTerminal().substring(0, 6)
				+ puestoDestino;
		itrtraspasoactusaldos.getVVVTRASPASOTNTNDP().getTNDESTINO()
				.setIDINTERNOTERMTN(terminalDestino);
		itrtraspasoactusaldos.getVVVTRASPASOTNTNDP().getTNDESTINO()
				.setNUMPUESTO(puestoDestino);

		itrtraspasoactusaldos.getVVVTRASPASOTNTNDP().setIMPNOMINAL(
				importeTraspaso);
		itrtraspasoactusaldos.getVVVTRASPASOTNTNDP().setCODNUMRCOMONEDA(
				ConstantesFuncionales.COD_MONEDA);

		itrtraspasoactusaldos.getSTDAPPLPARMV()
				.setCODNRBEEN(super.getEntidad());
		itrtraspasoactusaldos.getSTDAPPLPARMV().setCODNRBEENFSC(
				super.getEntidad());
		itrtraspasoactusaldos.getSTDAPPLPARMV().setCODINTERNOUO(
				super.getSucursal());
		itrtraspasoactusaldos.getSTDAPPLPARMV().setCODINTERNOUOFSC(
				super.getSucursal());
		itrtraspasoactusaldos.getSTDAPPLPARMV()
				.setCODCSBOF(super.getSucursal());
		itrtraspasoactusaldos.getSTDAPPLPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrtraspasoactusaldos.getSTDAPPLPARMV().setIDINTERNOEMPLEP(
				super.getUsuarioId());
		itrtraspasoactusaldos.getSTDAPPLPARMV().setFECHAOPRCN(
				integerToDateConverter.convertFrom(super.getFechaSistema()));
		itrtraspasoactusaldos.getSTDAPPLPARMV().setFECHACTBLE(
				integerToDateConverter.convertFrom(super.getFechaSistema()));

		return itrtraspasoactusaldos;
	}

}