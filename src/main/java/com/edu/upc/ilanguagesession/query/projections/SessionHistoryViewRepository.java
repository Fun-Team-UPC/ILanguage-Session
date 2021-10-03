package com.edu.upc.ilanguagesession.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionHistoryViewRepository extends JpaRepository<SessionHistoryView, String> {
    @Query(value = "SELECT * FROM session_history_view WHERE session_history_id = (SELECT MAX(session_history_id) FROM session_history_view WHERE session_id = :sessionId)", nativeQuery = true)
    Optional<SessionHistoryView> getLastBySessionId(String sessionId);

    @Query(value = "SELECT * FROM session_history_view WHERE session_id = :sessionId ORDER BY created_at", nativeQuery = true)
    List<SessionHistoryView> getHistoryBySessionId(String sessionId);
}
