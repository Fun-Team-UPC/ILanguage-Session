package com.edu.upc.ilanguagesession.command.api;

import com.edu.upc.ilanguagesession.command.application.dto.request.EditSessionRequest;
import com.edu.upc.ilanguagesession.command.application.dto.response.EditSessionResponse;
import com.edu.upc.ilanguagesession.command.application.dto.request.RegisterSessionRequest;
import com.edu.upc.ilanguagesession.command.application.dto.response.RegisterSessionResponse;
import com.edu.upc.ilanguagesession.command.application.services.SessionAplicationService;

import com.edu.upc.ilanguagesession.ILanguageSessionApplication;

//import com.edu.upc.ilanguagesession.common.api.ApiController;
//import com.edu.upc.ilanguagesession.common.application.Notification;
//import com.edu.upc.ilanguagesession.common.application.Result;

import pe.com.ilanguage.common.api.ApiController;
import pe.com.ilanguage.common.application.Result;
import pe.com.ilanguage.common.application.Notification;

import io.swagger.annotations.Api;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sessions")
@Api(tags = "Sessions")
public class SessionCommandController {
    private final SessionAplicationService sessionAplicationService;
    private final CommandGateway commandGateway;

    public SessionCommandController(SessionAplicationService sessionAplicationService, CommandGateway commandGateway) {
        this.sessionAplicationService = sessionAplicationService;
        this.commandGateway = commandGateway;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterSessionRequest registerSessionRequest){
        try {
            Result<RegisterSessionResponse, Notification> result = sessionAplicationService.register(registerSessionRequest);
                if (result.isSuccess()){
                    return ApiController.created(result.getSuccess());
                }
//            result.getFailure().getErrors()
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/{sessionId}")
    public ResponseEntity<Object> edit(@PathVariable("sessionId") String sessionId, @RequestBody EditSessionRequest editSessionRequest){
        try {
            editSessionRequest.setSessionId(sessionId);
            Result<EditSessionResponse, Notification> result = sessionAplicationService.edit(editSessionRequest);
            if (result.isSuccess()){
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        }catch (AggregateNotFoundException exception){
            return ApiController.notFound();
        }catch (Exception e){
            return ApiController.serverError();
        }
    }
}
