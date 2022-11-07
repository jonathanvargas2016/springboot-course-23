package com.js.movies.modelo;

import javax.persistence.*;

@Entity
@Table(name = "generos")
public class Genero {
    @Id
    @Column(name = "ID_GENERO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOMBRE_GENERO", nullable = false, length = 50)
    private String nombreGenero;

    @Column(name = "CODIGO_GENERO", nullable = false, length = 50)
    private String codigoGenero;


    public Integer getId() {
        return id;
    }

    public String getCodigoGenero() {
        return codigoGenero;
    }

    public void setCodigoGenero(String codigoGenero) {
        this.codigoGenero = codigoGenero;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

}