package com.example.hotelbooking.hotel_booking_service.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingEvent {
	private Long userId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
}
