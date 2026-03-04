package com.example.hotelbooking.hotel_booking_service.service.impl;

import com.example.hotelbooking.hotel_booking_service.exception.NotFoundException;
import com.example.hotelbooking.hotel_booking_service.model.Hotel;
import com.example.hotelbooking.hotel_booking_service.repository.HotelRepository;
import com.example.hotelbooking.hotel_booking_service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

	@Override
	public void updateRating(Long id, Integer newMark) {
		if (newMark < 1 || newMark > 5) {
			throw new IllegalArgumentException("Оценка должна быть от 1 до 5");
		}

		Hotel hotel = hotelRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Отель не найден"));

		double currentRating = hotel.getRating();
		int count = hotel.getNumberOfRatings();

		double newRating;

		// Если это самая первая оценка в истории отеля
		if (count == 0) {
			newRating = newMark.doubleValue();
		} else {
			// Формула строго по ТЗ
			double totalRating = currentRating * count;
			totalRating = totalRating - currentRating + newMark;
			newRating = totalRating / count;
		}

		// Округление до 1 знака после запятой
		BigDecimal bd = new BigDecimal(Double.toString(newRating));
		bd = bd.setScale(1, RoundingMode.HALF_UP);

		hotel.setRating(bd.doubleValue());
		hotel.setNumberOfRatings(count + 1);

		hotelRepository.save(hotel);
	}


}