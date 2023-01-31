package com.js.cinema.service.dto;

import com.js.cinema.domain.enums.AppType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReleasesReportDto {

    private String appName;
    private String appType;
    private Long ticketNumber;
    private String releaseDate;
    private String versionNumber;
}
