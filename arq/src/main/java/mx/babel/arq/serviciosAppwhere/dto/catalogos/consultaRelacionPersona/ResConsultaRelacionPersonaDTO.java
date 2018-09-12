package mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRelacionPersona;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by AppWIns on 27/04/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResConsultaRelacionPersonaDTO {

    private Integer STATUS;
    private RelacionesPersonaDTO DATOS_LISTA;
    private String ERROR_DESC;

}
