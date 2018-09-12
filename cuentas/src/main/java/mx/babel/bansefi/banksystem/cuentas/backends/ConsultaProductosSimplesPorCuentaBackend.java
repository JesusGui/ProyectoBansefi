package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.utils.ProductosSimplesWrapperUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultaproductossimplesporcuenta.ConsultaProductosSimplesPorCuentaServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaproductossimplesporcuenta.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaproductossimplesporcuenta.Ejecutar.ITRPRESENTACIONACTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.base.webservices.consultaproductossimplesporcuenta.Ejecutar.ITRPRESENTACIONACTRNI.TRPRESENTACIONACEVTY.ACDATOSV;
import mx.babel.bansefi.banksystem.base.webservices.consultaproductossimplesporcuenta.Ejecutar.ITRPRESENTACIONACTRNI.TRPRESENTACIONACEVTY.KAREPOSV;
import mx.babel.bansefi.banksystem.base.webservices.consultaproductossimplesporcuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaproductossimplesporcuenta.ResponseBansefi.OTRPRESENTACIONACTRNO.TRPRESENTACIONACEVTZ.TRCONSVALMSVKPEVTZ.KACDAC1V;
import mx.babel.bansefi.banksystem.base.webservices.consultaproductossimplesporcuenta.ResponseBansefi.OTRPRESENTACIONACTRNO.TRPRESENTACIONACEVTZ.TRCONSVALMSVKPEVTZ.KACDAC1V.KACDACE;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la consulta de productos simples por cuenta.
 *
 * @author joseluis.pena
 *
 */

@Component
public class ConsultaProductosSimplesPorCuentaBackend extends BackEndBean {

	private static final long serialVersionUID = 1516747809634979350L;

	@Autowired
	ProductosSimplesWrapperUtils productosSimplesWrapperUtils;

	@Autowired
    ServicioWebUtils servicioWebUtils;

    public List<ProductoSimpleBean> ejecutarTRN(final Long numCuenta, final TarifaBean tarifaBean){
        List<KACDAC1V> listaRespuesta = new ArrayList<>();

        listaRespuesta = this.ejecutarPaginacion(numCuenta, tarifaBean, listaRespuesta);

        return getResponse(listaRespuesta);
    }

    /**
     * Método que ejecuta la TRN
     * @param cuentaBean
     * @param lista
     * @return Lista de TODOS los productos simples de la cuenta
     */
    public List<KACDAC1V> ejecutarPaginacion(final Long numCuenta, final TarifaBean tarifaBean, final List<KACDAC1V> lista){
        Ejecutar.ITRPRESENTACIONACTRNI contexto = null;

        if(lista.isEmpty()){
            contexto = initPeticion(numCuenta, tarifaBean, null);
        } else {
            contexto = initPeticion(numCuenta, tarifaBean, lista.get(lista.size()-1).getKACDACE());
        }

        final EjecutarResult respuesta = ejecutarWS(contexto);

        try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return lista;
            }
        }
        //SE VERIFICA SI HAY MÁS DATOS QUE CONSULTAR
        if(respuesta.getResponseBansefi().getOTRPRESENTACIONACTRNO().getMOREDATAIN() > 0){
            lista.addAll(respuesta.getResponseBansefi().getOTRPRESENTACIONACTRNO().getTRPRESENTACIONACEVTZ().getTRCONSVALMSVKPEVTZ().getKACDAC1V());
            this.ejecutarPaginacion(numCuenta, tarifaBean, lista);
        } else{
            lista.addAll(respuesta.getResponseBansefi().getOTRPRESENTACIONACTRNO().getTRPRESENTACIONACEVTZ().getTRCONSVALMSVKPEVTZ().getKACDAC1V());
        }

        return lista;
    }
    /**
     * Función que inicializa el objeto para realizar la petición al servicio web.
     * @param numCuenta
     *
     * @return Objeto a ser enviado al servicio web.
     */
    private Ejecutar.ITRPRESENTACIONACTRNI initPeticion(final Long numCuenta, final TarifaBean tarifaBean, final KACDACE kacdace){
        final Ejecutar.ITRPRESENTACIONACTRNI contexto = new Ejecutar.ITRPRESENTACIONACTRNI();


        super.initialize(contexto);
        contexto.setSCROLLABLEOCCURS(50);

        final ACDATOSV acdatosv = contexto.getTRPRESENTACIONACEVTY().getACDATOSV();
        acdatosv.setCODLINEA(tarifaBean.getLinea());
        acdatosv.setIDGRPPD(tarifaBean.getGrupo());
        acdatosv.setIDPDV(tarifaBean.getProducto());
        acdatosv.setIDTRFAPDV(tarifaBean.getId());
        acdatosv.setCODECVAC("1");

        if(null != numCuenta){
        contexto.getTRPRESENTACIONACEVTY().getACACP().setCODNRBEEN(this.getEntidad());
        contexto.getTRPRESENTACIONACEVTY().getACACP().setNUMSECAC(numCuenta);
        }

        final STDTRNIPARMV stdtrniparmv = contexto.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_PRESENTACION_AC_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());
        //PARA EL CASO DE PAGINACIÓN
        if(kacdace != null){
            final KAREPOSV karespov = contexto.getTRPRESENTACIONACEVTY().getKAREPOSV();
            karespov.setIDPARMCD(kacdace.getIDPARMCD().trim());
            karespov.setIDPDS(kacdace.getIDPDS().trim());
        }
        return contexto;
    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     *
     * @param contexto Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(final Ejecutar.ITRPRESENTACIONACTRNI contexto)
            throws NoControlableException{
        EjecutarResult respuesta = null;

        try{
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
                    ConsultaProductosSimplesPorCuentaServicio.class, contexto);
        }catch(final NoControlableException e){
            throw new NoControlableException("Error al invocar servicio web de consulta de "
                    + "productos simples por cuenta.", e);
        }
        return respuesta;
    }

    /**
     * Función encargada de parsear la respuesta del servicio web.
     */
    private List<ProductoSimpleBean> getResponse(final List<KACDAC1V> listaKACDAC1V){
        List<ProductoSimpleBean> listaProdSimples = new ArrayList<ProductoSimpleBean>();

        if(null != listaKACDAC1V){
            listaProdSimples = productosSimplesWrapperUtils.wrappCondicionesDeConsultaProdSimplesPorCuenta(listaKACDAC1V);

        }
        return listaProdSimples;
    }


}
