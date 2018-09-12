package mx.babel.arq.serviciosAppwhere.dto.remesasNacionales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.babel.arq.serviciosAppwhere.dto.ApiErrorDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResCredencialesEntidadDto extends ApiErrorDto {

    private String user;
    private String password;
    private String terminal;
    private String branchOffice;
    private String organizationCode;
    private String timestamp;
    private String error;
    private Integer errorCode;
    private String status;
    private String message;
    private String path;

}
