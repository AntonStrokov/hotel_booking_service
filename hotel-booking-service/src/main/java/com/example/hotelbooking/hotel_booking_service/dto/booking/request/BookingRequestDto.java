package com.example.hotelbooking.hotel_booking_service.dto.booking.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequestDto {
	private Long userId;
	private Long roomId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
}
