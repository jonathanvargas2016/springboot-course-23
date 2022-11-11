package com.js.movies.controller;

import com.js.movies.config.TokenLogin;
import com.js.movies.modelo.Plan;
import com.js.movies.salida.Respuesta;
import com.js.movies.servicio.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlanController {
    @Autowired
    private PlanService planService;

    @Autowired
    private TokenLogin tokenLogin;

    @PostMapping("/plan")
    public @ResponseBody Respuesta savePlan(@RequestBody Plan plan) {
        Respuesta respuesta = new Respuesta();
        try {
            String estado = this.planService.savePlan(plan);
            if (estado.contains("OK")) {
                respuesta.setRespuesta(estado);
                respuesta.setCodigo(0);
                respuesta.setMensaje(estado);
            } else {
                respuesta.setCodigo(1);
                respuesta.setMensaje("Error: " + estado);
            }

        } catch (RuntimeException exc) {
            respuesta.setCodigo(2);
            respuesta.setMensaje("Error: intente mas tarde");
        }
        return respuesta;
    }

}
