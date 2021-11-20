package com.edu.upc.ilanguagesession;

import com.edu.upc.ilanguagesession.command.application.services.SessionAplicationService;
import com.edu.upc.ilanguagesession.command.infra.SessionInfra;
import com.edu.upc.ilanguagesession.command.infra.SessionInfraRepository;
//import contracts.events.SessionRegistered;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ILanguageSessionApplicationTests {

    @MockBean
    private SessionInfraRepository sessionInfraRepository;

    private SessionAplicationService sessionAplicationService;

    @BeforeEach()
    public void setUp(){
        //fixture = new AggregateTestFixture<Session>(Session.class);
    }

    @Test
    @DisplayName("Get session by name with valid name then return true")
    public void whenGetSessionByNameWithValidNameThenReturnsSubscription(){
        //Arrange
        String link = "FullPass";
        SessionInfra sessionInfra  = new SessionInfra();
        sessionInfra.setLink(link);
        sessionInfra.setSessionId("HDUD5FSDFDSFGJ");
        when(sessionInfraRepository.findByLink(link)).thenReturn(Optional.of(sessionInfra));
        Optional<SessionInfra> foundSubscription = sessionInfraRepository.findByLink(link);
        assertThat(foundSubscription.get().getLink()).isEqualTo(sessionInfra.getLink());
    }


//    @Test
//    @DisplayName("Get subscription by name with valid name then return true")
//    public void ssd(){
//        //https://docs.axoniq.io/reference-guide/v/3.3/part-ii-domain-logic/testing
//        /*fixture.given()
//                .when(new RegisterSubscription("sddsadsadsad","sadasd",5,100))
//                .expectSuccessfulHandlerExecution()
//                .expectReturnValue(new RegisterSubscriptionRes("sddsadsadsad","sadasd",5,100));
//               // .expectNoEvents(new SubscriptionRegistered("sddsadsadsad","sadasd", 5,100, Instant.now()));*/
//        apply(new SessionRegistered("sddsadsadsad",null,null,"gg","gg","gg","gg", Instant.now()));
//        assertEquals(1, AggregateLifecycle.isLive());
//
//    }
}
