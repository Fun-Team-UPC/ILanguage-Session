package com.edu.upc.ilanguagesession.command.domain.contrats.commands;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;


@Value
public class EditSesssion {
    @TargetAggregateIdentifier
    String sessionId;
    LocalDate startAt;
    LocalDate endAt;
    String link;
    String state;
    String topic;
    String information;
}
