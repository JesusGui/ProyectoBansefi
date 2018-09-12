
package mx.babel.bansefi.banksystem.base.webservices.consultadetalleatribuciones;

import java.math.BigDecimal;
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
 *         &lt;element name="OTR_EP_CON_ATRIB_EMPL_1_T">
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
 *                   &lt;element name="TR_EP_CON_ATRIB_EMPL_1_EV">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="EP_ATRIBUCIONES_E">
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
 *                                       &lt;element name="ID_INTERNO_EMPL_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000008"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="FECHA_INIC_TEMP_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="FECHA_FIN_TEMP_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="PCT_PSV_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                             &lt;totalDigits value="0000000005"/>
 *                                             &lt;fractionDigits value="03"/>
 *                                             &lt;maxInclusive value="99.999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="PCT_ACTIVO_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                             &lt;totalDigits value="0000000005"/>
 *                                             &lt;fractionDigits value="03"/>
 *                                             &lt;maxInclusive value="99.999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="PCT_EXTRANJERO_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                             &lt;totalDigits value="0000000005"/>
 *                                             &lt;fractionDigits value="03"/>
 *                                             &lt;maxInclusive value="99.999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="PCT_INTERMED_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                             &lt;totalDigits value="0000000005"/>
 *                                             &lt;fractionDigits value="03"/>
 *                                             &lt;maxInclusive value="99.999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="PCT_SERVICIOS_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                             &lt;totalDigits value="0000000005"/>
 *                                             &lt;fractionDigits value="03"/>
 *                                             &lt;maxInclusive value="99.999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="PCT_RSGO_PD_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                             &lt;totalDigits value="0000000005"/>
 *                                             &lt;fractionDigits value="03"/>
 *                                             &lt;maxInclusive value="99.999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="PCT_RSGO_CLIENT_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                             &lt;totalDigits value="0000000005"/>
 *                                             &lt;fractionDigits value="03"/>
 *                                             &lt;maxInclusive value="99.999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NIV_ATRIB_CMSN_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NUM_DIAS_VALOR_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="IND_AUT_SIT_ESP_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="PCT_CLI_GAR_REA_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                             &lt;totalDigits value="0000000005"/>
 *                                             &lt;fractionDigits value="03"/>
 *                                             &lt;maxInclusive value="99.999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="PCT_CLI_GAR_PER_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                             &lt;totalDigits value="0000000005"/>
 *                                             &lt;fractionDigits value="03"/>
 *                                             &lt;maxInclusive value="99.999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="IND_AUT_MOD_PERS">
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
 *                             &lt;element name="EP_ATRIB_PERF_TRAN_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="IND_AUT_BAJO_RSGO">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="IND_AUT_MEDIO_RSGO">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="IND_AUT_ALTO_RSGO">
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
    "otrepconatribempl1T"
})
public class ResponseBansefi {

    @XmlElement(name = "OTR_EP_CON_ATRIB_EMPL_1_T", required = true)
    protected ResponseBansefi.OTREPCONATRIBEMPL1T otrepconatribempl1T;

    /**
     * Obtiene el valor de la propiedad otrepconatribempl1T.
     * 
     * @return
     *     possible object is
     *     {@link ResponseBansefi.OTREPCONATRIBEMPL1T }
     *     
     */
    public ResponseBansefi.OTREPCONATRIBEMPL1T getOTREPCONATRIBEMPL1T() {
        return otrepconatribempl1T;
    }

