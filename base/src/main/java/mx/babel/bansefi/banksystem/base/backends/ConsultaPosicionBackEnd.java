package mx.babel.bansefi.banksystem.base.backends;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.arq.comun.utils.EstadoEnum;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.backends.BackEndBean;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonaFisicaBackEnd;
import mx.babel.bansefi.banksystem.base.backends.ConsultaPersonaMoralBackEnd;
import mx.babel.bansefi.banksystem.base.beans.ConceptoPosicionBean;
import mx.babel.bansefi.banksystem.base.beans.PosicionCuentaBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteBean;
import mx.babel.bansefi.banksystem.base.beans.domain.ClienteGrupoBean;
import mx.babel.bansefi.banksystem.base.beans.domain.CuentaBean;
import mx.babel.bansefi.banksystem.base.constants.CodTxConstants;
import mx.babel.bansefi.banksystem.base.webservices.consultaposicion.ConsultaIntegrantesGrupoServicio;
import mx.babel.bansefi.banksystem.base.webservices.consultaposicion.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.consultaposicion.EjecutarResult;
import mx.babel.bansefi.banksystem.base.webservices.consultaposicion.ResponseBansefi;
import mx.babel.bansefi.banksystem.base.webservices.consultaposicion.ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERCONCEPTOSLST.PERCONCEPTOSV.PERSALDOSV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Back end para consultas de integrantes de grupo.
 * 
 * @author mario.montesdeoca
 *
 */
@Component
public class ConsultaPosicionBackEnd extends BackEndBean{
	
	private static final long serialVersionUID = 4703146879020066496L;

    @Autowired
	ConsultaPersonaFisicaBackEnd clienteConsultaPersonaFisica;
    
    @Autowired
   	ConsultaPersonaMoralBackEnd clienteConsultaPersonaMoralBackEnd;
	
    @Autowired
    ServicioWebUtils servicioWebUtils;

	/**
	 * Método para definir la lista de integrantes de un grupo a través de un servicio web. 
	 * 
	 * @param grupoBean Grupo en el cual se definirá la lista
	 * @throws NoControlableException Excepción controlada de errores en front end
	 * @throws ControlableException Excepción no controlada de errores en host
	 */
	public void ejecutarTRN(ClienteGrupoBean grupoBean, CuentaBean cuentaBean)
			throws NoControlableException, ControlableException{
		if(grupoBean != null || cuentaBean != null){
			Ejecutar.ITRPECONSULTAPOSICION contexto = initPeticion(grupoBean,cuentaBean);
			
			EjecutarResult respuesta = ejecutarWS(contexto);
			
			super.verificaRespuesta(respuesta);
			
			getClientes(grupoBean, cuentaBean, respuesta.getResponseBansefi());
		}
	}
	
	/**
	 * Método que obtiene los integrantes de un grupo y los adiciona a la lista del grupo.
	 * 
	 * @param grupoBean Grupo en el que se adicionarán los integrantes
	 * @param response respuesta del servicio web
	 */
	private void getClientes(ClienteGrupoBean grupoBean, CuentaBean cuentaBean, ResponseBansefi response)
			throws NoControlableException, ControlableException{
		verificaResultado(response);
		if(verificaListaIntegrantes(response)){
			ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERPERSONASLST listaClientes = 
					response.getOTRPECONSULTAPOSICION().getTRPECONSULTAPOSICIONE().getPERPERSONASLST();

			ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERCONCEPTOSLST listaConceptos = 
					response.getOTRPECONSULTAPOSICION().getTRPECONSULTAPOSICIONE().getPERCONCEPTOSLST();

			List<ClienteBean> integrantes = new ArrayList<ClienteBean>();
//			List<ConceptoPosicionBean> posiciones = new ArrayList<ConceptoPosicionBean>();
			
			for (ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERPERSONASLST.PERPERSONASV 
					cliente : listaClientes.getPERPERSONASV()) {
				if(cliente.getIDINTERNOPE() != 0){
					try{
						integrantes.add(clienteConsultaPersonaFisica.getClienteResponse(cliente.getIDINTERNOPE()));
					}catch(ControlableException | NoControlableException ce){
						integrantes.add(clienteConsultaPersonaMoralBackEnd.ejecutarTRN(cliente.getIDINTERNOPE()));
					}
				}
			}
			
			PosicionCuentaBean posicion = new PosicionCuentaBean();
			posicion.setConceptos(new ArrayList<ConceptoPosicionBean>());
			
			ConceptoPosicionBean conceptoBean = null; 
			for (ResponseBansefi.OTRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.PERCONCEPTOSLST.PERCONCEPTOSV 
					concepto : listaConceptos.getPERCONCEPTOSV()) {
					conceptoBean = new ConceptoPosicionBean();
					
					if (concepto.getCODCAMPO()!=null) {
						conceptoBean.setCodDescripcion(concepto.getCODCAMPO().trim());
					}
					
					List<BigDecimal> importes = new ArrayList<BigDecimal>();
					boolean isZero = true;
					for (PERSALDOSV vSaldos : concepto.getPERSALDOSV())	{
						importes.add(vSaldos.getIMPSDO());
						if ( isZero && Double.doubleToRawLongBits(vSaldos.getIMPSDO().doubleValue())!=0 ) {
							isZero = false;
						}
					}
					
					// Solo se visualiza posición si es mayor o menor que 0 algún importe
					if (!isZero) {
						conceptoBean.setImportes(importes);
						posicion.getConceptos().add(conceptoBean);
					}
			}
			
			posicion.setIntervinientes(integrantes);
			posicion.setCliente(grupoBean);
			
			if (grupoBean!=null) {
				grupoBean.setPosicion(posicion);
				grupoBean.setIntegrantes(integrantes);
			} else {
				cuentaBean.setPosicion(posicion);
			}
			
		}
	}
	
