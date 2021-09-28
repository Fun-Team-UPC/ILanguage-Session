package com.edu.upc.ilanguagesession.command.application.dto;

public class EditSessionErrorResponseDto {
	private String message;

	public EditSessionErrorResponseDto()
	{
		this.message = "Error Editing Customer";
	}

	public String getMessage() {
		return message;
	}
}
