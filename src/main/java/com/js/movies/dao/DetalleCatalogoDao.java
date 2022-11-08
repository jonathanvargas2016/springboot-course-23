package com.js.movies.dao;

import com.js.movies.dao.interfaz.DetalleCatalogoJPARepository;
import com.js.movies.modelo.DetalleCatalogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DetalleCatalogoDao {

    @Autowired
    private DetalleCatalogoJPARepository detalleCatalogoJPARepository;

    public Boolean saveDetalleCatalogo(DetalleCatalogo detalleCatalogo){
        Boolean respuesta = false;
        DetalleCatalogo respDetalleCatag = this.detalleCatalogoJPARepository.save(detalleCatalogo);
        if(respDetalleCatag.getId() != null && respDetalleCatag.getId() > 0){
            respuesta = true;
        }
        return respuesta;
    }

}
