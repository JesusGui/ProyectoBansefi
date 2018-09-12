package mx.babel.arq.serviciosAppwhere.dto.response.oficina.atributos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class PuestoDTO {
    @Setter
    @Getter
    private String idInternoTermTn;
    @Setter
    @Getter
    private String numPuesto;
    @Setter
    @Getter
    private String totalArqueo;
    @Setter
    @Getter
    private String saldoCaja;
    @Setter
    @Getter
    private String diferencia;
}
