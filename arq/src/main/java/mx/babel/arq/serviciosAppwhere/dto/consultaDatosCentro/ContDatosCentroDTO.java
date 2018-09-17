package mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContDatosCentroDTO {

    public DatosCentroDTO getCENTRO() {
        return CENTRO;
    }

    public void setCENTRO(DatosCentroDTO CENTRO) {
        this.CENTRO = CENTRO;
    }

    private DatosCentroDTO CENTRO;

}
