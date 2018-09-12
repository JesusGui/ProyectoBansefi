package mx.babel.bansefi.banksystem.base.backends;


import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import mx.babel.arq.cache.annotation.Cache;
import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.catalogo.beans.CatalogoGeoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.CatalogoPaisesUtils;
import mx.babel.bansefi.banksystem.base.webservices.catalogogeo.ConsultaCatalogoGeoServicio;
import mx.babel.bansefi.banksystem.base.webservices.catalogogeo.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.catalogogeo.ResponseBansefi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la consulta de catalogos
 *
 * @author
 *
 */
@Component
public class ConsultaCatalogoGeoBackend extends BackEndBean {


	private static final long serialVersionUID = -677903888350583935L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

    @Autowired
    DozerBeanMapper dozerBeanMapper;
    
    @Autowired
    CatalogoPaisesUtils catalogoPaisesUtils; 

    private static final Logger LOGGER = LogManager.getLogger(ConsultaCatalogoGeoBackend.class);

    public static final String SEPARADOR_ABRE_LOC_MUN = "/";
    public static final String SEPARADOR_CIERRE_LOC_MUN = "";
 
    public static final String GUION_BAJO = "_";


    private List<CatalogoGeoBean> lstCatalogoGeoBean = new CopyOnWriteArrayList<CatalogoGeoBean>();
    private List<CatalogoBean> lstPaises = new CopyOnWriteArrayList<CatalogoBean>();
    private Integer cantidadCatalogosGeo = 0;
    /**
     * Constructor.
     */
    public ConsultaCatalogoGeoBackend() {
    }

    
    /**
     * Método que ejecuta la transacción de consulta de cuentas a partir de un
     * objeto CuentaBean.
     *
     * @param cuentaBean
     * @return cuentaBean con valores de atributos recuperados
     * @throws ControlableException
     * @throws NoControlableException
     */
    public void ejecutarTRN() throws Exception{
        
    	EjecutarResult resultado = null;
        List<ResponseBansefi> lstResBan = new CopyOnWriteArrayList<ResponseBansefi>();
        List<ResponseBansefi> lstTmp = null;
        String pagina = null;
        int size = 0;
        String msg = "";
        LOGGER.debug("GEO::Cargando catálogos GEO.");
//        int x = 0;
        do {
        	if(pagina==null){
        		pagina="000000000";
        	}else{
        		
        	}
            resultado = (EjecutarResult)servicioWebUtils.ejecutarWS(ConsultaCatalogoGeoServicio.class, 
            		null, new BigInteger("0"), pagina);
            
        	lstTmp = resultado.getResponseBansefi().getResponseBansefi();
        	lstResBan.addAll(lstTmp);
        	
        	size = lstTmp.size();
        	pagina  ="000000" + lstTmp.get(size-1).getCPNUMARGEO().intValue();
        	pagina = pagina.substring(pagina.length()-9);
        	
    		msg = resultado.getMENSAJE().trim();
//    		if( (x%1000)==0 )
//    	        LOGGER.debug("GEO::Procesando TRNs... ("+x+")");
//    		x++;
//        }while(x<5);
        } while ( "0".equals(resultado.getESTATUS()) && (ConstantesFuncionales.MSG_1.equals(msg) || ConstantesFuncionales.MSG_2.equals(msg)) );
        
        if( !lstResBan.isEmpty() ){
        	this.cantidadCatalogosGeo = lstResBan.size();
        }else{
        	this.cantidadCatalogosGeo = 0;
        }
        LOGGER.debug("GEO::Procesando las "+this.cantidadCatalogosGeo+" respuestas");
        this.lstCatalogoGeoBean = this.procesarRespuestas(lstResBan);
        LOGGER.debug("GEO::Terminado.");
    }
    
