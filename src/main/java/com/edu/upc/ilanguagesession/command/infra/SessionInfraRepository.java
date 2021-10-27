package com.edu.upc.ilanguagesession.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SessionInfraRepository extends JpaRepository<SessionInfra, String> {

    //!Query refers to the class (MODEL)
    @Query("SELECT s FROM SessionInfra s WHERE s.startAt = ?1")
    public Optional<SessionInfra> findByStartAt(LocalDate startAt);

    @Query("SELECT s FROM SessionInfra s WHERE s.endAt = ?1")
    public Optional<SessionInfra> findByEndAt(LocalDate endAt);

    @Query("SELECT s FROM SessionInfra s WHERE s.link = ?1")
    public Optional<SessionInfra>findByLink(String link);

    @Query("SELECT s FROM SessionInfra s WHERE s.state = ?1")
    public Optional<SessionInfra>findByState(String state);

    @Query("SELECT s FROM SessionInfra s WHERE s.topic = ?1")
    public Optional<SessionInfra>findByTopic(String topic);

    @Query("SELECT s FROM SessionInfra s WHERE s.information = ?1")
    public Optional<SessionInfra>findByInformation(String information);

    @Query("SELECT s FROM SessionInfra s WHERE s.sessionId = ?1")
    public Optional<SessionInfra>findSessionBySessionId(String sessionId);

}
