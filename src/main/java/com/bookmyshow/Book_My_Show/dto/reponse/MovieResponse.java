package com.bookmyshow.Book_My_Show.dto.reponse;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {

    private Long id;

    private String title;

    private String description;

    private Integer durationMins;

    private String language;

    private String genre;

    private LocalDate releaseDate;

    private String certificate;

    private String posterUrl;

}
