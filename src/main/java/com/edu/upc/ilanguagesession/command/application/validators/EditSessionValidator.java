package com.edu.upc.ilanguagesession.command.application.validators;

import com.edu.upc.ilanguagesession.command.application.dto.request.EditSessionRequest;
import com.edu.upc.ilanguagesession.command.domain.Session;
import com.edu.upc.ilanguagesession.command.infra.SessionInfra;
import com.edu.upc.ilanguagesession.command.infra.SessionInfraRepository;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;

import org.axonframework.modelling.command.Repository;
import pe.com.ilanguage.common.application.Notification;

//Cambiar esta dependency x la common!!
import java.util.Optional;

public class EditSessionValidator {
    private final SessionInfraRepository sessionInfraRepository;
    private final Repository<Session> sessionRepository;

    public EditSessionValidator(SessionInfraRepository sessionInfraRepository, Repository<Session> sessionRepository){
        this.sessionInfraRepository= sessionInfraRepository;
        this.sessionRepository = sessionRepository;
    }

    public Notification validate(EditSessionRequest editSessionRequest){
        Notification notification = new Notification();
        String sessionId = editSessionRequest.getSessionId().trim();
        if(sessionId.isEmpty()){
            notification.addError("Session id is required");
        }
        loadSessionAggregate(sessionId);

        String topic = editSessionRequest.getTopic().trim();
        if(topic.isEmpty()){notification.addError("topic is required");}
        String information = editSessionRequest.getTopic().trim();
        if(information.isEmpty()){notification.addError("information is required");}
        String state = editSessionRequest.getTopic().trim();
        if(state.isEmpty()){notification.addError("state is required");}
        String link = editSessionRequest.getTopic().trim();
        if(link.isEmpty()){notification.addError("link is required");}

        Optional<SessionInfra> sessionInfra = sessionInfraRepository.getByLinkForDistinctSessionId(link, sessionId);
        if (sessionInfra.isPresent()){
            notification.addError("Link is taken");
        }

        return notification;
    }

    private void loadSessionAggregate(String sessionId){
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            sessionRepository.load(sessionId);
            unitOfWork.commit();
        }catch (AggregateNotFoundException ex){
            unitOfWork.commit();
            throw ex;
        }catch (Exception ex){
            if(unitOfWork != null) unitOfWork.rollback();
        }
    }
}
