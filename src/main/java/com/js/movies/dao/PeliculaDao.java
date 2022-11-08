package com.js.movies.dao;

import com.js.movies.dao.interfaz.PeliculaJPARepository;
import com.js.movies.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PeliculaDao {

    @Autowired
    PeliculaJPARepository peliculaRepository;
    public Boolean savePelicula(Pelicula pelicula){
        Boolean respuesta = false;
        Pelicula resPelicula  = this.peliculaRepository.save(pelicula);
        if(resPelicula.getId() != null && resPelicula.getId() > 0){
            respuesta = true;
        }
        return respuesta;
    }

    public List<Pelicula> getPelicula(){
        return this.peliculaRepository.findAll();
    }

    public Pelicula getPeliculaId(Integer id){
        return this.peliculaRepository.findById(id).get();
    }

    public List<Pelicula> getPeliculaPaginada(Integer pagina, Integer cantidad){
        return this.peliculaRepository.findAll(PageRequest.of(pagina, cantidad)).toList();
    }

    public boolean deletepelicula(Integer id){
        this.peliculaRepository.deleteById(id);
        return true;
    }

//    public List<Pelicula> getPeliculaCategoria(Genero genero, Integer pagina, Integer cantidad) {
//        Pelicula plantilla = new Pelicula();
//        plantilla.setIdGenero(genero);
//        return this.peliculaRepository.findAll(Example.of(plantilla), PageRequest.of(pagina, cantidad)).toList();
//    }

}
