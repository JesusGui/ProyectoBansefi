package mx.babel.bansefi.banksystem.oficina.controllers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

import mx.babel.arq.serviciosAppwhere.clients.oficina.OficinaClient;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.request.oficina.Atributos.ExistenciaDenominacionDTO;
import mx.babel.arq.serviciosAppwhere.dto.request.oficina.ReqActualizarArqueoPuestoDTO;
import mx.babel.arq.serviciosAppwhere.dto.request.oficina.ReqConsultaTraspasosCentroDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.ResConsultaTraspasosCentroDTO;
import mx.babel.arq.serviciosAppwhere.dto.ResGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.ResConsultaArqueoPuestoDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.ResConsultaInformacionOficinaDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.atributos.TraspasoDTO;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.arq.storage.annotations.StoreStep;
import mx.babel.arq.storage.beans.StorageMgrBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.DenominacionValorComparator;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoCentrosLoaderTask;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.oficina.backends.ArqueoPuestoBackEnd;
import mx.babel.bansefi.banksystem.oficina.backends.ConsultaParillaPuestoBackEnd;
import mx.babel.bansefi.banksystem.oficina.backends.ConsultaTraspasosBackEnd;
import mx.babel.bansefi.banksystem.oficina.beans.ArqueoPuestoBean;
import mx.babel.bansefi.banksystem.oficina.beans.ConsultaTraspasoBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;

import mx.babel.bansefi.banksystem.oficina.beans.TraspasoBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador de vistas para flujo de arqueo de puesto
 *
 * @author mario.montesdeoca
 */
@ManagedBean(name = "arqueoPuestoController")
@Component
@Scope("view")
public class ArqueoPuestoController extends StorageMgrBean {

    @Autowired
    private ContextoUtils contextoUtils;

    @Autowired
    private PdfUtils pdfUtils;

    @Autowired
    CatalogoUtils catalogoUtils;

    @Autowired
    CatalogoCentrosLoaderTask catalogoCentrosLoaderTask;

    @Autowired
    ArqueoPuestoBackEnd arqueoPuestoBackEnd;
    @Autowired
    ConsultaParillaPuestoBackEnd consultaParillaPuestoBackEnd;
    @Autowired
    ConsultaTraspasosBackEnd consultaTraspasosBackEnd;

    @Autowired
    OficinaClient oficinaClient;

    private ArqueoPuestoBean arqueoPuestoBean;
    private ConsultaTraspasoBean consultaTraspasoBean;
    private Boolean muestraDialogoLimpiar;
    private Boolean muestraDialogoCancelar;
    private Date fechaArqueo = new Date();

