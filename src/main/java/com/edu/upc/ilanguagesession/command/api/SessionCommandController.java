package com.edu.upc.ilanguagesession.command.api;

import com.edu.upc.ilanguagesession.command.application.dto.request.EditSessionRequest;
import com.edu.upc.ilanguagesession.command.application.dto.response.EditSessionResponse;
import com.edu.upc.ilanguagesession.command.application.dto.request.RegisterSessionRequest;
import com.edu.upc.ilanguagesession.command.application.dto.response.RegisterSessionResponse;
import com.edu.upc.ilanguagesession.command.application.services.SessionAplicationService;

import com.edu.upc.ilanguagesession.ILanguageSessionApplication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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

    @Operation(summary = "Post session", description = "This endpoint is for saving a new Session for Ilanguage Application")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"startAt\": \"10-12-2021 02:30:00\", " +
                                    "\"endAt\": \"10-12-2021 03:30:00\", " +
                                    "\"link\": \"https://us04web.zoom.us/j/79547183125?pwd=NVZGcjdrQjhnR1lTUENCUXBKaDJ0Zz09\", " +
                                    "\"state\": \"Inactive\", " +
                                    "\"topic\": \"English\", " +
                                    "\"information\": \"Verb To Be\", " +
                                    "\"externalToolId\": 1}")
                    }
            )
    )
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterSessionRequest registerSessionRequest){
        try {
            Result<RegisterSessionResponse, Notification> result = sessionAplicationService.register(registerSessionRequest);
                if (result.isSuccess()){
                    return ApiController.created(result.getSuccess());
                }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"sessionId\": 1, " +
                                    "\"startAt\": \"10-12-2021 02:30:00\", " +
                                    "\"endAt\": \"10-12-2021 03:30:00\", " +
                                    "\"state\": \"Inactive\", " +
                                    "\"topic\": \"English\", " +
                                    "\"information\": \"Verb To Be\"}")
                    }
            )
    )
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
