package com.example.demo.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.FeedbackServiceException;
import com.example.demo.exception.TeamsServiceException;
import com.example.demo.exception.TopicsServiceException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(exception = FeedbackServiceException.class)
	ResponseEntity<String> handleFeedbackServiceException(FeedbackServiceException fse) {
		return new ResponseEntity<String>(fse.getMessage(), fse.getHttpStatus());
	}

	@ExceptionHandler(exception = TeamsServiceException.class)
	ResponseEntity<String> handleTeamsServiceException(TeamsServiceException tse) {
		return new ResponseEntity<String>(tse.getMessage(), tse.getHttpStatus());
	}

	@ExceptionHandler(exception = TopicsServiceException.class)
	ResponseEntity<String> handleTopicsServiceException(TopicsServiceException tse) {
		return new ResponseEntity<String>(tse.getMessage(), tse.getHttpStatus());
	}

	@ExceptionHandler(exception = Exception.class)
	ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
