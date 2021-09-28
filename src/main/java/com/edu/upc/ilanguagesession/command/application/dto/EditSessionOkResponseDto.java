package com.edu.upc.ilanguagesession.command.application.dto;

public class EditSessionOkResponseDto {
	private String sessionId;

	public EditSessionOkResponseDto(String customerId)
	{
		this.sessionId = customerId;
	}

	public String getSessionId() {
		return sessionId;
	}
}
