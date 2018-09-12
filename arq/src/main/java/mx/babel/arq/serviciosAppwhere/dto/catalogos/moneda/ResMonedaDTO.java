package mx.babel.arq.serviciosAppwhere.dto.catalogos.moneda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResMonedaDTO{

    private List<MonedaDTO> xm_MONEDA_E;
    private String estatus;
    private String mensaje;
}
