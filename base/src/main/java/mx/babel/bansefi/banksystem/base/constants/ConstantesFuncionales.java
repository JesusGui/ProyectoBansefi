package mx.babel.bansefi.banksystem.base.constants;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.enums.CuentaEnum;

import org.apache.commons.lang3.StringUtils;

/**
 * Clase de constantes para almacenar cualquier valor estático definido por
 * funcionalidades de la aplicación.
 *
 * @author mario.montesdeoca
 *
 */
public class ConstantesFuncionales {

	private ConstantesFuncionales(){
		//private constructor to hide the implicit public one
	}

	public static final String APP_BASE_CONTEXT = "banksystem";

	public static final String PARAM_MENSAJE_NOTIFICACION = "msjNotificacion";

	/**
	 * Codigo para la opcion de menu devuelta en el login para la busqueda
	 */
	public static final String COD_MENU_BUSQUEDA = "1";

	/**
	 * Codigo para el texto de la opcion de menu en el login para la busqueda
	 */
	public static final String COD_MENU_BUSQUEDA_TEXTO = "Búsqueda";

	/**
	 * Codigo de CASADO para el catalogo de estado civil - TP_EST_CIVIL_INDV
	 */
	public static final String TP_EST_CIVIL_INDV_CASADO = "05";

	/**
	 * Codigo de LIMITADA TEMPORAL para el catalogo de Solvencia Moral
	 * (Capacidad Legal) - TP_CPCDAD_LGL_INDV
	 */
	public static final String TP_CPCDAD_LGL_INDV_LIMITADA_TEMPORAL = "2";

	/**
	 * Codigo de MENOR 18 para el catalogo de tipo de Identificacion -
	 * TP_ID_EXT_PERS
	 */
	public static final String TP_ID_EXT_PERS_MENOR = "55";

	/**
	 * Constantes para el idioma Español
	 */
	public final static String COD_IDIOMA_ESPAN = "01";

	/**
	 * Codigo de MENOR DE EDAD para el catalogo de estado laboral -
	 * TP_EST_LAB_INDV
	 */
	public static final String TP_EST_LAB_INDV_MENOR = "07";

	/**
	 * Codigo de MENOR DE EDAD para el catalogo de cno - TP_CNO_INDV
	 */
	public static final String TP_CNO_INDV_MENOR = "15";

	/**
	 * Subcodigos para anotaciones de tipo informativas.
	 * </br>"100","101","102","103","104","105","106","107","1Z0"
	 */
	public static List<String> getSubcodigosAnotacionesInformativas() {
		return Arrays.asList("100", "101", "102", "103", "104", "105", "106",
				"107", "1Z0");
	}

	/**
	 * Subcodigos para anotaciones de tipo informativas.
	 */
	public static final String SUBCODIGOS_ANOTACIONES_AVISOS = "4[0-6][0-9]|47[0-4]|4Z0";

	/**
	 * Códigos de tipo de Anotacion (COD_ANTCN).
	 */
	public static final List<String> TIPOS_ANOTACION = Arrays.asList("1", "4");

	/**
	 * Códigos de dirigidoA de Anotacion (DEST_ANTCN). </br>"3","1"
	 */
	public static final List<String> DEST_ANTCN = Arrays.asList("3", "1");

	public static final List<String> DEST_ANTCN_PERSONAS = Arrays.asList("1",
			"2");

	public static final List<String> DEST_ANTCN_CUENTAS = Arrays.asList("3",
			"4");

	/**
	 * Catalogo de direcciones
	 */
	public static final CatalogoBean DIR_INTERNET = new CatalogoBean("08",
			"INTERNET");
	public static final List<CatalogoBean> CATALOGO_DIRECCIONES = Arrays
			.asList(new CatalogoBean("02", "TELEFONO"), DIR_INTERNET);

	/**
	 * Denominaciones de moneda y billetes.
	 * </br>"0.1:m","0.2:m","0.5:m","1:m","2:m"
	 * ,"5:m","10:m","20:m","20:b","50:b","100:b","200:b","500:b","1000:b"
	 */
	public static final List<String> getValorDenominaciones() {
		return Arrays.asList("0.01:m", "0.1:m", "0.2:m", "0.5:m", "1:m", "2:m",
				"5:m", "10:m", "20:m", "20:b", "50:b", "100:b", "200:b",
				"500:b", "1000:b");
	};

