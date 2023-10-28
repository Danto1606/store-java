package com.danny.store.java.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.danny.store.java.models.ErrorModel;

@RestControllerAdvice
@ResponseStatus
public class AppExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(PathNotFoundException.class)
	public ResponseEntity<ErrorModel> pathNotFoundException(PathNotFoundException exception, WebRequest request){
		ErrorModel message = new ErrorModel(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		
	}
	
	@ExceptionHandler(UserBadRequestException.class)
	public ResponseEntity<ErrorModel> userBadRequestException(UserBadRequestException exception, WebRequest request){
		ErrorModel message = new ErrorModel(HttpStatus.BAD_REQUEST, exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		
	}
	
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorModel> invaliTokenException(InvalidTokenException exception, WebRequest request){
		ErrorModel message = new ErrorModel(HttpStatus.UNAUTHORIZED, exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorModel> loginException(LoginException exception, WebRequest request){
		ErrorModel message = new ErrorModel(HttpStatus.BAD_REQUEST, exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		
	}
}
