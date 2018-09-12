package mx.babel.arq.serviciosAppwhere.dto.request.oficina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;

@NoArgsConstructor
@AllArgsConstructor
public class ReqAltaTraspasoFondosDTO extends ReqGralDTO {
    @Setter
    @Getter
    private String fechaCtble;
    @Setter
    @Getter
    private String numPuestoDe;
    @Setter
    @Getter
    private String impNominal;
    @Setter
    @Getter
    private String numPuesto;
    @Setter
    @Getter
    private String idInternoEmplEp2;
}
