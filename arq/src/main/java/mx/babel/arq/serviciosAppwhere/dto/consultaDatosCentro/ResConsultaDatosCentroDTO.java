package mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResConsultaDatosCentroDTO {

    private String STATUS;
    private ContDatosCentroDTO DATOS;
    private String ERROR_DESC;

}
