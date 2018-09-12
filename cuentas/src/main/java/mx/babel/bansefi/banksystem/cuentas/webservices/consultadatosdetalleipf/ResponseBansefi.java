
package mx.babel.bansefi.banksystem.cuentas.webservices.consultadatosdetalleipf;

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
 *         &lt;element name="OTR_CONS_IMPSCN_INDIV_TRN">
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
 *                   &lt;element name="NUMBER_OF_RECORDS">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                         &lt;maxInclusive value="9999"/>
 *                         &lt;minInclusive value="0"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="MORE_DATA_IN">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                         &lt;maxInclusive value="9999"/>
 *                         &lt;minInclusive value="0"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="TR_CONS_IMPSCN_INDIV_EVT">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PSV_CCC_V">
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
 *                                       &lt;element name="COD_CSB_OF">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000004"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_DIG_CR_UO">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NUM_SEC_AC">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_INTERNO_UO">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000004"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="FECHA_CTBLE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_PLZ_BANCARIA">
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
 *                             &lt;element name="NOMB_PDV">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000030"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IP_TITULAR_V" maxOccurs="4">
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
 *                                       &lt;element name="ID_INTERNO_PE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NUM_RL_ORDEN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999"/>
 *                                             &lt;minInclusive value="0"/>
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
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="TR_CONS_IMPSCN_INDIV_EVT">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="COD_ECV_IMPSCN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="FECHA_VALOR">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="IMP_SDO">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                             &lt;totalDigits value="0000000015"/>
 *                                             &lt;fractionDigits value="02"/>
 *                                             &lt;maxInclusive value="9999999999999.99"/>
 *                                             &lt;minInclusive value="0.0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="IP_IMP_NOMINAL_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_DEC_15Y2">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000015"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="9999999999999.99"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="FECHA_INIC_ECV">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="IP_FECHA_VTO_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_FECHA">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="IP_PROXLIQ_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_FECHA">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="IP_FULTLIQ_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_FECHA">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="IP_FECHA_CANCELACION_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_FECHA">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="IP_CAPITALIZABLE_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_CHAR_01">
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
 *                                       &lt;element name="IP_RENOVABLE_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_CHAR_01">
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
 *                                       &lt;element name="IP_DURAC_IP_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="RNG_VALOR">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000015"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="9999999999999.99"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_UM">
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
 *                                       &lt;element name="IP_FREC_LIQ_IP_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="RNG_VALOR">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000015"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="9999999999999.99"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_UM">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IP_IND_LIQ_VTO_V">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="STD_CHAR_01">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="ID_TRAMEADO">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000010"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="IP_INTERES_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="INT_VALOR">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000006"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="999.999"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="INT_INCREM">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000006"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="999.999"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_REF_INT">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000004"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="IP_FRANQUICIA_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="INT_VALOR">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000006"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="999.999"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="INT_INCREM">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000006"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="999.999"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_REF_INT">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000004"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IMP_FRANQ">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000015"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="9999999999999.99"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="IP_TAE_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="INT_VALOR">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000006"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="999.999"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="IP_IND_ESPECIES_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_CHAR_01">
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
 *                                       &lt;element name="IP_DESCUENTO_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_CHAR_01">
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
 *                                       &lt;element name="IP_PRIMA_VTO_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_CHAR_01">
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
 *                                       &lt;element name="IND_PZO_CREC">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_LINEA">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="ID_GRP_PD">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_NUMRCO_MONEDA">
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
 *                             &lt;element name="IP_DATOS_ABONO_IP_V" maxOccurs="50">
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
 *                                       &lt;element name="COD_CENT_UO">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000004"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_CSB_OF">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000004"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_DIG_CR_UO">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NUM_SEC_AC">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="PCT">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                             &lt;totalDigits value="0000000006"/>
 *                                             &lt;fractionDigits value="03"/>
 *                                             &lt;maxInclusive value="999.999"/>
 *                                             &lt;minInclusive value="0.0"/>
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
    "otrconsimpscnindivtrn"
})
public class ResponseBansefi {

    @XmlElement(name = "OTR_CONS_IMPSCN_INDIV_TRN", required = true)
    protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN otrconsimpscnindivtrn;

    /**
     * Obtiene el valor de la propiedad otrconsimpscnindivtrn.
     *
     * @return
     *     possible object is
     *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN }
     *
     */
    public ResponseBansefi.OTRCONSIMPSCNINDIVTRN getOTRCONSIMPSCNINDIVTRN() {
        return otrconsimpscnindivtrn;
    }

