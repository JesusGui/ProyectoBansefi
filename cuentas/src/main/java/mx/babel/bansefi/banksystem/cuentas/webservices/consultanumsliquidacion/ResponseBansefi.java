
package mx.babel.bansefi.banksystem.cuentas.webservices.consultanumsliquidacion;

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
 *         &lt;element name="OTR_LIQ_CONSULTAR_NUMEROS">
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
 *                   &lt;element name="TR_LIQ_CONSULTAR_NUMEROS">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="LIQ_NUMEROS_LST">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="NUMBER_OF_RECORDS">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="LIQ_NUMEROS_V" maxOccurs="366">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="FECHA_VALOR">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="STD_DEC_4_0">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="9999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IMP_APNTE">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="SGN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IMP_SDO">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="LIQ_NUM_A_V" maxOccurs="366">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="STD_DEC_18_2">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000017"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="LIQ_NUM_D_V" maxOccurs="366">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="STD_DEC_18_2">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000017"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="LIQ_NUM_E_V" maxOccurs="366">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="STD_DEC_18_2">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000017"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IMP_SDO_01">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
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
 *                             &lt;element name="LIQ_ACUM_NUMEROS_LST">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="NUMBER_OF_RECORDS">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="ACUMULADO_NUMEROS_V" maxOccurs="5">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_DEC_18_2">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000017"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="ID_PDS_1">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000003"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="ID_PARMCD_1">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000003"/>
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
 *                             &lt;element name="LIQ_CONSULTAR_NUMEROS_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="COD_OPER_LIQ">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000004"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="FECHA_DESDE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="FECHA_HASTA">
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
    "otrliqconsultarnumeros"
})
public class ResponseBansefi {

    @XmlElement(name = "OTR_LIQ_CONSULTAR_NUMEROS", required = true)
    protected ResponseBansefi.OTRLIQCONSULTARNUMEROS otrliqconsultarnumeros;

    /**
     * Obtiene el valor de la propiedad otrliqconsultarnumeros.
     * 
     * @return
     *     possible object is
     *     {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS }
     *     
     */
    public ResponseBansefi.OTRLIQCONSULTARNUMEROS getOTRLIQCONSULTARNUMEROS() {
        return otrliqconsultarnumeros;
    }

