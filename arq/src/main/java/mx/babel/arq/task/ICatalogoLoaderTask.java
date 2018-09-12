package mx.babel.arq.task;

import java.util.List;

import mx.babel.arq.catalogo.beans.CatalogoBean;

public interface ICatalogoLoaderTask extends ILoaderTask {
    public List<CatalogoBean> getCatalogo(final String catalogo);
}
