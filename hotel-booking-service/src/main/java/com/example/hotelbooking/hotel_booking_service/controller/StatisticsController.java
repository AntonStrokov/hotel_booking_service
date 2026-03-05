package com.example.hotelbooking.hotel_booking_service.controller;

import com.example.hotelbooking.hotel_booking_service.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

	private final StatisticsService statisticsService;

	@GetMapping("/export")
	@PreAuthorize("hasRole('ADMIN')") // Только для админов!
	public ResponseEntity<byte[]> exportStatistics() {
		String csvData = statisticsService.exportToCsv();
		byte[] output = csvData.getBytes();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=statistics.csv")
				.contentType(MediaType.parseMediaType("text/csv"))
				.body(output);
	}
}
