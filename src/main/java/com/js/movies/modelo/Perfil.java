package com.js.movies.modelo;

import javax.persistence.*;

@Entity
@Table(name = "perfiles")
public class Perfil {
    @Id
    @Column(name = "ID_PERFIL_", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario idUsuario;

    @Column(name = "NOMBRE_PERFIL", nullable = false, length = 100)
    private String nombrePerfil;

    @Column(name = "TIPO_CONTENIDO", nullable = false, length = 50)
    private String tipoContenido;

    @Column(name = "URLFOTO", nullable = false, length = 200)
    private String urlfoto;

    @Column(name = "ESTADO_PERFIL", nullable = false)
    private Short estadoPerfil;

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

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }

    public Short getEstadoPerfil() {
        return estadoPerfil;
    }

    public void setEstadoPerfil(Short estadoPerfil) {
        this.estadoPerfil = estadoPerfil;
    }

}