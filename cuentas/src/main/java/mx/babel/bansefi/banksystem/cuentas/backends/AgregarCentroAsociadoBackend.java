package mx.babel.bansefi.banksystem.cuentas.backends;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.AgregarCentroAsociadoBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.agregarcentroasociado.AgregarCentroAsociadoServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.agregarcentroasociado.Ejecutar.ITRMODIFUOACTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.agregarcentroasociado.Ejecutar.ITRMODIFUOACTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.agregarcentroasociado.Ejecutar.ITRMODIFUOACTRNI.TRMODIFUOACEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.agregarcentroasociado.Ejecutar.ITRMODIFUOACTRNI.TRMODIFUOACEVTY.ACACRLCENTROE;
import mx.babel.bansefi.banksystem.cuentas.webservices.agregarcentroasociado.Ejecutar.ITRMODIFUOACTRNI.TRMODIFUOACEVTY.ACACRLCENTROP;
import mx.babel.bansefi.banksystem.cuentas.webservices.agregarcentroasociado.Ejecutar.ITRMODIFUOACTRNI.TRMODIFUOACEVTY.ACCENTROV;
import mx.babel.bansefi.banksystem.cuentas.webservices.agregarcentroasociado.Ejecutar.ITRMODIFUOACTRNI.TRMODIFUOACEVTY.ACFECHAINICIOV;
import mx.babel.bansefi.banksystem.cuentas.webservices.agregarcentroasociado.Ejecutar.ITRMODIFUOACTRNI.TRMODIFUOACEVTY.NUMSUBACV;
import mx.babel.bansefi.banksystem.cuentas.webservices.agregarcentroasociado.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BackEnd para la consulta de conversion cuenta
 * 
 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
 * @category BackEnd
 */

@Component
public class AgregarCentroAsociadoBackend extends BackEndBean {

	private static final long serialVersionUID = -54491901632074024L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

	/**
	 * Ejecutar TRN de agregar centro asociado
	 * 
	 * @return <code>Date</code> con la fecha actual del sistema
	 * @author Natalio Iván Mendoza Sánchez &lt;nmendoza@uanlmexico.mx&gt;
	 */
	public AgregarCentroAsociadoBean ejecutarTRN(int fechaIniciAnterior,String agregarCentro,
			long numCuenta, int fechaInicio, String codId, String centroAnterior){

		AgregarCentroAsociadoBean agregarCentroAsociadoBean = new AgregarCentroAsociadoBean();

		ITRMODIFUOACTRNI contexto = initPeticion(fechaIniciAnterior,agregarCentro, numCuenta,
				fechaInicio, codId, centroAnterior);

		EjecutarResult respuesta = ejecutarWS(contexto);
		super.verificaRespuesta(respuesta);
		agregarCentroAsociadoBean.setCentroAgregado(""
				+ respuesta.getResponseBansefi().getOTRMODIFUOACTRNO()
						.getRTRNCD());
		 agregarCentroAsociadoBean.setCentroAgregado(centroAnterior);
		return agregarCentroAsociadoBean;
	}

	/**
	 * ------Método para inicializar el objeto de petición al servicio web
	 * 
	 * @param idcentro
	 *            id del centro poseedor de los campos, numero de cuenta , feha
	 *            de inicio , fecha de cierre
	 * @return objeto para petición al servicio web
	 */
	public ITRMODIFUOACTRNI initPeticion(int fechaIniciAnterior,String agregarCentro, long numCuenta,
			int fechaInicio, String codId, String centroAnterior) {

		ITRMODIFUOACTRNI contexto = new ITRMODIFUOACTRNI();
		STDTRNIPARMV cuerpoContexto = new STDTRNIPARMV();
		TRMODIFUOACEVTY datosEntrada = new TRMODIFUOACEVTY();

		NUMSUBACV ultimoCentro = new NUMSUBACV();
		ultimoCentro.setULTNUMSUBAC(0);

		ACACRLCENTROP cuentaAnti = new ACACRLCENTROP();
		cuentaAnti.setCODNRBEEN(getEntidad());
		cuentaAnti.setNUMSECAC(numCuenta);
		cuentaAnti.setCODRLACUO(codId);
		cuentaAnti.setFECHAINICIO(fechaIniciAnterior);

		ACACRLCENTROE cuentaNew = new ACACRLCENTROE();
		cuentaNew.setCODNRBEEN(getEntidad());
		cuentaNew.setNUMSECAC(numCuenta);
		cuentaNew.setCODRLACUO(codId);
		cuentaNew.setCODINTERNOUO(agregarCentro);
		cuentaNew.setFECHAINICIO(fechaInicio);
		cuentaNew.setFECHACRRE(99991231);

		ACCENTROV std = new ACCENTROV();
		std.setCODINTERNOUO(centroAnterior);

		ACFECHAINICIOV fechadeInicio = new ACFECHAINICIOV();
		fechadeInicio.setFECHAINICIO(fechaIniciAnterior);

		cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
		cuerpoContexto.setCODTX(CodTxConstants.TR_MODIF_UO_AC_TRN);

		datosEntrada.setACACRLCENTROP(cuentaAnti);
		datosEntrada.setACCENTROV(std);
		datosEntrada.setACACRLCENTROE(cuentaNew);
		datosEntrada.setNUMSUBACV(ultimoCentro);
		datosEntrada.setACFECHAINICIOV(fechadeInicio);

		contexto.setTRMODIFUOACEVTY(datosEntrada);
		contexto.setSTDTRNIPARMV(cuerpoContexto);

		super.initialize(contexto);

		return contexto;

	}

	/**
	 * -----Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto
	 *            Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	public EjecutarResult ejecutarWS(ITRMODIFUOACTRNI contexto) {

		EjecutarResult respuesta = null;

		try {
			respuesta = (EjecutarResult) this.servicioWebUtils.ejecutarWS(
					AgregarCentroAsociadoServicio.class, contexto);
		} catch (NoControlableException ex) {
			throw new NoControlableException(
			"Error al invocar el servicio de consulta de Centros Asociados a Emitir. ",
			ex);
		}

		return respuesta;
	}

}
