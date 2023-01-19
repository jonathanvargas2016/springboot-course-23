package com.js.movies.dao.interfaz;

import com.js.movies.modelo.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaJPARepository extends JpaRepository<Pelicula, Integer> {
}
