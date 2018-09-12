package mx.babel.bansefi.banksystem.administracion.backends;

import java.io.Serializable;

import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.administracion.beans.EntidadBean;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionentidad.Ejecutar;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionentidad.Ejecutar.ITRMODIENTIDADTRNI;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionentidad.Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY.DRDMDPOBJTRDV;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionentidad.Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY.DRDMDPOBJTRDV.COMPDOMICV;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionentidad.EjecutarResult;
import mx.babel.bansefi.banksystem.administracion.webservices.modificacionentidad.ModificacionEntidadServicio;
import mx.babel.bansefi.banksystem.administracion.wrappers.EntidadWrapper;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModificaEntidadBackend extends BackEndBean implements Serializable{

    private static final long serialVersionUID = 5591658295629739538L;

	private static final Logger LOGGER = LogManager.getLogger(ModificaEntidadBackend.class.getName());

    @Autowired
    EntidadWrapper entidadWrapper;
    
    @Autowired
	ServicioWebUtils servicioWebUtils;
 
    public int ejectuarTRN(EntidadBean req){
    	
    	LOGGER.debug("Entra al metodo ejecutarTRN");
    
	    int numDir = 0;
	    
	    Ejecutar.ITRMODIENTIDADTRNI contexto = this.initPeticion(req);
	     
	    EjecutarResult respuesta = ejecutarWS(contexto);
	    
	    super.verificaRespuesta(respuesta);
	    
	    if(verificaResponseBansefi(respuesta)){
	        numDir = respuesta.getResponseBansefi().getOTRMODIENTIDADTRNO().getTRMODIENTIDADEVTZ().getDRDOMICP().getNUMDIR();
	    }
	    
	    LOGGER.debug("Sale del metodo ejecutarTRN");
	    return numDir;
    }
    
   
    
    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio web.
     * 
     * @param req bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    private ITRMODIENTIDADTRNI initPeticion(EntidadBean req) {
    	ITRMODIENTIDADTRNI contexto = new ITRMODIENTIDADTRNI();
        Ejecutar.ITRMODIENTIDADTRNI.STDTRNIPARMV stdtrniparmv =  new ITRMODIENTIDADTRNI.STDTRNIPARMV();
        Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY trconentidadevty = new Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY();
        Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY.INDMODIFDOMICV indmodifdomicv = new Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY.INDMODIFDOMICV();
        
        Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY.INDMODIFENTIDADV indmodifentidadv = new Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY.INDMODIFENTIDADV();
        Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY.INDMENSAJEMODIFDOMICV indmensajemodifdomicv = new Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY.INDMENSAJEMODIFDOMICV();
        Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY.DRDMDPOBJTRDV drdmdpobjtrdv = new Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY.DRDMDPOBJTRDV();
        Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY.CRENTIDADP crentidadp = new Ejecutar.ITRMODIENTIDADTRNI.TRMODIENTIDADEVTY.CRENTIDADP();
        
        crentidadp.setCODNRBEEN(req.getCodigo());
        
        drdmdpobjtrdv = creaDatosDomicilio(req);
        
        super.initialize(indmensajemodifdomicv);
        indmensajemodifdomicv.setSTDCHAR01("S");
        
        super.initialize(indmodifentidadv);
        indmodifentidadv.setSTDCHAR01("N");
        
        super.initialize(indmodifdomicv);
        indmodifdomicv.setSTDCHAR01("S");
        super.initialize(stdtrniparmv);
       
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_MODI_ENTIDAD_TRN);
       
        
        trconentidadevty = this.entidadWrapper.wrappBeanModificarEntidad(req);
        trconentidadevty.setCRENTIDADP(crentidadp);
        trconentidadevty.setINDMENSAJEMODIFDOMICV(indmensajemodifdomicv);
        trconentidadevty.setINDMODIFDOMICV(indmodifdomicv);
        trconentidadevty.setINDMODIFENTIDADV(indmodifentidadv);
        trconentidadevty.setDRDMDPOBJTRDV(drdmdpobjtrdv);
        
        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        
        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRMODIENTIDADEVTY(trconentidadevty);
        return contexto;
    
    }
    
    /**
     * Función para invocar al servicio web y obtener su respuesta.
     * 
     * @param contexto Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(Ejecutar.ITRMODIENTIDADTRNI contexto){
        EjecutarResult respuesta = null;
        
        try{
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(ModificacionEntidadServicio.class, contexto);
        }catch(NoControlableException e){
            throw new NoControlableException("Error al invocar servicio web de modificación de "
                    + "entidad.", e);
        }
        return respuesta;
    }
    
    /**
     * Función que valida que la respuesta del servidor no este vacía. 
     *  
     * @param respuesta Objeto respuesta del servicio web
     * @return <code>true</code> si la respuesta no esta vacía.
     */
    private Boolean verificaResponseBansefi(EjecutarResult respuesta){
        Boolean noNulo = false;
        if(respuesta != null && respuesta.getResponseBansefi() != null){
            noNulo = true;
        }
        return noNulo;
    }
        
    public DRDMDPOBJTRDV creaDatosDomicilio(final EntidadBean entidad){
        
        DRDMDPOBJTRDV drdmdpobjtrdv = new DRDMDPOBJTRDV();
        DRDMDPOBJTRDV.CODARGEODOMICILIOV codArgeodomiciliov = new DRDMDPOBJTRDV.CODARGEODOMICILIOV();
        DRDMDPOBJTRDV.NUMARGEODOMICILIOV numArgeodomiciliov = new DRDMDPOBJTRDV.NUMARGEODOMICILIOV();
        
        drdmdpobjtrdv.setCODNRBEEN(entidad.getCodigo());
        drdmdpobjtrdv.setNUMDIR(entidad.getCodDomicilio());
        drdmdpobjtrdv.setCODVIA(entidad.getDomicilio().getTipoCalle());
        drdmdpobjtrdv.setCODREGIMOCUP(entidad.getDomicilio().getRegimenOcupacion());
        drdmdpobjtrdv.setCODPOSTALAG(entidad.getDomicilio().getDatosFinalesCatGeo().getCodigoPostal());
        drdmdpobjtrdv.setNUMTLFNODOMIC(entidad.getDomicilio().getTelefono());
       
        codArgeodomiciliov.setCODARGEO(entidad.getDomicilio().getDatosFinalesCatGeo().getCodArGeo());
        drdmdpobjtrdv.setCODARGEODOMICILIOV(codArgeodomiciliov);
        numArgeodomiciliov.setNUMARGEO(Integer.valueOf(entidad.getDomicilio().getDatosFinalesCatGeo().getNumArGeo()));
        drdmdpobjtrdv.setNUMARGEODOMICILIOV(numArgeodomiciliov);
        
        COMPDOMICV calle = new COMPDOMICV();
        calle.setCODCOMPDOMIC(ConstantesFuncionales.CALLE);
        calle.setVALCOMPDOMICDRLEN(ConstantesFuncionales.CALLE_LONG);
        calle.setVALCOMPDOMICDR(entidad.getDomicilio().getNombreCalle());
        drdmdpobjtrdv.getCOMPDOMICV().add(calle);
        
        COMPDOMICV numExterior = new COMPDOMICV();
        numExterior.setCODCOMPDOMIC(ConstantesFuncionales.NUMERO_EXT);
        numExterior.setVALCOMPDOMICDRLEN(ConstantesFuncionales.NUMERO_EXT_LONG);
        numExterior.setVALCOMPDOMICDR(entidad.getDomicilio().getNumeroExterior());
        drdmdpobjtrdv.getCOMPDOMICV().add(numExterior);
        
        COMPDOMICV piso = new COMPDOMICV();
        piso.setCODCOMPDOMIC(ConstantesFuncionales.INTERIOR);
        piso.setVALCOMPDOMICDRLEN(ConstantesFuncionales.INTERIOR_LONG);
        piso.setVALCOMPDOMICDR(entidad.getDomicilio().getInterior());
        drdmdpobjtrdv.getCOMPDOMICV().add(piso);
        
        COMPDOMICV casa = new COMPDOMICV();
        casa.setCODCOMPDOMIC(ConstantesFuncionales.CASA);
        casa.setVALCOMPDOMICDRLEN(ConstantesFuncionales.CASA_LONG);
        casa.setVALCOMPDOMICDR(entidad.getDomicilio().getCasa());
        drdmdpobjtrdv.getCOMPDOMICV().add(casa);
        
        COMPDOMICV colonia = new COMPDOMICV();
        colonia.setCODCOMPDOMIC(ConstantesFuncionales.COLONIA);
        colonia.setVALCOMPDOMICDRLEN(ConstantesFuncionales.COLONIA_LONG);
        colonia.setVALCOMPDOMICDR(entidad.getDomicilio().getColonia());
        drdmdpobjtrdv.getCOMPDOMICV().add(colonia);
        
        COMPDOMICV otrosDatos = new COMPDOMICV();
        otrosDatos.setCODCOMPDOMIC(ConstantesFuncionales.OTROS_DATOS);
        otrosDatos.setVALCOMPDOMICDRLEN(ConstantesFuncionales.OTROS_DATOS_LONG);
        otrosDatos.setVALCOMPDOMICDR(entidad.getDomicilio().getOtrosDatos());
        drdmdpobjtrdv.getCOMPDOMICV().add(otrosDatos);
        
        COMPDOMICV unidadHabitacional = new COMPDOMICV();
        unidadHabitacional.setCODCOMPDOMIC(ConstantesFuncionales.UNIDAD_HABITACIONAL);
        unidadHabitacional.setVALCOMPDOMICDRLEN(ConstantesFuncionales.UNIDAD_HABITACIONAL_LONG);
        unidadHabitacional.setVALCOMPDOMICDR(entidad.getDomicilio().getUnidadHabitacional());
        drdmdpobjtrdv.getCOMPDOMICV().add(unidadHabitacional);
        
        COMPDOMICV edificio = new COMPDOMICV();
        edificio.setCODCOMPDOMIC(ConstantesFuncionales.EDIFICIO);
        edificio.setVALCOMPDOMICDRLEN(ConstantesFuncionales.EDIFICIO_LONG);
        edificio.setVALCOMPDOMICDR(entidad.getDomicilio().getEdificio());
        drdmdpobjtrdv.getCOMPDOMICV().add(edificio);
        
        COMPDOMICV bloque = new COMPDOMICV();
        bloque.setCODCOMPDOMIC(ConstantesFuncionales.BLOQUE);
        bloque.setVALCOMPDOMICDRLEN(ConstantesFuncionales.BLOQUE_LONG);
        bloque.setVALCOMPDOMICDR(entidad.getDomicilio().getBloque());
        drdmdpobjtrdv.getCOMPDOMICV().add(bloque);
        
        COMPDOMICV entrada = new COMPDOMICV();
        entrada.setCODCOMPDOMIC(ConstantesFuncionales.ENTRADA);
        entrada.setVALCOMPDOMICDRLEN(ConstantesFuncionales.ENTRADA_LONG);
        entrada.setVALCOMPDOMICDR(entidad.getDomicilio().getEntrada());
        drdmdpobjtrdv.getCOMPDOMICV().add(entrada);
        
        COMPDOMICV fase = new COMPDOMICV();
        fase.setCODCOMPDOMIC(ConstantesFuncionales.FASE);
        fase.setVALCOMPDOMICDRLEN(ConstantesFuncionales.FASE_LONG);
        fase.setVALCOMPDOMICDR(entidad.getDomicilio().getFase());
        drdmdpobjtrdv.getCOMPDOMICV().add(fase);
        
        COMPDOMICV lote = new COMPDOMICV();
        lote.setCODCOMPDOMIC(ConstantesFuncionales.LOTE);
        lote.setVALCOMPDOMICDRLEN(ConstantesFuncionales.LOTE_LONG);
        lote.setVALCOMPDOMICDR(entidad.getDomicilio().getLote());
        drdmdpobjtrdv.getCOMPDOMICV().add(lote);
        
        COMPDOMICV manzana = new COMPDOMICV();
        manzana.setCODCOMPDOMIC(ConstantesFuncionales.MANZANA);
        manzana.setVALCOMPDOMICDRLEN(ConstantesFuncionales.MANZANA_LONG);
        manzana.setVALCOMPDOMICDR(entidad.getDomicilio().getManzana());
        drdmdpobjtrdv.getCOMPDOMICV().add(manzana);
        
        COMPDOMICV departamento = new COMPDOMICV();
        departamento.setCODCOMPDOMIC(ConstantesFuncionales.DEPARTAMENTO);
        departamento.setVALCOMPDOMICDRLEN(ConstantesFuncionales.DEPARTAMENTO_LONG);
        departamento.setVALCOMPDOMICDR(entidad.getDomicilio().getDepartamento());
        drdmdpobjtrdv.getCOMPDOMICV().add(departamento);
        
        return drdmdpobjtrdv;
        
    }
    
}
