package mx.babel.bansefi.banksystem.oficina.backends;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.serviciosAppwhere.clients.oficina.OficinaClient;
import mx.babel.arq.serviciosAppwhere.dto.DatosSesionDTO;
import mx.babel.arq.serviciosAppwhere.dto.ReqGralDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.ResConsultaTotalArqueoPuestosDTO;
import mx.babel.arq.serviciosAppwhere.dto.response.oficina.atributos.PuestoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.babel.arq.comun.beans.PaginacionBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.oficina.beans.ArqueoCentroBean;
import mx.babel.bansefi.banksystem.oficina.beans.CuadrePuestoBean;
import mx.babel.bansefi.banksystem.oficina.webservices.cuadrespuestos.CuadresPuestosServicio;
import mx.babel.bansefi.banksystem.oficina.webservices.cuadrespuestos.Ejecutar;
import mx.babel.bansefi.banksystem.oficina.webservices.cuadrespuestos.EjecutarResult;
import mx.babel.bansefi.banksystem.oficina.webservices.cuadrespuestos.ResponseBansefi;
import mx.babel.bansefi.banksystem.oficina.webservices.cuadrespuestos.ResponseBansefi.OUOCUADRECONSTNHTRNO;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@Component
public class CuadresPuestosBackEnd extends BackEndBean {

    private static final long serialVersionUID = -531799847861933443L;

    @Autowired
    ServicioWebUtils servicioWebUtils;

    @Autowired
    OficinaClient oficinaClient;

    /**
     * Método que consulta los cuadres de los puestos en un centro y los devuelve en una lista
     *
     * @return Lista de cuadres en puestos
     */
    public List<CuadrePuestoBean> obtenerCuadresBDIntermedia(ArqueoCentroBean arqueoCentro) {
        List<CuadrePuestoBean> puestos = new ArrayList<CuadrePuestoBean>();
        arqueoCentro.setTotalArqueoPuestos(new BigDecimal(0));
        arqueoCentro.setNetoTraspasosPuestos(new BigDecimal(0));
        arqueoCentro.setSaldoPuestos(new BigDecimal(0));
        //Consumir dos sercicos para obtener cuadres y saldos netos (JJVC)
        HttpServletRequest requestObj = (HttpServletRequest)
                FacesContext.getCurrentInstance().getExternalContext().getRequest();
        DatosSesionDTO datosSesion =
                (DatosSesionDTO) requestObj.getSession().getAttribute("datosSesion");

        ResConsultaTotalArqueoPuestosDTO resTotalArqueoPuestos =
                oficinaClient.consultaTotalArqueoPuestos(
                        new ReqGralDTO(
                                datosSesion.getUsuario(),
                                datosSesion.getPassword(),
                                datosSesion.getEntidad(),
                                datosSesion.getVentanilla()));
        if (resTotalArqueoPuestos != null && resTotalArqueoPuestos.getEstatus() != null && resTotalArqueoPuestos.getEstatus().equals("1")) {
            if (resTotalArqueoPuestos.getPuestos() != null) {
                for (PuestoDTO arqueoPuesto : resTotalArqueoPuestos.getPuestos()) {
                    CuadrePuestoBean cuadrePuestoUANL = new CuadrePuestoBean();
                    cuadrePuestoUANL.setTerminal(arqueoPuesto.getIdInternoTermTn());
                    cuadrePuestoUANL.setTotalArqueo(BigDecimal.valueOf(Double.valueOf(arqueoPuesto.getTotalArqueo())));
                    cuadrePuestoUANL.setSaldoCaja(BigDecimal.valueOf(Double.valueOf(arqueoPuesto.getSaldoCaja())));
                    cuadrePuestoUANL.setDiferencia(BigDecimal.valueOf(Double.valueOf(arqueoPuesto.getDiferencia())));

                    puestos.add(cuadrePuestoUANL);
                    arqueoCentro.setSaldoPuestos(arqueoCentro.getSaldoPuestos().add(cuadrePuestoUANL.getDiferencia()));
                    arqueoCentro.setTotalArqueoPuestos(arqueoCentro.getTotalArqueoPuestos().add(cuadrePuestoUANL.getTotalArqueo()));
                    arqueoCentro.setNetoTraspasosPuestos(arqueoCentro.getNetoTraspasosPuestos().add(cuadrePuestoUANL.getSaldoCaja()));
                }
            }
        }
        return puestos;
    }

