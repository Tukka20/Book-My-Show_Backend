package com.bookmyshow.Book_My_Show.repo;

import com.bookmyshow.Book_My_Show.entity.SeatType;
import com.bookmyshow.Book_My_Show.entity.Show;
import com.bookmyshow.Book_My_Show.entity.ShowSeatPricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowSeatPricingRepo extends JpaRepository<ShowSeatPricing,Long> {

    Optional<ShowSeatPricing> findByShowAndSeatType(Show show, SeatType seatType);
}


