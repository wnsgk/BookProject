package com.book.domain.user;

import lombok.Data;

@Data
public class LoginDto {

    private String password;

    private String email;
}
