package com.bookmyshow.Book_My_Show.dto.reponse;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TheatreResponse {

    private Long id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String pinCode;

}
