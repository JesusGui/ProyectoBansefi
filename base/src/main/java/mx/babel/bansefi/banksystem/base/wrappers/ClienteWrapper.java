package mx.babel.bansefi.banksystem.base.wrappers;

import mx.babel.bansefi.banksystem.base.beans.DocumentoPersonaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteGrupoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaFisicaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClientePersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DocumentoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.DonativosPersonaMoralBean;
import mx.babel.bansefi.banksystem.base.beans.domain.TipoCliente;
import mx.babel.bansefi.banksystem.base.beans.domain.TransaccionalidadBean;
import mx.babel.bansefi.banksystem.base.beans.domain.UsoCuentaBean;
import mx.babel.bansefi.banksystem.base.webservices.altaperfiltransaccional.Ejecutar.IPEALTAINDVPERFTRANT.PEALTAINDVPERFTRANEV;
import mx.babel.bansefi.banksystem.base.webservices.busquedaidinterna.ResponseBansefi.OTRPECLCBDACNSTRNO.TRPECLCBDACNSEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultacuentascliente.ResponseBansefi.OTRCONSULTARPPANT5TR.TRCONSULTARPPANT5EVT.RPPERSRLACE;
import mx.babel.bansefi.banksystem.base.webservices.consultadocumentos.ResponseBansefi.OTRDCLSCNSTRNO.TRDCLSCNSEVTZ.TRDCLSCNSEVTV;
import mx.babel.bansefi.banksystem.base.webservices.consultadonativosmoral.ResponseBansefi.OTRCNSINFFINDNTTRNO.TRCNSINFFINDNTEVTZ.PEORGDNTVSV;
import mx.babel.bansefi.banksystem.base.webservices.consultagrupo.ResponseBansefi.OTRGRCONSTRNO.TRGRCONSEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultaperfiltransaccionalmoralestim.ResponseBansefi.OPECONSTRANESTIMTRNO.PECONSTRANESTIMEVTZ;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonafisica.ResponseBansefi.OTRPECONSINDVTRNO.TRPECONSINDVEVTZ.PECONSINDVOBJTRDV;
import mx.babel.bansefi.banksystem.base.webservices.consultapersonamoral.ResponseBansefi.OTRPECONSORGTRNO.TRPECONSORGEVTZ.PECONSORGOBJTRDV;
import mx.babel.bansefi.banksystem.base.webservices.consultarecursoscuentamoral.ResponseBansefi.OPECONSORGRECCTADST.PECONSORGRECCTADSEV.PEPERSRCSOSCTAE;
import mx.babel.bansefi.banksystem.base.webservices.consultausoscuentamoral.ResponseBansefi.OPECONSORGUSOCTATRN.PECONSORGUSOCTAEVTZ.PEPERSUSOCTAE;
import mx.babel.bansefi.banksystem.base.webservices.modificacionperfiltransaccional.Ejecutar.IPEMODIINDVPERFTRANT.PEMODIINDVPERFTRANEV;
import mx.babel.bansefi.banksystem.base.webservices.validaidinterna.ResponseBansefi.OTRCONSMINIMAPERSONAT.TRCONSMINIMAPERSONAEV;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean encargado de realizar mapeos entre respuestas de webservices hacia beans relacionados a clientes.
 *
 * @author mario.montesdeoca
 *
 */
@Component
public class ClienteWrapper {

	/**
	 * Asignación de bean de mapeo
	 */
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	/**
	 * Función responsable de  mapeo entre respuesta de webservice TR_PE_CONS_INDC_TRN
	 * y ClientePersonaFisicaBean
	 *
	 * @param cliente objeto respuesta del webservice TR_PE_CONS_INDC_TRN
	 * @return bean ClientePersonaFisicaBean
	 */
	public ClientePersonaFisicaBean wrappBean(final PECONSINDVOBJTRDV cliente){
		final Mapper mapper = dozerBeanMapper;
		return mapper.map(cliente, ClientePersonaFisicaBean.class,"clientePersonaFisica");
	}

	/**
	 * Función responsable de  mapeo entre respuesta de webservice TR_PE_CONS_ORG_TRN
	 * y ClientePersonaMoralBean
	 *
	 * @param cliente objeto respuesta del webservice TR_PE_CONS_ORG_TRN
	 * @return bean ClientePersonaMoralBean
	 */
	public ClientePersonaMoralBean wrappBean(final PECONSORGOBJTRDV cliente){
		final Mapper mapper = dozerBeanMapper;
		return mapper.map(cliente, ClientePersonaMoralBean.class,"clientePersonaMoral");
	}

	/**
	 * Función responsable del mapeo entre respuesta de webservice TR_GR_CONS_TRN
	 * y ClienteGrupoBean
	 *
	 * @param cliente objeto respuesta del webservice TR_GR_CONS_TRN
	 * @return bean ClienteGrupoBean
	 */
	public ClienteGrupoBean wrappBean(final TRGRCONSEVTZ cliente){
		final Mapper mapper = dozerBeanMapper;
		return mapper.map(cliente, ClienteGrupoBean.class,"clienteGrupo");
	}

	/**
	 * Función responsable del mapeo entre respuesta de webservice TR_DC_LS_CNS_TRN
	 * y DomicilioBean
	 *
	 * @param cliente objeto respuesta del webservice TR_DC_LS_CNS_TRN
	 * @return bean DocumentoBean
	 */
	public DocumentoBean wrappBean(final TRDCLSCNSEVTV documento){
		final Mapper mapper = dozerBeanMapper;
		return mapper.map(documento, DocumentoBean.class, "documento");
	}

