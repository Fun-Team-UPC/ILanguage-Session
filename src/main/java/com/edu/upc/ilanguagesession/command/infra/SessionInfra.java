package com.edu.upc.ilanguagesession.command.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class SessionInfra {
    @Id
    private String sessionId;
    private LocalDate startAt;
    private LocalDate endAt;
    private String link;
    private String state;
    @NotBlank(message ="Topic is mandatory")
    private String topic;
    @NotBlank(message ="Information is mandatory")
    private String information;

    public SessionInfra() {
    }

    public SessionInfra(String link, String sessionId) {
        this.link = link;
        this.sessionId = sessionId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
