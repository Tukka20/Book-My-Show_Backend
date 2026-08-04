package com.bookmyshow.Book_My_Show.repo;

import com.bookmyshow.Book_My_Show.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreRepo extends JpaRepository<Theatre,Long> {

    boolean existsByNameIgnoreCase(String name);

    List<Theatre> findByCityIgnoreCase(String city);

    Optional<Theatre> findByNameIgnoreCase(String name);

    List<Theatre> findByNameContainingIgnoreCase(String name);

}
