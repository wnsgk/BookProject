package com.book.domain.recommend;

import com.book.domain.book.Book;
import com.book.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Recommend {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private String content;

    @Builder
    public Recommend(User user, Book book, String content){
        this.book = book;
        this.user = user;
        this.content = content;
    }

    public void addRec(){
        this.user.addRec(this);
        this.book.addRec(this);
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
