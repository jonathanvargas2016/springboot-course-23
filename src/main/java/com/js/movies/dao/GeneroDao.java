package com.js.movies.dao;

import com.js.movies.dao.interfaz.GeneroJPARepository;
import com.js.movies.modelo.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GeneroDao {

    @Autowired
    private GeneroJPARepository generoRepository;

    public boolean saveGenero(Genero genero){
        boolean respuesta = false;
        Genero respGenero = this.generoRepository.save(genero);
        if(respGenero.getId() != null && respGenero.getId() > 0){
            respuesta = true;
        }
        return respuesta;
    }

    public List<Genero> getAllGenero(){
        return this.generoRepository.findAll();
    }

    public boolean deleteGenero(Genero genero){
        this.generoRepository.deleteById(genero.getId());
        return true;
    }
}