	/**
	 * Número de registros a mostrar por pagina en una tabla
	 */
	public static final int NUM_FILAS_PAGINA = 10;

	/**
	 * Mensaje paginación catálogo geográfico
	 */
	public static String MSG_1 = "NO EXISTEN MAS HACIA ATRAS, SI ADELANTE";

	/**
	 * Mensaje paginación catálogo geográfico
	 */
	public static String MSG_2 = "SI HAY REGISTROS HACIA ATRAS, Y ADELANTE";

	/**
	 * Constantes para la direccion registral
	 */
	public static final int TP_DIRECCION_REGISTRAL_LEN = 25;

	public static final String TP_COD_COMP_RGSTRO_NUM_REGISTRAL = "01";
	public static final int TP_VAL_COMP_RGSTRO_NUM_REGISTRAL_LEN = 15;

	public static final String TP_COD_COMP_RGSTRO_TOMO = "02";
	public static final int TP_VAL_COMP_RGSTRO_TOMO_LEN = 15;

	public static final String TP_COD_COMP_RGSTRO_LIBRO = "03";
	public static final int TP_VAL_COMP_RGSTRO_LIBRO_LEN = 5;

	public static final String TP_COD_COMP_RGSTRO_FOLIO = "04";
	public static final int TP_VAL_COMP_RGSTRO_FOLIO_LEN = 15;

	public static final String TP_COD_COMP_RGSTRO_NUM_INSC = "05";
	public static final int TP_VAL_COMP_RGSTRO_NUM_INSC_LEN = 15;

	public static final String TP_COD_COMP_RGSTRO_FECHA_EXP = "07";
	public static final int TP_VAL_COMP_RGSTRO_FECHA_EXP_LEN = 10;

	/**
	 * Constantes generales con el formato que se debe aplicar a una fecha.
	 */
	public static final String GENERAL_DATE_FORMATTER = "dd/MM/yyyy";
	public static final String GENERAL_DATE_FORMATTER_INT = "yyyyMMdd";

	/**
	 * Constante con el valor máximo para una fecha de término (hasta).
	 */
	public static final String MAX_FECHA_FIN = "31/12/9999";

	/**
	 * Constante con el valor mínimo para una fecha de inicio (desde).
	 */
	public static final String MIN_FECHA_INICIO = "01/01/1950";

	/**
     * Constante con el valor máximo para una fecha de término (hasta).
     */
    public static final Integer MAX_FECHA_FIN_INTEGER = 99991231;

    /**
     * Constante con el valor máximo para una fecha de término (hasta).
     */
    public static final Integer MIN_FECHA_INICIO_INTEGER = 19500101;
    
    /**
	 * Constante con el valor mínimo para una fecha de inicio TCB (desde).
	 */
	public static final Integer MIN_FECHA_INICIO_INT_TCB = 11111111;

	/**
	 * Constante general con el código de moneda.
	 */
	public static final String COD_MONEDA = "MXN";

	/**
	 * Constante para filtrar la consulta general de apuntes.
	 */
	public static final String PRPDAD_CTA = "A";

	/**
	 * Constante para obtener relación de represnetado por en persona físicas y
	 * morales
	 */
	public static final List<String> REPRESENTADO_POR = Arrays.asList("117",
			"121");

	/**
	 * Constante para obtener relación de representa a en persona físicas y
	 * morales
	 */
	public static final List<String> REPRESENTA_A = Arrays.asList("116",
			"120");

	public static final List<String> APODERADO = Arrays.asList("407",
			"451");

	public static final String APODERADO_DE = "407";
	
	public static final String CODIGO_CUENTA_CONTABLE = "APM";

	public static final String REPRESENTANTE_MENOR = "121";
	
	public static final String REPRESENTANTE_MENOR_CONTRARIA = "120";
	
	/**
	 * Constante para obtener capacidad legal en persona físicas
	 */
	public static final String CAPACIDAD_LEGAL_PLENA = "3";

