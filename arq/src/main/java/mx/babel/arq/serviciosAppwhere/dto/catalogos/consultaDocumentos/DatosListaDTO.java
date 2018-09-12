package mx.babel.arq.serviciosAppwhere.dto.catalogos.consultaDocumentos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by AppWIns on 18/05/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatosListaDTO {

    private ArrayList<DocumentoDigitalizarDTO> DOCUMENTOS;

}
