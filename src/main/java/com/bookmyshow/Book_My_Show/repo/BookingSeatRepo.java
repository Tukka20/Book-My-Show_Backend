package com.bookmyshow.Book_My_Show.repo;

import com.bookmyshow.Book_My_Show.entity.BookingSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingSeatRepo extends JpaRepository<BookingSeat,Long> {

    List<BookingSeat> findByBookingId(Long bookingId);

}
