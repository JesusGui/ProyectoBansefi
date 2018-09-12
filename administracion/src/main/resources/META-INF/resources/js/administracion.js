/**
 * Funciones para la logica de navegacion y validacion en las pantallas de alta
 * de Empleados
 * 
 */
function acordion() {
	PF('accordionAlta1').select(0);
	PF('accordionAlta1').select(1);
	PF('accordionAlta1').select(2);	
}

function acordionModif() {	
	PF('accordionAlta1').select(0);
	PF('accordionAlta1').select(1);
	PF('accordionAlta1').select(2);
	PF('accordionAlta1').select(3);
	
}

function acordion3() {
	PF('accordionAlta3').select(0);
	PF('accordionAlta3').select(1);
}

function handleChange(input, input2) {
	if (input.val() < 0 || input.val() == '') {
		input.val(0);
	}

	if (input.val() > 100) {
		input.val(100);
	}
	while (input.val().substr(0, 1) == '0' && input.val().length > 1) {
		input.val(input.val().substr(1, 9999));
	}
	input2.val(100 - input.val());
}

function limitar(input) {
	if (input.val().length >= 3) {
		input.val(input.val().substr(0, 2));
	}
}

$(document).on('keypress', '.alfanumericos_ene', function (evt) {

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

    var caracteres = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ,.1234567890 ";

    if (caracteres.indexOf(res) == -1)
    { return false; }
    else
    { return true; }

    }


});

$(document).on('keypress', '.letrasnumeros', function (evt) {

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

   
    var res = caracter.toUpperCase();

    var caracteres = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890";

    if (caracteres.indexOf(res) == -1)
    { return false; }
    else
    { return true; }

    


});


