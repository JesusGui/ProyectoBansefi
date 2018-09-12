package mx.babel.arq.serviciosAppwhere.dto.request.oficina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.request.oficina.Atributos.ExistenciaDenominacionDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ReqActualizarArqueoPuestoDTO extends ReqGralDTO {
    @Setter
    @Getter
    private String fechaCtble;
    @Setter
    @Getter
    private String cajaDiferencia;
    @Setter
    @Getter
    private String cajaCobrosOn;
    @Setter
    @Getter
    private String cajaPagosOn;
    @Setter
    @Getter
    private String intDiferenciaOn;
    @Setter
    @Getter
    private String intCobrosOn;
    @Setter
    @Getter
    private String intPagosOn;
    @Setter
    @Getter
    private String resultadoCuadre;
    @Setter
    @Getter
    private String netoTraspasos;
    @Setter
    @Getter
    private String totalArqueo;
    @Setter
    @Getter
    private List<ExistenciaDenominacionDTO> arqueo;
}
