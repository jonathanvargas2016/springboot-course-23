package com.js.movies.modelo;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "suscripcion")
public class Suscripcion {
    @Id
    @Column(name = "ID_SUSCRIPCION", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PLAN")
    private Plan idPlan;

    @Column(name = "DURACION_MESES", nullable = false)
    private Integer duracionMeses;

    @Column(name = "FECHA_INICIO", nullable = false)
    private Instant fechaInicio;

    @Column(name = "FECHA_FINALIZACION", nullable = false)
    private Instant fechaFinalizacion;

    @Column(name = "ESTADO", nullable = false)
    private Short estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Plan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Plan idPlan) {
        this.idPlan = idPlan;
    }

    public Integer getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(Integer duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Instant getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Instant fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

}