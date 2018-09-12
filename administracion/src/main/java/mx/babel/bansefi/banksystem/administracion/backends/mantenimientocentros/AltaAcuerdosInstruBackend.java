package mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.beans.centro.AcuerdoInstrumentalBean;
import mx.babel.bansefi.banksystem.administracion.webservices.altaacuerdosinst.AltaAcuerdosInstrumentalesServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.altaacuerdosinst.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.altaacuerdosinst.Ejecutar.ITRALTAACINSTOFICTRN;
import mx.babel.bansefi.banksystem.administracion.webservices.altaacuerdosinst.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.wrappers.VentanaCuentasInstruWrapper;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para la ejecución del servicio para el alta de acuerdos
 * instrumentales.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class AltaAcuerdosInstruBackend extends BackEndBean implements Serializable {

	private static final long serialVersionUID = -7439003233343751596L;

	@Autowired
	private VentanaCuentasInstruWrapper ventanaWrapperService;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar el servicio de alta de cuenta instrumental.
	 * 
	 * @param cuenta Bean de Cuenta Instrumental
	 * @return int Codigo de retorno del servicio
	 */
	public int ejecutarWS(AcuerdoInstrumentalBean cuenta){
		Ejecutar.ITRALTAACINSTOFICTRN contextoEntrada = initPeticion(cuenta);

		EjecutarResult respuesta = null;

		respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				AltaAcuerdosInstrumentalesServicio.class, contextoEntrada);
		try{
			super.verificaRespuesta(respuesta);
			if(!"Exito".equals(respuesta.getResponseBansefi().getOTRALTAACINSTOFICTRN().getTRALTAACINSTOFICEVTZ().getUOACINSTLAV().get(0).getUOACINSTLGV().get(0).getSTDCHAR80().trim())){
				throw new ControlableException();
			}
		} catch(ControlableException c) {
			String error = respuesta.getResponseBansefi().getOTRALTAACINSTOFICTRN().getTRALTAACINSTOFICEVTZ().getUOACINSTLAV().get(0).getUOACINSTLGV().get(0).getSTDCHAR80();
			throw new ControlableException(error, error);
		}
		
		
		return respuesta.getResponseBansefi().getOTRALTAACINSTOFICTRN().getRTRNCD();
	}
	
	
	private ITRALTAACINSTOFICTRN initPeticion(AcuerdoInstrumentalBean cuenta){
		ITRALTAACINSTOFICTRN itraltaacinstofictrn = new ITRALTAACINSTOFICTRN();
		super.initialize(itraltaacinstofictrn);
		
		Ejecutar.ITRALTAACINSTOFICTRN.TRALTAACINSTOFICEVTY traltaacinstoficevty = new Ejecutar.ITRALTAACINSTOFICTRN.TRALTAACINSTOFICEVTY();
		itraltaacinstofictrn.getTRALTAACINSTOFICEVTY().add(traltaacinstoficevty);
		itraltaacinstofictrn.getTRALTAACINSTOFICEVTY().get(0).setUOACINSTALTAV(ventanaWrapperService.wrappBeanAltaCuenta(cuenta));
		itraltaacinstofictrn.getTRALTAACINSTOFICEVTY().get(0).getUOACINSTALTAV().setCODNRBEEN(super.getEntidad());
		itraltaacinstofictrn.getTRALTAACINSTOFICEVTY().get(0).getUOACINSTALTAV().setCODCSBOF("");
		
		itraltaacinstofictrn.setOCCURSNR(1);
		
		Ejecutar.ITRALTAACINSTOFICTRN.UOACINSTOKV uoacinstokv = new Ejecutar.ITRALTAACINSTOFICTRN.UOACINSTOKV();
		super.initialize(uoacinstokv);
		itraltaacinstofictrn.getUOACINSTOKV().add(uoacinstokv);
		
		Ejecutar.ITRALTAACINSTOFICTRN.UOACINSTOKV.UOACINSTOKCV uoacinstokcv = new Ejecutar.ITRALTAACINSTOFICTRN.UOACINSTOKV.UOACINSTOKCV();
		super.initialize(uoacinstokcv);
		itraltaacinstofictrn.getUOACINSTOKV().get(0).getUOACINSTOKCV().add(uoacinstokcv);
		
		itraltaacinstofictrn.getACINSTTIPOOPV().setSTDCHAR01("V");
		
		itraltaacinstofictrn.getSTDTRNIPARMV().setCODTX(CodTxConstants.COD_TX_TR_ALTA_AC_INST_OFIC_TRN);
		itraltaacinstofictrn.getSTDTRNIPARMV().setIDINTERNOTERMTN(super.getTerminal());
		
		return itraltaacinstofictrn;
	}
	
}
