package com.book.domain.book;

import com.book.domain.MyBook.MyBook;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String title;

    private String author;

    private String publisher;

    private String isbn;

    private Integer page;

    private String description;

    private String url;

    private String image;

    @OneToMany(mappedBy = "book")
    private List<MyBook> myBooks = new ArrayList<>();

    @Builder
    public Book(String title, String author, String publisher, String isbn, Integer page, String description, String url, String image){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.page = page;
        this.description = description;
        this.url = url;
        this.image = image;
    }

    public void deleteUserBook(MyBook myBook){
        this.myBooks.remove(myBook);
    }

    public int numberOfUser(){
        return myBooks.size();
    }

    public BookResDto toResDto(){
        BookResDto bookResDto = BookResDto.builder()
                .image(image)
                .description(description)
                .link(url)
                .publisher(publisher)
                .author(author)
                .page(page)
                .title(title)
                .isbn(isbn)
                .build();
        return bookResDto;
    }
}
