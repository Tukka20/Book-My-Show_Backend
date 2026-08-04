package com.bookmyshow.Book_My_Show.mapper;

import com.bookmyshow.Book_My_Show.dto.reponse.SeatTypeResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateSeatTypeRequest;
import com.bookmyshow.Book_My_Show.entity.SeatType;

public class SeatTypeMapper {

    private SeatTypeMapper()
    {

    }


    //map seat type create request dto with seat type entity
    public static SeatType mapCreateRequestToEntity(CreateSeatTypeRequest request)
    {
        return SeatType.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

    }



    //map seat type entity with seat type response dto
    public static SeatTypeResponse mapResponseToDto (SeatType seatType)
    {

        return SeatTypeResponse.builder()
                .id(seatType.getId())
                .name(seatType.getName())
                .description(seatType.getDescription())
                .totalSeats(seatType.getSeats().size())
                .build();

    }

}
