package com.example.hotelbooking.hotel_booking_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable) // Отключаем для REST API
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/users/register").permitAll()

						.requestMatchers("/api/hotels/**").hasRole("ADMIN")
						.requestMatchers("/api/rooms/**").hasRole("ADMIN")

						.requestMatchers("/api/bookings").hasRole("ADMIN")

						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}
}
