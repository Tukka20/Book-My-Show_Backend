package com.bookmyshow.Book_My_Show.dto.reponse;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PaymentResponse {


    private Long id;

    private String transactionId;

    private BigDecimal amount;

    private String paymentMethod;

    private String paymentStatus;

    private LocalDateTime paymentTime;

    private Long bookingId;



}
