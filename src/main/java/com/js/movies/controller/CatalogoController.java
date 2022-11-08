package com.js.movies.controller;

import com.js.movies.excepcion.ElementoNuloExcepcion;
import com.js.movies.modelo.Catalogo;
import com.js.movies.salida.Respuesta;
import com.js.movies.servicio.CatalogoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogoController {

    @Autowired
    private CatalogoServicio generoServicio;

    @PostMapping("/catalogo")
    public @ResponseBody Respuesta saveGenero(@RequestBody Catalogo catalogo){
        Respuesta respuesta = new Respuesta();
        try{
            Boolean estado = this.generoServicio.saveGenero(catalogo);
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

    @GetMapping("/catalogos")
    public @ResponseBody Respuesta getAllGenero(){
        Respuesta respuesta = new Respuesta();
        try{
            List<Catalogo> generos =  this.generoServicio.getCatalogos();
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
