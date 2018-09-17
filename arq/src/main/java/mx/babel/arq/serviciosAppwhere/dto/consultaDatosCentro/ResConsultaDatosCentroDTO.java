package mx.babel.arq.serviciosAppwhere.dto.consultaDatosCentro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResConsultaDatosCentroDTO {

    private String STATUS;
    private ContDatosCentroDTO DATOS;

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public ContDatosCentroDTO getDATOS() {
        return DATOS;
    }

    public void setDATOS(ContDatosCentroDTO DATOS) {
        this.DATOS = DATOS;
    }

    public String getERROR_DESC() {
        return ERROR_DESC;
    }

    public void setERROR_DESC(String ERROR_DESC) {
        this.ERROR_DESC = ERROR_DESC;
    }

    private String ERROR_DESC;

}
