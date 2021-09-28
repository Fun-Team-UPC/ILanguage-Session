package com.edu.upc.ilanguagesession.command.application.handlers;

import com.edu.upc.ilanguagesession.command.domain.Session;
import com.edu.upc.ilanguagesession.command.domain.contrats.events.SessionEdited;
import com.edu.upc.ilanguagesession.command.domain.contrats.events.SessionRegistered;
import com.edu.upc.ilanguagesession.command.infra.*;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("session")
public class SessionEventHandler {
    private final SessionInfraRepository sessionInfraRepository;

    public SessionEventHandler(SessionInfraRepository sessionRepository) {
        this.sessionInfraRepository = sessionRepository;
    }

    @EventHandler
    public void on(SessionRegistered event) {
        sessionInfraRepository.save(new SessionInfra(event.getLink(),event.getSessionId()));
    }

    @EventHandler
    public void on(SessionEdited event) {
        Optional<SessionInfra> SessionInfraOptional = sessionInfraRepository.getLinkBySessionId(event.getSessionId());
        SessionInfraOptional.ifPresent(sessionInfraRepository::delete);
        sessionInfraRepository.save(new SessionInfra(event.getLink(), event.getSessionId()));
    }
}