	/**
	 * Catalogo de situacion de bloqueo de cuenta.
	 */
	public static Map<String, String> getRelacionIdentificacionDocumento() {
		final Map<String, String> relacionMap = new HashMap<String, String>();

		// Acta constitutiva
		relacionMap.put("01", "2A");
		// IFE
		relacionMap.put("51", "10");
		// PASAPORTE
		relacionMap.put("52", "11");
		// EMPLEADO BANSEFI
		relacionMap.put("53", "12");
		// CARTILLA DEL SERVICIO NACIONAL MILITAR
		relacionMap.put("54", "13");
		// MENOR 18 AÑOS
		relacionMap.put("55", "20");
		// DESC. (INTER. FIS.)
		relacionMap.put("56", "56");
		// LICENCIA DE CONDUCIR
		relacionMap.put("58", "15");
		// TARJETA UNICA DE IDENTIDAD MILITAR
		relacionMap.put("60", "13");
		// REFERENCIA NOMINA
		relacionMap.put("61", "2Ñ");
		// CREDENCIAL EMPLEADO DEPENDENCIA OFICIAL
		relacionMap.put("63", "16");
		// FORMA MIGRATORIA
		relacionMap.put("66", "19");
		// CEDULA PROFESIONAL
		relacionMap.put("68", "14");
		// CARTA DE IDENTIDAD MUNICIPAL
		relacionMap.put("59", "17");
		// CERTIFICADO MATRÍCULA CONSULAR
		relacionMap.put("67", "F2");

		return relacionMap;
	}

	/**
	 * Constante para domicilios
	 */
	public static final String CALLE = "01";
	public static final int CALLE_LONG = 45;
	public static final String NUMERO_EXT = "02";
	public static final int NUMERO_EXT_LONG = 4;
	public static final String INTERIOR = "03";
	public static final int INTERIOR_LONG = 2;
	public static final String ENTRADA = "04";
	public static final int ENTRADA_LONG = 2;
	public static final String DEPARTAMENTO = "05";
	public static final int DEPARTAMENTO_LONG = 3;
	public static final String CASA = "06";
	public static final int CASA_LONG = 2;
	public static final String BLOQUE = "07";
	public static final int BLOQUE_LONG = 2;
	public static final String FASE = "08";
	public static final int FASE_LONG = 3;
	public static final String EDIFICIO = "09";
	public static final int EDIFICIO_LONG = 45;
	public static final String COLONIA = "10";
	public static final int COLONIA_LONG = 45;
	public static final String UNIDAD_HABITACIONAL = "11";
	public static final int UNIDAD_HABITACIONAL_LONG = 45;
	public static final String MANZANA = "12";
	public static final int MANZANA_LONG = 45;
	public static final String LOTE = "13";
	public static final int LOTE_LONG = 45;
	public static final String OTROS_DATOS = "99";
	public static final int OTROS_DATOS_LONG = 100;

	public static final String SEPARADOR_ABRE_LOC_MUN = "(";
	public static final String SEPARADOR_CIERRE_LOC_MUN = ")";

	public static final String GUION_BAJO = "_";

	/**
	 * Enumerado de tipo de condiciones.
	 */
	// TODO sustituir por catalog TP_COND_ACUERDOS
	public static enum CATALOGO_TIPO_CONDICION {
		INTERES("1"), COMISION("2"), LISTA("3"), RANGO("4"), VALOR_LISTA("5"), VALOR_RANGO(
				"6");

		String id;

		private CATALOGO_TIPO_CONDICION(final String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public static CATALOGO_TIPO_CONDICION getTipo(final String searchId) {
			for (final CATALOGO_TIPO_CONDICION tpComision : CATALOGO_TIPO_CONDICION
					.values()) {
				if (StringUtils.equals(tpComision.id, searchId)) {
					return tpComision;
				}
			}
			throw new NoControlableException(
					"Tipo de comision desconocido",
					ConstantesFuncionales.class.getName()
							+ ".CATALOGO_TIPO_CONDICION.getTipo(): Tipo de comision desconocido");
		}

	};

	/**
	 * Constantes Balance
	 * */
	public static final String COD_DOC_BALANCE = "26"; // CODIGO PARA CONSULTAR
														// BALANCE

