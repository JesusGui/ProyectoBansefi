var ajaxStatusTimeout = undefined;
var specialChars = [];
var charReplacer = '#';

(function($) {

	$(document).ready(function ($) {
	    var isLateralNavAnimating = false;
	
	    //abrir y cerrar navegaci�n lateral
	    $('.cd-nav-trigger').on('click', function (event) {
	        event.preventDefault();
	        //stop if nav animation is running 
	        if (!isLateralNavAnimating) {
	            if ($(this).parents('.csstransitions').length > 0) isLateralNavAnimating = true;
	
	            $('body').toggleClass('navigation-is-open');
	            $('.cd-navigation-wrapper').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function () {
	                //animation is over
	                isLateralNavAnimating = false;
	            });
	        }
	    });
	    
	    if ( $( "form" ).length > 0 ) {
	        $("form").each(function() {
	        	 $("input:text:visible:first").focus();
	        });
	    }
	    
	    //quitar autocomplete en todos los formularios
	    if ( $( "form" ).length > 0 ) {
	        $("form").each(function() {
	        	  $(this).attr("autocomplete","off");
	        	  $(this).attr("autocorrect","off");
	        	  $(this).attr("spellcheck","false");
	        });
	    }
	    	    
	    //establecer el no. de tareas cuando la p�gina se encuentre lista
	    if(!window.location.pathname.includes("login")){
	    	$("#frmTareas\\:btnTareas").val(storage.getListDataSize());
	    	if (storage.getListDataSize() > 0) {
	    		$("#frmTareas\\:btnTareas .ui-button-text.ui-c").text(storage.getListDataSize());
	    	} else {
	    		$("#frmTareas\\:btnTareas .ui-button-text.ui-c").text("");
	    	}
	    }
	    
	    $(document).on("keydown", function (e) {
	    	if([13,17,112,113,114,115,116,117,118,119,120,122,123,124,125,126].indexOf(e.which) != -1){
	    		e.preventDefault();
	    		return false;
	    	}
	    	if (e.which === 8 && (!$(e.target).is("input, textarea") || $(e.target).is("input[type='checkbox']") || $(e.target).is("input[readonly='readonly']") ) ) {	   	
	    		e.preventDefault();
	    		}
	        if (e.ctrlKey &&  e.keyCode === 82) {
	        	return false;
	        } 
	    });
	    
	    $(document).on( "pfAjaxStart", function( event) {
	    	if($(document.activeElement).hasClass( "ui-button")){
	    		$(document.activeElement).attr('disabled','true').addClass('ui-state-disabled').addClass('arqBtnCtrlAjax');
	    	}
		});
	    
	    // Funcion para eliminar las aspas de las validaciones required y no de las de formato
	    $(document).on("pfAjaxComplete",function() {
	    	jQuery('.ui-message-error-icon[title=""]').parent().hide();
	    	$(".arqBtnCtrlAjax").removeAttr('disabled').removeClass('ui-state-disabled').removeClass("arqBtnCtrlAjax");
    	});
	    
	    $(document).on("pfAjaxError",function() {
	    	$(".arqBtnCtrlAjax").removeAttr('disabled').removeClass('ui-state-disabled').removeClass("arqBtnCtrlAjax");
    	});
	   
	    //establecer tama�o m�nimo de la ventana
	    if ( $( "main" ).length > 0 ) {
	    	$("main").css("min-height",$(window).height());	    
	    }
	    
	    $("main").on("keydown", function(){applyInputValidator()})
	    
	});
}(jQuery))

//funcion para ocultar y mostrar la toolbox
$(function() {
	$('.icon').click(function() {
		$('.toolbox').toggleClass('tool-300');
	});
});

//funcion para cambiar el hover del bot�n del menu global
$(function() {
	$('.cd-nav-trigger').click(function() {
		$('.cd-nav-trigger').toggleClass('ocultar-menu');
	});
});

//funcion para adaptar texto a un contenedor
$(function() {
	if(!window.location.pathname.includes("login")){
		$('.textfill').textfill({});
    }
});

/**
 * Funci�n responsable de cargar la lista de tareas pendientes.
 * 
 * Utilizada en: encabezado.xhtml
 */
function cargarListaDeTareasPendientes() {
	var formId = $("#storage").closest("form").attr("id");
	var listado = storage.listSavedData();
	document.getElementById("frmTareas:listaDatosOculta").value = listado;
};

