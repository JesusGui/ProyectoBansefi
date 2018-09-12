package mx.babel.arq.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EjecutorDeTareas implements Runnable {
	
	private static final Logger LOGGER = LogManager.getLogger(EjecutorDeTareas.class);
	
	private Object proceso;

	@Override
	public void run() {
        LOGGER.debug("Ejecuta tarea indicada...");
        ILoaderTask task = null;
        try {
        	task = (ILoaderTask)this.proceso;
        	task.loadTask();
		} catch (Exception ignore) {
			LOGGER.error("Error en la ejecución programada del proceso: "+ignore.getMessage());
		} 
	}


	public void setProceso(Object procesoAEjecutar) {
		this.proceso = procesoAEjecutar;
	}

}
