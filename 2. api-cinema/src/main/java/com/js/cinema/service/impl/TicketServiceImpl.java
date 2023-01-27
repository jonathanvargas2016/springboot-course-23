package com.js.cinema.service.impl;

import com.js.cinema.domain.Application;
import com.js.cinema.domain.ReleaseApp;
import com.js.cinema.domain.Ticket;
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
    public Ticket updateTicket(TicketReleaseDto ticket) {

        // retornar otro tipo de respuesta y no ticket....
        //
        if (ticket.getIdTicket() == null || ticket.getIdTicket() < 0 || ticket.getRelease().getReleaseVersion() == null
                || ticket.getRelease().getReleaseVersion().isBlank() || ticket.getRelease().getDescriptionRelease() == null ||
                ticket.getRelease().getDescriptionRelease().isBlank()) {
            throw new RuntimeException("It is necessary to send all data");
        } else {
            Ticket findTicket = this.ticketRepository.findById(ticket.getIdTicket()).orElse(null);
            if (findTicket != null) {

                findTicket.setStatus(StatusType.DONE);
                this.ticketRepository.save(findTicket);

                ticket.getRelease().setReleaseDate(LocalDate.now());
                this.releaseRepository.save(ticket.getRelease());


            } else {
                throw new RuntimeException("Ticket with id " + ticket.getIdTicket() + " does not exist");
            }
        }
        return null;
    }
}