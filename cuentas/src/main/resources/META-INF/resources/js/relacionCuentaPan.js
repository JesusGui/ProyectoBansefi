$(document).on('keypress', '.letras', function (evt) {

    var caracter;
    
    var key;

    if (window.event) // IE
    {
    	key = evt.keyCode;
        if (evt.char != null) {
            caracter = evt.char;
        }
        else {//chrome
            caracter = String.fromCharCode(evt.charCode);
        }
    }
    else if (evt.which) // Netscape/Firefox/Opera
    {
    	 key = evt.which;
        caracter = evt.key;
        if (caracter == "Backspace")
        { return true; }
    }

    if (key == 241 || key == 209)
    { return true }
    else
    {
    var res = caracter.toUpperCase();

    var caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";

    if (caracteres.indexOf(res) == -1)
    { return false; }
    else
    { return true; }

    }


});
$(document).on('keypress', '.alfanumerico', function (evt) {

    var caracter;
    
    var key;

    if (window.event) // IE
    {
    	key = evt.keyCode;
        if (evt.char != null) {
            caracter = evt.char;
        }
        else {//chrome
            caracter = String.fromCharCode(evt.charCode);
        }
    }
    else if (evt.which) // Netscape/Firefox/Opera
    {
    	 key = evt.which;
        caracter = evt.key;
        if (caracter == "Backspace")
        { return true; }
    }

    if (key == 241 || key == 209)
    { return true }
    else
    {
    var res = caracter.toUpperCase();

    var caracteres = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ ";

    if (caracteres.indexOf(res) == -1)
    { return false; }
    else
    { return true; }

    }


});

$(document).on('keypress', '.numeros', function (evt) {

    var caracter;
    
    var key;

    if (window.event) // IE
    {
    	key = evt.keyCode;
        if (evt.char != null) {
            caracter = evt.char;
        }
        else {//chrome
            caracter = String.fromCharCode(evt.charCode);
        }
    }
    else if (evt.which) // Netscape/Firefox/Opera
    {
    	 key = evt.which;
        caracter = evt.key;
        if (caracter == "Backspace")
        { return true; }
    }

    if (key == 241 || key == 209)
    { return true }
    else
    {
    var res = caracter.toUpperCase();

    var caracteres = "0123456789";

    if (caracteres.indexOf(res) == -1)
    { return false; }
    else
    { return true; }

    }


});