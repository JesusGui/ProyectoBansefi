package mx.babel.bansefi.banksystem.personas.wrappers;

import mx.babel.bansefi.banksystem.personas.beans.BalanceBean;
import mx.babel.bansefi.banksystem.personas.beans.RegistroBalanceBean;
import mx.babel.bansefi.banksystem.personas.webservices.consultabalance.ResponseBansefi.OTRCONSBLNCETRNO.TRCONSBLNCEEVTZ.DCBLLINEAV;
import mx.babel.bansefi.banksystem.personas.webservices.listabalances.ResponseBansefi.OTRDCBL1LSCNSTRNO.TRDCBL1LSCNSEVTZ.TRDCBL1LSCNSEVTV;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean encargado de realizar mapeos entre respuestas de webservices hacia beans relacionados a balances.
 * 
 * @author babel
 *
 */
@Component
public class BalanceWrapper {
	
	/**
	 * Asignación de bean de mapeo
	 */
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Función responsable del mapeo entre respuesta de webservice TR_CONS_BLNCE_TRN 
	 * y RegistroBalanceBean
	 * 
	 * @param balance objeto respuesta del webservice TR_CONS_BLNCE_TRN
	 * @return bean RegistroBalanceBean
	 */
	public RegistroBalanceBean wrappBeanBalance(DCBLLINEAV balance){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(balance, RegistroBalanceBean.class, "consultaBalance");
	}
	
	/**
	 * Función responsable del mapeo entre respuesta de webservice TR_DC_BL_1_LS_CNS_TRN
	 * 
	 * @param lista balances espuesta del webservice TR_DC_BL_1_LS_CNS_TRN
	 * @return bean BalanceBean
	 */
	public BalanceBean wrappBeanListaBalance(TRDCBL1LSCNSEVTV listaBalances){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(listaBalances, BalanceBean.class,"listaBalances");
	}
	
	/**
	 * Función responsable de  mapeo entre RegistroBalanceBean y TR_ALTA_BLNCE_TRN
	 * 
	 * @param balance respuesta - RegistroBalanceBean
	 * @return balance objeto entrada del webservice TR_ALTA_BLNCE_TRN
	 */
	public mx.babel.bansefi.banksystem.personas.webservices.altabalance.Ejecutar.ITRALTABLNCETRNI.TRALTABLNCEEVTY.DCBLLINEAV 
		wrappBeanAltaBalance(RegistroBalanceBean balance){
		
		Mapper mapper = dozerBeanMapper;
		return mapper.map(balance, 
		        mx.babel.bansefi.banksystem.personas.webservices.altabalance.Ejecutar.ITRALTABLNCETRNI.TRALTABLNCEEVTY.DCBLLINEAV.class, "altaBalance");
	}
	
	/**
	 * Función responsable de mapeo entre RegistroBalanceBean y TR_MODI_BLNCE_TRN
	 * 
	 * @param balance respuesta - RegistroBalanceBean
	 * @return balance entrada del webservice TR_MODI_BLNCE_TRN
	 */
	public mx.babel.bansefi.banksystem.personas.webservices.modificacionbalance.Ejecutar.ITRMODIBLNCETRNI.TRMODIBLNCEEVTY.DCBLLINEAV 
		wrappBeanModifBalance(RegistroBalanceBean balance){
		
		Mapper mapper = dozerBeanMapper;
		return mapper.map(balance, 
				mx.babel.bansefi.banksystem.personas.webservices.modificacionbalance.Ejecutar
				.ITRMODIBLNCETRNI.TRMODIBLNCEEVTY.DCBLLINEAV .class,"modificarBalance");
	}
}
