package com.bookmyshow.Book_My_Show.dto.reponse;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserResponse {


    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;



}
