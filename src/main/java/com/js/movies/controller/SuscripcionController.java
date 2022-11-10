package com.js.movies.controller;
import com.js.movies.config.TokenLogin;
import com.js.movies.dto.InscripcionDto;
import com.js.movies.dto.UsuarioDTO;
import com.js.movies.modelo.Usuario;
import com.js.movies.salida.Respuesta;
import com.js.movies.servicio.SuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SuscripcionController {
    @Autowired
    SuscripcionService suscripcionService;

    @Autowired
    private TokenLogin tokenLogin;

    @PostMapping(path="/register",consumes = "application/json",produces = "application/json")
    public ResponseEntity<String>guardar(@RequestBody InscripcionDto inscripcionDto){
        return suscripcionService.SuscripcionService(inscripcionDto.getUsuario(), inscripcionDto.getSuscripcion(), inscripcionDto.getIntPlan());
    }
    @GetMapping(path="/user")
    public @ResponseBody Respuesta getUsers (@RequestHeader(name = "token", defaultValue = "NOTOKEN") String token){
        Respuesta respuesta = new Respuesta();
        if(tokenLogin.exist(token)){
            List<UsuarioDTO> usuarios = suscripcionService.getUsuarios();
            respuesta.setCodigo(0);
            respuesta.setMensaje("OK");
            respuesta.setRespuesta(usuarios);
        }else{
            respuesta.setCodigo(2);
            respuesta.setMensaje("Acceso no autorizado");
        }
        return respuesta;
    }

}
