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

    private String username;

    private String password;

    private String name;

    private String email;

    private LocalDateTime createAt;

    private String role;

    //oauth
    private String provider;
    private String providerId;

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
            if(book.getBook().getId().equals(bookId)) {
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
            if(book.getShelf().equals(name)) {
                books.add(book);
            }
        }
        return books;
    }

    @Builder
    public User(String username, String password, String name, String email, String role, String provider, String providerId){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void deleteUserBook(UserBook userBook){
        this.userBooks.remove(userBook);
    }

    public ProfileResponse toProfileResponse(boolean me){
        return ProfileResponse.builder()
                .email(this.email)
                .name(this.name)
                .me(me)
                .build();
    }
}
