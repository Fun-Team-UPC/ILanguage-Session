package com.edu.upc.ilanguagesession.command.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EditSessionRequest {
	private String sessionId;
	private LocalDate startAt;
	private LocalDate endAt;
	private String link;
	private String state;
	private String topic;
	private String information;
}
