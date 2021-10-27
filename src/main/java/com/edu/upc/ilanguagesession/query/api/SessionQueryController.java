package com.edu.upc.ilanguagesession.query.api;

import com.edu.upc.ilanguagesession.command.domain.Session;
import com.edu.upc.ilanguagesession.query.projections.SessionHistoryView;
import com.edu.upc.ilanguagesession.query.projections.SessionHistoryViewRepository;
import com.edu.upc.ilanguagesession.query.projections.SessionView;
import com.edu.upc.ilanguagesession.query.projections.SessionViewRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessions")
@Api(tags= "Sessions")
public class SessionQueryController {

    private final SessionViewRepository sessionViewRepository;
    private final SessionHistoryViewRepository sessionHistoryViewRepository;

    public SessionQueryController(SessionViewRepository sessionViewRepository, SessionHistoryViewRepository sessionHistoryViewRepository) {
        this.sessionViewRepository = sessionViewRepository;
        this.sessionHistoryViewRepository = sessionHistoryViewRepository;
    }

    @Operation(summary="Get all sessions", description="This endpoint returns all the available sessions for Ilanguage Application", tags = {"Session"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All Session returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Session Not Found", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("")
    @ApiOperation(value = "Get All Sessions", response = List.class)
    public ResponseEntity<List<SessionView>> GetAll() {
        try {
            return new ResponseEntity<List<SessionView>>(sessionViewRepository.findAll(), HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary="Get session by id", description="This endpoint returns an specific session by the given ID Ilanguage Application", tags = {"Session"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="Session returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Session Not Found", content = @Content(mediaType = "application/json"))

    })
    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Session by id", response = SessionView.class)
    public ResponseEntity<SessionView> getById(@PathVariable("id") String id) {
        try {
            Optional<SessionView> sessionViewOptional = sessionViewRepository.findById(id);
            if (sessionViewOptional.isPresent()) {
                return new ResponseEntity<SessionView>(sessionViewOptional.get(), HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary="Get session by name", description="This endpoint returns an specific session by the given ID Ilanguage Application", tags = {"Session"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="Session returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Session Not Found", content = @Content(mediaType = "application/json"))

    })
    @GetMapping(path = "/session/{link}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get session by link", response = SessionView.class)
    public ResponseEntity<SessionView> getByDocument(@PathVariable("dni") String link) {
        try {
            Optional<SessionView> customerViewOptional = sessionViewRepository.getByLink(link);
            if (customerViewOptional.isPresent()) {
                return new ResponseEntity<SessionView>(customerViewOptional.get(), HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary="Get history session by id", description="This endpoint returns the list with the history of an specific session by the given ID Ilanguage Application", tags = {"Session"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="Session returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Session Not Found", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/history/{id}")
    @ApiOperation(value = "Get session history", response = List.class)
    public ResponseEntity<List<SessionHistoryView>> getHistoryById(@PathVariable("id") String id) {
        try {
            List<SessionHistoryView> sessions = sessionHistoryViewRepository.getHistoryBySessionId(id);
            return new ResponseEntity<List<SessionHistoryView>>(sessions, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
