package com.bookmyshow.Book_My_Show.repo;

import com.bookmyshow.Book_My_Show.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {


    List<Booking> findByUserId(Long userId);

    Optional<Booking> findByBookingNumber(String bookingNumber);

    List<Booking> findByStatusAndBookingDateTimeBefore(String status, LocalDateTime time);

}
