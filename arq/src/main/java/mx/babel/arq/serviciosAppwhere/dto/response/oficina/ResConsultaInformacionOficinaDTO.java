package mx.babel.arq.serviciosAppwhere.dto.response.oficina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.babel.arq.serviciosAppwhere.dto.ResGralDTO;

@AllArgsConstructor
@NoArgsConstructor
public class ResConsultaInformacionOficinaDTO extends ResGralDTO {
    @Setter
    @Getter
    private String codNrbeEnFsc;
    @Setter
    @Getter
    private String tipoPuesto;
    @Setter
    @Getter
    private String numSec;
    @Setter
    @Getter
    private String indAperturaTn;
    @Setter
    @Getter
    private String idInternoTermTn;
    @Setter
    @Getter
    private String impIni;
    @Setter
    @Getter
    private String numPuesto;
    @Setter
    @Getter
    private String puestoPrincipal;
    @Setter
    @Getter
    private String codNrbeEn;
    @Setter
    @Getter
    private String idInternoEmplEp;
    @Setter
    @Getter
    private String codInternoUo;
    @Setter
    @Getter
    private String codCsbOf;
}
