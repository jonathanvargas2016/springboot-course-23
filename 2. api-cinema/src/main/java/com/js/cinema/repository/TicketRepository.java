package com.js.cinema.repository;

import com.js.cinema.domain.Ticket;
import com.js.cinema.service.dto.ReleasesReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT a.name, a.app_type, t.id_ticket, ra.release_date, ra.release_version FROM ticket t INNER JOIN application a ON t.ID_APP = a.ID_APP INNER JOIN release_app ra ON ra.id_release = t.id_release where ra.release_date = ?1 and a.name like ?2", nativeQuery = true)
    List<Object[]> findReleasesReport(String dateRelease, String nameApp);

}
