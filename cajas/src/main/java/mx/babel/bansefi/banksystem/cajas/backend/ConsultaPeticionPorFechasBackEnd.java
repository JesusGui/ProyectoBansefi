package mx.babel.bansefi.banksystem.cajas.backend;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.cajas.beans.PeticionEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.beans.PeticionUrgenteBean;
import mx.babel.bansefi.banksystem.cajas.beans.RecogidaEfectivoBean;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionporfechas.*;
import mx.babel.bansefi.banksystem.cajas.webservice.consultapeticionporfechas.ResponseBansefi.OTRSOLCTSMLSTTRNO.TRSOLCTSMLSTEVTZ.SMSOLCTMONEDAV;

@Component
public class ConsultaPeticionPorFechasBackEnd extends BackEndBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6334905100635252931L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	private List<PeticionUrgenteBean> listaPeticionesUrgentes;

	public List<PeticionUrgenteBean> getListaPeticionesUrgentes() {
		return listaPeticionesUrgentes;
	}

	public void setListaPeticionesUrgentes(
			List<PeticionUrgenteBean> listaPeticionesUrgentes) {
		this.listaPeticionesUrgentes = listaPeticionesUrgentes;
	}

	/**
	 * Método para inicializar objeto de petición
	 * 
	 * @return Objeto de petición al ws
	 */
	private Ejecutar.ITRSOLCTSMLSTTRNI initPeticion(int fechaSolic) {
		Ejecutar.ITRSOLCTSMLSTTRNI contexto = new Ejecutar.ITRSOLCTSMLSTTRNI();
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(50);

		Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY tRSOLCTSMLSTEVTY = new Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY();

		Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY.SMSOLCTMONEDAP sMSOLCTMONEDAP = new Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY.SMSOLCTMONEDAP();
		sMSOLCTMONEDAP.setCODNRBEEN(super.getEntidad());
		sMSOLCTMONEDAP.setCODINTERNOUO(super.getSucursal());
		sMSOLCTMONEDAP.setCODPPL("M");
		sMSOLCTMONEDAP.setCODDISTRIB("U");

		tRSOLCTSMLSTEVTY.setSMSOLCTMONEDAP(sMSOLCTMONEDAP);

		Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY.SMFECHADESDEV sMFECHADESDEV = new Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY.SMFECHADESDEV();
		sMFECHADESDEV.setSTDFECHA(fechaSolic);

		Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY.SMFECHAHASTAV sMFECHAHASTAV = new Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY.SMFECHAHASTAV();
		sMFECHAHASTAV.setSTDFECHA(fechaSolic);

		tRSOLCTSMLSTEVTY.setSMFECHADESDEV(sMFECHADESDEV);
		tRSOLCTSMLSTEVTY.setSMFECHAHASTAV(sMFECHAHASTAV);

		contexto.setTRSOLCTSMLSTEVTY(tRSOLCTSMLSTEVTY);

		Ejecutar.ITRSOLCTSMLSTTRNI.STDTRNIPARMV sTDTRNIPARMV = new Ejecutar.ITRSOLCTSMLSTTRNI.STDTRNIPARMV();
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setNUMSEC(0);
		sTDTRNIPARMV.setCODTX("VCM27COU");
		sTDTRNIPARMV.setCODTXDI("CO27");
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);

		return contexto;

	}

	/**
	 * Método para obtener la respuesta del ws a partir de un objeto de petición
	 * 
	 * @param contexto
	 *            Objeto de petición
	 * @return respuesta del ws
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRSOLCTSMLSTTRNI contexto) {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaPeticionPorFechasServicio.class, contexto);

		} catch (NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de alta "
							+ "de peticion de efectivo.", e);
		}

		return respuesta;

	}

	/**
	 * Método que consulta peticion de efectivo por fechas
	 */
	public void ejecutarTRN(PeticionEfectivoBean peticion, int fechaSolic) {
		Ejecutar.ITRSOLCTSMLSTTRNI contexto = initPeticion(fechaSolic);
		EjecutarResult respuesta = ejecutarWS(contexto);

		try {
			super.verificaRespuesta(respuesta);
		} catch (ControlableException ce) {
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS) {
				throw ce;
			} else {
				return;
			}
		}

		if (verificaResponseBansefi(respuesta)) {
			getPeticionesUrgentes(respuesta.getResponseBansefi(), peticion);
		}

	}
	
	/**
	 * Método que consulta peticion de efectivo por fechas
	 */
	public void ejecutarTRN(int fechaSolic) {
		Ejecutar.ITRSOLCTSMLSTTRNI contexto = initPeticion2(fechaSolic);
		EjecutarResult respuesta = ejecutarWS(contexto);

		try {
			super.verificaRespuesta(respuesta);
		} catch (ControlableException ce) {
			if (ce.getRtncod() != RETURN_COD_OK) {
				throw ce;
			}
		}

		if (verificaResponseBansefi(respuesta)) {
			getPeticionesUrgentes(respuesta.getResponseBansefi());
		}
	}
	
	private Ejecutar.ITRSOLCTSMLSTTRNI initPeticion2(int fechaSolic) {
		Ejecutar.ITRSOLCTSMLSTTRNI contexto = new Ejecutar.ITRSOLCTSMLSTTRNI();
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(50);

		Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY tRSOLCTSMLSTEVTY = new Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY();

		Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY.SMSOLCTMONEDAP sMSOLCTMONEDAP = new Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY.SMSOLCTMONEDAP();
		sMSOLCTMONEDAP.setCODNRBEEN(super.getEntidad());
		sMSOLCTMONEDAP.setCODINTERNOUO(super.getSucursal());
		sMSOLCTMONEDAP.setCODPPL("M");
		sMSOLCTMONEDAP.setCODDISTRIB("T");

		tRSOLCTSMLSTEVTY.setSMSOLCTMONEDAP(sMSOLCTMONEDAP);

		Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY.SMFECHADESDEV sMFECHADESDEV = new Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY.SMFECHADESDEV();
		sMFECHADESDEV.setSTDFECHA(fechaSolic);

		Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY.SMFECHAHASTAV sMFECHAHASTAV = new Ejecutar.ITRSOLCTSMLSTTRNI.TRSOLCTSMLSTEVTY.SMFECHAHASTAV();
		sMFECHAHASTAV.setSTDFECHA(fechaSolic);

		tRSOLCTSMLSTEVTY.setSMFECHADESDEV(sMFECHADESDEV);
		tRSOLCTSMLSTEVTY.setSMFECHAHASTAV(sMFECHAHASTAV);

		contexto.setTRSOLCTSMLSTEVTY(tRSOLCTSMLSTEVTY);

		Ejecutar.ITRSOLCTSMLSTTRNI.STDTRNIPARMV sTDTRNIPARMV = new Ejecutar.ITRSOLCTSMLSTTRNI.STDTRNIPARMV();
		sTDTRNIPARMV.setIDINTERNOTERMTN(super.getTerminal());
		sTDTRNIPARMV.setNUMSEC(0);
		sTDTRNIPARMV.setCODTX("VCM27COU");
		sTDTRNIPARMV.setCODTXDI("CO27");
		contexto.setSTDTRNIPARMV(sTDTRNIPARMV);

		return contexto;

	}
	
	private void getPeticionesUrgentes(ResponseBansefi respuesta) {
		this.listaPeticionesUrgentes = new ArrayList<PeticionUrgenteBean>();
		for (SMSOLCTMONEDAV denominacion : respuesta.getOTRSOLCTSMLSTTRNO().getTRSOLCTSMLSTEVTZ().getSMSOLCTMONEDAV()) {
			if (denominacion.getINDURG() != 0) {
				
				PeticionUrgenteBean pUrgente = new PeticionUrgenteBean();

				pUrgente.setIndicadorUrgente(denominacion.getINDURG());
				pUrgente.setIntFechasolicitud(denominacion.getFECHASOLCTSM());
				String descripcion = Integer.toString(denominacion.getINDURG()) + " Tot.Pet. " + formateaNumero(denominacion.getIMPPEDIDOV());
				pUrgente.setDescripcionL(descripcion);
				pUrgente.setCodigo(denominacion.getCODPPL());
				pUrgente.setCodigoDistribucion(denominacion.getCODDISTRIB());
				
				this.listaPeticionesUrgentes.add(pUrgente);
			}
		}
	}
	
	private String formateaNumero(BigDecimal importe){
		DecimalFormat formateador = new DecimalFormat("#,##0.00");
		return formateador.format(importe) + " MXN";
	}

	/**
	 * Función que valida que la respues del servidor no este vacía.
	 * 
	 * @param respuesta
	 *            Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
		Boolean noNulo = false;
		if (respuesta != null && respuesta.getResponseBansefi() != null) {
			noNulo = true;
		}
		return noNulo;
	}

	private void getPeticionesUrgentes(ResponseBansefi respuesta,
			PeticionEfectivoBean peticion) {
		// peticion.getComboUrgenteBean().setListaIndicadoresUrgentes(new
		// ArrayList<PeticionUrgenteBean>());

		for (SMSOLCTMONEDAV denominacion : respuesta.getOTRSOLCTSMLSTTRNO()
				.getTRSOLCTSMLSTEVTZ().getSMSOLCTMONEDAV()) {
			if (denominacion.getINDURG() != 0) {
				PeticionUrgenteBean pUrgente = new PeticionUrgenteBean();

				pUrgente.setIndicadorUrgente(denominacion.getINDURG());
				pUrgente.setIntFechasolicitud(denominacion.getFECHASOLCTSM());
				String descripcion = Integer.toString(denominacion.getINDURG())
						+ " Tot.Pet. " + denominacion.getIMPPEDIDOV().toString();
				pUrgente.setDescripcionL(descripcion);
				peticion.getComboUrgenteBean().getListaIndicadoresUrgentes()
						.add(pUrgente);
			}

		}
	}
}
