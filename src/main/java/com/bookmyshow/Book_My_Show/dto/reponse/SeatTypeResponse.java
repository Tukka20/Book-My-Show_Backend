package com.bookmyshow.Book_My_Show.dto.reponse;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SeatTypeResponse {


    private Long id;

    private String name;

    private String description;

    private Integer totalSeats;
}
