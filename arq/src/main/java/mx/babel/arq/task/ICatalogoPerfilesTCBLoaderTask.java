package mx.babel.arq.task;

import java.util.List;

public interface ICatalogoPerfilesTCBLoaderTask extends ILoaderTask{	
	public List<String> getCatalogo(final String entidad);
}
