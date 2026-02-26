package com.example.hotelbooking.hotel_booking_service.repository;

import com.example.hotelbooking.hotel_booking_service.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}