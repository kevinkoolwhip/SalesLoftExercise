package com.kevinkoleck.io.controller;

import com.kevinkoleck.io.model.DuplicateEmail;
import com.kevinkoleck.io.model.EmailAndUniqueCharacterCount;
import com.kevinkoleck.io.model.Person;
import com.kevinkoleck.io.service.ApplicationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kevinkoleck on 1/19/20.
 */
@RestController
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @CrossOrigin
    @GetMapping("/person")
    public List<Person> getPersons() {
        return applicationService.getListOfPeople().get();
    }

    @CrossOrigin
    @GetMapping("/person/unique")
    public List<EmailAndUniqueCharacterCount> getEmailAndUniqueCharacterAndCount() {
        return applicationService.getEmailAndUniqueCharacterAndCount();
    }

    @CrossOrigin
    @GetMapping("/email/unique")
    public List<DuplicateEmail> getDuplicateEmail() {
        return applicationService.getDuplicateEmail();
    }
}
