package com.example.hotelbooking.hotel_booking_service.service.kafka;

import com.example.hotelbooking.hotel_booking_service.dto.statistics.BookingEvent;
import com.example.hotelbooking.hotel_booking_service.dto.statistics.UserRegistrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${app.statistics.registration-topic}")
	private String registrationTopic;

	@Value("${app.statistics.booking-topic}")
	private String bookingTopic;

	public void sendRegistrationEvent(UserRegistrationEvent event) {
		kafkaTemplate.send(registrationTopic, event);
	}

	public void sendBookingEvent(BookingEvent event) {
		kafkaTemplate.send(bookingTopic, event);
	}
}

