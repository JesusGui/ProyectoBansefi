package mx.babel.bansefi.banksystem.personas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.personas.backend.AltaBalanceBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ConsultaBalanceBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ListaBalancesBackEnd;
import mx.babel.bansefi.banksystem.personas.backend.ModificacionBalanceBackEnd;
import mx.babel.bansefi.banksystem.personas.beans.BalanceBean;
import mx.babel.bansefi.banksystem.personas.beans.RegistroBalanceBean;
import mx.babel.bansefi.banksystem.personas.beans.TabBalanceBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado de gestionar el Balance de una persona moral.
 * 
 * @author babel
 * */
@ManagedBean(name="balanceController")
@ViewScoped
@Component
@Scope("view")
public class BalanceController implements Serializable {

	private static final Logger LOGGER = LogManager.getLogger(BalanceController.class.getName());
	
	static final long serialVersionUID = -6061986299791150086L;
	
	private static final String ID_LIS_BALANCES = "listaBalances";
	private static final String ID_BALANCE 	    = "balance";
	private static final String ID_BALANCE_SEL  = "balanceSel";
	private static final String ID_TABS 		= "tabs";
	private static final String ID_TOTAL		= "total";
	private static final String ID_ACTIVE_TAB   = "activeTab";
	private static final String ID_RATIOS 		= "ratios";
	private static final String ID_TOTAL_GRUPO  = "totalGrupo";
	private static final String ID_INDICADOR  	= "indicador";
	
	private static final String IND_ALTA 			= "A";
	private static final String IND_MODIFICACION 	= "M";
	
	private static final String STYLE_LINEA_RES 	= "linea_azul resumen";
	private static final String STYLE_LINEA_TIT 	= "linea_azul";
	private static final String STYLE_LINEA_PAR  	= "linea_gris_clara";
	private static final String STYLE_LINEA_IMPAR 	= "linea_gris_oscura";
	private static final String STYLE_TITULO_AZUL 	= "titulo_azul";
	private static final String STYLE_TITULO_GRIS 	= "titulo_gris";
	private static final String STYLE_CUADRA	 	= "cuadra";
	private static final String STYLE_DESCUADRA 	= "no-cuadra";
	
	private ClienteBean cliente;
	
	private List<TabBalanceBean> tabs;
    
    private List<BalanceBean> listaBalances;
    
    private List<RegistroBalanceBean> balance;
    
    private BalanceBean balanceSel = new BalanceBean();
    
    private BigDecimal totales[] = null; // total por agrupación
    
    private String resumen[] = null; // agrupaciones para resumen
    
    private String indicador;
    
    private int anyoActual;
    
    private int posRatios[] = {0, 0}; // posición ratios a visualizar
    
    private BigDecimal totalAP[] = {new BigDecimal(0), new BigDecimal(0)}; //total activo y pasivo
    
    private String styleDif = "";
    
    private int activeTab = 0;
    
    private int activeGroup = 0;
    
    private boolean hayCambios = false;
    
    private boolean abierto = true;
               
	@Autowired
    CatalogoUtils catalogo;
   
	@Autowired
	private AltaBalanceBackEnd altaBackend;
	
	@Autowired
	private ModificacionBalanceBackEnd modifBackend;
	
	@Autowired
	private ListaBalancesBackEnd listaBackend;
	
	@Autowired
	private ConsultaBalanceBackEnd consultaBackend;
	
	 /**
     * Se inicializan las variables de la vista.
     */
    @SuppressWarnings("unchecked")
	@PostConstruct
    public void init() {
    	this.cliente = (ClienteBean)this.obtieneFlash().get(ParametrosFlashEnum.CLIENTE.getParamFlash());
    	
		/*ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext
	               .getCurrentInstance().getApplication()
	               .getNavigationHandler();*/
		
		this.listaBalances = (List<BalanceBean>) obtieneFlash().get(BalanceController.ID_LIS_BALANCES);
		this.balanceSel = (BalanceBean) obtieneFlash().get(BalanceController.ID_BALANCE_SEL);
		indicador = (String)obtieneFlash().get(BalanceController.ID_INDICADOR);
		this.hayCambios = false;
		
		this.anyoActual = Calendar.getInstance().get(Calendar.YEAR);
		
		if (obtieneFlash().get(BalanceController.ID_BALANCE) != null) {
    		
    		this.balance = (List<RegistroBalanceBean>) obtieneFlash().get(BalanceController.ID_BALANCE);
    		this.tabs = (List<TabBalanceBean>) obtieneFlash().get(BalanceController.ID_TABS);
    		this.activeTab = (int) obtieneFlash().get(BalanceController.ID_ACTIVE_TAB);
    		this.totalAP = (BigDecimal[]) obtieneFlash().get(BalanceController.ID_TOTAL);
    		this.totales = (BigDecimal[]) obtieneFlash().get(BalanceController.ID_TOTAL_GRUPO);
    		this.posRatios = (int[]) obtieneFlash().get(BalanceController.ID_RATIOS);
    		if (BalanceController.IND_ALTA.equals((String)obtieneFlash().get(BalanceController.ID_INDICADOR))){
    			this.styleDif = BalanceController.STYLE_CUADRA;
    		} else this.styleDif = (totalAP[0].compareTo(totalAP[1])!=0) ? BalanceController.STYLE_DESCUADRA : BalanceController.STYLE_CUADRA;
    		
    	} else {
    	
	    	//TODO: Invocar TRN consulta balances y quitar código dummy
			//TODO: Si no tiene balances debe ir directamente a dar de alta
    		
    		try {

        		//TODO: Ordenar los valores del balance y setearlos en el balanceSel para su posterior modificación
        		if (this.cliente != null) {
        			listaBalances = listaBackend.ejecutarTRN(this.cliente.getIdInterna());
        			obtieneFlash().put(BalanceController.ID_LIS_BALANCES,this.listaBalances);
        		}
    		} catch (ControlableException ce) {
    			
    			//TODO: gestionar excepciones
    			
    		} catch (NoControlableException nce) {
    			
    			LOGGER.debug("Error al intentar consultar el balance");
    		
    			// TODO: Error en página y return
    		}
    		
    	}
	}

