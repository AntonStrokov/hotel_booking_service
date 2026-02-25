package com.example.hotelbooking.hotel_booking_service.dto.hotel.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelResponseDto {

	private Long id;

	private String name;

	private String title;

	private String city;

	private String address;

	private Double distanceFromCenter;

	private Double rating;

	private Integer numberOfRatings;
}