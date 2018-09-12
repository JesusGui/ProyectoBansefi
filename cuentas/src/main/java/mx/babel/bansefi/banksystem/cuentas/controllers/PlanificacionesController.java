package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaDetallePlanificacionBackend;
import mx.babel.bansefi.banksystem.cuentas.backends.ConsultaPlanificacionesBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.PlanificacionBean;
import mx.babel.bansefi.banksystem.cuentas.beans.PlanificacionBeanFechaComparator;

import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class PlanificacionesController implements Serializable{
    
    private static final long serialVersionUID = 544773292184482727L;
    
    @Autowired
    ContextoUtils contextoUtils;
   
    @Autowired
    ConsultaPlanificacionesBackend consultaPlanificacionesBackend;
    
    @Autowired
    ConsultaDetallePlanificacionBackend consultaDetallePlanificacionBackend;
    
    @Autowired
	ManagedBeanStateRecover managedBeanStateRecover;
    
    private int pagina;
    private String tipoConsulta;
	
	/**
	 * Variable utilizada para guardar la redireccion al flujo de entrada
	 * a la consulta de planificaciones.
	 */
	private NavegacionEnum destino;
	
	/**
	 * Variable utilizada para guardar el contenido del controller origen
	 * a la consulta de planificaciones.
	 */
	private Object destinoController;
    
	private List<PlanificacionBean> planificaciones;
    private TreeNode root;
    private boolean mostrarPendientes;
    private CuentaBean cuentaBean;
	
    @Autowired
	CatalogoUtils catalogoUtils;
    
    @PostConstruct
    public void init() {
        planificaciones = new ArrayList<>();
        mostrarPendientes = true;
        root = new DefaultTreeNode("Root", null);
        destino = managedBeanStateRecover.getDestino();
		destinoController = managedBeanStateRecover.getController();
		pagina = 0;
		tipoConsulta = ConstantesFuncionales.COD_PLANIFICACIONES_PENDIENTES;
		
        if (obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash()) != null){
        	cuentaBean = (CuentaBean) obtieneFlash().get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
            planificaciones = consultaPlanificacionesBackend.ejecutarTRN(cuentaBean.getNumeroCuenta(), ConstantesFuncionales.COD_PLANIFICACIONES_PENDIENTES);
        	if(planificaciones.size()>0){
        		pagina = 1;
        	}
        	
        }else{
        	throw new NoControlableException("Error al llamar a TRN de consulta de planificaciones con cuenta vacía", "Error al llamar a TRN de consulta de planificaciones con cuenta vacía");
        }
        construyeArbol(planificaciones, false);
    }
    
    public void muestraDetalle(NodeExpandEvent event){
    	DefaultTreeNode nodo = (DefaultTreeNode)event.getTreeNode();
    	
    	if("EVENTO".equals(nodo.getType())){
	    	Calendar cal = Calendar.getInstance();
	    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");    	
			try {
				consultaDetallePlanificacionBackend.ejecutarTRN(cuentaBean.getNumeroCuenta(), formatter.parse(nodo.getParent().getData().toString()));
			} catch (ParseException e) {
				throw new NoControlableException("Error al parsear fecha para consulta de planificación",e);
			}catch (ControlableException ce) {
				// TODO: no data found
			}
    	}
        
    }
    
    /**
     * Método para construir un arbol de planificaciones
     * @param planificaciones lista de planificacionBean para desplegar como árbol
     */
    public void construyeArbol(List<PlanificacionBean> planificaciones, boolean reverseOrder){
    	if(!planificaciones.isEmpty()){
	        TreeMap<String, List<PlanificacionBean>> nodosPlanificaciones = new TreeMap<String, List<PlanificacionBean>>(); 
	        String valorConCero = "%02d";
	        for (int i = 0; i < pagina*10; i++) {
	        	if(i<planificaciones.size()){
		            PlanificacionBean planificacion = planificaciones.get(i);
		            Calendar cal = Calendar.getInstance();
		            cal.setTime(planificacion.getFechaPlanificacion());
		            int year = cal.get(Calendar.YEAR);
		            
		            String month = String.format(valorConCero, cal.get(Calendar.MONTH)+1);            
		            String day = String.format(valorConCero,cal.get(Calendar.DAY_OF_MONTH));
		            String llave = year+"/"+month+"/"+day;
		            
		            if(nodosPlanificaciones.containsKey(llave)){
		                List<PlanificacionBean> valor = nodosPlanificaciones.get(llave);
		                valor.add(planificacion);
		                nodosPlanificaciones.put(llave, valor );
		            }else{
		                List<PlanificacionBean> valor = new ArrayList<PlanificacionBean>();
		                valor.add(planificacion);
		                nodosPlanificaciones.put(llave, valor );
		            }
	        	}
	        }
	        
	        Set<String> nodos = nodosPlanificaciones.keySet();
	        List<String> listaNodos = new ArrayList<>();
	        listaNodos.addAll(nodos);
	        if(reverseOrder){
	        	Collections.reverse(listaNodos);
	        }
	        for (String key : listaNodos) {
	        	String[] llaveBack = key.split("/");
	            TreeNode nodoFecha = new DefaultTreeNode(llaveBack[2]+"/"+llaveBack[1]+"/"+llaveBack[0],root);
	            ((DefaultTreeNode)nodoFecha).setType("FECHA");
	            List<PlanificacionBean> subNodos = nodosPlanificaciones.get(key);
	            if(subNodos.size()>1){
	            	Collections.sort(subNodos, new PlanificacionBeanFechaComparator());
	            }
	            for (int i = 0; i < subNodos.size(); i++) {
	            	String descEvento = "";
	            	try{
	            		descEvento = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_TX, subNodos.get(i).getCodEvento()).getDescripcionC();
	            	}catch(NoControlableException | ControlableException e){
	            		descEvento = "No hay descripción.";
	            	}
	            	subNodos.get(i).setDescEvento(descEvento);
	                TreeNode nodoEvento = new DefaultTreeNode(subNodos.get(i).getCodEvento()+" - "+subNodos.get(i).getDescEvento(),nodoFecha);
	                ((DefaultTreeNode)nodoEvento).setType("EVENTO");
	                //add fake node to be able to expand *removed because there is no data
	                //nodoEvento.getChildren().add(new DefaultTreeNode("",nodoFecha));
	                nodoFecha.getChildren().add(nodoEvento);
	            }
	            
	            root.getChildren().add(nodoFecha);
	            
	        }
    	}else{
    		root = new DefaultTreeNode("Root", null);
    	}
    }
    
    public void mostrarHistorico(){
    	tipoConsulta = ConstantesFuncionales.COD_PLANIFICACIONES_REALIZADAS;
    	pagina = 1;
        mostrarPendientes = false;        
        planificaciones = new ArrayList<PlanificacionBean>();
		planificaciones = consultaPlanificacionesBackend.ejecutarTRN(cuentaBean.getNumeroCuenta(), tipoConsulta);
		root = new DefaultTreeNode("Root", null);
		construyeArbol(planificaciones, true);
		pagina = 1;
    }
    
    public void mostrarPendientes(){
    	tipoConsulta = ConstantesFuncionales.COD_PLANIFICACIONES_PENDIENTES;
    	pagina = 1;
    	mostrarPendientes = true;
    	planificaciones = new ArrayList<PlanificacionBean>();
		planificaciones = consultaPlanificacionesBackend.ejecutarTRN(cuentaBean.getNumeroCuenta(), tipoConsulta);
		root = new DefaultTreeNode("Root", null);
		construyeArbol(planificaciones, false);
		pagina = 1;
    }
    
    public void obtenerMasDatos(){
    	try{
			
			if(pagina*10 < planificaciones.get(planificaciones.size()-1).getNumeroDatos()){
				root = new DefaultTreeNode("Root", null);
				pagina++;
				if(ConstantesFuncionales.COD_PLANIFICACIONES_PENDIENTES.equals(tipoConsulta)){
					construyeArbol(planificaciones, false);
				}else if(ConstantesFuncionales.COD_PLANIFICACIONES_REALIZADAS.equals(tipoConsulta)){
					construyeArbol(planificaciones, true);
				}
			}
				
		}catch(ControlableException ce){
			if(ce.getRtncod() == 7){
			}
		}
    }
    

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public boolean isMostrarPendientes() {
        return mostrarPendientes;
    }

    public void setMostrarPendientes(boolean mostrarPendientes) {
        this.mostrarPendientes = mostrarPendientes;
    }
    
    /**
     * Método privado que obtiene el objeto Flash proveniente del contexto
     * externo.
     * 
     * @return Flash
     */
    private Flash obtieneFlash() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getFlash();
    }
    
    /**
	 * @return Metodo utilizado para volver a la ficha de cuenta
	 */
	public String volver() {		
		obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(),this.cuentaBean);		
		managedBeanStateRecover.finNavegacion(destinoController);
		return destino.getRuta();					
	}

	public List<PlanificacionBean> getPlanificaciones() {
		return planificaciones;
	}

	public void setPlanificaciones(List<PlanificacionBean> planificaciones) {
		this.planificaciones = planificaciones;
	}

	public CuentaBean getCuentaBean() {
		return cuentaBean;
	}

	public void setCuentaBean(CuentaBean cuentaBean) {
		this.cuentaBean = cuentaBean;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	
    
	
}
