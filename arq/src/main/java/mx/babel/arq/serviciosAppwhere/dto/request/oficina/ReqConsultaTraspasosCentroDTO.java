package mx.babel.arq.serviciosAppwhere.dto.request.oficina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;

@AllArgsConstructor
@NoArgsConstructor
public class ReqConsultaTraspasosCentroDTO extends ReqGralDTO{
    @Setter
    @Getter
    private String numPuesto;
    @Setter
    @Getter
    private String fechaPcDesde;
    @Setter
    @Getter
    private String fechaPcHasta;
}
