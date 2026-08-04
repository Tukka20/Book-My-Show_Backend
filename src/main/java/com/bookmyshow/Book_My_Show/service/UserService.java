package com.bookmyshow.Book_My_Show.service;

import com.bookmyshow.Book_My_Show.dto.reponse.UserResponse;
import com.bookmyshow.Book_My_Show.dto.request.*;
import com.bookmyshow.Book_My_Show.entity.User;
import com.bookmyshow.Book_My_Show.exception.DuplicateResourceFoundException;
import com.bookmyshow.Book_My_Show.exception.InvalidRequestException;
import com.bookmyshow.Book_My_Show.exception.ResourceNotFoundException;
import com.bookmyshow.Book_My_Show.mapper.UserMapper;
import com.bookmyshow.Book_My_Show.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;



    //Register user
    @Transactional
    public UserResponse registerUser(RegisterUserRequest request)
    {
        if(userRepo.existsByEmail(request.getEmail()))
        {
            throw new DuplicateResourceFoundException("Email is already registered");
        }

        if(userRepo.existsByMobileNumber(request.getMobileNumber()))
        {
            throw new DuplicateResourceFoundException("The mobile number is already registered");
        }

        User user= UserMapper.mapRegisterRequestToEntity(request);

        User savedUser=userRepo.save(user);

        return UserMapper.mapResponseToDto(savedUser);

    }



    //login user
    public UserResponse login(LoginUserRequest request)  {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid Email"));

        if (!user.getPassword().equals(request.getPassword())) {

            throw new InvalidRequestException("Invalid Password");

        }

        return UserMapper.mapResponseToDto(user);

    }



    //find user by id
    public UserResponse findUserById(Long id)
    {

        User user=userRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No user found with id "+id));

        return UserMapper.mapResponseToDto(user);

    }


    //find user by email
    public UserResponse findUserByEmail(String email)
    {
        User user=userRepo.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("No user found with email "+email));

        return UserMapper.mapResponseToDto(user);

    }


    //find all user
    public List<UserResponse> findAllUsers()
    {
        List<User> users=userRepo.findAll();

        return users.stream()
                .map(UserMapper::mapResponseToDto)
                .toList();
    }


    //update user
    @Transactional
    public UserResponse updateUser(Long userId, UpdateUserRequest request)
    {
        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("No user found with id "+userId));

        if(request.getMobileNumber()!=null && !request.getMobileNumber().equals(user.getMobileNumber()))
        {
            if (userRepo.existsByMobileNumber(request.getMobileNumber()))
            {
                throw new DuplicateResourceFoundException("Phone number is already registered");
            }
        }
        UserMapper.mapUpdateRequestToEntity(request,user);

        return UserMapper.mapResponseToDto(user);
    }


    //update user's password
    @Transactional
    public UserResponse updateUserPassword(Long userId, ChangePasswordRequest request)
    {
        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("No user found with id "+userId));

        if(!user.getPassword().equals(request.getCurrentPassword()))
        {
            throw new InvalidRequestException("Current password is incorrect");
        }

        if(!request.getNewPassword().equals(request.getConfirmPassword()))
        {

            throw new InvalidRequestException("New password and confirm password do not match");

        }

        user.setPassword(request.getNewPassword());

        return UserMapper.mapResponseToDto(user);

    }


    //delete user
    @Transactional
    public void deleteUser(Long userId)
    {
        User user=userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("No user found with id "+userId));

        if(!user.getBookings().isEmpty())
        {
            throw new IllegalStateException("Can not delete user with existing bookings");
        }

        userRepo.delete(user);
    }

}
