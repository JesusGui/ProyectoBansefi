package mx.babel.arq.serviciosAppwhere.dto.catalogos.catPerMor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqCatPerMorDTO extends ReqGralDTO {

    private String tblRef;
    private String aplcnSub;
    private String indPag;
    private String cveFila;
}
