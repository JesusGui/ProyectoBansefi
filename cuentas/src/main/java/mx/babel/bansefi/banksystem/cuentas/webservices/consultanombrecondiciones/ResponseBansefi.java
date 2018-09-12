
package mx.babel.bansefi.banksystem.cuentas.webservices.consultanombrecondiciones;

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
 *         &lt;element name="OTR_CONS_GLOBAL_KP_RL_TS">
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
 *                   &lt;element name="TR_CONS_GLOBAL_KP_RL_TS_E">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="TR_CONS_GLOBAL_KP_RL_TS_E" maxOccurs="30">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="KP_INDICADOR_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="IND_MIN_MAX">
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
 *                                       &lt;element name="KP_NOMB_PARMCD_V" maxOccurs="30">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="NOMB_PARMCD">
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
 *                                       &lt;element name="KP_NOMB_DOMINIO_V" maxOccurs="30">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="TXT_DOM_PARMCD">
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
 *                                       &lt;element name="KP_DATOS_CD_PD_V" maxOccurs="30">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
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
 *                                                 &lt;element name="COD_ESTRCT_PARMCD">
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
 *                                                 &lt;element name="COD_ECV_KP">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000002"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_NEGOC_HAB">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
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
 *                                                 &lt;element name="IND_KP">
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
 *                                       &lt;element name="KP_DEPENDEN_PARMCD_V" maxOccurs="30">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="IND_DEPENDC">
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
 *                                       &lt;element name="KP_ID_PK_V" maxOccurs="30">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="ID_PARMCD">
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
 *                                       &lt;element name="KP_RNG_SMPL_V" maxOccurs="30">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="RNG_MIN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="RNG_MAX">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="RNG_PREF">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="RNG_INCREM">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
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
 *                                       &lt;element name="KP_LISTA_SMPL_V" maxOccurs="30">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="ID_PARMCD_1">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000003"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
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
 *                                       &lt;element name="KP_CMSN_SMPL_V" maxOccurs="30">
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
 *                                                 &lt;element name="COD_AGRU_CLOP">
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
 *                                                 &lt;element name="FIJ_MAX">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FIJ_INCREM">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FIJ_MIN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FIJ_PREF">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
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
 *                                                 &lt;element name="COD_COMSN_PRCTL">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="PRCTL_INCREM">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="PRCTL_MAX">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="PRCTL_MIN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="PRCTL_PREF">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IMP_VAL_FRQC">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KP_IMP_CMSN_MAX">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000014"/>
 *                                                       &lt;fractionDigits value="02"/>
 *                                                       &lt;maxInclusive value="999999999999.99"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KP_IMP_CMSN_MIN">
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
 *                                       &lt;element name="KP_INT_SMPL_V" maxOccurs="30">
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
 *                                                 &lt;element name="KP_INT_MIN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KP_INT_MAX">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KP_INT_PREF">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KP_INT_INCREM">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FRACCION_REV">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000006"/>
 *                                                       &lt;fractionDigits value="04"/>
 *                                                       &lt;maxInclusive value="99.9999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KP_INT_INCREM_VR">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KP_INT_TOTAL_MIN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KP_INT_TOTAL_MAX">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="PCT_MIN_REV">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KP_INT_DIF_MIN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KP_INT_DIF_MAX">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KP_INT_DIF_PREF">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                       &lt;totalDigits value="0000000005"/>
 *                                                       &lt;fractionDigits value="03"/>
 *                                                       &lt;maxInclusive value="99.999"/>
 *                                                       &lt;minInclusive value="0"/>
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
 *                                                 &lt;element name="COD_REF_INT">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000004"/>
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
 *                                                 &lt;element name="FREQ_REV_INT_VAR">
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
 *                                       &lt;element name="KP_ID_TRMD_V" maxOccurs="30">
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
 *                                                 &lt;element name="COD_AGRU_CLOP">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000002"/>
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
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="KP_CONTROL_TBL_V" maxOccurs="30">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="COD_ESTRCT_PARMCD">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_CMSN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
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
 *                                                 &lt;element name="IND_FRQC">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="IND_KP_INT_ESP">
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
 *                             &lt;element name="TS_CCPS_E">
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
 *                                       &lt;element name="ID_PDS">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000003"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="ID_CCPS">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000003"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_TP_TARIFA">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_NRBE_EN_1">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000004"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_ECV_CCPS">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
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
 *                                       &lt;element name="NOMB_CCPS">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000030"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="DESCR_CCPS">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000060"/>
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
 *                             &lt;element name="PS_PDS_E">
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
 *                                       &lt;element name="NOMB_PDS">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000030"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="DESCR_PDS">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000060"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="IND_ACTV">
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
    "otrconsglobalkprlts"
})
public class ResponseBansefi {

    @XmlElement(name = "OTR_CONS_GLOBAL_KP_RL_TS", required = true)
    protected ResponseBansefi.OTRCONSGLOBALKPRLTS otrconsglobalkprlts;

    /**
     * Obtiene el valor de la propiedad otrconsglobalkprlts.
     *
     * @return
     *     possible object is
     *     {@link ResponseBansefi.OTRCONSGLOBALKPRLTS }
     *
     */
    public ResponseBansefi.OTRCONSGLOBALKPRLTS getOTRCONSGLOBALKPRLTS() {
        return otrconsglobalkprlts;
    }

