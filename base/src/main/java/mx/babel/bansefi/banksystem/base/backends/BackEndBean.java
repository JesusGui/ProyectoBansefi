package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.EstadoEnum;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase que contiene la funcionalidad comun en las llamadas a Host.
 * 
 * @author
 * 
 */
@Component
public class BackEndBean implements Serializable {

	private static final long serialVersionUID = -4543342362906959566L;

	public final static String ESTATUS_OK = "0";
	public final static int RETURN_COD_OK = 1;
	public final static int RETURN_COD_SIN_DATOS = 7;
	public final static int SCROLLABLE_OCCURS = 50;

	private static final Logger LOGGER = LogManager.getLogger(BackEndBean.class
			.getName());

	@Autowired
	ContextoUtils contexto;

	public String getEntidad() {
		return contexto.getEntidad();
	}

	public String getRegion() {
		return contexto.getRegion();
	}

	public String getSucursal() {
		return contexto.getSucursal();
	}

	public String getTerminal() {
		return contexto.getTerminal();
	}

	public String getPuesto() {
		return contexto.getPuesto();
	}

	public String getPlazaBancaria() {
		return contexto.getPlazaBancaria();
	}

	public String getUsuarioId() {
		return contexto.getId();
	}

	public String getNombreUsuario() {
		return contexto.getNombre();
	}

	public String getPassword() {
		return contexto.getPwd();
	}

	public String getIp() {
		return contexto.getIp();
	}

	public Date getFechaSistema() {
		return contexto.getFechaContableActual();
	}

	public int getFechaSistemaInteger() {
		final IntegerToDateConverter itdConverter = new IntegerToDateConverter();
		return itdConverter.convertFrom(this.getFechaSistema());
	}

	/**
	 * Metodo que se encarga de inicializar todas las variables de un objeto.
	 * Aquellas variables tipo Lista son inicializadas como listas sin
	 * elementos.
	 * 
	 * @param object
	 *            Objeto que debe poblarse.
	 */
	public void initialize(final Object object) {
		final Set<String> packages = new HashSet<String>();
		packages.add(object.getClass().getPackage().toString()
				.replace("package ", ""));
		try {
			this.initializeData(object, packages);
		} catch (final IllegalArgumentException | IllegalAccessException e) {
			throw new NoControlableException(
					"Ha ocurrido un error al inicializar el bean de datos", e);
		}
	}

	/**
	 * Metodo que se encarga de inicializar todas las variables de un objeto.
	 * Aquellas variables tipo Lista son inicializadas como listas sin
	 * elementos.
	 * 
	 * @param object
	 *            Objeto que debe poblarse.
	 * @param packages
	 *            Solo se inciaran las variables primitivas y las que
	 *            pertenezcan a los paquetes indicados en este listado
	 */
	public void initialize(final Object object, final Set<String> packages) {
		try {
			this.initializeData(object, packages);
		} catch (final IllegalArgumentException | IllegalAccessException e) {
			throw new NoControlableException(
					"Ha ocurrido un error al inicializar el bean de datos", e);
		}
	}

	private void initializeData(final Object object, final Set<String> packages)
			throws IllegalArgumentException, IllegalAccessException {
		final Field[] fields = object.getClass().getDeclaredFields();

		for (final Field field : fields) {
			final String fieldName = field.getName();
			final Class<?> fieldClass = field.getType();

			// skip primitives
			if (fieldClass.isPrimitive()) {
				LOGGER.debug("Skipping primitive: " + fieldName);
				continue;
			}

			// skip if not in packages
			boolean inPackage = false;
			for (final String pack : packages) {
				if (fieldClass.getPackage() != null) {
					if (fieldClass.getPackage().getName().startsWith(pack)) {
						inPackage = true;
					}
				}
			}

			// allow access to private fields
			final boolean isAccessible = field.isAccessible();
			field.setAccessible(true);

			Object fieldValue = field.get(object);
			if (fieldValue == null) {
				LOGGER.debug("Initializing: " + fieldName);
				try {
					if ("java.util.List".equalsIgnoreCase(fieldClass.getName())) {
						field.set(object, new ArrayList<>());
					} else if ("java.math.BigDecimal"
							.equalsIgnoreCase(fieldClass.getName())) {
						field.set(object, null);
					} else {
						field.set(object, fieldClass.newInstance());
					}
				} catch (IllegalArgumentException | IllegalAccessException
						| InstantiationException e) {
					LOGGER.debug(
							"Could not initialize "
									+ fieldClass.getSimpleName(), e);
				}
			} else {
				LOGGER.debug("Field is already initialized: " + fieldName);
			}

			if (!inPackage) {
				String skipped = fieldClass.toString();
				if (fieldClass.getPackage() != null) {
					skipped = fieldClass.getPackage().getName();
				}
				LOGGER.debug("Skipping package: " + skipped);
				continue;
			}

			fieldValue = field.get(object);

			// reset accessible
			field.setAccessible(isAccessible);

			// recursive call for sub-objects
			initialize(fieldValue, packages);
		}

	}

