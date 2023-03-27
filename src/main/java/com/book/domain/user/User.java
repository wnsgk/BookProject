package com.book.domain.user;

import com.book.domain.alarm.Alarm;
import com.book.domain.book.UserBook;
import com.book.domain.follow.Follow;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
//@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String password;

    private String name;

    private String email;

    private LocalDateTime createAt;

    @OneToMany(mappedBy = "user")
    private List<UserBook> userBooks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Alarm> alarms = new ArrayList<>();

//    private List<String> shelf = new ArrayList<>();

    @OneToMany(mappedBy = "toUser")
    private List<Follow> following = new ArrayList<>();

    @OneToMany(mappedBy = "fromUser")
    private List<Follow> follower = new ArrayList<>();


    @PrePersist
    public void createTime(){
        this.createAt = LocalDateTime.now();
    }

    public void updateName(UserUpdateDto updateDto){
        this.name = updateDto.getName();
    }

    public boolean findBook(Long bookId){
        for (UserBook book:this.userBooks) {
            if(book.getBook().getId() == bookId) {
                return true;
            }
        }
        return false;
    }

//    public void addShelf(String name){
//        shelf.add(name);
//    }

    public List<UserBook> getBookInShelf(String name){
        List<UserBook> books = new ArrayList<>();
        for(UserBook book : this.userBooks){
            if(book.getShelf() == name) {
                books.add(book);
            }
        }
        return books;
    }

//    public void deleteShelf(String name){
//        this.shelf.remove(name);
//    }

    @Builder
    public User(String password, String name, String email){
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
