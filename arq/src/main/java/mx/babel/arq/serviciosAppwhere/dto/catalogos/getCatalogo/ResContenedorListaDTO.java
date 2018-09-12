package mx.babel.arq.serviciosAppwhere.dto.catalogos.getCatalogo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by AppWIns on 25/04/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResContenedorListaDTO {

    private ArrayList<CatalogoDTO> Identificacion;
}
