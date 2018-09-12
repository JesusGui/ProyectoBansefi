package mx.babel.bansefi.banksystem.base.backends.consultaintervenientescuenta;

import java.util.ArrayList;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.ConsultaRelacionPersonasCuentaServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.Ejecutar.ITRCONSULTARPPANTTRN;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.ResponseBansefi.OTRCONSULTARPPANTTRN;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.ResponseBansefi.OTRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.ResponseBansefi.OTRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTZ.RPPERSV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaIntervenientesBackEnd extends BackEndBean{

	private static final long serialVersionUID = 3275190130985469163L;
	@Autowired
	ServicioWebUtils servicioWebUtils;

	public ArrayList<ConsultaIntervenientesRes> consultaIntervenientes(Long numCuenta) throws ControlableException,
			NoControlableException {
		
		if(numCuenta == 0L) {
			 throw new ControlableException(
	                    "No se puede realizar la consulta",
	                    "Alguno de los parametros de entrada es vacio");
		} 
		
		ConsultaIntervenientesReq request = new ConsultaIntervenientesReq();
		ArrayList<ConsultaIntervenientesRes> responseList = new ArrayList<ConsultaIntervenientesRes> ();
		ITRCONSULTARPPANTTRN consultaIntervenientes = request.consultarIntervenientes(numCuenta);
		
		EjecutarResult respuesta = null;
		
		try{
            //Realizamos la llamada al servicio
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaRelacionPersonasCuentaServicio.class,consultaIntervenientes);
        }catch (NullPointerException npe){
            throw new ControlableException(
                    "No se puede realizar la consulta",
                    npe);
        }catch (NumberFormatException nfe){
            throw new ControlableException(
                    "No se puede realizar la consulta",
                    nfe);
        }catch (IllegalArgumentException iae){
            throw new ControlableException(
                "No se puede realizar la consulta",
                iae);
        }
		
		if (respuesta !=null) {

			ResponseBansefi responseBansefi = respuesta.getResponseBansefi();
			
			OTRCONSULTARPPANTTRN consultarppanttrno = responseBansefi.getOTRCONSULTARPPANTTRN();
			TRCONSULTARPPANTEVTZ trconsultarppantevtz = consultarppanttrno.getTRCONSULTARPPANTEVTZ();
			java.util.List<RPPERSV> rppersv = trconsultarppantevtz.getRPPERSV();
			for (RPPERSV persona : rppersv) {
				ConsultaIntervenientesRes res = new ConsultaIntervenientesRes();
				res.setNumeroIdentificacion(persona.getIDEXTPE());
				res.setTipoIdentificacion(persona.getCODIDEXTPERS());
				persona.getIDEXTPE();
				responseList.add(res);
			}
        }else{
            throw new NoControlableException(
                    "No hay respuesta al servicio",
                    "La respuesta es null");
        }
		return responseList;
	}


}
