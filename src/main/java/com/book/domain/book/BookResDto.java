package com.book.domain.book;

import com.book.domain.MyBook.MyBookResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResDto{

    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String description;
    private String image;
    private String link;
    private Integer page;

    private List<MyBookResDto> bookList;

    public void addBook(MyBookResDto myBook) {
        bookList.add(myBook);
    }
}
