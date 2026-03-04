package com.example.hotelbooking.hotel_booking_service.model.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "statistics") // Указываем коллекцию в MongoDB
public class UserActionLog {

	@Id
	private String id;

	private Long userId;

	private String actionType;

	private Instant timestamp;

	private Instant checkInDate;
	private Instant checkOutDate;
}
