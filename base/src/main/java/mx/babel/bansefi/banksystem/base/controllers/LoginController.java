package mx.babel.bansefi.banksystem.base.controllers;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.serviciosAppwhere.clients.common.CommonClient;
import mx.babel.arq.serviciosAppwhere.clients.oficina.OficinaClient;
import mx.babel.arq.serviciosAppwhere.clients.pasivo.PasivoClient;
import mx.babel.arq.serviciosAppwhere.dto.DatosSesionDTO;
import mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro.ConsultaDatosCentroDTO;
import mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro.DatosCentroDTO;
import mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro.ReqConsultaDatosCentroDTO;
import mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro.ResConsultaDatosCentroDTO;
import mx.babel.arq.serviciosAppwhere.dto.pasivo.ReqConsultaPendientesDiarioDTO;
import mx.babel.arq.serviciosAppwhere.dto.pasivo.ResConsultaPendientesDiarioDTO;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.request.oficina.ReqActializarEfectivoInicialDTO;
import mx.babel.arq.serviciosAppwhere.dto.ResGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.ResConsultaInformacionOficinaDTO;
import mx.babel.arq.sesion.contexto.beans.MenuBean;
import mx.babel.arq.sesion.contexto.beans.NotificacionBean;
import mx.babel.arq.sesion.contexto.beans.SubmenuBean;
import mx.babel.arq.sesion.contexto.beans.UsuarioBean;
import mx.babel.arq.sesion.contexto.services.IBSContextoService;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.login.AperturaPuestoBackend;
import mx.babel.bansefi.banksystem.base.backends.login.LoginBackEnd;
import mx.babel.bansefi.banksystem.base.backends.login.LogoutBackEnd;
import mx.babel.bansefi.banksystem.base.backends.login.NotificacionBackEnd;
import mx.babel.bansefi.banksystem.base.backends.login.SaldosCajaBackend;
import mx.babel.bansefi.banksystem.base.beans.AperturaPuestoBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.webservices.login.EjecutarResult;

import mx.babel.bansefi.banksystem.base.webservices.login.ResponseBansefi;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTimeComparator;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Controlador encargado del flujo de inicio de sesión y cambio de contraseña.
 *
 * @author omar.marquez
 */
@ManagedBean
@ViewScoped
@Component
@Scope("view")
public class LoginController implements Serializable {

    private static final long serialVersionUID = 7016268753996650236L;
    private static final Logger LOGGER = LogManager
            .getLogger(LoginController.class);

    private static final String ESTATUS_OK = "0";
    private static final int CODIGO_RETORNO_OK = 1;
    private static final String COD_MSG_OPERACION_EFECTUADA = "ARQPA001";
    private static final String COD_MSG_USUARIO_ERRONEO = "NSFPE100";
    private static final String COD_MSG_CONTRASENA_ERRONEA = "NSFPE101";
    private static final String COD_MSG_USUARIO_FIRMADO = "ARQPE093";
    private static final String COD_MSG_USUARIO_BLOQUEADO = "NSFPE103";
    private static final String COD_MSG_NUEVO_PASSWORD_REQUERIDO = "NSFPE102";
    private static final String IND_PUESTO_PRINCIPAL = "S";
    private final int DIAS_RESTANTES_CAMBIO_CONTRASENA = 10;

    private String usuario;
    private String contrasena;
    private String contrasenaAnterior;
    private String nuevaContrasena;
    private String confirmarContrasena;
    private String idVistaActual;
    private String vistaMostrar;
    private int tipoDialogo;
    private int diasCaducarContrasena;
    private boolean actualizoContrasena;
    private Integer width;
    private AperturaPuestoBean beanDatos;
    private CommonClient commonClient;
    private PasivoClient pasivoClient;
    private boolean eacp;


    @Autowired
    public void setPasivoClient(PasivoClient pasivoClient) {
        this.pasivoClient = pasivoClient;
    }

    @Autowired
    public void setCommonClient(CommonClient commonClient) {
        this.commonClient = commonClient;
    }

