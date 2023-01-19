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

    public String saveCatalogo(Genero genero) {
        String respuesta = "";
        Genero respGenero = this.generoRepository.save(genero);
        if (respGenero.getId() != null && respGenero.getId() > 0) {
            respuesta = "OK";
        } else {
            respuesta = "No se pudo guardar el genero";
        }
        return respuesta;
    }

    public String deleteGenero(Integer idGenero) {
        String salida = "Error no se pudo eliminar el genero";
        Genero genero = this.generoRepository.findById(idGenero).orElse(null);
        if (genero != null && genero.getEstado() == 1) {
            Short id = 0;
            genero.setEstado(id);
            Genero respGenero = this.generoRepository.save(genero);
            if (respGenero != null) {
                salida = "OK";
            }
        } else {
            salida = "Error no existe el genero";
        }
        return salida;
    }

    public List<Genero> getAllCatalogos() {
        return this.generoRepository.findAll();
    }

    public Genero getCatalogoId(Integer id) {
        return this.generoRepository.findById(id).get();
    }
}
