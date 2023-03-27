package com.book.domain.book;

import com.book.domain.user.User;
import com.book.domain.tag.UserBookTag;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class UserBook {

    @Id @GeneratedValue
    @Column(name = "userBook_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private Integer star;

    private String comment;

    private String shelf;

    private LocalDateTime createTime;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean open;

    @PrePersist
    public void createTime(){
        this.createTime = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "userBook")
    private List<UserBookTag> tags = new ArrayList<>();

    public Period duration() {
        return Period.between(this.startDate.toLocalDate(), this.endDate.toLocalDate());
    }

    private enum Status {
        READING, READ, WISH
    }

    @Builder
    public UserBook(User user, Book book, Integer star, String comment, List<UserBookTag> tags, boolean open){
        this.user = user;
        this.book = book;
        this.star = star;
        this.comment = comment;
        this.tags = tags;
        this.open = open;
    }

    public void updateUserBook(UserBookDto userBookDto){
        this.star = userBookDto.getStar();
        this.comment = userBookDto.getComment();
    }

    //== 연관관계 메서드 ==//
    public void addUser(User user){
        this.user = user;
        user.getUserBooks().add(this);
    }

    public void addBook(Book book){
        this.book = book;
        book.getUserBooks().add(this);
    }

    public void addTag(UserBookTag tag){
        this.tags.add(tag);
    }
}
