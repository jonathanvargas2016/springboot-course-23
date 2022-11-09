package com.js.movies.controller;

import com.js.movies.config.TokenLogin;
import com.js.movies.dto.PeliculaDTO;
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

    @GetMapping("/peliculas/raiting")
    public @ResponseBody Respuesta getPelicula(@RequestHeader(name = "token", defaultValue = "NOTOKEN") String token) {
        Respuesta respuesta = new Respuesta();

        if(tokenLogin.exist(token)){
            List<PeliculaDTO> peliculas = this.peliculaServicio.getPeliculasRaiting();
            respuesta.setCodigo(0);
            respuesta.setMensaje("Ok");
            respuesta.setRespuesta(peliculas);
        }else {
            respuesta.setMensaje("Acceso no autorizado");
            respuesta.setCodigo(2);
        }

        return respuesta;
    }
//
//    @GetMapping("/pelicula/{id}")
//    public @ResponseBody Respuesta getPeliculaId(@PathVariable(name = "id") Integer id) {
//        Respuesta respuesta = new Respuesta();
//        try {
//            Pelicula pelicula = this.peliculaServicio.getPeliculaId(id);
//            respuesta.setCodigo(0);
//            respuesta.setMensaje("Ok");
//            respuesta.setRespuesta(pelicula);
//
//        } catch (ElementoNuloExcepcion exc) {
//            respuesta.setCodigo(1);
//            respuesta.setMensaje("Error: " + exc.toString());
//
//        } catch (RuntimeException exc) {
//            respuesta.setCodigo(2);
//            respuesta.setMensaje("Error: " + exc.toString());
//        }
//        return respuesta;
//    }
//
//    @PostMapping("/delete/pelicula")
//    public @ResponseBody Respuesta deletePelicula(@RequestBody Pelicula pelicula) {
//        Respuesta respuesta = new Respuesta();
//        try {
//            boolean resp = this.peliculaServicio.deletePelicula(pelicula.getId());
//            respuesta.setCodigo(0);
//            respuesta.setMensaje("Ok");
//            respuesta.setRespuesta(resp);
//        } catch (RuntimeException exc) {
//            respuesta.setCodigo(2);
//            respuesta.setMensaje("Error: " + exc.toString());
//        }
//        return respuesta;
//    }
//

    @GetMapping("/peliculas/{genero}")
    public @ResponseBody Respuesta getPeliculaGenero(@RequestHeader(name = "token", defaultValue = "NOTOKEN") String token,
                                                     @PathVariable(name = "genero") String descripcionGenero,
                                                     @RequestParam(name = "pagina") Integer pagina,
                                                     @RequestParam(name = "cantidad") Integer cantidad) {
        Respuesta respuesta = new Respuesta();
        System.out.println("genero " + descripcionGenero);
        if (tokenLogin.exist(token)) {

            List<PeliculaDTO> data = this.peliculaServicio.getPeliculas(descripcionGenero, pagina, cantidad);

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


}
