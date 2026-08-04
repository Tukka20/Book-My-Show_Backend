package com.bookmyshow.Book_My_Show.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateScreenRequest {

    @NotBlank(message = "Screen name is required")
    private String name;


    @NotNull(message = "Theatre id is required")
    @Positive(message = "Theatre id must be greater than 0")
    private Long theatreId;

    @NotEmpty(message = "At least one seat type is required")
    @Valid
    private List<CreateSeatTypeRequest> seatTypes;
}
