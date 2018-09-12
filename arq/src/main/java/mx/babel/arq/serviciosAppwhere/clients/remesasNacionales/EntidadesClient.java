package mx.babel.arq.serviciosAppwhere.clients.remesasNacionales;

import mx.babel.arq.serviciosAppwhere.constants.CommonClientConstant;
import mx.babel.arq.serviciosAppwhere.dto.remesasNacionales.ResCredencialesEntidadDto;
import mx.babel.arq.serviciosAppwhere.utils.Util;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Autor: Jose Angel Hernandez Gonzalez
 * Fecha: 02/02/2018
 */
@Component
public class EntidadesClient {

    private Util util;

    @Autowired
    public void setUtil(Util util) {
        this.util = util;
    }

    public ResCredencialesEntidadDto consultaDatosCentro(String codigoEntidad) {
        ResCredencialesEntidadDto res = null;
        String responseBody = null;
        try {
            StringBuilder uri = new StringBuilder(CommonClientConstant.URICREDENCIALESENTIDAD);
            uri.append("/" + codigoEntidad);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = null;
            response = restTemplate.getForEntity(uri.toString(), String.class);
            responseBody = response.getBody();
        } catch (HttpClientErrorException ex) {
            responseBody = ex.getResponseBodyAsString();
        }
        try {
            res = new ResCredencialesEntidadDto();
            res = (ResCredencialesEntidadDto) this.util.jsonToObject(res, responseBody);
        } catch (ParseException ex) {
        ex.printStackTrace();
        res = new ResCredencialesEntidadDto();
        res.setStatus("500");
        res.setError(ex.getMessage());
    }
        return res;
    }

}
