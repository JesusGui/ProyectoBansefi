package mx.babel.bansefi.banksystem.transacciones.enums;

/**
 * Concepto retiro.
 * @author alejandro.perez
 *
 */

/**
 * Enumeración con los conceptos de retiro.
 * @author alejandro.perez
 *
 */
public enum ConceptoRetiroEnum {
    CODIGO_010001("Reintegro caja", "010001"),
    CODIGO_030005("Doc. no compensado", "030005"),
    CODIGO_220054("Recibos varios", "220054"),
    CODIGO_990001("Adeudos varios", "990001");
    
    private String concepto;
    private String codigo;

    private ConceptoRetiroEnum(String concepto, String codigo){
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
