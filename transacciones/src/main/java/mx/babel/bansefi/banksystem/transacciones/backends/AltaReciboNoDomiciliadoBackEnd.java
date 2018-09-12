package mx.babel.bansefi.banksystem.transacciones.backends;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.ReciboBean;
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;
import mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.AltaReciboNoDomiciliadoServicio;
import mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.Ejecutar;
import mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.EjecutarResult;
import mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.ResponseBansefi;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para el cobro/alta de recibos no domiciliados
 * @author manuel.nieto
 *
 */
@Component
public class AltaReciboNoDomiciliadoBackEnd extends BackEndBean implements Serializable {

	private static final long serialVersionUID = -2531161506382619954L;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * WebService de alta de recibos no domiciliados
	 * 
	 * @param reciboBean Bean con los datos del recibo no domiciliado a incluir
	 * @return ReciboBean Bean que incluye los datos recuperados en el webservices de alta de recibos no domiciliados
	 */
	public ReciboBean ejecutarTRN(ReciboBean reciboBean){
		Ejecutar contexto = new Ejecutar();
		contexto.setENTIDAD(super.getEntidad());
		if (reciboBean.getNumeroCuenta() !=null){
			contexto.setACUERDO(new BigInteger(reciboBean.getNumeroCuenta()));
		}else{
			contexto.setACUERDO(new BigInteger("0"));
		}
		contexto.setCVESERV(reciboBean.getEmisora());
		contexto.setNUMTELRCBO(reciboBean.getReferencia());
		contexto.setTIPOPGO(reciboBean.getFormaCobro());
		contexto.setIMPORTE(reciboBean.getImporteEntregado());
		contexto.setDOMCTA(new BigInteger("0"));
		
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(AltaReciboNoDomiciliadoServicio.class, contexto.getENTIDAD(),
					contexto.getACUERDO(),contexto.getCVESERV(),contexto.getNUMTELRCBO(),contexto.getIMPORTE(),contexto.getTIPOPGO(),
					contexto.getDOMCTA());

		} catch (NoControlableException e) {
			throw new NoControlableException("Error al invocar servicio web de alta de recibos no domiciliados.", e);
		}
		
		super.verificaRespuestaWS(respuesta);
		
		if (verificaResponseBansefi(respuesta)) {
			return validaAltaReciboNoDom(respuesta.getResponseBansefi(),reciboBean);
		}
		return null;
	}

	/**
	 * Funcion que recibe la respuesta del WS.
	 * 
	 * @param response
	 * @return ContadoresCentroBean
	 */
	private ReciboBean validaAltaReciboNoDom(ResponseBansefi response, ReciboBean recibo){
				
		StringToDateConverter converter = new StringToDateConverter();
		recibo.setFechaOperacion(converter.convertTo(response.getFECHA().trim(), new Date()));
		recibo.setHoraOperacion(converter.convertTo(response.getHORA().trim(), new Date()));
		recibo.setCentro(response.getSUCDESC().trim());
		recibo.setReferencia(response.getREFEREN().trim());
		recibo.setIdTransaccion(response.getIDTRAN().trim());
		recibo.setNumeroOperacion(response.getNUMOPER().trim());
		recibo.setConcepto(response.getCONCEPTO().trim());
		
		//Forma de pago
		if(("1").equals(recibo.getFormaCobro())){
			recibo.setFormaPago("EFECTIVO");
		}else if(("2").equals(recibo.getFormaCobro())){
			recibo.setFormaPago("CARGO A CUENTA " + recibo.getNumeroCuenta());
		}
		
		if(!StringUtils.isBlank(recibo.getConcepto()) && recibo.getConcepto().contains("REF")){
			int index = recibo.getConcepto().indexOf("REF");
			String concepto = recibo.getConcepto().substring(0, index);
			String referencia = recibo.getConcepto().substring(index + 4);
			
			recibo.setConcepto(concepto);
			recibo.setReferencia(referencia);
		}
		
		recibo.setImporteEntregado(new BigDecimal(response.getIMPORTE().trim()));
		recibo.setImporteComision(new BigDecimal(response.getIMPCOMI().trim()));
		recibo.setImporteIva(new BigDecimal(response.getIMPIVA().trim()));
		BigDecimal importeIva = recibo.getImporteIva().add(recibo.getImporteComision().negate());
		recibo.setImporteIva(importeIva);
		recibo.setImporteTotal(new BigDecimal(response.getIMPTOTAL().trim()));
		
		return recibo;
	}
		
	/**
	 * Función que valida que la respuesta del servidor no este vacía.
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
}
