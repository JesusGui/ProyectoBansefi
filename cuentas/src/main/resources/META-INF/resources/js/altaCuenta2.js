function toogleDivs() {
  if($(".pnlColapsable:hidden").length > 0){
  	$(".pnlColapsable:hidden").show();
    $("#formAltaCuenta2\\:toogleDivs").children(".ui-button-text").text("Ocultar");
    $("#formAltaCuenta2\\:toogleDivs").children(".ui-button-icon-left").removeClass('fa-chevron-down');
    $("#formAltaCuenta2\\:toogleDivs").children(".ui-button-icon-left").addClass('fa-chevron-up');
  }else{
    $(".pnlColapsable:visible").hide();
    $("#formAltaCuenta2\\:toogleDivs").children(".ui-button-text").text("Mostrar todas");
    $("#formAltaCuenta2\\:toogleDivs").children(".ui-button-icon-left").removeClass('fa-chevron-up');
    $("#formAltaCuenta2\\:toogleDivs").children(".ui-button-icon-left").addClass('fa-chevron-down');
  }
  
  return false;
}