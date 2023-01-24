package com.js.cinema.service.impl;

import com.js.cinema.domain.Application;
import com.js.cinema.domain.enums.AppType;
import com.js.cinema.repository.ApplicationRepository;
import com.js.cinema.service.ApplicationService;
import com.js.cinema.service.dto.ApplicationDto;
import com.js.cinema.service.mapper.impl.ApplicationMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public Application saveApplication(ApplicationDto app) {

        if (app.getName() == null || app.getName().isBlank() ||
            app.getDescriptionApp() == null || app.getDescriptionApp().isBlank() ||
            app.getAppType() == null || app.getAppType().isBlank()){
            throw new RuntimeException("Error, user should send all data");
        }

        if(app.getAppType().equals(AppType.INTERNAL.name()) || app.getAppType().equals(AppType.EXTERNAL.name())){
            return this.applicationRepository.save(new ApplicationMapperImpl().getAsEntity(app));

        }else {
            throw new RuntimeException("App type does not exist");
        }
    }
}