    /**
     * Vuelve a ficha del cliente.
     * 
     * @return ruta
     * */
    public String navegar() {
    	
		String ruta = null;
		
		if (listaBalances == null || listaBalances.size()==0) {
			ruta = this.altaBalance();
			obtieneFlash().put(BalanceController.ID_LIS_BALANCES,this.listaBalances);
		}
		
		return ruta;
    }

    /**
     * Vuelve a ficha del cliente.
     * 
     * @return ruta
     * */
    public String volver(){
    	
    	String ruta = "";
    	
    	if (this.hayCambios) {
    		
    		RequestContext.getCurrentInstance().execute("PF('dlgAlerta').show()");
    		ruta = "";
    		
    	} else {
    	
			ruta = NavegacionEnum.PERSONA_BALANCE_LISTA.getRuta();
			
			if (listaBalances == null || listaBalances.size()==0) {
				ruta = NavegacionEnum.FICHA_CLIENTE.getRuta();
			}
			
	    	obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
    	}
    	
		return ruta;
    }
    
    /**
     * Vuelve a ficha del cliente.
     * 
     * @return ruta
     * */
    public String volverConfirmacion(){
    	
    	String ruta = NavegacionEnum.PERSONA_BALANCE_LISTA.getRuta();
			
		if (listaBalances == null || listaBalances.size()==0) {
			ruta = NavegacionEnum.FICHA_CLIENTE.getRuta();
		}
		
    	obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);

