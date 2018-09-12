var indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB;
var urlApplication = window.location.protocol + "//" + window.location.host;
var pathName = window.location.pathname;
var context;
var dataBase = null;
var active = null;

var tablasGetIdentificacion = [
    {id: 1, nombreTabla: "catalogo_identificacion", tipoCatalogo: "ID_EXT"},
    {id: 2, nombreTabla: "catalogo_edo_civil", tipoCatalogo: "EDO_CIVIL"},
    {id: 3, nombreTabla: "catalogo_act_giro", tipoCatalogo: "ACT_GIRO"},
    {id: 4, nombreTabla: "catalogo_ocupacion", tipoCatalogo: "OCUP"},
    {id: 5, nombreTabla: "catalogo_tipo_documento", tipoCatalogo: "TIPODOC"},
    {id: 6, nombreTabla: "catalogo_lugar_nacimiento", tipoCatalogo: "CP_NAC"},
    {id: 7, nombreTabla: "catalogo_cve_oper_deposito", tipoCatalogo: "CVEOPERD"},
    {id: 8, nombreTabla: "catalogo_cve_oper_retiro", tipoCatalogo: "CVEOPERR"},
    {id: 9, nombreTabla: "catalogo_gestion_cuenta_finalidad", tipoCatalogo: "FINA"},
    {id: 10, nombreTabla: "catalogo_medios_comunicacion", tipoCatalogo: "TEL_FAX"},
    {id: 11, nombreTabla: "catalogo_otras_identificaciones_pm", tipoCatalogo: "ID_EXT"},
    {id: 12, nombreTabla: "catalogo_tipo_de_identificacion_pm", tipoCatalogo: "ID_EXT_PM"},
    {id: 13, nombreTabla: "catalogo_regimen_de_ocupacion_pm", tipoCatalogo: "REGOCUPA"}
];
var tablasPersonasMorales = [
    {id: 1, nombreTabla: "catalogo_razon_alta_pm",codApp: "00003", subCodApp: "PGE"},
    {id: 2, nombreTabla: "catalogo_estructura_legal_pm", codApp: "00035", subCodApp: "PGE"},
    {id: 3, nombreTabla: "catalogo_cnae_pm", codApp: "00015", subCodApp: "POT"},
    {id: 4, nombreTabla: "catalogo_ambito_pm", codApp: "00036", subCodApp: "PGE"},
    {id: 5, nombreTabla: "catalogo_situacion_economica_pm", codApp: "00019", subCodApp: "POT"},
    {id: 6, nombreTabla: "catalogo_comprobante_de_domicilio_pm", codApp: "00005", subCodApp: "PDO"},
    {id: 9, nombreTabla: "catalogo_tipo_de_calle_pm", codApp: "00008", subCodApp: "POT"},
    {id: 10, nombreTabla: "catalogo_tipo_registro_pm", codApp: "00007", subCodApp: "POT"},
    {id: 11, nombreTabla: "catalogo_idioma_pm", codApp: "00001", subCodApp: "PGE"},
    {id: 12, nombreTabla: "catalogo_relacion_cliente_pm", codApp: "00010", subCodApp: "POT"},
    {id: 13, nombreTabla: "catalogo_tipo_documento_pm", codApp: "00005", subCodApp: "PDO"},
    {id: 14, nombreTabla: "catalogo_mercados_organizados_pm", codApp: "00030", subCodApp: "POT"},
    {id: 15, nombreTabla: "catalogo_sector_econ_act_empr_pm", codApp: "00038", subCodApp: "POT"},
    {id: 16, nombreTabla: "catalogo_origen_ingresos_pm", codApp: "00025", subCodApp: "POT"},
    {id: 17, nombreTabla: "catalogo_actividad_profesional_pm", codApp: "00022", subCodApp: "POT"},
    {id: 18, nombreTabla: "catalogo_bienes_ingresos_pm", codApp: "00005", subCodApp: "PBI"},
    {id: 19, nombreTabla: "catalogo_otras_identificaciones_pm2", codApp: "00017", subCodApp: "PGE"},
    {id: 20, nombreTabla: "catalogo_otros_nombres_pm", codApp: "00016", subCodApp: "POT"},
    {id: 21, nombreTabla: "catalogo_modalidad_pm", codApp: "00013", subCodApp: "GCA"}
];

