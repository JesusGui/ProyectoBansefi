package mx.babel.bansefi.banksystem.oficina.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.parrilla.DenominacionValorComparator;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.oficina.backends.ArqueoCentroBackEnd;
import mx.babel.bansefi.banksystem.oficina.backends.ConsultaExistenciaDenominacionesCentroBackEnd;
import mx.babel.bansefi.banksystem.oficina.backends.ConsultaResultadoArqueoBackEnd;
import mx.babel.bansefi.banksystem.oficina.backends.CuadresPuestosBackEnd;
import mx.babel.bansefi.banksystem.oficina.beans.ArqueoCentroBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador para vistas del flujo de arqueo de centros.
 *
 * @author mario.montesdeoca
 */
@ManagedBean(name = "arqueoCentroController")
@ViewScoped
@Component
@Scope("view")
public class ArqueoCentroController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ContextoUtils contextoUtils;

    @Autowired
    private PdfUtils pdfUtils;

    @Autowired
    CatalogoUtils catalogoUtils;

    @Autowired
    CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;

    @Autowired
    ConsultaExistenciaDenominacionesCentroBackEnd consultaExistenciaDenominacionesCentroBackEnd;
    @Autowired
    ConsultaResultadoArqueoBackEnd consultaResultadoArqueoBackEnd;
    @Autowired
    ArqueoCentroBackEnd arqueoCentroBackEnd;
    @Autowired
    CuadresPuestosBackEnd cuadresPuestosBackEnd;

    private ArqueoCentroBean arqueoCentroBean;
    private PaginacionBean paginacionPuestos;
    private Boolean mostrarPuestos;
    private Boolean muestraDialogo;
    private int resultados;
    private Date fechaActual = new Date();

    /**
     * Clase para incializar bean de arqueo de centro
     */
    @PostConstruct
    public void init() {
        if (this.obtieneFlash().get(ParametrosFlashEnum.ARQUEO_CENTRO_BEAN.getParamFlash()) != null) {
            this.arqueoCentroBean = (ArqueoCentroBean) this.obtieneFlash().get(ParametrosFlashEnum.ARQUEO_CENTRO_BEAN.getParamFlash());
        } else {
            this.arqueoCentroBean = new ArqueoCentroBean();
            this.arqueoCentroBean.setNumeroCentro(contextoUtils.getTerminal().substring(2, 6));
            this.arqueoCentroBean.setExistenciaDenominaciones(consultaExistenciaDenominacionesCentroBackEnd.ejecutarTRN());
            Collections.sort(this.arqueoCentroBean.getExistenciaDenominaciones(), new DenominacionValorComparator());
        }
    }

    /**
     * @return Atributo arqueoCentroBean
     */
    public ArqueoCentroBean getArqueoCentroBean() {
        return arqueoCentroBean;
    }

    /**
     * @param arqueoCentroBean Atributo arqueoCentroBean a definir
     */
    public void setArqueoCentroBean(ArqueoCentroBean arqueoCentroBean) {
        this.arqueoCentroBean = arqueoCentroBean;
    }

    /**
     * @return Atributo paginacionPuestos
     */
    public PaginacionBean getPaginacionPuestos() {
        return paginacionPuestos;
    }

    /**
     * @param paginacionPuestos Atributo paginacionPuestos a definir
     */
    public void setPaginacionPuestos(PaginacionBean paginacionPuestos) {
        this.paginacionPuestos = paginacionPuestos;
    }

    /**
     * @return Atributo mostrarPuestos
     */
    public Boolean getMostrarPuestos() {
        return mostrarPuestos;
    }

    /**
     * @param mostrarPuestos Atributo mostrarPuestos a definir
     */
    public void setMostrarPuestos(Boolean mostrarPuestos) {
        this.mostrarPuestos = mostrarPuestos;
    }

    /**
     * @return Atributo muestraDialogo
     */
    public Boolean getMuestraDialogo() {
        return muestraDialogo;
    }

    /**
     * @param muestraDialogo Atributo muestraDialogo a definir
     */
    public void setMuestraDialogo(Boolean muestraDialogo) {
        this.muestraDialogo = muestraDialogo;
    }

    /**
     * @return Atributo resultados
     */
    public int getResultados() {
        return resultados;
    }

    /**
     * @param resultados Atributo resultados a definir
     */
    public void setResultados(int resultados) {
        this.resultados = resultados;
    }

    /**
     * Mètodo encargado de consultar el resultado del ùltimo arqueo del centro
     */
    public void resultadoArqueo() {
        if (consultaResultadoArqueoBackEnd.ejecutarTRN(this.arqueoCentroBean)) {
            calculaMontos();
            if (this.arqueoCentroBean.getSaldoResultante().
                    add(this.arqueoCentroBean.getSaldoContableCajaActual().negate())
                    .setScale(2, BigDecimal.ROUND_DOWN).compareTo(BigDecimal.ZERO) == 0) {
                this.arqueoCentroBean.setCuadrada(true);
                this.arqueoCentroBean.setRestante(BigDecimal.ZERO);
            } else {
                this.arqueoCentroBean.setCuadrada(false);
                this.arqueoCentroBean.setRestante(this.arqueoCentroBean.getSaldoContableCajaActual().add(this.arqueoCentroBean.getSaldoResultante().negate()));
            }
            setMostrarPuestos(false);
            setMuestraDialogo(false);
            setResultados(10);
        }
    }

    /**
     * Mètodo que calcula los montos del arqueo que no son entregados por el ws
     */
    private void calculaMontos() {
        BigDecimal saldoContableCajaActual = this.arqueoCentroBean.getSaldoContableCajaAnterior();
        saldoContableCajaActual = adicionaBigDecimals(saldoContableCajaActual, this.arqueoCentroBean.getCobrosOn(), true);
        saldoContableCajaActual = adicionaBigDecimals(saldoContableCajaActual, this.arqueoCentroBean.getPagosOn(), false);
        this.arqueoCentroBean.setSaldoContableCajaActual(saldoContableCajaActual);
        BigDecimal saldoResultante = this.arqueoCentroBean.getTotalArqueo();
        this.arqueoCentroBean.setSaldoResultante(saldoResultante);
    }

    /**
     * Mètodo que realizà el arqueo del centro y muestra el resultado del mismo.
     */
    public void arqueaCentro() {
        Date fechaHoraOperacion = new Date();
        this.arqueoCentroBean.setFechaArqueo(fechaHoraOperacion);
        if (arqueoCentroBackEnd.ejecutarTRN(this.arqueoCentroBean)) {
            this.arqueoCentroBean.setFechaArqueo(fechaHoraOperacion);
            arqueoCentroBackEnd.actualizarArqueoCentro(this.arqueoCentroBean, contextoUtils.getFechaContableActual());
            setMuestraDialogo(true);
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "", "Ocurrió un error al realizar el arqueo del centro.");
        }
    }

    /**
     * Mètodo para ocultar el dialogo de èxito
     */
    public void cierraDialogo() {
        setMuestraDialogo(false);
    }

    /**
     * Mètodo para realizar sumas y restas entre BigDecimals
     *
     * @param value  Valor base
     * @param augend Valor a sumar o restarse
     * @param suma   boolean para definir si es suma o resta
     * @return BigDecimal con resultado de la operaciòn.
     */
    public BigDecimal adicionaBigDecimals(BigDecimal value, BigDecimal augend, Boolean suma) {
        if (value != null) {
            if (augend != null) {
                if (suma) {
                    return value.add(augend);
                } else {
                    return value.add(augend.negate());
                }
            }
        }
        return value;
    }

    /**
     * Mètodo para mostrar u ocultar los cuadres de puestos
     */
    public void toogleMostrarPuestos() {
        if (this.arqueoCentroBean != null) {
            if (this.arqueoCentroBean.getCuadrePuestos().isEmpty()) {
                paginacionPuestos = new PaginacionBean();
                this.arqueoCentroBean.setCuadrePuestos(cuadresPuestosBackEnd.ejecutarTRN(arqueoCentroBean, paginacionPuestos));
                this.arqueoCentroBean.setCuadrePuestos(cuadresPuestosBackEnd.obtenerCuadresBDIntermedia(arqueoCentroBean));
            }
        }
        setMostrarPuestos(!mostrarPuestos);
    }

    /**
     * Mètodo para aumentar el nùmero de resultados en tabla de cuadre de puesto.
     */
    public void masResultados() {
        if (this.arqueoCentroBean.getCuadrePuestos().size() > resultados) {
            resultados = resultados + 10;
        }
    }

    /**
     * Mètodo para navegaciòn a inicio
     *
     * @return ruta de inicio
     */
    public String rutaInicio() {
        return NavegacionEnum.INICIO.getRuta();
    }

    /**
     * Mètodo para navegar a desglose de arqueo
     *
     * @return ruta de desglose de arqueo
     */
    public String inicio() {
        return NavegacionEnum.DESGLOSE_ARQUEO_CENTRO.getRuta();
    }

    /**
     * Mètodo para navegar a el resultado de arqueo
     *
     * @return ruta de resultado de arqueo
     */
    public String rutaResultado() {
        this.obtieneFlash().put(ParametrosFlashEnum.ARQUEO_CENTRO_BEAN.getParamFlash(), this.arqueoCentroBean);
        return NavegacionEnum.RESULTADO_ARQUEO_CENTRO.getRuta();
    }

    /**
     * Método para crear un archivo pdf con los datos desplegados en pantalla.
     */
    public void printReport() {
        final Map<String, Object> params = new HashMap<String, Object>();
        String nombreOficina;
        String plazaBancaria;
        try {
            nombreOficina = catalogoCentrosLoaderTask.getCatalogoBean(contextoUtils.getEntidad(), contextoUtils.getSucursal()).getDescripcionL();
        } catch (Exception e) {
            nombreOficina = "";
        }
        try {
            plazaBancaria = catalogoUtils.getCatalogoBean(CatalogoEnum.TP_PLAZA_BANCARIA, contextoUtils.getPlazaBancaria()).getDescripcionL();
        } catch (Exception e) {
            plazaBancaria = "";
        }

        params.put("FECHA_CONTABLE", contextoUtils.getFechaContableActual());
        params.put("FECHA_ARQUEO", fechaActual);

        params.put("OFICINA", nombreOficina);
        params.put("PLAZA", plazaBancaria);

        params.put("TERMINAL", contextoUtils.getTerminal());
        params.put("USUARIO", contextoUtils.getId());

        final Map<String, String> subReportes = new HashMap<String, String>();
        subReportes.put("Denominacion.jrxml", "SUBREPORT_DENOMINACION");

        final Map<String, String> images = new HashMap<String, String>();
        images.put("Logo_bsfi_bn.png", "LOGO");

        final List<ArqueoCentroBean> listaBeans = new ArrayList<ArqueoCentroBean>();
        listaBeans.add(arqueoCentroBean);
        pdfUtils.generaPdf("ArqueoCentro.jrxml", params, images, subReportes, "arqueoCentro", listaBeans);
    }

    /**
     * Se encarga de obtener el flash.
     *
     * @return Flash con los datos de la pagina
     */
    public Flash obtieneFlash() {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash();
    }

    /**
     * Función para adicionar alertas globales en la vista
     *
     * @param severity Severidad de la alerta.
     * @param title    Titulo de la alerta.
     * @param message  Mensaje desplegado en la alerta.
     */
    private void addMessage(FacesMessage.Severity severity, String title, String message) {
        FacesMessage facesMessage = new FacesMessage(severity, title, message);
        FacesContext.getCurrentInstance().addMessage("arqueoCentro", facesMessage);
    }
}
