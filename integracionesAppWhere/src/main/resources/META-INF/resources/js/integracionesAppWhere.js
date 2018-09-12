/**
 * Variables
 */
var regresarMenuFrecuenteButton;
var urlApplication = window.location.protocol + "//" + window.location.host;
var iframe;

/**
 * Funcion que se inicia al completar la carga de la pagina
 */
$( document ).ready(function() {
    $("#formRenderFuncionalidad").attr("action", urlApplication + $("#formRenderFuncionalidad").attr("action"));
    $("#formRenderFuncionalidad").submit();
    regresarMenuFrecuenteButton = $("#regresarMenuFrecuenteButton");
    setTimeout(function () {
        mostrarDialogCargando();
    }, 1000);
});

/**
 *  Funcion para redimensionar iframe
 */
function redimensionarIframe(iframeElement) {
    iframe = iframeElement;
    try {
        iframeElement.style.height = iframeElement.contentWindow.document.body.scrollHeight + 'px';
    } catch (error) {}
    setTimeout(function(){redimensionarIframe(iframe);}, 1000);
}

/**
 * Handler para realizar acciones despues de que cargo el contenido del Iframe.
 */
function onLoadHandlerIframe(element) {
    redimensionarIframe(element);
    setTimeout(function(){ocultarDialogCargando();}, 1000);
}

/**
 * Funcion para regresar al menu frecuente.
 */
function regresarMenuFrecuente() {
    regresarMenuFrecuenteButton.click();
}

/**
 * Funcion temporal
 */
function setFrame() {
    iframe.style.height = iframe.contentWindow.document.body.scrollHeight + 'px';
}

/**
 * Funcion para mostrar barra de progreso banksystem
 */
function mostrarDialogCargando() {
    PF('statusDialog').show();
}

/**
 * Funcion para ocultar barra de progreso banksystem
 */
function ocultarDialogCargando() {
    PF('statusDialog').hide();
}

/**
 * Funcion para mostrar dialogo de perder datos al cancelar operacion
 */
function regresarPerderCambios() {
    PF('dlgCancelar').show();
}