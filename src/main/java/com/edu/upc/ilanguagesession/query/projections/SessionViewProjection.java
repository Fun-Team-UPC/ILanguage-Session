package com.edu.upc.ilanguagesession.query.projections;

import com.edu.upc.ilanguagesession.command.domain.SessionStatus;
import contracts.events.SessionEdited;
import contracts.events.SessionRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class SessionViewProjection {
	private final SessionViewRepository sessionViewRepository;

	public SessionViewProjection(SessionViewRepository customerViewRepository) {
        this.sessionViewRepository = customerViewRepository;
    }

	@EventHandler
    public void on(SessionRegistered event, @Timestamp Instant timestamp) {
		SessionView sessionView = new SessionView(event.getSessionId(), event.getStartAt(), event.getEndAt(), event.getLink(), event.getTopic(),event.getInformation(), SessionStatus.ACTIVE.toString(), event.getOccurredOn());
		sessionViewRepository.save(sessionView);
    }

	@EventHandler
    public void on(SessionEdited event, @Timestamp Instant timestamp) {
		Optional<SessionView> sessionViewOptional = sessionViewRepository.findById(event.getSessionId().toString());
		if (sessionViewOptional.isPresent()) {
			SessionView sessionView = sessionViewOptional.get();
			sessionView.setStartAt(event.getStartAt());
			sessionView.setEndAt(event.getEndAt());
			sessionView.setTopic(event.getTopic());
			sessionView.setState(event.getState());
			sessionView.setUpdateddAt(event.getOccurredOn());
			sessionViewRepository.save(sessionView);
		}
    }
}
