package com.bookmyshow.Book_My_Show.service;

import com.bookmyshow.Book_My_Show.dto.reponse.MovieResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateMovieRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateMovieRequest;
import com.bookmyshow.Book_My_Show.entity.Movie;
import com.bookmyshow.Book_My_Show.exception.DuplicateResourceFoundException;
import com.bookmyshow.Book_My_Show.exception.ResourceNotFoundException;
import com.bookmyshow.Book_My_Show.mapper.MovieMapper;
import com.bookmyshow.Book_My_Show.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    //Create movie
    @Transactional
    public MovieResponse createMovie( CreateMovieRequest request){

        if(movieRepo.existsByTitleIgnoreCase(request.getTitle()))
        {
            throw new DuplicateResourceFoundException("Movie already exits with title "+request.getTitle());
        }

        Movie movie= MovieMapper.mapCreateRequestToEntity(request);

        Movie savedMovie=movieRepo.save(movie);

        return MovieMapper.mapResponseToDto(savedMovie);

    }


//Find movie by id
    public MovieResponse findByMovieId(Long movieId){

        Movie movie=movieRepo.findById(movieId)
                .orElseThrow(()->new ResourceNotFoundException("Movie not found with this id "+movieId));

        return MovieMapper.mapResponseToDto(movie);

    }

//Find movie by title
    public List<MovieResponse> findMovieByTitle(String title)
    {

        List<Movie> movies=movieRepo.findByTitleContainingIgnoreCase(title);

        if(movies.isEmpty())
        {
            throw new ResourceNotFoundException("No movies found with title "+title);
        }

        return movies.stream()
                .map(MovieMapper::mapResponseToDto)
                .toList();

    }


//Find movie by language
    public List<MovieResponse> findMovieByLanguage(String language)
    {

        List<Movie> movies=movieRepo.findByLanguage(language);

        if(movies.isEmpty())
        {
            throw new ResourceNotFoundException("No movie found with language "+language);
        }

        return movies.stream()
                .map(MovieMapper::mapResponseToDto)
                .toList();

    }


//Find movies by Genre
    public List<MovieResponse> findMovieByGenre(String genre)
    {
        List<Movie> movies=movieRepo.findByGenre(genre);

        if(movies.isEmpty())
        {
            throw new ResourceNotFoundException("No movie found with genre "+genre);
        }
        return movies.stream()
                .map(MovieMapper::mapResponseToDto)
                .toList();
    }


//Get All movie
    public List<MovieResponse> getAllMovies()
    {

        List<Movie> movies=movieRepo.findAll();

         return movies.stream()
                .map(MovieMapper::mapResponseToDto)
                 .toList();

    }

//Update Movie
    @Transactional
    public MovieResponse updateMovie(Long movieId, UpdateMovieRequest request)
    {

        Movie movie=movieRepo.findById(movieId)
                .orElseThrow(()->new ResourceNotFoundException("No movie found with id "+movieId));

        if(request.getTitle()!=null && !request.getTitle().equalsIgnoreCase(movie.getTitle()))
        {
            Optional<Movie> existingMovie=movieRepo.findByTitleIgnoreCase(request.getTitle());

            if (existingMovie.isPresent() && !existingMovie.get().getId().equals(movieId)) {

                throw new DuplicateResourceFoundException(
                        "Movie already exists with title " + request.getTitle());
            }
        }
        MovieMapper.mapUpdateRequestToEntity(request,movie);
        return MovieMapper.mapResponseToDto(movie);

    }


//Delete movie
    @Transactional
    public void deleteMovie(Long movieId)
    {

        Movie movie=movieRepo.findById(movieId)
                .orElseThrow(()->new ResourceNotFoundException("No movie found with id "+movieId));


        if (!movie.getShows().isEmpty())
        {
            throw new IllegalStateException("Cannot delete a movie with scheduled shows");
        }

        movieRepo.delete(movie);

    }

}
