package mx.babel.arq.serviciosAppwhere.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatosSesionDTO {

    private String usuario;
    private String password;
    private String nombre;
    private String entidad;
    private String centro;
    private String ventanilla;
    private String fecsys;

}
