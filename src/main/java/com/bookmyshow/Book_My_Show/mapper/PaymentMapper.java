package com.bookmyshow.Book_My_Show.mapper;

import com.bookmyshow.Book_My_Show.dto.reponse.PaymentResponse;
import com.bookmyshow.Book_My_Show.dto.request.CreatePaymentRequest;
import com.bookmyshow.Book_My_Show.entity.Booking;
import com.bookmyshow.Book_My_Show.entity.Payment;

public class PaymentMapper {


    private PaymentMapper()
    {

    }


    //map payment create request dto with payment entity
    public static Payment mapCreateRequestToEntity(CreatePaymentRequest request, Booking booking ){

        return Payment.builder()
                .paymentMethod(request.getPaymentMethod())
                .booking(booking)
                .build();

    }



    //map payment entity with payment response dto
    public static PaymentResponse mapResponseToDto(Payment payment){

        return PaymentResponse.builder()
                .id(payment.getId())
                .bookingId(payment.getBooking().getId())
                .transactionId(payment.getTransactionId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .paymentTime(payment.getPaymentTime())
                .build();

    }


}
