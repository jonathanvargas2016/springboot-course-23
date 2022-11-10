package com.js.movies.controller;

import com.js.movies.config.TokenLogin;
import com.js.movies.dto.PagosEventoDTO;
import com.js.movies.salida.Respuesta;
import com.js.movies.servicio.PagosEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PagosEventoController {

    @Autowired
    private PagosEventoService pagosEventoService;

    @Autowired
    private TokenLogin tokenLogin;

    @PostMapping("/pelicula/pago")
    public @ResponseBody Respuesta savePagosEvento(@RequestHeader(name = "token", defaultValue = "NOTOKEN") String token,
                                                   @RequestBody PagosEventoDTO pagosEventoDTO) {
        Respuesta respuesta = new Respuesta();

        if (tokenLogin.exist(token)) {
            try {

                String salida = this.pagosEventoService.savePagosEvento(pagosEventoDTO.getIdUsuario(), pagosEventoDTO.getIdPelicula());

                if (salida.contains("OK")) {
                    respuesta.setCodigo(0);
                    respuesta.setMensaje("OK");
                    respuesta.setRespuesta(salida);
                } else {
                    respuesta.setCodigo(1);
                    respuesta.setMensaje("Error en los datos");
                }

            } catch (RuntimeException exc) {
                respuesta.setCodigo(2);
                respuesta.setMensaje("Error: intente mas tarde");

            }
        } else {
            respuesta.setCodigo(2);
            respuesta.setMensaje("Acceso no autorizado");
        }

        return respuesta;
    }
}
