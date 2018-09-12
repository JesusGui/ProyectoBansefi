package mx.babel.arq.serviciosAppwhere.dto.catalogos.catPasivoPlazo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.babel.arq.serviciosAppwhere.dto.ResGralDTO;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResCatPasivoPlazo extends ResGralDTO {

    private ArrayList<RegPasivoPlazo> responseBansefi;

}
