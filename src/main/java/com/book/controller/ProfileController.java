package com.book.controller;

import com.book.config.auth.PrincipalDetails;
import com.book.domain.user.User;
import com.book.domain.user.dto.response.ProfileResDto;
import com.book.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;


    @GetMapping("/user/profile")
    public ResponseEntity<ProfileResDto> userProfile(@AuthenticationPrincipal PrincipalDetails userDetail) {
        User user = userDetail.getUser();
        ProfileResDto userProfile = userService.getUserProfile(user.getId());
        return ResponseEntity.ok(userProfile);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileResDto> profile(@PathVariable Long id){
        ProfileResDto userProfile = userService.getProfile(id);
        return ResponseEntity.ok(userProfile);
    }

}
