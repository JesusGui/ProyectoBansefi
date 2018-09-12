
package mx.babel.bansefi.banksystem.base.webservices.busquedacuenta;

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
 *         &lt;element name="ENTIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CENTRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACUERDO" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="CICLOVIDA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FECHA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="INDSOCIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RESDNC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CTER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODIGOACTIVO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DIGITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODINTERNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CSB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LINEA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GRP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PDV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TARIFA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODORIGEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MONEDA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RECLAM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IRREG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CNA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SECTOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FNDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GRTIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ESTICO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PERS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GTIACTBL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GTIACIRBE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PLAZABANCA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PAN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TITULAR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NIVELCUENTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "entidad",
    "centro",
    "acuerdo",
    "ciclovida",
    "fecha",
    "indsocio",
    "resdnc",
    "cter",
    "codigoactivo",
    "digito",
    "codinterno",
    "csb",
    "linea",
    "grp",
    "pdv",
    "tarifa",
    "codorigen",
    "moneda",
    "reclam",
    "irreg",
    "cno",
    "cna",
    "sector",
    "fndad",
    "grtia",
    "estico",
    "pers",
    "gtiactbl",
    "gtiacirbe",
    "plazabanca",
    "pan",
    "titular",
    "nivelcuenta"
})
public class ResponseBansefi {

    @XmlElement(name = "ENTIDAD")
    protected String entidad;
    @XmlElement(name = "CENTRO")
    protected String centro;
    @XmlElement(name = "ACUERDO")
    protected BigInteger acuerdo;
    @XmlElement(name = "CICLOVIDA")
    protected String ciclovida;
    @XmlElement(name = "FECHA")
    protected String fecha;
    @XmlElement(name = "INDSOCIO")
    protected String indsocio;
    @XmlElement(name = "RESDNC")
    protected String resdnc;
    @XmlElement(name = "CTER")
    protected String cter;
    @XmlElement(name = "CODIGOACTIVO")
    protected String codigoactivo;
    @XmlElement(name = "DIGITO")
    protected String digito;
    @XmlElement(name = "CODINTERNO")
    protected String codinterno;
    @XmlElement(name = "CSB")
    protected String csb;
    @XmlElement(name = "LINEA")
    protected String linea;
    @XmlElement(name = "GRP")
    protected String grp;
    @XmlElement(name = "PDV")
    protected String pdv;
    @XmlElement(name = "TARIFA")
    protected String tarifa;
    @XmlElement(name = "CODORIGEN")
    protected String codorigen;
    @XmlElement(name = "MONEDA")
    protected String moneda;
    @XmlElement(name = "RECLAM")
    protected String reclam;
    @XmlElement(name = "IRREG")
    protected String irreg;
    @XmlElement(name = "CNO")
    protected String cno;
    @XmlElement(name = "CNA")
    protected String cna;
    @XmlElement(name = "SECTOR")
    protected String sector;
    @XmlElement(name = "FNDAD")
    protected String fndad;
    @XmlElement(name = "GRTIA")
    protected String grtia;
    @XmlElement(name = "ESTICO")
    protected String estico;
    @XmlElement(name = "PERS")
    protected String pers;
    @XmlElement(name = "GTIACTBL")
    protected String gtiactbl;
    @XmlElement(name = "GTIACIRBE")
    protected String gtiacirbe;
    @XmlElement(name = "PLAZABANCA")
    protected String plazabanca;
    @XmlElement(name = "PAN")
    protected String pan;
    @XmlElement(name = "TITULAR")
    protected String titular;
    @XmlElement(name = "NIVELCUENTA")
    protected String nivelcuenta;

