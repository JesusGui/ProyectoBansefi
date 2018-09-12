package mx.babel.bansefi.banksystem.base.backends;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.busquedas.BusquedaIdExternaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaRelacionadaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.constants.ConstantesFuncionales;
import mx.babel.bansefi.banksystem.base.utils.IntegerToDateConverter;
import mx.babel.bansefi.banksystem.base.utils.StringToDateConverter;
import mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona2.AltaRelacionClientePersonaServicio2;
import mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona2.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona2.Ejecutar.ITRPEALTARL2PEDSTR;
import mx.babel.bansefi.banksystem.base.webservices.relacioncliente.altapersona2.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para la TRN de alta de una relación tipo cliente persona 2.
 * 
 * @author aaron
 * 
 */
@Component
public class AltaRelacionClientePersonaBackEnd2 extends BackEndBean{
	
	private static final long serialVersionUID = 5173198114510234551L;
	
	private IntegerToDateConverter integerToDateConverter;
	private StringToDateConverter stringToDateConverter;
	
	@Autowired
	ServicioWebUtils servicioWebUtils;
	
	@Autowired
	ConsultaPersonaMoralBackEnd consultaPersonaMoralBackEnd;
	
	@Autowired
	ConsultaPersonaFisicaBackEnd consultaPersonaFisicaBackEnd;
	
	@Autowired
	BusquedaIdExternaBackEnd busquedaIdExternaBackEnd; 
	
	/**
	 * Constructor.
	 */
	public AltaRelacionClientePersonaBackEnd2(){
		super();
		this.integerToDateConverter = new IntegerToDateConverter();
		this.stringToDateConverter = new StringToDateConverter();
	}
	
	/**
	 * Método que ejecuta la TRN de alta de una relación tipo cliente-persona 2.
	 * 
	 * @throws ControlableException
	 * @throws NoControlableException
	 */
	public void ejecutarTRN(final ClienteBean clienteBean, final PersonaRelacionadaBean personaRelacionadaBean){
		Ejecutar.ITRPEALTARL2PEDSTR contexto = initPeticion(clienteBean, personaRelacionadaBean);
		EjecutarResult respuesta = ejecutarWS(contexto);
		
		try{
			super.verificaRespuesta(respuesta);
		}catch (ControlableException ce){
			if(ce.getRtncod() != RETURN_COD_SIN_DATOS){
				throw ce;
			}
		}
	}
	
