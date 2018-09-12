package mx.babel.arq.serviciosAppwhere.dto.catalogos.moneda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqMonedaDTO extends ReqGralDTO {

    private String codigoMoneda;
}
