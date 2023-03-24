package com.book.domain.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {
    private String password;

    private String name;

    private String email;
}
