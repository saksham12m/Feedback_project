package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class FeedbackServiceException extends RuntimeException {

	private String message;
	private HttpStatus httpStatus;

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public FeedbackServiceException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}
}
