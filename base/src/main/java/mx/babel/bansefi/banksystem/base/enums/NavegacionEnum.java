package mx.babel.bansefi.banksystem.base.enums;

/**
 * Enumerado que define las rutas de navegación utilizadas por la aplicación.
 */ 
public enum NavegacionEnum {
	LOGIN("/views/login/login?faces-redirect=true"),
	TIMEOUT("/views/error/timeout?faces-redirect=true"),
	SALDOS_CAJA("/views/login/saldosCaja?faces-redirect=true"),
	CAMBIAR_CONTRASENA("/views/login/cambiarContrasena?faces-redirect=true"),
	CAMBIAR_CONTRASENA_2("/views/login/cambiarContrasena2?faces-redirect=true"),
	INICIO("/views/inicio/inicio?faces-redirect=true"),
	TAREAS_PENDIENTES("/views/inicio/tareas?faces-redirect=true"),
	NOTIFICACIONES("/views/inicio/notificaciones?faces-redirect=true"),
	ALTA_NOTIFICACION("/views/adminNotificaciones/altaNotificacion?faces-redirect=true"),
	DETALLE_NOTIFICACION("/views/adminNotificaciones/detalleNotificacion?faces-redirect=true"),
	ALTA_CLIENTE1("/views/altaCliente/altaCliente1?faces-redirect=true"),
	ALTA_CLIENTE2("/views/altaCliente/altaCliente2?faces-redirect=true"),
	ALTA_CLIENTE3("/views/altaCliente/altaCliente3?faces-redirect=true"),
	ALTA_CLIENTE_RIESGO1("/views/altaCliente/datosClienteRiesgo/altaClienteRiesgo1?faces-redirect=true"),
	ALTA_CLIENTE_RIESGO2("/views/altaCliente/datosClienteRiesgo/altaClienteRiesgo2?faces-redirect=true"),
	REALIZAR_DEPOSITO("/views/deposito/realizarDeposito?faces-redirect=true"),
    CONFIRMAR_DEPOSITO("/views/deposito/confirmarDeposito?faces-redirect=true"),
    RESUMEN_DEPOSITO("/views/deposito/resumenDeposito?faces-redirect=true"),
	FICHA_CLIENTE("/views/fichaCliente/fichaCliente?faces-redirect=true"),
	FICHA_CUENTA("/views/consultaCuenta/fichaCuenta?faces-redirect=true"),
	REALIZAR_RETIRO("/views/retiro/realizarRetiro.xhtml?faces-redirect=true"),
	DETALLE_RETIRO("/views/retiro/detalleRetiro.xhtml?faces-redirect=true"),
	BUSQUEDA("/views/buscador/buscador?faces-redirect=true"),
	ALTA_EMPLEADO_FILTRO("/views/altaEmpleado/altaEmpleadoFiltro?faces-redirect=true"),
	ALTA_EMPLEADO1("/views/altaEmpleado/altaEmpleado1?faces-redirect=true"),
	ALTA_EMPLEADO2("/views/altaEmpleado/altaEmpleado2?faces-redirect=true"),
	ALTA_EMPLEADO3("/views/altaEmpleado/altaEmpleado3?faces-redirect=true"),
	FICHA_EMPLEADO("/views/fichaEmpleado/fichaEmpleado?faces-redirect=true"),
	ATRIBUCIONES_EMPLEADO("/views/atribuciones/atribuciones?faces-redirect=true"),
	CONSULTA_MOVIMIENTOS("/views/movimientos/consultaMovimientos?faces-redirect=true"),
	DETALLE_MOVIMIENTOS("/views/movimientos/detalleMovimientos?faces-redirect=true"),
	ALTA_CUENTA1("/views/altaCuenta/altaCuenta1?faces-redirect=true"),
	ALTA_CUENTA2("/views/altaCuenta/altaCuenta2?faces-redirect=true"),
	ALTA_CUENTA3("/views/altaCuenta/altaCuenta3?faces-redirect=true"),
	ALTA_CUENTA4("/views/altaCuenta/altaCuenta3?faces-redirect=true"),
	CONSULTA_PLANIFICACIONES("/views/consultaCuenta/consultaPlanificaciones?faces-redirect=true"),
	ALTA_CENTRO("/views/altaCentro/altaCentro?faces-redirect=true"),
	ALTA_CENTRO2("/views/altaCentro/altaCentro2?faces-redirect=true"),
	ALTA_ENTIDAD1("/views/entidad/altaEntidad1?faces-redirect=true"),
    CONSULTA_SALDOS("/views/saldos/consultaSaldos?faces-redirect=true"),
	DETALLE_SALDOS("/views/saldos/detalleSaldos?faces-redirect=true"),
	CONSULTA_ENTIDAD("/views/entidad/consultaEntidad?faces-redirect=true"),
	RELACIONES_CUENTA_PERSONAS("/views/relacionesCuenta/relacionCuentaPersonas?faces-redirect=true"),
	RELACIONES_CUENTA_DOCUMENTOS("/views/relacionesCuenta/relacionCuentaDocumentos?faces-redirect=true"),
	RELACIONES_CUENTA_CUENTAS("/views/relacionesCuenta/relacionCuentaCuentas?faces-redirect=true"),
	RELACIONES_CUENTA_PAN("/views/relacionesCuenta/relacionCuentaPan?faces-redirect=true"),
	EMITIR_DOCUMENTOS("/views/relacionesCuenta/emitirDocumentos?faces-redirect=true"),
	HISTORICO_RELACION_CUENTA_PERSONA("/views/relacionesCuenta/historicoRelacionPersonaCuenta?faces-redirect=true"),
	HISTORICO_RELACION_CUENTA_CUENTA("/views/relacionesCuenta/historicoRelacionCuentaCuenta?faces-redirect=true"),
	HISTORICO_DOCUMENTOS("/views/relacionesCuenta/historicoDocumentos?faces-redirect=true"),
	INFORMACION_DERIVADA("/views/informacionCuenta/informacionDerivada?faces-redirect=true"),
	PERSONA_MORAL_CONTITUCION("/views/personaMoral/moralDatosConstitucion?faces-redirect=true"),
	PERSONA_MORAL_GENERAL("/views/personaMoral/moralDatosGenerales?faces-redirect=true"),
	PERSONA_MORAL_CONOCIMIENTO("/views/personaMoral/moralCedulaConocimiento?faces-redirect=true"),
	PERSONA_MORAL_PERSONAS("/views/personaMoral/datosPersonaRiesgo/moralPersonas?faces-redirect=true"),
	PERSONA_MORAL_GRUPOFILIAL("/views/personaMoral/datosPersonaRiesgo/moralGrupoFilial?faces-redirect=true"),
	PERSONA_MORAL_DONATIVOS("/views/personaMoral/datosPersonaRiesgo/moralDonativos?faces-redirect=true"),
	CONSULTA_AUDITORIA_CUENTAS("/views/historicoEstados/auditoriaHistorico?faces-redirect=true"),
	ACTIVIDAD_EMPRESARIAL("/views/ampliarDatos/actividadEmpresarial?faces-redirect=true"),
	PERSONA_BALANCE_LISTA("/views/ampliarDatos/listaBalances?faces-redirect=true"),
	PERSONA_BALANCE_DETALLE("/views/ampliarDatos/balance?faces-redirect=true"),
	ALTA_BIENES2("/views/bienes/bienes?faces-redirect=true"),
	DESGLOSE_ARQUEO_CENTRO("/views/arqueoCentro/desgloseArqueoCentro?faces-redirect=true"),
	RESULTADO_ARQUEO_CENTRO("/views/arqueoCentro/resultadoArqueoCentro?faces-redirect=true"),
	ARQUEO_PUESTO("/views/arqueoPuesto/arqueoPuesto?faces-redirect=true"),
	DETALLE_ARQUEO_PUESTO("/views/arqueoPuesto/detalleArqueoPuesto?faces-redirect=true"),
	CONSULTA_TRASPASOS("/views/traspasos/consultaTraspasos?faces-redirect=true"),
	SALDOS_NETOS("/views/traspasos/saldosNetos?faces-redirect=true"),
	ALTA_ANOTACIONES("/views/anotaciones/altaAnotaciones?faces-redirect=true"),
	DETALLE_ANOTACIONES("/views/anotaciones/detalleAnotaciones?faces-redirect=true"),
	CONSULTA_POSICION("/views/consultaCuenta/posicion?faces-redirect=true"),
	DIARIO_BUSQUEDA("/views/diarioElectronico/diarioBusqueda?faces-redirect=true"),
	DIARIO_TABLA("/views/diarioElectronico/diarioTabla?faces-redirect=true"),
	DIARIO_DETALLE("/views/diarioElectronico/diarioDetalle?faces-redirect=true"),
	CONSULTA_LIQUIDACIONES("/views/liquidacionesCuenta/consultaLiquidaciones?faces-redirect=true"),
	DETALLE_LIQUIDACION("/views/liquidacionesCuenta/consultaDetalleLiquidacion?faces-redirect=true"),
	DETALLE_SIMULAR_LIQUIDACION("/views/liquidacionesCuenta/detalleSimularLiquidacion?faces-redirect=true"),
	DETALLE_AMP_LIQUIDACION("/views/liquidacionesCuenta/consultaDetAmpliadoLiquidacion?faces-redirect=true"),
	DETALLE_AMP_INT_LIQUIDACION("/views/liquidacionesCuenta/consultaDetAmpIntLiquidacion?faces-redirect=true"),
	DETALLE_AMP_COM_LIQUIDACION("/views/liquidacionesCuenta/consultaDetAmpComLiquidacion?faces-redirect=true"),
	DETALLE_APUNTE("/views/apuntesCuenta/consultaDetalleApunte?faces-redirect=true"),
	CONTADORES_CENTRO( "/views/contadoresCentro/mostrarContadoresCentro?faces-redirect=true" ),
	DIFERENCIAS_CAJA( "/views/diferenciasCaja/consultaDiferenciasCaja.xhtml?faces-redirect=true" ),
	CONTADORES_PUESTO( "/views/contadoresPuesto/mostrarContadoresPuesto.xhtml?faces-redirect=true" ),
	AMPLIAR_DATOS1("/views/ampliarDatos/ampliarDatos1?faces-redirect=true"),
	RELACIONES_CLIENTE("/views/ampliarDatos/relacionesCliente?faces-redirect=true"),
	DATOS_ECONOMICOS("/views/ampliarDatos/datosEconomicos?faces-redirect=true"),
	BIENES("/views/bienes/bienes?faces-redirect=true"),
    DOCUMENTOS("/views/documentos/documentos?faces-redirect=true"),
    DOMICILIOS_CUENTA("/views/domiciliosCuenta/domiciliosCuenta?faces-redirect=true"),
    DOMICILIO_PERSONA("/views/domicilioPersona/domicilioPersona?faces-redirect=true"),
    BUSQUEDA_APUNTE("/views/apuntesCuenta/busquedaApunte?faces-redirect=true"),
    BUSQUEDA_APUNTE2("/views/apuntesCuenta/busquedaApunte2?faces-redirect=true"),
    REALIZAR_ALTA_APUNTE("/views/apuntes/realizarAltaApunte?faces-redirect=true"),
	DETALLE_ALTA_APUNTE("/views/apuntes/detalleAltaApunte?faces-redirect=true"),
	BLOQUEO_CUENTA_1("/views/bloqueoCuenta/consultaBloqueosCuenta?faces-redirect=true"),
    BLOQUEO_CUENTA_2("/views/bloqueoCuenta/nuevoBloqueoCuenta?faces-redirect=true"),
	CLASIFICACION_CUENTA("/views/clasificacionCuenta/clasificacionCuenta?faces-redirect=true"),
	SALDOS_CUENTA("/views/saldos/saldosCuenta?faces-redirect=true"),
	SALDOS_FECHA("/views/saldos/saldosFecha?faces-redirect=true"),
	DETALLE_PERSONA("/views/domicilioPersona/detallePersona?faces-redirect=true"),
	DATOS_ADICIONALES("/views/datosAdicionalesCuenta/datosAdicionales?faces-redirect=true"),
	OTROS_DATOS("/views/ampliarDatos/otrosDatos?faces-redirect=true"),
	CENTRO_ASOCIADO("/views/centroAsociado/centroAsociado?faces-redirect=true"),
	BUSQUEDA_ANULACION_APUNTE("/views/anulacionApuntes/busquedaAnulacion?faces-redirect=true"),
	DETALLE_ANULACION_APUNTE("/views/anulacionApuntes/detalleAnulacion?faces-redirect=true"),
	PAGO_RECIBOS_NO_DOMICILIADOS("/views/recibosNoDomiciliados/altaRecibo?faces-redirect=true"),
	RESUMEN_TRANSACCION_RECIBO_NO_DOMICILIADO("/views/recibosNoDomiciliados/resumenTransaccion?faces-redirect=true"),
	ALTA_IPF_1("/views/altaIPF/altaIPF1?faces-redirect=true"),
    ALTA_IPF_2("/views/altaIPF/altaIPF2?faces-redirect=true"),
	CANCELAR_CUENTA("/views/estadosCuenta/cancelarCuenta?faces-redirect=true"),
	ANULAR_CONSTITUCION("/views/estadosCuenta/anularConstitucion?faces-redirect=true"),
	TRASPASO_PUESTO("/views/traspasos/traspasoPuesto?faces-redirect=true"),
    PAGO_CUOTA_1("/views/pagoCuota/pagoCuota1?faces-redirect=true"),
    PAGO_CUOTA_2("/views/pagoCuota/pagoCuota2?faces-redirect=true"),
    ALTA_APUNTE_MANUAL("/views/apuntesManuales/altaApunteManual?faces-redirect=true"),
    RESUMEN_APUNTE_MANUAL("/views/apuntesManuales/resumenApunteManual?faces-redirect=true"),
    CIERRE_CONTABLE("/views/cierreContable/cierreContable?faces-redirect=true"),
	PETICION_EFECTIVO("/views/peticionEfectivo/peticionEfectivo?faces-redirect=true"),
    DETALLE_PETICION_EFECTIVO("/views/peticionEfectivo/detallePeticionEfectivo?faces-redirect=true"),
    RECEPCION_EFECTIVO("/views/recepcionEfectivo/recepcionEfectivo?faces-redirect=true"),
    DETALLE_RECEPCION_EFECTIVO("/views/recepcionEfectivo/detalleRecepcionEfectivo?faces-redirect=true"),
    AJUSTE_EXISTENCIAS("/views/ajusteExistencias/ajusteExistencias?faces-redirect=true"),
    AUTORIZACION_PETICION("/views/autorizacionPeticion/autorizacionPeticion?faces-redirect=true"),
    RESUMEN_AUTORIZACION_PETICION("/views/autorizacionPeticion/resumenPeticionEfectivo?faces-redirect=true"),
    RESUMEN_AUTORIZACION_PETICION_TOTALES("/views/autorizacionPeticion/resumenPeticionTotales?faces-redirect=true"),
    RESUMEN_AUTORIZACION_PETICION_URGENTE("/views/autorizacionPeticion/resumenPeticionUrgente?faces-redirect=true"),
    ENVIO_ENTRE_OFICINAS("/views/envioEntreOficinas/envioEntreOficinas?faces-redirect=true"),
    DETALLE_ENVIO_ENTRE_OFICINAS("/views/envioEntreOficinas/detalleEnvioEntreOficinas?faces-redirect=true"),
    RECOGIDA_EFECTIVO("/views/recogidaEfectivo/recogidaEfectivo?faces-redirect=true"),
    DETALLE_RECOGIDA_EFECTIVO("/views/recogidaEfectivo/detalleRecogidaEfectivo?faces-redirect=true"),
    RECEPCION_ENTRE_OFICINAS("/views/recepcionEntreOficinas/recepcionEntreOficinas?faces-redirect=true"),
    DETALLE_RECEPCION_ENTRE_OFICINAS("/views/recepcionEntreOficinas/detalleRecepcionEntreOficinas?faces-redirect=true"),
    GASTOS_EXTERNOS("/views/cancelacionCuenta/gastosExternos?faces-redirect=true"),
    DETALLE_GASTO_IMPUTACION("/views/cancelacionCuenta/detalleImputacion?faces-redirect=true"),
    DIFERENCIAS("/views/diferencias/diferencias?faces-redirect=true"),
    DETALLE_DIFERENCIAS("/views/diferencias/detalleDiferencias?faces-redirect=true"),
    ALTA_ANOTACIONES_GASTOS("/views/anotaciones/altaAnotaciones2?faces-redirect=true");
	

	
	private String ruta;

	/**
	 * Constructor.
	 * @param ruta .
	 */
	private NavegacionEnum(final String ruta){
		this.ruta = ruta;
	}

	/**
	 * Este método devuelve la ruta de un recurso XHTML.
	 * @return ruta
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * Este método recibe la ruta de un recurso XHTML.
	 * @param ruta .
	 */
	public void setRuta(final String ruta) {
		this.ruta = ruta;
	}

}