	/**
	 * Metodo que trata el objeto de respuesta del WS. Se encarga de comprobar
	 * el valor del ESTATUS para verificar que todo ha ido correcto, en caso
	 * contrario genera un error incluyendo el detalle indicado en el mensaje del WS.
	 * 
	 * @param object respuesta del WS
	 */
	protected void verificaRespuestaWS(final Object object) {
		if (null != object) {
			final Object estatus = this.getObjectRecursively("getESTATUS", object);
			if (estatus != null) {
				if (!ESTATUS_OK.equals((String) estatus)) {
					
					final String codigo = (String) this.getObjectRecursively("getCODIGO", object);
					final String mensaje = (String) this.getObjectRecursively("getMENSAJE", object);
					
					if(!StringUtils.isBlank(codigo) && !StringUtils.isBlank(mensaje)){
						throw new ControlableException(codigo.toString().trim(), mensaje.toString().trim());
					}else{
						throw new NoControlableException(
								"Error al verificar la respuesta del servidor",
								"Codigo y mensaje de error no informados");
					}					
				}
			} else {
				throw new NoControlableException(
						"Error al verificar la respuesta del servidor",
						"Parámetro estatus no encontrado en la respuesta");
			}
		} else {
			throw new NoControlableException(
					"Error al verificar la respuesta del servidor",
					"El objecto ResponseBansefi es nulo");
		}

	}
	
