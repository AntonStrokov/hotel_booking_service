package com.example.hotelbooking.hotel_booking_service.service.impl;

import com.example.hotelbooking.hotel_booking_service.dto.statistics.UserRegistrationEvent;
import com.example.hotelbooking.hotel_booking_service.exception.NotFoundException;
import com.example.hotelbooking.hotel_booking_service.model.User;
import com.example.hotelbooking.hotel_booking_service.repository.UserRepository;
import com.example.hotelbooking.hotel_booking_service.service.UserService;
import com.example.hotelbooking.hotel_booking_service.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final KafkaProducerService kafkaProducer;

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
	public User register(User user) {
		return create(user);
	}

	@Override
	@Transactional
	public User create(User user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new IllegalArgumentException("Пользователь с именем " + user.getUsername() + " уже зарегистрирован");
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new IllegalArgumentException("Пользователь с почтой " + user.getEmail() + " уже зарегистрирован");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(user);

		kafkaProducer.sendRegistrationEvent(new UserRegistrationEvent(
				savedUser.getId(),
				LocalDateTime.now()
		));

		return savedUser;
	}

	@Override
	@Transactional
	public User update(Long id, User userDetails) {
		User existingUser = findById(id);
		existingUser.setUsername(userDetails.getUsername());
		existingUser.setEmail(userDetails.getEmail());
		existingUser.setRole(userDetails.getRole());

		if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
			existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		}
		return userRepository.save(existingUser);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		if (!userRepository.existsById(id)) {
			throw new NotFoundException("Пользователь не найден");
		}
		userRepository.deleteById(id);
	}
}
