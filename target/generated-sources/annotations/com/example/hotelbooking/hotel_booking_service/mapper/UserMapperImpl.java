package com.example.hotelbooking.hotel_booking_service.mapper;

import com.example.hotelbooking.hotel_booking_service.dto.user.request.UserRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.user.response.UserResponseDto;
import com.example.hotelbooking.hotel_booking_service.model.Role;
import com.example.hotelbooking.hotel_booking_service.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T16:29:48+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User requestDtoToUser(UserRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( requestDto.getUsername() );
        user.password( requestDto.getPassword() );
        user.email( requestDto.getEmail() );
        if ( requestDto.getRole() != null ) {
            user.role( Enum.valueOf( Role.class, requestDto.getRole() ) );
        }

        return user.build();
    }

    @Override
    public UserResponseDto userToResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setUsername( user.getUsername() );
        userResponseDto.setEmail( user.getEmail() );
        if ( user.getRole() != null ) {
            userResponseDto.setRole( user.getRole().name() );
        }

        return userResponseDto;
    }
}
