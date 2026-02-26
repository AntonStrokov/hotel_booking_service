package com.example.hotelbooking.hotel_booking_service.controller;

import com.example.hotelbooking.hotel_booking_service.dto.hotel.request.HotelCreateUpdateRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.hotel.response.HotelResponseDto;
import com.example.hotelbooking.hotel_booking_service.mapper.HotelMapper;
import com.example.hotelbooking.hotel_booking_service.model.Hotel;
import com.example.hotelbooking.hotel_booking_service.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

	private final HotelService hotelService;
	private final HotelMapper hotelMapper;

	@GetMapping("/{id}")
	public HotelResponseDto getById(@PathVariable Long id) {
		Hotel hotel = hotelService.getById(id);
		return hotelMapper.toResponseDto(hotel);
	}

	@GetMapping
	public List<HotelResponseDto> getAll() {
		return hotelService.getAll().stream()
				.map(hotelMapper::toResponseDto)
				.toList();
	}

	@PostMapping
	public HotelResponseDto create(
			@RequestBody @Valid HotelCreateUpdateRequestDto dto
	) {
		Hotel hotel = hotelMapper.toEntity(dto);
		return hotelMapper.toResponseDto(hotelService.create(hotel));
	}

	@PutMapping("/{id}")
	public HotelResponseDto update(
			@PathVariable Long id,
			@RequestBody @Valid HotelCreateUpdateRequestDto dto
	) {
		Hotel hotel = hotelService.getById(id);
		hotelMapper.updateEntityFromDto(dto, hotel);
		return hotelMapper.toResponseDto(hotelService.update(id, hotel));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		hotelService.delete(id);
	}
}