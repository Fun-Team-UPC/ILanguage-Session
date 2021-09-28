package com.edu.upc.ilanguagesession.command.application.handlers;

import com.edu.upc.ilanguagesession.command.domain.Session;
import com.edu.upc.ilanguagesession.command.domain.contrats.events.SessionEdited;
import com.edu.upc.ilanguagesession.command.domain.contrats.events.SessionRegistered;
import com.edu.upc.ilanguagesession.command.infra.SessionInfra;
import com.edu.upc.ilanguagesession.command.infra.SessionInfraRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("customerDni")
public class SessionEventHandler {
    private final SessionInfraRepository sessionRepository;

    public SessionEventHandler(SessionInfraRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @EventHandler
    public void on(SessionRegistered event) {
        sessionRepository.save(new SessionInfra(event.getLink(), event.getSessionId()));
    }

    @EventHandler
    public void on(SessionEdited event) {
        Optional<SessionInfra> SessionInfraOptional = sessionRepository.getLinkBySessionId(event.getSessionId());
        SessionInfraOptional.ifPresent(sessionRepository::delete);
        sessionRepository.save(new SessionInfra(event.getLink(), event.getSessionId()));
    }
}
