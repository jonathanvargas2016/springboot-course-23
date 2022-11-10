package com.js.movies.dao;

import com.js.movies.dao.interfaz.DetallesJPARepository;
import com.js.movies.dao.interfaz.PagosEventoJPARepository;
import com.js.movies.dao.interfaz.PeliculaJPARepository;
import com.js.movies.dao.interfaz.UsuarioRepository;
import com.js.movies.modelo.Detalle;
import com.js.movies.modelo.PagosEvento;
import com.js.movies.modelo.Pelicula;
import com.js.movies.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PagosEventoDao {

    @Autowired
    private DetallesJPARepository detallesJPARepository;


    public String savePagosEvento(Detalle detalle) {

        Detalle resp = this.detallesJPARepository.save(detalle);
        if(resp != null){
            return "OK";
        }

        return "";
    }


}
