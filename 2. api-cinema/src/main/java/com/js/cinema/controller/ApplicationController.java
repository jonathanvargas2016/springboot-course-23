package com.js.cinema.controller;

import com.js.cinema.domain.Application;
import com.js.cinema.output.Answer;
import com.js.cinema.service.dto.ApplicationDto;
import com.js.cinema.service.impl.ApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("application")
public class ApplicationController {


    @Autowired
    private ApplicationServiceImpl applicationService;

    @PostMapping()
    public ResponseEntity<?> saveApplication(@RequestBody ApplicationDto application){
        Answer answer = new Answer();
        try{
            Application res = this.applicationService.saveApplication(application);
            if(res != null){
                answer.setMessage("Application was created");
                answer.setDataAnswer(res);
            }
            return new ResponseEntity<>(answer, HttpStatus.OK);
        }catch (RuntimeException exc){
            answer.setMessage(exc.getMessage());
            answer.setDataAnswer(null);
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }






}
