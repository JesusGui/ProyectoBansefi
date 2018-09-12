package mx.babel.bansefi.banksystem.personas.beans;

import java.io.Serializable;

import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;
import mx.babel.bansefi.banksystem.personas.enums.BeanCrudEnum;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang3.StringUtils;

public class OtroValorBean implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 3920767012926707004L;
    private Integer idInterno;
    private String clave;
    private String descripcion;
    private String valor;
    private String numero;
    private String valorAnterior;
    private BeanCrudEnum operacion;
    private EstadoListadosEnum estado;
    private String respaldo;
    private boolean pintarEdicion;


    public OtroValorBean() {
        this.operacion = BeanCrudEnum.SIN_CAMBIOS;
    }

    public OtroValorBean(final String clave, final String descripcion) {
        super();
        this.clave = clave;
        this.descripcion = descripcion;
        this.operacion = BeanCrudEnum.ALTA;
    }
    

    /**
     * @return the idInterno
     */
    public Integer getIdInterno() {
        return idInterno;
    }

    /**
     * @param idInterno the idInterno to set
     */
    public void setIdInterno(final Integer idInterno) {
        this.idInterno = idInterno;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }
    /**
     * @param clave the clave to set
     */
    public void setClave(final String clave) {
        this.clave = clave;
    }
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }
    /**
     * @param nombre the valor to set
     */
    public void setValor(final String valor) {
        this.valor = valor;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(final String numero) {
        this.numero = numero;
    }
    
    public String getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	/**
     * @return the operacion
     */
    public BeanCrudEnum getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(final BeanCrudEnum operacion) {
        this.operacion = operacion;
    }
    
	public EstadoListadosEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoListadosEnum estado) {
		this.estado = estado;
	}
	
	public String getRespaldo() {
		return respaldo;
	}

	public void setRespaldo(String respaldo) {
		this.respaldo = respaldo;
	}

	public void valueChanged(){

        if(! BeanCrudEnum.isModified(this.operacion)){
            this.operacion = BeanCrudEnum.addNewState(this.operacion, BeanCrudEnum.MODIFICACION);
        }
    }

    public void deleteValue(){
        if(! BeanCrudEnum.isDeleted(this.operacion)){
            this.operacion = BeanCrudEnum.addNewState(this.operacion, BeanCrudEnum.BAJA);
        }
    }

    public void restoreValue(){
        if(BeanCrudEnum.isDeleted(this.operacion)){
            this.operacion = BeanCrudEnum.removeNewState(this.operacion, BeanCrudEnum.BAJA);
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append(idInterno)
        .append(clave)
        .append(descripcion)
        .append(valor)
        .append(numero)
        .toHashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OtroValorBean)) {
            return false;
        }
        final OtroValorBean castObj = (OtroValorBean) obj;
        return new EqualsBuilder()
        .append(idInterno, castObj.idInterno)
        .append(clave, castObj.clave)
        .append(descripcion, castObj.descripcion)
        .append(valor, castObj.valor)
        .append(numero, castObj.numero)
        .isEquals();
    }

    public boolean isDeleted(){
        return BeanCrudEnum.isDeleted(this.operacion);
    }

    public boolean isEditable(){
        return BeanCrudEnum.isAlta(this.operacion);
    }

    public boolean isEmail(){
        return StringUtils.equalsIgnoreCase(ConstantesFuncionales.DIR_INTERNET.getClaveFila(), this.clave);
    }

	public boolean isPintarEdicion() {
		return pintarEdicion;
	}

	public void setPintarEdicion(boolean pintarEdicion) {
		this.pintarEdicion = pintarEdicion;
	}
}
