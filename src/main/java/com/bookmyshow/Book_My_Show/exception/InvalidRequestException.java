package com.bookmyshow.Book_My_Show.exception;

public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException(String massage)
    {
        super(massage);
    }
}
