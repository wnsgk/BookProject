package com.book.exception.book;

public class TagNotFoundException extends RuntimeException{

    public TagNotFoundException(String message){
        super(message);
    }
}
