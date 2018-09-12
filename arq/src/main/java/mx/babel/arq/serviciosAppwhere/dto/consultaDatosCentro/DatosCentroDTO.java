package mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatosCentroDTO {

    private String NOMBRE;
    private String FECHA_ALTA;
    private String CALLE;
    private String NUMEROEXT;
    private String NUMEROINT;
    private String COLONIA;
    private String CP;

    public String getDireccionCompleta() {
        return "{\"FECHA_ALTA\": \"" + FECHA_ALTA + "\","+
                "\"CALLE\": \"" + CALLE + "\","+
                "\"NUMEROEXT\": \"" + NUMEROEXT + "\","+
                "\"NUMEROINT\": \"" + NUMEROINT + "\","+
                "\"COLONIA\": \"" + COLONIA + "\","+
                "\"CP\": \"" + CP + "\"}";
    }

}
