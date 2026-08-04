package com.bookmyshow.Book_My_Show.repo;

import com.bookmyshow.Book_My_Show.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SeatRepo extends JpaRepository<Seat,Long> {

    List<Seat> findByScreenId(Long screenId);


}
