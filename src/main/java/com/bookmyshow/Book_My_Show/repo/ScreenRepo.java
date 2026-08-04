package com.bookmyshow.Book_My_Show.repo;

import com.bookmyshow.Book_My_Show.entity.Screen;
import com.bookmyshow.Book_My_Show.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScreenRepo extends JpaRepository<Screen,Long> {

    boolean existsByTheatreIdAndNameIgnoreCase(Long theatreId, String name);

    Optional<Screen> findByTheatreIdAndNameIgnoreCase(Long theatreId,String name);

    List<Screen> findByTheatreId(Long theatreId);




}
