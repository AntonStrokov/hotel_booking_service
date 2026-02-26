package com.example.hotelbooking.hotel_booking_service.service;

import com.example.hotelbooking.hotel_booking_service.dto.booking.request.BookingRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.booking.response.BookingResponseDto;
import java.util.List;

public interface BookingService {

	BookingResponseDto create(BookingRequestDto dto);

	List<BookingResponseDto> findAll(int page, int size);


}