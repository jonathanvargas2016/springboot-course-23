package com.js.movies.servicio;

import com.js.movies.dao.PlanDao;
import com.js.movies.modelo.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
    @Autowired
    private PlanDao planDao;

    public String savePlan(Plan plan) {
        String salida = "Error en los datos";
        if (plan.getNombre() != null && !plan.getNombre().isBlank() &&
                plan.getPrecio() != null && plan.getPrecio() > 0) {
            salida = this.planDao.savePlan(plan);
        }
        return salida;
    }

}
