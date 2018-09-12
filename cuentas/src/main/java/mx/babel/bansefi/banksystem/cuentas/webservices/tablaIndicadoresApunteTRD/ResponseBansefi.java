
package mx.babel.bansefi.banksystem.cuentas.webservices.tablaIndicadoresApunteTRD;

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
 *         &lt;element name="OAF_APUNTE_BASCP_TRN_O">
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
 *                   &lt;element name="AF_APUNTE_BASCP_EVT_Z">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AF_ORGN_APNTE_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="COD_ORGN_APNTE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="ID_ORGN_APNTE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000040"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="AF_APUNTE_BAS_CP">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="NOMB_PDV">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000030"/>
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
 *                                       &lt;element name="NUM_SUBAC">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_CTA">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="DESCR_CTA">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000020"/>
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
 *                                       &lt;element name="DESCR_MONEDA">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000040"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NUM_SEC">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999999"/>
 *                                             &lt;minInclusive value="0"/>
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
 *                                       &lt;element name="FECHA_VALOR">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="IMP_APNTE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                             &lt;totalDigits value="0000000015"/>
 *                                             &lt;fractionDigits value="02"/>
 *                                             &lt;maxInclusive value="9999999999999.99"/>
 *                                             &lt;minInclusive value="0.0"/>
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
 *                                       &lt;element name="COD_ORIGEN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000008"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="DESCR_ORIGEN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000060"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="CONCPT_APNTE_LEN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="CONCPT_APNTE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000100"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="DESCR_AF_RL_AF">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000018"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="AF_APUNTE_BAS_CP_LP" maxOccurs="10">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="DESCR_DOM_IND">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000020"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_VALOR_DOM_IND">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_INDIC">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="DESCR_INDIC">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000020"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="TR_PETCN_OBTE_AF_EVT_Z">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="AF_APNTE_E">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="COD_NRBE_EN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000004"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_CENT_UO">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000004"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="PRPDAD_CTA">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="NUM_SEC_AC">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="9999999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="NUM_SUBAC">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="9999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="ID_EXP_RECLAM">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_CTA">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000002"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_NUMRCO_MONEDA">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000003"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="NUM_SEC">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="9999999"/>
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
 *                                                 &lt;element name="IMP_APNTE">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000015"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="9999999999999.99"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FECHA_CTBLE">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FECHA_VALOR">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_1">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_2">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_3">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_4">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_5">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_6">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_7">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_8">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_9">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_10">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_ORGN_APNTE">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000002"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="ID_ORGN_APNTE">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000040"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="CONCPT_APNTE_LEN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="9999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="CONCPT_APNTE">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000100"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_ORIGEN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000008"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_INTERNO_UO">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000004"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_LINEA">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000002"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="ID_GRP_PD">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000002"/>
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
    "oafapuntebascptrno"
})
public class ResponseBansefi {

    @XmlElement(name = "OAF_APUNTE_BASCP_TRN_O", required = true)
    protected ResponseBansefi.OAFAPUNTEBASCPTRNO oafapuntebascptrno;

    /**
     * Obtiene el valor de la propiedad oafapuntebascptrno.
     * 
     * @return
     *     possible object is
     *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO }
     *     
     */
    public ResponseBansefi.OAFAPUNTEBASCPTRNO getOAFAPUNTEBASCPTRNO() {
        return oafapuntebascptrno;
    }

