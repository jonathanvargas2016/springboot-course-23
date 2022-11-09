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

    public String saveCatalogo(Genero genero){
        String respuesta = "";
        Genero respGenero = this.generoRepository.save(genero);
        if(respGenero.getId() != null && respGenero.getId() > 0){
            respuesta = "OK";
        }else {
            respuesta = "No se pudo guardar el genero";
        }
        return respuesta;
    }

    public List<Genero> getAllCatalogos(){
        return this.generoRepository.findAll();
    }

    public Genero getCatalogoId(Integer id){
        return this.generoRepository.findById(id).get();
    }
}