    public void ejecutarCargaDeArchivo() throws Exception{
    	
		String fileOK = null;
		String registro = null;
		CatalogoGeoBean geoBean = null;
		List<CatalogoGeoBean> lstCatGeoBean = null;
        CatalogoBean pais = null;
		FileInputStream inputStream = null;
		Scanner sc = null;
		
		String fileErrores = null;
		List<String> lstErrores = null;
		Path p = null;
		byte[] data = null;
		
        pais = catalogoPaisesUtils.getCatalogoBean("412");
    	lstPaises.add(new CatalogoBean(pais.getClaveFila(), pais.getDescripcionL()));
		
		fileOK = ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.configuracion")+
				ProveedorMensajeSpringUtils.getValorConfiguracion("path.archivo.catalogos.geo");
		
		fileErrores = ProveedorMensajeSpringUtils.getValorConfiguracion("ruta.configuracion")+"registrosConError.txt";
		lstErrores = new ArrayList<String>();
		LOGGER.debug("GEO::leyendo " + fileOK);
		
		try {
		    inputStream = new FileInputStream(new File(fileOK));
		    sc = new Scanner(inputStream, "UTF-8");
			lstCatGeoBean = new CopyOnWriteArrayList<CatalogoGeoBean>();
		    while (sc.hasNextLine()) {
		    	try{
			        registro = sc.nextLine();
					geoBean = this.getGeoBean(registro.replaceAll("#", "Ñ"), pais);
					lstCatGeoBean.add(geoBean);
		    	}catch(Exception registrarErrorYContinuar){
		    		lstErrores.add(registro); 
		    	}
		    }
		    // Scanner suprime las excepción del final de archivo en caso de ocurrir.
		    if (sc.ioException() != null) {
		    	LOGGER.error("GEO::Error al leer el archivo del catálogo GEO.",sc.ioException());
		        throw sc.ioException();
		    }
		} finally {
		    if (inputStream != null) {
		        inputStream.close();
		        LOGGER.debug("GEO::stream cerrado.");
		    }
		    if (sc != null) {
		        sc.close();
		        LOGGER.debug("GEO::scanner cerrado.");
		    }
		}
		LOGGER.debug("GEO:: "+lstCatGeoBean.size()+" registros GEO procesados.");
		
		this.lstCatalogoGeoBean.addAll(lstCatGeoBean);
        if( !lstCatGeoBean.isEmpty() ){
        	this.cantidadCatalogosGeo = lstCatGeoBean.size();
        }else{
        	this.cantidadCatalogosGeo = 0;
        }
        
        if(lstErrores.size()>0){
    		LOGGER.debug("GEO:: "+lstErrores.size()+" registros GEO con error.");
			p = Paths.get(fileErrores);
			for(int i=0 ; i<lstErrores.size() ; i++){
				data = lstErrores.get(i).getBytes();
				try(OutputStream ouputStream = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND)) ){
					ouputStream.write(data, 0, data.length);
			    } catch (IOException x) {
			      LOGGER.error("No se pudo escribir en el archivo de errores.",x);
			    }
			}
        }else{
        	LOGGER.debug("No hay registros con error.");
        }
        
		
        LOGGER.debug("GEO::Terminado 2.");
    }
    
    private CatalogoGeoBean getGeoBean(String registro, CatalogoBean pais) throws Exception{
    	CatalogoGeoBean bean = null;
    	String munLoc = null;
    	int index = 0;
    	int ini = 0;
    	int fin = 0;
    	
    	bean = new CatalogoGeoBean();
    	
		fin = ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setId( Integer.valueOf(registro.substring(0, fin).trim()) );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setCodigoPostal( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setSubCodigoPostal( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setNumAgLocine( registro.substring(ini, fin).trim() );
    	bean.setNumArGeo( bean.getNumAgLocine() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setCodigoProvincia( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setNombreLocalidad( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setIndPzaBancaria( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setCodigoMunicipio( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setCodigoEntidadColectiva( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setNombreMunicipio( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setPrNumArGeo( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setCodigoComunidadAutonoma( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setNombreProvincia( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setPrefijoTelefono( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setMatricula( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setCodigoImpuesto( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	fin = ini+ConstantesFuncionales.getPosicionesCatalogoAdminGeo().get(index);
    	bean.setCaNumArGeo( registro.substring(ini, fin).trim() );
    	index++;
    	ini = fin;
    	bean.setNombreComunidadAutonoma( registro.substring(ini).trim() );
    	
    	bean.setCodigoPais(pais.getClaveFila());
    	bean.setNombrePais(pais.getDescripcionL());
    	bean.setCodArGeo(ConstantesFuncionales.COD_AR_GEO);
    	
    	munLoc = bean.getNombreMunicipio();
    	if(bean.getNombreProvincia()!=null && !bean.getNombreProvincia().isEmpty())
    		munLoc = munLoc.concat(SEPARADOR_ABRE_LOC_MUN).concat(bean.getNombreProvincia()).concat(SEPARADOR_CIERRE_LOC_MUN);
    	bean.setMunicipioLocalidad( munLoc );
    	
    	return bean;
    }
    
    private List<CatalogoGeoBean> procesarRespuestas(List<ResponseBansefi> lstResponseBansefi){
    	
        CatalogoGeoBean catalogoGeoBean = null;
        String munLoc = null;
        CatalogoBean pais = null;
        List<CatalogoGeoBean> lstGeoBean = new CopyOnWriteArrayList<CatalogoGeoBean>();
        
        pais = catalogoPaisesUtils.getCatalogoBean("412");
    	lstPaises.add(new CatalogoBean(pais.getClaveFila(), pais.getDescripcionL()));
    	ResponseBansefi data = null;
        
    	for( int x = 0 ; x<lstResponseBansefi.size() ; x++ ){
    		data = lstResponseBansefi.get(x);
        	catalogoGeoBean = dozerBeanMapper.map(data, CatalogoGeoBean.class, "resConsultaCatalogoGeo");
        	catalogoGeoBean.setCodigoPais(pais.getClaveFila());
        	catalogoGeoBean.setNombrePais(pais.getDescripcionL());
        	catalogoGeoBean.setCodArGeo(ConstantesFuncionales.COD_AR_GEO);
        	
        	munLoc = catalogoGeoBean.getNombreMunicipio();
        	if(catalogoGeoBean.getNombreProvincia()!=null && !catalogoGeoBean.getNombreProvincia().isEmpty())
        		munLoc = munLoc.concat(SEPARADOR_ABRE_LOC_MUN).concat(catalogoGeoBean.getNombreProvincia()).concat(SEPARADOR_CIERRE_LOC_MUN);
        	catalogoGeoBean.setMunicipioLocalidad( munLoc );
        	
        	lstGeoBean.add(catalogoGeoBean);
        	if( (lstResponseBansefi.size()%100)==0 )
    	        LOGGER.debug("GEO::Procesando respuestas de TRNs... ("+lstResponseBansefi.size()+")");
        	
        	lstResponseBansefi.remove(x);
        	x--;// para que no se salte el elemento que ocupa el índice que se acaba de borrar.
    	}
    	return lstGeoBean;
    }
    
    @Cache(cacheName="catalogoGeoCache")
    public List<CatalogoGeoBean> getCatalogoGeoBean(){
    	return this.lstCatalogoGeoBean;
    }
    
    @Cache
    public List<CatalogoBean> getCatalogoBeanPaises(){
    	return this.lstPaises;
    }
    
	public Integer getCantidadCatalogosGeo() {
		return cantidadCatalogosGeo;
	}


	public void setCantidadCatalogosGeo(Integer cantidadCatalogosGeo) {
		this.cantidadCatalogosGeo = cantidadCatalogosGeo;
	}
    
}
