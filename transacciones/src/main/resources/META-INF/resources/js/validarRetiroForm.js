(function($){ 
	
	// Función que comprueba todos los imput del formulario
	// en caso de que alguno esté vacío no muestra el modal
	var mostrarModal = function() {
		var elements = $(".ui-messages-fatal-summary");
		if(comprobarCampos() && elem.length === 0) {
			PF('dlgAvisoNoRelacionado').show();
		}
			
		else {
			$.each(elements, function(elem) {
				console.log(elem);	
			});
		}
	};
	
	var comprobarCampos = function () {
		var campoVacio = true;
		$("#formRetiro input").each(function() {
			if($.trim(this.value) == "") {
				campoVacio =  false;
			}
		})
		
		return campoVacio;
	};
	
	
	$( document ).ready(function() {
		$("button[id$=aceptar]").on("click", mostrarModal);
	});
	
	
}(jQuery))