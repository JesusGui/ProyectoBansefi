package mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaRelacionPersona;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by AppWIns on 27/04/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RelacionesPersonaDTO {

    private ArrayList<RelacionPersonaDTO> RELACION;
}
