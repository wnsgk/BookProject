package com.book.domain.book;

import com.book.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserBookDto {

    private User user;

    private Book book;

    private Integer star;

    private String comment;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean open;

    private List<String> tags = new ArrayList<>();

    public UserBook toEntity(){
        return UserBook.builder()
                .user(this.user)
                .book(this.book)
                .star(this.star)
                .comment(this.comment)
                .open(this.open)
                .build();
    }
}
