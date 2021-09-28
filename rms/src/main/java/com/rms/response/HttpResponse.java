package com.rms.response;

import lombok.Getter;
import lombok.Setter;

// HTTP RESPONSE FORMAT

@Getter 
@Setter
public class HttpResponse{

	private int statusCode;
	private String message;
	private Object data;
	
	public HttpResponse(int statusCode, String message, Object data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public HttpResponse(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	
}