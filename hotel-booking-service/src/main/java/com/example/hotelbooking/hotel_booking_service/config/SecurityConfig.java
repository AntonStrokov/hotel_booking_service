package com.example.hotelbooking.hotel_booking_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						// регистрация доступна всем
						.requestMatchers("/api/users/register").permitAll()

						// отели и комнаты только админ
						.requestMatchers("/api/hotels/**").hasRole("ADMIN")
						.requestMatchers("/api/rooms/**").hasRole("ADMIN")

						// бронирования: GET только админ, POST доступен всем авторизованным
						.requestMatchers(HttpMethod.GET, "/api/bookings/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/bookings/**").hasAnyRole("USER", "ADMIN")

						// остальные методы доступны авторизованным пользователям
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}
}
