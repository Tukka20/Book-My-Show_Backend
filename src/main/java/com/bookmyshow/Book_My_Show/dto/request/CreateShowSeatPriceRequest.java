package com.bookmyshow.Book_My_Show.dto.request;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateShowSeatPriceRequest {


    @NotNull(message = "Seat type id is required")
    private Long seatTypeId;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", inclusive = true,
            message = "Price must be greater than zero")
    private BigDecimal price;

}