	/**
	 * RATIOS PARA BALANCE<br/><br/>
	 * ratios a visualizar: "091", "092"
	 */
	public final static List<String> RATIOS = Arrays.asList("091", "092");

	/**
	 * ids indicadores para calcular ratios<br/><br/>
	 * ratios a visualizar: "010", "020", "0101031", "0101032"
	 */
	public final static List<String> CAL_RATIOS = Arrays.asList("010", "020", "0101031", "0101032" );

	public final static String COD_CAPITAL = "03";

	public final static String COD_RATIOS = "09";

	/**
	 * Constantes para códigos de relaciones entre cuentas permitidas en el alta
	 */
	public final static String REL_AC_AC_CUENTA_OPERATIVA = "10";

	public final static String REL_AC_AC_ABONO_INTERES = "16";

	public final static String REL_AC_AC_CARGO_INTERES = "17";

	public final static String REL_AC_AC_REVERSION = "23";

	public final static String REL_AC_AC_GARANTIA = "02";

	public final static String IND_SI = "S";

	public final static String IND_NO = "N";

	/**
	 * Constante para definir el maximo de resultados de las busquedas de
	 * autocomplete
	 */
	public static final int MAX_RESULTS_AUTOCOMPLETE = 5;

	/**
	 * Constante para el estado de anotaciones activas
	 */
	public static final String COD_ESTADO_ANOTACION_ACTIVA = "1";

	/**
	 * Constante para el estado de anotaciones activas
	 */
	public static final String COD_PRIORIDAD_ANOTACION_ALTA = "3";

	/**
	 * Constante que recupera del parametro configuracion del contexto el ancho
	 * en pixeles del navegador al iniciar sesion.
	 */
	public static final String BROWSER_WIDTH = "browser_width";

	/**
	 * Constante que indica el estado Comercializacion para una tarifa de cuenta
	 */
	public static final CharSequence CTE_TARIFA_ESTADO_COMERCIALIZACION = "7";

	/**
	 * Constante que indica el tipo de pkanificaciones pendientes para una
	 * cuenta
	 */
	public static final String COD_PLANIFICACIONES_PENDIENTES = "1";
	/**
	 * Constante que indica el tipo de pkanificaciones pendientes para una
	 * cuenta
	 */
	public static final String COD_PLANIFICACIONES_REALIZADAS = "2";

	/**
	 * Constante que indica el cod event cd 2
	 */
	public static final int CTE_EVENTCD_2 = 2;

	/**
	 * Constante que indica el cod idccps E
	 */
	public static final String CTE_IDCCPS_E = "E";

	/**
	 * Constante que indica el código de cuentas instrumentales.
	 */
	public static final String CTE_INSTRUMENTALES = "09";

	/**
	 * Constante que indica el código de gestión de incidencias de
	 * instrumentales.
	 */
	public static final String CTE_GESTION_INCIDENCIAS = "11";

	/**
	 * Constante que indica el código de emisión de cheques de instrumentales.
	 */
	public static final String CTE_EMISION_CHEQUES = "14";

	/**
	 * Constante que indica el código de depositos de instrumentales.
	 */
	public static final String CTE_DEPOSITOS = "03";

	/**
	 * Constante que indica el cod cuentas de intervención de instrumentales.
	 */
	public static final String CTE_INTERVENCION = "17";

	/**
	 * Constante para la entidad del usuario Adan
	 */
	public static final String ENTIDAD_ADAN = "9998";

	/**
	 * Constante para centro controlador
	 */
	public static final String CENTRO_CONTROLADOR = "0001";

	/**
	 * Constantes para el codigo de pais de Mexico
	 */
	public final static String COD_PAIS_MEXICO = "412";

	/**
	 * Constante de credencial IFE de persona fisisca
	 */
	public static final String CTE_CREDENCIAL_IFE = "10";

	/**
	 * Constante de acta de nacimiento de persona fisisca
	 */
	public static final String CTE_ACTA_NACIMIENTO = "VK";

	/**
	 * Constante de cedula de conocimiento de persona fisisca
	 */
	public static final String CTE_CEDULA_PERSONA_FISICA = "E1";

