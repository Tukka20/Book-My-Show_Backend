package com.bookmyshow.Book_My_Show.dto.reponse;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowSeatResponse {

    private Long id;

    private Long showId;

    private  Long seatId;

    private String seatNumber;

    private String seatType;

    private BigDecimal price;

    private String status;


}
