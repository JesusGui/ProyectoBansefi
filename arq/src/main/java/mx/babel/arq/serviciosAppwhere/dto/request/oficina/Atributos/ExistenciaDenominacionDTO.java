package mx.babel.arq.serviciosAppwhere.dto.request.oficina.Atributos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ExistenciaDenominacionDTO {
    @Setter
    @Getter
    private String codValorFacial;
    @Setter
    @Getter
    private String codDstn;
    @Setter
    @Getter
    private String impNominal;
    @Setter
    @Getter
    private String unidades;
}
