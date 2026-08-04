package com.bookmyshow.Book_My_Show.controller;

import com.bookmyshow.Book_My_Show.dto.reponse.UserResponse;
import com.bookmyshow.Book_My_Show.dto.request.*;
import com.bookmyshow.Book_My_Show.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    //register user
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request)
    {

        UserResponse userResponse=userService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);

    }


    //login user
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @RequestBody LoginUserRequest request)  {

        return ResponseEntity.ok(
                userService.login(request)
        );

    }


    //get user by user id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id)
    {

        UserResponse response=userService.findUserById(id);

        return ResponseEntity.ok(response);

    }


    //get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email)
    {

        UserResponse response=userService.findUserByEmail(email);

        return ResponseEntity.ok(response);

    }


    //get all user
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser()
    {
        List<UserResponse> responses=userService.findAllUsers();

        return ResponseEntity.ok(responses);
    }


    //update user
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request)
    {

        UserResponse response=userService.updateUser(id,request);

        return ResponseEntity.ok(response);

    }


    //update user's password
    @PatchMapping("/{id}/password")
    public ResponseEntity<UserResponse> updateUserPassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest request)
    {

        UserResponse response=userService.updateUserPassword(id,request);

        return ResponseEntity.ok(response);

    }



    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }



}
