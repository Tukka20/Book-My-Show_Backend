package com.bookmyshow.Book_My_Show.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTheatreRequest {

    @NotBlank(message = "Theatre name is required")
    private String name;


    @NotBlank(message = "Address is required")
    private String address;


    @NotBlank(message = "City is required")
    private String city;


    @NotBlank(message = "State is required")
    private String state;


    @Pattern(
            regexp = "^[1-9][0-9]{5}$",
            message = "Invalid pin code"

    )
    @NotBlank(message = "Pin Code is required")
    private String pinCode;

}
