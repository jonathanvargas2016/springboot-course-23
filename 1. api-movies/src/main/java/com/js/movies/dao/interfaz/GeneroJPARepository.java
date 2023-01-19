package com.js.movies.dao.interfaz;

import com.js.movies.modelo.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroJPARepository extends JpaRepository<Genero, Integer> {
}
