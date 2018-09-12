package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.movimientos.MovimientoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.cuentas.webservices.tablaIndicadoresApunteTRD.Ejecutar.IAFAPUNTEBASCPTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.tablaIndicadoresApunteTRD.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.tablaIndicadoresApunteTRD.ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.tablaIndicadoresApunteTRD.ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFAPUNTEBASCPLP;
import mx.babel.bansefi.banksystem.cuentas.webservices.tablaIndicadoresApunteTRD.TablaIndicadoresApunteTRDServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que ejecuta la TRD de consulta de indicadores de un apunte.
 * 
 * @author omar.marquez
 */
@Component
public class TablaIndicadoresApunteTRDBackend extends BackEndBean {

	private static final long serialVersionUID = 4047449218283433253L;

	// Constante que representa el número de indicadores a visualizar.
	private static final int NUM_DE_REGISTROS = 10;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Método que ejecuta el servicio para traducir los indicadores de un
	 * apunte.
	 * 
	 * @param movimientoBean
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public void ejecutarTRN(MovimientoBean movimientoBean)
			throws NullPointerException, ControlableException,
			NoControlableException {
		IAFAPUNTEBASCPTRNI iafapuntebascptrni = initPeticion(movimientoBean);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(TablaIndicadoresApunteTRDServicio.class,
						iafapuntebascptrni);

		super.verificaRespuesta(resultado);

		AFAPUNTEBASCPEVTZ salida = resultado.getResponseBansefi()
				.getOAFAPUNTEBASCPTRNO().getAFAPUNTEBASCPEVTZ();

		establecerValoresIndicadores(salida, movimientoBean);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param movimientoBean
	 * @return parametros de entrada encapsulados en un objeto
	 *         IAFAPUNTEBASCPTRNI
	 */
	private IAFAPUNTEBASCPTRNI initPeticion(MovimientoBean movimientoBean) {
		IAFAPUNTEBASCPTRNI iafapuntebascptrni = new IAFAPUNTEBASCPTRNI();

		super.initialize(iafapuntebascptrni);

		iafapuntebascptrni.setELEVATORPOSITION(0);
		iafapuntebascptrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		iafapuntebascptrni.setNUMBEROFRECORDS(NUM_DE_REGISTROS);

		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setCODNRBEEN(super.getEntidad());
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setNUMSECAC(movimientoBean.getNumCuenta());
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setCODCTA(movimientoBean.getCodigoCuenta());
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE()
				.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setIND1(movimientoBean.getIndicador1());
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setIND2(movimientoBean.getIndicador2());
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setIND3(movimientoBean.getIndicador3());
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setIND4(movimientoBean.getIndicador4());
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setIND5(movimientoBean.getIndicador5());
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setIND6(movimientoBean.getIndicador6());
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setIND7(movimientoBean.getIndicador7());
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setIND8(movimientoBean.getIndicador8());
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setIND9(movimientoBean.getIndicador9());
		iafapuntebascptrni.getAFAPUNTEBASCPEVTY().getTRPETCNOBTEAFEVTZ()
				.getAFAPNTEE().setIND10(movimientoBean.getIndicador10());

		iafapuntebascptrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		iafapuntebascptrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_AF_CONS_APUNTE_TRN);

		return iafapuntebascptrni;
	}

	/**
	 * Método privado encargado de mapear los valores de los indicadores en una
	 * lista.
	 * 
	 * @param salida
	 * @param movimientoBean
	 * @throws NullPointerException
	 */
	private void establecerValoresIndicadores(AFAPUNTEBASCPEVTZ salida,
			MovimientoBean movimientoBean) throws NullPointerException {
		if (salida != null) {
			List<String> valores = new ArrayList<>();
			for (int i = 0; i < NUM_DE_REGISTROS; i++) {
				AFAPUNTEBASCPLP elemento = salida.getAFAPUNTEBASCPLP().get(i);
				if (elemento.getDESCRINDIC() != null
						&& !elemento.getDESCRINDIC().trim().isEmpty()
						&& elemento.getDESCRDOMIND() != null
						&& !elemento.getDESCRINDIC().trim().isEmpty()) {
					String indicador = elemento.getDESCRINDIC().trim();
					String situacion = elemento.getDESCRDOMIND().trim();
					if (indicador != null && !indicador.isEmpty()
							&& situacion != null && !situacion.isEmpty()) {
						valores.add((i + 1) + "+" + indicador + "+" + situacion);
					}
				}
			}
			movimientoBean.setIndicadores(valores);
		}
	}

}