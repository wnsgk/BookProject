package com.book.repository;

import com.book.domain.follow.Follow;
import com.book.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    boolean existsByFollowerAndFollowing(User following, User follower);

    void deleteByFollowerAndFollowing(User following, User follower);
}
