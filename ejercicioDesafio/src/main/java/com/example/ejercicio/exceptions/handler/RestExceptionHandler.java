package com.example.ejercicio.exceptions.handler;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.ejercicio.exceptions.BadRequestException;
import com.example.ejercicio.models.dto.ErrorResponse;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	protected ResponseEntity<Object> handleException(NoSuchElementException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	@ExceptionHandler
	protected ResponseEntity<Object> handleException(DuplicateKeyException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	@ExceptionHandler
	protected ResponseEntity<Object> handleException(IllegalArgumentException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	@ExceptionHandler
	protected ResponseEntity<Object> handleBadRequestException(BadRequestException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return buildResponseEntity(httpStatus, exc);
	}

	@ExceptionHandler
	protected ResponseEntity<Object> handleAuthenticationException(AuthenticationException exc) {
		HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
		return buildResponseEntity(httpStatus, exc);
	}

	@ExceptionHandler
	protected ResponseEntity<Object> handleException(MethodArgumentTypeMismatchException exc) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		// Aplica cuando en el URL se envia un argumento invalido, por ejemplo String
		// por Integer
		return buildResponseEntity(httpStatus, new RuntimeException("Tipo de Argumento invalido"));
	}

	@ExceptionHandler
	protected ResponseEntity<Object> handleException(Exception exc) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return buildResponseEntity(httpStatus,
				new RuntimeException("Se presento un problema, reporte e intente luego."));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		List<String> errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

		return buildResponseEntity(httpStatus, ex, errorMessage);
	}

	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		return buildResponseEntity(httpStatus, ex);
	}

	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		return buildResponseEntity(httpStatus, ex);
	}

	private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, Exception exc) {
		
		ErrorResponse error = new ErrorResponse();
		error.setMessage(exc.getMessage());
		error.setStatus(httpStatus.value());
		error.setTimestamp(new Date());
		return new ResponseEntity<>(error, httpStatus);
	}

	private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, Exception exc, List<String> errors) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(errors.get(0));
		error.setStatus(httpStatus.value());
		error.setTimestamp(new Date());
		error.setErrors(errors);
		return new ResponseEntity<>(error, httpStatus);

	}
	

}
