package com.js.movies.servicio;

import com.js.movies.dao.GeneroDao;
import com.js.movies.modelo.Genero;
import com.js.movies.operacion.Operacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServicio {

    @Autowired
    private GeneroDao generoDao;

    public String saveGenero(Genero genero) {
        if (genero.getNombreGenero() == null || genero.getNombreGenero().isBlank() || genero.getEstado() == null || genero.getEstado() < 0) {
            return "Error: en los datos";
        }
        Operacion operacion = new Operacion();
        genero.setDescripcion(operacion.convertirGenero(genero.getNombreGenero()));
        return  this.generoDao.saveCatalogo(genero);
    }

    public List<Genero> getCatalogos() {
        return this.generoDao.getAllCatalogos();
    }

    public Genero getGeneroId(Integer id) {
        Genero respGenero = this.generoDao.getCatalogoId(id);
        return respGenero != null ? respGenero : null;
    }

    public String deleteGenero(Integer id){
        String salida = "Error en los datos";
        if(id != null && id > 0){
            salida = this.generoDao.deleteGenero(id);
        }
        return salida;
    }
}
