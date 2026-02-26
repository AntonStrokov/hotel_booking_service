package com.example.hotelbooking.hotel_booking_service.service.impl;

import com.example.hotelbooking.hotel_booking_service.exception.NotFoundException;
import com.example.hotelbooking.hotel_booking_service.model.User;
import com.example.hotelbooking.hotel_booking_service.repository.UserRepository;
import com.example.hotelbooking.hotel_booking_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Пользователь с ID " + id + " не найден"));
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Пользователь с именем " + username + " не найден"));
	}

	@Override
	@Transactional
	public User create(User user) {

		if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())) {
			throw new IllegalArgumentException("Пользователь с таким именем или email уже существует");
		}

		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User update(Long id, User userDetails) {
		User existingUser = findById(id);

		existingUser.setUsername(userDetails.getUsername());
		existingUser.setEmail(userDetails.getEmail());
		existingUser.setPassword(userDetails.getPassword());
		existingUser.setRole(userDetails.getRole());

		return userRepository.save(existingUser);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		if (!userRepository.existsById(id)) {
			throw new NotFoundException("Невозможно удалить: пользователь с ID " + id + " не найден");
		}
		userRepository.deleteById(id);
	}
}