var tablasPasivoPlazo = [
    {id: 1, nombreTabla: "catalogo_bienes", cod: "00005PBI"},
    {id: 2, nombreTabla: "catalogo_mutuas_ajenas", cod: "00002DVI"},
    {id: 3, nombreTabla: "catalogo_bloqueos", cod: "00005DVI"},
    {id: 4, nombreTabla: "catalogo_emision_cheque", cod: "00010DVI"},
    {id: 5, nombreTabla: "catalogo_retenciones", cod: "S0050041"}
];

var tablasGestionCuenta = [
    {id: 1, nombreTabla: "catalogo_gestion_cuenta_relaciones"}
];

var tablasPersMor = [
    {nombreTabla: "catalogo_grupo_anotaciones", tblRef: "00003", aplcnSub: "ATC"},
    {nombreTabla: "catalogo_tipo_anotacion", tblRef: "00001", aplcnSub: "ATC"}
];
var tablaRelacionPersonaFisica = "catalogo_relacion_persona";
var tablaRazonesAlta = "catalogo_razones_alta";
var tablaMoneda = "catalogo_Moneda";

/**
 * Funcion para cargar tablas
 */
function crearTablas() {

    // Creacion y llenado de tablas getIdentificacion
    for (var i = 0; i < tablasGetIdentificacion.length; i++) {
        crearTablaCatIdentificadorDescripcionNombre(tablasGetIdentificacion[i].nombreTabla);
    }

    // Creacion de tablas personas morales.
    for (var i = 0; i < tablasPersonasMorales.length; i++) {
        crearTablaCatIdentificadorDescripcion(tablasPersonasMorales[i].nombreTabla);
    }

    // Creacion de tablas de pasivo plazo.
    for (var i = 0; i < tablasPasivoPlazo.length; i++) {
        crearTablaCatIdentificadorDescripcionNombre(tablasPasivoPlazo[i].nombreTabla);
    }

    // Creacion de tablas de gestion cuenta.
    for (var i = 0; i < tablasGestionCuenta.length; i++) {
        crearTablaCatIdentificadorDescripcion(tablasGestionCuenta[i].nombreTabla);
    }

    // Creacion de tablas de per mor.
    for (var i = 0; i < tablasPersMor.length; i++) {
        crearTablaCatIdentificadorDescripcion(tablasPersMor[i].nombreTabla);
    }

    crearTablaCatIdentificadorDescripcion(tablaRelacionPersonaFisica);

    crearTablaCatIdentificadorDescripcionNombre(tablaRazonesAlta);

    crearTablaCatIdentificadorDescripcionNombre(tablaMoneda);
}

/**
 * Funcion para poblar tablas.
 */
