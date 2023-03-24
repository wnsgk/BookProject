package com.book.domain.book;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserBookDto {

    private Long userId;

    private Long bookId;

    private Integer star;

    private String comment;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean open;

    private List<String> tags = new ArrayList<>();
}
