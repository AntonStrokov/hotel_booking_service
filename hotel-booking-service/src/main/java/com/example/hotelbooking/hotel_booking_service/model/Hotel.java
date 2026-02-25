package com.example.hotelbooking.hotel_booking_service.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String address;

	@Column(name = "distance_from_center", nullable = false)
	private Double distanceFromCenter;

	@Column(nullable = false)
	private Double rating;

	@Column(name = "number_of_ratings", nullable = false)
	private Integer numberOfRatings;
}