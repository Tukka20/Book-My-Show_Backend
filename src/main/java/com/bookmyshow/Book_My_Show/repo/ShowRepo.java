package com.bookmyshow.Book_My_Show.repo;

import com.bookmyshow.Book_My_Show.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepo extends JpaRepository<Show,Long> {


    List<Show> findByMovieId(Long id);

    List<Show> findByScreenTheatreId(Long theatreId);

    List<Show> findByScreenId(Long screenId);

    List<Show> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);



}
