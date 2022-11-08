package com.js.movies.servicio;

import com.js.movies.dao.CatalogoDao;
import com.js.movies.excepcion.ElementoNuloExcepcion;
import com.js.movies.modelo.Catalogo;
import com.js.movies.operacion.Operacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoServicio {

    @Autowired
    private CatalogoDao catalogoDao;

    public Boolean saveGenero(Catalogo catalogo) {

        if (catalogo.getNombreCatalogo() == null || catalogo.getNombreCatalogo().isBlank()) {
            throw new ElementoNuloExcepcion("El catalogo es nulo o blanco");
        }
        return this.catalogoDao.saveCatalogo(catalogo);

    }

    public List<Catalogo> getCatalogos() {
        return this.catalogoDao.getAllCatalogos();
    }

    public Catalogo getGeneroId(Integer id) {
        if (id == null) {
            throw new ElementoNuloExcepcion("Id es nulo");
        }
        return this.catalogoDao.getCatalogoId(id);
    }
}
