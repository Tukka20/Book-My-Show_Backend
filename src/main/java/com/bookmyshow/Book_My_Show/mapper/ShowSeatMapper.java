package com.bookmyshow.Book_My_Show.mapper;

import com.bookmyshow.Book_My_Show.dto.reponse.ShowSeatResponse;
import com.bookmyshow.Book_My_Show.entity.ShowSeat;

public class ShowSeatMapper {

    private ShowSeatMapper()
    {

    }


    //map show seat entity with show seat response dto
    public static ShowSeatResponse mapResponseToEntity(ShowSeat showSeat)
    {

        return ShowSeatResponse.builder()
                .id(showSeat.getId())
                .showId(showSeat.getShow().getId())
                .seatId(showSeat.getSeat().getId())
                .seatNumber(showSeat.getSeat().getSeatNumber())
                .seatType(showSeat.getSeat().getSeatType().getName())
                .price(showSeat.getPrice())
                .status(showSeat.getStatus())
                .build();

    }
}
