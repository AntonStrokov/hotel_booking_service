package com.example.hotelbooking.hotel_booking_service.service.impl;

import com.example.hotelbooking.hotel_booking_service.exception.NotFoundException;
import com.example.hotelbooking.hotel_booking_service.model.Hotel;
import com.example.hotelbooking.hotel_booking_service.repository.HotelRepository;
import com.example.hotelbooking.hotel_booking_service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

	private final HotelRepository hotelRepository;

	@Override
	public Hotel getById(Long id) {
		return hotelRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Hotel not found with id = " + id));

	}

	@Override
	public List<Hotel> getAll() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel create(Hotel hotel) {
		hotel.setId(null);
		hotel.setRating(0.0);
		hotel.setNumberOfRatings(0);
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel update(Long id, Hotel hotel) {
		Hotel existing = getById(id);

		existing.setName(hotel.getName());
		existing.setTitle(hotel.getTitle());
		existing.setCity(hotel.getCity());
		existing.setAddress(hotel.getAddress());
		existing.setDistanceFromCenter(hotel.getDistanceFromCenter());

		return hotelRepository.save(existing);
	}

	@Override
	public void delete(Long id) {
		hotelRepository.deleteById(id);
	}
}