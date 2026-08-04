package com.bookmyshow.Book_My_Show.mapper;

import com.bookmyshow.Book_My_Show.dto.reponse.BookingResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateBookingRequest;
import com.bookmyshow.Book_My_Show.entity.Booking;
import com.bookmyshow.Book_My_Show.entity.BookingSeat;
import com.bookmyshow.Book_My_Show.entity.Show;
import com.bookmyshow.Book_My_Show.entity.User;

public class BookingMapper {

    private BookingMapper()
    {

    }


    //map booking create request dto with booking entity
    public static Booking mapCreateRequestToEntity(CreateBookingRequest request, Show show, User user){

        return Booking.builder()
                .user(user)
                .show(show)
                .build();

    }


    //Map booking entity into booking response
    public static BookingResponse mapResponseToDto(Booking booking){

        return BookingResponse.builder()
                .id(booking.getId())
                .bookingNumber(booking.getBookingNumber())
                .bookingDateTime(booking.getBookingDateTime())
                .totalAmount(booking.getTotalAmount())
                .status(booking.getStatus())
                .movieTitle(booking.getShow().getMovie().getTitle())
                .moviePoster(booking.getShow().getMovie().getPosterUrl())
                .theatreName(booking.getShow().getScreen().getTheatre().getName())
                .screenName(booking.getShow().getScreen().getName())
                .showTime(booking.getShow().getStartTime())
                .bookedSeatNumbers(booking.getBookingSeats()
                        .stream()
                        .map(bookingSeat -> bookingSeat.getShowSeat().getSeat().getSeatNumber())
                        .toList())
                .paymentStatus(booking.getPayment().getPaymentStatus())
                .build();


    }
}
