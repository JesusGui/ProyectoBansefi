
package mx.babel.bansefi.banksystem.base.webservices.consultacuenta;

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
 *         &lt;element name="OTR_CONSULTA_ACUERDO_TRN">
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
 *                   &lt;element name="MORE_DATA_IN">
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
 *                   &lt;element name="TR_CONSULTA_ACUERDO_EVT_Z">
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
 *                             &lt;element name="IND_REPRESENTANTE_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="IND_REPRESENTANTE">
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
 *                             &lt;element name="DESCR_PDV_V">
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
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="PERSONA_AC_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="ID_INTERNO_PE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_ID_EXT_PERS">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="ID_EXT_PE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000014"/>
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
 *                                       &lt;element name="SIT_RESDNC_PE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
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
 *                                       &lt;element name="IND_SOCIO_PE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000001"/>
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
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="NUM_TARJETA_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="STD_PIC_16_0">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999999999999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="TR_CONS_VAL_MSV_KA_EVT_Z">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="KA_CD_AC_1_V" maxOccurs="50">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="KA_CD_AC_E">
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
 *                                                           &lt;element name="ID_PARMCD">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="FECHA_INIC_VAL">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="99999999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="FECHA_CRRE_VAL">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="99999999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_ECV_KA">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="FECHA_ECV_ACT">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="99999999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="IND_CD_PD">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_ORG_CD">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_ESTRCT_CD">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_CONCPT_NEG">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KA_ESTRCT_VLISTA_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="COD_DOM_PARMCD">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KA_KA_RL_PK_DOM_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="COD_DOM_PARMCD">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="DOM_IND_PREF">
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
 *                                                 &lt;element name="KA_ESTRCT_RNG_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="RNG_PREF">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000014"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="RNG_MIN">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000014"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="RNG_MAX">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000014"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="RNG_INCREM">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000014"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_UM">
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
 *                                                 &lt;element name="KA_ESTRCT_VRNG_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="RNG_VALOR">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000014"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_UM">
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
 *                                                 &lt;element name="KA_ESTRCT_CMSN_1_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="COD_CMSN">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="ID_PDS_1">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="ID_PARMCD_1">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
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
 *                                                           &lt;element name="COD_AGRU_CLOP">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KA_CMSN_PRCTL_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="COD_COMSN_PRCTL">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="CMSN_PCT">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000005"/>
 *                                                                 &lt;fractionDigits value="03"/>
 *                                                                 &lt;maxInclusive value="99.999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="CMSN_MIN">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000014"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="CMSN_MAX">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000014"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="IMP_FRANQ">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000014"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KA_CMSN_FIJ_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="IMP_CMSN_FIJ">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000014"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="FOR_CAL_PER_PCAL">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KA_ESTRCT_INT_1_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="ID_PDS_1">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="ID_PARMCD_1">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_INT">
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
 *                                                 &lt;element name="KA_ESTRCT_INT_FIJ_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="INT_VALOR">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000005"/>
 *                                                                 &lt;fractionDigits value="03"/>
 *                                                                 &lt;maxInclusive value="99.999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KA_ESTRCT_INT_VAR_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="INT_INCREM">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000005"/>
 *                                                                 &lt;fractionDigits value="03"/>
 *                                                                 &lt;maxInclusive value="99.999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="INT_MIN">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000005"/>
 *                                                                 &lt;fractionDigits value="03"/>
 *                                                                 &lt;maxInclusive value="99.999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="INT_MAX">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000005"/>
 *                                                                 &lt;fractionDigits value="03"/>
 *                                                                 &lt;maxInclusive value="99.999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="INT_FREC_REV">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="FRACCION_REV">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000006"/>
 *                                                                 &lt;fractionDigits value="04"/>
 *                                                                 &lt;maxInclusive value="99.9999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="PCT_MIN_REV">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000005"/>
 *                                                                 &lt;fractionDigits value="03"/>
 *                                                                 &lt;maxInclusive value="99.999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_REF_INT">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000004"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_CRIT_REV">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="FORMA_REV">
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
 *                                                 &lt;element name="KA_INT_FRANQ_1_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="IMP_FRANQ">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000014"/>
 *                                                                 &lt;fractionDigits value="02"/>
 *                                                                 &lt;maxInclusive value="999999999999.99"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_INT">
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
 *                                                 &lt;element name="KA_INT_FRANQ_FIJ_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="INT_VALOR">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000005"/>
 *                                                                 &lt;fractionDigits value="03"/>
 *                                                                 &lt;maxInclusive value="99.999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KA_INT_FRANQ_VAR_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="INT_INCREM">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000005"/>
 *                                                                 &lt;fractionDigits value="03"/>
 *                                                                 &lt;maxInclusive value="99.999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="INT_MIN">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000005"/>
 *                                                                 &lt;fractionDigits value="03"/>
 *                                                                 &lt;maxInclusive value="99.999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="INT_MAX">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000005"/>
 *                                                                 &lt;fractionDigits value="03"/>
 *                                                                 &lt;maxInclusive value="99.999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="INT_FREC_REV">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="FRACCION_REV">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000006"/>
 *                                                                 &lt;fractionDigits value="04"/>
 *                                                                 &lt;maxInclusive value="99.9999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="PCT_MIN_REV">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                                                 &lt;totalDigits value="0000000005"/>
 *                                                                 &lt;fractionDigits value="03"/>
 *                                                                 &lt;maxInclusive value="99.999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_REF_INT">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000004"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_CRIT_REV">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="FORMA_REV">
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
 *                                                 &lt;element name="KA_INT_RL_SP_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="COD_CLASE_SP">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="ID_SP_NUM">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="9999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="KA_MATRIZ_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="ID_TRAMEADO">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000010"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_FOR_APLC_TR">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="ID_PDS_1">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="ID_PARMCD_1">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000003"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_AGRU_CLOP">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="ID_CCPS_1_V" maxOccurs="50">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="ID_CCPS">
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
    "otrconsultaacuerdotrn"
})
public class ResponseBansefi {

    @XmlElement(name = "OTR_CONSULTA_ACUERDO_TRN", required = true)
    protected ResponseBansefi.OTRCONSULTAACUERDOTRN otrconsultaacuerdotrn;

