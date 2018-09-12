package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacionpersonacuenta.BajaRelacionPersonaCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacionpersonacuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacionpersonacuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacionpersonacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.RelacionesCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BajaRelacionPersonaCuentaBackEnd extends BackEndBean{

	private static final long serialVersionUID = -3620218229981715903L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	RelacionesCuentaWrapper relacionesCuentaWrapper;
	
	public Boolean ejecutarTRN(RelacionadoBean relacionadoBean, CuentaBean cuentaBean){
		Ejecutar.ITRBAJARPPANTTRNI contexto = initPeticion(relacionadoBean, cuentaBean);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return false;
			}
		}
		
		return verificaRespuestaBaja(respuesta.getResponseBansefi());
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRBAJARPPANTTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(BajaRelacionPersonaCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de baja de "
					+ "relación persona-cuenta.", e);
		}
		return respuesta;
	}
	
	private Ejecutar.ITRBAJARPPANTTRNI initPeticion(RelacionadoBean relacionadoBean, CuentaBean cuentaBean){
		Ejecutar.ITRBAJARPPANTTRNI contexto = new Ejecutar.ITRBAJARPPANTTRNI();
		Ejecutar.ITRBAJARPPANTTRNI.TRBAJARPPANTEVTY detalles =
				new Ejecutar.ITRBAJARPPANTTRNI.TRBAJARPPANTEVTY();
		Ejecutar.ITRBAJARPPANTTRNI.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRBAJARPPANTTRNI.STDTRNIPARMV();
		Ejecutar.ITRBAJARPPANTTRNI.DATOSACUERDOV acuerdo =
				new Ejecutar.ITRBAJARPPANTTRNI.DATOSACUERDOV();
		Ejecutar.ITRBAJARPPANTTRNI.TRBAJARPPANTEVTY.RPPERSRLACE relacion =
				new Ejecutar.ITRBAJARPPANTTRNI.TRBAJARPPANTEVTY.RPPERSRLACE();
		Ejecutar.ITRBAJARPPANTTRNI.TRBAJARPPANTEVTY.RPPERSRLACV tarifa =
				new Ejecutar.ITRBAJARPPANTTRNI.TRBAJARPPANTEVTY.RPPERSRLACV();
		contexto.setTRBAJARPPANTEVTY(detalles);
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setDATOSACUERDOV(acuerdo);
		detalles.setRPPERSRLACE(relacion);
		detalles.setRPPERSRLACV(tarifa);
		super.initialize(contexto);
		
		relacionadoBean.setFechaInactivo(super.getFechaSistema());
		relacionesCuentaWrapper.wrappBean(contexto, relacionadoBean, cuentaBean);
		
		acuerdo.setCODNRBEEN(super.getEntidad());
		acuerdo.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_BAJA_RP_PANT_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		
		relacion.setCODNRBEEN(super.getEntidad());
		
		return contexto;
	}
	
	/**
	 * Función que valida que la respuesta Bansefi contenga un objeto con los datos
	 * de la persona física.
	 * 
	 * @param response Respuesta Bansefi con datos de la persona moral
	 * @return <code>true</code> si la respuesta Bansefi contiene un cliente de tipo persona moral
	 */
	private Boolean verificaRespuestaBaja(ResponseBansefi response){
		Boolean noNulo = false;
		if(response != null && response.getOTRBAJARPPANTTRNO() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
