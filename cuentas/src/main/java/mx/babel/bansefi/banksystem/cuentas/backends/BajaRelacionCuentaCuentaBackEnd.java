package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacioncuentacuenta.BajaRelacionCuentaCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacioncuentacuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacioncuentacuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.bajarelacioncuentacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.RelacionesCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BajaRelacionCuentaCuentaBackEnd extends BackEndBean{

	private static final long serialVersionUID = -3620218229981715903L;

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	RelacionesCuentaWrapper relacionesCuentaWrapper;
	
	public Boolean ejecutarTRN(CuentaRelacionadaBean relacionadaBean, CuentaBean cuentaBean){
		Ejecutar.ITRBAJARXPANTTRNI contexto = initPeticion(relacionadaBean, cuentaBean);
		
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		super.verificaRespuesta(respuesta);
		
		return verificaRespuestaBaja(respuesta.getResponseBansefi());
	}	
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRBAJARXPANTTRNI contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(BajaRelacionCuentaCuentaServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de baja de "
					+ "relación cuenta-cuenta.", e);
		}
		return respuesta;
	}
	
	private Ejecutar.ITRBAJARXPANTTRNI initPeticion(CuentaRelacionadaBean relacionadaBean, CuentaBean cuentaBean){
		Ejecutar.ITRBAJARXPANTTRNI contexto = new Ejecutar.ITRBAJARXPANTTRNI();
		Ejecutar.ITRBAJARXPANTTRNI.TRBAJARXPANTEVTY detalles =
				new Ejecutar.ITRBAJARXPANTTRNI.TRBAJARXPANTEVTY();
		Ejecutar.ITRBAJARXPANTTRNI.STDTRNIPARMV cuerpoContexto = 
				new Ejecutar.ITRBAJARXPANTTRNI.STDTRNIPARMV();
		Ejecutar.ITRBAJARXPANTTRNI.TRBAJARXPANTEVTY.RXACRLACE relacion =
				new Ejecutar.ITRBAJARXPANTTRNI.TRBAJARXPANTEVTY.RXACRLACE();
		Ejecutar.ITRBAJARXPANTTRNI.TRBAJARXPANTEVTY.RXACRLACV tarifa =
				new Ejecutar.ITRBAJARXPANTTRNI.TRBAJARXPANTEVTY.RXACRLACV();
		contexto.setTRBAJARXPANTEVTY(detalles);
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		detalles.setRXACRLACE(relacion);
		detalles.setRXACRLACV(tarifa);
		super.initialize(contexto);
		
		IntegerToDateConverter itdc = new IntegerToDateConverter();
		
		relacion.setCODNRBEEN(super.getEntidad());
		relacion.setNUMSECAC1(cuentaBean.getNumeroCuenta());
		relacion.setCODRLACAC(relacionadaBean.getTipoRelacion());
		relacion.setNUMSECAC2(relacionadaBean.getCuenta().getNumeroCuenta());
		relacion.setNUMORDENRX(relacionadaBean.getNumero());
		if(relacionadaBean.getAlta() != null){
			relacion.setFECHAALTA(itdc.convertFrom(relacionadaBean.getAlta()));
		}
		if(relacionadaBean.getInicioIf() != null){
			relacion.setFECHAALTA(itdc.convertFrom(relacionadaBean.getInicioIf()));
		}
		if(relacionadaBean.getInicio() != null){
			relacion.setFECHAALTA(itdc.convertFrom(relacionadaBean.getInicio()));
		}
		if(relacionadaBean.getCierre() != null){
			relacion.setFECHACRRE(itdc.convertFrom(relacionadaBean.getCierre()));
		}
		relacion.setCODECVACAC(relacionadaBean.getEstadoRelacion());
		
		tarifa.setCODLINEA1(cuentaBean.getCodLinea());
		tarifa.setIDGRPPD1(cuentaBean.getIdGrupoProducto());
		tarifa.setIDPDV1(cuentaBean.getIdProducto());
		
		tarifa.setCODLINEA2(relacionadaBean.getCuenta().getCodLinea());
		tarifa.setIDGRPPD2(relacionadaBean.getCuenta().getIdGrupoProducto());
		tarifa.setIDPDV2(relacionadaBean.getCuenta().getIdProducto());
		
		tarifa.setCODRZNECVACAC(relacionadaBean.getRazonCambio());
		
		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_BAJA_RX_PANT_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		
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
		if(response != null && response.getOTRBAJARXPANTTRNO() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
}
