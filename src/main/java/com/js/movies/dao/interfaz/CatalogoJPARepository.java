package com.js.movies.dao.interfaz;

import com.js.movies.modelo.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoJPARepository extends JpaRepository<Catalogo, Integer> {
}
