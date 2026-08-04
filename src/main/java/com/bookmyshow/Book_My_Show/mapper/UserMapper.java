package com.bookmyshow.Book_My_Show.mapper;

import com.bookmyshow.Book_My_Show.dto.reponse.UserResponse;
import com.bookmyshow.Book_My_Show.dto.request.RegisterUserRequest;
import com.bookmyshow.Book_My_Show.dto.request.UpdateUserRequest;
import com.bookmyshow.Book_My_Show.entity.User;

public class UserMapper {

    private UserMapper()
    {

    }

    //map user register request dto with user entity
    public static User mapRegisterRequestToEntity(RegisterUserRequest request){
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .mobileNumber(request.getMobileNumber())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

    }


    //map user update request dto with user entity
    public static void mapUpdateRequestToEntity(UpdateUserRequest request, User user){

        if(request.getFirstName()!=null){

            user.setFirstName(request.getFirstName());
        }

        if(request.getLastName()!=null){

            user.setLastName(request.getLastName());

        }

        if(request.getMobileNumber()!=null){

            user.setMobileNumber(request.getMobileNumber());

        }


    }


    //map user entity with user response dto
    public static UserResponse mapResponseToDto(User user){

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobileNumber(user.getMobileNumber())
                .email(user.getEmail())
                .build();


    }

}
