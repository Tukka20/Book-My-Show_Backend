package com.bookmyshow.Book_My_Show.scheduler;

import com.bookmyshow.Book_My_Show.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingSchedular {

    private final BookingService bookingService;


    @Scheduled(fixedDelay = 60000)
    public  void expirePendingBooking()
    {
        bookingService.expirePendingBooking();
    }
}
