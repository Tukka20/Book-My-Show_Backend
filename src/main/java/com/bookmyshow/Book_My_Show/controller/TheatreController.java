package com.bookmyshow.Book_My_Show.controller;

import com.bookmyshow.Book_My_Show.dto.reponse.TheatreResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateTheatreRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateTheatreRequest;
import com.bookmyshow.Book_My_Show.service.TheatreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    //create theatre
    @PostMapping
    public ResponseEntity<TheatreResponse> createTheatre(@Valid @RequestBody CreateTheatreRequest request)
    {
        TheatreResponse response=theatreService.createTheatre(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //get theatre by id
    @GetMapping("/{id}")
    public ResponseEntity<TheatreResponse> getTheatreById(@PathVariable Long id)
    {
        TheatreResponse response=theatreService.findTheatreById(id);

        return ResponseEntity.ok(response);

    }


    //get theatres by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<TheatreResponse>> getTheatresByName(@PathVariable String name)
    {
        List<TheatreResponse> responses=theatreService.findTheatreByName(name);

        return ResponseEntity.ok(responses);

    }


    //get theatres by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<TheatreResponse>> getTheatresByCity(@PathVariable String city)
    {
        List<TheatreResponse> responses=theatreService.findTheatreByCity(city);

        return ResponseEntity.ok(responses);
    }


    //update theatre
    @PutMapping("/{id}")
    public ResponseEntity<TheatreResponse> updateTheatre(@PathVariable Long id, @Valid @RequestBody UpdateTheatreRequest request)
    {
        TheatreResponse response=theatreService.updateTheatre(id,request);

        return ResponseEntity.ok(response);

    }

    //delete theatre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheatre(@PathVariable Long id)
    {
        theatreService.deleteTheatre(id);

        return ResponseEntity.noContent().build();

    }


}
