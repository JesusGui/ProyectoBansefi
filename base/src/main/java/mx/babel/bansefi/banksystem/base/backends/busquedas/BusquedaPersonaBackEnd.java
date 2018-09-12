package mx.babel.bansefi.banksystem.base.backends.busquedas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonaMoralBusquedaBean;
import mx.babel.bansefi.banksystem.base.beans.busqueda.PersonasClienteBusquedaBean;

@Component
public class BusquedaPersonaBackEnd extends BackEndBean{

	private static final long serialVersionUID = 2555085837363771590L;

	@Autowired
	BusquedaPersonaMoralBackEnd busquedaPersonaMoralBackEnd;
	
	@Autowired
	BusquedaPersonaFisicaBackEnd busquedaPersonaFisicaBackEnd;
	
	/**
	 * Método que ejecuta el servicio de búsqueda de Persona Moral.
	 * 
	 * @param busquedaPersona
	 *            Bean con los datos a buscar
	 * @return String con la ruta de la ficha de cuenta.
	 * @throws ControlableException
	 *             Excepción Controlable del servicio.
	 * @throws NoControlableException
	 *             Excepción No Controlable del servicio.
	 */
	public List<Object> ejecutarWS(Object busquedaPersona)
			throws ControlableException, NoControlableException {
		PersonasClienteBusquedaBean cliente = (PersonasClienteBusquedaBean) busquedaPersona;
		
		PersonaMoralBusquedaBean personaMoral = new PersonaMoralBusquedaBean();
		personaMoral.setIdInterna(cliente.getIdInterna());
		
		List<Object> listaDatos = new ArrayList<>();
		
		listaDatos.addAll(busquedaPersonaFisicaBackEnd.ejecutarWS(cliente));
		listaDatos.addAll(busquedaPersonaMoralBackEnd.ejecutarWS(personaMoral));
		
		return listaDatos;
	}
}
