package com.example.hotelbooking.hotel_booking_service.service;

import com.example.hotelbooking.hotel_booking_service.model.User;

import java.util.List;

public interface UserService {

	List<User> findAll();

	User findById(Long id);

	User findByUsername(String username);

	User create(User user);

	User update(Long id, User user);

	void delete(Long id);
}
