package com.example.hotelbooking.hotel_booking_service.mapper;

import com.example.hotelbooking.hotel_booking_service.dto.booking.request.BookingRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.booking.response.BookingResponseDto;
import com.example.hotelbooking.hotel_booking_service.model.Booking;
import com.example.hotelbooking.hotel_booking_service.model.Room;
import com.example.hotelbooking.hotel_booking_service.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T17:08:36+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingResponseDto toResponse(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingResponseDto bookingResponseDto = new BookingResponseDto();

        bookingResponseDto.setUserId( bookingUserId( booking ) );
        bookingResponseDto.setRoomId( bookingRoomId( booking ) );
        bookingResponseDto.setId( booking.getId() );
        bookingResponseDto.setCheckInDate( booking.getCheckInDate() );
        bookingResponseDto.setCheckOutDate( booking.getCheckOutDate() );

        return bookingResponseDto;
    }

    @Override
    public Booking toEntity(BookingRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Booking.BookingBuilder booking = Booking.builder();

        booking.checkInDate( dto.getCheckInDate() );
        booking.checkOutDate( dto.getCheckOutDate() );

        return booking.build();
    }

    private Long bookingUserId(Booking booking) {
        if ( booking == null ) {
            return null;
        }
        User user = booking.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long bookingRoomId(Booking booking) {
        if ( booking == null ) {
            return null;
        }
        Room room = booking.getRoom();
        if ( room == null ) {
            return null;
        }
        Long id = room.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
