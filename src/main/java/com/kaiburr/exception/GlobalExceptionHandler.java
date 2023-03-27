package com.kaiburr.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kaiburr.model.ErrorsDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorsDetails errorsDetails = new ErrorsDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorsDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorsDetails errorsDetails = new ErrorsDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorsDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
