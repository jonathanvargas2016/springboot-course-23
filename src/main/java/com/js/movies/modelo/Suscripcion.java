package com.js.movies.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

@Entity
@Table(name = "suscripcion")
public class Suscripcion implements Serializable {

    private static final long serialVersionUID = 6354871230L;
    @Id
    @Column(name = "ID_SUSCRIPCION", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOMBRE_", nullable = false, length = 100)
    private String nombre;

    @Column(name = "PRECIO", nullable = false)
    private Float precio;

    @Column(name = "DURACION_MESES", nullable = false)
    private Integer duracionMeses;

    @Column(name = "FECHA_INICIO", nullable = false)
    private Instant fechaInicio;

    @Column(name = "FECHA_FINALIZACION", nullable = false)
    private Instant fechaFinalizacion;

    @Column(name = "ESTADO_", nullable = false)
    private Boolean estado = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSuscripcion")
    private Collection<Usuario> UsuariosCollection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Collection<Usuario> getUsuariosCollection() {
        return UsuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuario> usuariosCollection) {
        UsuariosCollection = usuariosCollection;
    }
}