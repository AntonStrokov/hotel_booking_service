package com.example.hotelbooking.hotel_booking_service.service;

import com.example.hotelbooking.hotel_booking_service.dto.hotel.request.HotelSearchRequest;
import com.example.hotelbooking.hotel_booking_service.dto.hotel.response.HotelListResponseDto;
import com.example.hotelbooking.hotel_booking_service.model.Hotel;

import java.util.List;

public interface HotelService {

	Hotel getById(Long id);

	List<Hotel> getAll();

	Hotel create(Hotel hotel);

	Hotel update(Long id, Hotel hotel);

	void delete(Long id);
	public void updateRating(Long hotelId, Integer newMark);
	HotelListResponseDto findAll(HotelSearchRequest filter);
}