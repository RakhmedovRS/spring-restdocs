package com.github.rakhmedors.springrestdocs.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;

/**
 * @author RakhmedovRS
 * @created 10/6/2020
 */
@ControllerAdvice
public class MvcExceptionHandler
{
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException ex)
	{
		List<String> errorsList = new ArrayList<>(ex.getConstraintViolations().size());

		ex.getConstraintViolations().forEach(error -> errorsList.add(error.toString()));

		return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
	}
}
