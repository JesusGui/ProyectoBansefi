package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.CentroAsociadoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaCentroAsociado.CentroAsociadoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaCentroAsociado.Ejecutar.ITRCONSUTODASOFIACTR;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaCentroAsociado.Ejecutar.ITRCONSUTODASOFIACTR.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaCentroAsociado.Ejecutar.ITRCONSUTODASOFIACTR.TRCONSUTODASOFIACEVT;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaCentroAsociado.Ejecutar.ITRCONSUTODASOFIACTR.TRCONSUTODASOFIACEVT.ACACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaCentroAsociado.Ejecutar.ITRCONSUTODASOFIACTR.TRCONSUTODASOFIACEVT.ACLINEAGRUPOV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaCentroAsociado.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaCentroAsociado.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaCentroAsociado.ResponseBansefi.OTRCONSUTODASOFIACTR;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaCentroAsociadoWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BackEnd para la consulta de Centro Asociado
 * 
 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
 * @category BackEnd
 */
@Component
public class ConsultaCentroAsociadoBackend extends BackEndBean {

	private static final long serialVersionUID = 758469545533692071L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	@Autowired
	ConsultaCentroAsociadoWrapper consultaCentroAsociadoWrapper;

	/**
	 * Método para consultar los campos de un centro
	 * 
	 * @param idCentroAsociado
	 *            id del centro poseedor de los campos
	 * @return lista de campos asociados a un centro
	 */
	public List<CentroAsociadoBean> ejecutarTRN(long agreementNumber,String codeLine, String idGrp) {

		List<CentroAsociadoBean> centros = new ArrayList<CentroAsociadoBean>();

		if (agreementNumber != 0) {
			ITRCONSUTODASOFIACTR contexto = initPeticion(agreementNumber,codeLine, idGrp);
			EjecutarResult respuesta = ejecutarWS(contexto);
			
			try{
				super.verificaRespuesta(respuesta);
			}catch(ControlableException ce){
				if(ce.getRtncod()!=RETURN_COD_SIN_DATOS){
					throw ce;
				}else{
					return centros;
				}
			}						
			centros = getCentros(respuesta.getResponseBansefi());			
		}

		return centros;
	}

	/**
	 * Método para construir una lista de campos de un centro
	 * 
	 * @param response
	 *            respuesta de servicio web
	 * @return lista de campos asociados a un centro
	 */
	public List<CentroAsociadoBean> getCentros(ResponseBansefi response){
		List<CentroAsociadoBean> result = new ArrayList<CentroAsociadoBean>();		
		if (verificaRespuestaCampos(response)) {
			List<OTRCONSUTODASOFIACTR.TRCONSUTODASOFIACEVT> centrosResult = response
					.getOTRCONSUTODASOFIACTR().getTRCONSUTODASOFIACEVT();

			for (OTRCONSUTODASOFIACTR.TRCONSUTODASOFIACEVT bean : centrosResult) {
				String been = bean.getCODRLACUO().trim();
				if (!been.equals("")) {
					CentroAsociadoBean centro = consultaCentroAsociadoWrapper
							.wrappBean(bean);
					result.add(centro);
				}
			}
		}

		return result;
	}

	/**
	 * ------------------------Método para inicializar el objeto de petición al
	 * servicio web
	 * 
	 * @param idcentro
	 *            id del centro poseedor de los campos
	 * @return objeto para petición al servicio web
	 */
	public ITRCONSUTODASOFIACTR initPeticion(long agreementNumber,
			String codeLine, String idGrp) {

		ITRCONSUTODASOFIACTR contexto = new ITRCONSUTODASOFIACTR();
		STDTRNIPARMV cuerpoContexto = new STDTRNIPARMV();

		TRCONSUTODASOFIACEVT datosEntrada = new TRCONSUTODASOFIACEVT();

		ACACP acacp = new ACACP();
		ACLINEAGRUPOV acLine = new ACLINEAGRUPOV();

		acacp.setCODNRBEEN(super.getEntidad());
		acacp.setNUMSECAC(agreementNumber);

		//acLine.setCODLINEA(codeLine);
		//acLine.setIDGRPPD(idGrp);

		datosEntrada.setACACP(acacp);
		datosEntrada.setACLINEAGRUPOV(acLine);

		cuerpoContexto.setCODTX(CodTxConstants.TR_CONSU_TODAS_OFI_AC_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setSCROLLABLEOCCURS(50);

		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRCONSUTODASOFIACEVT(datosEntrada);

		super.initialize(contexto);

		return contexto;

	}

	/**
	 * ------------------------Función para invocar al servicio web y obtener su
	 * respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	public EjecutarResult ejecutarWS(ITRCONSUTODASOFIACTR contexto) {

		EjecutarResult respuesta = null;

		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					CentroAsociadoServicio.class, contexto);
		} catch (NoControlableException ex) {
			throw new NoControlableException(
					"Error al invocar el servicio de consulta de Centros Asociados a Emitir. ",
					ex);
		}

		return respuesta;
	}

	/**
	 * ------------Función que valida que la respuesta Bansefi contenga un
	 * objeto con los datos de cuentas.
	 * 
	 * @param response
	 *            Respuesta Bansefi con datos de cuentas
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de
	 *         alta
	 */
	private Boolean verificaRespuestaCampos(ResponseBansefi response) {
		Boolean noNulo = false;
		if (response != null
				&& response.getOTRCONSUTODASOFIACTR() != null
				&& response.getOTRCONSUTODASOFIACTR().getTRCONSUTODASOFIACEVT() != null
				&& !response.getOTRCONSUTODASOFIACTR()
						.getTRCONSUTODASOFIACEVT().isEmpty()) {
			noNulo = true;
		}
		return noNulo;
	}
	
}
