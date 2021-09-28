package com.edu.upc.ilanguagesession.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface SessionInfraRepository extends JpaRepository<SessionInfra, String> {

    Optional<SessionInfra> getLinkBySessionId(String sessionId);

    @Query("SELECT s FROM SessionInfra s WHERE s.topic = ?1")
    public Optional<SessionInfra> findByName(String name);
}
