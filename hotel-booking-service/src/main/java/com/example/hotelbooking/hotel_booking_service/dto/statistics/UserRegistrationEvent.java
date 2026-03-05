package com.example.hotelbooking.hotel_booking_service.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationEvent {
	private Long userId;
	private LocalDateTime registrationTime;
}
