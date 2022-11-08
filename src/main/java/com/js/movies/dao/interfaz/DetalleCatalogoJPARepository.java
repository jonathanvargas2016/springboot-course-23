package com.js.movies.dao.interfaz;

import com.js.movies.modelo.DetalleCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCatalogoJPARepository extends JpaRepository<DetalleCatalogo, Integer> {
}
