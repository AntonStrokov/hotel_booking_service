package com.example.hotelbooking.hotel_booking_service.service.impl;

import com.example.hotelbooking.hotel_booking_service.exception.NotFoundException;
import com.example.hotelbooking.hotel_booking_service.model.Hotel;
import com.example.hotelbooking.hotel_booking_service.model.Room;
import com.example.hotelbooking.hotel_booking_service.repository.HotelRepository;
import com.example.hotelbooking.hotel_booking_service.repository.RoomRepository;
import com.example.hotelbooking.hotel_booking_service.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomRepository roomRepository;
	private final HotelRepository hotelRepository;

	@Override
	public Room getById(Long id) {
		return roomRepository.findById(id)
				.orElseThrow(() ->
						new NotFoundException("Room not found with id = " + id));
	}

	@Override
	public List<Room> getAll() {
		return roomRepository.findAll();
	}

	@Override
	public Room create(Room room, Long hotelId) {
		Hotel hotel = hotelRepository.findById(hotelId)
				.orElseThrow(() ->
						new NotFoundException("Hotel not found with id = " + hotelId));

		room.setId(null);
		room.setHotel(hotel);

		return roomRepository.save(room);
	}

	@Override
	public Room update(Long id, Room room) {
		Room existing = getById(id);

		existing.setName(room.getName());
		existing.setDescription(room.getDescription());
		existing.setRoomNumber(room.getRoomNumber());
		existing.setPrice(room.getPrice());
		existing.setMaxGuests(room.getMaxGuests());

		return roomRepository.save(existing);
	}

	@Override
	public void delete(Long id) {
		roomRepository.deleteById(id);
	}
}