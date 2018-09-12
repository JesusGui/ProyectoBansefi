package mx.babel.bansefi.banksystem.oficina.backends;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.serviciosAppwhere.clients.oficina.OficinaClient;
import mx.babel.arq.serviciosAppwhere.dto.DatosSesionDTO;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.request.oficina.Atributos.ExistenciaDenominacionDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.ResConsultaArqueoCentroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.parrilla.ExistenciaDenominacionBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaexistenciadenominacioncentro.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaexistenciadenominacioncentro.ConsultaExistenciaDenominacionCentroServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaexistenciadenominacioncentro.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.consultaexistenciadenominacioncentro.Ejecutar;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Back End encargado de la consulta de las de existencias de denominaciones en el centro
 *
 * @author mario.montesdeoca
 */
@Component
public class ConsultaExistenciaDenominacionesCentroBackEnd extends BackEndBean {

    private static final long serialVersionUID = 1L;

    private static final String COD_MONEDA = "M";

    @Autowired
    ServicioWebUtils servicioWebUtils;

    @Autowired
    OficinaClient oficinaClient;

    /**
     * Método que consulta las existencias de denominaciones en un centro
     *
     * @return Lista de existencias de denominaciones
     */
    public List<ExistenciaDenominacionBean> ejecutarTRN() {
        List<ExistenciaDenominacionBean> denominaciones = new ArrayList<ExistenciaDenominacionBean>();
        Ejecutar.ITRUOARQUEOOBTEDATOS contexto = initPeticion();
        EjecutarResult respuesta = ejecutarWS(contexto);
        try {
            super.verificaRespuesta(respuesta);
        } catch (ControlableException ce) {
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS) {
                throw ce;
            } else {
                return denominaciones;
            }
        }
        //consultar el arqueo de centro JJVC
        HttpServletRequest requestObj = (HttpServletRequest)
                FacesContext.getCurrentInstance().getExternalContext().getRequest();
        DatosSesionDTO datosSesion =
                (DatosSesionDTO) requestObj.getSession().getAttribute("datosSesion");
        ResConsultaArqueoCentroDTO resConsultaArqueoCentroDTO =
                oficinaClient.consultaArqueoCentro(
                        new ReqGralDTO(
                                datosSesion.getUsuario(),
                                datosSesion.getPassword(),
                                datosSesion.getEntidad(),
                                datosSesion.getVentanilla()));
        if (verificaResponseBansefi(respuesta) && resConsultaArqueoCentroDTO != null && resConsultaArqueoCentroDTO.getArqueo() != null && resConsultaArqueoCentroDTO.getArqueo().size() > 0) {
            denominaciones = getExistenciasDenominaciones(respuesta.getResponseBansefi(), resConsultaArqueoCentroDTO.getArqueo());
        }
        return denominaciones;
    }

    /**
     * Método que construye la lista de existencias de denominaciones a partir de la respuesta del ws
     *
     * @param respuesta del ws
     * @return lista de existencia de denominaciones
     */
    private List<ExistenciaDenominacionBean> getExistenciasDenominaciones(ResponseBansefi respuesta, List<ExistenciaDenominacionDTO> arqueo) {
        List<ExistenciaDenominacionBean> denominaciones = new ArrayList<ExistenciaDenominacionBean>();
        if (verificaRespuesta(respuesta)) {
            //for (UOARQUEOLP denominacion : respuesta.getOTRUOARQUEOOBTEDATOS().getTRUOARQUEOOBTEDATOSEVTZ().getARQUEOLP().getUOARQUEOLP()) {
            for (ExistenciaDenominacionDTO denominacion : arqueo) {
                if (BigDecimal.valueOf(Double.valueOf(denominacion.getImpNominal())).compareTo(BigDecimal.ZERO) != 0) {
                    ExistenciaDenominacionBean existenciaDenominacion = new ExistenciaDenominacionBean();
                    existenciaDenominacion.setFormato("E");
                    existenciaDenominacion.setOrigen(denominacion.getCodDstn());
                    existenciaDenominacion.setValor(oficinaClient.getCodValorFacial().get(denominacion.getCodValorFacial()));
                    existenciaDenominacion.setValorFacial(denominacion.getCodValorFacial());
                    if (COD_MONEDA.equals(denominacion.getCodValorFacial().substring(0, 1))) {
                        existenciaDenominacion.setMoneda(true);
                    } else {
                        existenciaDenominacion.setMoneda(false);
                    }
//                    if (!denominacion.getTOTAL().isEmpty()) {
//                    existenciaDenominacion.setImporteModificable(denominacion.getTOTAL().get(0).getIMPNOMINAL());
                    existenciaDenominacion.setImporteModificable(BigDecimal.valueOf(Double.valueOf(denominacion.getImpNominal())));
                    try {
                        existenciaDenominacion.setUnidades(existenciaDenominacion.getImporteModificable().divide(existenciaDenominacion.getValor()).longValue());
                    }catch (Exception ex){
                        break;
                    }
//                  }
                    denominaciones.add(existenciaDenominacion);
                }
            }
        }
        return denominaciones;
    }

    /**
     * Método para inicializar objeto de petición
     *
     * @return Objeto de petición al ws
     */
    private Ejecutar.ITRUOARQUEOOBTEDATOS initPeticion() {
        Ejecutar.ITRUOARQUEOOBTEDATOS contexto =
                new Ejecutar.ITRUOARQUEOOBTEDATOS();
        Ejecutar.ITRUOARQUEOOBTEDATOS.TRUOARQUEOOBTEDATOSEVTY cuerpoContexto =
                new Ejecutar.ITRUOARQUEOOBTEDATOS.TRUOARQUEOOBTEDATOSEVTY();
        Ejecutar.ITRUOARQUEOOBTEDATOS.TRUOARQUEOOBTEDATOSEVTY.STDAPPLPARMV centro =
                new Ejecutar.ITRUOARQUEOOBTEDATOS.TRUOARQUEOOBTEDATOSEVTY.STDAPPLPARMV();
        contexto.setTRUOARQUEOOBTEDATOSEVTY(cuerpoContexto);
        cuerpoContexto.setSTDAPPLPARMV(centro);
        super.initialize(contexto);

        cuerpoContexto.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);

        centro.setCODNRBEEN(super.getEntidad());
        centro.setCODINTERNOUO(super.getSucursal());

        return contexto;
    }

    /**
     * Método para obtener la respuesta del ws a partir de un objeto de petición
     *
     * @param contexto Objeto de petición
     * @return respuesta del ws
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRUOARQUEOOBTEDATOS contexto) {
        EjecutarResult respuesta = null;
        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
                    ConsultaExistenciaDenominacionCentroServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException("Error al invocar servicio web de consulta "
                    + "de existencias de denominaciones.", e);
        }
        return respuesta;
    }

    /**
     * Función que valida que la respues del servidor no este vacía.
     *
     * @param respuesta Objeto respuesta del servicio web
     * @return <code>true</code> si la respuesta no esta vacía.
     */
    private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
        Boolean noNulo = false;
        if (respuesta != null && respuesta.getResponseBansefi() != null) {
            noNulo = true;
        }
        return noNulo;
    }

    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos
     * de cuentas.
     *
     * @param response Respuesta Bansefi con datos de cuentas
     * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
     */
    private Boolean verificaRespuesta(ResponseBansefi response) {
        Boolean noNulo = false;
        if (response != null && response.getOTRUOARQUEOOBTEDATOS() != null &&
                response.getOTRUOARQUEOOBTEDATOS().getTRUOARQUEOOBTEDATOSEVTZ() != null &&
                response.getOTRUOARQUEOOBTEDATOS().getTRUOARQUEOOBTEDATOSEVTZ().getARQUEOLP() != null &&
                !response.getOTRUOARQUEOOBTEDATOS().getTRUOARQUEOOBTEDATOSEVTZ().getARQUEOLP().getUOARQUEOLP().isEmpty()) {
            noNulo = true;
        }
        return noNulo;
    }

}
