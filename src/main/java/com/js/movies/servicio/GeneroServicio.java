package com.js.movies.servicio;

import com.js.movies.dao.GeneroDao;
import com.js.movies.modelo.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServicio {

    @Autowired
    private GeneroDao catalogoDao;

    public String saveGenero(Genero genero) {
        if (genero.getNombreGenero() == null || genero.getNombreGenero().isBlank()) {
            return "El genero es nulo o blanco";
        }
        return  this.catalogoDao.saveCatalogo(genero);
    }

    public List<Genero> getCatalogos() {
        return this.catalogoDao.getAllCatalogos();
    }

    public Genero getGeneroId(Integer id) {
        Genero respGenero = this.catalogoDao.getCatalogoId(id);
        return respGenero != null ? respGenero : null;
    }
}
