package com.example.RestDemo.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CutomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	final public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
		
//		ExceptionResponse exp;
		ExceptionHandle exceptionHandler = new ExceptionHandle(new Date(), ex.getMessage(), request.getDescription(false));
		System.out.println("Internal Server Error");
		return new ResponseEntity(exceptionHandler, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	final public ResponseEntity<Object> handleAllUserException(UserNotFoundException ex, WebRequest request){
		
		ExceptionHandle exceptionHandler = new ExceptionHandle(new Date(), ex.getMessage(), request.getDescription(false));
		System.out.println("staus error");
		return new ResponseEntity(exceptionHandler, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionHandle exceptionHandler = new ExceptionHandle(new Date(), ex.getMessage(), ex.getBindingResult().toString());
		
		return new ResponseEntity(exceptionHandler, HttpStatus.BAD_REQUEST);
	}
}
