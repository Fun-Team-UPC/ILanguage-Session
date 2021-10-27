package com.edu.upc.ilanguagesession.command.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionInfra {
    @Id
    private String sessionId;
    private LocalDate startAt;
    private LocalDate endAt;
    private String link;
    private String state;
    private String topic;
    private String information;


    public SessionInfra(String link, String sessionId) {
        this.sessionId=sessionId;
        this.link = link;
    }
}
