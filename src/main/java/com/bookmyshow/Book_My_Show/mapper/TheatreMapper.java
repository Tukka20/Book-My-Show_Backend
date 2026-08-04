package com.bookmyshow.Book_My_Show.mapper;

import com.bookmyshow.Book_My_Show.dto.reponse.TheatreResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateTheatreRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateTheatreRequest;
import com.bookmyshow.Book_My_Show.entity.Theatre;

public class TheatreMapper {


    private TheatreMapper()
    {

    }


    //map theatre create request dto with theatre entity
    public static Theatre mapCreateRequestToEntity(CreateTheatreRequest request) {

        return Theatre.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .pinCode(request.getPinCode())
                .build();

    }


    //map theatre update request dto with theatre entity
    public static void mapUpdateRequestToEntity(UpdateTheatreRequest request,Theatre theatre){

        if(request.getName()!=null){
            theatre.setName(request.getName());
        }

        if(request.getAddress()!=null){
            theatre.setAddress(request.getAddress());
        }

        if(request.getCity()!=null){
            theatre.setCity(request.getCity());
        }

        if(request.getState()!=null){
            theatre.setState(request.getState());
        }

        if(request.getPinCode()!=null){
            theatre.setPinCode(request.getPinCode());
        }

    }

    //map theatre entity with theatre response dto
    public static TheatreResponse mapResponseToDto(Theatre theatre){

        return TheatreResponse.builder()
                .id(theatre.getId())
                .name(theatre.getName())
                .address(theatre.getAddress())
                .city(theatre.getCity())
                .state(theatre.getState())
                .pinCode(theatre.getPinCode())
                .build();

    }
}
