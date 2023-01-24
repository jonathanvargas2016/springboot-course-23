package com.js.cinema.service;

import com.js.cinema.domain.Application;
import com.js.cinema.service.dto.ApplicationDto;

public interface ApplicationService {

    Application saveApplication(ApplicationDto app);

}
