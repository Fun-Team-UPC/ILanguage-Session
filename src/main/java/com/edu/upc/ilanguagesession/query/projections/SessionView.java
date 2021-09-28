package com.edu.upc.ilanguagesession.query.projections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SessionView {
	@Id
	private String sessionId;
	private LocalDate startAt;
	private LocalDate endAt;
	private String link;
	private String state;
	private String topic;
	private String information;
	private Instant updateddAt;

	public SessionView(String sessionId, LocalDate startAt, LocalDate endAt, String link, String state, String topic, String information, Instant updateddAt) {
		this.sessionId = sessionId;
		this.startAt = startAt;
		this.endAt = endAt;
		this.link = link;
		this.state = state;
		this.topic = topic;
		this.information = information;
		this.updateddAt = updateddAt;
	}
}