	/**
	 * Función para inicializar los atributos del objeto de peticíon al servicio web.
	 * 
	 * @param idInterno Id del grupo
	 * @return Objeto de petición al web service
	 */
	private Ejecutar.ITRPECONSULTAPOSICION initPeticion(ClienteGrupoBean grupoBean, CuentaBean cuentaBean) {
		Ejecutar.ITRPECONSULTAPOSICION contexto = new Ejecutar.ITRPECONSULTAPOSICION();
		Ejecutar.ITRPECONSULTAPOSICION.STDTRNIPARMV contextoEntrada = 
				new Ejecutar.ITRPECONSULTAPOSICION.STDTRNIPARMV();
		Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE cuerpoContexto =
				new Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE();

		if (grupoBean!=null) {
			Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.GRGRPP grupo = 
					new Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.GRGRPP();
			
			grupo.setCODNRBEEN(super.getEntidad());
			grupo.setIDEXTGR(grupoBean.getNumIdentificacion());
			grupo.setCODGRP(grupoBean.getTipoGrupo());
			
			cuerpoContexto.setGRGRPP(grupo);
		} else {
			Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.ACACP cuenta = 
					new Ejecutar.ITRPECONSULTAPOSICION.TRPECONSULTAPOSICIONE.ACACP();
			
			cuenta.setCODNRBEEN(super.getEntidad());
//			cuenta.setCODCENTUO(cuentaBean.getCentro());
			cuenta.setNUMSECAC(cuentaBean.getNumeroCuenta());
			
			cuerpoContexto.setACACP(cuenta);
		}
		
		contextoEntrada.setIDINTERNOTERMTN(super.getTerminal());
		contextoEntrada.setCODTX(CodTxConstants.COD_TX_TR_PE_CONSULTA_POSICION_TRN);
		contexto.setSTDTRNIPARMV(contextoEntrada);
		contexto.setTRPECONSULTAPOSICIONE(cuerpoContexto);
		super.initialize(contexto);
		
		return contexto;
	}
	
	/**
	 * Función para invocar al servicio web y obtener su respuesta.
	 * 
	 * @param contexto Objeto de petición al servicio web
	 * @return La respuesta del servicio web.
	 */
	private EjecutarResult ejecutarWS(Ejecutar.ITRPECONSULTAPOSICION contexto){
		EjecutarResult respuesta = null;
		
		try{
			respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(
						ConsultaIntegrantesGrupoServicio.class, contexto);
		}catch(NoControlableException e){
			throw new NoControlableException("Error al invocar servicio web de consulta de "
					+ "integrantes de grupo.", e);
		}
		return respuesta;
	}
	
	
	/**
	 * Función que valida que la respuesta Bansefi contenga una lista con los 
	 * integrantes del grupo.
	 * 
	 * @param response Respuesta Bansefi con lista de integrantes del grupo
	 * @return <code>true</code> si la respuesta Bansefi contiene una lista de integrantes
	 */
	private Boolean verificaListaIntegrantes(ResponseBansefi response){
		Boolean noNulo = false;
		if(response!= null && response.getOTRPECONSULTAPOSICION() != null &&
				response.getOTRPECONSULTAPOSICION().getTRPECONSULTAPOSICIONE() != null &&
				response.getOTRPECONSULTAPOSICION().getTRPECONSULTAPOSICIONE().getPERPERSONASLST() != null){
			noNulo = true;
		}
		return noNulo;
	}
	
	/**
	 * Método que verifica los códigos de respuesta entregados por el servicio web.
	 * 
	 * @param response Respuesta Bansefi con mensajes
	 * @throws NoControlableException Excepción controlada de errores en front end
	 * @throws ControlableException Excepción no controlada de errores en host
	 */
	private void verificaResultado(ResponseBansefi response) 
			throws NoControlableException, ControlableException{
		int codigo = 1;
		if(response.getOTRPECONSULTAPOSICION().getRTRNCD() != 1){
			for (ResponseBansefi.OTRPECONSULTAPOSICION.STDTRNMSJPARMV mensaje : 
				response.getOTRPECONSULTAPOSICION().getSTDTRNMSJPARMV()) {
				codigo = mensaje.getTEXTCODE();
			}
		}
		if (codigo != 1) {
			EstadoEnum.mensajesError("trn", codigo);
		} 
	}
}
