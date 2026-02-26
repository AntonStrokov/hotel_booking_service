package com.example.hotelbooking.hotel_booking_service.repository;

import com.example.hotelbooking.hotel_booking_service.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("SELECT COUNT(b) > 0 FROM Booking b " +
			"WHERE b.room.id = :roomId " +
			"AND b.checkInDate < :checkOutDate " +
			"AND b.checkOutDate > :checkInDate")
	boolean existsOverlappingBookings(
			@Param("roomId") Long roomId,
			@Param("checkInDate") LocalDate checkInDate,
			@Param("checkOutDate") LocalDate checkOutDate
	);
}

