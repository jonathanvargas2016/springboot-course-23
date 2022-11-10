package com.js.movies.servicio;

import com.js.movies.dao.PagosEventoDao;
import com.js.movies.dao.interfaz.PagosEventoJPARepository;
import com.js.movies.dao.interfaz.PeliculaJPARepository;
import com.js.movies.dao.interfaz.UsuarioRepository;
import com.js.movies.modelo.Detalle;
import com.js.movies.modelo.PagosEvento;
import com.js.movies.modelo.Pelicula;
import com.js.movies.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagosEventoService {

    @Autowired
    private PagosEventoDao pagosEventoDao;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PeliculaJPARepository peliculaRepository;

    public String savePagosEvento(Integer idUsuario, Integer idPelicula){

        String respuesta = "";
        Pelicula pelicula = this.peliculaRepository.findById(idPelicula).orElse(null);
        Usuario usuario = this.usuarioRepository.findById(idUsuario).orElse(null);

        if ( pelicula != null && pelicula.getIdPagoEvento().getId() > 1 && usuario != null) {
            Detalle detalle = new Detalle();
            detalle.setIdPelicula(pelicula);
            detalle.setIdUsuario(usuario);
            respuesta = this.pagosEventoDao.savePagosEvento(detalle);
        }
        return respuesta;
    }


}
