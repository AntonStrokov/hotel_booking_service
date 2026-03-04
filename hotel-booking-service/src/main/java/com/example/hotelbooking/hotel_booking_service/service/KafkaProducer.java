package com.example.hotelbooking.hotel_booking_service.service;

import com.example.hotelbooking.hotel_booking_service.dto.kafka.BookingEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void sendRegistrationEvent(Long event) {
		kafkaTemplate.send("registration-topic", event);
	}

	public void sendBookingEvent(BookingEvent event) {
		kafkaTemplate.send("booking-topic", event);
	}
}

