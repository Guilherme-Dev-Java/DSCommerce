package com.carlosguilherme.dscommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.carlosguilherme.dscommerce.dto.CustomError;
import com.carlosguilherme.dscommerce.dto.ValidationError;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError(Instant.now(),status.value(),"Dados inválidos",request.getRequestURI());
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(),f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err); 
	}
}
