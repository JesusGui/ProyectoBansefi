package mx.babel.bansefi.banksystem.integracionesAppWhere.services.impl;

import mx.babel.arq.serviciosAppwhere.clients.common.EncryptDecryptClient;
import mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro.DatosCentroDTO;
import mx.babel.arq.serviciosAppwhere.dto.encryptDecrypt.ReqEncryptDTO;
import mx.babel.arq.serviciosAppwhere.dto.encryptDecrypt.ResEncryptDTO;
import mx.babel.arq.serviciosAppwhere.utils.Util;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;
import mx.babel.bansefi.banksystem.integracionesAppWhere.dto.bsfOperador.BsfOperadorContainerDTO;
import mx.babel.bansefi.banksystem.integracionesAppWhere.dto.bsfOperador.BsfOperadorDTO;
import mx.babel.bansefi.banksystem.integracionesAppWhere.services.IIntegracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@Service
public class IntegracionService implements IIntegracionService {

    /*
     * Variables de clase
     */
    private Util util;
    private EncryptDecryptClient encryptDecryptClient;
    private final Format formatter = new SimpleDateFormat("yyyy/MM/dd");

    /*
     * Inyeccion de dependencias
     */
    @Autowired
    public void setEncryptDecryptClient(EncryptDecryptClient encryptDecryptClient) {
        this.encryptDecryptClient = encryptDecryptClient;
    }
    @Autowired
    public void setUtil(Util util) {
        this.util = util;
    }

    /*
     * Metodo para generar bsfOperador comun y devolverlo como json encriptado.
     */
    @Override
    public String getBsfOperadorComun(final ContextoUtils contextoUtils) {
        BsfOperadorDTO bsfOperadorDTO = this.conformarBsfOperadorComun(contextoUtils);
        return encriptarBsfOperador(bsfOperadorDTO, true);
    }

    /*
     * Metodo para generar bsfOperador de gastos y devolverlo como json encriptado.
     */
    @Override
    public String getBsfOperadorMovimientos(final ContextoUtils contextoUtils) {
        BsfOperadorDTO bsfOperadorDTO = this.conformarBsfOperadorComun(contextoUtils);
        HashMap<String, Object> transport = (HashMap<String, Object>) bsfOperadorDTO.getTRANSPORT();
        transport.put("SUCURSAL", bsfOperadorDTO.getCENTRO());
        return encriptarBsfOperador(bsfOperadorDTO, true);
    }

    /*
     * Metodo para generar bsfOperador de gastos y devolverlo como json encriptado.
     */
    @Override
    public String getBsfOperadorAltaCuentaPlazo(
            final ContextoUtils contextoUtils, final ClienteBean clienteBean, final TarifaBean tarifaBean) {
        BsfOperadorDTO bsfOperadorDTO = this.conformarBsfOperadorComun(contextoUtils);
        HashMap<String, Object> transport = (HashMap<String, Object>) bsfOperadorDTO.getTRANSPORT();
        transport.put("IDINTERNOPE", clienteBean.getIdInterna());
        transport.put("TIPOPERSONA", clienteBean.getTipoClienteEnum().getCodPe());
        transport.put("LINEATARIFA", tarifaBean.getLinea());
        transport.put("GRUPOTARIFA", tarifaBean.getGrupo());
        transport.put("PRODUCTOTARIFA", tarifaBean.getProducto());
        transport.put("NOMBREPRODUCTO", tarifaBean.getNombre());
        transport.put("TARIFA", tarifaBean.getId());
        return encriptarBsfOperador(bsfOperadorDTO, true);
    }

    /*
     * Metodo para generar bsfOperador de solicitudes pendientes de limites
     */
    @Override
    public String getBsfOperadorSolicitudesLimites(final ContextoUtils contextoUtils) {
        BsfOperadorDTO bsfOperadorDTO = this.conformarBsfOperadorComun(contextoUtils);
        HashMap<String, Object> transport = (HashMap<String, Object>) bsfOperadorDTO.getTRANSPORT();
        transport.put("IDEMPLEADO", bsfOperadorDTO.getUSERTCB());
        return encriptarBsfOperador(bsfOperadorDTO, true);
    }

    @Override
    public String getBsfOperadorCambioTarifa(ContextoUtils contextoUtils, String acuerdo, String tipoAcuerdo) {
        BsfOperadorDTO bsfOperadorDTO = this.conformarBsfOperadorComun(contextoUtils);
        HashMap<String, Object> transport = (HashMap<String, Object>) bsfOperadorDTO.getTRANSPORT();
        transport.put("acuerdo", acuerdo);
        transport.put("tipoAcuerdo", tipoAcuerdo);
        return encriptarBsfOperador(bsfOperadorDTO, true);
    }

    @Override
    public String getBsfOperadorFichaPersonaFisica(ContextoUtils contextoUtils, String idInternoPe) {
        BsfOperadorDTO bsfOperadorDTO = this.conformarBsfOperadorComun(contextoUtils);
        HashMap<String, Object> transport = (HashMap<String, Object>) bsfOperadorDTO.getTRANSPORT();
        transport.put("IDINTERNOPE", idInternoPe);
        return encriptarBsfOperador(bsfOperadorDTO, false);
    }

