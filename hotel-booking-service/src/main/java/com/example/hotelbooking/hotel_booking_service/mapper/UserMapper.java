package com.example.hotelbooking.hotel_booking_service.mapper;

import com.example.hotelbooking.hotel_booking_service.dto.user.request.UserRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.user.response.UserResponseDto;
import com.example.hotelbooking.hotel_booking_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	User requestDtoToUser(UserRequestDto requestDto);
	UserResponseDto userToResponseDto(User user);

}
