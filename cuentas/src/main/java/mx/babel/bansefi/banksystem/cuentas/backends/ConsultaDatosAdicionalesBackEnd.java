package mx.babel.bansefi.banksystem.cuentas.backends;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.cuentas.beans.DatoAdicionalBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ConsultaDatosAdicionalesServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.Ejecutar.ITRCONSUAMPLIARACTRN;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.Ejecutar.ITRCONSUAMPLIARACTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.Ejecutar.ITRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.Ejecutar.ITRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTY.ACACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.Ejecutar.ITRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTY.ACLINEAGRUPOV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ResponseBansefi.OTRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTZ;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ResponseBansefi.OTRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTZ.ACACRLCENTROE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ResponseBansefi.OTRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTZ.ACINFADICE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ResponseBansefi.OTRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTZ.ACINFADIOTRPERSE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ResponseBansefi.OTRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTZ.ACOTRSIDFREXTE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ResponseBansefi.OTRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTZ.ACOTRSPERSE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ResponseBansefi.OTRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTZ.YFINFADICACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ResponseBansefi.OTRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTZ.YFINFADIPERSACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ResponseBansefi.OTRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTZ.YFOTRSIDEXTACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ResponseBansefi.OTRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTZ.YFOTRSPERSACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosadicionales.ResponseBansefi.OTRCONSUAMPLIARACTRN.TRCONSUAMPLIARACEVTZ.YFRLACUOE;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaDatosAdicionalesWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BackEnd para mapear la consulta de Datos Adicionales
 * 
 * @author Aramis Oswaldo Gadea Gerónimo &lt;agadea@uanlmexico.mx&gt;
 * @category BackEnd
 */
@Component
public class ConsultaDatosAdicionalesBackEnd extends BackEndBean {

