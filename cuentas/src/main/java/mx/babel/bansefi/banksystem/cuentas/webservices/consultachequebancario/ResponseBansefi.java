
package mx.babel.bansefi.banksystem.cuentas.webservices.consultachequebancario;

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
 *         &lt;element name="OTR_CONSULTA_CHEQUE_2_TRN">
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
 *                   &lt;element name="TR_CONSULTA_CHEQUE_2_EVT">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="RM_REM_P">
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
 *                                       &lt;element name="COD_DOC_FNCRO">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;length value="0000000002"/>
 *                                             &lt;whiteSpace value="preserve"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="AA_AWO">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;maxInclusive value="9999"/>
 *                                             &lt;minInclusive value="0"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="NUM_SEC_RM">
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
 *                             &lt;element name="COD_CJ_INTERNO">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000003"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="CCC_DOC">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000020"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NUM_DOC">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000015"/>
 *                                   &lt;whiteSpace value="preserve"/>
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
 *                             &lt;element name="NUM_CERTIF">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="INDS_CLAUSULA">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000008"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="IND_SEG_PRES">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="FECHA_PRES">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="99999999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_SIT_RECLAM_CJ">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_ENT_SAL">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000002"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="FECHA_VALOR_ABONO">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="99999999"/>
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
 *                             &lt;element name="UO_TOMADOR">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000004"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="FECHA_ALTA">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="99999999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="EF_OFCNA_LIBRADA">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000008"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="EF_OFCNA_TOMADORA">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000008"/>
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
 *                             &lt;element name="CODIGO_SEGURIDAD">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;maxInclusive value="999"/>
 *                                   &lt;minInclusive value="0"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_DIG_INTCBIO">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_DIG_PREMAR_CHQ">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000001"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="COD_CJ_CHQ_PG">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000002"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NUM_SEC_CJ">
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
    "otrconsultacheque2TRN"
})
public class ResponseBansefi {

    @XmlElement(name = "OTR_CONSULTA_CHEQUE_2_TRN", required = true)
    protected ResponseBansefi.OTRCONSULTACHEQUE2TRN otrconsultacheque2TRN;

    /**
     * Obtiene el valor de la propiedad otrconsultacheque2TRN.
     * 
     * @return
     *     possible object is
     *     {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN }
     *     
     */
    public ResponseBansefi.OTRCONSULTACHEQUE2TRN getOTRCONSULTACHEQUE2TRN() {
        return otrconsultacheque2TRN;
    }

