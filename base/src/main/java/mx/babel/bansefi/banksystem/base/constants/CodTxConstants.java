package mx.babel.bansefi.banksystem.base.constants;

/**
 * Clase de constantes para almacenar los valores cod_tx necesarios en las
 * llamadas a HOST.
 * 
 * @author joseluis.pena
 * 
 */
public class CodTxConstants {

	/**
	 * Constante para el servicio ConsultaGrpPrdVendServicio.
	 */
	public static final String COD_TX_TR_CONS_LISTA_PDV_TRN = "GCC31COU";

	/**
	 * Constante para el servicio ConsultaDetalleGrpPrdVendServicio.
	 */
	public static final String COD_TX_TR_LISTA_TV_TRN = "GCC45COU";

	/**
	 * Constante para el servicio BusquedaIdExternaServicio.
	 */
	public static final String COD_TX_TR_PE_CB_ID_EXTERNA_CNS_TRN = "PGE10CON";

	/**
	 * Constante para el servicio AltaPersonaServicio.
	 */
	public static final String COD_TX_TR_PE_ALTA_INDV_TRN = "PGE01MON";

	/**
	 * Constante para el servicio ModificacionPersonaFisicaServicio.
	 */
	public static final String COD_TX_TR_PE_MODIF_INDV_TRN = "PGE05MON";

	/**
	 * Constante para el servicio ConsultaEmpleadoServicio.
	 */
	public static final String COD_TX_TR_CONS_EMPL_TRN = "GCA20CON";

	/**
	 * Constante para el servicio ConsultaUsoCuentaServicio.
	 */
	public static final String COD_TX_PE_CONS_INDV_USO_CTA_TRN = "PGE17CON";

	/**
	 * Constante para el servicio ConsultaPersonaFisicaServicio.
	 */
	public static final String COD_TX_TR_PE_CONS_INDV_TRN = "PGE01MON";

	/**
	 * Constante para el servicio AltaPersonaMoralServicio.
	 */
	public static final String COD_TX_TR_PE_ALTA_ORG_TRN = "PGE02MON";

	/**
	 * Constante para el servicio ConsultaPersonaMoralServicio.
	 */
	public static final String COD_TX_TR_PE_CONS_ORG_TRN = "PGE04CON";

	/**
	 * Constante para el servicio ConsultaGrupoServicio.
	 */
	public static final String COD_TX_TR_GR_CONS_TRN = "PGE04CON";

	/**
	 * Constante para el servicio Consulta integrantes grupo.
	 */
	public static final String COD_TX_TR_PE_CONSULTA_POSICION_TRN = "PGE50CON";

	/**
	 * Constante para el servicio ConsultaRelacionesObligatoriasServicio.
	 */
	public static final String COD_TX_TR_LISTA_LG_RL_PERS_TRN = "GCC17COU";

	/**
	 * Constante para el servicio Consulta relaciones persona cuenta.
	 */
	public static final String COD_TX_TR_CONSULTA_RP_PANT_TRN = "GAC67COU";

	/**
	 * Constante para el servicio alta relaciones persona cuenta.
	 */
	public static final String COD_TX_TR_ALTA_RP_PANT_TRN = "GAC63MOU";

	/**
	 * Constante para el servicio Consulta cuentas de personas.
	 */
	public static final String COD_TX_TR_CONSULTA_RP_PANT_5_TRN = "GAC67COU";

	/**
	 * Constante para el servicio ConsultaDocumentosServicio.
	 */
	public static final String COD_TX_TR_DC_LS_CNS_TRN = "PDO07CON";

	/**
	 * Constante para el servicio ConsultaDomicilioPrincipalServicio.
	 */
	public static final String COD_TX_TR_CONSU_SELECTIV2_AC_TRN = "GAC08COU";

	/**
	 * Constante para el servicio ConsultaRetencionesServicio.
	 */
	public static final String COD_TX_TR_CONSULTA_GLOBAL_RT_TRN = "DGE10COU";

	/**
	 * Constante para el servicio ConsultaBloqueosServicio.
	 */
	public static final String COD_TX_TR_PETICION_CONSULTA_BP_TRN = "DGE05COU";

	/**
	 * Constante para el servicio ConsultaAutorizacionesServicio.
	 */
	public static final String COD_TX_TR_CONSULTA_GLOBAL_AT_TRN = "DVI06COU";

	/**
	 * Constante para los servicios de consulta de condiciones.
	 */
	public static final String COD_TX_TR_CONS_VAL_KA = "GAC34COU";

	/**
	 * Constante para los servicios de modificacion de condiciones.
	 */
	public static final String COD_TX_TR_MODIFICAR_KA = "GAC34OOU";

	/**
	 * Constante para los servicios de consulta de lista de empleados por
	 * centro.
	 */
	public static final String COD_TX_TR_CON_LISTA_EMPL_TRN = "GCA61CON";

	/**
	 * Constante para los servicios de consulta de validacion de identificacion
	 * de persona por Id Externa.
	 */
	public static final String COD_TX_TR_PE_CONS_VAL_ID_EXT_TRN = "POT44CON";

	/**
	 * Constante para los servicios de consulta de validacion de identificacion
	 * de persona por Id Interna.
	 */
	public static final String COD_TX_TR_CONS_MINIMA_PERSONA_TRN = "PGE13CON";

	/**
	 * Constante para la consulta de datos de cuenta.
	 */
	public static final String COD_TX_TR_CONSU_DATOS_AC_TRN = "GAC10COU";

	/**
	 * Constante para dar de alta una relación tipo cliente-persona.
	 */
	public static final String COD_TX_TR_PE_ALTA_RL_PE_DS_TRN = "PGE29MON";

	/**
	 * Constante para dar de alta una relación tipo cliente-grupo.
	 */
	public static final String COD_TX_TR_GR_ALTA_RL_PE_TRN = "PGR08MON";

	/**
	 * Constante para dar de alta una relación tipo cliente-gestor.
	 */
	public static final String COD_TX_TR_PE_ALTA_RL_GT_TRN = "PGT18MON";

