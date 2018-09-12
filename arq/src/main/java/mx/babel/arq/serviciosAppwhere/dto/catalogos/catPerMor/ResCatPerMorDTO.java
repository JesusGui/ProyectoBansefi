package mx.babel.arq.serviciosAppwhere.dto.catalogos.catPerMor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResCatPerMorDTO {

    private String STATUS;
    private String MENSAJE;
    private String CODIGO;
    private RegistrosCatPerMorDTO ResponseBansefi;

}
