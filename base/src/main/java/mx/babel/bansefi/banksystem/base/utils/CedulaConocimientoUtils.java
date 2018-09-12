package mx.babel.bansefi.banksystem.base.utils;

import java.util.ArrayList;

import mx.babel.bansefi.banksystem.base.backends.ConsultaAccionistasFuncionariosBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaDonativosMoralBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaGrupoFilialMoralBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPerfilTransaccionalMoralBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPerfilTransaccionalMoralEstimBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaReferenciasBancariasComercialesBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaReferenciasPersonalesRiesgoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaUsoCuentaMoralBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultaperfiltransaccional.ConsultaPerfilTransaccionalBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultarelacioncargopublico.ConsultaRelacionCargoPublicoBackEnd;
import mx.babel.bansefi.banksystem.base.backends.consultausocuenta.ConsultaUsoCuentaBackEnd;
import mx.babel.bansefi.banksystem.base.beans.RelacionesClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.PersonaMoralAccionistaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ReferenciaPersonalBean;
import mx.babel.bansefi.banksystem.base.beans.domain.RiesgoClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TransaccionalidadBean;
import mx.babel.bansefi.banksystem.base.beans.domain.UsoCuentaBean;
import mx.babel.bansefi.banksystem.base.enums.EstadoListadosEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase con utilidades para la consulta completa de la cedula de conocimiento de
 * una persona
 * @author javier.martinnino
 *
 */
@Component
public class CedulaConocimientoUtils {

    @Autowired
    private ConsultaUsoCuentaBackEnd consultaUsoCuenta;
    
    @Autowired
    private ConsultaPerfilTransaccionalBackEnd consultaPerfilTransaccionalBackEnd;
    
    @Autowired
    private ConsultaRelacionCargoPublicoBackEnd consultaRelacionCargoPublicoBackEnd;
    
    @Autowired
    private ConsultaReferenciasPersonalesRiesgoBackEnd consultaReferenciasPersonalesRiesgoBackEnd;
    
    // Servicios de persona moral
    
    @Autowired
	private ConsultaPerfilTransaccionalMoralBackEnd consultaPerfilTransaccionalMoralBackEnd;
	
	@Autowired
	private ConsultaPerfilTransaccionalMoralEstimBackEnd consultaPerfilTransaccionalMoralEstimBackEnd;

	@Autowired
	private ConsultaUsoCuentaMoralBackEnd consultaUsoCuentaMoralBackEnd;
	
	@Autowired
	private ConsultaDonativosMoralBackEnd consultaDonativos;
	
	@Autowired 
	private ConsultaGrupoFilialMoralBackEnd consultaGrupoFilial;
    
	@Autowired 
	private ConsultaAccionistasFuncionariosBackEnd consultaAccionistaFuncionarios;

	@Autowired
	private ConsultaReferenciasBancariasComercialesBackEnd consultaRBancariaComercial;

	
    /**
     * Consulta la cedula de conocimiento de un cliente a partir de su idInterna
     * @param idInterna del cliente
     * @return ClientePersonaFisicaBean
     */
    public ClientePersonaFisicaBean consultaCedulaConocimiento (Integer idInterna){
        
        ClientePersonaFisicaBean persona = new ClientePersonaFisicaBean();
        
        RiesgoClientePersonaFisicaBean riesgo = new RiesgoClientePersonaFisicaBean();
        riesgo.setReferenciasPersonales(new ArrayList<ReferenciaPersonalBean>());
        
        TransaccionalidadBean transaccionalidad = new TransaccionalidadBean();
        transaccionalidad.setTransaccionalidad(new ArrayList<String>());
        
        UsoCuentaBean usoCuenta = new UsoCuentaBean();
        usoCuenta.setUsos(new ArrayList<String>());
        
        UsoCuentaBean usoCuentaAux = new UsoCuentaBean();
        usoCuenta.setUsos(new ArrayList<String>());
        
        persona.setTransaccionalidad(transaccionalidad);
        persona.setUsoCuenta(usoCuenta);
        
        transaccionalidad = this.consultaPerfilTransaccionalBackEnd.ejecutarTRN(idInterna);
        if (transaccionalidad != null){
        	persona.setTransaccionalidad(transaccionalidad);
        }
                
        usoCuenta = this.consultaUsoCuenta.ejecutarTRN(idInterna);        
        usoCuentaAux = this.consultaRelacionCargoPublicoBackEnd.ejecutarTRN(idInterna);
        
        if (usoCuenta != null){
        	if (usoCuentaAux !=null){
	        	usoCuenta.setFuncionarioPublico(usoCuentaAux.getFuncionarioPublico());
	        	usoCuenta.setFuncionarioPublicoCargo(usoCuentaAux.getFuncionarioPublicoCargo());
	        	usoCuenta.setAsociadoFuncionarioPublico(usoCuentaAux.getAsociadoFuncionarioPublico());
	        	usoCuenta.setAsociadoFuncionarioPublicoCargo(usoCuentaAux.getAsociadoFuncionarioPublicoCargo());
	        	usoCuenta.setAsociadoFuncionarioPublicoNombre(usoCuentaAux.getAsociadoFuncionarioPublicoNombre());
        	}
        	persona.setUsoCuenta(usoCuenta);
        }else if (usoCuentaAux !=null){
        	persona.setUsoCuenta(usoCuentaAux);
        }
        
        if (persona.getUsoCuenta().getUsos() == null){
        	persona.getUsoCuenta().setUsos(new ArrayList<String>()); 
        }
        
        // Se obtienen datos de riesgo del cliente
        
        persona.setEsClienteRiesgo(this.consultaRelacionCargoPublicoBackEnd.ejecutarTRN(idInterna, true));
        
        riesgo = this.consultaReferenciasPersonalesRiesgoBackEnd.ejecutarTRN(idInterna);
        if (riesgo != null){
        	persona.setDatosRiesgo(riesgo);
        }
        
        return persona;
        
    }
    
