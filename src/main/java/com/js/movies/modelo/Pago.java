package com.js.movies.modelo;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "pagos")
public class Pago {
    @Id
    @Column(name = "ID_TARJETA", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario idUsuario;

    @Column(name = "NUMERO_TARJETA", nullable = false, length = 30)
    private String numeroTarjeta;

    @Column(name = "FECHA_TARJETA", nullable = false)
    private Instant fechaTarjeta;

    @Column(name = "CVV", nullable = false, length = 4)
    private String cvv;

    @Column(name = "ESTADO_TARJETA")
    private Short estadoTarjeta;

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

    public Instant getFechaTarjeta() {
        return fechaTarjeta;
    }

    public void setFechaTarjeta(Instant fechaTarjeta) {
        this.fechaTarjeta = fechaTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Short getEstadoTarjeta() {
        return estadoTarjeta;
    }

    public void setEstadoTarjeta(Short estadoTarjeta) {
        this.estadoTarjeta = estadoTarjeta;
    }

}