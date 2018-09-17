package mx.babel.arq.serviciosAppwhere.dto.request.oficina;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;

@AllArgsConstructor
@NoArgsConstructor
public class ReqActializarEfectivoInicialDTO extends ReqGralDTO {
    @Setter
    @Getter
    private String impEfectivoIni;

    public String getImpEfectivoIni() {
        return impEfectivoIni;
    }

    public void setImpEfectivoIni(String impEfectivoIni) {
        this.impEfectivoIni = impEfectivoIni;
    }

    public String getBorrarSaldoNetoTraspasos() {
        return borrarSaldoNetoTraspasos;
    }

    public void setBorrarSaldoNetoTraspasos(String borrarSaldoNetoTraspasos) {
        this.borrarSaldoNetoTraspasos = borrarSaldoNetoTraspasos;
    }

    @Setter
    @Getter
    private String borrarSaldoNetoTraspasos;
}
