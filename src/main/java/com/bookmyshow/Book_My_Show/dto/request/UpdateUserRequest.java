package com.bookmyshow.Book_My_Show.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UpdateUserRequest {

    private String firstName;

    private String lastName;

    private String mobileNumber;


}
