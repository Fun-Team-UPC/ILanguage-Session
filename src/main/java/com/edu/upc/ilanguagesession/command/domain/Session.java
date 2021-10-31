package com.edu.upc.ilanguagesession.command.domain;


import contracts.commands.EditSesssion;
import contracts.commands.RegisterSession;
import contracts.events.SessionEdited;
import contracts.events.SessionRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDate;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Session {
    @AggregateIdentifier
    private String sessionId;
    private LocalDate startAt;
    private LocalDate endAt;
    private String link;
    private String state;
    private String topic;
    private String information;
    private String externalToolId;

    public Session(){}

    @CommandHandler
    public Session(RegisterSession registerSession) {
        Instant now =Instant.now();
        apply(
                new SessionRegistered(
                        registerSession.getSessionId(),
                        registerSession.getStartAt(),
                        registerSession.getEndAt(),
                        registerSession.getLink(),
                        registerSession.getState(),
                        registerSession.getTopic(),
                        registerSession.getInformation(),
                        registerSession.getExternalToolId(),
                        now
                )
        );
    }

    @CommandHandler
    public void handle(EditSesssion command){
        Instant now =Instant.now();
        apply(
                new SessionEdited(
                        command.getSessionId(),
                        command.getStartAt(),
                        command.getEndAt(),
                        command.getState(),
                        command.getTopic(),
                        command.getInformation(),
                        now
                )
        );
    }

    @EventSourcingHandler
    protected void on(SessionRegistered event) {
        sessionId = event.getSessionId();
        startAt =event.getStartAt();
        endAt =event.getEndAt();
        link =event.getLink();
        state =event.getState();
        topic =event.getTopic();
        information =event.getInformation();
        externalToolId = event.getExternalToolId();
    }


    @EventHandler
    protected void on(SessionEdited event) {
        startAt = event.getStartAt();
        endAt = event.getStartAt();
        state =event.getState();
        topic =event.getTopic();
        information =event.getInformation();
    }
}
