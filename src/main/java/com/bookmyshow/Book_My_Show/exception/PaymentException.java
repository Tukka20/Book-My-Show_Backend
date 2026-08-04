package com.bookmyshow.Book_My_Show.exception;

public class PaymentException extends RuntimeException{

    public PaymentException(String massage)
    {
        super(massage);
    }
}
