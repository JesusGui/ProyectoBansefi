package mx.babel.bansefi.banksystem.cuentas.controllers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDatosIPFBackend;
import mx.babel.bansefi.banksystem.base.beans.DepositoIPFBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.utils.NumberToLetterConverter;
import mx.babel.bansefi.banksystem.base.utils.PdfUtils;
import mx.babel.bansefi.banksystem.cuentas.backends.PagoCuotaBackend;
import mx.babel.bansefi.banksystem.cuentas.beans.CuotaIPFBean;
import mx.babel.bansefi.banksystem.cuentas.beans.RespuestaCuotaIPFBean;
import mx.babel.bansefi.banksystem.cuentas.enums.TipoCargoEnum;
import mx.babel.bansefi.banksystem.cuentas.utils.TarifasUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean(name = "pagoCuotaController")
@Component
@Scope("view")
public class PagoCuotaController {

    private final static String CUOTA_IPF = "cuotaIPF";
    private final static String RESP_CUOTA_IPF = "respuestaCuotaIPF";

    private CuotaIPFBean cuotaIPF;
    private RespuestaCuotaIPFBean respuestaCuotaIPF;
    private List<Integer> ipfsActivas;
    private CuentaBean acuerdoPlazo = null;

    @Autowired
    ConsultaDatosIPFBackend consultaDatosIPFBackend;

    @Autowired
    PagoCuotaBackend pagoCuotaBackend;

    @Autowired
    private PdfUtils pdfUtils;

    @Autowired
    private ContextoUtils contextoUtils;
    @Autowired
    private CatalogoUtils catalogoUtils;

    public void iniciaPaso1(){
        final Flash flash = obtieneFlash();
        if(null!=flash.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash())){
            acuerdoPlazo = (CuentaBean)flash.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
        }else{
            throw new NoControlableException("Ha ocurrido un error:",
                    "Acuerdo no disponible");
        }
        final List<DepositoIPFBean> ipfsLista = acuerdoPlazo.getIpfs();

