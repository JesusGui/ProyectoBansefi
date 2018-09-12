
package mx.babel.bansefi.banksystem.base.webservices.validaidinterna;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResponseBansefi complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResponseBansefi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OTR_CONS_MINIMA_PERSONA_T">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RTRN_CD">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                         &lt;maxInclusive value="9999"/>
 *                         &lt;minInclusive value="0"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="TR_CONS_MINIMA_PERSONA_EV">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ID_INTERNO_PE">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="99999999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_ID_EXT_PERS">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000002"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="ID_EXT_PE">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000014"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NOMB_IN_APE_1_IN">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000030"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NOMB_IN_APE_2_IN">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000030"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NOMB_IN_NOMB_PILA">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000030"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_CNO_INDV">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000006"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="DENOM_LEGAL_OR">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000090"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_ESTRCT_LGL_ORG">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000003"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_CNAE_PERS">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000007"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NOMB_LOCALIDAD_AG">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000045"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NOMB_PROVINCIA_AG">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000035"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NOMB_PAIS_AG">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000035"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_OFCNA_ALTA">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000004"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_POSTAL_AG">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000005"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="DOMIC_50">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000050"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="SEXO_IN">
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
 *                             &lt;element name="STD_TLFNOS_CONS_MINIMA_V" maxOccurs="3">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="VALOR_ELCTR_DR_LEN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="VALOR_ELCTR_DR">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000020"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="PREF_TLFNO_AG">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000008"/>
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
 *                   &lt;element name="STD_TRN_MSJ_PARM_V" maxOccurs="5">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="TEXT_CODE">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="9999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="TEXT_ARG1">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000018"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="STD_TRN_O_PARM_V">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="FECHA_OPRCN">
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
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseBansefi", propOrder = {
    "otrconsminimapersonat"
})
public class ResponseBansefi {

    @XmlElement(name = "OTR_CONS_MINIMA_PERSONA_T", required = true)
    protected ResponseBansefi.OTRCONSMINIMAPERSONAT otrconsminimapersonat;

    /**
     * Obtiene el valor de la propiedad otrconsminimapersonat.
     * 
     * @return
     *     possible object is
     *     {@link ResponseBansefi.OTRCONSMINIMAPERSONAT }
     *     
     */
    public ResponseBansefi.OTRCONSMINIMAPERSONAT getOTRCONSMINIMAPERSONAT() {
        return otrconsminimapersonat;
    }

