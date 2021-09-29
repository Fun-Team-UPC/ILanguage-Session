package com.edu.upc.ilanguagesession.command.application.validators;

import com.edu.upc.ilanguagesession.command.application.dto.request.RegisterSessionRequest;
import com.edu.upc.ilanguagesession.command.infra.SessionInfra;
import com.edu.upc.ilanguagesession.command.infra.SessionInfraRepository;
import com.edu.upc.ilanguagesession.common.application.Notification;
import org.springframework.stereotype.Component;
//Cambiar esta dependency x la common!!
import java.util.Optional;

@Component
public class RegisterSessionValidator {
    public final SessionInfraRepository sessionInfraRepository;

    public RegisterSessionValidator(SessionInfraRepository sessionInfraRepository){
        this.sessionInfraRepository = sessionInfraRepository;
    }

    public Notification validate(RegisterSessionRequest registerSessionRequest){
        Notification notification = new Notification();
        String topic = registerSessionRequest.getTopic().trim();
        if (topic.isEmpty()) {
            notification.addError("Session Topic is required");
        }
        String information = registerSessionRequest.getTopic().trim();
        if (information.isEmpty()) {
            notification.addError("Session Information is required");
        }
        String link = registerSessionRequest.getTopic().trim();
        if (link.isEmpty()) {
            notification.addError("Session Link is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        Optional<SessionInfra> sessionLinkOptional = sessionInfraRepository.findById(link);
        if (sessionLinkOptional.isPresent()) {
            notification.addError("Session link is taken");
        }
        return notification;
    }
}
