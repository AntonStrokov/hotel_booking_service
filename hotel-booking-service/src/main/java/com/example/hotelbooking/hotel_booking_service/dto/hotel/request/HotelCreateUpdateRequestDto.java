package com.example.hotelbooking.hotel_booking_service.dto.hotel.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelCreateUpdateRequestDto {

	@NotBlank
	private String name;

	@NotBlank
	private String title;

	@NotBlank
	private String city;

	@NotBlank
	private String address;

	@NotNull
	@Positive
	private Double distanceFromCenter;
}