/**
 * Funci�n responsable de cargar los datos que ser�n restaurados y presentados
 * en la vista.
 * 
 * Utilizada en: tareas.xhtml
 * 
 * @param clave
 */
function cargarDatosARestaurar(clave) {
	var datosARestaurar = storage.recoverData(clave);
	document.getElementById("frmListaTareas:datosOcultos").value = datosARestaurar;
};

/**
 * Funci�n que permite actualizar el n�mero de tareas pendientes conforme vayan
 * siendo agregadas, editadas o eliminadas.
 * 
 * Utilizada en: encabezado.xhtml
 */
function actualizarNumeroDeTareas() {
	if (storage != null) {
		$("#frmTareas\\:btnTareas").val(storage.getListDataSize());
		if (storage.getListDataSize() > 0) {
			$("#frmTareas\\:btnTareas .ui-button-text.ui-c").text(storage.getListDataSize());
		} else {
			$("#frmTareas\\:btnTareas .ui-button-text.ui-c").text("");
		}
	}
}

/**
 * Funci�n para mostrar barra de procesando
 * ejecuci�n
 */
function showAjaxDialog() {
	ajaxStatusTimeout = setTimeout("PF('statusDialog').show();", 2000);
}

/**
 * Funci�n para generar un pdf en un iframe y poderlo imprimir
 */
function print(url)
{
    var _this = this,
        iframeId = 'iframeprint',
        $iframe = $('iframe#iframeprint');
    $iframe.attr('src', url);

    $iframe.load(function() {
        _this.callPrint(iframeId);
    });
}

/**
 * Funci�n que inicia la impresion una vez cargado el pdf
 */
function callPrint(iframeId) {
    var PDF = document.getElementById(iframeId);
    PDF.focus();
    PDF.contentWindow.print();
}

function applyInputValidator(){
	$.merge($("input[type=text]"),$("textarea")).bind('input', function() {
		var start = this.selectionStart,
        	end = this.selectionEnd;
    	var _this = $(this);
    	for(var propertyName in PrimeFaces.widgets) {
    		if(PrimeFaces.widgets[propertyName] != null && PrimeFaces.widgets[propertyName] != undefined &&
    				PrimeFaces.widgets[propertyName].cfg != null && PrimeFaces.widgets[propertyName].cfg != undefined){
	    		if(PrimeFaces.widgets[propertyName].cfg.target == this.id){
	    			var regEx = PrimeFaces.widgets[propertyName].cfg.regEx;
	    			if(regEx != null && regEx != undefined){
	    				_this.val(_this.val().removeDiacritics());
		    			for(j=0; j < _this.val().length; j++){
		    				if(!regEx.test(_this.val().charAt(j))){
		    					_this.val(_this.val().replaceAt(j,charReplacer));
		    				}
		    			}
	    			}
	    			this.setSelectionRange(start, end);
	    			return;
	    		}
    		}
    	}
    	for(i=0; i < specialChars.length ; i++){
    		_this.val(_this.val().removeDiacritics().split(specialChars[i]).join(charReplacer));
    	}
    	this.setSelectionRange(start, end);
    });
}

String.prototype.replaceAt=function(index, character) {
    return this.substr(0, index) + character + this.substr(index+character.length);
}

String.prototype.removeDiacritics = function() {
	var diacritics = [
	    [/[\300-\306]/g, 'A'],
	    [/[\310-\313]/g, 'E'],
	    [/[\314-\317]/g, 'I'],
	    [/[\322-\330]/g, 'O'],
	    [/[\331-\334]/g, 'U'],
	    [/[\307]/g, 'C']
	];
	var s = this.toUpperCase();
	for (var i = 0; i < diacritics.length; i++) {
	    s = s.replace(diacritics[i][0], diacritics[i][1]);
	}
	return s;
}

/**
 * Funi�n llamada antes de cambiar de pantalla para mostrar la carga de la 
 * siguiente ventana 
 */
window.onbeforeunload = function(e) {
	PF('redirectDialog').show();
}
	
// /**
// * Desabilita el bot�n secundario del rat�n
//	 */
//	window.oncontextmenu = function () {
//	   return false;
//	}
//
//	/**
//	 * Desabilita el f12
//	 */
//	document.onkeydown = function (e) { 
//	    if (window.event.keyCode == 123 ||  e.button==2)    
//	    return false;
//	}