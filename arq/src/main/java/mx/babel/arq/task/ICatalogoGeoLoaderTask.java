package mx.babel.arq.task;

import java.util.List;
import java.util.Map;

import mx.babel.arq.catalogo.beans.CatalogoBean;

public interface ICatalogoGeoLoaderTask extends ILoaderTask {
	
	public List<CatalogoBean> getCatalogo(final String catalogo);
    public Map<String, Integer> getCantidadCatalogosMap();
	void ejecutarCargaDesdeWS() throws Exception;
	
}
