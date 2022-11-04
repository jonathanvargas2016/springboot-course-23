package com.js.movies.controller;

import com.js.movies.excepcion.ElementoNuloExcepcion;
import com.js.movies.modelo.Genero;
import com.js.movies.salida.Respuesta;
import com.js.movies.servicio.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GeneroController {

    @Autowired
    private GeneroServicio generoServicio;

    @PostMapping("/genero")
    public @ResponseBody Respuesta saveGenero(@RequestBody Genero genero){
        Respuesta respuesta = new Respuesta();
        try{
            boolean estado = this.generoServicio.saveGenero(genero);
            respuesta.setRespuesta(estado);
            respuesta.setCodigo(0);
            respuesta.setMensaje("Ok");
        }catch (ElementoNuloExcepcion exc){
            respuesta.setMensaje("Error: " + exc.toString());
            respuesta.setCodigo(1);
        }catch (RuntimeException exc){
            respuesta.setCodigo(2);
            respuesta.setMensaje("OK");
            respuesta.setRespuesta("Error: " + exc.toString());

        }
        return respuesta;
    }

    @GetMapping("/genero")
    public @ResponseBody Respuesta getAllGenero(){
        Respuesta respuesta = new Respuesta();
        try{
            List<Genero> generos =  this.generoServicio.getGenero();
            respuesta.setCodigo(0);
            respuesta.setMensaje("OK");
            respuesta.setRespuesta(generos);
        }catch (RuntimeException exc){
            respuesta.setCodigo(2);
            respuesta.setMensaje("OK");
            respuesta.setRespuesta("Error: " + exc.toString());
        }
        return respuesta;
    }

}
