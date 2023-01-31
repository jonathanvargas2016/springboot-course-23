package com.js.cinema.controller;

import com.js.cinema.domain.Ticket;
import com.js.cinema.output.Answer;
import com.js.cinema.service.dto.ReleasesReportDto;
import com.js.cinema.service.dto.TicketDto;
import com.js.cinema.service.dto.TicketReleaseDto;
import com.js.cinema.service.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketService;

    @PostMapping("")
    public ResponseEntity<?> saveTicket(@RequestBody TicketDto ticket) {
        Answer answer = new Answer();
        try {

            Ticket resTicket = this.ticketService.saveTicket(ticket);
            if (resTicket != null) {
                answer.setMessage("Ticket has been created");
                answer.setDataAnswer(resTicket);
            }
            return new ResponseEntity<>(answer, HttpStatus.OK);
        } catch (RuntimeException exc) {
            answer.setMessage(exc.getMessage());
            answer.setDataAnswer(null);
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateStatusTicket(@RequestBody TicketReleaseDto ticket) {
        Answer answer = new Answer();
        try {
            TicketReleaseDto res = this.ticketService.updateTicket(ticket);
            if (res != null) {
                answer.setMessage("Ticket has been updated");
                answer.setDataAnswer(res);
            }
            return new ResponseEntity<>(answer, HttpStatus.OK);
        } catch (RuntimeException exc) {
            answer.setMessage(exc.getMessage());
            answer.setDataAnswer(null);
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("release-report")
    public ResponseEntity<?> getReleasesReport(@RequestParam(name = "dateRelease", defaultValue = "2023-01-01") String dateRelease, @RequestParam(name = "nameApp", defaultValue = "") String nameApp) {
        Answer answer = new Answer();
        try {

            List<ReleasesReportDto> releasesReport = this.ticketService.findReleasesReport(dateRelease, nameApp);
            if (releasesReport.size() > 0) {
                answer.setMessage("List of releases report");
                answer.setDataAnswer(releasesReport);
            } else {
                answer.setMessage("List of releases report is 0");
                answer.setDataAnswer(null);
            }
            return new ResponseEntity<>(answer, HttpStatus.OK);
        } catch (RuntimeException exc) {
            answer.setMessage(exc.getMessage());
            answer.setDataAnswer(null);
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

}
