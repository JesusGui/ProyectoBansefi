package mx.babel.arq.serviciosAppwhere.dto.response.oficina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.babel.arq.serviciosAppwhere.dto.ResGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.atributos.PuestoDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ResConsultaTotalArqueoPuestosDTO extends ResGralDTO {
    @Setter
    @Getter
    private List<PuestoDTO> puestos;
}
