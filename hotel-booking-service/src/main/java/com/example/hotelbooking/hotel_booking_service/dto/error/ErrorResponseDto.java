package com.example.hotelbooking.hotel_booking_service.dto.error;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {

	private final String message;
	private final int status;
	private final LocalDateTime timestamp;
}