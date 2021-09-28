package com.edu.upc.ilanguagesession.command.application.dto;

import java.time.LocalDate;

public class RegisterSessionRequestDto {
	private String sessionId;
	private LocalDate startAt;
	private LocalDate endAt;
	private String link;
	private String state;
	private String topic;
	private String information;

	public String getSessionId() {
		return sessionId;
	}

	public LocalDate getStartAt() {
		return startAt;
	}

	public LocalDate getEndAt() {
		return endAt;
	}

	public String getLink() {
		return link;
	}

	public String getState() {
		return state;
	}

	public String getTopic() {
		return topic;
	}

	public String getInformation() {
		return information;
	}

	public void setSessionId(String sessionId){
		this.sessionId =sessionId;
	}

}
