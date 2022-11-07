package com.js.movies.servicio;

import com.js.movies.dao.GeneroDao;
import com.js.movies.excepcion.ElementoNuloExcepcion;
import com.js.movies.modelo.Genero;
import com.js.movies.operacion.Operacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServicio {

    @Autowired
    private GeneroDao generoDao;

    public boolean saveGenero(Genero genero) {
        Operacion operacion = new Operacion();
        String codigoGenero = operacion.convertirGenero(genero.getNombreGenero());
        genero.setCodigoGenero(codigoGenero);

        if (genero.getNombreGenero() == null || genero.getNombreGenero().isBlank()) {
            throw new ElementoNuloExcepcion("El género es nulo o blanco");
        }
        return this.generoDao.saveGenero(genero);

    }

    public List<Genero> getGenero() {
        return this.generoDao.getAllGenero();
    }

    public Genero getGeneroId(Integer id) {
        if (id == null) {
            throw new ElementoNuloExcepcion("Id es nulo");
        }
        return this.generoDao.getGeneroId(id);
    }
}
