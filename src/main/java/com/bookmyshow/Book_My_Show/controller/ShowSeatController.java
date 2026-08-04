package com.bookmyshow.Book_My_Show.controller;

import com.bookmyshow.Book_My_Show.dto.reponse.ShowSeatResponse;
import com.bookmyshow.Book_My_Show.service.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/show-seats")
public class ShowSeatController {

    @Autowired
    private ShowSeatService showSeatService;

    //get show seats by show id
    @GetMapping("show/{showId}")
    public ResponseEntity<List<ShowSeatResponse>> getSeatByShow(@PathVariable Long showId)
    {
        List<ShowSeatResponse> responses=showSeatService.findSeatByShow(showId);

        return ResponseEntity.ok(responses);

    }

}
