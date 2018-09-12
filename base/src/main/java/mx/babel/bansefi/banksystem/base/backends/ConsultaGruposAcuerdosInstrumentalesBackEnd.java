package mx.babel.bansefi.banksystem.base.backends;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultagruposproductosvendibles.ConsultaGrpPrdVendServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultagruposproductosvendibles.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultagruposproductosvendibles.Ejecutar.ITRCONSLISTAPDVTRNI;
import mx.babel.bansefi.banksystem.base.webservices.consultagruposproductosvendibles.ResponseBansefi.OTRCONSLISTAPDVTRNO.TRCONSLISTAPDVEVTZ.LGLISTAV;

/**
 * Clase BackEnd para consultar los grupos de acuerdos instrumentales.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class ConsultaGruposAcuerdosInstrumentalesBackEnd extends BackEndBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3385885728026811492L;
	
	@Autowired
	private ServicioWebUtils servicioWebUtils;

	public ConsultaGruposAcuerdosInstrumentalesBackEnd() {
		super();
	}
	
	public List<CatalogoBean> ejecutarTRN(String codLinea) {
		return ejecutarTRN(codLinea, false);
	}
	
	public List<CatalogoBean> ejecutarTRN(String codLinea, Boolean catalogoTarifas) {
		List<CatalogoBean> listCatalogo = new ArrayList<>();
		ITRCONSLISTAPDVTRNI itrconslistapdvtrni = initPeticion(codLinea, catalogoTarifas);

		EjecutarResult resultado = null;
		resultado = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ConsultaGrpPrdVendServicio.class, itrconslistapdvtrni);
		super.verificaRespuesta(resultado);
		List<LGLISTAV> grupos = resultado.getResponseBansefi()
		.getOTRCONSLISTAPDVTRNO().getTRCONSLISTAPDVEVTZ().getLGLISTAV();
		for (int i = 0; i <resultado.getResponseBansefi()
				.getOTRCONSLISTAPDVTRNO().getNUMBEROFRECORDS();i++) {
			CatalogoBean catalogo = new CatalogoBean();
			catalogo.setClaveFila(grupos.get(i).getIDGRPPD());
			catalogo.setDescripcionC(grupos.get(i).getNOMBGRPPD().trim());
			catalogo.setDescripcionL(grupos.get(i).getNOMBGRPPD().trim());
			listCatalogo.add(catalogo);
		}
		return listCatalogo;
	}

	private ITRCONSLISTAPDVTRNI initPeticion(String codLinea, Boolean catalogoTarifas) {

		ITRCONSLISTAPDVTRNI itrconslistapdvtrni = new ITRCONSLISTAPDVTRNI();
		super.initialize(itrconslistapdvtrni);

		itrconslistapdvtrni.setELEVATORPOSITION(1);
		itrconslistapdvtrni.setSCROLLABLEOCCURS(50);

		if(catalogoTarifas){
			itrconslistapdvtrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		}else{
			itrconslistapdvtrni.getTRCONSLISTAPDVEVTY().getPVPDVP()
				.setCODNRBEEN(super.getEntidad());
			itrconslistapdvtrni.getSTDTRNIPARMV().setIDINTERNOTERMTN(
				super.getTerminal());
		}
		
		itrconslistapdvtrni.getTRCONSLISTAPDVEVTY().getPVPDVP()
				.setCODLINEA(codLinea);
		itrconslistapdvtrni.getSTDTRNIPARMV().setCODTX(
				CodTxConstants.COD_TX_TR_CONS_LISTA_PDV_TRN);

		return itrconslistapdvtrni;
	}
}
