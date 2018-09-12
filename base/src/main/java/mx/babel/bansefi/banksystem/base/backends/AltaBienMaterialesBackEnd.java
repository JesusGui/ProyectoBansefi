package mx.babel.bansefi.banksystem.base.backends;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.beans.domain.BienBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.webservices.altabienmateriales.AltaBienMaterialesServicio;
import mx.babel.bansefi.banksystem.base.webservices.altabienmateriales.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.altabienmateriales.Ejecutar.ITRALTABIENMATERIALES;
import mx.babel.bansefi.banksystem.base.webservices.altabienmateriales.Ejecutar.ITRALTABIENMATERIALES.TRALTABIENMATERIALESE.DATOSCARACTV;
import mx.babel.bansefi.banksystem.base.webservices.altabienmateriales.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.altabienmateriales.ResponseBansefi;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend que se encarga de dar de alta un los datos materiales de un bien
 *
 * @author luis.gcastellano
 *
 */
@Component
public class AltaBienMaterialesBackEnd extends BackEndBean {

    private static final long serialVersionUID = -899250773803594084L;

    private static final Logger LOGGER = LogManager.getLogger(AltaBienMaterialesBackEnd.class
            .getName());

    @Autowired
    ServicioWebUtils servicioWebUtils;

    public int ejectuarTRN(final BienBean bien) {

        LOGGER.debug("Entra al metrodo ejectuarTRN");

        final ResponseBansefi response = this.getAltaBienMaterialesResponse(bien);

        LOGGER.debug("Sale del metrodo ejectuarTRN");

        return response.getOTRALTABIENMATERIALES().getRTRNCD();
    }

    /**
     * Función para dar de alta los datos materiales de un bien invocando un servicio web.
     *
     * @param bien
     *            bean con los datos de entrada
     * @return Objeto indicando se ha realizado bien el alta
     */
    public ResponseBansefi getAltaBienMaterialesResponse(final BienBean bien)
            throws NoControlableException, ControlableException {
        LOGGER.debug("Entra al metrodo getAltaBienResponse");

        ResponseBansefi response = null;

        final Ejecutar.ITRALTABIENMATERIALES contexto = this.initPeticion(bien);

        final EjecutarResult respuesta = ejecutarWS(contexto);

        super.verificaRespuesta(respuesta);


        response = respuesta.getResponseBansefi();

        LOGGER.debug("Sale del metrodo getAltaBienResponse");
        return response;
    }

