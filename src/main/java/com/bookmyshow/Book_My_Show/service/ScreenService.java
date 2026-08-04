package com.bookmyshow.Book_My_Show.service;

import com.bookmyshow.Book_My_Show.dto.reponse.ScreenResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateScreenRequest;
import com.bookmyshow.Book_My_Show.dto.request.CreateSeatTypeRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateScreenRequest;
import com.bookmyshow.Book_My_Show.entity.Screen;
import com.bookmyshow.Book_My_Show.entity.Seat;
import com.bookmyshow.Book_My_Show.entity.SeatType;
import com.bookmyshow.Book_My_Show.entity.Theatre;
import com.bookmyshow.Book_My_Show.exception.DuplicateResourceFoundException;
import com.bookmyshow.Book_My_Show.exception.InvalidRequestException;
import com.bookmyshow.Book_My_Show.exception.ResourceNotFoundException;
import com.bookmyshow.Book_My_Show.mapper.ScreenMapper;
import com.bookmyshow.Book_My_Show.mapper.SeatTypeMapper;
import com.bookmyshow.Book_My_Show.repo.ScreenRepo;
import com.bookmyshow.Book_My_Show.repo.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepo screenRepo;

    @Autowired
    private TheatreRepo theatreRepo;

    //Create screen
    @Transactional
    public ScreenResponse createScreen(CreateScreenRequest request)
    {
        Theatre theatre=theatreRepo.findById(request.getTheatreId())
                .orElseThrow(()->new ResourceNotFoundException("No theatre find with id "+request.getTheatreId()));


        if(screenRepo.existsByTheatreIdAndNameIgnoreCase(request.getTheatreId(),request.getName()))
        {

            throw new DuplicateResourceFoundException("Screen is already exists in this theatre");

        }

        Screen screen= ScreenMapper.mapCreateRequestToEntity(request,theatre);

        int totalSeats=0;

        Set<String> seatTypeNames = new HashSet<>();

        for (CreateSeatTypeRequest seatTypeRequest : request.getSeatTypes())
        {

            String seatTypeName = seatTypeRequest.getName().trim().toLowerCase();

            if (!seatTypeNames.add(seatTypeName))
            {
                throw new DuplicateResourceFoundException(
                        "Duplicate seat type '" + seatTypeRequest.getName() + "' found.");
            }

            if (seatTypeRequest.getSeatsPerRow() <= 0)
            {
                throw new InvalidRequestException(
                        "Seats per row must be greater than 0.");
            }

            if (seatTypeRequest.getStartRow().compareTo(seatTypeRequest.getEndRow()) > 0)
            {
                throw new InvalidRequestException(
                        "Start row cannot be after end row.");
            }

            SeatType seatType =
                    SeatTypeMapper.mapCreateRequestToEntity(seatTypeRequest);

            seatType.setScreen(screen);

            screen.getSeatTypes().add(seatType);

            char start = seatTypeRequest.getStartRow().charAt(0);
            char end = seatTypeRequest.getEndRow().charAt(0);

            for (char row = start; row <= end; row++) {

                for (int seatNo = 1;
                     seatNo <= seatTypeRequest.getSeatsPerRow();
                     seatNo++) {

                    Seat seat = Seat.builder()
                            .seatNumber(row + String.valueOf(seatNo))
                            .screen(screen)
                            .seatType(seatType)
                            .build();

                    screen.getSeats().add(seat);
                    seatType.getSeats().add(seat);

                    totalSeats++;
                }
            }
        }

        screen.setTotalSeats(totalSeats);

        Screen savedScreen = screenRepo.save(screen);

        return ScreenMapper.mapResponseToDto(savedScreen);

    }



    //find By screen id
    public ScreenResponse findByScreenId(Long id)
    {
        Screen screen=screenRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No screen found with id "+id));

        return ScreenMapper.mapResponseToDto(screen);
    }



    //find screens by theatre
    public List<ScreenResponse> findScreensByTheatre(Long id)
    {

       theatreRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No theatre found with id "+id));

       List<Screen> screens=screenRepo.findByTheatreId(id);

       if(screens.isEmpty())
       {
           throw new ResourceNotFoundException("No screens found with this theatre id "+id);
       }

       return screens.stream()
               .map(ScreenMapper::mapResponseToDto)
               .toList();

    }


    //gets all screens
    public List<ScreenResponse> findAllScreens()
    {
        List<Screen> screens=screenRepo.findAll();

        return screens.stream()
                .map(ScreenMapper::mapResponseToDto)
                .toList();
    }


    //update screen
    @Transactional
    public ScreenResponse updateScreen(Long id, UpdateScreenRequest request)
    {

        Screen screen=screenRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No screen found with id "+id));


        if(request.getName()!=null && !request.getName().equalsIgnoreCase(screen.getName()))
        {
            Optional<Screen> existingScreen = screenRepo.findByTheatreIdAndNameIgnoreCase(
                    screen.getTheatre().getId(), request.getName());

            if (existingScreen.isPresent() && !existingScreen.get().getId().equals(id)) {
               throw new DuplicateResourceFoundException("Screen already exists in the theatre with name " + request.getName());
           }

        }

        ScreenMapper.mapUpdateRequestToEntity(request, screen);

        return ScreenMapper.mapResponseToDto(screen);



    }



    //Delete screen
    @Transactional
    public void deleteScreen(Long id)
    {
        Screen screen=screenRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No screen find with id "+id));

        if (!screen.getShows().isEmpty())
        {
            throw new InvalidRequestException("Cannot delete screen with scheduled  shows");
        }


        screenRepo.delete(screen);
    }




}
