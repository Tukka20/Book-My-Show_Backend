package com.bookmyshow.Book_My_Show.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreatePaymentRequest {

    @NotNull(message = "Booking Id is required")
    @Positive(message = "Booking id must be greater than 0")
    private Long bookingId;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;


}
