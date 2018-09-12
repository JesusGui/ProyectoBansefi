package mx.babel.bansefi.banksystem.personas.backend;

import java.util.ArrayList;
import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;
import mx.babel.arq.comun.exceptions.ControlableException;
import mx.babel.arq.comun.exceptions.NoControlableException;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCatalogeBackend;
import mx.babel.bansefi.banksystem.base.backends.ConsultaCatalogoBackend;
import mx.babel.bansefi.banksystem.base.enums.CatalogoEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase que invoca los servicios de catalogo y cataloge
 * 
 * */
@Component
public class CatalogoRelacionesBackEnd {
	
	@Autowired
	private ConsultaCatalogoBackend consultaCatalogoBackend;

	@Autowired
	private ConsultaCatalogeBackend consultaCatalogeBackend;
	
	private List<CatalogoBean> listaCatalogo;

	public List<CatalogoBean> getListaCatalogo() {
		return listaCatalogo;
	}

	public void setListaCatalogo(List<CatalogoBean> listaCatalogo) {
		this.listaCatalogo = listaCatalogo;
	}
	
	/**
	 * Método que ejecuta la busqueda de catalogos.
	 * 
	 * @param tipoPersona
	 * 	Tipo de persona a buscar en los catalogos
	 * @return listaCatalogo
	 * 	Lista con los registros de catalogos dependiendo del tipo de persona ingresada.
	 * */
	public List<CatalogoBean> ejecutaBusquedaCatalogo(final String tipoPersona) throws NoControlableException, ControlableException{
		this.listaCatalogo = ejecutaBusqueda(tipoPersona);
		return listaCatalogo;
	}
	
	/**
	 * Método que ejecuta la consulta de los servicios de catalogo y cataloge en base al tipo de persona
	 * 
	 * @param tipoPersona
	 *  Tipo de persona a buscar en los catalogos
	 * @return nuevaLista
	 *  Lista con los valores consultados del catalogo en base al tipo de persona
	 * */
	private List<CatalogoBean> ejecutaBusqueda(final String tipoPersona) throws NoControlableException, ControlableException{
		List<CatalogoBean> listaCataloge = new ArrayList<CatalogoBean>();
		List<CatalogoBean> listaCatalogo = new ArrayList<CatalogoBean>();
		List<CatalogoBean> nuevaLista = new ArrayList<CatalogoBean>();
		listaCataloge = this.consultaCatalogeBackend.ejecutarTRN(CatalogoEnum.TP_PERS_RL_PERS, tipoPersona);
		listaCatalogo = this.consultaCatalogoBackend.ejecutarTRN(CatalogoEnum.TP_PERS_RL_PERS);
		for(CatalogoBean dataCataloge : listaCataloge){
			for(CatalogoBean dataCatalogo : listaCatalogo){
				if(dataCataloge.getClaveFila().equals(dataCatalogo.getClaveFila())){
					CatalogoBean catalogoBean = new CatalogoBean();
					catalogoBean.setClaveFila(dataCataloge.getClaveFila());
					catalogoBean.setContenido(dataCatalogo.getContenido());
					catalogoBean.setDescripcionC(dataCatalogo.getDescripcionC());
					catalogoBean.setDescripcionL(dataCatalogo.getDescripcionL());
					
					nuevaLista.add(catalogoBean);
					
					break;
				}
			}
		}
		return nuevaLista;
	}

}

