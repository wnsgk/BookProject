package com.book.exception.book;

import com.book.exception.CustomRuntimeException;

public class BookNotFoundException extends CustomRuntimeException {

    public BookNotFoundException(){
        super("책을 찾을 수 없습니다.");
        super.setName("BookNotFoundException");
    }

    public BookNotFoundException(String message){
        super(message);
        super.setName("BookNotFoundException");
    }
}
