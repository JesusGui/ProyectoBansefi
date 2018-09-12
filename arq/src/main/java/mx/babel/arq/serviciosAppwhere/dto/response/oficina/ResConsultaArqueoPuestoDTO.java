package mx.babel.arq.serviciosAppwhere.dto.response.oficina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.babel.arq.serviciosAppwhere.dto.request.oficina.Atributos.ExistenciaDenominacionDTO;
import mx.babel.arq.serviciosAppwhere.dto.ResGralDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ResConsultaArqueoPuestoDTO extends ResGralDTO {
    @Setter
    @Getter
    private String codInternoUo;
    @Setter
    @Getter
    private String codNrbeEn;
    @Setter
    @Getter
    private String idInternoTermTn;
    @Setter
    @Getter
    private List<ExistenciaDenominacionDTO> arqueo;
}
