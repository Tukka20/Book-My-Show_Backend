package com.bookmyshow.Book_My_Show.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor@NoArgsConstructor

public class UpdateSeatTypeRequest {

    private String name;

    private String description;


}