	private Ejecutar.ITRPEALTARL2PEDSTR initPeticion(final ClienteBean clienteBean, final PersonaRelacionadaBean personaRelacionadaBean){
		Ejecutar.ITRPEALTARL2PEDSTR contexto = new Ejecutar.ITRPEALTARL2PEDSTR();
		
		super.initialize(contexto);
		
		Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY nivel1_1 = new Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY();
		Ejecutar.ITRPEALTARL2PEDSTR.STDTRNIPARMV nivel1_2 = new Ejecutar.ITRPEALTARL2PEDSTR.STDTRNIPARMV();
		
		Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE1V nivel2_1 = new Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE1V();
		Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE2 nivel2_2 = new Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.STDCODPE2();
		
		Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.INDCODPERLPECONTRARIOV nivel2_3 = new Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.INDCODPERLPECONTRARIOV();
		
		Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERLCPDADLGLV nivel2_4 = new Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERLCPDADLGLV();
		
		Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV nivel2_5 = new Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV();
		
		Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE1DS nivel3_1 = new Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE1DS();
		Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE2DS nivel3_2 = new Ejecutar.ITRPEALTARL2PEDSTR.TRPEALTARL2PEDSEVTY.PERL2PEVALV.PERLPE2DS();
		
		nivel1_1.setCODNRBEEN(super.getEntidad());
		nivel1_1.setIDINTERNOPE1(clienteBean.getIdInterna());
		
		nivel2_1.setCODPE(clienteBean.getTipoClienteEnum().getCodPe());
		nivel1_1.setSTDCODPE1V(nivel2_1);
		
		nivel1_1.setIDINTERNOPE2(personaRelacionadaBean.getIdInterna());
		
		final List<ClientePersonaFisicaBean> listaClientes = busquedaIdExternaBackEnd.ejecutarTRN(personaRelacionadaBean.getIdExterna());
		final String tipoPersonaRelacionada = listaClientes.get(0).getTipoClienteEnum().getCodPe();
		
		nivel2_2.setSTDCHAR01(tipoPersonaRelacionada);
		nivel1_1.setSTDCODPE2(nivel2_2);
		
		nivel1_1.setCODPERSRLPERS(personaRelacionadaBean.getRelacionSeleccionada().getClaveFila());
		nivel1_1.setFECHAINICRL(integerToDateConverter.convertFrom(Calendar.getInstance().getTime()));
		
		if(personaRelacionadaBean.getFechaInicio() == null){
			nivel1_1.setFECHAINICVLDZ(nivel1_1.getFECHAINICRL());
		}else{
			nivel1_1.setFECHAINICVLDZ(integerToDateConverter.convertFrom(personaRelacionadaBean.getFechaInicio()));
		}
		
		if(personaRelacionadaBean.getFechaFin() == null){
			nivel1_1.setFECHAFINVLDZ(integerToDateConverter.convertFrom(stringToDateConverter.convertTo(ConstantesFuncionales.MAX_FECHA_FIN)));
		}else{
			nivel1_1.setFECHAFINVLDZ(integerToDateConverter.convertFrom(personaRelacionadaBean.getFechaFin()));
		}
		
		nivel1_1.setTXTPERLPE(personaRelacionadaBean.getObservaciones());
		
		nivel2_3.setSTDCHAR01("");
		nivel2_3.setDESCRPERSRLPERS(personaRelacionadaBean.getRelacionSeleccionada().getDescripcionC().trim());
		nivel1_1.setINDCODPERLPECONTRARIOV(nivel2_3);
		
		ClientePersonaFisicaBean clienteFisicaBuscado = new ClientePersonaFisicaBean();
		ClientePersonaMoralBean clienteMoralBuscado = new ClientePersonaMoralBean();
		
		if(clienteBean.getTipoClienteEnum().getCodPe().equals("J")){
			clienteMoralBuscado = consultaPersonaMoralBackEnd.ejecutarTRN(clienteBean.getIdInterna());
			
			nivel2_4.setCODESTRCTLGLORG(clienteMoralBuscado.getEstructuraLegal());
			nivel2_4.setCODCPCDADLGLIN("");
			nivel2_4.setFECNCTOCONSTPE(0);
		}else if(clienteBean.getTipoClienteEnum().getCodPe().equals("F")){
			clienteFisicaBuscado = consultaPersonaFisicaBackEnd.getClienteResponse(clienteBean.getIdInterna());
			nivel2_4.setCODESTRCTLGLORG("");
			nivel2_4.setCODCPCDADLGLIN(clienteFisicaBuscado.getCapacidadLegal());
			nivel2_4.setFECNCTOCONSTPE(0);
		}
		
		nivel1_1.setPERLCPDADLGLV(nivel2_4);
		
		nivel3_1.setIDEXTPE(clienteBean.getNumIdentificacion());
		nivel3_1.setNOMB50(clienteBean.getNombreCompleto());
		nivel3_1.setCODPE(clienteBean.getTipoClienteEnum().getCodPe());
		
		nivel3_2.setIDEXTPE(personaRelacionadaBean.getIdExterna());
		nivel3_2.setNOMB50(personaRelacionadaBean.getNombre());
		nivel3_2.setCODPE(tipoPersonaRelacionada);
		
		nivel2_5.setPERLPE1DS(nivel3_1);
		nivel2_5.setPERLPE2DS(nivel3_2);
		
		nivel1_1.setPERL2PEVALV(nivel2_5);
		
		contexto.setTRPEALTARL2PEDSEVTY(nivel1_1);
		
		nivel1_2.setIDINTERNOTERMTN(super.getTerminal());
		nivel1_2.setIDEMPLAUT("");
		nivel1_2.setNUMSEC(0);
		nivel1_2.setCODTX("PGE29MON");
		nivel1_2.setCODTXDI("");

		contexto.setSTDTRNIPARMV(nivel1_2);
		
		return contexto;
	}
	
	private EjecutarResult ejecutarWS(Ejecutar.ITRPEALTARL2PEDSTR contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AltaRelacionClientePersonaServicio2.class, contexto);
		}catch (NoControlableException nce){
			throw new NoControlableException("No se pudó invocar el servicio de"
					+ " alta de relación de persona - persona.", nce);
		}
		return respuesta;
	}
}