	/**
	 * Constante para modificar una relación tipo cliente-persona.
	 */
	public static final String COD_TX_TR_PE_MODI_RL_PE_DS_TRN = "PGE31MON";

	/**
	 * Constante para modificar una relación tipo cliente-gestor.
	 */
	public static final String COD_TX_TR_PE_MODIF_RL_GT_TRN = "PGT21MON";

	/**
	 * Constante para dar de baja una relación tipo cliente-persona.
	 */
	public static final String COD_TX_TR_PE_BAJA_RL_PE_DS_TRN = "PGE44MON";

	/**
	 * Constante para dar de baja una relación tipo cliente-grupo.
	 */
	public static final String COD_TX_TR_GR_BAJA_RL_PE_TRN = "PGR09MON";

	/**
	 * Constante para dar de baja una relación tipo cliente-gestor.
	 */
	public static final String COD_TR_PE_BAJA_RL_GT_TRN = "PGT19MON";

	/**
	 * Constante para consultar las personas relacionadas a un cliente.
	 */
	public static final String COD_TX_TR_PE_RL_PE_LS_CNS_TRN = "PGE28CON";

	/**
	 * Constante para consultar los tipos de relaciones de un cliente (detalle
	 * de una relación persona persona).
	 */
	public static final String COD_TX_TR_PE_CONS_RL_PE_DS_TRN = "PGE30CON";

	/**
	 * Constante para consultar los grupos relacionados a un cliente.
	 */
	public static final String COD_TX_TR_GR_CONS_RL_PE_TRN = "PGR10CON";

	/**
	 * Constante para consultar los gestores relacionados a un cliente.
	 */
	public static final String COD_TX_TR_PE_RL_GT_LS_CNS_TRN = "PGT17CON";

	/**
	 * Constante para los servicios de consulta de los documentos a emititr de
	 * un acuerdo
	 */
	public static final String COD_TX_TR_DE_TODOS_DOCU_AC_TRN = "GDE26COU";

	/**
	 * Constante para los servicios de consulta de estados de documento
	 */
	public static final String COD_TX_TR_DE_CONS_ECVS_TRN = "GDE26COU";

	/**
	 * Constante para los servicios de emisiòn de documento
	 */
	public static final String COD_TX_TR_DE_EMITIR_CDO_TRN = "GDE01MOU";

	/**
	 * Constante para los servicios de consulta campos de documento
	 */
	public static final String COD_TX_TR_DE_CONS_DATOS_ENT_TRN = "GDE03COU";

	/**
	 * Constante para los servicios de formaliza de documento
	 */
	public static final String COD_TX_TR_DE_FORMALIZAR_TRN = "GDE10MOU";

	/**
	 * Constante para los servicios de modificar campo de documento a emitir
	 */
	public static final String COD_TX_TR_DE_MODI_DATOS_ENT_TRN = "GDE03COU";

	/**
	 * Constante para los servicios de anular documento a emitir
	 */
	public static final String COD_TX_TR_DE_CANCELAR_TRN = "GDE02MOU";

	/**
	 * Constante para el servicio ConsultaBloqueosServicio.
	 */
	public static final String COD_TR_PETICION_CONSULTA_BP_TRN = "DGE05COU";

	/**
	 * Constante para el servicio ConsultaAutorizacionesServicio.
	 */
	public static final String COD_TR_CONSULTA_GLOBAL_AT_TRN = "DVI06COU";

	/**
	 * Constante para el servicio ConsultaRetencionesServicio.
	 */
	public static final String COD_TR_CONSULTA_GLOBAL_RT_TRN = "DGE10COU";

	/**
	 * Constante para el servicio ConsultaCuentasRelacionablesServicio.
	 */
	public static final String COD_TR_CONSU_SELECTIV2_AC_TRN = "GAC08COU";

	/**
	 * Constante para la consulta de otras identificaciones de persona
	 */
	public static final String COD_TX_TR_PE_CONS_PE_ID_EXT_TRN = "POT44CON";

	/**
	 * Constante para la consulta de otros nombres de persona
	 */
	public static final String COD_TX_TR_PE_CONS_PE_OTRO_NOMB_TRN = "POT48CON";

	/**
	 * Constante para la consulta de otros telefonos/faxes de persona
	 */
	public static final String COD_TX_TR_DR_CONS_DR_ELCTR_TRN = "POT03CON";

	/**
	 * Constante para la alta de otro nombre de persona
	 */
	public static final String COD_TX_TR_PE_ALTA_PE_OTRO_NOMB_TRN = "POT46MON";

	/**
	 * Constante para la alta de otra identificacion de persona
	 */
	public static final String COD_TX_TR_PE_ALTA_PE_ID_EXT_TRN = "POT42MON";

	/**
	 * Constante para la alta de otro tel/fax de persona
	 */
	public static final String COD_TX_TR_DR_ALTA_DR_ELCTR_TRN = "POT01MON";

	/**
	 * Constante para la baja de otra identificacion de persona
	 */
	public static final String COD_TX_TR_PE_BAJA_PE_ID_EXT_TRN = "POT43MON";

	/**
	 * Constante para la baja de otro nombre de persona
	 */
	public static final String COD_TX_TR_PE_BAJA_PE_OTRO_NOMB_TRN = "POT47MON";

	/**
	 * Constante para la baja de otro nombre de persona
	 */
	public static final String COD_TX_TR_DR_BAJA_DR_ELCTR_TRN = "POT02MON";

	/**
	 * Constante para la modificacion de otro nombre de persona
	 */
	public static final String COD_TX_TR_PE_MODIF_PE_OTRO_NOMB_TRN = "POT49MON";

	/**
	 * Constante para la modificacion de otro telefono o fax de persona
	 */
	public static final String COD_TX_TR_DR_MODIF_DR_ELCTR_TRN = "POT04MON";

