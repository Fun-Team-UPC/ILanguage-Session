package com.edu.upc.ilanguagesession;

import com.edu.upc.ilanguagesession.command.domain.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.axonframework.eventsourcing.eventstore.EventStoreException;

import ILanguage.session.contracts.commands.RegisterSession;
import ILanguage.session.contracts.commands.SessionRegistered;


import java.time.Instant;
import java.util.UUID;


public class RegisterEventTest {

    private FixtureConfiguration<Session> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(Session.class);
    }

    @Test
    public void createRegister() throws Exception{
        String userId = UUID.randomUUID().toString();
        Instant ocurredOn = Instant.now();
        RegisterSession registerSession = new RegisterSession(userId,"testt222","SWAGtest","75104901",ocurredOn);

        SessionRegistered sessionRegistered = new SessionRegistered(registerSession.getUserId(),registerSession.getFirstName(),registerSession.getLastName(),registerSession.getDni(),ocurredOn);

        fixture.given()
                .when(registerSession)
                .expectEvents(sessionRegistered);
    }

}