	/**
	 * Constante de cedula de conocimiento de persona moral
	 */
	public static final String CTE_CEDULA_PERSONA_MORAL = "E2";

	public static final String COD_AR_GEO = "07";

	/**
	 * Constante para saber lo estados en los que una cuenta puede operar
	 * relaciones con personas
	 */
	public static final List<String> ESTADOS_CUENTA_OPERACION = Arrays.asList(
			"1", "4", "5");

	public static final String ESTADO_CUENTA_ACTIVO = "4";
	/**
	 * Constante que se utiliza para borrar una dirección registral.
	 */
	public static final int ACTION_TYPE_BORRAR_DIRECCION_REGISTRAL = -5;

	/**
	 * Constante que se utiliza para modificar una dirección registral.
	 */
	public static final int ACTION_TYPE_MODIFICAR_DIRECCION_REGISTRAL = -3;

	/**
	 * Constante que se utiliza para dar de alta una dirección registral.
	 */
	public static final int ACTION_TYPE_ALTA_DIRECCION_REGISTRAL = -4;

	/**
	 * Constante COD_DIR para la dirección registral
	 */
	public static final String COD_DIR = "3";

	/**
	 * Constante COD_PERS_DIR para la dirección registral
	 */
	public static final String COD_PERS_DIR = "08";

	/**
	 * Catalogo de filtrado de situaciones de bloqueos de cuenta
	 */
	public static final CatalogoBean SIT_BLOQ_ACTIVO = new CatalogoBean("A",
			"ACTIVO");
	public static final CatalogoBean SIT_BLOQ_CANCELADO = new CatalogoBean("C",
			"CANCELADO");
	public static final CatalogoBean SIT_BLOQ_INACTIVO = new CatalogoBean("I",
			"INACTIVO");
	public static final CatalogoBean SIT_BLOQ_VENCIDO = new CatalogoBean("V",
			"VENCIDO");
	public static final CatalogoBean SIT_BLOQ_TODOS = new CatalogoBean("0",
			"TODOS");
	public static final List<CatalogoBean> CATALOGO_BLOQUEOS = Arrays.asList(
			SIT_BLOQ_ACTIVO, SIT_BLOQ_CANCELADO, SIT_BLOQ_INACTIVO,
			SIT_BLOQ_VENCIDO, SIT_BLOQ_TODOS);

	/**
	 * Constante para la entidad del usuario Adan
	 */
	public static final int INICIO_BIEN_INGRESO = 100;

	public static final int FIN_BIEN_INGRESO = 399;

	public static final int INICIO_BIEN_INMUEBLE = 400;

	public static final int FIN_BIEN_INMUEBLE = 460;

	public static final int INICIO_BIEN_VEHICULOS = 461;

	public static final int FIN_BIEN_VEHICULOS = 498;

	public static final int INICIO_BIEN_DEUDA = 499;

	public static final int INICIO_BIEN_DEUDA_DATOS = 710;

	public static final int FIN_BIEN_DEUDA_DATOS = 799;

	public static final String COD_ECV_PERS_AC_ACTIVA = "2";

	public static final String COD_ECV_PERS_AC_INACTIVA = "3";
	
	public static final String COD_ECV_PERS_AC_SOLICITADA = "1";

	public static final String COD_ECV_AC_AC_INACTIVA = "3";
	
	public static final String COD_ECV_AC_AC_SOLICITADA = "1";
	
	public static final String COD_ECV_AC_AC_ACTIVA = "2";

	public static final int COD_INICIO_IDENTFICACION_PERS_FISICAS = 51;

	public static final int COD_INICIO_NOMBRES_PERS_MORALES = 5;

	/**
	 * Constante para diferenciar el catálogo de centros del resto de los
	 * catálogos. Para más información revisar NotificacionBusquedaBean.
	 */
	public static final String CATALOGO_CENTROS = "CATALOGO CENTROS";

	/**
	 * Constantes posición<br/><br/>
	 *
	 * "9021009021", "9022009022", "9023009023", "9035009035", "9036009036", "9037009037", "9038009038", "9051009050", "9051009051", "9052009052"
	 *
	 * */
	public static final List<String> TOTALES_POSICION = Arrays.asList("9021009021", "9022009022", "9023009023", "9035009035", "9036009036",
			"9037009037", "9038009038", "9051009050", "9051009051", "9052009052" );

