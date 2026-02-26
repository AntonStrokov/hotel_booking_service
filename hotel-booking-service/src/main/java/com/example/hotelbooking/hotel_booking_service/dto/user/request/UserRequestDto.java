package com.example.hotelbooking.hotel_booking_service.dto.user.request;

import lombok.Data;

@Data
public class UserRequestDto {
	private String username;
	private String password;
	private String email;
	private String role; // Будем передавать строкой
}
