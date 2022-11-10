package com.js.movies.servicio;


import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.js.movies.dao.interfaz.PlanRepository;
import com.js.movies.dao.interfaz.SuscripcionRepository;
import com.js.movies.dao.interfaz.UsuarioRepository;
import com.js.movies.dto.InscripcionDto;
import com.js.movies.dto.PeliculaDTO;
import com.js.movies.dto.UsuarioDTO;
import com.js.movies.modelo.Pelicula;
import com.js.movies.modelo.Plan;
import com.js.movies.modelo.Suscripcion;
import com.js.movies.modelo.Usuario;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SuscripcionService {
    @Autowired
    SuscripcionRepository suscripcionRepository;
    @Autowired
    PlanRepository planeRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<String> SuscripcionService(Usuario usuario, Suscripcion suscripcion, Integer idPlan) {
        if (usuario != null && suscripcion != null && idPlan != null) {
            try {
                Plan plan = planeRepository.findById(idPlan).orElse(null);
                if (plan != null) {
                    suscripcion.setIdPlan(plan);
                    usuario.setIdSuscripcion(suscripcion);
                    planeRepository.save(plan);
                    suscripcionRepository.save(suscripcion);
                    usuarioRepository.save(usuario);
                    return new ResponseEntity<>("Se ha guardado correctamente el usuario", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("No se ha encontrado el plan", HttpStatus.NOT_FOUND);
                }
            } catch (ConstraintViolationException e) {
                return new ResponseEntity<>("Error al guardar" + e.toString(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("No se pudo suscrbir el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public List<UsuarioDTO> getUsuarios() {
        List<UsuarioDTO> usuarios = new ArrayList<>();
        for (Usuario usuario : this.usuarioRepository.findAll()) {
            Suscripcion suscripcion = new Suscripcion();
            Plan plan = new Plan();

            plan.setId(usuario.getIdSuscripcion().getIdPlan().getId());
            plan.setNombre(usuario.getIdSuscripcion().getIdPlan().getNombre());
            plan.setPrecio(usuario.getIdSuscripcion().getIdPlan().getPrecio());

            suscripcion.setId(usuario.getIdSuscripcion().getId());
            suscripcion.setIdPlan(plan);

            suscripcion.setFechaInicio(usuario.getIdSuscripcion().getFechaInicio());
            suscripcion.setFechaFinalizacion(usuario.getIdSuscripcion().getFechaFinalizacion());
            suscripcion.setDuracionMeses(usuario.getIdSuscripcion().getDuracionMeses());
            suscripcion.setEstado(usuario.getIdSuscripcion().getEstado());

                usuarios.add(new UsuarioDTO(usuario.getId(), suscripcion, usuario.getNombre(),
                        usuario.getApellido(), usuario.getCorreo(), usuario.getUsername(), usuario.getEstadoUsuario()
                ));
            }
            return usuarios;
        }
    }
