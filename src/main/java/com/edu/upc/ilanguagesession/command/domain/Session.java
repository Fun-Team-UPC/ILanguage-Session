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

    public Session(){}

    @CommandHandler
    public Session(RegisterSession command) {
        Instant now =Instant.now();
        apply(
                new SessionRegistered(
                        command.getSessionId(),
                        command.getStartAt(),
                        command.getEndAt(),
                        command.getLink(),
                        command.getState(),
                        command.getTopic(),
                        command.getInformation(),
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
                        command.getLink(),
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
    }


    @EventHandler
    protected void on(SessionEdited event) {
        topic =event.getTopic();
        information =event.getInformation();
        state =event.getState();
    }
}
