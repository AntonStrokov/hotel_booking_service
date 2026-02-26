package com.example.hotelbooking.hotel_booking_service.service;

import com.example.hotelbooking.hotel_booking_service.model.Room;

import java.util.List;

public interface RoomService {

	Room getById(Long id);

	List<Room> getAll();

	Room create(Room room, Long hotelId);

	Room update(Long id, Room room);

	void delete(Long id);
}