	/**
	 * Constante para la modificacion de otro identificacion de persona
	 */
	public static final String COD_TX_TR_PE_MODIF_PE_ID_EXT_TRN = "POT45MON";

	/**
	 * Constante para el servicio de alta de bien inmueble
	 */
	public static final String COD_TX_TR_BIEN_ALTA_TRN = "PBI06MON";

	/**
	 * Constante para el servicio de alta de bien ingreso gasto
	 */
	public static final String COD_TX_TR_ALTA_BIEN_ING_GAS_TRN = "PBI20MON";

	/**
	 * Constante para el servicio de consulta de lista de bienes por persona
	 */
	public static final String COD_TX_TR_PE_RL_BI_LST_TRN = "PBI16CON";

	/**
	 * Constante para el servicio de consulta de detalle de bien
	 */
	public static final String COD_TX_TR_CON_ENTIDAD_TRN = "GCA05CON";

	/**
	 * Constante para el servicio de baja de bien
	 */
	public static final String COD_TX_TR_BIEN_BAJA_TRN = "PBI07MON";

	/**
	 * Constante para el servicio de modificacion de bien
	 */
	public static final String COD_TX_TR_BIEN_MODI_TRN = "PBI09MON";

	/**
	 * Constante para el servicio de modificacion de bien
	 */
	public static final String COD_TX_TR_MODI_BIEN_ING_GAS_TRN = "PBI22MON";

	/**
	 * Constante para el servicio de consulta arqueo centro
	 */
	public static final String COD_TX_TR_OBTE_DATOS_ARQUEO_TRN = "VOF01MOU";

	/**
	 * Constante para el servicio de arqueo centro
	 */
	public static final String COD_TX_TR_ARQUEO_CAJA_EX_TRN = "VOF01MOU";

	/**
	 * Constante para el servicio de consulta cuadre de puestos
	 */
	public static final String COD_TX_TR_TN_CONT_HOST_2_CNS_TRN = "VPU01TLU";

	/**
	 * Constante para el servicio de consulta de perfil de empleado
	 */
	public static final String COD_TX_TR_EP_CON_PERFIL_EMPL_TRN = "GCA29CON";

	/**
	 * Constante para el servicio de modificacion de perfil de empleado
	 */
	public static final String COD_TX_TR_MODIF_PERFIL_EMPL_TRN = "GCS40MON";

	/**
	 * Constante para el servicio de alta de perfil de empleado
	 */
	public static final String COD_TX_TR_ALTA_PERFIL_EMPL_TRN = "GCS07MON";

	/**
	 * Constante para el servicio de consulta de grupos relacionados a un
	 * cliente
	 */
	public static final String COD_TX_TR_PE_RL_GR_LST_TRN = "PGR07CON";

	/**
	 * Contiene el código de la TR que consulta los datos adicionales
	 */
	public static final String COD_TR_CONSU_AMPLIAR_AC_TRN = "GAC04COU";

	/**
	 * Contiene el código de la TR que modifica los datos adicionales
	 */
	public static final String TR_MODIF_AMPLIAR_AC_TRN = "GAC21MOU";

	/**
	 * Contiene el código de la TR que consulta los centros asociados
	 */
	public static final String TR_CONSU_TODAS_OFI_AC_TRN = "GAC14COU";

	/**
	 * Contiene el código de la TR que modifica los centros asociados
	 */
	public static final String TR_MODIF_UO_AC_TRN = "GAC11MOU";

	/**
	 * Contiene el código de la TR que modifica los datos adicionales
	 */
	public static final String COD_TR_MODIF_AMPLIAR_AC_TRN = "GAC21MOU";

	/**
	 * Constante para la consulta del detalle de una anotacion
	 */
	public static final String COD_TX_TR_CONSUL_AN_PANT_TRN = "ATC12COU";

	/**
	 * Constante para la consulta del detalle de una anotacion
	 */
	public static final String COD_TX_TR_CONSULTA_AN_MAN_TRN = "ATC11COU";

	/**
	 * Constante para la modificacion de una anotacion
	 */
	public static final String COD_TX_TR_AN_MODI_DATOS_DP_TRN = "ATC18MOU";

	/**
	 * Constante para el alta de una anotacion
	 */
	public static final String COD_TX_TR_AN_ALTA_DATOS_DP_TRN = "ATC17MOU";

	/**
	 * Constante para la consulta de auditoria de historicos de cuentas
	 */
	public static final String COD_TX_TR_CONSU_AUDIT_ECV_AC_TRN = "GAC12COU";

	/**
	 * Constate para la consulta de Contadores de Centros
	 */
	public static final String COD_TX_TR_OBTE_CONT_TRN = "VOF02COU";

	/**
	 * Constante para la consulta de Accionistas y Funcionarios, Relaciones
	 * Bancarias y Comerciales
	 */
	public static final String COD_TX_PE_CONS_ORG_ACC_FUN_TRN = "PGE30CON";

	/**
	 * Constante para la consulta de Modificacion persona moral
	 */
	public static final String COD_TX_TR_PE_MODIF_ORG_TRN = "PGE06MON";

	/**
	 * Constante para la consulta de Modificacion perfil transaccional moral
	 */
	public static final String COD_TX_PE_MODI_ORG_PERF_TRAN_TRN = "PGE05MON";

	/**
	 * Constante para la consulta de perfil transaccional y persona moral
	 */
	public static final String COD_TX_PE_CONS_ORG_PERF_TRAN_TRN = "PGE04MON";

	/**
	 * Constante para la consulta de Informacion de Liquidaciones
	 */
	public static final String COD_TX_TR_CONS_HL_CD_APLIC_TRN = "GAE21COU";

	/**
	 * Constante para la consulta masiva de liquidaciones
	 */
	public static final String COD_TX_TR_LIQ_CONS_MASIVA_YZ_TRN = "GAE17COU";

	/**
	 * Constante para la consulta de detalle de liquidacion
	 */
	public static final String COD_TX_TR_LIQ_CONS_BASICA_TRN = "GAE20COU";

