package com.bookmyshow.Book_My_Show.exception;

public class DuplicateResourceFoundException extends RuntimeException{

    public DuplicateResourceFoundException(String massage)
    {
        super(massage);
    }
}
