package com.bookmyshow.Book_My_Show.controller;

import com.bookmyshow.Book_My_Show.dto.reponse.BookingResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateBookingRequest;
import com.bookmyshow.Book_My_Show.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/bookings")
public class BookingController {


    @Autowired
    private BookingService bookingService;


    //create booking
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody CreateBookingRequest request)
    {

        BookingResponse response=bookingService.createBooking(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    //get booking by id
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id)
    {

        BookingResponse response=bookingService.findBookingById(id);

        return ResponseEntity.ok(response);

    }


    //get bookings by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByUserId(@PathVariable Long userId)
    {

        List<BookingResponse> responses=bookingService.findBookingsByUserId(userId);

        return ResponseEntity.ok(responses);

    }


    //get all bookings
    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings()
    {

        List<BookingResponse> responses=bookingService.findAllBookings();

        return ResponseEntity.ok(responses);

    }


    //cancel booking
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(@PathVariable Long id)
    {
        BookingResponse response=bookingService.cancelBooking(id);

        return ResponseEntity.ok(response);

    }


}