	/**
	 * Constante para la consulta de movimientos de liquidacion
	 */
	public static final String COD_TX_TR_CONS_HL_RL_AF_TRN = "GAE24COU";

	/**
	 * Constante para la consulta de auditoria de liquidacion
	 */
	public static final String COD_TX_TR_TX_CONS_AUDIT_TRN = "GAE15COU";

	/**
	 * Constante para la consulta de info adicional de auditoria de liquidacion
	 */
	public static final String COD_TX_TR_CARGAR_AUDIT_TRN = "GAE14COU";

	/**
	 * Constante para la simulacion de liquidaciones
	 */
	public static final String COD_TX_TR_LIQ_SIMULAR_LIQ_TRN = "GAE70COU";

	/**
	 * Constante para la cosnulta de tramo de liquidacion
	 */
	public static final String COD_TX_TR_CONS_HL_TRAMO_TRN = "GAE22COU";

	/**
	 * Constante para la consulta de contadores de puesto, tipo host
	 */
	public static final String COD_TR_TN_CONT_HOST_CNS_TRN = "VPU04COU";

	/**
	 * Constante para la consulta de detalle de un documento
	 */
	public static final String COD_TR_CONS_DOCUMENTO_TRN = "PDO02CON";

	/**
	 * Constante para la baja de un documento
	 */
	public static final String COD_TX_TR_PE_BAJA_DC_TRN = "PDO04MON";

	/**
	 * Constante para la alta de un documento
	 */
	public static final String COD_TX_TR_PE_ALTA_DC_TRN = "PDO01MON";

	/**
	 * Constante para la alta de apuntes CARGO
	 */
	public static final String COD_TX_TR_IMPUTAC_CTAS_BANCOS_TRN_CARGO = "DGE33OOU";

	/**
	 * Constante para consulta de domicilios de cuenta
	 */
	public static final String COD_TX_TR_CONSU_DIR_AC_TRN = "GAC03COU";

	/**
	 * Constante para alta de domicilios de cuenta
	 */
	public static final String COD_TX_TR_AC_CREAR_AC_DIR_TRN = "GAC24MOU";

	/**
	 * Constante para modificar domicilios de cuenta
	 */
	public static final String COD_TX_TR_MODIF_DIR_AC_TRN = "GAC23MOU";

	/**
	 * Constante para la alta de apuntes ABONO
	 */
	public static final String COD_TX_TR_IMPUTAC_CTAS_BANCOS_TRN_ABONO = "DGE34OOU";

	/**
	 * Constante para consulta de domicilios de personas
	 */
	public static final String COD_TX_TR_DR_DM_LS_CNS_TRN = "PGE25CON";

	/**
	 * Constante para consulta de detalles de domicilio
	 */
	public static final String COD_TX_TR_PE_CONS_DOMIC_TRN = "PGE20CON";

	/**
	 * Constante para el alta de domicilio de personas
	 */
	public static final String COD_TX_TR_PE_ALTA_DOMIC_TRN = "PGE19MON";

	/**
	 * Constante para la consulta de la lista de atribuciones de un empleado
	 */
	public static final String COD_TX_TR_EP_CON_LIS_ATRIB_EMPL_TRN = "GCA27CON";

	/**
	 * Constante para la consulta del detalle de atribuciones de un empleado
	 */
	public static final String COD_TX_TR_EP_CON_ATRIB_EMPL_1_TRN = "GCA26CON";

	/**
	 * Constante para el alta de atribuciones de un empleado
	 */
	public static final String COD_TX_TR_ALTA_ATRIB_EMPL_TRN = "GCS01MON";

	/**
	 * Constante para la modificacion de atribuciones de un empleado
	 */
	public static final String COD_TX_TR_EP_MODIF_ATRIB_EMPL_TRN = "GCS35MON";

	/**
	 * Constante para la baja de atribuciones de un empleado
	 */
	public static final String COD_TX_TR_BAJA_ATRIB_EMPL_TRN = "GCS08MON";

	/**
	 * Constante para la anulacion del apunte de un cargo
	 */
	public static final String COD_TX_TR_IMPUTAC_CTAS_BANCOS_1_TRN_CARGO = "DGE35OOU";

	/**
	 * Constante para la anulación del apunte de un abono
	 */
	public static final String COD_TX_TR_IMPUTAC_CTAS_BANCOS_1_TRN_ABONO = "DGE36OOU";

	/**
	 * Constante para la busqueda de lista de entidades
	 */
	public static final String COD_TX_TR_LISTA_ENTIDADES_2_TRN = "GCA49CON";

	/**
	 * Constante para el alta de bloqueo de cuenta
	 */
	public static final String COD_TX_TR_ALTA_BLOQUEO_PRTCN_TRN = "DGE03MOU";

	/**
	 * Constante para la baja de bloqueo de cuenta
	 */
	public static final String COD_TX_TR_BAJA_BLOQUEO_PRTCN_TRN = "DGE04MOU";

	/**
	 * Constante para los servicios de consulta de planificaciones
	 */
	public static final String COD_TX_TR_CONSU_PL_AC_YZ_TRN = "GAC09COU";

	/**
	 * Constante para los servicios de consulta de detalle planificaciones
	 */
	public static final String COD_TX_TR_CONSU_ADIC_PL_AC_TRN = "GAC02COU";

	/**
	 * Constante para el servicio de consulta de detalle de bien
	 */
	public static final String COD_TX_TR_BIEN_CNS_TRN = "PBI08CON";

	/**
	 * Constante para la busqueda de localidad
	 */
	public static final String COD_TX_TR_AG_LOCALIDAD_CNS_TRN = "PGE39CON";

	/**
	 * Constante para el servicio Consulta puestos de centro.
	 */
	public static final String COD_TX_TR_CON_LISTA_TERM_CENT_TRN = "VOF04TLU";

	/**
	 * Constante para la consulta general de apuntes.
	 */
	public static final String COD_TX_TR_CONS_GEN_APNTE_TRN = "GAE01COU";

