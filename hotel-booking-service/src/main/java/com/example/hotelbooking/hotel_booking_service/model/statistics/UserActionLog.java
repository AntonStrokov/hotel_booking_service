package com.example.hotelbooking.hotel_booking_service.model.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_actions") // Название коллекции в MongoDB
public class UserActionLog {

	@Id
	private String id;

	private Long userId;

	private String actionType; // Будет принимать значения "REGISTRATION" или "BOOKING"

	private LocalDateTime timestamp;

	// Эти поля будут заполнены только для действия "BOOKING"
	private LocalDateTime checkIn;
	private LocalDateTime checkOut;
}
