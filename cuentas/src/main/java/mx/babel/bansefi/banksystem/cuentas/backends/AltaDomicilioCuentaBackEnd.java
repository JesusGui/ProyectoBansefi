package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.DomicilioCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.altadomiciliocuenta.AltaDomicilioCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.altadomiciliocuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.altadomiciliocuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.altadomiciliocuenta.ResponseBansefi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltaDomicilioCuentaBackEnd extends BackEndBean {

	private static final long serialVersionUID = -7124828180027357495L;

	private static final String COD_DIR = "1";

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public Boolean ejecutarTRN(Long numeroCuenta,
			DomicilioCuentaBean domicilioCuentaBean){
		Ejecutar.ITRACCREARACDIRTRNI contexto = initPeticion(numeroCuenta,
				domicilioCuentaBean);

		EjecutarResult respuesta = ejecutarWS(contexto);

		super.verificaRespuesta(respuesta);
		
		return getRespuesta(respuesta.getResponseBansefi());
	}

	private Boolean getRespuesta(ResponseBansefi respuesta){
		Boolean correcta = false;		
		if (respuesta.getOTRACCREARACDIRTRNO() != null) {
			correcta = true;
		}
		return correcta;
	}

	private Ejecutar.ITRACCREARACDIRTRNI initPeticion(Long numeroCuenta,
			DomicilioCuentaBean domicilioCuentaBean) {
		Ejecutar.ITRACCREARACDIRTRNI contexto = new Ejecutar.ITRACCREARACDIRTRNI();
		Ejecutar.ITRACCREARACDIRTRNI.STDTRNIPARMV cuerpoContexto = new Ejecutar.ITRACCREARACDIRTRNI.STDTRNIPARMV();
		Ejecutar.ITRACCREARACDIRTRNI.TRACCREARACDIREVTY objetoPeticion = new Ejecutar.ITRACCREARACDIRTRNI.TRACCREARACDIREVTY();

		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRACCREARACDIREVTY(objetoPeticion);
		super.initialize(contexto);

		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_AC_CREAR_AC_DIR_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());

		IntegerToDateConverter itdc = new IntegerToDateConverter();
		Ejecutar.ITRACCREARACDIRTRNI.TRACCREARACDIREVTY.ACACRLDIRPERE cuenta = new Ejecutar.ITRACCREARACDIRTRNI.TRACCREARACDIREVTY.ACACRLDIRPERE();
		cuenta.setCODNRBEEN(super.getEntidad());
		cuenta.setNUMSECAC(numeroCuenta);
		cuenta.setIDINTERNOPE(domicilioCuentaBean.getIdPersona());
		cuenta.setNUMDIR(domicilioCuentaBean.getNumeroDireccion());
		cuenta.setFECHAINICIO(itdc.convertFrom(super.getFechaSistema()));
		if(domicilioCuentaBean.getEstadoLista().equals(EstadoListadosEnum.NUEVO)){
			cuenta.setCODPERSRLDIR(domicilioCuentaBean.getTipoDomicilio().get(0)
					.getClave());
		}else{
			cuenta.setCODPERSRLDIR(domicilioCuentaBean.getCodPersDir());
		}
		
		if (domicilioCuentaBean.getCatalogoCentro() != null) {
			cuenta.setCODINTERNOUO(domicilioCuentaBean.getCatalogoCentro()
					.getClaveFila());
			cuenta.setCODDIR(COD_DIR);
			super.initialize(cuenta);
			objetoPeticion.getACACRLDIRPERE().add(cuenta);
		}

		return contexto;
	}

	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRACCREARACDIRTRNI peticion) throws NoControlableException, ControlableException {
		EjecutarResult respuesta = null;
		try {
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					AltaDomicilioCuentaServicio.class, peticion);
		} catch (NoControlableException e) {
			throw new NoControlableException(
					"Error al invocar servicio web de consulta "
							+ "domicilios de cuenta.", e);
		}
		return respuesta;
	}
}
