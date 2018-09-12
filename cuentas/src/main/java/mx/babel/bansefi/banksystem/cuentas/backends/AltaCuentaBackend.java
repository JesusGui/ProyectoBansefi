package mx.babel.bansefi.banksystem.cuentas.backends;

import java.util.List;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.BooleanToStringConverter;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.cuentas.webservices.altacuenta.AltaCuentaServicio;
import mx.babel.bansefi.banksystem.cuentas.webservices.altacuenta.Ejecutar;
import mx.babel.bansefi.banksystem.cuentas.webservices.altacuenta.Ejecutar.ITRSOLICITARACTRNI.STDTRNIPARMV;
import mx.babel.bansefi.banksystem.cuentas.webservices.altacuenta.Ejecutar.ITRSOLICITARACTRNI.TRSOLICITARACEVTY.ACACE;
import mx.babel.bansefi.banksystem.cuentas.webservices.altacuenta.Ejecutar.ITRSOLICITARACTRNI.TRSOLICITARACEVTY.ACACRLPDSE;
import mx.babel.bansefi.banksystem.cuentas.webservices.altacuenta.Ejecutar.ITRSOLICITARACTRNI.TRSOLICITARACEVTY.PERSONAACV;
import mx.babel.bansefi.banksystem.cuentas.webservices.altacuenta.EjecutarResult;
import mx.babel.bansefi.banksystem.cuentas.webservices.altacuenta.ResponseBansefi;
import mx.babel.bansefi.banksystem.cuentas.wrappers.AltaCuentaWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para el alta de cuenta/acuerdos.
 *
 * @author
 *
 */

@Component
public class AltaCuentaBackend extends BackEndBean {

	private static final long serialVersionUID = 1516747809634979350L;

	@Autowired
    ServicioWebUtils servicioWebUtils;

	@Autowired
    AltaCuentaWrapper altaCuentaWrapper;

    public CuentaBean ejecutarTRN(final ClienteBean clienteBean, final TarifaBean tarifaBean){
       
        final Ejecutar.ITRSOLICITARACTRNI contexto = initPeticion(clienteBean, tarifaBean);

        final EjecutarResult respuesta = ejecutarWS(contexto);

        super.verificaRespuesta(respuesta);
        
        return getResponse(respuesta.getResponseBansefi());
    }

    /**
     * Función que inicializa el objeto para realizar la petición al servicio web.
     * @param numCuenta
     *
     * @return Objeto a ser enviado al servicio web.
     */
    private Ejecutar.ITRSOLICITARACTRNI initPeticion(final ClienteBean clienteBean, final TarifaBean tarifaBean){
        final Ejecutar.ITRSOLICITARACTRNI contexto = new Ejecutar.ITRSOLICITARACTRNI();


        super.initialize(contexto);

        final IntegerToDateConverter itdConverter = new IntegerToDateConverter();
        final BooleanToStringConverter btsConverter = new BooleanToStringConverter();
        final int today = itdConverter.convertFrom(this.getFechaSistema());

        final ACACE acace = contexto.getTRSOLICITARACEVTY().getACACE();
        acace.setCODNRBEEN(this.getEntidad());
        //TODO arreglar este desaguisado
        acace.setNUMSECAC(0l);
        //TODO arreglar este desaguisado
        acace.setCODECVAC("1");
        //TODO arreglar este desaguisado
        acace.setFECHAINIECVACT(today);
        acace.setCODINTERNOUO(this.getSucursal());
        //TODO sera realmente la sucursal, en el ejemplo codinternouo tiene el mismo valor que codcsbof
        acace.setCODCSBOF(this.getSucursal());
        acace.setCODLINEA(tarifaBean.getLinea());
        acace.setIDGRPPD(tarifaBean.getGrupo());
        acace.setIDPDV(tarifaBean.getProducto());
        acace.setIDTRFAPDV(tarifaBean.getId());
        acace.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);

        final List<ProductoSimpleBean> listaProductosSimples = tarifaBean.getProductosSimples();
        if(listaProductosSimples != null && !listaProductosSimples.isEmpty()){
            final List<ACACRLPDSE> acacrlpdseList = contexto.getTRSOLICITARACEVTY().getACACRLPDSE();
            ACACRLPDSE acacrlpdse = null;
            for(final ProductoSimpleBean psBean : listaProductosSimples){
                acacrlpdse = new ACACRLPDSE();
                acacrlpdse.setCODNRBEEN(this.getEntidad());
                //TODO arreglar este desaguisado
                acacrlpdse.setNUMSECAC(0l);
                acacrlpdse.setIDPDS(psBean.getId());
                if(null!=psBean.getFechaInicio()){
                    acacrlpdse.setFECHAINICIO(itdConverter.convertFrom(psBean.getFechaInicio()));
                }

                if(null!=psBean.getObligatorio()){
                    acacrlpdse.setINDOBLIG(btsConverter.convertTo(psBean.getObligatorio()));
                }
                //TODO arreglar este desaguisado
                acacrlpdse.setFECHACRRE(00000000);
                acacrlpdseList.add(acacrlpdse);
            }
        }

        final PERSONAACV personaacv = contexto.getTRSOLICITARACEVTY().getPERSONAACV();
        personaacv.setIDINTERNOPE(clienteBean.getIdInterna());
        //TODO arreglar este desaguisado
        personaacv.setCODPE(clienteBean.getTipoClienteEnum().getCodPe());
        //TODO arreglar este desaguisado
        personaacv.setNOMB50(clienteBean.getNombre());

        //TODO arreglar este desaguisado
        contexto.getTRSOLICITARACEVTY().getCODRZNECVACV().setCODRZNECVAC("01");
        //TODO arreglar este desaguisado
        contexto.getTRSOLICITARACEVTY().setEVENTCD(3);

        final STDTRNIPARMV stdtrniparmv = contexto.getSTDTRNIPARMV();
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_SOLICITAR_AC_TRN);
        stdtrniparmv.setIDINTERNOTERMTN(this.getTerminal());

        return contexto;
    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     *
     * @param contexto Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(final Ejecutar.ITRSOLICITARACTRNI contexto)
            throws NoControlableException{
        EjecutarResult respuesta = null;

        try{
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
                    AltaCuentaServicio.class, contexto);
        }catch(final NoControlableException e){
            throw new NoControlableException("Error al invocar servicio web de alta de "
                    + "cuenta.", e);
        }
        return respuesta;
    }

    /**
     * Función encargada de parsear la respuesta del servicio web.
     */
    private CuentaBean getResponse(final ResponseBansefi response){
        CuentaBean cuentaBean = null;      

        if(verificaRespuesta(response)){
            cuentaBean = altaCuentaWrapper.wrapAltaCuenta(
                    response.getOTRSOLICITARACTRNO().getTRSOLICITARACEVTZ().getACACE());
        }

        return cuentaBean;
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
        if(response != null && response.getOTRSOLICITARACTRNO() != null &&
                response.getOTRSOLICITARACTRNO().getTRSOLICITARACEVTZ() != null &&
                response.getOTRSOLICITARACTRNO().getTRSOLICITARACEVTZ().getACACE() != null){
            noNulo = true;
        }
        return noNulo;
    }

}
