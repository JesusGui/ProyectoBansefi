package mx.babel.bansefi.banksystem.base.beans.domain;

import mx.babel.arq.comun.exceptions.NoControlableException;

import org.apache.commons.lang3.StringUtils;

/**
 * Enum para clasificación de clientes.
 * 
 * @author mario.montesdeoca
 *
 */
public enum TipoCliente {
	PERSONA_FISICA("Persona Física", "F", 1),
	MENOR_EDAD_DISCAPAZ("Menor de edad / Incapaz", "F",1),
	CLIENTE_GESTOR("Cliente Gestor","F",1),
	PERSONA_MORAL("Persona Moral", "J",1),
	GRUPO("Grupo", "G",2),
	GESTOR("Gestor", "E",3),
	FUSIONADO("Fusionado", "Fu",3);

	private String tipo;
	private String codPe;
	private int tipoFicha;

	/**
	 *  Constructor.
	 * @param tipo
	 */
	private TipoCliente(final String tipo, final String codPe, final int tipoFicha){
        this.tipo = tipo;
		this.codPe = codPe;
		this.tipoFicha = tipoFicha;
	}

	/**
	 * @return Atributo tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo Atributo tipo a definir
	 */
	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

    /**
     * @return the codPe
     */
    public String getCodPe() {
        return codPe;
    }
    
    /**
	 * @return Atributo modalidad
	 */
	public int getTipoFicha() {
		return tipoFicha;
	}

    /**
     * Método para obtener un tipo de cliente enum por medio de su descripción.
     * @param tipoCliente Descripción del tipo de cliente
     * @return Enum de tipo de cliente
     */
    public static TipoCliente getTipoCliente(final String tipoCliente){
    	if(tipoCliente == null){
    		return null;
    	}
        if(StringUtils.isNotBlank(tipoCliente)){
            for(final TipoCliente tpCliente: TipoCliente.values()){
                if(StringUtils.equals(tpCliente.getTipo(), tipoCliente)){
                    return tpCliente;
                }
            }
        }
        throw new NoControlableException("TipoCliente no encontrado",
                TipoCliente.class.getName()+": No encontrada correspondencia con el tipoCliente "+tipoCliente);
    }
    
    /**
     * Método para obtener un tipo de cliente enum por medio de su código
     * @param codPe Código de persona
     * @return Enum de tipo de cliente
     */
    public static TipoCliente codPeDe(final String codPe){
    	if(StringUtils.isNotBlank(codPe)){
            for(final TipoCliente tpCliente: TipoCliente.values()){
                if(StringUtils.equals(tpCliente.getCodPe(), codPe)){
                    return tpCliente;
                }
            }
        }
        throw new NoControlableException("TipoCliente no encontrado",
                TipoCliente.class.getName()+": Código "+ codPe +" no encontrado.");
    }

}
