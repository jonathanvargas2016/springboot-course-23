package com.js.movies.servicio;

import com.js.movies.dao.PeliculaDao;
import com.js.movies.excepcion.ElementoNuloExcepcion;
import com.js.movies.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServicio {

    @Autowired
    private PeliculaDao peliculaDao;

    public boolean savePelicula(Pelicula pelicula) {

        if (pelicula == null) {
            throw new ElementoNuloExcepcion("Es necesario enviar todos los elementos");
        }
        return this.peliculaDao.savePelicula(pelicula);
    }

}
