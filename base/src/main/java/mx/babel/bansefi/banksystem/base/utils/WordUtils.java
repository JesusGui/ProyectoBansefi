package mx.babel.bansefi.banksystem.base.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.PlantillasUtils;
import mx.babel.arq.comun.utils.VariablePlantillaUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Se encarga de generar un Word a partir de una plantilla Word y levantar una nueva ventana mostrandolo
 * @author luis.gcastellano
 *
 */
@Service
public class WordUtils {
	
	@Autowired
	ContextoUtils contexto;

	/**
     * 
     * @param archivoReporte Nombre de jrxml (debe encontrarse en la ruta META-INF/resources/jasper/)
     * @param variables Mapa clave-valor con los parametros que recibe el word     
     */
    public void generaWord(String plantillaWord, Map<String, VariablePlantillaUtils> variables) throws  ControlableException, NoControlableException{
        
		String wordTemp = PlantillasUtils.creaPlantillaWord(plantillaWord, variables, contexto.getEntidad());
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("window.open('/banksystem/WordServlet?nombreWord="+wordTemp+"');");
  
    }
    
    /**
     * 
     * @param archivoReporte Nombre de jrxml (debe encontrarse en la ruta META-INF/resources/jasper/)
     * @param variables Mapa clave-valor con los parametros que recibe el word     
     * @throws IOException 
     */
    public void generaExcel(String plantillaExcel, List<?> listaObjetos) throws  ControlableException, NoControlableException, IOException{
        
        String excelTemp = PlantillasUtils.crearPlantillaExcel(plantillaExcel, listaObjetos);
        
        RequestContext context = RequestContext.getCurrentInstance();
        
        context.execute("window.open('/banksystem/WordServlet?nombreExcel="+ excelTemp +"');");
        
    }
    
}
