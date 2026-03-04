package com.example.hotelbooking.hotel_booking_service.dto.hotel.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelListResponseDto {
	private List<HotelResponseDto> hotels;
	private long totalCount;
}
