package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.Iterator;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionBean;
import mx.babel.bansefi.banksystem.base.beans.LiquidacionConceptoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.descConceptoLiquidacionTRD.DescConceptoLiquidacionTRDServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.descConceptoLiquidacionTRD.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.descConceptoLiquidacionTRD.Ejecutar.IHLDESCRCONCEPTRNI.HLDESCRCONCEPEVTY.LIQCODOPERLIQV;
import mx.babel.bansefi.banksystem.cuentas.webservices.descConceptoLiquidacionTRD.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.descConceptoLiquidacionTRD.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaApunteWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DescConceptoLiquidacionTRDBackend extends BackEndBean {

	private static final long serialVersionUID = 3652783561118549315L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaApunteWrapper consultaApunteWrapper;

	public void ejecutarTRN(LiquidacionBean liquidacionSeleccionada) {

		Ejecutar.IHLDESCRCONCEPTRNI contexto = initPeticion(liquidacionSeleccionada);
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils
				.ejecutarWS(DescConceptoLiquidacionTRDServicio.class, contexto);

		super.verificaRespuesta(respuesta);
		getDescLiquidacion(respuesta, liquidacionSeleccionada);
	}

	/**
	 * Función que inicializa el objeto para realizar la petición al servicio
	 * web.
	 * 
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.IHLDESCRCONCEPTRNI initPeticion(LiquidacionBean liquidacionSeleccionada) {
		Ejecutar.IHLDESCRCONCEPTRNI contexto = new Ejecutar.IHLDESCRCONCEPTRNI();
		Ejecutar.IHLDESCRCONCEPTRNI.STDTRNIPARMV contextoEntrada = new Ejecutar.IHLDESCRCONCEPTRNI.STDTRNIPARMV();
		Ejecutar.IHLDESCRCONCEPTRNI.HLDESCRCONCEPEVTY cuerpoContexto = new Ejecutar.IHLDESCRCONCEPTRNI.HLDESCRCONCEPEVTY();

		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_LIQ_CONS_BASICA_TRN);
		contextoEntrada.setIDINTERNOTERMTN(getTerminal());
		
		for (Iterator<LiquidacionConceptoBean> iterator = liquidacionSeleccionada.getListaConceptos().iterator(); iterator.hasNext();) {
			Ejecutar.IHLDESCRCONCEPTRNI.HLDESCRCONCEPEVTY.LIQAPUNTEDESCRV consultaInfo = new Ejecutar.IHLDESCRCONCEPTRNI.HLDESCRCONCEPEVTY.LIQAPUNTEDESCRV();
			LiquidacionConceptoBean mov = (LiquidacionConceptoBean) iterator.next();
			consultaInfo.setCODCTA(mov.getCodCuenta());
			//setear lista de indicadores
			consultaInfo.setIND1(mov.getIndicadores().get(0));
			consultaInfo.setIND2(mov.getIndicadores().get(1));
			consultaInfo.setIND3(mov.getIndicadores().get(2));
			consultaInfo.setIND4(mov.getIndicadores().get(3));
			consultaInfo.setIND5(mov.getIndicadores().get(4));
			consultaInfo.setIND6(mov.getIndicadores().get(5));
			consultaInfo.setIND7(mov.getIndicadores().get(6));
			consultaInfo.setIND8(mov.getIndicadores().get(7));
			consultaInfo.setIND9(mov.getIndicadores().get(8));
			consultaInfo.setIND10(mov.getIndicadores().get(9));
			consultaInfo.setCODORIGEN(mov.getCodOrigen());
			consultaInfo.setCODLINEA(liquidacionSeleccionada.getCodLinea());
			consultaInfo.setIDGRPPD(liquidacionSeleccionada.getIdGrpPd());
			cuerpoContexto.getLIQAPUNTEDESCRV().add(consultaInfo);
			LIQCODOPERLIQV nuevoCodOperLiq = new LIQCODOPERLIQV();
			nuevoCodOperLiq.setCODOPERLIQ(liquidacionSeleccionada.getCodOperacionLiq());
			cuerpoContexto.getLIQCODOPERLIQV().add(nuevoCodOperLiq);
		}

		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setSCROLLABLEOCCURS(SCROLLABLE_OCCURS);
		contexto.setELEVATORPOSITION(1);
		contexto.setNUMBEROFRECORDS(cuerpoContexto.getLIQCODOPERLIQV().size());
		contexto.setHLDESCRCONCEPEVTY(cuerpoContexto);
		super.initialize(contexto);

		return contexto;
	}

	/**
	 * Función encargada de obtener una lista de liquidaciones a partir de la
	 * respuesta del servicio.
	 * 
	 * @param idInterno
	 *            El id interno de la persona moral
	 * @param response
	 *            El objeto de reultado enviado por el servicio web.
	 * @return
	 */
	private void getDescLiquidacion(EjecutarResult respuesta,LiquidacionBean liquidacionSeleccionada)throws NoControlableException, ControlableException {
		List<LiquidacionConceptoBean> movs = liquidacionSeleccionada.getListaConceptos();
		if (verificaRespuesta(respuesta)) {
			List<ResponseBansefi.OHLDESCRCONCEPTRNO.HLDESCRCONCEPEVTZ.LIQCONCEPTODESCV> detalleMovs = respuesta.getResponseBansefi().getOHLDESCRCONCEPTRNO().getHLDESCRCONCEPEVTZ().getLIQCONCEPTODESCV();
			for(int i = 0; i< detalleMovs.size();i++){
				if(detalleMovs.get(i).getNOMBPARMCD() != null && !"".equals(detalleMovs.get(i).getNOMBPARMCD().trim())){
					movs.get(i).setDescConcepto(detalleMovs.get(i).getNOMBPARMCD().trim());
				}
				if(respuesta.getResponseBansefi().getOHLDESCRCONCEPTRNO().getHLDESCRCONCEPEVTZ().getLIQORDENAPUNTEV().get(i).getSGN()!=null &&
						!"".equals(respuesta.getResponseBansefi().getOHLDESCRCONCEPTRNO().getHLDESCRCONCEPEVTZ().getLIQORDENAPUNTEV().get(i).getSGN().trim())){
					movs.get(i).setSigno(respuesta.getResponseBansefi().getOHLDESCRCONCEPTRNO().getHLDESCRCONCEPEVTZ().getLIQORDENAPUNTEV().get(i).getSGN());
				}
			}
		}
	}

	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los
	 * datos de la consulta del detalle de liquidacion
	 * 
	 * @param respuesta
	 *            Respuesta Bansefi con datos de la consulta
	 * @return <code>true</code> si la respuesta Bansefi contiene datos
	 */
	private Boolean verificaRespuesta(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null
				&& respuesta.getResponseBansefi().getOHLDESCRCONCEPTRNO() != null
				&& respuesta.getResponseBansefi().getOHLDESCRCONCEPTRNO().getHLDESCRCONCEPEVTZ() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	

}