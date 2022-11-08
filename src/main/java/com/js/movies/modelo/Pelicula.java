package com.js.movies.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "peliculas")
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 6352487410L;

    @Id
    @Column(name = "ID_PELICULA", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GENERO")
    private DetalleCatalogo idGenero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_IDIOMA")
    private DetalleCatalogo idIdioma;

    @Column(name = "TITULO", nullable = false, length = 100)
    private String titulo;

    @Column(name = "DURACION", nullable = false)
    private Float duracion;

    @Lob
    @Column(name = "SINOPSIS", nullable = false)
    private String sinopsis;

    @Column(name = "ESTADO", nullable = false)
    private Boolean estado = false;

    @Column(name = "FECHA_ESTRENO", nullable = false)
    private LocalDate fechaEstreno;

    @Column(name = "NUMERO_VISTAS", nullable = false)
    private Integer numeroVistas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPelicula")
    private Collection<Detalle> detalleCollection;

    public Collection<Detalle> getDetalleCollection() {
        return detalleCollection;
    }

    public void setDetalleCollection(Collection<Detalle> detalleCollection) {
        this.detalleCollection = detalleCollection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DetalleCatalogo getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(DetalleCatalogo idGenero) {
        this.idGenero = idGenero;
    }

    public DetalleCatalogo getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(DetalleCatalogo idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Float getDuracion() {
        return duracion;
    }

    public void setDuracion(Float duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public Integer getNumeroVistas() {
        return numeroVistas;
    }

    public void setNumeroVistas(Integer numeroVistas) {
        this.numeroVistas = numeroVistas;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", idGenero=" + idGenero +
                ", idIdioma=" + idIdioma +
                ", titulo='" + titulo + '\'' +
                ", duracion=" + duracion +
                ", sinopsis='" + sinopsis + '\'' +
                ", estado=" + estado +
                ", fechaEstreno=" + fechaEstreno +
                ", numeroVistas=" + numeroVistas +
                ", detalleCollection=" + detalleCollection +
                '}';
    }
}