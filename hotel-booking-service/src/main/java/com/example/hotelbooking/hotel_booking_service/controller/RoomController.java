package com.example.hotelbooking.hotel_booking_service.controller;

import com.example.hotelbooking.hotel_booking_service.dto.room.request.RoomCreateUpdateRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.room.response.RoomResponseDto;
import com.example.hotelbooking.hotel_booking_service.mapper.RoomMapper;
import com.example.hotelbooking.hotel_booking_service.model.Room;
import com.example.hotelbooking.hotel_booking_service.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

	private final RoomService roomService;
	private final RoomMapper roomMapper;

	@GetMapping("/{id}")
	public RoomResponseDto getById(@PathVariable Long id) {
		Room room = roomService.getById(id);
		return roomMapper.toResponseDto(room);
	}

	@GetMapping
	public List<RoomResponseDto> getAll() {
		return roomService.getAll().stream()
				.map(roomMapper::toResponseDto)
				.toList();
	}

	@PostMapping("/hotel/{hotelId}")
	public RoomResponseDto create(
			@PathVariable Long hotelId,
			@RequestBody @Valid RoomCreateUpdateRequestDto dto
	) {
		Room room = roomMapper.toEntity(dto);
		return roomMapper.toResponseDto(
				roomService.create(room, hotelId)
		);
	}

	@PutMapping("/{id}")
	public RoomResponseDto update(
			@PathVariable Long id,
			@RequestBody @Valid RoomCreateUpdateRequestDto dto
	) {
		Room room = roomService.getById(id);
		roomMapper.updateEntityFromDto(dto, room);
		return roomMapper.toResponseDto(
				roomService.update(id, room)
		);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		roomService.delete(id);
	}
}