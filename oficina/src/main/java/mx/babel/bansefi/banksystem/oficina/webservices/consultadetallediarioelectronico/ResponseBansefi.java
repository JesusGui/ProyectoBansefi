
package mx.babel.bansefi.banksystem.oficina.webservices.consultadetallediarioelectronico;

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
 *         &lt;element name="OCONDIA02_O">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CAMPOENTRADA" maxOccurs="100">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CAMPO_ENTRADA">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000020"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="VALOR_ENTRADA">
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
 *                   &lt;element name="CAMPOSSALIDA" maxOccurs="100">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CAMPO_SALIDA">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;length value="0000000020"/>
 *                                   &lt;whiteSpace value="preserve"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="VALOR_SALIDA">
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
@XmlType(name = "ResponseBansefi", propOrder = {
    "ocondia02O"
})
public class ResponseBansefi {

    @XmlElement(name = "OCONDIA02_O", required = true)
    protected ResponseBansefi.OCONDIA02O ocondia02O;

    /**
     * Obtiene el valor de la propiedad ocondia02O.
     * 
     * @return
     *     possible object is
     *     {@link ResponseBansefi.OCONDIA02O }
     *     
     */
    public ResponseBansefi.OCONDIA02O getOCONDIA02O() {
        return ocondia02O;
    }

    /**
     * Define el valor de la propiedad ocondia02O.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseBansefi.OCONDIA02O }
     *     
     */
    public void setOCONDIA02O(ResponseBansefi.OCONDIA02O value) {
        this.ocondia02O = value;
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
     *         &lt;element name="CAMPOENTRADA" maxOccurs="100">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CAMPO_ENTRADA">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000020"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="VALOR_ENTRADA">
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
     *         &lt;element name="CAMPOSSALIDA" maxOccurs="100">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CAMPO_SALIDA">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;length value="0000000020"/>
     *                         &lt;whiteSpace value="preserve"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="VALOR_SALIDA">
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
        "campoentrada",
        "campossalida"
    })
    public static class OCONDIA02O {

        @XmlElement(name = "CAMPOENTRADA", required = true)
        protected List<ResponseBansefi.OCONDIA02O.CAMPOENTRADA> campoentrada;
        @XmlElement(name = "CAMPOSSALIDA", required = true)
        protected List<ResponseBansefi.OCONDIA02O.CAMPOSSALIDA> campossalida;

        /**
         * Gets the value of the campoentrada property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the campoentrada property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCAMPOENTRADA().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResponseBansefi.OCONDIA02O.CAMPOENTRADA }
         * 
         * 
         */
        public List<ResponseBansefi.OCONDIA02O.CAMPOENTRADA> getCAMPOENTRADA() {
            if (campoentrada == null) {
                campoentrada = new ArrayList<ResponseBansefi.OCONDIA02O.CAMPOENTRADA>();
            }
            return this.campoentrada;
        }

        /**
         * Gets the value of the campossalida property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the campossalida property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCAMPOSSALIDA().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResponseBansefi.OCONDIA02O.CAMPOSSALIDA }
         * 
         * 
         */
        public List<ResponseBansefi.OCONDIA02O.CAMPOSSALIDA> getCAMPOSSALIDA() {
            if (campossalida == null) {
                campossalida = new ArrayList<ResponseBansefi.OCONDIA02O.CAMPOSSALIDA>();
            }
            return this.campossalida;
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
         *         &lt;element name="CAMPO_ENTRADA">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000020"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="VALOR_ENTRADA">
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
            "campoentrada",
            "valorentrada"
        })
        public static class CAMPOENTRADA {

            @XmlElement(name = "CAMPO_ENTRADA", required = true)
            protected String campoentrada;
            @XmlElement(name = "VALOR_ENTRADA", required = true)
            protected String valorentrada;

            /**
             * Obtiene el valor de la propiedad campoentrada.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCAMPOENTRADA() {
                return campoentrada;
            }

            /**
             * Define el valor de la propiedad campoentrada.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCAMPOENTRADA(String value) {
                this.campoentrada = value;
            }

            /**
             * Obtiene el valor de la propiedad valorentrada.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVALORENTRADA() {
                return valorentrada;
            }

            /**
             * Define el valor de la propiedad valorentrada.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVALORENTRADA(String value) {
                this.valorentrada = value;
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
         *         &lt;element name="CAMPO_SALIDA">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;length value="0000000020"/>
         *               &lt;whiteSpace value="preserve"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="VALOR_SALIDA">
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
            "camposalida",
            "valorsalida"
        })
        public static class CAMPOSSALIDA {

            @XmlElement(name = "CAMPO_SALIDA", required = true)
            protected String camposalida;
            @XmlElement(name = "VALOR_SALIDA", required = true)
            protected String valorsalida;

            /**
             * Obtiene el valor de la propiedad camposalida.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCAMPOSALIDA() {
                return camposalida;
            }

            /**
             * Define el valor de la propiedad camposalida.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCAMPOSALIDA(String value) {
                this.camposalida = value;
            }

            /**
             * Obtiene el valor de la propiedad valorsalida.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVALORSALIDA() {
                return valorsalida;
            }

            /**
             * Define el valor de la propiedad valorsalida.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVALORSALIDA(String value) {
                this.valorsalida = value;
            }

        }

    }

}
