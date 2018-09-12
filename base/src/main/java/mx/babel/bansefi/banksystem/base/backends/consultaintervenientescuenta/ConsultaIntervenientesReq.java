package mx.babel.bansefi.banksystem.base.backends.consultaintervenientescuenta;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.babel.arq.sesion.utils.ContextoUtils;
import mx.babel.bansefi.banksystem.base.webservices.consultarelacionpersonascuenta.Ejecutar;

import org.springframework.beans.factory.annotation.Autowired;

public class ConsultaIntervenientesReq implements Serializable{

    private static final long serialVersionUID = 1L;

    @Autowired
    private ContextoUtils contextoUtils;
	
    public Ejecutar.ITRCONSULTARPPANTTRN consultarIntervenientes (Long numCuenta) {

    	Ejecutar.ITRCONSULTARPPANTTRN consulta = new Ejecutar.ITRCONSULTARPPANTTRN();
    	consulta.setCONSULTAV(this.consultav());
    	consulta.setEVENTCD(0);
    	consulta.setRPECVACV(this.rpecvacv());
    	consulta.setSTDTRNIPARMV(this.stdtrniparmv());
    	consulta.setTRCONSULTARPPANTEVTY(this.trconsultarpantevty(numCuenta));
		return consulta;
    	
    }
    
    private Ejecutar.ITRCONSULTARPPANTTRN.CONSULTAV consultav () {
    	Ejecutar.ITRCONSULTARPPANTTRN.CONSULTAV consultav = new Ejecutar.ITRCONSULTARPPANTTRN.CONSULTAV();
    	
    	consultav.setSTDCHAR02("    ");
    	return consultav;
    }
    
    private Ejecutar.ITRCONSULTARPPANTTRN.RPECVACV rpecvacv () {
    	Ejecutar.ITRCONSULTARPPANTTRN.RPECVACV rpecvacv = new Ejecutar.ITRCONSULTARPPANTTRN.RPECVACV();
    	
    	rpecvacv.setCODECVAC("    ");
    	return rpecvacv;
    }
    
    private Ejecutar.ITRCONSULTARPPANTTRN.STDTRNIPARMV stdtrniparmv () {
    	Ejecutar.ITRCONSULTARPPANTTRN.STDTRNIPARMV stdtrniparmv = new Ejecutar.ITRCONSULTARPPANTTRN.STDTRNIPARMV();
    	
    	stdtrniparmv.setCODTX("     ");
    	stdtrniparmv.setCODTXDI("     ");
    	stdtrniparmv.setIDEMPLAUT("     ");
    	stdtrniparmv.setIDINTERNOTERMTN("     ");
    	stdtrniparmv.setNUMSEC(1);
    	return stdtrniparmv;
    }
    
    private Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY trconsultarpantevty (Long numCuenta) {
    	Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY trconsultarpantevty = new Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY();
    	
    	trconsultarpantevty.setRPCAMPOOPCV(this.rpcampoopcv());
    	trconsultarpantevty.setRPPERSRLACP(this.rppersrlacp(numCuenta));
    	trconsultarpantevty.setRPPERSRLACV(this.rppersrlacv());
    	return trconsultarpantevty;
    }
    
    private Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY.RPCAMPOOPCV rpcampoopcv () {
    	Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY.RPCAMPOOPCV rpcampoopcv = new Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY.RPCAMPOOPCV();
    	
    	rpcampoopcv.setCODECVPERSAC("    ");
    	rpcampoopcv.setCODLINEA("     ");
    	rpcampoopcv.setIDGRPPD("    ");
    	rpcampoopcv.setIDPDV("     ");
    	rpcampoopcv.setINDGTCOMRCLGT("     ");
    	rpcampoopcv.setINDGTCONTCTGT("    ");
    	rpcampoopcv.setINDGTSITESPCLGT("      ");
    	rpcampoopcv.setNUMRLORDEN(1);
    	return rpcampoopcv;
    }
    
    // Esta es la que necesito
    private Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY.RPPERSRLACP rppersrlacp (Long numCuenta) {
    	Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY.RPPERSRLACP rppersrlacp = new Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY.RPPERSRLACP();
    	
    	
    	rppersrlacp.setCODCENTUO("Centro"); // Centro
    	rppersrlacp.setCODNRBEEN("sucursal"); // Entidad
    	rppersrlacp.setCODRLPERSAC("     ");
    	rppersrlacp.setIDINTERNOPE(1);
    	rppersrlacp.setNUMSECAC(numCuenta); // Numero de cuenta
    	return rppersrlacp;
    }
    
    private Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY.RPPERSRLACV rppersrlacv () {
    	Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY.RPPERSRLACV rppersrlacv = new Ejecutar.ITRCONSULTARPPANTTRN.TRCONSULTARPPANTEVTY.RPPERSRLACV();
    	
    	rppersrlacv.setCODLINEA("    ");
    	rppersrlacv.setCODNUMRCOMONEDA("    ");
    	rppersrlacv.setCODRZNECVRP("     ");
    	rppersrlacv.setCODRZNECVRP("    ");
    	rppersrlacv.setFECHAINICIOI(1);
    	rppersrlacv.setFECHAINICIOR(1);
    	rppersrlacv.setIDGRPPD("    ");
    	rppersrlacv.setIDPDV("    ");
    	rppersrlacv.setIMPGRTZAI(new BigDecimal("11111"));
    	rppersrlacv.setIMPGRTZAR(new BigDecimal("11111"));
    	rppersrlacv.setINDHAYMAS("     ");
    	return rppersrlacv;
    }
    
}
    

