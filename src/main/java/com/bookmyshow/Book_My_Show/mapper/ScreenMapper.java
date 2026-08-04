package com.bookmyshow.Book_My_Show.mapper;

import com.bookmyshow.Book_My_Show.dto.reponse.ScreenResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateScreenRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateScreenRequest;
import com.bookmyshow.Book_My_Show.entity.Screen;
import com.bookmyshow.Book_My_Show.entity.Theatre;

public class ScreenMapper {


    private ScreenMapper()
    {

    }

    //map screen create request dto with screen entity
    public static Screen mapCreateRequestToEntity(CreateScreenRequest request, Theatre theatre){

        return Screen.builder()
                .name(request.getName())
                .theatre(theatre)
                .build();

    }


    //map screen update request dto with screen entity
    public static void mapUpdateRequestToEntity(UpdateScreenRequest request, Screen screen){

        if(request.getName()!=null){

            screen.setName(request.getName());

        }


    }



    //map screen entity with screen response dto
    public static ScreenResponse mapResponseToDto(Screen screen){

        return ScreenResponse.builder()
                .id(screen.getId())
                .name(screen.getName())
                .totalSeats(screen.getTotalSeats())
                .theatreId(screen.getTheatre().getId())
                .seatTypes(screen.getSeatTypes()
                        .stream()
                        .map(SeatTypeMapper::mapResponseToDto)
                        .toList())
                .build();


    }


}
