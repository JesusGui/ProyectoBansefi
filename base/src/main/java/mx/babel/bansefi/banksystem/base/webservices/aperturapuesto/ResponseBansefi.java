
package mx.babel.bansefi.banksystem.base.webservices.aperturapuesto;

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
 *         &lt;element name="OTR_APERTURA_PUESTOS_TRN">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
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
 *                   &lt;element name="TR_APERTURA_PUESTOS_EVT_Z">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PERFIL_TRAN_TX_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="CR_PERFIL_TRAN_TX_V" maxOccurs="200">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="FECHA_OPRCN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="HORA_OPRCN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_TX">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000008"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_ACCION">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000001"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_APLCCN_SUBAPL">
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
 *                             &lt;element name="PERFIL_ENTIDAD_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="CR_ENT_PERFIL_TX_E">
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
 *                                                 &lt;element name="NOM_PERFIL_EN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000020"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FECHA_OPRCN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="HORA_OPRCN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FECHA_FIN_PERFIL">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FECHA_MOD_PERFIL">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_PERFIL">
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
 *                             &lt;element name="PERFIL_EMPLEADO_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="EP_EMPL_PERFIL_TX_E">
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
 *                                                 &lt;element name="ID_INTERNO_EMPL_EP">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000008"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="NOM_PERFIL_EN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                       &lt;length value="0000000020"/>
 *                                                       &lt;whiteSpace value="preserve"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FECHA_OPRCN_1">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="HORA_OPRCN_1">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FECHA_OPRCN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="HORA_OPRCN">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="FECHA_FIN_PERFIL">
 *                                                   &lt;simpleType>
 *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                       &lt;maxInclusive value="99999999"/>
 *                                                       &lt;minInclusive value="0"/>
 *                                                     &lt;/restriction>
 *                                                   &lt;/simpleType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="COD_PERFIL">
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
 *                                       &lt;element name="FECHA_PERFIL">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="99999999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="HORA_PERFIL">
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
 *                             &lt;element name="STD_AN_AL_MSJ_V">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="STD_AN_AV_MSJ_V">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="AN_NUM_ANOTACIONES_V">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="STD_DEC_3">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="STD_AN_AV_MSJ_LS" maxOccurs="5">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="NUMERO_ANTCN">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                                                 &lt;maxInclusive value="999999999999999"/>
 *                                                                 &lt;minInclusive value="0"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="COD_ANTCN">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="DESCRIP_ANTCN_V" maxOccurs="5">
 *                                                             &lt;complexType>
 *                                                               &lt;complexContent>
 *                                                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                                   &lt;sequence>
 *                                                                     &lt;element name="STD_CHAR_11">
 *                                                                       &lt;simpleType>
 *                                                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                           &lt;length value="0000000011"/>
 *                                                                           &lt;whiteSpace value="preserve"/>
 *                                                                         &lt;/restriction>
 *                                                                       &lt;/simpleType>
 *                                                                     &lt;/element>
 *                                                                   &lt;/sequence>
 *                                                                 &lt;/restriction>
 *                                                               &lt;/complexContent>
 *                                                             &lt;/complexType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="IND_PRDAD">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000001"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="DESC_IND_PRDAD_V" maxOccurs="5">
 *                                                             &lt;complexType>
 *                                                               &lt;complexContent>
 *                                                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                                   &lt;sequence>
 *                                                                     &lt;element name="STD_CHAR_07">
 *                                                                       &lt;simpleType>
 *                                                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                           &lt;length value="0000000007"/>
 *                                                                           &lt;whiteSpace value="preserve"/>
 *                                                                         &lt;/restriction>
 *                                                                       &lt;/simpleType>
 *                                                                     &lt;/element>
 *                                                                   &lt;/sequence>
 *                                                                 &lt;/restriction>
 *                                                               &lt;/complexContent>
 *                                                             &lt;/complexType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="SUBCD_ANTCN">
 *                                                             &lt;simpleType>
 *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                 &lt;length value="0000000002"/>
 *                                                                 &lt;whiteSpace value="preserve"/>
 *                                                               &lt;/restriction>
 *                                                             &lt;/simpleType>
 *                                                           &lt;/element>
 *                                                           &lt;element name="STD_DESCR_C_ANTCN_V" maxOccurs="5">
 *                                                             &lt;complexType>
 *                                                               &lt;complexContent>
 *                                                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                                   &lt;sequence>
 *                                                                     &lt;element name="STD_CHAR_30">
 *                                                                       &lt;simpleType>
 *                                                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                                                           &lt;length value="0000000030"/>
 *                                                                           &lt;whiteSpace value="preserve"/>
 *                                                                         &lt;/restriction>
 *                                                                       &lt;/simpleType>
 *                                                                     &lt;/element>
 *                                                                   &lt;/sequence>
 *                                                                 &lt;/restriction>
 *                                                               &lt;/complexContent>
 *                                                             &lt;/complexType>
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
 *                             &lt;element name="FECHA_CTBLE">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="99999999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NUM_DIAS_BAK_DI">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="9999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NUM_DIAS_BORR_DI">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="9999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NUM_TLFNO_DOMIC">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000020"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NOMB_CENT_UO">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000036"/>
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
 *                             &lt;element name="IMP_INI">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="0000000015"/>
 *                                   &lt;fractionDigits value="02"/>
 *                                   &lt;maxInclusive value="9999999999999.99"/>
 *                                   &lt;minInclusive value="0.0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IMPAC_SDO_PNTAL">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="0000000019"/>
 *                                   &lt;fractionDigits value="02"/>
 *                                   &lt;maxInclusive value="99999999999999999.99"/>
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
 *                             &lt;element name="NOMB_ENT_EN">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000036"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="SALDO_CONT_DISPONIBLE_V">
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
 *                             &lt;element name="RESUL_APERTURA_V">
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
 *                             &lt;element name="MS_PEND_CNT_V">
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
 *                                       &lt;element name="STD_SMALL_INT_1">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="STD_SMALL_INT_2">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
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
 *                             &lt;element name="AR_PEND_CNT_V">
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
 *                                       &lt;element name="STD_SMALL_INT_1">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="STD_SMALL_INT_2">
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
 *                             &lt;element name="PUESTO_PRINCIPAL">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IMP_TOTAL">
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
 *                   &lt;element name="RTRN_CD">
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
    "otraperturapuestostrn"
})
public class ResponseBansefi {

