package com.bookmyshow.Book_My_Show.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMovieRequest {

    private String title;

    private String description;

    @Positive(message = "Duration must be greater than 0")
    private Integer durationMins;

    private String language;

    private String genre;

    private LocalDate releaseDate;

    private String certificate;

    private String posterUrl;

}
