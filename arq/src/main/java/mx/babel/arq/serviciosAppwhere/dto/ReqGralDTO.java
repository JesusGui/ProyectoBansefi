package mx.babel.arq.serviciosAppwhere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by AppWIns on 26/07/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqGralDTO {

    private String usuario;
    private String password;
    private String entidad;
    private String terminal;

}
