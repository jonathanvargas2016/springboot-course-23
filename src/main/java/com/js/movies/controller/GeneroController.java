package com.js.movies.controller;

import com.js.movies.config.TokenLogin;
import com.js.movies.modelo.Genero;
import com.js.movies.operacion.Operacion;
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
    public @ResponseBody Respuesta saveGenero(@RequestHeader(name = "token", defaultValue = "NOTOKEN") String token, @RequestBody Genero genero) {
        Respuesta respuesta = new Respuesta();
        Operacion operacion = new Operacion();

        if (tokenLogin.exist(token)) {
            genero.setDescripcion(operacion.convertirCatalogo(genero.getNombreGenero()));
            String estado = this.generoServicio.saveGenero(genero);
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

}
