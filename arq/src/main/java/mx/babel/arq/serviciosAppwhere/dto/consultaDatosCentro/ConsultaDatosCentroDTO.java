package mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaDatosCentroDTO {

    private String entidad;
    private String centro;
    private String terminal;

}
