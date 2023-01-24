package com.js.cinema.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {
    private Long id;
    private String name;
    private String descriptionApp;
    private String appType;

}
