package com.bookmyshow.Book_My_Show.dto.reponse;

import lombok.*;


import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponse {


    private Long id;

    private Long movieId;

    private Long screenId;

    private String screenName;

    private Long theatreId;

    private String theatreName;

    private String theatreCity;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


}
