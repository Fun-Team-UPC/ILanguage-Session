package com.edu.upc.ilanguagesession.command.application.dto.request;

import lombok.Value;

import java.time.LocalDate;

@Value
public class RegisterSessionRequest {
	private LocalDate startAt;
	private LocalDate endAt;
	private String link;
	private String state;
	private String topic;
	private String information;
}