	/**
	 * Constante para consultar el detalle de un apunte.
	 */
	public static final String COD_TX_TR_AF_CONS_APUNTE_TRN = "GAE05COU";

	/**
	 * Constante para la consulta ampliada de un apunte.
	 */
	public static final String COD_TX_TR_CONS_DETALLE_AF_TRN = "GAE03COU";

	/**
	 * Constante para la consulta de personas/domicilios compartidos
	 */
	public static final String COD_TX_TR_PE_COMP_LA_CNS_TRN = "PGE42CON";

	/**
	 * Constante para la simulacion de cancelacion de cuentas
	 */
	public static final String COD_TX_TR_SIMUL_AC_TRN = "GAC13COU";

	/**
	 * Constante para el servicio de Modificacion de Empleados
	 */
	public static final String COD_TX_TR_MODIF_EMPL_TRN = "GCS38MON";

	/**
	 * Constante para el servicio de Alta de Empleados
	 */
	public static final String COD_TX_TR_ALTA_EMPL_TRN = "GCS05MON";

	/**
	 * Constante para el servicio de consulta general de saldos.
	 */
	public static final String COD_TR_CONS_SALDOS_GEN_TRN = "GAE06COU";

	/**
	 * Constante para el servicio de consulta de saldos por fecha.
	 */
	public static final String COD_TR_SD_OBTE_SDO_FECHA_TRN = "GAE07COU";

	/**
	 * Constante para la consulta de lista de anotaciones
	 */
	public static final String COD_TX_TR_AN_CONS_ANOT_M_TRN = "ATC09COU";

	/**
	 * Constante para la modificacion de entidad
	 */
	public static final String COD_TX_TR_MODI_ENTIDAD_TRN = "GCS37MON";

	/**
	 * Constante para el servicio de Consulta de Productos Simples por cuenta.
	 */
	public static final String COD_TX_TR_PRESENTACION_AC_TRN = "GAC18COU";

	/**
	 * Constante para el servicio de Modificacion de Domicilio de Personas
	 */
	public static final String COD_TX_TR_PE_MODI_DOMIC_TRN = "PGE21MON";

	/**
	 * Constante para la consulta de centros por entidad
	 */
	public static final String COD_TX_TR_LIST_NOMB_CENT_TRN = "PGE21MON";

	/**
	 * Constante para el alta de cuenta
	 */
	public static String COD_TX_TR_SOLICITAR_AC_TRN = "GAC03MOU";

	/**
	 * Constante para la conversion de Cuentas
	 */
	public static final String TR_CNVT_CTA_VALENCIA_TRN = "SCI04COU";

	/**
	 * Constante para la consulta de un grupo por identificación.
	 */
	public static String COD_TX_TR_GR_LST_TRN = "PGR05CON";

	/**
	 * Constante para la modificacion de domicilios
	 */
	public static String COD_TX_TR_PE_BAJA_DOMIC_TRN = "PGE22MON";

	/**
	 * Constante para la consulta de datos de cuenta
	 */
	public static String COD_TX_TR_CONSULTA_ACUERDO_TRN = "GAC11COU";

	/**
	 * Constante para el servicio de Consulta de Productos Simples.
	 */
	public static final String COD_TX_TR_LISTA_RL_PV_PS_TRN = "GCC45COU";

	/**
	 * Constante para el servicio de Consulta de Anotaciones Relacionadas.
	 */
	public static final String COD_TX_TR_CONSUL_AN_AC_PANT_TRN = "ATC05COU";

	/**
	 * Constante para el servicio de Consulta Catalogo Valor Lista.
	 */
	public static final String COD_TX_TR_KP_CNS_LST_SMPL_TRN = "GKP00COU";

	/**
	 * Constante para el servicio de Consulta Catalogo Lista.
	 */
	public static final String COD_TX_TR_LISTA_DOM_PK_TRN = "GCC08COU";

	/**
	 * Constante para el listado de balances
	 */
	public static String COD_TX_TR_DC_BL_1_LS_CNS_TRN = "PDO14CON";

	/**
	 * Constante para la consulta del balance
	 */
	public static String COD_TX_TR_CONS_BLNCE_TRN = "PBA03CON";

	/**
	 * Constante para el alta de balance
	 */
	public static String COD_TX_TR_ALTA_BLNCE_TRN = "PBA01MON";

	/**
	 * Constante para la modificación del balance
	 */
	public static String COD_TX_TR_MODI_BLNCE_TRN = "PBA02MON";

	/**
	 * Constante para la consulta de detalle de tramo
	 */
	public static final String COD_TX_TR_CONS_VAL_MSV_KF_TRN = "GAC41COU";

	/**
	 * Constante para el alta de informe de clasificación
	 */
	public static String COD_TR_ALTA_DATOS_CL_AC_TRN = "GAC27MOU";

	/**
	 * Constante para lista Finalidad
	 */
	public static String COD_TR_LISTA_RL_TV_FIN_TRN = "GCC55COU";

	/**
	 * Constante para la busqueda de gestores por nombre.
	 */
	public static String COD_TX_TR_PE_CB_NOMBRE_CNS_TRN = "PGE11CON";

	/**
	 * Constante para la baja de centros controladores.
	 */
	public static String COD_TX_TR_BAJA_CENT_CTRL_TRN = "GCS09MON";

	/**
	 * Constante para ejecutar la trn de compartir domicilio de personas
	 */
	public static String COD_TX_TR_PE_COMPARTIR_DOMIC_TRN = "PGE23MON";

	/**
	 * Constante para la consulta del nombre de la condicion
	 */
	public static final String COD_TX_TR_CONS_GLOBAL_KP_RL_TS_TRN = "GAC18COU";

	/**
	 * Constante para ejecutar la trn de aprobar acuerdo
	 */
	public static String COD_TX_TR_APROBAR_AC_TRN = "GAC02MOU";

