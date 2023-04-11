package com.book.service;

import com.book.domain.follow.Follow;
import com.book.domain.user.User;
import com.book.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public boolean isFollowed(User following, User follower){
        if(followRepository.existsByFollowerAndFollowing(following, follower)){
            return true;
        }
        return false;
    }

    public void follow(User following, User follower){
        if(isFollowed(following, follower)){
            throw new RuntimeException("이미 팔로우 중인 유저입니다.");
        }
        Follow newFollow = Follow.builder()
                .following(following)
                .follower(follower).build();
        followRepository.save(newFollow);
    }

    public void followCancel(User following, User follower){
        if(!isFollowed(following, follower)){
            throw new RuntimeException("팔로우 중인 유저가 아닙니다.");
        }
        followRepository.deleteByFollowerAndFollowing(following, follower);
    }
}
