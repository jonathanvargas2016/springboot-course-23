package com.js.movies.servicio;

import com.js.movies.dao.PeliculaDao;
import com.js.movies.excepcion.ElementoNuloExcepcion;
import com.js.movies.modelo.DetalleCatalogo;
import com.js.movies.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaServicio {

    @Autowired
    private PeliculaDao peliculaDao;

    public boolean savePelicula(Pelicula pelicula, Integer idGenero, Integer idIdioma) {

        DetalleCatalogo genero = new DetalleCatalogo();
        genero.setId(idGenero);
        DetalleCatalogo idioma = new DetalleCatalogo();
        idioma.setId(idIdioma);
        pelicula.setIdGenero(genero);
        pelicula.setIdIdioma(idioma);

        System.out.println("pelicula: " + pelicula);


        if (pelicula == null || idGenero == null || idGenero < 0 || idIdioma == null || idIdioma < 0) {
            throw new ElementoNuloExcepcion("Es necesario enviar todos los elementos");
        }

        return this.peliculaDao.savePelicula(pelicula);
    }
//
//    public List<Pelicula> getPeliculaCatalogo(String tipo) {
//        List<Pelicula> peliculas = this.peliculaDao.getPelicula();
//        if (tipo.contains("vistas")) {
//
//            peliculas = peliculas.stream().
//                    filter(pelicula -> pelicula.getNumeroVistas() > 100).
//                    limit(12).collect(Collectors.toList());
//        }
//
//        if (tipo.contains("estrenos")) {
//            //peliculas = peliculas.stream()
//        }
//
//        return peliculas;
//    }
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
//    public List<Pelicula> getPeliculaGenero(Genero genero, Integer pagina, Integer cantidad) {
//
//        if (genero == null || pagina == null || pagina < 0 || cantidad == null || cantidad < 0) {
//            throw new ElementoNuloExcepcion("el genero, la pagina y la cantidad no deben ser nulo");
//        }
//
//        return this.peliculaDao.getPeliculaCategoria(genero, pagina, cantidad);
//
//    }

}
