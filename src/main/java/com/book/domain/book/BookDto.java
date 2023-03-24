package com.book.domain.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

    public String name;

    public String author;

    private String publisher;

    private String isbn;

    private Integer page;

    private String introduction;

}
