package mx.babel.bansefi.banksystem.base.backends;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DatosDeudaBienBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DatosSeguroBienBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DatosTerrenoBienBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DatosValuacionBienBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DireccionRegistralBienBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultabienes.ConsultaBienesServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultabienes.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultabienes.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultabienes.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultabienes.ResponseBansefi.OTRPERLBILSTTRNO.TRPERLBILSTEVTZ.BIINGGASLSV;
import mx.babel.bansefi.banksystem.base.webservices.consultabienes.ResponseBansefi.OTRPERLBILSTTRNO.TRPERLBILSTEVTZ.BIOTRBIENLSV;
import mx.babel.bansefi.banksystem.base.wrappers.BienWrapper;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Se encarga de consultar los bienes asociados a un cliente
 * 
 * @author luis.gcastellano
 * 
 */

@Component
public class ConsultaBienesBackEnd extends BackEndBean implements Serializable {


	private static final long serialVersionUID = -1609185830842412315L;

	private static final Logger LOGGER = LogManager
            .getLogger(ConsultaBienesBackEnd.class.getName());

    private static final int SCROLLABLE_OCCURS = 50;
    private static final String EMPTY = "";

    @Autowired
    private CatalogoUtils catalogoUtils;
    
    @Autowired
    BienWrapper bienWrapper;
    
	@Autowired
	ServicioWebUtils servicioWebUtils;


    public List<BienBean> ejectuarTRN(int codigoInternoPersona) {
        LOGGER.debug("Entra al metodo ejectuarTRN");
        List<BienBean> bienesList = this
                .getListaBienesResponse(codigoInternoPersona);
        LOGGER.debug("Sale del metodo ejectuarTRN");
        return bienesList;
    }

    /**
     * Función para obtener una lista de bienes invocando un servicio web.
     * 
     * @param codigo
     *            intrrno de cliente
     * @return Objeto con atributos de los bienes.
     */
    public List<BienBean> getListaBienesResponse(int codigoInternoPersona)
            throws NoControlableException, ControlableException {

        LOGGER.debug("Entra al metodo getListaBienesResponse");
        Ejecutar.ITRPERLBILSTTRNI contexto = this
                .initPeticion(codigoInternoPersona);

        EjecutarResult respuesta = ejecutarWS(contexto);

        List<BienBean> bienesList = null;

        if (verificaResponseBansefi(respuesta)) {
            bienesList = getBienesList(respuesta.getResponseBansefi(), codigoInternoPersona);
        }
        LOGGER.debug("Sale del metodo getListaBienesResponse");
        return bienesList;
    }

