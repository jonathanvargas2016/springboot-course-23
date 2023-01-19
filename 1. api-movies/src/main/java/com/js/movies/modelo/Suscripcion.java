package com.js.movies.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonEncoding;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

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
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date fechaInicio;

    @Column(name = "FECHA_FINALIZACION", nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date fechaFinalizacion;

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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

}