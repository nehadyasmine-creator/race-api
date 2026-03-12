package com.takima.race.registration.services;

import com.takima.race.race.entities.Race;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.runner.repositories.RunnerRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.race.repositories.RaceRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.time.LocalDate;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RaceRepository raceRepository;
    private final RunnerRepository runnerRepository;


    public RegistrationService(RegistrationRepository registrationRepository, RaceRepository raceRepository, RunnerRepository runnerRepository  ) {
        this.registrationRepository = registrationRepository;
        this.raceRepository = raceRepository;
        this.runnerRepository = runnerRepository;
    }

    public Registration create(Registration registration, Long race_id) {
        if (registrationRepository.existsByRaceIdAndRunnerId(race_id, registration.getRunnerId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Runner %s is already registered for race %s", registration.getRunnerId(), race_id)
            );
        }

        Race race = raceRepository.findById(race_id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Race %s not found", race_id)
            ));
        
        Runner runner = runnerRepository.findById(registration.getRunnerId())
        .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Runner %s not found", registration.getRunnerId())
        ));

        long currentRegistrations = registrationRepository.countByRaceId(race_id);
        if (currentRegistrations >= race.getMaxParticipants()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Race %s is full", race_id)
            );
        }
        registration.setRaceId(race_id);
        registration.setRegistrationDate(LocalDate.now());
        return registrationRepository.save(registration);
    }

    public List<Registration> list(Long race_id) {
        return registrationRepository.findByRaceId(race_id);
    }
    
    public Long countRunnerByRaceId(Long race_id) {
        return registrationRepository.countByRaceId(race_id);
    }

    public List<Registration> getRegistrations(Long runner_id) {
        return registrationRepository.findByRunnerId(runner_id);
    }
}