package com.example.hotelbooking.hotel_booking_service.dto.room.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomCreateUpdateRequestDto {

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	@NotBlank
	private String roomNumber;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal price;

	@NotNull
	@Min(1)
	private Integer maxGuests;
}