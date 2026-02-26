package com.example.hotelbooking.hotel_booking_service.dto.user.response;

import lombok.Data;

@Data
public class UserResponseDto {
	private Long id;
	private String username;
	private String email;
	private String role;
}
