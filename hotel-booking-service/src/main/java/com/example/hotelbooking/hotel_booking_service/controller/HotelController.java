package com.example.hotelbooking.hotel_booking_service.controller;

import com.example.hotelbooking.hotel_booking_service.dto.hotel.request.HotelCreateUpdateRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.hotel.request.HotelSearchRequest;
import com.example.hotelbooking.hotel_booking_service.dto.hotel.response.HotelListResponseDto;
import com.example.hotelbooking.hotel_booking_service.dto.hotel.response.HotelResponseDto;
import com.example.hotelbooking.hotel_booking_service.mapper.HotelMapper;
import com.example.hotelbooking.hotel_booking_service.model.Hotel;
import com.example.hotelbooking.hotel_booking_service.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

	private final HotelService hotelService;
	private final HotelMapper hotelMapper;

	// Доступно ВСЕМ
	@GetMapping("/{id}")
	public HotelResponseDto getById(@PathVariable Long id) {
		Hotel hotel = hotelService.getById(id);
		return hotelMapper.toResponseDto(hotel);
	}

	// Доступно ВСЕМ
	@GetMapping
	public List<HotelResponseDto> getAll() {
		return hotelService.getAll().stream()
				.map(hotelMapper::toResponseDto)
				.toList();
	}

	// Только ADMIN
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public HotelResponseDto create(@RequestBody @Valid HotelCreateUpdateRequestDto dto) {
		Hotel hotel = hotelMapper.toEntity(dto);
		return hotelMapper.toResponseDto(hotelService.create(hotel));
	}

	// Только ADMIN
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HotelResponseDto update(@PathVariable Long id, @RequestBody @Valid HotelCreateUpdateRequestDto dto) {
		Hotel hotel = hotelService.getById(id);
		hotelMapper.updateEntityFromDto(dto, hotel);
		return hotelMapper.toResponseDto(hotelService.update(id, hotel));
	}

	// Только ADMIN
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(@PathVariable Long id) {
		hotelService.delete(id);
	}

	// Только авторизованные (USER или ADMIN)
	@PostMapping("/{id}/rate")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Void> rateHotel(@PathVariable Long id, @RequestParam Integer newMark) {
		hotelService.updateRating(id, newMark);
		return ResponseEntity.ok().build();
	}

	// Доступно ВСЕМ
	@GetMapping("/filter")
	public ResponseEntity<HotelListResponseDto> filterHotels(HotelSearchRequest filter) {
		return ResponseEntity.ok(hotelService.findAll(filter));
	}
}
