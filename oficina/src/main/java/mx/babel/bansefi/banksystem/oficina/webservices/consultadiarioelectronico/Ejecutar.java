
package mx.babel.bansefi.banksystem.oficina.webservices.consultadiarioelectronico;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="USERHEADER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PASSHEADER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IPHEADER" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FECHA_DESDE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FECHA_HASTA" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IMPORTE_DESDE" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="IMPORTE_HASTA" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="IDTASK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CENTRO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TERMIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HORA_INI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HORA_FIN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SIGNO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TIPO_OPERACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ACUERDO" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="USUARIO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userheader",
    "passheader",
    "ipheader",
    "fechadesde",
    "fechahasta",
    "importedesde",
    "importehasta",
    "idtask",
    "entidad",
    "centro",
    "termin",
    "horaini",
    "horafin",
    "signo",
    "tipooperacion",
    "acuerdo",
    "usuario"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USERHEADER", required = true)
    protected String userheader;
    @XmlElement(name = "PASSHEADER", required = true)
    protected String passheader;
    @XmlElement(name = "IPHEADER", required = true)
    protected String ipheader;
    @XmlElement(name = "FECHA_DESDE", required = true)
    protected String fechadesde;
    @XmlElement(name = "FECHA_HASTA", required = true)
    protected String fechahasta;
    @XmlElement(name = "IMPORTE_DESDE", required = true)
    protected BigDecimal importedesde;
    @XmlElement(name = "IMPORTE_HASTA", required = true)
    protected BigDecimal importehasta;
    @XmlElement(name = "IDTASK", required = true)
    protected String idtask;
    @XmlElement(name = "ENTIDAD", required = true)
    protected String entidad;
    @XmlElement(name = "CENTRO", required = true)
    protected String centro;
    @XmlElement(name = "TERMIN", required = true)
    protected String termin;
    @XmlElement(name = "HORA_INI", required = true)
    protected String horaini;
    @XmlElement(name = "HORA_FIN", required = true)
    protected String horafin;
    @XmlElement(name = "SIGNO", required = true)
    protected String signo;
    @XmlElement(name = "TIPO_OPERACION", required = true)
    protected String tipooperacion;
    @XmlElement(name = "ACUERDO", required = true)
    protected BigInteger acuerdo;
    @XmlElement(name = "USUARIO", required = true)
    protected String usuario;

    /**
     * Obtiene el valor de la propiedad userheader.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERHEADER() {
        return userheader;
    }

    /**
     * Define el valor de la propiedad userheader.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERHEADER(String value) {
        this.userheader = value;
    }

    /**
     * Obtiene el valor de la propiedad passheader.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPASSHEADER() {
        return passheader;
    }

    /**
     * Define el valor de la propiedad passheader.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPASSHEADER(String value) {
        this.passheader = value;
    }

    /**
     * Obtiene el valor de la propiedad ipheader.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPHEADER() {
        return ipheader;
    }

    /**
     * Define el valor de la propiedad ipheader.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPHEADER(String value) {
        this.ipheader = value;
    }

    /**
     * Obtiene el valor de la propiedad fechadesde.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHADESDE() {
        return fechadesde;
    }

    /**
     * Define el valor de la propiedad fechadesde.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHADESDE(String value) {
        this.fechadesde = value;
    }

    /**
     * Obtiene el valor de la propiedad fechahasta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHAHASTA() {
        return fechahasta;
    }

    /**
     * Define el valor de la propiedad fechahasta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHAHASTA(String value) {
        this.fechahasta = value;
    }

    /**
     * Obtiene el valor de la propiedad importedesde.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIMPORTEDESDE() {
        return importedesde;
    }

    /**
     * Define el valor de la propiedad importedesde.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIMPORTEDESDE(BigDecimal value) {
        this.importedesde = value;
    }

    /**
     * Obtiene el valor de la propiedad importehasta.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIMPORTEHASTA() {
        return importehasta;
    }

    /**
     * Define el valor de la propiedad importehasta.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIMPORTEHASTA(BigDecimal value) {
        this.importehasta = value;
    }

    /**
     * Obtiene el valor de la propiedad idtask.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDTASK() {
        return idtask;
    }

    /**
     * Define el valor de la propiedad idtask.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDTASK(String value) {
        this.idtask = value;
    }

    /**
     * Obtiene el valor de la propiedad entidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENTIDAD() {
        return entidad;
    }

    /**
     * Define el valor de la propiedad entidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENTIDAD(String value) {
        this.entidad = value;
    }

    /**
     * Obtiene el valor de la propiedad centro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCENTRO() {
        return centro;
    }

    /**
     * Define el valor de la propiedad centro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCENTRO(String value) {
        this.centro = value;
    }

    /**
     * Obtiene el valor de la propiedad termin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTERMIN() {
        return termin;
    }

    /**
     * Define el valor de la propiedad termin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTERMIN(String value) {
        this.termin = value;
    }

    /**
     * Obtiene el valor de la propiedad horaini.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHORAINI() {
        return horaini;
    }

    /**
     * Define el valor de la propiedad horaini.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHORAINI(String value) {
        this.horaini = value;
    }

    /**
     * Obtiene el valor de la propiedad horafin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHORAFIN() {
        return horafin;
    }

    /**
     * Define el valor de la propiedad horafin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHORAFIN(String value) {
        this.horafin = value;
    }

    /**
     * Obtiene el valor de la propiedad signo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIGNO() {
        return signo;
    }

    /**
     * Define el valor de la propiedad signo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIGNO(String value) {
        this.signo = value;
    }

    /**
     * Obtiene el valor de la propiedad tipooperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIPOOPERACION() {
        return tipooperacion;
    }

    /**
     * Define el valor de la propiedad tipooperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIPOOPERACION(String value) {
        this.tipooperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad acuerdo.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getACUERDO() {
        return acuerdo;
    }

    /**
     * Define el valor de la propiedad acuerdo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setACUERDO(BigInteger value) {
        this.acuerdo = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSUARIO() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSUARIO(String value) {
        this.usuario = value;
    }

}
