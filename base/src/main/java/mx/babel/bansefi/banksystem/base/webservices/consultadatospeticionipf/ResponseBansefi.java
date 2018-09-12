
package mx.babel.bansefi.banksystem.base.webservices.consultadatospeticionipf;

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
 *         &lt;element name="OTR_DATOS_PET_IMPSCN_PAG">
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
 *                   &lt;element name="TR_DATOS_PET_IMPSCN_PAG_E">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="NOMB_50">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000050"/>
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
 *                             &lt;element name="DESCR_PDV">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000060"/>
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
 *                             &lt;element name="IP_MONEDA_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
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
 *                             &lt;element name="IP_ORDEN_CANC_V">
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
 *                             &lt;element name="IMP_NOMINAL">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="0000000015"/>
 *                                   &lt;fractionDigits value="02"/>
 *                                   &lt;maxInclusive value="9999999999999.99"/>
 *                                   &lt;minInclusive value="0.0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="TR_DATOS_PET_IMPSCN_PAG_E" maxOccurs="50">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="IP_DATOS_COND_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="ID_PDS">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000003"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="ID_PARMCD">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000003"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FECHA_INIC_VAL">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FECHA_INIC_VAL_1">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FECHA_CRRE_VAL">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FECHA_ECV_ACT">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_ECV_KS">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_CD_AC">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_ORG_CD">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_ESTRCT_CD">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_CONCPT_NEG">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000002"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_MODIF_CD">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000002"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_PROF_CD">
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
 *                                       &lt;element name="KA_ESTRCT_VLISTA_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="COD_DOM_PARMCD">
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
 *                                       &lt;element name="KA_KA_RL_PK_DOM_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="COD_DOM_PARMCD">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000002"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="DOM_IND_PREF">
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
 *                                       &lt;element name="KA_ESTRCT_RNG_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="RNG_PREF">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000015"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="9999999999999.99"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="RNG_MIN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000015"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="9999999999999.99"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="RNG_MAX">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000015"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="9999999999999.99"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="RNG_INCREM">
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
 *                                       &lt;element name="KA_ESTRCT_VRNG_V" maxOccurs="50">
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
 *                                       &lt;element name="KA_ESTRCT_CMSN_1_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="COD_CMSN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
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
 *                                                 &lt;element name="COD_NUMRCO_MONEDA">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000003"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_AGRU_CLOP">
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
 *                                       &lt;element name="KA_CMSN_FIJ_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="IMP_CMSN_FIJ">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000015"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="9999999999999.99"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FOR_CAL_PER_PCAL">
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
 *                                       &lt;element name="KA_CMSN_PRCTL_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="COD_COMSN_PRCTL">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="CMSN_PCT">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000006"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="999.999"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="CMSN_MIN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000015"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="9999999999999.99"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="CMSN_MAX">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000015"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="9999999999999.99"/>
 *                                                       &lt;minInclusive value="0.0"/>
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
 *                                       &lt;element name="KA_ESTRCT_INT_1_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
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
 *                                                 &lt;element name="COD_INT">
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
 *                                       &lt;element name="KA_ESTRCT_INT_FIJ_V" maxOccurs="50">
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
 *                                       &lt;element name="KA_ESTRCT_INT_VAR_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
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
 *                                                 &lt;element name="INT_MIN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000006"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="999.999"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="INT_MAX">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000006"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="999.999"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="INT_FREC_REV">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FRACCION_REV">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000007"/>
 *                                                       &lt;fractionDigits value="04"/>
 *                                                       &lt;maxInclusive value="999.9999"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="PCT_MIN_REV">
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
 *                                                 &lt;element name="COD_CRIT_REV">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000002"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FORMA_REV">
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
 *                                       &lt;element name="KA_INT_FRANQ_1_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
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
 *                                                 &lt;element name="COD_INT">
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
 *                                       &lt;element name="KA_INT_FRANQ_FIJ_V" maxOccurs="50">
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
 *                                       &lt;element name="KA_INT_FRANQ_VAR_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
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
 *                                                 &lt;element name="INT_MIN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000006"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="999.999"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="INT_MAX">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000006"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="999.999"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="INT_FREC_REV">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FRACCION_REV">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000007"/>
 *                                                       &lt;fractionDigits value="04"/>
 *                                                       &lt;maxInclusive value="999.9999"/>
 *                                                       &lt;minInclusive value="0.0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="PCT_MIN_REV">
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
 *                                                 &lt;element name="COD_CRIT_REV">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000002"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FORMA_REV">
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
 *                                       &lt;element name="KA_INT_RL_SP_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="COD_CLASE_SP">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000003"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="ID_SP_NUM">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="9999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="KA_MATRIZ_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="ID_TRAMEADO">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000010"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_FOR_APLC_TR">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
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
 *                                                 &lt;element name="COD_AGRU_CLOP">
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
 *                                       &lt;element name="ID_CCPS_1_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="ID_CCPS">
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
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="STD_AN_AV_MSJ_V">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AN_NUM_ANOTACIONES_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="STD_DEC_3">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="STD_AN_AV_MSJ_LS" maxOccurs="5">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="NUMERO_ANTCN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="999999999999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_ANTCN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="DESCRIP_ANTCN_V" maxOccurs="5">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_CHAR_11">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000011"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="IND_PRDAD">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="DESC_IND_PRDAD_V" maxOccurs="5">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_CHAR_07">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000007"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="SUBCD_ANTCN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="STD_DESCR_C_ANTCN_V" maxOccurs="5">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="STD_CHAR_30">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000030"/>
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
    "otrdatospetimpscnpag"
})
public class ResponseBansefi {

    @XmlElement(name = "OTR_DATOS_PET_IMPSCN_PAG", required = true)
    protected ResponseBansefi.OTRDATOSPETIMPSCNPAG otrdatospetimpscnpag;

    /**
     * Obtiene el valor de la propiedad otrdatospetimpscnpag.
     *
     * @return
     *     possible object is
     *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG }
     *
     */
    public ResponseBansefi.OTRDATOSPETIMPSCNPAG getOTRDATOSPETIMPSCNPAG() {
        return otrdatospetimpscnpag;
    }

