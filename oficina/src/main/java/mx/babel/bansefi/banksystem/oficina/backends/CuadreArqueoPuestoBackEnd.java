package mx.babel.bansefi.banksystem.oficina.backends;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.oficina.beans.ArqueoPuestoBean;
import mx.babel.bansefi.banksystem.oficina.webservices.cuadreArqueoPuesto.CuadreArqueoPuestoServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.cuadreArqueoPuesto.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.cuadreArqueoPuesto.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CuadreArqueoPuestoBackEnd extends BackEndBean{

	private static final long serialVersionUID = 4213191269292857991L;
	
	@Autowired
    ServicioWebUtils servicioWebUtils;
	
	/**
	 * Mètodo que recibe un bean de arqueo de puesto y realiza su arqueo.
	 * @param puesto bean de arqueo de puesto
	 * @return <code>true</code> si la operaciòn fue realizada con èxito.
	 */
	public Boolean ejecutarTRN(ArqueoPuestoBean puesto) throws ControlableException, NoControlableException{
		if(puesto != null){
			Ejecutar.ITRCUADREHOSTTRNI contexto = initPeticion(puesto);
			
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
			return true;
		}
		return false;
	}
		
	/**
	 * Mètodo que inicializa la peticiòn hacie el ws
	 * @param centro bean de arqueo de centro
	 * @return objeto de peticiòn al ws
	 */
	private Ejecutar.ITRCUADREHOSTTRNI initPeticion(ArqueoPuestoBean puesto){
		Ejecutar.ITRCUADREHOSTTRNI contexto = new Ejecutar.ITRCUADREHOSTTRNI();
		Ejecutar.ITRCUADREHOSTTRNI.STDAPPLPARMV cuerpoContexto =
				new Ejecutar.ITRCUADREHOSTTRNI.STDAPPLPARMV();
		Ejecutar.ITRCUADREHOSTTRNI.TRCUADREHOSTEVTY arqueoPuesto = 
				new Ejecutar.ITRCUADREHOSTTRNI.TRCUADREHOSTEVTY();
		contexto.setSTDAPPLPARMV(cuerpoContexto);
		contexto.setTRCUADREHOSTEVTY(arqueoPuesto);
		super.initialize(contexto);
		
		cuerpoContexto.setCODNRBEEN(super.getEntidad());
		cuerpoContexto.setCODINTERNOUO(super.getSucursal());
		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		cuerpoContexto.setCODISO(ConstantesFuncionales.COD_MONEDA);
		
		IntegerToDateConverter itdc = new IntegerToDateConverter();
		cuerpoContexto.setFECHACTBLE(itdc.convertFrom(super.getFechaSistema()));
		
		arqueoPuesto.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
		arqueoPuesto.getTERMINALV().setIDINTERNOTERMTN(super.getTerminal());
		
		for (ExistenciaDenominacionBean existenciaDenominacionBean : puesto.getListaDenominaciones()) {
			Ejecutar.ITRCUADREHOSTTRNI.TRCUADREHOSTEVTY.TNEXLOCALT denominacionArqueo =
					new Ejecutar.ITRCUADREHOSTTRNI.TRCUADREHOSTEVTY.TNEXLOCALT();
			denominacionArqueo.setIDINTERNOTERMTN(super.getTerminal());
			denominacionArqueo.setCODNRBEEN(super.getEntidad());
			denominacionArqueo.setCODINTERNOUO(super.getSucursal());
			denominacionArqueo.setCODVALORFACIAL(existenciaDenominacionBean.getValorFacial());
			denominacionArqueo.setCODDSTN(existenciaDenominacionBean.getOrigen());
			denominacionArqueo.setIMPNOMINAL(existenciaDenominacionBean.getImporte());
			denominacionArqueo.setUNIDADES(existenciaDenominacionBean.getUnidades().intValue());
			denominacionArqueo.setFECHAPC(itdc.convertFrom(super.getFechaSistema()));
			super.initialize(denominacionArqueo);
			arqueoPuesto.getTNEXLOCALT().add(denominacionArqueo);
		}
		
		for(int i = puesto.getListaDenominaciones().size(); i < 50 ; i++){
			Ejecutar.ITRCUADREHOSTTRNI.TRCUADREHOSTEVTY.TNEXLOCALT denominacionArqueo =
					new Ejecutar.ITRCUADREHOSTTRNI.TRCUADREHOSTEVTY.TNEXLOCALT();
			super.initialize(denominacionArqueo);
			arqueoPuesto.getTNEXLOCALT().add(denominacionArqueo);
		}
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	public EjecutarResult ejecutarWS(Ejecutar.ITRCUADREHOSTTRNI contexto){
		EjecutarResult respuesta = null;
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
					CuadreArqueoPuestoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de cuadre arqueo "
					+ "de puesto.", e);
		}
		return respuesta;
	}
	
}
