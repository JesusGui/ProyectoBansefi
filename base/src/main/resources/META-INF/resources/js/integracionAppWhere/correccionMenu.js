/**
 * Definicion de variables.
 */
var contenedorMenu;
var centradorObjeto;

/**
 * Acciones a ejecutar cuando se encuentre completamente cargado el document.
 */
$(document).ready(function () {
    contenedorMenu = $("#contenedorMenu");
    centradorObjeto = $("#centradorObjeto");
    ampliarContenedorMenu();
    $(".titulo-menu").click(function () {
        setTimeout(function () {
            ampliarContenedorMenu();
        }, 200);
    });
});

/**
 * Funcion para ampliar el tamanio del contenedor de menu más 300px.
 */
function ampliarContenedorMenu() {
    centradorObjeto.height(contenedorMenu.height() + 300);
}