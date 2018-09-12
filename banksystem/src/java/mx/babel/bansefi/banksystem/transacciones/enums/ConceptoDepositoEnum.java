package mx.babel.bansefi.banksystem.transacciones.enums;

/**
 * Enumerado que se encarga de recoger los codigos de operacion y sus descripciones
 * @author luis.gcastellano
 *
 */
public enum ConceptoDepositoEnum {
    CODIGO_010012("Depósito Efectivo", "010002"),
    CODIGO_030005("Cheques, pagarés y otros", "030005"),
    CODIGO_990002("Abonos varios", "990002"),
    CODIGO_990005("Abono pag prestamo", "990005");
    
    private String concepto;
    private String codigo;

    private ConceptoDepositoEnum(String concepto, String codigo){
        this.concepto = concepto;
        this.codigo = codigo;
    }
 
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}
