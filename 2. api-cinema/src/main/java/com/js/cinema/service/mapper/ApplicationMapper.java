package com.js.cinema.service.mapper;

import com.js.cinema.domain.Application;
import com.js.cinema.service.dto.ApplicationDto;
import org.mapstruct.Mapper;

@Mapper
public interface ApplicationMapper {

    Application getAsEntity(ApplicationDto applicationDto);


}
