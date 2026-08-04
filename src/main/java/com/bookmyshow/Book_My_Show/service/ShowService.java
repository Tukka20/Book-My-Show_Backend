package com.bookmyshow.Book_My_Show.service;

import com.bookmyshow.Book_My_Show.dto.reponse.ShowResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateShowRequest;
import com.bookmyshow.Book_My_Show.dto.request.CreateShowSeatPriceRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateShowRequest;
import com.bookmyshow.Book_My_Show.entity.*;
import com.bookmyshow.Book_My_Show.exception.InvalidRequestException;
import com.bookmyshow.Book_My_Show.exception.ResourceNotFoundException;
import com.bookmyshow.Book_My_Show.mapper.ShowMapper;
import com.bookmyshow.Book_My_Show.repo.MovieRepo;
import com.bookmyshow.Book_My_Show.repo.ScreenRepo;
import com.bookmyshow.Book_My_Show.repo.ShowRepo;
import com.bookmyshow.Book_My_Show.repo.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ScreenRepo screenRepo;

    @Autowired
    private TheatreRepo theatreRepo;




    //validate show timing
    private void validateShowTiming(Long screenId, LocalDateTime startTime,
                                   LocalDateTime endTime,Long currentShowId)
    {

        List<Show> existingShows=showRepo.findByScreenId(screenId);

        for( Show existingShow : existingShows)
        {
            if(currentShowId!=null && existingShow.getId().equals(currentShowId))
            {
                continue;
            }

            if(startTime.isBefore(existingShow.getEndTime()) && endTime.isAfter(existingShow.getStartTime()))
            {
                throw new InvalidRequestException("Another show is already scheduled during this time");
            }
        }

    }


    //validate pricing
    private void validatePricing(Screen screen, CreateShowRequest request) {

        // Get all unique SeatTypes available in this screen
        Map<Long, SeatType> screenSeatTypes = screen.getSeats()
                .stream()
                .map(Seat::getSeatType)
                .collect(Collectors.toMap(
                        SeatType::getId,
                        Function.identity(),
                        (existing, duplicate) -> existing
                ));

        // Check duplicate seat types in request
        Set<Long> requestedSeatTypeIds = new HashSet<>();

        for (CreateShowSeatPriceRequest pricing : request.getPricing()) {

            if (!requestedSeatTypeIds.add(pricing.getSeatTypeId())) {
                throw new InvalidRequestException(
                        "Duplicate pricing found for seat type id "
                                + pricing.getSeatTypeId());
            }

            if (!screenSeatTypes.containsKey(pricing.getSeatTypeId())) {
                throw new InvalidRequestException(
                        "Seat type id "
                                + pricing.getSeatTypeId()
                                + " does not belong to this screen.");
            }
        }

        // Check missing pricing
        if (requestedSeatTypeIds.size() != screenSeatTypes.size()) {
            throw new InvalidRequestException(
                    "Pricing must be provided for every seat type.");
        }
    }



    //generate show seat pricing
    private void generateShowSeatPricing(
            Show show,
            Screen screen,
            CreateShowRequest request) {

        // Build a map of SeatType ID -> SeatType from the screen
        Map<Long, SeatType> seatTypeMap = screen.getSeats()
                .stream()
                .map(Seat::getSeatType)
                .collect(Collectors.toMap(
                        SeatType::getId,
                        Function.identity(),
                        (existing, duplicate) -> existing
                ));

        for (CreateShowSeatPriceRequest pricingRequest : request.getPricing()) {

            SeatType seatType = seatTypeMap.get(pricingRequest.getSeatTypeId());


            ShowSeatPricing showPricing = ShowSeatPricing.builder()
                    .show(show)
                    .seatType(seatType)
                    .price(pricingRequest.getPrice())
                    .build();

            show.getPricing().add(showPricing);
        }
    }


    //generate Show seat
    private void generateShowSeats(Show show, Screen screen) {

        Map<Long, BigDecimal> pricingMap = show.getPricing()
                .stream()
                .collect(Collectors.toMap(
                        pricing -> pricing.getSeatType().getId(),
                        ShowSeatPricing::getPrice
                ));

        for (Seat seat : screen.getSeats()) {

            BigDecimal price = pricingMap.get(seat.getSeatType().getId());

            if (price == null) {
                throw new InvalidRequestException(
                        "No price found for seat type "
                                + seat.getSeatType().getName()
                );
            }

            ShowSeat showSeat = ShowSeat.builder()
                    .show(show)
                    .seat(seat)
                    .price(price)
                    .status("AVAILABLE")
                    .build();

            show.getShowSeats().add(showSeat);
        }
    }


    //create show
    @Transactional
    public ShowResponse createShow(CreateShowRequest request) {
        //validate movie
        Movie movie = movieRepo.findById(request.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("No movie found with id " + request.getMovieId()));


        //validate screen
        Screen screen = screenRepo.findById(request.getScreenId())
                .orElseThrow(() -> new ResourceNotFoundException("No screen found with id " + request.getScreenId()));

        if (!request.getStartTime().isBefore(request.getEndTime())) {
            throw new InvalidRequestException("Start time must be before end time");
        }

        validateShowTiming(request.getScreenId(), request.getStartTime(),request.getEndTime(),null);


        validatePricing(screen, request);

        Show show = ShowMapper.mapCreateRequestToEntity(request, movie, screen);

        generateShowSeatPricing(show, screen, request);

        generateShowSeats(show, screen);

        Show savedShow = showRepo.save(show);

        return ShowMapper.mapResponseToDto(savedShow);

    }


    //find show by id
    public ShowResponse findShowById(Long id) {
        Show show = showRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No show found with id " + id));

        return ShowMapper.mapResponseToDto(show);
    }


    //find shows by movie
    public List<ShowResponse> findShowsByMovie(Long id) {
        movieRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No show found with this movie id " + id));

        List<Show> shows = showRepo.findByMovieId(id);

        if (shows.isEmpty()) {
            throw new ResourceNotFoundException("No shows available with movie id" + id);
        }

        return shows.stream()
                .map(ShowMapper::mapResponseToDto)
                .toList();
    }


    //find shows by screen id

    public List<ShowResponse> findShowsByScreen(Long id) {
        screenRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No screen found with id " + id));

        List<Show> shows = showRepo.findByScreenId(id);

        if (shows.isEmpty()) {
            throw new ResourceNotFoundException("No shows found with screen id " + id);
        }

        return shows.stream()
                .map(ShowMapper::mapResponseToDto)
                .toList();

    }


    //find shows by theatre id
    public List<ShowResponse> findShowsByTheatre(Long id) {

        theatreRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No theatre found with id " + id));

        List<Show> shows = showRepo.findByScreenTheatreId(id);

        if (shows.isEmpty()) {
            throw new ResourceNotFoundException("No shows available with id " + id);
        }

        return shows.stream()
                .map(ShowMapper::mapResponseToDto)
                .toList();

    }


    //find shows by date
    public List<ShowResponse> findShowsByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<Show> shows = showRepo.findByStartTimeBetween(startOfDay, endOfDay);

        if (shows.isEmpty()) {
            throw new ResourceNotFoundException("No shows available for date " + date);
        }

        return shows.stream()
                .map(ShowMapper::mapResponseToDto)
                .toList();
    }


    //find all shows
    public List<ShowResponse> findAllShows() {
        List<Show> shows = showRepo.findAll();

        return shows.stream()
                .map(ShowMapper::mapResponseToDto)
                .toList();
    }


    //update show
    @Transactional
    public ShowResponse updateShow(Long id, UpdateShowRequest request) {
        Show show = showRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No show found with id " + id));

        LocalDateTime updatedStartTime = request.getStartTime() != null ? request.getStartTime() : show.getStartTime();

        LocalDateTime updatedEndTime = request.getEndTime() != null ? request.getEndTime() : show.getEndTime();


        if (!updatedStartTime.isBefore(updatedEndTime)) {
            throw new InvalidRequestException("Start time must be before end time");
        }

        validateShowTiming(show.getScreen().getId(),updatedStartTime,updatedEndTime,show.getId());

        ShowMapper.mapUpdateRequestToEntity(request, show);

        return ShowMapper.mapResponseToDto(show);
    }


    //delete show
    @Transactional
    public void deleteShow(Long id) {
        Show show = showRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No show found with id " + id));

        showRepo.delete(show);
    }


}
