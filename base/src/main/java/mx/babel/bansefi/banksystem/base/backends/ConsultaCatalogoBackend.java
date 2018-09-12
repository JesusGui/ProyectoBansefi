package mx.babel.bansefi.banksystem.base.backends;


import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.cache.annotation.Cache;
import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.webservices.consultacatalogo.ConsultaCatalogoServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultacatalogo.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultacatalogo.ResponseBansefi;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la consulta de catalogos
 *
 * @author
 *
 */
@Component
public class ConsultaCatalogoBackend extends BackEndBean {
	
	private static final long serialVersionUID = 658574192640643937L;

	@Autowired
	ServicioWebUtils servicioWebUtils;

    private static final Logger LOGGER = LogManager.getLogger(ConsultaCatalogoBackend.class.getName());

    private static final Object NUM_REG = "100";
    private static final String CTE_HAY_MAS_DATOS = "FT1A0002";
    private String codigoRetorno;

    /**
     * Constructor.
     */
    public ConsultaCatalogoBackend() {
        codigoRetorno = new String("-1");
    }

    /**
     * Método que obtiene el código de retorno devuelto por el servicio de
     * consulta de cuentas.
     *
     * @return codigoRetorno
     */
    public String getCodigoRetorno() {
        return codigoRetorno;
    }

    /**
     * Método que establece el código de retorno.
     *
     * @param codigoRetorno
     */
    public void setCodigoRetorno(final String codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }
    /**
     * Método que ejecuta la transacción de consulta de cuentas a partir de un
     * objeto CuentaBean.
     *
     * @param cuentaBean
     * @return cuentaBean con valores de atributos recuperados
     * @throws ControlableException
     * @throws NoControlableException
     */
    @Cache
    public List<CatalogoBean> ejecutarTRN(final CatalogoEnum catalogo){
        final List<CatalogoBean> result = new ArrayList<CatalogoBean>();
        final String claveacc = catalogo.getCodTblRef()+catalogo.getCodAplccnSubapl();
        String clavePaginacion = "";
        boolean hayMasDatos = true;
        while (hayMasDatos){

            final EjecutarResult resultado = (EjecutarResult) servicioWebUtils.ejecutarWS(ConsultaCatalogoServicio.class,
                    claveacc, "", clavePaginacion, NUM_REG);
            if (verificarResultado(resultado)) {
                codigoRetorno = resultado.getESTATUS();
                if (codigoRetorno.equals("0")) {
                	
                    for(final ResponseBansefi data : resultado.getResponseBansefi().getResponseBansefi()){
                    	CatalogoBean catalogoBean = new CatalogoBean();
                    	if (data.getCONTENIDODEDATOS1() !=null){
                    		catalogoBean.setContenido(data.getCONTENIDODEDATOS1().trim());
                    	}
                    	if (data.getCLAVEDEFILANOMBRE() !=null){
                    		catalogoBean.setClaveFila(data.getCLAVEDEFILANOMBRE().trim());
                    	}
                    	if (data.getDESCRIPCIONCORTAFL() != null){
                    		catalogoBean.setDescripcionC(data.getDESCRIPCIONCORTAFL().trim());
                    	}                    	
                    	if (data.getDESCRIPCIONLARGAFL() != null){
                    		catalogoBean.setDescripcionL(data.getDESCRIPCIONLARGAFL().trim());
                    		if (catalogo.isDescripcionExtendida()){
                        		catalogoBean.setDescripcionL(data.getCLAVEDEFILANOMBRE() !=null?
                        									 data.getCLAVEDEFILANOMBRE().trim()+" - "+data.getDESCRIPCIONLARGAFL().trim():
                        									 data.getDESCRIPCIONLARGAFL().trim());
                        	}
                    	}               	                    	
                    	result.add(catalogoBean);
                    }
                
                    if(StringUtils.equalsIgnoreCase(ConsultaCatalogoBackend.CTE_HAY_MAS_DATOS, resultado.getCODIGO())){
                        clavePaginacion = result.get(result.size() - 1).getClaveFila();
                    }else{
                        hayMasDatos = false;
                    }

                } else if (codigoRetorno.equals(7)) {
                    LOGGER.debug("Datos no encontrados");
                } else{
                    throw new NoControlableException("Ha ocurrido un error en la consulta de catalogos", resultado.getMENSAJE());
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
    private boolean verificarResultado(final EjecutarResult resultado) {
        try {
            if (resultado != null
                    && resultado.getResponseBansefi() != null) {
                return true;
            }
        } catch (final NullPointerException e) {
            throw new NoControlableException(
                    "Error al invocar el servicio de consulta de catalogos.",
                    this.getClass().getName()
                            + ". La respuesta contiene valores nulos.");
        }
        return false;
    }

}
