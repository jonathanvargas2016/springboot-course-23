package com.js.cinema.service;

import com.js.cinema.domain.Ticket;
import com.js.cinema.service.dto.TicketDto;
import com.js.cinema.service.dto.TicketReleaseDto;

public interface TicketService {

    Ticket saveTicket(TicketDto ticket);
    TicketReleaseDto updateTicket(TicketReleaseDto ticket);

}
