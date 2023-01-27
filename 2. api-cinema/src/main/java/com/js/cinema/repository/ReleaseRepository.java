package com.js.cinema.repository;

import com.js.cinema.domain.ReleaseApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseRepository extends JpaRepository<ReleaseApp, Long> {
}