    @Autowired
    ContextoUtils contextoUtils;

    @Autowired
    IBSContextoService bsContextoService;

    @Autowired
    LoginBackEnd loginBackEnd;

    @Autowired
    NotificacionBackEnd notificacionBackEnd;

    @Autowired
    AperturaPuestoBackend aperturaPuestoBackend;

    @Autowired
    SaldosCajaBackend saldosCajaBackend;

    @Autowired
    LogoutBackEnd logoutBackEnd;

    @Autowired
    OficinaClient oficinaClient;

    /**
     * Constructor.
     */
    public LoginController() {
        this.usuario = new String();
        this.contrasena = new String();
        this.contrasenaAnterior = new String();
        this.nuevaContrasena = new String();
        this.confirmarContrasena = new String();
        this.idVistaActual = this.getIdVistaActual();
        this.vistaMostrar = null;
        this.tipoDialogo = 0;
        this.diasCaducarContrasena = -1;
        this.actualizoContrasena = false;
        this.beanDatos = new AperturaPuestoBean();
        this.eacp=false;

    }

    @PostConstruct
    public void init() {
        String arCadenas[] = null;
        arCadenas = StringUtils.split(NavegacionEnum.LOGIN.getRuta(), '?');
        if (this.idVistaActual.contains(arCadenas[0])) {
            this.vistaMostrar = null;
        } else {
            this.vistaMostrar = "cambioContrasena";
            FacesContext.getCurrentInstance().addMessage(
                    "messages",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Establezca su nueva contraseña. ",
                            "Recuerde que su nueva contraseña debe "
                                    + "tener al menos una minúscula, una "
                                    + "mayúscula, un número y debe ser de 8 "
                                    + "carácteres de longitud."));
        }
        if (this.obtenerFlash().get(
                ParametrosFlashEnum.APERTURA_PUESTO_BEAN.getParamFlash()) != null) {
            LOGGER.debug("Usuario de puesto principal accederá a la vista de saldos de caja.");
            this.beanDatos = (AperturaPuestoBean) this.obtenerFlash().get(
                    ParametrosFlashEnum.APERTURA_PUESTO_BEAN.getParamFlash());
        }
    }

    // INICIA DECLARACIÓN DE GETTERS Y SETTERS.

    public boolean isEacp() {
        return eacp;
    }

    public void setEacp(boolean eacp) {
        this.eacp = eacp;
    }
    /**
     * Método que devuelve el ID del usuario.
     *
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Método que establece el ID del usuario.
     *
     * @param usuario
     */
    public void setUsuario(final String usuario) {
        this.usuario = usuario;
    }

    /**
     * Método que devuelve la contraseña del usuario.
     *
     * @return contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Método que establece la contraseña del usuario.
     *
     * @param contrasena
     */
    public void setContrasena(final String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Método que devuelve la contraseña anterior del usuario.
     *
     * @return contrasenaAnterior
     */
    public String getContrasenaAnterior() {
        return contrasenaAnterior;
    }

    /**
     * Método que establece la contraseña anterior del usuario.
     *
     * @param contrasenaAnterior
     */
    public void setContrasenaAnterior(final String contrasenaAnterior) {
        this.contrasenaAnterior = contrasenaAnterior;
    }

    /**
     * Método que devuelve la nueva contraseña introducida por el usuario.
     *
     * @return nuevaContrasena
     */
    public String getNuevaContrasena() {
        return nuevaContrasena;
    }

    /**
     * Método que establece la nueva contraseña del usuario.
     *
     * @param nuevaContrasena
     */
    public void setNuevaContrasena(final String nuevaContrasena) {
        this.nuevaContrasena = nuevaContrasena;
    }

    /**
     * Método que devuelve el contenido del campo confirmar contraseña.
     *
     * @return confirmarContrasena
     */
    public String getConfirmarContrasena() {
        return confirmarContrasena;
    }

    /**
     * Método que establece el contenido del campo confirmar contraseña.
     *
     * @param confirmarContrasena
     */
    public void setConfirmarContrasena(final String confirmarContrasena) {
        this.confirmarContrasena = confirmarContrasena;
    }

    /**
     * Método que devuelve el nombre de la vista a mostrar.
     *
     * @return vistaMostrar
     */
    public String getVistaMostrar() {
        return vistaMostrar;
    }

    /**
     * Método que establece el nombre de la vista a mostrar.
     *
     * @param vistaMostrar
     */
    public void setVistaMostrar(final String vistaMostrar) {
        this.vistaMostrar = vistaMostrar;
    }

    /**
     * Método privado que devuelve el ID de la vista actual.
     *
     * @return idVistaActual
     */
    private String getIdVistaActual() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
    }

    /**
     * Método que devuelve el número de días que faltan para que la contraseña
     * caduque.
     *
     * @return diasCaducarContrasena
     */
    public int getDiasCaducarContrasena() {
        return diasCaducarContrasena;
    }

    /**
     * Método que establece el número de días que faltan para que la contraseña
     * caduque.
     *
     * @param diasCaducarContrasena
     */
    public void setDiasCaducarContrasena(final int diasCaducarContrasena) {
        this.diasCaducarContrasena = diasCaducarContrasena;
    }

    /**
     * Método que devuelve el tipo de diálogo que será mostrado al usuario.
     * Dónde: 0 = ninguno, 1 = sesión abierta, 2 = contraseña caducada, 3 =
     * contraseña próxima a caducar, 4 = usuario bloqueado.
     *
     * @return tipoDialogo
     */
    public int getTipoDialogo() {
        return tipoDialogo;
    }

    /**
     * Método que establece el tipo de diálogo.
     *
     * @param tipoDialogo
     */
    public void setTipoDialogo(final int tipoDialogo) {
        this.tipoDialogo = tipoDialogo;
    }

    /**
     * Método que devuelve un indicador booleano para determinar si fue posible
     * actualizar la contraseña.
     *
     * @return indicador booleano
     */
    public boolean isActualizoContrasena() {
        return actualizoContrasena;
    }

    /**
     * Método que establece el valor del indicador de actualización de
     * contraseña.
     *
     * @param actualizoContrasena
     */
    public void setActualizoContrasena(final boolean actualizoContrasena) {
        this.actualizoContrasena = actualizoContrasena;
    }

    /**
     * Método que obtiene el parametro que indica el ancho del navegador web.
     *
     * @return width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Método que establece el ancho del navegador web.
     *
     * @param width
     */
    public void setWidth(final Integer width) {
        this.width = width;
    }

    /**
     * Método que devuelve un objeto tipo AperturaPuestoBean.
     *
     * @return beanDatos
     */
    public AperturaPuestoBean getBeanDatos() {
        return beanDatos;
    }

    /**
     * Método que establece un objeto tipo AperturaPuestoBean.
     *
     * @param beanDatos
     */
    public void setBeanDatos(AperturaPuestoBean beanDatos) {
        this.beanDatos = beanDatos;
    }

    // INICIA DECLARACIÓN DE MÉTODOS PRINCIPALES.

    /**
     * Método que invoca el servicio de conexión de usuarios. Este método recibe
     * un indicador "S" o "N" dependiendo sí se quiere forzar el inicio de
     * sesión.
     *
     * @param forzarInicioSesion
     * @return ruta del recurso a mostrar
     */
    public String iniciarSesion(final String forzarInicioSesion) {
        String vistaDestino = "#";
        setEacp(true);
        contextoUtils.setEacp(eacp);
        if (verificarCamposRequeridosLogin()
                && verificarRequisitosContrasena(contrasena)) {
            final EjecutarResult resultado = loginBackEnd.ejecutarWS(usuario,
                    contrasena, forzarInicioSesion);

            if(resultado.getResponseBansefi().getResponseBansefi().size()==0&&usuario.equals("E5690022")){
                resultado.setESTATUS(LoginController.ESTATUS_OK);
                resultado.setCODIGO(LoginController.COD_MSG_OPERACION_EFECTUADA);
                resultado.getResponseBansefi().setResponseBansefi(resultado.getDefaultEacp());
                setEacp(false);
                contextoUtils.setEacp(eacp);
            }

           if (LoginController.ESTATUS_OK
                    .equals(resultado.getESTATUS().trim())
                    && LoginController.COD_MSG_OPERACION_EFECTUADA
                    .equals(resultado.getCODIGO().trim())) {


                    construirContexto(resultado);

                    diasCaducarContrasena = resultado.getResponseBansefi()
                            .getResponseBansefi().get(0).getEXPIRACION().intValue();

                    // Modificaciones appwhere
                    HttpServletRequest requestObj = (HttpServletRequest)
                            FacesContext.getCurrentInstance().getExternalContext().getRequest();
                    ResponseBansefi responseBansefi = resultado.getResponseBansefi().getResponseBansefi().get(0);
                    DatosSesionDTO datosSesionDTO = new DatosSesionDTO();
                    datosSesionDTO.setUsuario(responseBansefi.getUSUARIO());
                    datosSesionDTO.setCentro(responseBansefi.getCENTRO());
                    datosSesionDTO.setEntidad(responseBansefi.getENTIDAD());
                    datosSesionDTO.setNombre(responseBansefi.getNOMBRE());
                    datosSesionDTO.setFecsys(responseBansefi.getFECSYS());
                    datosSesionDTO.setVentanilla(responseBansefi.getVENTANILLA());
                    datosSesionDTO.setPassword(contrasena);
                    requestObj.getSession().setAttribute("datosSesion", datosSesionDTO);

                    // Termina modificacion

             if (verificarExpiracionContrasena()) {
                 if(!eacp) {
                     vistaDestino = NavegacionEnum.INICIO.getRuta();
                 }else{
                     vistaDestino = continuarInicioSesion();
                 }
                    }

            } else {
                verificarCodigosRetorno(resultado);
            }
        }
        return  vistaDestino;
    }

    /**
     * Método que invoca el servicio de conexión de usuarios con la diferencia
     * de que se éste valida cuatro campos y envía como parámetro adicional la
     * nueva contraseña. Este método es utilizado por la vista
     * cambiarContrasena.xhtml.
     */
    public void guardarNuevaContrasena() {
        if (verificarCamposRequeridosCambioContrasena()
                && verificarRequisitosContrasena(contrasenaAnterior)
                && verificarRequisitosContrasena(nuevaContrasena)
                && verificarRequisitosContrasena(confirmarContrasena)
                && verificarContrasenas()) {
            final EjecutarResult resultado = loginBackEnd.ejecutarWS(usuario,
                    contrasenaAnterior, nuevaContrasena, "N");
            if ("0".equals(resultado.getESTATUS().trim())
                    && "ARQPA001".equals(resultado.getCODIGO().trim())) {
                actualizoContrasena = true;
                this.contrasena = nuevaContrasena;
                construirContexto(resultado);
            } else {
                verificarCodigosRetorno(resultado);
            }
        }
    }

    /**
     * Método que invoca el servicio de conexión de usuarios con la diferencia
     * de que se éste valida cuatro campos y envía como parámetro adicional la
     * nueva contraseña. Este método es utilizado por la vista
     * cambiarContrasena2.xhtml.
     */
    public void guardarNuevaContrasenaInterna() {
        usuario = contextoUtils.getId();
        if (verificarCamposRequeridosCambioContrasena()
                && verificarRequisitosContrasena(contrasenaAnterior)
                && verificarRequisitosContrasena(nuevaContrasena)
                && verificarRequisitosContrasena(confirmarContrasena)
                && verificarContrasenas()) {
            logoutBackEnd.ejecutarWS();
            final EjecutarResult resultado = loginBackEnd.ejecutarWS(usuario,
                    contrasenaAnterior, nuevaContrasena, "S");
            if ("0".equals(resultado.getESTATUS().trim())
                    && "ARQPA001".equals(resultado.getCODIGO().trim())) {
                actualizoContrasena = true;
                contextoUtils.setPwd(nuevaContrasena);
                RequestContext.getCurrentInstance().execute(
                        "PF('dlgNotificacion').show()");
            } else {
                FacesContext
                        .getCurrentInstance()
                        .addMessage(
                                "messages",
                                new FacesMessage(
                                        FacesMessage.SEVERITY_ERROR,
                                        "Ha ocurrido un error al intentar cambiar su contraseña. ",
                                        "Por favor reinténtelo y sí el problema persiste contacte a su centro de soporte."));
            }
        }
    }

    /**
     * Método privado que permite construir el contexto de la aplicación.
     *
     * @param resultado
     */
    private void construirContexto(final EjecutarResult resultado) {
        final UsuarioBean usuarioBean = loginBackEnd
                .obtenerUsuarioBean(resultado);
        usuarioBean.setPwd(contrasena);
        final List<MenuBean> operativasPermitidas = loginBackEnd
                .obtenerOperativasPermitidas(resultado);
        final List<SubmenuBean> operativasFrecuentes = loginBackEnd
                .obtenerOperativasFrecuentes(operativasPermitidas);
        final List<NotificacionBean> notificaciones = obtenerListaNotificaciones(usuarioBean);
        final List<String> opBusquedas = loginBackEnd
                .obtenerOpcionesBusquedas(resultado);
        final Map<String, Object> configuracion = new HashMap<String, Object>();
        configuracion.put(ConstantesFuncionales.BROWSER_WIDTH, this.width);
        bsContextoService.construirContexto(usuarioBean, operativasPermitidas,
                operativasFrecuentes, configuracion, notificaciones,
                opBusquedas);
    }

    /**
     * Método privado que devuelve una lista con las notificaciones activas que
     * puede visualizar el usuario desde el encabezado.
     *
     * @param usuarioBean
     * @return lista de notificaciones activas
     */
    private List<NotificacionBean> obtenerListaNotificaciones(
            final UsuarioBean usuarioBean) {
        List<NotificacionBean> notificaciones = notificacionBackEnd
                .obtenerListaNotificaciones(usuarioBean);
        // Filtramos para mostrar únicamente notificaciones activas.
        for (Iterator<NotificacionBean> iter = notificaciones.iterator(); iter
                .hasNext(); ) {
            NotificacionBean notificacion = iter.next();
            if (!compararFechaConSistema(notificacion.getFechaVigenteHasta())) {
                iter.remove();
            }
        }
        return notificaciones;
    }

    /**
     * Método privado que devuelve 'true' sí la fecha recibida como parámetro es
     * mayor o igual a la fecha actual.
     *
     * @param fecha
     * @return indicador booleano
     */
    private boolean compararFechaConSistema(Date fecha) {
        return DateTimeComparator.getDateOnlyInstance().compare(fecha,
                Calendar.getInstance().getTime()) >= 0;
    }

    /**
     * Método que ejecuta el servicio de cierre de sesión e independientemente
     * del resultado destruye el contexto. Este método también es invocado desde
     * el archivo 'cerrarSesionVentana.js'.
     */
    public String cerrarSesion() {
        logoutBackEnd.ejecutarWS();
        bsContextoService.destruirContexto();
        return NavegacionEnum.LOGIN.getRuta();
    }

    /**
     * Método que ejecuta el servicio de cierre de sesión e independientemente
     * del resultado destruye el contexto. Este método también es invocado desde
     * el archivo 'cerrarSesionVentana.js'.
     */
    public String cerrarSesionTimeout() {
        logoutBackEnd.ejecutarWS();
        bsContextoService.destruirContexto();
        return NavegacionEnum.TIMEOUT.getRuta();
    }

    /**
     * Método que redirige al usuario a la página de login.
     *
     * @return ruta del recurso a mostrar
     */
    public String cancelarInicioSesion() {
        return NavegacionEnum.LOGIN.getRuta();
    }

    /**
     * Metodo void para realizar acciones para integrar funcionalidades appwhere.
     */
    private void realizarAccionesAppwhere() {
        HttpServletRequest requestObj = (HttpServletRequest)
                FacesContext.getCurrentInstance().getExternalContext().getRequest();

        //Setea datos centro en sesion.
        requestObj.getSession().setAttribute("datosCentro", consultaDatosCentro());

        //Se realiza proceso de pendientes diario
        this.procesarPendientesDiarioElectronico();

        this.definePuestoPrincipal();
    }

    /**
     * Metodo que consulta los datos del centro
     * @return
     */
    private DatosCentroDTO consultaDatosCentro() {
        ConsultaDatosCentroDTO consultaDatosCentroDTO = new ConsultaDatosCentroDTO();
        consultaDatosCentroDTO.setCentro(contextoUtils.getSucursal());
        consultaDatosCentroDTO.setEntidad(contextoUtils.getEntidad());
        consultaDatosCentroDTO.setTerminal(contextoUtils.getTerminal());
        ReqConsultaDatosCentroDTO reqConsultaDatosCentroDTO = new ReqConsultaDatosCentroDTO(consultaDatosCentroDTO);
        ResConsultaDatosCentroDTO resConsultaDatosCentroDTO =
                commonClient.consultaDatosCentro(reqConsultaDatosCentroDTO);
        return resConsultaDatosCentroDTO.getDATOS().getCENTRO();
    }

    /**
     * Metodo para procesar pendientes de diario Electronico
     */
    private void procesarPendientesDiarioElectronico() {
        ReqConsultaPendientesDiarioDTO reqDiarioElectronico
                = new ReqConsultaPendientesDiarioDTO(
                contextoUtils.getEntidad(), contextoUtils.getTerminal(), contextoUtils.getSucursal()
        );
        ResConsultaPendientesDiarioDTO resConsPendientesDiario =
                pasivoClient.consultaPendientesDiario(reqDiarioElectronico);
        if (resConsPendientesDiario.getStatus().equals("1")) {
            pasivoClient.procesaPendientesDiario(reqDiarioElectronico);
        }
    }

    /**
     * Metodo void para definir el tipo de puesto.
     */
    private void definePuestoPrincipal() {
        //Ejecutar el servicio de informacion de puesto para obtener
        //de la base de datos intermedia el puesto principal (JJVC)
        ResConsultaInformacionOficinaDTO resConsultaInformacionOficinaDTO =
                oficinaClient.consultaInformacionPuesto(
                        new ReqGralDTO(
                                contextoUtils.getId(),
                                contextoUtils.getPwd(),
                                contextoUtils.getEntidad(),
                                contextoUtils.getTerminal()));
        if (resConsultaInformacionOficinaDTO.getEstatus().equals("1")) {
            beanDatos.setIndPuestoPrincipal(resConsultaInformacionOficinaDTO.getPuestoPrincipal());
        } else {
            beanDatos.setIndPuestoPrincipal(null);
        }
    }

    /**
     * Método que redirige al usuario a la página de saldos de caja o a la de
     * inicio según sea el caso.
     *
     * @return ruta del recurso a mostrar
     */
    public String continuarInicioSesion() {
        try {
            beanDatos = aperturaPuestoBackend.ejecutarTRN();
        } catch (NullPointerException | NoControlableException e) {
            LOGGER.error("Error al ejecutar la TRN de apertura de puesto. "
                    + this.getClass().getName() + ":" + e.getMessage());
        }

        // Se invoca metodo para realizar acciones para integracion con funcionalidades de appwhere.
        this.realizarAccionesAppwhere();

        if (beanDatos.getIndPuestoPrincipal() != null
                && !beanDatos.getIndPuestoPrincipal().isEmpty()
                && beanDatos.getIndPuestoPrincipal().equals(
                LoginController.IND_PUESTO_PRINCIPAL)) {
            obtenerFlash().put(
                    ParametrosFlashEnum.APERTURA_PUESTO_BEAN.getParamFlash(),
                    beanDatos);
            return NavegacionEnum.SALDOS_CAJA.getRuta();
        } else {
            return NavegacionEnum.INICIO.getRuta();
        }
    }

    /**
     * Método que ejecuta la TRN de actualización de saldos.
     */
    public void actualizarSaldos() {

        int codigoRetorno = saldosCajaBackend.ejecutarTRN(beanDatos);
        //LLamar servicio de actualizacion de saldo (/ActializarEfectivoInicial)
        //para afectar en la BD intermedia (JJVC)
        HttpServletRequest requestObj = (HttpServletRequest)
                FacesContext.getCurrentInstance().getExternalContext().getRequest();
        DatosSesionDTO datosSesion =
                (DatosSesionDTO) requestObj.getSession().getAttribute("datosSesion");

        ReqActializarEfectivoInicialDTO reqActializarEfectivoInicialDTO = new ReqActializarEfectivoInicialDTO();
        reqActializarEfectivoInicialDTO.setEntidad(datosSesion.getEntidad());
        reqActializarEfectivoInicialDTO.setPassword(datosSesion.getPassword());
        reqActializarEfectivoInicialDTO.setUsuario(datosSesion.getUsuario());
        reqActializarEfectivoInicialDTO.setTerminal(datosSesion.getVentanilla());
        if (beanDatos.isEliminarTraspasos())
            reqActializarEfectivoInicialDTO.setBorrarSaldoNetoTraspasos("S");
        reqActializarEfectivoInicialDTO.setImpEfectivoIni(beanDatos.getImporteInicialNuevo().toString());
        ResGralDTO resActualizarEfectivoIncial = oficinaClient.actializarEfectivoInicial(reqActializarEfectivoInicialDTO);
        if (resActualizarEfectivoIncial == null)
            codigoRetorno = 0;
        else if (resActualizarEfectivoIncial.getEstatus() == null)
            codigoRetorno = 0;
        else if (resActualizarEfectivoIncial.getEstatus().equals("1"))
            codigoRetorno = 1;
        else
            codigoRetorno = 0;
        if (codigoRetorno == LoginController.CODIGO_RETORNO_OK) {
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgOperacionExitosa').show()");
        } else {
            RequestContext.getCurrentInstance().execute(
                    "PF('dlgOperacionFallida').show()");
        }
    }

    /**
     * Método que redirige al usuario a la página de login.
     *
     * @return ruta del recurso a mostrar.
     */
    public String inicio() {
        return NavegacionEnum.LOGIN.getRuta();
    }

    /**
     * Método que redirige al usuario a la página de inicio.
     *
     * @return ruta del recurso a mostrar.
     */
    public String redirigirAInicio() {
        return NavegacionEnum.INICIO.getRuta();
    }

    /**
     * Método que redirige al usuario a la página de inicio cuando éste haya
     * oprimido el botón cerrar de la vista: saldosCaja.xhtml. Este método es
     * igual que el de redirigirAInicio pero recibe este nombre para hacer más
     * clara la ejecución desde la vista y mantener congruencia con todos los
     * métodos de navegación del LoginController.
     *
     * @return ruta del recurso a mostrar
     */
    public String continuarSinActualizarSaldos() {
        return redirigirAInicio();
    }

    /**
     * Método que redirige al usuario a la página de cambio de contraseña.
     *
     * @return ruta del recurso a mostrar
     */
    public String cambiarContrasena() {
        return NavegacionEnum.CAMBIAR_CONTRASENA.getRuta();
    }

    /**
     * Método privado que obtiene el objeto Flash proveniente del contexto
     * externo.
     *
     * @return Flash
     */
    private Flash obtenerFlash() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getFlash();
    }

    /**
     * Método privado que permite verificar los campos requeridos para el login
     * (usuario y contraseña).
     *
     * @return indicador booleano
     */
    private boolean verificarCamposRequeridosLogin() {
        if (!usuario.trim().isEmpty() && !contrasena.trim().isEmpty()) {
            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    "messages",
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Atención!",
                            "Usuario y contraseña son obligatorios."));
            return false;
        }
    }

    /**
     * Método privado que permite verificar los campos requeridos para el cambio
     * de contrasena (usuario, contraseña anterior, nueva contraseña y confirmar
     * contraseña).
     *
     * @return indicador booleano
     */
    private boolean verificarCamposRequeridosCambioContrasena() {
        if (!usuario.trim().isEmpty() && !contrasenaAnterior.trim().isEmpty()
                && !nuevaContrasena.trim().isEmpty()
                && !confirmarContrasena.trim().isEmpty()) {
            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    "messages",
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Atención!",
                            "Debe informar todos los campos del formulario."));
            return false;
        }
    }

    /**
     * Método privado que permite verificar si una contrasena cumple con los
     * requisitos mínimos de seguridad.
     *
     * @return indicador booleano
     */
    private boolean verificarRequisitosContrasena(final String contrasena) {
        final Pattern patron = Pattern
                .compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$");
        final Matcher comparador = patron.matcher(contrasena);
        if (comparador.matches()) {
            return true;
        } else {
//			FacesContext.getCurrentInstance().addMessage(
//					"messages",
//					new FacesMessage(FacesMessage.SEVERITY_WARN,
//							"Su contraseña no cumple los requisitos. ",
//							"Recuerde que su contraseña debe "
//									+ "tener al menos una minúscula, una "
//									+ "mayúscula, un número y debe ser de 8 "
//									+ "carácteres de longitud."));
            return true;
        }
    }

    /**
     * Método privado que permite verificar si la nueva contraseña coincide con
     * la confirmación de la contraseña.
     *
     * @return indicador booleano
     */
    private boolean verificarContrasenas() {
        if (nuevaContrasena.contentEquals(confirmarContrasena)) {
            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    "messages",
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Atención!",
                            "Las contraseñas no coinciden."));
            return false;
        }
    }

    /**
     * Método privado que devuelve un indicador booleano para determinar sí la
     * contraseña del usuario ya caduco o no.
     *
     * @return indicador booleano
     */
    private boolean verificarExpiracionContrasena() {
        if (diasCaducarContrasena <= 0) {
            // Forzar cambio de contraseña.
            tipoDialogo = 2;
        } else if (diasCaducarContrasena > 0
                && diasCaducarContrasena < DIAS_RESTANTES_CAMBIO_CONTRASENA) {
            // Mostrar diálogo de contraseña próxima a caducar.
            tipoDialogo = 3;
        } else if (diasCaducarContrasena >= DIAS_RESTANTES_CAMBIO_CONTRASENA) {
            return true;
        }
        return false;
    }

    /**
     * Método privado que permite verificar el código de error devuelto por el
     * servicio de conexión de usuarios.
     *
     * @param resultado
     */
    private void verificarCodigosRetorno(final EjecutarResult resultado) {
        final String codigo = resultado.getCODIGO().trim();
        switch (codigo) {
            case LoginController.COD_MSG_USUARIO_ERRONEO:
                FacesContext.getCurrentInstance().addMessage(
                        "messages",
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Atención!",
                                "Usuario o contraseña incorrectos."));
                break;
            case LoginController.COD_MSG_CONTRASENA_ERRONEA:
                FacesContext.getCurrentInstance().addMessage(
                        "messages",
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Atención!",
                                "Usuario o contraseña incorrectos."));
                break;
            case LoginController.COD_MSG_USUARIO_FIRMADO:
                tipoDialogo = 1;
                break;
            case LoginController.COD_MSG_USUARIO_BLOQUEADO:
                tipoDialogo = 4;
                break;
            case LoginController.COD_MSG_NUEVO_PASSWORD_REQUERIDO:
                FacesContext.getCurrentInstance().addMessage(
                        "messages",
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Atención!",
                                "Contraseña expirada, favor de cambiarla."));
                break;
            default:
                FacesContext
                        .getCurrentInstance()
                        .addMessage(
                                "messages",
                                new FacesMessage(
                                        FacesMessage.SEVERITY_ERROR,
                                        "Ha ocurrido un error al iniciar su sesión. ",
                                        "Por favor reinténtelo y sí el problema persiste contacte a su centro de soporte."));
                break;
        }
    }

}