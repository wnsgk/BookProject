package com.book.domain.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchResDto {

    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String description;
    private String image;
    private String link;
    private Integer page;
}
