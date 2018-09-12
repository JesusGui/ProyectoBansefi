package mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRazonesAlta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by AppWIns on 14/06/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResConsultaRazonesAltaDTO {

    private String STATUS;
    private RazonesAltaDTO DATOS_LISTA;
    private String ERROR_DESC;

}