    /**
     * Define el valor de la propiedad otrconsglobalkprlts.
     *
     * @param value
     *     allowed object is
     *     {@link ResponseBansefi.OTRCONSGLOBALKPRLTS }
     *
     */
    public void setOTRCONSGLOBALKPRLTS(final ResponseBansefi.OTRCONSGLOBALKPRLTS value) {
        this.otrconsglobalkprlts = value;
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
     *         &lt;element name="TR_CONS_GLOBAL_KP_RL_TS_E">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="TR_CONS_GLOBAL_KP_RL_TS_E" maxOccurs="30">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="KP_INDICADOR_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="IND_MIN_MAX">
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
     *                             &lt;element name="KP_NOMB_PARMCD_V" maxOccurs="30">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="NOMB_PARMCD">
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
     *                             &lt;element name="KP_NOMB_DOMINIO_V" maxOccurs="30">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="TXT_DOM_PARMCD">
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
     *                             &lt;element name="KP_DATOS_CD_PD_V" maxOccurs="30">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
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
     *                                       &lt;element name="COD_ESTRCT_PARMCD">
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
     *                                       &lt;element name="COD_ECV_KP">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000002"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_NEGOC_HAB">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
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
     *                                       &lt;element name="IND_KP">
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
     *                             &lt;element name="KP_DEPENDEN_PARMCD_V" maxOccurs="30">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="IND_DEPENDC">
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
     *                             &lt;element name="KP_ID_PK_V" maxOccurs="30">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="ID_PARMCD">
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
     *                             &lt;element name="KP_RNG_SMPL_V" maxOccurs="30">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="RNG_MIN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="RNG_MAX">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="RNG_PREF">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="RNG_INCREM">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
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
     *                             &lt;element name="KP_LISTA_SMPL_V" maxOccurs="30">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="ID_PARMCD_1">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000003"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
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
     *                             &lt;element name="KP_CMSN_SMPL_V" maxOccurs="30">
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
     *                                       &lt;element name="COD_AGRU_CLOP">
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
     *                                       &lt;element name="FIJ_MAX">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FIJ_INCREM">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FIJ_MIN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FIJ_PREF">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
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
     *                                       &lt;element name="COD_COMSN_PRCTL">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="PRCTL_INCREM">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="PRCTL_MAX">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="PRCTL_MIN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="PRCTL_PREF">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IMP_VAL_FRQC">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="KP_IMP_CMSN_MAX">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000014"/>
     *                                             &lt;fractionDigits value="02"/>
     *                                             &lt;maxInclusive value="999999999999.99"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="KP_IMP_CMSN_MIN">
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
     *                             &lt;element name="KP_INT_SMPL_V" maxOccurs="30">
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
     *                                       &lt;element name="KP_INT_MIN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="KP_INT_MAX">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="KP_INT_PREF">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="KP_INT_INCREM">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FRACCION_REV">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000006"/>
     *                                             &lt;fractionDigits value="04"/>
     *                                             &lt;maxInclusive value="99.9999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="KP_INT_INCREM_VR">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="KP_INT_TOTAL_MIN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="KP_INT_TOTAL_MAX">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="PCT_MIN_REV">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="KP_INT_DIF_MIN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="KP_INT_DIF_MAX">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="KP_INT_DIF_PREF">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                             &lt;totalDigits value="0000000005"/>
     *                                             &lt;fractionDigits value="03"/>
     *                                             &lt;maxInclusive value="99.999"/>
     *                                             &lt;minInclusive value="0"/>
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
     *                                       &lt;element name="COD_REF_INT">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000004"/>
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
     *                                       &lt;element name="FREQ_REV_INT_VAR">
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
     *                             &lt;element name="KP_ID_TRMD_V" maxOccurs="30">
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
     *                                       &lt;element name="COD_AGRU_CLOP">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000002"/>
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
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="KP_CONTROL_TBL_V" maxOccurs="30">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="COD_ESTRCT_PARMCD">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_CMSN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
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
     *                                       &lt;element name="IND_FRQC">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="IND_KP_INT_ESP">
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
     *                   &lt;element name="TS_CCPS_E">
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
     *                             &lt;element name="ID_PDS">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000003"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="ID_CCPS">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000003"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_TP_TARIFA">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_NRBE_EN_1">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000004"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_ECV_CCPS">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
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
     *                             &lt;element name="NOMB_CCPS">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000030"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="DESCR_CCPS">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000060"/>
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
     *                   &lt;element name="PS_PDS_E">
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
     *                             &lt;element name="NOMB_PDS">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000030"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="DESCR_PDS">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000060"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="IND_ACTV">
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
        "numberofrecords",
        "moredatain",
        "trconsglobalkprltse",
        "stdtrnmsjparmv",
        "stdtrnoparmv"
    })
    public static class OTRCONSGLOBALKPRLTS {

        @XmlElement(name = "RTRN_CD")
        protected int rtrncd;
        @XmlElement(name = "NUMBER_OF_RECORDS")
        protected int numberofrecords;
        @XmlElement(name = "MORE_DATA_IN")
        protected int moredatain;
        @XmlElement(name = "TR_CONS_GLOBAL_KP_RL_TS_E", required = true)
        protected ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE trconsglobalkprltse;
        @XmlElement(name = "STD_TRN_MSJ_PARM_V", required = true)
        protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.STDTRNMSJPARMV> stdtrnmsjparmv;
        @XmlElement(name = "STD_TRN_O_PARM_V", required = true)
        protected ResponseBansefi.OTRCONSGLOBALKPRLTS.STDTRNOPARMV stdtrnoparmv;

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
         * Obtiene el valor de la propiedad trconsglobalkprltse.
         *
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE }
         *
         */
        public ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE getTRCONSGLOBALKPRLTSE() {
            return trconsglobalkprltse;
        }

        /**
         * Define el valor de la propiedad trconsglobalkprltse.
         *
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE }
         *
         */
        public void setTRCONSGLOBALKPRLTSE(final ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE value) {
            this.trconsglobalkprltse = value;
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
         * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.STDTRNMSJPARMV }
         *
         *
         */
        public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.STDTRNMSJPARMV> getSTDTRNMSJPARMV() {
            if (stdtrnmsjparmv == null) {
                stdtrnmsjparmv = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.STDTRNMSJPARMV>();
            }
            return this.stdtrnmsjparmv;
        }

        /**
         * Obtiene el valor de la propiedad stdtrnoparmv.
         *
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.STDTRNOPARMV }
         *
         */
        public ResponseBansefi.OTRCONSGLOBALKPRLTS.STDTRNOPARMV getSTDTRNOPARMV() {
            return stdtrnoparmv;
        }

        /**
         * Define el valor de la propiedad stdtrnoparmv.
         *
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.STDTRNOPARMV }
         *
         */
        public void setSTDTRNOPARMV(final ResponseBansefi.OTRCONSGLOBALKPRLTS.STDTRNOPARMV value) {
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
         *         &lt;element name="TR_CONS_GLOBAL_KP_RL_TS_E" maxOccurs="30">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="KP_INDICADOR_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="IND_MIN_MAX">
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
         *                   &lt;element name="KP_NOMB_PARMCD_V" maxOccurs="30">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="NOMB_PARMCD">
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
         *                   &lt;element name="KP_NOMB_DOMINIO_V" maxOccurs="30">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="TXT_DOM_PARMCD">
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
         *                   &lt;element name="KP_DATOS_CD_PD_V" maxOccurs="30">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
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
         *                             &lt;element name="COD_ESTRCT_PARMCD">
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
         *                             &lt;element name="COD_ECV_KP">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000002"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_NEGOC_HAB">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
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
         *                             &lt;element name="IND_KP">
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
         *                   &lt;element name="KP_DEPENDEN_PARMCD_V" maxOccurs="30">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="IND_DEPENDC">
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
         *                   &lt;element name="KP_ID_PK_V" maxOccurs="30">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="ID_PARMCD">
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
         *                   &lt;element name="KP_RNG_SMPL_V" maxOccurs="30">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="RNG_MIN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="RNG_MAX">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="RNG_PREF">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="RNG_INCREM">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
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
         *                   &lt;element name="KP_LISTA_SMPL_V" maxOccurs="30">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="ID_PARMCD_1">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000003"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
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
         *                   &lt;element name="KP_CMSN_SMPL_V" maxOccurs="30">
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
         *                             &lt;element name="COD_AGRU_CLOP">
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
         *                             &lt;element name="FIJ_MAX">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FIJ_INCREM">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FIJ_MIN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FIJ_PREF">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
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
         *                             &lt;element name="COD_COMSN_PRCTL">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="PRCTL_INCREM">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="PRCTL_MAX">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="PRCTL_MIN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="PRCTL_PREF">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IMP_VAL_FRQC">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="KP_IMP_CMSN_MAX">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000014"/>
         *                                   &lt;fractionDigits value="02"/>
         *                                   &lt;maxInclusive value="999999999999.99"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="KP_IMP_CMSN_MIN">
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
         *                   &lt;element name="KP_INT_SMPL_V" maxOccurs="30">
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
         *                             &lt;element name="KP_INT_MIN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="KP_INT_MAX">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="KP_INT_PREF">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="KP_INT_INCREM">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FRACCION_REV">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000006"/>
         *                                   &lt;fractionDigits value="04"/>
         *                                   &lt;maxInclusive value="99.9999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="KP_INT_INCREM_VR">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="KP_INT_TOTAL_MIN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="KP_INT_TOTAL_MAX">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="PCT_MIN_REV">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="KP_INT_DIF_MIN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="KP_INT_DIF_MAX">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="KP_INT_DIF_PREF">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                   &lt;totalDigits value="0000000005"/>
         *                                   &lt;fractionDigits value="03"/>
         *                                   &lt;maxInclusive value="99.999"/>
         *                                   &lt;minInclusive value="0"/>
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
         *                             &lt;element name="COD_REF_INT">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000004"/>
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
         *                             &lt;element name="FREQ_REV_INT_VAR">
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
         *                   &lt;element name="KP_ID_TRMD_V" maxOccurs="30">
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
         *                             &lt;element name="COD_AGRU_CLOP">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000002"/>
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
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="KP_CONTROL_TBL_V" maxOccurs="30">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="COD_ESTRCT_PARMCD">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_CMSN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
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
         *                             &lt;element name="IND_FRQC">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="IND_KP_INT_ESP">
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
         *         &lt;element name="TS_CCPS_E">
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
         *                   &lt;element name="ID_PDS">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000003"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="ID_CCPS">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000003"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_TP_TARIFA">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_NRBE_EN_1">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000004"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_ECV_CCPS">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
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
         *                   &lt;element name="NOMB_CCPS">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000030"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="DESCR_CCPS">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000060"/>
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
         *         &lt;element name="PS_PDS_E">
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
         *                   &lt;element name="NOMB_PDS">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000030"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="DESCR_PDS">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000060"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="IND_ACTV">
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
            "trconsglobalkprltse",
            "tsccpse",
            "pspdse"
        })
        public static class TRCONSGLOBALKPRLTSE {

            @XmlElement(name = "TR_CONS_GLOBAL_KP_RL_TS_E", required = true)
            protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1> trconsglobalkprltse;
            @XmlElement(name = "TS_CCPS_E", required = true)
            protected ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TSCCPSE tsccpse;
            @XmlElement(name = "PS_PDS_E", required = true)
            protected ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.PSPDSE pspdse;

            /**
             * Gets the value of the trconsglobalkprltse property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the trconsglobalkprltse property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getTRCONSGLOBALKPRLTSE().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1 }
             *
             *
             */
            public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1> getTRCONSGLOBALKPRLTSE() {
                if (trconsglobalkprltse == null) {
                    trconsglobalkprltse = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1>();
                }
                return this.trconsglobalkprltse;
            }

            /**
             * Obtiene el valor de la propiedad tsccpse.
             *
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TSCCPSE }
             *
             */
            public ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TSCCPSE getTSCCPSE() {
                return tsccpse;
            }

            /**
             * Define el valor de la propiedad tsccpse.
             *
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TSCCPSE }
             *
             */
            public void setTSCCPSE(final ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TSCCPSE value) {
                this.tsccpse = value;
            }

            /**
             * Obtiene el valor de la propiedad pspdse.
             *
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.PSPDSE }
             *
             */
            public ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.PSPDSE getPSPDSE() {
                return pspdse;
            }

            /**
             * Define el valor de la propiedad pspdse.
             *
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.PSPDSE }
             *
             */
            public void setPSPDSE(final ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.PSPDSE value) {
                this.pspdse = value;
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
             *         &lt;element name="NOMB_PDS">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000030"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="DESCR_PDS">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000060"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="IND_ACTV">
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
                "idpds",
                "nombpds",
                "descrpds",
                "indactv"
            })
            public static class PSPDSE {

                @XmlElement(name = "ID_PDS", required = true)
                protected String idpds;
                @XmlElement(name = "NOMB_PDS", required = true)
                protected String nombpds;
                @XmlElement(name = "DESCR_PDS", required = true)
                protected String descrpds;
                @XmlElement(name = "IND_ACTV", required = true)
                protected String indactv;

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
                 * Obtiene el valor de la propiedad nombpds.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getNOMBPDS() {
                    return nombpds;
                }

                /**
                 * Define el valor de la propiedad nombpds.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setNOMBPDS(final String value) {
                    this.nombpds = value;
                }

                /**
                 * Obtiene el valor de la propiedad descrpds.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getDESCRPDS() {
                    return descrpds;
                }

                /**
                 * Define el valor de la propiedad descrpds.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setDESCRPDS(final String value) {
                    this.descrpds = value;
                }

                /**
                 * Obtiene el valor de la propiedad indactv.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getINDACTV() {
                    return indactv;
                }

                /**
                 * Define el valor de la propiedad indactv.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setINDACTV(final String value) {
                    this.indactv = value;
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
             *         &lt;element name="KP_INDICADOR_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="IND_MIN_MAX">
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
             *         &lt;element name="KP_NOMB_PARMCD_V" maxOccurs="30">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="NOMB_PARMCD">
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
             *         &lt;element name="KP_NOMB_DOMINIO_V" maxOccurs="30">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="TXT_DOM_PARMCD">
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
             *         &lt;element name="KP_DATOS_CD_PD_V" maxOccurs="30">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
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
             *                   &lt;element name="COD_ESTRCT_PARMCD">
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
             *                   &lt;element name="COD_ECV_KP">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000002"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_NEGOC_HAB">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
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
             *                   &lt;element name="IND_KP">
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
             *         &lt;element name="KP_DEPENDEN_PARMCD_V" maxOccurs="30">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="IND_DEPENDC">
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
             *         &lt;element name="KP_ID_PK_V" maxOccurs="30">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="ID_PARMCD">
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
             *         &lt;element name="KP_RNG_SMPL_V" maxOccurs="30">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="RNG_MIN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="RNG_MAX">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="RNG_PREF">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="RNG_INCREM">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
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
             *         &lt;element name="KP_LISTA_SMPL_V" maxOccurs="30">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="ID_PARMCD_1">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000003"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
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
             *         &lt;element name="KP_CMSN_SMPL_V" maxOccurs="30">
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
             *                   &lt;element name="COD_AGRU_CLOP">
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
             *                   &lt;element name="FIJ_MAX">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FIJ_INCREM">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FIJ_MIN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FIJ_PREF">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
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
             *                   &lt;element name="COD_COMSN_PRCTL">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="PRCTL_INCREM">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="PRCTL_MAX">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="PRCTL_MIN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="PRCTL_PREF">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IMP_VAL_FRQC">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="KP_IMP_CMSN_MAX">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000014"/>
             *                         &lt;fractionDigits value="02"/>
             *                         &lt;maxInclusive value="999999999999.99"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="KP_IMP_CMSN_MIN">
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
             *         &lt;element name="KP_INT_SMPL_V" maxOccurs="30">
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
             *                   &lt;element name="KP_INT_MIN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="KP_INT_MAX">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="KP_INT_PREF">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="KP_INT_INCREM">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FRACCION_REV">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000006"/>
             *                         &lt;fractionDigits value="04"/>
             *                         &lt;maxInclusive value="99.9999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="KP_INT_INCREM_VR">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="KP_INT_TOTAL_MIN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="KP_INT_TOTAL_MAX">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="PCT_MIN_REV">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="KP_INT_DIF_MIN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="KP_INT_DIF_MAX">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="KP_INT_DIF_PREF">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                         &lt;totalDigits value="0000000005"/>
             *                         &lt;fractionDigits value="03"/>
             *                         &lt;maxInclusive value="99.999"/>
             *                         &lt;minInclusive value="0"/>
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
             *                   &lt;element name="COD_REF_INT">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000004"/>
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
             *                   &lt;element name="FREQ_REV_INT_VAR">
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
             *         &lt;element name="KP_ID_TRMD_V" maxOccurs="30">
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
             *                   &lt;element name="COD_AGRU_CLOP">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000002"/>
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
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="KP_CONTROL_TBL_V" maxOccurs="30">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="COD_ESTRCT_PARMCD">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_CMSN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
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
             *                   &lt;element name="IND_FRQC">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="IND_KP_INT_ESP">
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
                "kpindicadorv",
                "kpnombparmcdv",
                "kpnombdominiov",
                "kpdatoscdpdv",
                "kpdependenparmcdv",
                "kpidpkv",
                "kprngsmplv",
                "kplistasmplv",
                "kpcmsnsmplv",
                "kpintsmplv",
                "kpidtrmdv",
                "kpcontroltblv"
            })
            public static class TRCONSGLOBALKPRLTSE1 {

                @XmlElement(name = "KP_INDICADOR_V", required = true)
                protected ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPINDICADORV kpindicadorv;
                @XmlElement(name = "KP_NOMB_PARMCD_V", required = true)
                protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPNOMBPARMCDV> kpnombparmcdv;
                @XmlElement(name = "KP_NOMB_DOMINIO_V", required = true)
                protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPNOMBDOMINIOV> kpnombdominiov;
                @XmlElement(name = "KP_DATOS_CD_PD_V", required = true)
                protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPDATOSCDPDV> kpdatoscdpdv;
                @XmlElement(name = "KP_DEPENDEN_PARMCD_V", required = true)
                protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPDEPENDENPARMCDV> kpdependenparmcdv;
                @XmlElement(name = "KP_ID_PK_V", required = true)
                protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPIDPKV> kpidpkv;
                @XmlElement(name = "KP_RNG_SMPL_V", required = true)
                protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPRNGSMPLV> kprngsmplv;
                @XmlElement(name = "KP_LISTA_SMPL_V", required = true)
                protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPLISTASMPLV> kplistasmplv;
                @XmlElement(name = "KP_CMSN_SMPL_V", required = true)
                protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPCMSNSMPLV> kpcmsnsmplv;
                @XmlElement(name = "KP_INT_SMPL_V", required = true)
                protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPINTSMPLV> kpintsmplv;
                @XmlElement(name = "KP_ID_TRMD_V", required = true)
                protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPIDTRMDV> kpidtrmdv;
                @XmlElement(name = "KP_CONTROL_TBL_V", required = true)
                protected List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPCONTROLTBLV> kpcontroltblv;

                /**
                 * Obtiene el valor de la propiedad kpindicadorv.
                 *
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE.KPINDICADORV }
                 *
                 */
                public ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPINDICADORV getKPINDICADORV() {
                    return kpindicadorv;
                }

                /**
                 * Define el valor de la propiedad kpindicadorv.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE.KPINDICADORV }
                 *
                 */
                public void setKPINDICADORV(final ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPINDICADORV value) {
                    this.kpindicadorv = value;
                }

                /**
                 * Gets the value of the kpnombparmcdv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kpnombparmcdv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKPNOMBPARMCDV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE.KPNOMBPARMCDV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPNOMBPARMCDV> getKPNOMBPARMCDV() {
                    if (kpnombparmcdv == null) {
                        kpnombparmcdv = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPNOMBPARMCDV>();
                    }
                    return this.kpnombparmcdv;
                }

                /**
                 * Gets the value of the kpnombdominiov property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kpnombdominiov property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKPNOMBDOMINIOV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPNOMBDOMINIOV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPNOMBDOMINIOV> getKPNOMBDOMINIOV() {
                    if (kpnombdominiov == null) {
                        kpnombdominiov = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPNOMBDOMINIOV>();
                    }
                    return this.kpnombdominiov;
                }

                /**
                 * Gets the value of the kpdatoscdpdv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kpdatoscdpdv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKPDATOSCDPDV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPDATOSCDPDV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPDATOSCDPDV> getKPDATOSCDPDV() {
                    if (kpdatoscdpdv == null) {
                        kpdatoscdpdv = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPDATOSCDPDV>();
                    }
                    return this.kpdatoscdpdv;
                }

                /**
                 * Gets the value of the kpdependenparmcdv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kpdependenparmcdv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKPDEPENDENPARMCDV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPDEPENDENPARMCDV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPDEPENDENPARMCDV> getKPDEPENDENPARMCDV() {
                    if (kpdependenparmcdv == null) {
                        kpdependenparmcdv = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPDEPENDENPARMCDV>();
                    }
                    return this.kpdependenparmcdv;
                }

                /**
                 * Gets the value of the kpidpkv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kpidpkv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKPIDPKV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPIDPKV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPIDPKV> getKPIDPKV() {
                    if (kpidpkv == null) {
                        kpidpkv = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPIDPKV>();
                    }
                    return this.kpidpkv;
                }

                /**
                 * Gets the value of the kprngsmplv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kprngsmplv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKPRNGSMPLV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPRNGSMPLV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPRNGSMPLV> getKPRNGSMPLV() {
                    if (kprngsmplv == null) {
                        kprngsmplv = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPRNGSMPLV>();
                    }
                    return this.kprngsmplv;
                }

                /**
                 * Gets the value of the kplistasmplv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kplistasmplv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKPLISTASMPLV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPLISTASMPLV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPLISTASMPLV> getKPLISTASMPLV() {
                    if (kplistasmplv == null) {
                        kplistasmplv = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPLISTASMPLV>();
                    }
                    return this.kplistasmplv;
                }

                /**
                 * Gets the value of the kpcmsnsmplv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kpcmsnsmplv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKPCMSNSMPLV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPCMSNSMPLV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPCMSNSMPLV> getKPCMSNSMPLV() {
                    if (kpcmsnsmplv == null) {
                        kpcmsnsmplv = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPCMSNSMPLV>();
                    }
                    return this.kpcmsnsmplv;
                }

                /**
                 * Gets the value of the kpintsmplv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kpintsmplv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKPINTSMPLV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPINTSMPLV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPINTSMPLV> getKPINTSMPLV() {
                    if (kpintsmplv == null) {
                        kpintsmplv = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPINTSMPLV>();
                    }
                    return this.kpintsmplv;
                }

                /**
                 * Gets the value of the kpidtrmdv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kpidtrmdv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKPIDTRMDV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPIDTRMDV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPIDTRMDV> getKPIDTRMDV() {
                    if (kpidtrmdv == null) {
                        kpidtrmdv = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPIDTRMDV>();
                    }
                    return this.kpidtrmdv;
                }

                /**
                 * Gets the value of the kpcontroltblv property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kpcontroltblv property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKPCONTROLTBLV().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPCONTROLTBLV }
                 *
                 *
                 */
                public List<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPCONTROLTBLV> getKPCONTROLTBLV() {
                    if (kpcontroltblv == null) {
                        kpcontroltblv = new ArrayList<ResponseBansefi.OTRCONSGLOBALKPRLTS.TRCONSGLOBALKPRLTSE.TRCONSGLOBALKPRLTSE1.KPCONTROLTBLV>();
                    }
                    return this.kpcontroltblv;
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
                 *         &lt;element name="COD_AGRU_CLOP">
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
                 *         &lt;element name="FIJ_MAX">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FIJ_INCREM">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FIJ_MIN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FIJ_PREF">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
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
                 *         &lt;element name="COD_COMSN_PRCTL">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="PRCTL_INCREM">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="PRCTL_MAX">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="PRCTL_MIN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="PRCTL_PREF">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IMP_VAL_FRQC">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="KP_IMP_CMSN_MAX">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="KP_IMP_CMSN_MIN">
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
                    "idpds1",
                    "idparmcd1",
                    "codagruclop",
                    "codnumrcomoneda",
                    "fijmax",
                    "fijincrem",
                    "fijmin",
                    "fijpref",
                    "forcalperpcal",
                    "codcomsnprctl",
                    "prctlincrem",
                    "prctlmax",
                    "prctlmin",
                    "prctlpref",
                    "impvalfrqc",
                    "kpimpcmsnmax",
                    "kpimpcmsnmin"
                })
                public static class KPCMSNSMPLV {

                    @XmlElement(name = "ID_PDS_1", required = true)
                    protected String idpds1;
                    @XmlElement(name = "ID_PARMCD_1", required = true)
                    protected String idparmcd1;
                    @XmlElement(name = "COD_AGRU_CLOP", required = true)
                    protected String codagruclop;
                    @XmlElement(name = "COD_NUMRCO_MONEDA", required = true)
                    protected String codnumrcomoneda;
                    @XmlElement(name = "FIJ_MAX", required = true)
                    protected BigDecimal fijmax;
                    @XmlElement(name = "FIJ_INCREM", required = true)
                    protected BigDecimal fijincrem;
                    @XmlElement(name = "FIJ_MIN", required = true)
                    protected BigDecimal fijmin;
                    @XmlElement(name = "FIJ_PREF", required = true)
                    protected BigDecimal fijpref;
                    @XmlElement(name = "FOR_CAL_PER_PCAL", required = true)
                    protected String forcalperpcal;
                    @XmlElement(name = "COD_COMSN_PRCTL", required = true)
                    protected String codcomsnprctl;
                    @XmlElement(name = "PRCTL_INCREM", required = true)
                    protected BigDecimal prctlincrem;
                    @XmlElement(name = "PRCTL_MAX", required = true)
                    protected BigDecimal prctlmax;
                    @XmlElement(name = "PRCTL_MIN", required = true)
                    protected BigDecimal prctlmin;
                    @XmlElement(name = "PRCTL_PREF", required = true)
                    protected BigDecimal prctlpref;
                    @XmlElement(name = "IMP_VAL_FRQC", required = true)
                    protected BigDecimal impvalfrqc;
                    @XmlElement(name = "KP_IMP_CMSN_MAX", required = true)
                    protected BigDecimal kpimpcmsnmax;
                    @XmlElement(name = "KP_IMP_CMSN_MIN", required = true)
                    protected BigDecimal kpimpcmsnmin;

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
                     * Obtiene el valor de la propiedad fijmax.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getFIJMAX() {
                        return fijmax;
                    }

                    /**
                     * Define el valor de la propiedad fijmax.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setFIJMAX(final BigDecimal value) {
                        this.fijmax = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad fijincrem.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getFIJINCREM() {
                        return fijincrem;
                    }

                    /**
                     * Define el valor de la propiedad fijincrem.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setFIJINCREM(final BigDecimal value) {
                        this.fijincrem = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad fijmin.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getFIJMIN() {
                        return fijmin;
                    }

                    /**
                     * Define el valor de la propiedad fijmin.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setFIJMIN(final BigDecimal value) {
                        this.fijmin = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad fijpref.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getFIJPREF() {
                        return fijpref;
                    }

                    /**
                     * Define el valor de la propiedad fijpref.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setFIJPREF(final BigDecimal value) {
                        this.fijpref = value;
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
                     * Obtiene el valor de la propiedad prctlincrem.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getPRCTLINCREM() {
                        return prctlincrem;
                    }

                    /**
                     * Define el valor de la propiedad prctlincrem.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setPRCTLINCREM(final BigDecimal value) {
                        this.prctlincrem = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad prctlmax.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getPRCTLMAX() {
                        return prctlmax;
                    }

                    /**
                     * Define el valor de la propiedad prctlmax.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setPRCTLMAX(final BigDecimal value) {
                        this.prctlmax = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad prctlmin.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getPRCTLMIN() {
                        return prctlmin;
                    }

                    /**
                     * Define el valor de la propiedad prctlmin.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setPRCTLMIN(final BigDecimal value) {
                        this.prctlmin = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad prctlpref.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getPRCTLPREF() {
                        return prctlpref;
                    }

                    /**
                     * Define el valor de la propiedad prctlpref.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setPRCTLPREF(final BigDecimal value) {
                        this.prctlpref = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad impvalfrqc.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getIMPVALFRQC() {
                        return impvalfrqc;
                    }

                    /**
                     * Define el valor de la propiedad impvalfrqc.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setIMPVALFRQC(final BigDecimal value) {
                        this.impvalfrqc = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad kpimpcmsnmax.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getKPIMPCMSNMAX() {
                        return kpimpcmsnmax;
                    }

                    /**
                     * Define el valor de la propiedad kpimpcmsnmax.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setKPIMPCMSNMAX(final BigDecimal value) {
                        this.kpimpcmsnmax = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad kpimpcmsnmin.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getKPIMPCMSNMIN() {
                        return kpimpcmsnmin;
                    }

                    /**
                     * Define el valor de la propiedad kpimpcmsnmin.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setKPIMPCMSNMIN(final BigDecimal value) {
                        this.kpimpcmsnmin = value;
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
                 *         &lt;element name="COD_ESTRCT_PARMCD">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_CMSN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
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
                 *         &lt;element name="IND_FRQC">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_KP_INT_ESP">
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
                    "codestrctparmcd",
                    "codcmsn",
                    "codint",
                    "indfrqc",
                    "indkpintesp"
                })
                public static class KPCONTROLTBLV {

                    @XmlElement(name = "COD_ESTRCT_PARMCD", required = true)
                    protected String codestrctparmcd;
                    @XmlElement(name = "COD_CMSN", required = true)
                    protected String codcmsn;
                    @XmlElement(name = "COD_INT", required = true)
                    protected String codint;
                    @XmlElement(name = "IND_FRQC", required = true)
                    protected String indfrqc;
                    @XmlElement(name = "IND_KP_INT_ESP", required = true)
                    protected String indkpintesp;

                    /**
                     * Obtiene el valor de la propiedad codestrctparmcd.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODESTRCTPARMCD() {
                        return codestrctparmcd;
                    }

                    /**
                     * Define el valor de la propiedad codestrctparmcd.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODESTRCTPARMCD(final String value) {
                        this.codestrctparmcd = value;
                    }

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

                    /**
                     * Obtiene el valor de la propiedad indfrqc.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getINDFRQC() {
                        return indfrqc;
                    }

                    /**
                     * Define el valor de la propiedad indfrqc.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setINDFRQC(final String value) {
                        this.indfrqc = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad indkpintesp.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getINDKPINTESP() {
                        return indkpintesp;
                    }

                    /**
                     * Define el valor de la propiedad indkpintesp.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setINDKPINTESP(final String value) {
                        this.indkpintesp = value;
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
                 *         &lt;element name="COD_ESTRCT_PARMCD">
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
                 *         &lt;element name="COD_ECV_KP">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000002"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="IND_NEGOC_HAB">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
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
                 *         &lt;element name="IND_KP">
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
                    "idparmcd",
                    "fechainicval",
                    "codestrctparmcd",
                    "codorgcd",
                    "codecvkp",
                    "indnegochab",
                    "fechaecvact",
                    "indkp"
                })
                public static class KPDATOSCDPDV {

                    @XmlElement(name = "ID_PARMCD", required = true)
                    protected String idparmcd;
                    @XmlElement(name = "FECHA_INIC_VAL")
                    protected int fechainicval;
                    @XmlElement(name = "COD_ESTRCT_PARMCD", required = true)
                    protected String codestrctparmcd;
                    @XmlElement(name = "COD_ORG_CD", required = true)
                    protected String codorgcd;
                    @XmlElement(name = "COD_ECV_KP", required = true)
                    protected String codecvkp;
                    @XmlElement(name = "IND_NEGOC_HAB", required = true)
                    protected String indnegochab;
                    @XmlElement(name = "FECHA_ECV_ACT")
                    protected int fechaecvact;
                    @XmlElement(name = "IND_KP", required = true)
                    protected String indkp;

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
                     * Obtiene el valor de la propiedad codestrctparmcd.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODESTRCTPARMCD() {
                        return codestrctparmcd;
                    }

                    /**
                     * Define el valor de la propiedad codestrctparmcd.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODESTRCTPARMCD(final String value) {
                        this.codestrctparmcd = value;
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
                     * Obtiene el valor de la propiedad codecvkp.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getCODECVKP() {
                        return codecvkp;
                    }

                    /**
                     * Define el valor de la propiedad codecvkp.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setCODECVKP(final String value) {
                        this.codecvkp = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad indnegochab.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getINDNEGOCHAB() {
                        return indnegochab;
                    }

                    /**
                     * Define el valor de la propiedad indnegochab.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setINDNEGOCHAB(final String value) {
                        this.indnegochab = value;
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
                     * Obtiene el valor de la propiedad indkp.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getINDKP() {
                        return indkp;
                    }

                    /**
                     * Define el valor de la propiedad indkp.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setINDKP(final String value) {
                        this.indkp = value;
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
                 *         &lt;element name="IND_DEPENDC">
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
                    "inddependc"
                })
                public static class KPDEPENDENPARMCDV {

                    @XmlElement(name = "IND_DEPENDC", required = true)
                    protected String inddependc;

                    /**
                     * Obtiene el valor de la propiedad inddependc.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getINDDEPENDC() {
                        return inddependc;
                    }

                    /**
                     * Define el valor de la propiedad inddependc.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setINDDEPENDC(final String value) {
                        this.inddependc = value;
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
                 *         &lt;element name="ID_PARMCD">
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
                    "idparmcd"
                })
                public static class KPIDPKV {

                    @XmlElement(name = "ID_PARMCD", required = true)
                    protected String idparmcd;

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
                 *         &lt;element name="COD_AGRU_CLOP">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000002"/>
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
                    "codagruclop",
                    "codforaplctr"
                })
                public static class KPIDTRMDV {

                    @XmlElement(name = "ID_TRAMEADO", required = true)
                    protected String idtrameado;
                    @XmlElement(name = "COD_AGRU_CLOP", required = true)
                    protected String codagruclop;
                    @XmlElement(name = "COD_FOR_APLC_TR", required = true)
                    protected String codforaplctr;

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
                 *         &lt;element name="IND_MIN_MAX">
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
                    "indminmax"
                })
                public static class KPINDICADORV {

                    @XmlElement(name = "IND_MIN_MAX", required = true)
                    protected String indminmax;

                    /**
                     * Obtiene el valor de la propiedad indminmax.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getINDMINMAX() {
                        return indminmax;
                    }

                    /**
                     * Define el valor de la propiedad indminmax.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setINDMINMAX(final String value) {
                        this.indminmax = value;
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
                 *         &lt;element name="KP_INT_MIN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="KP_INT_MAX">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="KP_INT_PREF">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="KP_INT_INCREM">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FRACCION_REV">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000006"/>
                 *               &lt;fractionDigits value="04"/>
                 *               &lt;maxInclusive value="99.9999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="KP_INT_INCREM_VR">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="KP_INT_TOTAL_MIN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="KP_INT_TOTAL_MAX">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="PCT_MIN_REV">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="KP_INT_DIF_MIN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="KP_INT_DIF_MAX">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="KP_INT_DIF_PREF">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000005"/>
                 *               &lt;fractionDigits value="03"/>
                 *               &lt;maxInclusive value="99.999"/>
                 *               &lt;minInclusive value="0"/>
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
                 *         &lt;element name="COD_REF_INT">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000004"/>
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
                 *         &lt;element name="FREQ_REV_INT_VAR">
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
                    "kpintmin",
                    "kpintmax",
                    "kpintpref",
                    "kpintincrem",
                    "fraccionrev",
                    "kpintincremvr",
                    "kpinttotalmin",
                    "kpinttotalmax",
                    "pctminrev",
                    "kpintdifmin",
                    "kpintdifmax",
                    "kpintdifpref",
                    "codcritrev",
                    "codrefint",
                    "formarev",
                    "freqrevintvar"
                })
                public static class KPINTSMPLV {

                    @XmlElement(name = "ID_PDS_1", required = true)
                    protected String idpds1;
                    @XmlElement(name = "ID_PARMCD_1", required = true)
                    protected String idparmcd1;
                    @XmlElement(name = "KP_INT_MIN", required = true)
                    protected BigDecimal kpintmin;
                    @XmlElement(name = "KP_INT_MAX", required = true)
                    protected BigDecimal kpintmax;
                    @XmlElement(name = "KP_INT_PREF", required = true)
                    protected BigDecimal kpintpref;
                    @XmlElement(name = "KP_INT_INCREM", required = true)
                    protected BigDecimal kpintincrem;
                    @XmlElement(name = "FRACCION_REV", required = true)
                    protected BigDecimal fraccionrev;
                    @XmlElement(name = "KP_INT_INCREM_VR", required = true)
                    protected BigDecimal kpintincremvr;
                    @XmlElement(name = "KP_INT_TOTAL_MIN", required = true)
                    protected BigDecimal kpinttotalmin;
                    @XmlElement(name = "KP_INT_TOTAL_MAX", required = true)
                    protected BigDecimal kpinttotalmax;
                    @XmlElement(name = "PCT_MIN_REV", required = true)
                    protected BigDecimal pctminrev;
                    @XmlElement(name = "KP_INT_DIF_MIN", required = true)
                    protected BigDecimal kpintdifmin;
                    @XmlElement(name = "KP_INT_DIF_MAX", required = true)
                    protected BigDecimal kpintdifmax;
                    @XmlElement(name = "KP_INT_DIF_PREF", required = true)
                    protected BigDecimal kpintdifpref;
                    @XmlElement(name = "COD_CRIT_REV", required = true)
                    protected String codcritrev;
                    @XmlElement(name = "COD_REF_INT", required = true)
                    protected String codrefint;
                    @XmlElement(name = "FORMA_REV", required = true)
                    protected String formarev;
                    @XmlElement(name = "FREQ_REV_INT_VAR", required = true)
                    protected String freqrevintvar;

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
                     * Obtiene el valor de la propiedad kpintmin.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getKPINTMIN() {
                        return kpintmin;
                    }

                    /**
                     * Define el valor de la propiedad kpintmin.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setKPINTMIN(final BigDecimal value) {
                        this.kpintmin = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad kpintmax.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getKPINTMAX() {
                        return kpintmax;
                    }

                    /**
                     * Define el valor de la propiedad kpintmax.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setKPINTMAX(final BigDecimal value) {
                        this.kpintmax = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad kpintpref.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getKPINTPREF() {
                        return kpintpref;
                    }

                    /**
                     * Define el valor de la propiedad kpintpref.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setKPINTPREF(final BigDecimal value) {
                        this.kpintpref = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad kpintincrem.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getKPINTINCREM() {
                        return kpintincrem;
                    }

                    /**
                     * Define el valor de la propiedad kpintincrem.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setKPINTINCREM(final BigDecimal value) {
                        this.kpintincrem = value;
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
                     * Obtiene el valor de la propiedad kpintincremvr.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getKPINTINCREMVR() {
                        return kpintincremvr;
                    }

                    /**
                     * Define el valor de la propiedad kpintincremvr.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setKPINTINCREMVR(final BigDecimal value) {
                        this.kpintincremvr = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad kpinttotalmin.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getKPINTTOTALMIN() {
                        return kpinttotalmin;
                    }

                    /**
                     * Define el valor de la propiedad kpinttotalmin.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setKPINTTOTALMIN(final BigDecimal value) {
                        this.kpinttotalmin = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad kpinttotalmax.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getKPINTTOTALMAX() {
                        return kpinttotalmax;
                    }

                    /**
                     * Define el valor de la propiedad kpinttotalmax.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setKPINTTOTALMAX(final BigDecimal value) {
                        this.kpinttotalmax = value;
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
                     * Obtiene el valor de la propiedad kpintdifmin.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getKPINTDIFMIN() {
                        return kpintdifmin;
                    }

                    /**
                     * Define el valor de la propiedad kpintdifmin.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setKPINTDIFMIN(final BigDecimal value) {
                        this.kpintdifmin = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad kpintdifmax.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getKPINTDIFMAX() {
                        return kpintdifmax;
                    }

                    /**
                     * Define el valor de la propiedad kpintdifmax.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setKPINTDIFMAX(final BigDecimal value) {
                        this.kpintdifmax = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad kpintdifpref.
                     *
                     * @return
                     *     possible object is
                     *     {@link BigDecimal }
                     *
                     */
                    public BigDecimal getKPINTDIFPREF() {
                        return kpintdifpref;
                    }

                    /**
                     * Define el valor de la propiedad kpintdifpref.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link BigDecimal }
                     *
                     */
                    public void setKPINTDIFPREF(final BigDecimal value) {
                        this.kpintdifpref = value;
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

                    /**
                     * Obtiene el valor de la propiedad freqrevintvar.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getFREQREVINTVAR() {
                        return freqrevintvar;
                    }

                    /**
                     * Define el valor de la propiedad freqrevintvar.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setFREQREVINTVAR(final String value) {
                        this.freqrevintvar = value;
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
                 *         &lt;element name="ID_PARMCD_1">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000003"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
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
                    "idparmcd1",
                    "coddomparmcd",
                    "domindpref"
                })
                public static class KPLISTASMPLV {

                    @XmlElement(name = "ID_PARMCD_1", required = true)
                    protected String idparmcd1;
                    @XmlElement(name = "COD_DOM_PARMCD", required = true)
                    protected String coddomparmcd;
                    @XmlElement(name = "DOM_IND_PREF", required = true)
                    protected String domindpref;

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
                 *         &lt;element name="TXT_DOM_PARMCD">
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
                    "txtdomparmcd"
                })
                public static class KPNOMBDOMINIOV {

                    @XmlElement(name = "TXT_DOM_PARMCD", required = true)
                    protected String txtdomparmcd;

                    /**
                     * Obtiene el valor de la propiedad txtdomparmcd.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getTXTDOMPARMCD() {
                        return txtdomparmcd;
                    }

                    /**
                     * Define el valor de la propiedad txtdomparmcd.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setTXTDOMPARMCD(final String value) {
                        this.txtdomparmcd = value;
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
                 *         &lt;element name="NOMB_PARMCD">
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
                    "nombparmcd"
                })
                public static class KPNOMBPARMCDV {

                    @XmlElement(name = "NOMB_PARMCD", required = true)
                    protected String nombparmcd;

                    /**
                     * Obtiene el valor de la propiedad nombparmcd.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getNOMBPARMCD() {
                        return nombparmcd;
                    }

                    /**
                     * Define el valor de la propiedad nombparmcd.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setNOMBPARMCD(final String value) {
                        this.nombparmcd = value;
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
                 *         &lt;element name="RNG_MIN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="RNG_MAX">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="RNG_PREF">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="RNG_INCREM">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *               &lt;totalDigits value="0000000014"/>
                 *               &lt;fractionDigits value="02"/>
                 *               &lt;maxInclusive value="999999999999.99"/>
                 *               &lt;minInclusive value="0"/>
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
                    "rngmin",
                    "rngmax",
                    "rngpref",
                    "rngincrem",
                    "codum"
                })
                public static class KPRNGSMPLV {

                    @XmlElement(name = "RNG_MIN", required = true)
                    protected BigDecimal rngmin;
                    @XmlElement(name = "RNG_MAX", required = true)
                    protected BigDecimal rngmax;
                    @XmlElement(name = "RNG_PREF", required = true)
                    protected BigDecimal rngpref;
                    @XmlElement(name = "RNG_INCREM", required = true)
                    protected BigDecimal rngincrem;
                    @XmlElement(name = "COD_UM", required = true)
                    protected String codum;

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
             *         &lt;element name="ID_PDS">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000003"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="ID_CCPS">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000003"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_TP_TARIFA">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_NRBE_EN_1">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000004"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_ECV_CCPS">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
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
             *         &lt;element name="NOMB_CCPS">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000030"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="DESCR_CCPS">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000060"/>
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
                "codnrbeen",
                "idpds",
                "idccps",
                "codtptarifa",
                "codnrbeen1",
                "codecvccps",
                "fechaecvact",
                "nombccps",
                "descrccps",
                "codnumrcomoneda"
            })
            public static class TSCCPSE {

                @XmlElement(name = "COD_NRBE_EN", required = true)
                protected String codnrbeen;
                @XmlElement(name = "ID_PDS", required = true)
                protected String idpds;
                @XmlElement(name = "ID_CCPS", required = true)
                protected String idccps;
                @XmlElement(name = "COD_TP_TARIFA", required = true)
                protected String codtptarifa;
                @XmlElement(name = "COD_NRBE_EN_1", required = true)
                protected String codnrbeen1;
                @XmlElement(name = "COD_ECV_CCPS", required = true)
                protected String codecvccps;
                @XmlElement(name = "FECHA_ECV_ACT")
                protected int fechaecvact;
                @XmlElement(name = "NOMB_CCPS", required = true)
                protected String nombccps;
                @XmlElement(name = "DESCR_CCPS", required = true)
                protected String descrccps;
                @XmlElement(name = "COD_NUMRCO_MONEDA", required = true)
                protected String codnumrcomoneda;

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

                /**
                 * Obtiene el valor de la propiedad codtptarifa.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODTPTARIFA() {
                    return codtptarifa;
                }

                /**
                 * Define el valor de la propiedad codtptarifa.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODTPTARIFA(final String value) {
                    this.codtptarifa = value;
                }

                /**
                 * Obtiene el valor de la propiedad codnrbeen1.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODNRBEEN1() {
                    return codnrbeen1;
                }

                /**
                 * Define el valor de la propiedad codnrbeen1.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODNRBEEN1(final String value) {
                    this.codnrbeen1 = value;
                }

                /**
                 * Obtiene el valor de la propiedad codecvccps.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getCODECVCCPS() {
                    return codecvccps;
                }

                /**
                 * Define el valor de la propiedad codecvccps.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setCODECVCCPS(final String value) {
                    this.codecvccps = value;
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
                 * Obtiene el valor de la propiedad nombccps.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getNOMBCCPS() {
                    return nombccps;
                }

                /**
                 * Define el valor de la propiedad nombccps.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setNOMBCCPS(final String value) {
                    this.nombccps = value;
                }

                /**
                 * Obtiene el valor de la propiedad descrccps.
                 *
                 * @return
                 *     possible object is
                 *     {@link String }
                 *
                 */
                public String getDESCRCCPS() {
                    return descrccps;
                }

                /**
                 * Define el valor de la propiedad descrccps.
                 *
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *
                 */
                public void setDESCRCCPS(final String value) {
                    this.descrccps = value;
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

            }

        }

    }

}
