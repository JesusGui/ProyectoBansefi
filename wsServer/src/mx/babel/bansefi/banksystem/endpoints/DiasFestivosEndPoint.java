package mx.babel.bansefi.banksystem.endpoints;

import mx.babel.bansefi.banksystem.response.dias_festivos.ArrayOfResponseBansefi;
import mx.babel.bansefi.banksystem.response.dias_festivos.ResponseBansefi;
import mx.babel.bansefi.banksystem.utils.MarshallObjetoUtils;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class DiasFestivosEndPoint {
    @PayloadRoot(localPart = "Ejecutar", namespace = "http://BansefiNSS/WebServicesNSS/TR_CON_LISTA_FIESTAS_TRN")
    
    @ResponsePayload
    public Element ejecutarDiasHabiles(@RequestPayload Element element) {
        //Ejecutar objEjecutar = (Ejecutar) MarshallObjetoUtils.unMarshallObjeto(mx.babel.bansefi.banksystem.response.dias_festivos.Ejecutar.class, element);
        
        mx.babel.bansefi.banksystem.response.dias_festivos.EjecutarResponse response = new mx.babel.bansefi.banksystem.response.dias_festivos.EjecutarResponse();
        mx.babel.bansefi.banksystem.response.dias_festivos.EjecutarResult result = new mx.babel.bansefi.banksystem.response.dias_festivos.EjecutarResult();

        //Objecto ejecutar
//        Ejecutar.TRCONLISTAFIESTASTRNI trconlistafiestastrni = new Ejecutar.TRCONLISTAFIESTASTRNI();
//        
//        Ejecutar.TRCONLISTAFIESTASTRNI.STDTRNIPARMV stdtrniparmv = new Ejecutar.TRCONLISTAFIESTASTRNI.STDTRNIPARMV();
//        stdtrniparmv.setIDINTERNOTERMTN("");
//        stdtrniparmv.setNUMSEC(0);
//        stdtrniparmv.setIDEMPLAUT("eliot");
//        stdtrniparmv.setCODTX("");
//        stdtrniparmv.setCODTXDI("");
//        
//        trconlistafiestastrni.setSTDTRNIPARMV(stdtrniparmv);
//        
//        Ejecutar.TRCONLISTAFIESTASTRNI.TRCONLISTAFIESTASEVTY.FCTIPOFIESTA fctipofiesta = new Ejecutar.TRCONLISTAFIESTASTRNI.TRCONLISTAFIESTASEVTY.FCTIPOFIESTA();
//        fctipofiesta.setCODFIESTA("");
//        Ejecutar.TRCONLISTAFIESTASTRNI.TRCONLISTAFIESTASEVTY.FCFIESTAENTIDADP fcfiestaentidadp = Ejecutar.TRCONLISTAFIESTASTRNI.TRCONLISTAFIESTASEVTY.FCFIESTAENTIDADP();
//        fcfiestaentidadp.setFECHAFC(20150101);
//        fcfiestaentidadp.setCODNRBEEN("");
//        Ejecutar.TRCONLISTAFIESTASTRNI.TRCONLISTAFIESTASEVTY.FCFIESTACENTROP fcfiestacentrop = new Ejecutar.TRCONLISTAFIESTASTRNI.TRCONLISTAFIESTASEVTY.FCFIESTACENTROP();
//        fcfiestacentrop.setCODINTERNOUO("");
//        fcfiestacentrop.setCODNRBEEN("");
//        fcfiestacentrop.setFECHAFC(20150101);
//        Ejecutar.TRCONLISTAFIESTASTRNI.TRCONLISTAFIESTASEVTY.FCFIESTAARGEOP fcfiestaargeop = Ejecutar.TRCONLISTAFIESTASTRNI.TRCONLISTAFIESTASEVTY.FCFIESTAARGEOP();
//        fcfiestaargeop.setCODARGEO("");
//        fcfiestaargeop.setFECHAFC(20150101);
//        fcfiestaargeop.setNUMARGEO(1);
//        Ejecutar.TRCONLISTAFIESTASTRNI.TRCONLISTAFIESTASEVTY.FCFIESTAMONEDAP fcfiestamonedap = Ejecutar.TRCONLISTAFIESTASTRNI.TRCONLISTAFIESTASEVTY.FCFIESTAMONEDAP();
//        fcfiestamonedap.setCODNUMRCOMONEDA("");
//        fcfiestamonedap.setFECHAFC(20150101);
//        
//        Ejecutar.TRCONLISTAFIESTASTRNI.TRCONLISTAFIESTASEVTY trconlistafiestasevty = new Ejecutar.TRCONLISTAFIESTASTRNI.TRCONLISTAFIESTASEVTY();
//        trconlistafiestasevty.setFCTIPOFIESTA(fctipofiesta);
//        trconlistafiestasevty.setFCFIESTAENTIDADP(fcfiestaentidadp);
//        trconlistafiestasevty.setFCFIESTACENTROP(fcfiestacentrop);
//        trconlistafiestasevty.setFCFIESTAARGEOP(fcfiestaargeop);
//        trconlistafiestasevty.setFCFIESTAMONEDAP(fcfiestamonedap);
//        
//        trconlistafiestastrni.setTRCONLISTAFIESTASEVTY(trconlistafiestasevty);
//        objEjecutar.setTRCONLISTAFIESTASTRNI(trconlistafiestastrni);
        //--------
        
        ArrayOfResponseBansefi arResponseBansefi = new ArrayOfResponseBansefi();
        ResponseBansefi responseBansefi = new ResponseBansefi();
        ResponseBansefi.TRCONLISTAFIESTASTRNO trconlistafiestastrno = new ResponseBansefi.TRCONLISTAFIESTASTRNO();
        trconlistafiestastrno.setRTRNCD(0);
        trconlistafiestastrno.setMOREDATAIN(0);
        trconlistafiestastrno.setNUMBEROFRECORDS(0);
                
        ResponseBansefi.TRCONLISTAFIESTASTRNO.STDTRNOPARMV stdtrnoparmv = new ResponseBansefi.TRCONLISTAFIESTASTRNO.STDTRNOPARMV();
        stdtrnoparmv.setFECHAOPRCN(20150101);
        stdtrnoparmv.setHORAOPRCN(1215);
        ResponseBansefi.TRCONLISTAFIESTASTRNO.TRCONLISTAFIESTASEVTZ trconlistafiestasevtz = new ResponseBansefi.TRCONLISTAFIESTASTRNO.TRCONLISTAFIESTASEVTZ();
        
        ResponseBansefi.TRCONLISTAFIESTASTRNO.TRCONLISTAFIESTASEVTZ.TRCONLISTAFIESTASEVTV diaFestivo1 = 
                new ResponseBansefi.TRCONLISTAFIESTASTRNO.TRCONLISTAFIESTASEVTZ.TRCONLISTAFIESTASEVTV();
        diaFestivo1.setCODFIESTA("diaMuertos");
        diaFestivo1.setCODMDLDADFIESTA("");
        diaFestivo1.setNOMBFIESTAENTFC("DIA MUERTOS");
        diaFestivo1.setFECHAFC(20151102);
        
        
        ResponseBansefi.TRCONLISTAFIESTASTRNO.TRCONLISTAFIESTASEVTZ.TRCONLISTAFIESTASEVTV diaFestivo2 = 
                new ResponseBansefi.TRCONLISTAFIESTASTRNO.TRCONLISTAFIESTASEVTZ.TRCONLISTAFIESTASEVTV();
        
        diaFestivo2.setCODFIESTA("diaTrabajo");
        diaFestivo2.setCODMDLDADFIESTA("");
        diaFestivo2.setNOMBFIESTAENTFC("DIA INTERNACIONAL DEL TRABAJO");
        diaFestivo2.setFECHAFC(20151102);
        
        trconlistafiestasevtz.getTRCONLISTAFIESTASEVTV().add(diaFestivo1);
        trconlistafiestasevtz.getTRCONLISTAFIESTASEVTV().add(diaFestivo2);
        
        
        trconlistafiestastrno.setTRCONLISTAFIESTASEVTZ(trconlistafiestasevtz);
        trconlistafiestastrno.setSTDTRNOPARMV(stdtrnoparmv);
        ResponseBansefi.TRCONLISTAFIESTASTRNO.STDTRNMSJPARMV stdtrnmsjparmv = new ResponseBansefi.TRCONLISTAFIESTASTRNO.STDTRNMSJPARMV();
        stdtrnmsjparmv.setTEXTARG1("");
        stdtrnmsjparmv.setTEXTCODE(0);
        trconlistafiestastrno.getSTDTRNMSJPARMV().add(stdtrnmsjparmv);
        
        responseBansefi.setTRCONLISTAFIESTASTRNO(trconlistafiestastrno);
        
        arResponseBansefi.getResponseBansefi().add(responseBansefi);
        result.setResponseBansefi(arResponseBansefi);
        response.setEjecutarResult(result);
        System.out.println("About to return...");
        return MarshallObjetoUtils.marshallObjeto(mx.babel.bansefi.banksystem.response.dias_festivos.EjecutarResponse.class, response);
    }
}
