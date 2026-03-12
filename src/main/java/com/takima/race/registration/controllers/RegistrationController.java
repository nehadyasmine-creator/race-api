package com.takima.race.registration.controllers;

import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.services.RegistrationService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/races")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/{race_id}/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public Registration create(@RequestBody Registration registration, @PathVariable Long race_id) {
        return registrationService.create(registration, race_id);
    }

    @GetMapping("/{race_id}/registrations")
    public List<Registration> list(@PathVariable Long race_id) {
        return registrationService.list(race_id);
    }
}