package com.edu.upc.ilanguagesession.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionInfraRepository extends JpaRepository<SessionInfra, String> {

    Optional<SessionInfra> getLinkBySessionId(String sessionId);

    @Query(value = "SELECT * FROM session_link WHERE session_id<> :session_id AND link = :link LIMIT 1", nativeQuery = true)
    Optional<SessionInfra> getByLinkForDistinctSessionId(String link, String sessionId);
}
