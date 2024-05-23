package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.user.UserNotFoundException;


@ControllerAdvice
public class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errordetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);
	
}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleuserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errordetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity(errordetails, HttpStatus.NOT_FOUND);
	
}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorDetails errordetails = new ErrorDetails(LocalDateTime.now(), 
        	"Total errors:" + ex.getErrorCount() +  " First error " + ex.getFieldError().getDefaultMessage(), request.getDescription(false));
		
		return new ResponseEntity(errordetails, HttpStatus.NOT_FOUND);
	
	}
	
		
	
	
}
	


