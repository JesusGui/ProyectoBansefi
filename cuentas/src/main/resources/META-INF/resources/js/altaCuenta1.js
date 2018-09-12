PrimeFaces.widget.Droppable.prototype.bindDropListener = function() {
	var _self = this;
	this.cfg.drop = function(event, ui) {
		if (_self.cfg.onDrop) {
			_self.cfg.onDrop.call(_self, event, ui);
		}
		if (_self.cfg.behaviors) {
			var dropBehavior = _self.cfg.behaviors['drop'];
			if (dropBehavior) {
				var draggableId = ui.draggable.attr('id');
				var ext = {
					params : [ {
					  name : _self.id + '_dragId',
					  value : draggableId
					}, {
					  name : _self.id + '_dropId',
					  value : _self.cfg.target
					}, {
					  name : draggableId + '_selfId',
					  value : _self.id
					} ]
				};
				dropBehavior.call(_self, ext);
			}
		}
	};
}

var irSiguientePaso = true;

function getPosition(elementId) {
	var splittedId = elementId.split(':');
	return splittedId[splittedId.length - 2];
}
function isInCuentasFrecuentes(element) {
	return $("fieldset[id$='cuentasFrecuentes']").has(element).length > 0;
}

function nuevaFilaVacia() {
	var element = $("fieldset[id$='cuentasFrecuentes']").find(
	    ".ui-datagrid-row:first").clone();
	$(element).addClass("removableRow");
	$(element).children("td").empty().removeClass("ui-state-highlight");
	$(element).appendTo(
	    $("fieldset[id$='cuentasFrecuentes']").find("tbody:first"));
	$(element).children("td").droppable({
	  activeClass : 'ui-state-active',
	  hoverClass : 'ui-state-highlight',
	  tolerance : 'pointer',
	  scope : 'dropIn',
	  greedy : true,
	  over : function(event, ui) {
		  console.log("over temp");
		  $(".removable").remove();
		  $(".hiddeable").show().removeClass("hiddeable");
	  },
	  drop : function(event, ui) {
		  console.log("drop temp");
		  dropAtTheEnd([ {
		    name : 'atTheEnd_dragId',
		    value : ui.draggable.attr("id")
		  } ]);
	  }
	});
}

