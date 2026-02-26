package com.example.hotelbooking.hotel_booking_service.dto.room.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponseDto {

	private Long id;

	private String name;

	private String description;

	private String roomNumber;

	private BigDecimal price;

	private Integer maxGuests;

	private Long hotelId;
}