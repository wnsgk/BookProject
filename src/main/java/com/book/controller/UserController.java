package com.book.controller;

import com.book.config.auth.PrincipalDetails;
import com.book.domain.user.*;
import com.book.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileResDto> profile(@AuthenticationPrincipal PrincipalDetails userDetail,
                                                 @PathVariable("id") Long id) {
        User user = userDetail.getUser();
        ProfileResDto userProfile = userService.getUserProfile(user, id);
        return ResponseEntity.ok(userProfile);
    }

    @PostMapping("/password")
    public ResponseEntity<Void> changePassword(@AuthenticationPrincipal PrincipalDetails userDetails,
                                               @RequestBody PasswordChangeDto password) {
        userService.updatePassword(userDetails.getUser().getId(), password);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/name")
    public ResponseEntity<Void> changeName(@AuthenticationPrincipal PrincipalDetails userDetails,
                                           @RequestBody UserUpdateDto updateDto) {
        userService.updateProfile(userDetails.getUser().getId(), updateDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal PrincipalDetails userDetails) {
        userService.deleteUser(userDetails.getUser().getId());

        return ResponseEntity.ok().build();
    }


}