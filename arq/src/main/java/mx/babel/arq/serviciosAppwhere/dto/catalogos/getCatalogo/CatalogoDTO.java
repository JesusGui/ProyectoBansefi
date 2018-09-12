package mx.babel.arq.serviciosAppwhere.dto.catalogos.getCatalogo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by AppWIns on 25/04/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogoDTO {

    private String identificador;
    private String nombre;
    private String descripcion;
}
