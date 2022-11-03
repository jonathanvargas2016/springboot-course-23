package com.js.movies.controller;

import com.js.movies.modelo.Genero;
import com.js.movies.salida.Respuesta;
import com.js.movies.servicio.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneroController {

    @Autowired
    private GeneroServicio generoServicio;

    @PostMapping("/genero")
    public @ResponseBody Respuesta saveGenero(@RequestBody Genero genero){
        Respuesta respuesta = new Respuesta();
        genero.setEstado(true);
        try{
            boolean estado = this.generoServicio.saveGenero(genero);
            respuesta.setRespuesta(estado);
            respuesta.setCodigo(0);
            respuesta.setMensaje("Ok");
        }catch (RuntimeException exc){
            respuesta.setMensaje("Error: " + exc.toString());
            respuesta.setCodigo(1);
        }
        return respuesta;
    }
}