    @Override
    public String getBsfOperadorRelacionarPersonaMoral(ContextoUtils contextoUtils, String idInternoPe, String razonSocial) {
        BsfOperadorDTO bsfOperadorDTO = this.conformarBsfOperadorComun(contextoUtils);
        HashMap<String, Object> transport = (HashMap<String, Object>) bsfOperadorDTO.getTRANSPORT();
        transport.put("IDINTERNOPE", idInternoPe);
        transport.put("RAZONSOCIAL", razonSocial);
        return encriptarBsfOperador(bsfOperadorDTO, true);
    }

    @Override
    public String getBsfOperadorFichaPersonaMoral(ContextoUtils contextoUtils, String idInternoPe) {
        BsfOperadorDTO bsfOperadorDTO = this.conformarBsfOperadorComun(contextoUtils);
        HashMap<String, Object> transport = (HashMap<String, Object>) bsfOperadorDTO.getTRANSPORT();
        transport.put("IDINTERNOPE", idInternoPe);
        return encriptarBsfOperador(bsfOperadorDTO, true);
    }

    @Override
    public String getBsfOperadorPersonas(
            ContextoUtils contextoUtils, String idInternoPe, String nombrePersona,
            String codTx, String tipoPersona, String numOrdEmpPe) {
        BsfOperadorDTO bsfOperadorDTO = this.conformarBsfOperadorComun(contextoUtils);
        HashMap<String, Object> transport = (HashMap<String, Object>) bsfOperadorDTO.getTRANSPORT();
        transport.put("ID_INTERNO_PE", idInternoPe);
        transport.put("NombrePersona", nombrePersona);
        transport.put("codTx", codTx);
        transport.put("TIPO_PER", tipoPersona);
        transport.put("fechaContable", bsfOperadorDTO.getFECHACONTABLE());
        transport.put("NUM_ORD_EMP_PE", numOrdEmpPe);
        return encriptarBsfOperador(bsfOperadorDTO, true);
    }

    @Override
    public String getBsfOperadorDudosidad(
            ContextoUtils contextoUtils, String target) {
        BsfOperadorDTO bsfOperadorDTO = this.conformarBsfOperadorComun(contextoUtils);
        HashMap<String, Object> transport = (HashMap<String, Object>) bsfOperadorDTO.getTRANSPORT();
        transport.put("TARGET", target);
        return encriptarBsfOperador(bsfOperadorDTO, true);
    }

    /*
     * Metodo para generar estrucutra del bsfOperador
     */
    private BsfOperadorDTO conformarBsfOperadorComun(ContextoUtils contextoUtils) {
        String fechaContable = formatter.format(contextoUtils.getFechaContableActual());
        HttpServletRequest requestObj = (HttpServletRequest)
                FacesContext.getCurrentInstance().getExternalContext().getRequest();
        DatosCentroDTO datosCentroDTO = (DatosCentroDTO) requestObj.getSession().getAttribute("datosCentro");
        BsfOperadorDTO bsfOperadorDTO = new BsfOperadorDTO();
        HashMap<String, Object> transport = new HashMap<String, Object>();
        bsfOperadorDTO.setUSERTCB(contextoUtils.getId());
        bsfOperadorDTO.setPASSTCB(contextoUtils.getPwd());
        bsfOperadorDTO.setTERMINAL(Integer.parseInt(contextoUtils.getTerminal()));
        bsfOperadorDTO.setNOMBREEMPLEADO(contextoUtils.getNombre());
        bsfOperadorDTO.setENTIDAD(contextoUtils.getEntidad());
        bsfOperadorDTO.setCENTRO(contextoUtils.getSucursal());
        bsfOperadorDTO.setSESSIONID("");
        bsfOperadorDTO.setFECHACONTABLE(fechaContable);
        bsfOperadorDTO.setNOMBRECENTRO(datosCentroDTO.getNOMBRE());
        bsfOperadorDTO.setDIRECCIONCENTRO(datosCentroDTO);
        bsfOperadorDTO.setTRANSPORT(transport);
        transport.put("MenuDinamico","1");
        return bsfOperadorDTO;
    }

    /*
     * Metodo para convertir el bsfOperador a json y encriptarlo.
     */
    private String encriptarBsfOperador(BsfOperadorDTO bsfOperadorDTO, boolean withContainer) {
        String jsonBsfOperador;
        jsonBsfOperador = withContainer
                ? this.util.objectToJson(new BsfOperadorContainerDTO(bsfOperadorDTO))
                : this.util.objectToJson(bsfOperadorDTO);
        jsonBsfOperador = replaceCharsInString(jsonBsfOperador);
        ResEncryptDTO resEncryptDTO = encryptDecryptClient.encrypt(new ReqEncryptDTO(jsonBsfOperador));
        jsonBsfOperador = resEncryptDTO.getRespuesta();
        return jsonBsfOperador;
    }

    private String replaceCharsInString(String cadena) {

        return cadena
                .replaceAll("Á", "\\\\u00C1")
                .replaceAll("É", "\\\\u00C9")
                .replaceAll("Í", "\\\\u00CD")
                .replaceAll("Ó", "\\\\u00D3")
                .replaceAll("Ú", "\\\\u00DA")
                .replaceAll("á", "\\\\u00E1")
                .replaceAll("é", "\\\\u00E9")
                .replaceAll("í", "\\\\u00ED")
                .replaceAll("ó", "\\\\u00F3")
                .replaceAll("ú", "\\\\u00FA")
                .replaceAll("Ñ", "\\\\u00D1")
                .replaceAll("ñ", "\\\\u00F1")
                .replaceAll("\"", "\\\"");
    }

}
