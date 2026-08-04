package com.bookmyshow.Book_My_Show.repo;

import com.bookmyshow.Book_My_Show.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatRepo extends JpaRepository<ShowSeat ,Long> {

    List<ShowSeat> findAllByIdIn(List<Long> ids);

    List<ShowSeat> findByShowId(Long showId);

    List<ShowSeat> findByShowIdAndStatus(Long showId, String status);

    Optional<ShowSeat> findByShowIdAndSeatId(Long showId, Long seatId);

}