	/**
	 * Función responsable del mapeo entre respuesta de webservice TR_CONS_MINIMA_PERSONA_TRN
	 * y DomicilioBean
	 *
	 * @param cliente objeto respuesta del webservice TR_CONS_MINIMA_PERSONA_TRN
	 * @return bean DocumentoBean
	 */
	public ClientePersonaFisicaBean wrappBean(final TRCONSMINIMAPERSONAEV cliente){
		final Mapper mapper = dozerBeanMapper;
		return mapper.map(cliente, ClientePersonaFisicaBean.class, "validaIdInterna");
	}

	/**
	 * Función responsable del mapeo entre respuesta de webservice TR_CONSULTA_RP_PANT_5_TRN
	 * y CuentaBusquedaBean
	 *
	 * @param cuenta objeto respuesta del webservice TR_CONS_DOMIC_PPAL_TRNO
	 * @return bean CuentaBusquedaBean
	 */
	public CuentaClienteBean wrappbean(final RPPERSRLACE cuenta){
		final Mapper mapper = dozerBeanMapper;
		return mapper.map(cuenta, CuentaClienteBean.class, "cuenta");
	}

	/**
     * Función responsable del mapeo entre respuesta de webservice TR_CONS_MINIMA_PERSONA_TRN
     * y DomicilioBean
     *
     * @param cliente objeto respuesta del webservice TR_CONS_MINIMA_PERSONA_TRN
     * @return bean DocumentoBean
     */
    public ClienteBean wrappBean(final TRPECLCBDACNSEVTZ cliente){
        final Mapper mapper = dozerBeanMapper;
        final ClienteBean clienteBean = mapper.map(cliente, ClienteBean.class, "consultaExistenciaIdInterno");

        if(StringUtils.isNotBlank(cliente.getCODPE()) &&
                StringUtils.equals(cliente.getCODPE().trim(), TipoCliente.PERSONA_FISICA.getCodPe())){
            clienteBean.setTipoClienteEnum(TipoCliente.PERSONA_FISICA);
        }else{
            clienteBean.setTipoClienteEnum(TipoCliente.PERSONA_MORAL);
        }

        return clienteBean;
    }
    
    /**
	 * Función responsable de  mapeo entre ClientePersonaFisicaBean y PE_ALTA_INDV_PERF_TRAN_TRN
	 * 
	 * @param cliente bean respuesta - ClientePersonaFisicaBean
	 * @return cliente objeto entrada del webservice PE_ALTA_INDV_PERF_TRAN_TRN
	 */
	public PEALTAINDVPERFTRANEV wrappBeanAltaClientePerfTrans(ClientePersonaFisicaBean cliente){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(cliente, PEALTAINDVPERFTRANEV.class,"altaClientePerfilTransaccional");
	}
	
	/**
	 * Función responsable de mapeo entre ClientePersonaFisicaBean y PE_MODI_INDV_PERF_TRAN_TRN
	 * 
	 * @param cliente bean respuesta - ClientePersonaFisicaBean
	 * @return cliente objeto entrada del webservice PE_MODI_INDV_PERF_TRAN_TRN
	 */
	public PEMODIINDVPERFTRANEV wrappBeanModifClientePerfTrans(ClientePersonaFisicaBean cliente){
		Mapper mapper = dozerBeanMapper;
		return mapper.map(cliente, PEMODIINDVPERFTRANEV.class,"modificacionClientePerfilTransaccional");
	}

	/**
	 * 
	 * @param personaMoralBean
	 * @return
	 */
	public TransaccionalidadBean wrappConsultarPerfilTransaccionalEstim (PECONSTRANESTIMEVTZ response) {
		Mapper mapper = dozerBeanMapper;
		return mapper.map(response, TransaccionalidadBean.class, "consultarPerfilTransaccionalmoralEstim");
	}
	
	public DonativosPersonaMoralBean wrappDonativos(PEORGDNTVSV donativos) {
	   	Mapper mapper = dozerBeanMapper;
	   	return mapper.map(donativos, DonativosPersonaMoralBean.class, "donativosMoral");
	}
	
	 /**
     * Función responsable de mapeo entre respuesta de webservice PE_CONS_INDV_USO_CTA_TRN
     * y UsoCuentaBean
     * 
     * @param usoCuenta objeto respuesta del webservice PE_CONS_INDV_USO_CTA_TRN
     * @return bean UsoCuentaBean
     */
    public UsoCuentaBean wrappUsoCuenta(PEPERSUSOCTAE usoCuenta){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(usoCuenta, UsoCuentaBean.class,"usocuentaMoral");
    }
    
    public ClientePersonaMoralBean wrappRecursosCuenta(PEPERSRCSOSCTAE usoCuenta){
        Mapper mapper = dozerBeanMapper;
        return mapper.map(usoCuenta, ClientePersonaMoralBean.class,"recursosCuentaMoral");
    }
    
    /**
     * Función responsable de mapeo entre objeto de salida del WS de consulta
     * Lista documentos y el bean DocumentoPersonasBean
     * 
     * 
     * @param documento objeto respuesta del webservice TR_DC_LS_CNS_TRN
     * @return bean DocumentoPersonaBean
     */
    public DocumentoPersonaBean wrappDocumentoListaBean(mx.babel.bansefi.banksystem.base.webservices.consultalistadocumentos.ResponseBansefi.OTRDCLSCNSTRNO.TRDCLSCNSEVTZ.TRDCLSCNSEVTV documento) {
        Mapper mapper = dozerBeanMapper;
        return mapper.map(documento, DocumentoPersonaBean.class,
                "documentoPersonaLista");
    }
}
