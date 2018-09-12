package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.List;

import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.DatoAdicionalBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificaciondatosadicionales.Ejecutar.ITRMODIFAMPLIARACTRN;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificaciondatosadicionales.Ejecutar.ITRMODIFAMPLIARACTRN.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificaciondatosadicionales.Ejecutar.ITRMODIFAMPLIARACTRN.TRMODIFAMPLIARACEVTY;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificaciondatosadicionales.Ejecutar.ITRMODIFAMPLIARACTRN.TRMODIFAMPLIARACEVTY.ACACP;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificaciondatosadicionales.Ejecutar.ITRMODIFAMPLIARACTRN.TRMODIFAMPLIARACEVTY.ACACRLCENTROE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificaciondatosadicionales.Ejecutar.ITRMODIFAMPLIARACTRN.TRMODIFAMPLIARACEVTY.ACINFADICE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificaciondatosadicionales.Ejecutar.ITRMODIFAMPLIARACTRN.TRMODIFAMPLIARACEVTY.ACINFADIOTRPERSE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificaciondatosadicionales.Ejecutar.ITRMODIFAMPLIARACTRN.TRMODIFAMPLIARACEVTY.ACOTRSIDFREXTE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificaciondatosadicionales.Ejecutar.ITRMODIFAMPLIARACTRN.TRMODIFAMPLIARACEVTY.ACOTRSPERSE;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificaciondatosadicionales.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.modificaciondatosadicionales.ModificacionDatosAdicionalesServicio;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaDatosAdicionalesWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt
 * @category Backend
 */
@Component
public class ModificacionDatosAdicionalesBackend extends BackEndBean {
	private static final long serialVersionUID = -101630322054496331L;
	private static final Logger LOGGER = LogManager
			.getLogger(ConsultaDatosAdicionalesBackEnd.class);

	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	ConsultaDatosAdicionalesWrapper wrapper;

	/**
	 * 
	 * @param numeroCuenta
	 * @param data
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void ejecutarTRN(long numeroCuenta, List<DatoAdicionalBean> data) {
		
		ITRMODIFAMPLIARACTRN context = initializePeticion(numeroCuenta);

		addModifications(context.getTRMODIFAMPLIARACEVTY(), data, numeroCuenta);
		checkContext(context.getTRMODIFAMPLIARACEVTY());

		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificacionDatosAdicionalesServicio.class, context);

		super.verificaRespuesta(respuesta);
		
	}

	/**
	 * 
	 * @param contextBody
	 * @param dataSource
	 * @param numSecAc
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public void addModifications(TRMODIFAMPLIARACEVTY contextBody,
			List<DatoAdicionalBean> dataSource, long numSecAc) {

		for (DatoAdicionalBean bean : dataSource) {
			if (!isBeanValid(bean))
				continue;

			try {
				switch (bean.getEntityType()) {
				case "ACINFADICE":
					ACINFADICE acinfadice = wrapper.wrappService(
								ACINFADICE.class, bean, "modificarACINFADICE");
						acinfadice.setNUMSECAC(numSecAc);
						contextBody.getACINFADICE().add(acinfadice);	
					break;
				case "ACINFADIOTRPERSE":
					ACINFADIOTRPERSE acinfadiotrperse = wrapper.wrappService(
							ACINFADIOTRPERSE.class, bean,
							"modificarACINFADIOTRPERSE");
					acinfadiotrperse.setNUMSECAC(numSecAc);
					contextBody.getACINFADIOTRPERSE().add(acinfadiotrperse);
					break;
				case "ACOTRSIDFREXTE":
						ACOTRSIDFREXTE acotrsidfrexte = wrapper.wrappService(
								ACOTRSIDFREXTE.class, bean,
								"modificarACOTRSIDFREXTE");
						acotrsidfrexte.setNUMSECAC(numSecAc);
						contextBody.getACOTRSIDFREXTE().add(acotrsidfrexte);
					break;
				case "ACOTRSPERSE":
					ACOTRSPERSE acotrsperse = wrapper.wrappService(
							ACOTRSPERSE.class, bean, "modificarACOTRSPERSE");
					acotrsperse.setNUMSECAC(numSecAc);
					contextBody.getACOTRSPERSE().add(acotrsperse);
					break;
				case "ACACRLCENTROE":
					ACACRLCENTROE acacrlcentroe = wrapper
							.wrappService(ACACRLCENTROE.class, bean,
									"modificarACACRLCENTROE");
					acacrlcentroe.setNUMSECAC(numSecAc);
					contextBody.getACACRLCENTROE().add(acacrlcentroe);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				LOGGER.error("Ocurrió un error al ejecutar el método addModifications"
						+ e.getMessage());
				continue;
			}
		}
	}

	/**
	 * 
	 * @param numSecAc
	 * @param codNrbeEn
	 * @param idInternoTermTn
	 * @param numSec
	 * @param codTx
	 * @param codTxDi
	 * @return
	 * @author Aramis Oswaldo Gadea Gerónimo &lt;gadea@uanlmexico.mx&gt;
	 */
	public ITRMODIFAMPLIARACTRN initializePeticion(long numSecAc) {
		ITRMODIFAMPLIARACTRN context = new ITRMODIFAMPLIARACTRN();
		TRMODIFAMPLIARACEVTY contextBody = new TRMODIFAMPLIARACEVTY();
		ACACP acacp = new ACACP();
		STDTRNIPARMV std = new STDTRNIPARMV();

		acacp.setCODNRBEEN(super.getEntidad());
		acacp.setNUMSECAC(numSecAc);

		contextBody.setACACP(acacp);

		std.setIDINTERNOTERMTN(super.getTerminal());
		std.setCODTX(CodTxConstants.TR_MODIF_AMPLIAR_AC_TRN);

		context.setSTDTRNIPARMV(std);
		context.setTRMODIFAMPLIARACEVTY(contextBody);

		return context;
	}

