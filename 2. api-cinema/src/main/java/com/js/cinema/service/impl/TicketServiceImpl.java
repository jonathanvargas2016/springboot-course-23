package com.js.cinema.service.impl;

import com.js.cinema.domain.Application;
import com.js.cinema.domain.ReleaseApp;
import com.js.cinema.domain.Ticket;
import com.js.cinema.domain.enums.AppType;
import com.js.cinema.domain.enums.StatusType;
import com.js.cinema.repository.ReleaseRepository;
import com.js.cinema.repository.TicketRepository;
import com.js.cinema.service.TicketService;
import com.js.cinema.service.dto.TicketDto;
import com.js.cinema.service.dto.TicketReleaseDto;
import com.js.cinema.service.mapper.impl.TicketMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ApplicationServiceImpl applicationService;

    @Autowired
    private ReleaseRepository releaseRepository;

    @Override
    public Ticket saveTicket(TicketDto ticket) {
        Ticket resTicket = null;
        if (ticket.getIdApplication() == null || ticket.getIdApplication() <= 0 ||
                ticket.getTitle() == null || ticket.getTitle().isBlank() || ticket.getStatus() == null ||
                ticket.getStatus().isBlank()) {
            throw new RuntimeException("Debe enviar todos los datos");
        } else {
            Application app = this.applicationService.getApplicationById(ticket.getIdApplication());
            if (app != null) {
                resTicket = this.ticketRepository.save(new TicketMapperImpl().getAsEntity(ticket, app));
            } else {
                throw new RuntimeException("App with id " + ticket.getIdApplication() + " does not exist");
            }
        }

        return resTicket;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TicketReleaseDto updateTicket(TicketReleaseDto ticket) {
        TicketReleaseDto ticketReleaseDto = null;
        if (ticket.getIdTicket() == null || ticket.getIdTicket() < 0 || ticket.getRelease() == null) {
            throw new RuntimeException("It is necessary to send all data");
        } else {

            Ticket ticketFind = this.ticketRepository.findById(ticket.getIdTicket()).orElse(null);
            if (ticketFind != null) {
                ReleaseApp release = this.releaseRepository.save(ticket.getRelease());
                ticketFind.setStatus(StatusType.DONE);
                ticketFind.setRelease(release);
                Ticket res = this.ticketRepository.save(ticketFind);
                ticketReleaseDto = new TicketMapperImpl().getAsTicketReleaseDto(res);
            } else {
                throw new RuntimeException("Ticket with id " + ticket.getIdTicket() + " does not exist");
            }

        }
        return ticketReleaseDto;
    }
}
