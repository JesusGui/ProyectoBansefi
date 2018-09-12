
package mx.babel.bansefi.banksystem.oficina.webservices.consultadetallediarioelectronico;

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
 *         &lt;element name="ICONDIA02_I">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="FECHA">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;length value="0000000010"/>
 *                         &lt;whiteSpace value="preserve"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="IDTASK">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;length value="0000000013"/>
 *                         &lt;whiteSpace value="preserve"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ENTIDAD">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;length value="0000000004"/>
 *                         &lt;whiteSpace value="preserve"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="CENTRO">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;length value="0000000004"/>
 *                         &lt;whiteSpace value="preserve"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="TERMIN">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;length value="0000000008"/>
 *                         &lt;whiteSpace value="preserve"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "icondia02I"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USERHEADER", required = true)
    protected String userheader;
    @XmlElement(name = "PASSHEADER", required = true)
    protected String passheader;
    @XmlElement(name = "IPHEADER", required = true)
    protected String ipheader;
    @XmlElement(name = "ICONDIA02_I", required = true)
    protected Ejecutar.ICONDIA02I icondia02I;

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
     * Obtiene el valor de la propiedad icondia02I.
     * 
     * @return
     *     possible object is
     *     {@link Ejecutar.ICONDIA02I }
     *     
     */
    public Ejecutar.ICONDIA02I getICONDIA02I() {
        return icondia02I;
    }

    /**
     * Define el valor de la propiedad icondia02I.
     * 
     * @param value
     *     allowed object is
     *     {@link Ejecutar.ICONDIA02I }
     *     
     */
    public void setICONDIA02I(Ejecutar.ICONDIA02I value) {
        this.icondia02I = value;
    }


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
     *         &lt;element name="FECHA">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;length value="0000000010"/>
     *               &lt;whiteSpace value="preserve"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="IDTASK">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;length value="0000000013"/>
     *               &lt;whiteSpace value="preserve"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="ENTIDAD">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;length value="0000000004"/>
     *               &lt;whiteSpace value="preserve"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="CENTRO">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;length value="0000000004"/>
     *               &lt;whiteSpace value="preserve"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="TERMIN">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;length value="0000000008"/>
     *               &lt;whiteSpace value="preserve"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
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
        "fecha",
        "idtask",
        "entidad",
        "centro",
        "termin"
    })
    public static class ICONDIA02I {

        @XmlElement(name = "FECHA", required = true)
        protected String fecha;
        @XmlElement(name = "IDTASK", required = true)
        protected String idtask;
        @XmlElement(name = "ENTIDAD", required = true)
        protected String entidad;
        @XmlElement(name = "CENTRO", required = true)
        protected String centro;
        @XmlElement(name = "TERMIN", required = true)
        protected String termin;

        /**
         * Obtiene el valor de la propiedad fecha.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFECHA() {
            return fecha;
        }

        /**
         * Define el valor de la propiedad fecha.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFECHA(String value) {
            this.fecha = value;
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

    }

}