    /**
     * Consulta la cedula de conocimiento de un cliente persona Moral a partir de su idInterna
     * @param idInterna del cliente
     * @return ClientePersonaMoralBean
     */
    public ClientePersonaMoralBean consultaCedulaConocimientoMoral (Integer idInterna){
        
        ClientePersonaMoralBean personaMoral = new ClientePersonaMoralBean();
        
        TransaccionalidadBean transaccionalidad = new TransaccionalidadBean();
        transaccionalidad.setTransaccionalidad(new ArrayList<String>());
        
        UsoCuentaBean usoCuenta = new UsoCuentaBean();
        usoCuenta.setUsos(new ArrayList<String>());
        
        UsoCuentaBean usoCuentaAux = new UsoCuentaBean();
        usoCuenta.setUsos(new ArrayList<String>());
        
        personaMoral.setTransaccionalidad(transaccionalidad);
        personaMoral.setUsoCuenta(usoCuenta);
        
        transaccionalidad = this.consultaPerfilTransaccionalMoralEstimBackEnd.ejecutarTRN(idInterna);
        if (transaccionalidad != null){
        	personaMoral.setTransaccionalidad(transaccionalidad);
        }
                
        usoCuenta = this.consultaUsoCuentaMoralBackEnd.ejecutarTRN(idInterna);        
        usoCuentaAux = this.consultaPerfilTransaccionalMoralBackEnd.ejecutarTRN(idInterna);
        
        if (usoCuenta != null){
        	if (usoCuentaAux !=null){
	        	usoCuenta.setFuncionarioPublico(usoCuentaAux.getFuncionarioPublico());
	        	usoCuenta.setFuncionarioPublicoCargo(usoCuentaAux.getFuncionarioPublicoCargo());
	        	usoCuenta.setAsociadoFuncionarioPublico(usoCuentaAux.getAsociadoFuncionarioPublico());
	        	usoCuenta.setAsociadoFuncionarioPublicoCargo(usoCuentaAux.getAsociadoFuncionarioPublicoCargo());
	        	usoCuenta.setAsociadoFuncionarioPublicoNombre(usoCuentaAux.getAsociadoFuncionarioPublicoNombre());
        	}
        	personaMoral.setUsoCuenta(usoCuenta);
        }else if (usoCuentaAux !=null){
        	personaMoral.setUsoCuenta(usoCuentaAux);
        }
        
        if (personaMoral.getUsoCuenta().getUsos() == null){
        	personaMoral.getUsoCuenta().setUsos(new ArrayList<String>()); 
        }
        
        // Se obtienen datos de riesgo de la persona Moral
        
        personaMoral.setEsClienteRiesgo(this.consultaPerfilTransaccionalMoralBackEnd.ejecutarTRN(idInterna, true));
		        
        personaMoral.setDonativosBean(this.consultaDonativos.ejecutarTRN(idInterna));
		personaMoral.setGrupoFilialBean(this.consultaGrupoFilial.ejecutarTRN(idInterna));
		personaMoral.setRelacionesClienteRiesgo(this.consultaAccionistaFuncionarios.ejecutarTRN(idInterna));
		
		// Incluimos el minimo de dos accionistas para personas morales
		while (personaMoral.getRelacionesClienteRiesgo().getAccionistas().size()<2){
			PersonaMoralAccionistaBean accionista = new PersonaMoralAccionistaBean();
			accionista.setEstado(EstadoListadosEnum.NUEVO);
			personaMoral.getRelacionesClienteRiesgo().getAccionistas().add(accionista);
		}
		
		RelacionesClienteBean refBancariasComerciales = this.consultaRBancariaComercial.ejecutarTRN(idInterna);			
		personaMoral.getRelacionesClienteRiesgo().setReferenciasBancarias(refBancariasComerciales.getReferenciasBancarias());			
		personaMoral.getRelacionesClienteRiesgo().setReferenciasComerciales(refBancariasComerciales.getReferenciasComerciales());
        
        return personaMoral;
        
    }
    
}
