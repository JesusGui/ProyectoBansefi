package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.DomicilioCuentaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificadomiciliocuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificadomiciliocuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificadomiciliocuenta.ModificaDomicilioCuentaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaDomicilioCuentaBackEnd extends BackEndBean {

	private static final long serialVersionUID = -7124828180027357495L;

	private static final String COD_DIR = "1";

	@Autowired
	ServicioWebUtils servicioWebUtils;

	public Boolean ejecutarTRN(Long numeroCuenta, DomicilioCuentaBean domicilio){
		Ejecutar.ITRMODIFDIRACTRNI contexto = initPeticion(numeroCuenta,domicilio);

		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}else{
				return true;
			}
		}

		return true;
	}	

	private Ejecutar.ITRMODIFDIRACTRNI initPeticion(Long numeroCuenta,
			DomicilioCuentaBean domicilio) {
		Ejecutar.ITRMODIFDIRACTRNI contexto = new Ejecutar.ITRMODIFDIRACTRNI();
		Ejecutar.ITRMODIFDIRACTRNI.STDTRNIPARMV cuerpoContexto = new Ejecutar.ITRMODIFDIRACTRNI.STDTRNIPARMV();
		Ejecutar.ITRMODIFDIRACTRNI.TRMODIFDIRACEVTY objetoPeticion = new Ejecutar.ITRMODIFDIRACTRNI.TRMODIFDIRACEVTY();
		Ejecutar.ITRMODIFDIRACTRNI.TRMODIFDIRACEVTY.ACACRLDIRPERE cuenta = new Ejecutar.ITRMODIFDIRACTRNI.TRMODIFDIRACEVTY.ACACRLDIRPERE();
		contexto.setSTDTRNIPARMV(cuerpoContexto);
		contexto.setTRMODIFDIRACEVTY(objetoPeticion);
		objetoPeticion.setACACRLDIRPERE(cuenta);
		super.initialize(contexto);

		cuerpoContexto.setCODTX(CodTxConstants.COD_TX_TR_MODIF_DIR_AC_TRN);
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());

		cuenta.setCODNRBEEN(super.getEntidad());
		cuenta.setNUMSECAC(numeroCuenta);
		cuenta.setIDINTERNOPE(domicilio.getIdPersona());
		cuenta.setNUMDIR(domicilio.getNumeroDireccion());
		if (domicilio.getCatalogoCentro() != null) {
			cuenta.setCODINTERNOUO(domicilio.getCatalogoCentro().getClaveFila());
		}
		cuenta.setCODPERSRLDIR(domicilio.getCodPersDir());
		cuenta.setCODDIR(COD_DIR);
		IntegerToDateConverter itdc = new IntegerToDateConverter();
		cuenta.setFECHAINICIO(itdc.convertFrom(domicilio.getFechaInicio()));
		if (domicilio.getFechaCierre() != null) {
			cuenta.setFECHACRRE(itdc.convertFrom(domicilio.getFechaCierre()));
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
	private EjecutarResult ejecutarWS(Ejecutar.ITRMODIFDIRACTRNI peticion) throws NoControlableException, ControlableException{
		EjecutarResult respuesta = null;
		respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ModificaDomicilioCuentaServicio.class, peticion);

		return respuesta;
	}	
}
