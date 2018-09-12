package mx.babel.arq.serviciosAppwhere.dto.catalogos.catPerMor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrosCatPerMorDTO {

    private ArrayList<RegistroCatPerMorDTO> ResponseBansefi;
}
