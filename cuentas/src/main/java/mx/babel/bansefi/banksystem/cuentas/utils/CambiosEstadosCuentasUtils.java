package mx.babel.bansefi.banksystem.cuentas.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDocumentosBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DocumentoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.TipoRelacionPersonaCuenta;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.EstadosCuentaEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.EstadosCuentaEnumUtils;
import mx.babel.bansefi.banksystem.base.utils.FechaUtils;
import mx.babel.bansefi.banksystem.cuentas.backends.AnularConstitucionAcuerdoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.AprobarCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.CancelacionAcuerdoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.CancelacionAcuerdoSolicitadoBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConstituirCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaNivelAcuerdoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaNivelClienteBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaNiveladoraCuentaBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.EsperaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.backends.RenegociacionAcuerdoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.RetrocesionCancelacionAcuerdoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.RetrocesionConstitucionAcuerdoBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.SuspenderCuentaBackEnd;
import mx.babel.bansefi.banksystem.cuentas.beans.CancelacionCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.EsperaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.SuspenderCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CambiosEstadosCuentasUtils {

	private static final Logger LOGGER = LogManager
			.getLogger(CambiosEstadosCuentasUtils.class.getName());

	@Autowired
	ContextoUtils contextoUtils;

	// Metodos utilizados para consultar los posibles cambios de estados en
	// funcion del estado del acuerdo
	public List<EstadosCuentaEnum> getCodigosCambiosEstadosSolicitado() {
		// return Arrays.asList(EstadosCuentaEnum.ACTIVO,
		// EstadosCuentaEnum.APROBADO, EstadosCuentaEnum.CANCELADO);
		return Arrays.asList(EstadosCuentaEnum.ACTIVO,
				EstadosCuentaEnum.CANCELADO);
	}

	public List<EstadosCuentaEnum> getCodigosCambiosEstadosAprobado() {
		return Arrays.asList(EstadosCuentaEnum.SOLICITADO,
				EstadosCuentaEnum.ACTIVO, EstadosCuentaEnum.CANCELADO);
	}

	public List<EstadosCuentaEnum> getCodigosCambiosEstadosActivo(
			final CuentaBean cuentaBean) {
		// Para la opcion de cambio a aprobado hay que validar
		// Si no es del mismo dia contable o si es de otro centro, no permite
		// cambiar a aprobado
		// if (!isMismoDia(cuentaBean)
		// || !cuentaBean.getCentro().equals(contextoUtils.getSucursal())) {
		// return Arrays.asList(EstadosCuentaEnum.SUSPENDIDO,
		// EstadosCuentaEnum.CANCELADO);
		// } else {
		// return Arrays.asList(EstadosCuentaEnum.APROBADO,
		// EstadosCuentaEnum.SOLICITADO, EstadosCuentaEnum.SUSPENDIDO,
		// EstadosCuentaEnum.CANCELADO);
		return Arrays.asList(EstadosCuentaEnum.SOLICITADO,
				EstadosCuentaEnum.CANCELADO);
		// }
	}

	public List<EstadosCuentaEnum> getCodigosCambiosEstadosSuspendido() {
		// return Arrays.asList(EstadosCuentaEnum.ACTIVO);
		return Arrays.asList();
	}

	public List<EstadosCuentaEnum> getCodigosCambiosEstadosCancelado() {
		return Arrays.asList(EstadosCuentaEnum.ACTIVO);
	}

	public List<EstadosCuentaEnum> getCodigosCambiosEstadosVencido() {
		return Arrays.asList(EstadosCuentaEnum.CANCELADO);
	}

	@Autowired
	CatalogoUtils catalogoUtils;

	@Autowired
	EstadosCuentaEnumUtils estadosCuentaEnumUtils;

	@Autowired
	AprobarCuentaBackEnd aprobarCuentaBackEnd;

	@Autowired
	CancelacionAcuerdoBackend cancelacionAcuerdoBackend;

	@Autowired
	RenegociacionAcuerdoBackend renegociacionAcuerdoBackend;

	@Autowired
	ConstituirCuentaBackEnd constituirCuentaBackEnd;

	@Autowired
	RetrocesionCancelacionAcuerdoBackend retrocesionCancelacionAcuerdoBackend;

	@Autowired
	AnularConstitucionAcuerdoBackend anularConstitucionAcuerdoBackend;

	@Autowired
	RetrocesionConstitucionAcuerdoBackend retrocesionConstitucionAcuerdoBackend;

	@Autowired
	SuspenderCuentaBackEnd suspenderCuentaBackEnd;

	@Autowired
	CancelacionAcuerdoSolicitadoBackEnd cancelacionAcuerdoSolicitadoBackEnd;

	// TRN que cambia el nivel de la persona al contratar una cuenta de nivel 4
	@Autowired
	ConsultaNiveladoraCuentaBackend consultaNiveladoraCuentaBackend;

	@Autowired
	EsperaBackEnd esperaBackEnd;

	/**
	 * @return List<CatalogoBean> utilizado para cargar el combo de cambios de
	 *         estados de la cuenta
	 * 
	 */
	public List<EstadosCuentaEnum> obtenerCambiosEstadosCuentaDisponibles(
			final CuentaBean cuentaBean) {
		LOGGER.debug("Obtenemos los valores de combo de cambio de estados: ENTRADA");
		List<EstadosCuentaEnum> cambiosEstadosPosibles = null;

		if (cuentaBean.getEstadoEnum().equals(EstadosCuentaEnum.SOLICITADO)) {
			cambiosEstadosPosibles = getCodigosCambiosEstadosSolicitado();
		} else if (cuentaBean.getEstadoEnum()
				.equals(EstadosCuentaEnum.APROBADO)) {
			cambiosEstadosPosibles = getCodigosCambiosEstadosAprobado();
		} else if (cuentaBean.getEstadoEnum().equals(EstadosCuentaEnum.ACTIVO)) {
			cambiosEstadosPosibles = getCodigosCambiosEstadosActivo(cuentaBean);
		} else if (cuentaBean.getEstadoEnum().equals(
				EstadosCuentaEnum.SUSPENDIDO)) {
			cambiosEstadosPosibles = getCodigosCambiosEstadosSuspendido();
		} else if (cuentaBean.getEstadoEnum().equals(EstadosCuentaEnum.VENCIDO)) {
			cambiosEstadosPosibles = getCodigosCambiosEstadosVencido();
		} else if (cuentaBean.getEstadoEnum().equals(
				EstadosCuentaEnum.CANCELADO)) {
			cambiosEstadosPosibles = getCodigosCambiosEstadosCancelado();
		}

		LOGGER.debug("Obtenemos los valores del combo de cambio de estados: SALIDA");
		return cambiosEstadosPosibles;
	}

	/**
	 * @return List<CatalogoBean> utilizado para cargar el combo de cambios de
	 *         estados de la cuenta
	 * 
	 */
	public Map<Boolean, String> cambiarEstadoCuenta(
			final EstadosCuentaEnum estadoActualCuenta,
			final EstadosCuentaEnum nuevoEstadoCuenta, final Object cuentaBean) {
		LOGGER.debug("Cambiamos el estado de la cuenta: ENTRADA");
		final Map<Boolean, String> respuesta = new HashMap<Boolean, String>();

		// TODO: Revisar el funcionamiento de cada caso, obtener los backend
		// pendientes y verificar si hay estado
		// que solo se rendericen en funcion del tipo de producto o asi.

		// CAMBIOS DE ESTADOS DE CUENTAS EN ESTADO SOLICITADO
		if (estadoActualCuenta.equals(EstadosCuentaEnum.SOLICITADO)) {
			if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.APROBADO)) {
				try {
					this.aprobarCuentaBackEnd
							.ejecutarTRN(((CuentaBean) cuentaBean)
									.getNumeroCuenta());
					respuesta
							.put(Boolean.TRUE,
									"La cuenta se volvió a estado Aprobado correctamente.");
				} catch (final NoControlableException e) {
					respuesta
							.put(Boolean.FALSE,
									e.getMensajeUsuario()
											+ " "
											+ e.getMensajeDetalle());
				} catch (final ControlableException ce) {
					final String detalle = ce.getMensajeUsuario() + " "
							+ ce.getMensajeDetalle();
					throw new ControlableException(
							"ERROR.",
							detalle, ce.getRtncod());
				}
			} else if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.ACTIVO)) {
				boolean pasoCompleto = false;
				try {
					this.aprobarCuentaBackEnd
							.ejecutarTRN(((CuentaBean) cuentaBean)
									.getNumeroCuenta());
					pasoCompleto = true;
				} catch (final NoControlableException e) {
					respuesta
							.put(Boolean.FALSE,
									e.getMensajeUsuario()
											+ " "
											+ e.getMensajeDetalle());
				} catch (final ControlableException ce) {
					final String detalle = ce.getMensajeUsuario()
							+ " " + ce.getMensajeDetalle();
					throw new ControlableException(
							"ERROR",
							detalle, ce.getRtncod());
				}

				if (pasoCompleto) {
					try {
						constituirAcuerdo(0, (CuentaBean) cuentaBean, respuesta);
					} catch (final NoControlableException e) {
						respuesta
								.put(Boolean.FALSE,
										e.getMensajeUsuario()
												+ " "
												+ e.getMensajeDetalle());
					} catch (final ControlableException ce) {
						final String detalle = ce.getMensajeUsuario() + " "
								+ ce.getMensajeDetalle();
						throw new ControlableException(
								"ERROR",
								detalle, ce.getRtncod());
					}
					// TODO control nivel
					final ClienteBean persona = encontrarTitular((CuentaBean) cuentaBean);
					if (this.hayQueNivelarCuenta((CuentaBean) cuentaBean,
							persona)) {
						consultaNiveladoraCuentaBackend.ejecutarTRN(
								((CuentaBean) cuentaBean).getNumeroCuenta(),
								persona.getIdInterna());
					}
				}
			} else if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.CANCELADO)) {
				try {
					this.cancelacionAcuerdoSolicitadoBackEnd.ejecutarTRN((CancelacionCuentaBean)cuentaBean);
					respuesta.put(Boolean.TRUE,
							"La cuenta se canceló correctamente.");
				} catch (final NoControlableException e) {
					respuesta
							.put(Boolean.FALSE,
									e.getMensajeUsuario()
											+ " "
											+ e.getMensajeDetalle());
				} catch (final ControlableException ce) {
					final String detalle = ce.getMensajeUsuario() + " "
							+ ce.getMensajeDetalle();
					throw new ControlableException(
							"ERROR.",
							detalle, ce.getRtncod());
				}
			}
		}

		// CAMBIOS DE ESTADOS DE CUENTAS EN ESTADO APROBADO
		else if (estadoActualCuenta.equals(EstadosCuentaEnum.APROBADO)) {
			if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.SOLICITADO)) {
				try {
					this.renegociacionAcuerdoBackend
							.ejecutarTRN(((CuentaBean) cuentaBean)
									.getNumeroCuenta());
					respuesta
							.put(Boolean.TRUE,
									"La cuenta se volvió a estado Solicitado correctamente.");
				} catch (final NoControlableException e) {
					respuesta
							.put(Boolean.FALSE,
									e.getMensajeUsuario()
											+ " "
											+ e.getMensajeDetalle());
				}

			} else if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.ACTIVO)) {
				try {
					constituirAcuerdo(0, (CuentaBean) cuentaBean, respuesta);
				} catch (final NoControlableException e) {
					respuesta
							.put(Boolean.FALSE,
									e.getMensajeUsuario()
											+ " "
											+ e.getMensajeDetalle());
				}
				// TODO control nivel
				final ClienteBean persona = encontrarTitular((CuentaBean) cuentaBean);
				if (this.hayQueNivelarCuenta((CuentaBean) cuentaBean, persona)) {
					consultaNiveladoraCuentaBackend.ejecutarTRN(
							((CuentaBean) cuentaBean).getNumeroCuenta(),
							persona.getIdInterna());
				}
			} else if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.CANCELADO)) {
				cancelarAcuerdo(1, (CancelacionCuentaBean) cuentaBean,
						respuesta);
			}
		}

		// CAMBIOS DE ESTADOS DE CUENTAS EN ESTADO ACTIVO
		else if (estadoActualCuenta.equals(EstadosCuentaEnum.ACTIVO)) {
			if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.APROBADO)) {
				// Valida que el centro del contexto de sesion y la cuenta, sea
				// el mismo
				// if
				// (cuentaBean.getCentro().equals(contextoUtils.getSucursal()))
				// {
				// Validar si la fecha de constitucion es la misma que la
				// fecha
				// contable actual
				// if (isMismoDia(cuentaBean)) {
				// RET_CONST_TOTAL
				try {
					this.retrocesionConstitucionAcuerdoBackend
							.ejecutarTRN((CuentaBean) cuentaBean);
					respuesta
							.put(Boolean.TRUE,
									"La cuenta se volvió a estado Aprobado correctamente.");
				} catch (final NoControlableException e) {
					respuesta
							.put(Boolean.FALSE,
									e.getMensajeUsuario()
											+ " "
											+ e.getMensajeDetalle());
				}
				// } else {
				// respuesta
				// .put(Boolean.FALSE,
				// "Solo se puede realizar esta modificacion en el mismo dia de la constitucion de la cuenta");
				// }
				// } else {
				// respuesta
				// .put(Boolean.FALSE,
				// "No es posible efectuar el cambio de estado, ya que esta cuenta pertenece a otro centro ("
				// + cuentaBean.getCentro()
				// + "). Centro actual: "
				// + contextoUtils.getSucursal());
				// }
			} else if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.SOLICITADO)) {
				boolean pasoCompleto = false;
				try {
					this.anularConstitucionAcuerdoBackend
							.ejecutarTRN((CuentaBean)cuentaBean, true);
					this.retrocesionConstitucionAcuerdoBackend
							.ejecutarTRN((CuentaBean) cuentaBean);
					
					pasoCompleto = true;
				} catch (final NoControlableException e) {
					respuesta
							.put(Boolean.FALSE,
									e.getMensajeUsuario()
											+ " "
											+ e.getMensajeDetalle());
				} catch (final ControlableException ce) {
					final String detalle = ce.getMensajeUsuario()
							+ " " + ce.getMensajeDetalle();
					throw new ControlableException(
							"ERROR",
							detalle, ce.getRtncod());
				}

				if (pasoCompleto) {
					try {
						this.renegociacionAcuerdoBackend
								.ejecutarTRN(((CuentaBean) cuentaBean)
										.getNumeroCuenta());
						respuesta
								.put(Boolean.TRUE,
										"La cuenta se volvió a estado Solicitado correctamente.");
					} catch (final NoControlableException e) {
						respuesta
								.put(Boolean.FALSE,
										e.getMensajeUsuario()
												+ " "
												+ e.getMensajeDetalle());
					} catch (final ControlableException ce) {
						final String detalle = " Detalle: "
								+ ce.getMensajeUsuario() + " "
								+ ce.getMensajeDetalle();
						throw new ControlableException(
								"ERROR",
								detalle, ce.getRtncod());
					}
				}
			} else if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.CANCELADO)) {
				cancelarAcuerdo(1, (CancelacionCuentaBean) cuentaBean,
						respuesta);
			} else if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.SUSPENDIDO)) {
				final SuspenderCuentaBean suspenderCuentaBean = new SuspenderCuentaBean();
				this.suspenderCuentaBackEnd.ejecutarTRN(suspenderCuentaBean);
			}
			// else if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.SOLICITADO))
			// {
			// // TODO: Verificar si aplica
			// // TR_CONSU_ANULAR_AC_TRN
			// this.anularConstitucionAcuerdoBackend.ejecutarTRN(numCuenta);
			// }
		}

		// CAMBIOS DE ESTADOS DE CUENTAS EN ESTADO SUSPENDIDO
		else if (estadoActualCuenta.equals(EstadosCuentaEnum.SUSPENDIDO)) {
			if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.ACTIVO)) {

			}
		}

		// CAMBIOS DE ESTADOS DE CUENTAS EN ESTADO CANCELADO
		else if (estadoActualCuenta.equals(EstadosCuentaEnum.CANCELADO)) {
			if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.ACTIVO)) {
				// // TR_CONSU_ANULAR_AC_TRN
				// this.anularConstitucionAcuerdoBackend.ejecutarTRN(cuentaBean
				// .getNumeroCuenta());
				try {
					this.retrocesionCancelacionAcuerdoBackend
							.ejecutarTRN(((CuentaBean) cuentaBean)
									.getNumeroCuenta());
					respuesta
							.put(Boolean.TRUE,
									"La cuenta se volvió a estado Activo correctamente.");
				} catch (final NoControlableException e) {
					respuesta
							.put(Boolean.FALSE,
									e.getMensajeUsuario()
											+ " "
											+ e.getMensajeDetalle());
				}
			}
		}

		// CAMBIOS DE ESTADOS DE CUENTAS EN ESTADO VENCIDO
		else if (estadoActualCuenta.equals(EstadosCuentaEnum.VENCIDO)) {
			if (nuevoEstadoCuenta.equals(EstadosCuentaEnum.CANCELADO)) {
				cancelarAcuerdo(1, (CancelacionCuentaBean) cuentaBean,
						respuesta);
			}
		}

		LOGGER.debug("Cambiamos el estado de la cuenta: SALIDA");
		return respuesta;
	}

	/**
	 * Función que ejecuta una lógica para consituir acuerdos
	 * 
	 * @param nLlamadas
	 * @param cuentaBean
	 */
	public void constituirAcuerdo(final int nLlamadas,
			final CuentaBean cuentaBean, final Map<Boolean, String> respuesta) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (final InterruptedException e) {
		}
		try {
			this.constituirCuentaBackEnd.ejecutarTRN(cuentaBean
					.getNumeroCuenta(), contextoUtils.getFechaContableActual(),
					ConstantesFuncionales.isMediosPago(
							cuentaBean.getCodLinea(),
							cuentaBean.getIdGrupoProducto()),
					ConstantesFuncionales.isCredito(cuentaBean.getCodLinea(),
							cuentaBean.getIdGrupoProducto()));

			respuesta.put(Boolean.TRUE,
					"La cuenta se volvió a estado Activo correctamente.");

		} catch (final ControlableException ex) {
			if (ex.getRtncod() == 18) {
				if (nLlamadas < 2) {
					constituirAcuerdo(nLlamadas + 1, cuentaBean, respuesta);
				} else {
					respuesta
							.put(Boolean.FALSE,
									ex.getMensajeDetalle());
				}
			} else {
				respuesta
						.put(Boolean.FALSE,
								ex.getMensajeDetalle());
			}
		}
	}

	/**
	 * Función que implimenta logica de cancelación igual a TCB
	 * 
	 * @param nLlamadas
	 * @param cuentaBean
	 * @param respuesta
	 */
	public void cancelarAcuerdo(final int nLlamadas,
			final CancelacionCuentaBean cuentaBean,
			final Map<Boolean, String> respuesta) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (final InterruptedException e) {
		}

		switch (nLlamadas) {
		case 1:
			cuentaBean.setRespuestaSincrona("S");
			break;
		case 2:
			cuentaBean.setRespuestaSincrona("N");
			break;
		case 3:
			// TODO: Si es el tercer intento primero ejecuta TRN de espera
			// try {
			// esperaBackEnd.ejecutarTRN(cuentaBean.getEsperaBean());
			//
			// for (int i = 0; i < cuentaBean.getEsperaBean().getFsIntentos() -
			// 1
			// && !cuentaBean.getEsperaBean().getFsEstado()
			// .equals("S")
			// && !cuentaBean.getEsperaBean().getFsEstado()
			// .equals("L"); i++) {
			// try {
			// TimeUnit.SECONDS.sleep(cuentaBean.getEsperaBean().getFsIntervalo());
			// } catch (final InterruptedException e) {
			// }
			// esperaBackEnd.ejecutarTRN(cuentaBean.getEsperaBean());
			// }
			//
			// } catch (Exception e) {
			// LOGGER.debug("Error al ejecutar TRN de espera");
			// }
			cuentaBean.setRespuestaSincrona("S");
			break;

		}

		// Asigna en numero de la llamada
		cuentaBean.setnLlamada(String.valueOf(nLlamadas));

		try {
			this.cancelacionAcuerdoBackend.ejecutarTRN(cuentaBean);

			if (cuentaBean.getEsperaBean() != null
					&& !StringUtils.isBlank(cuentaBean.getEsperaBean()
							.getRespuestaSincrona())
					&& ("S").equals(cuentaBean.getEsperaBean()
							.getRespuestaSincrona())) {
				respuesta.put(Boolean.TRUE,
						"La cuenta se canceló correctamente.");
			} else {
				if (nLlamadas < 3) {
					cancelarAcuerdo(nLlamadas + 1, cuentaBean, respuesta);
				} else {
					respuesta
							.put(Boolean.FALSE,
									"La cuenta quedó en estado "
											+ cuentaBean.getCuentaBean()
													.getEstadoEnum()
													.getNombre()
											+ ". Por favor, intente cambiarlo a estado cancelado más tarde.");
				}
			}

		} catch (final NoControlableException e) {
			respuesta
					.put(Boolean.FALSE,
							e.getMensajeUsuario() + " "
									+ e.getMensajeDetalle());
		}

	}

	/**
	 * Funcion que determina el mensaje apropiado para la confirmación dle
	 * cambio de estado
	 * 
	 * @param entrada
	 * @param salida
	 * @return
	 */
	public String mensajeConfirmacion(final EstadosCuentaEnum entrada,
			final EstadosCuentaEnum salida) {
		// CAMBIOS DE ESTADOS DE CUENTAS EN ESTADO SOLICITADO
		if (entrada.equals(EstadosCuentaEnum.SOLICITADO)) {
			if (salida.equals(EstadosCuentaEnum.APROBADO)) {
				return "¿Está seguro que desea Aprobar esta cuenta?";
			} else if (salida.equals(EstadosCuentaEnum.ACTIVO)) {
				return "¿Está seguro que desea Activar esta cuenta?";
			} else if (salida.equals(EstadosCuentaEnum.CANCELADO)) {
				return "¡Atención! Si continúa cancelará la cuenta. ";
			}
		}

		// CAMBIOS DE ESTADOS DE CUENTAS EN ESTADO APROBADO
		else if (entrada.equals(EstadosCuentaEnum.APROBADO)) {
			if (salida.equals(EstadosCuentaEnum.SOLICITADO)) {
				return "¡Atención! Si continúa volverá a Solicitado la cuenta. ";
			} else if (salida.equals(EstadosCuentaEnum.ACTIVO)) {
				return "¡Atención! Si continúa volverá a Activo la cuenta. ";
			} else if (salida.equals(EstadosCuentaEnum.CANCELADO)) {
				return "¡Atención! Si continúa cancelará la cuenta. ";
			}
		}

		// CAMBIOS DE ESTADOS DE CUENTAS EN ESTADO ACTIVO
		else if (entrada.equals(EstadosCuentaEnum.ACTIVO)) {
			if (salida.equals(EstadosCuentaEnum.APROBADO)) {
				return "¡Atención! Si continúa volverá a estado Aprobado la cuenta. ";
			} else if (salida.equals(EstadosCuentaEnum.SOLICITADO)) {
				return "¡Atención! Si continúa volverá a estado Solicitado la cuenta. ";
			} else if (salida.equals(EstadosCuentaEnum.CANCELADO)) {
				return "¡Atención! Si continúa cancelará la cuenta. ";
			} else if (salida.equals(EstadosCuentaEnum.SUSPENDIDO)) {
				return "¡Atención! Si continúa volverá a estado Suspendido la cuenta. ";
			}

		}

		// CAMBIOS DE ESTADOS DE CUENTAS EN ESTADO SUSPENDIDO
		else if (entrada.equals(EstadosCuentaEnum.SUSPENDIDO)) {
			if (salida.equals(EstadosCuentaEnum.ACTIVO)) {

			}
		}

		// CAMBIOS DE ESTADOS DE CUENTAS EN ESTADO CANCELADO
		else if (entrada.equals(EstadosCuentaEnum.CANCELADO)) {
			if (salida.equals(EstadosCuentaEnum.ACTIVO)) {
				return "¡Atención! Si continúa volverá a estado Activo la cuenta.";
			}
		}

		// CAMBIOS DE ESTADOS DE CUENTAS EN ESTADO VENCIDO
		else if (entrada.equals(EstadosCuentaEnum.VENCIDO)) {
			if (salida.equals(EstadosCuentaEnum.CANCELADO)) {

			}
		}

		return null;
	}

	/**
	 * Funcion que valida si la fecha de un bean es igual a la fecha contable
	 * del sistema
	 * 
	 * @param cuentaBean
	 * @return
	 */
	public boolean isMismoDia(final CuentaBean cuentaBean) {
		if (contextoUtils != null
				&& contextoUtils.getFechaContableActual() != null
				&& cuentaBean.getFechaEstado() != null) {
			final String fechaContableActual = FechaUtils.formatFecha(
					contextoUtils.getFechaContableActual(), "dd-MM-yyyy");
			final String fechaOperacion = FechaUtils.formatFecha(
					cuentaBean.getFechaEstado(), "dd-MM-yyyy");
			if (fechaContableActual.equals(fechaOperacion)) {
				return true;
			}
		}

		return false;
	}

	@Autowired
	ConsultaNivelClienteBackend consultaNivelClienteBackend;

	@Autowired
	ConsultaDocumentosBackEnd consultaDocumentosBackEnd;

	@Autowired
	ConsultaNivelAcuerdoBackend consultaNivelAcuerdoBackend;

	private boolean hayQueNivelarCuenta(final CuentaBean cuenta,
			final ClienteBean persona) {

		final String nivelClienteString = this.consultaNivelClienteBackend
				.ejecutarTRN(persona.getIdInterna());
		Integer nivelCliente = null;
		if (nivelClienteString == null
				|| Integer.parseInt(nivelClienteString) == 2) {
			nivelCliente = consultaNivelDocumentos(persona);
		} else {
			nivelCliente = Integer.parseInt(nivelClienteString);
		}
		final TarifaBean tarifa = new TarifaBean();
		tarifa.setLinea(cuenta.getCodLinea());
		tarifa.setGrupo(cuenta.getIdGrupoProducto());
		tarifa.setProducto(cuenta.getIdProducto());
		tarifa.setId(cuenta.getIdTarifaProducto());
		String nivelAcuerdo = this.consultaNivelAcuerdoBackend
				.ejecutarWS(tarifa);
		if (StringUtils.isNotBlank(nivelAcuerdo)) {
			nivelAcuerdo = nivelAcuerdo.toUpperCase().replace("NIVEL ", "");
			if (Integer.parseInt(nivelAcuerdo) == 2
					&& nivelCliente.intValue() == 4) {
				return true;
			}
		}

		return false;
	}

	private Integer consultaNivelDocumentos(final ClienteBean cliente) {
		Integer nivel = 2;
		try {
			consultaDocumentosBackEnd.ejecutarTRN(cliente);
		} catch (final ControlableException e) {
			return nivel;
		}
		Boolean tieneCedula = false;
		Boolean tieneIdentificacion = false;
		Boolean tieneComprobante = false;
		for (final DocumentoBean documento : cliente.getDocumentos()) {
			if (ConstantesFuncionales.getCodigosComprobanteDomicilio()
					.contains(documento.getTipoDocumento())) {
				tieneComprobante = true;
			}
			if (ConstantesFuncionales.getCodigosCedulaConocimiento().contains(
					documento.getTipoDocumento())) {
				tieneCedula = true;
			}
			if (ConstantesFuncionales.getCodigosComprobanteIdentificacion()
					.contains(documento.getTipoDocumento())) {
				tieneIdentificacion = true;
			}
		}
		if (tieneIdentificacion && tieneCedula && tieneComprobante) {
			nivel = 4;
		}
		return nivel;
	}

	private ClienteBean encontrarTitular(final CuentaBean cuenta) {
		ClientePersonaFisicaBean persona = new ClientePersonaFisicaBean();
		for (final RelacionadoBean relacionado : cuenta
				.getPersonasRelacionadas()) {
			if ((TipoRelacionPersonaCuenta.TITULAR
					.equals(relacionado.getTipo()))) {
				if (relacionado.getNumero() == 1) {
					persona = relacionado.getPersona();
				}
			}
		}
		if (null == persona.getIdInterna() || persona.getIdInterna() <= 0) {
			throw new ControlableException(
					"Ha ocurrido un error al verificar el nivel de cuenta",
					"Titular de la cuenta no encontrado");
		}
		return persona;
	}

}