	/**
	 * Constante para ejecutar la trn de alta relaciones cuenta con cuenta
	 */
	public static String COD_TX_TR_ALTA_RX_PANT_TRN = "GAC60OOU";

	/**
	 * Constante para ejecutar la trn de consulta relaciones de tarifas
	 */
	public static String COD_TX_TR_YF_RL_AC_AC_CNS_TRN = "GAC98MOU";

	/**
	 * Constante para el servicio utilizado para compartir cuentas/personas en
	 * anotaciones
	 */
	public static String COD_TX_TR_ALTA_AN_COMP_PANT_TRN = "ATC13MOU";

	/**
	 * Constante para el servicio de consulta de cuentas instrumentales.
	 */
	public static String COD_TX_TR_CONS_AC_INST_OFIC_TRN = "GCS01COU";

	/**
	 * Constante para el servicio de consulta de paises para catalogo.
	 */
	public static String COD_TX_TR_AG_CNS_NOMBRE_TRN = "PGE55CON";

	/**
     *
     */
	public static final String COD_TR_PE_CL_CB_DA_CNS_TRN = "PGE07CON";

	/**
	 * Constante para el servicio de consulta relaciones cuenta cuentas
	 */
	public static String COD_TX_TR_CONSULTA_RX_PANT_TRN = "GAC62COU";

	/**
	 * Constante para el servicio de alta de cuentas instrumentales.
	 */
	public static String COD_TX_TR_ALTA_AC_INST_OFIC_TRN = "GCS54MON";

	/**
	 * Constante para el servicio de consulta información derivada
	 */
	public static final String COD_TX_TR_CONSU_DERIVADAS_AC_TRN = "GAC95COU";

	/**
	 * Constante para el servicio de consulta de apuntes por naturaleza.
	 */
	public static final String COD_TX_TR_CONS_CT_AF_TRN = "GAE08COU";

	/**
	 * Constante para el servicio de consulta datos de gestor
	 */
	public static final String COD_TX_TR_GT_CONS_GT_GESTOR_TRN = "PGT05CON";

	/**
	 * Constante para el servicio de consulta de perfiles de TCB para catalogo
	 */
	public static final String COD_TX_TR_LIST_PERFIL_ENT_TRN = "GCA41CON";

	/**
	 * Constante para el servicio de arqueo de puesto
	 */
	public static final String COD_TX_TR_TN_CONT_HOST_CNS_TRN = "VPU04COU";

	/**
	 * Constante para el servicio de cuadres de puestos
	 */
	public static final String COD_TX_UO_CUADRE_CONS_TNH_TRN = "VOF07TLU";

	/**
	 * Constante para el servicio de constituir acuerdo
	 */
	public static final String COD_TX_TR_CONSTI_TOTAL_AC_TRN = "GAC05OOU";

	/**
	 * Constante para el servicio de consulta de traspasos
	 */
	public static final String COD_TX_VVV_TRASPASOS_LISTM_TRN = "VPU14TLU";

	/**
	 * Constante para el servicio de consulta de saldos de centro
	 */
	public static final String COD_TX_UO_TERM_CNS_2M_LIST_TRN = "VOF07TLU";

	/**
	 * Constante para el servicio de consulta nivel cliente
	 */
	public static final String COD_TX_TR_BUSCA_NIVEL_PER_TRN = "PGE04CON";

	/**
	 * Constante para el servicio de consulta descripcion base calculo
	 */
	public static final String COD_TX_TR_CONS_LISTA_PSPK_PSPK_TRN = "GCC18COU";

	/**
	 * Constante para el servicio de alta de recibos no domiciliados y
	 * domicializacion
	 */
	public static final String COD_TX_TR_ALTA_RV_NORM57_TRN = "SVE15OOU";
	/**
	 * Constante para el servicio de alta de recibos no domiciliados y
	 * domicializacion COD_TX para pagos con cargo a una cuenta
	 */
	public static final String COD_TX_TR_ALTA_RV_NORM57_TRN_CUENTA = "SVE15OOU";

	/**
	 * Constante para el servicio de alta de recibos no domiciliados y
	 * domicializacion COD_TX para pagos en efectivo
	 */
	public static final String COD_TX_TR_ALTA_RV_NORM57_TRN_EFECTIVO = "SVE14OOU";

	/**
	 * Constante para el servicio de consulta de recibos no domiciliados
	 */
	public static final String COD_TX_TR_CONSUL_RCBO_VNTLLA_6_TRN = "SVE05COU";

	/**
	 * Constante para el servicio de anulacion de recibos no domiciliados
	 */
	public static final String COD_TX_TR_ANULACION_APLCCN_RV_TRN = "SVE13OOU";

	/**
	 * Constante para el servicio de modificaciòn relaciòn persona cuenta
	 */
	public static final String COD_TX_TR_MOD_RL_RP_PANT_TRN = "GAC65MOU";

	/**
	 * Constante para el servicio de consulta de referencias personales de
	 * personas fisicas de riesgo
	 */
	public static final String COD_TX_PE_CONS_INDV_SOCIOEC_TRN = "PGE30CON";

	/**
	 * Constante para la consulta la alta,modificacion,baja de refrencias
	 * personales de personas fisicas de riesgo
	 */
	public static final String COD_TX_PE_ALTA_INDV_REF_PER_TRN = "PGE05MON";

	/**
	 * Constante para la consulta de informacion financiera de riesgos de
	 * clientes (Plazas a Operar)
	 */
	public static final String COD_TX_TR_CNS_INF_FINANC_TRNN = "PGE17CON";

	/**
	 * Constante para el servicio de anotaciones de la cuenta
	 */
	public static final String COD_TX_TR_INFOR_AVISOS_AN_TRN = "ATC12COU";

	/**
	 * Constante para el servicio de baja relacion cuenta persona
	 */
	public static final String COD_TX_TR_BAJA_RP_PANT_TRN = "GAC64MOU";

