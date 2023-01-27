package com.js.cinema.service.mapper.impl;

import com.js.cinema.domain.Application;
import com.js.cinema.domain.ReleaseApp;
import com.js.cinema.domain.Ticket;
import com.js.cinema.domain.enums.StatusType;
import com.js.cinema.service.dto.TicketDto;
import com.js.cinema.service.mapper.TicketMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketMapperImpl implements TicketMapper {


    @Override
    public Ticket getAsEntity(TicketDto ticketDto, Application app) {

        Ticket ticket = new Ticket();
        ticket.setApplication(app);
        ticket.setTitle(ticketDto.getTitle());

        if(ticketDto.getDescriptionTitle() != null && !ticketDto.getDescriptionTitle().isBlank()){
            ticket.setDescriptionTitle(ticketDto.getDescriptionTitle());
        }

        if(ticketDto.getIdTicket() != null && ticketDto.getIdTicket() > 0){
            ticket.setId(ticketDto.getIdTicket());
        }

        if(ticketDto.getStatus().equals(StatusType.CREATED.name())){
            ticket.setStatus(StatusType.CREATED);
        }else if(ticketDto.getStatus().equals(StatusType.DONE.name())){
            ticket.setStatus(StatusType.DONE);
        }else{
            ticket.setStatus(StatusType.NOT_EXECUTED);
        }

        return ticket;
    }
}