	/**
	 * Constante para consultar las crcts de un bien
	 */
	public static final List<String> CODIGOS_CRCT = Arrays.asList("001", "002",
			"003", "004", "005", "006", "007", "008", "009", "010", "011",
			"012", "013", "014", "015", "016", "017", "018", "019", "020",
			"021", "022", "023", "024", "026", "027", "028", "029", "030",
			"031", "032", "033", "034", "035", "036", "037", "038", "039",
			"040", "041", "042", "043", "044", "045", "046", "047", "048",
			"049", "998");

	public static final int CTE_ONE = 1;
	public static final String CTE_STR_ONE = "1";

	public static final String CTE_CT = "CT";
	public static final String CTE_A = "A";

	public static Map<String, String> estadosGestor() {
		final Map<String, String> estadosGestor = new HashMap<String, String>();

		estadosGestor.put("1", "Potencial");
		estadosGestor.put("2", "Activo");
		estadosGestor.put("3", "Inactivo");

		return estadosGestor;
	}

	/**Constantes para cedulas de conocimiento
	 *
	 */
	public static final String CTE_CEDULA_PF_BR = "P0700100.jrxml";

    public static final String CTE_CEDULA_PF_AR = "P0700200.jrxml";

    public static final String CTE_CEDULA_PM_BR = "P0700300.jrxml";

	public static final String CTE_CEDULA_PM_AR = "P0700400.jrxml";

	/**
	 * Linea y grupo para cuentas MEDIO DE PAGO - DEPRECATED
	 * Se cambia por la utilizacion de una clase de tipo ENUM CuentaEnum
	 */
//	public static final String MEDIOS_PAGO_LINEA = "05";
//	public static final String MEDIOS_PAGO_GRUPO = "50";


	public static Boolean isMediosPago(final String linea, final String grupo){
		if(linea.equals(CuentaEnum.MEDIO_PAGO.getLinea()) && grupo.equals(CuentaEnum.MEDIO_PAGO.getGrupo())){
			return true;
		}
		return false;
	}
	
	public static Boolean cuentaSinBeneficiarios(final String linea, final String grupo){
		if((linea.equals(CuentaEnum.MEDIO_PAGO.getLinea()) || linea.equals(CuentaEnum.CREDITO_NO_BANSEFI.getLinea()))
				&& grupo.equals(CuentaEnum.MEDIO_PAGO.getGrupo())){
			return true;
		}
		return false;
	}

	public static Boolean isVista(final String linea, final String grupo){
		if(linea.equals(CuentaEnum.VISTA.getLinea()) && grupo.equals(CuentaEnum.VISTA.getGrupo())){
			return true;
		}
		return false;
	}

	public static final String CREDITO_GRUPO = "41";

	public static final String CREDITO_LINEA = "01";

	public static final String PASIVO_LINEA = "03";

	public static final String VISTA_GRUPO = "11";

	public static final String PLAZO_GRUPO = "51";

	public static final String CREDITO_PRODUCTO_SIMPLE_LIMITE_CONCEDIDO = "012";

	public static final String CREDITO_PARAMETRO_LIMITE_CONCEDIDO = "B09";

	public static Boolean isCredito(final String linea, final String grupo){
		if(linea.equals(CuentaEnum.CREDITO_REVOLVENTE.getLinea()) && grupo.equals(CuentaEnum.CREDITO_REVOLVENTE.getGrupo())){
			return true;
		}
		return false;
	}

	/**
	 * Función para identificar si una tarifa es de PLAZO
	 * @author manuel.nieto
	 * @param linea
	 * @param grupo
	 * @return
	 */
	public static Boolean isPlazo(final String linea, final String grupo){
		if(linea.equals(CuentaEnum.PLAZO.getLinea()) && grupo.equals(CuentaEnum.PLAZO.getGrupo())){
			return true;
		}
		return false;
	}

	public static final String ECV_STOCK_TJ_RECIBIDO = "R";

	public static final String ID_TRFA_PDV_CUENTA_OPERATIVA_MEDIOS_PAGO = "001";

