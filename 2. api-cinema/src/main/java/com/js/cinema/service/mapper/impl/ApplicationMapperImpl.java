package com.js.cinema.service.mapper.impl;

import com.js.cinema.domain.Application;
import com.js.cinema.domain.enums.AppType;
import com.js.cinema.service.dto.ApplicationDto;
import com.js.cinema.service.mapper.ApplicationMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapperImpl implements ApplicationMapper {
    @Override
    public Application getAsEntity(ApplicationDto applicationDto) {
        Application app = new Application();
        if(applicationDto.getAppType().equals(AppType.INTERNAL.name())){
            app.setAppType(AppType.INTERNAL);
        }

        if(applicationDto.getAppType().equals(AppType.EXTERNAL.name())){
            app.setAppType(AppType.EXTERNAL);
        }

        if(applicationDto.getId() != null && applicationDto.getId() > 0){
            app.setId(applicationDto.getId());
        }

        app.setName(applicationDto.getName());
        app.setDescriptionApp(applicationDto.getDescriptionApp());

        return app;
    }
}
