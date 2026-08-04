package com.bookmyshow.Book_My_Show.controller;

import com.bookmyshow.Book_My_Show.dto.reponse.ScreenResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateScreenRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateScreenRequest;
import com.bookmyshow.Book_My_Show.service.ScreenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/screens")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    //create screen
    @PostMapping
    public ResponseEntity<ScreenResponse> createScreen(@Valid @RequestBody CreateScreenRequest request)
    {

        ScreenResponse response=screenService.createScreen(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    //get screen by id
    @GetMapping("/{id}")
    public ResponseEntity<ScreenResponse> getScreenById(@PathVariable Long id)
    {

        ScreenResponse response=screenService.findByScreenId(id);

        return ResponseEntity.ok(response);

    }


    //get screens by theatre id
    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<ScreenResponse>> getScreensByTheatreId(@PathVariable Long theatreId)
    {

        List<ScreenResponse> responses=screenService.findScreensByTheatre(theatreId);

        return ResponseEntity.ok(responses);

    }


    //get all screens
    @GetMapping
    public ResponseEntity<List<ScreenResponse>> getAllScreens()
    {
        List<ScreenResponse> responses=screenService.findAllScreens();

        return ResponseEntity.ok(responses);

    }


    //update screen
    @PutMapping("/{id}")
    public ResponseEntity<ScreenResponse> updateScreen(@PathVariable Long id, @Valid @RequestBody UpdateScreenRequest request)
    {

        ScreenResponse response=screenService.updateScreen(id,request);

        return ResponseEntity.ok(response);

    }


    //delete screen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScreen(@PathVariable Long id)
    {

        screenService.deleteScreen(id);

        return ResponseEntity.noContent().build();

    }
}
