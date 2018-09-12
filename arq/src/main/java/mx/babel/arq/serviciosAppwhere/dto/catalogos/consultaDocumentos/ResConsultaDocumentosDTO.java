package mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaDocumentos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by AppWIns on 18/05/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResConsultaDocumentosDTO {

    private Integer STATUS;
    private DatosListaDTO DATOS_LISTA;

}
