package mx.babel.arq.serviciosAppwhere.dto.response.oficina.atributos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class SaldoDTO {
    @Setter
    @Getter
    private String idInternoTermTn;
    @Setter
    @Getter
    private String impIni;
    @Setter
    @Getter
    private String numPuesto;
}
