package mx.babel.bansefi.banksystem.base.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.PlantillasUtils;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales; 

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

/**
 * Se encarga de generar un PDF a partir de una plantilla Jasper y levantar una nueva ventana mostrandolo
 * @author luis.gcastellano
 *
 */

@Service
public class PdfUtils {
	
	@Autowired
	ContextoUtils contexto;
	
    /**
     * 
     * @param archivoReporte Nombre de jrxml (debe encontrarse en la ruta META-INF/resources/jasper/)
     * @param params Mapa clave-valor con los parametros que recibe el jasper
     * @param imagenes Un mapa que tiene com clave el nombre de la imagen (debe encontrarse en la ruta META-INF/resources/img/)
     *                 y como valor el nombre del param que espera recibir el jasper
     * @param archivoDestino Nombre del pdf que se generará (se le añaden digitos aleatorios)
     * @param listaBeans Lista de beans en caso de que el report tenga subReports
     * @param entidad La entidad desde la que se esta realizando la consulta.
     */
    public void generaPdf(String archivoReporte, Map<String, Object> params,Map<String, String> imagenes, Map<String,String> subReportes, String archivoDestino, Collection<?> listaBeans) throws  ControlableException, NoControlableException{
        generaPdf(archivoReporte, params, imagenes, subReportes, archivoDestino, listaBeans, null, null);
    }
    
    /**
     * 
     * @param archivoReporte Nombre de jrxml (debe encontrarse en la ruta META-INF/resources/jasper/)
     * @param params Mapa clave-valor con los parametros que recibe el jasper
     * @param imagenes Un mapa que tiene com clave el nombre de la imagen (debe encontrarse en la ruta META-INF/resources/img/)
     *                 y como valor el nombre del param que espera recibir el jasper
     * @param archivoDestino Nombre del pdf que se generará (se le añaden digitos aleatorios)
     * @param listaBeans Lista de beans en caso de que el report tenga subReports
     * @param entidad La entidad desde la que se esta realizando la consulta.
     */
    public void generaPdf(String archivoReporte, Map<String, Object> params, Map<String, String> imagenes, Map<String,String> subReportes, String archivoDestino, Collection<?> listaBeans, String clausulas, Integer posicion) throws  ControlableException, NoControlableException{
    	 String pdfTemp = PlantillasUtils.creaPlantillaPDF(archivoReporte, params, imagenes, subReportes, archivoDestino, listaBeans,contexto.getEntidad());
         
    	 if(clausulas != null){
    		 pdfTemp = mergePDFs(pdfTemp, clausulas, pdfTemp.replace(archivoDestino, archivoDestino + "clausulas"), posicion);
    	 }
    	 
         RequestContext context = RequestContext.getCurrentInstance();
         
         HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

         String url = request.getScheme() + "://" +  request.getServerName() +  ":" + request.getServerPort() + request.getContextPath(); 
         context.execute("print('"+url+"/PdfServlet?nombrePdf="+pdfTemp+"');");
    }
    
    /**
     * @param originalFilePath PDF to append to
     * @param fileToInsertPath PDF to append
     * @param outputFile Where to save to
     * @param location where you want to insert the page
     */
    public String mergePDFs(String originalFilePath, String fileToInsertPath, String outputFile, int location) {

        PdfReader originalFileReader = null;
        try {
            originalFileReader = new PdfReader(originalFilePath);
        } catch (IOException ex) {
        }
        PdfReader fileToAddReader = null;
        try {
            fileToAddReader = new PdfReader(ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas")+contexto.getEntidad()+ 
    				ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas.jasper.clausulas")+ fileToInsertPath +".pdf");
        } catch (IOException ex) {
        }

        if (originalFileReader != null && fileToAddReader != null) {
            int numberOfOriginalPages = originalFileReader.getNumberOfPages();
            Document document = new Document();
            PdfCopy copy = null;
            try {
                copy = new PdfCopy(document, new FileOutputStream(outputFile));
                document.open();

                for (int i = 1; i <= numberOfOriginalPages; i++) {
                    if (i == (numberOfOriginalPages - location) + 1) {
                        for (int j = 1; j <= fileToAddReader.getNumberOfPages(); j++) {
                            copy.addPage(copy.getImportedPage(fileToAddReader, j));
                        }
                    }
                    copy.addPage(copy.getImportedPage(originalFileReader, i));

                }
                document.close();
                originalFileReader.close();
                fileToAddReader.close();
                return outputFile;
            } catch (DocumentException | IOException ex) {
            	return originalFilePath;
            } 
        }
        return originalFilePath;
    }
    
}
