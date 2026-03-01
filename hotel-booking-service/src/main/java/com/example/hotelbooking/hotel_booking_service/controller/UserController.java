package com.example.hotelbooking.hotel_booking_service.controller;

import com.example.hotelbooking.hotel_booking_service.dto.user.request.UserRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.user.response.UserResponseDto;
import com.example.hotelbooking.hotel_booking_service.mapper.UserMapper;
import com.example.hotelbooking.hotel_booking_service.model.User;
import com.example.hotelbooking.hotel_booking_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserMapper userMapper;

	@PostMapping
	public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
		User user = userMapper.requestDtoToUser(requestDto);
		User createdUser = userService.create(user);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userMapper.userToResponseDto(createdUser));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
		User user = userService.findById(id);
		return ResponseEntity.ok(userMapper.userToResponseDto(user));
	}

	@GetMapping("/search")
	public ResponseEntity<UserResponseDto> getUserByUsername(@RequestParam String username) {
		User user = userService.findByUsername(username);
		return ResponseEntity.ok(userMapper.userToResponseDto(user));
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		List<UserResponseDto> users = userService.findAll().stream()
				.map(userMapper::userToResponseDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(users);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,
	                                                  @RequestBody UserRequestDto requestDto) {
		User userDetails = userMapper.requestDtoToUser(requestDto);
		User updatedUser = userService.update(id, userDetails);
		return ResponseEntity.ok(userMapper.userToResponseDto(updatedUser));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto requestDto) {
		User user = userMapper.requestDtoToUser(requestDto);
		User createdUser = userService.create(user);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userMapper.userToResponseDto(createdUser));
	}
}
