package mx.babel.bansefi.banksystem.cuentas.beans;

import java.util.Comparator;
import java.util.Date;

public class PlanificacionBeanFechaComparator  implements Comparator<PlanificacionBean>{
    
    @Override
    public int compare(PlanificacionBean o1, PlanificacionBean o2) {

        Date fechao1 = o1.getFechaPlanificacion();
        Date fechao2 = o2.getFechaPlanificacion();
        
        return fechao1.compareTo(fechao2);

    }
}
