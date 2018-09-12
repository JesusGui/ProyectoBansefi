package mx.babel.bansefi.banksystem.oficina.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.oficina.beans.CuadrePuestoBean;
import mx.babel.bansefi.banksystem.oficina.webservices.consultacuadrepuesto.ConsultaCuadrePuestoServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.consultacuadrepuesto.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.consultacuadrepuesto.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.consultacuadrepuesto.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para consultar los cuadres de los puestos en un centro
 * @author mario.montesdeoca	
 *
 */
@Component
public class ConsultaCuadrePuestosBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = -400178975538896980L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
    /**
	 * Método que consulta los cuadres de los puestos en un centro y los devuelve en una lista
	 * @return Lista de cuadres en puestos
	 */
	public List<CuadrePuestoBean> ejecutarTRN(List<String> puestos){
		List<CuadrePuestoBean> cuadres = new ArrayList<CuadrePuestoBean>();
		
		Ejecutar.ITRTNCONTHOST2CNSTR contexto = initPeticion(puestos);
			
		EjecutarResult respuesta = ejecutarWS(contexto);
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return cuadres;
			}
		}
		if(verificaResponseBansefi(respuesta)){
			cuadres.addAll(getCuadres(respuesta.getResponseBansefi()));
		}
		return cuadres;
	}
	
	/**
	 * Método encargado de construir una lista de cuadres de puesta a partir de la respuesta del ws.
	 * @param respuesta Respuesta del ws
	 * @return lista de cuadres
	 */
	private List<CuadrePuestoBean> getCuadres(ResponseBansefi respuesta){
		List<CuadrePuestoBean> cuadres = new ArrayList<CuadrePuestoBean>();		
		if(verificaRespuesta(respuesta)){
			for (ResponseBansefi.OTRTNCONTHOST2CNSTR.TRTNCONTHOST2EVTZ.PCTERMINALV puesto : 
				respuesta.getOTRTNCONTHOST2CNSTR().getTRTNCONTHOST2EVTZ().getPCTERMINALV()) {
				if(!"".equals(puesto.getIDINTERNOTERMTN().trim())){
					CuadrePuestoBean cuadre = new CuadrePuestoBean();
					cuadre.setTerminal(puesto.getIDINTERNOTERMTN());
					cuadre.setTotalArqueo(puesto.getCONTADORESV().get(0).getIMPNOMINAL());
					cuadres.add(cuadre);
				}
			}
		}
		return cuadres;
	}
	
	/**
	 * Método para inicializar el objeto de petición al ws.
	 * @return Objeto de petición al ws.
	 */
	private Ejecutar.ITRTNCONTHOST2CNSTR initPeticion(List<String> puestos){
		Ejecutar.ITRTNCONTHOST2CNSTR contexto = new Ejecutar.ITRTNCONTHOST2CNSTR();
		Ejecutar.ITRTNCONTHOST2CNSTR.STDTRNIPARMV cuerpoContexto =
				new Ejecutar.ITRTNCONTHOST2CNSTR.STDTRNIPARMV();
		Ejecutar.ITRTNCONTHOST2CNSTR.TRTNCONTHOST2EVTY terminal =
				new Ejecutar.ITRTNCONTHOST2CNSTR.TRTNCONTHOST2EVTY();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRTNCONTHOST2EVTY(terminal);
		super.initialize(contexto);
		
		terminal.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		
		for (String puestoCentro : puestos) {
			Ejecutar.ITRTNCONTHOST2CNSTR.TRTNCONTHOST2EVTY.PCTERMINALP puesto =
					new Ejecutar.ITRTNCONTHOST2CNSTR.TRTNCONTHOST2EVTY.PCTERMINALP();
			super.initialize(puesto);
			puesto.setCODINTERNOUOFSC(super.getSucursal());
			puesto.setCODNRBEENFSC(super.getEntidad());
			puesto.setIDINTERNOTERMTN(puestoCentro);
			terminal.getPCTERMINALP().add(puesto);
		}
		
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_TN_CONT_HOST_2_CNS_TRN);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	public EjecutarResult ejecutarWS(Ejecutar.ITRTNCONTHOST2CNSTR contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCuadrePuestoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "de cuadre de puestos.", e);
		}
		
		return respuesta;
	}
	
	/**
	 * Función que valida que la respues del servidor no este vacía. 
	 *  
	 * @param respuesta Objeto respuesta del servicio web
	 * @return <code>true</code> si la respuesta no esta vacía.
	 */
	private Boolean verificaResponseBansefi(EjecutarResult respuesta){
		Boolean noNulo = false;
		if(respuesta != null && respuesta.getResponseBansefi() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de cuentas.
	 * 
	 * @param response Respuesta Bansefi con datos de cuentas
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
	 */
	private Boolean verificaRespuesta(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRTNCONTHOST2CNSTR() != null && 
				response.getOTRTNCONTHOST2CNSTR().getTRTNCONTHOST2EVTZ() != null &&
				response.getOTRTNCONTHOST2CNSTR().getTRTNCONTHOST2EVTZ().getPCTERMINALV() != null &&
				!response.getOTRTNCONTHOST2CNSTR().getTRTNCONTHOST2EVTZ().getPCTERMINALV().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}
}
