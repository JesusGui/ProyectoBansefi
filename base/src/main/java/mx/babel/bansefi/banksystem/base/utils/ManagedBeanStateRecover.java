package mx.babel.bansefi.banksystem.base.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * Utilidad encargada de enviar y recibir atributos de un managed bean, para poder reconstruir una vista a partir de un punto determinado.
 * @author mario.montesdeoca
 *
 */
@Component
public class ManagedBeanStateRecover {
	
	/**
	 * Mètodo que reconstruye un managed bean a partir de una lista de objetos.
	 * 
	 * @param controller ManagedBean a reconstruir
	 * @param lista Mapa de objetos con su mètodo de acceso.
	 */
	public void recuperaController(Object controller) { 
		Map<?,?> lista = (Map<?,?>) this.obtieneFlash().get(ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash());
	    if(controller != null && lista != null){
	    	for (Method metodo : ReflectionUtils.getAllDeclaredMethods(controller.getClass())) {
	    		if(Modifier.isPublic(metodo.getModifiers()) && lista.containsKey(metodo.getName())){
	    			try {
	    				metodo.invoke(controller, lista.get(metodo.getName()));
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | SecurityException e) {
					}
	    		}				
			}
	    }
	}
	
	/**
	 * Mètodo que obtinen los atributos con un mètodo get y set, de un managed bean, y los adiciona en un mapa.
	 * 
	 * @param controller Managed Bean del cùal se obtendran los atributos
	 * @return mapa de atributo con su mètodo de acceso.
	 */
	public void enviaController(Object controller, NavegacionEnum destino){
		Map<String,Object> lista = new HashMap<String,Object>();
		List<String> metodos = new ArrayList<String>();
		if(controller != null){
			try{
				metodos = obtenerListaMetodos(controller);
			}catch(IllegalAccessException | NoSuchFieldException e){
			}
			for (Method metodo : ReflectionUtils.getAllDeclaredMethods(controller.getClass())) {
				if(Modifier.isPublic(metodo.getModifiers()) && (metodo.getName().startsWith("get") || metodo.getName().startsWith("is")) 
						&& !metodos.contains(metodo.getName())){
					String setter = "";
					String iniciales = "";
					if(metodo.getName().startsWith("get")){						
						setter = metodo.getName().replaceFirst("get", "set");
					} else if(metodo.getName().startsWith("is")){
						setter = metodo.getName().replaceFirst("is", "set");
					}		
					try{
						Object object = metodo.invoke(controller);
						lista.put(setter, object);
					}catch(IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | SecurityException e){
					}
				}
			}
		}
		this.obtieneFlash().put(ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(), lista);
		this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(),destino);
		this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),true);
	}
	
	public void enviaControllerMap(Object lista, NavegacionEnum destino){
	    this.obtieneFlash().put(ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(), lista);
        this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash(),destino);
        this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),true);
	    
	}
	
	public NavegacionEnum getDestino(){
		return (NavegacionEnum) this.obtieneFlash().get(ParametrosFlashEnum.NAVEGACION_BUSQUEDA.getParamFlash());
	}
	
	public Object getController(){
		return this.obtieneFlash().get(ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash());
	}
	
	public void finNavegacion(Object destinoController){
		obtieneFlash().put(ParametrosFlashEnum.CONTROLLER_DESTINO.getParamFlash(),destinoController);
		obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_MANAGED_BEAN.getParamFlash(),false);
	}
	
	private List<String> obtenerListaMetodos(Object controller) throws IllegalAccessException, NoSuchFieldException{
		Field listaMetodos = controller.getClass().getDeclaredField("METODOS");
		if(listaMetodos != null){
			return Arrays.asList((String [])listaMetodos.get(null));
		}
		return new ArrayList<String>();
	}
	
	 /**
     * Se encarga de obtener el flash.
     * 
     * @return Flash con los datos de la pagina
     */
    public Flash obtieneFlash() {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash();
    }
}