    /**
     * Define el valor de la propiedad oafapuntebascptrno.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO }
     *     
     */
    public void setOAFAPUNTEBASCPTRNO(ResponseBansefi.OAFAPUNTEBASCPTRNO value) {
        this.oafapuntebascptrno = value;
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
     *         &lt;element name="AF_APUNTE_BASCP_EVT_Z">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="AF_ORGN_APNTE_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="COD_ORGN_APNTE">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="ID_ORGN_APNTE">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000040"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="AF_APUNTE_BAS_CP">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="NOMB_PDV">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000030"/>
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
     *                             &lt;element name="NUM_SUBAC">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_CTA">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="DESCR_CTA">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000020"/>
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
     *                             &lt;element name="DESCR_MONEDA">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000040"/>
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
     *                             &lt;element name="FECHA_CTBLE">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
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
     *                             &lt;element name="IMP_APNTE">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                   &lt;totalDigits value="0000000015"/>
     *                                   &lt;fractionDigits value="02"/>
     *                                   &lt;maxInclusive value="9999999999999.99"/>
     *                                   &lt;minInclusive value="0.0"/>
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
     *                             &lt;element name="COD_ORIGEN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000008"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="DESCR_ORIGEN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000060"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="CONCPT_APNTE_LEN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="CONCPT_APNTE">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000100"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="DESCR_AF_RL_AF">
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
     *                   &lt;element name="AF_APUNTE_BAS_CP_LP" maxOccurs="10">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="DESCR_DOM_IND">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000020"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_VALOR_DOM_IND">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_INDIC">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="DESCR_INDIC">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000020"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="TR_PETCN_OBTE_AF_EVT_Z">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="AF_APNTE_E">
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
     *                                       &lt;element name="PRPDAD_CTA">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
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
     *                                       &lt;element name="NUM_SUBAC">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="9999999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="ID_EXP_RECLAM">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="99"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_CTA">
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
     *                                       &lt;element name="NUM_SEC">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="9999999"/>
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
     *                                       &lt;element name="IMP_APNTE">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000015"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="9999999999999.99"/>
     *                                             &lt;minInclusive value="0.0"/>
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
     *                                       &lt;element name="FECHA_VALOR">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="99999999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_1">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_2">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_3">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_4">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_5">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_6">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_7">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_8">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_9">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_10">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_ORGN_APNTE">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000002"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="ID_ORGN_APNTE">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000040"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="CONCPT_APNTE_LEN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="9999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="CONCPT_APNTE">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000100"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_ORIGEN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000008"/>
     *                                             &lt;whiteSpace value="preserve"/>
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
        "afapuntebascpevtz",
        "stdtrnmsjparmv",
        "stdtrnoparmv"
    })
    public static class OAFAPUNTEBASCPTRNO {

        @XmlElement(name = "RTRN_CD")
        protected int rtrncd;
        @XmlElement(name = "NUMBER_OF_RECORDS")
        protected int numberofrecords;
        @XmlElement(name = "MORE_DATA_IN")
        protected int moredatain;
        @XmlElement(name = "AF_APUNTE_BASCP_EVT_Z", required = true)
        protected ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ afapuntebascpevtz;
        @XmlElement(name = "STD_TRN_MSJ_PARM_V", required = true)
        protected List<ResponseBansefi.OAFAPUNTEBASCPTRNO.STDTRNMSJPARMV> stdtrnmsjparmv;
        @XmlElement(name = "STD_TRN_O_PARM_V", required = true)
        protected ResponseBansefi.OAFAPUNTEBASCPTRNO.STDTRNOPARMV stdtrnoparmv;

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
        public void setMOREDATAIN(int value) {
            this.moredatain = value;
        }

        /**
         * Obtiene el valor de la propiedad afapuntebascpevtz.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ }
         *     
         */
        public ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ getAFAPUNTEBASCPEVTZ() {
            return afapuntebascpevtz;
        }

        /**
         * Define el valor de la propiedad afapuntebascpevtz.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ }
         *     
         */
        public void setAFAPUNTEBASCPEVTZ(ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ value) {
            this.afapuntebascpevtz = value;
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
         * {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.STDTRNMSJPARMV }
         * 
         * 
         */
        public List<ResponseBansefi.OAFAPUNTEBASCPTRNO.STDTRNMSJPARMV> getSTDTRNMSJPARMV() {
            if (stdtrnmsjparmv == null) {
                stdtrnmsjparmv = new ArrayList<ResponseBansefi.OAFAPUNTEBASCPTRNO.STDTRNMSJPARMV>();
            }
            return this.stdtrnmsjparmv;
        }

        /**
         * Obtiene el valor de la propiedad stdtrnoparmv.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.STDTRNOPARMV }
         *     
         */
        public ResponseBansefi.OAFAPUNTEBASCPTRNO.STDTRNOPARMV getSTDTRNOPARMV() {
            return stdtrnoparmv;
        }

        /**
         * Define el valor de la propiedad stdtrnoparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.STDTRNOPARMV }
         *     
         */
        public void setSTDTRNOPARMV(ResponseBansefi.OAFAPUNTEBASCPTRNO.STDTRNOPARMV value) {
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
         *         &lt;element name="AF_ORGN_APNTE_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="COD_ORGN_APNTE">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="ID_ORGN_APNTE">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000040"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="AF_APUNTE_BAS_CP">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="NOMB_PDV">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000030"/>
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
         *                   &lt;element name="NUM_SUBAC">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_CTA">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="DESCR_CTA">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000020"/>
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
         *                   &lt;element name="DESCR_MONEDA">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000040"/>
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
         *                   &lt;element name="FECHA_CTBLE">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
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
         *                   &lt;element name="IMP_APNTE">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                         &lt;totalDigits value="0000000015"/>
         *                         &lt;fractionDigits value="02"/>
         *                         &lt;maxInclusive value="9999999999999.99"/>
         *                         &lt;minInclusive value="0.0"/>
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
         *                   &lt;element name="COD_ORIGEN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000008"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="DESCR_ORIGEN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000060"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="CONCPT_APNTE_LEN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="CONCPT_APNTE">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000100"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="DESCR_AF_RL_AF">
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
         *         &lt;element name="AF_APUNTE_BAS_CP_LP" maxOccurs="10">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="DESCR_DOM_IND">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000020"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_VALOR_DOM_IND">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_INDIC">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="DESCR_INDIC">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000020"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="TR_PETCN_OBTE_AF_EVT_Z">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="AF_APNTE_E">
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
         *                             &lt;element name="PRPDAD_CTA">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
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
         *                             &lt;element name="NUM_SUBAC">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="9999999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="ID_EXP_RECLAM">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="99"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_CTA">
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
         *                             &lt;element name="NUM_SEC">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="9999999"/>
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
         *                             &lt;element name="IMP_APNTE">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000015"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="9999999999999.99"/>
         *                                   &lt;minInclusive value="0.0"/>
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
         *                             &lt;element name="FECHA_VALOR">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="99999999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_1">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_2">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_3">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_4">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_5">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_6">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_7">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_8">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_9">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_10">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_ORGN_APNTE">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000002"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="ID_ORGN_APNTE">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000040"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="CONCPT_APNTE_LEN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="9999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="CONCPT_APNTE">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000100"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_ORIGEN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000008"/>
         *                                   &lt;whiteSpace value="preserve"/>
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
            "aforgnapntev",
            "afapuntebascp",
            "afapuntebascplp",
            "trpetcnobteafevtz"
        })
        public static class AFAPUNTEBASCPEVTZ {

            @XmlElement(name = "AF_ORGN_APNTE_V", required = true)
            protected ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFORGNAPNTEV aforgnapntev;
            @XmlElement(name = "AF_APUNTE_BAS_CP", required = true)
            protected ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFAPUNTEBASCP afapuntebascp;
            @XmlElement(name = "AF_APUNTE_BAS_CP_LP", required = true)
            protected List<ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFAPUNTEBASCPLP> afapuntebascplp;
            @XmlElement(name = "TR_PETCN_OBTE_AF_EVT_Z", required = true)
            protected ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.TRPETCNOBTEAFEVTZ trpetcnobteafevtz;

            /**
             * Obtiene el valor de la propiedad aforgnapntev.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFORGNAPNTEV }
             *     
             */
            public ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFORGNAPNTEV getAFORGNAPNTEV() {
                return aforgnapntev;
            }

            /**
             * Define el valor de la propiedad aforgnapntev.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFORGNAPNTEV }
             *     
             */
            public void setAFORGNAPNTEV(ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFORGNAPNTEV value) {
                this.aforgnapntev = value;
            }

            /**
             * Obtiene el valor de la propiedad afapuntebascp.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFAPUNTEBASCP }
             *     
             */
            public ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFAPUNTEBASCP getAFAPUNTEBASCP() {
                return afapuntebascp;
            }

            /**
             * Define el valor de la propiedad afapuntebascp.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFAPUNTEBASCP }
             *     
             */
            public void setAFAPUNTEBASCP(ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFAPUNTEBASCP value) {
                this.afapuntebascp = value;
            }

            /**
             * Gets the value of the afapuntebascplp property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the afapuntebascplp property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getAFAPUNTEBASCPLP().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFAPUNTEBASCPLP }
             * 
             * 
             */
            public List<ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFAPUNTEBASCPLP> getAFAPUNTEBASCPLP() {
                if (afapuntebascplp == null) {
                    afapuntebascplp = new ArrayList<ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.AFAPUNTEBASCPLP>();
                }
                return this.afapuntebascplp;
            }

            /**
             * Obtiene el valor de la propiedad trpetcnobteafevtz.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.TRPETCNOBTEAFEVTZ }
             *     
             */
            public ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.TRPETCNOBTEAFEVTZ getTRPETCNOBTEAFEVTZ() {
                return trpetcnobteafevtz;
            }

            /**
             * Define el valor de la propiedad trpetcnobteafevtz.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.TRPETCNOBTEAFEVTZ }
             *     
             */
            public void setTRPETCNOBTEAFEVTZ(ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.TRPETCNOBTEAFEVTZ value) {
                this.trpetcnobteafevtz = value;
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
             *         &lt;element name="NOMB_PDV">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000030"/>
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
             *         &lt;element name="NUM_SUBAC">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_CTA">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="DESCR_CTA">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000020"/>
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
             *         &lt;element name="DESCR_MONEDA">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000040"/>
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
             *         &lt;element name="FECHA_CTBLE">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
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
             *         &lt;element name="IMP_APNTE">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *               &lt;totalDigits value="0000000015"/>
             *               &lt;fractionDigits value="02"/>
             *               &lt;maxInclusive value="9999999999999.99"/>
             *               &lt;minInclusive value="0.0"/>
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
             *         &lt;element name="COD_ORIGEN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000008"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="DESCR_ORIGEN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000060"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="CONCPT_APNTE_LEN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="CONCPT_APNTE">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000100"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="DESCR_AF_RL_AF">
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
                "nombpdv",
                "nomb50",
                "numsubac",
                "codcta",
                "descrcta",
                "codnumrcomoneda",
                "descrmoneda",
                "numsec",
                "fechactble",
                "fechavalor",
                "impapnte",
                "sgn",
                "codorigen",
                "descrorigen",
                "concptapntelen",
                "concptapnte",
                "descrafrlaf"
            })
            public static class AFAPUNTEBASCP {

                @XmlElement(name = "NOMB_PDV", required = true)
                protected String nombpdv;
                @XmlElement(name = "NOMB_50", required = true)
                protected String nomb50;
                @XmlElement(name = "NUM_SUBAC")
                protected int numsubac;
                @XmlElement(name = "COD_CTA", required = true)
                protected String codcta;
                @XmlElement(name = "DESCR_CTA", required = true)
                protected String descrcta;
                @XmlElement(name = "COD_NUMRCO_MONEDA", required = true)
                protected String codnumrcomoneda;
                @XmlElement(name = "DESCR_MONEDA", required = true)
                protected String descrmoneda;
                @XmlElement(name = "NUM_SEC")
                protected int numsec;
                @XmlElement(name = "FECHA_CTBLE")
                protected int fechactble;
                @XmlElement(name = "FECHA_VALOR")
                protected int fechavalor;
                @XmlElement(name = "IMP_APNTE", required = true)
                protected BigDecimal impapnte;
                @XmlElement(name = "SGN", required = true)
                protected String sgn;
                @XmlElement(name = "COD_ORIGEN", required = true)
                protected String codorigen;
                @XmlElement(name = "DESCR_ORIGEN", required = true)
                protected String descrorigen;
                @XmlElement(name = "CONCPT_APNTE_LEN")
                protected int concptapntelen;
                @XmlElement(name = "CONCPT_APNTE", required = true)
                protected String concptapnte;
                @XmlElement(name = "DESCR_AF_RL_AF", required = true)
                protected String descrafrlaf;

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
                public void setNOMBPDV(String value) {
                    this.nombpdv = value;
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
                 * Obtiene el valor de la propiedad numsubac.
                 * 
                 */
                public int getNUMSUBAC() {
                    return numsubac;
                }

                /**
                 * Define el valor de la propiedad numsubac.
                 * 
                 */
                public void setNUMSUBAC(int value) {
                    this.numsubac = value;
                }

                /**
                 * Obtiene el valor de la propiedad codcta.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODCTA() {
                    return codcta;
                }

                /**
                 * Define el valor de la propiedad codcta.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODCTA(String value) {
                    this.codcta = value;
                }

                /**
                 * Obtiene el valor de la propiedad descrcta.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDESCRCTA() {
                    return descrcta;
                }

                /**
                 * Define el valor de la propiedad descrcta.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDESCRCTA(String value) {
                    this.descrcta = value;
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
                public void setCODNUMRCOMONEDA(String value) {
                    this.codnumrcomoneda = value;
                }

                /**
                 * Obtiene el valor de la propiedad descrmoneda.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDESCRMONEDA() {
                    return descrmoneda;
                }

                /**
                 * Define el valor de la propiedad descrmoneda.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDESCRMONEDA(String value) {
                    this.descrmoneda = value;
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
                public void setFECHACTBLE(int value) {
                    this.fechactble = value;
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
                public void setFECHAVALOR(int value) {
                    this.fechavalor = value;
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
                 * Obtiene el valor de la propiedad codorigen.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODORIGEN() {
                    return codorigen;
                }

                /**
                 * Define el valor de la propiedad codorigen.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODORIGEN(String value) {
                    this.codorigen = value;
                }

                /**
                 * Obtiene el valor de la propiedad descrorigen.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDESCRORIGEN() {
                    return descrorigen;
                }

                /**
                 * Define el valor de la propiedad descrorigen.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDESCRORIGEN(String value) {
                    this.descrorigen = value;
                }

                /**
                 * Obtiene el valor de la propiedad concptapntelen.
                 * 
                 */
                public int getCONCPTAPNTELEN() {
                    return concptapntelen;
                }

                /**
                 * Define el valor de la propiedad concptapntelen.
                 * 
                 */
                public void setCONCPTAPNTELEN(int value) {
                    this.concptapntelen = value;
                }

                /**
                 * Obtiene el valor de la propiedad concptapnte.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCONCPTAPNTE() {
                    return concptapnte;
                }

                /**
                 * Define el valor de la propiedad concptapnte.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCONCPTAPNTE(String value) {
                    this.concptapnte = value;
                }

                /**
                 * Obtiene el valor de la propiedad descrafrlaf.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDESCRAFRLAF() {
                    return descrafrlaf;
                }

                /**
                 * Define el valor de la propiedad descrafrlaf.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDESCRAFRLAF(String value) {
                    this.descrafrlaf = value;
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
             *         &lt;element name="DESCR_DOM_IND">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000020"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_VALOR_DOM_IND">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_INDIC">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="DESCR_INDIC">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000020"/>
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
                "descrdomind",
                "codvalordomind",
                "codindic",
                "descrindic"
            })
            public static class AFAPUNTEBASCPLP {

                @XmlElement(name = "DESCR_DOM_IND", required = true)
                protected String descrdomind;
                @XmlElement(name = "COD_VALOR_DOM_IND", required = true)
                protected String codvalordomind;
                @XmlElement(name = "COD_INDIC", required = true)
                protected String codindic;
                @XmlElement(name = "DESCR_INDIC", required = true)
                protected String descrindic;

                /**
                 * Obtiene el valor de la propiedad descrdomind.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDESCRDOMIND() {
                    return descrdomind;
                }

                /**
                 * Define el valor de la propiedad descrdomind.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDESCRDOMIND(String value) {
                    this.descrdomind = value;
                }

                /**
                 * Obtiene el valor de la propiedad codvalordomind.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODVALORDOMIND() {
                    return codvalordomind;
                }

                /**
                 * Define el valor de la propiedad codvalordomind.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODVALORDOMIND(String value) {
                    this.codvalordomind = value;
                }

                /**
                 * Obtiene el valor de la propiedad codindic.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODINDIC() {
                    return codindic;
                }

                /**
                 * Define el valor de la propiedad codindic.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODINDIC(String value) {
                    this.codindic = value;
                }

                /**
                 * Obtiene el valor de la propiedad descrindic.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDESCRINDIC() {
                    return descrindic;
                }

                /**
                 * Define el valor de la propiedad descrindic.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDESCRINDIC(String value) {
                    this.descrindic = value;
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
             *         &lt;element name="COD_ORGN_APNTE">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="ID_ORGN_APNTE">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000040"/>
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
                "codorgnapnte",
                "idorgnapnte"
            })
            public static class AFORGNAPNTEV {

                @XmlElement(name = "COD_ORGN_APNTE", required = true)
                protected String codorgnapnte;
                @XmlElement(name = "ID_ORGN_APNTE", required = true)
                protected String idorgnapnte;

                /**
                 * Obtiene el valor de la propiedad codorgnapnte.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODORGNAPNTE() {
                    return codorgnapnte;
                }

                /**
                 * Define el valor de la propiedad codorgnapnte.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODORGNAPNTE(String value) {
                    this.codorgnapnte = value;
                }

                /**
                 * Obtiene el valor de la propiedad idorgnapnte.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getIDORGNAPNTE() {
                    return idorgnapnte;
                }

                /**
                 * Define el valor de la propiedad idorgnapnte.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setIDORGNAPNTE(String value) {
                    this.idorgnapnte = value;
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
             *         &lt;element name="AF_APNTE_E">
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
             *                   &lt;element name="PRPDAD_CTA">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
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
             *                   &lt;element name="NUM_SUBAC">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="9999999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="ID_EXP_RECLAM">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="99"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_CTA">
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
             *                   &lt;element name="NUM_SEC">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="9999999"/>
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
             *                   &lt;element name="IMP_APNTE">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000015"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="9999999999999.99"/>
             *                         &lt;minInclusive value="0.0"/>
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
             *                   &lt;element name="FECHA_VALOR">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="99999999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_1">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_2">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_3">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_4">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_5">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_6">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_7">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_8">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_9">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_10">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_ORGN_APNTE">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000002"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="ID_ORGN_APNTE">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000040"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="CONCPT_APNTE_LEN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="9999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="CONCPT_APNTE">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000100"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_ORIGEN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000008"/>
             *                         &lt;whiteSpace value="preserve"/>
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
                "afapntee"
            })
            public static class TRPETCNOBTEAFEVTZ {

                @XmlElement(name = "AF_APNTE_E", required = true)
                protected ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.TRPETCNOBTEAFEVTZ.AFAPNTEE afapntee;

                /**
                 * Obtiene el valor de la propiedad afapntee.
                 * 
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.TRPETCNOBTEAFEVTZ.AFAPNTEE }
                 *     
                 */
                public ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.TRPETCNOBTEAFEVTZ.AFAPNTEE getAFAPNTEE() {
                    return afapntee;
                }

                /**
                 * Define el valor de la propiedad afapntee.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.TRPETCNOBTEAFEVTZ.AFAPNTEE }
                 *     
                 */
                public void setAFAPNTEE(ResponseBansefi.OAFAPUNTEBASCPTRNO.AFAPUNTEBASCPEVTZ.TRPETCNOBTEAFEVTZ.AFAPNTEE value) {
                    this.afapntee = value;
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
                 *         &lt;element name="PRPDAD_CTA">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
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
                 *         &lt;element name="NUM_SUBAC">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="9999999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="ID_EXP_RECLAM">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="99"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_CTA">
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
                 *         &lt;element name="NUM_SEC">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="9999999"/>
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
                 *         &lt;element name="IMP_APNTE">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000015"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="9999999999999.99"/>
                 *               &lt;minInclusive value="0.0"/>
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
                 *         &lt;element name="FECHA_VALOR">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="99999999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_1">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_2">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_3">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_4">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_5">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_6">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_7">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_8">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_9">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_10">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_ORGN_APNTE">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000002"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="ID_ORGN_APNTE">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000040"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="CONCPT_APNTE_LEN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="9999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="CONCPT_APNTE">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000100"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_ORIGEN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000008"/>
                 *               &lt;whiteSpace value="preserve"/>
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
                    "prpdadcta",
                    "numsecac",
                    "numsubac",
                    "idexpreclam",
                    "codcta",
                    "codnumrcomoneda",
                    "numsec",
                    "sgn",
                    "impapnte",
                    "fechactble",
                    "fechavalor",
                    "ind1",
                    "ind2",
                    "ind3",
                    "ind4",
                    "ind5",
                    "ind6",
                    "ind7",
                    "ind8",
                    "ind9",
                    "ind10",
                    "codorgnapnte",
                    "idorgnapnte",
                    "concptapntelen",
                    "concptapnte",
                    "codorigen",
                    "codinternouo",
                    "codlinea",
                    "idgrppd"
                })
                public static class AFAPNTEE {

                    @XmlElement(name = "COD_NRBE_EN", required = true)
                    protected String codnrbeen;
                    @XmlElement(name = "COD_CENT_UO", required = true)
                    protected String codcentuo;
                    @XmlElement(name = "PRPDAD_CTA", required = true)
                    protected String prpdadcta;
                    @XmlElement(name = "NUM_SEC_AC")
                    protected long numsecac;
                    @XmlElement(name = "NUM_SUBAC")
                    protected int numsubac;
                    @XmlElement(name = "ID_EXP_RECLAM")
                    protected int idexpreclam;
                    @XmlElement(name = "COD_CTA", required = true)
                    protected String codcta;
                    @XmlElement(name = "COD_NUMRCO_MONEDA", required = true)
                    protected String codnumrcomoneda;
                    @XmlElement(name = "NUM_SEC")
                    protected int numsec;
                    @XmlElement(name = "SGN", required = true)
                    protected String sgn;
                    @XmlElement(name = "IMP_APNTE", required = true)
                    protected BigDecimal impapnte;
                    @XmlElement(name = "FECHA_CTBLE")
                    protected int fechactble;
                    @XmlElement(name = "FECHA_VALOR")
                    protected int fechavalor;
                    @XmlElement(name = "IND_1", required = true)
                    protected String ind1;
                    @XmlElement(name = "IND_2", required = true)
                    protected String ind2;
                    @XmlElement(name = "IND_3", required = true)
                    protected String ind3;
                    @XmlElement(name = "IND_4", required = true)
                    protected String ind4;
                    @XmlElement(name = "IND_5", required = true)
                    protected String ind5;
                    @XmlElement(name = "IND_6", required = true)
                    protected String ind6;
                    @XmlElement(name = "IND_7", required = true)
                    protected String ind7;
                    @XmlElement(name = "IND_8", required = true)
                    protected String ind8;
                    @XmlElement(name = "IND_9", required = true)
                    protected String ind9;
                    @XmlElement(name = "IND_10", required = true)
                    protected String ind10;
                    @XmlElement(name = "COD_ORGN_APNTE", required = true)
                    protected String codorgnapnte;
                    @XmlElement(name = "ID_ORGN_APNTE", required = true)
                    protected String idorgnapnte;
                    @XmlElement(name = "CONCPT_APNTE_LEN")
                    protected int concptapntelen;
                    @XmlElement(name = "CONCPT_APNTE", required = true)
                    protected String concptapnte;
                    @XmlElement(name = "COD_ORIGEN", required = true)
                    protected String codorigen;
                    @XmlElement(name = "COD_INTERNO_UO", required = true)
                    protected String codinternouo;
                    @XmlElement(name = "COD_LINEA", required = true)
                    protected String codlinea;
                    @XmlElement(name = "ID_GRP_PD", required = true)
                    protected String idgrppd;

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
                    public void setCODCENTUO(String value) {
                        this.codcentuo = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad prpdadcta.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPRPDADCTA() {
                        return prpdadcta;
                    }

                    /**
                     * Define el valor de la propiedad prpdadcta.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPRPDADCTA(String value) {
                        this.prpdadcta = value;
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
                    public void setNUMSECAC(long value) {
                        this.numsecac = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad numsubac.
                     * 
                     */
                    public int getNUMSUBAC() {
                        return numsubac;
                    }

                    /**
                     * Define el valor de la propiedad numsubac.
                     * 
                     */
                    public void setNUMSUBAC(int value) {
                        this.numsubac = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad idexpreclam.
                     * 
                     */
                    public int getIDEXPRECLAM() {
                        return idexpreclam;
                    }

                    /**
                     * Define el valor de la propiedad idexpreclam.
                     * 
                     */
                    public void setIDEXPRECLAM(int value) {
                        this.idexpreclam = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codcta.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCODCTA() {
                        return codcta;
                    }

                    /**
                     * Define el valor de la propiedad codcta.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCODCTA(String value) {
                        this.codcta = value;
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
                    public void setCODNUMRCOMONEDA(String value) {
                        this.codnumrcomoneda = value;
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
                    public void setFECHACTBLE(int value) {
                        this.fechactble = value;
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
                    public void setFECHAVALOR(int value) {
                        this.fechavalor = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad ind1.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIND1() {
                        return ind1;
                    }

                    /**
                     * Define el valor de la propiedad ind1.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIND1(String value) {
                        this.ind1 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad ind2.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIND2() {
                        return ind2;
                    }

                    /**
                     * Define el valor de la propiedad ind2.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIND2(String value) {
                        this.ind2 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad ind3.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIND3() {
                        return ind3;
                    }

                    /**
                     * Define el valor de la propiedad ind3.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIND3(String value) {
                        this.ind3 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad ind4.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIND4() {
                        return ind4;
                    }

                    /**
                     * Define el valor de la propiedad ind4.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIND4(String value) {
                        this.ind4 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad ind5.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIND5() {
                        return ind5;
                    }

                    /**
                     * Define el valor de la propiedad ind5.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIND5(String value) {
                        this.ind5 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad ind6.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIND6() {
                        return ind6;
                    }

                    /**
                     * Define el valor de la propiedad ind6.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIND6(String value) {
                        this.ind6 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad ind7.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIND7() {
                        return ind7;
                    }

                    /**
                     * Define el valor de la propiedad ind7.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIND7(String value) {
                        this.ind7 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad ind8.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIND8() {
                        return ind8;
                    }

                    /**
                     * Define el valor de la propiedad ind8.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIND8(String value) {
                        this.ind8 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad ind9.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIND9() {
                        return ind9;
                    }

                    /**
                     * Define el valor de la propiedad ind9.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIND9(String value) {
                        this.ind9 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad ind10.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIND10() {
                        return ind10;
                    }

                    /**
                     * Define el valor de la propiedad ind10.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIND10(String value) {
                        this.ind10 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codorgnapnte.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCODORGNAPNTE() {
                        return codorgnapnte;
                    }

                    /**
                     * Define el valor de la propiedad codorgnapnte.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCODORGNAPNTE(String value) {
                        this.codorgnapnte = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad idorgnapnte.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getIDORGNAPNTE() {
                        return idorgnapnte;
                    }

                    /**
                     * Define el valor de la propiedad idorgnapnte.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setIDORGNAPNTE(String value) {
                        this.idorgnapnte = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad concptapntelen.
                     * 
                     */
                    public int getCONCPTAPNTELEN() {
                        return concptapntelen;
                    }

                    /**
                     * Define el valor de la propiedad concptapntelen.
                     * 
                     */
                    public void setCONCPTAPNTELEN(int value) {
                        this.concptapntelen = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad concptapnte.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCONCPTAPNTE() {
                        return concptapnte;
                    }

                    /**
                     * Define el valor de la propiedad concptapnte.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCONCPTAPNTE(String value) {
                        this.concptapnte = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codorigen.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCODORIGEN() {
                        return codorigen;
                    }

                    /**
                     * Define el valor de la propiedad codorigen.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCODORIGEN(String value) {
                        this.codorigen = value;
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
                    public void setCODINTERNOUO(String value) {
                        this.codinternouo = value;
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
                    public void setCODLINEA(String value) {
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
                    public void setIDGRPPD(String value) {
                        this.idgrppd = value;
                    }

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

    }

}