    /**
     * Función para invocar al servicio web y obtener su respuesta.
     *
     * @param contexto
     *            Objeto de petición al servicio web
     * @return La respuesta del servicio web.
     */
    private EjecutarResult ejecutarWS(final Ejecutar.ITRALTABIENMATERIALES contexto)
            throws NoControlableException {
        EjecutarResult respuesta = null;

        try {
            respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaBienMaterialesServicio.class, contexto);
        } catch (final NoControlableException e) {
            throw new NoControlableException(
                    "Error al invocar servicio web alta de bien ", e);
        }
        return respuesta;
    }

    /**
     * Función para inicializar los atributos del objeto de peticíon al servicio
     * web.
     *
     * @param req
     *            bean con los datos de entrada
     * @return Objeto de petición al web service
     */
    private ITRALTABIENMATERIALES initPeticion(final BienBean bien) {

        final DateFormat df = new SimpleDateFormat(ConstantesFuncionales.GENERAL_DATE_FORMATTER);

        final Ejecutar.ITRALTABIENMATERIALES contexto = new Ejecutar.ITRALTABIENMATERIALES();
        final Ejecutar.ITRALTABIENMATERIALES.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRALTABIENMATERIALES.STDTRNIPARMV();
        final Ejecutar.ITRALTABIENMATERIALES.TRALTABIENMATERIALESE traltabienmaterialese  = new Ejecutar.ITRALTABIENMATERIALES.TRALTABIENMATERIALESE();

        stdtrniparmv.setIDINTERNOTERMTN(super.getTerminal());
        stdtrniparmv.setCODTX(CodTxConstants.COD_TX_TR_ALTA_BIEN_MATERIALES_TRN);

        //Superficie
        if(StringUtils.isNotBlank(bien.getDatosTerreno().getSuperficieConstruidaString())){
            final DATOSCARACTV superficie = new DATOSCARACTV();
            superficie.setCODBIEN(bien.getTipoCodigo());
            superficie.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(0));
            superficie.setCODNRBEEN(super.getEntidad());
            superficie.setVALORCRCTBI(bien.getDatosTerreno().getSuperficieConstruidaString());
            if(StringUtils.isNotBlank(bien.getDatosTerreno().getSuperficieConstruidaString())){
                superficie.setVALORCRCTBILEN(bien.getDatosTerreno().getSuperficieConstruidaString().length());
            }
            superficie.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(superficie);
        }

        //PARTIDA
        if(StringUtils.isNotBlank(bien.getDatosTerreno().getPartida())){
            final DATOSCARACTV partida = new DATOSCARACTV();
            partida.setCODBIEN(bien.getTipoCodigo());
            partida.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(1));
            partida.setCODNRBEEN(super.getEntidad());
            partida.setVALORCRCTBI(bien.getDatosTerreno().getPartida());
            if(StringUtils.isNotBlank(bien.getDatosTerreno().getPartida())){
                partida.setVALORCRCTBILEN(bien.getDatosTerreno().getPartida().length());
            }
            partida.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(partida);
        }


        //TIPO_CULTIVO
        if(StringUtils.isNotBlank(bien.getDatosTerreno().getTipoCultivo())){

            final DATOSCARACTV tipoCultivo = new DATOSCARACTV();
            tipoCultivo.setCODBIEN(bien.getTipoCodigo());
            tipoCultivo.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(2));
            tipoCultivo.setCODNRBEEN(super.getEntidad());
            tipoCultivo.setVALORCRCTBI(bien.getDatosTerreno().getTipoCultivo());
            if(StringUtils.isNotBlank(bien.getDatosTerreno().getTipoCultivo())){
                tipoCultivo.setVALORCRCTBILEN(bien.getDatosTerreno().getTipoCultivo().length());
            }
            tipoCultivo.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(tipoCultivo);
        }

        //TIPO_DE_TERRENO
        if(StringUtils.isNotBlank(bien.getDatosTerreno().getTipoTerreno())){
            final DATOSCARACTV tipoTerreno = new DATOSCARACTV();
            tipoTerreno.setCODBIEN(bien.getTipoCodigo());
            tipoTerreno.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(3));
            tipoTerreno.setCODNRBEEN(super.getEntidad());
            tipoTerreno.setVALORCRCTBI(bien.getDatosTerreno().getTipoTerreno());
            if(StringUtils.isNotBlank(bien.getDatosTerreno().getTipoTerreno())){
                tipoTerreno.setVALORCRCTBILEN(bien.getDatosTerreno().getTipoTerreno().length());
            }
            tipoTerreno.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(tipoTerreno);
        }


        //HORAS_REGAD_PRPDAD
        if(bien.getDatosTerreno().getHorasRegadioString()!=null){
            final DATOSCARACTV horasRegadio = new DATOSCARACTV();
            horasRegadio.setCODBIEN(bien.getTipoCodigo());
            horasRegadio.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(4));
            horasRegadio.setCODNRBEEN(super.getEntidad());
            horasRegadio.setVALORCRCTBI(bien.getDatosTerreno().getHorasRegadioString());
            if(bien.getDatosTerreno().getHorasRegadioString()!=null){
                horasRegadio.setVALORCRCTBILEN(bien.getDatosTerreno().getHorasRegadioString().length());
            }
            horasRegadio.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(horasRegadio);
        }


        //HORA_REG_NO_PRPDAD
        if(bien.getDatosTerreno().getOtrasString()!=null){
            final DATOSCARACTV horasRegadioNoPrpdad = new DATOSCARACTV();
            horasRegadioNoPrpdad.setCODBIEN(bien.getTipoCodigo());
            horasRegadioNoPrpdad.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(5));
            horasRegadioNoPrpdad.setCODNRBEEN(super.getEntidad());
            horasRegadioNoPrpdad.setVALORCRCTBI(bien.getDatosTerreno().getOtrasString());
            if(bien.getDatosTerreno().getOtrasString()!=null){
                horasRegadioNoPrpdad.setVALORCRCTBILEN(bien.getDatosTerreno().getOtrasString().length());
            }
            horasRegadioNoPrpdad.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(horasRegadioNoPrpdad);
        }


        //NOMBRE_DE_LA_FINCA
        if(StringUtils.isNotBlank(bien.getDatosTerreno().getNombreFinca())){
            final DATOSCARACTV nombreFinca = new DATOSCARACTV();
            nombreFinca.setCODBIEN(bien.getTipoCodigo());
            nombreFinca.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(6));
            nombreFinca.setCODNRBEEN(super.getEntidad());
            nombreFinca.setVALORCRCTBI(bien.getDatosTerreno().getNombreFinca());
            if(StringUtils.isNotBlank(bien.getDatosTerreno().getNombreFinca())){
                nombreFinca.setVALORCRCTBILEN(bien.getDatosTerreno().getNombreFinca().length());
            }
            nombreFinca.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(nombreFinca);
        }