    /**
     * Método que consulta los cuadres de los puestos en un centro y los devuelve en una lista
     *
     * @return Lista de cuadres en puestos
     */
    public List<CuadrePuestoBean> ejecutarTRN(ArqueoCentroBean arqueoCentro, PaginacionBean paginacion) {
        List<CuadrePuestoBean> puestos = new ArrayList<CuadrePuestoBean>();
        paginacion.setMasDatos(true);
        arqueoCentro.setTotalArqueoPuestos(new BigDecimal(0));
        arqueoCentro.setNetoTraspasosPuestos(new BigDecimal(0));
        arqueoCentro.setSaldoPuestos(new BigDecimal(0));
        while (paginacion.getMasDatos()) {
            puestos.addAll(obtenerCuadres(arqueoCentro, paginacion));
        }
        return puestos;
    }

    /**
     * Método que consulta los cuadres de los puestos en un centro y los devuelve en una lista
     *
     * @return Lista de cuadres en puestos
     */
    public List<CuadrePuestoBean> obtenerCuadres(ArqueoCentroBean arqueoCentro, PaginacionBean paginacion) {
        List<CuadrePuestoBean> puestos = new ArrayList<CuadrePuestoBean>();

        Ejecutar.IUOCUADRECONSTNHTRNI contexto = initPeticion(paginacion);

        EjecutarResult respuesta = ejecutarWS(contexto);

        try {
            super.verificaRespuesta(respuesta);
        } catch (ControlableException ce) {
            if (ce.getRtncod() != RETURN_COD_SIN_DATOS) {
                throw ce;
            } else {
                return puestos;
            }
        }

        puestos = getPuestos(respuesta.getResponseBansefi(), arqueoCentro, paginacion);

        return puestos;
    }

    /**
     * Método encargado de construir una lista de cuadres de puesta a partir de la respuesta del ws.
     *
     * @param respuesta Respuesta del ws
     * @return lista de cuadres
     */
    private List<CuadrePuestoBean> getPuestos(ResponseBansefi respuesta, ArqueoCentroBean arqueoCentro, PaginacionBean paginacion) {
        List<CuadrePuestoBean> puestos = new ArrayList<CuadrePuestoBean>();
        if (verificaRespuesta(respuesta)) {
            paginacion.adicionaNumeroDatos(respuesta.getOUOCUADRECONSTNHTRNO().getSTDCONTALISTA());
            paginacion.setMasDatos(respuesta.getOUOCUADRECONSTNHTRNO().getMOREDATAIN() == 1);
            List<OUOCUADRECONSTNHTRNO.UOTERMCNS2LP.UOTERMCNS2LPDESCRV> terminales =
                    respuesta.getOUOCUADRECONSTNHTRNO().getUOTERMCNS2LP().getUOTERMCNS2LPDESCRV();
            paginacion.setUltimoDatoConsultaAnterior(
                    terminales.get(respuesta.getOUOCUADRECONSTNHTRNO().getSTDCONTALISTA() - 1)
                            .getPCTERMINALT().getIDINTERNOTERMTN());
            for (int i = 0; i < respuesta.getOUOCUADRECONSTNHTRNO().getSTDCONTALISTA(); i++) {
                CuadrePuestoBean cuadrePuestoBean = new CuadrePuestoBean();
                cuadrePuestoBean.setTerminal(terminales.get(i).getPCTERMINALT().getIDINTERNOTERMTN());
                cuadrePuestoBean.setSaldoCaja(terminales.get(i).getPCTERMINALT().getIMPINI());
                cuadrePuestoBean.setTotalArqueo(terminales.get(i).getTOTALARQUEOPCV().get(0).getIMPINI());
                cuadrePuestoBean.setDiferencia(terminales.get(i).getDIFERENCIACAJAV().get(0).getIMPNOMINAL());
                puestos.add(cuadrePuestoBean);
            }
            OUOCUADRECONSTNHTRNO.UOTERMCNS2LP.UOTERMCNS2LPTOTV saldos =
                    respuesta.getOUOCUADRECONSTNHTRNO().getUOTERMCNS2LP().getUOTERMCNS2LPTOTV();
            arqueoCentro.setTotalArqueoPuestos(arqueoCentro.getTotalArqueoPuestos().add(saldos.getTOTALARQUEOPCV().getIMPINI()));
            arqueoCentro.setNetoTraspasosPuestos(arqueoCentro.getNetoTraspasosPuestos().add(saldos.getNETOTRASPASOSV().getIMPINI()));
            arqueoCentro.setSaldoPuestos(arqueoCentro.getSaldoPuestos().add(saldos.getDIFERENCIACAJAV().getIMPNOMINAL()));
        }
        return puestos;
    }

