package com.js.movies.servicio;

import com.js.movies.dao.PeliculaDao;
import com.js.movies.dto.PeliculaDTO;
import com.js.movies.dto.UsuarioDTO;
import com.js.movies.dto.UsuarioPeliculaDTO;
import com.js.movies.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServicio {

    @Autowired
    private PeliculaDao peliculaDao;

    public String savePelicula(Pelicula pelicula) {

        String salida = "";
        if (pelicula != null && pelicula.getIdGenero() != null && pelicula.getIdPagoEvento() != null) {
            salida = this.peliculaDao.savePelicula(pelicula);
        }else {
            salida = "Error en los datos";
        }
        return  salida;
    }

    public List<PeliculaDTO> getPeliculasCalificacion() {
        List<PeliculaDTO> peliculas = this.peliculaDao.getPeliculasCalificacion();
        return peliculas.stream().filter(pelicula -> pelicula.getCalificacion() >= 4.0F).
                limit(12).toList();
    }

    public PeliculaDTO getPeliculaId(Integer id) {
        if (id == null || id < 0) {
            return null;
        }
        return this.peliculaDao.getPeliculaId(id);
    }

    public List<PeliculaDTO> getPeliculas(String descripcionGenero, Integer pagina, Integer cantidad, Integer plan) {
        if (descripcionGenero == null || pagina == null || pagina < 0 || cantidad == null || cantidad < 0) {
            return null;
        }
        return this.peliculaDao.getPeliculas(descripcionGenero, pagina, cantidad, plan);

    }

    public UsuarioPeliculaDTO getUsuarioPeliculas(Integer idUsuario) {
        UsuarioPeliculaDTO usuarioPeliculaDTO;
        if (idUsuario < 0 || idUsuario == null) {
            return null;
        }
        usuarioPeliculaDTO = this.peliculaDao.getUsuarioPeliculas(idUsuario);
        if (usuarioPeliculaDTO.getId() == null || usuarioPeliculaDTO.getId() < 0) {
            usuarioPeliculaDTO = null;
        }
        return usuarioPeliculaDTO;
    }

}
