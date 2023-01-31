package com.js.cinema.service;

import com.js.cinema.domain.Ticket;
import com.js.cinema.service.dto.ReleasesReportDto;
import com.js.cinema.service.dto.TicketDto;
import com.js.cinema.service.dto.TicketReleaseDto;

import java.util.List;

public interface TicketService {

    Ticket saveTicket(TicketDto ticket);
    TicketReleaseDto updateTicket(TicketReleaseDto ticket);

    List<ReleasesReportDto> findReleasesReport(String dateRelease, String nameApp);

}
