package com.js.movies.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "detalle_catalogo")
public class DetalleCatalogo implements Serializable {
    private static final long serialVersionUID = 5263415482L;

    @Id
    @Column(name = "ID_DETA_CATALOGO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATALOGO")
    private Catalogo idCatalogo;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "ESTADO", nullable = false)
    private Boolean estado = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma")
    private Collection<Pelicula> peliculasIdioma;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGenero")
    private Collection<Pelicula> peliculasGenero;

    public DetalleCatalogo(){

    }

    public DetalleCatalogo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Catalogo getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Catalogo idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Collection<Pelicula> getPeliculasIdioma() {
        return peliculasIdioma;
    }

    public void setPeliculasIdioma(Collection<Pelicula> peliculasIdioma) {
        this.peliculasIdioma = peliculasIdioma;
    }

    public Collection<Pelicula> getPeliculasGenero() {
        return peliculasGenero;
    }

    public void setPeliculasGenero(Collection<Pelicula> peliculasGenero) {
        this.peliculasGenero = peliculasGenero;
    }
}