package com.example.hotelbooking.hotel_booking_service.mapper;

import com.example.hotelbooking.hotel_booking_service.dto.room.request.RoomCreateUpdateRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.room.response.RoomResponseDto;
import com.example.hotelbooking.hotel_booking_service.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "hotel", ignore = true)
	Room toEntity(RoomCreateUpdateRequestDto dto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "hotel", ignore = true)
	void updateEntityFromDto(
			RoomCreateUpdateRequestDto dto,
			@MappingTarget Room room
	);

	@Mapping(source = "hotel.id", target = "hotelId")
	RoomResponseDto toResponseDto(Room room);
}