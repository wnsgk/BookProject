package com.book.domain.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {

    private String name;

    private String email;

    private String password;

    public User toEntity(){
        return User.builder()
                .email(this.email)
                .name(this.name)
                .password(this.password)
                .build();
    }
}
