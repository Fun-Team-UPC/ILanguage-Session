package com.edu.upc.ilanguagesession.command.application.dto;

public class RegisterSessionOkResponseDto {
	private String sessionId;

	public RegisterSessionOkResponseDto(String sessionId)
	{
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}
}
