package mx.babel.bansefi.banksystem.oficina.controllers;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.serviciosAppwhere.clients.oficina.OficinaClient;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.ResGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.request.oficina.ReqAltaTraspasoFondosDTO;
import mx.babel.arq.serviciosAppwhere.dto.request.oficina.ReqConsultaTraspasosCentroDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.ResConsultaTraspasosCentroDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.ResConsultaInformacionOficinaDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.ResConsultaSaldosNetosTerminalesDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.atributos.SaldoDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.atributos.TraspasoDTO;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.oficina.backends.ConsultaSaldoTerminalesBackEnd;
import mx.babel.bansefi.banksystem.oficina.backends.ConsultaTraspasosBackEnd;
import mx.babel.bansefi.banksystem.oficina.backends.TraspasoEntrePuestosBackend;
import mx.babel.bansefi.banksystem.oficina.beans.*;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador para las vistas de traspasos entre puestos y saldos netos de
 * terminal
 *
 * @author mario.montesdeoca
 */
@ManagedBean(name = "traspasosController")
@Component
@Scope("view")
public class TraspasosController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ContextoUtils contextoUtils;

    @Autowired
    CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;

    @Autowired
    ConsultaTraspasosBackEnd consultaTraspasosBackEnd;

    @Autowired
    TraspasoEntrePuestosBackend traspasoEntrePuestosBackend;

    @Autowired
    ConsultaSaldoTerminalesBackEnd consultaSaldosBackEnd;

    @Autowired
    OficinaClient oficinaClient;

    private ConsultaTraspasoBean consultaTraspasoBean;
    private ConsultaSaldosBean consultaSaldosBean;
    private NavegacionEnum destino;
    private ArqueoPuestoBean arqueoPuestoBean;
    private int resultados;

    // Constante para comparar el código de retorno de la TRN de traspaso.
    private static final int CODIGO_RETORNO_OK = 1;

    // Variables para el traspaso entre puestos
    private String puestoDestino;
    private BigDecimal importeTraspaso;
    private boolean panelResultadoDisponible = false;

    // Constante para comparar el importe contra el mínimo aceptado por la TRN.
    private static final double IMPORTE_MINIMO_TRASPASO = 0.01;

    /**
     * Clase para incializar bean de arqueo de centro
     */
    @PostConstruct
    public void init() {
        this.panelResultadoDisponible = false;
        if (this.obtieneFlash().get(
                ParametrosFlashEnum.CONSULTA_TRASPASO.getParamFlash()) != null) {
            this.consultaTraspasoBean = (ConsultaTraspasoBean) this
                    .obtieneFlash().get(
                            ParametrosFlashEnum.CONSULTA_TRASPASO
                                    .getParamFlash());
        } else {
            this.consultaTraspasoBean = new ConsultaTraspasoBean();
        }
        if (this.obtieneFlash().get(
                ParametrosFlashEnum.NAVEGACION_TRASPASOS.getParamFlash()) != null) {
            this.destino = (NavegacionEnum) this.obtieneFlash().get(
                    ParametrosFlashEnum.NAVEGACION_TRASPASOS.getParamFlash());
            if (this.obtieneFlash().get(
                    ParametrosFlashEnum.ARQUEO_PUESTO_BEAN.getParamFlash()) != null) {
                this.arqueoPuestoBean = (ArqueoPuestoBean) this.obtieneFlash()
                        .get(ParametrosFlashEnum.ARQUEO_PUESTO_BEAN
                                .getParamFlash());
            }
        }
    }

    /**
     * @return Atributo contextoUtils
     */
    public ContextoUtils getContextoUtils() {
        return contextoUtils;
    }

    /**
     * @param contextoUtils Atributo contextoUtils a definir
     */
    public void setContextoUtils(ContextoUtils contextoUtils) {
        this.contextoUtils = contextoUtils;
    }

    /**
     * @return Atributo consultaTraspasoBean
     */
    public ConsultaTraspasoBean getConsultaTraspasoBean() {
        return consultaTraspasoBean;
    }

    /**
     * @param consultaTraspasoBean Atributo consultaTraspasoBean a definir
     */
    public void setConsultaTraspasoBean(
            ConsultaTraspasoBean consultaTraspasoBean) {
        this.consultaTraspasoBean = consultaTraspasoBean;
    }

    /**
     * @return Atributo consultaSaldosBean
     */
    public ConsultaSaldosBean getConsultaSaldosBean() {
        if (consultaSaldosBean == null) {
            consultaSaldosBean = new ConsultaSaldosBean();
        }
        return consultaSaldosBean;
    }

    /**
     * @param consultaSaldosBean Atributo consultaSaldosBean a definir
     */
    public void setConsultaSaldosBean(ConsultaSaldosBean consultaSaldosBean) {
        this.consultaSaldosBean = consultaSaldosBean;
    }

    /**
     * @return Atributo destino
     */
    public NavegacionEnum getDestino() {
        return destino;
    }

    /**
     * @param destino Atributo destino a definir
     */
    public void setDestino(NavegacionEnum destino) {
        this.destino = destino;
    }

    /**
     * @return Atributo arqueoPuestoBean
     */
    public ArqueoPuestoBean getArqueoPuestoBean() {
        return arqueoPuestoBean;
    }

    /**
     * @param arqueoPuestoBean Atributo arqueoPuestoBean a definir
     */
    public void setArqueoPuestoBean(ArqueoPuestoBean arqueoPuestoBean) {
        this.arqueoPuestoBean = arqueoPuestoBean;
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
     * Método que devuelve el puesto destino del traspaso.
     *
     * @return puestoDestino
     */
    public String getPuestoDestino() {
        return puestoDestino;
    }

    /**
     * Método que establece el puesto destino del traspaso.
     *
     * @param puestoDestino
     */
    public void setPuestoDestino(String puestoDestino) {
        this.puestoDestino = puestoDestino;
    }

    /**
     * Método que devuelve el importe del traspaso.
     *
     * @return importeTraspaso
     */
    public BigDecimal getImporteTraspaso() {
        return importeTraspaso;
    }

    /**
     * Método que establece el importe del traspaso.
     *
     * @param importeTraspaso
     */
    public void setImporteTraspaso(BigDecimal importeTraspaso) {
        this.importeTraspaso = importeTraspaso;
    }

    /**
     * Método que devuelve un indicador booleano para mostrar u ocultar parte de
     * la vista: traspasoPuesto.xhtml.
     *
     * @return indicador booleano
     * <p>
     * false --> significa que el usuario podrá visualizar sólo el
     * panelPeticion.
     * <p>
     * true --> significa que el usuario realizó un traspaso de manera
     * exitosa y que se deberá mostrar sólo el panelResultado.
     */
    public boolean isPanelResultadoDisponible() {
        return panelResultadoDisponible;
    }

    /**
     * Método que establece el valor del indicador booleano para mostrar el
     * panel con el resumen del traspaso efectuado.
     *
     * @param panelResultadoDisponible
     */
    public void setPanelResultadoDisponible(boolean panelResultadoDisponible) {
        this.panelResultadoDisponible = panelResultadoDisponible;
    }

    /**
     * Método que devuelve el valor de la constante IMPORTE_MINIMO_TRASPASO.
     *
     * @return IMPORTE_MINIMO_TRASPASO
     */
    public static double getImporteMinimoTraspaso() {
        return IMPORTE_MINIMO_TRASPASO;
    }

    /**
     * Método que consulta los traspasos de un puesto utilizando los filtros de
     * la vista
     */
    public void buscarTraspasos() {
        if (!StringUtils.isEmpty(this.consultaTraspasoBean.getPuesto())) {
            setResultados(10);
            limpiaTraspasos();
            consultaTraspasosBackEnd.ejecutarTRN(this.consultaTraspasoBean);
            limpiaTraspasos();
            //consultar traspasos de la tabla intermedia (JJVC)
            ReqConsultaTraspasosCentroDTO reqConsultaTraspasosCentroDTO = new ReqConsultaTraspasosCentroDTO();
            //Setear datos de sesion
            reqConsultaTraspasosCentroDTO.setUsuario(contextoUtils.getId());
            reqConsultaTraspasosCentroDTO.setPassword(contextoUtils.getPwd());
            reqConsultaTraspasosCentroDTO.setEntidad(contextoUtils.getEntidad());
            reqConsultaTraspasosCentroDTO.setTerminal(contextoUtils.getTerminal());
            //setear datos de la vista
            reqConsultaTraspasosCentroDTO.setNumPuesto(this.consultaTraspasoBean.getPuesto());
            SimpleDateFormat formatoLocalFecha = new SimpleDateFormat("dd/MM/yyyy");
            reqConsultaTraspasosCentroDTO.setFechaPcDesde(formatoLocalFecha.format(this.consultaTraspasoBean.getDesde()));
            reqConsultaTraspasosCentroDTO.setFechaPcHasta(formatoLocalFecha.format(this.consultaTraspasoBean.getHasta()));
            //consumir AS
            ResConsultaTraspasosCentroDTO resConsultaTraspasosCentroDTO = oficinaClient.consultaTraspasosCentro(reqConsultaTraspasosCentroDTO);
            ResConsultaInformacionOficinaDTO resConsultaInformacionOficinaDTO =
                    oficinaClient.consultaInformacionPuesto(
                            new ReqGralDTO(
                                    contextoUtils.getId(),
                                    contextoUtils.getPwd(),
                                    contextoUtils.getEntidad(),
                                    contextoUtils.getTerminal()));
            if (resConsultaTraspasosCentroDTO != null && resConsultaTraspasosCentroDTO.getEstatus() != null && resConsultaTraspasosCentroDTO.getEstatus().equals("1")) {
                if (resConsultaTraspasosCentroDTO.getTraspasos() != null) {
                    setResultados(resConsultaTraspasosCentroDTO.getTraspasos().size());
                    this.consultaTraspasoBean.setMasDatos(false);
                    this.consultaTraspasoBean.setNetoTraspaso(null);
                    this.consultaTraspasoBean.setNumeroDatos(resConsultaTraspasosCentroDTO.getTraspasos().size());
                    this.consultaTraspasoBean.setPagina(0);
                    this.consultaTraspasoBean.setTotalEnviado(null);
                    this.consultaTraspasoBean.setTotalRecibido(null);
                    this.consultaTraspasoBean.setUltimoDatoConsultaAnterior(null);
                    List<TraspasoBean> traspasos = new ArrayList<TraspasoBean>();
                    for (TraspasoDTO element : resConsultaTraspasosCentroDTO.getTraspasos()) {
                        TraspasoBean elementUANL = new TraspasoBean();
                        if (element.getFechaPc().length() == 10) {
                            try {
                                elementUANL.setFecha(formatoLocalFecha.parse(element.getFechaPc()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        elementUANL.setHora(element.getHoraPc().replaceAll(":", ""));
                        elementUANL.setOrigen(element.getNumPuesto());
                        elementUANL.setDestino(element.getNumPuestoDe());
                        elementUANL.setUsuarioOrigen(element.getIdInternoEmplEp());
                        elementUANL.setUsuarioDestino(element.getIdInternoEmplEp2());
                        elementUANL.setImporte(BigDecimal.valueOf(Double.valueOf(element.getImpNominal())));
                        traspasos.add(elementUANL);
                    }
                    if (resConsultaInformacionOficinaDTO != null && resConsultaInformacionOficinaDTO.getImpIni() != null) {
                        this.consultaTraspasoBean.setNetoTraspaso(BigDecimal.valueOf(Double.valueOf(resConsultaInformacionOficinaDTO.getImpIni())));
                    }
                    this.consultaTraspasoBean.setTraspasos(traspasos);
                } else {
                    limpiaTraspasos();
                }
            }
            this.consultaTraspasoBean.actualizaTotales();
        }
    }

    private void limpiaTraspasos() {
        this.consultaTraspasoBean.setMasDatos(false);
        this.consultaTraspasoBean.setNetoTraspaso(null);
        this.consultaTraspasoBean.setNumeroDatos(0);
        this.consultaTraspasoBean.setPagina(0);
        this.consultaTraspasoBean.setTotalEnviado(null);
        this.consultaTraspasoBean.setTotalRecibido(null);
        this.consultaTraspasoBean.setUltimoDatoConsultaAnterior(null);
        this.consultaTraspasoBean.setTraspasos(null);
    }

    /**
     * Método que consulta los saldos netos de los puestos en un centro.
     */
    public void buscarSaldos() {
        setResultados(10);
        consultaSaldosBackEnd.ejecutarTRN(getConsultaSaldosBean());
        //Obtner los saldos de la tabla intermedia (JJVC)
        ResConsultaSaldosNetosTerminalesDTO resConsultaSaldosNetos =
                oficinaClient.consultaSaldosNetosTerminales(
                        new ReqGralDTO(
                                contextoUtils.getId(),
                                contextoUtils.getPwd(),
                                contextoUtils.getEntidad(),
                                contextoUtils.getTerminal()));
        List<SaldoTerminalBean> saldos = new ArrayList<SaldoTerminalBean>();
        if (resConsultaSaldosNetos != null && resConsultaSaldosNetos.getEstatus() != null && resConsultaSaldosNetos.getEstatus().equals("1")) {
            if (resConsultaSaldosNetos.getSaldos() != null) {
                resultados = resConsultaSaldosNetos.getSaldos().size();
                for (SaldoDTO element : resConsultaSaldosNetos.getSaldos()) {
                    SaldoTerminalBean elementUANL = new SaldoTerminalBean();
                    elementUANL.setTerminal(element.getIdInternoTermTn());
                    elementUANL.setPuesto(element.getNumPuesto());
                    elementUANL.setSaldoNeto(BigDecimal.valueOf(Double.valueOf(element.getImpIni())));
                    saldos.add(elementUANL);
                }
            }
            this.consultaSaldosBean.setSaldos(saldos);
        }
    }

    /**
     * Mètodo para aumentar el nùmero de resultados en tabla de traspasos.
     */
    public void masResultados() {
        resultados = resultados + 10;
        if (this.consultaTraspasoBean.getTraspasos().size() < resultados
                && this.consultaTraspasoBean.getMasDatos()) {
            consultaTraspasosBackEnd.ejecutarTRN(this.consultaTraspasoBean);
        }
    }

    /**
     * Mètodo para aumentar el nùmero de resultados en tabla de saldos.
     */
    public void masSaldos() {
        resultados = resultados + 10;
        if (getConsultaSaldosBean().getSaldos().size() < resultados
                && getConsultaSaldosBean().getMasDatos()) {
            consultaSaldosBackEnd.ejecutarTRN(getConsultaSaldosBean());
        }
    }

    /**
     * Mètodo que inicializa los filtros para la consulta de los traspasos.
     */
    public void nuevaConsulta() {
        this.consultaTraspasoBean = new ConsultaTraspasoBean();
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
     * Mètodo para navegaciòn hacia vista anterior
     *
     * @return ruta de destino
     */
    public String regresar() {
        if (this.destino != null) {
            this.obtieneFlash().put(
                    ParametrosFlashEnum.ARQUEO_PUESTO_BEAN.getParamFlash(),
                    this.arqueoPuestoBean);
            return this.destino.getRuta();
        }
        return NavegacionEnum.INICIO.getRuta();
    }

    /**
     * Mètodo para navegar a el resultado de arqueo
     *
     * @return ruta de resultado de arqueo
     */
    public String rutaSaldos() {
        this.obtieneFlash().put(
                ParametrosFlashEnum.CONSULTA_TRASPASO.getParamFlash(),
                this.consultaTraspasoBean);
        if (this.destino != null) {
            this.obtieneFlash().put(
                    ParametrosFlashEnum.ARQUEO_PUESTO_BEAN.getParamFlash(),
                    this.arqueoPuestoBean);
            this.obtieneFlash().put(
                    ParametrosFlashEnum.NAVEGACION_TRASPASOS.getParamFlash(),
                    NavegacionEnum.DETALLE_ARQUEO_PUESTO);
        }
        return NavegacionEnum.SALDOS_NETOS.getRuta();
    }

    /**
     * Mètodo para navegar a el resultado de arqueo
     *
     * @return ruta de resultado de arqueo
     */
    public String inicio() {
        this.obtieneFlash().put(
                ParametrosFlashEnum.CONSULTA_TRASPASO.getParamFlash(),
                this.consultaTraspasoBean);
        return NavegacionEnum.CONSULTA_TRASPASOS.getRuta();
    }

    /**
     * Método para navegar al traspaso entre puestos.
     *
     * @return ruta del recurso a mostrar
     */
    public String inicioDos() {
        return NavegacionEnum.TRASPASO_PUESTO.getRuta();
    }

    /**
     * Método para navegar desde la vista de saldo hacia la de traspasos
     *
     * @return ruta de la vista de traspasos
     */
    public String regresaTraspaso() {
        this.obtieneFlash().put(
                ParametrosFlashEnum.ARQUEO_PUESTO_BEAN.getParamFlash(),
                this.arqueoPuestoBean);
        this.obtieneFlash().put(
                ParametrosFlashEnum.NAVEGACION_TRASPASOS.getParamFlash(),
                this.destino);
        return inicio();
    }

    /**
     * Se encarga de obtener el flash.
     *
     * @return Flash con los datos de la pagina
     */
    public Flash obtieneFlash() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getFlash();
    }

    /**
     * Método que formatea el campo puesto destino antes de que éste sea enviado
     * a la TRN de traspasos entre puestos.
     */
    public void formatearPuestoDestino() {
        if (this.puestoDestino != null && !this.puestoDestino.isEmpty()) {
            this.puestoDestino = String.format("%02d",
                    Integer.parseInt(this.puestoDestino));
        }
    }

    /**
     * Método que devuelve la hora actual.
     *
     * @return hora actual
     */
    public String obtenerHoraActual() {
        DateTime now = DateTime.now();
        return now.toString("HH:mm:ss");
    }

    /**
     * Método que devuelve la descripción asociada al ID de un centro.
     *
     * @return descripción larga
     */
    public String obtenerDescCentro() {
        try {
            return catalogoCentrosLoaderTask.getCatalogoBean(
                    contextoUtils.getEntidad(), contextoUtils.getSucursal())
                    .getDescripcionL();
        } catch (ControlableException | NullPointerException e) {
            return new String();
        }
    }

    /**
     * Método que muestra un mensaje al usuario para que éste confirme el
     * traspaso.
     */
    public void verificarTraspaso() {
        if ((puestoDestino != null && !puestoDestino.isEmpty())
                && (importeTraspaso != null && (importeTraspaso.doubleValue() > TraspasosController.IMPORTE_MINIMO_TRASPASO))) {
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgConfirmarTraspaso').show()");
        }
    }

    /**
     * Método que ejecuta la TRN de traspaso entre puestos.
     *
     * @author omar.marquez
     */
    public void ejecutarTraspaso() {
        try {
            int codigoRetorno = traspasoEntrePuestosBackend.ejecutarTRN(
                    puestoDestino, importeTraspaso);
            //Consumir el alta de traspasos para afectar la BD intermedia (JJVC)
            ReqAltaTraspasoFondosDTO reqAltaTraspasoFondosDTO = new ReqAltaTraspasoFondosDTO();
            reqAltaTraspasoFondosDTO.setUsuario(contextoUtils.getId());
            reqAltaTraspasoFondosDTO.setPassword(contextoUtils.getPwd());
            reqAltaTraspasoFondosDTO.setEntidad(contextoUtils.getEntidad());
            reqAltaTraspasoFondosDTO.setTerminal(contextoUtils.getTerminal());

            SimpleDateFormat formatoLocalFecha = new SimpleDateFormat("dd/MM/yyyy");
            reqAltaTraspasoFondosDTO.setFechaCtble(formatoLocalFecha.format(contextoUtils.getFechaContableActual()));
            reqAltaTraspasoFondosDTO.setIdInternoEmplEp2("");
            reqAltaTraspasoFondosDTO.setImpNominal(importeTraspaso.toString());
            reqAltaTraspasoFondosDTO.setNumPuesto(contextoUtils.getTerminal().substring(6));
            reqAltaTraspasoFondosDTO.setNumPuestoDe(puestoDestino);
            ResGralDTO resTraspaso = oficinaClient.altaTraspasoFondos(reqAltaTraspasoFondosDTO);
//            if (codigoRetorno == TraspasosController.CODIGO_RETORNO_OK) {
            if (resTraspaso != null && resTraspaso.getEstatus() != null && resTraspaso.getEstatus().equals("1")) {
                this.panelResultadoDisponible = true;
                RequestContext.getCurrentInstance().execute(
                        "printContenedorCamposId('printArea')");
            } else {
                this.panelResultadoDisponible = false;
                RequestContext.getCurrentInstance().execute(
                        "PF('dlgOperacionFallida').show()");
            }
        } catch (NullPointerException e) {
            this.panelResultadoDisponible = false;
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgOperacionFallida').show()");
        }
    }

    /**
     * Método que redirige al usuario a la página de arqueo de puesto.
     *
     * @return ruta del recurso a mostrar
     */
    public String redirigirAArqueo() {
        return NavegacionEnum.ARQUEO_PUESTO.getRuta();
    }

    /**
     * Método que permite que el usuario realice un nuevo traspaso.
     */
    public void realizarOtroTraspaso() {
        this.puestoDestino = null;
        this.importeTraspaso = null;
        this.panelResultadoDisponible = false;
    }

    public String getCurrentDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    }

}