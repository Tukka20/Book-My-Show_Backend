package com.bookmyshow.Book_My_Show.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateShowRequest {

    @NotNull(message = "Movie Id is required")
    @Positive(message = "Movie id must be greater than 0")
    private Long movieId;

    @NotNull(message = "Screen Id is required")
    @Positive(message = "Screen Id must be greater than 0")
    private Long screenId;

    @NotNull(message = "Start time is required")
    @Future(message = "Start time must be in the future")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;


    @Valid
    @NotEmpty(message = "Pricing information is required")
    private List<CreateShowSeatPriceRequest> pricing;


}
