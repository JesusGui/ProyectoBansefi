package mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta;

import java.util.Comparator;

public class CuentaRelacionadaBeanTipoComparator implements Comparator<CuentaRelacionadaBean> {
    @Override
    public int compare(CuentaRelacionadaBean a, CuentaRelacionadaBean b) {
        return a.getTipoRelacion().compareTo(b.getTipoRelacion());
    }
}