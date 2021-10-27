package com.edu.upc.ilanguagesession.command.application.dto.request;

import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

@Value
@Getter
public class RegisterSessionRequest {
	private LocalDate startAt;
	private LocalDate endAt;
	private String link;
	private String state;
	private String topic;
	private String information;
}
