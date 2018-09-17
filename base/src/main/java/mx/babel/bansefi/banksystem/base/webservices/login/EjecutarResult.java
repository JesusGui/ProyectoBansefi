
package mx.babel.bansefi.banksystem.base.webservices.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Clase Java para EjecutarResult complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EjecutarResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TRANID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ESTATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CODIGO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MENSAJE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NUMTASK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ResponseBansefi" type="{http://10.200.14.125:3096/WebServices/LOGONNSF}ArrayOfResponseBansefi" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EjecutarResult", propOrder = {
    "tranid",
    "estatus",
    "codigo",
    "mensaje",
    "numtask",
    "responseBansefi"
})
public class EjecutarResult {

    @XmlElement(name = "TRANID", required = true)
    protected String tranid;
    @XmlElement(name = "ESTATUS", required = true)
    protected String estatus;
    @XmlElement(name = "CODIGO", required = true)
    protected String codigo;
    @XmlElement(name = "MENSAJE", required = true)
    protected String mensaje;
    @XmlElement(name = "NUMTASK", required = true)
    protected String numtask;
    @XmlElement(name = "ResponseBansefi")
    protected ArrayOfResponseBansefi responseBansefi;

    /**
     * Obtiene el valor de la propiedad tranid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTRANID() {
        return tranid;
    }

    /**
     * Define el valor de la propiedad tranid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTRANID(String value) {
        this.tranid = value;
    }

    /**
     * Obtiene el valor de la propiedad estatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESTATUS() {
        return estatus;
    }

    /**
     * Define el valor de la propiedad estatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESTATUS(String value) {
        this.estatus = value;
    }

    /**
     * Obtiene el valor de la propiedad codigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCODIGO() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCODIGO(String value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMENSAJE() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMENSAJE(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad numtask.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNUMTASK() {
        return numtask;
    }

    /**
     * Define el valor de la propiedad numtask.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNUMTASK(String value) {
        this.numtask = value;
    }

    /**
     * Obtiene el valor de la propiedad responseBansefi.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfResponseBansefi }
     *     
     */
    public ArrayOfResponseBansefi getResponseBansefi() {
        return responseBansefi;
    }

    /**
     * Define el valor de la propiedad responseBansefi.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfResponseBansefi }
     *     
     */
    public void setResponseBansefi(ArrayOfResponseBansefi value) {
        this.responseBansefi = value;
    }


        public List<ResponseBansefi> getDefaultEacp() {

            List<ResponseBansefi> listaMenusDefault= new ArrayList<>();

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                    ,"18","REMESAS NACIONALES","","181","Originar","#{remNacController.originar()}",""
                    ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                    ,"18","REMESAS NACIONALES","","182","Liquidar","#{remNacController.liquidar()}",""
                    ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                    ,"18","REMESAS NACIONALES","","184","Consulta de Operaciones","#{remNacController.consultaOperaciones()}",""
                    ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                    ,"18","REMESAS NACIONALES","","188","Aclaraciones","#{remNacController.aclaraciones()}",""
                    ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));


                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                         ,"19","REMESAS INTERNACIONALES","","191","Abono Cuenta","#{remInterController.abonoCuenta()}",""
                         ,"","","2015-08-06","420", BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                    ,"19","REMESAS INTERNACIONALES","","192","Aclaracion","#{remInterController.aclaracion()}",""
                    ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                    ,"19","REMESAS INTERNACIONALES","","198","Consulta de Movimientos\t","#{remInterController.consulMovimientos()}",""
                    ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                    ,"19","REMESAS INTERNACIONALES","","199","Consulta de Movimientos por Entidad\t","#{remInterController.consulMovEntidad()}",""
                    ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                         ,"19","REMESAS INTERNACIONALES","","200","Consulta de Movimientos por Remesedora","#{remInterController.consulMovRemesa()",""
                         ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                         ,"19","REMESAS INTERNACIONALES","","205","Pago en Ventanilla","#{remInterController.pagoVentanilla()}",""
                         ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                    ,"21","RECEPCIÓN DE PAGOS A TERCEROS","","211","recepción de pagos","#{recepPagoTerceroController.recepcionPago()}",""
                    ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                    ,"21","RECEPCIÓN DE PAGOS A TERCEROS","","212","Anulación de Pagos","#{recepPagoTerceroController.anulacionPago()}",""
                    ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                    ,"21","RECEPCIÓN DE PAGOS A TERCEROS","","213","Reporte de Operaciones","#{recepPagoTerceroController.reporteOperaciones()}",""
                    ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));

                 listaMenusDefault.add((new ResponseBansefi()).create("E5690019","Gerardo", "0569","001","","EACP","1","NSS"
                    ,"12","","","","","",""
                    ,"","","2015-08-06","420",BigInteger.valueOf(18), BigInteger.valueOf(0),"2015-08-05","2015-08-07"));



            return listaMenusDefault;

    }
}