    /**
     * Función encargada de obtener la lista de bienes a partir de la respuesta
     * del servicio web
     * 
     * @param response
     *            El objeto de reultado enviado por el servicio web.
     * @return La entidad con los datos recibidos del ser web
     */
    private List<BienBean> getBienesList(ResponseBansefi response,
            int codigoInternoPersona) throws NoControlableException,
            ControlableException {
        LOGGER.debug("Entra al metodo getBienesList");
        BienBean bien = null;
        List<BienBean> bienesList = null;

        if (verificaRespuestaEntidad(response)) {
            bienesList = new ArrayList<BienBean>();
            for (int i = 0; i < response.getOTRPERLBILSTTRNO()
                    .getNUMBEROFRECORDS1V().getNUMBEROFRECORDS(); i++) {
                LOGGER.debug("Se va a añadir un bien de tipo inmueble");
                BIOTRBIENLSV bienInmueble = response.getOTRPERLBILSTTRNO()
                        .getTRPERLBILSTEVTZ().getBIOTRBIENLSV().get(i);
                bien = bienWrapper.wrappBienInmuebleBean(bienInmueble);

                bien.setClase(1);
                bien.setTipo(Integer.parseInt(bien.getTipoCodigo()));
                //Obtenemos la descripcion del bien
                if (StringUtils.isNotBlank(bien.getTipoCodigo())) {
                    CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
                            CatalogoEnum.TP_BIEN, bien.getTipoCodigo());

                    bien.setTipoDesc(catalogo.getDescripcionL());
                }
                bien.setEstado(EstadoListadosEnum.CONSULTADO);
                bien.tipoBien();
                
                DatosDeudaBienBean datosDeuda = new DatosDeudaBienBean();
                DatosSeguroBienBean datosSeguro = new DatosSeguroBienBean();
                DatosValuacionBienBean datosValuacion = new DatosValuacionBienBean();
                DatosTerrenoBienBean datosTerreno = new DatosTerrenoBienBean();
                DireccionRegistralBienBean direccionRegistral = new DireccionRegistralBienBean();
                bien.setDatosDeuda(datosDeuda);

                bien.setDatosSeguro(datosSeguro);
                bien.setDatosTerreno(datosTerreno);
                bien.setDatosValuacion(datosValuacion);
                bien.setDireccionRegistral(direccionRegistral);
                bienesList.add(bien);
            }
            for (int i = 0; i < response.getOTRPERLBILSTTRNO()
                    .getNUMBEROFRECORDS2V().getNUMBEROFRECORDS(); i++) {
                LOGGER.debug("Se va a añadir un bien de tipo ingreso");
                BIINGGASLSV bienIngreso = response.getOTRPERLBILSTTRNO()
                        .getTRPERLBILSTEVTZ().getBIINGGASLSV().get(i);
                bien = bienWrapper.wrappBienIngresoBean(bienIngreso);
                bien.setTipo(Integer.parseInt(bien.getTipoCodigo()));
                //Obtenemos la descripcion del bien
                if (StringUtils.isNotBlank(bien.getTipoCodigo())) {
                    CatalogoBean catalogo = catalogoUtils.getCatalogoBean(
                            CatalogoEnum.TP_BIEN, bien.getTipoCodigo());

                    bien.setTipoDesc(catalogo.getDescripcionL());
                }
                bien.setEstado(EstadoListadosEnum.CONSULTADO);
                bien.setIdInternoPersona(codigoInternoPersona);
                bien.setClase(2);
                bien.tipoBien();
               
                DatosDeudaBienBean datosDeuda = new DatosDeudaBienBean();
                DatosSeguroBienBean datosSeguro = new DatosSeguroBienBean();
                DatosValuacionBienBean datosValuacion = new DatosValuacionBienBean();
                DatosTerrenoBienBean datosTerreno = new DatosTerrenoBienBean();
                DireccionRegistralBienBean direccionRegistral = new DireccionRegistralBienBean();
                bien.setDatosDeuda(datosDeuda);

                bien.setDatosSeguro(datosSeguro);
                bien.setDatosTerreno(datosTerreno);
                bien.setDatosValuacion(datosValuacion);
                bien.setDireccionRegistral(direccionRegistral);
                bienesList.add(bien);
            }
        }
        LOGGER.debug("Sale del metodo getBienesList");
        return bienesList;
    }

    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los
     * datos de bienes
     * 
     * @param response
     *            Respuesta Bansefi con datos de bienes
     * @return <code>true</code> si la respuesta Bansefi contiene datos validos
     */
    private Boolean verificaRespuestaEntidad(ResponseBansefi response) {
        LOGGER.debug("Entra al metodo verificaRespuestaEntidad");
        Boolean noNulo = false;

        if (response != null
                && response.getOTRPERLBILSTTRNO() != null
                && (response.getOTRPERLBILSTTRNO().getLISTA1V() != null || response
                        .getOTRPERLBILSTTRNO().getLISTA2V() != null)
                && response.getOTRPERLBILSTTRNO().getTRPERLBILSTEVTZ() != null
                && (response.getOTRPERLBILSTTRNO().getTRPERLBILSTEVTZ()
                        .getBIINGGASLSV() != null || response
                        .getOTRPERLBILSTTRNO().getTRPERLBILSTEVTZ()
                        .getBIOTRBIENLSV() != null)) {
            noNulo = true;
        }
        LOGGER.debug("Sale del metodo verificaRespuestaEntidad con valor -> "
                + noNulo);
        return noNulo;
    }

    /**
     * Función que valida que la respuesta del servidor no este vacía.
     * 
     * @param respuesta
     *            Objeto respuesta del servicio web
     * @return <code>true</code> si la respuesta no esta vacía.
     */
    private Boolean verificaResponseBansefi(EjecutarResult respuesta) {
        LOGGER.debug("Entra al metodo verificaResponseBansefi");
        Boolean noNulo = false;
        if (respuesta != null && respuesta.getResponseBansefi() != null) {
            noNulo = true;
        }
        LOGGER.debug("Sale del metodo verificaResponseBansefi con valor -> "
                + noNulo);
        return noNulo;
    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRPERLBILSTTRNI contexto)
            throws NoControlableException {
        LOGGER.debug("Entra al metodo ejecutarWS");
        EjecutarResult respuesta = null;
        Ejecutar ejecutar = new Ejecutar();
        ejecutar.setITRPERLBILSTTRNI(contexto);
        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaBienesServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web de consulta de "
                            + "entidades.", e);
        }
        LOGGER.debug("Sale del metodo ejecutarWS");
        return respuesta;
    }

    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio
     * web.
     * 
     * @param object
     *            bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    private Ejecutar.ITRPERLBILSTTRNI initPeticion(int codigoInternoPersona) {
        LOGGER.debug("Entra al metodo initPeticion");
        Ejecutar.ITRPERLBILSTTRNI contexto = new Ejecutar.ITRPERLBILSTTRNI();

        Ejecutar.ITRPERLBILSTTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRPERLBILSTTRNI.STDTRNIPARMV();
        Ejecutar.ITRPERLBILSTTRNI.TRPERLBILSTEVTY trperlbilstevty = new Ejecutar.ITRPERLBILSTTRNI.TRPERLBILSTEVTY();
        Ejecutar.ITRPERLBILSTTRNI.TRPERLBILSTEVTY.PEPERSRLBIENP pepersrlbienp = new Ejecutar.ITRPERLBILSTTRNI.TRPERLBILSTEVTY.PEPERSRLBIENP();

        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PE_RL_BI_LST_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setIDEMPLAUT(ConsultaBienesBackEnd.EMPTY);
        stdtrniparmv.setNUMSEC(1);

        pepersrlbienp.setCODNRBEEN(super.getEntidad());
        pepersrlbienp.setIDINTERNOPE(codigoInternoPersona);
        super.initialize(pepersrlbienp);

        trperlbilstevty.setPEPERSRLBIENP(pepersrlbienp);

        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRPERLBILSTEVTY(trperlbilstevty);
        contexto.setACTIONTYPE(0);
        contexto.setELEVATORPOSITION(0);
        contexto.setSCROLLABLEOCCURS(ConsultaBienesBackEnd.SCROLLABLE_OCCURS);
        contexto.setEVENTCD(3);

        LOGGER.debug("Sale del metodo initPeticion");
        return contexto;
    }

}