    /**
     * Define el valor de la propiedad otrepconatribempl1T.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseBansefi.OTREPCONATRIBEMPL1T }
     *     
     */
    public void setOTREPCONATRIBEMPL1T(ResponseBansefi.OTREPCONATRIBEMPL1T value) {
        this.otrepconatribempl1T = value;
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
     *         &lt;element name="TR_EP_CON_ATRIB_EMPL_1_EV">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="EP_ATRIBUCIONES_E">
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
     *                             &lt;element name="ID_INTERNO_EMPL_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000008"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="FECHA_INIC_TEMP_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="FECHA_FIN_TEMP_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="PCT_PSV_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                   &lt;totalDigits value="0000000005"/>
     *                                   &lt;fractionDigits value="03"/>
     *                                   &lt;maxInclusive value="99.999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="PCT_ACTIVO_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                   &lt;totalDigits value="0000000005"/>
     *                                   &lt;fractionDigits value="03"/>
     *                                   &lt;maxInclusive value="99.999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="PCT_EXTRANJERO_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                   &lt;totalDigits value="0000000005"/>
     *                                   &lt;fractionDigits value="03"/>
     *                                   &lt;maxInclusive value="99.999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="PCT_INTERMED_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                   &lt;totalDigits value="0000000005"/>
     *                                   &lt;fractionDigits value="03"/>
     *                                   &lt;maxInclusive value="99.999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="PCT_SERVICIOS_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                   &lt;totalDigits value="0000000005"/>
     *                                   &lt;fractionDigits value="03"/>
     *                                   &lt;maxInclusive value="99.999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="PCT_RSGO_PD_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                   &lt;totalDigits value="0000000005"/>
     *                                   &lt;fractionDigits value="03"/>
     *                                   &lt;maxInclusive value="99.999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="PCT_RSGO_CLIENT_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                   &lt;totalDigits value="0000000005"/>
     *                                   &lt;fractionDigits value="03"/>
     *                                   &lt;maxInclusive value="99.999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="NIV_ATRIB_CMSN_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="NUM_DIAS_VALOR_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="IND_AUT_SIT_ESP_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="PCT_CLI_GAR_REA_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                   &lt;totalDigits value="0000000005"/>
     *                                   &lt;fractionDigits value="03"/>
     *                                   &lt;maxInclusive value="99.999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="PCT_CLI_GAR_PER_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                   &lt;totalDigits value="0000000005"/>
     *                                   &lt;fractionDigits value="03"/>
     *                                   &lt;maxInclusive value="99.999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="IND_AUT_MOD_PERS">
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
     *                   &lt;element name="EP_ATRIB_PERF_TRAN_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="IND_AUT_BAJO_RSGO">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="IND_AUT_MEDIO_RSGO">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="IND_AUT_ALTO_RSGO">
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
        "trepconatribempl1EV",
        "stdtrnmsjparmv",
        "stdtrnoparmv"
    })
    public static class OTREPCONATRIBEMPL1T {

        @XmlElement(name = "RTRN_CD")
        protected int rtrncd;
        @XmlElement(name = "TR_EP_CON_ATRIB_EMPL_1_EV", required = true)
        protected ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV trepconatribempl1EV;
        @XmlElement(name = "STD_TRN_MSJ_PARM_V", required = true)
        protected List<ResponseBansefi.OTREPCONATRIBEMPL1T.STDTRNMSJPARMV> stdtrnmsjparmv;
        @XmlElement(name = "STD_TRN_O_PARM_V", required = true)
        protected ResponseBansefi.OTREPCONATRIBEMPL1T.STDTRNOPARMV stdtrnoparmv;

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
         * Obtiene el valor de la propiedad trepconatribempl1EV.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV }
         *     
         */
        public ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV getTREPCONATRIBEMPL1EV() {
            return trepconatribempl1EV;
        }

        /**
         * Define el valor de la propiedad trepconatribempl1EV.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV }
         *     
         */
        public void setTREPCONATRIBEMPL1EV(ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV value) {
            this.trepconatribempl1EV = value;
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
         * {@link ResponseBansefi.OTREPCONATRIBEMPL1T.STDTRNMSJPARMV }
         * 
         * 
         */
        public List<ResponseBansefi.OTREPCONATRIBEMPL1T.STDTRNMSJPARMV> getSTDTRNMSJPARMV() {
            if (stdtrnmsjparmv == null) {
                stdtrnmsjparmv = new ArrayList<ResponseBansefi.OTREPCONATRIBEMPL1T.STDTRNMSJPARMV>();
            }
            return this.stdtrnmsjparmv;
        }

        /**
         * Obtiene el valor de la propiedad stdtrnoparmv.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTREPCONATRIBEMPL1T.STDTRNOPARMV }
         *     
         */
        public ResponseBansefi.OTREPCONATRIBEMPL1T.STDTRNOPARMV getSTDTRNOPARMV() {
            return stdtrnoparmv;
        }

        /**
         * Define el valor de la propiedad stdtrnoparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTREPCONATRIBEMPL1T.STDTRNOPARMV }
         *     
         */
        public void setSTDTRNOPARMV(ResponseBansefi.OTREPCONATRIBEMPL1T.STDTRNOPARMV value) {
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
         *         &lt;element name="EP_ATRIBUCIONES_E">
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
         *                   &lt;element name="ID_INTERNO_EMPL_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000008"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="FECHA_INIC_TEMP_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="FECHA_FIN_TEMP_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="PCT_PSV_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                         &lt;totalDigits value="0000000005"/>
         *                         &lt;fractionDigits value="03"/>
         *                         &lt;maxInclusive value="99.999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="PCT_ACTIVO_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                         &lt;totalDigits value="0000000005"/>
         *                         &lt;fractionDigits value="03"/>
         *                         &lt;maxInclusive value="99.999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="PCT_EXTRANJERO_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                         &lt;totalDigits value="0000000005"/>
         *                         &lt;fractionDigits value="03"/>
         *                         &lt;maxInclusive value="99.999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="PCT_INTERMED_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                         &lt;totalDigits value="0000000005"/>
         *                         &lt;fractionDigits value="03"/>
         *                         &lt;maxInclusive value="99.999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="PCT_SERVICIOS_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                         &lt;totalDigits value="0000000005"/>
         *                         &lt;fractionDigits value="03"/>
         *                         &lt;maxInclusive value="99.999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="PCT_RSGO_PD_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                         &lt;totalDigits value="0000000005"/>
         *                         &lt;fractionDigits value="03"/>
         *                         &lt;maxInclusive value="99.999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="PCT_RSGO_CLIENT_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                         &lt;totalDigits value="0000000005"/>
         *                         &lt;fractionDigits value="03"/>
         *                         &lt;maxInclusive value="99.999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="NIV_ATRIB_CMSN_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="NUM_DIAS_VALOR_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="IND_AUT_SIT_ESP_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="PCT_CLI_GAR_REA_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                         &lt;totalDigits value="0000000005"/>
         *                         &lt;fractionDigits value="03"/>
         *                         &lt;maxInclusive value="99.999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="PCT_CLI_GAR_PER_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                         &lt;totalDigits value="0000000005"/>
         *                         &lt;fractionDigits value="03"/>
         *                         &lt;maxInclusive value="99.999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="IND_AUT_MOD_PERS">
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
         *         &lt;element name="EP_ATRIB_PERF_TRAN_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="IND_AUT_BAJO_RSGO">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="IND_AUT_MEDIO_RSGO">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="IND_AUT_ALTO_RSGO">
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
            "epatribucionese",
            "epatribperftranv"
        })
        public static class TREPCONATRIBEMPL1EV {

            @XmlElement(name = "EP_ATRIBUCIONES_E", required = true)
            protected ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV.EPATRIBUCIONESE epatribucionese;
            @XmlElement(name = "EP_ATRIB_PERF_TRAN_V", required = true)
            protected ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV.EPATRIBPERFTRANV epatribperftranv;

            /**
             * Obtiene el valor de la propiedad epatribucionese.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV.EPATRIBUCIONESE }
             *     
             */
            public ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV.EPATRIBUCIONESE getEPATRIBUCIONESE() {
                return epatribucionese;
            }

            /**
             * Define el valor de la propiedad epatribucionese.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV.EPATRIBUCIONESE }
             *     
             */
            public void setEPATRIBUCIONESE(ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV.EPATRIBUCIONESE value) {
                this.epatribucionese = value;
            }

            /**
             * Obtiene el valor de la propiedad epatribperftranv.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV.EPATRIBPERFTRANV }
             *     
             */
            public ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV.EPATRIBPERFTRANV getEPATRIBPERFTRANV() {
                return epatribperftranv;
            }

            /**
             * Define el valor de la propiedad epatribperftranv.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV.EPATRIBPERFTRANV }
             *     
             */
            public void setEPATRIBPERFTRANV(ResponseBansefi.OTREPCONATRIBEMPL1T.TREPCONATRIBEMPL1EV.EPATRIBPERFTRANV value) {
                this.epatribperftranv = value;
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
             *         &lt;element name="IND_AUT_BAJO_RSGO">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="IND_AUT_MEDIO_RSGO">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="IND_AUT_ALTO_RSGO">
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
                "indautbajorsgo",
                "indautmediorsgo",
                "indautaltorsgo"
            })
            public static class EPATRIBPERFTRANV {

                @XmlElement(name = "IND_AUT_BAJO_RSGO", required = true)
                protected String indautbajorsgo;
                @XmlElement(name = "IND_AUT_MEDIO_RSGO", required = true)
                protected String indautmediorsgo;
                @XmlElement(name = "IND_AUT_ALTO_RSGO", required = true)
                protected String indautaltorsgo;

                /**
                 * Obtiene el valor de la propiedad indautbajorsgo.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getINDAUTBAJORSGO() {
                    return indautbajorsgo;
                }

                /**
                 * Define el valor de la propiedad indautbajorsgo.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setINDAUTBAJORSGO(String value) {
                    this.indautbajorsgo = value;
                }

                /**
                 * Obtiene el valor de la propiedad indautmediorsgo.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getINDAUTMEDIORSGO() {
                    return indautmediorsgo;
                }

                /**
                 * Define el valor de la propiedad indautmediorsgo.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setINDAUTMEDIORSGO(String value) {
                    this.indautmediorsgo = value;
                }

                /**
                 * Obtiene el valor de la propiedad indautaltorsgo.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getINDAUTALTORSGO() {
                    return indautaltorsgo;
                }

                /**
                 * Define el valor de la propiedad indautaltorsgo.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setINDAUTALTORSGO(String value) {
                    this.indautaltorsgo = value;
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
             *         &lt;element name="ID_INTERNO_EMPL_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000008"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="FECHA_INIC_TEMP_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="FECHA_FIN_TEMP_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="PCT_PSV_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *               &lt;totalDigits value="0000000005"/>
             *               &lt;fractionDigits value="03"/>
             *               &lt;maxInclusive value="99.999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="PCT_ACTIVO_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *               &lt;totalDigits value="0000000005"/>
             *               &lt;fractionDigits value="03"/>
             *               &lt;maxInclusive value="99.999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="PCT_EXTRANJERO_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *               &lt;totalDigits value="0000000005"/>
             *               &lt;fractionDigits value="03"/>
             *               &lt;maxInclusive value="99.999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="PCT_INTERMED_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *               &lt;totalDigits value="0000000005"/>
             *               &lt;fractionDigits value="03"/>
             *               &lt;maxInclusive value="99.999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="PCT_SERVICIOS_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *               &lt;totalDigits value="0000000005"/>
             *               &lt;fractionDigits value="03"/>
             *               &lt;maxInclusive value="99.999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="PCT_RSGO_PD_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *               &lt;totalDigits value="0000000005"/>
             *               &lt;fractionDigits value="03"/>
             *               &lt;maxInclusive value="99.999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="PCT_RSGO_CLIENT_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *               &lt;totalDigits value="0000000005"/>
             *               &lt;fractionDigits value="03"/>
             *               &lt;maxInclusive value="99.999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="NIV_ATRIB_CMSN_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="NUM_DIAS_VALOR_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="IND_AUT_SIT_ESP_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="PCT_CLI_GAR_REA_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *               &lt;totalDigits value="0000000005"/>
             *               &lt;fractionDigits value="03"/>
             *               &lt;maxInclusive value="99.999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="PCT_CLI_GAR_PER_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *               &lt;totalDigits value="0000000005"/>
             *               &lt;fractionDigits value="03"/>
             *               &lt;maxInclusive value="99.999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="IND_AUT_MOD_PERS">
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
                "codnrbeen",
                "idinternoemplep",
                "fechainictempep",
                "fechafintempep",
                "pctpsvep",
                "pctactivoep",
                "pctextranjeroep",
                "pctintermedep",
                "pctserviciosep",
                "pctrsgopdep",
                "pctrsgoclientep",
                "nivatribcmsnep",
                "numdiasvalorep",
                "indautsitespep",
                "pctcligarreaep",
                "pctcligarperep",
                "indautmodpers"
            })
            public static class EPATRIBUCIONESE {

                @XmlElement(name = "COD_NRBE_EN", required = true)
                protected String codnrbeen;
                @XmlElement(name = "ID_INTERNO_EMPL_EP", required = true)
                protected String idinternoemplep;
                @XmlElement(name = "FECHA_INIC_TEMP_EP")
                protected int fechainictempep;
                @XmlElement(name = "FECHA_FIN_TEMP_EP")
                protected int fechafintempep;
                @XmlElement(name = "PCT_PSV_EP", required = true)
                protected BigDecimal pctpsvep;
                @XmlElement(name = "PCT_ACTIVO_EP", required = true)
                protected BigDecimal pctactivoep;
                @XmlElement(name = "PCT_EXTRANJERO_EP", required = true)
                protected BigDecimal pctextranjeroep;
                @XmlElement(name = "PCT_INTERMED_EP", required = true)
                protected BigDecimal pctintermedep;
                @XmlElement(name = "PCT_SERVICIOS_EP", required = true)
                protected BigDecimal pctserviciosep;
                @XmlElement(name = "PCT_RSGO_PD_EP", required = true)
                protected BigDecimal pctrsgopdep;
                @XmlElement(name = "PCT_RSGO_CLIENT_EP", required = true)
                protected BigDecimal pctrsgoclientep;
                @XmlElement(name = "NIV_ATRIB_CMSN_EP", required = true)
                protected String nivatribcmsnep;
                @XmlElement(name = "NUM_DIAS_VALOR_EP")
                protected int numdiasvalorep;
                @XmlElement(name = "IND_AUT_SIT_ESP_EP", required = true)
                protected String indautsitespep;
                @XmlElement(name = "PCT_CLI_GAR_REA_EP", required = true)
                protected BigDecimal pctcligarreaep;
                @XmlElement(name = "PCT_CLI_GAR_PER_EP", required = true)
                protected BigDecimal pctcligarperep;
                @XmlElement(name = "IND_AUT_MOD_PERS", required = true)
                protected String indautmodpers;

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
                 * Obtiene el valor de la propiedad idinternoemplep.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getIDINTERNOEMPLEP() {
                    return idinternoemplep;
                }

                /**
                 * Define el valor de la propiedad idinternoemplep.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setIDINTERNOEMPLEP(String value) {
                    this.idinternoemplep = value;
                }

                /**
                 * Obtiene el valor de la propiedad fechainictempep.
                 * 
                 */
                public int getFECHAINICTEMPEP() {
                    return fechainictempep;
                }

                /**
                 * Define el valor de la propiedad fechainictempep.
                 * 
                 */
                public void setFECHAINICTEMPEP(int value) {
                    this.fechainictempep = value;
                }

                /**
                 * Obtiene el valor de la propiedad fechafintempep.
                 * 
                 */
                public int getFECHAFINTEMPEP() {
                    return fechafintempep;
                }

                /**
                 * Define el valor de la propiedad fechafintempep.
                 * 
                 */
                public void setFECHAFINTEMPEP(int value) {
                    this.fechafintempep = value;
                }

                /**
                 * Obtiene el valor de la propiedad pctpsvep.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getPCTPSVEP() {
                    return pctpsvep;
                }

                /**
                 * Define el valor de la propiedad pctpsvep.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setPCTPSVEP(BigDecimal value) {
                    this.pctpsvep = value;
                }

                /**
                 * Obtiene el valor de la propiedad pctactivoep.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getPCTACTIVOEP() {
                    return pctactivoep;
                }

                /**
                 * Define el valor de la propiedad pctactivoep.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setPCTACTIVOEP(BigDecimal value) {
                    this.pctactivoep = value;
                }

                /**
                 * Obtiene el valor de la propiedad pctextranjeroep.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getPCTEXTRANJEROEP() {
                    return pctextranjeroep;
                }

                /**
                 * Define el valor de la propiedad pctextranjeroep.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setPCTEXTRANJEROEP(BigDecimal value) {
                    this.pctextranjeroep = value;
                }

                /**
                 * Obtiene el valor de la propiedad pctintermedep.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getPCTINTERMEDEP() {
                    return pctintermedep;
                }

                /**
                 * Define el valor de la propiedad pctintermedep.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setPCTINTERMEDEP(BigDecimal value) {
                    this.pctintermedep = value;
                }

                /**
                 * Obtiene el valor de la propiedad pctserviciosep.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getPCTSERVICIOSEP() {
                    return pctserviciosep;
                }

                /**
                 * Define el valor de la propiedad pctserviciosep.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setPCTSERVICIOSEP(BigDecimal value) {
                    this.pctserviciosep = value;
                }

                /**
                 * Obtiene el valor de la propiedad pctrsgopdep.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getPCTRSGOPDEP() {
                    return pctrsgopdep;
                }

                /**
                 * Define el valor de la propiedad pctrsgopdep.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setPCTRSGOPDEP(BigDecimal value) {
                    this.pctrsgopdep = value;
                }

                /**
                 * Obtiene el valor de la propiedad pctrsgoclientep.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getPCTRSGOCLIENTEP() {
                    return pctrsgoclientep;
                }

                /**
                 * Define el valor de la propiedad pctrsgoclientep.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setPCTRSGOCLIENTEP(BigDecimal value) {
                    this.pctrsgoclientep = value;
                }

                /**
                 * Obtiene el valor de la propiedad nivatribcmsnep.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getNIVATRIBCMSNEP() {
                    return nivatribcmsnep;
                }

                /**
                 * Define el valor de la propiedad nivatribcmsnep.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setNIVATRIBCMSNEP(String value) {
                    this.nivatribcmsnep = value;
                }

                /**
                 * Obtiene el valor de la propiedad numdiasvalorep.
                 * 
                 */
                public int getNUMDIASVALOREP() {
                    return numdiasvalorep;
                }

                /**
                 * Define el valor de la propiedad numdiasvalorep.
                 * 
                 */
                public void setNUMDIASVALOREP(int value) {
                    this.numdiasvalorep = value;
                }

                /**
                 * Obtiene el valor de la propiedad indautsitespep.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getINDAUTSITESPEP() {
                    return indautsitespep;
                }

                /**
                 * Define el valor de la propiedad indautsitespep.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setINDAUTSITESPEP(String value) {
                    this.indautsitespep = value;
                }

                /**
                 * Obtiene el valor de la propiedad pctcligarreaep.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getPCTCLIGARREAEP() {
                    return pctcligarreaep;
                }

                /**
                 * Define el valor de la propiedad pctcligarreaep.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setPCTCLIGARREAEP(BigDecimal value) {
                    this.pctcligarreaep = value;
                }

                /**
                 * Obtiene el valor de la propiedad pctcligarperep.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getPCTCLIGARPEREP() {
                    return pctcligarperep;
                }

                /**
                 * Define el valor de la propiedad pctcligarperep.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setPCTCLIGARPEREP(BigDecimal value) {
                    this.pctcligarperep = value;
                }

                /**
                 * Obtiene el valor de la propiedad indautmodpers.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getINDAUTMODPERS() {
                    return indautmodpers;
                }

                /**
                 * Define el valor de la propiedad indautmodpers.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setINDAUTMODPERS(String value) {
                    this.indautmodpers = value;
                }

            }

        }

    }

}
