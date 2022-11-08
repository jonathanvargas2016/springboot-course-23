package com.js.movies.controller;

import com.js.movies.excepcion.ElementoNuloExcepcion;
import com.js.movies.modelo.DetalleCatalogo;
import com.js.movies.salida.Respuesta;
import com.js.movies.servicio.DetalleCatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetalleCatalogoController {

    @Autowired
    private DetalleCatalogoService detalleCatalogoService;

    @PostMapping("/catalogo/detalles")
    public @ResponseBody Respuesta saveDetalleCatalogo(@RequestBody DetalleCatalogo detalleCatalogo){
        Respuesta respuesta = new Respuesta();

        try{
            Boolean estado = this.detalleCatalogoService.saveDetalleCatalogo(detalleCatalogo);
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

}