    /**
     * Obtiene el valor de la propiedad entidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENTIDAD() {
        return entidad;
    }

    /**
     * Define el valor de la propiedad entidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENTIDAD(String value) {
        this.entidad = value;
    }

    /**
     * Obtiene el valor de la propiedad centro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCENTRO() {
        return centro;
    }

    /**
     * Define el valor de la propiedad centro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCENTRO(String value) {
        this.centro = value;
    }

    /**
     * Obtiene el valor de la propiedad acuerdo.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getACUERDO() {
        return acuerdo;
    }

    /**
     * Define el valor de la propiedad acuerdo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setACUERDO(BigInteger value) {
        this.acuerdo = value;
    }

    /**
     * Obtiene el valor de la propiedad ciclovida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCICLOVIDA() {
        return ciclovida;
    }

    /**
     * Define el valor de la propiedad ciclovida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCICLOVIDA(String value) {
        this.ciclovida = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFECHA() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFECHA(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad indsocio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINDSOCIO() {
        return indsocio;
    }

    /**
     * Define el valor de la propiedad indsocio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINDSOCIO(String value) {
        this.indsocio = value;
    }

    /**
     * Obtiene el valor de la propiedad resdnc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRESDNC() {
        return resdnc;
    }

    /**
     * Define el valor de la propiedad resdnc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRESDNC(String value) {
        this.resdnc = value;
    }

    /**
     * Obtiene el valor de la propiedad cter.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCTER() {
        return cter;
    }

    /**
     * Define el valor de la propiedad cter.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCTER(String value) {
        this.cter = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoactivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODIGOACTIVO() {
        return codigoactivo;
    }

    /**
     * Define el valor de la propiedad codigoactivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODIGOACTIVO(String value) {
        this.codigoactivo = value;
    }

    /**
     * Obtiene el valor de la propiedad digito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIGITO() {
        return digito;
    }

    /**
     * Define el valor de la propiedad digito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIGITO(String value) {
        this.digito = value;
    }

    /**
     * Obtiene el valor de la propiedad codinterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODINTERNO() {
        return codinterno;
    }

    /**
     * Define el valor de la propiedad codinterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODINTERNO(String value) {
        this.codinterno = value;
    }

    /**
     * Obtiene el valor de la propiedad csb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCSB() {
        return csb;
    }

    /**
     * Define el valor de la propiedad csb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCSB(String value) {
        this.csb = value;
    }

    /**
     * Obtiene el valor de la propiedad linea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLINEA() {
        return linea;
    }

    /**
     * Define el valor de la propiedad linea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLINEA(String value) {
        this.linea = value;
    }

    /**
     * Obtiene el valor de la propiedad grp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRP() {
        return grp;
    }

    /**
     * Define el valor de la propiedad grp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRP(String value) {
        this.grp = value;
    }

    /**
     * Obtiene el valor de la propiedad pdv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDV() {
        return pdv;
    }

    /**
     * Define el valor de la propiedad pdv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDV(String value) {
        this.pdv = value;
    }

    /**
     * Obtiene el valor de la propiedad tarifa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTARIFA() {
        return tarifa;
    }

    /**
     * Define el valor de la propiedad tarifa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTARIFA(String value) {
        this.tarifa = value;
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
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMONEDA() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMONEDA(String value) {
        this.moneda = value;
    }

    /**
     * Obtiene el valor de la propiedad reclam.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRECLAM() {
        return reclam;
    }

    /**
     * Define el valor de la propiedad reclam.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRECLAM(String value) {
        this.reclam = value;
    }

    /**
     * Obtiene el valor de la propiedad irreg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIRREG() {
        return irreg;
    }

    /**
     * Define el valor de la propiedad irreg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIRREG(String value) {
        this.irreg = value;
    }

    /**
     * Obtiene el valor de la propiedad cno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCNO() {
        return cno;
    }

    /**
     * Define el valor de la propiedad cno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCNO(String value) {
        this.cno = value;
    }

    /**
     * Obtiene el valor de la propiedad cna.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCNA() {
        return cna;
    }

    /**
     * Define el valor de la propiedad cna.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCNA(String value) {
        this.cna = value;
    }

    /**
     * Obtiene el valor de la propiedad sector.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSECTOR() {
        return sector;
    }

    /**
     * Define el valor de la propiedad sector.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSECTOR(String value) {
        this.sector = value;
    }

    /**
     * Obtiene el valor de la propiedad fndad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFNDAD() {
        return fndad;
    }

    /**
     * Define el valor de la propiedad fndad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFNDAD(String value) {
        this.fndad = value;
    }

    /**
     * Obtiene el valor de la propiedad grtia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGRTIA() {
        return grtia;
    }

    /**
     * Define el valor de la propiedad grtia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGRTIA(String value) {
        this.grtia = value;
    }

    /**
     * Obtiene el valor de la propiedad estico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESTICO() {
        return estico;
    }

    /**
     * Define el valor de la propiedad estico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESTICO(String value) {
        this.estico = value;
    }

    /**
     * Obtiene el valor de la propiedad pers.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPERS() {
        return pers;
    }

    /**
     * Define el valor de la propiedad pers.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPERS(String value) {
        this.pers = value;
    }

    /**
     * Obtiene el valor de la propiedad gtiactbl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGTIACTBL() {
        return gtiactbl;
    }

    /**
     * Define el valor de la propiedad gtiactbl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGTIACTBL(String value) {
        this.gtiactbl = value;
    }

    /**
     * Obtiene el valor de la propiedad gtiacirbe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGTIACIRBE() {
        return gtiacirbe;
    }

    /**
     * Define el valor de la propiedad gtiacirbe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGTIACIRBE(String value) {
        this.gtiacirbe = value;
    }

    /**
     * Obtiene el valor de la propiedad plazabanca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPLAZABANCA() {
        return plazabanca;
    }

    /**
     * Define el valor de la propiedad plazabanca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPLAZABANCA(String value) {
        this.plazabanca = value;
    }

    /**
     * Obtiene el valor de la propiedad pan.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAN() {
        return pan;
    }

    /**
     * Define el valor de la propiedad pan.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAN(String value) {
        this.pan = value;
    }

    /**
     * Obtiene el valor de la propiedad titular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTITULAR() {
        return titular;
    }

    /**
     * Define el valor de la propiedad titular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTITULAR(String value) {
        this.titular = value;
    }

    /**
     * Obtiene el valor de la propiedad nivelcuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNIVELCUENTA() {
        return nivelcuenta;
    }

    /**
     * Define el valor de la propiedad nivelcuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNIVELCUENTA(String value) {
        this.nivelcuenta = value;
    }

}
