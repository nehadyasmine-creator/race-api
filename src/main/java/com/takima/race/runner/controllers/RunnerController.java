package com.takima.race.runner.controllers;

import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.services.RunnerService;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.services.RegistrationService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/runners")
public class RunnerController {
    private final RunnerService runnerService;
    private final RegistrationService registrationService;

    public RunnerController(RunnerService runnerService, RegistrationService registrationService) {
        this.runnerService = runnerService;
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<Runner> getAll() {
        return runnerService.getAll();
    }

    @GetMapping("/{id}")
    public Runner getById(@PathVariable Long id) {
        return runnerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Runner create(@RequestBody Runner runner) {
        return runnerService.create(runner);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        runnerService.delete(id);
    }

    @GetMapping("/{id}/races")
    public List<Registration> getRegistrations(@PathVariable Long id) {
        return registrationService.getRegistrations(id);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Runner update(@PathVariable Long id, @RequestBody Runner runner) {
        Runner existingRunner = runnerService.getById(id);
        existingRunner.setFirstName(runner.getFirstName());
        existingRunner.setLastName(runner.getLastName());
        existingRunner.setEmail(runner.getEmail()); 
        existingRunner.setAge(runner.getAge());
        return runnerService.update(existingRunner);
    }
}
