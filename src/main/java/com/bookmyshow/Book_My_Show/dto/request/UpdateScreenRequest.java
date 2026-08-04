package com.bookmyshow.Book_My_Show.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateScreenRequest {

    private String name;


}
