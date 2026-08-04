package com.bookmyshow.Book_My_Show.exception;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String massage)
    {
        super(massage);
    }

}
