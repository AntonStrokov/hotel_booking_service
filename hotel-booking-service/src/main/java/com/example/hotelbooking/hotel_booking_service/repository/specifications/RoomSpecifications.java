package com.example.hotelbooking.hotel_booking_service.repository.specifications;

import com.example.hotelbooking.hotel_booking_service.dto.room.request.RoomSearchRequest;
import com.example.hotelbooking.hotel_booking_service.model.Booking;
import com.example.hotelbooking.hotel_booking_service.model.Room;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RoomSpecifications {
	public static Specification<Room> withFilter(RoomSearchRequest filter) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (filter.getId() != null) predicates.add(cb.equal(root.get("id"), filter.getId()));
			if (filter.getHotelId() != null) predicates.add(cb.equal(root.get("hotel").get("id"), filter.getHotelId()));
			if (filter.getGuestCount() != null) predicates.add(cb.equal(root.get("maxPeople"), filter.getGuestCount()));
			if (filter.getMinPrice() != null) predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMinPrice()));
			if (filter.getMaxPrice() != null) predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMaxPrice()));

			// Фильтрация по датам (только если заполнены оба поля по ТЗ)
			if (filter.getCheckIn() != null && filter.getCheckOut() != null) {
				Subquery<Long> subquery = query.subquery(Long.class);
				Root<Booking> bookingRoot = subquery.from(Booking.class);
				subquery.select(bookingRoot.get("room").get("id"));

				// Условие пересечения дат
				Predicate overlap = cb.and(
						cb.lessThan(bookingRoot.get("checkIn"), filter.getCheckOut()),
						cb.greaterThan(bookingRoot.get("checkOut"), filter.getCheckIn())
				);
				subquery.where(overlap);
				predicates.add(cb.not(root.get("id").in(subquery)));
			}
			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
