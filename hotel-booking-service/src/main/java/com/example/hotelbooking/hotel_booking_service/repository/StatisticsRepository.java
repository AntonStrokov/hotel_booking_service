package com.example.hotelbooking.hotel_booking_service.repository;

import com.example.hotelbooking.hotel_booking_service.model.statistics.UserActionLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends MongoRepository<UserActionLog, String> {
}