	/**
	 * Constante para el servicio de alta de actividad empresarial.
	 */
	public static final String COD_TX_TR_PE_ALTA_COMPL_EMPR_TRN = "POT17MON";

	/**
	 * Constante para el servicio de alta de mercados organizados.
	 */
	public static final String COD_TX_TR_PE_ALTA_MERCADOS_ORG_TRN = "POT33MON";

	/**
	 * Constante para el servicio de alta de mercados organizados.
	 */
	public static final String COD_TX_TR_PE_AMPLI_CNAE_OBJSOC_TRN = "POT15MON";

	/**
	 * Constante para el servicio de mantenimiento de direcciones registrales.
	 */
	public static final String COD_TX_TR_DR_REG_MNT_TRN = "POT06MON";

	/**
	 * Constante para el servicio de modificación de actividad empresarial.
	 */
	public static final String COD_TX_TR_PE_MODIF_COMPL_EMPR_TRN = "POT20MON";

	/**
	 * Constante para el servicio de modificación de un Objeto Social CNAE.
	 */
	public static final String COD_TX_TR_PE_MODI_CNAE_OBJSOC_TRN = "POT14CON";

	/**
	 * Constante para el servicio de baja de un CNAE.
	 */
	public static final String COD_TX_TR_PE_BAJA_CNAE_OBJSOC_TRN = "POT13MON";

	/**
	 * Constante para el servicio de baja de un mercado organizado.
	 */
	public static final String COD_TX_TR_PE_BAJA_MERCADOS_ORG_TRN = "POT34MON";

	/**
	 * Constante para el servicio de consulta de Crcts de bienes
	 */
	public static final String COD_TX_TR_CRCT_LST_TRN = "PBI11CON";

	/**
	 * Constante para el servicio de modificaciòn relaciones cuenta-cuenta
	 */
	public static final String COD_TX_TR_MOD_RL_RX_PANT_TRN = "GAC62OOU";

	/**
	 * Constante para el servicio de alta de bienes materiales
	 */
	public static final String COD_TX_TR_ALTA_BIEN_MATERIALES_TRN = "PBI20MON";

	/**
	 * Constante para el servicio de consulta de datos adicionales de bien
	 */
	public static final String COD_TX_TR_CONS_DATOS_ADIC_TRN = "PBI08CON";

	/**
	 * Constante para el servicio de anulacion de constitucion de acuerdo
	 */
	public static final String COD_TX_TR_CONSU_ANULAR_AC_TRN = "GAC96COU";

	/**
	 * Constante para el servicio de retrocesion de cancelacion de acuerdo
	 */
	public static final String COD_TX_TR_RET_CANC_TOTAL_TRN = "GAC32OOU";

	/**
	 * Constante para el servicio de retrocesion de constitucion de acuerdo
	 */
	public static final String COD_TX_TR_RET_CONST_TOTAL_TRN = "GAC32OOU";

	/**
	 * Constante para el servicio de suspension de cuenta/acuerdo
	 */
	public static final String COD_TX_TR_SUSPENDER_AC_TRN = "GAC07MOU";

	/**
	 * Constante para el servicio de renegociacion de acuerdo
	 */
	public static final String COD_TX_TR_RENEGOCIAR_AC_TRN = "GAC09MOU";

	/**
	 * Constante para el servicio de cancelacion de acuerdo
	 */
	public static final String COD_TX_TR_CANCE_TOTAL_AC_TRN = "GAC06OOU";

	/**
	 * Constante para el servicio de espera
	 */
	public static final String COD_TX_TR_CX_SCI_ESPERA_OBTE_TRN = "GACT820";

	/**
	 * Constante para el servicio de cancelacion de acuerdos en estado
	 * solicitado
	 */
	public static final String COD_TX_TR_CANCE_TRAMI_AC_TRN = "GAC05MOU";

	/**
	 * Constante para el servicio de activacion de plazos
	 */
	public static final String COD_TX_TR_ACTVCN_IMPSCN_PAG_TRN = "DPL12OOU";

	/**
	 * Constante para el servicio de aprobacion de plazos
	 */
	public static final String COD_TX_TR_APROB_SOL_IMPSCN_PAG_TRN = "DPL09MOU";

	/**
	 * Constante para el servicio de modificacion de bienes materiales
	 */
	public static final String COD_TX_TR_MODI_BIEN_MATERIALES_TRN = "PBI22MON";

	/**
	 * Constante para el servicio de consulta historico de relacion
	 * persona-cuenta
	 */
	public static final String COD_TX_TR_CONSULTA_ECV_RP_TRN = "GAC69COU";

	/**
	 * Constante para el servicio de consulta historico de relacion
	 * cuenta-cuenta
	 */
	public static final String COD_TX_TR_RX_CONS_MASIVA_ECV_TRN = "GAC63COU";

	/**
	 * Constante para los servicios de consulta de condiciones de plazo.
	 */
	public static final String COD_TX_TR_CONS_VAL_KS = "GAC53COU";

	/**
	 * Constante para los servicios de modificacion de condiciones de plazo.
	 */
	public static final String COD_TX_TR_MODIFICAR_KS = "GAC43MOU";

	/**
	 * Constante para el servicio de peticion de activacion de IPF.
	 */
	public static final String COD_TX_TR_PETCN_IMPSCN_PAG_TRN = "DPL36MOU";

	/**
	 * Constante para el servicio de consulta de datos de peticion IPF.
	 */
	public static final String COD_TX_TR_DATOS_PET_IMPSCN_PAG_TRN = "DPL06COU";

	/**
	 * Constante para el servicio de apertura de puesto.
	 */
	public static final String COD_TX_TR_APERTURA_PUESTOS_TRN = "VPU20MOU";

	/**
	 * Constante para el servicio de verifica estado pan.
	 */
	public static final String COD_TX_TR_LST_STOCK_TARJETAS_TRN = "GAC11COU";

	/**
	 * Constante para el servicio de relacionar cuenta con pan
	 */
	public static final String COD_TX_TR_ACT_STOCK_TAR_TRN = "DGE12COU";

