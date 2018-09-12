function loadTab(id) {
	if (PF(id).cfg.active != null) {
		return !PF(id).isLoaded(PF(id).panels.eq(PF(id).cfg.active));
	}
	return true;
}

function focusOpenErrorTabs() {
	$.each($(".ui-state-error"), function(index, value) {
		if ($(value).closest("div[role='tabpanel']").css("display") === "none") {
			PF('accordionCondiciones').select(
			    $(value).closest("div[role='tabpanel']").attr("id").split(":")[2]);
		}
	});
	if ($(".ui-state-error").first().length > 0) {
		$(".ui-state-error").first().focus();
	}
}

function focusOpenErrorTabs() {
	$.each($(".ui-state-error"), function(index, value) {
		if ($(value).closest("div[role='tabpanel']").css("display") === "none") {
			var WV = $(value).closest("div[data-widget^='idPds']")
			    .attr("data-widget");
			PF(WV).select(
			    $(value).closest("div[role='tabpanel']").attr("id").split(":")[2]);

		}
	});
	if ($(".ui-state-error").first().length > 0) {
		$(".ui-state-error").first().focus();
	}
}

function unLoadTab() {
	$.each($("div[data-widget^='idPds']"), function(index, value) {
		$.each(PF($(value).attr("data-widget")).panels, function(index2, value2) {
			PF($(value).attr("data-widget")).unselect($(value2).closest("div[role='tabpanel']").attr("id").split(":")[2]);
			$(value2).data('loaded', false);
	  });
	});
	
}