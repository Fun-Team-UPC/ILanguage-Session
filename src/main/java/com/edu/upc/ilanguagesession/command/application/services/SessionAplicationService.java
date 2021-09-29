package com.edu.upc.ilanguagesession.command.application.services;

import com.edu.upc.ilanguagesession.command.application.dto.request.EditSessionRequest;
import com.edu.upc.ilanguagesession.command.application.dto.request.RegisterSessionRequest;
import com.edu.upc.ilanguagesession.command.application.dto.response.EditSessionResponse;
import com.edu.upc.ilanguagesession.command.application.dto.response.RegisterSessionResponse;
import com.edu.upc.ilanguagesession.command.application.validators.EditSessionValidator;
import com.edu.upc.ilanguagesession.command.application.validators.RegisterSessionValidator;
import com.edu.upc.ilanguagesession.command.domain.contrats.commands.EditSesssion;
import com.edu.upc.ilanguagesession.command.domain.contrats.commands.RegisterSession;
import com.edu.upc.ilanguagesession.command.infra.SessionInfraRepository;
import com.edu.upc.ilanguagesession.common.application.Notification;
import com.edu.upc.ilanguagesession.common.application.Result;
import com.edu.upc.ilanguagesession.common.application.ResultType;
import org.axonframework.commandhandling.gateway.CommandGateway;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class SessionAplicationService {

    private final RegisterSessionValidator registerSessionValidator;
    private final EditSessionValidator editSessionValidator;
    private final CommandGateway commandGateway;
    private final SessionInfraRepository sessionInfraRepository;

    public SessionAplicationService(RegisterSessionValidator registerSessionValidator, EditSessionValidator editSessionValidator, CommandGateway commandGateway, SessionInfraRepository sessionInfraRepository) {
        this.registerSessionValidator = registerSessionValidator;
        this.editSessionValidator = editSessionValidator;
        this.commandGateway = commandGateway;
        this.sessionInfraRepository = sessionInfraRepository;
    }

    public Result<RegisterSessionResponse, Notification> register(RegisterSessionRequest registerSessionRequest) throws Exception{
        Notification notification = this.registerSessionValidator.validate(registerSessionRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String sessionId = UUID.randomUUID().toString();
        RegisterSession registerSession = new RegisterSession(
                sessionId,
                registerSessionRequest.getStartAt(),
                registerSessionRequest.getEndAt(),
                registerSessionRequest.getLink().trim(),
                registerSessionRequest.getState().trim(),
                registerSessionRequest.getTopic().trim(),
                registerSessionRequest.getInformation().trim()

        );
        CompletableFuture<Object> future = commandGateway.send(registerSession);
        CompletableFuture<ResultType> futureResult =future.handle((ok, ex) -> (ex != null ? ResultType.FAILURE : ResultType.SUCCESS));
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE){
            throw new Exception();
        }
        RegisterSessionResponse registerSessionResponseDTO = new RegisterSessionResponse(
                registerSession.getSessionId(),
                registerSession.getStartAt(),
                registerSession.getEndAt(),
                registerSession.getLink(),
                registerSession.getState(),
                registerSession.getTopic(),
                registerSession.getInformation()
        );
        return Result.success(registerSessionResponseDTO);
    }

    public Result<EditSessionResponse, Notification> edit(EditSessionRequest editSessionRequest) throws Exception {
        Notification notification = this.editSessionValidator.validate(editSessionRequest);
        if (notification.hasErrors()){
            return Result.failure(notification);
        }
        EditSessionResponse editSesssion =new EditSessionResponse(
                editSessionRequest.getSessionId().trim(),
                editSessionRequest.getStartAt(),
                editSessionRequest.getEndAt(),
                editSessionRequest.getLink().trim(),
                editSessionRequest.getState().trim(),
                editSessionRequest.getTopic().trim(),
                editSessionRequest.getInformation().trim()
        );

        CompletableFuture<Object> future = commandGateway.send(editSesssion);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
            EditSessionResponse editSessionResponse = new EditSessionResponse(
                editSesssion.getSessionId(),
                editSesssion.getStartAt(),
                editSesssion.getEndAt(),
                editSesssion.getLink(),
                editSesssion.getState(),
                editSesssion.getTopic(),
                editSesssion.getInformation()
        );
        return Result.success(editSessionResponse);
    }
}
