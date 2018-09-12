package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetallegrpprdvend.ConsultaDetalleGrpPrdVendServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetallegrpprdvend.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetallegrpprdvend.Ejecutar.ITRLISTATVTRNI;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetallegrpprdvend.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetallegrpprdvend.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultadetallegrpprdvend.ResponseBansefi.OTRLISTATVTRNO.TRLISTATVEVTZ.TRLISTATVEVTV;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaDetalleGrpPrdVendWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la consulta de grupos y productos vendibles
 *
 * @author
 *
 */

@Component
public class ConsultaDetalleGrpPrdVendBackend extends BackEndBean {

	private static final long serialVersionUID = -5931583324403862980L;

	private static final Logger LOGGER = LogManager.getLogger(ConsultaDetalleGrpPrdVendBackend.class.getName());

    private static final int CTE_ELEVATOR_POS = 0;
    private static final int CTE_SCROLLABLE_OCCURS = 50;
    private Integer codigoRetorno;

    @Autowired
    ConsultaDetalleGrpPrdVendWrapper consultaDetalleGrpPrdVendWrapper;

    @Autowired
    ServicioWebUtils servicioWebUtils;

    /**
     * Constructor.
     */
    public ConsultaDetalleGrpPrdVendBackend() {
        codigoRetorno = new Integer(-1);
    }

    /**
     * Método que obtiene el código de retorno devuelto por el servicio de
     * consulta de cuentas.
     *
     * @return codigoRetorno
     */
    public Integer getCodigoRetorno() {
        return codigoRetorno;
    }

    /**
     * Método que establece el código de retorno.
     *
     * @param codigoRetorno
     */
    public void setCodigoRetorno(final Integer codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }



