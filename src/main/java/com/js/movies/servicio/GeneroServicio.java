package com.js.movies.servicio;

import com.js.movies.dao.GeneroDao;
import com.js.movies.modelo.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServicio {

    @Autowired
    private GeneroDao generoDao;

    public boolean saveGenero(Genero genero) {
        boolean respuesta = false;
        if (genero.getNombreGenero() == null || genero.getNombreGenero().isBlank()) {
            throw new RuntimeException("El género es nulo o blanco");
        }else {
            respuesta = this.generoDao.saveGenero(genero);
        }
        return respuesta;
    }

    public List<Genero> getGenero() {
        return this.generoDao.getAllGenero();
    }
}
