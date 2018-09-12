package mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta;

import java.util.Comparator;

public class RelacionadoBeanTipoComparator implements Comparator<RelacionadoBean> {
    @Override
    public int compare(RelacionadoBean a, RelacionadoBean b) {
    	if(a.getTipo().getCodigo().equals(b.getTipo().getCodigo())){
    		return a.getNumero().compareTo(b.getNumero());
    	}
        return a.getTipo().getCodigo().compareTo(b.getTipo().getCodigo());
    }
}