package com.book.domain.MyBook;

import com.book.domain.book.Book;
import com.book.domain.book.BookStatus;
import com.book.domain.user.User;
import com.book.domain.tag.MyBookTag;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyBook {

    @Id @GeneratedValue
    @Column(name = "myBook_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private String isbn;

    private Integer star;

    private String comment;

    private String shelf;

    private LocalDateTime createTime;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean open;

    @PrePersist
    public void createTime(){
        this.createTime = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "myBook")
    private List<MyBookTag> tags = new ArrayList<>();

    public Period duration() {
        return Period.between(this.startDate, this.endDate);
    }

    private BookStatus bookStatus;

    @Builder
    public MyBook(User user, Book book, Integer star, String isbn, String comment, BookStatus status,
                  LocalDate startDate, LocalDate endDate, boolean open){
        this.user = user;
        this.book = book;
        this.isbn = isbn;
        this.star = star;
        this.comment = comment;
        this.bookStatus = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.open = open;
    }

    public void update(MyBookUpdateDto myBookDto){
        this.star = myBookDto.getStar();
        this.comment = myBookDto.getComment();
        this.startDate = myBookDto.getStartDate();
        this.endDate = myBookDto.getEndDate();
        this.bookStatus = myBookDto.getBookStatus();
    }

    //== 연관관계 메서드 ==//
    public void addUser(User user){
        this.user = user;
        user.getMyBooks().add(this);
    }

    public void addBook(Book book){
        this.book = book;
        book.getMyBooks().add(this);
    }

    public void addTag(MyBookTag tag){
        this.tags.add(tag);
    }

    public void deleteTag(MyBookTag tag){
        this.tags.remove(tag);
    }

    public void deleteBook(){
        this.book.deleteMyBook(this);
        this.user.deleteMyBook(this);
    }

    public MyBookResDto toResDto(){
        return MyBookResDto.builder()
                .title(this.book.getTitle())
                .image(this.book.getImage())
                .star(this.star)
                .build();
    }
}
