
package mx.babel.bansefi.banksystem.base.webservices.catalogogeo;

import java.math.BigInteger;

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
 *         &lt;element name="CP_NUM_AR_GEO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="CP_COD_POSTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CP_SUBCD_POSTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CP_NUM_AG_LOCINE" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="CP_COD_PROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LI_NOMB_LOCALIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LI_IND_PZA_BNCRIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LI_COD_MUNICIPIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LI_COD_ENT_COLEC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MU_NOMB_MUNICIPIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PR_NUM_AR_GEO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="PR_COD_COM_AUTNMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PR_NOMB_PROVINCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PR_PREF_TLFNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PR_MATRICULA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PR_COD_IMPTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CA_NUM_AR_GEO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="CA_NOMB_COM_AUTNMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "cpnumargeo",
    "cpcodpostal",
    "cpsubcdpostal",
    "cpnumaglocine",
    "cpcodprovincia",
    "linomblocalidad",
    "liindpzabncria",
    "licodmunicipio",
    "licodentcolec",
    "munombmunicipio",
    "prnumargeo",
    "prcodcomautnma",
    "prnombprovincia",
    "prpreftlfno",
    "prmatricula",
    "prcodimpto",
    "canumargeo",
    "canombcomautnma"
})
public class ResponseBansefi {

    @XmlElement(name = "CP_NUM_AR_GEO")
    protected BigInteger cpnumargeo;
    @XmlElement(name = "CP_COD_POSTAL")
    protected String cpcodpostal;
    @XmlElement(name = "CP_SUBCD_POSTAL")
    protected String cpsubcdpostal;
    @XmlElement(name = "CP_NUM_AG_LOCINE")
    protected BigInteger cpnumaglocine;
    @XmlElement(name = "CP_COD_PROVINCIA")
    protected String cpcodprovincia;
    @XmlElement(name = "LI_NOMB_LOCALIDAD")
    protected String linomblocalidad;
    @XmlElement(name = "LI_IND_PZA_BNCRIA")
    protected String liindpzabncria;
    @XmlElement(name = "LI_COD_MUNICIPIO")
    protected String licodmunicipio;
    @XmlElement(name = "LI_COD_ENT_COLEC")
    protected String licodentcolec;
    @XmlElement(name = "MU_NOMB_MUNICIPIO")
    protected String munombmunicipio;
    @XmlElement(name = "PR_NUM_AR_GEO")
    protected BigInteger prnumargeo;
    @XmlElement(name = "PR_COD_COM_AUTNMA")
    protected String prcodcomautnma;
    @XmlElement(name = "PR_NOMB_PROVINCIA")
    protected String prnombprovincia;
    @XmlElement(name = "PR_PREF_TLFNO")
    protected String prpreftlfno;
    @XmlElement(name = "PR_MATRICULA")
    protected String prmatricula;
    @XmlElement(name = "PR_COD_IMPTO")
    protected String prcodimpto;
    @XmlElement(name = "CA_NUM_AR_GEO")
    protected BigInteger canumargeo;
    @XmlElement(name = "CA_NOMB_COM_AUTNMA")
    protected String canombcomautnma;

