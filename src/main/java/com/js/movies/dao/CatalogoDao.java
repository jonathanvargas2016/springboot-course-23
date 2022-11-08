package com.js.movies.dao;

import com.js.movies.dao.interfaz.CatalogoJPARepository;
import com.js.movies.modelo.Catalogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CatalogoDao {

    @Autowired
    private CatalogoJPARepository catalogoRepository;

    public Boolean saveCatalogo(Catalogo genero){
        Boolean respuesta = false;
        Catalogo respGenero = this.catalogoRepository.save(genero);
        if(respGenero.getId() != null && respGenero.getId() > 0){
            respuesta = true;
        }
        return respuesta;
    }

    public List<Catalogo> getAllCatalogos(){
        return this.catalogoRepository.findAll();
    }

    public Catalogo getCatalogoId(Integer id){
        return this.catalogoRepository.findById(id).get();
    }
}
