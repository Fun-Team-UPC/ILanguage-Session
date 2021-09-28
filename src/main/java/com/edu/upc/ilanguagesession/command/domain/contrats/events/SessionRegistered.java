package com.edu.upc.ilanguagesession.command.domain.contrats.events;

import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;

@Value
public class SessionRegistered {
    String sessionId;
    LocalDate startAt;
    LocalDate endAt;
    String link;
    String state;
    String topic;
    String information;
    Instant occurredOn;
}