        if (null== ipfsLista||ipfsLista.isEmpty()) {
            throw new NoControlableException("Ha ocurrido un error:",
                    "Datos del Subacuerdo no disponible");
        }
        ipfsActivas = new ArrayList<>(ipfsLista.size());
        for(final DepositoIPFBean ipfBean:ipfsLista){
            if(StringUtils.equals(ipfBean.getEstado(), ConstantesFuncionales.IPF_ESTADO_ACTIVO)){
                ipfsActivas.add(ipfBean.getNumSubAc());
            }
        }
        cuotaIPF = new CuotaIPFBean();
        cuotaIPF.setTipoCargo(TipoCargoEnum.ACUERDO);
        cuotaIPF.setNumAcuerdo(acuerdoPlazo.getNumeroCuenta());
        cuotaIPF.setDescripcion(acuerdoPlazo.getTipoCuenta());
        if(ipfsActivas.size() == 1){
            cuotaIPF.setNumSubAc(ipfsActivas.get(0));
            recalculaDatosIPF();
        }
    }


    public void iniciaPaso2(){
        final Flash flash = obtieneFlash();
        if(null!=flash.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash())){
            acuerdoPlazo = (CuentaBean)flash.get(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash());
        }else{
            throw new NoControlableException("Ha ocurrido un error:",
                    "Acuerdo no disponible");
        }
        if(null!=flash.get(PagoCuotaController.CUOTA_IPF)){
            cuotaIPF = (CuotaIPFBean)flash.get(PagoCuotaController.CUOTA_IPF);
        }else{
            throw new NoControlableException("Ha ocurrido un error:",
                    "Cuota IPF no disponible");
        }

        if(null!=flash.get(PagoCuotaController.RESP_CUOTA_IPF)){
            respuestaCuotaIPF = (RespuestaCuotaIPFBean)flash.get(PagoCuotaController.RESP_CUOTA_IPF);
        }else{
            throw new NoControlableException("Ha ocurrido un error:",
                    "Respuesta cuota IPF no disponible");
        }
        this.generaPlantillaDeposito();
    }

    /**
     *
     */
    private void recalculaDatosIPF() {
        final DepositoIPFBean depositoIPFBean = consultaDatosIPFBackend.ejecutarTRN(cuotaIPF.getNumAcuerdo(), cuotaIPF.getNumSubAc());
        if(null != depositoIPFBean && null!= depositoIPFBean.getProductosSimples()){
            cuotaIPF.setImporteMinimo(TarifasUtils.getCondicionImporteMinimoCuotaIPF(depositoIPFBean.getProductosSimples()));
            cuotaIPF.setImporteDeposito(TarifasUtils.getCondicionImporteCuotaIPF(depositoIPFBean.getProductosSimples()));
        }
    }

    public String irAHome() {
        this.obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), this.acuerdoPlazo);
        return NavegacionEnum.FICHA_CUENTA.getRuta();
    }

    public String irAPaso1() {
        this.obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), this.acuerdoPlazo);
        return NavegacionEnum.PAGO_CUOTA_1.getRuta();
    }

    public String realizarDeposito(){
        respuestaCuotaIPF = pagoCuotaBackend.ejecutarTRN(cuotaIPF);
        this.obtieneFlash().put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), this.acuerdoPlazo);
        this.obtieneFlash().put(PagoCuotaController.CUOTA_IPF, this.cuotaIPF);
        this.obtieneFlash().put(PagoCuotaController.RESP_CUOTA_IPF, this.respuestaCuotaIPF);
        return NavegacionEnum.PAGO_CUOTA_2.getRuta();
    }

    /**
     * @return Metodo utilizado para recuperar una instancia de Flash
     */
    public Flash obtieneFlash() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getFlash();
    }

    /**
     * @return the cuotaIPF
     */
    public CuotaIPFBean getCuotaIPF() {
        return cuotaIPF;
    }

    /**
     * @param cuotaIPF the cuotaIPF to set
     */
    public void setCuotaIPF(final CuotaIPFBean cuotaIPF) {
        this.cuotaIPF = cuotaIPF;
    }

    /**
     * @return the ipfsActivas
     */
    public List<Integer> getIpfsActivas() {
        return ipfsActivas;
    }

    /**
     * @param ipfsActivas the ipfsActivas to set
     */
    public void setIpfsActivas(final List<Integer> ipfsActivas) {
        this.ipfsActivas = ipfsActivas;
    }

    /**
     * Método para generar el comprobante de depósito
     * @param datos
     * @throws ControlableException
     * @throws NoControlableException
     */
    public void generaPlantillaDeposito()
            throws ControlableException, NoControlableException {

        final SimpleDateFormat formatter = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER_INT);
        final StringBuffer nombrePdf = new StringBuffer(this.contextoUtils.getId());
        final Integer fecha = Integer.parseInt(respuestaCuotaIPF.getFechaOperacion().replaceAll("-", ""));
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("tipoCuenta", this.cuotaIPF.getDescripcion());
        params.put("imposicion", this.cuotaIPF.getNumSubAc());
        try {
            final Date date = formatter.parse(fecha.toString());
            params.put("fecha", date);
        } catch (final ParseException e) {
            params.put("fecha", new Date());
        }
        params.put("hora", respuestaCuotaIPF.getHoraOperacion().replaceAll("\\.", ":"));
        params.put("plaza", this.buscarPlaza(respuestaCuotaIPF.getPlaza()));
        params.put("oficina", respuestaCuotaIPF.getSucursal());
        params.put("nombreTitular", this.respuestaCuotaIPF.getTitular());
        params.put("clabe", this.creaClabe(this.contextoUtils.getEntidad(),
                respuestaCuotaIPF.getPlaza(), respuestaCuotaIPF.getCuentaDeposito().toString(),
                respuestaCuotaIPF.getDigito()));
        params.put("importeEfectivo", this.respuestaCuotaIPF.getImporte());

        try{
            //TODO cambiar lo del doubleValue
            params.put("importeLetra", (NumberToLetterConverter
                    .convertirImporteAImporteEnletraMN(this.respuestaCuotaIPF.getImporte().doubleValue())));
        }catch(final NumberFormatException nfe){
            params.put("importeLetra", "No es posible realizar la conversión.");
        }

        if(StringUtils.equals("P007",this.acuerdoPlazo.getIdProducto())) {
            params.put("cuotas", this.respuestaCuotaIPF.getNumCuotas());
            params.put("total", this.respuestaCuotaIPF.getTotal());
            params.put("mensualidad", this.respuestaCuotaIPF.getMensualidad());
            params.put("meta", this.respuestaCuotaIPF.getMetaAhorro());
            params.put("interes", this.respuestaCuotaIPF.getInteres());
            params.put("isr", this.respuestaCuotaIPF.getIsr());
            params.put("neto", this.respuestaCuotaIPF.getNeto());
            params.put("deposito", this.respuestaCuotaIPF.getDeposito());
            params.put("saldo", this.respuestaCuotaIPF.getSaldo());
            pdfUtils.generaPdf("pagoCuota007Reporte.jrxml", params, null, null,
                    nombrePdf.toString(), null);
        }else if(StringUtils.equals("P005",this.acuerdoPlazo.getIdProducto())) {
            params.put("interes", this.respuestaCuotaIPF.getInteres());
            params.put("isr", this.respuestaCuotaIPF.getIsr());
            params.put("neto", this.respuestaCuotaIPF.getNeto());
            BigDecimal saldoAnterior = null;
            for(final DepositoIPFBean ipfBean : this.acuerdoPlazo.getIpfs()){
                if(this.cuotaIPF.getNumSubAc().intValue() == ipfBean.getNumSubAc().intValue()){
                    saldoAnterior = ipfBean.getSaldo();
                    break;
                }
            }
            params.put("saldoAnterior", saldoAnterior);
            params.put("saldo", this.respuestaCuotaIPF.getSaldo());
            pdfUtils.generaPdf("pagoCuota005Reporte.jrxml", params, null, null,
                    nombrePdf.toString(), null);
        }else{
            throw new NoControlableException("Ha ocurrido un error:", "Reporte para tarifa "+
              this.acuerdoPlazo.getIdProducto()+" no disponible");
        }
    }

    /**
    *
    * @param clave de la plaza a buscar
    * @return Descripcion de la plaza
    */
   public String buscarPlaza(final String clave){
       String descripcion = clave;
       final List<CatalogoBean> catalogo = catalogoUtils.getCatalogo(CatalogoEnum.TP_PLAZA_BANCARIA);

       for (final CatalogoBean elemento : catalogo) {
           if(elemento.getClaveFila().equals(clave.trim())){
               descripcion = elemento.getDescripcionL();
           }
       }

       return descripcion;
   }


   /**
   *
   * @param entidad
   * @param plaza
   * @param cuenta
   * @param digito
   * @return CLABE interbancaria
   */
  public String creaClabe(final String entidad, final String plaza, String cuenta,
          final String digito) {

      cuenta = org.apache.commons.lang.StringUtils.leftPad(cuenta, 10, "0");

      final StringBuffer clabeBuffer =
              new StringBuffer().append(entidad.substring(1)).append(plaza.trim())
                      .append(" ")
                      .append("0")
                      .append(cuenta)
                      .append(" ")
                      .append(digito);

      return clabeBuffer.toString();
  }
}