    public TarifaBean ejecutarTRN(final TarifaBean tarifaBean){

        final Ejecutar.ITRLISTATVTRNI request = initPeticion(tarifaBean, null);

        final EjecutarResult respuesta = ejecutarWS(request);

        try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return null;
            }
        }

        return getTarifa(respuesta.getResponseBansefi(), tarifaBean);
    }

    private ITRLISTATVTRNI initPeticion(final TarifaBean tarifaBean, final String entidad) {
        final Ejecutar.ITRLISTATVTRNI trlistatvtrni = new Ejecutar.ITRLISTATVTRNI();
        trlistatvtrni.setELEVATORPOSITION(ConsultaDetalleGrpPrdVendBackend.CTE_ELEVATOR_POS);
        trlistatvtrni.setSCROLLABLEOCCURS(ConsultaDetalleGrpPrdVendBackend.CTE_SCROLLABLE_OCCURS);

        final ITRLISTATVTRNI.TRLISTATVEVTY trlistatvevty = new ITRLISTATVTRNI.TRLISTATVEVTY();
        final ITRLISTATVTRNI.TRLISTATVEVTY.TVTRFAPDVP tvtrfapdvp = consultaDetalleGrpPrdVendWrapper
                .wrappDetalleGrpPrdVend(tarifaBean);
        if(StringUtils.isBlank(entidad)){
            tvtrfapdvp.setCODNRBEEN(super.getEntidad());
        }else{
            tvtrfapdvp.setCODNRBEEN(entidad);
        }

        trlistatvevty.setTVTRFAPDVP(tvtrfapdvp);
        trlistatvtrni.setTRLISTATVEVTY(trlistatvevty);

        final ITRLISTATVTRNI.STDTRNIPARMV stdtrniparmv = new ITRLISTATVTRNI.STDTRNIPARMV();
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_LISTA_TV_TRN);
        trlistatvtrni.setSTDTRNIPARMV(stdtrniparmv);

        final Ejecutar.ITRLISTATVTRNI.TRLISTATVEVTY.CTLCODECVV ctlcodecvv = new Ejecutar.ITRLISTATVTRNI.TRLISTATVEVTY.CTLCODECVV();
        this.initialize(ctlcodecvv);
        this.initialize(trlistatvtrni);

        for(int i = 0; i<9; i++){
            trlistatvtrni.getTRLISTATVEVTY().getCTLCODECVV().add(ctlcodecvv);
        }
        return trlistatvtrni;
    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     *
     * @param contexto Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(final Ejecutar.ITRLISTATVTRNI contexto)
            throws NoControlableException{
        EjecutarResult respuesta = null;

        try{
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
                    ConsultaDetalleGrpPrdVendServicio.class, contexto);
        }catch(final NoControlableException e){
            throw new NoControlableException("Error al invocar servicio web de consulta "
                    + "de detalle de grupos y productos vendibles.", e);
        }
        return respuesta;
    }

    private TarifaBean getTarifa(final ResponseBansefi response, final TarifaBean tarifaBean)
            throws NoControlableException, ControlableException{
        TarifaBean respuesta = null;
        if(verificarResultado(response)){
            final List<TRLISTATVEVTV> lista = response.getOTRLISTATVTRNO().getTRLISTATVEVTZ().getTRLISTATVEVTV();
            for(final TRLISTATVEVTV trlistatvevtv:lista){
               if(trlistatvevtv != null && StringUtils.isNotBlank(trlistatvevtv.getIDTRFAPDV())
                   && StringUtils.equals(tarifaBean.getId(), StringUtils.trim(trlistatvevtv.getIDTRFAPDV()))){
                       respuesta = consultaDetalleGrpPrdVendWrapper.wrappDetalleGrpPrdVend(trlistatvevtv);
                        if(respuesta != null){
                            respuesta.setLinea(tarifaBean.getLinea());
                            respuesta.setGrupo(tarifaBean.getGrupo());
                            respuesta.setProducto(tarifaBean.getProducto());
                        }
                        break;
                }
            }
        }
        return respuesta;
    }

    /**
     * Método que ejecuta la transacción de consulta de cuentas a partir de un
     * objeto CuentaBean.
     * @param entidad
     *
     * @param cuentaBean
     * @return cuentaBean con valores de atributos recuperados
     * @throws ControlableException
     * @throws NoControlableException
     */
    public List<TarifaBean> ejecutarTRN(final TarifaBean tarifaBean, final String entidad){
        final List<TarifaBean> result = new CopyOnWriteArrayList<TarifaBean>();
        final Ejecutar.ITRLISTATVTRNI request = initPeticion(tarifaBean, entidad);

        final EjecutarResult resultado = ejecutarWS(request);

        try{
            super.verificaRespuesta(resultado);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                LOGGER.debug("Error en tarifa Linea "+tarifaBean.getLinea()+" Grupo "+tarifaBean.getGrupo()
                        +" Producto "+tarifaBean.getProducto()+" --> Datos no encontrados");
                return result;
            }
        }
        final List<TRLISTATVEVTV> lista = resultado.getResponseBansefi().getOTRLISTATVTRNO().getTRLISTATVEVTZ().getTRLISTATVEVTV();
        TarifaBean tempTarifa = null;
        for(final TRLISTATVEVTV trlistatvevtv:lista){
           if(trlistatvevtv != null &&
               StringUtils.isNotBlank(trlistatvevtv.getCODECVTRFAPDV())
               && StringUtils.equals(ConstantesFuncionales.CTE_TARIFA_ESTADO_COMERCIALIZACION,
                       StringUtils.trim(trlistatvevtv.getCODECVTRFAPDV()))){
               try{
                    tempTarifa = consultaDetalleGrpPrdVendWrapper
                        .wrappDetalleGrpPrdVend(trlistatvevtv);
                    if(tempTarifa != null){
                        tempTarifa.setLinea(tarifaBean.getLinea());
                        tempTarifa.setGrupo(tarifaBean.getGrupo());
                        tempTarifa.setProducto(tarifaBean.getProducto());
                        result.add(tempTarifa);
                    }
                }catch(final ControlableException e){
                    LOGGER.error("Error en consulta de Linea "+tarifaBean.getLinea()+" Grupo "
                        +tarifaBean.getGrupo()+" Producto "+tarifaBean.getProducto()
                        +" --> "+e.getMessage());
                }
            }
        }


        return result;
    }

    /**
     * Método privado que verifica que el resultado obtenido no contenga valores
     * nulos.
     *
     * @param resultado
     * @return indicador booleano para determinar que el resultado no es nulo
     */
    private boolean verificarResultado(final ResponseBansefi resultado) {
        try {
        	return (resultado != null
                && resultado.getOTRLISTATVTRNO() != null
                && resultado.getOTRLISTATVTRNO().getTRLISTATVEVTZ() != null
                && resultado.getOTRLISTATVTRNO().getTRLISTATVEVTZ().getTRLISTATVEVTV() != null
                && !resultado.getOTRLISTATVTRNO().getTRLISTATVEVTZ().getTRLISTATVEVTV().isEmpty() );
        } catch (final NullPointerException e) {
            throw new NoControlableException(
                    "Error al invocar el servicio de consulta de detalle de grupos y productos vendibles.",
                    this.getClass().getName()
                            + ". La respuesta contiene valores nulos.");
        }
    }

}
