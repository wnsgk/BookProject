package com.book.domain.recommend.dto.request;

import lombok.Getter;

@Getter
public class RecommendUpdate {

    private Long id;
    private Long userId;
    private Long bookId;

    private String content;

}
