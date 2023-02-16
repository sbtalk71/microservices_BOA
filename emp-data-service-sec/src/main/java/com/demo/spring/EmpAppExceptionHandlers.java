package com.demo.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmpAppExceptionHandlers {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleNotFound(RuntimeException ex){
		return ResponseEntity.status(404).body(ex.getMessage());
	}
	
	
}
