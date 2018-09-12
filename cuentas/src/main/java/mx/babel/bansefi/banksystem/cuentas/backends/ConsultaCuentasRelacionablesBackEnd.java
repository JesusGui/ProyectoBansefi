package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacuentasrelacionables.ConsultaCuentasRelacionablesServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacuentasrelacionables.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacuentasrelacionables.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacuentasrelacionables.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultacuentasrelacionables.ResponseBansefi.OTRCONSUSELECTIV2ACTR.TRCONSUSELECTIV2ACEVT;
import mx.babel.bansefi.banksystem.cuentas.wrappers.RelacionesCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back End para consulta de las cuentas que se pueden relacionar para un tipo de producto
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaCuentasRelacionablesBackEnd extends BackEndBean{

	private static final long serialVersionUID = -9114851612374019109L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	RelacionesCuentaWrapper relacionesCuentaWrapper;
	
	/**
	 * Mètodo para consultar las cuentas relacionables al producto
	 * @param cuentaBean a la cual se le relacionaràn las cuentas
	 * @return lista de cuentas que se pueden relacionar
	 */
	public List<CuentaBean> ejecutarTRN(CuentaBean cuentaBean){
		
		Ejecutar.ITRCONSUSELECTIV2ACTR contexto = initPeticion(); 
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return new ArrayList<CuentaBean>();
            }        
        }
		return getCuentas(respuesta.getResponseBansefi());	
	}
	
	/**
	 * Función encargada de construir una lista de cuentas relacionables a través de la
	 * respuesta del servicio web.
	 * 
	 * @param response respuesta del servicio web
	 * @return lista con los detalles de cuentas relacionables
	 */
	private List<CuentaBean> getCuentas(ResponseBansefi response){
		List<CuentaBean> relacionables = new ArrayList<CuentaBean>();		
		if(verificaRespuestaRelaciones(response)){
			for (TRCONSUSELECTIV2ACEVT cuenta : response.getOTRCONSUSELECTIV2ACTR().getTRCONSUSELECTIV2ACEVT()) {
				relacionables.add(relacionesCuentaWrapper.wrappBean(cuenta));
			}
		}		
		return relacionables;
	}
	
	/**
	 * Función que inicializa el objeto para realizar la petición al servicio web.
	 * 
	 * @return Objeto a ser enviado al servicio web.
	 */
	private Ejecutar.ITRCONSUSELECTIV2ACTR initPeticion(){
		Ejecutar.ITRCONSUSELECTIV2ACTR contexto = new Ejecutar.ITRCONSUSELECTIV2ACTR();
		Ejecutar.ITRCONSUSELECTIV2ACTR.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRCONSUSELECTIV2ACTR.STDTRNIPARMV();
		Ejecutar.ITRCONSUSELECTIV2ACTR.TRCONSUSELECTIV2ACEVT cuerpoContexto =
				new Ejecutar.ITRCONSUSELECTIV2ACTR.TRCONSUSELECTIV2ACEVT();
		Ejecutar.ITRCONSUSELECTIV2ACTR.TRCONSUSELECTIV2ACEVT.ACACE cuenta =
				new Ejecutar.ITRCONSUSELECTIV2ACTR.TRCONSUSELECTIV2ACEVT.ACACE();
		contexto.setTRCONSUSELECTIV2ACEVT(cuerpoContexto);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		cuerpoContexto.setACACE(cuenta);
		super.initialize(contexto);
		
		//TODO verificar datos utilizado para la consulta
		cuenta.setCODNRBEEN(super.getEntidad());
		cuenta.setCODLINEA("03");
		cuenta.setIDGRPPD("11");
		cuenta.setIDPDV("V001");
		cuenta.setIDTRFAPDV("001");

		contextoEntrada.setCODTX(CodTxConstants.COD_TR_CONSU_SELECTIV2_AC_TRN);
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		
		contexto.setEVENTCD(1);
		contexto.setELEVATORPOSITION(1);
		contexto.setSCROLLABLEOCCURS(50);
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRCONSUSELECTIV2ACTR contexto) 
			throws NoControlableException{
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					ConsultaCuentasRelacionablesServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta "
					+ "cuentas relacionables.", e);
		}
		return respuesta;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de cuentas.
	 * 
	 * @param response Respuesta Bansefi con datos de cuentas
	 * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
	 */
	private Boolean verificaRespuestaRelaciones(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRCONSUSELECTIV2ACTR() != null && 
				response.getOTRCONSUSELECTIV2ACTR().getTRCONSUSELECTIV2ACEVT() != null &&
				!response.getOTRCONSUSELECTIV2ACTR().getTRCONSUSELECTIV2ACEVT().isEmpty()){
			noNulo = true;
		}
		return noNulo;
	}
	
}