function poblarTablas() {
    context = pathName.split("/")[1];

    //tablasGetIdentificacion
    var urlGetIdentificacion = urlApplication + "/" + context + "/rest/catalogos/identificacion";
    for (var iTablas = 0; iTablas < tablasGetIdentificacion.length; iTablas++) {
        simpleAjaxGet(
            urlGetIdentificacion + "?tipoCatalogo=" + tablasGetIdentificacion[iTablas].tipoCatalogo,
            function(data, tabla) {
                var listaRegistros = data;
                for (var iLista = 0; iLista < listaRegistros.identificacion.length; iLista++) {
                    var registro = listaRegistros.identificacion[iLista];
                    var insert = {
                        identificador: registro.identificador,
                        nombre: registro.nombre,
                        descripcion: registro.descripcion
                    };
                    addCatalogo(null, tabla, insert);
                }
            },
            tablasGetIdentificacion[iTablas].nombreTabla);
    }
    // Tablas pasivo.
    var urlCatalogosPasivoPlazo = urlApplication + "/" + context + "/rest/catalogos/catalogosPasivoPlazo";
    for (var iTablas = 0; iTablas < tablasPasivoPlazo.length; iTablas++) {
        simpleAjaxGet(
            urlCatalogosPasivoPlazo + "?cod=" + tablasPasivoPlazo[iTablas].cod,
            function(data, tabla) {
                var listaRegistros = data;
                for (var iLista = 0; iLista < listaRegistros.responseBansefi.length; iLista++) {
                    var registro = listaRegistros.responseBansefi[iLista];
                    var insert = {
                        identificador: registro.clave_DE_FILA_NOMBRE==null?"":registro.clave_DE_FILA_NOMBRE.trim(),
                        nombre: registro.contenido_DE_DATOS_1==null?"":registro.contenido_DE_DATOS_1.trim(),
                        descripcion: registro.descripcion_LARGA_FL==null?"":registro.descripcion_LARGA_FL.trim()
                    };
                    addCatalogo(null, tabla, insert);
                }
            },
            tablasPasivoPlazo[iTablas].nombreTabla);
    }
    // Tablas Per Mor.
    var urlCatalogosPerMor = urlApplication + "/" + context + "/rest/catalogos/catalogosPerMor";
    for (var iTablas = 0; iTablas < tablasPersMor.length; iTablas++) {
        simpleAjaxGet(
            urlCatalogosPerMor + "?tblRef=" + tablasPersMor[iTablas].tblRef + "&aplcnSub=" + tablasPersMor[iTablas].aplcnSub,
            function (listaRegistros, tabla) {
                for (var iLista = 0; iLista < listaRegistros.responseBansefi.responseBansefi.length; iLista++) {
                    var registro = listaRegistros.responseBansefi.responseBansefi[iLista];
                    var insert = {
                        identificador: registro.claveFila==null?"":registro.claveFila.trim(),
                        descripcion: registro.descrLarga==null?"":registro.descrLarga.trim()
                    };
                    addCatalogo(null, tabla, insert);
                }
            },
            tablasPersMor[iTablas].nombreTabla
        );
    }
    // Tablas Persona Moral.
    var urlCatalogosPersonaMoral = urlApplication + "/" + context + "/rest/catalogos/catalogosPersonaMoral";
    for (var iTablas = 0; iTablas < tablasPersonasMorales.length; iTablas++) {
        simpleAjaxGet(
            urlCatalogosPersonaMoral + "?codApp=" + tablasPersonasMorales[iTablas].codApp +
            "&codSubApp=" + tablasPersonasMorales[iTablas].subCodApp + "&indPag&cveFila",
            function (listaRegistros, tabla) {
                for (var iLista = 0; iLista < listaRegistros.responseBansefi.length; iLista++) {
                    var registro = listaRegistros.responseBansefi[iLista];
                    var insert = {
                        identificador: registro.clave==null?"":registro.clave.trim(),
                        descripcion: registro.descripcion==null?"":registro.descripcion.trim()
                    };
                    addCatalogo(null, tabla, insert);
                }
            },
            tablasPersonasMorales[iTablas].nombreTabla
        );
    }
    var urlGestionCuentaRelaciones = urlApplication + "/" + context + "/rest/catalogos/listaRelaciones";
    simpleAjaxGet(
        urlGestionCuentaRelaciones,
        function(data, tabla) {
            var listaRegistros = data;
            for (var iLista = 0; iLista < listaRegistros.RELACIONES.length; iLista++) {
                var registro = listaRegistros.RELACIONES[iLista];
                var insert = {
                    identificador: registro.CLAVE_FILA,
                    descripcion: registro.DESCR_LARGA
                };
                addCatalogo(null, tabla, insert);
            }
        },
        tablasGestionCuenta[0].nombreTabla);
    var urlConsultaRelacionesFisica = urlApplication + "/" + context + "/rest/catalogos/relacionPersona";
    simpleAjaxGet(
        urlConsultaRelacionesFisica,
        function (data, tabla) {
            var listaRegistros = data;
            for (var iLista = 0; iLista < listaRegistros.DATOS_LISTA.RELACION.length; iLista++) {
                var registro = listaRegistros.DATOS_LISTA.RELACION[iLista];
                var insert = {
                    identificador: registro.ID==null?"":registro.ID.trim(),
                    descripcion: registro.DESC==null?"":registro.DESC.trim()
                };
                addCatalogo(null, tablaRelacionPersonaFisica, insert);
            }
        },
        tablaRelacionPersonaFisica
    );
    var urlConsultaRazonesAlta = urlApplication + "/" + context + "/rest/catalogos/razonesAlta";
    simpleAjaxGet(
        urlConsultaRazonesAlta,
        function (data, tabla) {
            var listaRegistros = data;
            for (var iLista = 0; iLista < listaRegistros.datos_LISTA.razones.length; iLista++) {
                var registro = listaRegistros.datos_LISTA.razones[iLista];
                var insert = {
                    identificador: registro.id==null?"":registro.id.trim(),
                    descripcion: registro.desc==null?"":registro.desc.trim(),
                    nombre: registro.desc==null?"":registro.desc.trim()
                };
                addCatalogo(null, tablaRazonesAlta, insert);
            }
        },
        tablaRazonesAlta
    );

    var urlConsultaMoneda = urlApplication + "/" + context + "/rest/catalogos/catalogosMoneda";
    simpleAjaxGet(
        urlConsultaMoneda,
        function (data, tabla) {
            var listaRegistros = data;
            for (var iLista = 0; iLista < listaRegistros.xm_MONEDA_E.length; iLista++) {
                var registro = listaRegistros.xm_MONEDA_E[iLista];
                var insert = {
                    identificador: registro.cod_NUMRCO_MONEDA==null?"":registro.cod_NUMRCO_MONEDA.trim(),
                    descripcion: registro.descr_MONEDA==null?"":registro.descr_MONEDA.trim(),
                    nombre: registro.descr_MONEDA==null?"":registro.descr_MONEDA.trim()
                };
                addCatalogo(null, tablaMoneda, insert);
            }
        },
        tablaMoneda
    );

}

