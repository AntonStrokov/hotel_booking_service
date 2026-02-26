package com.example.hotelbooking.hotel_booking_service.mapper;

import com.example.hotelbooking.hotel_booking_service.dto.room.request.RoomCreateUpdateRequestDto;
import com.example.hotelbooking.hotel_booking_service.dto.room.response.RoomResponseDto;
import com.example.hotelbooking.hotel_booking_service.model.Hotel;
import com.example.hotelbooking.hotel_booking_service.model.Room;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-26T15:47:08+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public Room toEntity(RoomCreateUpdateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Room.RoomBuilder room = Room.builder();

        room.name( dto.getName() );
        room.description( dto.getDescription() );
        room.roomNumber( dto.getRoomNumber() );
        room.price( dto.getPrice() );
        room.maxGuests( dto.getMaxGuests() );

        return room.build();
    }

    @Override
    public void updateEntityFromDto(RoomCreateUpdateRequestDto dto, Room room) {
        if ( dto == null ) {
            return;
        }

        room.setName( dto.getName() );
        room.setDescription( dto.getDescription() );
        room.setRoomNumber( dto.getRoomNumber() );
        room.setPrice( dto.getPrice() );
        room.setMaxGuests( dto.getMaxGuests() );
    }

    @Override
    public RoomResponseDto toResponseDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomResponseDto.RoomResponseDtoBuilder roomResponseDto = RoomResponseDto.builder();

        roomResponseDto.hotelId( roomHotelId( room ) );
        roomResponseDto.id( room.getId() );
        roomResponseDto.name( room.getName() );
        roomResponseDto.description( room.getDescription() );
        roomResponseDto.roomNumber( room.getRoomNumber() );
        roomResponseDto.price( room.getPrice() );
        roomResponseDto.maxGuests( room.getMaxGuests() );

        return roomResponseDto.build();
    }

    private Long roomHotelId(Room room) {
        if ( room == null ) {
            return null;
        }
        Hotel hotel = room.getHotel();
        if ( hotel == null ) {
            return null;
        }
        Long id = hotel.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
