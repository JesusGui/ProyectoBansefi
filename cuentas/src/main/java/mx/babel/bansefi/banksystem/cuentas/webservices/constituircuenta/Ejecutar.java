
package mx.babel.bansefi.banksystem.cuentas.webservices.constituircuenta;

import java.math.BigDecimal;
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
 *         &lt;element name="TR_CONSTI_TOTAL_AC_TRN_I">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TR_CONSTI_TOTAL_AC_EVT_Y">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PSV_ERROR_V" maxOccurs="5">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="COD_ERROR">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000005"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="ID_EMPL_AUT">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000008"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="ATRIBUIDO_S_N">
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
 *                             &lt;element name="PSV_IMP_AUT_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="IMP_AUT">
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
 *                             &lt;element name="FECHA_V">
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
 *                             &lt;element name="ACV_TIPO_COBRO_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="STD_CHAR_02">
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
 *                             &lt;element name="ACV_LIM_CONCEDIDO_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="IMP_LIMITE">
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
 *                             &lt;element name="RX_AC_RL_AC_P">
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
 *                                       &lt;element name="COD_CENT_UO_1">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000004"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NUM_SEC_AC_1">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_RL_AC_AC">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_CENT_UO_2">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000004"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NUM_SEC_AC_2">
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
 *                             &lt;element name="AC_CUADRO_IRREG_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="STD_SMALL_INT">
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
 *                             &lt;element name="SIT_IRREG_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="STD_CHAR_10">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000010"/>
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
 *                   &lt;element name="STD_AUTORIZA_V">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="IND_BORRADO_AR">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="DESCR_TX">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000050"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IND_AUT_SOLIC">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IND_ATRIB_MANC_EP">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_ESTADO_AR">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="ID_EMPL_SOL_AUT">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000008"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IND_VERIF_ATRIB">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IND_URG_AR">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="MOTIVO_ACCION_AUT_LEN">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="9999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="MOTIVO_ACCION_AUT">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000200"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IND_ESCALABLE">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IMP_AUT">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="0000000014"/>
 *                                   &lt;fractionDigits value="02"/>
 *                                   &lt;maxInclusive value="999999999999.99"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IMPORTE_AR">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="0000000014"/>
 *                                   &lt;fractionDigits value="02"/>
 *                                   &lt;maxInclusive value="999999999999.99"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="AR_AUT_REMOTA_P">
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
 *                                       &lt;element name="ID_INTERNO_TERM_TN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000008"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="FECHA_OPRCN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="HORA_OPRCN">
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
 *                             &lt;element name="AR_TRN_MSJ_PARM_V" maxOccurs="10">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="TEXT_CODE">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="TEXT_ARG1">
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
 *                             &lt;element name="STD_TARGET_TERMINAL_V" maxOccurs="50">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="ID_INTERNO_EMPL_EP">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000008"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="ID_INTERNO_TERM_TN">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000008"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="COD_ECV_SESION">
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
 *                             &lt;element name="AR_ID_SALTADO_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="ID_INTERNO_EMPL_EP">
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
 *                   &lt;element name="RESPUESTA_SINCRONA_V">
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
 *                   &lt;element name="NRO_LLAMADA_V">
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
    "trconstitotalactrni"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USERHEADER", required = true)
    protected String userheader;
    @XmlElement(name = "PASSHEADER", required = true)
    protected String passheader;
    @XmlElement(name = "IPHEADER", required = true)
    protected String ipheader;
    @XmlElement(name = "TR_CONSTI_TOTAL_AC_TRN_I", required = true)
    protected Ejecutar.TRCONSTITOTALACTRNI trconstitotalactrni;

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
     * Obtiene el valor de la propiedad trconstitotalactrni.
     * 
     * @return
     *     possible object is
     *     {@link Ejecutar.TRCONSTITOTALACTRNI }
     *     
     */
    public Ejecutar.TRCONSTITOTALACTRNI getTRCONSTITOTALACTRNI() {
        return trconstitotalactrni;
    }

    /**
     * Define el valor de la propiedad trconstitotalactrni.
     * 
     * @param value
     *     allowed object is
     *     {@link Ejecutar.TRCONSTITOTALACTRNI }
     *     
     */
    public void setTRCONSTITOTALACTRNI(Ejecutar.TRCONSTITOTALACTRNI value) {
        this.trconstitotalactrni = value;
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
     *         &lt;element name="TR_CONSTI_TOTAL_AC_EVT_Y">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="PSV_ERROR_V" maxOccurs="5">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="COD_ERROR">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000005"/>
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
     *                             &lt;element name="ATRIBUIDO_S_N">
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
     *                   &lt;element name="PSV_IMP_AUT_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="IMP_AUT">
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
     *                   &lt;element name="FECHA_V">
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
     *                   &lt;element name="ACV_TIPO_COBRO_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="STD_CHAR_02">
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
     *                   &lt;element name="ACV_LIM_CONCEDIDO_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="IMP_LIMITE">
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
     *                   &lt;element name="RX_AC_RL_AC_P">
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
     *                             &lt;element name="COD_CENT_UO_1">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000004"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="NUM_SEC_AC_1">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_RL_AC_AC">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_CENT_UO_2">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000004"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="NUM_SEC_AC_2">
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
     *                   &lt;element name="AC_CUADRO_IRREG_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="STD_SMALL_INT">
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
     *                   &lt;element name="SIT_IRREG_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="STD_CHAR_10">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000010"/>
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
     *         &lt;element name="STD_AUTORIZA_V">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="IND_BORRADO_AR">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="DESCR_TX">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000050"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IND_AUT_SOLIC">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IND_ATRIB_MANC_EP">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_ESTADO_AR">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="ID_EMPL_SOL_AUT">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000008"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IND_VERIF_ATRIB">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IND_URG_AR">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="MOTIVO_ACCION_AUT_LEN">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="9999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="MOTIVO_ACCION_AUT">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000200"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IND_ESCALABLE">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IMP_AUT">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="0000000014"/>
     *                         &lt;fractionDigits value="02"/>
     *                         &lt;maxInclusive value="999999999999.99"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IMPORTE_AR">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="0000000014"/>
     *                         &lt;fractionDigits value="02"/>
     *                         &lt;maxInclusive value="999999999999.99"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="AR_AUT_REMOTA_P">
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
     *                             &lt;element name="ID_INTERNO_TERM_TN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000008"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
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
     *                   &lt;element name="AR_TRN_MSJ_PARM_V" maxOccurs="10">
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
     *                   &lt;element name="STD_TARGET_TERMINAL_V" maxOccurs="50">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="ID_INTERNO_EMPL_EP">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000008"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="ID_INTERNO_TERM_TN">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000008"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="COD_ECV_SESION">
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
     *                   &lt;element name="AR_ID_SALTADO_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="ID_INTERNO_EMPL_EP">
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
     *         &lt;element name="RESPUESTA_SINCRONA_V">
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
     *         &lt;element name="NRO_LLAMADA_V">
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
        "trconstitotalacevty",
        "stdautorizav",
        "stdtrniparmv",
        "respuestasincronav",
        "nrollamadav"
    })
    public static class TRCONSTITOTALACTRNI {

        @XmlElement(name = "TR_CONSTI_TOTAL_AC_EVT_Y", required = true)
        protected Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY trconstitotalacevty;
        @XmlElement(name = "STD_AUTORIZA_V", required = true)
        protected Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV stdautorizav;
        @XmlElement(name = "STD_TRN_I_PARM_V", required = true)
        protected Ejecutar.TRCONSTITOTALACTRNI.STDTRNIPARMV stdtrniparmv;
        @XmlElement(name = "RESPUESTA_SINCRONA_V", required = true)
        protected Ejecutar.TRCONSTITOTALACTRNI.RESPUESTASINCRONAV respuestasincronav;
        @XmlElement(name = "NRO_LLAMADA_V", required = true)
        protected Ejecutar.TRCONSTITOTALACTRNI.NROLLAMADAV nrollamadav;

        /**
         * Obtiene el valor de la propiedad trconstitotalacevty.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY }
         *     
         */
        public Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY getTRCONSTITOTALACEVTY() {
            return trconstitotalacevty;
        }

        /**
         * Define el valor de la propiedad trconstitotalacevty.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY }
         *     
         */
        public void setTRCONSTITOTALACEVTY(Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY value) {
            this.trconstitotalacevty = value;
        }

        /**
         * Obtiene el valor de la propiedad stdautorizav.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV }
         *     
         */
        public Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV getSTDAUTORIZAV() {
            return stdautorizav;
        }

        /**
         * Define el valor de la propiedad stdautorizav.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV }
         *     
         */
        public void setSTDAUTORIZAV(Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV value) {
            this.stdautorizav = value;
        }

        /**
         * Obtiene el valor de la propiedad stdtrniparmv.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.TRCONSTITOTALACTRNI.STDTRNIPARMV }
         *     
         */
        public Ejecutar.TRCONSTITOTALACTRNI.STDTRNIPARMV getSTDTRNIPARMV() {
            return stdtrniparmv;
        }

        /**
         * Define el valor de la propiedad stdtrniparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.TRCONSTITOTALACTRNI.STDTRNIPARMV }
         *     
         */
        public void setSTDTRNIPARMV(Ejecutar.TRCONSTITOTALACTRNI.STDTRNIPARMV value) {
            this.stdtrniparmv = value;
        }

        /**
         * Obtiene el valor de la propiedad respuestasincronav.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.TRCONSTITOTALACTRNI.RESPUESTASINCRONAV }
         *     
         */
        public Ejecutar.TRCONSTITOTALACTRNI.RESPUESTASINCRONAV getRESPUESTASINCRONAV() {
            return respuestasincronav;
        }

        /**
         * Define el valor de la propiedad respuestasincronav.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.TRCONSTITOTALACTRNI.RESPUESTASINCRONAV }
         *     
         */
        public void setRESPUESTASINCRONAV(Ejecutar.TRCONSTITOTALACTRNI.RESPUESTASINCRONAV value) {
            this.respuestasincronav = value;
        }

        /**
         * Obtiene el valor de la propiedad nrollamadav.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.TRCONSTITOTALACTRNI.NROLLAMADAV }
         *     
         */
        public Ejecutar.TRCONSTITOTALACTRNI.NROLLAMADAV getNROLLAMADAV() {
            return nrollamadav;
        }

        /**
         * Define el valor de la propiedad nrollamadav.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.TRCONSTITOTALACTRNI.NROLLAMADAV }
         *     
         */
        public void setNROLLAMADAV(Ejecutar.TRCONSTITOTALACTRNI.NROLLAMADAV value) {
            this.nrollamadav = value;
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
        public static class NROLLAMADAV {

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
        public static class RESPUESTASINCRONAV {

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
         *         &lt;element name="IND_BORRADO_AR">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="DESCR_TX">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000050"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IND_AUT_SOLIC">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IND_ATRIB_MANC_EP">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_ESTADO_AR">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="ID_EMPL_SOL_AUT">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000008"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IND_VERIF_ATRIB">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IND_URG_AR">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="MOTIVO_ACCION_AUT_LEN">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="9999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="MOTIVO_ACCION_AUT">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000200"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IND_ESCALABLE">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IMP_AUT">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="0000000014"/>
         *               &lt;fractionDigits value="02"/>
         *               &lt;maxInclusive value="999999999999.99"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IMPORTE_AR">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="0000000014"/>
         *               &lt;fractionDigits value="02"/>
         *               &lt;maxInclusive value="999999999999.99"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="AR_AUT_REMOTA_P">
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
         *                   &lt;element name="ID_INTERNO_TERM_TN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000008"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
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
         *         &lt;element name="AR_TRN_MSJ_PARM_V" maxOccurs="10">
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
         *         &lt;element name="STD_TARGET_TERMINAL_V" maxOccurs="50">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="ID_INTERNO_EMPL_EP">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000008"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="ID_INTERNO_TERM_TN">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000008"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_ECV_SESION">
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
         *         &lt;element name="AR_ID_SALTADO_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="ID_INTERNO_EMPL_EP">
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
            "indborradoar",
            "descrtx",
            "indautsolic",
            "indatribmancep",
            "codestadoar",
            "idemplsolaut",
            "indverifatrib",
            "indurgar",
            "motivoaccionautlen",
            "motivoaccionaut",
            "indescalable",
            "impaut",
            "importear",
            "arautremotap",
            "artrnmsjparmv",
            "stdtargetterminalv",
            "aridsaltadov"
        })
        public static class STDAUTORIZAV {

            @XmlElement(name = "IND_BORRADO_AR", required = true)
            protected String indborradoar;
            @XmlElement(name = "DESCR_TX", required = true)
            protected String descrtx;
            @XmlElement(name = "IND_AUT_SOLIC", required = true)
            protected String indautsolic;
            @XmlElement(name = "IND_ATRIB_MANC_EP", required = true)
            protected String indatribmancep;
            @XmlElement(name = "COD_ESTADO_AR", required = true)
            protected String codestadoar;
            @XmlElement(name = "ID_EMPL_SOL_AUT", required = true)
            protected String idemplsolaut;
            @XmlElement(name = "IND_VERIF_ATRIB", required = true)
            protected String indverifatrib;
            @XmlElement(name = "IND_URG_AR", required = true)
            protected String indurgar;
            @XmlElement(name = "MOTIVO_ACCION_AUT_LEN")
            protected int motivoaccionautlen;
            @XmlElement(name = "MOTIVO_ACCION_AUT", required = true)
            protected String motivoaccionaut;
            @XmlElement(name = "IND_ESCALABLE", required = true)
            protected String indescalable;
            @XmlElement(name = "IMP_AUT", required = true)
            protected BigDecimal impaut;
            @XmlElement(name = "IMPORTE_AR", required = true)
            protected BigDecimal importear;
            @XmlElement(name = "AR_AUT_REMOTA_P", required = true)
            protected Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARAUTREMOTAP arautremotap;
            @XmlElement(name = "AR_TRN_MSJ_PARM_V", required = true)
            protected List<Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARTRNMSJPARMV> artrnmsjparmv;
            @XmlElement(name = "STD_TARGET_TERMINAL_V", required = true)
            protected List<Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.STDTARGETTERMINALV> stdtargetterminalv;
            @XmlElement(name = "AR_ID_SALTADO_V", required = true)
            protected Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARIDSALTADOV aridsaltadov;

            /**
             * Obtiene el valor de la propiedad indborradoar.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getINDBORRADOAR() {
                return indborradoar;
            }

            /**
             * Define el valor de la propiedad indborradoar.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setINDBORRADOAR(String value) {
                this.indborradoar = value;
            }

            /**
             * Obtiene el valor de la propiedad descrtx.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDESCRTX() {
                return descrtx;
            }

            /**
             * Define el valor de la propiedad descrtx.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDESCRTX(String value) {
                this.descrtx = value;
            }

            /**
             * Obtiene el valor de la propiedad indautsolic.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getINDAUTSOLIC() {
                return indautsolic;
            }

            /**
             * Define el valor de la propiedad indautsolic.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setINDAUTSOLIC(String value) {
                this.indautsolic = value;
            }

            /**
             * Obtiene el valor de la propiedad indatribmancep.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getINDATRIBMANCEP() {
                return indatribmancep;
            }

            /**
             * Define el valor de la propiedad indatribmancep.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setINDATRIBMANCEP(String value) {
                this.indatribmancep = value;
            }

            /**
             * Obtiene el valor de la propiedad codestadoar.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODESTADOAR() {
                return codestadoar;
            }

            /**
             * Define el valor de la propiedad codestadoar.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODESTADOAR(String value) {
                this.codestadoar = value;
            }

            /**
             * Obtiene el valor de la propiedad idemplsolaut.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIDEMPLSOLAUT() {
                return idemplsolaut;
            }

            /**
             * Define el valor de la propiedad idemplsolaut.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIDEMPLSOLAUT(String value) {
                this.idemplsolaut = value;
            }

            /**
             * Obtiene el valor de la propiedad indverifatrib.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getINDVERIFATRIB() {
                return indverifatrib;
            }

            /**
             * Define el valor de la propiedad indverifatrib.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setINDVERIFATRIB(String value) {
                this.indverifatrib = value;
            }

            /**
             * Obtiene el valor de la propiedad indurgar.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getINDURGAR() {
                return indurgar;
            }

            /**
             * Define el valor de la propiedad indurgar.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setINDURGAR(String value) {
                this.indurgar = value;
            }

            /**
             * Obtiene el valor de la propiedad motivoaccionautlen.
             * 
             */
            public int getMOTIVOACCIONAUTLEN() {
                return motivoaccionautlen;
            }

            /**
             * Define el valor de la propiedad motivoaccionautlen.
             * 
             */
            public void setMOTIVOACCIONAUTLEN(int value) {
                this.motivoaccionautlen = value;
            }

            /**
             * Obtiene el valor de la propiedad motivoaccionaut.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMOTIVOACCIONAUT() {
                return motivoaccionaut;
            }

            /**
             * Define el valor de la propiedad motivoaccionaut.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMOTIVOACCIONAUT(String value) {
                this.motivoaccionaut = value;
            }

            /**
             * Obtiene el valor de la propiedad indescalable.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getINDESCALABLE() {
                return indescalable;
            }

            /**
             * Define el valor de la propiedad indescalable.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setINDESCALABLE(String value) {
                this.indescalable = value;
            }

            /**
             * Obtiene el valor de la propiedad impaut.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getIMPAUT() {
                return impaut;
            }

            /**
             * Define el valor de la propiedad impaut.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setIMPAUT(BigDecimal value) {
                this.impaut = value;
            }

            /**
             * Obtiene el valor de la propiedad importear.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getIMPORTEAR() {
                return importear;
            }

            /**
             * Define el valor de la propiedad importear.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setIMPORTEAR(BigDecimal value) {
                this.importear = value;
            }

            /**
             * Obtiene el valor de la propiedad arautremotap.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARAUTREMOTAP }
             *     
             */
            public Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARAUTREMOTAP getARAUTREMOTAP() {
                return arautremotap;
            }

            /**
             * Define el valor de la propiedad arautremotap.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARAUTREMOTAP }
             *     
             */
            public void setARAUTREMOTAP(Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARAUTREMOTAP value) {
                this.arautremotap = value;
            }

            /**
             * Gets the value of the artrnmsjparmv property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the artrnmsjparmv property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getARTRNMSJPARMV().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARTRNMSJPARMV }
             * 
             * 
             */
            public List<Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARTRNMSJPARMV> getARTRNMSJPARMV() {
                if (artrnmsjparmv == null) {
                    artrnmsjparmv = new ArrayList<Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARTRNMSJPARMV>();
                }
                return this.artrnmsjparmv;
            }

            /**
             * Gets the value of the stdtargetterminalv property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the stdtargetterminalv property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getSTDTARGETTERMINALV().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.STDTARGETTERMINALV }
             * 
             * 
             */
            public List<Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.STDTARGETTERMINALV> getSTDTARGETTERMINALV() {
                if (stdtargetterminalv == null) {
                    stdtargetterminalv = new ArrayList<Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.STDTARGETTERMINALV>();
                }
                return this.stdtargetterminalv;
            }

            /**
             * Obtiene el valor de la propiedad aridsaltadov.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARIDSALTADOV }
             *     
             */
            public Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARIDSALTADOV getARIDSALTADOV() {
                return aridsaltadov;
            }

            /**
             * Define el valor de la propiedad aridsaltadov.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARIDSALTADOV }
             *     
             */
            public void setARIDSALTADOV(Ejecutar.TRCONSTITOTALACTRNI.STDAUTORIZAV.ARIDSALTADOV value) {
                this.aridsaltadov = value;
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
             *         &lt;element name="ID_INTERNO_TERM_TN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000008"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
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
                "codnrbeen",
                "idinternotermtn",
                "fechaoprcn",
                "horaoprcn"
            })
            public static class ARAUTREMOTAP {

                @XmlElement(name = "COD_NRBE_EN", required = true)
                protected String codnrbeen;
                @XmlElement(name = "ID_INTERNO_TERM_TN", required = true)
                protected String idinternotermtn;
                @XmlElement(name = "FECHA_OPRCN")
                protected int fechaoprcn;
                @XmlElement(name = "HORA_OPRCN")
                protected int horaoprcn;

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
             *         &lt;element name="ID_INTERNO_EMPL_EP">
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
                "idinternoemplep"
            })
            public static class ARIDSALTADOV {

                @XmlElement(name = "ID_INTERNO_EMPL_EP", required = true)
                protected String idinternoemplep;

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
            public static class ARTRNMSJPARMV {

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
             *         &lt;element name="ID_INTERNO_EMPL_EP">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000008"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="ID_INTERNO_TERM_TN">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000008"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_ECV_SESION">
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
                "idinternoemplep",
                "idinternotermtn",
                "codecvsesion"
            })
            public static class STDTARGETTERMINALV {

                @XmlElement(name = "ID_INTERNO_EMPL_EP", required = true)
                protected String idinternoemplep;
                @XmlElement(name = "ID_INTERNO_TERM_TN", required = true)
                protected String idinternotermtn;
                @XmlElement(name = "COD_ECV_SESION", required = true)
                protected String codecvsesion;

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
                 * Obtiene el valor de la propiedad codecvsesion.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODECVSESION() {
                    return codecvsesion;
                }

                /**
                 * Define el valor de la propiedad codecvsesion.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODECVSESION(String value) {
                    this.codecvsesion = value;
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
         *         &lt;element name="PSV_ERROR_V" maxOccurs="5">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="COD_ERROR">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000005"/>
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
         *                   &lt;element name="ATRIBUIDO_S_N">
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
         *         &lt;element name="PSV_IMP_AUT_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="IMP_AUT">
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
         *         &lt;element name="FECHA_V">
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
         *         &lt;element name="ACV_TIPO_COBRO_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="STD_CHAR_02">
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
         *         &lt;element name="ACV_LIM_CONCEDIDO_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="IMP_LIMITE">
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
         *         &lt;element name="RX_AC_RL_AC_P">
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
         *                   &lt;element name="COD_CENT_UO_1">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000004"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="NUM_SEC_AC_1">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_RL_AC_AC">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="COD_CENT_UO_2">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000004"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="NUM_SEC_AC_2">
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
         *         &lt;element name="AC_CUADRO_IRREG_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="STD_SMALL_INT">
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
         *         &lt;element name="SIT_IRREG_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="STD_CHAR_10">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000010"/>
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
            "psverrorv",
            "psvimpautv",
            "acacp",
            "fechav",
            "acvtipocobrov",
            "acvlimconcedidov",
            "rxacrlacp",
            "accuadroirregv",
            "sitirregv"
        })
        public static class TRCONSTITOTALACEVTY {

            @XmlElement(name = "PSV_ERROR_V", required = true)
            protected List<Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.PSVERRORV> psverrorv;
            @XmlElement(name = "PSV_IMP_AUT_V", required = true)
            protected Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.PSVIMPAUTV psvimpautv;
            @XmlElement(name = "AC_AC_P", required = true)
            protected Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACACP acacp;
            @XmlElement(name = "FECHA_V", required = true)
            protected Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.FECHAV fechav;
            @XmlElement(name = "ACV_TIPO_COBRO_V", required = true)
            protected Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACVTIPOCOBROV acvtipocobrov;
            @XmlElement(name = "ACV_LIM_CONCEDIDO_V", required = true)
            protected Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACVLIMCONCEDIDOV acvlimconcedidov;
            @XmlElement(name = "RX_AC_RL_AC_P", required = true)
            protected Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.RXACRLACP rxacrlacp;
            @XmlElement(name = "AC_CUADRO_IRREG_V", required = true)
            protected Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACCUADROIRREGV accuadroirregv;
            @XmlElement(name = "SIT_IRREG_V", required = true)
            protected Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.SITIRREGV sitirregv;

            /**
             * Gets the value of the psverrorv property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the psverrorv property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getPSVERRORV().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.PSVERRORV }
             * 
             * 
             */
            public List<Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.PSVERRORV> getPSVERRORV() {
                if (psverrorv == null) {
                    psverrorv = new ArrayList<Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.PSVERRORV>();
                }
                return this.psverrorv;
            }

            /**
             * Obtiene el valor de la propiedad psvimpautv.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.PSVIMPAUTV }
             *     
             */
            public Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.PSVIMPAUTV getPSVIMPAUTV() {
                return psvimpautv;
            }

            /**
             * Define el valor de la propiedad psvimpautv.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.PSVIMPAUTV }
             *     
             */
            public void setPSVIMPAUTV(Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.PSVIMPAUTV value) {
                this.psvimpautv = value;
            }

            /**
             * Obtiene el valor de la propiedad acacp.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACACP }
             *     
             */
            public Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACACP getACACP() {
                return acacp;
            }

            /**
             * Define el valor de la propiedad acacp.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACACP }
             *     
             */
            public void setACACP(Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACACP value) {
                this.acacp = value;
            }

            /**
             * Obtiene el valor de la propiedad fechav.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.FECHAV }
             *     
             */
            public Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.FECHAV getFECHAV() {
                return fechav;
            }

            /**
             * Define el valor de la propiedad fechav.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.FECHAV }
             *     
             */
            public void setFECHAV(Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.FECHAV value) {
                this.fechav = value;
            }

            /**
             * Obtiene el valor de la propiedad acvtipocobrov.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACVTIPOCOBROV }
             *     
             */
            public Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACVTIPOCOBROV getACVTIPOCOBROV() {
                return acvtipocobrov;
            }

            /**
             * Define el valor de la propiedad acvtipocobrov.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACVTIPOCOBROV }
             *     
             */
            public void setACVTIPOCOBROV(Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACVTIPOCOBROV value) {
                this.acvtipocobrov = value;
            }

            /**
             * Obtiene el valor de la propiedad acvlimconcedidov.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACVLIMCONCEDIDOV }
             *     
             */
            public Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACVLIMCONCEDIDOV getACVLIMCONCEDIDOV() {
                return acvlimconcedidov;
            }

            /**
             * Define el valor de la propiedad acvlimconcedidov.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACVLIMCONCEDIDOV }
             *     
             */
            public void setACVLIMCONCEDIDOV(Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACVLIMCONCEDIDOV value) {
                this.acvlimconcedidov = value;
            }

            /**
             * Obtiene el valor de la propiedad rxacrlacp.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.RXACRLACP }
             *     
             */
            public Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.RXACRLACP getRXACRLACP() {
                return rxacrlacp;
            }

            /**
             * Define el valor de la propiedad rxacrlacp.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.RXACRLACP }
             *     
             */
            public void setRXACRLACP(Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.RXACRLACP value) {
                this.rxacrlacp = value;
            }

            /**
             * Obtiene el valor de la propiedad accuadroirregv.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACCUADROIRREGV }
             *     
             */
            public Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACCUADROIRREGV getACCUADROIRREGV() {
                return accuadroirregv;
            }

            /**
             * Define el valor de la propiedad accuadroirregv.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACCUADROIRREGV }
             *     
             */
            public void setACCUADROIRREGV(Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.ACCUADROIRREGV value) {
                this.accuadroirregv = value;
            }

            /**
             * Obtiene el valor de la propiedad sitirregv.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.SITIRREGV }
             *     
             */
            public Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.SITIRREGV getSITIRREGV() {
                return sitirregv;
            }

            /**
             * Define el valor de la propiedad sitirregv.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.SITIRREGV }
             *     
             */
            public void setSITIRREGV(Ejecutar.TRCONSTITOTALACTRNI.TRCONSTITOTALACEVTY.SITIRREGV value) {
                this.sitirregv = value;
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
             *         &lt;element name="STD_SMALL_INT">
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
                "stdsmallint"
            })
            public static class ACCUADROIRREGV {

                @XmlElement(name = "STD_SMALL_INT")
                protected int stdsmallint;

                /**
                 * Obtiene el valor de la propiedad stdsmallint.
                 * 
                 */
                public int getSTDSMALLINT() {
                    return stdsmallint;
                }

                /**
                 * Define el valor de la propiedad stdsmallint.
                 * 
                 */
                public void setSTDSMALLINT(int value) {
                    this.stdsmallint = value;
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
             *         &lt;element name="IMP_LIMITE">
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
                "implimite"
            })
            public static class ACVLIMCONCEDIDOV {

                @XmlElement(name = "IMP_LIMITE", required = true)
                protected BigDecimal implimite;

                /**
                 * Obtiene el valor de la propiedad implimite.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getIMPLIMITE() {
                    return implimite;
                }

                /**
                 * Define el valor de la propiedad implimite.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setIMPLIMITE(BigDecimal value) {
                    this.implimite = value;
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
             *         &lt;element name="STD_CHAR_02">
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
                "stdchar02"
            })
            public static class ACVTIPOCOBROV {

                @XmlElement(name = "STD_CHAR_02", required = true)
                protected String stdchar02;

                /**
                 * Obtiene el valor de la propiedad stdchar02.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSTDCHAR02() {
                    return stdchar02;
                }

                /**
                 * Define el valor de la propiedad stdchar02.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSTDCHAR02(String value) {
                    this.stdchar02 = value;
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
            public static class FECHAV {

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
                public void setSTDFECHA(int value) {
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
             *         &lt;element name="COD_ERROR">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000005"/>
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
             *         &lt;element name="ATRIBUIDO_S_N">
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
                "coderror",
                "idemplaut",
                "atribuidosn"
            })
            public static class PSVERRORV {

                @XmlElement(name = "COD_ERROR", required = true)
                protected String coderror;
                @XmlElement(name = "ID_EMPL_AUT", required = true)
                protected String idemplaut;
                @XmlElement(name = "ATRIBUIDO_S_N", required = true)
                protected String atribuidosn;

                /**
                 * Obtiene el valor de la propiedad coderror.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODERROR() {
                    return coderror;
                }

                /**
                 * Define el valor de la propiedad coderror.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODERROR(String value) {
                    this.coderror = value;
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
                 * Obtiene el valor de la propiedad atribuidosn.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getATRIBUIDOSN() {
                    return atribuidosn;
                }

                /**
                 * Define el valor de la propiedad atribuidosn.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setATRIBUIDOSN(String value) {
                    this.atribuidosn = value;
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
             *         &lt;element name="IMP_AUT">
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
                "impaut"
            })
            public static class PSVIMPAUTV {

                @XmlElement(name = "IMP_AUT", required = true)
                protected BigDecimal impaut;

                /**
                 * Obtiene el valor de la propiedad impaut.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigDecimal }
                 *     
                 */
                public BigDecimal getIMPAUT() {
                    return impaut;
                }

                /**
                 * Define el valor de la propiedad impaut.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigDecimal }
                 *     
                 */
                public void setIMPAUT(BigDecimal value) {
                    this.impaut = value;
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
             *         &lt;element name="COD_CENT_UO_1">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000004"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="NUM_SEC_AC_1">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_RL_AC_AC">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="COD_CENT_UO_2">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000004"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="NUM_SEC_AC_2">
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
                "codcentuo1",
                "numsecac1",
                "codrlacac",
                "codcentuo2",
                "numsecac2"
            })
            public static class RXACRLACP {

                @XmlElement(name = "COD_NRBE_EN", required = true)
                protected String codnrbeen;
                @XmlElement(name = "COD_CENT_UO_1", required = true)
                protected String codcentuo1;
                @XmlElement(name = "NUM_SEC_AC_1")
                protected long numsecac1;
                @XmlElement(name = "COD_RL_AC_AC", required = true)
                protected String codrlacac;
                @XmlElement(name = "COD_CENT_UO_2", required = true)
                protected String codcentuo2;
                @XmlElement(name = "NUM_SEC_AC_2")
                protected long numsecac2;

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
                 * Obtiene el valor de la propiedad codcentuo1.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODCENTUO1() {
                    return codcentuo1;
                }

                /**
                 * Define el valor de la propiedad codcentuo1.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODCENTUO1(String value) {
                    this.codcentuo1 = value;
                }

                /**
                 * Obtiene el valor de la propiedad numsecac1.
                 * 
                 */
                public long getNUMSECAC1() {
                    return numsecac1;
                }

                /**
                 * Define el valor de la propiedad numsecac1.
                 * 
                 */
                public void setNUMSECAC1(long value) {
                    this.numsecac1 = value;
                }

                /**
                 * Obtiene el valor de la propiedad codrlacac.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODRLACAC() {
                    return codrlacac;
                }

                /**
                 * Define el valor de la propiedad codrlacac.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODRLACAC(String value) {
                    this.codrlacac = value;
                }

                /**
                 * Obtiene el valor de la propiedad codcentuo2.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODCENTUO2() {
                    return codcentuo2;
                }

                /**
                 * Define el valor de la propiedad codcentuo2.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODCENTUO2(String value) {
                    this.codcentuo2 = value;
                }

                /**
                 * Obtiene el valor de la propiedad numsecac2.
                 * 
                 */
                public long getNUMSECAC2() {
                    return numsecac2;
                }

                /**
                 * Define el valor de la propiedad numsecac2.
                 * 
                 */
                public void setNUMSECAC2(long value) {
                    this.numsecac2 = value;
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
             *         &lt;element name="STD_CHAR_10">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000010"/>
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
                "stdchar10"
            })
            public static class SITIRREGV {

                @XmlElement(name = "STD_CHAR_10", required = true)
                protected String stdchar10;

                /**
                 * Obtiene el valor de la propiedad stdchar10.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getSTDCHAR10() {
                    return stdchar10;
                }

                /**
                 * Define el valor de la propiedad stdchar10.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setSTDCHAR10(String value) {
                    this.stdchar10 = value;
                }

            }

        }

    }

}
