package com.js.movies.controller;

import com.js.movies.config.TokenLogin;
import com.js.movies.dto.PeliculaDTO;
import com.js.movies.dto.UsuarioPeliculaDTO;
import com.js.movies.modelo.Pelicula;
import com.js.movies.salida.Respuesta;
import com.js.movies.servicio.PeliculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PeliculaController {

    @Autowired
    private PeliculaServicio peliculaServicio;

    @Autowired
    private TokenLogin tokenLogin;

    @PostMapping("/pelicula")
    public @ResponseBody Respuesta savePelicula(@RequestHeader(name = "token", defaultValue = "NOTOKEN") String token, @RequestBody Pelicula pelicula) {
        Respuesta respuesta = new Respuesta();

        if (tokenLogin.exist(token)) {
            String estado = this.peliculaServicio.savePelicula(pelicula);

            if (estado.contains("OK")) {
                respuesta.setRespuesta(estado);
                respuesta.setCodigo(0);
                respuesta.setMensaje("Ok");
            } else {
                respuesta.setMensaje("Error: " + estado);
                respuesta.setCodigo(1);
            }
        } else {
            respuesta.setMensaje("Acceso no autorizado");
            respuesta.setCodigo(2);
        }
        return respuesta;
    }

    @GetMapping("/peliculas/calificacion")
    public @ResponseBody Respuesta getPelicula(@RequestHeader(name = "token", defaultValue = "NOTOKEN") String token) {
        Respuesta respuesta = new Respuesta();

        if (tokenLogin.exist(token)) {
            List<PeliculaDTO> peliculas = this.peliculaServicio.getPeliculasCalificacion();
            respuesta.setCodigo(0);
            respuesta.setMensaje("Ok");
            respuesta.setRespuesta(peliculas);
        } else {
            respuesta.setMensaje("Acceso no autorizado");
            respuesta.setCodigo(2);
        }

        return respuesta;
    }

    @GetMapping("/peliculas/{genero}")
    public @ResponseBody Respuesta getPeliculaGenero(@RequestHeader(name = "token", defaultValue = "NOTOKEN") String token,
                                                     @PathVariable(name = "genero") String descripcionGenero,
                                                     @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
                                                     @RequestParam(name = "cantidad", defaultValue = "12") Integer cantidad,
                                                     @RequestParam(name = "plan", defaultValue = "1") Integer plan) {
        Respuesta respuesta = new Respuesta();
        System.out.println("genero " + descripcionGenero);
        if (tokenLogin.exist(token)) {

            List<PeliculaDTO> data = this.peliculaServicio.getPeliculas(descripcionGenero, pagina, cantidad, plan);

            if (data == null) {
                respuesta.setMensaje("Error: el genero, la pagina y la cantidad no deben ser nulo");
                respuesta.setCodigo(1);
            } else {
                respuesta.setRespuesta(data);
                respuesta.setCodigo(0);
                respuesta.setMensaje("Ok");
            }

        } else {
            respuesta.setMensaje("Acceso no autorizado");
            respuesta.setCodigo(2);
        }

        return respuesta;
    }

    @GetMapping("/pelicula")
    public @ResponseBody Respuesta getPeliculaPorId(@RequestHeader(name = "token", defaultValue = "NOTOKEN") String token,
                                                    @RequestParam(name = "id", defaultValue = "") Integer id) {
        Respuesta respuesta = new Respuesta();

        if (tokenLogin.exist(token)) {
            try {
                PeliculaDTO peliculaDTO = this.peliculaServicio.getPeliculaId(id);
                if (peliculaDTO != null) {
                    respuesta.setCodigo(0);
                    respuesta.setMensaje("Ok");
                    respuesta.setRespuesta(peliculaDTO);

                } else {
                    respuesta.setCodigo(1);
                    respuesta.setMensaje("Pelicula no existe");
                }
            } catch (RuntimeException exc) {
                respuesta.setCodigo(2);
                respuesta.setMensaje("Error: intente mas tarde");
            }
        } else {
            respuesta.setMensaje("Acceso no autorizado");
            respuesta.setCodigo(2);
        }
        return respuesta;
    }

    @GetMapping("/peliculas/usuario")
    public @ResponseBody Respuesta getUsuarioPeliculas(@RequestHeader(name = "token", defaultValue = "NOTOKEN") String token,
                                                       @RequestParam(name = "id", defaultValue = "") Integer id){
        Respuesta respuesta = new Respuesta();
        if(tokenLogin.exist(token)){
            try{
                UsuarioPeliculaDTO datos = this.peliculaServicio.getUsuarioPeliculas(id);
                if(datos != null){
                    respuesta.setCodigo(0);
                    respuesta.setMensaje("Ok");
                    respuesta.setRespuesta(datos);
                }else {
                    respuesta.setCodigo(1);
                    respuesta.setMensaje("Usuario no existe o no tiene registros");
                }
            }catch (RuntimeException exc){
                respuesta.setCodigo(2);
                respuesta.setMensaje("Error: intente mas tarde");
            }
        }else{
            respuesta.setCodigo(2);
            respuesta.setMensaje("Acceso no autorizado");
        }
        return respuesta;
    }


}
