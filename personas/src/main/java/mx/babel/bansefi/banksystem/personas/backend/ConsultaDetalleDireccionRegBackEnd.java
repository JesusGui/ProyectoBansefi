package mx.babel.bansefi.banksystem.personas.backend;

import java.util.Date;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.consultalocalidad.ConsultaLocalidadBackend;
import mx.babel.bansefi.banksystem.base.beans.domain.DomicilioTipoBean;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;
import mx.babel.bansefi.banksystem.personas.beans.DireccionRegistralBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledirecregistralpersona.ConsultaDetalleDirecRegistralServicio;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledirecregistralpersona.Ejecutar;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledirecregistralpersona.Ejecutar.ITRDRRGSTROCNSTRNI;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledirecregistralpersona.EjecutarResult;
import mx.babel.bansefi.banksystem.personas.webservices.consultadetalledirecregistralpersona.ResponseBansefi.OTRDRRGSTROCNSTRNO.TRDRRGSTROCNSEVTZ.DRCOMPRGSTROV;
import mx.babel.bansefi.banksystem.personas.wrappers.ActividadEmpresarialWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para ejecutar el servicio de Detalle de una Dirección
 * Registral.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class ConsultaDetalleDireccionRegBackEnd extends BackEndBean {

	private static final long serialVersionUID = -1506315994410968375L;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	@Autowired
	private ConsultaLocalidadBackend consultaLocalidadBackend;

	@Autowired
	private ActividadEmpresarialWrapper actividadEmpresarialWrapper;

	/**
	 * Método para ejecutar la TRN de consulta Detalle de Direcciones
	 * registrales.
	 * 
	 * @param direccion
	 *            Bean de dirección a consultar.
	 * @return DireccionRegistralBean
	 */
	public DireccionRegistralBean ejecutarTRN(DireccionRegistralBean direccion)
			throws NoControlableException, ControlableException {
		Ejecutar.ITRDRRGSTROCNSTRNI contexto = new Ejecutar.ITRDRRGSTROCNSTRNI();
		contexto.setELEVATORPOSITION(0);
		contexto.setSCROLLABLEOCCURS(50);

		Ejecutar.ITRDRRGSTROCNSTRNI.TRDRRGSTROCNSEVTY contextoEntrada = new Ejecutar.ITRDRRGSTROCNSTRNI.TRDRRGSTROCNSEVTY();

		Ejecutar.ITRDRRGSTROCNSTRNI.TRDRRGSTROCNSEVTY.DRRGSTROP contextoEntradaCampos = new Ejecutar.ITRDRRGSTROCNSTRNI.TRDRRGSTROCNSEVTY.DRRGSTROP();
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setNUMDIR(direccion.getNumDir());
		contextoEntrada.setDRRGSTROP(contextoEntradaCampos);

		Ejecutar.ITRDRRGSTROCNSTRNI.STDTRNIPARMV contextoComun = new Ejecutar.ITRDRRGSTROCNSTRNI.STDTRNIPARMV();
		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX("POT03CON");
		contextoComun.setCODTXDI("");

		// TODO: CODTX de ConstantesFuncionales y initialize si procede

		contexto.setSTDTRNIPARMV(contextoComun);
		contexto.setTRDRRGSTROCNSEVTY(contextoEntrada);

		return this.obtenerRespuestaServicio(contexto, direccion);

	}

	/**
	 * Método para obtener el resultado del servicio de consulta detalle de
	 * dirección registral.
	 * 
	 * @param contextoEntrada
	 *            Entrada con los datos a consultar
	 * @param direccion
	 *            Bean de dirección a consultar.
	 * @return DireccionRegistralBean
	 */
	private DireccionRegistralBean obtenerRespuestaServicio(ITRDRRGSTROCNSTRNI contexto, DireccionRegistralBean direccion) {

		IntegerToDateConverter converterITD = new IntegerToDateConverter();
		Date fechaInvalida1 = converterITD.convertTo(99991231);
		Date fechaInvalida2 = converterITD.convertTo(19500101);
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ConsultaDetalleDirecRegistralServicio.class, contexto);
		
		super.verificaRespuesta(respuesta);

		actividadEmpresarialWrapper.wrapperConsultaDetalleDireccion(respuesta
				.getResponseBansefi().getOTRDRRGSTROCNSTRNO()
				.getTRDRRGSTROCNSEVTZ(), direccion);
		for (DRCOMPRGSTROV dato : respuesta.getResponseBansefi()
				.getOTRDRRGSTROCNSTRNO().getTRDRRGSTROCNSEVTZ()
				.getDRCOMPRGSTROV()) {
			if (("07").equals(dato.getCODCOMPRGSTRO())) {
				StringToDateConverter converter = new StringToDateConverter();
				direccion.setfExpedicion(converter.convertTo(dato
						.getVALCOMPRGSTRODR().trim()));
				if (null != direccion.getfExpedicion()) {
					if ((!direccion.getfExpedicion().before(fechaInvalida1) && !fechaInvalida1
							.before(direccion.getfExpedicion()))
							|| (!direccion.getfExpedicion().before(
									fechaInvalida2) && !fechaInvalida2
									.before(direccion.getfExpedicion()))) {
						direccion.setfExpedicion(null);
					}
				}

			}
		}
		DomicilioTipoBean domicilioAux = new DomicilioTipoBean();
		domicilioAux.setCodArGeo(respuesta.getResponseBansefi()
				.getOTRDRRGSTROCNSTRNO().getTRDRRGSTROCNSEVTZ().getCODARGEO());
		domicilioAux.setNumArGeo(respuesta.getResponseBansefi()
				.getOTRDRRGSTROCNSTRNO().getTRDRRGSTROCNSEVTZ().getNUMARGEO());

		direccion.setLocalidad(this.consultaLocalidadBackend.ejecutarTRN(
				domicilioAux).getDatosFinalesCatGeo());

		direccion.getLocalidad().setCodArGeo(domicilioAux.getCodArGeo());
		direccion.getLocalidad().setNumArGeo(
				String.valueOf(domicilioAux.getNumArGeo()));
		return direccion;
	}

}
