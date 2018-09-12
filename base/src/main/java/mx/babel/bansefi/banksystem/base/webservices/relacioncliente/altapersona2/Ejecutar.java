
package mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona2;

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
 *         &lt;element name="ITR_PE_ALTA_RL_2_PE_DS_TR">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TRPEALTARL2PEDSEVTY">
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
 *                             &lt;element name="ID_INTERNO_PE_1">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="99999999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="STD_COD_PE_1_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="COD_PE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="ID_INTERNO_PE_2">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="99999999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="STD_COD_PE_2">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="STD_CHAR_01">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="COD_PERS_RL_PERS">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000003"/>
 *                                   &lt;whiteSpace value="preserve"/>
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
 *                             &lt;element name="FECHA_INIC_VLDZ">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="99999999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="FECHA_FIN_VLDZ">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="99999999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="TXT_PE_RL_PE">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000060"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="INDCODPERLPECONTRARIOV">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="STD_CHAR_01">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="DESCR_PERS_RL_PERS">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000025"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="PE_RL_CPDAD_LGL_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="COD_ESTRCT_LGL_ORG">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000003"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_CPCDAD_LGL_IN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="FEC_NCTO_CONST_PE">
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
 *                             &lt;element name="PE_RL_2_PE_VAL_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="PE_RL_PE_1_DS">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="ID_EXT_PE">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000014"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="NOMB_50">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000050"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_PE">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="PE_RL_PE_2_DS">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="ID_EXT_PE">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000014"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="NOMB_50">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000050"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_PE">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
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
    "itrpealtarl2PEDSTR"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USERHEADER", required = true)
    protected String userheader;
    @XmlElement(name = "PASSHEADER", required = true)
    protected String passheader;
    @XmlElement(name = "IPHEADER", required = true)
    protected String ipheader;
    @XmlElement(name = "ITR_PE_ALTA_RL_2_PE_DS_TR", required = true)
    protected Ejecutar.ITRPEALTARL2PEDSTR itrpealtarl2PEDSTR;

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
     * Obtiene el valor de la propiedad itrpealtarl2PEDSTR.
     * 
     * @return
     *     possible object is
     *     {@link Ejecutar.ITRPEALTARL2PEDSTR }
     *     
     */
    public Ejecutar.ITRPEALTARL2PEDSTR getITRPEALTARL2PEDSTR() {
        return itrpealtarl2PEDSTR;
    }

    /**
     * Define el valor de la propiedad itrpealtarl2PEDSTR.
     * 
     * @param value
     *     allowed object is
     *     {@link Ejecutar.ITRPEALTARL2PEDSTR }
     *     
     */
    public void setITRPEALTARL2PEDSTR(Ejecutar.ITRPEALTARL2PEDSTR value) {
        this.itrpealtarl2PEDSTR = value;
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
     *         &lt;element name="TRPEALTARL2PEDSEVTY">
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
     *                   &lt;element name="ID_INTERNO_PE_1">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="99999999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="STD_COD_PE_1_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="COD_PE">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="ID_INTERNO_PE_2">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="99999999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="STD_COD_PE_2">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="STD_CHAR_01">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="COD_PERS_RL_PERS">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000003"/>
     *                         &lt;whiteSpace value="preserve"/>
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
     *                   &lt;element name="FECHA_INIC_VLDZ">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="99999999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="FECHA_FIN_VLDZ">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="99999999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="TXT_PE_RL_PE">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000060"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="INDCODPERLPECONTRARIOV">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="STD_CHAR_01">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="DESCR_PERS_RL_PERS">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000025"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="PE_RL_CPDAD_LGL_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="COD_ESTRCT_LGL_ORG">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000003"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_CPCDAD_LGL_IN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="FEC_NCTO_CONST_PE">
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
     *                   &lt;element name="PE_RL_2_PE_VAL_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="PE_RL_PE_1_DS">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="ID_EXT_PE">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000014"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="NOMB_50">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000050"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_PE">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="PE_RL_PE_2_DS">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="ID_EXT_PE">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000014"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="NOMB_50">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000050"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_PE">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
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
        "trpealtarl2PEDSEVTY",
        "stdtrniparmv"
    })
    public static class ITRPEALTARL2PEDSTR {

        @XmlElement(name = "TRPEALTARL2PEDSEVTY", required = true)
        protected Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY trpealtarl2PEDSEVTY;
        @XmlElement(name = "STD_TRN_I_PARM_V", required = true)
        protected Ejecutar.ITRPEALTARL2PEDSTR.STDTRNIPARMV stdtrniparmv;

        /**
         * Obtiene el valor de la propiedad trpealtarl2PEDSEVTY.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY }
         *     
         */
        public Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY getTRPEALTARL2PEDSEVTY() {
            return trpealtarl2PEDSEVTY;
        }

        /**
         * Define el valor de la propiedad trpealtarl2PEDSEVTY.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY }
         *     
         */
        public void setTRPEALTARL2PEDSEVTY(Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY value) {
            this.trpealtarl2PEDSEVTY = value;
        }

        /**
         * Obtiene el valor de la propiedad stdtrniparmv.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.ITRPEALTARL2PEDSTR.STDTRNIPARMV }
         *     
         */
        public Ejecutar.ITRPEALTARL2PEDSTR.STDTRNIPARMV getSTDTRNIPARMV() {
            return stdtrniparmv;
        }

        /**
         * Define el valor de la propiedad stdtrniparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.ITRPEALTARL2PEDSTR.STDTRNIPARMV }
         *     
         */
        public void setSTDTRNIPARMV(Ejecutar.ITRPEALTARL2PEDSTR.STDTRNIPARMV value) {
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
         *         &lt;element name="COD_NRBE_EN">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000004"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="ID_INTERNO_PE_1">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="99999999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="STD_COD_PE_1_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="COD_PE">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="ID_INTERNO_PE_2">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="99999999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="STD_COD_PE_2">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="STD_CHAR_01">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="COD_PERS_RL_PERS">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000003"/>
         *               &lt;whiteSpace value="preserve"/>
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
         *         &lt;element name="FECHA_INIC_VLDZ">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="99999999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="FECHA_FIN_VLDZ">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="99999999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="TXT_PE_RL_PE">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000060"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="INDCODPERLPECONTRARIOV">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="STD_CHAR_01">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="DESCR_PERS_RL_PERS">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000025"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="PE_RL_CPDAD_LGL_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="COD_ESTRCT_LGL_ORG">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000003"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_CPCDAD_LGL_IN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="FEC_NCTO_CONST_PE">
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
         *         &lt;element name="PE_RL_2_PE_VAL_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="PE_RL_PE_1_DS">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="ID_EXT_PE">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000014"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="NOMB_50">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000050"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_PE">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="PE_RL_PE_2_DS">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="ID_EXT_PE">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000014"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="NOMB_50">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000050"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_PE">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
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
            "codnrbeen",
            "idinternope1",
            "stdcodpe1V",
            "idinternope2",
            "stdcodpe2",
            "codpersrlpers",
            "fechainicrl",
            "fechainicvldz",
            "fechafinvldz",
            "txtperlpe",
            "indcodperlpecontrariov",
            "perlcpdadlglv",
            "perl2PEVALV"
        })
        public static class TRPEALTARL2PEDSEVTY {

            @XmlElement(name = "COD_NRBE_EN", required = true)
            protected String codnrbeen;
            @XmlElement(name = "ID_INTERNO_PE_1")
            protected int idinternope1;
            @XmlElement(name = "STD_COD_PE_1_V", required = true)
            protected Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE1V stdcodpe1V;
            @XmlElement(name = "ID_INTERNO_PE_2")
            protected int idinternope2;
            @XmlElement(name = "STD_COD_PE_2", required = true)
            protected Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE2 stdcodpe2;
            @XmlElement(name = "COD_PERS_RL_PERS", required = true)
            protected String codpersrlpers;
            @XmlElement(name = "FECHA_INIC_RL")
            protected int fechainicrl;
            @XmlElement(name = "FECHA_INIC_VLDZ")
            protected int fechainicvldz;
            @XmlElement(name = "FECHA_FIN_VLDZ")
            protected int fechafinvldz;
            @XmlElement(name = "TXT_PE_RL_PE", required = true)
            protected String txtperlpe;
            @XmlElement(name = "INDCODPERLPECONTRARIOV", required = true)
            protected Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.INDCODPERLPECONTRARIOV indcodperlpecontrariov;
            @XmlElement(name = "PE_RL_CPDAD_LGL_V", required = true)
            protected Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERLCPDADLGLV perlcpdadlglv;
            @XmlElement(name = "PE_RL_2_PE_VAL_V", required = true)
            protected Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV perl2PEVALV;

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
             * Obtiene el valor de la propiedad idinternope1.
             * 
             */
            public int getIDINTERNOPE1() {
                return idinternope1;
            }

            /**
             * Define el valor de la propiedad idinternope1.
             * 
             */
            public void setIDINTERNOPE1(int value) {
                this.idinternope1 = value;
            }

            /**
             * Obtiene el valor de la propiedad stdcodpe1V.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE1V }
             *     
             */
            public Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE1V getSTDCODPE1V() {
                return stdcodpe1V;
            }

            /**
             * Define el valor de la propiedad stdcodpe1V.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE1V }
             *     
             */
            public void setSTDCODPE1V(Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE1V value) {
                this.stdcodpe1V = value;
            }

            /**
             * Obtiene el valor de la propiedad idinternope2.
             * 
             */
            public int getIDINTERNOPE2() {
                return idinternope2;
            }

            /**
             * Define el valor de la propiedad idinternope2.
             * 
             */
            public void setIDINTERNOPE2(int value) {
                this.idinternope2 = value;
            }

            /**
             * Obtiene el valor de la propiedad stdcodpe2.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE2 }
             *     
             */
            public Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE2 getSTDCODPE2() {
                return stdcodpe2;
            }

            /**
             * Define el valor de la propiedad stdcodpe2.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE2 }
             *     
             */
            public void setSTDCODPE2(Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE2 value) {
                this.stdcodpe2 = value;
            }

            /**
             * Obtiene el valor de la propiedad codpersrlpers.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODPERSRLPERS() {
                return codpersrlpers;
            }

            /**
             * Define el valor de la propiedad codpersrlpers.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODPERSRLPERS(String value) {
                this.codpersrlpers = value;
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
             * Obtiene el valor de la propiedad fechainicvldz.
             * 
             */
            public int getFECHAINICVLDZ() {
                return fechainicvldz;
            }

            /**
             * Define el valor de la propiedad fechainicvldz.
             * 
             */
            public void setFECHAINICVLDZ(int value) {
                this.fechainicvldz = value;
            }

            /**
             * Obtiene el valor de la propiedad fechafinvldz.
             * 
             */
            public int getFECHAFINVLDZ() {
                return fechafinvldz;
            }

            /**
             * Define el valor de la propiedad fechafinvldz.
             * 
             */
            public void setFECHAFINVLDZ(int value) {
                this.fechafinvldz = value;
            }

            /**
             * Obtiene el valor de la propiedad txtperlpe.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTXTPERLPE() {
                return txtperlpe;
            }

            /**
             * Define el valor de la propiedad txtperlpe.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTXTPERLPE(String value) {
                this.txtperlpe = value;
            }

            /**
             * Obtiene el valor de la propiedad indcodperlpecontrariov.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.INDCODPERLPECONTRARIOV }
             *     
             */
            public Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.INDCODPERLPECONTRARIOV getINDCODPERLPECONTRARIOV() {
                return indcodperlpecontrariov;
            }

            /**
             * Define el valor de la propiedad indcodperlpecontrariov.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.INDCODPERLPECONTRARIOV }
             *     
             */
            public void setINDCODPERLPECONTRARIOV(Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.INDCODPERLPECONTRARIOV value) {
                this.indcodperlpecontrariov = value;
            }

            /**
             * Obtiene el valor de la propiedad perlcpdadlglv.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERLCPDADLGLV }
             *     
             */
            public Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERLCPDADLGLV getPERLCPDADLGLV() {
                return perlcpdadlglv;
            }

            /**
             * Define el valor de la propiedad perlcpdadlglv.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERLCPDADLGLV }
             *     
             */
            public void setPERLCPDADLGLV(Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERLCPDADLGLV value) {
                this.perlcpdadlglv = value;
            }

            /**
             * Obtiene el valor de la propiedad perl2PEVALV.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV }
             *     
             */
            public Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV getPERL2PEVALV() {
                return perl2PEVALV;
            }

            /**
             * Define el valor de la propiedad perl2PEVALV.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV }
             *     
             */
            public void setPERL2PEVALV(Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV value) {
                this.perl2PEVALV = value;
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
             *         &lt;element name="STD_CHAR_01">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="DESCR_PERS_RL_PERS">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000025"/>
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
                "stdchar01",
                "descrpersrlpers"
            })
            public static class INDCODPERLPECONTRARIOV {

                @XmlElement(name = "STD_CHAR_01", required = true)
                protected String stdchar01;
                @XmlElement(name = "DESCR_PERS_RL_PERS", required = true)
                protected String descrpersrlpers;

                /**
                 * Obtiene el valor de la propiedad stdchar01.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSTDCHAR01() {
                    return stdchar01;
                }

                /**
                 * Define el valor de la propiedad stdchar01.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSTDCHAR01(String value) {
                    this.stdchar01 = value;
                }

                /**
                 * Obtiene el valor de la propiedad descrpersrlpers.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDESCRPERSRLPERS() {
                    return descrpersrlpers;
                }

                /**
                 * Define el valor de la propiedad descrpersrlpers.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDESCRPERSRLPERS(String value) {
                    this.descrpersrlpers = value;
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
             *         &lt;element name="PE_RL_PE_1_DS">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="ID_EXT_PE">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000014"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="NOMB_50">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000050"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_PE">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="PE_RL_PE_2_DS">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="ID_EXT_PE">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000014"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="NOMB_50">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000050"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_PE">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
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
                "perlpe1DS",
                "perlpe2DS"
            })
            public static class PERL2PEVALV {

                @XmlElement(name = "PE_RL_PE_1_DS", required = true)
                protected Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE1DS perlpe1DS;
                @XmlElement(name = "PE_RL_PE_2_DS", required = true)
                protected Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE2DS perlpe2DS;

                /**
                 * Obtiene el valor de la propiedad perlpe1DS.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE1DS }
                 *     
                 */
                public Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE1DS getPERLPE1DS() {
                    return perlpe1DS;
                }

                /**
                 * Define el valor de la propiedad perlpe1DS.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE1DS }
                 *     
                 */
                public void setPERLPE1DS(Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE1DS value) {
                    this.perlpe1DS = value;
                }

                /**
                 * Obtiene el valor de la propiedad perlpe2DS.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE2DS }
                 *     
                 */
                public Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE2DS getPERLPE2DS() {
                    return perlpe2DS;
                }

                /**
                 * Define el valor de la propiedad perlpe2DS.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE2DS }
                 *     
                 */
                public void setPERLPE2DS(Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE2DS value) {
                    this.perlpe2DS = value;
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
                 *         &lt;element name="ID_EXT_PE">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000014"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="NOMB_50">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000050"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_PE">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
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
                    "idextpe",
                    "nomb50",
                    "codpe"
                })
                public static class PERLPE1DS {

                    @XmlElement(name = "ID_EXT_PE", required = true)
                    protected String idextpe;
                    @XmlElement(name = "NOMB_50", required = true)
                    protected String nomb50;
                    @XmlElement(name = "COD_PE", required = true)
                    protected String codpe;

                    /**
                     * Obtiene el valor de la propiedad idextpe.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIDEXTPE() {
                        return idextpe;
                    }

                    /**
                     * Define el valor de la propiedad idextpe.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIDEXTPE(String value) {
                        this.idextpe = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad nomb50.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getNOMB50() {
                        return nomb50;
                    }

                    /**
                     * Define el valor de la propiedad nomb50.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setNOMB50(String value) {
                        this.nomb50 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codpe.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCODPE() {
                        return codpe;
                    }

                    /**
                     * Define el valor de la propiedad codpe.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCODPE(String value) {
                        this.codpe = value;
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
                 *         &lt;element name="ID_EXT_PE">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000014"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="NOMB_50">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000050"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_PE">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
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
                    "idextpe",
                    "nomb50",
                    "codpe"
                })
                public static class PERLPE2DS {

                    @XmlElement(name = "ID_EXT_PE", required = true)
                    protected String idextpe;
                    @XmlElement(name = "NOMB_50", required = true)
                    protected String nomb50;
                    @XmlElement(name = "COD_PE", required = true)
                    protected String codpe;

                    /**
                     * Obtiene el valor de la propiedad idextpe.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIDEXTPE() {
                        return idextpe;
                    }

                    /**
                     * Define el valor de la propiedad idextpe.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIDEXTPE(String value) {
                        this.idextpe = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad nomb50.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getNOMB50() {
                        return nomb50;
                    }

                    /**
                     * Define el valor de la propiedad nomb50.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setNOMB50(String value) {
                        this.nomb50 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codpe.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCODPE() {
                        return codpe;
                    }

                    /**
                     * Define el valor de la propiedad codpe.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCODPE(String value) {
                        this.codpe = value;
                    }

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
             *         &lt;element name="COD_ESTRCT_LGL_ORG">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000003"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_CPCDAD_LGL_IN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="FEC_NCTO_CONST_PE">
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
                "codestrctlglorg",
                "codcpcdadlglin",
                "fecnctoconstpe"
            })
            public static class PERLCPDADLGLV {

                @XmlElement(name = "COD_ESTRCT_LGL_ORG", required = true)
                protected String codestrctlglorg;
                @XmlElement(name = "COD_CPCDAD_LGL_IN", required = true)
                protected String codcpcdadlglin;
                @XmlElement(name = "FEC_NCTO_CONST_PE")
                protected int fecnctoconstpe;

                /**
                 * Obtiene el valor de la propiedad codestrctlglorg.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODESTRCTLGLORG() {
                    return codestrctlglorg;
                }

                /**
                 * Define el valor de la propiedad codestrctlglorg.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODESTRCTLGLORG(String value) {
                    this.codestrctlglorg = value;
                }

                /**
                 * Obtiene el valor de la propiedad codcpcdadlglin.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODCPCDADLGLIN() {
                    return codcpcdadlglin;
                }

                /**
                 * Define el valor de la propiedad codcpcdadlglin.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODCPCDADLGLIN(String value) {
                    this.codcpcdadlglin = value;
                }

                /**
                 * Obtiene el valor de la propiedad fecnctoconstpe.
                 * 
                 */
                public int getFECNCTOCONSTPE() {
                    return fecnctoconstpe;
                }

                /**
                 * Define el valor de la propiedad fecnctoconstpe.
                 * 
                 */
                public void setFECNCTOCONSTPE(int value) {
                    this.fecnctoconstpe = value;
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
             *         &lt;element name="COD_PE">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
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
                "codpe"
            })
            public static class STDCODPE1V {

                @XmlElement(name = "COD_PE", required = true)
                protected String codpe;

                /**
                 * Obtiene el valor de la propiedad codpe.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODPE() {
                    return codpe;
                }

                /**
                 * Define el valor de la propiedad codpe.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODPE(String value) {
                    this.codpe = value;
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
             *         &lt;element name="STD_CHAR_01">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
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
                "stdchar01"
            })
            public static class STDCODPE2 {

                @XmlElement(name = "STD_CHAR_01", required = true)
                protected String stdchar01;

                /**
                 * Obtiene el valor de la propiedad stdchar01.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSTDCHAR01() {
                    return stdchar01;
                }

                /**
                 * Define el valor de la propiedad stdchar01.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSTDCHAR01(String value) {
                    this.stdchar01 = value;
                }

            }

        }

    }

}
