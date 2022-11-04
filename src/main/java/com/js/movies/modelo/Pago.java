package com.js.movies.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @Column(name = "ID_PAGO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario idUsuario;

    @Column(name = "NUMERO_TARJETA", nullable = false, length = 16)
    private String numeroTarjeta;

    @Column(name = "TIPO_TARJETA", nullable = false, length = 20)
    private String tipoTarjeta;

    @Column(name = "FECHA_CADUCIDAD", nullable = false)
    private LocalDate fechaCaducidad;

    @Column(name = "CVV_TARJETA", nullable = false, length = 4)
    private String cvvTarjeta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCvvTarjeta() {
        return cvvTarjeta;
    }

    public void setCvvTarjeta(String cvvTarjeta) {
        this.cvvTarjeta = cvvTarjeta;
    }

}