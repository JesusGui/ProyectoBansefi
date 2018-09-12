/**
 * 
 */
(function($) {
	$(document).ready(
			function($) {
//				$(document).on("pfAjaxStart", function(event) {
//							if ($(document.activeElement).hasClass("ui-datatable")) {
//								$(document.activeElement).attr('disabledSelection', 'true').css('opacity', '0.6');
//							}
//						});
				
				$(document).on("pfAjaxSend", function(event, xhr, options) {
				    var $source = $(document.getElementById(options.source));
				    if($source.hasClass("ui-datatable")){
					    $source.addClass('blocker');
				    }
				    
				});

				$(document).on("pfAjaxComplete", function(event, xhr, options) {
					jQuery('.ui-message-error-icon[title=""]').parent().hide();
					var $source = $(document.getElementById(options.source));
					if ($source.hasClass("ui-datatable")) {
						$source.removeClass('blocker');
					}
				});

			});
}(jQuery))