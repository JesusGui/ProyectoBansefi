
package mx.babel.bansefi.banksystem.cuentas.webservices.consultaconceptopk;

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
 *         &lt;element name="ITR_NOTIF_CONS_PK_TRN_I">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ELEVATOR_POSITION">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                         &lt;maxInclusive value="9999"/>
 *                         &lt;minInclusive value="0"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="SCROLLABLE_OCCURS">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                         &lt;maxInclusive value="9999"/>
 *                         &lt;minInclusive value="0"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="TR_NOTIF_CONS_PK_EVT_Y">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PK_PARMCD_P">
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
    "itrnotifconspktrni"
})
@XmlRootElement(name = "Ejecutar")
public class Ejecutar {

    @XmlElement(name = "USERHEADER", required = true)
    protected String userheader;
    @XmlElement(name = "PASSHEADER", required = true)
    protected String passheader;
    @XmlElement(name = "IPHEADER", required = true)
    protected String ipheader;
    @XmlElement(name = "ITR_NOTIF_CONS_PK_TRN_I", required = true)
    protected Ejecutar.ITRNOTIFCONSPKTRNI itrnotifconspktrni;

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
     * Obtiene el valor de la propiedad itrnotifconspktrni.
     * 
     * @return
     *     possible object is
     *     {@link Ejecutar.ITRNOTIFCONSPKTRNI }
     *     
     */
    public Ejecutar.ITRNOTIFCONSPKTRNI getITRNOTIFCONSPKTRNI() {
        return itrnotifconspktrni;
    }

    /**
     * Define el valor de la propiedad itrnotifconspktrni.
     * 
     * @param value
     *     allowed object is
     *     {@link Ejecutar.ITRNOTIFCONSPKTRNI }
     *     
     */
    public void setITRNOTIFCONSPKTRNI(Ejecutar.ITRNOTIFCONSPKTRNI value) {
        this.itrnotifconspktrni = value;
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
     *         &lt;element name="ELEVATOR_POSITION">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *               &lt;maxInclusive value="9999"/>
     *               &lt;minInclusive value="0"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="SCROLLABLE_OCCURS">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *               &lt;maxInclusive value="9999"/>
     *               &lt;minInclusive value="0"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="TR_NOTIF_CONS_PK_EVT_Y">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="PK_PARMCD_P">
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
        "elevatorposition",
        "scrollableoccurs",
        "trnotifconspkevty",
        "stdtrniparmv"
    })
    public static class ITRNOTIFCONSPKTRNI {

        @XmlElement(name = "ELEVATOR_POSITION")
        protected int elevatorposition;
        @XmlElement(name = "SCROLLABLE_OCCURS")
        protected int scrollableoccurs;
        @XmlElement(name = "TR_NOTIF_CONS_PK_EVT_Y", required = true)
        protected Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY trnotifconspkevty;
        @XmlElement(name = "STD_TRN_I_PARM_V", required = true)
        protected Ejecutar.ITRNOTIFCONSPKTRNI.STDTRNIPARMV stdtrniparmv;

        /**
         * Obtiene el valor de la propiedad elevatorposition.
         * 
         */
        public int getELEVATORPOSITION() {
            return elevatorposition;
        }

        /**
         * Define el valor de la propiedad elevatorposition.
         * 
         */
        public void setELEVATORPOSITION(int value) {
            this.elevatorposition = value;
        }

        /**
         * Obtiene el valor de la propiedad scrollableoccurs.
         * 
         */
        public int getSCROLLABLEOCCURS() {
            return scrollableoccurs;
        }

        /**
         * Define el valor de la propiedad scrollableoccurs.
         * 
         */
        public void setSCROLLABLEOCCURS(int value) {
            this.scrollableoccurs = value;
        }

        /**
         * Obtiene el valor de la propiedad trnotifconspkevty.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY }
         *     
         */
        public Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY getTRNOTIFCONSPKEVTY() {
            return trnotifconspkevty;
        }

        /**
         * Define el valor de la propiedad trnotifconspkevty.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY }
         *     
         */
        public void setTRNOTIFCONSPKEVTY(Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY value) {
            this.trnotifconspkevty = value;
        }

        /**
         * Obtiene el valor de la propiedad stdtrniparmv.
         * 
         * @return
         *     possible object is
         *     {@link Ejecutar.ITRNOTIFCONSPKTRNI.STDTRNIPARMV }
         *     
         */
        public Ejecutar.ITRNOTIFCONSPKTRNI.STDTRNIPARMV getSTDTRNIPARMV() {
            return stdtrniparmv;
        }

        /**
         * Define el valor de la propiedad stdtrniparmv.
         * 
         * @param value
         *     allowed object is
         *     {@link Ejecutar.ITRNOTIFCONSPKTRNI.STDTRNIPARMV }
         *     
         */
        public void setSTDTRNIPARMV(Ejecutar.ITRNOTIFCONSPKTRNI.STDTRNIPARMV value) {
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
         *         &lt;element name="PK_PARMCD_P">
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
            "pkparmcdp"
        })
        public static class TRNOTIFCONSPKEVTY {

            @XmlElement(name = "PK_PARMCD_P", required = true)
            protected Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY.PKPARMCDP pkparmcdp;

            /**
             * Obtiene el valor de la propiedad pkparmcdp.
             * 
             * @return
             *     possible object is
             *     {@link Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY.PKPARMCDP }
             *     
             */
            public Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY.PKPARMCDP getPKPARMCDP() {
                return pkparmcdp;
            }

            /**
             * Define el valor de la propiedad pkparmcdp.
             * 
             * @param value
             *     allowed object is
             *     {@link Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY.PKPARMCDP }
             *     
             */
            public void setPKPARMCDP(Ejecutar.ITRNOTIFCONSPKTRNI.TRNOTIFCONSPKEVTY.PKPARMCDP value) {
                this.pkparmcdp = value;
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
            public static class PKPARMCDP {

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
                public void setIDPARMCD(String value) {
                    this.idparmcd = value;
                }

            }

        }

    }

}
