package com.carlosguilherme.dscommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.carlosguilherme.dscommerce.dto.CustomError;
import com.carlosguilherme.dscommerce.services.execptions.DatabaseExecption;
import com.carlosguilherme.dscommerce.services.execptions.ResourceNotFoundExecption;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExeptionHandler {

	@ExceptionHandler(ResourceNotFoundExecption.class)
	public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundExecption e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
				return ResponseEntity.status(status).body(err); 
	}
	
	@ExceptionHandler(DatabaseExecption.class)
	public ResponseEntity<CustomError> database(DatabaseExecption e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		CustomError err = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
				return ResponseEntity.status(status).body(err); 
	}
}
