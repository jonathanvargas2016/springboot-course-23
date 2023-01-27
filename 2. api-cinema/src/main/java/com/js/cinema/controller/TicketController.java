package com.js.cinema.controller;

import com.js.cinema.domain.Ticket;
import com.js.cinema.output.Answer;
import com.js.cinema.service.dto.TicketDto;
import com.js.cinema.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketService;

    @PostMapping("")
    public ResponseEntity<?> saveTicket(@RequestBody TicketDto ticket){
        Answer answer = new Answer();
        try{

            Ticket resTicket =  this.ticketService.saveTicket(ticket);
            if(resTicket != null){
                answer.setMessage("Ticket has been created");
                answer.setDataAnswer(resTicket);
            }
            return new ResponseEntity<>(answer, HttpStatus.OK);
        }catch (RuntimeException exc){
            answer.setMessage(exc.getMessage());
            answer.setDataAnswer(null);
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping()
//    public ResponseEntity<?> updateStatusTicket(){
//
//    }

}
