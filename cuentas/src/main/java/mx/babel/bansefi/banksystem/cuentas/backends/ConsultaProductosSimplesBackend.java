package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaproductossimples.ConsultaProductosSimplesServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaproductossimples.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaproductossimples.Ejecutar.ITRLISTARLPVPSTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaproductossimples.Ejecutar.ITRLISTARLPVPSTRNI.TRLISTARLPVPSEVTY.PVPDVRLPDSP;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaproductossimples.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.consultaproductossimples.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.ConsultaProductosSimplesWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la consulta de productos simples
 *
 * @author
 *
 */

@Component
public class ConsultaProductosSimplesBackend extends BackEndBean {

	private static final long serialVersionUID = 1516747809634979350L;
	
	@Autowired
    ServicioWebUtils servicioWebUtils;

   @Autowired
    private ConsultaProductosSimplesWrapper consultaProductosSimplesWrapper;

    public List<ProductoSimpleBean> ejecutarTRN(final TarifaBean tarifaBean){
        List<ProductoSimpleBean> productosSimples = new ArrayList<ProductoSimpleBean>();
        final Ejecutar.ITRLISTARLPVPSTRNI contexto = initPeticion(tarifaBean);

        final EjecutarResult respuesta = ejecutarWS(contexto);

        try{
            super.verificaRespuesta(respuesta);
        }catch (final ControlableException ce){
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS){
                throw ce;
            }else{
                return productosSimples;
            }
        }
        
        return getResponse(respuesta.getResponseBansefi());
    }

    /**
     * Función que inicializa el objeto para realizar la petición al servicio web.
     * @param numCuenta
     *
     * @return Objeto a ser enviado al servicio web.
     */
    private Ejecutar.ITRLISTARLPVPSTRNI initPeticion(final TarifaBean tarifaBean){
        final Ejecutar.ITRLISTARLPVPSTRNI contexto = new Ejecutar.ITRLISTARLPVPSTRNI();


        super.initialize(contexto);
        contexto.setSCROLLABLEOCCURS(50);
        contexto.setELEVATORPOSITION(1);
        final PVPDVRLPDSP pvpdvrlpdsp = contexto.getTRLISTARLPVPSEVTY().getPVPDVRLPDSP();
        pvpdvrlpdsp.setCODNRBEEN(this.getEntidad());
        pvpdvrlpdsp.setCODLINEA(tarifaBean.getLinea());
        pvpdvrlpdsp.setIDGRPPD(tarifaBean.getGrupo());
        pvpdvrlpdsp.setIDPDV(tarifaBean.getProducto());


        final STDTRNIPARMV stdtrniparmv = contexto.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_LISTA_RL_PV_PS_TRN);

        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

        return contexto;
    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     *
     * @param contexto Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(final Ejecutar.ITRLISTARLPVPSTRNI contexto)
            throws NoControlableException{
        EjecutarResult respuesta = null;

        try{
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
                    ConsultaProductosSimplesServicio.class, contexto);
        }catch(final NoControlableException e){
            throw new NoControlableException("Error al invocar servicio web de consulta de "
                    + "productos simples.", e);
        }
        return respuesta;
    }

    /**
     * Función encargada de parsear la respuesta del servicio web.
     */
    private List<ProductoSimpleBean> getResponse(final ResponseBansefi response)
            throws NoControlableException, ControlableException{
        List<ProductoSimpleBean> prodSimplesLista = new ArrayList<ProductoSimpleBean>();
        
        if(verificaRespuesta(response)){
            prodSimplesLista = consultaProductosSimplesWrapper.wrapProductoSimple(
                    response.getOTRLISTARLPVPSTRNO().getTRLISTARLPVPSEVTZ());
        }

        return prodSimplesLista;
    }

    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos
     * del alta de realción.
     *
     * @param response Respuesta Bansefi con datos del alta
     * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
     */
    private Boolean verificaRespuesta(final ResponseBansefi response){
        Boolean noNulo = false;
        if(response != null && response.getOTRLISTARLPVPSTRNO() != null &&
                response.getOTRLISTARLPVPSTRNO().getTRLISTARLPVPSEVTZ() != null &&
                !response.getOTRLISTARLPVPSTRNO().getTRLISTARLPVPSEVTZ().isEmpty()){
            noNulo = true;
        }
        return noNulo;
    }

}
