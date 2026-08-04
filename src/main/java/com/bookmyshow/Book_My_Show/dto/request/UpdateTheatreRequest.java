package com.bookmyshow.Book_My_Show.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UpdateTheatreRequest {

    private String name;

    private String address;

    private String city;

    private String state;

    @Pattern(
            regexp = "^[1-9][0-9]{5}$",
            message = "Invalid pin code"
    )
    private String pinCode;




}
