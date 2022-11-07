package com.js.movies.controller;

import com.js.movies.excepcion.ElementoNuloExcepcion;
import com.js.movies.modelo.Genero;
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

    @PostMapping("/pelicula")
    public @ResponseBody Respuesta savePelicula(@RequestBody Pelicula pelicula) {
        Respuesta respuesta = new Respuesta();
        try {
            boolean estado = this.peliculaServicio.savePelicula(pelicula);
            respuesta.setCodigo(0);
            respuesta.setMensaje("Ok");
            respuesta.setRespuesta(estado);
        } catch (ElementoNuloExcepcion exc) {
            respuesta.setCodigo(1);
            respuesta.setMensaje("Error: " + exc.toString());

        } catch (RuntimeException exc) {
            respuesta.setCodigo(2);
            respuesta.setMensaje("Error: " + exc.toString());

        }
        return respuesta;
    }

    @GetMapping("/pelicula")
    public @ResponseBody Respuesta getPelicula() {
        Respuesta respuesta = new Respuesta();
        try {
            List<Pelicula> peliculas = this.peliculaServicio.getPeliculaCatalogo("vistas");
            respuesta.setCodigo(0);
            respuesta.setMensaje("Ok");
            respuesta.setRespuesta(peliculas);
        } catch (RuntimeException exc) {
            respuesta.setCodigo(2);
            respuesta.setMensaje("Error: " + exc.toString());
        }
        return respuesta;
    }

    @GetMapping("/pelicula/{id}")
    public @ResponseBody Respuesta getPeliculaId(@PathVariable(name = "id") Integer id) {
        Respuesta respuesta = new Respuesta();
        try {
            Pelicula pelicula = this.peliculaServicio.getPeliculaId(id);
            respuesta.setCodigo(0);
            respuesta.setMensaje("Ok");
            respuesta.setRespuesta(pelicula);

        } catch (ElementoNuloExcepcion exc) {
            respuesta.setCodigo(1);
            respuesta.setMensaje("Error: " + exc.toString());

        } catch (RuntimeException exc) {
            respuesta.setCodigo(2);
            respuesta.setMensaje("Error: " + exc.toString());
        }
        return respuesta;
    }

    @PostMapping("/delete/pelicula")
    public @ResponseBody Respuesta deletePelicula(@RequestBody Pelicula pelicula) {
        Respuesta respuesta = new Respuesta();
        try {
            boolean resp = this.peliculaServicio.deletePelicula(pelicula.getId());
            respuesta.setCodigo(0);
            respuesta.setMensaje("Ok");
            respuesta.setRespuesta(resp);
        } catch (RuntimeException exc) {
            respuesta.setCodigo(2);
            respuesta.setMensaje("Error: " + exc.toString());
        }
        return respuesta;
    }

    @GetMapping("/peliculas/{genero}")
    public @ResponseBody Respuesta getPeliculaGenero(@PathVariable(name = "genero") String codigoGenero,
                                                     @RequestParam(name = "pagina") Integer pagina,
                                                     @RequestParam(name = "cantidad") Integer cantidad) {
        Genero genero = new Genero();
        genero.setCodigoGenero(codigoGenero);
        Respuesta respuesta = new Respuesta();
        try {
            List peliculas = this.peliculaServicio.getPeliculaGenero(genero, pagina, cantidad);
            respuesta.setCodigo(0);
            respuesta.setMensaje("Ok");
            respuesta.setRespuesta(peliculas);
        } catch (ElementoNuloExcepcion exc) {
            respuesta.setCodigo(1);
            respuesta.setMensaje("Error: " + exc.toString());
        } catch (RuntimeException exc) {
            respuesta.setCodigo(2);
            respuesta.setMensaje("Error: " + exc.toString());
        }
        return respuesta;
    }


}
