package com.book.domain.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProfileResponse {

    private String email;

    private String name;

    private boolean me;
}
