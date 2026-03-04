package com.example.hotelbooking.hotel_booking_service.dto.hotel.request;

import lombok.Data;

@Data
public class HotelSearchRequest {
	private Long id;
	private String name;
	private String title;
	private String city;
	private String address;
	private Double distanceToCenter;
	private Double rating;
	private Integer numberOfRatings;
	private Integer pageSize = 10;
	private Integer pageNumber = 0;
}

