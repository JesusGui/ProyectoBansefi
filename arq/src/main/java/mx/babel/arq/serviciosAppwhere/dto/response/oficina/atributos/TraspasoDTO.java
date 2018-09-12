package mx.babel.arq.serviciosAppwhere.dto.response.oficina.atributos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class TraspasoDTO {
    @Setter
    @Getter
    private String codNrbeEnFsc;
    @Setter
    @Getter
    private String codInternoUoFsc;
    @Setter
    @Getter
    private String numPuestoDe;
    @Setter
    @Getter
    private String impNominal;
    @Setter
    @Getter
    private String horaPc;
    @Setter
    @Getter
    private String idInternoEmplEp;
    @Setter
    @Getter
    private String numPuesto;
    @Setter
    @Getter
    private String idInternoEmplEp2;
    @Setter
    @Getter
    private String fechaPc;
}
