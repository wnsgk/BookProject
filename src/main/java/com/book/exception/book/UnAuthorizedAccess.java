package com.book.exception.book;

public class UnAuthorizedAccess extends RuntimeException{

    public UnAuthorizedAccess(String message){
        super(message);
    }
}
