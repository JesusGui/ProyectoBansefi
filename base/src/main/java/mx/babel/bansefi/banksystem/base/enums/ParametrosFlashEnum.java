package mx.babel.bansefi.banksystem.base.enums;
 
/**
 * Enumerado para guardar variables que se pasan
 * entre pantallas mediante flash
 * @author gerard.chavez
 *
 */
public enum ParametrosFlashEnum {

    CUENTA_BEAN("cuentaBean"),
    NUMERO_CUENTA("numeroCuenta"),
    TITULAR_CUENTA("titular"),
    NIVEL_CUENTA("nivelCuenta"),
    TIPO_DE_CUENTA("tipoDeCuenta"),
    ID_INTERNO_CLIENTE("idInternoCliente"),
    CLIENTE_PF_BEAN("clientePFBean"),
    CLIENTE_PF_BEAN_CONSULTADO("clientePFBeanConsultado"),
    MODIFICACION_CLIENTE("modificacionCliente"),
    CLIENTE_PM_BEAN("clientePMBean"),
    CLIENTE_PM_BEAN_CONSULTADO("clientePMBeanConsultado"),
    MODIFICACION_CUENTA("modificacionCuenta"),
    EMPLEADO_BEAN("empleadoBean"),
    EMPLEADO_BEAN_CONSULTADO("empleadoBeanConsultado"),
    MODIFICACION_EMPLEADO("modificacionEmpleado"),
    ID_EMPLEADO("idEmpleado"),
    VISUALIZA_ESTADO_PAIS_NAC("visualizaEstadoPaisNac"),
    TIPO_BUSQUEDA("tipoBusqueda"),
    ACCION_BUSQUEDA("accionBusqueda"),
    NOMBRE_CLIENTE("nombreCliente"),
    TIPO_CLIENTE("tipoCliente"),
    DEPOSITO("deposito"),
    NAVEGACION_BUSQUEDA("destinoBusqueda"),
    CONTROLLER_DESTINO("controllerDestino"),
    NAVEGACION_MANAGED_BEAN("inicio"),
    RETURN_AND_REFRESH("returnRefresh"),
    CLIENTE("cliente"),
    RESULTADO_BUSQUEDA("resultadoBusqueda"),
    ARQUEO_CENTRO_BEAN("arqueoCentroBean"),
    ARQUEO_PUESTO_BEAN("arqueoPuestoBean"),
    CONSULTA_TRASPASO("consultaTraspasoBean"),
    NAVEGACION_TRASPASOS("navegacionTraspasos"),
    LIQUIDACION_BEAN("liquidacionBean"),
    LIQUIDACION_CONCEPTO_BEAN("liquidacionConceptoBean"),
    LISTA_LIQ_CONCEPTOS("lstLiquidacionConceptoBean"),
	MOVIMIENTO_BEAN("movimientoBean"),
	DIARIO_ELECTRONICO_BUSQUEDA("diarioElectronicoBusqueda"),
    DIARIO_ELECTRONICO_TABLA("diarioElectronicoRespuesta"),
    ENTIDAD("entidad"),
    RETIRO("retiro"),
    CODIGO_ENTIDAD("codigoEntidad"),
    MOSTRAR_INICIO("mostrarInicio"),
    RESULTADOS_BUSQUEDA("resultadosBusqueda"),
    ANOTACION_BEAN("anotacionBean"),
    NUMERO_ANOTACION("numero"),
    TIPO_ANOTACION("tipo"),
    SUBCODIGO_ANOTACION("subcodigo"),
    ID_INTERNA("idInterna"),
    NOMBRE("nombre"),
    DOMICILIO_COMPARTIDO("domicilioCompartido"),
    CENTRO("centro"),
    CODIGO_CENTRO("codigoCentro"),
    RESPALDO_CENTRO("respaldoCentro"),
    APLICACIONES_CENTROS_SELEC("aplicacionesCentrosSelec"),
    APLICACIONES_CENTROS_CTRLS("aplicacionesCentrosCtrl"),
    ACUERDOS_INSTRUMENTALES("acuerdosInstrumentales"),
    ACUERDOS_INSTRUMENTALES_BEAN("acuerdoInstrumentalBean"),
    MANTENIMIENTO_ACUERDO("mantenimientoAcuerdo"),
    EXISTEN_CUENTAS("existenCuentas"),
    SALDOS_CODIGO_CUENTA("cdoCuenta"),
    SALDOS_CODIGO_SALDO("cdoSaldo"),
    SALDO_BEAN("saldoBean"),
    TARIFA_SELECCIONADA("tarifa_seleccionada"),
    TARIFA_RELACIONADA("tarifa_relacionada"),
    ANADIR_DOMICILIO_COMPARTIDO("anadirDomicilioCompartido"),
    NUMERO_DIRECCION("numeroDireccion"),
    NOMBRE_COMPLETO_PERSONA("nombreCompleto"),
    ID_EXTERNA("idExterna"),
    COD_TIPO_GRUPO("codGrupo"),
    NOMBRE_GRUPO("nombre"),
    ERROR_DOCUMENTOS("errorDocumentos"),
    MODIFICACION_DOCUMENTOS("tipoModificacionDocumentos"),
    PARAMETROS_BUSQUEDA_APUNTE_BEAN("parametrosBusquedaApunteBean"),
    APUNTE_BEAN("datosApunteBean"),
    BUSQUEDA_APUNTE("busquedaApunte"),
    PROCEDENCIA_RESUMEN_RECIBO_NO_DOMICILIADO("procedencia"),
    RECIBO_BEAN("reciboBean"),
    NUMERO_INTERNO_DOCUMENTO("noInternoDocumento"),
    ALTA_RECIBO_NO_DOMICILIADO("altaReciboNoDomiciliado"),
    LISTADO_TAREAS("listadoTareas"),
    DESTINO("destino"),
    DESTINO_CONTROLLER("destinoController"),
    OBJETO_ENTRADA_PLANTILLA("objEntradaPlantilla"),
    CUENTAS_FICHA("cuentas"),
    CUENTAS_FICHA2("totalCuentas"),
    HISTORICO_DOCUMENTO("historicoDocumento"),
    HISTORICO_PERSONA_RELACIONADA("historicoRelacionado"),
    HISTORICO_CUENTA_RELACIONADA("historicoRelacionada"),
    NAVEGACION_ALTA_CUENTA("estadoAltaCuenta"),
    MAPA_NATURALEZAS_CUENTA("mapaNaturalezas"),
    LISTA_SALDOS("listaSaldos"),
    ERROR_BIENES("errorBienes"),
    MODIFICACION_BIENES("tipoModificacionBienes"),
    SUBFLUJO_ALTA_CLIENTE("subflujoAltaCliente"),
    SUBFLUJO_ALTA_CUENTA("subflujoAltaCuenta"),
    CUENTA_SUBFLUJO_ALTA("cuentaSubflujoAlta"),
    NUMERO_CUENTA_OPERATIVA("numeroCuentaOperativa"),
    IMPORTE_MINIMO_IPF("importeMinimoImposicion"),
    ES_DEPOSITO_IPF("esDepositoIPF"),
    APERTURA_PUESTO_BEAN("aperturaPuestoBean"),
    ORIGEN_FICHA_CUENTA("origenFichaCuenta"),
    RAZON_SOCIAL("razonSocial"),
    ACTA_CONSTITUTIVA("actaConstitutiva"),
    IDENTIFICACION("identificacion"),
    ID_TRANSACCION("idTransaccion"),
    IPFS_ACTIVAS("ipfsActivas"),
    IPFS_TODAS("ipfsTodas"),
    IND_ALTA_REPRESENTANTE("indAltaRepresentante"),
    BEAN_DATOS_BUSQUEDA("beanDatosBusqueda"),
    ORIGEN_BUSQUEDA("origenBusqueda"),
    NIVELAR_CUENTA("nivelarCuenta"),
    APUNTES_BEAN("apuntesBean"),
    ALTA_APUNTE_BEAN("altaApunteBean"),
    PETICION_EFECTIVO_BEAN("peticionEfectivoBean"),
    AJUSTE_EXISTENCIAS_BEAN("ajusteExistenciasBean"),
    AUTORIZACION_PETICION_BEAN("autorizacionPeticionBean"),
    AUTORIZACION_PETICION_TOTALES_BEAN("autorizacionPeticionTotalesBean"),
    TOTAL_AUTORIZADO("totalAutorizado"),
    AUTORIZACION_PETICION_URGENTE_BEAN("autorizacionPeticionUrgenteBean"),
    ENVIO_ENTRE_OFICINAS_BEAN("envioEntreOficinasBean"),
    RECOGIDA_EFECTIVO_BEAN("recogidaEfectivoBean"),
    RECEPCION_EFECTIVO_BEAN("recepcionEfectivoBean"),
    GASTOS_EXTERNOS_BEAN("gastosExternosBean"),
    RECEPCION_ENTRE_OFICINAS_BEAN("recepcionEntreOficinasBean"),
    DIFERENCIA_BEAN_PRINCIPAL("diferenciaBean"),
    DIFERENCIA_BEAN("diferencia"),
    ANOTACION_GASTO_BEAN("anotacionGastoBean");

    private String paramFlash;

    /**
     * Constructor.
     * @param paramFlash .
     */
    private ParametrosFlashEnum(final String paramFlash){
        this.paramFlash = paramFlash;
    }

    /**
     * Este método devuelve el parámetro que se usará en la flash
     * de una navegacion entre páginas.
     * @return paramFlash
     */
    public String getParamFlash() {
        return paramFlash;
    }

    /**
     * Este método recibe el parámetro que se usará en la flash
     * de una navegacion entre páginas.
     * @param paramFlash .
     */
    public void setParamFlash(final String paramFlash) {
        this.paramFlash = paramFlash;
    }

}