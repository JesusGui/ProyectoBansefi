package mx.babel.arq.task;

import java.util.Map;

public interface ILoaderTask {

    public void loadTask() throws Exception;
    public Map<String, Integer> getCantidadCatalogosMap();

}
