package com.example.hotelbooking.hotel_booking_service.service;

import com.example.hotelbooking.hotel_booking_service.model.statistics.UserActionLog;
import com.example.hotelbooking.hotel_booking_service.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

	private final StatisticsRepository repository;

	public String exportToCsv() {
		List<UserActionLog> logs = repository.findAll();

		StringBuilder csv = new StringBuilder("ID,User_ID,Action_Type,Timestamp,Check_In,Check_Out\n");

		for (UserActionLog log : logs) {
			csv.append(log.getId()).append(",")
					.append(log.getUserId()).append(",")
					.append(log.getActionType()).append(",")
					.append(log.getTimestamp()).append(",")
					.append(log.getCheckIn() != null ? log.getCheckIn() : "").append(",")
					.append(log.getCheckOut() != null ? log.getCheckOut() : "").append("\n");
		}

		return csv.toString();
	}
}
