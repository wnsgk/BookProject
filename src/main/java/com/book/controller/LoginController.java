package com.book.controller;

import com.book.domain.user.LoginDto;
import com.book.domain.user.UserCreateDto;
import com.book.service.LoginService;
import com.book.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(
            @Valid @RequestBody UserCreateDto userCreateDto) {
        userService.signUp(userCreateDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
        loginService.login(loginDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(){
        loginService.logout();

        return ResponseEntity.ok().build();
    }
}
