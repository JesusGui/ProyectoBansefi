package mx.babel.arq.serviciosAppwhere.dto.response.oficina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.babel.arq.serviciosAppwhere.dto.ResGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.atributos.TraspasoDTO;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
public class ResConsultaTraspasosCentroDTO extends ResGralDTO {
    @Setter
    @Getter
    ArrayList<TraspasoDTO> traspasos;
}
