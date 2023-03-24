package com.book.domain.book;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Book {

    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String name;

    private String author;

    private String publisher;

    private String isbn;

    private Integer page;

    private String introduction;

    @OneToMany(mappedBy = "book")
    private List<UserBook> userBooks = new ArrayList<>();

    @Builder
    public Book(String name, String author, String publisher, String isbn, Integer page, String introduction){
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.page = page;
        this.introduction = introduction;
    }
}
