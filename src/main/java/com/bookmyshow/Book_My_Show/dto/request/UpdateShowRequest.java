package com.bookmyshow.Book_My_Show.dto.request;

import lombok.*;


import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShowRequest {


    private LocalDateTime startTime;

    private LocalDateTime endTime;


}
