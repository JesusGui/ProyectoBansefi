/**
 * Funciones para la logica de navegacion y validacion en las pantallas de alta
 * de Clientes
 * 
 */

function acordionCentro() {
	PF('accordionCentro').unselect(1);
}

function acordion() {
	PF('accordionAlta1').select(0);
	PF('accordionAlta1').select(1);
	PF('accordionAlta1').select(2);
}

function acordion3() {
	PF('accordionAlta3').select(0);
	PF('accordionAlta3').select(1);
}

function acordionRiesgo() {
	PF('accordionAltaRiesgo1').select(0);
	PF('accordionAltaRiesgo1').select(1);
	PF('accordionAltaRiesgo1').select(2);
}

function acordionMoral() {
	PF('accordionMoral1').select(0);
	PF('accordionMoral1').select(1);
}

function acordionMoral3() {
	PF('accordionMoral3').select(0);
	PF('accordionMoral3').select(1);
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

function handleSlider(input, input2) {
	input2.val(100 - input.val());
}

function limitar(input) {
	if (input.val().length >= 3) {
		input.val(input.val().substr(0, 2));
	}
}