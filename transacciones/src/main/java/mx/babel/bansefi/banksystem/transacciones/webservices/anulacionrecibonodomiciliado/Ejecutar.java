
package mx.babel.bansefi.banksystem.transacciones.webservices.anulacionrecibonodomiciliado;

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
 *         &lt;element name="ITR_ANULACION_APLCCN_RV_T">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
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
 *                   &lt;element name="TR_ANULACION_APLCCN_RV_EV">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="RV_IDENTIF_V">
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
 *                                       &lt;element name="COD_RCBO_VNTLLA">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NUM_SEC_RV">
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
 *                             &lt;element name="COD_CAPTU_RV">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_MOD_PAGO_HAC">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000002"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IND_FORMA_PAGOCOBR">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IMP_NOMINAL">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="0000000014"/>
 *                                   &lt;fractionDigits value="02"/>
 *                                   &lt;maxInclusive value="999999999999.99"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
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
 *                             &lt;element name="COD_NUMRCO_MONEDA">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000003"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IMP_RCGO">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                                   &lt;totalDigits value="0000000014"/>
 *                                   &lt;fractionDigits value="02"/>
 *                                   &lt;maxInclusive value="999999999999.99"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IMP_DEDUC">
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
    "itranulacionaplccnrvt"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USERHEADER", required = true)
    protected String userheader;
    @XmlElement(name = "PASSHEADER", required = true)
    protected String passheader;
    @XmlElement(name = "IPHEADER", required = true)
    protected String ipheader;
    @XmlElement(name = "ITR_ANULACION_APLCCN_RV_T", required = true)
    protected Ejecutar.ITRANULACIONAPLCCNRVT itranulacionaplccnrvt;

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
     * Obtiene el valor de la propiedad itranulacionaplccnrvt.
     * 
     * @return
     *     possible object is
     *     {@link Ejecutar.ITRANULACIONAPLCCNRVT }
     *     
     */
    public Ejecutar.ITRANULACIONAPLCCNRVT getITRANULACIONAPLCCNRVT() {
        return itranulacionaplccnrvt;
    }

    /**
     * Define el valor de la propiedad itranulacionaplccnrvt.
     * 
     * @param value
     *     allowed object is
     *     {@link Ejecutar.ITRANULACIONAPLCCNRVT }
     *     
     */
    public void setITRANULACIONAPLCCNRVT(Ejecutar.ITRANULACIONAPLCCNRVT value) {
        this.itranulacionaplccnrvt = value;
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
     *         &lt;element name="TR_ANULACION_APLCCN_RV_EV">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="RV_IDENTIF_V">
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
     *                             &lt;element name="COD_RCBO_VNTLLA">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="NUM_SEC_RV">
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
     *                   &lt;element name="COD_CAPTU_RV">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_MOD_PAGO_HAC">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000002"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IND_FORMA_PAGOCOBR">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IMP_NOMINAL">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="0000000014"/>
     *                         &lt;fractionDigits value="02"/>
     *                         &lt;maxInclusive value="999999999999.99"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
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
     *                   &lt;element name="COD_NUMRCO_MONEDA">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000003"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IMP_RCGO">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *                         &lt;totalDigits value="0000000014"/>
     *                         &lt;fractionDigits value="02"/>
     *                         &lt;maxInclusive value="999999999999.99"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IMP_DEDUC">
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
        "stdtrniparmv",
        "stdautorizav",
        "tranulacionaplccnrvev"
    })
    public static class ITRANULACIONAPLCCNRVT {

        @XmlElement(name = "STD_TRN_I_PARM_V", required = true)
        protected Ejecutar.ITRANULACIONAPLCCNRVT.STDTRNIPARMV stdtrniparmv;
        @XmlElement(name = "STD_AUTORIZA_V", required = true)
        protected Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV stdautorizav;
        @XmlElement(name = "TR_ANULACION_APLCCN_RV_EV", required = true)
        protected Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV tranulacionaplccnrvev;

        /**
         * Obtiene el valor de la propiedad stdtrniparmv.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.STDTRNIPARMV }
         *     
         */
        public Ejecutar.ITRANULACIONAPLCCNRVT.STDTRNIPARMV getSTDTRNIPARMV() {
            return stdtrniparmv;
        }

        /**
         * Define el valor de la propiedad stdtrniparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.STDTRNIPARMV }
         *     
         */
        public void setSTDTRNIPARMV(Ejecutar.ITRANULACIONAPLCCNRVT.STDTRNIPARMV value) {
            this.stdtrniparmv = value;
        }

        /**
         * Obtiene el valor de la propiedad stdautorizav.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV }
         *     
         */
        public Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV getSTDAUTORIZAV() {
            return stdautorizav;
        }

        /**
         * Define el valor de la propiedad stdautorizav.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV }
         *     
         */
        public void setSTDAUTORIZAV(Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV value) {
            this.stdautorizav = value;
        }

        /**
         * Obtiene el valor de la propiedad tranulacionaplccnrvev.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV }
         *     
         */
        public Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV getTRANULACIONAPLCCNRVEV() {
            return tranulacionaplccnrvev;
        }

        /**
         * Define el valor de la propiedad tranulacionaplccnrvev.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV }
         *     
         */
        public void setTRANULACIONAPLCCNRVEV(Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV value) {
            this.tranulacionaplccnrvev = value;
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
            protected Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARAUTREMOTAP arautremotap;
            @XmlElement(name = "AR_TRN_MSJ_PARM_V", required = true)
            protected List<Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARTRNMSJPARMV> artrnmsjparmv;
            @XmlElement(name = "STD_TARGET_TERMINAL_V", required = true)
            protected List<Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.STDTARGETTERMINALV> stdtargetterminalv;
            @XmlElement(name = "AR_ID_SALTADO_V", required = true)
            protected Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARIDSALTADOV aridsaltadov;

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
             *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARAUTREMOTAP }
             *     
             */
            public Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARAUTREMOTAP getARAUTREMOTAP() {
                return arautremotap;
            }

            /**
             * Define el valor de la propiedad arautremotap.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARAUTREMOTAP }
             *     
             */
            public void setARAUTREMOTAP(Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARAUTREMOTAP value) {
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
             * {@link Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARTRNMSJPARMV }
             * 
             * 
             */
            public List<Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARTRNMSJPARMV> getARTRNMSJPARMV() {
                if (artrnmsjparmv == null) {
                    artrnmsjparmv = new ArrayList<Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARTRNMSJPARMV>();
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
             * {@link Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.STDTARGETTERMINALV }
             * 
             * 
             */
            public List<Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.STDTARGETTERMINALV> getSTDTARGETTERMINALV() {
                if (stdtargetterminalv == null) {
                    stdtargetterminalv = new ArrayList<Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.STDTARGETTERMINALV>();
                }
                return this.stdtargetterminalv;
            }

            /**
             * Obtiene el valor de la propiedad aridsaltadov.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARIDSALTADOV }
             *     
             */
            public Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARIDSALTADOV getARIDSALTADOV() {
                return aridsaltadov;
            }

            /**
             * Define el valor de la propiedad aridsaltadov.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARIDSALTADOV }
             *     
             */
            public void setARIDSALTADOV(Ejecutar.ITRANULACIONAPLCCNRVT.STDAUTORIZAV.ARIDSALTADOV value) {
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
         *         &lt;element name="RV_IDENTIF_V">
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
         *                   &lt;element name="COD_RCBO_VNTLLA">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="NUM_SEC_RV">
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
         *         &lt;element name="COD_CAPTU_RV">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_MOD_PAGO_HAC">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000002"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IND_FORMA_PAGOCOBR">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IMP_NOMINAL">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="0000000014"/>
         *               &lt;fractionDigits value="02"/>
         *               &lt;maxInclusive value="999999999999.99"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
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
         *         &lt;element name="COD_NUMRCO_MONEDA">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000003"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IMP_RCGO">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
         *               &lt;totalDigits value="0000000014"/>
         *               &lt;fractionDigits value="02"/>
         *               &lt;maxInclusive value="999999999999.99"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IMP_DEDUC">
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
            "rvidentifv",
            "codcapturv",
            "codmodpagohac",
            "indformapagocobr",
            "impnominal",
            "psverrorv",
            "psvimpautv",
            "codnumrcomoneda",
            "imprcgo",
            "impdeduc"
        })
        public static class TRANULACIONAPLCCNRVEV {

            @XmlElement(name = "RV_IDENTIF_V", required = true)
            protected Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.RVIDENTIFV rvidentifv;
            @XmlElement(name = "COD_CAPTU_RV", required = true)
            protected String codcapturv;
            @XmlElement(name = "COD_MOD_PAGO_HAC", required = true)
            protected String codmodpagohac;
            @XmlElement(name = "IND_FORMA_PAGOCOBR", required = true)
            protected String indformapagocobr;
            @XmlElement(name = "IMP_NOMINAL", required = true)
            protected BigDecimal impnominal;
            @XmlElement(name = "PSV_ERROR_V", required = true)
            protected List<Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.PSVERRORV> psverrorv;
            @XmlElement(name = "PSV_IMP_AUT_V", required = true)
            protected Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.PSVIMPAUTV psvimpautv;
            @XmlElement(name = "COD_NUMRCO_MONEDA", required = true)
            protected String codnumrcomoneda;
            @XmlElement(name = "IMP_RCGO", required = true)
            protected BigDecimal imprcgo;
            @XmlElement(name = "IMP_DEDUC", required = true)
            protected BigDecimal impdeduc;

            /**
             * Obtiene el valor de la propiedad rvidentifv.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.RVIDENTIFV }
             *     
             */
            public Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.RVIDENTIFV getRVIDENTIFV() {
                return rvidentifv;
            }

            /**
             * Define el valor de la propiedad rvidentifv.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.RVIDENTIFV }
             *     
             */
            public void setRVIDENTIFV(Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.RVIDENTIFV value) {
                this.rvidentifv = value;
            }

            /**
             * Obtiene el valor de la propiedad codcapturv.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODCAPTURV() {
                return codcapturv;
            }

            /**
             * Define el valor de la propiedad codcapturv.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODCAPTURV(String value) {
                this.codcapturv = value;
            }

            /**
             * Obtiene el valor de la propiedad codmodpagohac.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODMODPAGOHAC() {
                return codmodpagohac;
            }

            /**
             * Define el valor de la propiedad codmodpagohac.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODMODPAGOHAC(String value) {
                this.codmodpagohac = value;
            }

            /**
             * Obtiene el valor de la propiedad indformapagocobr.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getINDFORMAPAGOCOBR() {
                return indformapagocobr;
            }

            /**
             * Define el valor de la propiedad indformapagocobr.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setINDFORMAPAGOCOBR(String value) {
                this.indformapagocobr = value;
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
            public void setIMPNOMINAL(BigDecimal value) {
                this.impnominal = value;
            }

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
             * {@link Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.PSVERRORV }
             * 
             * 
             */
            public List<Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.PSVERRORV> getPSVERRORV() {
                if (psverrorv == null) {
                    psverrorv = new ArrayList<Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.PSVERRORV>();
                }
                return this.psverrorv;
            }

            /**
             * Obtiene el valor de la propiedad psvimpautv.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.PSVIMPAUTV }
             *     
             */
            public Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.PSVIMPAUTV getPSVIMPAUTV() {
                return psvimpautv;
            }

            /**
             * Define el valor de la propiedad psvimpautv.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.PSVIMPAUTV }
             *     
             */
            public void setPSVIMPAUTV(Ejecutar.ITRANULACIONAPLCCNRVT.TRANULACIONAPLCCNRVEV.PSVIMPAUTV value) {
                this.psvimpautv = value;
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
             * Obtiene el valor de la propiedad imprcgo.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getIMPRCGO() {
                return imprcgo;
            }

            /**
             * Define el valor de la propiedad imprcgo.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setIMPRCGO(BigDecimal value) {
                this.imprcgo = value;
            }

            /**
             * Obtiene el valor de la propiedad impdeduc.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getIMPDEDUC() {
                return impdeduc;
            }

            /**
             * Define el valor de la propiedad impdeduc.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setIMPDEDUC(BigDecimal value) {
                this.impdeduc = value;
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
             *         &lt;element name="COD_RCBO_VNTLLA">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="NUM_SEC_RV">
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
                "codrcbovntlla",
                "numsecrv"
            })
            public static class RVIDENTIFV {

                @XmlElement(name = "COD_NRBE_EN", required = true)
                protected String codnrbeen;
                @XmlElement(name = "COD_RCBO_VNTLLA", required = true)
                protected String codrcbovntlla;
                @XmlElement(name = "NUM_SEC_RV")
                protected long numsecrv;

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
                 * Obtiene el valor de la propiedad codrcbovntlla.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODRCBOVNTLLA() {
                    return codrcbovntlla;
                }

                /**
                 * Define el valor de la propiedad codrcbovntlla.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODRCBOVNTLLA(String value) {
                    this.codrcbovntlla = value;
                }

                /**
                 * Obtiene el valor de la propiedad numsecrv.
                 * 
                 */
                public long getNUMSECRV() {
                    return numsecrv;
                }

                /**
                 * Define el valor de la propiedad numsecrv.
                 * 
                 */
                public void setNUMSECRV(long value) {
                    this.numsecrv = value;
                }

            }

        }

    }

}
