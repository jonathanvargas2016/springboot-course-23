package com.js.movies.dto;

public class PagosEventoDTO {

    Integer idUsuario;
    Integer idPelicula;

    public PagosEventoDTO() {
    }

    public PagosEventoDTO(Integer idUsuario, Integer idPelicula) {
        this.idUsuario = idUsuario;
        this.idPelicula = idPelicula;
    }


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }
}
