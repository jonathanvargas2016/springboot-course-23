package com.js.movies.controller;

import com.js.movies.config.TokenLogin;
import com.js.movies.modelo.Genero;
import com.js.movies.salida.Respuesta;
import com.js.movies.servicio.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class GeneroController {

    @Autowired
    private GeneroServicio generoServicio;

    @Autowired
    private TokenLogin tokenLogin;

    @PostMapping("/genero")
    public @ResponseBody Respuesta saveGenero(@RequestBody Genero genero) {
        Respuesta respuesta = new Respuesta();
        String estado = this.generoServicio.saveGenero(genero);

        try {
            if (estado.contains("OK")) {
                respuesta.setRespuesta(estado);
                respuesta.setCodigo(0);
                respuesta.setMensaje(estado);
            } else {
                respuesta.setCodigo(1);
                respuesta.setMensaje(estado);
            }
        } catch (RuntimeException exc) {
            respuesta.setCodigo(2);
            respuesta.setMensaje("Error: intente mas tarde");
        }
        return respuesta;
    }

    @PostMapping("delete/genero")
    public @ResponseBody Respuesta deleteGenero(@RequestParam(name = "id", defaultValue = "") Integer id) {
        Respuesta respuesta = new Respuesta();
        try {
            String estado = this.generoServicio.deleteGenero(id);
            if (estado.contains("OK")) {
                respuesta.setRespuesta(estado);
                respuesta.setCodigo(0);
                respuesta.setMensaje(estado);
            } else {
                respuesta.setCodigo(1);
                respuesta.setMensaje(estado);
            }
        } catch (RuntimeException exc) {
            respuesta.setCodigo(2);
            respuesta.setMensaje("Error: intente mas tarde");
        }

        return respuesta;
    }

}