	public Boolean isBeanValid(DatoAdicionalBean bean) {
		if (bean.getValor().trim().equals("")) {
			if (bean.getEntityType().trim().equals(""))
				return false;
			else if (bean
					.getValor()
					.trim()
					.equals(bean.getOldValue() == null ? "" : bean
							.getOldValue().trim()))
				return false;
		}

		return true;
	}

	public <T> void fillModifications(List<T> listOfEntities, Class<T> entity,
			int sizeOfEntities) {
		try {
			for (int i = listOfEntities.size(); i < sizeOfEntities; i++) {
				listOfEntities.add(entity.newInstance());
			}
		} catch (Exception e) {
			LOGGER.error(
					"Ocurrió un error al ejecutar el método [fillModifications]\t",
					e.getMessage());
		}
	}

	public void checkContext(TRMODIFAMPLIARACEVTY context) {
		int sizeOfEntities = 15;

		if (context.getACINFADICE().isEmpty()
				|| context.getACINFADICE().size() < sizeOfEntities)
			fillModifications(context.getACINFADICE(), ACINFADICE.class,
					sizeOfEntities);

		if (context.getACINFADIOTRPERSE().isEmpty()
				|| context.getACINFADIOTRPERSE().size() < sizeOfEntities)
			fillModifications(context.getACINFADIOTRPERSE(),
					ACINFADIOTRPERSE.class, sizeOfEntities);

		if (context.getACOTRSIDFREXTE().isEmpty()
				|| context.getACOTRSIDFREXTE().size() < sizeOfEntities)
			fillModifications(context.getACOTRSIDFREXTE(),
					ACOTRSIDFREXTE.class, sizeOfEntities);

		if (context.getACOTRSPERSE().isEmpty()
				|| context.getACOTRSPERSE().size() < sizeOfEntities)
			fillModifications(context.getACOTRSPERSE(), ACOTRSPERSE.class,
					sizeOfEntities);

		if (context.getACACRLCENTROE().isEmpty()
				|| context.getACACRLCENTROE().size() < sizeOfEntities)
			fillModifications(context.getACACRLCENTROE(), ACACRLCENTROE.class,
					sizeOfEntities);
	}
}
