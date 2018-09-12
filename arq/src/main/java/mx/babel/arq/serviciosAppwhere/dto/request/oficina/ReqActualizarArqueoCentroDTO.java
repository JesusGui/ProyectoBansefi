package mx.babel.arq.serviciosAppwhere.dto.request.oficina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;

@AllArgsConstructor
@NoArgsConstructor
public class ReqActualizarArqueoCentroDTO extends ReqGralDTO {
    @Setter
    @Getter
    private String fechaCtble;
    @Setter
    @Getter
    private String cajaCobrosOn;
    @Setter
    @Getter
    private String cajaPagosOn;
    @Setter
    @Getter
    private String saldoCajaAnt;
    @Setter
    @Getter
    private String saldoCajaAct;
    @Setter
    @Getter
    private String totalArqueo;
    @Setter
    @Getter
    private String fechaOprcn;
    @Setter
    @Getter
    private String horaOprcn;
}
