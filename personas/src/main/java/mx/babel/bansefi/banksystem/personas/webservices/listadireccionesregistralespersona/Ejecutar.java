
package mx.babel.bansefi.banksystem.personas.webservices.listadireccionesregistralespersona;

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
 *         &lt;element name="ITR_DR_REG_LS_CNS_TRN_I">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ELEVATOR_POSITION">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                         &lt;maxInclusive value="9999"/>
 *                         &lt;minInclusive value="0"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="SCROLLABLE_OCCURS">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                         &lt;maxInclusive value="9999"/>
 *                         &lt;minInclusive value="0"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="TR_DR_REG_LS_CNS_EVT_Y">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PE_PERS_RL_DIR_P">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="COD_NRBE_EN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000004"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="ID_INTERNO_PE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_PERS_RL_DIR">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_DIR">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NUM_DIR">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="FECHA_INIC_RL">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="HORA_OPRCN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="BI_BIEN_RL_DIR_P">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="COD_NRBE_EN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000004"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="ID_INTERNO_BI">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_DIR">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NUM_DIR">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="FECHA_INIC_RL">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="HORA_OPRCN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="STD_TRN_I_PARM_V">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ID_INTERNO_TERM_TN">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000008"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="ID_EMPL_AUT">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000008"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NUM_SEC">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="9999999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_TX">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000008"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_TX_DI">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000004"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
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
    "itrdrreglscnstrni"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USERHEADER", required = true)
    protected String userheader;
    @XmlElement(name = "PASSHEADER", required = true)
    protected String passheader;
    @XmlElement(name = "IPHEADER", required = true)
    protected String ipheader;
    @XmlElement(name = "ITR_DR_REG_LS_CNS_TRN_I", required = true)
    protected Ejecutar.ITRDRREGLSCNSTRNI itrdrreglscnstrni;

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
     * Obtiene el valor de la propiedad itrdrreglscnstrni.
     * 
     * @return
     *     possible object is
     *     {@link Ejecutar.ITRDRREGLSCNSTRNI }
     *     
     */
    public Ejecutar.ITRDRREGLSCNSTRNI getITRDRREGLSCNSTRNI() {
        return itrdrreglscnstrni;
    }

    /**
     * Define el valor de la propiedad itrdrreglscnstrni.
     * 
     * @param value
     *     allowed object is
     *     {@link Ejecutar.ITRDRREGLSCNSTRNI }
     *     
     */
    public void setITRDRREGLSCNSTRNI(Ejecutar.ITRDRREGLSCNSTRNI value) {
        this.itrdrreglscnstrni = value;
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
     *         &lt;element name="ELEVATOR_POSITION">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *               &lt;maxInclusive value="9999"/>
     *               &lt;minInclusive value="0"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="SCROLLABLE_OCCURS">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *               &lt;maxInclusive value="9999"/>
     *               &lt;minInclusive value="0"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="TR_DR_REG_LS_CNS_EVT_Y">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="PE_PERS_RL_DIR_P">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="COD_NRBE_EN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000004"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="ID_INTERNO_PE">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_PERS_RL_DIR">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_DIR">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="NUM_DIR">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="FECHA_INIC_RL">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="HORA_OPRCN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="BI_BIEN_RL_DIR_P">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="COD_NRBE_EN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000004"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="ID_INTERNO_BI">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_DIR">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="NUM_DIR">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="FECHA_INIC_RL">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="HORA_OPRCN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="STD_TRN_I_PARM_V">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ID_INTERNO_TERM_TN">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000008"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="ID_EMPL_AUT">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000008"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NUM_SEC">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="9999999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_TX">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000008"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_TX_DI">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000004"/>
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
        "elevatorposition",
        "scrollableoccurs",
        "trdrreglscnsevty",
        "stdtrniparmv"
    })
    public static class ITRDRREGLSCNSTRNI {

        @XmlElement(name = "ELEVATOR_POSITION")
        protected int elevatorposition;
        @XmlElement(name = "SCROLLABLE_OCCURS")
        protected int scrollableoccurs;
        @XmlElement(name = "TR_DR_REG_LS_CNS_EVT_Y", required = true)
        protected Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY trdrreglscnsevty;
        @XmlElement(name = "STD_TRN_I_PARM_V", required = true)
        protected Ejecutar.ITRDRREGLSCNSTRNI.STDTRNIPARMV stdtrniparmv;

        /**
         * Obtiene el valor de la propiedad elevatorposition.
         * 
         */
        public int getELEVATORPOSITION() {
            return elevatorposition;
        }

        /**
         * Define el valor de la propiedad elevatorposition.
         * 
         */
        public void setELEVATORPOSITION(int value) {
            this.elevatorposition = value;
        }

        /**
         * Obtiene el valor de la propiedad scrollableoccurs.
         * 
         */
        public int getSCROLLABLEOCCURS() {
            return scrollableoccurs;
        }

        /**
         * Define el valor de la propiedad scrollableoccurs.
         * 
         */
        public void setSCROLLABLEOCCURS(int value) {
            this.scrollableoccurs = value;
        }

        /**
         * Obtiene el valor de la propiedad trdrreglscnsevty.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY }
         *     
         */
        public Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY getTRDRREGLSCNSEVTY() {
            return trdrreglscnsevty;
        }

        /**
         * Define el valor de la propiedad trdrreglscnsevty.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY }
         *     
         */
        public void setTRDRREGLSCNSEVTY(Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY value) {
            this.trdrreglscnsevty = value;
        }

        /**
         * Obtiene el valor de la propiedad stdtrniparmv.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.ITRDRREGLSCNSTRNI.STDTRNIPARMV }
         *     
         */
        public Ejecutar.ITRDRREGLSCNSTRNI.STDTRNIPARMV getSTDTRNIPARMV() {
            return stdtrniparmv;
        }

        /**
         * Define el valor de la propiedad stdtrniparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.ITRDRREGLSCNSTRNI.STDTRNIPARMV }
         *     
         */
        public void setSTDTRNIPARMV(Ejecutar.ITRDRREGLSCNSTRNI.STDTRNIPARMV value) {
            this.stdtrniparmv = value;
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
         *         &lt;element name="ID_INTERNO_TERM_TN">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000008"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="ID_EMPL_AUT">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000008"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NUM_SEC">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="9999999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_TX">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000008"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_TX_DI">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000004"/>
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
            "idinternotermtn",
            "idemplaut",
            "numsec",
            "codtx",
            "codtxdi"
        })
        public static class STDTRNIPARMV {

            @XmlElement(name = "ID_INTERNO_TERM_TN", required = true)
            protected String idinternotermtn;
            @XmlElement(name = "ID_EMPL_AUT", required = true)
            protected String idemplaut;
            @XmlElement(name = "NUM_SEC")
            protected int numsec;
            @XmlElement(name = "COD_TX", required = true)
            protected String codtx;
            @XmlElement(name = "COD_TX_DI", required = true)
            protected String codtxdi;

            /**
             * Obtiene el valor de la propiedad idinternotermtn.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIDINTERNOTERMTN() {
                return idinternotermtn;
            }

            /**
             * Define el valor de la propiedad idinternotermtn.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIDINTERNOTERMTN(String value) {
                this.idinternotermtn = value;
            }

            /**
             * Obtiene el valor de la propiedad idemplaut.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIDEMPLAUT() {
                return idemplaut;
            }

            /**
             * Define el valor de la propiedad idemplaut.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIDEMPLAUT(String value) {
                this.idemplaut = value;
            }

            /**
             * Obtiene el valor de la propiedad numsec.
             * 
             */
            public int getNUMSEC() {
                return numsec;
            }

            /**
             * Define el valor de la propiedad numsec.
             * 
             */
            public void setNUMSEC(int value) {
                this.numsec = value;
            }

            /**
             * Obtiene el valor de la propiedad codtx.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODTX() {
                return codtx;
            }

            /**
             * Define el valor de la propiedad codtx.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODTX(String value) {
                this.codtx = value;
            }

            /**
             * Obtiene el valor de la propiedad codtxdi.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODTXDI() {
                return codtxdi;
            }

            /**
             * Define el valor de la propiedad codtxdi.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODTXDI(String value) {
                this.codtxdi = value;
            }

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
         *         &lt;element name="PE_PERS_RL_DIR_P">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="COD_NRBE_EN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000004"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="ID_INTERNO_PE">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_PERS_RL_DIR">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_DIR">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="NUM_DIR">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="FECHA_INIC_RL">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="HORA_OPRCN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="BI_BIEN_RL_DIR_P">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="COD_NRBE_EN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000004"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="ID_INTERNO_BI">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_DIR">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="NUM_DIR">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="FECHA_INIC_RL">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="HORA_OPRCN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
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
            "pepersrldirp",
            "bibienrldirp"
        })
        public static class TRDRREGLSCNSEVTY {

            @XmlElement(name = "PE_PERS_RL_DIR_P", required = true)
            protected Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.PEPERSRLDIRP pepersrldirp;
            @XmlElement(name = "BI_BIEN_RL_DIR_P", required = true)
            protected Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.BIBIENRLDIRP bibienrldirp;

            /**
             * Obtiene el valor de la propiedad pepersrldirp.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.PEPERSRLDIRP }
             *     
             */
            public Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.PEPERSRLDIRP getPEPERSRLDIRP() {
                return pepersrldirp;
            }

            /**
             * Define el valor de la propiedad pepersrldirp.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.PEPERSRLDIRP }
             *     
             */
            public void setPEPERSRLDIRP(Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.PEPERSRLDIRP value) {
                this.pepersrldirp = value;
            }

            /**
             * Obtiene el valor de la propiedad bibienrldirp.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.BIBIENRLDIRP }
             *     
             */
            public Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.BIBIENRLDIRP getBIBIENRLDIRP() {
                return bibienrldirp;
            }

            /**
             * Define el valor de la propiedad bibienrldirp.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.BIBIENRLDIRP }
             *     
             */
            public void setBIBIENRLDIRP(Ejecutar.ITRDRREGLSCNSTRNI.TRDRREGLSCNSEVTY.BIBIENRLDIRP value) {
                this.bibienrldirp = value;
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
             *         &lt;element name="COD_NRBE_EN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000004"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="ID_INTERNO_BI">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_DIR">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="NUM_DIR">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="FECHA_INIC_RL">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="HORA_OPRCN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
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
                "codnrbeen",
                "idinternobi",
                "coddir",
                "numdir",
                "fechainicrl",
                "horaoprcn"
            })
            public static class BIBIENRLDIRP {

                @XmlElement(name = "COD_NRBE_EN", required = true)
                protected String codnrbeen;
                @XmlElement(name = "ID_INTERNO_BI")
                protected int idinternobi;
                @XmlElement(name = "COD_DIR", required = true)
                protected String coddir;
                @XmlElement(name = "NUM_DIR")
                protected int numdir;
                @XmlElement(name = "FECHA_INIC_RL")
                protected int fechainicrl;
                @XmlElement(name = "HORA_OPRCN")
                protected int horaoprcn;

                /**
                 * Obtiene el valor de la propiedad codnrbeen.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODNRBEEN() {
                    return codnrbeen;
                }

                /**
                 * Define el valor de la propiedad codnrbeen.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODNRBEEN(String value) {
                    this.codnrbeen = value;
                }

                /**
                 * Obtiene el valor de la propiedad idinternobi.
                 * 
                 */
                public int getIDINTERNOBI() {
                    return idinternobi;
                }

                /**
                 * Define el valor de la propiedad idinternobi.
                 * 
                 */
                public void setIDINTERNOBI(int value) {
                    this.idinternobi = value;
                }

                /**
                 * Obtiene el valor de la propiedad coddir.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODDIR() {
                    return coddir;
                }

                /**
                 * Define el valor de la propiedad coddir.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODDIR(String value) {
                    this.coddir = value;
                }

                /**
                 * Obtiene el valor de la propiedad numdir.
                 * 
                 */
                public int getNUMDIR() {
                    return numdir;
                }

                /**
                 * Define el valor de la propiedad numdir.
                 * 
                 */
                public void setNUMDIR(int value) {
                    this.numdir = value;
                }

                /**
                 * Obtiene el valor de la propiedad fechainicrl.
                 * 
                 */
                public int getFECHAINICRL() {
                    return fechainicrl;
                }

                /**
                 * Define el valor de la propiedad fechainicrl.
                 * 
                 */
                public void setFECHAINICRL(int value) {
                    this.fechainicrl = value;
                }

                /**
                 * Obtiene el valor de la propiedad horaoprcn.
                 * 
                 */
                public int getHORAOPRCN() {
                    return horaoprcn;
                }

                /**
                 * Define el valor de la propiedad horaoprcn.
                 * 
                 */
                public void setHORAOPRCN(int value) {
                    this.horaoprcn = value;
                }

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
             *         &lt;element name="COD_NRBE_EN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000004"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="ID_INTERNO_PE">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_PERS_RL_DIR">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_DIR">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="NUM_DIR">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="FECHA_INIC_RL">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="HORA_OPRCN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
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
                "codnrbeen",
                "idinternope",
                "codpersrldir",
                "coddir",
                "numdir",
                "fechainicrl",
                "horaoprcn"
            })
            public static class PEPERSRLDIRP {

                @XmlElement(name = "COD_NRBE_EN", required = true)
                protected String codnrbeen;
                @XmlElement(name = "ID_INTERNO_PE")
                protected int idinternope;
                @XmlElement(name = "COD_PERS_RL_DIR", required = true)
                protected String codpersrldir;
                @XmlElement(name = "COD_DIR", required = true)
                protected String coddir;
                @XmlElement(name = "NUM_DIR")
                protected int numdir;
                @XmlElement(name = "FECHA_INIC_RL")
                protected int fechainicrl;
                @XmlElement(name = "HORA_OPRCN")
                protected int horaoprcn;

                /**
                 * Obtiene el valor de la propiedad codnrbeen.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODNRBEEN() {
                    return codnrbeen;
                }

                /**
                 * Define el valor de la propiedad codnrbeen.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODNRBEEN(String value) {
                    this.codnrbeen = value;
                }

                /**
                 * Obtiene el valor de la propiedad idinternope.
                 * 
                 */
                public int getIDINTERNOPE() {
                    return idinternope;
                }

                /**
                 * Define el valor de la propiedad idinternope.
                 * 
                 */
                public void setIDINTERNOPE(int value) {
                    this.idinternope = value;
                }

                /**
                 * Obtiene el valor de la propiedad codpersrldir.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODPERSRLDIR() {
                    return codpersrldir;
                }

                /**
                 * Define el valor de la propiedad codpersrldir.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODPERSRLDIR(String value) {
                    this.codpersrldir = value;
                }

                /**
                 * Obtiene el valor de la propiedad coddir.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODDIR() {
                    return coddir;
                }

                /**
                 * Define el valor de la propiedad coddir.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODDIR(String value) {
                    this.coddir = value;
                }

                /**
                 * Obtiene el valor de la propiedad numdir.
                 * 
                 */
                public int getNUMDIR() {
                    return numdir;
                }

                /**
                 * Define el valor de la propiedad numdir.
                 * 
                 */
                public void setNUMDIR(int value) {
                    this.numdir = value;
                }

                /**
                 * Obtiene el valor de la propiedad fechainicrl.
                 * 
                 */
                public int getFECHAINICRL() {
                    return fechainicrl;
                }

                /**
                 * Define el valor de la propiedad fechainicrl.
                 * 
                 */
                public void setFECHAINICRL(int value) {
                    this.fechainicrl = value;
                }

                /**
                 * Obtiene el valor de la propiedad horaoprcn.
                 * 
                 */
                public int getHORAOPRCN() {
                    return horaoprcn;
                }

                /**
                 * Define el valor de la propiedad horaoprcn.
                 * 
                 */
                public void setHORAOPRCN(int value) {
                    this.horaoprcn = value;
                }

            }

        }

    }

}
