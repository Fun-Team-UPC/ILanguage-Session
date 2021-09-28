package com.edu.upc.ilanguagesession.command.application.dto;

public class RegisterSessionErrorResponseDto {
	private String message;

	public RegisterSessionErrorResponseDto()
	{
		this.message = "Error Registering Customer";
	}

	public String getMessage() {
		return message;
	}
}
