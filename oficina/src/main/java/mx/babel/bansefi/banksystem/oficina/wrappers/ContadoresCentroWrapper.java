package mx.babel.bansefi.banksystem.oficina.wrappers;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.oficina.beans.ContadoresCentroBean;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorescentro.Ejecutar.ITROBTECONTTRNI.TRCONSUOTNCONTEVTY;
import mx.babel.bansefi.banksystem.oficina.webservices.contadorescentro.ResponseBansefi.OTROBTECONTTRNO.TRCONSUOTNCONTEVTZ;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase en cargarda de realizar los mapeos entre la clase ContadoresCentroServicio
 * y ContadoresCentroBean
 * @author manuel.nieto
 *
 */
@Component
public class ContadoresCentroWrapper implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = -3403717457352118020L;
		@Autowired
		DozerBeanMapper dozerBeanMapper;
		
		/**
		 * Función Wrapp encargada de convertir la clase ContadoresCentroBean en la clase 
		 * propia de la TRN para realizar la petición.
		 * @param ejecutarTRN
		 * @return
		 */
		public TRCONSUOTNCONTEVTY wrappConsultaContadoresCentro(ContadoresCentroBean ejecutarTRN){
			Mapper mapper = dozerBeanMapper;
			return mapper.map(ejecutarTRN, TRCONSUOTNCONTEVTY.class, "consultaContadoresCentro");
		}
		
		/**
		 * Función Wrapp encargada de convertir la clase de respuesta de la TRN a el bean ContadoresCentroBean
		 * @param respuestaTRN
		 * @return
		 */
		public ContadoresCentroBean wrappRespuestaContadoresCentro(TRCONSUOTNCONTEVTZ respuestaTRN){
			Mapper mapper = dozerBeanMapper;
			return mapper.map(respuestaTRN, ContadoresCentroBean.class, "respuestaContadoresCentro");
		}
}
