package com.js.movies.modelo;

import javax.persistence.*;

@Entity
@Table(name = "peliculas")
public class Pelicula {
    @Id
    @Column(name = "ID_PELICULA", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PAGO_EVENTO")
    private PagosEvento idPagoEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GENERO")
    private Genero idGenero;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DURACION", nullable = false)
    private Float duracion;

    @Lob
    @Column(name = "RESUMEN", nullable = false)
    private String resumen;

    @Column(name = "IDIOMA", nullable = false, length = 20)
    private String idioma;

    @Column(name = "ESTADO", nullable = false)
    private Short estado;

    @Column(name = "CALIFICACION")
    private Integer calificacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PagosEvento getIdPagoEvento() {
        return idPagoEvento;
    }

    public void setIdPagoEvento(PagosEvento idPagoEvento) {
        this.idPagoEvento = idPagoEvento;
    }

    public Genero getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Genero idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getDuracion() {
        return duracion;
    }

    public void setDuracion(Float duracion) {
        this.duracion = duracion;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

}