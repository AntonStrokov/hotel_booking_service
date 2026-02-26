package com.example.hotelbooking.hotel_booking_service.mapper;

import com.example.hotelbooking.hotel_booking_service.dto.booking.request.BookingRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.booking.response.BookingResponseDto;
import com.example.hotelbooking.hotel_booking_service.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

	@Mapping(target = "userId", source = "user.id")
	@Mapping(target = "roomId", source = "room.id")
	BookingResponseDto toResponse(Booking booking);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "user", ignore = true) // Будем устанавливать в сервисе
	@Mapping(target = "room", ignore = true) // Будем устанавливать в сервисе
	Booking toEntity(BookingRequestDto dto);
}