    /**
     * Obtiene el valor de la propiedad otrconsultaacuerdotrn.
     * 
     * @return
     *     possible object is
     *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN }
     *     
     */
    public ResponseBansefi.OTRCONSULTAACUERDOTRN getOTRCONSULTAACUERDOTRN() {
        return otrconsultaacuerdotrn;
    }

    /**
     * Define el valor de la propiedad otrconsultaacuerdotrn.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN }
     *     
     */
    public void setOTRCONSULTAACUERDOTRN(ResponseBansefi.OTRCONSULTAACUERDOTRN value) {
        this.otrconsultaacuerdotrn = value;
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
     *         &lt;element name="MORE_DATA_IN">
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
     *         &lt;element name="TR_CONSULTA_ACUERDO_EVT_Z">
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
     *                   &lt;element name="IND_REPRESENTANTE_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="IND_REPRESENTANTE">
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
     *                   &lt;element name="DESCR_PDV_V">
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
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="PERSONA_AC_V">
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
     *                             &lt;element name="COD_PE">
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
     *                             &lt;element name="COD_CNAE_PERS">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000007"/>
     *                                   &lt;whiteSpace value="preserve"/>
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
     *                   &lt;element name="NUM_TARJETA_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="STD_PIC_16_0">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999999999999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="TR_CONS_VAL_MSV_KA_EVT_Z">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="KA_CD_AC_1_V" maxOccurs="50">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="KA_CD_AC_E">
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
     *                                                 &lt;element name="FECHA_CRRE_VAL">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                                       &lt;maxInclusive value="99999999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_ECV_KA">
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
     *                                                 &lt;element name="IND_CD_PD">
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
     *                                                       &lt;totalDigits value="0000000014"/>
     *                                                       &lt;fractionDigits value="02"/>
     *                                                       &lt;maxInclusive value="999999999999.99"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
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
     *                                       &lt;element name="KA_ESTRCT_VRNG_V" maxOccurs="50">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="RNG_VALOR">
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
     *                                                       &lt;totalDigits value="0000000005"/>
     *                                                       &lt;fractionDigits value="03"/>
     *                                                       &lt;maxInclusive value="99.999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="CMSN_MIN">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                                       &lt;totalDigits value="0000000014"/>
     *                                                       &lt;fractionDigits value="02"/>
     *                                                       &lt;maxInclusive value="999999999999.99"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="CMSN_MAX">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                                       &lt;totalDigits value="0000000014"/>
     *                                                       &lt;fractionDigits value="02"/>
     *                                                       &lt;maxInclusive value="999999999999.99"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="IMP_FRANQ">
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
     *                                       &lt;element name="KA_CMSN_FIJ_V" maxOccurs="50">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="IMP_CMSN_FIJ">
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
     *                                                       &lt;totalDigits value="0000000005"/>
     *                                                       &lt;fractionDigits value="03"/>
     *                                                       &lt;maxInclusive value="99.999"/>
     *                                                       &lt;minInclusive value="0"/>
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
     *                                                       &lt;totalDigits value="0000000005"/>
     *                                                       &lt;fractionDigits value="03"/>
     *                                                       &lt;maxInclusive value="99.999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="INT_MIN">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                                       &lt;totalDigits value="0000000005"/>
     *                                                       &lt;fractionDigits value="03"/>
     *                                                       &lt;maxInclusive value="99.999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="INT_MAX">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                                       &lt;totalDigits value="0000000005"/>
     *                                                       &lt;fractionDigits value="03"/>
     *                                                       &lt;maxInclusive value="99.999"/>
     *                                                       &lt;minInclusive value="0"/>
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
     *                                                       &lt;totalDigits value="0000000006"/>
     *                                                       &lt;fractionDigits value="04"/>
     *                                                       &lt;maxInclusive value="99.9999"/>
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
     *                                                       &lt;totalDigits value="0000000014"/>
     *                                                       &lt;fractionDigits value="02"/>
     *                                                       &lt;maxInclusive value="999999999999.99"/>
     *                                                       &lt;minInclusive value="0"/>
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
     *                                                       &lt;totalDigits value="0000000005"/>
     *                                                       &lt;fractionDigits value="03"/>
     *                                                       &lt;maxInclusive value="99.999"/>
     *                                                       &lt;minInclusive value="0"/>
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
     *                                                       &lt;totalDigits value="0000000005"/>
     *                                                       &lt;fractionDigits value="03"/>
     *                                                       &lt;maxInclusive value="99.999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="INT_MIN">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                                       &lt;totalDigits value="0000000005"/>
     *                                                       &lt;fractionDigits value="03"/>
     *                                                       &lt;maxInclusive value="99.999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="INT_MAX">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                                                       &lt;totalDigits value="0000000005"/>
     *                                                       &lt;fractionDigits value="03"/>
     *                                                       &lt;maxInclusive value="99.999"/>
     *                                                       &lt;minInclusive value="0"/>
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
     *                                                       &lt;totalDigits value="0000000006"/>
     *                                                       &lt;fractionDigits value="04"/>
     *                                                       &lt;maxInclusive value="99.9999"/>
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
        "moredatain",
        "numberofrecords",
        "trconsultaacuerdoevtz",
        "stdanavmsjv",
        "stdtrnmsjparmv",
        "stdtrnoparmv"
    })
    public static class OTRCONSULTAACUERDOTRN {

        @XmlElement(name = "RTRN_CD")
        protected int rtrncd;
        @XmlElement(name = "MORE_DATA_IN")
        protected int moredatain;
        @XmlElement(name = "NUMBER_OF_RECORDS")
        protected int numberofrecords;
        @XmlElement(name = "TR_CONSULTA_ACUERDO_EVT_Z", required = true)
        protected ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ trconsultaacuerdoevtz;
        @XmlElement(name = "STD_AN_AV_MSJ_V", required = true)
        protected ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV stdanavmsjv;
        @XmlElement(name = "STD_TRN_MSJ_PARM_V", required = true)
        protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDTRNMSJPARMV> stdtrnmsjparmv;
        @XmlElement(name = "STD_TRN_O_PARM_V", required = true)
        protected ResponseBansefi.OTRCONSULTAACUERDOTRN.STDTRNOPARMV stdtrnoparmv;

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
         * Obtiene el valor de la propiedad trconsultaacuerdoevtz.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ }
         *     
         */
        public ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ getTRCONSULTAACUERDOEVTZ() {
            return trconsultaacuerdoevtz;
        }

        /**
         * Define el valor de la propiedad trconsultaacuerdoevtz.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ }
         *     
         */
        public void setTRCONSULTAACUERDOEVTZ(ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ value) {
            this.trconsultaacuerdoevtz = value;
        }

        /**
         * Obtiene el valor de la propiedad stdanavmsjv.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV }
         *     
         */
        public ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV getSTDANAVMSJV() {
            return stdanavmsjv;
        }

        /**
         * Define el valor de la propiedad stdanavmsjv.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV }
         *     
         */
        public void setSTDANAVMSJV(ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV value) {
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
         * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.STDTRNMSJPARMV }
         * 
         * 
         */
        public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDTRNMSJPARMV> getSTDTRNMSJPARMV() {
            if (stdtrnmsjparmv == null) {
                stdtrnmsjparmv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDTRNMSJPARMV>();
            }
            return this.stdtrnmsjparmv;
        }

        /**
         * Obtiene el valor de la propiedad stdtrnoparmv.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.STDTRNOPARMV }
         *     
         */
        public ResponseBansefi.OTRCONSULTAACUERDOTRN.STDTRNOPARMV getSTDTRNOPARMV() {
            return stdtrnoparmv;
        }

        /**
         * Define el valor de la propiedad stdtrnoparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.STDTRNOPARMV }
         *     
         */
        public void setSTDTRNOPARMV(ResponseBansefi.OTRCONSULTAACUERDOTRN.STDTRNOPARMV value) {
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
            protected ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.ANNUMANOTACIONESV annumanotacionesv;
            @XmlElement(name = "STD_AN_AV_MSJ_LS", required = true)
            protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS> stdanavmsjls;

            /**
             * Obtiene el valor de la propiedad annumanotacionesv.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.ANNUMANOTACIONESV }
             *     
             */
            public ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.ANNUMANOTACIONESV getANNUMANOTACIONESV() {
                return annumanotacionesv;
            }

            /**
             * Define el valor de la propiedad annumanotacionesv.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.ANNUMANOTACIONESV }
             *     
             */
            public void setANNUMANOTACIONESV(ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.ANNUMANOTACIONESV value) {
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
             * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS }
             * 
             * 
             */
            public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS> getSTDANAVMSJLS() {
                if (stdanavmsjls == null) {
                    stdanavmsjls = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS>();
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
                public void setSTDDEC3(int value) {
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
                protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS.DESCRIPANTCNV> descripantcnv;
                @XmlElement(name = "IND_PRDAD", required = true)
                protected String indprdad;
                @XmlElement(name = "DESC_IND_PRDAD_V", required = true)
                protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV> descindprdadv;
                @XmlElement(name = "SUBCD_ANTCN", required = true)
                protected String subcdantcn;
                @XmlElement(name = "STD_DESCR_C_ANTCN_V", required = true)
                protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS.STDDESCRCANTCNV> stddescrcantcnv;

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
                public void setNUMEROANTCN(long value) {
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
                public void setCODANTCN(String value) {
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
                 * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS.DESCRIPANTCNV }
                 * 
                 * 
                 */
                public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS.DESCRIPANTCNV> getDESCRIPANTCNV() {
                    if (descripantcnv == null) {
                        descripantcnv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS.DESCRIPANTCNV>();
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
                public void setINDPRDAD(String value) {
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
                 * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV }
                 * 
                 * 
                 */
                public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV> getDESCINDPRDADV() {
                    if (descindprdadv == null) {
                        descindprdadv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV>();
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
                public void setSUBCDANTCN(String value) {
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
                 * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS.STDDESCRCANTCNV }
                 * 
                 * 
                 */
                public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS.STDDESCRCANTCNV> getSTDDESCRCANTCNV() {
                    if (stddescrcantcnv == null) {
                        stddescrcantcnv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.STDANAVMSJV.STDANAVMSJLS.STDDESCRCANTCNV>();
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
                    public void setSTDCHAR07(String value) {
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
                    public void setSTDCHAR11(String value) {
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
                    public void setSTDCHAR30(String value) {
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
         *         &lt;element name="IND_REPRESENTANTE_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="IND_REPRESENTANTE">
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
         *         &lt;element name="DESCR_PDV_V">
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
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="PERSONA_AC_V">
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
         *                   &lt;element name="COD_PE">
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
         *                   &lt;element name="COD_CNAE_PERS">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000007"/>
         *                         &lt;whiteSpace value="preserve"/>
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
         *         &lt;element name="NUM_TARJETA_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="STD_PIC_16_0">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999999999999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="TR_CONS_VAL_MSV_KA_EVT_Z">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="KA_CD_AC_1_V" maxOccurs="50">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="KA_CD_AC_E">
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
         *                                       &lt;element name="FECHA_CRRE_VAL">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                             &lt;maxInclusive value="99999999"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="COD_ECV_KA">
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
         *                                       &lt;element name="IND_CD_PD">
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
         *                                             &lt;totalDigits value="0000000014"/>
         *                                             &lt;fractionDigits value="02"/>
         *                                             &lt;maxInclusive value="999999999999.99"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
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
         *                             &lt;element name="KA_ESTRCT_VRNG_V" maxOccurs="50">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="RNG_VALOR">
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
         *                                             &lt;totalDigits value="0000000005"/>
         *                                             &lt;fractionDigits value="03"/>
         *                                             &lt;maxInclusive value="99.999"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="CMSN_MIN">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                             &lt;totalDigits value="0000000014"/>
         *                                             &lt;fractionDigits value="02"/>
         *                                             &lt;maxInclusive value="999999999999.99"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="CMSN_MAX">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                             &lt;totalDigits value="0000000014"/>
         *                                             &lt;fractionDigits value="02"/>
         *                                             &lt;maxInclusive value="999999999999.99"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="IMP_FRANQ">
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
         *                             &lt;element name="KA_CMSN_FIJ_V" maxOccurs="50">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="IMP_CMSN_FIJ">
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
         *                                             &lt;totalDigits value="0000000005"/>
         *                                             &lt;fractionDigits value="03"/>
         *                                             &lt;maxInclusive value="99.999"/>
         *                                             &lt;minInclusive value="0"/>
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
         *                                             &lt;totalDigits value="0000000005"/>
         *                                             &lt;fractionDigits value="03"/>
         *                                             &lt;maxInclusive value="99.999"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="INT_MIN">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                             &lt;totalDigits value="0000000005"/>
         *                                             &lt;fractionDigits value="03"/>
         *                                             &lt;maxInclusive value="99.999"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="INT_MAX">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                             &lt;totalDigits value="0000000005"/>
         *                                             &lt;fractionDigits value="03"/>
         *                                             &lt;maxInclusive value="99.999"/>
         *                                             &lt;minInclusive value="0"/>
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
         *                                             &lt;totalDigits value="0000000006"/>
         *                                             &lt;fractionDigits value="04"/>
         *                                             &lt;maxInclusive value="99.9999"/>
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
         *                                             &lt;totalDigits value="0000000014"/>
         *                                             &lt;fractionDigits value="02"/>
         *                                             &lt;maxInclusive value="999999999999.99"/>
         *                                             &lt;minInclusive value="0"/>
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
         *                                             &lt;totalDigits value="0000000005"/>
         *                                             &lt;fractionDigits value="03"/>
         *                                             &lt;maxInclusive value="99.999"/>
         *                                             &lt;minInclusive value="0"/>
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
         *                                             &lt;totalDigits value="0000000005"/>
         *                                             &lt;fractionDigits value="03"/>
         *                                             &lt;maxInclusive value="99.999"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="INT_MIN">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                             &lt;totalDigits value="0000000005"/>
         *                                             &lt;fractionDigits value="03"/>
         *                                             &lt;maxInclusive value="99.999"/>
         *                                             &lt;minInclusive value="0"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                       &lt;element name="INT_MAX">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *                                             &lt;totalDigits value="0000000005"/>
         *                                             &lt;fractionDigits value="03"/>
         *                                             &lt;maxInclusive value="99.999"/>
         *                                             &lt;minInclusive value="0"/>
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
         *                                             &lt;totalDigits value="0000000006"/>
         *                                             &lt;fractionDigits value="04"/>
         *                                             &lt;maxInclusive value="99.9999"/>
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
            "indrepresentantev",
            "descrpdvv",
            "personaacv",
            "numtarjetav",
            "trconsvalmsvkaevtz"
        })
        public static class TRCONSULTAACUERDOEVTZ {

            @XmlElement(name = "AC_AC_E", required = true)
            protected ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.ACACE acace;
            @XmlElement(name = "IND_REPRESENTANTE_V", required = true)
            protected ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.INDREPRESENTANTEV indrepresentantev;
            @XmlElement(name = "DESCR_PDV_V", required = true)
            protected ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.DESCRPDVV descrpdvv;
            @XmlElement(name = "PERSONA_AC_V", required = true)
            protected ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.PERSONAACV personaacv;
            @XmlElement(name = "NUM_TARJETA_V", required = true)
            protected ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.NUMTARJETAV numtarjetav;
            @XmlElement(name = "TR_CONS_VAL_MSV_KA_EVT_Z", required = true)
            protected ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ trconsvalmsvkaevtz;

            /**
             * Obtiene el valor de la propiedad acace.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.ACACE }
             *     
             */
            public ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.ACACE getACACE() {
                return acace;
            }

            /**
             * Define el valor de la propiedad acace.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.ACACE }
             *     
             */
            public void setACACE(ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.ACACE value) {
                this.acace = value;
            }

            /**
             * Obtiene el valor de la propiedad indrepresentantev.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.INDREPRESENTANTEV }
             *     
             */
            public ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.INDREPRESENTANTEV getINDREPRESENTANTEV() {
                return indrepresentantev;
            }

            /**
             * Define el valor de la propiedad indrepresentantev.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.INDREPRESENTANTEV }
             *     
             */
            public void setINDREPRESENTANTEV(ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.INDREPRESENTANTEV value) {
                this.indrepresentantev = value;
            }

            /**
             * Obtiene el valor de la propiedad descrpdvv.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.DESCRPDVV }
             *     
             */
            public ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.DESCRPDVV getDESCRPDVV() {
                return descrpdvv;
            }

            /**
             * Define el valor de la propiedad descrpdvv.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.DESCRPDVV }
             *     
             */
            public void setDESCRPDVV(ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.DESCRPDVV value) {
                this.descrpdvv = value;
            }

            /**
             * Obtiene el valor de la propiedad personaacv.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.PERSONAACV }
             *     
             */
            public ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.PERSONAACV getPERSONAACV() {
                return personaacv;
            }

            /**
             * Define el valor de la propiedad personaacv.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.PERSONAACV }
             *     
             */
            public void setPERSONAACV(ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.PERSONAACV value) {
                this.personaacv = value;
            }

            /**
             * Obtiene el valor de la propiedad numtarjetav.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.NUMTARJETAV }
             *     
             */
            public ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.NUMTARJETAV getNUMTARJETAV() {
                return numtarjetav;
            }

            /**
             * Define el valor de la propiedad numtarjetav.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.NUMTARJETAV }
             *     
             */
            public void setNUMTARJETAV(ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.NUMTARJETAV value) {
                this.numtarjetav = value;
            }

            /**
             * Obtiene el valor de la propiedad trconsvalmsvkaevtz.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ }
             *     
             */
            public ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ getTRCONSVALMSVKAEVTZ() {
                return trconsvalmsvkaevtz;
            }

            /**
             * Define el valor de la propiedad trconsvalmsvkaevtz.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ }
             *     
             */
            public void setTRCONSVALMSVKAEVTZ(ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ value) {
                this.trconsvalmsvkaevtz = value;
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
             *         &lt;element name="NOMB_PDV">
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
                "nombpdv"
            })
            public static class DESCRPDVV {

                @XmlElement(name = "NOMB_PDV", required = true)
                protected String nombpdv;

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
             *         &lt;element name="IND_REPRESENTANTE">
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
                "indrepresentante"
            })
            public static class INDREPRESENTANTEV {

                @XmlElement(name = "IND_REPRESENTANTE", required = true)
                protected String indrepresentante;

                /**
                 * Obtiene el valor de la propiedad indrepresentante.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getINDREPRESENTANTE() {
                    return indrepresentante;
                }

                /**
                 * Define el valor de la propiedad indrepresentante.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setINDREPRESENTANTE(String value) {
                    this.indrepresentante = value;
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
             *         &lt;element name="STD_PIC_16_0">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999999999999999"/>
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
                "stdpic160"
            })
            public static class NUMTARJETAV {

                @XmlElement(name = "STD_PIC_16_0")
                protected long stdpic160;

                /**
                 * Obtiene el valor de la propiedad stdpic160.
                 * 
                 */
                public long getSTDPIC160() {
                    return stdpic160;
                }

                /**
                 * Define el valor de la propiedad stdpic160.
                 * 
                 */
                public void setSTDPIC160(long value) {
                    this.stdpic160 = value;
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
             *         &lt;element name="COD_PE">
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
             *         &lt;element name="COD_CNAE_PERS">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000007"/>
             *               &lt;whiteSpace value="preserve"/>
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
                "idinternope",
                "codidextpers",
                "idextpe",
                "codpe",
                "sitresdncpe",
                "codcnaepers",
                "indsociope",
                "nomb50"
            })
            public static class PERSONAACV {

                @XmlElement(name = "ID_INTERNO_PE")
                protected int idinternope;
                @XmlElement(name = "COD_ID_EXT_PERS", required = true)
                protected String codidextpers;
                @XmlElement(name = "ID_EXT_PE", required = true)
                protected String idextpe;
                @XmlElement(name = "COD_PE", required = true)
                protected String codpe;
                @XmlElement(name = "SIT_RESDNC_PE", required = true)
                protected String sitresdncpe;
                @XmlElement(name = "COD_CNAE_PERS", required = true)
                protected String codcnaepers;
                @XmlElement(name = "IND_SOCIO_PE", required = true)
                protected String indsociope;
                @XmlElement(name = "NOMB_50", required = true)
                protected String nomb50;

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
             *         &lt;element name="KA_CD_AC_1_V" maxOccurs="50">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="KA_CD_AC_E">
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
             *                             &lt;element name="FECHA_CRRE_VAL">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                                   &lt;maxInclusive value="99999999"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="COD_ECV_KA">
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
             *                             &lt;element name="IND_CD_PD">
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
             *                                   &lt;totalDigits value="0000000014"/>
             *                                   &lt;fractionDigits value="02"/>
             *                                   &lt;maxInclusive value="999999999999.99"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
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
             *                   &lt;element name="KA_ESTRCT_VRNG_V" maxOccurs="50">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="RNG_VALOR">
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
             *                                   &lt;totalDigits value="0000000005"/>
             *                                   &lt;fractionDigits value="03"/>
             *                                   &lt;maxInclusive value="99.999"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="CMSN_MIN">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                                   &lt;totalDigits value="0000000014"/>
             *                                   &lt;fractionDigits value="02"/>
             *                                   &lt;maxInclusive value="999999999999.99"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="CMSN_MAX">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                                   &lt;totalDigits value="0000000014"/>
             *                                   &lt;fractionDigits value="02"/>
             *                                   &lt;maxInclusive value="999999999999.99"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="IMP_FRANQ">
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
             *                   &lt;element name="KA_CMSN_FIJ_V" maxOccurs="50">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="IMP_CMSN_FIJ">
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
             *                                   &lt;totalDigits value="0000000005"/>
             *                                   &lt;fractionDigits value="03"/>
             *                                   &lt;maxInclusive value="99.999"/>
             *                                   &lt;minInclusive value="0"/>
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
             *                                   &lt;totalDigits value="0000000005"/>
             *                                   &lt;fractionDigits value="03"/>
             *                                   &lt;maxInclusive value="99.999"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="INT_MIN">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                                   &lt;totalDigits value="0000000005"/>
             *                                   &lt;fractionDigits value="03"/>
             *                                   &lt;maxInclusive value="99.999"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="INT_MAX">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                                   &lt;totalDigits value="0000000005"/>
             *                                   &lt;fractionDigits value="03"/>
             *                                   &lt;maxInclusive value="99.999"/>
             *                                   &lt;minInclusive value="0"/>
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
             *                                   &lt;totalDigits value="0000000006"/>
             *                                   &lt;fractionDigits value="04"/>
             *                                   &lt;maxInclusive value="99.9999"/>
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
             *                                   &lt;totalDigits value="0000000014"/>
             *                                   &lt;fractionDigits value="02"/>
             *                                   &lt;maxInclusive value="999999999999.99"/>
             *                                   &lt;minInclusive value="0"/>
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
             *                                   &lt;totalDigits value="0000000005"/>
             *                                   &lt;fractionDigits value="03"/>
             *                                   &lt;maxInclusive value="99.999"/>
             *                                   &lt;minInclusive value="0"/>
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
             *                                   &lt;totalDigits value="0000000005"/>
             *                                   &lt;fractionDigits value="03"/>
             *                                   &lt;maxInclusive value="99.999"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="INT_MIN">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                                   &lt;totalDigits value="0000000005"/>
             *                                   &lt;fractionDigits value="03"/>
             *                                   &lt;maxInclusive value="99.999"/>
             *                                   &lt;minInclusive value="0"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                             &lt;element name="INT_MAX">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
             *                                   &lt;totalDigits value="0000000005"/>
             *                                   &lt;fractionDigits value="03"/>
             *                                   &lt;maxInclusive value="99.999"/>
             *                                   &lt;minInclusive value="0"/>
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
             *                                   &lt;totalDigits value="0000000006"/>
             *                                   &lt;fractionDigits value="04"/>
             *                                   &lt;maxInclusive value="99.9999"/>
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
                "kacdac1V"
            })
            public static class TRCONSVALMSVKAEVTZ {

                @XmlElement(name = "KA_CD_AC_1_V", required = true)
                protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V> kacdac1V;

                /**
                 * Gets the value of the kacdac1V property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the kacdac1V property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getKACDAC1V().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V }
                 * 
                 * 
                 */
                public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V> getKACDAC1V() {
                    if (kacdac1V == null) {
                        kacdac1V = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V>();
                    }
                    return this.kacdac1V;
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
                 *         &lt;element name="KA_CD_AC_E">
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
                 *                   &lt;element name="FECHA_CRRE_VAL">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *                         &lt;maxInclusive value="99999999"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="COD_ECV_KA">
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
                 *                   &lt;element name="IND_CD_PD">
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
                 *                         &lt;totalDigits value="0000000014"/>
                 *                         &lt;fractionDigits value="02"/>
                 *                         &lt;maxInclusive value="999999999999.99"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
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
                 *         &lt;element name="KA_ESTRCT_VRNG_V" maxOccurs="50">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="RNG_VALOR">
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
                 *                         &lt;totalDigits value="0000000005"/>
                 *                         &lt;fractionDigits value="03"/>
                 *                         &lt;maxInclusive value="99.999"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="CMSN_MIN">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *                         &lt;totalDigits value="0000000014"/>
                 *                         &lt;fractionDigits value="02"/>
                 *                         &lt;maxInclusive value="999999999999.99"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="CMSN_MAX">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *                         &lt;totalDigits value="0000000014"/>
                 *                         &lt;fractionDigits value="02"/>
                 *                         &lt;maxInclusive value="999999999999.99"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="IMP_FRANQ">
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
                 *         &lt;element name="KA_CMSN_FIJ_V" maxOccurs="50">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="IMP_CMSN_FIJ">
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
                 *                         &lt;totalDigits value="0000000005"/>
                 *                         &lt;fractionDigits value="03"/>
                 *                         &lt;maxInclusive value="99.999"/>
                 *                         &lt;minInclusive value="0"/>
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
                 *                         &lt;totalDigits value="0000000005"/>
                 *                         &lt;fractionDigits value="03"/>
                 *                         &lt;maxInclusive value="99.999"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="INT_MIN">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *                         &lt;totalDigits value="0000000005"/>
                 *                         &lt;fractionDigits value="03"/>
                 *                         &lt;maxInclusive value="99.999"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="INT_MAX">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *                         &lt;totalDigits value="0000000005"/>
                 *                         &lt;fractionDigits value="03"/>
                 *                         &lt;maxInclusive value="99.999"/>
                 *                         &lt;minInclusive value="0"/>
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
                 *                         &lt;totalDigits value="0000000006"/>
                 *                         &lt;fractionDigits value="04"/>
                 *                         &lt;maxInclusive value="99.9999"/>
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
                 *                         &lt;totalDigits value="0000000014"/>
                 *                         &lt;fractionDigits value="02"/>
                 *                         &lt;maxInclusive value="999999999999.99"/>
                 *                         &lt;minInclusive value="0"/>
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
                 *                         &lt;totalDigits value="0000000005"/>
                 *                         &lt;fractionDigits value="03"/>
                 *                         &lt;maxInclusive value="99.999"/>
                 *                         &lt;minInclusive value="0"/>
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
                 *                         &lt;totalDigits value="0000000005"/>
                 *                         &lt;fractionDigits value="03"/>
                 *                         &lt;maxInclusive value="99.999"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="INT_MIN">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *                         &lt;totalDigits value="0000000005"/>
                 *                         &lt;fractionDigits value="03"/>
                 *                         &lt;maxInclusive value="99.999"/>
                 *                         &lt;minInclusive value="0"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                   &lt;element name="INT_MAX">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                 *                         &lt;totalDigits value="0000000005"/>
                 *                         &lt;fractionDigits value="03"/>
                 *                         &lt;maxInclusive value="99.999"/>
                 *                         &lt;minInclusive value="0"/>
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
                 *                         &lt;totalDigits value="0000000006"/>
                 *                         &lt;fractionDigits value="04"/>
                 *                         &lt;maxInclusive value="99.9999"/>
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
                    "kacdace",
                    "kaestrctvlistav",
                    "kakarlpkdomv",
                    "kaestrctrngv",
                    "kaestrctvrngv",
                    "kaestrctcmsn1V",
                    "kacmsnprctlv",
                    "kacmsnfijv",
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
                public static class KACDAC1V {

                    @XmlElement(name = "KA_CD_AC_E", required = true)
                    protected ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACDACE kacdace;
                    @XmlElement(name = "KA_ESTRCT_VLISTA_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTVLISTAV> kaestrctvlistav;
                    @XmlElement(name = "KA_KA_RL_PK_DOM_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAKARLPKDOMV> kakarlpkdomv;
                    @XmlElement(name = "KA_ESTRCT_RNG_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTRNGV> kaestrctrngv;
                    @XmlElement(name = "KA_ESTRCT_VRNG_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTVRNGV> kaestrctvrngv;
                    @XmlElement(name = "KA_ESTRCT_CMSN_1_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTCMSN1V> kaestrctcmsn1V;
                    @XmlElement(name = "KA_CMSN_PRCTL_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACMSNPRCTLV> kacmsnprctlv;
                    @XmlElement(name = "KA_CMSN_FIJ_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACMSNFIJV> kacmsnfijv;
                    @XmlElement(name = "KA_ESTRCT_INT_1_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTINT1V> kaestrctint1V;
                    @XmlElement(name = "KA_ESTRCT_INT_FIJ_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTINTFIJV> kaestrctintfijv;
                    @XmlElement(name = "KA_ESTRCT_INT_VAR_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTINTVARV> kaestrctintvarv;
                    @XmlElement(name = "KA_INT_FRANQ_1_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTFRANQ1V> kaintfranq1V;
                    @XmlElement(name = "KA_INT_FRANQ_FIJ_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTFRANQFIJV> kaintfranqfijv;
                    @XmlElement(name = "KA_INT_FRANQ_VAR_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTFRANQVARV> kaintfranqvarv;
                    @XmlElement(name = "KA_INT_RL_SP_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTRLSPV> kaintrlspv;
                    @XmlElement(name = "KA_MATRIZ_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAMATRIZV> kamatrizv;
                    @XmlElement(name = "ID_CCPS_1_V", required = true)
                    protected List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.IDCCPS1V> idccps1V;

                    /**
                     * Obtiene el valor de la propiedad kacdace.
                     * 
                     * @return
                     *     possible object is
                     *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACDACE }
                     *     
                     */
                    public ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACDACE getKACDACE() {
                        return kacdace;
                    }

                    /**
                     * Define el valor de la propiedad kacdace.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACDACE }
                     *     
                     */
                    public void setKACDACE(ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACDACE value) {
                        this.kacdace = value;
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTVLISTAV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTVLISTAV> getKAESTRCTVLISTAV() {
                        if (kaestrctvlistav == null) {
                            kaestrctvlistav = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTVLISTAV>();
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAKARLPKDOMV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAKARLPKDOMV> getKAKARLPKDOMV() {
                        if (kakarlpkdomv == null) {
                            kakarlpkdomv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAKARLPKDOMV>();
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTRNGV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTRNGV> getKAESTRCTRNGV() {
                        if (kaestrctrngv == null) {
                            kaestrctrngv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTRNGV>();
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTVRNGV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTVRNGV> getKAESTRCTVRNGV() {
                        if (kaestrctvrngv == null) {
                            kaestrctvrngv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTVRNGV>();
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTCMSN1V }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTCMSN1V> getKAESTRCTCMSN1V() {
                        if (kaestrctcmsn1V == null) {
                            kaestrctcmsn1V = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTCMSN1V>();
                        }
                        return this.kaestrctcmsn1V;
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACMSNPRCTLV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACMSNPRCTLV> getKACMSNPRCTLV() {
                        if (kacmsnprctlv == null) {
                            kacmsnprctlv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACMSNPRCTLV>();
                        }
                        return this.kacmsnprctlv;
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACMSNFIJV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACMSNFIJV> getKACMSNFIJV() {
                        if (kacmsnfijv == null) {
                            kacmsnfijv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KACMSNFIJV>();
                        }
                        return this.kacmsnfijv;
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTINT1V }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTINT1V> getKAESTRCTINT1V() {
                        if (kaestrctint1V == null) {
                            kaestrctint1V = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTINT1V>();
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTINTFIJV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTINTFIJV> getKAESTRCTINTFIJV() {
                        if (kaestrctintfijv == null) {
                            kaestrctintfijv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTINTFIJV>();
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTINTVARV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTINTVARV> getKAESTRCTINTVARV() {
                        if (kaestrctintvarv == null) {
                            kaestrctintvarv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAESTRCTINTVARV>();
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTFRANQ1V }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTFRANQ1V> getKAINTFRANQ1V() {
                        if (kaintfranq1V == null) {
                            kaintfranq1V = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTFRANQ1V>();
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTFRANQFIJV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTFRANQFIJV> getKAINTFRANQFIJV() {
                        if (kaintfranqfijv == null) {
                            kaintfranqfijv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTFRANQFIJV>();
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTFRANQVARV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTFRANQVARV> getKAINTFRANQVARV() {
                        if (kaintfranqvarv == null) {
                            kaintfranqvarv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTFRANQVARV>();
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTRLSPV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTRLSPV> getKAINTRLSPV() {
                        if (kaintrlspv == null) {
                            kaintrlspv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAINTRLSPV>();
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAMATRIZV }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAMATRIZV> getKAMATRIZV() {
                        if (kamatrizv == null) {
                            kamatrizv = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.KAMATRIZV>();
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
                     * {@link ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.IDCCPS1V }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.IDCCPS1V> getIDCCPS1V() {
                        if (idccps1V == null) {
                            idccps1V = new ArrayList<ResponseBansefi.OTRCONSULTAACUERDOTRN.TRCONSULTAACUERDOEVTZ.TRCONSVALMSVKAEVTZ.KACDAC1V.IDCCPS1V>();
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
                        public void setIDCCPS(String value) {
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
                     *         &lt;element name="FECHA_CRRE_VAL">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                     *               &lt;maxInclusive value="99999999"/>
                     *               &lt;minInclusive value="0"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="COD_ECV_KA">
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
                     *         &lt;element name="IND_CD_PD">
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
                        "idparmcd",
                        "fechainicval",
                        "fechacrreval",
                        "codecvka",
                        "fechaecvact",
                        "indcdpd",
                        "codorgcd",
                        "codestrctcd",
                        "codconcptneg"
                    })
                    public static class KACDACE {

                        @XmlElement(name = "COD_NRBE_EN", required = true)
                        protected String codnrbeen;
                        @XmlElement(name = "COD_CENT_UO", required = true)
                        protected String codcentuo;
                        @XmlElement(name = "NUM_SEC_AC")
                        protected long numsecac;
                        @XmlElement(name = "ID_PDS", required = true)
                        protected String idpds;
                        @XmlElement(name = "ID_PARMCD", required = true)
                        protected String idparmcd;
                        @XmlElement(name = "FECHA_INIC_VAL")
                        protected int fechainicval;
                        @XmlElement(name = "FECHA_CRRE_VAL")
                        protected int fechacrreval;
                        @XmlElement(name = "COD_ECV_KA", required = true)
                        protected String codecvka;
                        @XmlElement(name = "FECHA_ECV_ACT")
                        protected int fechaecvact;
                        @XmlElement(name = "IND_CD_PD", required = true)
                        protected String indcdpd;
                        @XmlElement(name = "COD_ORG_CD", required = true)
                        protected String codorgcd;
                        @XmlElement(name = "COD_ESTRCT_CD", required = true)
                        protected String codestrctcd;
                        @XmlElement(name = "COD_CONCPT_NEG", required = true)
                        protected String codconcptneg;

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
                        public void setIDPARMCD(String value) {
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
                        public void setFECHAINICVAL(int value) {
                            this.fechainicval = value;
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
                        public void setFECHACRREVAL(int value) {
                            this.fechacrreval = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad codecvka.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getCODECVKA() {
                            return codecvka;
                        }

                        /**
                         * Define el valor de la propiedad codecvka.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setCODECVKA(String value) {
                            this.codecvka = value;
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
                        public void setFECHAECVACT(int value) {
                            this.fechaecvact = value;
                        }

                        /**
                         * Obtiene el valor de la propiedad indcdpd.
                         * 
                         * @return
                         *     possible object is
                         *     {@link String }
                         *     
                         */
                        public String getINDCDPD() {
                            return indcdpd;
                        }

                        /**
                         * Define el valor de la propiedad indcdpd.
                         * 
                         * @param value
                         *     allowed object is
                         *     {@link String }
                         *     
                         */
                        public void setINDCDPD(String value) {
                            this.indcdpd = value;
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
                        public void setCODORGCD(String value) {
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
                        public void setCODESTRCTCD(String value) {
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
                        public void setCODCONCPTNEG(String value) {
                            this.codconcptneg = value;
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
                        public void setIMPCMSNFIJ(BigDecimal value) {
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
                        public void setFORCALPERPCAL(String value) {
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
                     *               &lt;totalDigits value="0000000005"/>
                     *               &lt;fractionDigits value="03"/>
                     *               &lt;maxInclusive value="99.999"/>
                     *               &lt;minInclusive value="0"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="CMSN_MIN">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                     *               &lt;totalDigits value="0000000014"/>
                     *               &lt;fractionDigits value="02"/>
                     *               &lt;maxInclusive value="999999999999.99"/>
                     *               &lt;minInclusive value="0"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="CMSN_MAX">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                     *               &lt;totalDigits value="0000000014"/>
                     *               &lt;fractionDigits value="02"/>
                     *               &lt;maxInclusive value="999999999999.99"/>
                     *               &lt;minInclusive value="0"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="IMP_FRANQ">
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
                        public void setCODCOMSNPRCTL(String value) {
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
                        public void setCMSNPCT(BigDecimal value) {
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
                        public void setCMSNMIN(BigDecimal value) {
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
                        public void setCMSNMAX(BigDecimal value) {
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
                        public void setIMPFRANQ(BigDecimal value) {
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
                        public void setCODCMSN(String value) {
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
                        public void setCODAGRUCLOP(String value) {
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
                        public void setCODINT(String value) {
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
                     *               &lt;totalDigits value="0000000005"/>
                     *               &lt;fractionDigits value="03"/>
                     *               &lt;maxInclusive value="99.999"/>
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
                        public void setINTVALOR(BigDecimal value) {
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
                     *               &lt;totalDigits value="0000000005"/>
                     *               &lt;fractionDigits value="03"/>
                     *               &lt;maxInclusive value="99.999"/>
                     *               &lt;minInclusive value="0"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="INT_MIN">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                     *               &lt;totalDigits value="0000000005"/>
                     *               &lt;fractionDigits value="03"/>
                     *               &lt;maxInclusive value="99.999"/>
                     *               &lt;minInclusive value="0"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="INT_MAX">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                     *               &lt;totalDigits value="0000000005"/>
                     *               &lt;fractionDigits value="03"/>
                     *               &lt;maxInclusive value="99.999"/>
                     *               &lt;minInclusive value="0"/>
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
                     *               &lt;totalDigits value="0000000006"/>
                     *               &lt;fractionDigits value="04"/>
                     *               &lt;maxInclusive value="99.9999"/>
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
                        public void setINTINCREM(BigDecimal value) {
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
                        public void setINTMIN(BigDecimal value) {
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
                        public void setINTMAX(BigDecimal value) {
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
                        public void setINTFRECREV(String value) {
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
                        public void setFRACCIONREV(BigDecimal value) {
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
                        public void setPCTMINREV(BigDecimal value) {
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
                        public void setCODREFINT(String value) {
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
                        public void setCODCRITREV(String value) {
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
                        public void setFORMAREV(String value) {
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
                     *               &lt;totalDigits value="0000000014"/>
                     *               &lt;fractionDigits value="02"/>
                     *               &lt;maxInclusive value="999999999999.99"/>
                     *               &lt;minInclusive value="0"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
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
                        public void setRNGPREF(BigDecimal value) {
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
                        public void setRNGMIN(BigDecimal value) {
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
                        public void setRNGMAX(BigDecimal value) {
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
                        public void setRNGINCREM(BigDecimal value) {
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
                        public void setCODUM(String value) {
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
                        public void setCODDOMPARMCD(String value) {
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
                        public void setRNGVALOR(BigDecimal value) {
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
                        public void setCODUM(String value) {
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
                     *               &lt;totalDigits value="0000000014"/>
                     *               &lt;fractionDigits value="02"/>
                     *               &lt;maxInclusive value="999999999999.99"/>
                     *               &lt;minInclusive value="0"/>
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
                        public void setIMPFRANQ(BigDecimal value) {
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
                        public void setCODINT(String value) {
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
                     *               &lt;totalDigits value="0000000005"/>
                     *               &lt;fractionDigits value="03"/>
                     *               &lt;maxInclusive value="99.999"/>
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
                        public void setINTVALOR(BigDecimal value) {
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
                     *               &lt;totalDigits value="0000000005"/>
                     *               &lt;fractionDigits value="03"/>
                     *               &lt;maxInclusive value="99.999"/>
                     *               &lt;minInclusive value="0"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="INT_MIN">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                     *               &lt;totalDigits value="0000000005"/>
                     *               &lt;fractionDigits value="03"/>
                     *               &lt;maxInclusive value="99.999"/>
                     *               &lt;minInclusive value="0"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
                     *         &lt;/element>
                     *         &lt;element name="INT_MAX">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
                     *               &lt;totalDigits value="0000000005"/>
                     *               &lt;fractionDigits value="03"/>
                     *               &lt;maxInclusive value="99.999"/>
                     *               &lt;minInclusive value="0"/>
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
                     *               &lt;totalDigits value="0000000006"/>
                     *               &lt;fractionDigits value="04"/>
                     *               &lt;maxInclusive value="99.9999"/>
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
                        public void setINTINCREM(BigDecimal value) {
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
                        public void setINTMIN(BigDecimal value) {
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
                        public void setINTMAX(BigDecimal value) {
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
                        public void setINTFRECREV(String value) {
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
                        public void setFRACCIONREV(BigDecimal value) {
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
                        public void setPCTMINREV(BigDecimal value) {
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
                        public void setCODREFINT(String value) {
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
                        public void setCODCRITREV(String value) {
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
                        public void setFORMAREV(String value) {
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
                        public void setCODCLASESP(String value) {
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
                        public void setIDSPNUM(int value) {
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
                        public void setCODDOMPARMCD(String value) {
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
                        public void setDOMINDPREF(String value) {
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
                        public void setIDTRAMEADO(String value) {
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
                        public void setCODFORAPLCTR(String value) {
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
                        public void setCODAGRUCLOP(String value) {
                            this.codagruclop = value;
                        }

                    }

                }

            }

        }

    }

}
