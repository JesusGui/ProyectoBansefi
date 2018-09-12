package mx.babel.bansefi.banksystem.personas.backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.personas.beans.DireccionRegistralBean;
import mx.babel.bansefi.banksystem.personas.webservices.mantenimientodireccionesregistrales.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.mantenimientodireccionesregistrales.Ejecutar.ITRDRREGMNTTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.mantenimientodireccionesregistrales.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.mantenimientodireccionesregistrales.MantenimientoDireccionesRegistralesServicio;
import mx.babel.bansefi.banksystem.personas.wrappers.ActividadEmpresarialWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para alta, baja y modificación de Dirección Registral.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class MantenimientoDireccionRegistralBackEnd extends BackEndBean {

	private static final long serialVersionUID = 1020480236321290143L;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	@Autowired
	private ActividadEmpresarialWrapper actividadEmpresarialWrapper;

	/**
	 * Método para ejecutar la TRN de alta, baja o modificacion de dirección
	 * registral.
	 * 
	 * @param direccionRegistral
	 *            Bean de una dirección registral.
	 * @return int Código de retorno del servicio
	 */
	public int ejecutarTRN(DireccionRegistralBean direccionRegistral){
		direccionRegistral.setEntidad(super.getEntidad());

		Ejecutar.ITRDRREGMNTTRNI contextoEntrada = initPeticion(direccionRegistral);

		return this.obtenerRespuestaServicio(contextoEntrada);

	}

	private ITRDRREGMNTTRNI initPeticion( DireccionRegistralBean direccionRegistral) {

		Ejecutar.ITRDRREGMNTTRNI contextoEntrada = new Ejecutar.ITRDRREGMNTTRNI();

		super.initialize(contextoEntrada);

		contextoEntrada.setNUMBEROFRECORDS(8);
		contextoEntrada.getPEPERSRLDIRP().setCODNRBEEN(super.getEntidad());
		contextoEntrada.getPEPERSRLDIRP().setIDINTERNOPE(
				direccionRegistral.getIdInterno());

		if (direccionRegistral.getActionType().intValue() == ConstantesFuncionales.ACTION_TYPE_BORRAR_DIRECCION_REGISTRAL
				|| direccionRegistral.getActionType().intValue() == ConstantesFuncionales.ACTION_TYPE_MODIFICAR_DIRECCION_REGISTRAL) {
			contextoEntrada.getPEPERSRLDIRP().setNUMDIR(direccionRegistral.getNumDir());
			contextoEntrada.getDRRGSTROP().setNUMDIR(direccionRegistral.getNumDir());
			contextoEntrada.getPEPERSRLDIRP().setCODDIR(ConstantesFuncionales.COD_DIR);
			contextoEntrada.getPEPERSRLDIRP().setCODPERSRLDIR(ConstantesFuncionales.COD_PERS_DIR);
		}

		contextoEntrada.getDRRGSTROP().setCODNRBEEN(super.getEntidad());

		contextoEntrada.setTRDRREGMNTTRNY(actividadEmpresarialWrapper
				.wrapperMantenimientoDetalleDireccion(direccionRegistral));

		Ejecutar.ITRDRREGMNTTRNI.TRDRREGMNTTRNY.DRCOMPRGSTROV2 drcomprgstrov2 = new Ejecutar.ITRDRREGMNTTRNI.TRDRREGMNTTRNY.DRCOMPRGSTROV2();
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		if (direccionRegistral.getfExpedicion() != null) {
			drcomprgstrov2.setCODCOMPRGSTRO("07");
			drcomprgstrov2.setVALCOMPRGSTRODRLEN(direccionRegistral.getfExpedicion().toString().length());
			drcomprgstrov2.setVALCOMPRGSTRODR(formatter.format(direccionRegistral.getfExpedicion()));
		}
		contextoEntrada.getTRDRREGMNTTRNY().getDRCOMPRGSTROV2().add(drcomprgstrov2);

		contextoEntrada.getTRDRREGMNTTRNY().setDRRGSTROV2(actividadEmpresarialWrapper
						.wrapperMantenimientoDireccion(direccionRegistral));

		contextoEntrada.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		contextoEntrada.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TX_TR_DR_REG_MNT_TRN);

		return contextoEntrada;
	}

	/**
	 * Método para obtener codigo de respuesta obtenido del servicio de alta de
	 * datos empresariales.
	 * 
	 * @param contextoEntrada Entrada de Datos.
	 * @return int código retornado.
	 */
	private int obtenerRespuestaServicio(ITRDRREGMNTTRNI contexto){		
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				MantenimientoDireccionesRegistralesServicio.class, contexto);

		super.verificaRespuesta(respuesta);

		return respuesta.getResponseBansefi().getOTRDRREGMNTTRNO().getRTRNCD();
	}

}
