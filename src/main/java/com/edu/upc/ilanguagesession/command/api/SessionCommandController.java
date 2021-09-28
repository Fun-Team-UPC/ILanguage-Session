package com.edu.upc.ilanguagesession.command.api;

import com.edu.upc.ilanguagesession.command.application.dto.RegisterSessionOkResponseDto;
import com.edu.upc.ilanguagesession.command.application.dto.RegisterSessionRequestDto;
import com.edu.upc.ilanguagesession.command.domain.contrats.commands.RegisterSession;
import com.edu.upc.ilanguagesession.command.infra.SessionInfra;
import com.edu.upc.ilanguagesession.command.infra.SessionInfraRepository;
import io.swagger.annotations.Api;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/sessions")
@Api(tags = "Customers")
public class SessionCommandController {
    private final CommandGateway commandGateway;
    private final SessionInfraRepository sessionRepository;

    public SessionCommandController(CommandGateway commandGateway, SessionInfraRepository sessionRepository) {
        this.commandGateway = commandGateway;
        this.sessionRepository = sessionRepository;
    }

    //    public SessionCommandController(CommandGateway _commandGateway, SessionInfraRepository sessionRepository) {
//        this.commandGateway = _commandGateway;
//        this.sessionRepository = sessionRepository;
//    }

    @PostMapping("")
    public ResponseEntity<Object> register(@RequestBody RegisterSessionRequestDto registerSessionRequestDto) {
        Optional<SessionInfra> customerDniOptional = sessionRepository.findById(registerSessionRequestDto.getLink());
        if (customerDniOptional.isPresent()) {
            return new ResponseEntity(new RegisterSessionRequestDto(), HttpStatus.BAD_REQUEST);
        }
        String sessionId = UUID.randomUUID().toString();
        RegisterSession registerSession = new RegisterSession(
                sessionId,
                registerSessionRequestDto.getStartAt(),
                registerSessionRequestDto.getEndAt(),
                registerSessionRequestDto.getLink(),
                registerSessionRequestDto.getState(),
                registerSessionRequestDto.getTopic(),
                registerSessionRequestDto.getInformation()
        );
        CompletableFuture<Object> future = commandGateway.send(registerSession);
        CompletableFuture<Object> futureResponse = future.handle((ok, ex) -> {
            if (ex != null) {
                return new RegisterSessionRequestDto();
            }
            return new RegisterSessionOkResponseDto(sessionId);
        });
        try {
            Object response = (Object)futureResponse.get();
            if (response instanceof RegisterSessionOkResponseDto) {
                return new ResponseEntity(response, HttpStatus.CREATED);
            }
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@PutMapping("/{customerId}")
    public ResponseEntity<Object> edit(
            @PathVariable("customerId") String customerId,
            @RequestBody EditCustomerRequestDto editCustomerRequestDto) {
        Optional<CustomerDni> customerDniOptional = customerDniRepository.getByDniForDistinctCustomerId(editCustomerRequestDto.getDni(), customerId);
        if (customerDniOptional.isPresent()) {
            return new ResponseEntity(new EditCustomerErrorResponseDto(), HttpStatus.BAD_REQUEST);
        }
        editCustomerRequestDto.setCustomerId(customerId);
        EditCustomer editCustomer = new EditCustomer(
                editCustomerRequestDto.getCustomerId(),
                editCustomerRequestDto.getFirstName(),
                editCustomerRequestDto.getLastName(),
                editCustomerRequestDto.getDni()
        );
        CompletableFuture<Object> future = commandGateway.send(editCustomer);
        CompletableFuture<Object> futureResponse = future.handle((ok, ex) -> {
            if (ex != null) {
                return new EditCustomerErrorResponseDto();
            }
            return new EditCustomerOkResponseDto(customerId);
        });
        try {
            Object response = (Object)futureResponse.get();
            if (response instanceof EditCustomerOkResponseDto) {
                return new ResponseEntity(response, HttpStatus.OK);
            }
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
