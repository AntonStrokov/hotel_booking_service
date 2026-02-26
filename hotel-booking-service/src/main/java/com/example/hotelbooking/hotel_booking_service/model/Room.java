package com.example.hotelbooking.hotel_booking_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, length = 1000)
	private String description;

	@Column(nullable = false)
	private String roomNumber;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private Integer maxGuests;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
}