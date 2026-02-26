package com.example.hotelbooking.hotel_booking_service.controller;

import com.example.hotelbooking.hotel_booking_service.dto.booking.request.BookingRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.booking.response.BookingResponseDto;
import com.example.hotelbooking.hotel_booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

	private final BookingService bookingService;

	@PostMapping
	public ResponseEntity<BookingResponseDto> create(@RequestBody BookingRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.create(dto));
	}

	@GetMapping
	public ResponseEntity<List<BookingResponseDto>> findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(bookingService.findAll(page, size));
	}
}