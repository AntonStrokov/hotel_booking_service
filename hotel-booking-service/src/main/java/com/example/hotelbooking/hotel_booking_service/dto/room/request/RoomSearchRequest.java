package com.example.hotelbooking.hotel_booking_service.dto.room.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomSearchRequest {
	private Long id;
	private String title;
	private Double minPrice;
	private Double maxPrice;
	private Integer guestCount;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Long hotelId;
	private Integer pageSize = 10;
	private Integer pageNumber = 0;
}