	/**
	 * Metodo que trata el objeto de respuesta del WS. Se encarga de comprobar
	 * el valor RTRNCD para verificar que todo ha ido correcto, en caso
	 * contrario genera un error incluyendo el detalle indicado en
	 * stdtrnmsjparmv.
	 * 
	 * @param object
	 *            respuesta del WS
	 */
	protected void verificaRespuesta(final Object object) {
		if (null != object) {
			final Object estatus = this.getObjectRecursively("getESTATUS", object);
			if (estatus != null) {
				if (!ESTATUS_OK.equals((String) estatus)) {
					Object responseBansefi = null;
					try{
						responseBansefi = this.getObjectRecursively("getRESPONSEBANSEFI", object);
					}catch(IllegalArgumentException iae){
						final String codigo = (String)this.getObjectRecursively("getCODIGO", object);
						final String mensaje = (String)this.getObjectRecursively("getMENSAJE", object);
						throw new ControlableException(codigo, mensaje);
					}
					if (responseBansefi !=null){
						final Object rtrncd = this.getObjectRecursively("getRTRNCD", responseBansefi);
						if (rtrncd != null) {
							if ((int) rtrncd != RETURN_COD_OK) {
								final String codigo = (String)this.getObjectRecursively("getCODIGO", object);
								final String mensaje = (String)this.getObjectRecursively("getMENSAJE", object);
								
								if(!StringUtils.isBlank(codigo) && !StringUtils.isBlank(mensaje)){
									EstadoEnum.mensajesError((int) rtrncd, codigo, mensaje);
								}else{
									this.verificaResponseBansefi(responseBansefi);
								}
							}
						} else {
							throw new NoControlableException(
									"Error al verificar la respuesta del servidor",
									"Parámetro rtrncd no encontrado en la respuesta");
						}	
					} else {
						throw new NoControlableException(
								"Error al verificar la respuesta del servidor",
								"Parámetro responseBansefi no encontrado en la respuesta");
					}	
				}
			} else {
				throw new NoControlableException(
						"Error al verificar la respuesta del servidor",
						"Parámetro estatus no encontrado en la respuesta");
			}
		} else {
			throw new NoControlableException(
					"Error al verificar la respuesta del servidor",
					"El objecto ResponseBansefi es nulo");
		}
	}
	
	
	/**
	 * Metodo que trata el objeto de respuesta del WS. Se encarga de comprobar
	 * el valor RTRNCD para verificar que todo ha ido correcto, en caso
	 * contrario genera un error incluyendo el detalle indicado en
	 * stdtrnmsjparmv.
	 * 
	 * @param object
	 *            respuesta del WS
	 */
	protected void verificaResponseBansefi(final Object object) {
		if (null != object) {
			final Object rtrncd = this
					.getObjectRecursively("getRTRNCD", object);
			if (rtrncd != null) {
				if ((int) rtrncd != RETURN_COD_OK) {
					final Object stdtrnmsjparmvList = this
							.getObjectRecursively("getSTDTRNMSJPARMV", object);
					if (null != stdtrnmsjparmvList) {
						final Map<Integer, String> detailMap = new HashMap<>();
						if (stdtrnmsjparmvList instanceof ArrayList<?>) {
							for (final Object stdtrnmsjparmv : (ArrayList<?>) stdtrnmsjparmvList) {
								final int textCode = (int) this
										.getObjectByReflection("getTEXTCODE",
												stdtrnmsjparmv);
								final String textArg1 = (String) this
										.getObjectByReflection("getTEXTARG1",
												stdtrnmsjparmv);
								detailMap.put(textCode, textArg1);
							}
						} else {
							final int textCode = (int) this
									.getObjectByReflection("getTEXTCODE",
											stdtrnmsjparmvList);
							final String textArg1 = (String) this
									.getObjectByReflection("getTEXTARG1",
											stdtrnmsjparmvList);
							detailMap.put(textCode, textArg1);
						}
						EstadoEnum.mensajesError("trn", (int) rtrncd, detailMap);
					} else {
						throw new NoControlableException(
								"Error al verificar la respuesta del servidor",
								"Parámetro stdtrnmsjparmv no encontrado en la respuesta");
					}
				}
			} else {
				throw new NoControlableException(
						"Error al verificar la respuesta del servidor",
						"Parámetro rtrncd no encontrado en la respuesta");
			}
		} else {
			throw new NoControlableException(
					"Error al verificar la respuesta del servidor",
					"El objecto ResponseBansefi es nulo");
		}

	}

	private Object getObjectRecursively(final String field, final Object object) {
		Object response = null;
		if (null != object) {
			LOGGER.debug(field + ":" + object.getClass());
			response = getObjectByReflection(field, object);
			if (response == null) {
				if (object instanceof List<?>) {
					for (final Object obj : (List<?>) object) {
						response = this.getObjectRecursively(field, obj);
						if (null != response) {
							break;
						}
					}
				} else {
					final Method[] m = object.getClass().getMethods();
					for (final Method method : m) {
						try {
							if (method.getName().startsWith("get")) {
								LOGGER.debug(method.getName());
								response = this.getObjectRecursively(field,
										method.invoke(object));
								if (null != response) {
									break;
								}
							}
						} catch (final IllegalAccessException e) {
							LOGGER.error("Could not determine method: "
									+ method.getName());
						} catch (final InvocationTargetException e) {
							LOGGER.error("Could not determine method: "
									+ method.getName());
						}
					}
				}
			}
		}
		return response;
	}

	private Object getObjectByReflection(final String field, final Object object) {
		if (null != object && null != object.getClass()
				&& null != object.getClass().getMethods()) {
			final Method[] m = object.getClass().getMethods();
			for (final Method method : m) {
				if (StringUtils.equalsIgnoreCase(field, method.getName())) {
					try {
						return method.invoke(object);
					} catch (final IllegalAccessException e) {
						LOGGER.error("Could not determine method: "
								+ method.getName());
					} catch (final InvocationTargetException e) {
						LOGGER.error("Could not determine method: "
								+ method.getName());
					}
				}
			}
		}
		return null;
	}
	
}