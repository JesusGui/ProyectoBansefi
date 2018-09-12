var username;
var modulosContextPath = window.location.protocol + "//" + window.location.host;

$( document ).ready(function() {
    $("#frmOpciones\\:mnuSolicitudes").append("<span id='numSolicitudesAlert' class='alertaContador'>0</span>");
    username = $("#frmOpciones\\:lblId").html();
    pintarNumSolicitudesPendientes();
    $("#numSolicitudesAlert").hide();
});

/*
 * Funcion para pintar numero de solicitudes pendientes.
 */
function pintarNumSolicitudesPendientes() {
    getNumSolicitudesPendientes(function(numSolicitudes) {
        if (numSolicitudes === "0") {
            $("#numSolicitudesAlert").hide();
        } else {
            $("#numSolicitudesAlert").html(numSolicitudes);
            $("#numSolicitudesAlert").show();
        }
    });
}

/*
 * Funcion para invocar funcionalidad de limites y obtener el numero de solicitudes pendientes.
 */
function getNumSolicitudesPendientes(callbackFunction) {
    var url = modulosContextPath + "/BsfPortalSucursalesLimites/limites/getNumSolicitudes";
    var parametros = "?usuario=" + username;
    $.ajax({
        type : "GET",
        url : url + parametros,
        timeout : 100000,
        success : function(data) {
            callbackFunction(data.total);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        done : function(e) {
            console.log("DONE");
        }
    });
}

