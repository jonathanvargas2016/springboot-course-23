package com.js.movies.dto;

import com.js.movies.modelo.Suscripcion;

import javax.persistence.*;

public class UsuarioDTO {

    private Integer id;

    private Suscripcion idSuscripcion;

    private String nombre;


    private String apellido;

    private String correo;


    private String username;

    private Short estadoUsuario;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer id, Suscripcion idSuscripcion, String nombre, String apellido, String correo, String username, Short estadoUsuario) {
        this.id = id;
        this.idSuscripcion = idSuscripcion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.username = username;
        this.estadoUsuario = estadoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Suscripcion getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Suscripcion idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Short getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Short estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }


}
