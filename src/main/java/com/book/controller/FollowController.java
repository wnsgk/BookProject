package com.book.controller;

import com.book.config.auth.PrincipalDetails;
import com.book.domain.user.User;
import com.book.service.FollowService;
import com.book.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @PostMapping("/follow/create")
    public ResponseEntity<Void> follow(@AuthenticationPrincipal PrincipalDetails userDetails,
                                       @RequestBody Long id){
        User follower = userService.findUser(id);
        followService.follow(userDetails.getUser(), follower);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/follow/delete")
    public ResponseEntity<Void> cancel(@AuthenticationPrincipal PrincipalDetails userDetails,
                                       @RequestBody Long id){
        User follower = userService.findUser(id);
        followService.followCancel(userDetails.getUser(), follower);

        return ResponseEntity.ok().build();
    }
}
