package com.bookmyshow.Book_My_Show.controller;


import com.bookmyshow.Book_My_Show.dto.reponse.MovieResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateMovieRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateMovieRequest;
import com.bookmyshow.Book_My_Show.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    //Create movie
    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody CreateMovieRequest request)
    {
        MovieResponse response=movieService.createMovie(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //get movie by id
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id)
    {
        MovieResponse response=movieService.findByMovieId(id);

        return ResponseEntity.ok(response);
    }



    //get movies by title
    @GetMapping("/title/{title}")
    public ResponseEntity<List<MovieResponse>> getMoviesByTitle(@PathVariable String title)
    {
        List<MovieResponse> responses=movieService.findMovieByTitle(title);

        return ResponseEntity.ok(responses);

    }


    //get movies by language
    @GetMapping("/language/{language}")
    public ResponseEntity<List<MovieResponse>> getMoviesByLanguage(@PathVariable String language)
    {
        List<MovieResponse> responses=movieService.findMovieByLanguage(language);

        return ResponseEntity.ok(responses);
    }


    //get movies by genre
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<MovieResponse>> getMoviesByGenre(@PathVariable String genre)
    {
        List<MovieResponse> responses=movieService.findMovieByGenre(genre);

        return ResponseEntity.ok(responses);
    }


    //get all movies
    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies()
    {
        List<MovieResponse> responses=movieService.getAllMovies();

        return ResponseEntity.ok(responses);
    }


    //update movie
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id, @Valid @RequestBody UpdateMovieRequest request)
    {
        MovieResponse response=movieService.updateMovie(id,request);

        return ResponseEntity.ok(response);
    }



    //delete movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id)
    {
        movieService.deleteMovie(id);

        return ResponseEntity.noContent().build();
    }


}
