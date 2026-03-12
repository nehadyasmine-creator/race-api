package com.takima.race.registration.repositories;

import com.takima.race.registration.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    long countByRaceId(Long race_id);
    java.util.List<Registration> findByRaceId(Long race_id);
    java.util.List<Registration> findByRunnerId(Long runner_id);
    boolean existsByRaceIdAndRunnerId(Long raceId, Long runnerId);
}
