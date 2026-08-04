package com.bookmyshow.Book_My_Show.service;

import com.bookmyshow.Book_My_Show.dto.reponse.BookingResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateBookingRequest;
import com.bookmyshow.Book_My_Show.entity.*;
import com.bookmyshow.Book_My_Show.exception.InvalidRequestException;
import com.bookmyshow.Book_My_Show.exception.ResourceNotFoundException;
import com.bookmyshow.Book_My_Show.mapper.BookingMapper;

import com.bookmyshow.Book_My_Show.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private ShowSeatRepo showSeatRepo;

    @Autowired
    private ShowSeatPricingRepo showSeatPricingRepo;


    //create booking
    @Transactional
    public BookingResponse createBooking(CreateBookingRequest request)
    {
        //Validate User
        User user=userRepo.findById(request.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("No user found with id "+request.getUserId()));

        //Validate Show
        Show show=showRepo.findById(request.getShowId())
                .orElseThrow(()->new ResourceNotFoundException("No show found with id "+request.getShowId()));

        //Load Selected Seats
        List<ShowSeat> showSeats=showSeatRepo.findAllByIdIn(request.getShowSeatIds());

        //validate duplicate seat id
        Set<Long> uniqueSeatIds = new HashSet<>(request.getShowSeatIds());

        if (uniqueSeatIds.size() != request.getShowSeatIds().size()) {
            throw new InvalidRequestException(
                    "Duplicate seat selection is not allowed.");
        }

        //Validate Seat Count
        if (showSeats.size() != uniqueSeatIds.size()) {
            throw new InvalidRequestException(
                    "One or more selected seats are invalid.");
        }

        for(ShowSeat showSeat:showSeats)
        {
            //Validate Seats Belong to the Show
            if(!showSeat.getShow().getId().equals(show.getId()))
            {
                throw new InvalidRequestException("Selected seats do not belong to this show");
            }


        //Check Availability
            if(!showSeat.getStatus().equalsIgnoreCase("AVAILABLE"))
            {
                throw new InvalidRequestException("Seat "+showSeat.getSeat().getSeatNumber()+
                        " is already booked");
            }
        }


        //Calculate Total Amount
        BigDecimal totalAmount=BigDecimal.ZERO;
        for(ShowSeat showSeat:showSeats)
        {

            SeatType seatType=showSeat.getSeat().getSeatType();

            ShowSeatPricing pricing=showSeatPricingRepo.findByShowAndSeatType(show,seatType)
                    .orElseThrow(()->new ResourceNotFoundException("Pricing not found for seat type " +
            seatType.getName() + " in this show"));

            totalAmount=totalAmount.add(pricing.getPrice());
        }

        //Generate Booking Number
        String bookingNumber="BOOK-"+ UUID.randomUUID().toString().substring(0,8).toUpperCase();


        //Create Booking
        Booking booking= BookingMapper.mapCreateRequestToEntity(request,show,user);

        booking.setBookingNumber(bookingNumber);
        booking.setBookingDateTime(LocalDateTime.now());
        booking.setStatus("PENDING");
        booking.setTotalAmount(totalAmount);

        //Create BookingSeat Records
        for (ShowSeat showSeat:showSeats)
        {
            SeatType seatType = showSeat.getSeat().getSeatType();

            ShowSeatPricing pricing = showSeatPricingRepo
                    .findByShowAndSeatType(showSeat.getShow(), seatType)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Pricing not found for seat type " + seatType.getName()));

            BookingSeat bookingSeat=BookingSeat.builder()
                    .booking(booking)
                    .showSeat(showSeat)
                    .ticketPrice(pricing.getPrice())
                    .status("BOOKED")
                    .build();

            booking.getBookingSeats().add(bookingSeat);
            showSeat.setStatus("BOOKED");
        }

        //Save Booking
        Booking savedBooking=bookingRepo.save(booking);

        return BookingMapper.mapResponseToDto(savedBooking);

    }


    //find booking by id
    public BookingResponse findBookingById(Long bookingId)
    {
        Booking booking=bookingRepo.findById(bookingId)
                .orElseThrow(()->new ResourceNotFoundException("No booking found with id "+bookingId));

        return BookingMapper.mapResponseToDto(booking);

    }



    //find booking by user id
    public List<BookingResponse> findBookingsByUserId(Long userId)
    {
        userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("No user found with id "+userId));

        List<Booking> bookings=bookingRepo.findByUserId(userId);

        return bookings.stream()
                .map(BookingMapper::mapResponseToDto)
                .toList();
    }


    //find all bookings
    public List<BookingResponse> findAllBookings()
    {
        List<Booking>bookings=bookingRepo.findAll();

        return bookings.stream()
                .map(BookingMapper::mapResponseToDto)
                .toList();
    }



    //expire pending bookings
    @Transactional
    public void expirePendingBooking()
    {

        List<Booking> expiredBookings=bookingRepo.findByStatusAndBookingDateTimeBefore("PENDING",
                LocalDateTime.now().minusMinutes(5));

        for (Booking booking:expiredBookings )
        {
            booking.setStatus("CANCELLED");

            if (booking.getPayment()!=null)
            {
                booking.getPayment().setPaymentStatus("EXPIRED");
            }

            for (BookingSeat bookingSeat:booking.getBookingSeats())
            {
                ShowSeat showSeat=bookingSeat.getShowSeat();

                showSeat.setStatus("AVAILABLE");
            }
        }
    }




    //cancel booking using id
    @Transactional
    public BookingResponse cancelBooking(Long bookingId)
    {
        Booking booking=bookingRepo.findById(bookingId)
                .orElseThrow(()->new ResourceNotFoundException("No booking found with id "+bookingId));

        if (booking.getStatus().equalsIgnoreCase("CANCELLED"))
        {
            throw new InvalidRequestException("Booking is already cancelled");
        }

        if (booking.getShow().getStartTime().isBefore(LocalDateTime.now()))
        {
            throw new InvalidRequestException("Cannot cancel a booking after the show has started");
        }

        if (booking.getStatus().equalsIgnoreCase("CONFIRMED") && booking.getShow().getStartTime().minusHours(1).isBefore(LocalDateTime.now()))
        {
            throw new InvalidRequestException("Cancellation is no longer allowed");
        }

        booking.setStatus("CANCELLED");

        if(booking.getPayment()!=null && "SUCCESS".equalsIgnoreCase(booking.getPayment().getPaymentStatus()))
        {
            booking.getPayment().setPaymentStatus("REFUNDED");
        }

        for (BookingSeat bookingSeat:booking.getBookingSeats())
        {
            bookingSeat.setStatus("CANCELLED");
            bookingSeat.getShowSeat().setStatus("AVAILABLE");
        }

        Booking savedBooking=bookingRepo.save(booking);

        return BookingMapper.mapResponseToDto(savedBooking);
    }



}
