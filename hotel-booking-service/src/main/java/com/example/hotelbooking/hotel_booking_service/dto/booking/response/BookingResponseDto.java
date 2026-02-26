package com.example.hotelbooking.hotel_booking_service.dto.booking.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingResponseDto {
	private Long id;
	private Long userId;
	private Long roomId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
}
