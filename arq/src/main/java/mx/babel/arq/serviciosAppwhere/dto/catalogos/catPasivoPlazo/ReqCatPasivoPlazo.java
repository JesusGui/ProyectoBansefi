package mx.babel.arq.serviciosAppwhere.dto.catalogos.catPasivoPlazo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqCatPasivoPlazo extends ReqGralDTO {

    private String catalogo;
    private String codigo;
    private String registros;

}
