package mx.babel.bansefi.banksystem.base.backends;

import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.utils.EstadoEnum;
import mx.babel.arq.comun.utils.ServicioWebUtils;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;
import mx.babel.bansefi.banksystem.base.utils.CatalogoUtils;
import mx.babel.bansefi.banksystem.base.webservices.abcperfilempleadosservicio.AbcPerfilEmpleadosServicio;
import mx.babel.bansefi.banksystem.base.webservices.abcperfilempleadosservicio.Ejecutar;
import mx.babel.bansefi.banksystem.base.webservices.abcperfilempleadosservicio.EjecutarResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Backend para el servicio de consulta/modificacion/alta de perfiles de empleados.
 * 
 * @author javier.martinnino
 * 
 */
@Component
public class AbcPerfilEmpleadoBackEnd extends BackEndBean {

	private static final long serialVersionUID = -1821614354936799363L;

	private static final String CONSULTA = "C";
	private static final String ASIGNACION = "P";
	
	@Autowired
	private ServicioWebUtils servicioWebUtils;
	
	@Autowired
	private CatalogoUtils catalogoUtils;
	
	/**
	 * Método que ejecuta el servicio de consulta de perfiles de empleados
	 * 
	 * @param idEmpleado el identificador del empleado a consultar
	 * @return String con el codigo del perfil del empleado consultado.
	 */
	public String ejecutarWS(String idEmpleado){
		
		String codigoPerfil="";
		
		Ejecutar contextoEntrada = new Ejecutar();
		
		contextoEntrada.setUSERHEADER(super.getUsuarioId());
		contextoEntrada.setPASSHEADER(super.getPassword());
		contextoEntrada.setIPHEADER(super.getIp());
		contextoEntrada.setENTIDAD(super.getEntidad());
		contextoEntrada.setTERMINAL(super.getTerminal());
		
		// Parametrizamos la opcion de consulta y el usuario a consultar
		contextoEntrada.setACCION(AbcPerfilEmpleadoBackEnd.CONSULTA);
		contextoEntrada.setUSUARIO(idEmpleado);
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AbcPerfilEmpleadosServicio.class,
				contextoEntrada.getACCION(), contextoEntrada.getUSUARIO(),contextoEntrada.getENTIDAD(), contextoEntrada.getPERFIL(),contextoEntrada.getTERMINAL());

		int codigo = Integer.parseInt(respuesta.getESTATUS());
		
		// TODO: Tratamiento de errores.
		if (codigo != 0) {
			if(!("ARQE153").equals(respuesta.getCODIGO().trim()) && !("ARQE118").equals(respuesta.getCODIGO().trim())){
				EstadoEnum.mensajesError("trn", codigo);
			}
			
		} else {
			String descPerfil = respuesta.getResponseBansefi().getPERFIL().trim();
			List<CatalogoBean> perfilesNSS = this.catalogoUtils.getCatalogo(CatalogoEnum.PERFILES_NSS);
			boolean encontrado = false;
			for (int i=0; i< perfilesNSS.size() && !encontrado;i++){
				if (descPerfil.equals(perfilesNSS.get(i).getDescripcionL())){
					codigoPerfil = perfilesNSS.get(i).getClaveFila();
					encontrado = true;
				}
			}
		}		
		return codigoPerfil;
	}
	
	/**
	 * Método que ejecuta el servicio de consulta de perfiles de empleados
	 * 
	 * @param idEmpleado el identificador del empleado a asignar perfil
	 * @param codPerfil codigo del perfil a asignar al empleado
	 * @return Integer con el resultado de la operacion.
	 */
	public Integer ejecutarWS(String idEmpleado, String codPerfil){
		
		String descPerfil= this.catalogoUtils.getCatalogoDesc(CatalogoEnum.PERFILES_NSS, codPerfil);
		
		Ejecutar contextoEntrada = new Ejecutar();
		
		contextoEntrada.setUSERHEADER(super.getUsuarioId());
		contextoEntrada.setPASSHEADER(super.getPassword());
		contextoEntrada.setIPHEADER(super.getIp());
		contextoEntrada.setENTIDAD(super.getEntidad());
		contextoEntrada.setTERMINAL(super.getTerminal());
		
		// Parametrizamos la opcion de asignacion, el empleado a asignar el perfil, y el perfil asignado
		contextoEntrada.setACCION(AbcPerfilEmpleadoBackEnd.ASIGNACION);
		contextoEntrada.setUSUARIO(idEmpleado);
		contextoEntrada.setPERFIL(descPerfil);
		
		// Se ejecuta el WebService correspondiente
		EjecutarResult respuesta = (EjecutarResult) servicioWebUtils.ejecutarWS(AbcPerfilEmpleadosServicio.class,
				contextoEntrada.getACCION(), contextoEntrada.getUSUARIO(),contextoEntrada.getENTIDAD(), contextoEntrada.getPERFIL(),contextoEntrada.getTERMINAL());

		int codigo = Integer.parseInt(respuesta.getESTATUS());
		
		// TODO: Tratamiento de errores.
		if (codigo != 0) {
			if(!("ARQE153").equals(respuesta.getCODIGO().trim()) && !("ARQE118").equals(respuesta.getCODIGO().trim())){
				EstadoEnum.mensajesError("trn", codigo);
			}
		}		
		return codigo;
	}
}