    /**
     * Define el valor de la propiedad otrdatospetimpscnpag.
     *
     * @param value
     *     allowed object is
     *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG }
     *
     */
    public void setOTRDATOSPETIMPSCNPAG(final ResponseBansefi.OTRDATOSPETIMPSCNPAG value) {
        this.otrdatospetimpscnpag = value;
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
     *         &lt;element name="TR_DATOS_PET_IMPSCN_PAG_E">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="NOMB_50">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000050"/>
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
     *                   &lt;element name="DESCR_PDV">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000060"/>
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
     *                   &lt;element name="IP_MONEDA_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
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
     *                   &lt;element name="IP_ORDEN_CANC_V">
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
     *                   &lt;element name="IMP_NOMINAL">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="0000000015"/>
     *                         &lt;fractionDigits value="02"/>
     *                         &lt;maxInclusive value="9999999999999.99"/>
     *                         &lt;minInclusive value="0.0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="TR_DATOS_PET_IMPSCN_PAG_E" maxOccurs="50">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="IP_DATOS_COND_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="ID_PDS">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000003"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="ID_PARMCD">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000003"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FECHA_INIC_VAL">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="99999999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FECHA_INIC_VAL_1">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="99999999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FECHA_CRRE_VAL">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="99999999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FECHA_ECV_ACT">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="99999999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_ECV_KS">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_CD_AC">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_ORG_CD">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_ESTRCT_CD">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_CONCPT_NEG">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000002"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_MODIF_CD">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000002"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_PROF_CD">
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
     *                             &lt;element name="KA_ESTRCT_VLISTA_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="COD_DOM_PARMCD">
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
     *                             &lt;element name="KA_KA_RL_PK_DOM_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="COD_DOM_PARMCD">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000002"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="DOM_IND_PREF">
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
     *                             &lt;element name="KA_ESTRCT_RNG_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="RNG_PREF">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000015"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="9999999999999.99"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="RNG_MIN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000015"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="9999999999999.99"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="RNG_MAX">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000015"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="9999999999999.99"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="RNG_INCREM">
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
     *                             &lt;element name="KA_ESTRCT_VRNG_V" maxOccurs="50">
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
     *                             &lt;element name="KA_ESTRCT_CMSN_1_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="COD_CMSN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
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
     *                                       &lt;element name="COD_NUMRCO_MONEDA">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000003"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_AGRU_CLOP">
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
     *                             &lt;element name="KA_CMSN_FIJ_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="IMP_CMSN_FIJ">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000015"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="9999999999999.99"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FOR_CAL_PER_PCAL">
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
     *                             &lt;element name="KA_CMSN_PRCTL_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="COD_COMSN_PRCTL">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="CMSN_PCT">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000006"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="999.999"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="CMSN_MIN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000015"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="9999999999999.99"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="CMSN_MAX">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000015"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="9999999999999.99"/>
     *                                             &lt;minInclusive value="0.0"/>
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
     *                             &lt;element name="KA_ESTRCT_INT_1_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
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
     *                                       &lt;element name="COD_INT">
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
     *                             &lt;element name="KA_ESTRCT_INT_FIJ_V" maxOccurs="50">
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
     *                             &lt;element name="KA_ESTRCT_INT_VAR_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
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
     *                                       &lt;element name="INT_MIN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000006"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="999.999"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="INT_MAX">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000006"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="999.999"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="INT_FREC_REV">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FRACCION_REV">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000007"/>
     *                                             &lt;fractionDigits value="04"/>
     *                                             &lt;maxInclusive value="999.9999"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="PCT_MIN_REV">
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
     *                                       &lt;element name="COD_CRIT_REV">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000002"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FORMA_REV">
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
     *                             &lt;element name="KA_INT_FRANQ_1_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
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
     *                                       &lt;element name="COD_INT">
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
     *                             &lt;element name="KA_INT_FRANQ_FIJ_V" maxOccurs="50">
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
     *                             &lt;element name="KA_INT_FRANQ_VAR_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
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
     *                                       &lt;element name="INT_MIN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000006"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="999.999"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="INT_MAX">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000006"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="999.999"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="INT_FREC_REV">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FRACCION_REV">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000007"/>
     *                                             &lt;fractionDigits value="04"/>
     *                                             &lt;maxInclusive value="999.9999"/>
     *                                             &lt;minInclusive value="0.0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="PCT_MIN_REV">
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
     *                                       &lt;element name="COD_CRIT_REV">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000002"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FORMA_REV">
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
     *                             &lt;element name="KA_INT_RL_SP_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="COD_CLASE_SP">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000003"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="ID_SP_NUM">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="9999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="KA_MATRIZ_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="ID_TRAMEADO">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000010"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_FOR_APLC_TR">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
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
     *                                       &lt;element name="COD_AGRU_CLOP">
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
     *                             &lt;element name="ID_CCPS_1_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="ID_CCPS">
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
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="STD_AN_AV_MSJ_V">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="AN_NUM_ANOTACIONES_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="STD_DEC_3">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="STD_AN_AV_MSJ_LS" maxOccurs="5">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="NUMERO_ANTCN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="999999999999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_ANTCN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="DESCRIP_ANTCN_V" maxOccurs="5">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="STD_CHAR_11">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000011"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="IND_PRDAD">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000001"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="DESC_IND_PRDAD_V" maxOccurs="5">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="STD_CHAR_07">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000007"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="SUBCD_ANTCN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="STD_DESCR_C_ANTCN_V" maxOccurs="5">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="STD_CHAR_30">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000030"/>
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
        "trdatospetimpscnpage",
        "stdanavmsjv",
        "stdtrnmsjparmv",
        "stdtrnoparmv"
    })
    public static class OTRDATOSPETIMPSCNPAG {

        @XmlElement(name = "RTRN_CD")
        protected int rtrncd;
        @XmlElement(name = "NUMBER_OF_RECORDS")
        protected int numberofrecords;
        @XmlElement(name = "TR_DATOS_PET_IMPSCN_PAG_E", required = true)
        protected ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE trdatospetimpscnpage;
        @XmlElement(name = "STD_AN_AV_MSJ_V", required = true)
        protected ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV stdanavmsjv;
        @XmlElement(name = "STD_TRN_MSJ_PARM_V", required = true)
        protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDTRNMSJPARMV> stdtrnmsjparmv;
        @XmlElement(name = "STD_TRN_O_PARM_V", required = true)
        protected ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDTRNOPARMV stdtrnoparmv;

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
         * Obtiene el valor de la propiedad trdatospetimpscnpage.
         *
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE }
         *
         */
        public ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE getTRDATOSPETIMPSCNPAGE() {
            return trdatospetimpscnpage;
        }

        /**
         * Define el valor de la propiedad trdatospetimpscnpage.
         *
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE }
         *
         */
        public void setTRDATOSPETIMPSCNPAGE(final ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE value) {
            this.trdatospetimpscnpage = value;
        }

        /**
         * Obtiene el valor de la propiedad stdanavmsjv.
         *
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV }
         *
         */
        public ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV getSTDANAVMSJV() {
            return stdanavmsjv;
        }

        /**
         * Define el valor de la propiedad stdanavmsjv.
         *
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV }
         *
         */
        public void setSTDANAVMSJV(final ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV value) {
            this.stdanavmsjv = value;
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
         * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDTRNMSJPARMV }
         *
         *
         */
        public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDTRNMSJPARMV> getSTDTRNMSJPARMV() {
            if (stdtrnmsjparmv == null) {
                stdtrnmsjparmv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDTRNMSJPARMV>();
            }
            return this.stdtrnmsjparmv;
        }

        /**
         * Obtiene el valor de la propiedad stdtrnoparmv.
         *
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDTRNOPARMV }
         *
         */
        public ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDTRNOPARMV getSTDTRNOPARMV() {
            return stdtrnoparmv;
        }

        /**
         * Define el valor de la propiedad stdtrnoparmv.
         *
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDTRNOPARMV }
         *
         */
        public void setSTDTRNOPARMV(final ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDTRNOPARMV value) {
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
         *         &lt;element name="AN_NUM_ANOTACIONES_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="STD_DEC_3">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="STD_AN_AV_MSJ_LS" maxOccurs="5">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="NUMERO_ANTCN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="999999999999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_ANTCN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="DESCRIP_ANTCN_V" maxOccurs="5">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="STD_CHAR_11">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000011"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="IND_PRDAD">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000001"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="DESC_IND_PRDAD_V" maxOccurs="5">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="STD_CHAR_07">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000007"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="SUBCD_ANTCN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="STD_DESCR_C_ANTCN_V" maxOccurs="5">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="STD_CHAR_30">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000030"/>
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
            "annumanotacionesv",
            "stdanavmsjls"
        })
        public static class STDANAVMSJV {

            @XmlElement(name = "AN_NUM_ANOTACIONES_V", required = true)
            protected ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.ANNUMANOTACIONESV annumanotacionesv;
            @XmlElement(name = "STD_AN_AV_MSJ_LS", required = true)
            protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS> stdanavmsjls;

            /**
             * Obtiene el valor de la propiedad annumanotacionesv.
             *
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.ANNUMANOTACIONESV }
             *
             */
            public ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.ANNUMANOTACIONESV getANNUMANOTACIONESV() {
                return annumanotacionesv;
            }

            /**
             * Define el valor de la propiedad annumanotacionesv.
             *
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.ANNUMANOTACIONESV }
             *
             */
            public void setANNUMANOTACIONESV(final ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.ANNUMANOTACIONESV value) {
                this.annumanotacionesv = value;
            }

            /**
             * Gets the value of the stdanavmsjls property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the stdanavmsjls property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getSTDANAVMSJLS().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS }
             *
             *
             */
            public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS> getSTDANAVMSJLS() {
                if (stdanavmsjls == null) {
                    stdanavmsjls = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS>();
                }
                return this.stdanavmsjls;
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
             *         &lt;element name="STD_DEC_3">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="999"/>
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
                "stddec3"
            })
            public static class ANNUMANOTACIONESV {

                @XmlElement(name = "STD_DEC_3")
                protected int stddec3;

                /**
                 * Obtiene el valor de la propiedad stddec3.
                 *
                 */
                public int getSTDDEC3() {
                    return stddec3;
                }

                /**
                 * Define el valor de la propiedad stddec3.
                 *
                 */
                public void setSTDDEC3(final int value) {
                    this.stddec3 = value;
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
             *         &lt;element name="NUMERO_ANTCN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="999999999999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_ANTCN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="DESCRIP_ANTCN_V" maxOccurs="5">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="STD_CHAR_11">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000011"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="IND_PRDAD">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000001"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="DESC_IND_PRDAD_V" maxOccurs="5">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="STD_CHAR_07">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000007"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="SUBCD_ANTCN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="STD_DESCR_C_ANTCN_V" maxOccurs="5">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="STD_CHAR_30">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000030"/>
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
                "numeroantcn",
                "codantcn",
                "descripantcnv",
                "indprdad",
                "descindprdadv",
                "subcdantcn",
                "stddescrcantcnv"
            })
            public static class STDANAVMSJLS {

                @XmlElement(name = "NUMERO_ANTCN")
                protected long numeroantcn;
                @XmlElement(name = "COD_ANTCN", required = true)
                protected String codantcn;
                @XmlElement(name = "DESCRIP_ANTCN_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS.DESCRIPANTCNV> descripantcnv;
                @XmlElement(name = "IND_PRDAD", required = true)
                protected String indprdad;
                @XmlElement(name = "DESC_IND_PRDAD_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV> descindprdadv;
                @XmlElement(name = "SUBCD_ANTCN", required = true)
                protected String subcdantcn;
                @XmlElement(name = "STD_DESCR_C_ANTCN_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS.STDDESCRCANTCNV> stddescrcantcnv;

                /**
                 * Obtiene el valor de la propiedad numeroantcn.
                 *
                 */
                public long getNUMEROANTCN() {
                    return numeroantcn;
                }

                /**
                 * Define el valor de la propiedad numeroantcn.
                 *
                 */
                public void setNUMEROANTCN(final long value) {
                    this.numeroantcn = value;
                }

                /**
                 * Obtiene el valor de la propiedad codantcn.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODANTCN() {
                    return codantcn;
                }

                /**
                 * Define el valor de la propiedad codantcn.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODANTCN(final String value) {
                    this.codantcn = value;
                }

                /**
                 * Gets the value of the descripantcnv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the descripantcnv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getDESCRIPANTCNV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS.DESCRIPANTCNV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS.DESCRIPANTCNV> getDESCRIPANTCNV() {
                    if (descripantcnv == null) {
                        descripantcnv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS.DESCRIPANTCNV>();
                    }
                    return this.descripantcnv;
                }

                /**
                 * Obtiene el valor de la propiedad indprdad.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getINDPRDAD() {
                    return indprdad;
                }

                /**
                 * Define el valor de la propiedad indprdad.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setINDPRDAD(final String value) {
                    this.indprdad = value;
                }

                /**
                 * Gets the value of the descindprdadv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the descindprdadv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getDESCINDPRDADV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV> getDESCINDPRDADV() {
                    if (descindprdadv == null) {
                        descindprdadv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV>();
                    }
                    return this.descindprdadv;
                }

                /**
                 * Obtiene el valor de la propiedad subcdantcn.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getSUBCDANTCN() {
                    return subcdantcn;
                }

                /**
                 * Define el valor de la propiedad subcdantcn.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setSUBCDANTCN(final String value) {
                    this.subcdantcn = value;
                }

                /**
                 * Gets the value of the stddescrcantcnv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the stddescrcantcnv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getSTDDESCRCANTCNV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS.STDDESCRCANTCNV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS.STDDESCRCANTCNV> getSTDDESCRCANTCNV() {
                    if (stddescrcantcnv == null) {
                        stddescrcantcnv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.STDANAVMSJV.STDANAVMSJLS.STDDESCRCANTCNV>();
                    }
                    return this.stddescrcantcnv;
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
                 *         &lt;element name="STD_CHAR_07">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000007"/>
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
                    "stdchar07"
                })
                public static class DESCINDPRDADV {

                    @XmlElement(name = "STD_CHAR_07", required = true)
                    protected String stdchar07;

                    /**
                     * Obtiene el valor de la propiedad stdchar07.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getSTDCHAR07() {
                        return stdchar07;
                    }

                    /**
                     * Define el valor de la propiedad stdchar07.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setSTDCHAR07(final String value) {
                        this.stdchar07 = value;
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
                 *         &lt;element name="STD_CHAR_11">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000011"/>
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
                    "stdchar11"
                })
                public static class DESCRIPANTCNV {

                    @XmlElement(name = "STD_CHAR_11", required = true)
                    protected String stdchar11;

                    /**
                     * Obtiene el valor de la propiedad stdchar11.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getSTDCHAR11() {
                        return stdchar11;
                    }

                    /**
                     * Define el valor de la propiedad stdchar11.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setSTDCHAR11(final String value) {
                        this.stdchar11 = value;
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
                 *         &lt;element name="STD_CHAR_30">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000030"/>
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
                    "stdchar30"
                })
                public static class STDDESCRCANTCNV {

                    @XmlElement(name = "STD_CHAR_30", required = true)
                    protected String stdchar30;

                    /**
                     * Obtiene el valor de la propiedad stdchar30.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getSTDCHAR30() {
                        return stdchar30;
                    }

                    /**
                     * Define el valor de la propiedad stdchar30.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setSTDCHAR30(final String value) {
                        this.stdchar30 = value;
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
         *         &lt;element name="NOMB_50">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000050"/>
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
         *         &lt;element name="DESCR_PDV">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000060"/>
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
         *         &lt;element name="IP_MONEDA_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
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
         *         &lt;element name="IP_ORDEN_CANC_V">
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
         *         &lt;element name="IMP_NOMINAL">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="0000000015"/>
         *               &lt;fractionDigits value="02"/>
         *               &lt;maxInclusive value="9999999999999.99"/>
         *               &lt;minInclusive value="0.0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="TR_DATOS_PET_IMPSCN_PAG_E" maxOccurs="50">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="IP_DATOS_COND_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="ID_PDS">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000003"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="ID_PARMCD">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000003"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FECHA_INIC_VAL">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="99999999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FECHA_INIC_VAL_1">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="99999999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FECHA_CRRE_VAL">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="99999999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FECHA_ECV_ACT">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="99999999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_ECV_KS">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_CD_AC">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_ORG_CD">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_ESTRCT_CD">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_CONCPT_NEG">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000002"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_MODIF_CD">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000002"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_PROF_CD">
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
         *                   &lt;element name="KA_ESTRCT_VLISTA_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="COD_DOM_PARMCD">
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
         *                   &lt;element name="KA_KA_RL_PK_DOM_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="COD_DOM_PARMCD">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000002"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="DOM_IND_PREF">
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
         *                   &lt;element name="KA_ESTRCT_RNG_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="RNG_PREF">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000015"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="9999999999999.99"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="RNG_MIN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000015"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="9999999999999.99"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="RNG_MAX">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000015"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="9999999999999.99"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="RNG_INCREM">
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
         *                   &lt;element name="KA_ESTRCT_VRNG_V" maxOccurs="50">
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
         *                   &lt;element name="KA_ESTRCT_CMSN_1_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="COD_CMSN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
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
         *                             &lt;element name="COD_NUMRCO_MONEDA">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000003"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_AGRU_CLOP">
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
         *                   &lt;element name="KA_CMSN_FIJ_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="IMP_CMSN_FIJ">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000015"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="9999999999999.99"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FOR_CAL_PER_PCAL">
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
         *                   &lt;element name="KA_CMSN_PRCTL_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="COD_COMSN_PRCTL">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="CMSN_PCT">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000006"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="999.999"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="CMSN_MIN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000015"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="9999999999999.99"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="CMSN_MAX">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000015"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="9999999999999.99"/>
         *                                   &lt;minInclusive value="0.0"/>
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
         *                   &lt;element name="KA_ESTRCT_INT_1_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
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
         *                             &lt;element name="COD_INT">
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
         *                   &lt;element name="KA_ESTRCT_INT_FIJ_V" maxOccurs="50">
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
         *                   &lt;element name="KA_ESTRCT_INT_VAR_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
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
         *                             &lt;element name="INT_MIN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000006"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="999.999"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="INT_MAX">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000006"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="999.999"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="INT_FREC_REV">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FRACCION_REV">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000007"/>
         *                                   &lt;fractionDigits value="04"/>
         *                                   &lt;maxInclusive value="999.9999"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="PCT_MIN_REV">
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
         *                             &lt;element name="COD_CRIT_REV">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000002"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FORMA_REV">
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
         *                   &lt;element name="KA_INT_FRANQ_1_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
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
         *                             &lt;element name="COD_INT">
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
         *                   &lt;element name="KA_INT_FRANQ_FIJ_V" maxOccurs="50">
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
         *                   &lt;element name="KA_INT_FRANQ_VAR_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
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
         *                             &lt;element name="INT_MIN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000006"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="999.999"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="INT_MAX">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000006"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="999.999"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="INT_FREC_REV">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FRACCION_REV">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000007"/>
         *                                   &lt;fractionDigits value="04"/>
         *                                   &lt;maxInclusive value="999.9999"/>
         *                                   &lt;minInclusive value="0.0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="PCT_MIN_REV">
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
         *                             &lt;element name="COD_CRIT_REV">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000002"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FORMA_REV">
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
         *                   &lt;element name="KA_INT_RL_SP_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="COD_CLASE_SP">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000003"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="ID_SP_NUM">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="9999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="KA_MATRIZ_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="ID_TRAMEADO">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000010"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_FOR_APLC_TR">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
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
         *                             &lt;element name="COD_AGRU_CLOP">
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
         *                   &lt;element name="ID_CCPS_1_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="ID_CCPS">
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
            "nomb50",
            "idextpe",
            "descrpdv",
            "codlinea",
            "idgrppd",
            "ipmonedav",
            "ipordencancv",
            "impapnte",
            "impnominal",
            "trdatospetimpscnpage",
            "psvcccv"
        })
        public static class TRDATOSPETIMPSCNPAGE {

            @XmlElement(name = "NOMB_50", required = true)
            protected String nomb50;
            @XmlElement(name = "ID_EXT_PE", required = true)
            protected String idextpe;
            @XmlElement(name = "DESCR_PDV", required = true)
            protected String descrpdv;
            @XmlElement(name = "COD_LINEA", required = true)
            protected String codlinea;
            @XmlElement(name = "ID_GRP_PD", required = true)
            protected String idgrppd;
            @XmlElement(name = "IP_MONEDA_V", required = true)
            protected ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.IPMONEDAV ipmonedav;
            @XmlElement(name = "IP_ORDEN_CANC_V", required = true)
            protected ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.IPORDENCANCV ipordencancv;
            @XmlElement(name = "IMP_APNTE", required = true)
            protected BigDecimal impapnte;
            @XmlElement(name = "IMP_NOMINAL", required = true)
            protected BigDecimal impnominal;
            @XmlElement(name = "TR_DATOS_PET_IMPSCN_PAG_E", required = true)
            protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1> trdatospetimpscnpage;
            @XmlElement(name = "PSV_CCC_V", required = true)
            protected ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.PSVCCCV psvcccv;

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
             * Obtiene el valor de la propiedad descrpdv.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDESCRPDV() {
                return descrpdv;
            }

            /**
             * Define el valor de la propiedad descrpdv.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDESCRPDV(final String value) {
                this.descrpdv = value;
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
             * Obtiene el valor de la propiedad ipmonedav.
             *
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.IPMONEDAV }
             *
             */
            public ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.IPMONEDAV getIPMONEDAV() {
                return ipmonedav;
            }

            /**
             * Define el valor de la propiedad ipmonedav.
             *
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.IPMONEDAV }
             *
             */
            public void setIPMONEDAV(final ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.IPMONEDAV value) {
                this.ipmonedav = value;
            }

            /**
             * Obtiene el valor de la propiedad ipordencancv.
             *
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.IPORDENCANCV }
             *
             */
            public ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.IPORDENCANCV getIPORDENCANCV() {
                return ipordencancv;
            }

            /**
             * Define el valor de la propiedad ipordencancv.
             *
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.IPORDENCANCV }
             *
             */
            public void setIPORDENCANCV(final ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.IPORDENCANCV value) {
                this.ipordencancv = value;
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
            public void setIMPAPNTE(final BigDecimal value) {
                this.impapnte = value;
            }

            /**
             * Obtiene el valor de la propiedad impnominal.
             *
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *
             */
            public BigDecimal getIMPNOMINAL() {
                return impnominal;
            }

            /**
             * Define el valor de la propiedad impnominal.
             *
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *
             */
            public void setIMPNOMINAL(final BigDecimal value) {
                this.impnominal = value;
            }

            /**
             * Gets the value of the trdatospetimpscnpage property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the trdatospetimpscnpage property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getTRDATOSPETIMPSCNPAGE().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1 }
             *
             *
             */
            public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1> getTRDATOSPETIMPSCNPAGE() {
                if (trdatospetimpscnpage == null) {
                    trdatospetimpscnpage = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1>();
                }
                return this.trdatospetimpscnpage;
            }

            /**
             * Obtiene el valor de la propiedad psvcccv.
             *
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.PSVCCCV }
             *
             */
            public ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.PSVCCCV getPSVCCCV() {
                return psvcccv;
            }

            /**
             * Define el valor de la propiedad psvcccv.
             *
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.PSVCCCV }
             *
             */
            public void setPSVCCCV(final ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.PSVCCCV value) {
                this.psvcccv = value;
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
                "codnumrcomoneda"
            })
            public static class IPMONEDAV {

                @XmlElement(name = "COD_NUMRCO_MONEDA", required = true)
                protected String codnumrcomoneda;

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
            public static class IPORDENCANCV {

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
             *         &lt;element name="IP_DATOS_COND_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="ID_PDS">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000003"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="ID_PARMCD">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000003"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FECHA_INIC_VAL">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="99999999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FECHA_INIC_VAL_1">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="99999999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FECHA_CRRE_VAL">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="99999999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FECHA_ECV_ACT">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="99999999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_ECV_KS">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_CD_AC">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_ORG_CD">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_ESTRCT_CD">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_CONCPT_NEG">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000002"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_MODIF_CD">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000002"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_PROF_CD">
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
             *         &lt;element name="KA_ESTRCT_VLISTA_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="COD_DOM_PARMCD">
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
             *         &lt;element name="KA_KA_RL_PK_DOM_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="COD_DOM_PARMCD">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000002"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="DOM_IND_PREF">
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
             *         &lt;element name="KA_ESTRCT_RNG_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="RNG_PREF">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000015"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="9999999999999.99"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="RNG_MIN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000015"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="9999999999999.99"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="RNG_MAX">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000015"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="9999999999999.99"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="RNG_INCREM">
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
             *         &lt;element name="KA_ESTRCT_VRNG_V" maxOccurs="50">
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
             *         &lt;element name="KA_ESTRCT_CMSN_1_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="COD_CMSN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
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
             *                   &lt;element name="COD_NUMRCO_MONEDA">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000003"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_AGRU_CLOP">
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
             *         &lt;element name="KA_CMSN_FIJ_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="IMP_CMSN_FIJ">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000015"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="9999999999999.99"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FOR_CAL_PER_PCAL">
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
             *         &lt;element name="KA_CMSN_PRCTL_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="COD_COMSN_PRCTL">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="CMSN_PCT">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000006"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="999.999"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="CMSN_MIN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000015"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="9999999999999.99"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="CMSN_MAX">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000015"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="9999999999999.99"/>
             *                         &lt;minInclusive value="0.0"/>
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
             *         &lt;element name="KA_ESTRCT_INT_1_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
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
             *                   &lt;element name="COD_INT">
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
             *         &lt;element name="KA_ESTRCT_INT_FIJ_V" maxOccurs="50">
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
             *         &lt;element name="KA_ESTRCT_INT_VAR_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
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
             *                   &lt;element name="INT_MIN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000006"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="999.999"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="INT_MAX">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000006"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="999.999"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="INT_FREC_REV">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FRACCION_REV">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000007"/>
             *                         &lt;fractionDigits value="04"/>
             *                         &lt;maxInclusive value="999.9999"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="PCT_MIN_REV">
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
             *                   &lt;element name="COD_CRIT_REV">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000002"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FORMA_REV">
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
             *         &lt;element name="KA_INT_FRANQ_1_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
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
             *                   &lt;element name="COD_INT">
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
             *         &lt;element name="KA_INT_FRANQ_FIJ_V" maxOccurs="50">
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
             *         &lt;element name="KA_INT_FRANQ_VAR_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
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
             *                   &lt;element name="INT_MIN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000006"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="999.999"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="INT_MAX">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000006"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="999.999"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="INT_FREC_REV">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FRACCION_REV">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000007"/>
             *                         &lt;fractionDigits value="04"/>
             *                         &lt;maxInclusive value="999.9999"/>
             *                         &lt;minInclusive value="0.0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="PCT_MIN_REV">
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
             *                   &lt;element name="COD_CRIT_REV">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000002"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FORMA_REV">
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
             *         &lt;element name="KA_INT_RL_SP_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="COD_CLASE_SP">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000003"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="ID_SP_NUM">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="9999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="KA_MATRIZ_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="ID_TRAMEADO">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000010"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_FOR_APLC_TR">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
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
             *                   &lt;element name="COD_AGRU_CLOP">
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
             *         &lt;element name="ID_CCPS_1_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="ID_CCPS">
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
                "ipdatoscondv",
                "kaestrctvlistav",
                "kakarlpkdomv",
                "kaestrctrngv",
                "kaestrctvrngv",
                "kaestrctcmsn1V",
                "kacmsnfijv",
                "kacmsnprctlv",
                "kaestrctint1V",
                "kaestrctintfijv",
                "kaestrctintvarv",
                "kaintfranq1V",
                "kaintfranqfijv",
                "kaintfranqvarv",
                "kaintrlspv",
                "kamatrizv",
                "idccps1V"
            })
            public static class TRDATOSPETIMPSCNPAGE1 {

                @XmlElement(name = "IP_DATOS_COND_V", required = true)
                protected ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.IPDATOSCONDV ipdatoscondv;
                @XmlElement(name = "KA_ESTRCT_VLISTA_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTVLISTAV> kaestrctvlistav;
                @XmlElement(name = "KA_KA_RL_PK_DOM_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAKARLPKDOMV> kakarlpkdomv;
                @XmlElement(name = "KA_ESTRCT_RNG_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTRNGV> kaestrctrngv;
                @XmlElement(name = "KA_ESTRCT_VRNG_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTVRNGV> kaestrctvrngv;
                @XmlElement(name = "KA_ESTRCT_CMSN_1_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTCMSN1V> kaestrctcmsn1V;
                @XmlElement(name = "KA_CMSN_FIJ_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KACMSNFIJV> kacmsnfijv;
                @XmlElement(name = "KA_CMSN_PRCTL_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KACMSNPRCTLV> kacmsnprctlv;
                @XmlElement(name = "KA_ESTRCT_INT_1_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTINT1V> kaestrctint1V;
                @XmlElement(name = "KA_ESTRCT_INT_FIJ_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTINTFIJV> kaestrctintfijv;
                @XmlElement(name = "KA_ESTRCT_INT_VAR_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTINTVARV> kaestrctintvarv;
                @XmlElement(name = "KA_INT_FRANQ_1_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTFRANQ1V> kaintfranq1V;
                @XmlElement(name = "KA_INT_FRANQ_FIJ_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTFRANQFIJV> kaintfranqfijv;
                @XmlElement(name = "KA_INT_FRANQ_VAR_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTFRANQVARV> kaintfranqvarv;
                @XmlElement(name = "KA_INT_RL_SP_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTRLSPV> kaintrlspv;
                @XmlElement(name = "KA_MATRIZ_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAMATRIZV> kamatrizv;
                @XmlElement(name = "ID_CCPS_1_V", required = true)
                protected List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.IDCCPS1V> idccps1V;

                /**
                 * Obtiene el valor de la propiedad ipdatoscondv.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.IPDATOSCONDV }
                 *
                 */
                public ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.IPDATOSCONDV getIPDATOSCONDV() {
                    return ipdatoscondv;
                }

                /**
                 * Define el valor de la propiedad ipdatoscondv.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.IPDATOSCONDV }
                 *
                 */
                public void setIPDATOSCONDV(final ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.IPDATOSCONDV value) {
                    this.ipdatoscondv = value;
                }

                /**
                 * Gets the value of the kaestrctvlistav property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kaestrctvlistav property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAESTRCTVLISTAV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTVLISTAV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTVLISTAV> getKAESTRCTVLISTAV() {
                    if (kaestrctvlistav == null) {
                        kaestrctvlistav = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTVLISTAV>();
                    }
                    return this.kaestrctvlistav;
                }

                /**
                 * Gets the value of the kakarlpkdomv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kakarlpkdomv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAKARLPKDOMV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAKARLPKDOMV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAKARLPKDOMV> getKAKARLPKDOMV() {
                    if (kakarlpkdomv == null) {
                        kakarlpkdomv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAKARLPKDOMV>();
                    }
                    return this.kakarlpkdomv;
                }

                /**
                 * Gets the value of the kaestrctrngv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kaestrctrngv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAESTRCTRNGV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTRNGV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTRNGV> getKAESTRCTRNGV() {
                    if (kaestrctrngv == null) {
                        kaestrctrngv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTRNGV>();
                    }
                    return this.kaestrctrngv;
                }

                /**
                 * Gets the value of the kaestrctvrngv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kaestrctvrngv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAESTRCTVRNGV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTVRNGV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTVRNGV> getKAESTRCTVRNGV() {
                    if (kaestrctvrngv == null) {
                        kaestrctvrngv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTVRNGV>();
                    }
                    return this.kaestrctvrngv;
                }

                /**
                 * Gets the value of the kaestrctcmsn1V property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kaestrctcmsn1V property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAESTRCTCMSN1V().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTCMSN1V }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTCMSN1V> getKAESTRCTCMSN1V() {
                    if (kaestrctcmsn1V == null) {
                        kaestrctcmsn1V = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTCMSN1V>();
                    }
                    return this.kaestrctcmsn1V;
                }

                /**
                 * Gets the value of the kacmsnfijv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kacmsnfijv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKACMSNFIJV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KACMSNFIJV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KACMSNFIJV> getKACMSNFIJV() {
                    if (kacmsnfijv == null) {
                        kacmsnfijv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KACMSNFIJV>();
                    }
                    return this.kacmsnfijv;
                }

                /**
                 * Gets the value of the kacmsnprctlv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kacmsnprctlv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKACMSNPRCTLV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KACMSNPRCTLV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KACMSNPRCTLV> getKACMSNPRCTLV() {
                    if (kacmsnprctlv == null) {
                        kacmsnprctlv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KACMSNPRCTLV>();
                    }
                    return this.kacmsnprctlv;
                }

                /**
                 * Gets the value of the kaestrctint1V property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kaestrctint1V property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAESTRCTINT1V().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTINT1V }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTINT1V> getKAESTRCTINT1V() {
                    if (kaestrctint1V == null) {
                        kaestrctint1V = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTINT1V>();
                    }
                    return this.kaestrctint1V;
                }

                /**
                 * Gets the value of the kaestrctintfijv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kaestrctintfijv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAESTRCTINTFIJV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTINTFIJV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTINTFIJV> getKAESTRCTINTFIJV() {
                    if (kaestrctintfijv == null) {
                        kaestrctintfijv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTINTFIJV>();
                    }
                    return this.kaestrctintfijv;
                }

                /**
                 * Gets the value of the kaestrctintvarv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kaestrctintvarv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAESTRCTINTVARV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTINTVARV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTINTVARV> getKAESTRCTINTVARV() {
                    if (kaestrctintvarv == null) {
                        kaestrctintvarv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAESTRCTINTVARV>();
                    }
                    return this.kaestrctintvarv;
                }

                /**
                 * Gets the value of the kaintfranq1V property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kaintfranq1V property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAINTFRANQ1V().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTFRANQ1V }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTFRANQ1V> getKAINTFRANQ1V() {
                    if (kaintfranq1V == null) {
                        kaintfranq1V = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTFRANQ1V>();
                    }
                    return this.kaintfranq1V;
                }

                /**
                 * Gets the value of the kaintfranqfijv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kaintfranqfijv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAINTFRANQFIJV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTFRANQFIJV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTFRANQFIJV> getKAINTFRANQFIJV() {
                    if (kaintfranqfijv == null) {
                        kaintfranqfijv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTFRANQFIJV>();
                    }
                    return this.kaintfranqfijv;
                }

                /**
                 * Gets the value of the kaintfranqvarv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kaintfranqvarv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAINTFRANQVARV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTFRANQVARV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTFRANQVARV> getKAINTFRANQVARV() {
                    if (kaintfranqvarv == null) {
                        kaintfranqvarv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTFRANQVARV>();
                    }
                    return this.kaintfranqvarv;
                }

                /**
                 * Gets the value of the kaintrlspv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kaintrlspv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAINTRLSPV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTRLSPV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTRLSPV> getKAINTRLSPV() {
                    if (kaintrlspv == null) {
                        kaintrlspv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAINTRLSPV>();
                    }
                    return this.kaintrlspv;
                }

                /**
                 * Gets the value of the kamatrizv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kamatrizv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKAMATRIZV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAMATRIZV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAMATRIZV> getKAMATRIZV() {
                    if (kamatrizv == null) {
                        kamatrizv = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.KAMATRIZV>();
                    }
                    return this.kamatrizv;
                }

                /**
                 * Gets the value of the idccps1V property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the idccps1V property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getIDCCPS1V().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.IDCCPS1V }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.IDCCPS1V> getIDCCPS1V() {
                    if (idccps1V == null) {
                        idccps1V = new ArrayList<ResponseBansefi.OTRDATOSPETIMPSCNPAG.TRDATOSPETIMPSCNPAGE.TRDATOSPETIMPSCNPAGE1.IDCCPS1V>();
                    }
                    return this.idccps1V;
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
                 *         &lt;element name="ID_CCPS">
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
                    "idccps"
                })
                public static class IDCCPS1V {

                    @XmlElement(name = "ID_CCPS", required = true)
                    protected String idccps;

                    /**
                     * Obtiene el valor de la propiedad idccps.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getIDCCPS() {
                        return idccps;
                    }

                    /**
                     * Define el valor de la propiedad idccps.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setIDCCPS(final String value) {
                        this.idccps = value;
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
                 *         &lt;element name="ID_PDS">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000003"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="ID_PARMCD">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000003"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FECHA_INIC_VAL">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="99999999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FECHA_INIC_VAL_1">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="99999999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FECHA_CRRE_VAL">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="99999999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FECHA_ECV_ACT">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="99999999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_ECV_KS">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_CD_AC">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_ORG_CD">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_ESTRCT_CD">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_CONCPT_NEG">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000002"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_MODIF_CD">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000002"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_PROF_CD">
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
                    "idpds",
                    "idparmcd",
                    "fechainicval",
                    "fechainicval1",
                    "fechacrreval",
                    "fechaecvact",
                    "codecvks",
                    "indcdac",
                    "codorgcd",
                    "codestrctcd",
                    "codconcptneg",
                    "codmodifcd",
                    "codprofcd"
                })
                public static class IPDATOSCONDV {

                    @XmlElement(name = "ID_PDS", required = true)
                    protected String idpds;
                    @XmlElement(name = "ID_PARMCD", required = true)
                    protected String idparmcd;
                    @XmlElement(name = "FECHA_INIC_VAL")
                    protected int fechainicval;
                    @XmlElement(name = "FECHA_INIC_VAL_1")
                    protected int fechainicval1;
                    @XmlElement(name = "FECHA_CRRE_VAL")
                    protected int fechacrreval;
                    @XmlElement(name = "FECHA_ECV_ACT")
                    protected int fechaecvact;
                    @XmlElement(name = "COD_ECV_KS", required = true)
                    protected String codecvks;
                    @XmlElement(name = "IND_CD_AC", required = true)
                    protected String indcdac;
                    @XmlElement(name = "COD_ORG_CD", required = true)
                    protected String codorgcd;
                    @XmlElement(name = "COD_ESTRCT_CD", required = true)
                    protected String codestrctcd;
                    @XmlElement(name = "COD_CONCPT_NEG", required = true)
                    protected String codconcptneg;
                    @XmlElement(name = "COD_MODIF_CD", required = true)
                    protected String codmodifcd;
                    @XmlElement(name = "COD_PROF_CD", required = true)
                    protected String codprofcd;

                    /**
                     * Obtiene el valor de la propiedad idpds.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getIDPDS() {
                        return idpds;
                    }

                    /**
                     * Define el valor de la propiedad idpds.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setIDPDS(final String value) {
                        this.idpds = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad idparmcd.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getIDPARMCD() {
                        return idparmcd;
                    }

                    /**
                     * Define el valor de la propiedad idparmcd.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setIDPARMCD(final String value) {
                        this.idparmcd = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad fechainicval.
                     *
                     */
                    public int getFECHAINICVAL() {
                        return fechainicval;
                    }

                    /**
                     * Define el valor de la propiedad fechainicval.
                     *
                     */
                    public void setFECHAINICVAL(final int value) {
                        this.fechainicval = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad fechainicval1.
                     *
                     */
                    public int getFECHAINICVAL1() {
                        return fechainicval1;
                    }

                    /**
                     * Define el valor de la propiedad fechainicval1.
                     *
                     */
                    public void setFECHAINICVAL1(final int value) {
                        this.fechainicval1 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad fechacrreval.
                     *
                     */
                    public int getFECHACRREVAL() {
                        return fechacrreval;
                    }

                    /**
                     * Define el valor de la propiedad fechacrreval.
                     *
                     */
                    public void setFECHACRREVAL(final int value) {
                        this.fechacrreval = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad fechaecvact.
                     *
                     */
                    public int getFECHAECVACT() {
                        return fechaecvact;
                    }

                    /**
                     * Define el valor de la propiedad fechaecvact.
                     *
                     */
                    public void setFECHAECVACT(final int value) {
                        this.fechaecvact = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codecvks.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODECVKS() {
                        return codecvks;
                    }

                    /**
                     * Define el valor de la propiedad codecvks.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODECVKS(final String value) {
                        this.codecvks = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad indcdac.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getINDCDAC() {
                        return indcdac;
                    }

                    /**
                     * Define el valor de la propiedad indcdac.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setINDCDAC(final String value) {
                        this.indcdac = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codorgcd.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODORGCD() {
                        return codorgcd;
                    }

                    /**
                     * Define el valor de la propiedad codorgcd.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODORGCD(final String value) {
                        this.codorgcd = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codestrctcd.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODESTRCTCD() {
                        return codestrctcd;
                    }

                    /**
                     * Define el valor de la propiedad codestrctcd.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODESTRCTCD(final String value) {
                        this.codestrctcd = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codconcptneg.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODCONCPTNEG() {
                        return codconcptneg;
                    }

                    /**
                     * Define el valor de la propiedad codconcptneg.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODCONCPTNEG(final String value) {
                        this.codconcptneg = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codmodifcd.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODMODIFCD() {
                        return codmodifcd;
                    }

                    /**
                     * Define el valor de la propiedad codmodifcd.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODMODIFCD(final String value) {
                        this.codmodifcd = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codprofcd.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODPROFCD() {
                        return codprofcd;
                    }

                    /**
                     * Define el valor de la propiedad codprofcd.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODPROFCD(final String value) {
                        this.codprofcd = value;
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
                 *         &lt;element name="IMP_CMSN_FIJ">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000015"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="9999999999999.99"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FOR_CAL_PER_PCAL">
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
                    "impcmsnfij",
                    "forcalperpcal"
                })
                public static class KACMSNFIJV {

                    @XmlElement(name = "IMP_CMSN_FIJ", required = true)
                    protected BigDecimal impcmsnfij;
                    @XmlElement(name = "FOR_CAL_PER_PCAL", required = true)
                    protected String forcalperpcal;

                    /**
                     * Obtiene el valor de la propiedad impcmsnfij.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getIMPCMSNFIJ() {
                        return impcmsnfij;
                    }

                    /**
                     * Define el valor de la propiedad impcmsnfij.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setIMPCMSNFIJ(final BigDecimal value) {
                        this.impcmsnfij = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad forcalperpcal.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getFORCALPERPCAL() {
                        return forcalperpcal;
                    }

                    /**
                     * Define el valor de la propiedad forcalperpcal.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setFORCALPERPCAL(final String value) {
                        this.forcalperpcal = value;
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
                 *         &lt;element name="COD_COMSN_PRCTL">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="CMSN_PCT">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000006"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="999.999"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="CMSN_MIN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000015"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="9999999999999.99"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="CMSN_MAX">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000015"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="9999999999999.99"/>
                 *               &lt;minInclusive value="0.0"/>
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
                    "codcomsnprctl",
                    "cmsnpct",
                    "cmsnmin",
                    "cmsnmax",
                    "impfranq"
                })
                public static class KACMSNPRCTLV {

                    @XmlElement(name = "COD_COMSN_PRCTL", required = true)
                    protected String codcomsnprctl;
                    @XmlElement(name = "CMSN_PCT", required = true)
                    protected BigDecimal cmsnpct;
                    @XmlElement(name = "CMSN_MIN", required = true)
                    protected BigDecimal cmsnmin;
                    @XmlElement(name = "CMSN_MAX", required = true)
                    protected BigDecimal cmsnmax;
                    @XmlElement(name = "IMP_FRANQ", required = true)
                    protected BigDecimal impfranq;

                    /**
                     * Obtiene el valor de la propiedad codcomsnprctl.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODCOMSNPRCTL() {
                        return codcomsnprctl;
                    }

                    /**
                     * Define el valor de la propiedad codcomsnprctl.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODCOMSNPRCTL(final String value) {
                        this.codcomsnprctl = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad cmsnpct.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getCMSNPCT() {
                        return cmsnpct;
                    }

                    /**
                     * Define el valor de la propiedad cmsnpct.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setCMSNPCT(final BigDecimal value) {
                        this.cmsnpct = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad cmsnmin.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getCMSNMIN() {
                        return cmsnmin;
                    }

                    /**
                     * Define el valor de la propiedad cmsnmin.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setCMSNMIN(final BigDecimal value) {
                        this.cmsnmin = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad cmsnmax.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getCMSNMAX() {
                        return cmsnmax;
                    }

                    /**
                     * Define el valor de la propiedad cmsnmax.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setCMSNMAX(final BigDecimal value) {
                        this.cmsnmax = value;
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
                 *         &lt;element name="COD_CMSN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
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
                 *         &lt;element name="COD_NUMRCO_MONEDA">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000003"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_AGRU_CLOP">
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
                    "codcmsn",
                    "idpds1",
                    "idparmcd1",
                    "codnumrcomoneda",
                    "codagruclop"
                })
                public static class KAESTRCTCMSN1V {

                    @XmlElement(name = "COD_CMSN", required = true)
                    protected String codcmsn;
                    @XmlElement(name = "ID_PDS_1", required = true)
                    protected String idpds1;
                    @XmlElement(name = "ID_PARMCD_1", required = true)
                    protected String idparmcd1;
                    @XmlElement(name = "COD_NUMRCO_MONEDA", required = true)
                    protected String codnumrcomoneda;
                    @XmlElement(name = "COD_AGRU_CLOP", required = true)
                    protected String codagruclop;

                    /**
                     * Obtiene el valor de la propiedad codcmsn.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODCMSN() {
                        return codcmsn;
                    }

                    /**
                     * Define el valor de la propiedad codcmsn.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODCMSN(final String value) {
                        this.codcmsn = value;
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
                    public void setIDPDS1(final String value) {
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
                    public void setIDPARMCD1(final String value) {
                        this.idparmcd1 = value;
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
                     * Obtiene el valor de la propiedad codagruclop.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODAGRUCLOP() {
                        return codagruclop;
                    }

                    /**
                     * Define el valor de la propiedad codagruclop.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODAGRUCLOP(final String value) {
                        this.codagruclop = value;
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
                 *         &lt;element name="COD_INT">
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
                    "idpds1",
                    "idparmcd1",
                    "codint"
                })
                public static class KAESTRCTINT1V {

                    @XmlElement(name = "ID_PDS_1", required = true)
                    protected String idpds1;
                    @XmlElement(name = "ID_PARMCD_1", required = true)
                    protected String idparmcd1;
                    @XmlElement(name = "COD_INT", required = true)
                    protected String codint;

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
                    public void setIDPDS1(final String value) {
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
                    public void setIDPARMCD1(final String value) {
                        this.idparmcd1 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codint.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODINT() {
                        return codint;
                    }

                    /**
                     * Define el valor de la propiedad codint.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODINT(final String value) {
                        this.codint = value;
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
                public static class KAESTRCTINTFIJV {

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
                 *         &lt;element name="INT_MIN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000006"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="999.999"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="INT_MAX">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000006"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="999.999"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="INT_FREC_REV">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FRACCION_REV">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000007"/>
                 *               &lt;fractionDigits value="04"/>
                 *               &lt;maxInclusive value="999.9999"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="PCT_MIN_REV">
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
                 *         &lt;element name="COD_CRIT_REV">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000002"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FORMA_REV">
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
                    "intincrem",
                    "intmin",
                    "intmax",
                    "intfrecrev",
                    "fraccionrev",
                    "pctminrev",
                    "codrefint",
                    "codcritrev",
                    "formarev"
                })
                public static class KAESTRCTINTVARV {

                    @XmlElement(name = "INT_INCREM", required = true)
                    protected BigDecimal intincrem;
                    @XmlElement(name = "INT_MIN", required = true)
                    protected BigDecimal intmin;
                    @XmlElement(name = "INT_MAX", required = true)
                    protected BigDecimal intmax;
                    @XmlElement(name = "INT_FREC_REV", required = true)
                    protected String intfrecrev;
                    @XmlElement(name = "FRACCION_REV", required = true)
                    protected BigDecimal fraccionrev;
                    @XmlElement(name = "PCT_MIN_REV", required = true)
                    protected BigDecimal pctminrev;
                    @XmlElement(name = "COD_REF_INT", required = true)
                    protected String codrefint;
                    @XmlElement(name = "COD_CRIT_REV", required = true)
                    protected String codcritrev;
                    @XmlElement(name = "FORMA_REV", required = true)
                    protected String formarev;

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
                     * Obtiene el valor de la propiedad intmin.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getINTMIN() {
                        return intmin;
                    }

                    /**
                     * Define el valor de la propiedad intmin.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setINTMIN(final BigDecimal value) {
                        this.intmin = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad intmax.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getINTMAX() {
                        return intmax;
                    }

                    /**
                     * Define el valor de la propiedad intmax.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setINTMAX(final BigDecimal value) {
                        this.intmax = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad intfrecrev.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getINTFRECREV() {
                        return intfrecrev;
                    }

                    /**
                     * Define el valor de la propiedad intfrecrev.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setINTFRECREV(final String value) {
                        this.intfrecrev = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad fraccionrev.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getFRACCIONREV() {
                        return fraccionrev;
                    }

                    /**
                     * Define el valor de la propiedad fraccionrev.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setFRACCIONREV(final BigDecimal value) {
                        this.fraccionrev = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad pctminrev.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getPCTMINREV() {
                        return pctminrev;
                    }

                    /**
                     * Define el valor de la propiedad pctminrev.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setPCTMINREV(final BigDecimal value) {
                        this.pctminrev = value;
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
                     * Obtiene el valor de la propiedad codcritrev.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODCRITREV() {
                        return codcritrev;
                    }

                    /**
                     * Define el valor de la propiedad codcritrev.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODCRITREV(final String value) {
                        this.codcritrev = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad formarev.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getFORMAREV() {
                        return formarev;
                    }

                    /**
                     * Define el valor de la propiedad formarev.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setFORMAREV(final String value) {
                        this.formarev = value;
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
                 *         &lt;element name="RNG_PREF">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000015"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="9999999999999.99"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="RNG_MIN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000015"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="9999999999999.99"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="RNG_MAX">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000015"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="9999999999999.99"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="RNG_INCREM">
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
                    "rngpref",
                    "rngmin",
                    "rngmax",
                    "rngincrem",
                    "codum"
                })
                public static class KAESTRCTRNGV {

                    @XmlElement(name = "RNG_PREF", required = true)
                    protected BigDecimal rngpref;
                    @XmlElement(name = "RNG_MIN", required = true)
                    protected BigDecimal rngmin;
                    @XmlElement(name = "RNG_MAX", required = true)
                    protected BigDecimal rngmax;
                    @XmlElement(name = "RNG_INCREM", required = true)
                    protected BigDecimal rngincrem;
                    @XmlElement(name = "COD_UM", required = true)
                    protected String codum;

                    /**
                     * Obtiene el valor de la propiedad rngpref.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getRNGPREF() {
                        return rngpref;
                    }

                    /**
                     * Define el valor de la propiedad rngpref.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setRNGPREF(final BigDecimal value) {
                        this.rngpref = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad rngmin.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getRNGMIN() {
                        return rngmin;
                    }

                    /**
                     * Define el valor de la propiedad rngmin.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setRNGMIN(final BigDecimal value) {
                        this.rngmin = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad rngmax.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getRNGMAX() {
                        return rngmax;
                    }

                    /**
                     * Define el valor de la propiedad rngmax.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setRNGMAX(final BigDecimal value) {
                        this.rngmax = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad rngincrem.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getRNGINCREM() {
                        return rngincrem;
                    }

                    /**
                     * Define el valor de la propiedad rngincrem.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setRNGINCREM(final BigDecimal value) {
                        this.rngincrem = value;
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
                 *         &lt;element name="COD_DOM_PARMCD">
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
                    "coddomparmcd"
                })
                public static class KAESTRCTVLISTAV {

                    @XmlElement(name = "COD_DOM_PARMCD", required = true)
                    protected String coddomparmcd;

                    /**
                     * Obtiene el valor de la propiedad coddomparmcd.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODDOMPARMCD() {
                        return coddomparmcd;
                    }

                    /**
                     * Define el valor de la propiedad coddomparmcd.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODDOMPARMCD(final String value) {
                        this.coddomparmcd = value;
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
                public static class KAESTRCTVRNGV {

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
                 *         &lt;element name="COD_INT">
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
                    "impfranq",
                    "codint"
                })
                public static class KAINTFRANQ1V {

                    @XmlElement(name = "IMP_FRANQ", required = true)
                    protected BigDecimal impfranq;
                    @XmlElement(name = "COD_INT", required = true)
                    protected String codint;

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

                    /**
                     * Obtiene el valor de la propiedad codint.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODINT() {
                        return codint;
                    }

                    /**
                     * Define el valor de la propiedad codint.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODINT(final String value) {
                        this.codint = value;
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
                public static class KAINTFRANQFIJV {

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
                 *         &lt;element name="INT_MIN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000006"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="999.999"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="INT_MAX">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000006"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="999.999"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="INT_FREC_REV">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FRACCION_REV">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000007"/>
                 *               &lt;fractionDigits value="04"/>
                 *               &lt;maxInclusive value="999.9999"/>
                 *               &lt;minInclusive value="0.0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="PCT_MIN_REV">
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
                 *         &lt;element name="COD_CRIT_REV">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000002"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FORMA_REV">
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
                    "intincrem",
                    "intmin",
                    "intmax",
                    "intfrecrev",
                    "fraccionrev",
                    "pctminrev",
                    "codrefint",
                    "codcritrev",
                    "formarev"
                })
                public static class KAINTFRANQVARV {

                    @XmlElement(name = "INT_INCREM", required = true)
                    protected BigDecimal intincrem;
                    @XmlElement(name = "INT_MIN", required = true)
                    protected BigDecimal intmin;
                    @XmlElement(name = "INT_MAX", required = true)
                    protected BigDecimal intmax;
                    @XmlElement(name = "INT_FREC_REV", required = true)
                    protected String intfrecrev;
                    @XmlElement(name = "FRACCION_REV", required = true)
                    protected BigDecimal fraccionrev;
                    @XmlElement(name = "PCT_MIN_REV", required = true)
                    protected BigDecimal pctminrev;
                    @XmlElement(name = "COD_REF_INT", required = true)
                    protected String codrefint;
                    @XmlElement(name = "COD_CRIT_REV", required = true)
                    protected String codcritrev;
                    @XmlElement(name = "FORMA_REV", required = true)
                    protected String formarev;

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
                     * Obtiene el valor de la propiedad intmin.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getINTMIN() {
                        return intmin;
                    }

                    /**
                     * Define el valor de la propiedad intmin.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setINTMIN(final BigDecimal value) {
                        this.intmin = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad intmax.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getINTMAX() {
                        return intmax;
                    }

                    /**
                     * Define el valor de la propiedad intmax.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setINTMAX(final BigDecimal value) {
                        this.intmax = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad intfrecrev.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getINTFRECREV() {
                        return intfrecrev;
                    }

                    /**
                     * Define el valor de la propiedad intfrecrev.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setINTFRECREV(final String value) {
                        this.intfrecrev = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad fraccionrev.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getFRACCIONREV() {
                        return fraccionrev;
                    }

                    /**
                     * Define el valor de la propiedad fraccionrev.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setFRACCIONREV(final BigDecimal value) {
                        this.fraccionrev = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad pctminrev.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getPCTMINREV() {
                        return pctminrev;
                    }

                    /**
                     * Define el valor de la propiedad pctminrev.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setPCTMINREV(final BigDecimal value) {
                        this.pctminrev = value;
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
                     * Obtiene el valor de la propiedad codcritrev.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODCRITREV() {
                        return codcritrev;
                    }

                    /**
                     * Define el valor de la propiedad codcritrev.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODCRITREV(final String value) {
                        this.codcritrev = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad formarev.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getFORMAREV() {
                        return formarev;
                    }

                    /**
                     * Define el valor de la propiedad formarev.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setFORMAREV(final String value) {
                        this.formarev = value;
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
                 *         &lt;element name="COD_CLASE_SP">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000003"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="ID_SP_NUM">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="9999"/>
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
                    "codclasesp",
                    "idspnum"
                })
                public static class KAINTRLSPV {

                    @XmlElement(name = "COD_CLASE_SP", required = true)
                    protected String codclasesp;
                    @XmlElement(name = "ID_SP_NUM")
                    protected int idspnum;

                    /**
                     * Obtiene el valor de la propiedad codclasesp.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODCLASESP() {
                        return codclasesp;
                    }

                    /**
                     * Define el valor de la propiedad codclasesp.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODCLASESP(final String value) {
                        this.codclasesp = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad idspnum.
                     *
                     */
                    public int getIDSPNUM() {
                        return idspnum;
                    }

                    /**
                     * Define el valor de la propiedad idspnum.
                     *
                     */
                    public void setIDSPNUM(final int value) {
                        this.idspnum = value;
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
                 *         &lt;element name="COD_DOM_PARMCD">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000002"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="DOM_IND_PREF">
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
                    "coddomparmcd",
                    "domindpref"
                })
                public static class KAKARLPKDOMV {

                    @XmlElement(name = "COD_DOM_PARMCD", required = true)
                    protected String coddomparmcd;
                    @XmlElement(name = "DOM_IND_PREF", required = true)
                    protected String domindpref;

                    /**
                     * Obtiene el valor de la propiedad coddomparmcd.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODDOMPARMCD() {
                        return coddomparmcd;
                    }

                    /**
                     * Define el valor de la propiedad coddomparmcd.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODDOMPARMCD(final String value) {
                        this.coddomparmcd = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad domindpref.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getDOMINDPREF() {
                        return domindpref;
                    }

                    /**
                     * Define el valor de la propiedad domindpref.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setDOMINDPREF(final String value) {
                        this.domindpref = value;
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
                 *         &lt;element name="ID_TRAMEADO">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000010"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_FOR_APLC_TR">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
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
                 *         &lt;element name="COD_AGRU_CLOP">
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
                    "idtrameado",
                    "codforaplctr",
                    "idpds1",
                    "idparmcd1",
                    "codagruclop"
                })
                public static class KAMATRIZV {

                    @XmlElement(name = "ID_TRAMEADO", required = true)
                    protected String idtrameado;
                    @XmlElement(name = "COD_FOR_APLC_TR", required = true)
                    protected String codforaplctr;
                    @XmlElement(name = "ID_PDS_1", required = true)
                    protected String idpds1;
                    @XmlElement(name = "ID_PARMCD_1", required = true)
                    protected String idparmcd1;
                    @XmlElement(name = "COD_AGRU_CLOP", required = true)
                    protected String codagruclop;

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
                     * Obtiene el valor de la propiedad codforaplctr.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODFORAPLCTR() {
                        return codforaplctr;
                    }

                    /**
                     * Define el valor de la propiedad codforaplctr.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODFORAPLCTR(final String value) {
                        this.codforaplctr = value;
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
                    public void setIDPDS1(final String value) {
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
                    public void setIDPARMCD1(final String value) {
                        this.idparmcd1 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codagruclop.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODAGRUCLOP() {
                        return codagruclop;
                    }

                    /**
                     * Define el valor de la propiedad codagruclop.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODAGRUCLOP(final String value) {
                        this.codagruclop = value;
                    }

                }

            }

        }

    }

}