    /**
     * Clase para incializar bean de arqueo de puesto
     */
    @PostConstruct
    public void init() {
        super.restoreflash();
        if (this.obtieneFlash().get(ParametrosFlashEnum.ARQUEO_PUESTO_BEAN.getParamFlash()) != null) {
            this.arqueoPuestoBean = (ArqueoPuestoBean) this.obtieneFlash().get(ParametrosFlashEnum.ARQUEO_PUESTO_BEAN.getParamFlash());
        } else {
            this.arqueoPuestoBean = new ArqueoPuestoBean();
            this.arqueoPuestoBean.setPuesto(contextoUtils.getTerminal().substring(6));
        }
        if (this.arqueoPuestoBean.getListaDenominaciones() == null) {
            iniciaListaDenominaciones(true);
        }
        if (this.obtieneFlash().get(ParametrosFlashEnum.CONSULTA_TRASPASO.getParamFlash()) != null) {
            this.consultaTraspasoBean = (ConsultaTraspasoBean) this.obtieneFlash().get(ParametrosFlashEnum.CONSULTA_TRASPASO.getParamFlash());
        }
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
     * @return Atributo muestraDialogoLimpiar
     */
    public Boolean getMuestraDialogoLimpiar() {
        return muestraDialogoLimpiar;
    }

    /**
     * @param muestraDialogoLimpiar Atributo muestraDialogoLimpiar a definir
     */
    public void setMuestraDialogoLimpiar(Boolean muestraDialogoLimpiar) {
        this.muestraDialogoLimpiar = muestraDialogoLimpiar;
    }

    /**
     * @return Atributo consultaTraspasoBean
     */
    public ConsultaTraspasoBean getConsultaTraspasoBean() {
        if (consultaTraspasoBean == null) {
            consultaTraspasoBean = new ConsultaTraspasoBean();
            consultaTraspasoBean.setPuesto(getArqueoPuestoBean().getPuesto());
            consultaTraspasoBean.setDesde(contextoUtils.getFechaContableActual());
            consultaTraspasoBean.setHasta(new Date());
        }
        return consultaTraspasoBean;
    }

    /**
     * @param consultaTraspasoBean Atributo consultaTraspasoBean a definir
     */
    public void setConsultaTraspasoBean(ConsultaTraspasoBean consultaTraspasoBean) {
        this.consultaTraspasoBean = consultaTraspasoBean;
    }

    /**
     * @return Atributo muestraDialogoCancelar
     */
    public Boolean getMuestraDialogoCancelar() {
        return muestraDialogoCancelar;
    }

    /**
     * @param muestraDialogoCancelar Atributo muestraDialogoCancelar a definir
     */
    public void setMuestraDialogoCancelar(Boolean muestraDialogoCancelar) {
        this.muestraDialogoCancelar = muestraDialogoCancelar;
    }

    /**
     * Método para inicializar la lista de denominaciones de la parrilla.
     */
    public void iniciaListaDenominaciones(Boolean consultaParrilla) {
        this.arqueoPuestoBean.setListaDenominaciones(new ArrayList<ExistenciaDenominacionBean>());
        List<String> valores = ConstantesFuncionales.getValorDenominaciones();
        for (String valor : valores) {
            String denominacion = valor.split(":")[0];
            Boolean moneda = valor.split(":")[1].equals("m");
            ExistenciaDenominacionBean existenciaDenominacion = new ExistenciaDenominacionBean();
            existenciaDenominacion.setMoneda(moneda);
            existenciaDenominacion.setValor(new BigDecimal(denominacion));
            existenciaDenominacion.setFormato("E");
            existenciaDenominacion.setOrigen("V");
            this.arqueoPuestoBean.getListaDenominaciones().add(existenciaDenominacion);
        }
        if (consultaParrilla) {
            consultaParillaPuestoBackEnd.ejecutarTRN(this.arqueoPuestoBean.getListaDenominaciones());
            //Vaciar el arreglo de la UANL
            for (ExistenciaDenominacionBean elementUANL : this.arqueoPuestoBean.getListaDenominaciones()) {
                elementUANL.setUnidades(Long.valueOf(0));
                elementUANL.setImporteModificable(BigDecimal.ZERO);
                if(elementUANL.getValor().toString().equals("0.01")){
                    elementUANL.setValorFacial("M1CT ");
                }
            }
            //consumir el listado de arqueo de la BD intermedia (JJCV)
            ResConsultaArqueoPuestoDTO res =
                    oficinaClient.consultaArqueoPuesto(
                            new ReqGralDTO(
                                    contextoUtils.getId(),
                                    contextoUtils.getPwd(),
                                    contextoUtils.getEntidad(),
                                    contextoUtils.getTerminal()));
            if (res != null && res.getEstatus() != null && res.getEstatus().equals("1")) {
                if (res.getArqueo() != null) {
                    for (ExistenciaDenominacionDTO element : res.getArqueo()) {
                        if (element.getUnidades() != null && Integer.valueOf(element.getUnidades()) > 0 && element.getImpNominal() != null && Double.valueOf(element.getImpNominal()) > 0) {
                            for (ExistenciaDenominacionBean elementUANL : this.arqueoPuestoBean.getListaDenominaciones()) {
                                if (elementUANL.getValorFacial().equals(element.getCodValorFacial())) {
                                    elementUANL.setUnidades(Long.valueOf(element.getUnidades()));
                                    elementUANL.setImporteModificable(BigDecimal.valueOf(Double.valueOf(element.getImpNominal())));
                                }
                            }
                        }
                    }
                }
            }
        }
        iniciaUnidades();
        Collections.sort(this.arqueoPuestoBean.getListaDenominaciones(), new DenominacionValorComparator());
    }

    private void iniciaUnidades() {
        for (ExistenciaDenominacionBean existenciaDenominacionBean : this.arqueoPuestoBean.getListaDenominaciones()) {
            actualizaUnidades(existenciaDenominacionBean);
        }
    }

    /**
     * Método que actualiza el importe de una denominación en función a las unidades ingresadas.
     *
     * @param denominacion Denomincación a evaluar
     */
    public void actualizaImporte(ExistenciaDenominacionBean denominacion) {
        if (denominacion.getValor() != null && denominacion.getUnidades() != null) {
            BigDecimal importe = denominacion.getValor().multiply(new BigDecimal(denominacion.getUnidades()));
            denominacion.setImporteModificable(importe);
        }
        actualizaTotalArqueo();
    }

    /**
     * Método que actualiza las unidades de una denominación en función al importe ingresado.
     *
     * @param denominacion DEnomincación a evaluar
     */
    public void actualizaUnidades(ExistenciaDenominacionBean denominacion) {
        if (denominacion.getValor() != null && denominacion.getImporteModificable() != null) {
            Long unidades = denominacion.getImporteModificable().divide(denominacion.getValor()).longValue();
            denominacion.setUnidades(unidades);
        }
        actualizaTotalArqueo();
    }

    /**
     * Método que calcula la longitud máxima de unidades para tener una longitud de importe de 12 digitos con 2 decimales.
     *
     * @param denominacion Denominación a evaluar
     * @return Longitud máxima de digitos
     */
    public Integer cantidadMaxima(ExistenciaDenominacionBean denominacion) {
        if (denominacion.getValor() != null) {
            BigDecimal maxLength = new BigDecimal("100000000000.00");
            BigInteger maxResult = maxLength.divide(denominacion.getValor(), 0, RoundingMode.UP).toBigInteger();
            return maxResult.toString().length();
        }
        return null;
    }

    /**
     * Método que actualiza el total de arqueo en relación a los valores ingresados en la parrilla de denominaciones.
     */
    public void actualizaTotalArqueo() {
        BigDecimal total = new BigDecimal(0);
        for (ExistenciaDenominacionBean denominacion : this.arqueoPuestoBean.getListaDenominaciones()) {
            if (denominacion.getImporteModificable() != null) {
                total = total.add(denominacion.getImporteModificable());
            }
        }
        this.arqueoPuestoBean.setTotalArqueo(total);
    }

    /**
     * Método utilizado para limpiar los valores de unidades e importes en la parrilla de denominaciones.
     */
    public void limpiar() {
        limpiaParrilla();
        setMuestraDialogoLimpiar(false);
    }

    public void limpiaParrilla() {
        for (ExistenciaDenominacionBean denominacion : this.arqueoPuestoBean.getListaDenominaciones()) {
            denominacion.setImporteModificable(null);
            denominacion.setUnidades(null);
        }
        this.arqueoPuestoBean.setTotalArqueo(BigDecimal.ZERO);
    }

    public String getLabelDescuadre() {
        if (this.arqueoPuestoBean.getDescuadre().compareTo(BigDecimal.ZERO) > 0) {
            return "Falta:";
        }
        return "Sobra:";
    }

    /**
     * Método que consulta los traspasos de un puesto utilizando los filtros de la vista
     */
    public void buscarTraspasos() {
        if (!StringUtils.isEmpty(getConsultaTraspasoBean().getPuesto())) {
            consultaTraspasosBackEnd.ejecutarTRN(getConsultaTraspasoBean(), true);
            getConsultaTraspasoBean().actualizaTotales();
            this.arqueoPuestoBean.setNetoTraspasos(getConsultaTraspasoBean().getNetoTraspaso());
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
            reqConsultaTraspasosCentroDTO.setFechaPcDesde(formatoLocalFecha.format(contextoUtils.getFechaContableActual()));
            reqConsultaTraspasosCentroDTO.setFechaPcHasta(formatoLocalFecha.format(contextoUtils.getFechaContableActual()));
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
                        elementUANL.setHora(element.getHoraPc().replaceAll(":",""));
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
                }
            }else{
                this.consultaTraspasoBean.setTraspasos(new ArrayList<TraspasoBean>());
                this.consultaTraspasoBean.setMasDatos(false);
                this.consultaTraspasoBean.setTotalEnviado(null);
                this.consultaTraspasoBean.setTotalRecibido(null);
                this.consultaTraspasoBean.setUltimoDatoConsultaAnterior(null);
            }
            //consultar el saldo neto de traspaso de la tabla intermedia (JJVC)
            BigDecimal netoTraspaso;
            ResConsultaInformacionOficinaDTO res =
                    oficinaClient.consultaInformacionPuesto(
                            new ReqGralDTO(
                                    contextoUtils.getId(),
                                    contextoUtils.getPwd(),
                                    contextoUtils.getEntidad(),
                                    contextoUtils.getTerminal()));
            if (res != null && res.getImpIni() != null)
                netoTraspaso = BigDecimal.valueOf(Double.valueOf(res.getImpIni()));
            else
                netoTraspaso = BigDecimal.valueOf(0);
            getConsultaTraspasoBean().actualizaTotales();
            this.arqueoPuestoBean.setNetoTraspasos(netoTraspaso);
            if (this.arqueoPuestoBean.getDescuadre() != null && this.arqueoPuestoBean.getNetoTraspasos() != null) {
                this.arqueoPuestoBean.setDescuadre(this.arqueoPuestoBean.getDescuadre()
                        .add(this.arqueoPuestoBean.getNetoTraspasos()).setScale(2));
            }
        }
    }

    /**
     * Método que realiza el arqueo de un puesto y redirecciona hacia la vista de resultado de arqueo.
     *
     * @return ruta de detalle del arqueo
     */
    public String arqueaPuesto() {
        if (arqueoPuestoBackEnd.ejecutarTRN(this.arqueoPuestoBean)) {
            if (this.arqueoPuestoBean.getTotalArqueo() != null && this.arqueoPuestoBean.getDifCajaOn() != null) {
                this.arqueoPuestoBean.setDescuadre(
                        this.arqueoPuestoBean.getDifCajaOn().add(this.arqueoPuestoBean.getTotalArqueo().negate()).setScale(2));
            } else {
                this.arqueoPuestoBean.setDescuadre(new BigDecimal(0));
            }
            buscarTraspasos();
            //Consumir servicio del arqueo para mover tablas de BD intermedia (JJVC)
            ReqActualizarArqueoPuestoDTO arqueoPuestoDTO = new ReqActualizarArqueoPuestoDTO();
            arqueoPuestoDTO.setUsuario(contextoUtils.getId());
            arqueoPuestoDTO.setPassword(contextoUtils.getPwd());
            arqueoPuestoDTO.setEntidad(contextoUtils.getEntidad());
            arqueoPuestoDTO.setTerminal(contextoUtils.getTerminal());
            SimpleDateFormat formatoLocalFecha = new SimpleDateFormat("dd/MM/yyyy");
            arqueoPuestoDTO.setFechaCtble(formatoLocalFecha.format(contextoUtils.getFechaContableActual()));
            arqueoPuestoDTO.setCajaDiferencia(this.arqueoPuestoBean.getDifCajaOn().toString());
            arqueoPuestoDTO.setCajaCobrosOn(this.arqueoPuestoBean.getCobroOn().toString());
            arqueoPuestoDTO.setCajaPagosOn(this.arqueoPuestoBean.getPagoOn().toString());
            arqueoPuestoDTO.setIntDiferenciaOn(this.arqueoPuestoBean.getDifCuentaOn().toString());
            arqueoPuestoDTO.setIntCobrosOn(this.arqueoPuestoBean.getDebeOn().toString());
            arqueoPuestoDTO.setIntPagosOn(this.arqueoPuestoBean.getHaberOn().toString());
            arqueoPuestoDTO.setNetoTraspasos(this.arqueoPuestoBean.getNetoTraspasos().toString());
            arqueoPuestoDTO.setTotalArqueo(this.arqueoPuestoBean.getTotalArqueo().toString());
            //recorrer la lista de existencia de denominaciones y setear a ExistenciaDenominacionDTO
            ArrayList<ExistenciaDenominacionDTO> list = new ArrayList<ExistenciaDenominacionDTO>();
            for (ExistenciaDenominacionBean item : this.arqueoPuestoBean.getListaDenominaciones()) {
                if (item.getUnidades() > 0) {
                    ExistenciaDenominacionDTO dto = new ExistenciaDenominacionDTO();
                    dto.setCodDstn(item.getOrigen());
                    String codValorFacial = item.getValorFacial() == null ? "M1CT " : item.getValorFacial();
                    dto.setCodValorFacial(codValorFacial);
                    dto.setImpNominal(item.getImporteModificable().toString());
                    dto.setUnidades(String.valueOf(item.getUnidades()));
                    list.add(dto);
                }
            }
            arqueoPuestoDTO.setArqueo(list);
            //Validar cuadre
            if (this.arqueoPuestoBean.getDescuadre().compareTo(BigDecimal.ZERO) == 0)
                arqueoPuestoDTO.setResultadoCuadre("CAJA CUADRADA");
            else if (this.arqueoPuestoBean.getDescuadre().compareTo(BigDecimal.ZERO) < 0)
                arqueoPuestoDTO.setResultadoCuadre("CAJA DESCUADRADA, SOBRA " + this.arqueoPuestoBean.getDescuadre().negate().toString());
            else if (this.arqueoPuestoBean.getDescuadre().compareTo(BigDecimal.ZERO) > 0)
                arqueoPuestoDTO.setResultadoCuadre("CAJA DESCUADRADA, FALTA " + this.arqueoPuestoBean.getDescuadre().toString());

            ResGralDTO res = oficinaClient.actualizarArqueoPuesto(arqueoPuestoDTO);
            if (res != null && res.getEstatus() != null && res.getEstatus().equals("1"))
                return rutaDetalle();
            else
                return "";
        }
        return "";
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
        params.put("FECHA_ARQUEO", fechaArqueo);

        params.put("OFICINA", nombreOficina);
        params.put("PLAZA", plazaBancaria);

        params.put("TERMINAL", contextoUtils.getTerminal());
        params.put("USUARIO", contextoUtils.getId());

        final Map<String, String> subReportes = new HashMap<String, String>();
        subReportes.put("Denominacion.jrxml", "SUBREPORT_DENOMINACION");

        final Map<String, String> images = new HashMap<String, String>();
        images.put("Logo_bsfi_bn.png", "LOGO");

        final List<ArqueoPuestoBean> listaBeans = new ArrayList<ArqueoPuestoBean>();
        listaBeans.add(arqueoPuestoBean);
        pdfUtils.generaPdf("ArqueoPuesto.jrxml", params, images, subReportes, "arqueoPuesto", listaBeans);
    }

    /**
     * Método que redirecciona al flujo de consulta de traspasos
     *
     * @return ruta de flujo de traspasos
     */
    public String consultaTraspasos() {
        return rutaTraspasos();
    }

    /**
     * Mètodo para navegaciòn a inicio
     *
     * @return ruta de inicio
     */
    @StoreStep
    public String rutaInicio() {
        return NavegacionEnum.INICIO.getRuta();
    }

    /**
     * Mètodo para navegar a el resultado de arqueo
     *
     * @return ruta de resultado de arqueo
     */
    public String rutaDetalle() {
        this.obtieneFlash().put(ParametrosFlashEnum.ARQUEO_PUESTO_BEAN.getParamFlash(), this.arqueoPuestoBean);
        this.obtieneFlash().put(ParametrosFlashEnum.CONSULTA_TRASPASO.getParamFlash(), this.consultaTraspasoBean);
        return NavegacionEnum.DETALLE_ARQUEO_PUESTO.getRuta();
    }

    /**
     * Mètodo para navegar a el resultado de arqueo
     *
     * @return ruta de resultado de arqueo
     */
    @StoreStep
    public String inicio() {
        this.obtieneFlash().put(ParametrosFlashEnum.ARQUEO_PUESTO_BEAN.getParamFlash(), this.arqueoPuestoBean);
        return NavegacionEnum.ARQUEO_PUESTO.getRuta();
    }

    /**
     * Mètodo para navegar a el resultado de arqueo
     *
     * @return ruta de resultado de arqueo
     */
    public String rutaSaldos() {
        this.obtieneFlash().put(ParametrosFlashEnum.ARQUEO_PUESTO_BEAN.getParamFlash(), this.arqueoPuestoBean);
        return NavegacionEnum.SALDOS_NETOS.getRuta();
    }

    /**
     * Mètodo para navegar al flujo de consulta de traspasos
     *
     * @return ruta de resultado de arqueo
     */
    public String rutaTraspasos() {
        this.obtieneFlash().put(ParametrosFlashEnum.ARQUEO_PUESTO_BEAN.getParamFlash(), this.arqueoPuestoBean);
        this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_TRASPASOS.getParamFlash(),
                NavegacionEnum.DETALLE_ARQUEO_PUESTO);
        this.obtieneFlash().put(ParametrosFlashEnum.CONSULTA_TRASPASO.getParamFlash(), consultaTraspasoBean);
        this.obtieneFlash().put(ParametrosFlashEnum.NAVEGACION_TRASPASOS.getParamFlash(),
                NavegacionEnum.DETALLE_ARQUEO_PUESTO);
        return NavegacionEnum.CONSULTA_TRASPASOS.getRuta();
    }

    /**
     * Se encarga de obtener el flash.
     *
     * @return Flash con los datos de la pagina
     */
    public Flash obtieneFlash() {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash();
    }

    @Override
    protected Map<String, Object> getBeanList() {
        Map<String, Object> beanList = new HashMap<String, Object>();
        beanList.put(ArqueoPuestoBean.class.getName(), this.arqueoPuestoBean);
        return beanList;
    }

    @Override
    protected void setBeanList(Map<String, Object> beanList) {
        if (null != beanList) {
            this.arqueoPuestoBean = (ArqueoPuestoBean) beanList.get(ArqueoPuestoBean.class.getName());
            obtieneFlash().put(ParametrosFlashEnum.ARQUEO_PUESTO_BEAN.getParamFlash(), this.arqueoPuestoBean);
        }
    }

    @Override
    protected String getNombreFlujo() {
        return "Arqueo de Puesto: " + contextoUtils.getTerminal();
    }

}
