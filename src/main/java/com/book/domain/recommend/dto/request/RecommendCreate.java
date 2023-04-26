package com.book.domain.recommend.dto.request;

import lombok.Getter;

@Getter
public class RecommendCreate {

    private Long userId;
    private Long bookId;
    private String content;
}
