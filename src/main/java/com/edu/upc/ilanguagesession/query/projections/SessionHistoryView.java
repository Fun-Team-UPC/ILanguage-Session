package com.edu.upc.ilanguagesession.query.projections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionHistoryView {
    @Id @GeneratedValue
    private long sessionHistoryId;
    private String sessionId;
    private LocalDate startAt;
    private LocalDate endAt;
    private String link;
    private String state;
    private String topic;
    private String information;
    private Instant createdAt;

    public SessionHistoryView(String sessionId, LocalDate startAt, LocalDate endAt, String link, String state, String topic, String information, Instant createdAt) {
        this.sessionId = sessionId;
        this.startAt = startAt;
        this.endAt = endAt;
        this.link = link;
        this.state = state;
        this.topic = topic;
        this.information = information;
        this.createdAt = createdAt;
    }

    public SessionHistoryView(SessionHistoryView sessionHistoryView){
        this.sessionId = sessionHistoryView.getSessionId();
        this.startAt = sessionHistoryView.getStartAt();
        this.endAt = sessionHistoryView.getEndAt();
        this.link = sessionHistoryView.getLink();
        this.state = sessionHistoryView.getState();
        this.topic = sessionHistoryView.getTopic();
        this.information = sessionHistoryView.getInformation();
        this.createdAt = sessionHistoryView.getCreatedAt();
    }
}