    /**
     * Define el valor de la propiedad otrconsminimapersonat.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseBansefi.OTRCONSMINIMAPERSONAT }
     *     
     */
    public void setOTRCONSMINIMAPERSONAT(ResponseBansefi.OTRCONSMINIMAPERSONAT value) {
        this.otrconsminimapersonat = value;
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
     *         &lt;element name="RTRN_CD">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *               &lt;maxInclusive value="9999"/>
     *               &lt;minInclusive value="0"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="TR_CONS_MINIMA_PERSONA_EV">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ID_INTERNO_PE">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="99999999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_ID_EXT_PERS">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000002"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="ID_EXT_PE">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000014"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NOMB_IN_APE_1_IN">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000030"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NOMB_IN_APE_2_IN">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000030"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NOMB_IN_NOMB_PILA">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000030"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_CNO_INDV">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000006"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="DENOM_LEGAL_OR">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000090"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_ESTRCT_LGL_ORG">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000003"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_CNAE_PERS">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000007"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NOMB_LOCALIDAD_AG">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000045"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NOMB_PROVINCIA_AG">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000035"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NOMB_PAIS_AG">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000035"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_OFCNA_ALTA">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000004"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_POSTAL_AG">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000005"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="DOMIC_50">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000050"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="SEXO_IN">
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
     *                   &lt;element name="STD_TLFNOS_CONS_MINIMA_V" maxOccurs="3">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="VALOR_ELCTR_DR_LEN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="VALOR_ELCTR_DR">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000020"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="PREF_TLFNO_AG">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000008"/>
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
     *         &lt;element name="STD_TRN_MSJ_PARM_V" maxOccurs="5">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="TEXT_CODE">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="9999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="TEXT_ARG1">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000018"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="STD_TRN_O_PARM_V">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="FECHA_OPRCN">
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
        "rtrncd",
        "trconsminimapersonaev",
        "stdtrnmsjparmv",
        "stdtrnoparmv"
    })
    public static class OTRCONSMINIMAPERSONAT {

        @XmlElement(name = "RTRN_CD")
        protected int rtrncd;
        @XmlElement(name = "TR_CONS_MINIMA_PERSONA_EV", required = true)
        protected ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV trconsminimapersonaev;
        @XmlElement(name = "STD_TRN_MSJ_PARM_V", required = true)
        protected List<ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNMSJPARMV> stdtrnmsjparmv;
        @XmlElement(name = "STD_TRN_O_PARM_V", required = true)
        protected ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNOPARMV stdtrnoparmv;

        /**
         * Obtiene el valor de la propiedad rtrncd.
         * 
         */
        public int getRTRNCD() {
            return rtrncd;
        }

        /**
         * Define el valor de la propiedad rtrncd.
         * 
         */
        public void setRTRNCD(int value) {
            this.rtrncd = value;
        }

        /**
         * Obtiene el valor de la propiedad trconsminimapersonaev.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV }
         *     
         */
        public ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV getTRCONSMINIMAPERSONAEV() {
            return trconsminimapersonaev;
        }

        /**
         * Define el valor de la propiedad trconsminimapersonaev.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV }
         *     
         */
        public void setTRCONSMINIMAPERSONAEV(ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV value) {
            this.trconsminimapersonaev = value;
        }

        /**
         * Gets the value of the stdtrnmsjparmv property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the stdtrnmsjparmv property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSTDTRNMSJPARMV().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNMSJPARMV }
         * 
         * 
         */
        public List<ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNMSJPARMV> getSTDTRNMSJPARMV() {
            if (stdtrnmsjparmv == null) {
                stdtrnmsjparmv = new ArrayList<ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNMSJPARMV>();
            }
            return this.stdtrnmsjparmv;
        }

        /**
         * Obtiene el valor de la propiedad stdtrnoparmv.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNOPARMV }
         *     
         */
        public ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNOPARMV getSTDTRNOPARMV() {
            return stdtrnoparmv;
        }

        /**
         * Define el valor de la propiedad stdtrnoparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNOPARMV }
         *     
         */
        public void setSTDTRNOPARMV(ResponseBansefi.OTRCONSMINIMAPERSONAT.STDTRNOPARMV value) {
            this.stdtrnoparmv = value;
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
         *         &lt;element name="TEXT_CODE">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="9999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="TEXT_ARG1">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000018"/>
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
            "textcode",
            "textarg1"
        })
        public static class STDTRNMSJPARMV {

            @XmlElement(name = "TEXT_CODE")
            protected int textcode;
            @XmlElement(name = "TEXT_ARG1", required = true)
            protected String textarg1;

            /**
             * Obtiene el valor de la propiedad textcode.
             * 
             */
            public int getTEXTCODE() {
                return textcode;
            }

            /**
             * Define el valor de la propiedad textcode.
             * 
             */
            public void setTEXTCODE(int value) {
                this.textcode = value;
            }

            /**
             * Obtiene el valor de la propiedad textarg1.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTEXTARG1() {
                return textarg1;
            }

            /**
             * Define el valor de la propiedad textarg1.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTEXTARG1(String value) {
                this.textarg1 = value;
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
         *         &lt;element name="FECHA_OPRCN">
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
            "fechaoprcn",
            "horaoprcn"
        })
        public static class STDTRNOPARMV {

            @XmlElement(name = "FECHA_OPRCN")
            protected int fechaoprcn;
            @XmlElement(name = "HORA_OPRCN")
            protected int horaoprcn;

            /**
             * Obtiene el valor de la propiedad fechaoprcn.
             * 
             */
            public int getFECHAOPRCN() {
                return fechaoprcn;
            }

            /**
             * Define el valor de la propiedad fechaoprcn.
             * 
             */
            public void setFECHAOPRCN(int value) {
                this.fechaoprcn = value;
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
         *         &lt;element name="ID_INTERNO_PE">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="99999999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_ID_EXT_PERS">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000002"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="ID_EXT_PE">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000014"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NOMB_IN_APE_1_IN">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000030"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NOMB_IN_APE_2_IN">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000030"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NOMB_IN_NOMB_PILA">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000030"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_CNO_INDV">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000006"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="DENOM_LEGAL_OR">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000090"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_ESTRCT_LGL_ORG">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000003"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_CNAE_PERS">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000007"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NOMB_LOCALIDAD_AG">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000045"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NOMB_PROVINCIA_AG">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000035"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NOMB_PAIS_AG">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000035"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_OFCNA_ALTA">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000004"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_POSTAL_AG">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000005"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="DOMIC_50">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000050"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="SEXO_IN">
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
         *         &lt;element name="STD_TLFNOS_CONS_MINIMA_V" maxOccurs="3">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="VALOR_ELCTR_DR_LEN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="VALOR_ELCTR_DR">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000020"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="PREF_TLFNO_AG">
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
            "idinternope",
            "codidextpers",
            "idextpe",
            "nombinape1IN",
            "nombinape2IN",
            "nombinnombpila",
            "codcnoindv",
            "denomlegalor",
            "codestrctlglorg",
            "codcnaepers",
            "nomblocalidadag",
            "nombprovinciaag",
            "nombpaisag",
            "codofcnaalta",
            "codpostalag",
            "domic50",
            "sexoin",
            "fecnctoconstpe",
            "stdtlfnosconsminimav"
        })
        public static class TRCONSMINIMAPERSONAEV {

            @XmlElement(name = "ID_INTERNO_PE")
            protected int idinternope;
            @XmlElement(name = "COD_ID_EXT_PERS", required = true)
            protected String codidextpers;
            @XmlElement(name = "ID_EXT_PE", required = true)
            protected String idextpe;
            @XmlElement(name = "NOMB_IN_APE_1_IN", required = true)
            protected String nombinape1IN;
            @XmlElement(name = "NOMB_IN_APE_2_IN", required = true)
            protected String nombinape2IN;
            @XmlElement(name = "NOMB_IN_NOMB_PILA", required = true)
            protected String nombinnombpila;
            @XmlElement(name = "COD_CNO_INDV", required = true)
            protected String codcnoindv;
            @XmlElement(name = "DENOM_LEGAL_OR", required = true)
            protected String denomlegalor;
            @XmlElement(name = "COD_ESTRCT_LGL_ORG", required = true)
            protected String codestrctlglorg;
            @XmlElement(name = "COD_CNAE_PERS", required = true)
            protected String codcnaepers;
            @XmlElement(name = "NOMB_LOCALIDAD_AG", required = true)
            protected String nomblocalidadag;
            @XmlElement(name = "NOMB_PROVINCIA_AG", required = true)
            protected String nombprovinciaag;
            @XmlElement(name = "NOMB_PAIS_AG", required = true)
            protected String nombpaisag;
            @XmlElement(name = "COD_OFCNA_ALTA", required = true)
            protected String codofcnaalta;
            @XmlElement(name = "COD_POSTAL_AG", required = true)
            protected String codpostalag;
            @XmlElement(name = "DOMIC_50", required = true)
            protected String domic50;
            @XmlElement(name = "SEXO_IN", required = true)
            protected String sexoin;
            @XmlElement(name = "FEC_NCTO_CONST_PE")
            protected int fecnctoconstpe;
            @XmlElement(name = "STD_TLFNOS_CONS_MINIMA_V", required = true)
            protected List<ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.STDTLFNOSCONSMINIMAV> stdtlfnosconsminimav;

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
             * Obtiene el valor de la propiedad codidextpers.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODIDEXTPERS() {
                return codidextpers;
            }

            /**
             * Define el valor de la propiedad codidextpers.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODIDEXTPERS(String value) {
                this.codidextpers = value;
            }

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
             * Obtiene el valor de la propiedad nombinape1IN.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNOMBINAPE1IN() {
                return nombinape1IN;
            }

            /**
             * Define el valor de la propiedad nombinape1IN.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNOMBINAPE1IN(String value) {
                this.nombinape1IN = value;
            }

            /**
             * Obtiene el valor de la propiedad nombinape2IN.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNOMBINAPE2IN() {
                return nombinape2IN;
            }

            /**
             * Define el valor de la propiedad nombinape2IN.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNOMBINAPE2IN(String value) {
                this.nombinape2IN = value;
            }

            /**
             * Obtiene el valor de la propiedad nombinnombpila.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNOMBINNOMBPILA() {
                return nombinnombpila;
            }

            /**
             * Define el valor de la propiedad nombinnombpila.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNOMBINNOMBPILA(String value) {
                this.nombinnombpila = value;
            }

            /**
             * Obtiene el valor de la propiedad codcnoindv.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODCNOINDV() {
                return codcnoindv;
            }

            /**
             * Define el valor de la propiedad codcnoindv.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODCNOINDV(String value) {
                this.codcnoindv = value;
            }

            /**
             * Obtiene el valor de la propiedad denomlegalor.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDENOMLEGALOR() {
                return denomlegalor;
            }

            /**
             * Define el valor de la propiedad denomlegalor.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDENOMLEGALOR(String value) {
                this.denomlegalor = value;
            }

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
             * Obtiene el valor de la propiedad codcnaepers.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODCNAEPERS() {
                return codcnaepers;
            }

            /**
             * Define el valor de la propiedad codcnaepers.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODCNAEPERS(String value) {
                this.codcnaepers = value;
            }

            /**
             * Obtiene el valor de la propiedad nomblocalidadag.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNOMBLOCALIDADAG() {
                return nomblocalidadag;
            }

            /**
             * Define el valor de la propiedad nomblocalidadag.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNOMBLOCALIDADAG(String value) {
                this.nomblocalidadag = value;
            }

            /**
             * Obtiene el valor de la propiedad nombprovinciaag.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNOMBPROVINCIAAG() {
                return nombprovinciaag;
            }

            /**
             * Define el valor de la propiedad nombprovinciaag.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNOMBPROVINCIAAG(String value) {
                this.nombprovinciaag = value;
            }

            /**
             * Obtiene el valor de la propiedad nombpaisag.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNOMBPAISAG() {
                return nombpaisag;
            }

            /**
             * Define el valor de la propiedad nombpaisag.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNOMBPAISAG(String value) {
                this.nombpaisag = value;
            }

            /**
             * Obtiene el valor de la propiedad codofcnaalta.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODOFCNAALTA() {
                return codofcnaalta;
            }

            /**
             * Define el valor de la propiedad codofcnaalta.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODOFCNAALTA(String value) {
                this.codofcnaalta = value;
            }

            /**
             * Obtiene el valor de la propiedad codpostalag.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODPOSTALAG() {
                return codpostalag;
            }

            /**
             * Define el valor de la propiedad codpostalag.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODPOSTALAG(String value) {
                this.codpostalag = value;
            }

            /**
             * Obtiene el valor de la propiedad domic50.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDOMIC50() {
                return domic50;
            }

            /**
             * Define el valor de la propiedad domic50.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDOMIC50(String value) {
                this.domic50 = value;
            }

            /**
             * Obtiene el valor de la propiedad sexoin.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSEXOIN() {
                return sexoin;
            }

            /**
             * Define el valor de la propiedad sexoin.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSEXOIN(String value) {
                this.sexoin = value;
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

            /**
             * Gets the value of the stdtlfnosconsminimav property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the stdtlfnosconsminimav property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getSTDTLFNOSCONSMINIMAV().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.STDTLFNOSCONSMINIMAV }
             * 
             * 
             */
            public List<ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.STDTLFNOSCONSMINIMAV> getSTDTLFNOSCONSMINIMAV() {
                if (stdtlfnosconsminimav == null) {
                    stdtlfnosconsminimav = new ArrayList<ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV.STDTLFNOSCONSMINIMAV>();
                }
                return this.stdtlfnosconsminimav;
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
             *         &lt;element name="VALOR_ELCTR_DR_LEN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="VALOR_ELCTR_DR">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000020"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="PREF_TLFNO_AG">
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
                "valorelctrdrlen",
                "valorelctrdr",
                "preftlfnoag"
            })
            public static class STDTLFNOSCONSMINIMAV {

                @XmlElement(name = "VALOR_ELCTR_DR_LEN")
                protected int valorelctrdrlen;
                @XmlElement(name = "VALOR_ELCTR_DR", required = true)
                protected String valorelctrdr;
                @XmlElement(name = "PREF_TLFNO_AG", required = true)
                protected String preftlfnoag;

                /**
                 * Obtiene el valor de la propiedad valorelctrdrlen.
                 * 
                 */
                public int getVALORELCTRDRLEN() {
                    return valorelctrdrlen;
                }

                /**
                 * Define el valor de la propiedad valorelctrdrlen.
                 * 
                 */
                public void setVALORELCTRDRLEN(int value) {
                    this.valorelctrdrlen = value;
                }

                /**
                 * Obtiene el valor de la propiedad valorelctrdr.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getVALORELCTRDR() {
                    return valorelctrdr;
                }

                /**
                 * Define el valor de la propiedad valorelctrdr.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setVALORELCTRDR(String value) {
                    this.valorelctrdr = value;
                }

                /**
                 * Obtiene el valor de la propiedad preftlfnoag.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPREFTLFNOAG() {
                    return preftlfnoag;
                }

                /**
                 * Define el valor de la propiedad preftlfnoag.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPREFTLFNOAG(String value) {
                    this.preftlfnoag = value;
                }

            }

        }

    }

}