    /**
     * Define el valor de la propiedad otrliqconsultarnumeros.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS }
     *     
     */
    public void setOTRLIQCONSULTARNUMEROS(ResponseBansefi.OTRLIQCONSULTARNUMEROS value) {
        this.otrliqconsultarnumeros = value;
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
     *         &lt;element name="TR_LIQ_CONSULTAR_NUMEROS">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="LIQ_NUMEROS_LST">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="NUMBER_OF_RECORDS">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="LIQ_NUMEROS_V" maxOccurs="366">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="FECHA_VALOR">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="99999999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="STD_DEC_4_0">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="9999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IMP_APNTE">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="SGN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IMP_SDO">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="LIQ_NUM_A_V" maxOccurs="366">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="STD_DEC_18_2">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                                       &lt;totalDigits value="0000000017"/>
     *                                                       &lt;fractionDigits value="02"/>
     *                                                       &lt;maxInclusive value="999999999999999.99"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                       &lt;element name="LIQ_NUM_D_V" maxOccurs="366">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="STD_DEC_18_2">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                                       &lt;totalDigits value="0000000017"/>
     *                                                       &lt;fractionDigits value="02"/>
     *                                                       &lt;maxInclusive value="999999999999999.99"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                       &lt;element name="LIQ_NUM_E_V" maxOccurs="366">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="STD_DEC_18_2">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                                       &lt;totalDigits value="0000000017"/>
     *                                                       &lt;fractionDigits value="02"/>
     *                                                       &lt;maxInclusive value="999999999999999.99"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                       &lt;element name="IMP_SDO_01">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
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
     *                   &lt;element name="LIQ_ACUM_NUMEROS_LST">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="NUMBER_OF_RECORDS">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="ACUMULADO_NUMEROS_V" maxOccurs="5">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="STD_DEC_18_2">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000017"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="ID_PDS_1">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000003"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="ID_PARMCD_1">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000003"/>
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
     *                   &lt;element name="LIQ_CONSULTAR_NUMEROS_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="COD_OPER_LIQ">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000004"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="FECHA_DESDE">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="FECHA_HASTA">
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
        "trliqconsultarnumeros",
        "stdtrnmsjparmv",
        "stdtrnoparmv"
    })
    public static class OTRLIQCONSULTARNUMEROS {

        @XmlElement(name = "RTRN_CD")
        protected int rtrncd;
        @XmlElement(name = "TR_LIQ_CONSULTAR_NUMEROS", required = true)
        protected ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS trliqconsultarnumeros;
        @XmlElement(name = "STD_TRN_MSJ_PARM_V", required = true)
        protected List<ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNMSJPARMV> stdtrnmsjparmv;
        @XmlElement(name = "STD_TRN_O_PARM_V", required = true)
        protected ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNOPARMV stdtrnoparmv;

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
         * Obtiene el valor de la propiedad trliqconsultarnumeros.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS }
         *     
         */
        public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS getTRLIQCONSULTARNUMEROS() {
            return trliqconsultarnumeros;
        }

        /**
         * Define el valor de la propiedad trliqconsultarnumeros.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS }
         *     
         */
        public void setTRLIQCONSULTARNUMEROS(ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS value) {
            this.trliqconsultarnumeros = value;
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
         * {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNMSJPARMV }
         * 
         * 
         */
        public List<ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNMSJPARMV> getSTDTRNMSJPARMV() {
            if (stdtrnmsjparmv == null) {
                stdtrnmsjparmv = new ArrayList<ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNMSJPARMV>();
            }
            return this.stdtrnmsjparmv;
        }

        /**
         * Obtiene el valor de la propiedad stdtrnoparmv.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNOPARMV }
         *     
         */
        public ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNOPARMV getSTDTRNOPARMV() {
            return stdtrnoparmv;
        }

        /**
         * Define el valor de la propiedad stdtrnoparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNOPARMV }
         *     
         */
        public void setSTDTRNOPARMV(ResponseBansefi.OTRLIQCONSULTARNUMEROS.STDTRNOPARMV value) {
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
         *         &lt;element name="LIQ_NUMEROS_LST">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="NUMBER_OF_RECORDS">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="LIQ_NUMEROS_V" maxOccurs="366">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="FECHA_VALOR">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="99999999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="STD_DEC_4_0">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="9999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IMP_APNTE">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="SGN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IMP_SDO">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="LIQ_NUM_A_V" maxOccurs="366">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="STD_DEC_18_2">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                             &lt;totalDigits value="0000000017"/>
         *                                             &lt;fractionDigits value="02"/>
         *                                             &lt;maxInclusive value="999999999999999.99"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                             &lt;element name="LIQ_NUM_D_V" maxOccurs="366">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="STD_DEC_18_2">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                             &lt;totalDigits value="0000000017"/>
         *                                             &lt;fractionDigits value="02"/>
         *                                             &lt;maxInclusive value="999999999999999.99"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                             &lt;element name="LIQ_NUM_E_V" maxOccurs="366">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="STD_DEC_18_2">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                             &lt;totalDigits value="0000000017"/>
         *                                             &lt;fractionDigits value="02"/>
         *                                             &lt;maxInclusive value="999999999999999.99"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                             &lt;element name="IMP_SDO_01">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
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
         *         &lt;element name="LIQ_ACUM_NUMEROS_LST">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="NUMBER_OF_RECORDS">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="ACUMULADO_NUMEROS_V" maxOccurs="5">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="STD_DEC_18_2">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000017"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="ID_PDS_1">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000003"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="ID_PARMCD_1">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000003"/>
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
         *         &lt;element name="LIQ_CONSULTAR_NUMEROS_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="COD_OPER_LIQ">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000004"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="FECHA_DESDE">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="FECHA_HASTA">
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
            "liqnumeroslst",
            "liqacumnumeroslst",
            "liqconsultarnumerosv"
        })
        public static class TRLIQCONSULTARNUMEROS {

            @XmlElement(name = "LIQ_NUMEROS_LST", required = true)
            protected ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST liqnumeroslst;
            @XmlElement(name = "LIQ_ACUM_NUMEROS_LST", required = true)
            protected ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST liqacumnumeroslst;
            @XmlElement(name = "LIQ_CONSULTAR_NUMEROS_V", required = true)
            protected ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQCONSULTARNUMEROSV liqconsultarnumerosv;

            /**
             * Obtiene el valor de la propiedad liqnumeroslst.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST }
             *     
             */
            public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST getLIQNUMEROSLST() {
                return liqnumeroslst;
            }

            /**
             * Define el valor de la propiedad liqnumeroslst.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST }
             *     
             */
            public void setLIQNUMEROSLST(ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST value) {
                this.liqnumeroslst = value;
            }

            /**
             * Obtiene el valor de la propiedad liqacumnumeroslst.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST }
             *     
             */
            public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST getLIQACUMNUMEROSLST() {
                return liqacumnumeroslst;
            }

            /**
             * Define el valor de la propiedad liqacumnumeroslst.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST }
             *     
             */
            public void setLIQACUMNUMEROSLST(ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST value) {
                this.liqacumnumeroslst = value;
            }

            /**
             * Obtiene el valor de la propiedad liqconsultarnumerosv.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQCONSULTARNUMEROSV }
             *     
             */
            public ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQCONSULTARNUMEROSV getLIQCONSULTARNUMEROSV() {
                return liqconsultarnumerosv;
            }

            /**
             * Define el valor de la propiedad liqconsultarnumerosv.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQCONSULTARNUMEROSV }
             *     
             */
            public void setLIQCONSULTARNUMEROSV(ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQCONSULTARNUMEROSV value) {
                this.liqconsultarnumerosv = value;
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
             *         &lt;element name="NUMBER_OF_RECORDS">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="ACUMULADO_NUMEROS_V" maxOccurs="5">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="STD_DEC_18_2">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000017"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="ID_PDS_1">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000003"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="ID_PARMCD_1">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000003"/>
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
                "numberofrecords",
                "acumuladonumerosv"
            })
            public static class LIQACUMNUMEROSLST {

                @XmlElement(name = "NUMBER_OF_RECORDS")
                protected int numberofrecords;
                @XmlElement(name = "ACUMULADO_NUMEROS_V", required = true)
                protected List<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST.ACUMULADONUMEROSV> acumuladonumerosv;

                /**
                 * Obtiene el valor de la propiedad numberofrecords.
                 * 
                 */
                public int getNUMBEROFRECORDS() {
                    return numberofrecords;
                }

                /**
                 * Define el valor de la propiedad numberofrecords.
                 * 
                 */
                public void setNUMBEROFRECORDS(int value) {
                    this.numberofrecords = value;
                }

                /**
                 * Gets the value of the acumuladonumerosv property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the acumuladonumerosv property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getACUMULADONUMEROSV().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST.ACUMULADONUMEROSV }
                 * 
                 * 
                 */
                public List<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST.ACUMULADONUMEROSV> getACUMULADONUMEROSV() {
                    if (acumuladonumerosv == null) {
                        acumuladonumerosv = new ArrayList<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQACUMNUMEROSLST.ACUMULADONUMEROSV>();
                    }
                    return this.acumuladonumerosv;
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
                 *         &lt;element name="STD_DEC_18_2">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000017"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="ID_PDS_1">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000003"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="ID_PARMCD_1">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000003"/>
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
                    "stddec182",
                    "idpds1",
                    "idparmcd1"
                })
                public static class ACUMULADONUMEROSV {

                    @XmlElement(name = "STD_DEC_18_2", required = true)
                    protected BigDecimal stddec182;
                    @XmlElement(name = "ID_PDS_1", required = true)
                    protected String idpds1;
                    @XmlElement(name = "ID_PARMCD_1", required = true)
                    protected String idparmcd1;

                    /**
                     * Obtiene el valor de la propiedad stddec182.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public BigDecimal getSTDDEC182() {
                        return stddec182;
                    }

                    /**
                     * Define el valor de la propiedad stddec182.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public void setSTDDEC182(BigDecimal value) {
                        this.stddec182 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad idpds1.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIDPDS1() {
                        return idpds1;
                    }

                    /**
                     * Define el valor de la propiedad idpds1.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIDPDS1(String value) {
                        this.idpds1 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad idparmcd1.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIDPARMCD1() {
                        return idparmcd1;
                    }

                    /**
                     * Define el valor de la propiedad idparmcd1.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIDPARMCD1(String value) {
                        this.idparmcd1 = value;
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
             *         &lt;element name="COD_OPER_LIQ">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000004"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="FECHA_DESDE">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="FECHA_HASTA">
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
                "codoperliq",
                "fechadesde",
                "fechahasta"
            })
            public static class LIQCONSULTARNUMEROSV {

                @XmlElement(name = "COD_OPER_LIQ", required = true)
                protected String codoperliq;
                @XmlElement(name = "FECHA_DESDE")
                protected int fechadesde;
                @XmlElement(name = "FECHA_HASTA")
                protected int fechahasta;

                /**
                 * Obtiene el valor de la propiedad codoperliq.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODOPERLIQ() {
                    return codoperliq;
                }

                /**
                 * Define el valor de la propiedad codoperliq.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODOPERLIQ(String value) {
                    this.codoperliq = value;
                }

                /**
                 * Obtiene el valor de la propiedad fechadesde.
                 * 
                 */
                public int getFECHADESDE() {
                    return fechadesde;
                }

                /**
                 * Define el valor de la propiedad fechadesde.
                 * 
                 */
                public void setFECHADESDE(int value) {
                    this.fechadesde = value;
                }

                /**
                 * Obtiene el valor de la propiedad fechahasta.
                 * 
                 */
                public int getFECHAHASTA() {
                    return fechahasta;
                }

                /**
                 * Define el valor de la propiedad fechahasta.
                 * 
                 */
                public void setFECHAHASTA(int value) {
                    this.fechahasta = value;
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
             *         &lt;element name="NUMBER_OF_RECORDS">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="LIQ_NUMEROS_V" maxOccurs="366">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="FECHA_VALOR">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="99999999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="STD_DEC_4_0">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="9999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IMP_APNTE">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="SGN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IMP_SDO">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="LIQ_NUM_A_V" maxOccurs="366">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="STD_DEC_18_2">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                                   &lt;totalDigits value="0000000017"/>
             *                                   &lt;fractionDigits value="02"/>
             *                                   &lt;maxInclusive value="999999999999999.99"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                           &lt;/sequence>
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                   &lt;element name="LIQ_NUM_D_V" maxOccurs="366">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="STD_DEC_18_2">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                                   &lt;totalDigits value="0000000017"/>
             *                                   &lt;fractionDigits value="02"/>
             *                                   &lt;maxInclusive value="999999999999999.99"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                           &lt;/sequence>
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                   &lt;element name="LIQ_NUM_E_V" maxOccurs="366">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="STD_DEC_18_2">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                                   &lt;totalDigits value="0000000017"/>
             *                                   &lt;fractionDigits value="02"/>
             *                                   &lt;maxInclusive value="999999999999999.99"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                           &lt;/sequence>
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                   &lt;element name="IMP_SDO_01">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
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
                "numberofrecords",
                "liqnumerosv"
            })
            public static class LIQNUMEROSLST {

                @XmlElement(name = "NUMBER_OF_RECORDS")
                protected int numberofrecords;
                @XmlElement(name = "LIQ_NUMEROS_V", required = true)
                protected List<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV> liqnumerosv;

                /**
                 * Obtiene el valor de la propiedad numberofrecords.
                 * 
                 */
                public int getNUMBEROFRECORDS() {
                    return numberofrecords;
                }

                /**
                 * Define el valor de la propiedad numberofrecords.
                 * 
                 */
                public void setNUMBEROFRECORDS(int value) {
                    this.numberofrecords = value;
                }

                /**
                 * Gets the value of the liqnumerosv property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the liqnumerosv property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getLIQNUMEROSV().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV }
                 * 
                 * 
                 */
                public List<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV> getLIQNUMEROSV() {
                    if (liqnumerosv == null) {
                        liqnumerosv = new ArrayList<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV>();
                    }
                    return this.liqnumerosv;
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
                 *         &lt;element name="FECHA_VALOR">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="99999999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="STD_DEC_4_0">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="9999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IMP_APNTE">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="SGN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IMP_SDO">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="LIQ_NUM_A_V" maxOccurs="366">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="STD_DEC_18_2">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *                         &lt;totalDigits value="0000000017"/>
                 *                         &lt;fractionDigits value="02"/>
                 *                         &lt;maxInclusive value="999999999999999.99"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *         &lt;element name="LIQ_NUM_D_V" maxOccurs="366">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="STD_DEC_18_2">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *                         &lt;totalDigits value="0000000017"/>
                 *                         &lt;fractionDigits value="02"/>
                 *                         &lt;maxInclusive value="999999999999999.99"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *         &lt;element name="LIQ_NUM_E_V" maxOccurs="366">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="STD_DEC_18_2">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *                         &lt;totalDigits value="0000000017"/>
                 *                         &lt;fractionDigits value="02"/>
                 *                         &lt;maxInclusive value="999999999999999.99"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *         &lt;element name="IMP_SDO_01">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
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
                    "fechavalor",
                    "stddec40",
                    "impapnte",
                    "sgn",
                    "impsdo",
                    "liqnumav",
                    "liqnumdv",
                    "liqnumev",
                    "impsdo01"
                })
                public static class LIQNUMEROSV {

                    @XmlElement(name = "FECHA_VALOR")
                    protected int fechavalor;
                    @XmlElement(name = "STD_DEC_4_0")
                    protected int stddec40;
                    @XmlElement(name = "IMP_APNTE", required = true)
                    protected BigDecimal impapnte;
                    @XmlElement(name = "SGN", required = true)
                    protected String sgn;
                    @XmlElement(name = "IMP_SDO", required = true)
                    protected BigDecimal impsdo;
                    @XmlElement(name = "LIQ_NUM_A_V", required = true)
                    protected List<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMAV> liqnumav;
                    @XmlElement(name = "LIQ_NUM_D_V", required = true)
                    protected List<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMDV> liqnumdv;
                    @XmlElement(name = "LIQ_NUM_E_V", required = true)
                    protected List<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMEV> liqnumev;
                    @XmlElement(name = "IMP_SDO_01", required = true)
                    protected BigDecimal impsdo01;

                    /**
                     * Obtiene el valor de la propiedad fechavalor.
                     * 
                     */
                    public int getFECHAVALOR() {
                        return fechavalor;
                    }

                    /**
                     * Define el valor de la propiedad fechavalor.
                     * 
                     */
                    public void setFECHAVALOR(int value) {
                        this.fechavalor = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad stddec40.
                     * 
                     */
                    public int getSTDDEC40() {
                        return stddec40;
                    }

                    /**
                     * Define el valor de la propiedad stddec40.
                     * 
                     */
                    public void setSTDDEC40(int value) {
                        this.stddec40 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad impapnte.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public BigDecimal getIMPAPNTE() {
                        return impapnte;
                    }

                    /**
                     * Define el valor de la propiedad impapnte.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public void setIMPAPNTE(BigDecimal value) {
                        this.impapnte = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad sgn.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getSGN() {
                        return sgn;
                    }

                    /**
                     * Define el valor de la propiedad sgn.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setSGN(String value) {
                        this.sgn = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad impsdo.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public BigDecimal getIMPSDO() {
                        return impsdo;
                    }

                    /**
                     * Define el valor de la propiedad impsdo.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public void setIMPSDO(BigDecimal value) {
                        this.impsdo = value;
                    }

                    /**
                     * Gets the value of the liqnumav property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the liqnumav property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getLIQNUMAV().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMAV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMAV> getLIQNUMAV() {
                        if (liqnumav == null) {
                            liqnumav = new ArrayList<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMAV>();
                        }
                        return this.liqnumav;
                    }

                    /**
                     * Gets the value of the liqnumdv property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the liqnumdv property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getLIQNUMDV().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMDV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMDV> getLIQNUMDV() {
                        if (liqnumdv == null) {
                            liqnumdv = new ArrayList<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMDV>();
                        }
                        return this.liqnumdv;
                    }

                    /**
                     * Gets the value of the liqnumev property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the liqnumev property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getLIQNUMEV().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMEV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMEV> getLIQNUMEV() {
                        if (liqnumev == null) {
                            liqnumev = new ArrayList<ResponseBansefi.OTRLIQCONSULTARNUMEROS.TRLIQCONSULTARNUMEROS.LIQNUMEROSLST.LIQNUMEROSV.LIQNUMEV>();
                        }
                        return this.liqnumev;
                    }

                    /**
                     * Obtiene el valor de la propiedad impsdo01.
                     * 
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public BigDecimal getIMPSDO01() {
                        return impsdo01;
                    }

                    /**
                     * Define el valor de la propiedad impsdo01.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *     
                     */
                    public void setIMPSDO01(BigDecimal value) {
                        this.impsdo01 = value;
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
                     *         &lt;element name="STD_DEC_18_2">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                     *               &lt;totalDigits value="0000000017"/>
                     *               &lt;fractionDigits value="02"/>
                     *               &lt;maxInclusive value="999999999999999.99"/>
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
                        "stddec182"
                    })
                    public static class LIQNUMAV {

                        @XmlElement(name = "STD_DEC_18_2", required = true)
                        protected BigDecimal stddec182;

                        /**
                         * Obtiene el valor de la propiedad stddec182.
                         * 
                         * @return
                         *     possible object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public BigDecimal getSTDDEC182() {
                            return stddec182;
                        }

                        /**
                         * Define el valor de la propiedad stddec182.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public void setSTDDEC182(BigDecimal value) {
                            this.stddec182 = value;
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
                     *         &lt;element name="STD_DEC_18_2">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                     *               &lt;totalDigits value="0000000017"/>
                     *               &lt;fractionDigits value="02"/>
                     *               &lt;maxInclusive value="999999999999999.99"/>
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
                        "stddec182"
                    })
                    public static class LIQNUMDV {

                        @XmlElement(name = "STD_DEC_18_2", required = true)
                        protected BigDecimal stddec182;

                        /**
                         * Obtiene el valor de la propiedad stddec182.
                         * 
                         * @return
                         *     possible object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public BigDecimal getSTDDEC182() {
                            return stddec182;
                        }

                        /**
                         * Define el valor de la propiedad stddec182.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public void setSTDDEC182(BigDecimal value) {
                            this.stddec182 = value;
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
                     *         &lt;element name="STD_DEC_18_2">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                     *               &lt;totalDigits value="0000000017"/>
                     *               &lt;fractionDigits value="02"/>
                     *               &lt;maxInclusive value="999999999999999.99"/>
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
                        "stddec182"
                    })
                    public static class LIQNUMEV {

                        @XmlElement(name = "STD_DEC_18_2", required = true)
                        protected BigDecimal stddec182;

                        /**
                         * Obtiene el valor de la propiedad stddec182.
                         * 
                         * @return
                         *     possible object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public BigDecimal getSTDDEC182() {
                            return stddec182;
                        }

                        /**
                         * Define el valor de la propiedad stddec182.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link BigDecimal }
                         *     
                         */
                        public void setSTDDEC182(BigDecimal value) {
                            this.stddec182 = value;
                        }

                    }

                }

            }

        }

    }

}
