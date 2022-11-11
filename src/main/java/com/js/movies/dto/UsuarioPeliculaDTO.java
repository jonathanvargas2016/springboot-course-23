package com.js.movies.dto;

import com.js.movies.modelo.Suscripcion;

import java.util.List;

public class UsuarioPeliculaDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;

    List<PeliculaDTO> pelicula;

    public UsuarioPeliculaDTO(Integer id, String nombre, String apellido, String correo, List<PeliculaDTO> pelicula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.pelicula = pelicula;
    }

    public UsuarioPeliculaDTO(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<PeliculaDTO> getPelicula() {
        return pelicula;
    }

    public void setPelicula(List<PeliculaDTO> pelicula) {
        this.pelicula = pelicula;
    }
}
