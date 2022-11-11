package com.js.movies.dao;

import com.js.movies.dao.interfaz.PlanRepository;
import com.js.movies.modelo.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlanDao {

    @Autowired
    private PlanRepository planRepository;

    public String savePlan(Plan plan) {
        String salida = "Error: no se pudo guardar";

        Plan resPlan = this.planRepository.save(plan);
        if (resPlan != null) {
            salida = "OK";
        }
        return salida;

    }

}