    @XmlElement(name = "OTR_APERTURA_PUESTOS_TRN", required = true)
    protected ResponseBansefi.OTRAPERTURAPUESTOSTRN otraperturapuestostrn;

    /**
     * Obtiene el valor de la propiedad otraperturapuestostrn.
     * 
     * @return
     *     possible object is
     *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN }
     *     
     */
    public ResponseBansefi.OTRAPERTURAPUESTOSTRN getOTRAPERTURAPUESTOSTRN() {
        return otraperturapuestostrn;
    }

    /**
     * Define el valor de la propiedad otraperturapuestostrn.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN }
     *     
     */
    public void setOTRAPERTURAPUESTOSTRN(ResponseBansefi.OTRAPERTURAPUESTOSTRN value) {
        this.otraperturapuestostrn = value;
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
     *         &lt;element name="TR_APERTURA_PUESTOS_EVT_Z">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="PERFIL_TRAN_TX_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="CR_PERFIL_TRAN_TX_V" maxOccurs="200">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
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
     *                                       &lt;element name="COD_TX">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000008"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_ACCION">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000001"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_APLCCN_SUBAPL">
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
     *                   &lt;element name="PERFIL_ENTIDAD_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="CR_ENT_PERFIL_TX_E">
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
     *                                       &lt;element name="NOM_PERFIL_EN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000020"/>
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
     *                                       &lt;element name="FECHA_FIN_PERFIL">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="99999999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FECHA_MOD_PERFIL">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="99999999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_PERFIL">
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
     *                   &lt;element name="PERFIL_EMPLEADO_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="EP_EMPL_PERFIL_TX_E">
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
     *                                       &lt;element name="NOM_PERFIL_EN">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                             &lt;length value="0000000020"/>
     *                                             &lt;whiteSpace value="preserve"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="FECHA_OPRCN_1">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="99999999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="HORA_OPRCN_1">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="99999999"/>
     *                                             &lt;minInclusive value="0"/>
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
     *                                       &lt;element name="FECHA_FIN_PERFIL">
     *                                         &lt;simpleType>
     *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                             &lt;maxInclusive value="99999999"/>
     *                                             &lt;minInclusive value="0"/>
     *                                           &lt;/restriction>
     *                                         &lt;/simpleType>
     *                                       &lt;/element>
     *                                       &lt;element name="COD_PERFIL">
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
     *                             &lt;element name="FECHA_PERFIL">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="99999999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="HORA_PERFIL">
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
     *                   &lt;element name="STD_AN_AL_MSJ_V">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="STD_AN_AV_MSJ_V">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="AN_NUM_ANOTACIONES_V">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="STD_DEC_3">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                                       &lt;maxInclusive value="999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                       &lt;element name="STD_AN_AV_MSJ_LS" maxOccurs="5">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="NUMERO_ANTCN">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                                       &lt;maxInclusive value="999999999999999"/>
     *                                                       &lt;minInclusive value="0"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="COD_ANTCN">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000001"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="DESCRIP_ANTCN_V" maxOccurs="5">
     *                                                   &lt;complexType>
     *                                                     &lt;complexContent>
     *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                                         &lt;sequence>
     *                                                           &lt;element name="STD_CHAR_11">
     *                                                             &lt;simpleType>
     *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                                 &lt;length value="0000000011"/>
     *                                                                 &lt;whiteSpace value="preserve"/>
     *                                                               &lt;/restriction>
     *                                                             &lt;/simpleType>
     *                                                           &lt;/element>
     *                                                         &lt;/sequence>
     *                                                       &lt;/restriction>
     *                                                     &lt;/complexContent>
     *                                                   &lt;/complexType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="IND_PRDAD">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000001"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="DESC_IND_PRDAD_V" maxOccurs="5">
     *                                                   &lt;complexType>
     *                                                     &lt;complexContent>
     *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                                         &lt;sequence>
     *                                                           &lt;element name="STD_CHAR_07">
     *                                                             &lt;simpleType>
     *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                                 &lt;length value="0000000007"/>
     *                                                                 &lt;whiteSpace value="preserve"/>
     *                                                               &lt;/restriction>
     *                                                             &lt;/simpleType>
     *                                                           &lt;/element>
     *                                                         &lt;/sequence>
     *                                                       &lt;/restriction>
     *                                                     &lt;/complexContent>
     *                                                   &lt;/complexType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="SUBCD_ANTCN">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                       &lt;length value="0000000002"/>
     *                                                       &lt;whiteSpace value="preserve"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                                 &lt;element name="STD_DESCR_C_ANTCN_V" maxOccurs="5">
     *                                                   &lt;complexType>
     *                                                     &lt;complexContent>
     *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                                         &lt;sequence>
     *                                                           &lt;element name="STD_CHAR_30">
     *                                                             &lt;simpleType>
     *                                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                                                 &lt;length value="0000000030"/>
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
     *                   &lt;element name="FECHA_CTBLE">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="99999999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NUM_DIAS_BAK_DI">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="9999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NUM_DIAS_BORR_DI">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="9999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NUM_TLFNO_DOMIC">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000020"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NOMB_CENT_UO">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000036"/>
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
     *                   &lt;element name="IMP_INI">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="0000000015"/>
     *                         &lt;fractionDigits value="02"/>
     *                         &lt;maxInclusive value="9999999999999.99"/>
     *                         &lt;minInclusive value="0.0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IMPAC_SDO_PNTAL">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="0000000019"/>
     *                         &lt;fractionDigits value="02"/>
     *                         &lt;maxInclusive value="99999999999999999.99"/>
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
     *                   &lt;element name="NOMB_ENT_EN">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000036"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="SALDO_CONT_DISPONIBLE_V">
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
     *                   &lt;element name="RESUL_APERTURA_V">
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
     *                   &lt;element name="MS_PEND_CNT_V">
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
     *                             &lt;element name="STD_SMALL_INT_1">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="STD_SMALL_INT_2">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
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
     *                   &lt;element name="AR_PEND_CNT_V">
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
     *                             &lt;element name="STD_SMALL_INT_1">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="STD_SMALL_INT_2">
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
     *                   &lt;element name="PUESTO_PRINCIPAL">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IMP_TOTAL">
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
     *         &lt;element name="RTRN_CD">
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
        "stdtrnoparmv",
        "stdtrnmsjparmv",
        "traperturapuestosevtz",
        "rtrncd"
    })
    public static class OTRAPERTURAPUESTOSTRN {

        @XmlElement(name = "STD_TRN_O_PARM_V", required = true)
        protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.STDTRNOPARMV stdtrnoparmv;
        @XmlElement(name = "STD_TRN_MSJ_PARM_V", required = true)
        protected List<ResponseBansefi.OTRAPERTURAPUESTOSTRN.STDTRNMSJPARMV> stdtrnmsjparmv;
        @XmlElement(name = "TR_APERTURA_PUESTOS_EVT_Z", required = true)
        protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ traperturapuestosevtz;
        @XmlElement(name = "RTRN_CD")
        protected int rtrncd;

        /**
         * Obtiene el valor de la propiedad stdtrnoparmv.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.STDTRNOPARMV }
         *     
         */
        public ResponseBansefi.OTRAPERTURAPUESTOSTRN.STDTRNOPARMV getSTDTRNOPARMV() {
            return stdtrnoparmv;
        }

        /**
         * Define el valor de la propiedad stdtrnoparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.STDTRNOPARMV }
         *     
         */
        public void setSTDTRNOPARMV(ResponseBansefi.OTRAPERTURAPUESTOSTRN.STDTRNOPARMV value) {
            this.stdtrnoparmv = value;
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
         * {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.STDTRNMSJPARMV }
         * 
         * 
         */
        public List<ResponseBansefi.OTRAPERTURAPUESTOSTRN.STDTRNMSJPARMV> getSTDTRNMSJPARMV() {
            if (stdtrnmsjparmv == null) {
                stdtrnmsjparmv = new ArrayList<ResponseBansefi.OTRAPERTURAPUESTOSTRN.STDTRNMSJPARMV>();
            }
            return this.stdtrnmsjparmv;
        }

        /**
         * Obtiene el valor de la propiedad traperturapuestosevtz.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ }
         *     
         */
        public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ getTRAPERTURAPUESTOSEVTZ() {
            return traperturapuestosevtz;
        }

        /**
         * Define el valor de la propiedad traperturapuestosevtz.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ }
         *     
         */
        public void setTRAPERTURAPUESTOSEVTZ(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ value) {
            this.traperturapuestosevtz = value;
        }

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
         *         &lt;element name="PERFIL_TRAN_TX_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="CR_PERFIL_TRAN_TX_V" maxOccurs="200">
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
         *                             &lt;element name="COD_TX">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000008"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_ACCION">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000001"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_APLCCN_SUBAPL">
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
         *         &lt;element name="PERFIL_ENTIDAD_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="CR_ENT_PERFIL_TX_E">
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
         *                             &lt;element name="NOM_PERFIL_EN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000020"/>
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
         *                             &lt;element name="FECHA_FIN_PERFIL">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="99999999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FECHA_MOD_PERFIL">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="99999999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_PERFIL">
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
         *         &lt;element name="PERFIL_EMPLEADO_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="EP_EMPL_PERFIL_TX_E">
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
         *                             &lt;element name="NOM_PERFIL_EN">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                                   &lt;length value="0000000020"/>
         *                                   &lt;whiteSpace value="preserve"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="FECHA_OPRCN_1">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="99999999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="HORA_OPRCN_1">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="99999999"/>
         *                                   &lt;minInclusive value="0"/>
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
         *                             &lt;element name="FECHA_FIN_PERFIL">
         *                               &lt;simpleType>
         *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                                   &lt;maxInclusive value="99999999"/>
         *                                   &lt;minInclusive value="0"/>
         *                                 &lt;/restriction>
         *                               &lt;/simpleType>
         *                             &lt;/element>
         *                             &lt;element name="COD_PERFIL">
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
         *                   &lt;element name="FECHA_PERFIL">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="99999999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="HORA_PERFIL">
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
         *         &lt;element name="STD_AN_AL_MSJ_V">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
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
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="FECHA_CTBLE">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="99999999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NUM_DIAS_BAK_DI">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="9999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NUM_DIAS_BORR_DI">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="9999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NUM_TLFNO_DOMIC">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000020"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NOMB_CENT_UO">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000036"/>
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
         *         &lt;element name="IMP_INI">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="0000000015"/>
         *               &lt;fractionDigits value="02"/>
         *               &lt;maxInclusive value="9999999999999.99"/>
         *               &lt;minInclusive value="0.0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IMPAC_SDO_PNTAL">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="0000000019"/>
         *               &lt;fractionDigits value="02"/>
         *               &lt;maxInclusive value="99999999999999999.99"/>
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
         *         &lt;element name="NOMB_ENT_EN">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000036"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="SALDO_CONT_DISPONIBLE_V">
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
         *         &lt;element name="RESUL_APERTURA_V">
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
         *         &lt;element name="MS_PEND_CNT_V">
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
         *                   &lt;element name="STD_SMALL_INT_1">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="STD_SMALL_INT_2">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
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
         *         &lt;element name="AR_PEND_CNT_V">
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
         *                   &lt;element name="STD_SMALL_INT_1">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="STD_SMALL_INT_2">
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
         *         &lt;element name="PUESTO_PRINCIPAL">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IMP_TOTAL">
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
            "perfiltrantxv",
            "perfilentidadv",
            "perfilempleadov",
            "stdanalmsjv",
            "fechactble",
            "numdiasbakdi",
            "numdiasborrdi",
            "numtlfnodomic",
            "nombcentuo",
            "codcsbof",
            "impini",
            "impacsdopntal",
            "nomb50",
            "nombenten",
            "saldocontdisponiblev",
            "resulaperturav",
            "mspendcntv",
            "arpendcntv",
            "puestoprincipal",
            "imptotal"
        })
        public static class TRAPERTURAPUESTOSEVTZ {

            @XmlElement(name = "PERFIL_TRAN_TX_V", required = true)
            protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILTRANTXV perfiltrantxv;
            @XmlElement(name = "PERFIL_ENTIDAD_V", required = true)
            protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILENTIDADV perfilentidadv;
            @XmlElement(name = "PERFIL_EMPLEADO_V", required = true)
            protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILEMPLEADOV perfilempleadov;
            @XmlElement(name = "STD_AN_AL_MSJ_V", required = true)
            protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV stdanalmsjv;
            @XmlElement(name = "FECHA_CTBLE")
            protected int fechactble;
            @XmlElement(name = "NUM_DIAS_BAK_DI")
            protected int numdiasbakdi;
            @XmlElement(name = "NUM_DIAS_BORR_DI")
            protected int numdiasborrdi;
            @XmlElement(name = "NUM_TLFNO_DOMIC", required = true)
            protected String numtlfnodomic;
            @XmlElement(name = "NOMB_CENT_UO", required = true)
            protected String nombcentuo;
            @XmlElement(name = "COD_CSB_OF", required = true)
            protected String codcsbof;
            @XmlElement(name = "IMP_INI", required = true)
            protected BigDecimal impini;
            @XmlElement(name = "IMPAC_SDO_PNTAL", required = true)
            protected BigDecimal impacsdopntal;
            @XmlElement(name = "NOMB_50", required = true)
            protected String nomb50;
            @XmlElement(name = "NOMB_ENT_EN", required = true)
            protected String nombenten;
            @XmlElement(name = "SALDO_CONT_DISPONIBLE_V", required = true)
            protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.SALDOCONTDISPONIBLEV saldocontdisponiblev;
            @XmlElement(name = "RESUL_APERTURA_V", required = true)
            protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.RESULAPERTURAV resulaperturav;
            @XmlElement(name = "MS_PEND_CNT_V", required = true)
            protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.MSPENDCNTV mspendcntv;
            @XmlElement(name = "AR_PEND_CNT_V", required = true)
            protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.ARPENDCNTV arpendcntv;
            @XmlElement(name = "PUESTO_PRINCIPAL", required = true)
            protected String puestoprincipal;
            @XmlElement(name = "IMP_TOTAL", required = true)
            protected BigDecimal imptotal;

            /**
             * Obtiene el valor de la propiedad perfiltrantxv.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILTRANTXV }
             *     
             */
            public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILTRANTXV getPERFILTRANTXV() {
                return perfiltrantxv;
            }

            /**
             * Define el valor de la propiedad perfiltrantxv.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILTRANTXV }
             *     
             */
            public void setPERFILTRANTXV(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILTRANTXV value) {
                this.perfiltrantxv = value;
            }

            /**
             * Obtiene el valor de la propiedad perfilentidadv.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILENTIDADV }
             *     
             */
            public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILENTIDADV getPERFILENTIDADV() {
                return perfilentidadv;
            }

            /**
             * Define el valor de la propiedad perfilentidadv.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILENTIDADV }
             *     
             */
            public void setPERFILENTIDADV(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILENTIDADV value) {
                this.perfilentidadv = value;
            }

            /**
             * Obtiene el valor de la propiedad perfilempleadov.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILEMPLEADOV }
             *     
             */
            public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILEMPLEADOV getPERFILEMPLEADOV() {
                return perfilempleadov;
            }

            /**
             * Define el valor de la propiedad perfilempleadov.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILEMPLEADOV }
             *     
             */
            public void setPERFILEMPLEADOV(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILEMPLEADOV value) {
                this.perfilempleadov = value;
            }

            /**
             * Obtiene el valor de la propiedad stdanalmsjv.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV }
             *     
             */
            public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV getSTDANALMSJV() {
                return stdanalmsjv;
            }

            /**
             * Define el valor de la propiedad stdanalmsjv.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV }
             *     
             */
            public void setSTDANALMSJV(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV value) {
                this.stdanalmsjv = value;
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
             * Obtiene el valor de la propiedad numdiasbakdi.
             * 
             */
            public int getNUMDIASBAKDI() {
                return numdiasbakdi;
            }

            /**
             * Define el valor de la propiedad numdiasbakdi.
             * 
             */
            public void setNUMDIASBAKDI(int value) {
                this.numdiasbakdi = value;
            }

            /**
             * Obtiene el valor de la propiedad numdiasborrdi.
             * 
             */
            public int getNUMDIASBORRDI() {
                return numdiasborrdi;
            }

            /**
             * Define el valor de la propiedad numdiasborrdi.
             * 
             */
            public void setNUMDIASBORRDI(int value) {
                this.numdiasborrdi = value;
            }

            /**
             * Obtiene el valor de la propiedad numtlfnodomic.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNUMTLFNODOMIC() {
                return numtlfnodomic;
            }

            /**
             * Define el valor de la propiedad numtlfnodomic.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNUMTLFNODOMIC(String value) {
                this.numtlfnodomic = value;
            }

            /**
             * Obtiene el valor de la propiedad nombcentuo.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNOMBCENTUO() {
                return nombcentuo;
            }

            /**
             * Define el valor de la propiedad nombcentuo.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNOMBCENTUO(String value) {
                this.nombcentuo = value;
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
             * Obtiene el valor de la propiedad impini.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getIMPINI() {
                return impini;
            }

            /**
             * Define el valor de la propiedad impini.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setIMPINI(BigDecimal value) {
                this.impini = value;
            }

            /**
             * Obtiene el valor de la propiedad impacsdopntal.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getIMPACSDOPNTAL() {
                return impacsdopntal;
            }

            /**
             * Define el valor de la propiedad impacsdopntal.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setIMPACSDOPNTAL(BigDecimal value) {
                this.impacsdopntal = value;
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
             * Obtiene el valor de la propiedad nombenten.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNOMBENTEN() {
                return nombenten;
            }

            /**
             * Define el valor de la propiedad nombenten.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNOMBENTEN(String value) {
                this.nombenten = value;
            }

            /**
             * Obtiene el valor de la propiedad saldocontdisponiblev.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.SALDOCONTDISPONIBLEV }
             *     
             */
            public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.SALDOCONTDISPONIBLEV getSALDOCONTDISPONIBLEV() {
                return saldocontdisponiblev;
            }

            /**
             * Define el valor de la propiedad saldocontdisponiblev.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.SALDOCONTDISPONIBLEV }
             *     
             */
            public void setSALDOCONTDISPONIBLEV(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.SALDOCONTDISPONIBLEV value) {
                this.saldocontdisponiblev = value;
            }

            /**
             * Obtiene el valor de la propiedad resulaperturav.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.RESULAPERTURAV }
             *     
             */
            public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.RESULAPERTURAV getRESULAPERTURAV() {
                return resulaperturav;
            }

            /**
             * Define el valor de la propiedad resulaperturav.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.RESULAPERTURAV }
             *     
             */
            public void setRESULAPERTURAV(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.RESULAPERTURAV value) {
                this.resulaperturav = value;
            }

            /**
             * Obtiene el valor de la propiedad mspendcntv.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.MSPENDCNTV }
             *     
             */
            public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.MSPENDCNTV getMSPENDCNTV() {
                return mspendcntv;
            }

            /**
             * Define el valor de la propiedad mspendcntv.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.MSPENDCNTV }
             *     
             */
            public void setMSPENDCNTV(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.MSPENDCNTV value) {
                this.mspendcntv = value;
            }

            /**
             * Obtiene el valor de la propiedad arpendcntv.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.ARPENDCNTV }
             *     
             */
            public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.ARPENDCNTV getARPENDCNTV() {
                return arpendcntv;
            }

            /**
             * Define el valor de la propiedad arpendcntv.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.ARPENDCNTV }
             *     
             */
            public void setARPENDCNTV(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.ARPENDCNTV value) {
                this.arpendcntv = value;
            }

            /**
             * Obtiene el valor de la propiedad puestoprincipal.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPUESTOPRINCIPAL() {
                return puestoprincipal;
            }

            /**
             * Define el valor de la propiedad puestoprincipal.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPUESTOPRINCIPAL(String value) {
                this.puestoprincipal = value;
            }

            /**
             * Obtiene el valor de la propiedad imptotal.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getIMPTOTAL() {
                return imptotal;
            }

            /**
             * Define el valor de la propiedad imptotal.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setIMPTOTAL(BigDecimal value) {
                this.imptotal = value;
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
             *         &lt;element name="STD_SMALL_INT_1">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="STD_SMALL_INT_2">
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
                "numberofrecords",
                "stdsmallint1",
                "stdsmallint2"
            })
            public static class ARPENDCNTV {

                @XmlElement(name = "NUMBER_OF_RECORDS")
                protected int numberofrecords;
                @XmlElement(name = "STD_SMALL_INT_1")
                protected int stdsmallint1;
                @XmlElement(name = "STD_SMALL_INT_2")
                protected int stdsmallint2;

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
                 * Obtiene el valor de la propiedad stdsmallint1.
                 * 
                 */
                public int getSTDSMALLINT1() {
                    return stdsmallint1;
                }

                /**
                 * Define el valor de la propiedad stdsmallint1.
                 * 
                 */
                public void setSTDSMALLINT1(int value) {
                    this.stdsmallint1 = value;
                }

                /**
                 * Obtiene el valor de la propiedad stdsmallint2.
                 * 
                 */
                public int getSTDSMALLINT2() {
                    return stdsmallint2;
                }

                /**
                 * Define el valor de la propiedad stdsmallint2.
                 * 
                 */
                public void setSTDSMALLINT2(int value) {
                    this.stdsmallint2 = value;
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
             *         &lt;element name="STD_SMALL_INT_1">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="STD_SMALL_INT_2">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
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
                "numberofrecords",
                "stdsmallint1",
                "stdsmallint2",
                "stdsmallint"
            })
            public static class MSPENDCNTV {

                @XmlElement(name = "NUMBER_OF_RECORDS")
                protected int numberofrecords;
                @XmlElement(name = "STD_SMALL_INT_1")
                protected int stdsmallint1;
                @XmlElement(name = "STD_SMALL_INT_2")
                protected int stdsmallint2;
                @XmlElement(name = "STD_SMALL_INT")
                protected int stdsmallint;

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
                 * Obtiene el valor de la propiedad stdsmallint1.
                 * 
                 */
                public int getSTDSMALLINT1() {
                    return stdsmallint1;
                }

                /**
                 * Define el valor de la propiedad stdsmallint1.
                 * 
                 */
                public void setSTDSMALLINT1(int value) {
                    this.stdsmallint1 = value;
                }

                /**
                 * Obtiene el valor de la propiedad stdsmallint2.
                 * 
                 */
                public int getSTDSMALLINT2() {
                    return stdsmallint2;
                }

                /**
                 * Define el valor de la propiedad stdsmallint2.
                 * 
                 */
                public void setSTDSMALLINT2(int value) {
                    this.stdsmallint2 = value;
                }

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
             *         &lt;element name="EP_EMPL_PERFIL_TX_E">
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
             *                   &lt;element name="NOM_PERFIL_EN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000020"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FECHA_OPRCN_1">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="99999999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="HORA_OPRCN_1">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="99999999"/>
             *                         &lt;minInclusive value="0"/>
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
             *                   &lt;element name="FECHA_FIN_PERFIL">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="99999999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_PERFIL">
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
             *         &lt;element name="FECHA_PERFIL">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="99999999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="HORA_PERFIL">
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
                "epemplperfiltxe",
                "fechaperfil",
                "horaperfil"
            })
            public static class PERFILEMPLEADOV {

                @XmlElement(name = "EP_EMPL_PERFIL_TX_E", required = true)
                protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILEMPLEADOV.EPEMPLPERFILTXE epemplperfiltxe;
                @XmlElement(name = "FECHA_PERFIL")
                protected int fechaperfil;
                @XmlElement(name = "HORA_PERFIL")
                protected int horaperfil;

                /**
                 * Obtiene el valor de la propiedad epemplperfiltxe.
                 * 
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILEMPLEADOV.EPEMPLPERFILTXE }
                 *     
                 */
                public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILEMPLEADOV.EPEMPLPERFILTXE getEPEMPLPERFILTXE() {
                    return epemplperfiltxe;
                }

                /**
                 * Define el valor de la propiedad epemplperfiltxe.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILEMPLEADOV.EPEMPLPERFILTXE }
                 *     
                 */
                public void setEPEMPLPERFILTXE(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILEMPLEADOV.EPEMPLPERFILTXE value) {
                    this.epemplperfiltxe = value;
                }

                /**
                 * Obtiene el valor de la propiedad fechaperfil.
                 * 
                 */
                public int getFECHAPERFIL() {
                    return fechaperfil;
                }

                /**
                 * Define el valor de la propiedad fechaperfil.
                 * 
                 */
                public void setFECHAPERFIL(int value) {
                    this.fechaperfil = value;
                }

                /**
                 * Obtiene el valor de la propiedad horaperfil.
                 * 
                 */
                public int getHORAPERFIL() {
                    return horaperfil;
                }

                /**
                 * Define el valor de la propiedad horaperfil.
                 * 
                 */
                public void setHORAPERFIL(int value) {
                    this.horaperfil = value;
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
                 *         &lt;element name="NOM_PERFIL_EN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000020"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FECHA_OPRCN_1">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="99999999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="HORA_OPRCN_1">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="99999999"/>
                 *               &lt;minInclusive value="0"/>
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
                 *         &lt;element name="FECHA_FIN_PERFIL">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="99999999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_PERFIL">
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
                    "nomperfilen",
                    "fechaoprcn1",
                    "horaoprcn1",
                    "fechaoprcn",
                    "horaoprcn",
                    "fechafinperfil",
                    "codperfil"
                })
                public static class EPEMPLPERFILTXE {

                    @XmlElement(name = "COD_NRBE_EN", required = true)
                    protected String codnrbeen;
                    @XmlElement(name = "ID_INTERNO_EMPL_EP", required = true)
                    protected String idinternoemplep;
                    @XmlElement(name = "NOM_PERFIL_EN", required = true)
                    protected String nomperfilen;
                    @XmlElement(name = "FECHA_OPRCN_1")
                    protected int fechaoprcn1;
                    @XmlElement(name = "HORA_OPRCN_1")
                    protected int horaoprcn1;
                    @XmlElement(name = "FECHA_OPRCN")
                    protected int fechaoprcn;
                    @XmlElement(name = "HORA_OPRCN")
                    protected int horaoprcn;
                    @XmlElement(name = "FECHA_FIN_PERFIL")
                    protected int fechafinperfil;
                    @XmlElement(name = "COD_PERFIL", required = true)
                    protected String codperfil;

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
                     * Obtiene el valor de la propiedad nomperfilen.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getNOMPERFILEN() {
                        return nomperfilen;
                    }

                    /**
                     * Define el valor de la propiedad nomperfilen.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setNOMPERFILEN(String value) {
                        this.nomperfilen = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad fechaoprcn1.
                     * 
                     */
                    public int getFECHAOPRCN1() {
                        return fechaoprcn1;
                    }

                    /**
                     * Define el valor de la propiedad fechaoprcn1.
                     * 
                     */
                    public void setFECHAOPRCN1(int value) {
                        this.fechaoprcn1 = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad horaoprcn1.
                     * 
                     */
                    public int getHORAOPRCN1() {
                        return horaoprcn1;
                    }

                    /**
                     * Define el valor de la propiedad horaoprcn1.
                     * 
                     */
                    public void setHORAOPRCN1(int value) {
                        this.horaoprcn1 = value;
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

                    /**
                     * Obtiene el valor de la propiedad fechafinperfil.
                     * 
                     */
                    public int getFECHAFINPERFIL() {
                        return fechafinperfil;
                    }

                    /**
                     * Define el valor de la propiedad fechafinperfil.
                     * 
                     */
                    public void setFECHAFINPERFIL(int value) {
                        this.fechafinperfil = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codperfil.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCODPERFIL() {
                        return codperfil;
                    }

                    /**
                     * Define el valor de la propiedad codperfil.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCODPERFIL(String value) {
                        this.codperfil = value;
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
             *         &lt;element name="CR_ENT_PERFIL_TX_E">
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
             *                   &lt;element name="NOM_PERFIL_EN">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000020"/>
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
             *                   &lt;element name="FECHA_FIN_PERFIL">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="99999999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="FECHA_MOD_PERFIL">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *                         &lt;maxInclusive value="99999999"/>
             *                         &lt;minInclusive value="0"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_PERFIL">
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
                "crentperfiltxe"
            })
            public static class PERFILENTIDADV {

                @XmlElement(name = "CR_ENT_PERFIL_TX_E", required = true)
                protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILENTIDADV.CRENTPERFILTXE crentperfiltxe;

                /**
                 * Obtiene el valor de la propiedad crentperfiltxe.
                 * 
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILENTIDADV.CRENTPERFILTXE }
                 *     
                 */
                public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILENTIDADV.CRENTPERFILTXE getCRENTPERFILTXE() {
                    return crentperfiltxe;
                }

                /**
                 * Define el valor de la propiedad crentperfiltxe.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILENTIDADV.CRENTPERFILTXE }
                 *     
                 */
                public void setCRENTPERFILTXE(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILENTIDADV.CRENTPERFILTXE value) {
                    this.crentperfiltxe = value;
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
                 *         &lt;element name="NOM_PERFIL_EN">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000020"/>
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
                 *         &lt;element name="FECHA_FIN_PERFIL">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="99999999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="FECHA_MOD_PERFIL">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
                 *               &lt;maxInclusive value="99999999"/>
                 *               &lt;minInclusive value="0"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_PERFIL">
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
                    "nomperfilen",
                    "fechaoprcn",
                    "horaoprcn",
                    "fechafinperfil",
                    "fechamodperfil",
                    "codperfil"
                })
                public static class CRENTPERFILTXE {

                    @XmlElement(name = "COD_NRBE_EN", required = true)
                    protected String codnrbeen;
                    @XmlElement(name = "NOM_PERFIL_EN", required = true)
                    protected String nomperfilen;
                    @XmlElement(name = "FECHA_OPRCN")
                    protected int fechaoprcn;
                    @XmlElement(name = "HORA_OPRCN")
                    protected int horaoprcn;
                    @XmlElement(name = "FECHA_FIN_PERFIL")
                    protected int fechafinperfil;
                    @XmlElement(name = "FECHA_MOD_PERFIL")
                    protected int fechamodperfil;
                    @XmlElement(name = "COD_PERFIL", required = true)
                    protected String codperfil;

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
                     * Obtiene el valor de la propiedad nomperfilen.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getNOMPERFILEN() {
                        return nomperfilen;
                    }

                    /**
                     * Define el valor de la propiedad nomperfilen.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setNOMPERFILEN(String value) {
                        this.nomperfilen = value;
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

                    /**
                     * Obtiene el valor de la propiedad fechafinperfil.
                     * 
                     */
                    public int getFECHAFINPERFIL() {
                        return fechafinperfil;
                    }

                    /**
                     * Define el valor de la propiedad fechafinperfil.
                     * 
                     */
                    public void setFECHAFINPERFIL(int value) {
                        this.fechafinperfil = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad fechamodperfil.
                     * 
                     */
                    public int getFECHAMODPERFIL() {
                        return fechamodperfil;
                    }

                    /**
                     * Define el valor de la propiedad fechamodperfil.
                     * 
                     */
                    public void setFECHAMODPERFIL(int value) {
                        this.fechamodperfil = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codperfil.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCODPERFIL() {
                        return codperfil;
                    }

                    /**
                     * Define el valor de la propiedad codperfil.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCODPERFIL(String value) {
                        this.codperfil = value;
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
             *         &lt;element name="CR_PERFIL_TRAN_TX_V" maxOccurs="200">
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
             *                   &lt;element name="COD_TX">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000008"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_ACCION">
             *                     &lt;simpleType>
             *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *                         &lt;length value="0000000001"/>
             *                         &lt;whiteSpace value="preserve"/>
             *                       &lt;/restriction>
             *                     &lt;/simpleType>
             *                   &lt;/element>
             *                   &lt;element name="COD_APLCCN_SUBAPL">
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
                "crperfiltrantxv"
            })
            public static class PERFILTRANTXV {

                @XmlElement(name = "CR_PERFIL_TRAN_TX_V", required = true)
                protected List<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILTRANTXV.CRPERFILTRANTXV> crperfiltrantxv;

                /**
                 * Gets the value of the crperfiltrantxv property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the crperfiltrantxv property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getCRPERFILTRANTXV().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILTRANTXV.CRPERFILTRANTXV }
                 * 
                 * 
                 */
                public List<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILTRANTXV.CRPERFILTRANTXV> getCRPERFILTRANTXV() {
                    if (crperfiltrantxv == null) {
                        crperfiltrantxv = new ArrayList<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.PERFILTRANTXV.CRPERFILTRANTXV>();
                    }
                    return this.crperfiltrantxv;
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
                 *         &lt;element name="COD_TX">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000008"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_ACCION">
                 *           &lt;simpleType>
                 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
                 *               &lt;length value="0000000001"/>
                 *               &lt;whiteSpace value="preserve"/>
                 *             &lt;/restriction>
                 *           &lt;/simpleType>
                 *         &lt;/element>
                 *         &lt;element name="COD_APLCCN_SUBAPL">
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
                    "fechaoprcn",
                    "horaoprcn",
                    "codtx",
                    "codaccion",
                    "codaplccnsubapl"
                })
                public static class CRPERFILTRANTXV {

                    @XmlElement(name = "FECHA_OPRCN")
                    protected int fechaoprcn;
                    @XmlElement(name = "HORA_OPRCN")
                    protected int horaoprcn;
                    @XmlElement(name = "COD_TX", required = true)
                    protected String codtx;
                    @XmlElement(name = "COD_ACCION", required = true)
                    protected String codaccion;
                    @XmlElement(name = "COD_APLCCN_SUBAPL", required = true)
                    protected String codaplccnsubapl;

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
                     * Obtiene el valor de la propiedad codaccion.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCODACCION() {
                        return codaccion;
                    }

                    /**
                     * Define el valor de la propiedad codaccion.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCODACCION(String value) {
                        this.codaccion = value;
                    }

                    /**
                     * Obtiene el valor de la propiedad codaplccnsubapl.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCODAPLCCNSUBAPL() {
                        return codaplccnsubapl;
                    }

                    /**
                     * Define el valor de la propiedad codaplccnsubapl.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCODAPLCCNSUBAPL(String value) {
                        this.codaplccnsubapl = value;
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
            public static class RESULAPERTURAV {

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
            public static class SALDOCONTDISPONIBLEV {

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
                "stdanavmsjv"
            })
            public static class STDANALMSJV {

                @XmlElement(name = "STD_AN_AV_MSJ_V", required = true)
                protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV stdanavmsjv;

                /**
                 * Obtiene el valor de la propiedad stdanavmsjv.
                 * 
                 * @return
                 *     possible object is
                 *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV }
                 *     
                 */
                public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV getSTDANAVMSJV() {
                    return stdanavmsjv;
                }

                /**
                 * Define el valor de la propiedad stdanavmsjv.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV }
                 *     
                 */
                public void setSTDANAVMSJV(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV value) {
                    this.stdanavmsjv = value;
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
                    protected ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.ANNUMANOTACIONESV annumanotacionesv;
                    @XmlElement(name = "STD_AN_AV_MSJ_LS", required = true)
                    protected List<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS> stdanavmsjls;

                    /**
                     * Obtiene el valor de la propiedad annumanotacionesv.
                     * 
                     * @return
                     *     possible object is
                     *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.ANNUMANOTACIONESV }
                     *     
                     */
                    public ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.ANNUMANOTACIONESV getANNUMANOTACIONESV() {
                        return annumanotacionesv;
                    }

                    /**
                     * Define el valor de la propiedad annumanotacionesv.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.ANNUMANOTACIONESV }
                     *     
                     */
                    public void setANNUMANOTACIONESV(ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.ANNUMANOTACIONESV value) {
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
                     * {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS }
                     * 
                     * 
                     */
                    public List<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS> getSTDANAVMSJLS() {
                        if (stdanavmsjls == null) {
                            stdanavmsjls = new ArrayList<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS>();
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
                        protected List<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS.DESCRIPANTCNV> descripantcnv;
                        @XmlElement(name = "IND_PRDAD", required = true)
                        protected String indprdad;
                        @XmlElement(name = "DESC_IND_PRDAD_V", required = true)
                        protected List<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV> descindprdadv;
                        @XmlElement(name = "SUBCD_ANTCN", required = true)
                        protected String subcdantcn;
                        @XmlElement(name = "STD_DESCR_C_ANTCN_V", required = true)
                        protected List<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS.STDDESCRCANTCNV> stddescrcantcnv;

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
                         * {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS.DESCRIPANTCNV }
                         * 
                         * 
                         */
                        public List<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS.DESCRIPANTCNV> getDESCRIPANTCNV() {
                            if (descripantcnv == null) {
                                descripantcnv = new ArrayList<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS.DESCRIPANTCNV>();
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
                         * {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV }
                         * 
                         * 
                         */
                        public List<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV> getDESCINDPRDADV() {
                            if (descindprdadv == null) {
                                descindprdadv = new ArrayList<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS.DESCINDPRDADV>();
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
                         * {@link ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS.STDDESCRCANTCNV }
                         * 
                         * 
                         */
                        public List<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS.STDDESCRCANTCNV> getSTDDESCRCANTCNV() {
                            if (stddescrcantcnv == null) {
                                stddescrcantcnv = new ArrayList<ResponseBansefi.OTRAPERTURAPUESTOSTRN.TRAPERTURAPUESTOSEVTZ.STDANALMSJV.STDANAVMSJV.STDANAVMSJLS.STDDESCRCANTCNV>();
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

            }

        }

    }

}
