package mx.babel.bansefi.banksystem.integracionesAppWhere.services;

import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.cuentas.beans.TarifaBean;

public interface IIntegracionService {

    String getBsfOperadorComun(ContextoUtils contextoUtils);

    String getBsfOperadorMovimientos(ContextoUtils contextoUtils);

    String getBsfOperadorSolicitudesLimites(ContextoUtils contextoUtils);

    String getBsfOperadorAltaCuentaPlazo(ContextoUtils contextoUtils, ClienteBean clienteBean, TarifaBean tarifaBean);

    String getBsfOperadorCambioTarifa(ContextoUtils contextoUtils, String acuerdo, String tipoAcuerdo);

    String getBsfOperadorFichaPersonaFisica(ContextoUtils contextoUtils, String idInternoPe);

    String getBsfOperadorRelacionarPersonaMoral(ContextoUtils contextoUtils, String idInternoPe, String razonSocial);

    String getBsfOperadorFichaPersonaMoral(ContextoUtils contextoUtils, String idInternoPe);

    String getBsfOperadorPersonas(ContextoUtils contextoUtils, String idInternoPe, String nombrePersona, String codTx, String tipoPersona, String numOrdEmpPe);

    String getBsfOperadorDudosidad(ContextoUtils contextoUtils, String target);

}
