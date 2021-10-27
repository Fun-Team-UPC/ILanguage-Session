package com.edu.upc.ilanguagesession.command.application.handlers;

import com.edu.upc.ilanguagesession.command.infra.SessionInfra;
import com.edu.upc.ilanguagesession.command.infra.SessionInfraRepository;
import contracts.events.SessionEdited;
import contracts.events.SessionRegistered;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("sessionInfra")
public class SessionEventHandler {
    private final SessionInfraRepository sessionInfraRepository;

    public SessionEventHandler(SessionInfraRepository sessionRepository) {
        this.sessionInfraRepository = sessionRepository;
    }

    @EventHandler
    public void on(SessionRegistered event) {
        sessionInfraRepository.save(new SessionInfra(
                event.getSessionId(),
                event.getStartAt(),
                event.getEndAt(),
                event.getLink(),
                event.getState(),
                event.getTopic(),
                event.getInformation()
        ));
    }

    @EventHandler
    public void on(SessionEdited event) {
        sessionInfraRepository.save(new SessionInfra(event.getLink(), event.getSessionId()));
    }
}
