package mx.babel.arq.serviciosAppwhere.clients.oficina;

import lombok.Getter;
import lombok.Setter;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.serviciosAppwhere.constants.OficinaClientConstant;
import mx.babel.arq.serviciosAppwhere.dto.request.oficina.*;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.ResConsultaTraspasosCentroDTO;
import mx.babel.arq.serviciosAppwhere.dto.ResGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.*;
import mx.babel.arq.serviciosAppwhere.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.json.simple.parser.ParseException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class OficinaClient {

    /*
     * Se definen variables de clase.
     */
    private String uriConsultaInformacionPuesto;
    private String uriActializarEfectivoInicial;
    private String uriActualizarArqueoPuesto;
    private String uriConsultaArqueoPuesto;
    private String uriConsultaTraspasosCentro;
    private String uriConsultaSaldosNetosTerminales;
    private String uriAltaTraspasoFondos;
    private String uriConsultaArqueoCentro;
    private String uriConsultaTotalArqueoPuestos;
    private String uriActualizarArqueoCentro;
    private Util util;
    private static final Logger log = LogManager.getLogger(OficinaClient.class);

    @Setter
    @Getter
    private Map<String, BigDecimal> codValorFacial;
    /*
     * Inyeccion de dependencias
     */
    @Autowired
    public void setUtil(Util util) {
        this.util = util;
    }

    /*
     * Constructor para inicializar uris de los servicios de oficina.
     */
    public OficinaClient() {
        codValorFacial = new HashMap<>();
        codValorFacial.put("B1000", BigDecimal.valueOf(Double.valueOf(1000)));
        codValorFacial.put("B500E", BigDecimal.valueOf(Double.valueOf(500)));
        codValorFacial.put("B200E", BigDecimal.valueOf(Double.valueOf(200)));
        codValorFacial.put("B100E", BigDecimal.valueOf(Double.valueOf(100)));
        codValorFacial.put("B50E ", BigDecimal.valueOf(Double.valueOf(50)));
        codValorFacial.put("B20E ", BigDecimal.valueOf(Double.valueOf(20)));
        codValorFacial.put("M20E ", BigDecimal.valueOf(Double.valueOf(20)));
        codValorFacial.put("M10E ", BigDecimal.valueOf(Double.valueOf(10)));
        codValorFacial.put("M5E  ", BigDecimal.valueOf(Double.valueOf(5)));
        codValorFacial.put("M2E  ", BigDecimal.valueOf(Double.valueOf(2)));
        codValorFacial.put("M1E  ", BigDecimal.valueOf(Double.valueOf(1)));
        codValorFacial.put("M50CT", BigDecimal.valueOf(Double.valueOf(0.50)));
        codValorFacial.put("M20CT", BigDecimal.valueOf(Double.valueOf(0.20)));
        codValorFacial.put("M10CT", BigDecimal.valueOf(Double.valueOf(0.10)));
        codValorFacial.put("M1CT ", BigDecimal.valueOf(Double.valueOf(0.01)));
    }

    public void init() {
        if (this.uriActializarEfectivoInicial == null) {
            this.uriConsultaInformacionPuesto = OficinaClientConstant.CONTEXT +
                            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.uri.asBsfOficinaBancaria.ConsultaInformacionPuesto");
            this.uriActializarEfectivoInicial = OficinaClientConstant.CONTEXT +
                            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.uri.asBsfOficinaBancaria.ActializarEfectivoInicial");
            this.uriActualizarArqueoPuesto = OficinaClientConstant.CONTEXT +
                            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.uri.asBsfOficinaBancaria.ActualizarArqueoPuesto");
            this.uriConsultaArqueoPuesto = OficinaClientConstant.CONTEXT +
                            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.uri.asBsfOficinaBancaria.ConsultaArqueoPuesto");
            this.uriConsultaTraspasosCentro = OficinaClientConstant.CONTEXT +
                            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.uri.asBsfOficinaBancaria.ConsultaTraspasosCentro");
            this.uriConsultaSaldosNetosTerminales = OficinaClientConstant.CONTEXT +
                            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.uri.asBsfOficinaBancaria.ConsultaSaldosNetosTerminales");
            this.uriAltaTraspasoFondos = OficinaClientConstant.CONTEXT +
                            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.uri.asBsfOficinaBancaria.AltaTraspasoFondos");
            this.uriConsultaArqueoCentro = OficinaClientConstant.CONTEXT +
                            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.uri.asBsfOficinaBancaria.ConsultaArqueoCentro");
            this.uriConsultaTotalArqueoPuestos = OficinaClientConstant.CONTEXT +
                            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.uri.asBsfOficinaBancaria.ConsultaTotalArqueoPuestos");
            this.uriActualizarArqueoCentro = OficinaClientConstant.CONTEXT +
                            ProveedorMensajeSpringUtils.getValoresServiciosWeb("rest.uri.asBsfOficinaBancaria.ActualizarArqueoCentro");

        }
    }

    /*
     * Metodo para consumir servicio ConsultaInformacionPuesto
     */
    public ResConsultaInformacionOficinaDTO consultaInformacionPuesto(ReqGralDTO req) {
        init();
        ResConsultaInformacionOficinaDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, uriConsultaInformacionPuesto);
            if (!jsonRes.equals("")) {
                res = new ResConsultaInformacionOficinaDTO();
                res = (ResConsultaInformacionOficinaDTO) this.util.jsonToObject(res, jsonRes, new ArrayList<String>());
                if (res == null) {
                    res = new ResConsultaInformacionOficinaDTO();
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                } else if (res.getEstatus() == null) {
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                }
            } else {
                res = new ResConsultaInformacionOficinaDTO();
                res.setEstatus("-1");
                res.setMensaje("Servicio inaccesible.");
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            res = new ResConsultaInformacionOficinaDTO();
            res.setEstatus("-1");
            res.setMensaje("Error al intentar consumir servicio.");
        }
        return res;
    }

    /*
     * Metodo para consumir servicio ActializarEfectivoInicial
     */
    public ResGralDTO actializarEfectivoInicial(ReqActializarEfectivoInicialDTO req) {
        init();
        ResGralDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, uriActializarEfectivoInicial);
            if (!jsonRes.equals("")) {
                res = new ResGralDTO();
                res = (ResGralDTO) this.util.jsonToObject(res, jsonRes, new ArrayList<String>());
                if (res == null) {
                    res = new ResGralDTO();
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                } else if (res.getEstatus() == null) {
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                }
            } else {
                res = new ResGralDTO();
                res.setEstatus("-1");
                res.setMensaje("Servicio inaccesible.");
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            res = new ResGralDTO();
            res.setEstatus("-1");
            res.setMensaje("Error al intentar consumir servicio.");
        }
        return res;
    }

    /*
     * Metodo para consumir servicio ActualizarArqueoPuesto
     */
    public ResGralDTO actualizarArqueoPuesto(ReqActualizarArqueoPuestoDTO req) {
        init();
        ResGralDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, uriActualizarArqueoPuesto);
            if (!jsonRes.equals("")) {
                res = new ResGralDTO();
                res = (ResGralDTO) this.util.jsonToObject(res, jsonRes, new ArrayList<String>());
                if (res == null) {
                    res = new ResGralDTO();
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                } else if (res.getEstatus() == null) {
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                }
            } else {
                res = new ResGralDTO();
                res.setEstatus("-1");
                res.setMensaje("Servicio inaccesible.");
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            res = new ResGralDTO();
            res.setEstatus("-1");
            res.setMensaje("Error al intentar consumir servicio.");
        }
        return res;
    }

    /*
     * Metodo para consumir servicio ConsultaArqueoPuesto
     */
    public ResConsultaArqueoPuestoDTO consultaArqueoPuesto(ReqGralDTO req) {
        init();
        ResConsultaArqueoPuestoDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, uriConsultaArqueoPuesto);
            if (!jsonRes.equals("")) {
                res = new ResConsultaArqueoPuestoDTO();
                res = (ResConsultaArqueoPuestoDTO) this.util.jsonToObject(res, jsonRes, new ArrayList<String>());
                if (res == null) {
                    res = new ResConsultaArqueoPuestoDTO();
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                } else if (res.getEstatus() == null) {
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                }
            } else {
                res = new ResConsultaArqueoPuestoDTO();
                res.setEstatus("-1");
                res.setMensaje("Servicio inaccesible.");
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            res = new ResConsultaArqueoPuestoDTO();
            res.setEstatus("-1");
            res.setMensaje("Error al intentar consumir servicio.");
        }
        return res;
    }

    /*
     * Metodo para consumir servicio ConsultaTraspasosCentro
     */
    public ResConsultaTraspasosCentroDTO consultaTraspasosCentro(ReqConsultaTraspasosCentroDTO req) {
        init();
        ResConsultaTraspasosCentroDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, uriConsultaTraspasosCentro);
            if (!jsonRes.equals("")) {
                res = new ResConsultaTraspasosCentroDTO();
                res = (ResConsultaTraspasosCentroDTO) this.util.jsonToObject(res, jsonRes, new ArrayList<String>());
                if (res == null) {
                    res = new ResConsultaTraspasosCentroDTO();
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                } else if (res.getEstatus() == null) {
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                }
            } else {
                res = new ResConsultaTraspasosCentroDTO();
                res.setEstatus("-1");
                res.setMensaje("Servicio inaccesible.");
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            res = new ResConsultaTraspasosCentroDTO();
            res.setEstatus("-1");
            res.setMensaje("Error al intentar consumir servicio.");
        }
        return res;
    }

    /*
     * Metodo para consumir servicio ConsultaSaldosNetosTerminales
     */
    public ResConsultaSaldosNetosTerminalesDTO consultaSaldosNetosTerminales(ReqGralDTO req) {
        init();
        ResConsultaSaldosNetosTerminalesDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, uriConsultaSaldosNetosTerminales);
            if (!jsonRes.equals("")) {
                res = new ResConsultaSaldosNetosTerminalesDTO();
                res = (ResConsultaSaldosNetosTerminalesDTO) this.util.jsonToObject(res, jsonRes, new ArrayList<String>());
                if (res == null) {
                    res = new ResConsultaSaldosNetosTerminalesDTO();
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                } else if (res.getEstatus() == null) {
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                }
            } else {
                res = new ResConsultaSaldosNetosTerminalesDTO();
                res.setEstatus("-1");
                res.setMensaje("Servicio inaccesible.");
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            res = new ResConsultaSaldosNetosTerminalesDTO();
            res.setEstatus("-1");
            res.setMensaje("Error al intentar consumir servicio.");
        }
        return res;
    }

    /*
     * Metodo para consumir servicio AltaTraspasoFondos
     */
    public ResGralDTO altaTraspasoFondos(ReqAltaTraspasoFondosDTO req) {
        init();
        ResGralDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, uriAltaTraspasoFondos);
            if (!jsonRes.equals("")) {
                res = new ResGralDTO();
                res = (ResGralDTO) this.util.jsonToObject(res, jsonRes, new ArrayList<String>());
                if (res == null) {
                    res = new ResGralDTO();
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                } else if (res.getEstatus() == null) {
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                }
            } else {
                res = new ResGralDTO();
                res.setEstatus("-1");
                res.setMensaje("Servicio inaccesible.");
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            res = new ResGralDTO();
            res.setEstatus("-1");
            res.setMensaje("Error al intentar consumir servicio.");
        }
        return res;
    }

    /*
     * Metodo para consumir servicio ConsultaArqueoCentro
     */
    public ResConsultaArqueoCentroDTO consultaArqueoCentro(ReqGralDTO req) {
        init();
        ResConsultaArqueoCentroDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, uriConsultaArqueoCentro);
            if (!jsonRes.equals("")) {
                res = new ResConsultaArqueoCentroDTO();
                res = (ResConsultaArqueoCentroDTO) this.util.jsonToObject(res, jsonRes, new ArrayList<String>());
                if (res == null) {
                    res = new ResConsultaArqueoCentroDTO();
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                } else if (res.getEstatus() == null) {
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                }
            } else {
                res = new ResConsultaArqueoCentroDTO();
                res.setEstatus("-1");
                res.setMensaje("Servicio inaccesible.");
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            res = new ResConsultaArqueoCentroDTO();
            res.setEstatus("-1");
            res.setMensaje("Error al intentar consumir servicio.");
        }
        return res;
    }

    /*
     * Metodo para consumir servicio ConsultaTotalArqueoPuestos
     */
    public ResConsultaTotalArqueoPuestosDTO consultaTotalArqueoPuestos(ReqGralDTO req) {
        init();
        ResConsultaTotalArqueoPuestosDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, uriConsultaTotalArqueoPuestos);
            if (!jsonRes.equals("")) {
                res = new ResConsultaTotalArqueoPuestosDTO();
                res = (ResConsultaTotalArqueoPuestosDTO) this.util.jsonToObject(res, jsonRes, new ArrayList<String>());
                if (res == null) {
                    res = new ResConsultaTotalArqueoPuestosDTO();
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                } else if (res.getEstatus() == null) {
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                }
            } else {
                res = new ResConsultaTotalArqueoPuestosDTO();
                res.setEstatus("-1");
                res.setMensaje("Servicio inaccesible.");
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            res = new ResConsultaTotalArqueoPuestosDTO();
            res.setEstatus("-1");
            res.setMensaje("Error al intentar consumir servicio.");
        }
        return res;
    }

    /*
     * Metodo para consumir servicio ActualizarArqueoCentro
     */
    public ResGralDTO actualizarArqueoCentro(ReqActualizarArqueoCentroDTO req) {
        init();
        ResGralDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, uriActualizarArqueoCentro);
            if (!jsonRes.equals("")) {
                res = new ResGralDTO();
                res = (ResGralDTO) this.util.jsonToObject(res, jsonRes, new ArrayList<String>());
                if (res == null) {
                    res = new ResGralDTO();
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                } else if (res.getEstatus() == null) {
                    res.setEstatus("-1");
                    res.setMensaje("Error en servicio.");
                }
            } else {
                res = new ResGralDTO();
                res.setEstatus("-1");
                res.setMensaje("Servicio inaccesible.");
            }
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            res = new ResGralDTO();
            res.setEstatus("-1");
            res.setMensaje("Error al intentar consumir servicio.");
        }
        return res;
    }
}
