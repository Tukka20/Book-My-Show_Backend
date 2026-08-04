package com.bookmyshow.Book_My_Show.service;

import com.bookmyshow.Book_My_Show.dto.reponse.ShowSeatResponse;
import com.bookmyshow.Book_My_Show.entity.Show;
import com.bookmyshow.Book_My_Show.exception.ResourceNotFoundException;
import com.bookmyshow.Book_My_Show.mapper.ShowSeatMapper;
import com.bookmyshow.Book_My_Show.repo.ShowRepo;
import com.bookmyshow.Book_My_Show.repo.ShowSeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSeatService {

    @Autowired
    private ShowSeatRepo showSeatRepo;

    @Autowired
    private ShowRepo showRepo;

    public List<ShowSeatResponse> findSeatByShow(Long showId)
    {

        Show show=showRepo.findById(showId)
                .orElseThrow(()->new ResourceNotFoundException("No show found with id "+showId));

        return showSeatRepo.findByShowId(show.getId())
                .stream()
                .sorted((a, b) ->
                        a.getSeat().getSeatNumber()
                                .compareTo(b.getSeat().getSeatNumber()))
                .map(ShowSeatMapper::mapResponseToEntity)
                .toList();
    }

}