    /**
     * Obtiene el valor de la propiedad cpnumargeo.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCPNUMARGEO() {
        return cpnumargeo;
    }

    /**
     * Define el valor de la propiedad cpnumargeo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCPNUMARGEO(BigInteger value) {
        this.cpnumargeo = value;
    }

    /**
     * Obtiene el valor de la propiedad cpcodpostal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPCODPOSTAL() {
        return cpcodpostal;
    }

    /**
     * Define el valor de la propiedad cpcodpostal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPCODPOSTAL(String value) {
        this.cpcodpostal = value;
    }

    /**
     * Obtiene el valor de la propiedad cpsubcdpostal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPSUBCDPOSTAL() {
        return cpsubcdpostal;
    }

    /**
     * Define el valor de la propiedad cpsubcdpostal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPSUBCDPOSTAL(String value) {
        this.cpsubcdpostal = value;
    }

    /**
     * Obtiene el valor de la propiedad cpnumaglocine.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCPNUMAGLOCINE() {
        return cpnumaglocine;
    }

    /**
     * Define el valor de la propiedad cpnumaglocine.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCPNUMAGLOCINE(BigInteger value) {
        this.cpnumaglocine = value;
    }

    /**
     * Obtiene el valor de la propiedad cpcodprovincia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPCODPROVINCIA() {
        return cpcodprovincia;
    }

    /**
     * Define el valor de la propiedad cpcodprovincia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPCODPROVINCIA(String value) {
        this.cpcodprovincia = value;
    }

    /**
     * Obtiene el valor de la propiedad linomblocalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLINOMBLOCALIDAD() {
        return linomblocalidad;
    }

    /**
     * Define el valor de la propiedad linomblocalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLINOMBLOCALIDAD(String value) {
        this.linomblocalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad liindpzabncria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLIINDPZABNCRIA() {
        return liindpzabncria;
    }

    /**
     * Define el valor de la propiedad liindpzabncria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLIINDPZABNCRIA(String value) {
        this.liindpzabncria = value;
    }

    /**
     * Obtiene el valor de la propiedad licodmunicipio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLICODMUNICIPIO() {
        return licodmunicipio;
    }

    /**
     * Define el valor de la propiedad licodmunicipio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLICODMUNICIPIO(String value) {
        this.licodmunicipio = value;
    }

    /**
     * Obtiene el valor de la propiedad licodentcolec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLICODENTCOLEC() {
        return licodentcolec;
    }

    /**
     * Define el valor de la propiedad licodentcolec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLICODENTCOLEC(String value) {
        this.licodentcolec = value;
    }

    /**
     * Obtiene el valor de la propiedad munombmunicipio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMUNOMBMUNICIPIO() {
        return munombmunicipio;
    }

    /**
     * Define el valor de la propiedad munombmunicipio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMUNOMBMUNICIPIO(String value) {
        this.munombmunicipio = value;
    }

    /**
     * Obtiene el valor de la propiedad prnumargeo.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPRNUMARGEO() {
        return prnumargeo;
    }

    /**
     * Define el valor de la propiedad prnumargeo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPRNUMARGEO(BigInteger value) {
        this.prnumargeo = value;
    }

    /**
     * Obtiene el valor de la propiedad prcodcomautnma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRCODCOMAUTNMA() {
        return prcodcomautnma;
    }

    /**
     * Define el valor de la propiedad prcodcomautnma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRCODCOMAUTNMA(String value) {
        this.prcodcomautnma = value;
    }

    /**
     * Obtiene el valor de la propiedad prnombprovincia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRNOMBPROVINCIA() {
        return prnombprovincia;
    }

    /**
     * Define el valor de la propiedad prnombprovincia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRNOMBPROVINCIA(String value) {
        this.prnombprovincia = value;
    }

    /**
     * Obtiene el valor de la propiedad prpreftlfno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRPREFTLFNO() {
        return prpreftlfno;
    }

    /**
     * Define el valor de la propiedad prpreftlfno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRPREFTLFNO(String value) {
        this.prpreftlfno = value;
    }

    /**
     * Obtiene el valor de la propiedad prmatricula.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRMATRICULA() {
        return prmatricula;
    }

    /**
     * Define el valor de la propiedad prmatricula.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRMATRICULA(String value) {
        this.prmatricula = value;
    }

    /**
     * Obtiene el valor de la propiedad prcodimpto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRCODIMPTO() {
        return prcodimpto;
    }

    /**
     * Define el valor de la propiedad prcodimpto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRCODIMPTO(String value) {
        this.prcodimpto = value;
    }

    /**
     * Obtiene el valor de la propiedad canumargeo.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCANUMARGEO() {
        return canumargeo;
    }

    /**
     * Define el valor de la propiedad canumargeo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCANUMARGEO(BigInteger value) {
        this.canumargeo = value;
    }

    /**
     * Obtiene el valor de la propiedad canombcomautnma.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCANOMBCOMAUTNMA() {
        return canombcomautnma;
    }

    /**
     * Define el valor de la propiedad canombcomautnma.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCANOMBCOMAUTNMA(String value) {
        this.canombcomautnma = value;
    }

}