    /**
     * Método para inicializar el objeto de petición al ws.
     *
     * @return Objeto de petición al ws.
     */
    private Ejecutar.IUOCUADRECONSTNHTRNI initPeticion(PaginacionBean paginacion) {
        Ejecutar.IUOCUADRECONSTNHTRNI contexto = new Ejecutar.IUOCUADRECONSTNHTRNI();
        Ejecutar.IUOCUADRECONSTNHTRNI.STDAPPLPARMV cuerpoContexto =
                new Ejecutar.IUOCUADRECONSTNHTRNI.STDAPPLPARMV();
        Ejecutar.IUOCUADRECONSTNHTRNI.PCTERMINALP detalle =
                new Ejecutar.IUOCUADRECONSTNHTRNI.PCTERMINALP();
        contexto.setSTDAPPLPARMV(cuerpoContexto);
        contexto.setPCTERMINALP(detalle);
        super.initialize(contexto);

        contexto.setCODNUMRCOMONEDA(ConstantesFuncionales.COD_MONEDA);
        contexto.setFIRSTREADIN(BackEndBean.SCROLLABLE_OCCURS);

        cuerpoContexto.setCODNRBEEN(super.getEntidad());
        cuerpoContexto.setCODNRBEENFSC(super.getEntidad());
        cuerpoContexto.setCODINTERNOUO(super.getSucursal());
        cuerpoContexto.setCODINTERNOUOFSC(super.getSucursal());
        cuerpoContexto.setIDINTERNOTERMTN(super.getTerminal());
        cuerpoContexto.setIDINTERNOEMPLEP(super.getUsuarioId());
        cuerpoContexto.setCODTX(CodTxConstants.COD_TX_UO_CUADRE_CONS_TNH_TRN);

        IntegerToDateConverter itdc = new IntegerToDateConverter();
        cuerpoContexto.setFECHACTBLE(itdc.convertFrom(super.getFechaSistema()));
        cuerpoContexto.setFECHAOPRCN(itdc.convertFrom(super.getFechaSistema()));

        detalle.setCODINTERNOUOFSC(super.getSucursal());
        detalle.setCODNRBEENFSC(super.getEntidad());
        if (paginacion.getUltimoDatoConsultaAnterior() != null) {
            detalle.setIDINTERNOTERMTN((String) paginacion.getUltimoDatoConsultaAnterior());
        }
        return contexto;
    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     *
     * @param contexto Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.IUOCUADRECONSTNHTRNI contexto) {
        EjecutarResult respuesta = null;
        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
                    CuadresPuestosServicio.class, contexto);
        } catch (NoControlableException e) {
            throw new NoControlableException("Error al invocar servicio web de consulta "
                    + "de cuadres de puestos en centro.", e);
        }
        return respuesta;
    }

    /**
     * Función que valida que la respuesta Bansefi contenga un objeto con los datos
     * de cuentas.
     *
     * @param response Respuesta Bansefi con datos de cuentas
     * @return <code>true</code> si la respuesta Bansefi contiene los datos de alta
     */
    private Boolean verificaRespuesta(ResponseBansefi response) {
        Boolean noNulo = false;
        if (response != null && response.getOUOCUADRECONSTNHTRNO() != null &&
                response.getOUOCUADRECONSTNHTRNO().getUOTERMCNS2LP() != null &&
                response.getOUOCUADRECONSTNHTRNO().getUOTERMCNS2LP().getUOTERMCNS2LPDESCRV() != null) {
            noNulo = true;
        }
        return noNulo;
    }
}
