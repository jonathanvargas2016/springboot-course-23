package com.js.movies.dao;

import com.js.movies.dao.interfaz.DetallesJPARepository;
import com.js.movies.dao.interfaz.PeliculaJPARepository;
import com.js.movies.dto.PeliculaDTO;
import com.js.movies.dto.UsuarioPeliculaDTO;
import com.js.movies.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PeliculaDao {

    @Autowired
    PeliculaJPARepository peliculaRepository;

    @Autowired
    DetallesJPARepository detallesJPARepository;

    public String savePelicula(Pelicula pelicula) {
        String respuesta = "No se pudo guardar la pelicula";
        Pelicula resPelicula = this.peliculaRepository.save(pelicula);
        if (resPelicula.getId() != null && resPelicula.getId() > 0) {
            respuesta = "OK";
        }
        return respuesta;
    }

    public List<PeliculaDTO> getPeliculas(String descripcionGenero, Integer pagina, Integer cantidad, Integer plan) {
        Pelicula plantilla = new Pelicula();
        Genero genero = new Genero();
        genero.setDescripcion(descripcionGenero);
        PagosEvento pagosEvento = new PagosEvento();
        pagosEvento.setId(plan);
        plantilla.setIdPagoEvento(pagosEvento);
        plantilla.setIdGenero(genero);
        List<PeliculaDTO> peliculas = new ArrayList<>();
        for (Pelicula pelicula : this.peliculaRepository.findAll(Example.of(plantilla), PageRequest.of(pagina, cantidad, Sort.by(Sort.Direction.DESC, "calificacion")))) {
            peliculas.add(new PeliculaDTO(pelicula.getId(), pelicula.getIdPagoEvento().getId(), pelicula.getIdGenero().getDescripcion(),
                    pelicula.getNombre(), pelicula.getDuracion(),
                    pelicula.getResumen(), pelicula.getIdioma(), pelicula.getEstado(), pelicula.getCalificacion()));
        }
        return peliculas;
    }


    public List<PeliculaDTO> getPeliculasCalificacion() {
        List<PeliculaDTO> peliculas = new ArrayList<>();
        for (Pelicula pelicula : this.peliculaRepository.findAll()) {
            peliculas.add(new PeliculaDTO(pelicula.getId(), pelicula.getIdPagoEvento().getId(), pelicula.getIdGenero().getDescripcion(),
                    pelicula.getNombre(), pelicula.getDuracion(),
                    pelicula.getResumen(), pelicula.getIdioma(), pelicula.getEstado(), pelicula.getCalificacion()));
        }
        return peliculas;
    }

    public PeliculaDTO getPeliculaId(Integer id) {
        Pelicula pelicula = this.peliculaRepository.findById(id).orElse(null);
        PeliculaDTO peliculaDTO;
        if (pelicula != null) {
            peliculaDTO = new PeliculaDTO(pelicula.getId(), pelicula.getIdPagoEvento().getId(), pelicula.getIdGenero().getDescripcion(),
                    pelicula.getNombre(), pelicula.getDuracion(),
                    pelicula.getResumen(), pelicula.getIdioma(), pelicula.getEstado(), pelicula.getCalificacion());
        } else {
            peliculaDTO = null;
        }

        return peliculaDTO;
    }

    public UsuarioPeliculaDTO getUsuarioPeliculas(Integer idUsuario) {
        Detalle plantilla = new Detalle();
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        plantilla.setIdUsuario(usuario);

        UsuarioPeliculaDTO usuarioPeliculas;
        Usuario usuarioDetalle = new Usuario();
        List<PeliculaDTO> peliculasDetalle = new ArrayList<>();

        for (Detalle detalle : this.detallesJPARepository.findAll(Example.of(plantilla))) {
            usuarioDetalle.setId(detalle.getIdUsuario().getId());
            usuarioDetalle.setNombre(detalle.getIdUsuario().getNombre());
            usuarioDetalle.setApellido(detalle.getIdUsuario().getApellido());
            usuarioDetalle.setCorreo(detalle.getIdUsuario().getCorreo());

            PeliculaDTO pelicula = new PeliculaDTO(detalle.getIdPelicula().getId(),
                    detalle.getIdPelicula().getIdPagoEvento().getId(),
                    detalle.getIdPelicula().getIdGenero().getDescripcion(),
                    detalle.getIdPelicula().getNombre(), detalle.getIdPelicula().getDuracion(),
                    detalle.getIdPelicula().getResumen(), detalle.getIdPelicula().getIdioma(),
                    detalle.getIdPelicula().getEstado(), detalle.getIdPelicula().getCalificacion());

            peliculasDetalle.add(pelicula);
        }
        usuarioPeliculas = new UsuarioPeliculaDTO(
                usuarioDetalle.getId(),
                usuarioDetalle.getNombre(),
                usuarioDetalle.getApellido(),
                usuarioDetalle.getCorreo(),
                peliculasDetalle);

        return usuarioPeliculas;
    }
}
