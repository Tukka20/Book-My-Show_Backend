package com.bookmyshow.Book_My_Show.mapper;

import com.bookmyshow.Book_My_Show.dto.reponse.MovieResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateMovieRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateMovieRequest;
import com.bookmyshow.Book_My_Show.entity.Movie;

public class MovieMapper {


    private MovieMapper()
    {

    }

    //map movie create request dto with movie entity
    public static Movie mapCreateRequestToEntity(CreateMovieRequest request){

       return Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .certificate(request.getCertificate())
                .durationMins(request.getDurationMins())
                .language(request.getLanguage())
                .genre(request.getGenre())
                .releaseDate(request.getReleaseDate())
                .posterUrl(request.getPosterUrl())
                .build();


    }


    //map movie update request dto with movie entity
    public static void mapUpdateRequestToEntity(UpdateMovieRequest request,Movie movie){

        if(request.getTitle()!=null) {

            movie.setTitle(request.getTitle());

        }

        if(request.getDescription()!=null) {

            movie.setDescription(request.getDescription());

        }

        if(request.getCertificate()!=null){

            movie.setCertificate(request.getCertificate());

        }

        if(request.getDurationMins()!=null) {

            movie.setDurationMins(request.getDurationMins());

        }

        if(request.getLanguage()!=null){

            movie.setLanguage(request.getLanguage());

        }

        if(request.getGenre()!=null) {

            movie.setGenre(request.getGenre());

        }

        if(request.getReleaseDate()!=null){

            movie.setReleaseDate(request.getReleaseDate());

        }

        if(request.getPosterUrl()!=null){

            movie.setPosterUrl(request.getPosterUrl());

        }

    }


    //map movie entity with movie response dto
    public static MovieResponse mapResponseToDto(Movie movie){

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .certificate(movie.getCertificate())
                .durationMins(movie.getDurationMins())
                .language(movie.getLanguage())
                .genre(movie.getGenre())
                .releaseDate(movie.getReleaseDate())
                .posterUrl(movie.getPosterUrl())
                .build();


    }

}
