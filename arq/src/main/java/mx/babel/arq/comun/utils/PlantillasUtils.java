package mx.babel.arq.comun.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import mx.babel.arq.comun.exceptions.NoControlableException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSwapFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * Clase para utilerias de plantillas 
 * @author gerard.chavez
 *
 */
public class PlantillasUtils {
	
	 private static final Logger LOGGER = LogManager.getLogger(PlantillasUtils.class.getName());
	
	 private PlantillasUtils(){
		 //método vacío para esconder el constructor público implícito
	 }
	 
	/**
     * Método de utilería para generar un archivo PDF con base en una plantilla jrxml
     * @param archivoReporte path del archivo de plantilla jrxml
     * @param params Map con los parámetros a sustituir en la plantilla
     * @imagenes imagenes Un mapa que tiene com clave el nombre de la imagen (debe encontrarse en la ruta META-INF/resources/img/)
     *                 y como valor el nombre del param que espera recibir el jasper
     * @param archivoDestino nombre del archivo pdf (con ruta)  para su posterior impresión 
     * @return La ruta del archivo recién creado.
     */
    public static String creaPlantillaPDF(String archivoReporte, Map<String, Object> params, Map<String, String> imagenes, Map<String,String> subReportes, String archivoDestino, Collection<?> listaBeans, String entidad) throws NoControlableException{
    	
    	if(params == null){
    		params = new HashMap<String, Object>();
    	}
    	try {
    		//Se encarga de recuperar las imagenes y metarlas en los params del report
    		if(imagenes!=null && imagenes.size()>0){
    			Iterator it = imagenes.keySet().iterator();
    			while(it.hasNext()){
    				String nombreImagen = (String)it.next();
    				BufferedImage image = ImageIO.read(new FileInputStream(ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas")+entidad+ 
    						ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas.img")+ nombreImagen));
    				params.put(imagenes.get(nombreImagen), image);
    			}
    		}
        
            /**
             * Compile the report to a file name same as
             * the JRXML file name
             */
    		File tempFile = File.createTempFile(archivoDestino, ".pdf");
    		String tempPath = tempFile.getAbsolutePath();
    		
    		JRSwapFileVirtualizer virtualizer = null;
    		virtualizer = new JRSwapFileVirtualizer(1, new JRSwapFile(tempPath.replace(tempFile.getName(),""), 2048, 1024), false);
    		params.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
    		
        	JRBeanCollectionDataSource origenDatos = new JRBeanCollectionDataSource(listaBeans);
            JasperReport reporte = getJasper(archivoReporte,entidad);
            adicionaSubReportes(subReportes, params,entidad);
            JasperPrint print = JasperFillManager.fillReport(reporte, params, origenDatos);
            JasperExportManager.exportReportToPdfFile(print, tempPath);
            
            virtualizer.cleanup();
            
            return tempPath.replace("\\", "//");
        } catch (JRException e) {
             throw new NoControlableException("Error al generar archivo para imprimir plantila.", e);
        } catch (IOException e) {
        	 throw new NoControlableException("No se encontró la plantilla del reporte.", e);
		}
    }
    
    /**
     * Método que compila los subreportes del reporte y los adiciona al map de parametros.
     * @param subReportes map con el nombre de los subreportes y su nombre de paramentro
     * @param params map de parametros donde se adicionarán los subreportes
     * @throws JRException En caso de obtener un error al compilar un subreporte
     * @throws IOException En caso de no encontrar alguno de los archivos de subreporte
     */
    private static void adicionaSubReportes(Map<String,String> subReportes, Map<String, Object> params, String entidad) throws JRException, IOException{
    	if(subReportes!= null && subReportes.size()>0){
    		Iterator it = subReportes.keySet().iterator();{
    			while(it.hasNext()){
	    	        String nombreSubReporte = (String)it.next();
	    	        JasperReport reporte = getJasper(nombreSubReporte, entidad);
	    	        params.put(subReportes.get(nombreSubReporte), reporte);
    			}
    	    }
    	}
    }
    
    private static JasperReport getJasper(String nombreReporte, String entidad) throws JRException, IOException{
    	JasperReport reporte;
    	
    	String archivoCompilado = getRutaPlantillas(entidad) + nombreReporte.replace(".jrxml",".jasper");
    	nombreReporte = getRutaPlantillas(entidad) + nombreReporte;
    	
    	if(!new File(archivoCompilado).isFile()){
    		JasperCompileManager.compileReportToFile(nombreReporte, archivoCompilado);
    	}
    	reporte =  (JasperReport) JRLoader.loadObject(new File(archivoCompilado));
    	return reporte;
    }
    
    public static String getRutaPlantillas(String entidad){
    	return ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas")+entidad+ 
				ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas.jasper");
    }

    /**
     * Método de utilería para generar un archivo PDF con base en una plantilla .docx
     * documento.
     * @param plantillaWord - Nombre del documento a recuperar.
     * @param variables - Lista de variables a incluir en la plantilla.
     * @return La ruta del archivo recién creado.
     * @throws IOException
     */
    public static String creaPlantillaWord(String plantillaWord, Map<String, VariablePlantillaUtils> variables, String entidad) throws NoControlableException {
        
    	File file = null;
        XWPFDocument document = null;
        String tempPath = null;
        try {
        	String rutaWord = ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas")+entidad+ 
    				ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.plantillas.word");                    
            document = new XWPFDocument(new FileInputStream(rutaWord + plantillaWord));
            recorrerElementos(document, variables);
            File tempFile = File.createTempFile(rutaWord + plantillaWord, ".docx");
    		tempPath = tempFile.getAbsolutePath();
            file = new File(tempPath);
            FileOutputStream fos = new FileOutputStream(file);
            document.write(fos);
            return tempPath.replace("\\", "//");
        }
        
        catch(IOException e) {
        	throw new NoControlableException("Error abriendo el fichero.", e);
        }              
		
    }

	public static int getCode(String code) {
		int colorCode;
		if (code != null)
			colorCode = Long.decode("0x" + code).intValue();
		else
			colorCode = Long.decode("0x000000").intValue();
		return colorCode;
	}

    /**
     * Recorre todos los elementos (parrafos y tablas) de un documento de word.
     * @param doc - documento de word
     * @param variables - variables que queremos formatear.
     */
    public static void recorrerElementos(XWPFDocument doc, Map<String, VariablePlantillaUtils> variables) {
    	List<IBodyElement> elementos = doc.getBodyElements();
		for (IBodyElement elem : elementos) {
			if(elem instanceof XWPFParagraph) {
				XWPFParagraph p = (XWPFParagraph)elem;
				for (Map.Entry<String, VariablePlantillaUtils> variable : variables.entrySet()) {
					recorrerParrafo(p, variable);
				}
	        }
	        if(elem instanceof XWPFTable) {
	        	XWPFTable table = (XWPFTable)elem;
	        	recorrerTabla(table, variables);
	        }
		}
    }
    
    /**
     * Recorre el parrafo indicado buscando el valor de la variable y si existe, lo sustituye por el valor dado
     * @param paragraph
     * @param variables
     */
    public static void recorrerParrafo (XWPFParagraph paragraph, Map.Entry<String, VariablePlantillaUtils> variable) {
    		List<XWPFRun> runList = paragraph.getRuns();
    		for (int i = 0; i < runList.size(); i++) {
    			XWPFRun run = runList.get(i);
    			String text = run.getText(0);
    			if (text != null && text.contains(variable.getKey())) {
    				text = text.replace(variable.getKey(), variable.getValue().getValor());
    				paragraph.removeRun(i);
    				XWPFRun newRun = paragraph.createRun();
    				newRun.setText(text, 0);
    				if ( variable.getValue().getFontFamily() != null ) {
    					newRun.setFontSize(variable.getValue().getFontSize());
        				newRun.setBold(variable.getValue().isBold());
        				newRun.setFontFamily(variable.getValue().getFontFamily());
    				} else {
    					newRun.setFontSize(11);
        				newRun.setBold(false);
        				newRun.setFontFamily("Arial");
    				}
    			}
    			
    		}
    }
   
    /**
     * Recorre la tabla, en filas y columnas y busca dentro de una celda las variables que buscamos y las
     * reemplaza por su valor.
     * @param table
     * @param variables
     */
    public static void recorrerTabla (XWPFTable table, Map<String, VariablePlantillaUtils> variables) {
    	for (XWPFTableRow row : table.getRows()) {
			for (XWPFTableCell cell : row.getTableCells()) {
				List<XWPFParagraph> paragraphList = cell.getParagraphs();
				for (XWPFParagraph p : paragraphList) {
					for (Map.Entry<String, VariablePlantillaUtils> variable : variables.entrySet()) {
						recorrerParrafo(p, variable);
					}
				}
            }
		}
    }
    
    public static String crearPlantillaExcel(String plantillaExcel, List<?> listaObjetos) throws IOException {

    	XSSFWorkbook workbook = new XSSFWorkbook();  	
    	XSSFSheet sheet = workbook.createSheet(plantillaExcel);
    	File file = null;
        String tempPath = null;    	
        
        crearHeaders(sheet, listaObjetos.get(0));
        crearFilas(sheet, listaObjetos);
        
        File tempFile = File.createTempFile(plantillaExcel, ".xlsx");
      	tempPath = tempFile.getAbsolutePath();
        file = new File(tempPath);
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
        
        return tempPath.replace("\\", "//");
    }
    
    private static void crearHeaders(XSSFSheet sheet, Object bean) {
    	 int rownum = 0;
         	Row row = sheet.createRow(rownum++);
         	Class<?> cls = null;
     		Field[] fields = null;
     		Object valor = null;

     		cls = bean.getClass();
     		fields = cls.getDeclaredFields();
     		
     		int cellnum = 0;
     		for (Field field : fields) {
     			try {
     				field.setAccessible(true);
     				valor = field.getName();
     				if(valor==null){
     					continue;
     				}
     				Cell cell = row.createCell(cellnum++);
     				cell.setCellValue(valor.toString());
     			
     			} catch (IllegalArgumentException e) {
     				LOGGER.error("Error al crear headers de plantilla: "+ e.getMessage());
     			}
     		}

    }
    
    private static void crearFilas(XSSFSheet sheet, List<?> listaObjetos) {
    	 int rownum = 1;
         for (Object bean : listaObjetos) {
         	Row row = sheet.createRow(rownum++);
         	Class<?> cls = null;
     		Field[] fields = null;
     		Object valor = null;

     		cls = bean.getClass();
     		fields = cls.getDeclaredFields();
     		
     		int cellnum = 0;
     		for (Field field : fields) {
     			try {
     				field.setAccessible(true);
     				valor = field.get(bean);
     				if(valor==null){
     					continue;
     				}
     				Cell cell = row.createCell(cellnum++);
     				cell.setCellValue(valor.toString());
     			} catch (IllegalAccessException e) {
     				LOGGER.error("Error al crear filas de plantilla: "+ e.getMessage());
     			} catch (IllegalArgumentException e) {
     				LOGGER.error("Error al crear filas de plantilla: "+ e.getMessage());
     			}
     		}

         }
    }
    
}