	/**
	 * Función para verificar si una persona física es menor de edad
	 * @param fechaNacimiento de la persona a consultar
	 * @return <code>true</code> en caso de que la persona sea menor de edad
	 */
	public static Boolean isMenorEdad(final Date fechaNacimiento){
		final Calendar fechaActual = Calendar.getInstance();
		fechaActual.add(Calendar.YEAR, -18);
		final Calendar nacimiento = Calendar.getInstance();
		nacimiento.setTime(fechaNacimiento);
		return fechaActual.before(nacimiento);
	}

	public static Map<String, String> estadosPan() {
		final Map<String, String> estadosPan = new HashMap<String, String>();

		estadosPan.put("T", "Transito");
		estadosPan.put("R", "Recibida");
		estadosPan.put("N", "No Recibida");
		estadosPan.put("D", "Defectuosa");
		estadosPan.put("E", "Entregada");
		estadosPan.put("Z", "Destruida");
		estadosPan.put("S", "Solicitada");

		return estadosPan;
	}

	/**
	 * Constantes para Datos Adicionales
	 */
	public static final String ESTR_CUENTA = "1";
	public static final String ESTR_TEXTO = "2";
	public static final String ESTR_CENTRO = "3";
	public static final String ESTR_INDICADOR = "4";
	public static final String ESTR_IMPORTE = "5";
	public static final String ESTR_TELEFONO = "6";

	/**
	 * Códigos de comprobantes de domicilios. </br>
	 * CODIGOS_COMPROBANTE_DOMICILIO= {"V#","VA","VC","D4","D5","V1","A5"}
	 */
	public static List<String> getCodigosComprobanteDomicilio() {
		return Arrays.asList("VA", "VC", "D4", "VD" , "V1", "A5", "B4", "V2");
	}

	/**
	 * Códigos de cédulas de conocimiento. </br> CODIGOS_CEDULA_CONOCIMIENTO =
	 * {"E1","E2"}
	 */
	public static List<String> getCodigosCedulaConocimiento() {
		return Arrays.asList("E1", "E2");
	}

	/**
	 * Códigos de comprobantes de identificación. </br>
	 * CODIGOS_COMPROBANTE_IDENTIFICACION =
	 * {"VK","VL","VT","VZ","1#","1B","1I","1J",
	 * "1K","1L","10","11","13","14","15","16","18","2#","2A","20","23","D6"}
	 */
	public static List<String> getCodigosComprobanteIdentificacion() {
		return Arrays.asList("VK", "VL", "VT", "VZ", "1#", "1B", "1I", "1J",
				"1K", "1L", "10", "11", "13", "14", "15", "16", "18", "2#",
				"2A", "20", "23", "D6");
	}

	public final static String CONDICION_MODIFICABLE = "02";

	public final static String CLIENTE_FUSIONADO = "F";

	public final static String IPF_ESTADO_ACTIVO = "A";
    public final static String IPF_ESTADO_SOLICITADO = "S";

    public final static String CONCEPTO_APUNTE_MXN = "MN";
    
    public final static String SIT_ECON_QUIEBRA = "2";
    public final static String SIT_ECON_SUSP_PAGO = "1";
    public final static String SIT_ECON_QUIEBRA_FRAUD = "5";
    
    public final static String REPRESENTANTE_DE = "120";
    
	public static List<Integer> getPosicionesCatalogoAdminGeo() {
		return Arrays.asList(9,5,2,9,2,45,1,3,2,45,9,2,35,8,3,1,9,25);
	}
	
	public final static String CLAVE_LIQUIDACION_INF_ADIC = "A006";
	
	public final static String LISTA_INFO_ADIC_LIQUIDACION = "TIPO DE APUNTES";
	
	public static Map<String, String> getListaInformacionAdicLiquidacion() {
		final Map<String, String> informacionMap = new HashMap<String, String>();

		informacionMap.put("A", "AMBOS_INCLUIDO");
		informacionMap.put("B", "AMBOS_EXCLUIDOS");
		informacionMap.put("C", "DESDE_EXCLUIDO");
		informacionMap.put("D", "HASTA_EXCLUIDO");
		informacionMap.put("E", "SIN_APUNTES");	

		return informacionMap;
	}
}