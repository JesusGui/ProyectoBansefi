package mx.babel.arq.serviciosAppwhere.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResEncryptDTO {

    private Integer codRet;
    private String error;
    private String respuesta;

}