    /**
     * Define el valor de la propiedad otrconsultacheque2TRN.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN }
     *     
     */
    public void setOTRCONSULTACHEQUE2TRN(ResponseBansefi.OTRCONSULTACHEQUE2TRN value) {
        this.otrconsultacheque2TRN = value;
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
     *         &lt;element name="TR_CONSULTA_CHEQUE_2_EVT">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="RM_REM_P">
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
     *                             &lt;element name="COD_DOC_FNCRO">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                                   &lt;length value="0000000002"/>
     *                                   &lt;whiteSpace value="preserve"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="AA_AWO">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;maxInclusive value="9999"/>
     *                                   &lt;minInclusive value="0"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                             &lt;element name="NUM_SEC_RM">
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
     *                   &lt;element name="COD_CJ_INTERNO">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000003"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="CCC_DOC">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000020"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NUM_DOC">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000015"/>
     *                         &lt;whiteSpace value="preserve"/>
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
     *                   &lt;element name="NUM_CERTIF">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="INDS_CLAUSULA">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000008"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="IND_SEG_PRES">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="FECHA_PRES">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="99999999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_SIT_RECLAM_CJ">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_ENT_SAL">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000002"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="FECHA_VALOR_ABONO">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="99999999"/>
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
     *                   &lt;element name="UO_TOMADOR">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000004"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="FECHA_ALTA">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="99999999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="EF_OFCNA_LIBRADA">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000008"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="EF_OFCNA_TOMADORA">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000008"/>
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
     *                   &lt;element name="CODIGO_SEGURIDAD">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;maxInclusive value="999"/>
     *                         &lt;minInclusive value="0"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_DIG_INTCBIO">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_DIG_PREMAR_CHQ">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000001"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="COD_CJ_CHQ_PG">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000002"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NUM_SEC_CJ">
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
        "stdtrnmsjparmv",
        "stdtrnoparmv",
        "trconsultacheque2EVT"
    })
    public static class OTRCONSULTACHEQUE2TRN {

        @XmlElement(name = "RTRN_CD")
        protected int rtrncd;
        @XmlElement(name = "STD_TRN_MSJ_PARM_V", required = true)
        protected List<ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNMSJPARMV> stdtrnmsjparmv;
        @XmlElement(name = "STD_TRN_O_PARM_V", required = true)
        protected ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNOPARMV stdtrnoparmv;
        @XmlElement(name = "TR_CONSULTA_CHEQUE_2_EVT", required = true)
        protected ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT trconsultacheque2EVT;

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
         * {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNMSJPARMV }
         * 
         * 
         */
        public List<ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNMSJPARMV> getSTDTRNMSJPARMV() {
            if (stdtrnmsjparmv == null) {
                stdtrnmsjparmv = new ArrayList<ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNMSJPARMV>();
            }
            return this.stdtrnmsjparmv;
        }

        /**
         * Obtiene el valor de la propiedad stdtrnoparmv.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNOPARMV }
         *     
         */
        public ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNOPARMV getSTDTRNOPARMV() {
            return stdtrnoparmv;
        }

        /**
         * Define el valor de la propiedad stdtrnoparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNOPARMV }
         *     
         */
        public void setSTDTRNOPARMV(ResponseBansefi.OTRCONSULTACHEQUE2TRN.STDTRNOPARMV value) {
            this.stdtrnoparmv = value;
        }

        /**
         * Obtiene el valor de la propiedad trconsultacheque2EVT.
         * 
         * @return
         *     possible object is
         *     {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT }
         *     
         */
        public ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT getTRCONSULTACHEQUE2EVT() {
            return trconsultacheque2EVT;
        }

        /**
         * Define el valor de la propiedad trconsultacheque2EVT.
         * 
         * @param value
         *     allowed object is
         *     {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT }
         *     
         */
        public void setTRCONSULTACHEQUE2EVT(ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT value) {
            this.trconsultacheque2EVT = value;
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
         *         &lt;element name="RM_REM_P">
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
         *                   &lt;element name="COD_DOC_FNCRO">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *                         &lt;length value="0000000002"/>
         *                         &lt;whiteSpace value="preserve"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="AA_AWO">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;maxInclusive value="9999"/>
         *                         &lt;minInclusive value="0"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                   &lt;element name="NUM_SEC_RM">
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
         *         &lt;element name="COD_CJ_INTERNO">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000003"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="CCC_DOC">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000020"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NUM_DOC">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000015"/>
         *               &lt;whiteSpace value="preserve"/>
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
         *         &lt;element name="NUM_CERTIF">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="INDS_CLAUSULA">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000008"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="IND_SEG_PRES">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="FECHA_PRES">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="99999999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_SIT_RECLAM_CJ">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_ENT_SAL">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000002"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="FECHA_VALOR_ABONO">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="99999999"/>
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
         *         &lt;element name="UO_TOMADOR">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000004"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="FECHA_ALTA">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="99999999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="EF_OFCNA_LIBRADA">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000008"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="EF_OFCNA_TOMADORA">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000008"/>
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
         *         &lt;element name="CODIGO_SEGURIDAD">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;maxInclusive value="999"/>
         *               &lt;minInclusive value="0"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_DIG_INTCBIO">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_DIG_PREMAR_CHQ">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000001"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="COD_CJ_CHQ_PG">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000002"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NUM_SEC_CJ">
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
            "rmremp",
            "codcjinterno",
            "cccdoc",
            "numdoc",
            "impnominal",
            "numcertif",
            "indsclausula",
            "indsegpres",
            "fechapres",
            "codsitreclamcj",
            "codentsal",
            "fechavalorabono",
            "codinternouo",
            "uotomador",
            "fechaalta",
            "efofcnalibrada",
            "efofcnatomadora",
            "codnumrcomoneda",
            "codigoseguridad",
            "coddigintcbio",
            "coddigpremarchq",
            "codcjchqpg",
            "numseccj"
        })
        public static class TRCONSULTACHEQUE2EVT {

            @XmlElement(name = "RM_REM_P", required = true)
            protected ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT.RMREMP rmremp;
            @XmlElement(name = "COD_CJ_INTERNO", required = true)
            protected String codcjinterno;
            @XmlElement(name = "CCC_DOC", required = true)
            protected String cccdoc;
            @XmlElement(name = "NUM_DOC", required = true)
            protected String numdoc;
            @XmlElement(name = "IMP_NOMINAL", required = true)
            protected BigDecimal impnominal;
            @XmlElement(name = "NUM_CERTIF")
            protected int numcertif;
            @XmlElement(name = "INDS_CLAUSULA", required = true)
            protected String indsclausula;
            @XmlElement(name = "IND_SEG_PRES", required = true)
            protected String indsegpres;
            @XmlElement(name = "FECHA_PRES")
            protected int fechapres;
            @XmlElement(name = "COD_SIT_RECLAM_CJ", required = true)
            protected String codsitreclamcj;
            @XmlElement(name = "COD_ENT_SAL", required = true)
            protected String codentsal;
            @XmlElement(name = "FECHA_VALOR_ABONO")
            protected int fechavalorabono;
            @XmlElement(name = "COD_INTERNO_UO", required = true)
            protected String codinternouo;
            @XmlElement(name = "UO_TOMADOR", required = true)
            protected String uotomador;
            @XmlElement(name = "FECHA_ALTA")
            protected int fechaalta;
            @XmlElement(name = "EF_OFCNA_LIBRADA", required = true)
            protected String efofcnalibrada;
            @XmlElement(name = "EF_OFCNA_TOMADORA", required = true)
            protected String efofcnatomadora;
            @XmlElement(name = "COD_NUMRCO_MONEDA", required = true)
            protected String codnumrcomoneda;
            @XmlElement(name = "CODIGO_SEGURIDAD")
            protected int codigoseguridad;
            @XmlElement(name = "COD_DIG_INTCBIO", required = true)
            protected String coddigintcbio;
            @XmlElement(name = "COD_DIG_PREMAR_CHQ", required = true)
            protected String coddigpremarchq;
            @XmlElement(name = "COD_CJ_CHQ_PG", required = true)
            protected String codcjchqpg;
            @XmlElement(name = "NUM_SEC_CJ")
            protected long numseccj;

            /**
             * Obtiene el valor de la propiedad rmremp.
             * 
             * @return
             *     possible object is
             *     {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT.RMREMP }
             *     
             */
            public ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT.RMREMP getRMREMP() {
                return rmremp;
            }

            /**
             * Define el valor de la propiedad rmremp.
             * 
             * @param value
             *     allowed object is
             *     {@link ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT.RMREMP }
             *     
             */
            public void setRMREMP(ResponseBansefi.OTRCONSULTACHEQUE2TRN.TRCONSULTACHEQUE2EVT.RMREMP value) {
                this.rmremp = value;
            }

            /**
             * Obtiene el valor de la propiedad codcjinterno.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODCJINTERNO() {
                return codcjinterno;
            }

            /**
             * Define el valor de la propiedad codcjinterno.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODCJINTERNO(String value) {
                this.codcjinterno = value;
            }

            /**
             * Obtiene el valor de la propiedad cccdoc.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCCCDOC() {
                return cccdoc;
            }

            /**
             * Define el valor de la propiedad cccdoc.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCCCDOC(String value) {
                this.cccdoc = value;
            }

            /**
             * Obtiene el valor de la propiedad numdoc.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNUMDOC() {
                return numdoc;
            }

            /**
             * Define el valor de la propiedad numdoc.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNUMDOC(String value) {
                this.numdoc = value;
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
             * Obtiene el valor de la propiedad numcertif.
             * 
             */
            public int getNUMCERTIF() {
                return numcertif;
            }

            /**
             * Define el valor de la propiedad numcertif.
             * 
             */
            public void setNUMCERTIF(int value) {
                this.numcertif = value;
            }

            /**
             * Obtiene el valor de la propiedad indsclausula.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getINDSCLAUSULA() {
                return indsclausula;
            }

            /**
             * Define el valor de la propiedad indsclausula.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setINDSCLAUSULA(String value) {
                this.indsclausula = value;
            }

            /**
             * Obtiene el valor de la propiedad indsegpres.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getINDSEGPRES() {
                return indsegpres;
            }

            /**
             * Define el valor de la propiedad indsegpres.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setINDSEGPRES(String value) {
                this.indsegpres = value;
            }

            /**
             * Obtiene el valor de la propiedad fechapres.
             * 
             */
            public int getFECHAPRES() {
                return fechapres;
            }

            /**
             * Define el valor de la propiedad fechapres.
             * 
             */
            public void setFECHAPRES(int value) {
                this.fechapres = value;
            }

            /**
             * Obtiene el valor de la propiedad codsitreclamcj.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODSITRECLAMCJ() {
                return codsitreclamcj;
            }

            /**
             * Define el valor de la propiedad codsitreclamcj.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODSITRECLAMCJ(String value) {
                this.codsitreclamcj = value;
            }

            /**
             * Obtiene el valor de la propiedad codentsal.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODENTSAL() {
                return codentsal;
            }

            /**
             * Define el valor de la propiedad codentsal.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODENTSAL(String value) {
                this.codentsal = value;
            }

            /**
             * Obtiene el valor de la propiedad fechavalorabono.
             * 
             */
            public int getFECHAVALORABONO() {
                return fechavalorabono;
            }

            /**
             * Define el valor de la propiedad fechavalorabono.
             * 
             */
            public void setFECHAVALORABONO(int value) {
                this.fechavalorabono = value;
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
             * Obtiene el valor de la propiedad uotomador.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUOTOMADOR() {
                return uotomador;
            }

            /**
             * Define el valor de la propiedad uotomador.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUOTOMADOR(String value) {
                this.uotomador = value;
            }

            /**
             * Obtiene el valor de la propiedad fechaalta.
             * 
             */
            public int getFECHAALTA() {
                return fechaalta;
            }

            /**
             * Define el valor de la propiedad fechaalta.
             * 
             */
            public void setFECHAALTA(int value) {
                this.fechaalta = value;
            }

            /**
             * Obtiene el valor de la propiedad efofcnalibrada.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEFOFCNALIBRADA() {
                return efofcnalibrada;
            }

            /**
             * Define el valor de la propiedad efofcnalibrada.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEFOFCNALIBRADA(String value) {
                this.efofcnalibrada = value;
            }

            /**
             * Obtiene el valor de la propiedad efofcnatomadora.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEFOFCNATOMADORA() {
                return efofcnatomadora;
            }

            /**
             * Define el valor de la propiedad efofcnatomadora.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEFOFCNATOMADORA(String value) {
                this.efofcnatomadora = value;
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
             * Obtiene el valor de la propiedad codigoseguridad.
             * 
             */
            public int getCODIGOSEGURIDAD() {
                return codigoseguridad;
            }

            /**
             * Define el valor de la propiedad codigoseguridad.
             * 
             */
            public void setCODIGOSEGURIDAD(int value) {
                this.codigoseguridad = value;
            }

            /**
             * Obtiene el valor de la propiedad coddigintcbio.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODDIGINTCBIO() {
                return coddigintcbio;
            }

            /**
             * Define el valor de la propiedad coddigintcbio.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODDIGINTCBIO(String value) {
                this.coddigintcbio = value;
            }

            /**
             * Obtiene el valor de la propiedad coddigpremarchq.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODDIGPREMARCHQ() {
                return coddigpremarchq;
            }

            /**
             * Define el valor de la propiedad coddigpremarchq.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODDIGPREMARCHQ(String value) {
                this.coddigpremarchq = value;
            }

            /**
             * Obtiene el valor de la propiedad codcjchqpg.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCODCJCHQPG() {
                return codcjchqpg;
            }

            /**
             * Define el valor de la propiedad codcjchqpg.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCODCJCHQPG(String value) {
                this.codcjchqpg = value;
            }

            /**
             * Obtiene el valor de la propiedad numseccj.
             * 
             */
            public long getNUMSECCJ() {
                return numseccj;
            }

            /**
             * Define el valor de la propiedad numseccj.
             * 
             */
            public void setNUMSECCJ(long value) {
                this.numseccj = value;
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
             *         &lt;element name="COD_DOC_FNCRO">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
             *               &lt;length value="0000000002"/>
             *               &lt;whiteSpace value="preserve"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="AA_AWO">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;maxInclusive value="9999"/>
             *               &lt;minInclusive value="0"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
             *         &lt;/element>
             *         &lt;element name="NUM_SEC_RM">
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
                "codnrbeen",
                "coddocfncro",
                "aaawo",
                "numsecrm"
            })
            public static class RMREMP {

                @XmlElement(name = "COD_NRBE_EN", required = true)
                protected String codnrbeen;
                @XmlElement(name = "COD_DOC_FNCRO", required = true)
                protected String coddocfncro;
                @XmlElement(name = "AA_AWO")
                protected int aaawo;
                @XmlElement(name = "NUM_SEC_RM")
                protected int numsecrm;

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
                 * Obtiene el valor de la propiedad coddocfncro.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCODDOCFNCRO() {
                    return coddocfncro;
                }

                /**
                 * Define el valor de la propiedad coddocfncro.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCODDOCFNCRO(String value) {
                    this.coddocfncro = value;
                }

                /**
                 * Obtiene el valor de la propiedad aaawo.
                 * 
                 */
                public int getAAAWO() {
                    return aaawo;
                }

                /**
                 * Define el valor de la propiedad aaawo.
                 * 
                 */
                public void setAAAWO(int value) {
                    this.aaawo = value;
                }

                /**
                 * Obtiene el valor de la propiedad numsecrm.
                 * 
                 */
                public int getNUMSECRM() {
                    return numsecrm;
                }

                /**
                 * Define el valor de la propiedad numsecrm.
                 * 
                 */
                public void setNUMSECRM(int value) {
                    this.numsecrm = value;
                }

            }

        }

    }

}
