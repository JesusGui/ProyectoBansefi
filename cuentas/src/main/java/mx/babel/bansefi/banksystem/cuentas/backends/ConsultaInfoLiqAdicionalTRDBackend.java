package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Date;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionInformacionBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaInfoLiqAdicionalTRD.ConsultaInfoLiqAdicionalTRDServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaInfoLiqAdicionalTRD.Ejecutar.ITRHLLIQCSOBJTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaInfoLiqAdicionalTRD.Ejecutar.ITRHLLIQCSOBJTRNI.TRHLLIQCSOBJEVTY.TRCONSHLCDAPLICEVTZ.HLCDAPLICE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaInfoLiqAdicionalTRD.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaInfoLiqAdicionalTRD.ResponseBansefi.OTRHLLIQCSOBJTRNO.TRHLLIQCSOBJEVTZ.HLLIQCDCS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que ejecuta la TRD de consulta información adicional de una
 * liquidación (tabla de transaccionalidad de la liquidación).
 * 
 * @author omar.marquez
 */
@Component
public class ConsultaInfoLiqAdicionalTRDBackend extends BackEndBean {

	private static final long serialVersionUID = -8099563792599416023L;

	private IntegerToDateConverter converter;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Constructor.
	 */
	public ConsultaInfoLiqAdicionalTRDBackend() {
		super();
		this.converter = new IntegerToDateConverter();
	}

	/**
	 * Método que ejecuta la TRN que contiene la lógica de la traductora que
	 * permite consultar la información adicional de una liquidación.
	 * 
	 * @param liquidacionInformacionBean
	 * @param numCuenta
	 * @param fechaLiquidacion
	 * @param numSec
	 * @param codInfAdicHL
	 * @param valorInfAdicHL
	 * @throws NullPointerException
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public void ejecutarTRN(
			LiquidacionInformacionBean liquidacionInformacionBean,
			long numCuenta, Date fechaLiquidacion, int numSec,
			String codInfAdicHL, String valorInfAdicHL)
			throws NullPointerException, ControlableException,
			NoControlableException {
		ITRHLLIQCSOBJTRNI itrhlliqcsobjtrni = initPeticion(numCuenta,
				fechaLiquidacion, numSec, codInfAdicHL, valorInfAdicHL);

		EjecutarResult resultado = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaInfoLiqAdicionalTRDServicio.class,
						itrhlliqcsobjtrni);

		super.verificaRespuesta(resultado);

		establecerInfoLiqAdicional(liquidacionInformacionBean, resultado,
				codInfAdicHL);
	}

	/**
	 * Método privado que prepara los datos que serán enviados a la TRN.
	 * 
	 * @param numCuenta
	 * @param fechaLiquidacion
	 * @param numSec
	 * @param codInfAdicHL
	 * @param valorInfAdicHL
	 * @return parametros de entrada encapsulados en un objeto ITRHLLIQCSOBJTRNI
	 * @throws NullPointerException
	 */
	private ITRHLLIQCSOBJTRNI initPeticion(long numCuenta,
			Date fechaLiquidacion, int numSec, String codInfAdicHL,
			String valorInfAdicHL) throws NullPointerException {
		ITRHLLIQCSOBJTRNI itrhlliqcsobjtrni = new ITRHLLIQCSOBJTRNI();

		super.initialize(itrhlliqcsobjtrni);

		itrhlliqcsobjtrni.setELEVATORPOSITION(0);
		itrhlliqcsobjtrni.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		itrhlliqcsobjtrni.setNUMBEROFRECORDS(1);

		itrhlliqcsobjtrni.getTRHLLIQCSOBJEVTY().getTRCONSHLCDAPLICEVTZ()
				.getHLCDAPLICE().add(new HLCDAPLICE());

		itrhlliqcsobjtrni.getTRHLLIQCSOBJEVTY().getTRCONSHLCDAPLICEVTZ()
				.getHLCDAPLICE().get(0).setCODNRBEEN(super.getEntidad());
		itrhlliqcsobjtrni.getTRHLLIQCSOBJEVTY().getTRCONSHLCDAPLICEVTZ()
				.getHLCDAPLICE().get(0).setNUMSECAC(numCuenta);
		itrhlliqcsobjtrni.getTRHLLIQCSOBJEVTY().getTRCONSHLCDAPLICEVTZ()
				.getHLCDAPLICE().get(0)
				.setFECHALIQ(converter.convertFrom(fechaLiquidacion));
		itrhlliqcsobjtrni.getTRHLLIQCSOBJEVTY().getTRCONSHLCDAPLICEVTZ()
				.getHLCDAPLICE().get(0).setNUMSEC(numSec);
		itrhlliqcsobjtrni.getTRHLLIQCSOBJEVTY().getTRCONSHLCDAPLICEVTZ()
				.getHLCDAPLICE().get(0).setCODINFADICHL(codInfAdicHL);
		itrhlliqcsobjtrni.getTRHLLIQCSOBJEVTY().getTRCONSHLCDAPLICEVTZ()
				.getHLCDAPLICE().get(0).setVALORINFADICHL(valorInfAdicHL);

		itrhlliqcsobjtrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		itrhlliqcsobjtrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_CONS_HL_CD_APLIC_TRN);

		return itrhlliqcsobjtrni;
	}

	/**
	 * Método privado que establece la información adicional en un
	 * LiquidacionInformacionBean de entrada.
	 * 
	 * @param liquidacionInformacionBean
	 * @param resultado
	 * @param codInfAdicHL
	 * @throws NullPointerException
	 */
	private void establecerInfoLiqAdicional(
			LiquidacionInformacionBean liquidacionInformacionBean,
			EjecutarResult resultado, String codInfAdicHL)
			throws NullPointerException {
		if (verificarResultado(resultado)) {
			for (HLLIQCDCS elemento : resultado.getResponseBansefi()
					.getOTRHLLIQCSOBJTRNO().getTRHLLIQCSOBJEVTZ()
					.getHLLIQCDCS()) {
				if (elemento != null
						&& elemento.getCODINFADICHL() != null
						&& !elemento.getCODINFADICHL().trim().isEmpty()
						&& codInfAdicHL.equalsIgnoreCase(elemento
								.getCODINFADICHL().trim())) {
					liquidacionInformacionBean.setCodInfAdicHL(elemento
							.getCODINFADICHL());
					liquidacionInformacionBean.setTipoInformacion(elemento
							.getDESCRINFADICHL().trim());
					liquidacionInformacionBean.setValor(elemento.getSTDCHAR20()
							.trim());
					break;
				}
			}
		}
	}

	/**
	 * Método privado que devuelve un indicador booleano para determinar sí la
	 * respuesta contiene datos.
	 * 
	 * @param resultado
	 * @return indicador booleano
	 */
	private boolean verificarResultado(EjecutarResult resultado) {
		if (resultado.getResponseBansefi() != null
				&& resultado.getResponseBansefi().getOTRHLLIQCSOBJTRNO() != null
				&& resultado.getResponseBansefi().getOTRHLLIQCSOBJTRNO()
						.getTRHLLIQCSOBJEVTZ() != null
				&& resultado.getResponseBansefi().getOTRHLLIQCSOBJTRNO()
						.getTRHLLIQCSOBJEVTZ().getHLLIQCDCS() != null) {
			return true;
		}
		return false;
	}

}