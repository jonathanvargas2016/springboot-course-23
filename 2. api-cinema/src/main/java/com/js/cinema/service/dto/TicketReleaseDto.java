package com.js.cinema.service.dto;

import com.js.cinema.domain.ReleaseApp;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketReleaseDto {

    @NotNull
    private Long idTicket;
    private ReleaseApp release;
}
