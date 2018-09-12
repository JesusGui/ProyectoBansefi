package mx.babel.arq.serviciosAppwhere.clients.common;

import mx.babel.arq.comun.utils.ProveedorMensajeSpringUtils;
import mx.babel.arq.serviciosAppwhere.constants.CommonClientConstant;
import mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro.ReqConsultaDatosCentroDTO;
import mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro.ResConsultaDatosCentroDTO;
import mx.babel.arq.serviciosAppwhere.utils.Util;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Autor: Jose Angel Hernandez Gonzalez
 * Fecha: 04/01/2018
 */
@Component
public class CommonClient {

    private Util util;

    @Autowired
    public void setUtil(Util util) {
        this.util = util;
    }

    public ResConsultaDatosCentroDTO consultaDatosCentro(ReqConsultaDatosCentroDTO req) {
        ResConsultaDatosCentroDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, CommonClientConstant.URICONSULTADATOSCENTRO);
            res = new ResConsultaDatosCentroDTO();

            if (!jsonRes.equals("")) {
                ArrayList<String> nodos = new ArrayList<String>();
                nodos.add("ConsultaDatosCentroResponse");
                nodos.add("return");
                nodos.add("RESPONSE");

                res = (ResConsultaDatosCentroDTO) this.util.jsonToObject(res, jsonRes, nodos);
            } else {
                res.setSTATUS("0");
                res.setERROR_DESC(ProveedorMensajeSpringUtils.getValoresServiciosWeb("mensajes.servicioInaccesible"));
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
            res = new ResConsultaDatosCentroDTO("0",null,"");
        }
        return res;
    }

}
