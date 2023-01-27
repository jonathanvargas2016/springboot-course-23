package com.js.cinema.service.mapper;

import com.js.cinema.domain.Application;
import com.js.cinema.domain.Ticket;
import com.js.cinema.service.dto.TicketDto;
import org.mapstruct.Mapper;

@Mapper
public interface TicketMapper {
    Ticket getAsEntity(TicketDto ticketDto, Application app);



}
