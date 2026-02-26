package com.example.hotelbooking.hotel_booking_service.mapper;

import com.example.hotelbooking.hotel_booking_service.dto.hotel.request.HotelCreateUpdateRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.hotel.response.HotelResponseDto;
import com.example.hotelbooking.hotel_booking_service.model.Hotel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T15:11:04+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class HotelMapperImpl implements HotelMapper {

    @Override
    public Hotel toEntity(HotelCreateUpdateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Hotel.HotelBuilder hotel = Hotel.builder();

        hotel.name( dto.getName() );
        hotel.title( dto.getTitle() );
        hotel.city( dto.getCity() );
        hotel.address( dto.getAddress() );
        hotel.distanceFromCenter( dto.getDistanceFromCenter() );

        return hotel.build();
    }

    @Override
    public void updateEntityFromDto(HotelCreateUpdateRequestDto dto, Hotel hotel) {
        if ( dto == null ) {
            return;
        }

        hotel.setName( dto.getName() );
        hotel.setTitle( dto.getTitle() );
        hotel.setCity( dto.getCity() );
        hotel.setAddress( dto.getAddress() );
        hotel.setDistanceFromCenter( dto.getDistanceFromCenter() );
    }

    @Override
    public HotelResponseDto toResponseDto(Hotel hotel) {
        if ( hotel == null ) {
            return null;
        }

        HotelResponseDto.HotelResponseDtoBuilder hotelResponseDto = HotelResponseDto.builder();

        hotelResponseDto.id( hotel.getId() );
        hotelResponseDto.name( hotel.getName() );
        hotelResponseDto.title( hotel.getTitle() );
        hotelResponseDto.city( hotel.getCity() );
        hotelResponseDto.address( hotel.getAddress() );
        hotelResponseDto.distanceFromCenter( hotel.getDistanceFromCenter() );
        hotelResponseDto.rating( hotel.getRating() );
        hotelResponseDto.numberOfRatings( hotel.getNumberOfRatings() );

        return hotelResponseDto.build();
    }
}
