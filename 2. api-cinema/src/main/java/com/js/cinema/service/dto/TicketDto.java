package com.js.cinema.service.dto;

import com.js.cinema.domain.Application;
import com.js.cinema.domain.ReleaseApp;
import com.js.cinema.domain.enums.StatusType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private Long idTicket;

    @NotNull
    private Long idApplication;

    @NotNull
    private String title;

    private String descriptionTitle;
    @NotNull
    private String status;


}
