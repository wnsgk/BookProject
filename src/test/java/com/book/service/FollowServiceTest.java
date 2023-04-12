package com.book.service;

import com.book.domain.follow.Follow;
import com.book.domain.user.User;
import com.book.domain.user.UserCreateDto;
import com.book.exception.user.DuplicateEmailException;
import com.book.repository.FollowRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@Transactional
class FollowServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private FollowService followService;
    @Autowired
    private FollowRepository followRepository;

    private User user1;
    private User user2;

    @BeforeEach
    public void init() {
        UserCreateDto createDto1 = new UserCreateDto("kim2", "kim2@kim.com", "1234");
        user1 = userService.signUp(createDto1);

        UserCreateDto createDto2 = new UserCreateDto("kim3", "kim3@kim.com", "1234");
        user2 = userService.signUp(createDto2);
    }

    @Test
    @DisplayName("팔로우")
    void follow() {
        Follow follow = followService.follow(user2, user1);
        assertEquals(user1.getFollower().contains(follow), true);
        assertEquals(user2.getFollowing().contains(follow), true);
    }

    @Test
    @DisplayName("팔로우 중복")
    void duplicateFollow() {

        followService.follow(user1, user2);
        assertThrows(RuntimeException.class, () -> followService.follow(user1, user2));
    }

    @Test
    @DisplayName("팔로우 취소")
    void delete() {

        Follow follow = followService.follow(user1, user2);
        followService.followCancel(user1, user2);
        assertEquals(true, followRepository.findById(follow.getId()).isEmpty());
    }
}