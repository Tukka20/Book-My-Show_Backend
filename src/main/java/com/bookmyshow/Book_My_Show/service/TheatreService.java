package com.bookmyshow.Book_My_Show.service;

import com.bookmyshow.Book_My_Show.dto.reponse.TheatreResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreateTheatreRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateTheatreRequest;
import com.bookmyshow.Book_My_Show.entity.Theatre;
import com.bookmyshow.Book_My_Show.exception.DuplicateResourceFoundException;
import com.bookmyshow.Book_My_Show.exception.ResourceNotFoundException;
import com.bookmyshow.Book_My_Show.mapper.TheatreMapper;
import com.bookmyshow.Book_My_Show.repo.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {


    @Autowired
    private TheatreRepo theatreRepo;

    //Create theatre
    @Transactional
    public TheatreResponse createTheatre(CreateTheatreRequest request)
    {
        if(theatreRepo.existsByNameIgnoreCase(request.getName()))
        {
            throw new DuplicateResourceFoundException("Theatre already exits with name "+request.getName());
        }

        Theatre theatre= TheatreMapper.mapCreateRequestToEntity(request);

        Theatre saveTheatre=theatreRepo.save(theatre);

        return TheatreMapper.mapResponseToDto(saveTheatre);
    }


    //Find theatre by id
    public TheatreResponse findTheatreById(Long id)
    {
        Theatre theatre=theatreRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No theatre found with id "+id));

        return TheatreMapper.mapResponseToDto(theatre);
    }


    //Find theatre by name
    public List<TheatreResponse> findTheatreByName(String name)
    {

        List<Theatre> theatres =theatreRepo.findByNameContainingIgnoreCase(name);

        if(theatres.isEmpty())
        {
            throw new ResourceNotFoundException("No theatre found with name "+name);
        }

        return theatres.stream()
                .map(TheatreMapper::mapResponseToDto)
                .toList();

    }



    //Find theatre by city
    public List<TheatreResponse> findTheatreByCity(String city)
    {

        List<Theatre> theatres=theatreRepo.findByCityIgnoreCase(city);

        if(theatres.isEmpty())
        {
            throw  new ResourceNotFoundException("No theatre found with city "+city);
        }

        return theatres.stream()
                .map(TheatreMapper::mapResponseToDto)
                .toList();

    }



    //Update theatre
    @Transactional
    public TheatreResponse updateTheatre(Long id, UpdateTheatreRequest request)
    {

        Theatre theatre=theatreRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No theatre found with id "+id));

        if(request.getName()!=null && !request.getName().equalsIgnoreCase(theatre.getName()))
        {
            Optional<Theatre> existingTheatre=theatreRepo.findByNameIgnoreCase(request.getName());

            if(existingTheatre.isPresent() && !existingTheatre.get().getId().equals(id))
            {
                throw new DuplicateResourceFoundException("Theatre already exists with name "+request.getName());
            }
        }

        TheatreMapper.mapUpdateRequestToEntity(request,theatre);

        return TheatreMapper.mapResponseToDto(theatre);

    }



    //delete theatre
    @Transactional
    public void deleteTheatre(Long id)
    {

        Theatre theatre=theatreRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No theatre found with id "+id));

        if (!theatre.getScreens().isEmpty())
        {
            throw new IllegalStateException("Cannot delete theatre with existing screens");
        }

        theatreRepo.delete(theatre);

    }


}
