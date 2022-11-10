package com.js.movies.dto;


import com.js.movies.modelo.Suscripcion;
import com.js.movies.modelo.Usuario;

public class InscripcionDto {

    Integer intPlan;
    Suscripcion suscripcion;
    Usuario usuario;

    public Integer getIntPlan() {
        return intPlan;
    }

    public void setIntPlan(Integer intPlan) {
        this.intPlan = intPlan;
    }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