	/**
	 * Constante para la consulta de detalle de tramo de plazo
	 */
	public static final String COD_TX_TR_CONS_VAL_MSV_KL_TRN = "GAC19COU";

	/**
	 * Constante para la consulta de relaciones cuenta plazo
	 */
	public static final String COD_TX_TR_REL_IMPSCN_AC_TRN = "DPL24COU";

	/**
	 * Constante para la alta de relaciones cuenta cuenta
	 */
	public static final String COD_TX_TR_BAJA_RX_PANT_TRN = "DPL25MOU";

	/**
	 * Constante para la baja de relaciones cuenta plazo
	 */
	public static final String COD_TX_TR_ALTA_RL_IMPSCN_PAG_TRN = "GAC61OOU";

	/**
	 * Constante para la consulta de condiciones
	 */
	public static final String COD_TX_TR_CONS_VAL_MSV_KA_TRN = "GAC33COU";

	/**
	 * Constante para el servicio de consulta datos de integrante
	 */
	public static final String COD_TX_PE_CONS_INTGRNT_TRN = "PGE17CON";

	/**
	 * Constante para la modificacion de relaciones cuenta plazo
	 */
	public static final String COD_TX_TR_MDF_RL_IMPSCN_PAG_TRN = "DPL25MOU";

	/**
	 * Constante para la baja de relaciones cuenta plazo
	 */
	public static final String COD_TX_TR_BAJA_RL_IMPSCN_PAG_TRN = "DPL25MOU";

	/**
	 * Constante para la consulta de condicion rango tarifa
	 */
	public static final String COD_TX_TR_KP_CNS_RNG_SMP_TRN = "GKP06COU";

	/**
	 * Constante para la consulta de condicion interes tarifa
	 */
	public static final String COD_TX_TR_KP_CNS_INT_SMP_TRN = "GKP01COU";

	/**
	 * Constante para la consulta de condicion comision tarifa
	 */
	public static final String COD_TX_TR_KP_CNS_CMS_SMP_TRN = "GKP04COU";

	/**
	 * Constante para la consulta datos de condicion de tarifa
	 */
	public static final String COD_TX_TR_KP_CONS_CONDICION_TRN = "PGE05MON";

	/**
	 * Constante para el servicio de consulta de conceptos de un apunte.
	 */
	public static final String COD_TX_TR_NOTIF_CONS_PK_TRN = "GCC05COU";

	/**
	 * Constante para el servicio de consulta de datos de IPF.
	 */
	public static final String COD_TX_TR_DATOS_REN_IMPSCN_PAG_TRN = "DPL06COU";

	/**
	 * Constante para el servicio de consulta de plazo.
	 */
	public static final String COD_TX_TR_CONSULTA_PLAZO_TRN = "DPL21COU";

	/**
	 * Constante para el servicio de consulta de comisiones de una liquidación.
	 */
	public static final String COD_TX_TR_CONS_KT_TRN = "GAE16COU";

	/**
	 * Constante para el servicio de consulta limite concedido
	 */
	public static String COD_TX_TR_CONSU_OPERAC_AC_TRN = "GAC16COU";

	/**
	 * Constante para el servicio de consulta listado de productos simples /
	 * vendibles.
	 */
	public static final String COD_TX_TR_CONS_LISTA_PS_RL_PK_TRN = "GCC14COU";

	/**
	 * Constante para el servicio de pago de cuota de IPF
	 */
	public static final String COD_TX_TR_COBRAR_CUOTA_AHORRO_TRN = "DPL57OOU";

	/**
	 * Constante para el servicio de busqueda de nivel de cuenta
	 */
	public static final String COD_TX_TR_BUSCA_NIVEL_CTA_TRN = "DVI41OOU";

	/**
	 * Constante para el servicio de consulta niveladora de cuenta
	 */
	public static final String COD_TX_TR_CONSU_NIVEL_AC_TRN = "GAC10COU";

	/**
	 * Constante para el servicio de consulta niveladora de cuenta
	 */
	public static final String COD_TX_TR_CONS_CENTRO_TRN = "GCA19CON";

	/**
	 * Constante para el servicio de consulta datos detalle ipf
	 */
	public static final String COD_TR_CONS_IMPSCN_INDIV_TRN = "DPL22COU";

	/**
	 * Constante para el servicio de cierre contable de oficina.
	 */
	public static final String COD_TX_TR_CIERRE_OFICINA_TRN = "VOF03MOU";

	/**
	 * Constante para el servicio de alta de apuntes manuales
	 */
	public static final String COD_TX_T1_AM_INI_APNTE_MANO_TRN = "COA01OOU";

	/**
	 * Constante para el servicio de consulta de cuenta contables
	 */
	public static final String COD_TX_TR_CC_AM_LST_TRN = "COA03COU";

	/**
	 * Constante para el servicio de consulta de apuntes manuales
	 */
	public static final String COD_TX_TR_AM_CONS_APNTE_LST_TRN = "COA02COU";

	/**
	 * Constante para el servicio de consulta de detalle de apuntes manuales
	 */
	public static final String COD_TX_TR_AM_CONS_APNTE_1_LST_TRN = "COA05COU";

	/**
	 * Constante para el servicio de consulta de números de liquidación /
	 * demora.
	 */
	public static final String COD_TX_TR_LIQ_CONSULTAR_NUMEROS_TRN = "GAE69COU";
	
	/**
	 * Constante para el servicio de consulta de traspaso TF
	 */
	public static final String COD_TX_TR_PETCN_OBTE_TRFRCIA_TRN = "STR08CON";
	
	/**
	 * Constante para el servicio de consulta de traspaso CP
	 */
	public static final String COD_TX_TR_CP_CONSULTA_DETALLE_TRN = "DVI34COU";

	/**
	 * Constante para el servicio de consulta de cheque bancario CJ
	 */
	public static final String COD_TX_TR_CONSULTA_CHEQUE_2_TRN = "SCH01COU";

}