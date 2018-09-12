package mx.babel.bansefi.banksystem.cuentas.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import mx.babel.bansefi.banksystem.base.backends.ConsultaCuentaBackend;
import mx.babel.bansefi.banksystem.base.backends.consultasaldo.ConsultaSaldoBackEnd;
import mx.babel.bansefi.banksystem.base.beans.DepositoIPFBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.SaldoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.CuentaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.RelacionadoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.relacioncuenta.TipoRelacionPersonaCuenta;
import mx.babel.bansefi.banksystem.base.beans.productossimples.ProductoSimpleBean;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.NavegacionEnum;
import mx.babel.bansefi.banksystem.base.enums.ParametrosFlashEnum;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.ManagedBeanStateRecover;
import mx.babel.bansefi.banksystem.cuentas.controllers.AltaIPFController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltaIPFUtils {
	
	private final static String ID_PRODUCTO_PRLVS = "P006";
	
    @Autowired
    ConsultaSaldoBackEnd consultaSaldoBackEnd;
	@Autowired
	ConsultaCuentaBackend consultaCuentaBackend;
    @Autowired
    ManagedBeanStateRecover managedBeanStateRecover;

    public boolean noSuperaImporteMinimoIPF(final CuentaBean acuerdoPlazo, final List<ProductoSimpleBean> prodSimpleList){

        final BigDecimal importeMinimo = TarifasUtils.getImporteMinimoIPF(prodSimpleList);
        final Long cuentaOP = this.getCuentaOperativa(acuerdoPlazo);

        final ClientePersonaFisicaBean persona = this.getTitular(acuerdoPlazo);
        if(persona != null){
           final SaldoBean saldo = getSaldoContable(cuentaOP, persona);
           if(saldo!=null && null!=saldo.getSaldoContable()
                   && saldo.getSaldoContable().compareTo(importeMinimo)==-1){
               return true;
           }
        }
        return false;
    }

    /**
     * @param cuentaOP
     * @param persona
     * @return
     */
    private SaldoBean getSaldoContable(final Long cuentaOP,
            final ClientePersonaFisicaBean persona) {
        String tpIdentificacion = ConstantesFuncionales.getRelacionIdentificacionDocumento().
                get(persona.getTipoIdentificacion());
        if(tpIdentificacion == null){
        	tpIdentificacion = persona.getTipoIdentificacion();
        }
        final SaldoBean saldo = consultaSaldoBackEnd.consultaSaldo(new BigInteger(cuentaOP.toString()),
                tpIdentificacion, persona.getNumIdentificacion() );
        return saldo;
    }

    public ClientePersonaFisicaBean getTitular(final CuentaBean acuerdoPlazo) {
        ClientePersonaFisicaBean persona = new ClientePersonaFisicaBean();
        for (final RelacionadoBean relacionado : acuerdoPlazo.getPersonasRelacionadas()) {
            if((TipoRelacionPersonaCuenta.TITULAR.equals(relacionado.getTipo()))){
                if(relacionado.getNumero() == 1){
                    persona = relacionado.getPersona();
                }
            }
        }
        return persona;
    }

    public String logicaRedireccionRealizarDesposito(final CuentaBean acuerdoPlazo,
            final List<ProductoSimpleBean> prodSimpleList, final boolean origenFichaCuenta){

        final BigDecimal importeMinimo = TarifasUtils.getImporteMinimoIPF(prodSimpleList);
        final Long cuentaOP = this.getCuentaOperativa(acuerdoPlazo);

        final ClientePersonaFisicaBean cpfBean = this.getTitular(acuerdoPlazo);
        final SaldoBean saldo = this.getSaldoContable(cuentaOP, cpfBean);
        final Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put(ParametrosFlashEnum.NUMERO_CUENTA.getParamFlash(), cuentaOP);
        flash.put(ParametrosFlashEnum.TITULAR_CUENTA.getParamFlash(), cpfBean);
        flash.put(ParametrosFlashEnum.IMPORTE_MINIMO_IPF.getParamFlash(), new BigDecimal(500));
        flash.put(ParametrosFlashEnum.ES_DEPOSITO_IPF.getParamFlash(), true);
        final AltaIPFController altaIPF = new AltaIPFController();
        altaIPF.setAcuerdoPlazo(acuerdoPlazo);
        altaIPF.setCliente(cpfBean);
        altaIPF.setImporteMinimo(importeMinimo);
        altaIPF.setImporteMinimo(importeMinimo);
        altaIPF.setOrigenFichaCuenta(origenFichaCuenta);
        altaIPF.setModificacion(true);
        managedBeanStateRecover.enviaController(altaIPF, NavegacionEnum.ALTA_IPF_1);
        return NavegacionEnum.REALIZAR_DEPOSITO.getRuta();
    }

    public String logicaRedireccionAltaIPF(final CuentaBean acuerdoPlazo, final List<ProductoSimpleBean> prodSimpleList,
            final boolean origenFichaCuenta){

        final BigDecimal importeMinimo = TarifasUtils.getImporteMinimoIPF(prodSimpleList);

        final ClientePersonaFisicaBean cpfBean = this.getTitular(acuerdoPlazo);
        final Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), acuerdoPlazo);
        flash.put(ParametrosFlashEnum.CLIENTE.getParamFlash(), cpfBean);
        flash.put(ParametrosFlashEnum.IMPORTE_MINIMO_IPF.getParamFlash(), importeMinimo);
        flash.put(ParametrosFlashEnum.ORIGEN_FICHA_CUENTA.getParamFlash(), origenFichaCuenta);
        flash.put(ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(), true);
        return NavegacionEnum.ALTA_IPF_1.getRuta();
    }

    public String logicaRedireccionConsultaIPF(final CuentaBean acuerdoPlazo, final List<ProductoSimpleBean> prodSimpleList,
            final List<DepositoIPFBean> ipfs){

        final BigDecimal importeMinimo = TarifasUtils.getImporteMinimoIPF(prodSimpleList);

        final ClientePersonaFisicaBean cpfBean = this.getTitular(acuerdoPlazo);
        final Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put(ParametrosFlashEnum.CUENTA_BEAN.getParamFlash(), acuerdoPlazo);
        flash.put(ParametrosFlashEnum.CLIENTE.getParamFlash(), cpfBean);
        flash.put(ParametrosFlashEnum.IMPORTE_MINIMO_IPF.getParamFlash(), importeMinimo);
        flash.put(ParametrosFlashEnum.ORIGEN_FICHA_CUENTA.getParamFlash(), true);
        flash.put(ParametrosFlashEnum.IPFS_TODAS.getParamFlash(), ipfs);
        flash.put(ParametrosFlashEnum.MODIFICACION_CUENTA.getParamFlash(), false);
        return NavegacionEnum.ALTA_IPF_1.getRuta();
    }

    public Long getCuentaOperativa(final CuentaBean cuenta) {
        if(null!=cuenta && null != cuenta.getCuentasRelacionadas()){
            for(final CuentaRelacionadaBean crb : cuenta.getCuentasRelacionadas()){
               if(StringUtils.equals(crb.getTipoRelacion(), ConstantesFuncionales.REL_AC_AC_CUENTA_OPERATIVA)
                       && crb.getNumero() == 1 && crb.getCuenta()!=null){
                   return crb.getCuenta().getNumeroCuenta();
               }
            }
        }
        return null;
    }


    public Integer getFechaVencimientoValida(final Date fechaVto) {
        if(null!=fechaVto){
            final IntegerToDateConverter itdConverter = new IntegerToDateConverter();
            final int intFechaVto = itdConverter.convertFrom(fechaVto);
            final int intFechaHoy = itdConverter.convertFrom(Calendar.getInstance().getTime());
            if(intFechaVto>intFechaHoy){
                return intFechaVto;
            }
        }
        return 0;
    }
    
    public Boolean isPRLVS(final CuentaBean cuenta){
    	Boolean prlvs = false;
    	if(StringUtils.isEmpty(cuenta.getIdProducto())){
    		CuentaBean cuentaBean = null;
    		try{
    			cuentaBean  = consultaCuentaBackend.ejecutarTRN(cuenta.getNumeroCuenta());
    		}catch(Exception e){
    		}
    		if(cuentaBean != null && !StringUtils.isEmpty(cuentaBean.getIdProducto())){
    			cuenta.setIdProducto(cuentaBean.getIdProducto());
    		}
    	}
    	if(cuenta != null && ID_PRODUCTO_PRLVS.equals(cuenta.getIdProducto())){
    		prlvs = true;
    	}
    	return prlvs;
    }

}
