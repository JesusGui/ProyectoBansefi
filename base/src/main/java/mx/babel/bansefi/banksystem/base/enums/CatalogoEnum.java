package mx.babel.bansefi.banksystem.base.enums;

/**
 * Enumerado con los catálogos disponibles en HOST y su tamaño.
 * 
 * @author joseluis.pena
 */ 
public enum CatalogoEnum {
    TP_ECV_SUBVCN_PTMO("ECV SUBVENCION","00001","ASU", Boolean.FALSE ,Boolean.FALSE),
    TP_ANTCN("TIPO SUBCODIGOS DE ANOTACION","00001","ATC", Boolean.TRUE ,Boolean.FALSE),
    TP_PROFESION_RGA("CORREIDURIA DE AUTOMOVILES","00001","CRR", Boolean.FALSE ,Boolean.FALSE),
    TP_IMP_CONT("IMPORTE CONTABLE","00001","CRV", Boolean.FALSE ,Boolean.FALSE),
    TP_COD_ESTICO("CODIGOS ESTADISTICOS","00001","EVE", Boolean.FALSE ,Boolean.FALSE),
    TP_MOT_REC_CV_TRPS("TRASPASO","00001","FFI", Boolean.FALSE ,Boolean.FALSE),
    TP_GESTION("TIPO DE GESTION","00001","FOP", Boolean.FALSE ,Boolean.FALSE),
    TP_ACCION("ACCION DE UNA TRANSACCION","00001","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_CRCT_PDV("CARACTERISTICAS DE PRODUCTOS","00001","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_ENTIDAD_CR("CODIGOS DE ENTIDAD","00001","GCR", Boolean.FALSE ,Boolean.FALSE),
    TP_CONCPT("CONCEPTO DEUDA RECLASIFICACION","00001","GDP", Boolean.FALSE ,Boolean.FALSE),
    TP_EXP("TIPOS EXPEDIENTES RECLAMACION","00001","GDR", Boolean.FALSE ,Boolean.FALSE),
    TP_PRSPCTV("TIPO PERSPECTIVA","00001","GDU", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_KA("ESTADO CONDICION","00001","GKA", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_KL("RELACION DE DOMINIOS FILA SB","00001","GKS", Boolean.FALSE ,Boolean.FALSE),
    TP_TX("TIPOS DE TRANSACCIONES","00001","GOT", Boolean.FALSE ,Boolean.FALSE),
    TP_DEUDA("TIPO DEUDA","00001","GRP", Boolean.FALSE ,Boolean.FALSE),
    TP_INF_AD_SB("INF. ADICIONAL SUBACUERDO","00001","GSB", Boolean.FALSE ,Boolean.FALSE),
    TP_RL_DIR("RELACIONES CON DIRECCIONES","00001","ICA", Boolean.FALSE ,Boolean.FALSE),
    TP_CONCEPTO_CSB("CODIGOS DE CONCEPTO CSB","00001","ICU", Boolean.FALSE ,Boolean.FALSE),
    TP_CLASE_SP("CLASE DE REGALO","00001","MSP", Boolean.FALSE ,Boolean.FALSE),
    TP_LIN_BLNCE_ORG("LINEA DE BALANCE","00001","PBA", Boolean.FALSE ,Boolean.FALSE),
    TP_IDIOM_PERS("IDIOMA","00001","PGE", Boolean.FALSE ,Boolean.FALSE),
    TP_DIVISA_CIRBE("TIPOS DE DIVISA CIRBE","00001","RGE", Boolean.FALSE ,Boolean.FALSE),
    TP_MOT_DEV_CJ_TRN("MOT. DEVOL. CH. TRUNCAMIENTO","00001","SCH", Boolean.FALSE ,Boolean.FALSE),
    TP_OPER_OD("ACCION DE UNA TRANSACCION","00001","SOD", Boolean.FALSE ,Boolean.FALSE),
    TP_MOD_RECAUT("MODELOS DE RECAUDACION AUTNMCA","00001","SRA", Boolean.FALSE ,Boolean.FALSE),
    TP_TF("TIPO DE TRANSFERENCIA","00001","STR", Boolean.FALSE ,Boolean.FALSE),
    TP_CONCPT_OPER_VAL("CONCEPTOS DE OPERACIONES","00001","VAI", Boolean.FALSE ,Boolean.FALSE),
    TP_REF_INT("TIPOS DE REFERENCIAS INTERES","00001","VCC", Boolean.FALSE ,Boolean.FALSE),
    TP_ORIGEN_TRADUCIR("TP_ORIGEN_TRADUCIR","00001","VGE", Boolean.FALSE ,Boolean.FALSE),
    TP_PLASTICO("TIPOS DE TARJETA MEDIOS PAGO","00001","VMP", Boolean.FALSE ,Boolean.TRUE),
    TP_PRF_OMEGA_RGA("PROFESIONES DE OMEGA","00001","VSE", Boolean.FALSE ,Boolean.FALSE),
    TP_CONVENIO_MOPT("PLANES DE CONVENIOS DEL MOPU","00002","ASU", Boolean.FALSE ,Boolean.FALSE),
    TP_MARCA_RGA("CORREIDURIA DE AUTOMOVILES","00002","CRR", Boolean.FALSE ,Boolean.FALSE),
    TP_CLOP("CLAVES DE OPERACION","00002","DVI", Boolean.FALSE ,Boolean.FALSE),
    TP_APARTADO_FO("APARTADOS DE FORMULARIOS","00002","FOP", Boolean.FALSE ,Boolean.FALSE),
    TP_CRIT_REV("CRITERIOS DE REVISION","00002","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_APLCCN("CODIGOS DE APLICACION-SUBAPLI.","00002","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_CRCT_PDV_DOM("DOMINIOS CARACT. DE PRODUCTOS","00002","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_UM_PLAZO("UNIDAD MEDIDA PLAZO","00002","GDP", Boolean.FALSE ,Boolean.FALSE),
    TP_EXP_RL_EXP("TIPOS RELACION EXPEDIENTES","00002","GDR", Boolean.FALSE ,Boolean.FALSE),
    TP_RZN_RL_SITIRREG("TIPO RAZON SITUACION IRREGULAR","00002","GDU", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_KF("ESTADO CONDICION FILA","00002","GKA", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_KS("RELACION CONDICION SB","00002","GKS", Boolean.FALSE ,Boolean.FALSE),
    TP_RL_AC_SUBAC("REL. ACUERDO SUBACUERDO","00002","GSB", Boolean.FALSE ,Boolean.FALSE),
    TP_RESULT_OF("RAZONES RESULTADO OFERTA","00002","MOF", Boolean.FALSE ,Boolean.FALSE),
    TP_CRCT_BIEN("CARACTERISTICAS DE UN BIEN","00002","PBI", Boolean.FALSE ,Boolean.FALSE),
    TP_GTIA_CIRBE("TIPOS DE GARANTIA CIRBE","00002","RGE", Boolean.FALSE ,Boolean.FALSE),
    TP_CJ_CHQ_PG("TIPO CHEQUE INTERNO","00002","SCH", Boolean.FALSE ,Boolean.FALSE),
    TP_MOTIV_DEVOL_RB("MOTIVO DEVOLUCION RECIBO","00002","SDO", Boolean.FALSE ,Boolean.FALSE),
    TP_CENT_RECAUDADOR("CENTRO DE RECAUDACION AUTNMCA","00002","SRA", Boolean.FALSE ,Boolean.FALSE),
    TP_COD_TRIBUTO("TIPO DE TRIBUTO","00002","SVE", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_VX_OPRCN("ESTADOS DE OPERACIONES","00002","VAI", Boolean.FALSE ,Boolean.FALSE),
    TP_FAM_REF_INT("TP_FAM_REF_INT","00002","VCC", Boolean.FALSE ,Boolean.FALSE),
    TP_CLECTIVO_MP("COLECTIVOS DE MEDIOS DE PAGO","00002","VMP", Boolean.FALSE ,Boolean.TRUE),
    TP_BENEF_MOPT("TIPO BENEFICIARIO SUBVENCIONES","00003","ASU", Boolean.FALSE ,Boolean.FALSE),
    TP_GRP_ANTCN("TIPO GRUPO ANOTACION","00003","ATC", Boolean.FALSE ,Boolean.FALSE),
    TP_MODELO_RGA("CORREIDURIA DE AUTOMOVILES","00003","CRR", Boolean.FALSE ,Boolean.FALSE),
    TP_CP_ENT("CHEQUES PROPIA ENTIDAD","00003","DVI", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_AC("ESTADOS DEL ACUERDO","00003","GAC", Boolean.TRUE ,Boolean.FALSE),
    TP_CONT_TN("CONTADORES DE TERMINAL","00003","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_RL_PKPS("RELACION ENTRE PARAMETROS","00003","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_RZN_SIT_IRREG("RAZON SITUACION IRREGULAR","00003","GDU", Boolean.FALSE ,Boolean.FALSE),
    TP_REL_DOM_PSPK("RELACION DE DOMINIOS DE CONDI.","00003","GKA", Boolean.FALSE ,Boolean.FALSE),
    TP_LINEA_ASES("LINEAS DE ASESORAMIENTO","00003","GPR", Boolean.FALSE ,Boolean.FALSE),
    TP_SB("TIPO SUBACUERDO","00003","GSB", Boolean.FALSE ,Boolean.FALSE),
    TP_CAMP_RL_SP("RELACION DE CAMPA#A Y REGALO","00003","MCA", Boolean.FALSE ,Boolean.FALSE),
    TP_RZN_ALTA_BIEN("RAZON DE ALTA DE UN BIEN","00003","PBI", Boolean.FALSE ,Boolean.FALSE),
    TP_RZN_ALTA_PERS("RAZON DE ALTA DE LA PERSONA","00003","PGE", Boolean.FALSE ,Boolean.FALSE),
    TP_MOT_DEVOL_CJ("MOTIVOS DEVOLUCION CHEQUES INT","00003","SCH", Boolean.FALSE ,Boolean.FALSE),
    TP_MOT_DEV_RB_CSB("MOTIVO DEVOLUCION RECIBO CSB","00003","SDO", Boolean.FALSE ,Boolean.FALSE),
    TP_MOT_DEV_TF("MOTIVO DEVOLUCION TRANSFERENC","00003","STR", Boolean.FALSE ,Boolean.FALSE),
    TP_MODELO_HAC("TP_MODELO_HACIENDA","00003","SVE", Boolean.FALSE ,Boolean.FALSE),
    TP_BOLSA("UBICACIONES DE BOLSA","00003","VAI", Boolean.FALSE ,Boolean.FALSE),
    TP_VMP_COND_ECONOM("VMP_CONDICION ECONOMICA","00003","VMP", Boolean.FALSE ,Boolean.TRUE),
    TP_INF_ADIC_SN("INF.ADIC.SUBVENCION PRESTAMO","00004","ASU", Boolean.FALSE ,Boolean.FALSE),
    TP_PRDAD_ANTCN("TIPO PROPIEDAD ANOTACION","00004","ATC", Boolean.TRUE ,Boolean.FALSE),
    TP_USO_AUTO_RGA("CORREIDURIA DE AUTOMOVILES","00004","CRR", Boolean.FALSE ,Boolean.FALSE),
    TP_LIBRETA("TIPOS DE LIBRETA","00004","DGE", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_AC_AC("ESTADOS RELACION DE ACUERDO","00004","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_AUSNCA_EMPL("MOTIVOS AUSENCIA DEL EMPLEADO","00004","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_DEF_PK("DEFINICION DE PARAMETROS","00004","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_PROCTO("TIPO PROCEDIMIENTO","00004","GDR", Boolean.FALSE ,Boolean.FALSE),    
    TP_SIT_IRREG("TIPO SITUACION IRREGULAR","00004","GDU", Boolean.FALSE ,Boolean.FALSE),
    TP_RL_SUBAC_APNTE("RELACION SB. CON APUNTE","00004","GSB", Boolean.FALSE ,Boolean.FALSE),
    TP_EP_CAMP("CARGOS DE CAMPANYA","00004","MCA", Boolean.FALSE ,Boolean.FALSE),
    TP_VALOR_BIEN("VALORES DE UN BIEN","00004","PBI", Boolean.FALSE ,Boolean.FALSE),
    TP_PD_CIRBE("TIPOS DE RIESGO CIRBE","00004","RGE", Boolean.FALSE ,Boolean.FALSE),
    TP_CENT_GEST_TASA("TP_CENT_GEST_TASA","00004","SVE", Boolean.FALSE ,Boolean.FALSE),
    TP_PRECIO_VAL("TIPOS DE PRECIO DEL VALOR","00004","VAI", Boolean.FALSE ,Boolean.FALSE),
    TP_VMP_PRDTO_TCB("TP_VMP_PRDTO_TCB","00004","VMP", Boolean.FALSE ,Boolean.TRUE),
    TP_MOTIV_IC_SUBSID("TP_MOTIV_IC_SUBSID","00005","ASU", Boolean.FALSE ,Boolean.FALSE),
    TP_BLOQUEO("TIPOS DE BLOQUEO/PROTECCION","00005","DVI", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_AC_BI("ESTADOS RELACION AC CON BIENES","00005","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_CARGO_EMPL("CARGOS DE EMPLEADO","00005","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_DOM_VAL_PK("DOMINIOS VALORES PARAMETROS","00005","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_RL_PERS_EXP("TIPO RELACION PERS EXPEDIENTE","00005","GDR", Boolean.FALSE ,Boolean.FALSE),
    TP_CONCPT_NEGOC("CONCEPTOS DE NEGOCIO","00005","GPR", Boolean.FALSE ,Boolean.FALSE),
    TP_MEDIO("MEDIOS DE COMUNICACION","00005","MCA", Boolean.FALSE ,Boolean.FALSE),
    TP_BIEN("TIPO DE BIEN","00005","PBI", Boolean.FALSE ,Boolean.FALSE),
    TP_DOC("TIPO DE DOCUMENTO","00005","PDO", Boolean.FALSE ,Boolean.FALSE),
    TP_SIT_IRREG_CIRBE("TIPOS DE SITUACION IREGULAR CIRBE","00005","RGE", Boolean.FALSE ,Boolean.FALSE),
    TP_TASA_ESTATAL("TP_TASA_ESTATAL","00005","SVE", Boolean.FALSE ,Boolean.FALSE),
    TP_AGENTE_VALORES("AGENTE VALORES","00005","VAI", Boolean.FALSE ,Boolean.FALSE),
    TP_VMP_BIN_TJ("VMP_BIN_TARJETAS","00005","VMP", Boolean.FALSE ,Boolean.TRUE),
    TP_MOTIV_LIQ_COMPL("TP_MOTIV_LIQ_COMPL","00006","ASU", Boolean.FALSE ,Boolean.FALSE),
    TP_RETENCION("TIPOS DE RETENCION","00006","DGE", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_AC_DOC("ESTADOS DOCUMENTOS CON ACUERDO","00006","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_CENT_CTRL("TIPO DE CENTRO CONTROLADOR","00006","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_KP("ESTADO VIDA CONDICION","00006","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_SIT_RECLAM("SITUACION RECLAMACION","00006","GDR", Boolean.FALSE ,Boolean.FALSE),
    TP_MEDIO_RES("RESPUESTAS A MEDIO","00006","MCA", Boolean.FALSE ,Boolean.FALSE),
    TP_SOPTE_DC("SOPORTE DEL DOCUMENTO","00006","PDO", Boolean.FALSE ,Boolean.FALSE),
    TP_VTO_CIRBE("TIPOS DE VENCIMIENTO CIRBE","00006","RGE", Boolean.FALSE ,Boolean.FALSE),
    TP_BOLETIN("TP_BOLETIN","00006","SVE", Boolean.FALSE ,Boolean.FALSE),
    TP_ERROR_BCOVAL("TIPOS ERRORES PROCED. BANCOVAL","00006","VAI", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_AC_RL_DOC("ESTADOS DOCUMENTOS CON ACUERDO","00007","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_CENT_RL_CENT("TIPO DE RELACION ENTRE CENTROS","00007","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_MODIF_CD("MODIFICACION DE CONDICIONES","00007","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_GASTO_RECLAM("GASTOS DE RECLAMACION","00007","GDR", Boolean.FALSE ,Boolean.FALSE),
    TP_PRSTO_CAMP("PARTIDAS DE PRESUPUESTO","00007","MCA", Boolean.FALSE ,Boolean.FALSE),
    TP_FORM_VERSION("PET. DUPLICADOS MAVER","00007","PDO", Boolean.FALSE ,Boolean.FALSE),
    TP_RGSTRO("TIPO DE REGISTRO","00007","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_RL_PD_CIRBE_GRP("EQUIV. PRODUCTOS-RIESGO CIRBE","00007","RGE", Boolean.FALSE ,Boolean.FALSE),
    TP_MOTIVO_RECLAM("MOTIVOS RECLAMACION CHEQUES","00007","SCH", Boolean.FALSE ,Boolean.FALSE),
    TP_EMISORA_57("TP_EMISORA_57","00007","SVE", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_VL("TIPOS DE ECV VALOR","00007","VAI", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_ANTCN("ESTADO ANOTACION","00008","ATC", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_TD("ESTADO VIDA TRAMEADO","00008","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_RZN_CANCEL_RJ("RAZON CANCELACION P.JUDICIAL","00008","GDR", Boolean.FALSE ,Boolean.FALSE),
    TP_FOR_CORREO("FORMATO CORREO","00008","MCA", Boolean.FALSE ,Boolean.FALSE),
    TP_VIA("TIPO DE VIA","00008","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_CJ_CHQ_PG_CSB("TIPO DE CHEQUE CSB","00008","SCH", Boolean.FALSE ,Boolean.FALSE),
    TP_CONCEPTOS_PAGO("TP_CONCEPTOS_PAGO","00008","SVE", Boolean.FALSE ,Boolean.FALSE),
    TP_CLASE_VALOR("TIPOS DE CLASE VALOR","00008","VAI", Boolean.FALSE ,Boolean.FALSE),
    TP_LIM_SDO_PREPAGO("LIMITE SALDO TARJETA PREPAGO","00009","DVI", Boolean.FALSE ,Boolean.FALSE),
    TP_CAMPO_PE("DATOS OBLIGATORIOS DE CLIENTE","00009","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_PDV("ESTADO VIDA PRODUCTO VENDIBLE","00009","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_GRP("TIPO DE GRUPO","00009","POT", Boolean.TRUE ,Boolean.FALSE),
    TP_LLAVES_SECRETAS("TP_LLAVES_SECRETAS","00009","SVE", Boolean.FALSE ,Boolean.FALSE),
    TP_EMISORA_VALOR("ENTIDADES EMISORAS DE VALORES","00009","VAI", Boolean.FALSE ,Boolean.FALSE),
    TP_CP_CHQ_EMISION("CHEQUES EMISION","00010","DVI", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_PERS_AC("ESTADO PERSONA CON ACUERDO","00010","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_HL_RL_HL("RELACIONES ENTRE LIQUIDACIONES","00010","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_CCPS("ESTADO VIDA TARIFA SIMPLE","00010","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_PERS_RL_PERS("RELACION PERSONA-PERSONA","00010","POT", Boolean.TRUE ,Boolean.TRUE),
    TP_SBYACNTE_WRANT("TIPO SUBYACENTE VALOR WARRANTS","00010","VAI", Boolean.FALSE ,Boolean.FALSE),
    TP_FNDAD_AC("FINALIDADES DEL ACUERDO","00011","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_HL_RL_AF("APUNTES DE LIQUIDACIONES","00011","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_INFO_ADIC_PL_CR("INFORMACION ADICIONAL EN TERM.","00011","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_TRFA_PDV("ESTADO VIDA TARIFA VENDIBLE","00011","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_SIST_LIQ_VAL("TIPO SISTEMA LIQUIDACION VALOR","00011","VAI", Boolean.FALSE ,Boolean.FALSE),
    TP_FRECUENCIA("TIPOS DE FRECUENCIAS","00012","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_INFO_ADIC_PL_UO("INFORMA. ADICIONAL EN CENTROS","00012","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_ECV_TRFA_CESTA("ESTADO VIDA TARIFA CESTA","00012","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_INF_ADIC_AC("INF. ADICIONAL DEL ACUERDO","00013","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_MDLDAD_CENT("MODALIDAD DE UN CENTRO.","00013","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_INF_ADIC_TV("ESTADO VIDA TARIFA CESTA","00013","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_TRATMTO_PERS("TRATAMIENTO DE LA PERSONA","00013","PGE", Boolean.FALSE ,Boolean.FALSE),
    TP_INF_ADIC_PLNFCN("INF. ADICIONAL PLANIF. DEL AC","00014","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_FNESP_PERS("RELACIONES ESPECIALES CON LA E","00014","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_INF_DRVD_AC("INF. DERIVADA ACUERDO","00015","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_PERF_TERMINAL("PERFIL DE LA OFICINA DEL TERMI","00015","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_TV_RL_TV("TARI. VENDIBLE RL TARI. VENDIB","00015","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_CNAE_PERS("C.N.A.E.","00015","POT", Boolean.FALSE ,Boolean.TRUE),
    TP_INFADI_PERS_AC("INF. ADICIONAL PERSONA AC","00016","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_SISTEMA("SISTEMAS DE CONEXION DE ENTIDAD","00016","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_CTROL_CONTRATA("CONTROLES CONTRATACION TARIFA","00016","GCC", Boolean.FALSE ,Boolean.FALSE),
    TP_NOMB_PERS("TIPO DE NOMBRE DE LA PERSONA","00016","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_INFO_ADIC_PE_AC("INF. ADIC. REL PERSONA AC","00017","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_TERMINAL("TIPO DE TERMINAL","00017","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_ID_EXT_PERS("IDENTIFICACION EXTERNA","00017","PGE", Boolean.FALSE ,Boolean.FALSE),
    TP_ID_EXT_PERS_PAS("IDENTIFICACION EXTERNA PASIVOS","S0050","034", Boolean.FALSE ,Boolean.FALSE),
    TP_ORGN_AC("ORIGEN DEL ACUERDO","00018","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_PROV_AEAT_AG("PROVINCIA AEAT","00018","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_OTRS_ID_EXT_AC("OTROS IDENTIF. DEL ACUERDO","00019","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_PRD_POL_SCORING("POLITICAS SCORING RESTRINGIDAS","00019","GCA", Boolean.FALSE ,Boolean.FALSE),
    TP_SIT_ECON_PERS("SITUACION ECONOMICA","00019","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_OTRS_PERS_AC("OTRAS PERSONAS DEL ACUERDO","00020","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_PLAZA_BANCARIA("PLAZAS BANCARIAS","00020","GCA", Boolean.FALSE ,Boolean.TRUE),
    TP_OTR_RL_PERS("OTRAS RELACIONES CON LA ENTID.","00020","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_RL_AC_AC("RELACION ENTRE ACUERDOS","00021","GAC", Boolean.FALSE ,Boolean.TRUE),
    TP_RL_AC_BI("RELACION BIEN DEL ACUERDO","00022","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_CNO_INDV("CODIGO NACIONAL DE OCUPACION","00022","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_RL_AC_DOC("RELACION DOC. CON ACUERDO","00023","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_CNTRTO_INDV("TIPO DE CONTRATO","00023","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_RL_AC_TP_DOC("RELACION TIPO DOC. CON AC","00024","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_NIV_INGR_INDV("NIVEL DE INGRESOS","00024","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_RL_AC_UO("RELACION ACUERDO OFICINA","00025","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_ORGN_INGR_INDV("ORIGEN DE INGRESOS","00025","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_RL_PERS_AC("RELACION PERSONA ACUERDO","00026","GAC", Boolean.FALSE ,Boolean.TRUE),
    TP_GRADO_TIT_INDV("GRADO DE TITULACION","00026","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_RZECV_AC_RL_DOC("RAZON ESTADO DOC. ACUERDO","00027","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_DENOM_TIT_INDV("DENOMINACION DEL TITULO","00027","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_RZN_ECV_AC("RAZON ESTADO ACUERDO","00028","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_CPCDAD_LGL_INDV("CAPACIDAD LEGAL DEL INDIVIDUO","00028","PGE", Boolean.FALSE ,Boolean.FALSE),
    TP_RZN_ECV_AC_AC("RAZON ESTADO REL. ACUERDO","00029","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_RZN_ECV_AC_BI("RAZON ESTADO BIEN ACUERDO","00030","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_AF_RL_AF("RELACIONES ENTRE APUNTES","00030","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_MERCADOS_ORG("MERCADOS ORGANIZADOS","00030","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_RZN_ECV_RP("RAZON ESTADO PERS. ACUERDO","00031","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_AF_INF_DRVD("INFORMACION ADICIONAL APUNTES","00031","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_EMPL_INDV("SITUACION DEL EMPLEADO","00031","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_CTA("TIPOS DE CUENTAS","00032","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_EST_CIVIL_INDV("ESTADO CIVIL","00032","PGE", Boolean.FALSE ,Boolean.FALSE),
    TP_AI_GTIA("TIPO GARANTIA BIEN","00033","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_INDIC("INDICADORES DE CUENTAS","00033","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_RGMN_MATRI_INDV("REGIMEN DEL MATRIMONIO","00033","PGE", Boolean.FALSE ,Boolean.FALSE),
    TP_GRTIA_AC("TIPOS DE GARANTIA AC","00034","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_SDO("TIPOS DE SALDOS","00034","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_EST_LAB_INDV("ESTADO LABORAL","00034","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_GASTO_EXT("TIPO GASTO EXTERNO","00035","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_RZN_SDO("TIPO DE SALDOS A FECHA","00035","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_ESTRCT_LGL_ORG("ESTRUCTURA LEGAL DE LA ORGANI.","00035","PGE", Boolean.FALSE ,Boolean.FALSE),
    TP_CLAS_OFIC("CLASIF. OFICIAL FINALIDADES","00036","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_DOM_INDIC("DOMINIOS DE INDICADORES","00036","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_AMBTO_ORG("AMBITO DE LA ORGANIZACION","00036","PGE", Boolean.FALSE ,Boolean.FALSE),
    TP_CLAS_OFIC_FNDAD("SUBCLAS. OFICIAL FINALIDADES","00037","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_NOMB_SDO("NOMBRES DE SALDOS","00037","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_PERS_CTBLE("PERSONAS CONTABLES","00037","PGE", Boolean.FALSE ,Boolean.FALSE),
    TP_AFICIONES("AFICIONES","00037","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_NATURALEZA_GTIA("NATURALEZA GTIA","00038","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_OPER_LIQ("OPERACIONES DE LIQUIDACIONES","00038","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_SIT_RESDNC_PE("RESIDENCIA DE LA PERSONA","00038","PGE", Boolean.FALSE ,Boolean.FALSE),
    TP_SEC_CNAE_PERS("SECCION DEL CNAE","00038","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_NTRLZA_FNDAD("NATURALEZA FINALIDADES","00039","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_PARAM_LIQ("PARAMETROS DE LIQUIDACIONES","00039","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_COMPUTO_FGD("COD FONDO GARANTIA DEPOSITO","00039","PGE", Boolean.FALSE ,Boolean.FALSE),
    TP_LETRA_CIF("TP_LETRA_CIF","00039","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_ORIGEN_GTIA("ORIGEN GARANTIA","00040","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_INF_ADIC_HL("INFORMACION ADICIONAL LIQUIDA","00040","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_LICENCIA_PERS("TIPOS DE LICENCIAS","00040","POT", Boolean.FALSE ,Boolean.FALSE),
    TP_UM("UNIDADES DE MEDIDA","00041","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_MODIF_HL("MODIFICACION DE LIQUIDACIONES","00041","GAE", Boolean.FALSE ,Boolean.FALSE),
    TP_DSTN_INVERSION("DESTINO INVERSION","00042","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_INVERSOR_VAL("INVERSORES","00043","GAC", Boolean.FALSE ,Boolean.FALSE),
    TP_SUSCRIPT_VAL("SUSCRIPTORES","00044","GAC", Boolean.FALSE ,Boolean.FALSE),
    
    // Catálogos en Front
    OP_DEPOSITO("CLOP CARGOS PASIVO","S0050","001", Boolean.FALSE ,Boolean.FALSE),
    OP_RETIRO("CLOP ABONOS PASIVO","S0050","002", Boolean.FALSE ,Boolean.FALSE),
    TP_OCUPACION_VIV("REGIMEN OCUPACION VIVIENDA","S0050","003", Boolean.FALSE ,Boolean.FALSE),
    DESGLOSE_CTBLE("TIPO DESGLOSE CONTABLE","S0050","004", Boolean.FALSE ,Boolean.FALSE),
    FORM_CALCULO("CALCULO PERIODO","S0050","005", Boolean.FALSE ,Boolean.FALSE),
    ESTADO_BIEN("ESTADO DEL BIEN","S0050","006", Boolean.FALSE ,Boolean.FALSE),
    UDAD_SUP("CODIGO UNIDAD MEDICION","S0050","007", Boolean.FALSE ,Boolean.FALSE),
    CARGAS("TIPO DE CARGA BIEN","S0050","008", Boolean.FALSE ,Boolean.FALSE),
    RGMEN_BIEN("REGIMEN DEL BIEN","S0050","009", Boolean.FALSE ,Boolean.FALSE),
    USO_BIEN("USO DEL BIEN","S0050","010", Boolean.FALSE ,Boolean.FALSE),
    ORIGEN_ADQ("ORIGEN ADQUISICION BIEN","S0050","011", Boolean.FALSE ,Boolean.FALSE),
    TP_EMPRESA("TIPO DE EMPRESA","S0050","012", Boolean.FALSE ,Boolean.FALSE),
    CALIF_INSOLV("CALIFICACION EMPRESA","S0050","013", Boolean.FALSE ,Boolean.FALSE),
    TP_TELEFONO("TIPO DIR ELECTRONICA","S0050","014", Boolean.FALSE ,Boolean.FALSE),
    FREC_REV_DOCUM("FRECUENCIA REVISION DOCUMENTO","S0050","015", Boolean.FALSE ,Boolean.FALSE),
    COD_GTIA_CTBLE("GARANTIA CONTABLE INF CLASIFICACION","S0050","016", Boolean.FALSE ,Boolean.FALSE),
    COD_ACTIVO("CODIGO ACTIVO INF CLASIFICACION","S0050","018", Boolean.FALSE ,Boolean.FALSE),
    IND_SOCIO("IND SOCIO INF CLASIFICACION","S0050","020", Boolean.FALSE ,Boolean.FALSE),
    SIT_RESIDENCIA("SITUACION RESIDENCIA INF CLASIFICACION","S0050","021", Boolean.FALSE ,Boolean.FALSE),
    PRIORIDAD_MSG("PRIORIDAD MENSAJE","S0050","022", Boolean.FALSE ,Boolean.FALSE),
    TP_REL_RSGO("TIPO REL PERSONA MORAL RIESGO","S0050","023", Boolean.FALSE ,Boolean.FALSE),
    FORM_REVISION("FORMA REVISION","S0050","024", Boolean.FALSE ,Boolean.FALSE),
    FREC_REV_PERIOD("FRECUENCIA REVISION PERIODOS","S0050","025", Boolean.FALSE ,Boolean.FALSE),
    TIPO_RELACION_ANOTACION("TIPO DE RELACION DE LA ANOTACION","S0050","026", Boolean.FALSE ,Boolean.FALSE),
    PRIORIDAD_ANOTACION("PRIORIDAD DE ANOTACION","S0050","027", Boolean.FALSE ,Boolean.FALSE),
    TIPO_ANOTACION("TIPO DE ANOTACION","S0050","028", Boolean.FALSE ,Boolean.FALSE),
    SIGNO_DIARIO("SIGNO CONTABLE DIARIO ELECTRONICO","S0050","029", Boolean.FALSE ,Boolean.FALSE),
    TP_OPER_DIARIO("TIPO DE OPERACION DIARIO ELECTRONICO","S0050","030", Boolean.FALSE ,Boolean.FALSE),
    RES_DIARIO("RESPUESTA DIARIO ELECTRONICO","S0050","031", Boolean.FALSE ,Boolean.FALSE),
    SIT_DIARIO("SITUACION DIARIO ELECTRONICO","S0050","032", Boolean.FALSE ,Boolean.FALSE),
    TP_COND_ACUERDOS("TIPO DE CONDICIONES DE CUENTAS","S0050","033", Boolean.FALSE ,Boolean.FALSE),
    SIT_BLOQUEO("SITUACION DE BLOQUEO DE CUENTAS","S0050","035", Boolean.FALSE ,Boolean.FALSE),
    TP_DOMIC("TIPO DE DOMICILIO","S0050","036", Boolean.FALSE ,Boolean.FALSE),
    PERFILES_NSS("PERFILES NSS","S0050","037", Boolean.FALSE ,Boolean.FALSE),
    PLANIF_CUENTAS("PLANIFICACIONES DE CUENTAS","S0050","038", Boolean.FALSE ,Boolean.FALSE),
    SITUACION_LIQUIDACION("SITUACION DE LIQUIDACION","S0050","039", Boolean.FALSE ,Boolean.FALSE),
    SALDO_LIQUIDACION("DESCRIPCION SALDO LIQUIDACION","S0050","040", Boolean.FALSE ,Boolean.FALSE),
    TIPO_RETENCION("TIPOS DE RETENCIONES", "S0050", "041", Boolean.FALSE, Boolean.FALSE),
    ESTADO_RETENCION("ESTADOS DE LAS RETENCIONES", "S0050", "042", Boolean.FALSE, Boolean.FALSE),
    POSICION("POSICIONES DE LA CUENTA O GRUPO", "S0050", "043", Boolean.FALSE, Boolean.FALSE),
    ENTIDAD_DEUDA("ENTIDAD DE LA DEUDA", "S0050", "044", Boolean.FALSE, Boolean.FALSE),
    VALOR_INF_DERIVADA("VALOR PARA INFORMACION DERIVADA NO NUMERICA", "S0050", "045", Boolean.FALSE, Boolean.FALSE),
    DESCRIPCION_INF_DERIVADA("DESCRIPCION PARA INFORMACIÓN DERIVADA NÚMERICA", "S0050", "046", Boolean.FALSE, Boolean.FALSE),
    DESCRIPCION_SALDO_LIQUIDACION("DESCRIPCION SALDO LIQUIDACION", "S0050", "047", Boolean.FALSE, Boolean.FALSE),
    TIPO_COMUNICADO_LIQUIDACION("DESCRIPCION SALDO LIQUIDACION", "S0050", "048", Boolean.FALSE, Boolean.FALSE),
    MODALIDAD_LIQUIDACION("DESCRIPCION SALDO LIQUIDACION", "S0050", "049", Boolean.FALSE, Boolean.FALSE),
    FORMA_APPCN("FORMA APLICACION DE UNA LIQUIDACION", "S0050", "050", Boolean.FALSE, Boolean.FALSE),
    OPERACION_RETIRO("OPERACIONES PARA RETIRO","S0050","001",Boolean.FALSE,Boolean.FALSE),
    OPERACION_DEPOSITO("OPERACIONES PARA DEPOSITO","S0050","002",Boolean.FALSE,Boolean.FALSE),
    ORIGEN_LIQUIDACION("ORIGEN DE LA LIQUIDACION","S0050","052",Boolean.FALSE,Boolean.FALSE),
    DISPOSICION("DISPOSICION","S0050","055",Boolean.FALSE,Boolean.FALSE),
    CLAUSULAS("CLAUSULAS","S0050","056",Boolean.FALSE,Boolean.FALSE),
    TP_OP_CHEQUE("TIPO OPERACION CHEQUE","S0050","057",Boolean.FALSE,Boolean.FALSE),
    CLASE_TF("CLASE TRANSFERENCIA","S0050","058",Boolean.FALSE,Boolean.FALSE),
    MOT_DEV_TF("MOTIVO DEVOLUCION","S0050","059",Boolean.FALSE,Boolean.FALSE),
    ECV_CHEQUE("ESTADO CHEQUE","S0050","060",Boolean.FALSE,Boolean.FALSE),
    SIT_PAGO("SITUACION PAGO","S0050","061",Boolean.FALSE,Boolean.FALSE),
    ESTATUS_CAJA_CENT("ESTATUS OPERACION","S0050","051",Boolean.FALSE,Boolean.FALSE),
// 2015 12 11 // 
    
    TEL_DIR_ELECTRONICAS("TELEFONO Y DIR ELECTRONICAS","S0050","014",Boolean.FALSE,Boolean.FALSE),
    ESTATUS_PETICION("ESTATUS DE UNA PETICION","S0050","051",Boolean.FALSE,Boolean.FALSE),
    CODIGO_DISTRIBUCION("CODIGO DE DISTRIBUCION","S0050","053",Boolean.FALSE,Boolean.FALSE);

    
    private String descripcion;
    private String codTblRef;
	private String codAplccnSubapl;
    private boolean loadOnServerStart;
    private boolean descripcionExtendida;

	private CatalogoEnum(final String descripcion, final String codTblRef, final String codAplccnSubapl,final boolean loadOnServerStart, final boolean descripcionExtendida) {
        this.descripcion = descripcion;
        this.codAplccnSubapl = codAplccnSubapl;
        this.codTblRef = codTblRef;
        this.loadOnServerStart = loadOnServerStart;
        this.descripcionExtendida = descripcionExtendida;
	}

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the codTblRef
     */
    public String getCodTblRef() {
        return codTblRef;
    }

    /**
     * @return the codAplccnSubapl
     */
    public String getCodAplccnSubapl() {
        return codAplccnSubapl;
    }

    /**
     * @return the loadOnServerStart
     */
    public boolean isLoadOnServerStart() {
        return loadOnServerStart;
    }
    
	/**
     * @return the loadOnServerStart
     */
	public boolean isDescripcionExtendida() {
		return descripcionExtendida;
	}
	
}