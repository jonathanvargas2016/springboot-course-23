package com.js.movies.modelo;

import javax.persistence.*;

@Entity
@Table(name = "pagos_evento")
public class PagosEvento {
    @Id
    @Column(name = "ID_PAGO_EVENTO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Column(name = "PRECIO", nullable = false)
    private Float precio;

    @Column(name = "RESUMEN", length = 250)
    private String resumen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

}