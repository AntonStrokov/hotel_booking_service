package com.example.hotelbooking.hotel_booking_service.service.impl;

import com.example.hotelbooking.hotel_booking_service.dto.booking.request.BookingRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.booking.response.BookingResponseDto;
import com.example.hotelbooking.hotel_booking_service.dto.statistics.BookingEvent;
import com.example.hotelbooking.hotel_booking_service.exception.NotFoundException;
import com.example.hotelbooking.hotel_booking_service.mapper.BookingMapper;
import com.example.hotelbooking.hotel_booking_service.model.Booking;
import com.example.hotelbooking.hotel_booking_service.model.Room;
import com.example.hotelbooking.hotel_booking_service.model.User;
import com.example.hotelbooking.hotel_booking_service.repository.BookingRepository;
import com.example.hotelbooking.hotel_booking_service.repository.RoomRepository;
import com.example.hotelbooking.hotel_booking_service.repository.UserRepository;
import com.example.hotelbooking.hotel_booking_service.service.BookingService;
import com.example.hotelbooking.hotel_booking_service.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final RoomRepository roomRepository;
	private final UserRepository userRepository;
	private final BookingMapper bookingMapper;
	private final KafkaProducerService kafkaProducer; // 1. ДОБАВИЛИ ПРОДЮСЕР

	@Override
	@Transactional
	public BookingResponseDto create(BookingRequestDto dto) {

		if (dto.getCheckOutDate().isBefore(dto.getCheckInDate())) {
			throw new IllegalArgumentException("Check-out date cannot be before check-in date");
		}

		boolean isOverlapping = bookingRepository.existsOverlappingBookings(
				dto.getRoomId(), dto.getCheckInDate(), dto.getCheckOutDate());

		if (isOverlapping) {
			throw new IllegalArgumentException("Dates are already booked for this room!");
		}

		Room room = roomRepository.findById(dto.getRoomId())
				.orElseThrow(() -> new NotFoundException("Room not found with ID: " + dto.getRoomId()));
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new NotFoundException("User not found with ID: " + dto.getUserId()));

		Booking booking = bookingMapper.toEntity(dto);
		booking.setRoom(room);
		booking.setUser(user);

		Booking savedBooking = bookingRepository.save(booking);

		kafkaProducer.sendBookingEvent(new BookingEvent(
				savedBooking.getUser().getId(),
				savedBooking.getCheckInDate(),
				savedBooking.getCheckOutDate()
		));

		return bookingMapper.toResponse(savedBooking);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingResponseDto> findAll(int page, int size) {

		return bookingRepository.findAll(PageRequest.of(page, size))
				.getContent()
				.stream()
				.map(bookingMapper::toResponse)
				.collect(Collectors.toList());
	}
}
