package com.bookmyshow.Book_My_Show.dto.reponse;

import com.bookmyshow.Book_My_Show.entity.BookingSeat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BookingResponse {

    private Long id;

    private String bookingNumber;

    private LocalDateTime  bookingDateTime;

    private String status;

    private BigDecimal totalAmount;

   private String movieTitle;

   private String moviePoster;

   private String theatreName;

   private String screenName;

   private LocalDateTime showTime;

   private List<String> bookedSeatNumbers;

   private String paymentStatus;






}
