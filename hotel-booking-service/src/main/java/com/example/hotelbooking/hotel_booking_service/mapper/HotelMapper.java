package com.example.hotelbooking.hotel_booking_service.mapper;

import com.example.hotelbooking.hotel_booking_service.dto.hotel.request.HotelCreateUpdateRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.hotel.response.HotelResponseDto;
import com.example.hotelbooking.hotel_booking_service.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HotelMapper {


	@Mapping(target = "id", ignore = true)
	@Mapping(target = "rating", ignore = true)
	@Mapping(target = "numberOfRatings", ignore = true)
	Hotel toEntity(HotelCreateUpdateRequestDto dto);


	@Mapping(target = "id", ignore = true)
	@Mapping(target = "rating", ignore = true)
	@Mapping(target = "numberOfRatings", ignore = true)
	void updateEntityFromDto(
			HotelCreateUpdateRequestDto dto,
			@MappingTarget Hotel hotel
	);

	HotelResponseDto toResponseDto(Hotel hotel);
}