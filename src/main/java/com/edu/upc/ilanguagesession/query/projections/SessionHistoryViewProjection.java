package com.edu.upc.ilanguagesession.query.projections;

import com.edu.upc.ilanguagesession.command.domain.SessionStatus;
import com.edu.upc.ilanguagesession.command.domain.contrats.events.SessionEdited;
import com.edu.upc.ilanguagesession.command.domain.contrats.events.SessionRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class SessionHistoryViewProjection {
    private final SessionHistoryViewRepository sessionHistoryViewRepository;

    public SessionHistoryViewProjection(SessionHistoryViewRepository sessionHistoryViewRepository) {
        this.sessionHistoryViewRepository = sessionHistoryViewRepository;
    }

    @EventHandler
    public void on(SessionRegistered event, @Timestamp Instant timestamp) {
        SessionHistoryView sessionHistoryView = new SessionHistoryView(event.getSessionId(), event.getStartAt(),event.getEndAt(),event.getLink(),event.getState(),event.getTopic(),event.getInformation(), event.getOccurredOn());
        sessionHistoryViewRepository.save(sessionHistoryView);
    }

    @EventHandler
    public void on(SessionEdited event, @Timestamp Instant timestamp) {
        Optional<SessionHistoryView> sessionHistoryViewOptional = sessionHistoryViewRepository.getLastBySessionId(event.getSessionId().toString());
        if (sessionHistoryViewOptional.isPresent()) {
            SessionHistoryView sessionHistoryView = sessionHistoryViewOptional.get();
            sessionHistoryView = new SessionHistoryView(sessionHistoryView);
            sessionHistoryView.setStartAt(event.getStartAt());
            sessionHistoryView.setEndAt(event.getEndAt());
            sessionHistoryView.setTopic(event.getLink());
            sessionHistoryView.setState(event.getState());
            sessionHistoryView.setTopic(event.getTopic());
            sessionHistoryView.setInformation(event.getInformation());
            sessionHistoryView.setCreatedAt(event.getOccurredOn());
            sessionHistoryViewRepository.save(sessionHistoryView);
        }
    }
}
