package mx.babel.arq.serviciosAppwhere.dto.catalogos.catalogosVariosPM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by AppWIns on 08/07/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResCatalogosVariosPMDTO {

    private String STATUS;
    private RegistrosCatalogoDTO CATALAGOS;
    private String ERROR_DESC;

}