	private static final long serialVersionUID = -101630322054496331L;

	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaDatosAdicionalesBackEnd.class);

	@Autowired
	ConsultaDatosAdicionalesWrapper consultaDatosAdicionalesWrapper;
	@Autowired
	ServicioWebUtils servicioWebUtils;
	@Autowired
	CatalogoUtils catalogoUtils;

	/**
	 * Consulta los datos adicionales asociados con un acuerdo
	 * 
	 * @param agreementNumber
	 *            Número de acuerdo
	 * @param codeLine
	 *            Línea de código
	 * @param idGrp
	 *            Id GRP
	 * @param numSec
	 *            Número Sec
	 * @return Lista con los datos adicionales encontrados
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public List<DatoAdicionalBean> ejecutarTRN(CuentaBean cuentaBean) {

		ITRCONSUAMPLIARACTRN contexto = initializePeticion(cuentaBean);
		
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(ConsultaDatosAdicionalesServicio.class, contexto);

		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return new ArrayList<DatoAdicionalBean>();
            }        
        }
		
		return getRecords(respuesta.getResponseBansefi());
	}

	/**
	 * Obtiene todos los datos arrojados por {@link ResponseBansefi}
	 * 
	 * @param response
	 *            Datos arrojados por el servicio web
	 * @return LIsta de datos adicionales
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public List<DatoAdicionalBean> getRecords(ResponseBansefi response) {
		List<DatoAdicionalBean> result = new ArrayList<DatoAdicionalBean>();
		
		if (verificarRespuestaDatos(response)) {

			TRCONSUAMPLIARACEVTZ data = response.getOTRCONSUAMPLIARACTRN()
					.getTRCONSUAMPLIARACEVTZ();

			List<YFOTRSIDEXTACE> yfotrsidextace = data.getYFOTRSIDEXTACE();
			List<ACOTRSIDFREXTE> acotrsidfrexte = data.getACOTRSIDFREXTE();

			List<YFINFADICACE> infAdic = data.getYFINFADICACE();
			List<ACINFADICE> acinfadice = data.getACINFADICE();

			List<YFRLACUOE> yfrlacuoe = data.getYFRLACUOE();
			List<ACACRLCENTROE> acacrlcentroe = data.getACACRLCENTROE();

			List<YFINFADIPERSACE> yfinfadipersace = data
					.getYFINFADIPERSACE();
			List<ACINFADIOTRPERSE> acinfadiotrperse = data
					.getACINFADIOTRPERSE();

			List<YFOTRSPERSACE> yfotrspersace = data.getYFOTRSPERSACE();
			List<ACOTRSPERSE> acotrsperse = data.getACOTRSPERSE();

			try {
				extractYFOTRSIDEXTACE(yfotrsidextace, acotrsidfrexte,
						result);

				extractYFINFADICACE(infAdic, acinfadice, result);

				extractYFRLACUOE(yfrlacuoe, acacrlcentroe, result);

				extractYFINFADIPERSACE(yfinfadipersace, acinfadiotrperse,
						result);

				extractYFOTRSPERSACE(yfotrspersace, acotrsperse, result);
			} catch (Exception e) {
				LOGGER.error("Falla al obtener resultado.", e);
			}
		}
		

		return result;
	}

	public void extractYFOTRSIDEXTACE(List<YFOTRSIDEXTACE> responseModels,
			List<ACOTRSIDFREXTE> responseValues,
			List<DatoAdicionalBean> entities) {
		try {
			for (YFOTRSIDEXTACE data : responseModels) {
				if (isDataValid(data, "codnrbeen")) {
					try {
						DatoAdicionalBean datoAdicional = consultaDatosAdicionalesWrapper
								.wrappBean(data, "consultaYFOTRSIDEXTACE");

						datoAdicional.setInformacionAdicional(getDescription(
								CatalogoEnum.TP_OTRS_ID_EXT_AC,
								data.getCODOTRIDEXTAC()));

						datoAdicional.setEntityType("ACOTRSIDFREXTE");

						for (ACOTRSIDFREXTE value : responseValues) {
							if (isDataValid(value, "codnrbeen")
									&& data.getCODOTRIDEXTAC().equals(
											value.getCODOTRIDEXTAC())) {
								String valueString = value.getVALORACIDEXT()
										.trim();
								datoAdicional.setValor(valueString);
								datoAdicional.setOldValue(valueString);
								break;
							}
						}

						entities.add(datoAdicional);

					} catch (Exception e) {
						LOGGER.error("Ocurrió un error al extraer los datos. "
								+ e.getMessage());
						continue;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al ejecutar el método extractYFOTRSIDEXTACE."
					+ e.getMessage());
		}
	}

	public void extractYFINFADICACE(List<YFINFADICACE> responseModels,
			List<ACINFADICE> responseValues, List<DatoAdicionalBean> entities) {
		try {
			for (YFINFADICACE data : responseModels) {
				if (isDataValid(data, "codnrbeen")) {
					try {
						DatoAdicionalBean datoAdicional = consultaDatosAdicionalesWrapper
								.wrappBean(data, "consultaYFINFADICACE");

						datoAdicional.setInformacionAdicional(getDescription(
								CatalogoEnum.TP_INF_ADIC_AC,
								data.getCODINFADICAC()));

						datoAdicional.setEntityType("ACINFADICE");

						for (ACINFADICE value : responseValues) {
							if (isDataValid(value, "codnrbeen")
									&& data.getCODINFADICAC().equals(
											value.getCODINFADICAC())) {
								String valueString = value.getVALORACINFADIC()
										.trim();
								datoAdicional.setValor(valueString);
								datoAdicional.setOldValue(valueString);
								break;
							}
						}

						entities.add(datoAdicional);

					} catch (Exception e) {
						LOGGER.error("Ocurrió un error al extraer los datos. "
								+ e.getMessage());
						continue;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al ejecutar el método extractYFINFADICACE."
					+ e.getMessage());
		}
	}

	public void extractYFRLACUOE(List<YFRLACUOE> responseModels,
			List<ACACRLCENTROE> responseValues, List<DatoAdicionalBean> entities) {
		try {
			for (YFRLACUOE data : responseModels) {
				if (isDataValid(data, "codnrbeen")) {
					try {
						DatoAdicionalBean datoAdicional = consultaDatosAdicionalesWrapper
								.wrappBean(data, "consultaYFRLACUOE");
						
						if( data.getCODRLACUO() != null){
							datoAdicional.setIndicadorEstructura("3");
						}

						datoAdicional.setInformacionAdicional(getDescription(
								CatalogoEnum.TP_RL_AC_UO, data.getCODRLACUO()));

						datoAdicional.setEntityType("ACACRLCENTROE");

						for (ACACRLCENTROE value : responseValues) {
							if (isDataValid(value, "codnrbeen")
									&& data.getCODRLACUO().equals(
											value.getCODCENTUO())) {
								String valueString = value.getCODRLACUO()
										.trim();
								datoAdicional.setValor(valueString);
								datoAdicional.setOldValue(valueString);
								break;
							}
						}

						entities.add(datoAdicional);

					} catch (Exception e) {
						LOGGER.error("Ocurrió un error al extraer los datos. "
								+ e.getMessage());
						continue;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al ejecutar el método extractYFRLACUOE."
					+ e.getMessage());
		}
	}

	public void extractYFOTRSPERSACE(List<YFOTRSPERSACE> responseModels,
			List<ACOTRSPERSE> responseValues, List<DatoAdicionalBean> entities) {
		try {
			for (YFOTRSPERSACE data : responseModels) {
				if (isDataValid(data, "codnrbeen")) {
					try {
						DatoAdicionalBean datoAdicional = consultaDatosAdicionalesWrapper
								.wrappBean(data, "consultaYFOTRSPERSACE");

						datoAdicional.setInformacionAdicional(getDescription(
								CatalogoEnum.TP_INFADI_PERS_AC,
								data.getCODOTRSPERSAC()));

						datoAdicional.setEntityType("ACOTRSPERSE");

						for (ACOTRSPERSE value : responseValues) {
							if (isDataValid(value, "codnrbeen")
									&& data.getCODOTRSPERSAC().equals(
											value.getCODOTRSPERSAC())) {
								String valueString = value.getNOMBPERS().trim();
								datoAdicional.setValor(valueString);
								datoAdicional.setOldValue(valueString);
								break;
							}
						}

						entities.add(datoAdicional);

					} catch (Exception e) {
						LOGGER.error("Ocurrió un error al extraer los datos. "
								+ e.getMessage());
						continue;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al ejecutar el método extractYFOTRSPERSACE."
					+ e.getMessage());
		}
	}

	public void extractYFINFADIPERSACE(List<YFINFADIPERSACE> responseModels,
			List<ACINFADIOTRPERSE> responseValues,
			List<DatoAdicionalBean> entities) {
		try {
			for (YFINFADIPERSACE data : responseModels) {
				if (isDataValid(data, "codnrbeen")) {
					try {
						DatoAdicionalBean datoAdicional = consultaDatosAdicionalesWrapper
								.wrappBean(data, "consultaYFINFADIPERSACE");

						datoAdicional.setInformacionAdicional(getDescription(
								CatalogoEnum.TP_OTRS_PERS_AC,
								data.getCODINFADIOTPEAC()));

						datoAdicional.setEntityType("ACINFADIOTRPERSE");

						for (ACINFADIOTRPERSE value : responseValues) {
							if (isDataValid(value, "codnrbeen")
									&& data.getCODINFADIOTPEAC().equals(
											value.getCODINFADIOTPEAC())) {
								String valueString = value.getVALACINFADIPER()
										.trim();
								datoAdicional.setValor(valueString);
								datoAdicional.setOldValue(valueString);
								break;
							}
						}

						entities.add(datoAdicional);

					} catch (Exception e) {
						LOGGER.error("Ocurrió un error al extraer los datos. "
								+ e.getMessage());
						continue;
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al ejecutar el método extractYFINFADIPERSACE."
					+ e.getMessage());
		}
	}

	public String getDescription(CatalogoEnum search, String entityId) {
		if (search != null) {

			List<CatalogoBean> catalogData = catalogoUtils.getCatalogo(search);
			for (CatalogoBean catalog : catalogData) {
				if (catalog.getClaveFila().equals(entityId))
					return catalog.getDescripcionL();
			}
		}

		return "";
	}

	/**
	 * Valida que objeto contenga la propiedad entidad y que su valor no sea una
	 * cadena vacía
	 * 
	 * @param responseModel
	 *            objeto que resgresa {@link ResponseBansefi} con los resultados
	 *            de la consulta
	 * @param <T>
	 *            Clase que debe contener la propiedad entidad
	 * @return <code>true</code> si el objeto contiene información de la
	 *         entidad; en caso contrario, devuelve <code>false</code>
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public <T> Boolean isDataValid(T responseModel) {
		try {
			for (Field field : responseModel.getClass().getDeclaredFields()) {
				if (field.getName().toLowerCase().equals("codnrbeen")) {
					for (Method method : responseModel.getClass()
							.getDeclaredMethods()) {
						if (method.getName().toLowerCase()
								.equals("getcodnrbeen")) {
							return !method.invoke(responseModel).toString()
									.trim().equals("");
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Falla al validar datos válidos.", e);
			return false;
		}

		return false;
	}

	/**
	 * Valida que una propiedad de tipo {@link String} no sea una cadena vacía
	 * 
	 * @param responseModel
	 *            modelo del cual se obtiene la propiedad a validar
	 * @param fieldToValidate
	 *            nombre de la propeidad que será validada
	 * @return <code>true</code> sí la cadena no esta vacía; en caso contrario,
	 *         devuelve <code>false</code>
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public <T> Boolean isDataValid(T responseModel, String fieldToValidate) {
		Boolean isValid = false;
		try {
			Field fielToValidate = responseModel.getClass().getDeclaredField(
					fieldToValidate);

			if (fielToValidate != null) {

				for (Method method : responseModel.getClass()
						.getDeclaredMethods()) {
					if (method.getName().toLowerCase()
							.equals("get" + fieldToValidate.toLowerCase())) {
						return !method.invoke(responseModel).toString().trim()
								.equals("");
					}
				}
			}

		} catch (Exception e) {
			LOGGER.error("Ocurrió un error al ejecutar el método isDataValid.");
			return false;
		}

		return isValid;
	}

	/**
	 * Inicializa el objeto para de petición al servicio web
	 * 
	 * @param agreementNumber
	 *            Número de acuerdo
	 * @param codeLine
	 *            Línea de código
	 * @param idGrp
	 *            Id GRP
	 * @param numSec
	 *            Número Sec
	 * @return {@link TRCONSUAMPLIARACTRNI} cargado con los valores para la
	 *         consulta al servicio web
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;agadea@uanlmexico.mx&gt;
	 */
	public ITRCONSUAMPLIARACTRN initializePeticion(CuentaBean cuentaBean) {

		ITRCONSUAMPLIARACTRN contexto = new ITRCONSUAMPLIARACTRN();
		STDTRNIPARMV cuerpoContexto = new STDTRNIPARMV();
		TRCONSUAMPLIARACEVTY datosEntrada = new TRCONSUAMPLIARACEVTY();

		ACACP acacp = new ACACP();
		ACLINEAGRUPOV acLine = new ACLINEAGRUPOV();

		acacp.setCODNRBEEN(super.getEntidad());
		acacp.setNUMSECAC(cuentaBean.getNumeroCuenta());

		acLine.setCODLINEA(cuentaBean.getCodLinea());
		acLine.setIDGRPPD(cuentaBean.getIdGrupoProducto());

		datosEntrada.setACACP(acacp);
		datosEntrada.setACLINEAGRUPOV(acLine);

		cuerpoContexto.setCODTX(CodTxConstants.COD_TR_CONSU_AMPLIAR_AC_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());

		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRCONSUAMPLIARACEVTY(datosEntrada);

		super.initialize(contexto);

		return contexto;
	}

	/**
	 * Invoca el servicio web que es pasado como parámetro y regresa la
	 * respuesta que arroja el servicio web
	 * 
	 * @param contexto
	 *            {@link TRCONSUAMPLIARACTRNI} con los parámetros de la petición
	 *            al servicio web
	 * @return {@link EjecutarResult} con los datos que devuelve el servicio web
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;agadea@uanlmexico.mx&gt;
	 */
	public EjecutarResult ejecutarWS(ITRCONSUAMPLIARACTRN contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaDatosAdicionalesServicio.class, contexto);

		} catch (NoControlableException ex) {
			throw new NoControlableException("Erro al invocar el servicio web",
					ex);
		}

		return respuesta;
	}

	/**
	 * Valida los datos contenidos en la respuesta que arroja el servicio web
	 * 
	 * @param response
	 *            {@link ResponseBansefi} objeto que devuelve el servicio web
	 * @return <code>true</code> sí la respuesta contiene datos, en caso
	 *         contrario;<code>false</code>
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	private Boolean verificarRespuestaDatos(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null
				&& response.getOTRCONSUAMPLIARACTRN() != null
				&& response.getOTRCONSUAMPLIARACTRN().getTRCONSUAMPLIARACEVTZ() != null
				&& response.getOTRCONSUAMPLIARACTRN().getTRCONSUAMPLIARACEVTZ() != null) {
			noNulo = true;
		}

		return noNulo;
	}
	

	public static String capitalize(String s) {
		if (s.length() == 0)
			return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
}