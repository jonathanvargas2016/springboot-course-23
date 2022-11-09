package com.js.movies.dao;

import com.js.movies.dao.interfaz.PeliculaJPARepository;
import com.js.movies.dto.PeliculaDTO;
import com.js.movies.modelo.Genero;
import com.js.movies.modelo.PagosEvento;
import com.js.movies.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PeliculaDao {

    @Autowired
    PeliculaJPARepository peliculaRepository;

    public String savePelicula(Pelicula pelicula) {
        String respuesta = "No se pudo guardar la pelicula";
        Pelicula resPelicula = this.peliculaRepository.save(pelicula);
        if (resPelicula.getId() != null && resPelicula.getId() > 0) {
            respuesta = "OK";
        }
        return respuesta;
    }

    public List<PeliculaDTO> getPeliculas(String descripcionGenero, Integer pagina, Integer cantidad, Integer plan) {
        Pelicula plantilla = new Pelicula();
        Genero genero = new Genero();
        genero.setDescripcion(descripcionGenero);
        PagosEvento pagosEvento = new PagosEvento();
        pagosEvento.setId(plan);
        plantilla.setIdPagoEvento(pagosEvento);
        plantilla.setIdGenero(genero);
        List<PeliculaDTO> peliculas = new ArrayList<>();
        for (Pelicula pelicula : this.peliculaRepository.findAll(Example.of(plantilla), PageRequest.of(pagina, cantidad, Sort.by(Sort.Direction.DESC, "calificacion")))) {
            peliculas.add(new PeliculaDTO(pelicula.getId(), pelicula.getIdPagoEvento().getId() ,pelicula.getIdGenero().getDescripcion(),
                    pelicula.getNombre(), pelicula.getDuracion(),
                    pelicula.getResumen(), pelicula.getIdioma(), pelicula.getEstado(), pelicula.getCalificacion()));
        }
        return peliculas;
    }


    public List<PeliculaDTO> getPeliculasCalificacion(){
        List<PeliculaDTO> peliculas = new ArrayList<>();
        for (Pelicula pelicula : this.peliculaRepository.findAll()) {
            peliculas.add(new PeliculaDTO(pelicula.getId(), null ,pelicula.getIdGenero().getDescripcion(),
                    pelicula.getNombre(), pelicula.getDuracion(),
                    pelicula.getResumen(), pelicula.getIdioma(), pelicula.getEstado(), pelicula.getCalificacion()));
        }
        return peliculas;
    }
}
