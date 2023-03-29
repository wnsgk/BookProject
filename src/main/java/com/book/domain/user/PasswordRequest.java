package com.book.domain.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PasswordRequest {

    private String curPassword;
    private String newPassword;

}
