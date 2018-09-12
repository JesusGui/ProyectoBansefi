package mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaListaFinalidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by AppWIns on 27/06/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResConsultaListaFinalidadDTO {


    private String STATUS;
    private String ERROR_DESC;
    private ArrayList<FinalidadDTO> FINALIDAD;

}
