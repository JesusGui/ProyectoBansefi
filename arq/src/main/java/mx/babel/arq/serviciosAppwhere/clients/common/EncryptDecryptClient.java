package mx.babel.arq.serviciosAppwhere.clients.common;

import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.serviciosAppwhere.constants.CommonClientConstant;
import mx.babel.arq.serviciosAppwhere.dto.encryptDecrypt.ReqEncryptDTO;
import mx.babel.arq.serviciosAppwhere.dto.encryptDecrypt.ResEncryptDTO;
import mx.babel.arq.serviciosAppwhere.utils.Util;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EncryptDecryptClient {

    private Util util;

    @Autowired
    public void setUtil(Util util) {
        this.util = util;
    }

    /*
     * Metodo para consumir servicio encrypt de WSO2.
     */
    public ResEncryptDTO encrypt(ReqEncryptDTO req) {
        ResEncryptDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, CommonClientConstant.URIENCRYPT);
            res = new ResEncryptDTO();

            if (!jsonRes.equals("")) {
                ArrayList<String> nodos = new ArrayList<String>();

                res = (ResEncryptDTO) this.util.jsonToObject(res, jsonRes, nodos);
            } else {
                res.setCodRet(-1);
                res.setError(ProveedorMensajeSpringUtils.getValoresServiciosWeb("mensajes.servicioInaccesible"));
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return res;
    }

}
