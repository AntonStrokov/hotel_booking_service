package com.example.hotelbooking.hotel_booking_service.service.kafka;

import com.example.hotelbooking.hotel_booking_service.dto.statistics.BookingEvent;
import com.example.hotelbooking.hotel_booking_service.dto.statistics.UserRegistrationEvent;
import com.example.hotelbooking.hotel_booking_service.model.statistics.UserActionLog;
import com.example.hotelbooking.hotel_booking_service.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

	private final StatisticsRepository repository;

	// Слушаем регистрацию
	@KafkaListener(topics = "${app.statistics.registration-topic}", groupId = "statistics-group")
	public void listenRegistration(UserRegistrationEvent event) {
		log.info("Mongo Consumer: Got registration event for user {}", event.getUserId());

		UserActionLog logEntry = UserActionLog.builder()
				.userId(event.getUserId())
				.actionType("REGISTRATION")
				.timestamp(event.getRegistrationTime())
				.build();

		repository.save(logEntry); // Сохраняем в MongoDB!
	}

	// Слушаем бронирование
	@KafkaListener(topics = "${app.statistics.booking-topic}", groupId = "statistics-group")
	public void listenBooking(BookingEvent event) {
		log.info("Mongo Consumer: Got booking event for user {}", event.getUserId());

		UserActionLog logEntry = UserActionLog.builder()
				.userId(event.getUserId())
				.actionType("BOOKING")
				.timestamp(LocalDateTime.now())
				.checkIn(event.getCheckInDate().atStartOfDay())
				.checkOut(event.getCheckOutDate().atStartOfDay())
				.build();

		repository.save(logEntry);
	}
}
