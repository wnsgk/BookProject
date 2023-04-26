package com.book.domain.recommend.dto.response;

import lombok.Builder;

@Builder
public class RecommendResDto {

    private Long userId;
    private String nickname;
    private String userImage;
    private String title;
    private String bookImage;
    private String content;
}
