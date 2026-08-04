package com.bookmyshow.Book_My_Show.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateMovieRequest {

    @NotBlank(message = "Movie title is required")
    private String title;

    @NotBlank(message = "Movie description is required")
    private String description;

    @NotBlank(message = "Certificate is required")
    private String certificate;

    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be greater than 0")
    private Integer durationMins;

    @NotBlank(message = "Language is required")
    private String language;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotNull(message = "Release Date is required")
    private LocalDate releaseDate;

    private String posterUrl;




}
