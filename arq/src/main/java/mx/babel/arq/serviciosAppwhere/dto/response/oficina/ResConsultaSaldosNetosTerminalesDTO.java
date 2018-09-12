package mx.babel.arq.serviciosAppwhere.dto.response.oficina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.babel.arq.serviciosAppwhere.dto.ResGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.atributos.SaldoDTO;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class ResConsultaSaldosNetosTerminalesDTO extends ResGralDTO{
    @Setter
    @Getter
    ArrayList<SaldoDTO> saldos;
}
