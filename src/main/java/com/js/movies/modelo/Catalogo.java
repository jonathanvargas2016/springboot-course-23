package com.js.movies.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "catalogo")
public class Catalogo implements Serializable {

    private static final long serialVersionUID = 7485124578L;
    @Id
    @Column(name = "ID_CATALOGO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOMBRE_CATALOGO", nullable = false, length = 50)
    private String nombreCatalogo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private Collection<DetalleCatalogo> detalleCatalogoCollection;

    public Catalogo(){

    }

    public Catalogo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCatalogo() {
        return nombreCatalogo;
    }

    public void setNombreCatalogo(String nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }

    public Collection<DetalleCatalogo> getDetalleCatalogoCollection() {
        return detalleCatalogoCollection;
    }

    public void setDetalleCatalogoCollection(Collection<DetalleCatalogo> detalleCatalogoCollection) {
        this.detalleCatalogoCollection = detalleCatalogoCollection;
    }
}