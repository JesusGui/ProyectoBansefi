
package mx.babel.bansefi.banksystem.cuentas.webservices.retrocesioncancelacionacuerdo;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="ITR_RET_CANC_TOTAL_TRN_I">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TR_RET_CANC_TOTAL_EVT_Y">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AC_AC_P">
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
 *                                       &lt;element name="NUM_SEC_AC">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="AC_CC_ASOCIADA_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="IDFR_AC_L_V">
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
 *                                                 &lt;element name="COD_CSB_OF">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000004"/>
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
 *                                                 &lt;element name="COD_DIG_CR_UO">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000002"/>
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
 *                                                 &lt;element name="COD_PLZ_BANCARIA">
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
 *                             &lt;element name="GAC_AC_HIJO_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="GAC_AC_AC_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="AC_AC_E">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="COD_NRBE_EN">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000004"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_CENT_UO">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000004"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="NUM_SEC_AC">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="9999999999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_ECV_AC">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="FECHA_INI_ECV_ACT">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="99999999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="IND_SOCIO_PE">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="SIT_RESDNC_PE">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="CTER_INT">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_ACTIVO">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_DIG_CR_UO">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_INTERNO_UO">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000004"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_CSB_OF">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000004"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_LINEA">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="ID_GRP_PD">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="ID_PDV">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000004"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="ID_TRFA_PDV">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_ORGN_AC">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_NUMRCO_MONEDA">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_SIT_RECLAM">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_SIT_IRREG">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_CNO_INDV">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000006"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_CNAE_PERS">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000007"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_SECTOR_AC">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000004"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_FNDAD_AC">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_GRTIA_AC">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_ESTICO">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000006"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_PERS_CTBLE">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_GTIA_CTBLE">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_GTIA_CIRBE">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_PLZ_BANCARIA">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="AC_AC_RL_PDS_E" maxOccurs="12">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="COD_NRBE_EN">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000004"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_CENT_UO">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000004"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="NUM_SEC_AC">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="9999999999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="ID_PDS">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="FECHA_INICIO">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="99999999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="IND_OBLIG">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="FECHA_CRRE">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="99999999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="AC_INF_DRVD_E" maxOccurs="28">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="COD_NRBE_EN">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000004"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_CENT_UO">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000004"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="NUM_SEC_AC">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="9999999999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_INF_DRVD_AC">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="VALOR_INF_DRVD">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000020"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="NUM_SUBAC_V">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="ULT_NUM_SUBAC">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="9999999"/>
 *                                                                 &lt;minInclusive value="0"/>
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
    "itrretcanctotaltrni"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USERHEADER", required = true)
    protected String userheader;
    @XmlElement(name = "PASSHEADER", required = true)
    protected String passheader;
    @XmlElement(name = "IPHEADER", required = true)
    protected String ipheader;
    @XmlElement(name = "ITR_RET_CANC_TOTAL_TRN_I", required = true)
    protected Ejecutar.ITRRETCANCTOTALTRNI itrretcanctotaltrni;

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
     * Obtiene el valor de la propiedad itrretcanctotaltrni.
     * 
     * @return
     *     possible object is
     *     {@link Ejecutar.ITRRETCANCTOTALTRNI }
     *     
     */
    public Ejecutar.ITRRETCANCTOTALTRNI getITRRETCANCTOTALTRNI() {
        return itrretcanctotaltrni;
    }

    /**
     * Define el valor de la propiedad itrretcanctotaltrni.
     * 
     * @param value
     *     allowed object is
     *     {@link Ejecutar.ITRRETCANCTOTALTRNI }
     *     
     */
    public void setITRRETCANCTOTALTRNI(Ejecutar.ITRRETCANCTOTALTRNI value) {
        this.itrretcanctotaltrni = value;
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
     *         &lt;element name="TR_RET_CANC_TOTAL_EVT_Y">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="AC_AC_P">
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
     *                             &lt;element name="NUM_SEC_AC">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="AC_CC_ASOCIADA_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="IDFR_AC_L_V">
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
     *                                       &lt;element name="COD_INTERNO_UO">
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
     *                   &lt;element name="GAC_AC_HIJO_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="GAC_AC_AC_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="AC_AC_E">
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
     *                                                 &lt;element name="NUM_SEC_AC">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                                       &lt;maxInclusive value="9999999999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_ECV_AC">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000001"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="FECHA_INI_ECV_ACT">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                                       &lt;maxInclusive value="99999999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="IND_SOCIO_PE">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000001"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="SIT_RESDNC_PE">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000001"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="CTER_INT">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000002"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_ACTIVO">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000002"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_DIG_CR_UO">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000002"/>
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
     *                                                 &lt;element name="COD_CSB_OF">
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
     *                                                 &lt;element name="ID_PDV">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000004"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="ID_TRFA_PDV">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000003"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_ORGN_AC">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000001"/>
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
     *                                                 &lt;element name="COD_SIT_RECLAM">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000001"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_SIT_IRREG">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000001"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_CNO_INDV">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000006"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_CNAE_PERS">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000007"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_SECTOR_AC">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000004"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_FNDAD_AC">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000003"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_GRTIA_AC">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000003"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_ESTICO">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000006"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_PERS_CTBLE">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000003"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_GTIA_CTBLE">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000002"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_GTIA_CIRBE">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000001"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_PLZ_BANCARIA">
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
     *                                       &lt;element name="AC_AC_RL_PDS_E" maxOccurs="12">
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
     *                                                 &lt;element name="NUM_SEC_AC">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                                       &lt;maxInclusive value="9999999999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="ID_PDS">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000003"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="FECHA_INICIO">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                                       &lt;maxInclusive value="99999999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="IND_OBLIG">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000001"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="FECHA_CRRE">
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
     *                                       &lt;element name="AC_INF_DRVD_E" maxOccurs="28">
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
     *                                                 &lt;element name="NUM_SEC_AC">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                                       &lt;maxInclusive value="9999999999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_INF_DRVD_AC">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000002"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="VALOR_INF_DRVD">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000020"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                       &lt;element name="NUM_SUBAC_V">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="ULT_NUM_SUBAC">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                                       &lt;maxInclusive value="9999999"/>
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
        "trretcanctotalevty",
        "stdtrniparmv"
    })
    public static class ITRRETCANCTOTALTRNI {

        @XmlElement(name = "TR_RET_CANC_TOTAL_EVT_Y", required = true)
        protected Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY trretcanctotalevty;
        @XmlElement(name = "STD_TRN_I_PARM_V", required = true)
        protected Ejecutar.ITRRETCANCTOTALTRNI.STDTRNIPARMV stdtrniparmv;

        /**
         * Obtiene el valor de la propiedad trretcanctotalevty.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY }
         *     
         */
        public Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY getTRRETCANCTOTALEVTY() {
            return trretcanctotalevty;
        }

        /**
         * Define el valor de la propiedad trretcanctotalevty.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY }
         *     
         */
        public void setTRRETCANCTOTALEVTY(Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY value) {
            this.trretcanctotalevty = value;
        }

        /**
         * Obtiene el valor de la propiedad stdtrniparmv.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.ITRRETCANCTOTALTRNI.STDTRNIPARMV }
         *     
         */
        public Ejecutar.ITRRETCANCTOTALTRNI.STDTRNIPARMV getSTDTRNIPARMV() {
            return stdtrniparmv;
        }

        /**
         * Define el valor de la propiedad stdtrniparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.ITRRETCANCTOTALTRNI.STDTRNIPARMV }
         *     
         */
        public void setSTDTRNIPARMV(Ejecutar.ITRRETCANCTOTALTRNI.STDTRNIPARMV value) {
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
         *         &lt;element name="AC_AC_P">
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
         *                   &lt;element name="NUM_SEC_AC">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="AC_CC_ASOCIADA_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="IDFR_AC_L_V">
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
         *                             &lt;element name="COD_INTERNO_UO">
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
         *         &lt;element name="GAC_AC_HIJO_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="GAC_AC_AC_V">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="AC_AC_E">
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
         *                                       &lt;element name="NUM_SEC_AC">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                             &lt;maxInclusive value="9999999999"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_ECV_AC">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000001"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="FECHA_INI_ECV_ACT">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                             &lt;maxInclusive value="99999999"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="IND_SOCIO_PE">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000001"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="SIT_RESDNC_PE">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000001"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="CTER_INT">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000002"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_ACTIVO">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000002"/>
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
         *                                       &lt;element name="COD_INTERNO_UO">
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
         *                                       &lt;element name="ID_PDV">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000004"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="ID_TRFA_PDV">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000003"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_ORGN_AC">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000001"/>
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
         *                                       &lt;element name="COD_SIT_RECLAM">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000001"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_SIT_IRREG">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000001"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_CNO_INDV">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000006"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_CNAE_PERS">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000007"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_SECTOR_AC">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000004"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_FNDAD_AC">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000003"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_GRTIA_AC">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000003"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_ESTICO">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000006"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_PERS_CTBLE">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000003"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_GTIA_CTBLE">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000002"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_GTIA_CIRBE">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000001"/>
         *                                             &lt;whiteSpace value="preserve"/>
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
         *                             &lt;element name="AC_AC_RL_PDS_E" maxOccurs="12">
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
         *                                       &lt;element name="NUM_SEC_AC">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                             &lt;maxInclusive value="9999999999"/>
         *                                             &lt;minInclusive value="0"/>
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
         *                                       &lt;element name="FECHA_INICIO">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                             &lt;maxInclusive value="99999999"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="IND_OBLIG">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000001"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="FECHA_CRRE">
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
         *                             &lt;element name="AC_INF_DRVD_E" maxOccurs="28">
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
         *                                       &lt;element name="NUM_SEC_AC">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                             &lt;maxInclusive value="9999999999"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_INF_DRVD_AC">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                             &lt;length value="0000000002"/>
         *                                             &lt;whiteSpace value="preserve"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="VALOR_INF_DRVD">
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
         *                             &lt;element name="NUM_SUBAC_V">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="ULT_NUM_SUBAC">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                             &lt;maxInclusive value="9999999"/>
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
            "acacp",
            "acccasociadav",
            "gacachijov"
        })
        public static class TRRETCANCTOTALEVTY {

            @XmlElement(name = "AC_AC_P", required = true)
            protected Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACACP acacp;
            @XmlElement(name = "AC_CC_ASOCIADA_V", required = true)
            protected Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACCCASOCIADAV acccasociadav;
            @XmlElement(name = "GAC_AC_HIJO_V", required = true)
            protected Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV gacachijov;

            /**
             * Obtiene el valor de la propiedad acacp.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACACP }
             *     
             */
            public Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACACP getACACP() {
                return acacp;
            }

            /**
             * Define el valor de la propiedad acacp.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACACP }
             *     
             */
            public void setACACP(Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACACP value) {
                this.acacp = value;
            }

            /**
             * Obtiene el valor de la propiedad acccasociadav.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACCCASOCIADAV }
             *     
             */
            public Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACCCASOCIADAV getACCCASOCIADAV() {
                return acccasociadav;
            }

            /**
             * Define el valor de la propiedad acccasociadav.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACCCASOCIADAV }
             *     
             */
            public void setACCCASOCIADAV(Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACCCASOCIADAV value) {
                this.acccasociadav = value;
            }

            /**
             * Obtiene el valor de la propiedad gacachijov.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV }
             *     
             */
            public Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV getGACACHIJOV() {
                return gacachijov;
            }

            /**
             * Define el valor de la propiedad gacachijov.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV }
             *     
             */
            public void setGACACHIJOV(Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV value) {
                this.gacachijov = value;
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
             *         &lt;element name="NUM_SEC_AC">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999999999"/>
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
                "codcentuo",
                "numsecac"
            })
            public static class ACACP {

                @XmlElement(name = "COD_NRBE_EN", required = true)
                protected String codnrbeen;
                @XmlElement(name = "COD_CENT_UO", required = true)
                protected String codcentuo;
                @XmlElement(name = "NUM_SEC_AC")
                protected long numsecac;

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
             *         &lt;element name="IDFR_AC_L_V">
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
             *                   &lt;element name="COD_INTERNO_UO">
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
                "idfraclv"
            })
            public static class ACCCASOCIADAV {

                @XmlElement(name = "IDFR_AC_L_V", required = true)
                protected Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACCCASOCIADAV.IDFRACLV idfraclv;

                /**
                 * Obtiene el valor de la propiedad idfraclv.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACCCASOCIADAV.IDFRACLV }
                 *     
                 */
                public Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACCCASOCIADAV.IDFRACLV getIDFRACLV() {
                    return idfraclv;
                }

                /**
                 * Define el valor de la propiedad idfraclv.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACCCASOCIADAV.IDFRACLV }
                 *     
                 */
                public void setIDFRACLV(Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.ACCCASOCIADAV.IDFRACLV value) {
                    this.idfraclv = value;
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
                 *         &lt;element name="COD_INTERNO_UO">
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
                    "codinternouo",
                    "coddigcruo",
                    "numsecac",
                    "codplzbancaria"
                })
                public static class IDFRACLV {

                    @XmlElement(name = "COD_NRBE_EN", required = true)
                    protected String codnrbeen;
                    @XmlElement(name = "COD_CSB_OF", required = true)
                    protected String codcsbof;
                    @XmlElement(name = "COD_INTERNO_UO", required = true)
                    protected String codinternouo;
                    @XmlElement(name = "COD_DIG_CR_UO", required = true)
                    protected String coddigcruo;
                    @XmlElement(name = "NUM_SEC_AC")
                    protected long numsecac;
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
                    public void setCODNRBEEN(String value) {
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
                    public void setCODCSBOF(String value) {
                        this.codcsbof = value;
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
                    public void setCODDIGCRUO(String value) {
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
                    public void setNUMSECAC(long value) {
                        this.numsecac = value;
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
                    public void setCODPLZBANCARIA(String value) {
                        this.codplzbancaria = value;
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
             *         &lt;element name="GAC_AC_AC_V">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="AC_AC_E">
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
             *                             &lt;element name="NUM_SEC_AC">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                                   &lt;maxInclusive value="9999999999"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_ECV_AC">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000001"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="FECHA_INI_ECV_ACT">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                                   &lt;maxInclusive value="99999999"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="IND_SOCIO_PE">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000001"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="SIT_RESDNC_PE">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000001"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="CTER_INT">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000002"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_ACTIVO">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000002"/>
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
             *                             &lt;element name="COD_INTERNO_UO">
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
             *                             &lt;element name="ID_PDV">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000004"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="ID_TRFA_PDV">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000003"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_ORGN_AC">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000001"/>
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
             *                             &lt;element name="COD_SIT_RECLAM">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000001"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_SIT_IRREG">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000001"/>
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
             *                             &lt;element name="COD_CNAE_PERS">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000007"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_SECTOR_AC">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000004"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_FNDAD_AC">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000003"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_GRTIA_AC">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000003"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_ESTICO">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000006"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_PERS_CTBLE">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000003"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_GTIA_CTBLE">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000002"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_GTIA_CIRBE">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000001"/>
             *                                   &lt;whiteSpace value="preserve"/>
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
             *                   &lt;element name="AC_AC_RL_PDS_E" maxOccurs="12">
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
             *                             &lt;element name="NUM_SEC_AC">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                                   &lt;maxInclusive value="9999999999"/>
             *                                   &lt;minInclusive value="0"/>
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
             *                             &lt;element name="FECHA_INICIO">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                                   &lt;maxInclusive value="99999999"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="IND_OBLIG">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000001"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="FECHA_CRRE">
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
             *                   &lt;element name="AC_INF_DRVD_E" maxOccurs="28">
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
             *                             &lt;element name="NUM_SEC_AC">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                                   &lt;maxInclusive value="9999999999"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_INF_DRVD_AC">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                                   &lt;length value="0000000002"/>
             *                                   &lt;whiteSpace value="preserve"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="VALOR_INF_DRVD">
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
             *                   &lt;element name="NUM_SUBAC_V">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="ULT_NUM_SUBAC">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                                   &lt;maxInclusive value="9999999"/>
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
            @XmlType(name = "", propOrder = {
                "gacacacv"
            })
            public static class GACACHIJOV {

                @XmlElement(name = "GAC_AC_AC_V", required = true)
                protected Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV gacacacv;

                /**
                 * Obtiene el valor de la propiedad gacacacv.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV }
                 *     
                 */
                public Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV getGACACACV() {
                    return gacacacv;
                }

                /**
                 * Define el valor de la propiedad gacacacv.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV }
                 *     
                 */
                public void setGACACACV(Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV value) {
                    this.gacacacv = value;
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
                 *         &lt;element name="AC_AC_E">
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
                 *                   &lt;element name="NUM_SEC_AC">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *                         &lt;maxInclusive value="9999999999"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_ECV_AC">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000001"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="FECHA_INI_ECV_ACT">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *                         &lt;maxInclusive value="99999999"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="IND_SOCIO_PE">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000001"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="SIT_RESDNC_PE">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000001"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="CTER_INT">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000002"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_ACTIVO">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000002"/>
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
                 *                   &lt;element name="COD_INTERNO_UO">
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
                 *                   &lt;element name="ID_PDV">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000004"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="ID_TRFA_PDV">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000003"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_ORGN_AC">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000001"/>
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
                 *                   &lt;element name="COD_SIT_RECLAM">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000001"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_SIT_IRREG">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000001"/>
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
                 *                   &lt;element name="COD_CNAE_PERS">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000007"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_SECTOR_AC">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000004"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_FNDAD_AC">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000003"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_GRTIA_AC">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000003"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_ESTICO">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000006"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_PERS_CTBLE">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000003"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_GTIA_CTBLE">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000002"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_GTIA_CIRBE">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000001"/>
                 *                         &lt;whiteSpace value="preserve"/>
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
                 *         &lt;element name="AC_AC_RL_PDS_E" maxOccurs="12">
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
                 *                   &lt;element name="NUM_SEC_AC">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *                         &lt;maxInclusive value="9999999999"/>
                 *                         &lt;minInclusive value="0"/>
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
                 *                   &lt;element name="FECHA_INICIO">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *                         &lt;maxInclusive value="99999999"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="IND_OBLIG">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000001"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="FECHA_CRRE">
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
                 *         &lt;element name="AC_INF_DRVD_E" maxOccurs="28">
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
                 *                   &lt;element name="NUM_SEC_AC">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *                         &lt;maxInclusive value="9999999999"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_INF_DRVD_AC">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *                         &lt;length value="0000000002"/>
                 *                         &lt;whiteSpace value="preserve"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="VALOR_INF_DRVD">
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
                 *         &lt;element name="NUM_SUBAC_V">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="ULT_NUM_SUBAC">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *                         &lt;maxInclusive value="9999999"/>
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
                    "acace",
                    "acacrlpdse",
                    "acinfdrvde",
                    "numsubacv"
                })
                public static class GACACACV {

                    @XmlElement(name = "AC_AC_E", required = true)
                    protected Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACACE acace;
                    @XmlElement(name = "AC_AC_RL_PDS_E", required = true)
                    protected List<Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACACRLPDSE> acacrlpdse;
                    @XmlElement(name = "AC_INF_DRVD_E", required = true)
                    protected List<Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACINFDRVDE> acinfdrvde;
                    @XmlElement(name = "NUM_SUBAC_V", required = true)
                    protected Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.NUMSUBACV numsubacv;

                    /**
                     * Obtiene el valor de la propiedad acace.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACACE }
                     *     
                     */
                    public Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACACE getACACE() {
                        return acace;
                    }

                    /**
                     * Define el valor de la propiedad acace.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACACE }
                     *     
                     */
                    public void setACACE(Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACACE value) {
                        this.acace = value;
                    }

                    /**
                     * Gets the value of the acacrlpdse property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the acacrlpdse property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getACACRLPDSE().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACACRLPDSE }
                     * 
                     * 
                     */
                    public List<Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACACRLPDSE> getACACRLPDSE() {
                        if (acacrlpdse == null) {
                            acacrlpdse = new ArrayList<Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACACRLPDSE>();
                        }
                        return this.acacrlpdse;
                    }

                    /**
                     * Gets the value of the acinfdrvde property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the acinfdrvde property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getACINFDRVDE().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACINFDRVDE }
                     * 
                     * 
                     */
                    public List<Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACINFDRVDE> getACINFDRVDE() {
                        if (acinfdrvde == null) {
                            acinfdrvde = new ArrayList<Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.ACINFDRVDE>();
                        }
                        return this.acinfdrvde;
                    }

                    /**
                     * Obtiene el valor de la propiedad numsubacv.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.NUMSUBACV }
                     *     
                     */
                    public Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.NUMSUBACV getNUMSUBACV() {
                        return numsubacv;
                    }

                    /**
                     * Define el valor de la propiedad numsubacv.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.NUMSUBACV }
                     *     
                     */
                    public void setNUMSUBACV(Ejecutar.ITRRETCANCTOTALTRNI.TRRETCANCTOTALEVTY.GACACHIJOV.GACACACV.NUMSUBACV value) {
                        this.numsubacv = value;
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
                     *         &lt;element name="NUM_SEC_AC">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                     *               &lt;maxInclusive value="9999999999"/>
                     *               &lt;minInclusive value="0"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="COD_ECV_AC">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000001"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="FECHA_INI_ECV_ACT">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                     *               &lt;maxInclusive value="99999999"/>
                     *               &lt;minInclusive value="0"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="IND_SOCIO_PE">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000001"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="SIT_RESDNC_PE">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000001"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="CTER_INT">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000002"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="COD_ACTIVO">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000002"/>
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
                     *         &lt;element name="COD_INTERNO_UO">
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
                     *         &lt;element name="ID_PDV">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000004"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="ID_TRFA_PDV">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000003"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="COD_ORGN_AC">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000001"/>
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
                     *         &lt;element name="COD_SIT_RECLAM">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000001"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="COD_SIT_IRREG">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000001"/>
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
                     *         &lt;element name="COD_CNAE_PERS">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000007"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="COD_SECTOR_AC">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000004"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="COD_FNDAD_AC">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000003"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="COD_GRTIA_AC">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000003"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="COD_ESTICO">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000006"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="COD_PERS_CTBLE">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000003"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="COD_GTIA_CTBLE">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000002"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="COD_GTIA_CIRBE">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000001"/>
                     *               &lt;whiteSpace value="preserve"/>
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
                        "codcentuo",
                        "numsecac",
                        "codecvac",
                        "fechainiecvact",
                        "indsociope",
                        "sitresdncpe",
                        "cterint",
                        "codactivo",
                        "coddigcruo",
                        "codinternouo",
                        "codcsbof",
                        "codlinea",
                        "idgrppd",
                        "idpdv",
                        "idtrfapdv",
                        "codorgnac",
                        "codnumrcomoneda",
                        "codsitreclam",
                        "codsitirreg",
                        "codcnoindv",
                        "codcnaepers",
                        "codsectorac",
                        "codfndadac",
                        "codgrtiaac",
                        "codestico",
                        "codpersctble",
                        "codgtiactble",
                        "codgtiacirbe",
                        "codplzbancaria"
                    })
                    public static class ACACE {

                        @XmlElement(name = "COD_NRBE_EN", required = true)
                        protected String codnrbeen;
                        @XmlElement(name = "COD_CENT_UO", required = true)
                        protected String codcentuo;
                        @XmlElement(name = "NUM_SEC_AC")
                        protected long numsecac;
                        @XmlElement(name = "COD_ECV_AC", required = true)
                        protected String codecvac;
                        @XmlElement(name = "FECHA_INI_ECV_ACT")
                        protected int fechainiecvact;
                        @XmlElement(name = "IND_SOCIO_PE", required = true)
                        protected String indsociope;
                        @XmlElement(name = "SIT_RESDNC_PE", required = true)
                        protected String sitresdncpe;
                        @XmlElement(name = "CTER_INT", required = true)
                        protected String cterint;
                        @XmlElement(name = "COD_ACTIVO", required = true)
                        protected String codactivo;
                        @XmlElement(name = "COD_DIG_CR_UO", required = true)
                        protected String coddigcruo;
                        @XmlElement(name = "COD_INTERNO_UO", required = true)
                        protected String codinternouo;
                        @XmlElement(name = "COD_CSB_OF", required = true)
                        protected String codcsbof;
                        @XmlElement(name = "COD_LINEA", required = true)
                        protected String codlinea;
                        @XmlElement(name = "ID_GRP_PD", required = true)
                        protected String idgrppd;
                        @XmlElement(name = "ID_PDV", required = true)
                        protected String idpdv;
                        @XmlElement(name = "ID_TRFA_PDV", required = true)
                        protected String idtrfapdv;
                        @XmlElement(name = "COD_ORGN_AC", required = true)
                        protected String codorgnac;
                        @XmlElement(name = "COD_NUMRCO_MONEDA", required = true)
                        protected String codnumrcomoneda;
                        @XmlElement(name = "COD_SIT_RECLAM", required = true)
                        protected String codsitreclam;
                        @XmlElement(name = "COD_SIT_IRREG", required = true)
                        protected String codsitirreg;
                        @XmlElement(name = "COD_CNO_INDV", required = true)
                        protected String codcnoindv;
                        @XmlElement(name = "COD_CNAE_PERS", required = true)
                        protected String codcnaepers;
                        @XmlElement(name = "COD_SECTOR_AC", required = true)
                        protected String codsectorac;
                        @XmlElement(name = "COD_FNDAD_AC", required = true)
                        protected String codfndadac;
                        @XmlElement(name = "COD_GRTIA_AC", required = true)
                        protected String codgrtiaac;
                        @XmlElement(name = "COD_ESTICO", required = true)
                        protected String codestico;
                        @XmlElement(name = "COD_PERS_CTBLE", required = true)
                        protected String codpersctble;
                        @XmlElement(name = "COD_GTIA_CTBLE", required = true)
                        protected String codgtiactble;
                        @XmlElement(name = "COD_GTIA_CIRBE", required = true)
                        protected String codgtiacirbe;
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
                         * Obtiene el valor de la propiedad codecvac.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODECVAC() {
                            return codecvac;
                        }

                        /**
                         * Define el valor de la propiedad codecvac.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODECVAC(String value) {
                            this.codecvac = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad fechainiecvact.
                         * 
                         */
                        public int getFECHAINIECVACT() {
                            return fechainiecvact;
                        }

                        /**
                         * Define el valor de la propiedad fechainiecvact.
                         * 
                         */
                        public void setFECHAINIECVACT(int value) {
                            this.fechainiecvact = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad indsociope.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getINDSOCIOPE() {
                            return indsociope;
                        }

                        /**
                         * Define el valor de la propiedad indsociope.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setINDSOCIOPE(String value) {
                            this.indsociope = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad sitresdncpe.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getSITRESDNCPE() {
                            return sitresdncpe;
                        }

                        /**
                         * Define el valor de la propiedad sitresdncpe.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setSITRESDNCPE(String value) {
                            this.sitresdncpe = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad cterint.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCTERINT() {
                            return cterint;
                        }

                        /**
                         * Define el valor de la propiedad cterint.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCTERINT(String value) {
                            this.cterint = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad codactivo.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODACTIVO() {
                            return codactivo;
                        }

                        /**
                         * Define el valor de la propiedad codactivo.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODACTIVO(String value) {
                            this.codactivo = value;
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
                        public void setCODDIGCRUO(String value) {
                            this.coddigcruo = value;
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
                        public void setCODCSBOF(String value) {
                            this.codcsbof = value;
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

                        /**
                         * Obtiene el valor de la propiedad idpdv.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getIDPDV() {
                            return idpdv;
                        }

                        /**
                         * Define el valor de la propiedad idpdv.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setIDPDV(String value) {
                            this.idpdv = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad idtrfapdv.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getIDTRFAPDV() {
                            return idtrfapdv;
                        }

                        /**
                         * Define el valor de la propiedad idtrfapdv.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setIDTRFAPDV(String value) {
                            this.idtrfapdv = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad codorgnac.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODORGNAC() {
                            return codorgnac;
                        }

                        /**
                         * Define el valor de la propiedad codorgnac.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODORGNAC(String value) {
                            this.codorgnac = value;
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
                         * Obtiene el valor de la propiedad codsitreclam.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODSITRECLAM() {
                            return codsitreclam;
                        }

                        /**
                         * Define el valor de la propiedad codsitreclam.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODSITRECLAM(String value) {
                            this.codsitreclam = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad codsitirreg.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODSITIRREG() {
                            return codsitirreg;
                        }

                        /**
                         * Define el valor de la propiedad codsitirreg.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODSITIRREG(String value) {
                            this.codsitirreg = value;
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
                         * Obtiene el valor de la propiedad codsectorac.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODSECTORAC() {
                            return codsectorac;
                        }

                        /**
                         * Define el valor de la propiedad codsectorac.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODSECTORAC(String value) {
                            this.codsectorac = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad codfndadac.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODFNDADAC() {
                            return codfndadac;
                        }

                        /**
                         * Define el valor de la propiedad codfndadac.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODFNDADAC(String value) {
                            this.codfndadac = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad codgrtiaac.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODGRTIAAC() {
                            return codgrtiaac;
                        }

                        /**
                         * Define el valor de la propiedad codgrtiaac.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODGRTIAAC(String value) {
                            this.codgrtiaac = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad codestico.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODESTICO() {
                            return codestico;
                        }

                        /**
                         * Define el valor de la propiedad codestico.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODESTICO(String value) {
                            this.codestico = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad codpersctble.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODPERSCTBLE() {
                            return codpersctble;
                        }

                        /**
                         * Define el valor de la propiedad codpersctble.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODPERSCTBLE(String value) {
                            this.codpersctble = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad codgtiactble.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODGTIACTBLE() {
                            return codgtiactble;
                        }

                        /**
                         * Define el valor de la propiedad codgtiactble.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODGTIACTBLE(String value) {
                            this.codgtiactble = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad codgtiacirbe.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODGTIACIRBE() {
                            return codgtiacirbe;
                        }

                        /**
                         * Define el valor de la propiedad codgtiacirbe.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODGTIACIRBE(String value) {
                            this.codgtiacirbe = value;
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
                        public void setCODPLZBANCARIA(String value) {
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
                     *         &lt;element name="NUM_SEC_AC">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                     *               &lt;maxInclusive value="9999999999"/>
                     *               &lt;minInclusive value="0"/>
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
                     *         &lt;element name="FECHA_INICIO">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                     *               &lt;maxInclusive value="99999999"/>
                     *               &lt;minInclusive value="0"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="IND_OBLIG">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000001"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="FECHA_CRRE">
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
                        "codcentuo",
                        "numsecac",
                        "idpds",
                        "fechainicio",
                        "indoblig",
                        "fechacrre"
                    })
                    public static class ACACRLPDSE {

                        @XmlElement(name = "COD_NRBE_EN", required = true)
                        protected String codnrbeen;
                        @XmlElement(name = "COD_CENT_UO", required = true)
                        protected String codcentuo;
                        @XmlElement(name = "NUM_SEC_AC")
                        protected long numsecac;
                        @XmlElement(name = "ID_PDS", required = true)
                        protected String idpds;
                        @XmlElement(name = "FECHA_INICIO")
                        protected int fechainicio;
                        @XmlElement(name = "IND_OBLIG", required = true)
                        protected String indoblig;
                        @XmlElement(name = "FECHA_CRRE")
                        protected int fechacrre;

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
                        public void setIDPDS(String value) {
                            this.idpds = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad fechainicio.
                         * 
                         */
                        public int getFECHAINICIO() {
                            return fechainicio;
                        }

                        /**
                         * Define el valor de la propiedad fechainicio.
                         * 
                         */
                        public void setFECHAINICIO(int value) {
                            this.fechainicio = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad indoblig.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getINDOBLIG() {
                            return indoblig;
                        }

                        /**
                         * Define el valor de la propiedad indoblig.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setINDOBLIG(String value) {
                            this.indoblig = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad fechacrre.
                         * 
                         */
                        public int getFECHACRRE() {
                            return fechacrre;
                        }

                        /**
                         * Define el valor de la propiedad fechacrre.
                         * 
                         */
                        public void setFECHACRRE(int value) {
                            this.fechacrre = value;
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
                     *         &lt;element name="COD_CENT_UO">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000004"/>
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
                     *         &lt;element name="COD_INF_DRVD_AC">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                     *               &lt;length value="0000000002"/>
                     *               &lt;whiteSpace value="preserve"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="VALOR_INF_DRVD">
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
                        "codnrbeen",
                        "codcentuo",
                        "numsecac",
                        "codinfdrvdac",
                        "valorinfdrvd"
                    })
                    public static class ACINFDRVDE {

                        @XmlElement(name = "COD_NRBE_EN", required = true)
                        protected String codnrbeen;
                        @XmlElement(name = "COD_CENT_UO", required = true)
                        protected String codcentuo;
                        @XmlElement(name = "NUM_SEC_AC")
                        protected long numsecac;
                        @XmlElement(name = "COD_INF_DRVD_AC", required = true)
                        protected String codinfdrvdac;
                        @XmlElement(name = "VALOR_INF_DRVD", required = true)
                        protected String valorinfdrvd;

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
                         * Obtiene el valor de la propiedad codinfdrvdac.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODINFDRVDAC() {
                            return codinfdrvdac;
                        }

                        /**
                         * Define el valor de la propiedad codinfdrvdac.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODINFDRVDAC(String value) {
                            this.codinfdrvdac = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad valorinfdrvd.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getVALORINFDRVD() {
                            return valorinfdrvd;
                        }

                        /**
                         * Define el valor de la propiedad valorinfdrvd.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setVALORINFDRVD(String value) {
                            this.valorinfdrvd = value;
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
                     *         &lt;element name="ULT_NUM_SUBAC">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                     *               &lt;maxInclusive value="9999999"/>
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
                        "ultnumsubac"
                    })
                    public static class NUMSUBACV {

                        @XmlElement(name = "ULT_NUM_SUBAC")
                        protected int ultnumsubac;

                        /**
                         * Obtiene el valor de la propiedad ultnumsubac.
                         * 
                         */
                        public int getULTNUMSUBAC() {
                            return ultnumsubac;
                        }

                        /**
                         * Define el valor de la propiedad ultnumsubac.
                         * 
                         */
                        public void setULTNUMSUBAC(int value) {
                            this.ultnumsubac = value;
                        }

                    }

                }

            }

        }

    }

}
