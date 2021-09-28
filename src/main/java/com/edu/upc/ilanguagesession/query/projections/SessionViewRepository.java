package com.edu.upc.ilanguagesession.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionViewRepository extends JpaRepository<SessionView, String> {
	Optional<SessionView> getByLink(String link);

	@Query(value = "SELECT * FROM session_view WHERE session_id <> :sessionId AND link = :link", nativeQuery = true)
	Optional<SessionView> getBySessionIdAndLink(String sessionId, String link);
}
