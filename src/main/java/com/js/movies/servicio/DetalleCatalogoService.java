package com.js.movies.servicio;

import com.js.movies.dao.DetalleCatalogoDao;
import com.js.movies.excepcion.ElementoNuloExcepcion;
import com.js.movies.modelo.DetalleCatalogo;
import com.js.movies.operacion.Operacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleCatalogoService {

    @Autowired
    private DetalleCatalogoDao detalleCatalogoDao;

    public Boolean saveDetalleCatalogo(DetalleCatalogo detalleCatalogo){
        Operacion operacion = new Operacion();
        String codigoCatalogo = operacion.convertirCatalogo(detalleCatalogo.getNombre());
        detalleCatalogo.setDescripcion(codigoCatalogo);
        detalleCatalogo.setEstado(true);
        if(detalleCatalogo.getNombre() == null || detalleCatalogo.getNombre().isBlank()){
            throw new ElementoNuloExcepcion("Nombre del detalle no debe ser nulo");
        }
        return this.detalleCatalogoDao.saveDetalleCatalogo(detalleCatalogo);
    }

}
