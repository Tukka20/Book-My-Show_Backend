package com.bookmyshow.Book_My_Show.dto.request;

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

public class CreateBookingRequest {

    @NotNull(message = "User id is required")
    @Positive(message = "User id must be greater than 0")
    private Long userId;

    @NotNull(message = "Show Id is required")
    @Positive(message = "Show id must be greater than 0")
    private Long showId;

    @NotEmpty(message = "At least one seat must be selected")
    private List<@NotNull(message = "Seat id can not be null")
                    @Positive(message = "Show seat id must be greater than 0")
            Long> showSeatIds;

}
