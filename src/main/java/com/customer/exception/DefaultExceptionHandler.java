package com.customer.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorMessage> somethingWentWrong(Exception ex) {
		
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),"User Not found");
		 return new ResponseEntity<ErrorMessage>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);

	}

}
