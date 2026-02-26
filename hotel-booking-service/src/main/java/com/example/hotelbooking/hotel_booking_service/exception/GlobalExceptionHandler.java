package com.example.hotelbooking.hotel_booking_service.exception;

import com.example.hotelbooking.hotel_booking_service.dto.error.ErrorResponseDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
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
	public ErrorResponseDto handleBadRequest(Exception ex) {
		return new ErrorResponseDto(
				ex.getMessage(),
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now()
		);
	}

	@ExceptionHandler(Exception.class)
	public ErrorResponseDto handleInternal(Exception ex) {
		return new ErrorResponseDto(
				"Internal server error",
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				LocalDateTime.now()
		);
	}
}