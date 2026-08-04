package com.bookmyshow.Book_My_Show.dto.reponse;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreenResponse {

    private Long id;

    private String name;

    private Integer totalSeats;

    private Long theatreId;

    @Builder.Default
    private List<SeatTypeResponse> seatTypes=new ArrayList<>();

}
