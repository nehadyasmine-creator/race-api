package com.takima.race.race.controllers;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceService;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.registration.services.RegistrationService;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/races")
public class RaceController {
    private final RaceService raceService;
    private final RaceRepository raceRepository;
    private final RegistrationService registrationService;

    public RaceController(RaceService raceService, RaceRepository raceRepository, RegistrationService registrationService) {
        this.raceService = raceService;
        this.raceRepository = raceRepository;
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<Race> getAll(@RequestParam(required = false) String location) {
        if (location != null && !location.isEmpty()) {
            return raceRepository.findByLocation(location);
        }
        return raceService.getAll();
    }

    @GetMapping("/{id}")
    public Race getById(@PathVariable Long id) {
        return raceService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Race create(@RequestBody Race race) {
        return raceService.create(race);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Race update(@PathVariable Long id, @RequestBody Race race) {
        Race existingRace = raceService.getById(id);
        existingRace.setName(race.getName());
        existingRace.setLocation(race.getLocation());
        existingRace.setDate(race.getDate());
        existingRace.setMaxParticipants(race.getMaxParticipants());
        return raceService.update(existingRace);
    }

    @GetMapping("/{id}/participants/count")
    public Long getParticipantsCount(@PathVariable Long id) {
        return registrationService.countRunnerByRaceId(id);
    }

}
