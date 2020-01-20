package com.kevinkoleck.io.controller;

import com.kevinkoleck.io.model.Person;
import com.kevinkoleck.io.service.ApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kevinkoleck on 1/19/20.
 */
@RestController
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService){
        this.applicationService = applicationService;
    }

    @GetMapping("/person")
    public List<Person> getPersons() throws Exception{
        return applicationService.getListOfPeople().get();
    }
}
