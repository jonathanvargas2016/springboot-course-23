package com.js.movies.dao.interfaz;

import com.js.movies.modelo.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallesJPARepository extends JpaRepository<Detalle, Integer> {
}
