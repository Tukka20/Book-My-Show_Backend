package com.bookmyshow.Book_My_Show.controller;

import com.bookmyshow.Book_My_Show.dto.reponse.ShowResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateShowRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateShowRequest;
import com.bookmyshow.Book_My_Show.service.ShowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    private ShowService showService;


    //create show
    @PostMapping
    public ResponseEntity<ShowResponse> createShow(@Valid @RequestBody CreateShowRequest request)
    {

        ShowResponse response=showService.createShow(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    //get show by id
    @GetMapping("/{id}")
    public ResponseEntity<ShowResponse> getShowById(@PathVariable Long id)
    {

        ShowResponse response=showService.findShowById(id);

        return ResponseEntity.ok(response);

    }


    //get shows by movie id
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowResponse>> getShowsByMovieId(@PathVariable Long movieId)
    {

        List<ShowResponse> responses=showService.findShowsByMovie(movieId);

        return ResponseEntity.ok(responses);

    }


    //get shows by screen id
    @GetMapping("/screen/{screenId}")
    public ResponseEntity<List<ShowResponse>> getShowsByScreenId(@PathVariable Long screenId)
    {

        List<ShowResponse> responses=showService.findShowsByScreen(screenId);

        return ResponseEntity.ok(responses);
    }


    //get shows by theatre id
    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<ShowResponse>> getShowsByTheatreId(@PathVariable Long theatreId)
    {

        List<ShowResponse> responses=showService.findShowsByTheatre(theatreId);

        return ResponseEntity.ok(responses);

    }


    //get shows by date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<ShowResponse>> getShowsByDate(@PathVariable LocalDate date)
    {
        List<ShowResponse> responses=showService.findShowsByDate(date);

        return ResponseEntity.ok(responses);

    }


    //get all show
    @GetMapping
    public ResponseEntity<List<ShowResponse>> getAllShows()
    {
        List<ShowResponse> responses=showService.findAllShows();

        return ResponseEntity.ok(responses);

    }


    //update show
    @PutMapping("/{id}")
    public ResponseEntity<ShowResponse> updateShow(@PathVariable Long id, @Valid @RequestBody UpdateShowRequest request)
    {

        ShowResponse response=showService.updateShow(id,request);

        return ResponseEntity.ok(response);
    }


    //delete show
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id)
    {

        showService.deleteShow(id);

        return ResponseEntity.noContent().build();

    }


}
