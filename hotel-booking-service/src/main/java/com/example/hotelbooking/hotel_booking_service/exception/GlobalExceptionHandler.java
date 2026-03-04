package com.example.hotelbooking.hotel_booking_service.exception;

import com.example.hotelbooking.hotel_booking_service.dto.error.ErrorResponseDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponseDto handleNotFound(NotFoundException ex) {
		return new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.NOT_FOUND.value(),
				LocalDateTime.now()
		);
	}

	@ExceptionHandler({
			MethodArgumentNotValidException.class,
			ConstraintViolationException.class,
			IllegalArgumentException.class
	})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponseDto handleBadRequest(Exception ex) {
		String errorMessage = "Bad Request: ";
		if (ex instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException methodArgEx = (MethodArgumentNotValidException) ex;
			errorMessage += "Validation errors: " + methodArgEx.getBindingResult().getFieldErrors().stream()
					.map(error -> error.getField() + ": " + error.getDefaultMessage())
					.collect(Collectors.joining(", "));
		}
		else if (ex instanceof ConstraintViolationException) {
			ConstraintViolationException constraintViolationEx = (ConstraintViolationException) ex;
			errorMessage += "Validation errors: " + constraintViolationEx.getConstraintViolations().stream()
					.map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
					.collect(Collectors.joining(", "));
		}
		else if (ex instanceof IllegalArgumentException) {
			errorMessage += "Illegal argument: " + ex.getMessage();
		}
		else {
			errorMessage += ex.getMessage();
		}
		return new ErrorResponseDto(
				errorMessage,
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now()
		);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponseDto handleInternal(Exception ex) {
		log.error("Unhandled exception caught: ", ex);
		return new ErrorResponseDto(
				"Internal server error: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				LocalDateTime.now()
		);
	}
}