    	return ruta;
    }
    
	
	 /**
     * Se inicializa el balance que se va a dar de alta.
     * 
     * @return ruta
     */
    public String altaBalance() {
		List<CatalogoBean> catBalance = catalogo.getCatalogo(CatalogoEnum.TP_LIN_BLNCE_ORG);
    	this.tabs = new ArrayList<TabBalanceBean>();
		this.balance = this.order(catBalance,null);
		this.balanceSel = new BalanceBean();
		this.styleDif = BalanceController.STYLE_CUADRA;   
		
		obtieneFlash().put(BalanceController.ID_TABS, this.tabs);
		obtieneFlash().put(BalanceController.ID_BALANCE, this.balance);
		obtieneFlash().put(BalanceController.ID_BALANCE_SEL, this.balanceSel);
		obtieneFlash().put(BalanceController.ID_ACTIVE_TAB, this.activeTab);
		obtieneFlash().put(BalanceController.ID_TOTAL, this.totalAP);
		obtieneFlash().put(BalanceController.ID_TOTAL_GRUPO, this.totales);
		obtieneFlash().put(BalanceController.ID_RATIOS, this.posRatios);
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(BalanceController.ID_INDICADOR, BalanceController.IND_ALTA); // indicador de alta
		obtieneFlash().put(BalanceController.ID_LIS_BALANCES,this.listaBalances);
		
		return NavegacionEnum.PERSONA_BALANCE_DETALLE.getRuta();
	}
    
    /**
     * Se inicializa el balance que se va a modificar.
     * 
     * @return ruta
     */
    public void modificarBalance(SelectEvent event) {
    	
    	try {
    		
    		//TODO: Ordenar los valores del balance y setearlos en el balanceSel para su posterior modificación
    		
    		this.balanceSel = (BalanceBean) event.getObject();
    		
    		
    		
    		List<RegistroBalanceBean> listaRegs = consultaBackend.ejecutarTRN(cliente.getIdInterna().intValue(),
    				this.balanceSel.getCodDocumento(), this.balanceSel.getIdInternoDoc());
    		
    		List<CatalogoBean> catBalance = catalogo.getCatalogo(CatalogoEnum.TP_LIN_BLNCE_ORG);
    		this.tabs = new ArrayList<TabBalanceBean>();
    		
    		Map<String,RegistroBalanceBean> mapa = new HashMap<String,RegistroBalanceBean>();
    		for (RegistroBalanceBean bal:listaRegs) {
    			mapa.put(bal.getId(),bal);
    		}
    		
    		this.balance = this.order(catBalance,mapa);
    		this.cambiarBalance(0);

    		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
    		
    		if (this.balanceSel.getFechaRevision()!=null) {
    			
    			try {
    				Calendar c = Calendar.getInstance();
    				c.setTime(sd.parse(this.balanceSel.getFechaRevision()));
    				this.anyoActual = c.get(Calendar.YEAR);
    			} catch (ParseException pe) {
    				this.anyoActual = Calendar.getInstance().get(Calendar.YEAR);
    			}
    		}
		
		} catch (ControlableException ce) {
			
			LOGGER.debug("Error al intentar consultar el balance");
		
			// TODO: Error en página y return
		}
    	
    	obtieneFlash().put(BalanceController.ID_LIS_BALANCES, this.listaBalances);
		obtieneFlash().put(BalanceController.ID_TABS, this.tabs);
		obtieneFlash().put(BalanceController.ID_BALANCE, this.balance);
		obtieneFlash().put(BalanceController.ID_ACTIVE_TAB, this.activeTab);
		obtieneFlash().put(BalanceController.ID_TOTAL, this.totalAP);
		obtieneFlash().put(BalanceController.ID_TOTAL_GRUPO, this.totales);
		obtieneFlash().put(BalanceController.ID_RATIOS, this.posRatios);
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
		obtieneFlash().put(BalanceController.ID_BALANCE_SEL, this.balanceSel);
    	obtieneFlash().put(BalanceController.ID_INDICADOR, BalanceController.IND_MODIFICACION); // indicador de modificacion

    	ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.
				getCurrentInstance().getApplication().getNavigationHandler();
    	String ruta = NavegacionEnum.PERSONA_BALANCE_DETALLE.getRuta();
    	configurableNavigationHandler.performNavigation(ruta);
    	//return NavegacionEnum.PERSONA_BALANCE_DETALLE.getRuta();
    
    }
    
    /**
     * Se vuelve a la ficha del cliente.
     * @return ruta
     */
    public String volverFicha() {
    	
    	
    	obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);
   	
    	return NavegacionEnum.FICHA_CLIENTE.getRuta();
    
    }
    
    /**
     * Renderiza el grupo en cuestión.
     * @param codGrupo Grupo
     * @param tab Pestaña
     */
    public void verGrupo(int codGrupo, int tab)
    {
    	
    	boolean actualizado = true;
    	if (tab!=this.activeTab)  {
    		actualizado = false;
    		
    	}
    	
    	for (RegistroBalanceBean bal:balance) {
    		
    		// En pestaña distinta se actualiza primer grupo
    		if (tab!=this.activeTab) {
    			if (bal.getTab()==tab && !actualizado) {
    				this.activeGroup=bal.getGrupo();
    				actualizado = true;    				
    			}
    		}
    		
    		   			
    		// Se deja de visualizar grupo anterior
    		if (activeGroup==bal.getGrupo() && bal.getTab()==tab && bal.isVisible()) {
    				bal.setVisible(false);
    				abierto=true;
    		} 
    		    		
    		// El nuevo grupo debe ser visible
    		if (bal.getTab()==tab && codGrupo==bal.getGrupo()) {
    			if(codGrupo != activeGroup || codGrupo == activeGroup && abierto==false){
	    			if(bal.isVisible()){
	    				bal.setVisible(false);
	    			}else{
	    				bal.setVisible(true);
	    			}
	    		}
    		}
    	}
    	this.activeTab = tab;    	
    	if(codGrupo == this.activeGroup){
    		abierto = false;
    	}else{
    		abierto = true;
    	}
    	this.activeGroup = codGrupo;
    	 	
    	
    	obtieneFlash().put(BalanceController.ID_TABS, this.tabs);
		obtieneFlash().put(BalanceController.ID_BALANCE, this.balance);
		obtieneFlash().put(BalanceController.ID_ACTIVE_TAB, this.activeTab);
		obtieneFlash().put(BalanceController.ID_TOTAL, this.totalAP);
		obtieneFlash().put(BalanceController.ID_TOTAL_GRUPO, this.totales);
		obtieneFlash().put(BalanceController.ID_RATIOS, this.posRatios);
		obtieneFlash().put(ParametrosFlashEnum.CLIENTE.getParamFlash(), this.cliente);

    }
        
    /**
     * Ordena el catálogo para presentar en la ventana.
     * */
    private List<RegistroBalanceBean> order(List<CatalogoBean> catBalance, Map<String,RegistroBalanceBean> listaModificar) {
    	
    	CatalogoBean lastBean = null;
    	CatalogoBean actualBean = null;
    	List<RegistroBalanceBean> listaBalance = new ArrayList<RegistroBalanceBean>();
    	RegistroBalanceBean regBalance = null;
    	
    	int tab = 0;
    	int nivel = 0;
    	int grupo = -1;
    	boolean grupoVisbile = true;
    	boolean par = true;
    	
    	int max = catBalance.size();
    	
    	String indResumen = "";
    	
    	TabBalanceBean tabBean = null;
    	
    	for (int i=0;i<max;i++) {
    		
    		actualBean = catBalance.get(i);
    		
    		if (lastBean!=null) {
    			
    			int sizeLast = (lastBean.getDescripcionL()!=null) ? lastBean.getClaveFila().trim().length() : 0;
    			int sizeActual = (actualBean.getDescripcionL()!=null) ? actualBean.getClaveFila().trim().length() : 0;
    		
    			// Se cambia de pestaña de la 2 a la 3
    			if (sizeActual == 2 && ConstantesFuncionales.COD_CAPITAL.equals(actualBean.getClaveFila())) {
    				tabs.get(1).setNombre(tabs.get(1).getNombre() + " Y "+ actualBean.getDescripcionL().trim());
    				nivel = 1;
    				listaBalance.get(listaBalance.size()-1).setEditable(true);
    			}
    			// Se cambia de pestaña
    			else if (sizeActual == 2) {
    				tab++;
    				nivel = 0;
    				
    				tabBean = new TabBalanceBean();
    				tabBean.setNombre(actualBean.getDescripcionL().trim());
    				tabBean.setPosicion(tab);
    				
    				tabs.add(tabBean);
    				 if(tabs.size()<=3) {
    					 listaBalance.get(listaBalance.size()-1).setEditable(true);
    				 }
    				 
    				 
    			} else {
    			
	    			// Se cambia de nivel -> se agrupan sumas en otro nivel
	    			if (sizeActual == 3) {
	    				if (!actualBean.getClaveFila().startsWith(ConstantesFuncionales.COD_RATIOS)) {
	    					indResumen += actualBean.getDescripcionL().trim()+"#";
	    				} else if(ConstantesFuncionales.RATIOS.get(0).equalsIgnoreCase(actualBean.getClaveFila())||
	    						ConstantesFuncionales.RATIOS.get(1).equalsIgnoreCase(actualBean.getClaveFila())) {
	    					indResumen += actualBean.getDescripcionL().trim()+"#";
	    				}
	    				
	    				nivel=1;
	    				grupo++;
	    				
	    				//Se comprueba la pestaña del ultimo elemento de la lista de balances
	    				regBalance = (listaBalance.size()>1) ? listaBalance.get(listaBalance.size()-1) : null;
	    				int tabAnt = (regBalance!=null) ? regBalance.getTab() : -1;
	    				
	    				if (tab>2 ||  tabAnt!=tab) {
	    					grupoVisbile = true;
	    				} else {
	    					grupoVisbile = false;
	    				}
	    			}
	    			
	    			// Se anida un nivel
	    			if (sizeActual>sizeLast && sizeActual != 3) {
	    				nivel++;
	    			} else if (sizeActual<sizeLast && sizeActual != 3) {
	    				nivel--;
	    			}
	    			
	    			regBalance = new RegistroBalanceBean(actualBean.getClaveFila());
	    			regBalance.setEditable(false);
	    			regBalance.setNivel(nivel);
	    			regBalance.setTab(tab);
	    			regBalance.setTexto(actualBean.getDescripcionL().trim());
	    			regBalance.setGrupo(grupo);
	    			
	    			if (nivel==1) {
	    				regBalance.setEstilo(BalanceController.STYLE_LINEA_TIT);
	    			} else if (nivel==2){
	    				par = !par;
	    				regBalance.setEstilo(par ? BalanceController.STYLE_LINEA_PAR : BalanceController.STYLE_LINEA_IMPAR);
	    			} else {
	    				regBalance.setEstilo(par ? BalanceController.STYLE_LINEA_PAR : BalanceController.STYLE_LINEA_IMPAR);
	    			}
	    			
	    			if (nivel==2) {
	    				regBalance.setEstiloTitulo(BalanceController.STYLE_TITULO_AZUL);
	    			} else if (nivel>2) {
	    				regBalance.setEstiloTitulo(BalanceController.STYLE_TITULO_GRIS);
	    			}

	    			if (listaModificar == null) {
	    				if(regBalance.getEstilo() == BalanceController.STYLE_LINEA_TIT ){
	    	    			regBalance.guardarValor(new BigDecimal(0));
	    	    		}else{
	    	    			regBalance.setValor(new BigDecimal(0));
	    	    		}	    				 			
		    			regBalance.setPorcentaje(new BigDecimal(0));
		    			
	    			} else {
	    				
	    				if (listaModificar.get(regBalance.getId())!= null) {
	    					BigDecimal valor = listaModificar.get(regBalance.getId()).getValor();
	    					if(valor != null){
	    						regBalance.setValor(valor);
	    					}
			    			regBalance.setPorcentaje(listaModificar.get(regBalance.getId()).getPorcentaje());
	    				} else {
	    					regBalance.setValor(new BigDecimal(0));
			    			regBalance.setPorcentaje(new BigDecimal(0));
	    				}
		    			
	    			}

//	    			regBalance.getValor().setScale(2, BigDecimal.ROUND_UP);
	    			
	    			// De inicio solo se visualizan los elementos del primer grupo de cada tabla
	    			if (regBalance.getNivel()==1 && isAbierto(regBalance.getGrupo()) ) {
	    				regBalance.setVisible(true);
	    			} else {
	    				regBalance.setVisible(grupoVisbile);
	    			}
	    			
	    			// Ultimo nivel es editable
	    			if (sizeActual == 7 && tabs.size()<=3) {
	    				regBalance.setEditable(true);
	    			}
	    			
	    			// Si es mismo nivel que el anterior el anterior es editable
	    			else if (sizeActual<=sizeLast && tabs.size()<=3) {
	    				listaBalance.get(listaBalance.size()-1).setEditable(true);
	    			}
	    			
	    			if (i==max-1 && tabs.size()<=3) {
	    				regBalance.setEditable(true);
	    			}
	    			
	    			// Solo se visualizarán los ratios de Solvencia circulante y Acido
	    			if (actualBean.getClaveFila().startsWith(ConstantesFuncionales.COD_RATIOS)) {

	    				if(ConstantesFuncionales.RATIOS.get(0).equalsIgnoreCase(actualBean.getClaveFila())) {
	    					posRatios[0] = listaBalance.size();
	    					regBalance.setEstilo(BalanceController.STYLE_LINEA_RES);
	    					listaBalance.add(regBalance);
	    				} else if(ConstantesFuncionales.RATIOS.get(1).equalsIgnoreCase(actualBean.getClaveFila())) {
	    					posRatios[1] = listaBalance.size();
	    					regBalance.setEstilo(BalanceController.STYLE_LINEA_RES);
	    					listaBalance.add(regBalance);
	    				}
	    				
	    			} else {		
	    				listaBalance.add(regBalance);
	    			}
	    		}
    		} else {
    			tabBean = new TabBalanceBean();
				tabBean.setNombre(actualBean.getDescripcionL().trim());
				tabBean.setPosicion(tab);
				
    			tabs.add(tabBean);
    		}
    			

    		// Almacenamos bean para siguiente iteración
    		lastBean = actualBean;
    	}
    	
    	resumen = indResumen.split("#");
    	int lenResumen = resumen!=null ? resumen.length : 0;
    	totales = new BigDecimal[lenResumen];
    	
    	tab++;
    	tabBean = new TabBalanceBean();
    	tabBean.setNombre("RESUMEN");
    	tabBean.setPosicion(tab);

    	for (int i=0;i<lenResumen;i++)
    	{
	    	regBalance = new RegistroBalanceBean("");
			regBalance.setEditable(false);
			regBalance.setNivel(1);
			regBalance.setTab(tab);
			regBalance.setTexto(resumen[i]);
			regBalance.guardarValor(new BigDecimal(0));
			regBalance.setPorcentaje(new BigDecimal(0));
//			regBalance.getValor().setScale(2,BigDecimal.ROUND_UP);
			regBalance.setResumen(true);
			
			regBalance.setEstilo(BalanceController.STYLE_LINEA_RES);
			
			if (i >= lenResumen - ConstantesFuncionales.RATIOS.size()) {
				regBalance.setRatio(true);
			}
			
	    	listaBalance.add(regBalance);
    	}

    	tabs.add(tabBean);

    	return listaBalance;
    }
    
    public void limpiar(int tabChanged){
    	RegistroBalanceBean bal = null;
    	int max = balance.size() - totales.length;
    	this.setActiveTab(tabChanged);
    	int indNivel=-1;
    	boolean isResta;
    	for (int i=0;i<max;i++) {
    		bal = balance.get(i);
    		isResta = bal.getTexto().indexOf("(-)")!=-1 ? true : false;
    		
    		if (bal.getNivel()==1) {
    			indNivel++;
    			if (indNivel<totales.length) {
    				totales[indNivel] = new BigDecimal(0);
    			}
    		} else {
    			if(bal.getValor() == null){
    				totales[indNivel] = totales[indNivel].add(new BigDecimal(0));
    			}else{
    				totales[indNivel] = totales[indNivel].add(isResta ? bal.getValor().negate() : bal.getValor());
    			}
    			
    		}
    		
    		
    	}
    }
    

    /**
     * Modifica un dato del balance.
     * @param tabChanged Pestaña actual
     * */
    public void cambiarBalance(int tabChanged){
    	
    	RegistroBalanceBean bal = null;
    	int max = balance.size() - totales.length;
    	this.totalAP = new BigDecimal[] {new BigDecimal(0),new BigDecimal(0)};
    	this.hayCambios = true; 
    	boolean isResta;
    			
    	Map<String,BigDecimal> mapaValores = new HashMap<String,BigDecimal>();
    	
    	int pos = 0;
    	BigDecimal suma = new BigDecimal(0);
    	int tab = 0;
    	
    	this.setActiveTab(tabChanged);
    	
    	int indNivel=-1;
    	for (int i=0;i<max;i++) {
    		bal = balance.get(i);
    		isResta = bal.getTexto().indexOf("(-)")!=-1 ? true : false;
    		if (bal.getNivel()==1) {
    			indNivel++;
    			if (indNivel<totales.length && !"013".equals(bal.getId()) && !"014".equals(bal.getId())) {
    				totales[indNivel] = new BigDecimal(0);
    			}else{
    				if (bal.isEditable()) {
	    				if(bal.getValor() == null){
	    					totales[indNivel] = new BigDecimal(0);
	    				}else{
	    					totales[indNivel] = bal.getValor();
	    				}
    				}
    			}
    		}else if(bal.getGrupo()==0 && bal.getNivel()==2 && "CUENTAS POR COBRAR".equals(bal.getTexto())){
    			bal.guardarValor(new BigDecimal(0));    			
    		}else {
    			if (bal.isEditable()) {
	    			if(bal.getValor() == null){
	    				bal.setValor(new BigDecimal(0));
	    				totales[indNivel] = totales[indNivel].add(new BigDecimal(0));
	    			}else{
	    				totales[indNivel] = totales[indNivel].add(isResta ? bal.getValor().negate() : bal.getValor());
	    			}
    			}
    		}
    	}
    	
    	
    	for (int i=0;i<max;i++) {
    		
    		bal = balance.get(i);
    		tab = bal.getTab();
    		isResta = bal.getTexto().indexOf("(-)")!=-1 ? true : false;
    		if (bal.getNivel()==1 && !"013".equals(bal.getId()) && !"014".equals(bal.getId())) {
   				
    			if (balance.get(pos).isEditable()){
    				if(balance.get(pos).getValor() == null){
    					suma = new BigDecimal(0);
    				}else{
    					suma = balance.get(pos).getValor();
    				}    				
    			}
    			balance.get(pos).setValor(suma);
    			
    			if(balance.get(pos).getValor() == null && bal.getEstilo() == BalanceController.STYLE_LINEA_TIT ){
        			balance.get(pos).guardarValor(new BigDecimal(0));
        		}
//    			balance.get(pos).getValor().setScale(2,BigDecimal.ROUND_UP);
    			
    			int posResumen = balance.size() - totales.length + balance.get(pos).getGrupo();
    			if(balance.get(posResumen).getValor() == null){
    				balance.get(posResumen).guardarValor(suma);
    			}else{
    				balance.get(posResumen).setValor(suma);
    			}
    			
//    			balance.get(posResumen).getValor().setScale(2,BigDecimal.ROUND_UP);
    			
    			if (suma.compareTo(BigDecimal.ZERO)!=0) {
    				balance.get(pos).setPorcentaje(new BigDecimal(100));
    				balance.get(pos).getPorcentaje().setScale(2,BigDecimal.ROUND_UP);
    			} else {
    				balance.get(pos).setPorcentaje(new BigDecimal(0));
    				balance.get(pos).getPorcentaje().setScale(2,BigDecimal.ROUND_UP);
    			}
   				if(balance.get(pos).getValor() == null){
   					mapaValores.put(balance.get(pos).getId(), new BigDecimal(0));
   				}else{
   					mapaValores.put(balance.get(pos).getId(), balance.get(pos).getValor());
   				}
    			
    			
   				suma = new BigDecimal(0);
    			pos = i;
    			
    		}else if("013".equals(bal.getId()) || "014".equals(bal.getId())){
    			if (balance.get(pos).isEditable()){
    				if(balance.get(pos).getValor() == null){
    					suma = new BigDecimal(0);
    				}else{
    					suma = balance.get(pos).getValor();
    				}    				
    			}
    			balance.get(pos).setValor(suma);
    			
    			if(balance.get(pos).getValor() == null && bal.getEstilo() == BalanceController.STYLE_LINEA_TIT ){
        			balance.get(pos).guardarValor(new BigDecimal(0));
        		}
//    			balance.get(pos).getValor().setScale(2,BigDecimal.ROUND_UP);
    			
    			int posResumen = balance.size() - totales.length + balance.get(pos).getGrupo();
    			if(balance.get(posResumen).getValor() == null){
    				balance.get(posResumen).guardarValor(suma);
    			}else{
    				balance.get(posResumen).setValor(suma);
    			}
    			
//    			balance.get(posResumen).getValor().setScale(2,BigDecimal.ROUND_UP);
    			
    			if (suma.compareTo(BigDecimal.ZERO)!=0) {
    				balance.get(pos).setPorcentaje(new BigDecimal(100));
    				balance.get(pos).getPorcentaje().setScale(2,BigDecimal.ROUND_UP);
    			} else {
    				balance.get(pos).setPorcentaje(new BigDecimal(0));
    				balance.get(pos).getPorcentaje().setScale(2,BigDecimal.ROUND_UP);
    			}
   				if(balance.get(pos).getValor() == null){
   					mapaValores.put(balance.get(pos).getId(), new BigDecimal(0));
   				}else{
   					mapaValores.put(balance.get(pos).getId(), balance.get(pos).getValor());
   				}
    			
    			
   				suma = new BigDecimal(0);
    			pos = i;
    			if(bal.getValor() == null){
    				suma = suma.add(new BigDecimal(0));
    			}else{
    				suma = suma.add(isResta ? bal.getValor().negate() : bal.getValor());
    			}
    			
    			if (totales[bal.getGrupo()].compareTo(BigDecimal.ZERO)!=0) {
    				if(bal.getValor() == null){
    					bal.setPorcentaje(new BigDecimal(0).multiply(new BigDecimal(100)).divide(totales[bal.getGrupo()].abs(),2,BigDecimal.ROUND_UP));
    					mapaValores.put(bal.getId(),new BigDecimal(0));
	    			}else{
	    				bal.setPorcentaje(bal.getValor().multiply(new BigDecimal(100)).divide(totales[bal.getGrupo()].abs(),2,BigDecimal.ROUND_UP));
	    				mapaValores.put(bal.getId(), bal.getValor());
	    			}	    			
    			} else {
    				bal.setPorcentaje(new BigDecimal(0));
    				if(bal.getValor() == null){
	    				mapaValores.put(bal.getId(), bal.getValor());
	    			}else{
	    				mapaValores.put(bal.getId(), bal.getValor());
	    			}
    			}

    			if (tab<2) {
    				if (bal.isEditable()) {
	    				if(bal.getValor() == null){
	    					totalAP[tab] = totalAP[tab].add(new BigDecimal(0));
	    				}else{
	    					totalAP[tab] = totalAP[tab].add(isResta ? bal.getValor().negate() : bal.getValor());
	    				}
    				}
    			} 
    			
    		}else {
    			
    			if (bal.isEditable()) {
	    			
	    			if(bal.getValor() == null){
	    				suma = suma.add(new BigDecimal(0));
	    			} else {
	    				suma = suma.add(isResta ? bal.getValor().negate() : bal.getValor());
	    			}
    			}
    			
    			if (totales[bal.getGrupo()].compareTo(BigDecimal.ZERO)!=0) {
    				if(bal.getValor() == null){
    					bal.setPorcentaje(new BigDecimal(0).multiply(new BigDecimal(100)).divide(totales[bal.getGrupo()].abs(),2,BigDecimal.ROUND_UP));
    					mapaValores.put(bal.getId(),new BigDecimal(0));
	    			}else{
	    				bal.setPorcentaje(bal.getValor().multiply(new BigDecimal(100)).divide(totales[bal.getGrupo()].abs(),2,BigDecimal.ROUND_UP));
	    				mapaValores.put(bal.getId(), bal.getValor());
	    			}	    			
    			} else {
    				bal.setPorcentaje(new BigDecimal(0));
    				if(bal.getValor() == null){
	    				mapaValores.put(bal.getId(), bal.getValor());
	    			}else{
	    				mapaValores.put(bal.getId(), bal.getValor());
	    			}
    			}

    			if (tab<2) {
    				if (bal.isEditable()) {
	    				if(bal.getValor() == null){
	    					totalAP[tab] = totalAP[tab].add(new BigDecimal(0));
	    				}else{
	    					totalAP[tab] = totalAP[tab].add(isResta ? bal.getValor().negate() : bal.getValor());
	    				}
    				}
    			} 
    		}
    		
    	}
    	
    	this.styleDif = (totalAP[0].compareTo(totalAP[1])!=0) ? BalanceController.STYLE_DESCUADRA : BalanceController.STYLE_CUADRA;   
    	
    	balance.get(balance.size()-1).setValor(suma);
//    	balance.get(balance.size()-1).getValor().setScale(2, BigDecimal.ROUND_UP);
    	mapaValores.put(balance.get(balance.size()-1).getId(), balance.get(balance.size()-1).getValor());
    	
    	this.calcularRatios(mapaValores);
    } 
    
    /**
     * Calcula ratios del balance. 
     * */
    private void calcularRatios(Map<String,BigDecimal> valores) {
    
    	BigDecimal result = new BigDecimal(0);
    	if (valores.get(ConstantesFuncionales.CAL_RATIOS.get(1)).compareTo(BigDecimal.ZERO)!=0) {
    		
        	// SOLVENCIA CIRCULANTE
    		result = valores.get(ConstantesFuncionales.CAL_RATIOS.get(0)).divide(
    				valores.get(ConstantesFuncionales.CAL_RATIOS.get(1)),2,BigDecimal.ROUND_UP);
   			balance.get(posRatios[0]).setValor(result);
   			balance.get(balance.size()-ConstantesFuncionales.RATIOS.size()).setValor(result);
   			
   			// ACIDO
   			result = valores.get(ConstantesFuncionales.CAL_RATIOS.get(0)).subtract(
   					valores.get(ConstantesFuncionales.CAL_RATIOS.get(2))).subtract(valores.get(ConstantesFuncionales.CAL_RATIOS.get(3)));
   			result = result.divide(valores.get(ConstantesFuncionales.CAL_RATIOS.get(1)),2,BigDecimal.ROUND_UP);
   			balance.get(posRatios[1]).setValor(result);
	    	balance.get(balance.size()-ConstantesFuncionales.RATIOS.size()+1).setValor(result);
    	}
    }
    
	/**
	 * Obtiene memoria flash para variables en scope view.
	 * @return Metodo utilizado para recuperar una instancia de Flash
	 */
	public Flash obtieneFlash() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
	}
	
	/**
	 * Almacena balance en Host.
	 * @return ruta
	 */
    public String guardarBalance(){
    	this.balanceSel.setDescripcion(this.balanceSel.getDescripcion().replace("#", ""));

		if (this.hayCambios) {
	    	try {
	    		
	    		// Se recompone la estructura para almacenar en BBDD
	    		List<CatalogoBean> catBalance = catalogo.getCatalogo(CatalogoEnum.TP_LIN_BLNCE_ORG);
	    		
	    		List<RegistroBalanceBean> balanceHost = new ArrayList<RegistroBalanceBean>();
	    		RegistroBalanceBean registro;
	    		CatalogoBean cat;
	    		int j=0;
	    		
	    		for (int i=0;i<catBalance.size();i++){
	    			registro = new RegistroBalanceBean();
	    			cat = catBalance.get(i);
	    			
	    			registro.setId(cat.getClaveFila().trim());
	    			registro.setTexto(cat.getDescripcionL().trim());
	    			
	    			if (j<this.balance.size() && registro.getId().equals(this.balance.get(j).getId())) {
	    				registro.guardarValor(this.balance.get(j).getValor());
	    				registro.setPorcentaje(this.balance.get(j).getPorcentaje());
	    				j++;
	    			}
	    			
	    			if (cat.getClaveFila().trim().length()==2) {
	    				if ("01".equals(cat.getClaveFila().trim())) {
	    					registro.guardarValor(totalAP[0]);
	    					registro.setPorcentaje(totalAP[0].intValue()!=0 ? new BigDecimal(100) : BigDecimal.ZERO);
	    				}
	    				if ("02".equals(cat.getClaveFila().trim())) {
	    					BigDecimal total = new BigDecimal(totales[6].floatValue() + totales[7].floatValue() + totales[8].floatValue());
	    					registro.guardarValor(total);
		    				registro.setPorcentaje(total.intValue()!=0 ? new BigDecimal(100) : BigDecimal.ZERO);
	    				}
	    				if ("03".equals(cat.getClaveFila().trim())) {
	    					BigDecimal total = new BigDecimal(totales[9].floatValue() + totales[10].floatValue());
	    					registro.guardarValor(total);
		    				registro.setPorcentaje(total.intValue()!=0 ? new BigDecimal(100) : BigDecimal.ZERO);
	    				}
	    				if ("04".equals(cat.getClaveFila().trim())) {
	    					BigDecimal total = new BigDecimal(totales[11].floatValue() + totales[12].floatValue() + totales[13].floatValue() + totales[14].floatValue() +
	    							totales[15].floatValue() + totales[16].floatValue() + totales[17].floatValue());
	    					registro.guardarValor(total);
		    				registro.setPorcentaje(total.intValue()!=0 ? new BigDecimal(100) : BigDecimal.ZERO);
	    				}
	    			}
	    			
	    			balanceHost.add(registro);
	    		}
	    		
	    
	    		if (BalanceController.IND_ALTA.equals(this.indicador)) {
	    		
	    			altaBackend.ejecutarTRN(balanceHost, this.cliente.getIdInterna(), this.balanceSel.getDescripcion(), this.balanceSel.getAnyo());
	
	    		} else {
	    		
	    			modifBackend.ejecutarTRN(balanceHost, this.cliente.getIdInterna(), this.balanceSel);
	    		}
	    		
	    	}catch (ControlableException ce){
				
	    		LOGGER.debug("Error al intentar realizar el alta del balance");
	
				// TODO: Error en página y return
			}
	    	
	    	RequestContext.getCurrentInstance().execute("PF('dlgConfirmacion').show()");
		} else {
			RequestContext.getCurrentInstance().execute("PF('dlgNoCambios').show()");
		}
    	
    	return "";
    }
    
    /**
     * Obtiene pestañas.
	 * @return lista de pestañas
	 */
	public List<TabBalanceBean> getTabs() {
		return tabs;
	}
	
	/**
	 * Obtiene balance.
	 * @return registros del balance
	 */
    public List<RegistroBalanceBean> getBalance() {
		return balance;
	}
    
	/**
	 * Obtiene pestaña activa.
	 * @return the activeTab
	 */
	public int getActiveTab() {
		return activeTab;
	}

	/**
	 * Almacena pestaña activa.
	 * @param activeTab the activeTab to set
	 */
	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}

	/**
	 * Obtiene totales activo y pasivo.
	 * @return the totalAP
	 */
	public BigDecimal[] getTotalAP() {
		return totalAP;
	}

	/**
	 * Obtiene listado balance.
	 * @return the listaBalances
	 */
	public List<BalanceBean> getListaBalances() {
		return listaBalances;
	}

	/**
	 * Obtiene balance seleccionado.
	 * @return the balanceSel
	 */
	public BalanceBean getBalanceSel() {
		return balanceSel;
	}

	/**
	 * Almacena balance seleccionado.
	 * @param balanceSel the balanceSel to set
	 */
	public void setBalanceSel(BalanceBean balanceSel) {
		this.balanceSel = balanceSel;
	}

	/**
	 * @return the cliente
	 */
	public ClienteBean getCliente() {
		return cliente;
	}

	/**
	 * @return the anyoActual
	 */
	public int getAnyoActual() {
		return anyoActual;
	}

	/**
	 * @param anyoActual the anyoActual to set
	 */
	public void setAnyoActual(int anyoActual) {
		this.anyoActual = anyoActual;
	}

	/**
	 * @return the styleDif
	 */
	public String getStyleDif() {
		return styleDif;
	}

	/**
	 * @return the indicador
	 */
	public String getIndicador() {
		return indicador;
	}
	
	public boolean isAbierto(int grupo){
		if(grupo == 0 || grupo == 3 || grupo == 4 || grupo == 13 || grupo == 15){
			return true;
		}else{
			return false;
		}
	}
		
	
}
