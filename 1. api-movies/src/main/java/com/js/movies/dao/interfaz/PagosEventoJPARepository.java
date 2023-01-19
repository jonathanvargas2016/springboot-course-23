package com.js.movies.dao.interfaz;

import com.js.movies.modelo.PagosEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagosEventoJPARepository extends JpaRepository<PagosEvento, Integer> {
}
