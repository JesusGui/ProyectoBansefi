package mx.babel.arq.sesion.contexto.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import mx.babel.arq.sesion.contexto.services.IBSContextoService;
import mx.babel.arq.sesion.contexto.services.impl.BSContextoService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase escuchadora encargada de eliminar el contexto de la sesion
 * cuando esta llega a su fin.
 * @author joseluis.pena
 *
 */
public class SesionListener implements HttpSessionListener  {
	
    @Autowired
    IBSContextoService bsContextoService;
    
    public SesionListener() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    	BSContextoService.numSesiones++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    	BSContextoService.numSesiones = (BSContextoService.numSesiones>0) ? BSContextoService.numSesiones-1 : 0;
    }
}
