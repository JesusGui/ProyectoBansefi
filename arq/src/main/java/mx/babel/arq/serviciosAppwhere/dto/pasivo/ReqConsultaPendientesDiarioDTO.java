package mx.babel.arq.serviciosAppwhere.dto.pasivo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqConsultaPendientesDiarioDTO {

    private String entidad;
    private String terminal;
    private String centro;

}
