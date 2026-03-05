package com.example.hotelbooking.hotel_booking_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						// Регистрация доступна всем
						.requestMatchers("/api/users/register").permitAll()

						// Разрешаем всем ПРОСМОТР отелей и комнат (GET-запросы)
						.requestMatchers(HttpMethod.GET, "/api/hotels/**", "/api/rooms/**").permitAll()

						// Остальные проверки (POST, PUT, DELETE) теперь возьмут на себя @PreAuthorize в контроллерах
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}
}
