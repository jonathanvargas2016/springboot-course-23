package com.js.movies.servicio;

import com.js.movies.dao.PeliculaDao;
import com.js.movies.dto.PeliculaDTO;
import com.js.movies.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaServicio {

    @Autowired
    private PeliculaDao peliculaDao;

    public String savePelicula(Pelicula pelicula) {

        System.out.println("pelicula: " + pelicula);
        if (pelicula == null) {
            return "La pelicula es nula";
        }
        return this.peliculaDao.savePelicula(pelicula);
    }

    public List<PeliculaDTO> getPeliculasRaiting() {
        List<PeliculaDTO> peliculas = this.peliculaDao.getPeliculasRaiting();
        return peliculas.stream().filter(pelicula -> pelicula.getRaiting() >= 4.0F).
                limit(12).toList();
    }

    //
//    public List<Pelicula> getPeliculaPaginado(Integer pagina, Integer cantidad) {
//
//        if (pagina == null || pagina < 0 || cantidad == null || cantidad < 0) {
//            throw new ElementoNuloExcepcion("La pagina o la cantidad no deben ser nulos");
//        }
//
//        return this.peliculaDao.getPeliculaPaginada(pagina, cantidad);
//    }
//
//    public Pelicula getPeliculaId(Integer id) {
//        if (id == null || id < 0) {
//            throw new ElementoNuloExcepcion("el id no debe ser nulo");
//        }
//        return this.peliculaDao.getPeliculaId(id);
//    }
//
//    public boolean deletePelicula(Integer id) {
//        if(id == null || id < 0){
//            throw new ElementoNuloExcepcion("el id no debe ser nulo");
//
//        }
//        this.peliculaDao.deletepelicula(id);
//        return true;
//    }
//
//
    public List<PeliculaDTO> getPeliculas(String descripcionGenero, Integer pagina, Integer cantidad) {
        if (descripcionGenero == null || pagina == null || pagina < 0 || cantidad == null || cantidad < 0) {
            return null;
        }
        return this.peliculaDao.getPeliculas(descripcionGenero, pagina, cantidad);

    }

}
