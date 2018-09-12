package mx.babel.arq.serviciosAppwhere.dto.catalogos.catalogosVariosPM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.babel.arq.serviciosAppwhere.dto.ResGralDTO;

import java.util.ArrayList;

/**
 * Created by AppWIns on 23/07/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResConsultaCatalogosDTO extends ResGralDTO {

    private ArrayList<CatalogoPersonaMoralDTO> responseBansefi;

}
