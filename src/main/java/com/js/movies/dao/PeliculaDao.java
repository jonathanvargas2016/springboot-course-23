package com.js.movies.dao;

import com.js.movies.dao.interfaz.PeliculaJPARepository;
import com.js.movies.modelo.Genero;
import com.js.movies.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PeliculaDao {

    @Autowired
    PeliculaJPARepository peliculaRepository;
    public boolean savePelicula(Pelicula pelicula){
        boolean respuesta = false;
        Pelicula resPelicula  = this.peliculaRepository.save(pelicula);
        if(resPelicula.getId() != null && resPelicula.getId() > 0){
            respuesta = true;
        }
        return respuesta;
    }

}
