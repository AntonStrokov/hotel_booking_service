package com.example.hotelbooking.hotel_booking_service.repository.specifications;

import com.example.hotelbooking.hotel_booking_service.dto.hotel.request.HotelSearchRequest;
import com.example.hotelbooking.hotel_booking_service.model.Hotel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecifications {
	public static Specification<Hotel> withFilter(HotelSearchRequest filter) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (filter.getId() != null) {
				predicates.add(cb.equal(root.get("id"), filter.getId()));
			}
			if (filter.getName() != null) {
				predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
			}
			if (filter.getTitle() != null) {
				predicates.add(cb.like(cb.lower(root.get("title")), "%" + filter.getTitle().toLowerCase() + "%"));
			}
			if (filter.getCity() != null) {
				predicates.add(cb.equal(cb.lower(root.get("city")), filter.getCity().toLowerCase()));
			}
			if (filter.getAddress() != null) {
				predicates.add(cb.like(cb.lower(root.get("address")), "%" + filter.getAddress().toLowerCase() + "%"));
			}
			if (filter.getDistanceToCenter() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("distanceToCenter"), filter.getDistanceToCenter()));
			}
			if (filter.getRating() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), filter.getRating()));
			}
			if (filter.getNumberOfRatings() != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("numberOfRatings"), filter.getNumberOfRatings()));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
