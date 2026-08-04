package com.bookmyshow.Book_My_Show.dto.request;


import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSeatTypeRequest {


    @NotBlank(message = "Seat type name is required")
    private String name;


    private String description;


    @NotBlank(message = "Start row is required")
    @Pattern(
            regexp = "^[A-Z]$",
    message = "Start row must be a single uppercase letter"
    )
    private String startRow;


    @NotBlank(message = "End row is required")
    @Pattern(
            regexp = "^[A-Z]$",
            message = "End row must be a single uppercase letter"
    )
    private String endRow;


    @NotNull(message = "Seats per row is required")
    @Positive(message = "Seats per row must be greater than 0")
    private Integer seatsPerRow;
}