function initDND() {
	$("fieldset[id$='cuentasFrecuentes']").find(".ui-datagrid-column").css(
	    "width", "208px").css("height", "160px");

	$("fieldset[id$='cuentasFrecuentes']").find(".ui-datagrid-column:empty")
	    .droppable({
	      activeClass : 'ui-state-active',
	      hoverClass : 'ui-state-highlight',
	      tolerance : 'pointer',
	      scope : 'dropIn',
	      greedy : true,
	      over : function(event, ui) {
		      console.log("over real");
		      $(".removable").remove();
		      $(".hiddeable").show().removeClass("hiddeable");
	      },
	      drop : function(event, ui) {
		      console.log("drop real");
		      dropAtTheEnd([ {
		        name : 'atTheEnd_dragId',
		        value : ui.draggable.attr("id")
		      } ]);
	      }
	    });
	$("fieldset[id$='cuentasFrecuentes']")
	    .find(".ui-datagrid-column:parent")
	    .droppable(
	        {
	          activeClass : 'ui-state-active',
	          hoverClass : 'ui-state-highlight',
	          tolerance : 'pointer',
	          scope : 'dropIn',
	          greedy : true,
	          over : function(event, ui) {
		          console.log('over');
		          $(".removable").remove();
		          $(".hiddeable").show().removeClass("hiddeable");
		          if (isInCuentasFrecuentes(ui.draggable)) {
			          console.log('old');
			          var start_pos = parseInt(getPosition(ui.draggable.attr("id")),
			              10);
			          if ($(
			              $("fieldset[id$='cuentasFrecuentes']").find("tbody:first")
			                  .children("tr").children("td")[start_pos]).children(
			              ":first").hasClass("absolutable")) {
				          $(".absolutable").children(":first").appendTo(
				              $(".absolutable").parent());
				          $(".absolutable").remove();
			          }
			          // if($(this).children(":first").attr("id") !==
			          // ui.draggable.attr("id")){
			          if ($(this).has(ui.draggable).length == 0) {
				          var tempPos = parseInt(getPosition($(this).children(":first")
				              .attr("id")), 10);

				          // TR pos --> tempPos mod 6
				          // TD pos --> Round (tempPos / 6)
				          if (start_pos < tempPos) {
					          console.log("-->" + start_pos + "-" + tempPos);
					          $(
					              $("fieldset[id$='cuentasFrecuentes']").find(
					                  "tbody:first").children("tr").children("td")[start_pos])
					              .children(":first")
					              .wrap(
					                  "<div class='absolutable' style='position:absolute;'></div>");

					          for (var i = start_pos; i < tempPos; i++) {
						          $(
						              $("fieldset[id$='cuentasFrecuentes']").find(
						                  "tbody:first").children("tr").children("td")[i + 1])
						              .children(":first")
						              .clone()
						              .addClass("removable")
						              .appendTo(
						                  $($("fieldset[id$='cuentasFrecuentes']").find(
						                      "tbody:first").children("tr").children("td")[i]));
						          $(
						              $("fieldset[id$='cuentasFrecuentes']").find(
						                  "tbody:first").children("tr").children("td")[i + 1])
						              .children(":first").addClass("hiddeable").hide();
					          }
				          } else {
					          console.log("<--" + start_pos + "-" + tempPos)
					          for (var i = tempPos; i < start_pos; i++) {
						          $(
						              $("fieldset[id$='cuentasFrecuentes']").find(
						                  "tbody:first").children("tr").children("td")[i])
						              .children(":first")
						              .clone()
						              .addClass("removable")
						              .appendTo(
						                  $($("fieldset[id$='cuentasFrecuentes']").find(
						                      "tbody:first").children("tr").children("td")[i + 1]));
						          $(
						              $("fieldset[id$='cuentasFrecuentes']").find(
						                  "tbody:first").children("tr").children("td")[i])
						              .children(":first").addClass("hiddeable").hide();
					          }
					          $(
					              $("fieldset[id$='cuentasFrecuentes']").find(
					                  "tbody:first").children("tr").children("td")[start_pos])
					              .children(":first")
					              .wrap(
					                  "<div class='hiddeable' style='position:absolute;'></div>");
				          }
			          }
		          } else {
			          var start_pos = ui.draggable.data("initialSize");
			          if (!start_pos) {
				          var totalDivs = $("fieldset[id$='cuentasFrecuentes']").find(
				              "tbody:first>tr").children("td:parent").size();
				          if ((totalDivs % 6) === 0) {
					          nuevaFilaVacia();
				          }
				          start_pos = $("div[id$='dropArea']").closest("tr td:parent")
				              .size();
				          ui.draggable.data("initialSize", start_pos);
			          }

			          var tempPos = parseInt(getPosition($(this).children(":first")
			              .attr("id")), 10);
			          for (var i = tempPos; i < start_pos; i++) {
				          $(
				              $("fieldset[id$='cuentasFrecuentes']")
				                  .find("tbody:first").children("tr").children("td")[i])
				              .children(":first")
				              .clone()
				              .addClass("removable")
				              .appendTo(
				                  $($("fieldset[id$='cuentasFrecuentes']").find(
				                      "tbody:first").children("tr").children("td")[i + 1]));
				          $(
				              $("fieldset[id$='cuentasFrecuentes']")
				                  .find("tbody:first").children("tr").children("td")[i])
				              .children(":first").addClass("hiddeable").hide();
			          }
		          }
	          },
	          deactivate : function(event, ui) {
		          ui.draggable.removeData("initialSize");
		          $(".removableRow").remove();
		          $(".removable").remove();
		          $(".hiddeable").show().removeClass("hiddeable");
	          },
	          drop : function(event, ui) {
		          if ($(this).has(ui.draggable).length == 0) {
			          var draggableId = ui.draggable.attr("id");
			          dropInOrdered([ {
			            name : 'inOrdered_dragId',
			            value : draggableId
			          }, {
			            name : draggableId + '_selfId',
			            value : $(this).children(":first").attr("id")
			          } ]);
		          }
	          }
	        });

	$("div[id$='dropArea']").draggable({
	  revert : true,
	  stack : '.ui-panel',
	  cursor : 'pointer',
	  scope : 'dropIn'
	});

	$("body").droppable({
	  activeClass : 'ui-state-active',
	  tolerance : 'pointer',
	  scope : 'dropIn',
	  drop : function(event, ui) {
		  if ($("fieldset[id$='cuentasFrecuentes']").has(ui.draggable).length > 0) {
			  dropOut([ {
			    name : 'dropOut_dragId',
			    value : ui.draggable.attr("id")
			  } ]);
		  }

	  }
	});

	$(".ui-panel.ui-widget.ui-widget-content.ui-corner-all.ui-draggable.ui-draggable-handle")
	    .click(function() {
	    	if(irSiguientePaso){
  		    clickTarifa([ {
  		      name : 'clickTarifa_target',
  		      value : $(this).attr("id")
  		    } ]);
	    	}
	    });
}

function ponerFalse(){
	irSiguientePaso = false;
}

function ponerTrue(){
	irSiguientePaso = true;
	if ($(".ui-state-error").length > 0) {
		$("#formAltaCuenta1\\:nombreTitular").hide();
	}
}

function focusOnError() {
	if ($(".ui-state-error").length > 0 || $(".ui-messages-fatal").length > 0) {
		window.scrollTo("#formAltaCuenta1");
	}
}

function initWithoutDND(){
  $(".ui-panel.ui-widget.ui-widget-content.ui-corner-all.objeto-drag")
  .click(function() {
  	if(irSiguientePaso){
      clickTarifa([ {
        name : 'clickTarifa_target',
        value : $(this).attr("id")
      } ]);
  	}
  });
}