    /**
     * Define el valor de la propiedad otrconsimpscnindivtrn.
     *
     * @param value
     *     allowed object is
     *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN }
     *
     */
    public void setOTRCONSIMPSCNINDIVTRN(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN value) {
        this.otrconsimpscnindivtrn = value;
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
     *         &lt;element name="NUMBER_OF_RECORDS">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *               &lt;maxInclusive value="9999"/>
     *               &lt;minInclusive value="0"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="MORE_DATA_IN">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *               &lt;maxInclusive value="9999"/>
     *               &lt;minInclusive value="0"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="TR_CONS_IMPSCN_INDIV_EVT">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="PSV_CCC_V">
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
     *                             &lt;element name="COD_CSB_OF">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000004"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_DIG_CR_UO">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="NUM_SEC_AC">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_INTERNO_UO">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000004"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="FECHA_CTBLE">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_PLZ_BANCARIA">
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
     *                   &lt;element name="NOMB_PDV">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000030"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IP_TITULAR_V" maxOccurs="4">
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
     *                             &lt;element name="ID_INTERNO_PE">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="NUM_RL_ORDEN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999"/>
     *                                   &lt;minInclusive value="0"/>
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
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="TR_CONS_IMPSCN_INDIV_EVT">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="COD_ECV_IMPSCN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="FECHA_VALOR">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="IMP_SDO">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                   &lt;totalDigits value="0000000015"/>
     *                                   &lt;fractionDigits value="02"/>
     *                                   &lt;maxInclusive value="9999999999999.99"/>
     *                                   &lt;minInclusive value="0.0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="IP_IMP_NOMINAL_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="STD_DEC_15Y2">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000015"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="9999999999999.99"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="FECHA_INIC_ECV">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="IP_FECHA_VTO_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="STD_FECHA">
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
     *                             &lt;element name="IP_PROXLIQ_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="STD_FECHA">
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
     *                             &lt;element name="IP_FULTLIQ_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="STD_FECHA">
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
     *                             &lt;element name="IP_FECHA_CANCELACION_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="STD_FECHA">
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
     *                             &lt;element name="IP_CAPITALIZABLE_V">
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
     *                             &lt;element name="IP_RENOVABLE_V">
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
     *                             &lt;element name="IP_DURAC_IP_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="RNG_VALOR">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000015"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="9999999999999.99"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_UM">
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
     *                             &lt;element name="IP_FREC_LIQ_IP_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="RNG_VALOR">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000015"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="9999999999999.99"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_UM">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IP_IND_LIQ_VTO_V">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="STD_CHAR_01">
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
     *                             &lt;element name="ID_TRAMEADO">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000010"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="IP_INTERES_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="INT_VALOR">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000006"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="999.999"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="INT_INCREM">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000006"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="999.999"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_REF_INT">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000004"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="IP_FRANQUICIA_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="INT_VALOR">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000006"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="999.999"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="INT_INCREM">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000006"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="999.999"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_REF_INT">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000004"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IMP_FRANQ">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000015"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="9999999999999.99"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="IP_TAE_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="INT_VALOR">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000006"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="999.999"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="IP_IND_ESPECIES_V">
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
     *                             &lt;element name="IP_DESCUENTO_V">
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
     *                             &lt;element name="IP_PRIMA_VTO_V">
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
     *                             &lt;element name="IND_PZO_CREC">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_LINEA">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="ID_GRP_PD">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_NUMRCO_MONEDA">
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
     *                   &lt;element name="IP_DATOS_ABONO_IP_V" maxOccurs="50">
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
     *                             &lt;element name="COD_CENT_UO">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000004"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_CSB_OF">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000004"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_DIG_CR_UO">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="NUM_SEC_AC">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="PCT">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                   &lt;totalDigits value="0000000006"/>
     *                                   &lt;fractionDigits value="03"/>
     *                                   &lt;maxInclusive value="999.999"/>
     *                                   &lt;minInclusive value="0.0"/>
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
        "numberofrecords",
        "moredatain",
        "trconsimpscnindivevt",
        "stdtrnmsjparmv",
        "stdtrnoparmv"
    })
    public static class OTRCONSIMPSCNINDIVTRN {

        @XmlElement(name = "RTRN_CD")
        protected int rtrncd;
        @XmlElement(name = "NUMBER_OF_RECORDS")
        protected int numberofrecords;
        @XmlElement(name = "MORE_DATA_IN")
        protected int moredatain;
        @XmlElement(name = "TR_CONS_IMPSCN_INDIV_EVT", required = true)
        protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT trconsimpscnindivevt;
        @XmlElement(name = "STD_TRN_MSJ_PARM_V", required = true)
        protected List<ResponseBansefi.OTRCONSIMPSCNINDIVTRN.STDTRNMSJPARMV> stdtrnmsjparmv;
        @XmlElement(name = "STD_TRN_O_PARM_V", required = true)
        protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.STDTRNOPARMV stdtrnoparmv;

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
        public void setRTRNCD(final int value) {
            this.rtrncd = value;
        }

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
        public void setNUMBEROFRECORDS(final int value) {
            this.numberofrecords = value;
        }

        /**
         * Obtiene el valor de la propiedad moredatain.
         *
         */
        public int getMOREDATAIN() {
            return moredatain;
        }

        /**
         * Define el valor de la propiedad moredatain.
         *
         */
        public void setMOREDATAIN(final int value) {
            this.moredatain = value;
        }

        /**
         * Obtiene el valor de la propiedad trconsimpscnindivevt.
         *
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT }
         *
         */
        public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT getTRCONSIMPSCNINDIVEVT() {
            return trconsimpscnindivevt;
        }

        /**
         * Define el valor de la propiedad trconsimpscnindivevt.
         *
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT }
         *
         */
        public void setTRCONSIMPSCNINDIVEVT(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT value) {
            this.trconsimpscnindivevt = value;
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
         * {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.STDTRNMSJPARMV }
         *
         *
         */
        public List<ResponseBansefi.OTRCONSIMPSCNINDIVTRN.STDTRNMSJPARMV> getSTDTRNMSJPARMV() {
            if (stdtrnmsjparmv == null) {
                stdtrnmsjparmv = new ArrayList<ResponseBansefi.OTRCONSIMPSCNINDIVTRN.STDTRNMSJPARMV>();
            }
            return this.stdtrnmsjparmv;
        }

        /**
         * Obtiene el valor de la propiedad stdtrnoparmv.
         *
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.STDTRNOPARMV }
         *
         */
        public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.STDTRNOPARMV getSTDTRNOPARMV() {
            return stdtrnoparmv;
        }

        /**
         * Define el valor de la propiedad stdtrnoparmv.
         *
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.STDTRNOPARMV }
         *
         */
        public void setSTDTRNOPARMV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.STDTRNOPARMV value) {
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
            public void setTEXTCODE(final int value) {
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
            public void setTEXTARG1(final String value) {
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
            public void setFECHAOPRCN(final int value) {
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
            public void setHORAOPRCN(final int value) {
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
         *         &lt;element name="PSV_CCC_V">
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
         *                   &lt;element name="COD_CSB_OF">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000004"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_DIG_CR_UO">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="NUM_SEC_AC">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_INTERNO_UO">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000004"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="FECHA_CTBLE">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_PLZ_BANCARIA">
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
         *         &lt;element name="NOMB_PDV">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000030"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IP_TITULAR_V" maxOccurs="4">
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
         *                   &lt;element name="ID_INTERNO_PE">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="NUM_RL_ORDEN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999"/>
         *                         &lt;minInclusive value="0"/>
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
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="TR_CONS_IMPSCN_INDIV_EVT">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="COD_ECV_IMPSCN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="FECHA_VALOR">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="IMP_SDO">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                         &lt;totalDigits value="0000000015"/>
         *                         &lt;fractionDigits value="02"/>
         *                         &lt;maxInclusive value="9999999999999.99"/>
         *                         &lt;minInclusive value="0.0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="IP_IMP_NOMINAL_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="STD_DEC_15Y2">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000015"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="9999999999999.99"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="FECHA_INIC_ECV">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="IP_FECHA_VTO_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="STD_FECHA">
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
         *                   &lt;element name="IP_PROXLIQ_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="STD_FECHA">
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
         *                   &lt;element name="IP_FULTLIQ_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="STD_FECHA">
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
         *                   &lt;element name="IP_FECHA_CANCELACION_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="STD_FECHA">
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
         *                   &lt;element name="IP_CAPITALIZABLE_V">
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
         *                   &lt;element name="IP_RENOVABLE_V">
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
         *                   &lt;element name="IP_DURAC_IP_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="RNG_VALOR">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000015"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="9999999999999.99"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_UM">
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
         *                   &lt;element name="IP_FREC_LIQ_IP_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="RNG_VALOR">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000015"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="9999999999999.99"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_UM">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IP_IND_LIQ_VTO_V">
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
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="ID_TRAMEADO">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000010"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="IP_INTERES_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="INT_VALOR">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000006"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="999.999"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="INT_INCREM">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000006"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="999.999"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_REF_INT">
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
         *                   &lt;element name="IP_FRANQUICIA_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="INT_VALOR">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000006"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="999.999"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="INT_INCREM">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000006"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="999.999"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_REF_INT">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000004"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IMP_FRANQ">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000015"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="9999999999999.99"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="IP_TAE_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="INT_VALOR">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000006"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="999.999"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="IP_IND_ESPECIES_V">
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
         *                   &lt;element name="IP_DESCUENTO_V">
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
         *                   &lt;element name="IP_PRIMA_VTO_V">
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
         *                   &lt;element name="IND_PZO_CREC">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_LINEA">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="ID_GRP_PD">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_NUMRCO_MONEDA">
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
         *         &lt;element name="IP_DATOS_ABONO_IP_V" maxOccurs="50">
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
         *                   &lt;element name="COD_CENT_UO">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000004"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_CSB_OF">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000004"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_DIG_CR_UO">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="NUM_SEC_AC">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="PCT">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                         &lt;totalDigits value="0000000006"/>
         *                         &lt;fractionDigits value="03"/>
         *                         &lt;maxInclusive value="999.999"/>
         *                         &lt;minInclusive value="0.0"/>
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
            "psvcccv",
            "nombpdv",
            "iptitularv",
            "trconsimpscnindivevt",
            "ipdatosabonoipv"
        })
        public static class TRCONSIMPSCNINDIVEVT {

            @XmlElement(name = "PSV_CCC_V", required = true)
            protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.PSVCCCV psvcccv;
            @XmlElement(name = "NOMB_PDV", required = true)
            protected String nombpdv;
            @XmlElement(name = "IP_TITULAR_V", required = true)
            protected List<ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.IPTITULARV> iptitularv;
            @XmlElement(name = "TR_CONS_IMPSCN_INDIV_EVT", required = true)
            protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1 trconsimpscnindivevt;
            @XmlElement(name = "IP_DATOS_ABONO_IP_V", required = true)
            protected List<ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.IPDATOSABONOIPV> ipdatosabonoipv;

            /**
             * Obtiene el valor de la propiedad psvcccv.
             *
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.PSVCCCV }
             *
             */
            public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.PSVCCCV getPSVCCCV() {
                return psvcccv;
            }

            /**
             * Define el valor de la propiedad psvcccv.
             *
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.PSVCCCV }
             *
             */
            public void setPSVCCCV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.PSVCCCV value) {
                this.psvcccv = value;
            }

            /**
             * Obtiene el valor de la propiedad nombpdv.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getNOMBPDV() {
                return nombpdv;
            }

            /**
             * Define el valor de la propiedad nombpdv.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setNOMBPDV(final String value) {
                this.nombpdv = value;
            }

            /**
             * Gets the value of the iptitularv property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the iptitularv property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getIPTITULARV().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.IPTITULARV }
             *
             *
             */
            public List<ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.IPTITULARV> getIPTITULARV() {
                if (iptitularv == null) {
                    iptitularv = new ArrayList<ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.IPTITULARV>();
                }
                return this.iptitularv;
            }

            /**
             * Obtiene el valor de la propiedad trconsimpscnindivevt.
             *
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1 }
             *
             */
            public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1 getTRCONSIMPSCNINDIVEVT() {
                return trconsimpscnindivevt;
            }

            /**
             * Define el valor de la propiedad trconsimpscnindivevt.
             *
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1 }
             *
             */
            public void setTRCONSIMPSCNINDIVEVT(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1 value) {
                this.trconsimpscnindivevt = value;
            }

            /**
             * Gets the value of the ipdatosabonoipv property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the ipdatosabonoipv property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getIPDATOSABONOIPV().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.IPDATOSABONOIPV }
             *
             *
             */
            public List<ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.IPDATOSABONOIPV> getIPDATOSABONOIPV() {
                if (ipdatosabonoipv == null) {
                    ipdatosabonoipv = new ArrayList<ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.IPDATOSABONOIPV>();
                }
                return this.ipdatosabonoipv;
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
             *         &lt;element name="COD_CENT_UO">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000004"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_CSB_OF">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000004"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_DIG_CR_UO">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="NUM_SEC_AC">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="PCT">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *               &lt;totalDigits value="0000000006"/>
             *               &lt;fractionDigits value="03"/>
             *               &lt;maxInclusive value="999.999"/>
             *               &lt;minInclusive value="0.0"/>
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
                "codcentuo",
                "codcsbof",
                "coddigcruo",
                "numsecac",
                "pct",
                "nomb50"
            })
            public static class IPDATOSABONOIPV {

                @XmlElement(name = "COD_NRBE_EN", required = true)
                protected String codnrbeen;
                @XmlElement(name = "COD_CENT_UO", required = true)
                protected String codcentuo;
                @XmlElement(name = "COD_CSB_OF", required = true)
                protected String codcsbof;
                @XmlElement(name = "COD_DIG_CR_UO", required = true)
                protected String coddigcruo;
                @XmlElement(name = "NUM_SEC_AC")
                protected long numsecac;
                @XmlElement(name = "PCT", required = true)
                protected BigDecimal pct;
                @XmlElement(name = "NOMB_50", required = true)
                protected String nomb50;

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
                public void setCODNRBEEN(final String value) {
                    this.codnrbeen = value;
                }

                /**
                 * Obtiene el valor de la propiedad codcentuo.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODCENTUO() {
                    return codcentuo;
                }

                /**
                 * Define el valor de la propiedad codcentuo.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODCENTUO(final String value) {
                    this.codcentuo = value;
                }

                /**
                 * Obtiene el valor de la propiedad codcsbof.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODCSBOF() {
                    return codcsbof;
                }

                /**
                 * Define el valor de la propiedad codcsbof.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODCSBOF(final String value) {
                    this.codcsbof = value;
                }

                /**
                 * Obtiene el valor de la propiedad coddigcruo.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODDIGCRUO() {
                    return coddigcruo;
                }

                /**
                 * Define el valor de la propiedad coddigcruo.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODDIGCRUO(final String value) {
                    this.coddigcruo = value;
                }

                /**
                 * Obtiene el valor de la propiedad numsecac.
                 *
                 */
                public long getNUMSECAC() {
                    return numsecac;
                }

                /**
                 * Define el valor de la propiedad numsecac.
                 *
                 */
                public void setNUMSECAC(final long value) {
                    this.numsecac = value;
                }

                /**
                 * Obtiene el valor de la propiedad pct.
                 *
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *
                 */
                public BigDecimal getPCT() {
                    return pct;
                }

                /**
                 * Define el valor de la propiedad pct.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *
                 */
                public void setPCT(final BigDecimal value) {
                    this.pct = value;
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
                public void setNOMB50(final String value) {
                    this.nomb50 = value;
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
             *         &lt;element name="ID_INTERNO_PE">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="NUM_RL_ORDEN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999"/>
             *               &lt;minInclusive value="0"/>
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
                "idinternope",
                "numrlorden",
                "nomb50"
            })
            public static class IPTITULARV {

                @XmlElement(name = "ID_EXT_PE", required = true)
                protected String idextpe;
                @XmlElement(name = "ID_INTERNO_PE")
                protected int idinternope;
                @XmlElement(name = "NUM_RL_ORDEN")
                protected int numrlorden;
                @XmlElement(name = "NOMB_50", required = true)
                protected String nomb50;

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
                public void setIDEXTPE(final String value) {
                    this.idextpe = value;
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
                public void setIDINTERNOPE(final int value) {
                    this.idinternope = value;
                }

                /**
                 * Obtiene el valor de la propiedad numrlorden.
                 *
                 */
                public int getNUMRLORDEN() {
                    return numrlorden;
                }

                /**
                 * Define el valor de la propiedad numrlorden.
                 *
                 */
                public void setNUMRLORDEN(final int value) {
                    this.numrlorden = value;
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
                public void setNOMB50(final String value) {
                    this.nomb50 = value;
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
             *         &lt;element name="COD_CSB_OF">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000004"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_DIG_CR_UO">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="NUM_SEC_AC">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_INTERNO_UO">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000004"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="FECHA_CTBLE">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_PLZ_BANCARIA">
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
                "codnrbeen",
                "codcsbof",
                "coddigcruo",
                "numsecac",
                "codinternouo",
                "fechactble",
                "codplzbancaria"
            })
            public static class PSVCCCV {

                @XmlElement(name = "COD_NRBE_EN", required = true)
                protected String codnrbeen;
                @XmlElement(name = "COD_CSB_OF", required = true)
                protected String codcsbof;
                @XmlElement(name = "COD_DIG_CR_UO", required = true)
                protected String coddigcruo;
                @XmlElement(name = "NUM_SEC_AC")
                protected long numsecac;
                @XmlElement(name = "COD_INTERNO_UO", required = true)
                protected String codinternouo;
                @XmlElement(name = "FECHA_CTBLE")
                protected int fechactble;
                @XmlElement(name = "COD_PLZ_BANCARIA", required = true)
                protected String codplzbancaria;

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
                public void setCODNRBEEN(final String value) {
                    this.codnrbeen = value;
                }

                /**
                 * Obtiene el valor de la propiedad codcsbof.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODCSBOF() {
                    return codcsbof;
                }

                /**
                 * Define el valor de la propiedad codcsbof.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODCSBOF(final String value) {
                    this.codcsbof = value;
                }

                /**
                 * Obtiene el valor de la propiedad coddigcruo.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODDIGCRUO() {
                    return coddigcruo;
                }

                /**
                 * Define el valor de la propiedad coddigcruo.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODDIGCRUO(final String value) {
                    this.coddigcruo = value;
                }

                /**
                 * Obtiene el valor de la propiedad numsecac.
                 *
                 */
                public long getNUMSECAC() {
                    return numsecac;
                }

                /**
                 * Define el valor de la propiedad numsecac.
                 *
                 */
                public void setNUMSECAC(final long value) {
                    this.numsecac = value;
                }

                /**
                 * Obtiene el valor de la propiedad codinternouo.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODINTERNOUO() {
                    return codinternouo;
                }

                /**
                 * Define el valor de la propiedad codinternouo.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODINTERNOUO(final String value) {
                    this.codinternouo = value;
                }

                /**
                 * Obtiene el valor de la propiedad fechactble.
                 *
                 */
                public int getFECHACTBLE() {
                    return fechactble;
                }

                /**
                 * Define el valor de la propiedad fechactble.
                 *
                 */
                public void setFECHACTBLE(final int value) {
                    this.fechactble = value;
                }

                /**
                 * Obtiene el valor de la propiedad codplzbancaria.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODPLZBANCARIA() {
                    return codplzbancaria;
                }

                /**
                 * Define el valor de la propiedad codplzbancaria.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODPLZBANCARIA(final String value) {
                    this.codplzbancaria = value;
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
             *         &lt;element name="COD_ECV_IMPSCN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="FECHA_VALOR">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="IMP_SDO">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *               &lt;totalDigits value="0000000015"/>
             *               &lt;fractionDigits value="02"/>
             *               &lt;maxInclusive value="9999999999999.99"/>
             *               &lt;minInclusive value="0.0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="IP_IMP_NOMINAL_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="STD_DEC_15Y2">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000015"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="9999999999999.99"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="FECHA_INIC_ECV">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="IP_FECHA_VTO_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="STD_FECHA">
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
             *         &lt;element name="IP_PROXLIQ_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="STD_FECHA">
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
             *         &lt;element name="IP_FULTLIQ_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="STD_FECHA">
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
             *         &lt;element name="IP_FECHA_CANCELACION_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="STD_FECHA">
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
             *         &lt;element name="IP_CAPITALIZABLE_V">
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
             *         &lt;element name="IP_RENOVABLE_V">
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
             *         &lt;element name="IP_DURAC_IP_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="RNG_VALOR">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000015"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="9999999999999.99"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_UM">
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
             *         &lt;element name="IP_FREC_LIQ_IP_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="RNG_VALOR">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000015"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="9999999999999.99"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_UM">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IP_IND_LIQ_VTO_V">
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
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="ID_TRAMEADO">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000010"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="IP_INTERES_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="INT_VALOR">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000006"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="999.999"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="INT_INCREM">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000006"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="999.999"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_REF_INT">
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
             *         &lt;element name="IP_FRANQUICIA_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="INT_VALOR">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000006"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="999.999"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="INT_INCREM">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000006"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="999.999"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_REF_INT">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000004"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IMP_FRANQ">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000015"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="9999999999999.99"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="IP_TAE_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="INT_VALOR">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000006"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="999.999"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="IP_IND_ESPECIES_V">
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
             *         &lt;element name="IP_DESCUENTO_V">
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
             *         &lt;element name="IP_PRIMA_VTO_V">
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
             *         &lt;element name="IND_PZO_CREC">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_LINEA">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="ID_GRP_PD">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_NUMRCO_MONEDA">
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
                "codecvimpscn",
                "fechavalor",
                "impsdo",
                "ipimpnominalv",
                "fechainicecv",
                "ipfechavtov",
                "ipproxliqv",
                "ipfultliqv",
                "ipfechacancelacionv",
                "ipcapitalizablev",
                "iprenovablev",
                "ipduracipv",
                "ipfrecliqipv",
                "idtrameado",
                "ipinteresv",
                "ipfranquiciav",
                "iptaev",
                "ipindespeciesv",
                "ipdescuentov",
                "ipprimavtov",
                "indpzocrec",
                "codlinea",
                "idgrppd",
                "codnumrcomoneda"
            })
            public static class TRCONSIMPSCNINDIVEVT1 {

                @XmlElement(name = "COD_ECV_IMPSCN", required = true)
                protected String codecvimpscn;
                @XmlElement(name = "FECHA_VALOR")
                protected int fechavalor;
                @XmlElement(name = "IMP_SDO", required = true)
                protected BigDecimal impsdo;
                @XmlElement(name = "IP_IMP_NOMINAL_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPIMPNOMINALV ipimpnominalv;
                @XmlElement(name = "FECHA_INIC_ECV")
                protected int fechainicecv;
                @XmlElement(name = "IP_FECHA_VTO_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFECHAVTOV ipfechavtov;
                @XmlElement(name = "IP_PROXLIQ_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPPROXLIQV ipproxliqv;
                @XmlElement(name = "IP_FULTLIQ_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFULTLIQV ipfultliqv;
                @XmlElement(name = "IP_FECHA_CANCELACION_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFECHACANCELACIONV ipfechacancelacionv;
                @XmlElement(name = "IP_CAPITALIZABLE_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPCAPITALIZABLEV ipcapitalizablev;
                @XmlElement(name = "IP_RENOVABLE_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPRENOVABLEV iprenovablev;
                @XmlElement(name = "IP_DURAC_IP_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPDURACIPV ipduracipv;
                @XmlElement(name = "IP_FREC_LIQ_IP_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRECLIQIPV ipfrecliqipv;
                @XmlElement(name = "ID_TRAMEADO", required = true)
                protected String idtrameado;
                @XmlElement(name = "IP_INTERES_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPINTERESV ipinteresv;
                @XmlElement(name = "IP_FRANQUICIA_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRANQUICIAV ipfranquiciav;
                @XmlElement(name = "IP_TAE_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPTAEV iptaev;
                @XmlElement(name = "IP_IND_ESPECIES_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPINDESPECIESV ipindespeciesv;
                @XmlElement(name = "IP_DESCUENTO_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPDESCUENTOV ipdescuentov;
                @XmlElement(name = "IP_PRIMA_VTO_V", required = true)
                protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPPRIMAVTOV ipprimavtov;
                @XmlElement(name = "IND_PZO_CREC", required = true)
                protected String indpzocrec;
                @XmlElement(name = "COD_LINEA", required = true)
                protected String codlinea;
                @XmlElement(name = "ID_GRP_PD", required = true)
                protected String idgrppd;
                @XmlElement(name = "COD_NUMRCO_MONEDA", required = true)
                protected String codnumrcomoneda;

                /**
                 * Obtiene el valor de la propiedad codecvimpscn.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODECVIMPSCN() {
                    return codecvimpscn;
                }

                /**
                 * Define el valor de la propiedad codecvimpscn.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODECVIMPSCN(final String value) {
                    this.codecvimpscn = value;
                }

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
                public void setFECHAVALOR(final int value) {
                    this.fechavalor = value;
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
                public void setIMPSDO(final BigDecimal value) {
                    this.impsdo = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipimpnominalv.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPIMPNOMINALV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPIMPNOMINALV getIPIMPNOMINALV() {
                    return ipimpnominalv;
                }

                /**
                 * Define el valor de la propiedad ipimpnominalv.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPIMPNOMINALV }
                 *
                 */
                public void setIPIMPNOMINALV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPIMPNOMINALV value) {
                    this.ipimpnominalv = value;
                }

                /**
                 * Obtiene el valor de la propiedad fechainicecv.
                 *
                 */
                public int getFECHAINICECV() {
                    return fechainicecv;
                }

                /**
                 * Define el valor de la propiedad fechainicecv.
                 *
                 */
                public void setFECHAINICECV(final int value) {
                    this.fechainicecv = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipfechavtov.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFECHAVTOV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFECHAVTOV getIPFECHAVTOV() {
                    return ipfechavtov;
                }

                /**
                 * Define el valor de la propiedad ipfechavtov.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFECHAVTOV }
                 *
                 */
                public void setIPFECHAVTOV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFECHAVTOV value) {
                    this.ipfechavtov = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipproxliqv.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPPROXLIQV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPPROXLIQV getIPPROXLIQV() {
                    return ipproxliqv;
                }

                /**
                 * Define el valor de la propiedad ipproxliqv.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPPROXLIQV }
                 *
                 */
                public void setIPPROXLIQV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPPROXLIQV value) {
                    this.ipproxliqv = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipfultliqv.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFULTLIQV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFULTLIQV getIPFULTLIQV() {
                    return ipfultliqv;
                }

                /**
                 * Define el valor de la propiedad ipfultliqv.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFULTLIQV }
                 *
                 */
                public void setIPFULTLIQV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFULTLIQV value) {
                    this.ipfultliqv = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipfechacancelacionv.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFECHACANCELACIONV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFECHACANCELACIONV getIPFECHACANCELACIONV() {
                    return ipfechacancelacionv;
                }

                /**
                 * Define el valor de la propiedad ipfechacancelacionv.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFECHACANCELACIONV }
                 *
                 */
                public void setIPFECHACANCELACIONV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFECHACANCELACIONV value) {
                    this.ipfechacancelacionv = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipcapitalizablev.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPCAPITALIZABLEV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPCAPITALIZABLEV getIPCAPITALIZABLEV() {
                    return ipcapitalizablev;
                }

                /**
                 * Define el valor de la propiedad ipcapitalizablev.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPCAPITALIZABLEV }
                 *
                 */
                public void setIPCAPITALIZABLEV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPCAPITALIZABLEV value) {
                    this.ipcapitalizablev = value;
                }

                /**
                 * Obtiene el valor de la propiedad iprenovablev.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPRENOVABLEV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPRENOVABLEV getIPRENOVABLEV() {
                    return iprenovablev;
                }

                /**
                 * Define el valor de la propiedad iprenovablev.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPRENOVABLEV }
                 *
                 */
                public void setIPRENOVABLEV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPRENOVABLEV value) {
                    this.iprenovablev = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipduracipv.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPDURACIPV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPDURACIPV getIPDURACIPV() {
                    return ipduracipv;
                }

                /**
                 * Define el valor de la propiedad ipduracipv.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPDURACIPV }
                 *
                 */
                public void setIPDURACIPV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPDURACIPV value) {
                    this.ipduracipv = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipfrecliqipv.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRECLIQIPV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRECLIQIPV getIPFRECLIQIPV() {
                    return ipfrecliqipv;
                }

                /**
                 * Define el valor de la propiedad ipfrecliqipv.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRECLIQIPV }
                 *
                 */
                public void setIPFRECLIQIPV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRECLIQIPV value) {
                    this.ipfrecliqipv = value;
                }

                /**
                 * Obtiene el valor de la propiedad idtrameado.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getIDTRAMEADO() {
                    return idtrameado;
                }

                /**
                 * Define el valor de la propiedad idtrameado.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setIDTRAMEADO(final String value) {
                    this.idtrameado = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipinteresv.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPINTERESV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPINTERESV getIPINTERESV() {
                    return ipinteresv;
                }

                /**
                 * Define el valor de la propiedad ipinteresv.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPINTERESV }
                 *
                 */
                public void setIPINTERESV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPINTERESV value) {
                    this.ipinteresv = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipfranquiciav.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRANQUICIAV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRANQUICIAV getIPFRANQUICIAV() {
                    return ipfranquiciav;
                }

                /**
                 * Define el valor de la propiedad ipfranquiciav.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRANQUICIAV }
                 *
                 */
                public void setIPFRANQUICIAV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRANQUICIAV value) {
                    this.ipfranquiciav = value;
                }

                /**
                 * Obtiene el valor de la propiedad iptaev.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPTAEV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPTAEV getIPTAEV() {
                    return iptaev;
                }

                /**
                 * Define el valor de la propiedad iptaev.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPTAEV }
                 *
                 */
                public void setIPTAEV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPTAEV value) {
                    this.iptaev = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipindespeciesv.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPINDESPECIESV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPINDESPECIESV getIPINDESPECIESV() {
                    return ipindespeciesv;
                }

                /**
                 * Define el valor de la propiedad ipindespeciesv.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPINDESPECIESV }
                 *
                 */
                public void setIPINDESPECIESV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPINDESPECIESV value) {
                    this.ipindespeciesv = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipdescuentov.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPDESCUENTOV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPDESCUENTOV getIPDESCUENTOV() {
                    return ipdescuentov;
                }

                /**
                 * Define el valor de la propiedad ipdescuentov.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPDESCUENTOV }
                 *
                 */
                public void setIPDESCUENTOV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPDESCUENTOV value) {
                    this.ipdescuentov = value;
                }

                /**
                 * Obtiene el valor de la propiedad ipprimavtov.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPPRIMAVTOV }
                 *
                 */
                public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPPRIMAVTOV getIPPRIMAVTOV() {
                    return ipprimavtov;
                }

                /**
                 * Define el valor de la propiedad ipprimavtov.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPPRIMAVTOV }
                 *
                 */
                public void setIPPRIMAVTOV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPPRIMAVTOV value) {
                    this.ipprimavtov = value;
                }

                /**
                 * Obtiene el valor de la propiedad indpzocrec.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getINDPZOCREC() {
                    return indpzocrec;
                }

                /**
                 * Define el valor de la propiedad indpzocrec.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setINDPZOCREC(final String value) {
                    this.indpzocrec = value;
                }

                /**
                 * Obtiene el valor de la propiedad codlinea.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODLINEA() {
                    return codlinea;
                }

                /**
                 * Define el valor de la propiedad codlinea.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODLINEA(final String value) {
                    this.codlinea = value;
                }

                /**
                 * Obtiene el valor de la propiedad idgrppd.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getIDGRPPD() {
                    return idgrppd;
                }

                /**
                 * Define el valor de la propiedad idgrppd.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setIDGRPPD(final String value) {
                    this.idgrppd = value;
                }

                /**
                 * Obtiene el valor de la propiedad codnumrcomoneda.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODNUMRCOMONEDA() {
                    return codnumrcomoneda;
                }

                /**
                 * Define el valor de la propiedad codnumrcomoneda.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODNUMRCOMONEDA(final String value) {
                    this.codnumrcomoneda = value;
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
                public static class IPCAPITALIZABLEV {

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
                    public void setSTDCHAR01(final String value) {
                        this.stdchar01 = value;
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
                public static class IPDESCUENTOV {

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
                    public void setSTDCHAR01(final String value) {
                        this.stdchar01 = value;
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
                 *         &lt;element name="RNG_VALOR">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000015"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="9999999999999.99"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_UM">
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
                    "rngvalor",
                    "codum"
                })
                public static class IPDURACIPV {

                    @XmlElement(name = "RNG_VALOR", required = true)
                    protected BigDecimal rngvalor;
                    @XmlElement(name = "COD_UM", required = true)
                    protected String codum;

                    /**
                     * Obtiene el valor de la propiedad rngvalor.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getRNGVALOR() {
                        return rngvalor;
                    }

                    /**
                     * Define el valor de la propiedad rngvalor.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setRNGVALOR(final BigDecimal value) {
                        this.rngvalor = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codum.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODUM() {
                        return codum;
                    }

                    /**
                     * Define el valor de la propiedad codum.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODUM(final String value) {
                        this.codum = value;
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
                 *         &lt;element name="STD_FECHA">
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
                    "stdfecha"
                })
                public static class IPFECHACANCELACIONV {

                    @XmlElement(name = "STD_FECHA")
                    protected int stdfecha;

                    /**
                     * Obtiene el valor de la propiedad stdfecha.
                     *
                     */
                    public int getSTDFECHA() {
                        return stdfecha;
                    }

                    /**
                     * Define el valor de la propiedad stdfecha.
                     *
                     */
                    public void setSTDFECHA(final int value) {
                        this.stdfecha = value;
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
                 *         &lt;element name="STD_FECHA">
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
                    "stdfecha"
                })
                public static class IPFECHAVTOV {

                    @XmlElement(name = "STD_FECHA")
                    protected int stdfecha;

                    /**
                     * Obtiene el valor de la propiedad stdfecha.
                     *
                     */
                    public int getSTDFECHA() {
                        return stdfecha;
                    }

                    /**
                     * Define el valor de la propiedad stdfecha.
                     *
                     */
                    public void setSTDFECHA(final int value) {
                        this.stdfecha = value;
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
                 *         &lt;element name="INT_VALOR">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000006"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="999.999"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="INT_INCREM">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000006"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="999.999"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_REF_INT">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000004"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IMP_FRANQ">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000015"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="9999999999999.99"/>
                 *               &lt;minInclusive value="0.0"/>
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
                    "intvalor",
                    "intincrem",
                    "codrefint",
                    "impfranq"
                })
                public static class IPFRANQUICIAV {

                    @XmlElement(name = "INT_VALOR", required = true)
                    protected BigDecimal intvalor;
                    @XmlElement(name = "INT_INCREM", required = true)
                    protected BigDecimal intincrem;
                    @XmlElement(name = "COD_REF_INT", required = true)
                    protected String codrefint;
                    @XmlElement(name = "IMP_FRANQ", required = true)
                    protected BigDecimal impfranq;

                    /**
                     * Obtiene el valor de la propiedad intvalor.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getINTVALOR() {
                        return intvalor;
                    }

                    /**
                     * Define el valor de la propiedad intvalor.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setINTVALOR(final BigDecimal value) {
                        this.intvalor = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad intincrem.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getINTINCREM() {
                        return intincrem;
                    }

                    /**
                     * Define el valor de la propiedad intincrem.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setINTINCREM(final BigDecimal value) {
                        this.intincrem = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codrefint.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODREFINT() {
                        return codrefint;
                    }

                    /**
                     * Define el valor de la propiedad codrefint.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODREFINT(final String value) {
                        this.codrefint = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad impfranq.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getIMPFRANQ() {
                        return impfranq;
                    }

                    /**
                     * Define el valor de la propiedad impfranq.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setIMPFRANQ(final BigDecimal value) {
                        this.impfranq = value;
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
                 *         &lt;element name="RNG_VALOR">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000015"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="9999999999999.99"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_UM">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IP_IND_LIQ_VTO_V">
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
                    "rngvalor",
                    "codum",
                    "ipindliqvtov"
                })
                public static class IPFRECLIQIPV {

                    @XmlElement(name = "RNG_VALOR", required = true)
                    protected BigDecimal rngvalor;
                    @XmlElement(name = "COD_UM", required = true)
                    protected String codum;
                    @XmlElement(name = "IP_IND_LIQ_VTO_V", required = true)
                    protected ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRECLIQIPV.IPINDLIQVTOV ipindliqvtov;

                    /**
                     * Obtiene el valor de la propiedad rngvalor.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getRNGVALOR() {
                        return rngvalor;
                    }

                    /**
                     * Define el valor de la propiedad rngvalor.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setRNGVALOR(final BigDecimal value) {
                        this.rngvalor = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codum.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODUM() {
                        return codum;
                    }

                    /**
                     * Define el valor de la propiedad codum.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODUM(final String value) {
                        this.codum = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad ipindliqvtov.
                     *
                     * @return
                     *     possible object is
                     *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRECLIQIPV.IPINDLIQVTOV }
                     *
                     */
                    public ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRECLIQIPV.IPINDLIQVTOV getIPINDLIQVTOV() {
                        return ipindliqvtov;
                    }

                    /**
                     * Define el valor de la propiedad ipindliqvtov.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRECLIQIPV.IPINDLIQVTOV }
                     *
                     */
                    public void setIPINDLIQVTOV(final ResponseBansefi.OTRCONSIMPSCNINDIVTRN.TRCONSIMPSCNINDIVEVT.TRCONSIMPSCNINDIVEVT1.IPFRECLIQIPV.IPINDLIQVTOV value) {
                        this.ipindliqvtov = value;
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
                    public static class IPINDLIQVTOV {

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
                        public void setSTDCHAR01(final String value) {
                            this.stdchar01 = value;
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
                 *         &lt;element name="STD_FECHA">
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
                    "stdfecha"
                })
                public static class IPFULTLIQV {

                    @XmlElement(name = "STD_FECHA")
                    protected int stdfecha;

                    /**
                     * Obtiene el valor de la propiedad stdfecha.
                     *
                     */
                    public int getSTDFECHA() {
                        return stdfecha;
                    }

                    /**
                     * Define el valor de la propiedad stdfecha.
                     *
                     */
                    public void setSTDFECHA(final int value) {
                        this.stdfecha = value;
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
                 *         &lt;element name="STD_DEC_15Y2">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000015"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="9999999999999.99"/>
                 *               &lt;minInclusive value="0.0"/>
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
                    "stddec15Y2"
                })
                public static class IPIMPNOMINALV {

                    @XmlElement(name = "STD_DEC_15Y2", required = true)
                    protected BigDecimal stddec15Y2;

                    /**
                     * Obtiene el valor de la propiedad stddec15Y2.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getSTDDEC15Y2() {
                        return stddec15Y2;
                    }

                    /**
                     * Define el valor de la propiedad stddec15Y2.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setSTDDEC15Y2(final BigDecimal value) {
                        this.stddec15Y2 = value;
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
                public static class IPINDESPECIESV {

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
                    public void setSTDCHAR01(final String value) {
                        this.stdchar01 = value;
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
                 *         &lt;element name="INT_VALOR">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000006"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="999.999"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="INT_INCREM">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000006"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="999.999"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_REF_INT">
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
                    "intvalor",
                    "intincrem",
                    "codrefint"
                })
                public static class IPINTERESV {

                    @XmlElement(name = "INT_VALOR", required = true)
                    protected BigDecimal intvalor;
                    @XmlElement(name = "INT_INCREM", required = true)
                    protected BigDecimal intincrem;
                    @XmlElement(name = "COD_REF_INT", required = true)
                    protected String codrefint;

                    /**
                     * Obtiene el valor de la propiedad intvalor.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getINTVALOR() {
                        return intvalor;
                    }

                    /**
                     * Define el valor de la propiedad intvalor.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setINTVALOR(final BigDecimal value) {
                        this.intvalor = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad intincrem.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getINTINCREM() {
                        return intincrem;
                    }

                    /**
                     * Define el valor de la propiedad intincrem.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setINTINCREM(final BigDecimal value) {
                        this.intincrem = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codrefint.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODREFINT() {
                        return codrefint;
                    }

                    /**
                     * Define el valor de la propiedad codrefint.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODREFINT(final String value) {
                        this.codrefint = value;
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
                public static class IPPRIMAVTOV {

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
                    public void setSTDCHAR01(final String value) {
                        this.stdchar01 = value;
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
                 *         &lt;element name="STD_FECHA">
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
                    "stdfecha"
                })
                public static class IPPROXLIQV {

                    @XmlElement(name = "STD_FECHA")
                    protected int stdfecha;

                    /**
                     * Obtiene el valor de la propiedad stdfecha.
                     *
                     */
                    public int getSTDFECHA() {
                        return stdfecha;
                    }

                    /**
                     * Define el valor de la propiedad stdfecha.
                     *
                     */
                    public void setSTDFECHA(final int value) {
                        this.stdfecha = value;
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
                public static class IPRENOVABLEV {

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
                    public void setSTDCHAR01(final String value) {
                        this.stdchar01 = value;
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
                 *         &lt;element name="INT_VALOR">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000006"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="999.999"/>
                 *               &lt;minInclusive value="0.0"/>
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
                    "intvalor"
                })
                public static class IPTAEV {

                    @XmlElement(name = "INT_VALOR", required = true)
                    protected BigDecimal intvalor;

                    /**
                     * Obtiene el valor de la propiedad intvalor.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getINTVALOR() {
                        return intvalor;
                    }

                    /**
                     * Define el valor de la propiedad intvalor.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setINTVALOR(final BigDecimal value) {
                        this.intvalor = value;
                    }

                }

            }

        }

    }

}
