package com.book.domain.follow;

import com.book.domain.user.User;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Follow {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following")
    private User toUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower")
    private User fromUser;
}
