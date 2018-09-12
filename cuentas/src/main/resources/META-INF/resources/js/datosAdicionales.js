$().ready(function () {
    $("#formDatosAdicionales label").each(function () {
        $(this).text($(this).text().replace("NUMERO", "NÚMERO"));
        $(this).text($(this).text().replace("TELEFONO", "TELÉFONO"));
        $(this).text($(this).text().replace("EMISION", "EMISIÓN"));
        $(this).text($(this).text().replace("FORMALIZACION", "FORMALIZACIÓN"));
    });
});