package com.js.movies.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "pago")
public class Pago implements Serializable {
    private static final long serialVersionUID = 55412478301L;

    @Id
    @Column(name = "ID_PAGO", nullable = false)
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
    private Instant fechaCaducidad;

    @Column(name = "CVV", nullable = false, length = 4)
    private String cvv;

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

    public Instant getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Instant fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

}