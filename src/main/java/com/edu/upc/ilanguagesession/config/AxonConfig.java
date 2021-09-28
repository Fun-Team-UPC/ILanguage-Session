package com.edu.upc.ilanguagesession.config;

import com.edu.upc.ilanguagesession.command.domain.Session;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.axonframework.modelling.command.Repository;

@Configuration
public class AxonConfig {

    @Bean
    public Repository<Session>eventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(Session.class)
                .eventStore(eventStore)
                .build();
    }
}
