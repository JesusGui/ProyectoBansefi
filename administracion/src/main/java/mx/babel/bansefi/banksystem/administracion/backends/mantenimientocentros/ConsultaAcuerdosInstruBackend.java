package mx.babel.bansefi.banksystem.administracion.backends.mantenimientocentros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.beans.centro.AcuerdoInstrumentalBean;
import mx.babel.bansefi.banksystem.administracion.webservices.consultaacuerdosinst.ConsultaAcuerdosInstrumentalesServicio;
import mx.babel.bansefi.banksystem.administracion.webservices.consultaacuerdosinst.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.consultaacuerdosinst.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.webservices.consultaacuerdosinst.ResponseBansefi.OTRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT.UOACINSTLAV;
import mx.babel.bansefi.banksystem.administracion.webservices.consultaacuerdosinst.ResponseBansefi.OTRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT.UOACINSTLAV.UOACINSTLGV;
import mx.babel.bansefi.banksystem.administracion.wrappers.VentanaCuentasInstruWrapper;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase BackEnd para ejecutar el servicio de consulta de cuentas
 * instrumentales.
 * 
 * @author alejandro.pineda
 * 
 */
@Component
public class ConsultaAcuerdosInstruBackend extends BackEndBean implements Serializable {

	private static final long serialVersionUID = 1068395230863202685L;

	@Autowired
	private VentanaCuentasInstruWrapper ventanaWrapperService;

	@Autowired
	private ServicioWebUtils servicioWebUtils;

	/**
	 * Método para ejecutar el servicio de consulta de cuentas instrumentales.
	 * 
	 * @param codigoCentro
	 *            Código del centro a consultar
	 * @return Lista de cuentas instrumentales.
	 */
	public List<AcuerdoInstrumentalBean> ejecutarTRN(String codigoCentro){
		
		Ejecutar.ITRCONSACINSTOFICTRN contextoEntrada = new Ejecutar.ITRCONSACINSTOFICTRN();
		contextoEntrada.setOCCURSNR(1);
		
		Ejecutar.ITRCONSACINSTOFICTRN.STDTRNIPARMV contextoComun = new Ejecutar.ITRCONSACINSTOFICTRN.STDTRNIPARMV();

		contextoComun.setIDINTERNOTERMTN(super.getTerminal());
		contextoComun.setIDEMPLAUT("");
		contextoComun.setNUMSEC(0);
		contextoComun.setCODTX(CodTxConstants.COD_TX_TR_CONS_AC_INST_OFIC_TRN);
		contextoComun.setCODTXDI("");		

		Ejecutar.ITRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT contextoEntradaCampos = new Ejecutar.ITRCONSACINSTOFICTRN.TRCONSACINSTOFICEVT();
		contextoEntradaCampos.setCODNRBEEN(super.getEntidad());
		contextoEntradaCampos.setCODINTERNOUO(codigoCentro);
		
		contextoEntrada.setSTDTRNIPARMV(contextoComun);
		contextoEntrada.getTRCONSACINSTOFICEVT().add(contextoEntradaCampos);

		EjecutarResult respuesta = null;

		respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
				ConsultaAcuerdosInstrumentalesServicio.class, contextoEntrada);

		super.verificaRespuesta(respuesta);

		List<AcuerdoInstrumentalBean> cuentas = new ArrayList<>(0);
		
		for (UOACINSTLAV salida : respuesta.getResponseBansefi().getOTRCONSACINSTOFICTRN()
				.getTRCONSACINSTOFICEVT().getUOACINSTLAV()) {
			for (UOACINSTLGV resultadoSalida : salida.getUOACINSTLGV()) {
				if (resultadoSalida.getNUMSECAC() != 0) {
					cuentas.add(ventanaWrapperService
							.wrappBeanConsultaCuenta(resultadoSalida));
				}
			}
		}

		return cuentas;
	}
}
