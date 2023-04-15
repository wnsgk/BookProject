package com.book.domain.book.dto.resposne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyBookResDto {

    private String title;
    private String author;
    private String image;
    private Integer star;
    private Long id;
}
