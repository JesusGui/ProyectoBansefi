package mx.babel.bansefi.banksystem.integracionesAppWhere.dto.bsfOperador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro.DatosCentroDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BsfOperadorDTO {

    private String ENTIDAD;
    private String CENTRO;
    private Integer TERMINAL;
    private String USERTCB;
    private String SESSIONID;
    private Object PASSTCB;
    private String NOMBREEMPLEADO;
    private String NOMBRECENTRO;
    private String FECHACONTABLE;
    private DatosCentroDTO DIRECCIONCENTRO;
    private Object TRANSPORT;

}