/**
 * Funcion para agregar un registro a un catalogo.
 * @param catalogo
 * @param nombreTabla
 * @param insert
 */
function addCatalogo(catalogo, nombreTabla, insert) {
    try {
        var active = dataBase.result;
        var data = active.transaction([nombreTabla], "readwrite");
        var object = data.objectStore(nombreTabla);

        var request = object.put(insert);

        request.onerror = function (e) {
            console.log(request.error.name + '\n\n' + request.error.message);
            console.log("Registro: " + insert);
        };
    } catch (err) {
        console.log("Ocurrió un error: add: " +err.message);
    }
}

/**
 * Funcion para crear tabla de catalogos con identificador y descripcion
 */
function crearTablaCatIdentificadorDescripcion(nombreTabla) {
    var tabla = active.createObjectStore(nombreTabla, {keyPath: 'identificador', autoIncrement: false});
    tabla.createIndex('by_identificador', 'identificador', {unique: true});
    tabla.createIndex('by_descripcion', 'descripcion', {unique: false});
}

/**
 * Funcion para crear tabla de catalogos con identificador, descripcion y nombre.
 */
function crearTablaCatIdentificadorDescripcionNombre(nombreTabla) {
    var tabla = active.createObjectStore(nombreTabla, {keyPath: 'identificador', autoIncrement: false});
    tabla.createIndex('by_identificador', 'identificador', {unique: true});
    tabla.createIndex('by_nombre', 'nombre', {unique: false});
    tabla.createIndex('by_descripcion', 'descripcion', {unique: false});
}

/**
 * Funcion para ejecutar una peticion AJAX de tipo GET
 */
function simpleAjaxGet(url, handleData, tabla) {
    $.ajax({
        type : "GET",
        url : url,
        cache: false,
        success : function(data) {
            handleData(data, tabla);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

/**
 * Funcion para crear la base de datos e inicializarla.
 */
function startDB() {

    try {
        dataBase = indexedDB.open('bansefi', 1);
        dataBase.onupgradeneeded = function (e) {
            active = dataBase.result;

            // Llamado a crear tablas
            crearTablas();
            console.log("Creo tablas");
        };
        dataBase.onsuccess = function (e) {

            // Llamado a poblar tablas.
            poblarTablas();
            console.log("Poblo tablas");
        };
        dataBase.onerror = function (e) {
            console.log('Error al cargar la Base de datos.');
        };
    } catch (err) {
        console.log("Ocurri&#243; un error: startDB: " + err.message);
    }
}

/**
 * Funcion para eliminar la base de datos.
 */
function deleteDB() {

    try {
        var DBDeleteRequest = indexedDB.deleteDatabase("bansefi");
        DBDeleteRequest.onerror = function (event) {};

        DBDeleteRequest.onsuccess = function (event) {};
    } catch (err) {
        console.log("Ocurri&#243; un error: deleteDB: " +err.message);
    }
}

$(document).ready(function () {
    if (typeof(Storage) !== "undefined") {
        var dataBase = sessionStorage.getItem('dataBase');
        console.log(dataBase);
        if (dataBase === undefined || dataBase === null) {
            sessionStorage.setItem('dataBase', 'true');
            startDB();
        }else {
        }
    } else {
        alert("Sorry, your browser does not support Web Storage...");
    }
});

$( document ).ajaxStart(function() {
    PF('statusDialog').show();
});

$( document ).ajaxStop(function() {
    PF('statusDialog').hide();
});