package mx.babel.bansefi.banksystem.transacciones.backends;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.ReciboBean;
import mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.AltaReciboNoDomiciliadoServicio;
import mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.Ejecutar;
import mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.EjecutarResult;
import mx.babel.bansefi.banksystem.transacciones.webservices.altarecibonodomiciliado.ResponseBansefi;

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
			contexto.setACUERDO(new BigInteger(reciboBean.getNumeroCuenta().getBytes()));
		}else{
			contexto.setACUERDO(new BigInteger("0"));
		}
		contexto.setCVESERV(reciboBean.getEmisora());
		contexto.setNUMTELRCBO(reciboBean.getReferencia());
		contexto.setTIPOPGO(reciboBean.getFormaCobro());
		contexto.setIMPORTE(reciboBean.getImporteTotal());
		contexto.setDOMCTA(new BigInteger("0"));
		
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(AltaReciboNoDomiciliadoServicio.class, contexto.getENTIDAD(),
					contexto.getACUERDO(),contexto.getCVESERV(),contexto.getNUMTELRCBO(),contexto.getIMPORTE(),contexto.getTIPOPGO(),
					contexto.getDOMCTA());

		} catch (NoControlableException e) {
			throw new NoControlableException("Error al invocar servicio web de alta de recibos no domiciliados.", e);
		}
		
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
		
		recibo.setFechaOperacion(response.getFECHA().trim());
		recibo.setHoraOperacion(response.getHORA().trim());
		recibo.setCentro(response.getSUCDESC().trim());
		recibo.setReferencia(response.getREFEREN().trim());
		recibo.setIdTransaccion(response.getIDTRAN().trim());
		recibo.setNumeroOperacion(response.getNUMOPER().trim());
		recibo.setFormaPago(response.getLEYENDA().trim());
		recibo.setImporteEntregado(new BigDecimal(response.getIMPORTE().trim()));
		recibo.setImporteComision(new BigDecimal(response.getIMPCOMI().trim()));
		recibo.setImporteIva(new BigDecimal(response.getIMPIVA().trim()));
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