//        //ENTIDAD_TASADORA
//        DATOSCARACTV entidadValuadora = new DATOSCARACTV();
//        entidadValuadora.setCODBIEN(bien.getTipoCodigo());
//        entidadValuadora.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(7));
//        entidadValuadora.setCODNRBEEN(super.getEntidad());
//        entidadValuadora.setVALORCRCTBI(bien.getDatosValuacion().getEntidadValuadora());
//        if(StringUtils.isNotBlank(bien.getDatosValuacion().getEntidadValuadora())){
//            entidadValuadora.setVALORCRCTBILEN(bien.getDatosValuacion().getEntidadValuadora().length());
//        }
//        entidadValuadora.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
//        traltabienmaterialese.getDATOSCARACTV().add(entidadValuadora);

        //TIPO_CARGA
        if(StringUtils.isNotBlank(bien.getDatosTerreno().getCarga())){
            final DATOSCARACTV tipoCarga = new DATOSCARACTV();
            tipoCarga.setCODBIEN(bien.getTipoCodigo());
            tipoCarga.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(8));
            tipoCarga.setCODNRBEEN(super.getEntidad());
            tipoCarga.setVALORCRCTBI(bien.getDatosTerreno().getCarga());
            if(StringUtils.isNotBlank(bien.getDatosTerreno().getCarga())){
                tipoCarga.setVALORCRCTBILEN(bien.getDatosTerreno().getCarga().length());
            }
            tipoCarga.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(tipoCarga);
        }


        //CANTIDAD
        if(bien.getDatosValuacion().getCantidad()!=null){
            final DATOSCARACTV cantidad = new DATOSCARACTV();
            cantidad.setCODBIEN(bien.getTipoCodigo());
            cantidad.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(9));
            cantidad.setCODNRBEEN(super.getEntidad());
            cantidad.setVALORCRCTBI(String.valueOf(bien.getDatosValuacion().getCantidad()));
            if(bien.getDatosValuacion().getCantidad()!=null){
                cantidad.setVALORCRCTBILEN(String.valueOf(bien.getDatosValuacion().getCantidad()).length());
            }
            cantidad.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(cantidad);
        }


        //MATRICULA
        if(StringUtils.isNotBlank(bien.getDatosGenerales().getMatricula())){
            final DATOSCARACTV matricula = new DATOSCARACTV();
            matricula.setCODBIEN(bien.getTipoCodigo());
            matricula.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(10));
            matricula.setCODNRBEEN(super.getEntidad());
            matricula.setVALORCRCTBI(bien.getDatosGenerales().getMatricula());
            if(StringUtils.isNotBlank(bien.getDatosGenerales().getMatricula())){
                matricula.setVALORCRCTBILEN(bien.getDatosGenerales().getMatricula().length());
            }
            matricula.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(matricula);
        }


        //MARCA
        if(StringUtils.isNotBlank(bien.getDatosGenerales().getMarca())){
            final DATOSCARACTV marca = new DATOSCARACTV();
            marca.setCODBIEN(bien.getTipoCodigo());
            marca.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(11));
            marca.setCODNRBEEN(super.getEntidad());
            marca.setVALORCRCTBI(bien.getDatosGenerales().getMarca());
            if(StringUtils.isNotBlank(bien.getDatosGenerales().getMarca())){
                marca.setVALORCRCTBILEN(bien.getDatosGenerales().getMarca().length());
            }
            marca.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(marca);
        }


        //MODELO
        if(StringUtils.isNotBlank(bien.getDatosGenerales().getModelo())){
            final DATOSCARACTV modelo = new DATOSCARACTV();
            modelo.setCODBIEN(bien.getTipoCodigo());
            modelo.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(12));
            modelo.setCODNRBEEN(super.getEntidad());
            modelo.setVALORCRCTBI(bien.getDatosGenerales().getModelo());
            if(StringUtils.isNotBlank(bien.getDatosGenerales().getModelo())){
                modelo.setVALORCRCTBILEN(bien.getDatosGenerales().getModelo().length());
            }
            modelo.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(modelo);
        }


        //NUMERO_DE_SERIE
        if(StringUtils.isNotBlank(bien.getDatosGenerales().getNumeroSerie())){
            final DATOSCARACTV numeroSerie = new DATOSCARACTV();
            numeroSerie.setCODBIEN(bien.getTipoCodigo());
            numeroSerie.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(13));
            numeroSerie.setCODNRBEEN(super.getEntidad());
            numeroSerie.setVALORCRCTBI(bien.getDatosGenerales().getNumeroSerie());
            if(StringUtils.isNotBlank(bien.getDatosGenerales().getNumeroSerie())){
                numeroSerie.setVALORCRCTBILEN(bien.getDatosGenerales().getNumeroSerie().length());
            }
            numeroSerie.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(numeroSerie);
        }


        //IDENT_FABRICANTE
        if(StringUtils.isNotBlank(bien.getDatosGenerales().getFabricante())){
            final DATOSCARACTV fabricante = new DATOSCARACTV();
            fabricante.setCODBIEN(bien.getTipoCodigo());
            fabricante.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(14));
            fabricante.setCODNRBEEN(super.getEntidad());
            fabricante.setVALORCRCTBI(bien.getDatosGenerales().getFabricante());
            if(StringUtils.isNotBlank(bien.getDatosGenerales().getFabricante())){
                fabricante.setVALORCRCTBILEN(bien.getDatosGenerales().getFabricante().length());
            }
            fabricante.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(fabricante);
        }


        //INVERNADERO
        if(StringUtils.isNotBlank(bien.getDatosTerreno().getSuperficieInvernaderoString())){
            final DATOSCARACTV invernadero = new DATOSCARACTV();
            invernadero.setCODBIEN(bien.getTipoCodigo());
            invernadero.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(15));
            invernadero.setCODNRBEEN(super.getEntidad());
            invernadero.setVALORCRCTBI(bien.getDatosTerreno().getSuperficieInvernaderoString());
            if(StringUtils.isNotBlank(bien.getDatosTerreno().getSuperficieInvernaderoString())){
                invernadero.setVALORCRCTBILEN(bien.getDatosTerreno().getSuperficieInvernaderoString().length());
            }
            invernadero.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(invernadero);
        }


        //LICENCIA_DE_PESCA
        if(StringUtils.isNotBlank(bien.getDatosGenerales().getNumeroLicPesca())){
            final DATOSCARACTV licenciaPesca = new DATOSCARACTV();
            licenciaPesca.setCODBIEN(bien.getTipoCodigo());
            licenciaPesca.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(16));
            licenciaPesca.setCODNRBEEN(super.getEntidad());
            licenciaPesca.setVALORCRCTBI(bien.getDatosGenerales().getNumeroLicPesca());
            if(StringUtils.isNotBlank(bien.getDatosGenerales().getNumeroLicPesca())){
                licenciaPesca.setVALORCRCTBILEN(bien.getDatosGenerales().getNumeroLicPesca().length());
            }
            licenciaPesca.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(licenciaPesca);
        }


        //TONELAJE
        if(bien.getDatosGenerales().getTonelaje()!=null){
            final DATOSCARACTV tonelaje = new DATOSCARACTV();
            tonelaje.setCODBIEN(bien.getTipoCodigo());
            tonelaje.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(17));
            tonelaje.setCODNRBEEN(super.getEntidad());
            tonelaje.setVALORCRCTBI(String.valueOf(bien.getDatosGenerales().getTonelaje()));
            if(bien.getDatosGenerales().getTonelaje()!=null){
                tonelaje.setVALORCRCTBILEN(String.valueOf(bien.getDatosGenerales().getTonelaje()).length());
            }
            tonelaje.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(tonelaje);
        }


        //PUERTO
        if(StringUtils.isNotBlank(bien.getDatosGenerales().getPuerto())){
            final DATOSCARACTV puerto = new DATOSCARACTV();
            puerto.setCODBIEN(bien.getTipoCodigo());
            puerto.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(18));
            puerto.setCODNRBEEN(super.getEntidad());
            puerto.setVALORCRCTBI(bien.getDatosGenerales().getPuerto());
            if(StringUtils.isNotBlank(bien.getDatosGenerales().getPuerto())){
                puerto.setVALORCRCTBILEN(bien.getDatosGenerales().getPuerto().length());
            }
            puerto.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(puerto);
        }


        //TIPO
        if(StringUtils.isNotBlank(bien.getDatosValuacion().getClase())){
            final DATOSCARACTV tipo = new DATOSCARACTV();
            tipo.setCODBIEN(bien.getTipoCodigo());
            tipo.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(20));
            tipo.setCODNRBEEN(super.getEntidad());
            tipo.setVALORCRCTBI(bien.getDatosValuacion().getClase());
            if(StringUtils.isNotBlank(bien.getDatosValuacion().getClase())){
                tipo.setVALORCRCTBILEN(bien.getDatosValuacion().getClase().length());
            }
            tipo.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(tipo);
        }


        //ENTIDAD
        if(StringUtils.isNotBlank(bien.getDatosDeuda().getEntidad())){
            final DATOSCARACTV entidad = new DATOSCARACTV();
            entidad.setCODBIEN(bien.getTipoCodigo());
            entidad.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(21));
            entidad.setCODNRBEEN(super.getEntidad());
            entidad.setVALORCRCTBI(bien.getDatosDeuda().getEntidad());
            if(StringUtils.isNotBlank(bien.getDatosDeuda().getEntidad())){
                entidad.setVALORCRCTBILEN(bien.getDatosDeuda().getEntidad().length());
            }
            entidad.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(entidad);
        }

        //Capital Concedido
        if(bien.getDatosDeuda().getConcedidoString()!=null){
            final DATOSCARACTV capitalConcedido = new DATOSCARACTV();
            capitalConcedido.setCODBIEN(bien.getTipoCodigo());
            capitalConcedido.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(23));
            capitalConcedido.setCODNRBEEN(super.getEntidad());
            capitalConcedido.setVALORCRCTBI(bien.getDatosDeuda().getConcedidoString());
            if(bien.getDatosDeuda().getConcedidoString()!=null){
                capitalConcedido.setVALORCRCTBILEN(bien.getDatosDeuda().getConcedidoString().length());
            }
            capitalConcedido.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(capitalConcedido);
        }

        //TIPO_DE_INTERES
        if(bien.getDatosDeuda().getTipoInteresString()!=null){
            final DATOSCARACTV tipoInteres = new DATOSCARACTV();
            tipoInteres.setCODBIEN(bien.getTipoCodigo());
            tipoInteres.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(24));
            tipoInteres.setCODNRBEEN(super.getEntidad());
            tipoInteres.setVALORCRCTBI(bien.getDatosDeuda().getTipoInteresString());
            if(bien.getDatosDeuda().getTipoInteresString()!=null){
                tipoInteres.setVALORCRCTBILEN(bien.getDatosDeuda().getTipoInteresString().length());
            }
            tipoInteres.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(tipoInteres);
        }

        //Duracion
        if(StringUtils.isNotBlank(bien.getDatosDeuda().getDuracion())){
            final DATOSCARACTV duracion = new DATOSCARACTV();
            duracion.setCODBIEN(bien.getTipoCodigo());
            duracion.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(25));
            duracion.setCODNRBEEN(super.getEntidad());
            duracion.setVALORCRCTBI(bien.getDatosDeuda().getDuracion());
            if(StringUtils.isNotBlank(bien.getDatosDeuda().getDuracion())){
                duracion.setVALORCRCTBILEN(bien.getDatosDeuda().getDuracion().length());
            }
            duracion.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(duracion);
        }

        //PERIODO_AMORTIZA
        if(StringUtils.isNotBlank(bien.getDatosDeuda().getFrecuenciaAmortizacion())){
            final DATOSCARACTV periodoAmortizacion = new DATOSCARACTV();
            periodoAmortizacion.setCODBIEN(bien.getTipoCodigo());
            periodoAmortizacion.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(26));
            periodoAmortizacion.setCODNRBEEN(super.getEntidad());
            periodoAmortizacion.setVALORCRCTBI(bien.getDatosDeuda().getFrecuenciaAmortizacion());
            if(StringUtils.isNotBlank(bien.getDatosDeuda().getFrecuenciaAmortizacion())){
                periodoAmortizacion.setVALORCRCTBILEN(bien.getDatosDeuda().getFrecuenciaAmortizacion().length());
            }
            periodoAmortizacion.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(periodoAmortizacion);
        }

        //FECHA_VENCIMIENTO
        if(bien.getDatosDeuda().getFechaVencimiento()!=null){
            final DATOSCARACTV fechaVencimiento = new DATOSCARACTV();
            fechaVencimiento.setCODBIEN(bien.getTipoCodigo());
            fechaVencimiento.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(27));
            fechaVencimiento.setCODNRBEEN(super.getEntidad());
            fechaVencimiento.setVALORCRCTBI(df.format(bien.getDatosDeuda().getFechaVencimiento()));
            fechaVencimiento.setVALORCRCTBILEN(String.valueOf(df.format(bien.getDatosDeuda().getFechaVencimiento())).length());

            fechaVencimiento.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(fechaVencimiento);
        }

        //TIPO_DE_DEUDA
        if(StringUtils.isNotBlank(bien.getDatosDeuda().getTipoDeuda())){
            final DATOSCARACTV tipoDeuda = new DATOSCARACTV();
            tipoDeuda.setCODBIEN(bien.getTipoCodigo());
            tipoDeuda.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(28));
            tipoDeuda.setCODNRBEEN(super.getEntidad());
            tipoDeuda.setVALORCRCTBI(bien.getDatosDeuda().getTipoDeuda());
            if(StringUtils.isNotBlank(bien.getDatosDeuda().getTipoDeuda())){
                tipoDeuda.setVALORCRCTBILEN(bien.getDatosDeuda().getTipoDeuda().length());
            }
            tipoDeuda.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(tipoDeuda);
        }


        //ESTADO
        if(StringUtils.isNotBlank(bien.getDatosGenerales().getEstado())){
            final DATOSCARACTV estado = new DATOSCARACTV();
            estado.setCODBIEN(bien.getTipoCodigo());
            estado.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(29));
            estado.setCODNRBEEN(super.getEntidad());
            estado.setVALORCRCTBI(bien.getDatosGenerales().getEstado());
            if(StringUtils.isNotBlank(bien.getDatosGenerales().getEstado())){
                estado.setVALORCRCTBILEN(bien.getDatosGenerales().getEstado().length());
            }
            estado.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(estado);
        }


        //IND_PRIMER_VIVIEND
        if(bien.getDatosTerreno().getPrimeraVivienda()!=null){
            final DATOSCARACTV primeraVivienda = new DATOSCARACTV();
            primeraVivienda.setCODBIEN(bien.getTipoCodigo());
            primeraVivienda.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(30));
            primeraVivienda.setCODNRBEEN(super.getEntidad());
            if(bien.getDatosTerreno().getPrimeraVivienda()) {
                primeraVivienda.setVALORCRCTBI(ConstantesFuncionales.IND_SI);
            } else{
                primeraVivienda.setVALORCRCTBI(ConstantesFuncionales.IND_NO);
            }
            primeraVivienda.setVALORCRCTBILEN(1);
            primeraVivienda.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(primeraVivienda);
        }



        //SUPERFICIE_TERRENO
        if(StringUtils.isNotBlank(bien.getDatosTerreno().getSuperficieTerrenoString())){
            final DATOSCARACTV superficieTerreno = new DATOSCARACTV();
            superficieTerreno.setCODBIEN(bien.getTipoCodigo());
            superficieTerreno.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(32));
            superficieTerreno.setCODNRBEEN(super.getEntidad());
            superficieTerreno.setVALORCRCTBI(bien.getDatosTerreno().getSuperficieTerrenoString());
            if(StringUtils.isNotBlank(bien.getDatosTerreno().getSuperficieTerrenoString())){
                superficieTerreno.setVALORCRCTBILEN(bien.getDatosTerreno().getSuperficieTerrenoString().length());
            }
            superficieTerreno.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(superficieTerreno);
        }


        //COMPAWIA_ASEGURA
        if(StringUtils.isNotBlank(bien.getDatosSeguro().getCiaAseguradora())){
            final DATOSCARACTV ciaAseguradora = new DATOSCARACTV();
            ciaAseguradora.setCODBIEN(bien.getTipoCodigo());
            ciaAseguradora.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(33));
            ciaAseguradora.setCODNRBEEN(super.getEntidad());
            ciaAseguradora.setVALORCRCTBI(bien.getDatosSeguro().getCiaAseguradora());
            if(StringUtils.isNotBlank(bien.getDatosSeguro().getCiaAseguradora())){
                ciaAseguradora.setVALORCRCTBILEN(bien.getDatosSeguro().getCiaAseguradora().length());
            }
            ciaAseguradora.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(ciaAseguradora);
        }


        //PRIMERA_HIPOTECA
        if(bien.getDatosTerreno().getPrimeraHipoteca()!=null){
            final DATOSCARACTV primeraHipoteca = new DATOSCARACTV();
            primeraHipoteca.setCODBIEN(bien.getTipoCodigo());
            primeraHipoteca.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(34));
            primeraHipoteca.setCODNRBEEN(super.getEntidad());
            if(bien.getDatosTerreno().getPrimeraHipoteca()) {
                primeraHipoteca.setVALORCRCTBI(ConstantesFuncionales.IND_SI);
            } else{
                primeraHipoteca.setVALORCRCTBI(ConstantesFuncionales.IND_NO);
            }
            primeraHipoteca.setVALORCRCTBILEN(1);
            primeraHipoteca.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(primeraHipoteca);
        }

        //CONTINGENCIA
        if(StringUtils.isNotBlank(bien.getDatosSeguro().getContingencia())){
            final DATOSCARACTV contingencia = new DATOSCARACTV();
            contingencia.setCODBIEN(bien.getTipoCodigo());
            contingencia.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(35));
            contingencia.setCODNRBEEN(super.getEntidad());
            contingencia.setVALORCRCTBI(bien.getDatosSeguro().getContingencia());
            if(StringUtils.isNotBlank(bien.getDatosSeguro().getContingencia())){
                contingencia.setVALORCRCTBILEN(bien.getDatosSeguro().getContingencia().length());
            }
            contingencia.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(contingencia);
        }


        //COBERTURA
        if(bien.getDatosSeguro().getCoberturaString()!=null){
            final DATOSCARACTV cobertura = new DATOSCARACTV();
            cobertura.setCODBIEN(bien.getTipoCodigo());
            cobertura.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(36));
            cobertura.setCODNRBEEN(super.getEntidad());
            cobertura.setVALORCRCTBI(bien.getDatosSeguro().getCoberturaString());
            if(bien.getDatosSeguro().getCoberturaString()!=null){
                cobertura.setVALORCRCTBILEN(bien.getDatosSeguro().getCoberturaString().length());
            }
            cobertura.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(cobertura);
        }


        //BENEFICIARIO
        if(bien.getDatosSeguro().getBeneficiario()!=null){
            final DATOSCARACTV beneficiario = new DATOSCARACTV();
            beneficiario.setCODBIEN(bien.getTipoCodigo());
            beneficiario.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(37));
            beneficiario.setCODNRBEEN(super.getEntidad());
            beneficiario.setVALORCRCTBI(String.valueOf(bien.getDatosSeguro().getBeneficiario()));
            if(bien.getDatosSeguro().getBeneficiario()!=null){
                beneficiario.setVALORCRCTBILEN(String.valueOf(bien.getDatosSeguro().getBeneficiario()).length());
            }
            beneficiario.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(beneficiario);
        }


        //PORCENTAJE_NEGOCIO
        if(bien.getDatosDeuda().getNegocioString()!=null){
            final DATOSCARACTV porcentajeNegocio = new DATOSCARACTV();
            porcentajeNegocio.setCODBIEN(bien.getTipoCodigo());
            porcentajeNegocio.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(38));
            porcentajeNegocio.setCODNRBEEN(super.getEntidad());
            porcentajeNegocio.setVALORCRCTBI(bien.getDatosDeuda().getNegocioString());
            if(bien.getDatosDeuda().getNegocioString()!=null){
                porcentajeNegocio.setVALORCRCTBILEN(bien.getDatosDeuda().getNegocioString().length());
            }
            porcentajeNegocio.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(porcentajeNegocio);
        }


        //CIF_TASADORA
        if(StringUtils.isNotBlank(bien.getDatosValuacion().getEntidadValuadora())){
            final DATOSCARACTV cifValuadora = new DATOSCARACTV();
            cifValuadora.setCODBIEN(bien.getTipoCodigo());
            cifValuadora.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(39));
            cifValuadora.setCODNRBEEN(super.getEntidad());
            cifValuadora.setVALORCRCTBI(String.valueOf(bien.getDatosValuacion().getEntidadValuadora()));
            if(StringUtils.isNotBlank(bien.getDatosValuacion().getEntidadValuadora())){
                cifValuadora.setVALORCRCTBILEN(String.valueOf(bien.getDatosValuacion().getEntidadValuadora()).length());
            }
            cifValuadora.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(cifValuadora);
        }


        //TIPO_SEGURO
        if(bien.getDatosSeguro().getTipoSeguro()!=null){
            final DATOSCARACTV tipoSeguro = new DATOSCARACTV();
            tipoSeguro.setCODBIEN(bien.getTipoCodigo());
            tipoSeguro.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(40));
            tipoSeguro.setCODNRBEEN(super.getEntidad());
            tipoSeguro.setVALORCRCTBI(String.valueOf(bien.getDatosSeguro().getTipoSeguro()));
            if(bien.getDatosSeguro().getTipoSeguro()!=null){
                tipoSeguro.setVALORCRCTBILEN(String.valueOf(bien.getDatosSeguro().getTipoSeguro()).length());
            }
            tipoSeguro.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(tipoSeguro);
        }


        //PRIMA_ANUAL
        if(bien.getDatosSeguro().getPrimaAnualString()!=null){
            final DATOSCARACTV primaAnual = new DATOSCARACTV();
            primaAnual.setCODBIEN(bien.getTipoCodigo());
            primaAnual.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(41));
            primaAnual.setCODNRBEEN(super.getEntidad());
            primaAnual.setVALORCRCTBI(bien.getDatosSeguro().getPrimaAnualString());
            if(bien.getDatosSeguro().getPrimaAnualString()!=null){
                primaAnual.setVALORCRCTBILEN(bien.getDatosSeguro().getPrimaAnualString().length());
            }
            primaAnual.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(primaAnual);
        }


        //CIF_TASADORA
        if(bien.getDatosSeguro().getCif()!=null){
            final DATOSCARACTV cifSeguro = new DATOSCARACTV();
            cifSeguro.setCODBIEN(bien.getTipoCodigo());
            cifSeguro.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(42));
            cifSeguro.setCODNRBEEN(super.getEntidad());
            cifSeguro.setVALORCRCTBI(String.valueOf(bien.getDatosSeguro().getCif()));
            if(bien.getDatosSeguro().getCif()!=null){
                cifSeguro.setVALORCRCTBILEN(String.valueOf(bien.getDatosSeguro().getCif()).length());
            }
            cifSeguro.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(cifSeguro);
        }


        //FECHA_ANTIG
        if(bien.getDatosSeguro().getFechaAntiguedad()!=null){
            final DATOSCARACTV fechaAntiguedad = new DATOSCARACTV();
            fechaAntiguedad.setCODBIEN(bien.getTipoCodigo());
            fechaAntiguedad.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(43));
            fechaAntiguedad.setCODNRBEEN(super.getEntidad());
            fechaAntiguedad.setVALORCRCTBI(df.format(bien.getDatosSeguro().getFechaAntiguedad()));
            fechaAntiguedad.setVALORCRCTBILEN(String.valueOf(df.format(bien.getDatosSeguro().getFechaAntiguedad())).length());

            fechaAntiguedad.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(fechaAntiguedad);
        }

        //FCH_INI_COBERTURA
        if(bien.getDatosSeguro().getFechaInicioCobertura()!=null){
            final DATOSCARACTV fechaInicioCobertura = new DATOSCARACTV();
            fechaInicioCobertura.setCODBIEN(bien.getTipoCodigo());
            fechaInicioCobertura.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(44));
            fechaInicioCobertura.setCODNRBEEN(super.getEntidad());
            fechaInicioCobertura.setVALORCRCTBI(df.format(bien.getDatosSeguro().getFechaInicioCobertura()));
            fechaInicioCobertura.setVALORCRCTBILEN(String.valueOf(df.format(bien.getDatosSeguro().getFechaInicioCobertura())).length());
            fechaInicioCobertura.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(fechaInicioCobertura);
        }

        //FCH_FIN_COBERTURA
        if(bien.getDatosSeguro().getFechaFinCobertura()!=null){
            final DATOSCARACTV fechaFinCobertura = new DATOSCARACTV();
            fechaFinCobertura.setCODBIEN(bien.getTipoCodigo());
            fechaFinCobertura.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(45));
            fechaFinCobertura.setCODNRBEEN(super.getEntidad());
            fechaFinCobertura.setVALORCRCTBI(df.format(bien.getDatosSeguro().getFechaFinCobertura()));
            fechaFinCobertura.setVALORCRCTBILEN(String.valueOf(df.format(bien.getDatosSeguro().getFechaFinCobertura())).length());
            fechaFinCobertura.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(fechaFinCobertura);
        }

        //NUM_POLIZA
        if(bien.getDatosSeguro().getNumeroPoliza()!=null){
            final DATOSCARACTV numeroPoliza = new DATOSCARACTV();
            numeroPoliza.setCODBIEN(bien.getTipoCodigo());
            numeroPoliza.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(46));
            numeroPoliza.setCODNRBEEN(super.getEntidad());
            numeroPoliza.setVALORCRCTBI(String.valueOf(bien.getDatosSeguro().getNumeroPoliza()));
            if(bien.getDatosSeguro().getNumeroPoliza()!=null){
                numeroPoliza.setVALORCRCTBILEN(String.valueOf(bien.getDatosSeguro().getNumeroPoliza()).length());
            }
            numeroPoliza.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(numeroPoliza);
        }


        //NUM SEGURO
        if(bien.getDatosSeguro().getNumeroSeguro()!=null){
            final DATOSCARACTV numeroSeguro = new DATOSCARACTV();
            numeroSeguro.setCODBIEN(bien.getTipoCodigo());
            numeroSeguro.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(47));
            numeroSeguro.setCODNRBEEN(super.getEntidad());
            numeroSeguro.setVALORCRCTBI(String.valueOf(bien.getDatosSeguro().getNumeroSeguro()));
            if(bien.getDatosSeguro().getNumeroSeguro()!=null){
                numeroSeguro.setVALORCRCTBILEN(String.valueOf(bien.getDatosSeguro().getNumeroSeguro()).length());
            }
            numeroSeguro.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(numeroSeguro);
        }


        //VERIFICADO
        if(bien.getDatosGenerales().getVerificado()!=null){
            final DATOSCARACTV verificado = new DATOSCARACTV();
            verificado.setCODBIEN(bien.getTipoCodigo());
            verificado.setCODCRCTBIEN(ConstantesFuncionales.CODIGOS_CRCT.get(48));
            verificado.setCODNRBEEN(super.getEntidad());
            if(bien.getDatosGenerales().getVerificado()) {
                verificado.setVALORCRCTBI(ConstantesFuncionales.IND_SI);
            } else{
                verificado.setVALORCRCTBI(ConstantesFuncionales.IND_NO);
            }
            verificado.setVALORCRCTBILEN(1);
            verificado.setIDINTERNOBI(Integer.valueOf(bien.getIdInterno()));
            traltabienmaterialese.getDATOSCARACTV().add(verificado);
        }



        contexto.setSTDTRNIPARMV(stdtrniparmv);
        contexto.setTRALTABIENMATERIALESE(traltabienmaterialese);

        return contexto;
    }

}
