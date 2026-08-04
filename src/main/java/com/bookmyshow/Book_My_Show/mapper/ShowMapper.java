package com.bookmyshow.Book_My_Show.mapper;

import com.bookmyshow.Book_My_Show.dto.reponse.ShowResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateShowRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateShowRequest;
import com.bookmyshow.Book_My_Show.entity.Movie;
import com.bookmyshow.Book_My_Show.entity.Screen;
import com.bookmyshow.Book_My_Show.entity.Show;

import java.util.ArrayList;

public class ShowMapper {

    private ShowMapper()
    {

    }

    //map show create request dto with show entity
    public static Show mapCreateRequestToEntity(CreateShowRequest request, Movie movie, Screen screen){

        return Show.builder()
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .movie(movie)
                .screen(screen)
                .build();


    }


    //map show update request dto with show entity
    public static void mapUpdateRequestToEntity(UpdateShowRequest request,Show show){

        if(request.getStartTime()!=null){

            show.setStartTime(request.getStartTime());

        }

        if(request.getEndTime()!=null){

            show.setEndTime(request.getEndTime());

        }

    }


    //map show entity with show response dto
    public static ShowResponse mapResponseToDto(Show show){

        return ShowResponse.builder()
                .id(show.getId())
                .startTime(show.getStartTime())
                .endTime(show.getEndTime())
                .movieId(show.getMovie().getId())
                .screenId(show.getScreen().getId())
                .screenName(show.getScreen().getName())
                .theatreId(show.getScreen().getTheatre().getId())
                .theatreName(show.getScreen().getTheatre().getName())
                .theatreCity(show.getScreen().getTheatre().getCity())
                .build();

    }
}
