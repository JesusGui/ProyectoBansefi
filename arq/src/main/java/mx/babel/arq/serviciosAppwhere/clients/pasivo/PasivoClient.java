package mx.babel.arq.serviciosAppwhere.clients.pasivo;

import mx.babel.arq.serviciosAppwhere.constants.PasivoClientConstant;
import mx.babel.arq.serviciosAppwhere.dto.pasivo.ReqConsultaPendientesDiarioDTO;
import mx.babel.arq.serviciosAppwhere.dto.pasivo.ResConsultaPendientesDiarioDTO;
import mx.babel.arq.serviciosAppwhere.utils.Util;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PasivoClient {

    /*
     * Definicion de variables de clase
     */
    private Util util;

    @Autowired
    public void setUtil(Util util) {
        this.util = util;
    }

    /*
     * Metodo para consumir servicio de consulta pendientes de diario
     * mediante ws02.
     */
    public ResConsultaPendientesDiarioDTO consultaPendientesDiario(ReqConsultaPendientesDiarioDTO req) {
        ResConsultaPendientesDiarioDTO res = null;
        try {
            String jsonRes = this.util.callRestPost(req, PasivoClientConstant.URICONSULTAPENDIENTESDIARIO);
            jsonRes = jsonRes.replaceAll("\\\\","");
            jsonRes = jsonRes.replaceAll("\"\\{","\\{");
            jsonRes = jsonRes.replaceAll("\\}\"","\\}");
            res = new ResConsultaPendientesDiarioDTO();

            if (!jsonRes.equals("")) {
                ArrayList<String> nodos = new ArrayList<String>();
                nodos.add("ConsultaPendienteDiarioResponse");
                nodos.add("return");
                nodos.add("ResConsPendiDiario");

                res = (ResConsultaPendientesDiarioDTO) this.util.jsonToObject(res, jsonRes, nodos);
            } else {
                res.setStatus("0");
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
            res = new ResConsultaPendientesDiarioDTO("","0");
        }
        return res;
    }

    /*
     * Metodo para consumir servicio para procesar pendientes de diario
     * mediante ws02.
     */
    public void procesaPendientesDiario(ReqConsultaPendientesDiarioDTO req) {
        String jsonRes = this.util.callRestPost(req, PasivoClientConstant.URIPROCESAPENDIENTESDIARIO);
    }

}
