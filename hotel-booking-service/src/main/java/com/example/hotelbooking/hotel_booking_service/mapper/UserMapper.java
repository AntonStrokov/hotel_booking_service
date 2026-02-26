package com.example.hotelbooking.hotel_booking_service.mapper;

import com.example.hotelbooking.hotel_booking_service.dto.user.request.UserRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.user.response.UserResponseDto;
import com.example.hotelbooking.hotel_booking_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

	// 1. Из DTO в сущность (для регистрации/обновления)
	User requestDtoToUser(UserRequestDto requestDto);

	// 2. Из сущности в DTO (для ответа контроллера)
	UserResponseDto userToResponseDto(User user);

	// 3. Для маппинга списка пользователей (если понадобится в сервисе)
	// List<UserResponseDto> userListToResponseDtoList(List<User> users);
}
